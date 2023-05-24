package growthcraft.milk.block.entity;

import growthcraft.milk.block.CheesePressBlock;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import growthcraft.milk.recipe.CheesePressRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheesePressBlockEntity extends BlockEntity implements BlockEntityTicker<CheesePressBlockEntity> {

    private int tickClock = 0;
    private int tickMax = -1;

    private boolean open;
    private int rotation;

    private Component customName;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> itemHandlerLazyOptional = LazyOptional.empty();

    public CheesePressBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(GrowthcraftMilkBlockEntities.CHEESE_PRESS_BLOCK_ENTITY.get(),
                blockPos, blockState);
    }

    public CheesePressBlockEntity(BlockEntityType<?> entityType, BlockPos blockPos, BlockState blockState) {
        super(entityType, blockPos, blockState);
        this.open = true;
        this.rotation = 0;
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, CheesePressBlockEntity blockEntity) {
        if(!level.isClientSide && !this.open && !this.getItemStackHandler().getStackInSlot(0).isEmpty()) {
            try {
                List<CheesePressRecipe> recipes = this.getMatchingRecipes();
                CheesePressRecipe recipe = recipes.get(0);

                if(recipe != null) {
                    if(this.tickClock <= this.tickMax) {
                        this.tickClock++;
                    } else if (this.tickMax > 0) {
                        // Process the recipe results.
                        blockEntity.getItemStackHandler().setStackInSlot(0, ItemStack.EMPTY);
                        blockEntity.getItemStackHandler().setStackInSlot(1, recipe.getResultItemStack());
                    } else if(this.tickMax == -1) {
                        this.tickMax = recipe.getProcessingTime();
                    } else {
                        this.resetTickClock();
                    }

                    level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL_IMMEDIATE);
                }
            } catch (Exception ex) {
                // Do nothing, just ignore it.
            }
        } else {
            this.resetTickClock();
        }
    }

    public int getTickClock(String type) {
        switch (type) {
            case "current":
                return this.tickClock;
            case "max":
                return this.tickMax;
            default:
                return 0;
        }
    }

    private void resetTickClock() {
        this.tickClock = 0;
        this.tickMax = -1;
    }


    public int doRotation(boolean increase) {
        // Can't increase, already at max.
        if (this.rotation == 7 && increase) return this.getRotation();
        // Can't decrease, already at min.
        if (this.rotation == 0 && !increase) return this.getRotation();

        if (increase) {
            this.rotation++;
        } else {
            // Fast track to 0
            this.rotation = 0;
        }
        this.open = this.rotation == 0;

        return this.getRotation();
    }

    private List<CheesePressRecipe> getMatchingRecipes() {
        if(level == null) return null;

        List<CheesePressRecipe> matchingRecipes = new ArrayList<>();

        List<CheesePressRecipe> recipes = level.getRecipeManager()
                .getAllRecipesFor(CheesePressRecipe.Type.INSTANCE);

        for (CheesePressRecipe recipe : recipes) {
            if (recipe.matches(this.itemStackHandler.getStackInSlot(0))) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;
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
        this.itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.tickClock = nbt.getInt("CurrentProcessTicks");
        this.rotation = nbt.getInt("rotation");
        this.tickMax = nbt.getInt("MaxProcessTicks");

        if (nbt.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.put("inventory", this.itemStackHandler.serializeNBT());
        nbt.putInt("CurrentProcessTicks", this.tickClock);
        nbt.putInt("rotation", this.rotation);
        nbt.putInt("MaxProcessTicks", this.tickMax);

        if (this.customName != null) {
            nbt.putString("CustomName", Component.Serializer.toJson(this.customName));
        }
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    @Override
    public void onLoad() {
        super.onLoad();
        itemHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandlerLazyOptional.invalidate();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemHandlerLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    public void dropItems() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());

        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(
                Objects.requireNonNull(this.getLevel()),
                this.worldPosition,
                inventory
        );
    }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    public int getRotation() {
        return this.rotation;
    }

    public boolean isOpen() {
        return this.open;
    }

    public static <E extends BlockEntity> void particleTick(Level level, BlockPos blockPos, BlockState blockState, CheesePressBlockEntity blockEntity) {
        RandomSource randomsource = level.random;
        if (randomsource.nextFloat() < 0.11F) {
            for (int i = 0; i < randomsource.nextInt(2) + 2; ++i) {
                CheesePressBlock.makeParticles(level, blockPos, blockState);
            }
        }
    }

}
