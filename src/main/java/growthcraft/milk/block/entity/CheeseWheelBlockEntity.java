package growthcraft.milk.block.entity;

import growthcraft.milk.block.CheeseWheelBlock;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import growthcraft.milk.recipe.CheesePressRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheeseWheelBlockEntity extends BlockEntity implements BlockEntityTicker<CheeseWheelBlockEntity> {
    private boolean aged;
    private int sliceCountTop;
    private int sliceCountBottom;

    private int tickClock = 0;
    //TODO: Make max aging for CheeseWheel come from a config
    private int tickMax = -1;

    private Component customName;

    public CheeseWheelBlockEntity (BlockPos blockPos, BlockState blockState) {
        this(GrowthcraftMilkBlockEntities.CHEESE_WHEEL_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public CheeseWheelBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        this.tickClock = 0;
        this.tickMax = 24000 * 3;

        // TODO: Implement aging process
        this.aged = true;
        this.sliceCountBottom = 4;
        this.sliceCountTop = 0;
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, CheeseWheelBlockEntity blockEntity) {
        if (level != null && !level.isClientSide() && Boolean.FALSE.equals(level.getBlockState(blockPos).getValue(CheeseWheelBlock.AGED))) {
            if (this.tickClock < tickMax) {
                this.tickClock++;
            } else if (tickMax != -1){
                BlockState state = this.level.getBlockState(blockPos);
                this.level.setBlock(blockPos, state.setValue(CheeseWheelBlock.AGED, true), Block.UPDATE_ALL_IMMEDIATE);
                this.tickClock = 0;
                this.tickMax = -1;
            }
        } else {
            // The cheese is aged, so there's nothing to do.
        }
    }

    private void setBlockState(int bottomSlices, int topSlices) {
        this.level.setBlock(
                this.getBlockPos(),
                this.getBlockState()
                    .setValue(CheeseWheelBlock.SLICE_COUNT_TOP, topSlices)
                    .setValue(CheeseWheelBlock.SLICE_COUNT_BOTTOM, bottomSlices),
                Block.UPDATE_ALL_IMMEDIATE
        );
    }

    public boolean canTakeSlice() {
        return this.aged && this.getSliceCount() > 0;
    }

    public ItemStack takeSlice() {
        List<CheesePressRecipe> cheesePressRecipes = this.getMatchingRecipes(
                new ItemStack(this.getBlockState().getBlock().asItem())
        );

        CheesePressRecipe recipe = cheesePressRecipes.get(0);

        if(recipe != null) {
            this.takeSlice(1);
            return recipe.getSliceItemStack();
        }

        return null;
    }

    private List<CheesePressRecipe> getMatchingRecipes(ItemStack itemStack) {
        List<CheesePressRecipe> matchingRecipes = new ArrayList<>();

        List<CheesePressRecipe> recipes = level.getRecipeManager().getAllRecipesFor(
                CheesePressRecipe.Type.INSTANCE
        );

        for (CheesePressRecipe recipe : recipes) {
            if (recipe.matchesOutput(itemStack)) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }

    public void takeSlice(int count) {
        if (this.sliceCountTop > 0) {
            this.sliceCountTop -= count;
        } else if (this.sliceCountBottom > 0) {
            this.sliceCountBottom -= count;
        }
        this.setBlockState(this.sliceCountBottom, this.sliceCountTop);
    }

    public void addSlice(int count) {
        int newTotal = this.sliceCountBottom + this.sliceCountTop + count;

        if (newTotal > 4) {
            // Then we have enough room to add the slice to the stack.
            this.sliceCountBottom = 4;
            this.sliceCountTop = newTotal - this.sliceCountBottom;
        } else {
            this.sliceCountBottom = newTotal;
            this.sliceCountTop = 0;
        }

        this.setBlockState(this.sliceCountBottom, this.sliceCountTop);
    }

    public int getSliceCount() {
        return this.sliceCountTop + this.sliceCountBottom;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return this.serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.tickClock = nbt.getInt("CurrentProcessTicks");
        this.tickMax = nbt.getInt("MaxProcessTicks");
        this.sliceCountTop = nbt.getInt("SliceCountTop");
        this.sliceCountBottom = nbt.getInt("SliceCountBottom");
        this.aged = nbt.getBoolean("aged");

        if (nbt.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("CurrentProcessTicks", this.tickClock);
        nbt.putInt("MaxProcessTicks", this.tickMax);
        nbt.putInt("slicestop", this.sliceCountTop);
        nbt.putInt("slicesbottom", this.sliceCountBottom);
        nbt.putBoolean("aged", this.aged);

        if (this.customName != null) {
            nbt.putString("CustomName", Component.Serializer.toJson(this.customName));
        }

        super.saveAdditional(nbt);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(Objects.requireNonNull(pkt.getTag()));
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
    }
}
