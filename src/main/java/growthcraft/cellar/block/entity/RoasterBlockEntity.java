package growthcraft.cellar.block.entity;

import growthcraft.cellar.init.GrowthcraftCellarBlockEntities;
import growthcraft.cellar.init.GrowthcraftCellarTags;
import growthcraft.cellar.recipe.RoasterRecipe;
import growthcraft.cellar.screen.container.RoasterMenu;
import growthcraft.lib.utils.BlockStateUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
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

import static growthcraft.cellar.block.CultureJarBlock.LIT;
import static growthcraft.cellar.block.RoasterBlock.ROASTING_LEVEL;

public class RoasterBlockEntity extends BlockEntity implements BlockEntityTicker<RoasterBlockEntity>, MenuProvider {

    private int tickClock = 0;
    private int tickMax = -1;
    private int currentRoastingLevel = 1;

    protected final ContainerData data;

    private Component customName;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return (slot == 0 && stack.is(GrowthcraftCellarTags.Items.GRAIN));
        }
    };

    private LazyOptional<IItemHandler> itemHandlerLazyOptional = LazyOptional.empty();

    public RoasterBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(GrowthcraftCellarBlockEntities.ROASTER_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public RoasterBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
        // tickMax is determined by the level of roasting.
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> RoasterBlockEntity.this.tickClock;
                    case 1 -> RoasterBlockEntity.this.tickMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> RoasterBlockEntity.this.tickClock = value;
                    case 1 -> RoasterBlockEntity.this.tickMax = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public boolean isHeated() {
        boolean heated = BlockStateUtils.isHeated(this.level, this.getBlockPos());
        // Only change the blockstate if it is different.
        if(Boolean.TRUE.equals(this.getBlockState().getValue(LIT)) != heated) {
            if(level != null) this.level.setBlock(this.getBlockPos(), this.getBlockState().setValue(LIT, heated), Block.UPDATE_ALL);
        }
        return heated;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return this.customName != null
                ? this.customName
                : Component.translatable("container.growthcraft_cellar.roaster");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new RoasterMenu(containerId, inventory, this, this.data);
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, RoasterBlockEntity blockEntity) {
        // TODO[9]: Implement Roaster ticking and processing methods.

        if(!level.isClientSide && isHeated() && !this.itemStackHandler.getStackInSlot(0).isEmpty()) {

            List<RoasterRecipe> recipes = this.getMatchingRecipes();
            RoasterRecipe recipe = recipes.get(0);

            if(recipe != null) {
                if(this.tickClock <= this.tickMax) {
                    this.tickClock++;
                } else if(this.tickMax > 0) {
                    int itemCount = this.itemStackHandler.getStackInSlot(0).getCount();
                    ItemStack resultItemStack = recipe.getResultItem().copy();
                    resultItemStack.setCount(itemCount);

                    this.itemStackHandler.setStackInSlot(
                            1,
                            resultItemStack
                    );

                    // Shrink the input item count.
                    this.itemStackHandler.getStackInSlot(0).shrink(itemCount);

                    // Reset the tick counters and notify all surrounding blocks
                    this.resetTickClock();
                    level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
                } else if(this.tickMax == -1) {
                    // Roaster processing is based on the roaster level times 600.
                    // Roaster level of 1 will take 600 ticks (30 secs) to process
                    // whereas level 8 will take 4800 ticks (4 minutes) to process.
                    this.tickMax = recipe.getRecipeProcessingTime() * 30 * 20;
                } else {
                    this.resetTickClock();
                }

                level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
            }
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

    private List<RoasterRecipe> getMatchingRecipes() {
        List<RoasterRecipe> matchingRecipes = new ArrayList<>();

        List<RoasterRecipe> recipes = level.getRecipeManager()
                .getAllRecipesFor(RoasterRecipe.Type.INSTANCE);

        for(RoasterRecipe recipe : recipes) {
            if(recipe.matches(this.itemStackHandler.getStackInSlot(0), this.getCurrentRoastingLevel() )) {
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

    public int getCurrentRoastingLevel() {
        return this.getBlockState().getValue(ROASTING_LEVEL);
    }


}
