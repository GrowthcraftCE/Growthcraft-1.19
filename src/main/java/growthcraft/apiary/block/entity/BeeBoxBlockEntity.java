package growthcraft.apiary.block.entity;

import growthcraft.apiary.init.GrowthcraftApiaryBlockEntities;
import growthcraft.apiary.init.GrowthcraftApiaryItems;
import growthcraft.apiary.init.GrowthcraftApiaryTags;
import growthcraft.apiary.init.config.GrowthcraftApiaryConfig;
import growthcraft.apiary.screen.BeeBoxMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class BeeBoxBlockEntity extends BlockEntity implements BlockEntityTicker<BeeBoxBlockEntity>, MenuProvider {

    private int tickClock = 0;
    private int tickMax;

    private Component customName;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(28) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return (slot == 0 && stack.is(GrowthcraftApiaryTags.Items.BEE)) ||
                    ( slot > 0 && slot <=27 && stack.is(GrowthcraftApiaryTags.Items.HONEY_COMB)) ;
        }
    };

    private LazyOptional<IItemHandler> itemHandlerLazyOptional = LazyOptional.empty();

    public BeeBoxBlockEntity(BlockPos pos, BlockState state) {
        this(GrowthcraftApiaryBlockEntities.BEE_BOX_BLOCK_ENTITY.get(), pos, state, GrowthcraftApiaryConfig.getBeeBoxMaxProcessingTime());
    }

    public BeeBoxBlockEntity(BlockEntityType<?> blockEntityType,  BlockPos pos, BlockState state, int tickMax) {
        super(blockEntityType, pos, state);
        this.tickMax = tickMax;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return new TranslatableComponent("container.growthcraft_apiary.bee_box");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new BeeBoxMenu(containerId, inventory, this);
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState blockState, BeeBoxBlockEntity beeBoxBlockEntity) {
        if(!level.isClientSide && this.itemStackHandler.getStackInSlot(0).getCount() > 0) {
            SecureRandom random = new SecureRandom();

            if (tickClock >= tickMax) {
                int workers = this.itemStackHandler.getStackInSlot(0).getCount();

                // Try and increase the bee population.
                if (workers < 64 && random.nextInt(100) <= GrowthcraftApiaryConfig.getChanceToIncreaseBees()) {
                    this.itemStackHandler.getStackInSlot(0).grow(1);
                }

                // For each worker bee try to do a task
                for (int i = 0; i < workers; i++) {
                    int jobID = random.nextInt(4);

                    switch (jobID) {
                        case 0:
                            // Do Nothing
                            break;
                        case 1:
                            // Check for comb conversion
                            int slotNeedsCombConversion = getSlotWithVanillaHoneyComb();

                            if (slotNeedsCombConversion < 0) {
                                slotNeedsCombConversion = getSlotWithEmptyHoneyComb();
                            }

                            // Try and fill an empty honey comb.
                            if (slotNeedsCombConversion > 0) {
                                this.itemStackHandler.setStackInSlot(
                                    slotNeedsCombConversion,
                                    new ItemStack(GrowthcraftApiaryItems.HONEY_COMB_FULL.get())
                                );
                            }
                            break;
                        case 2:
                            // Try and duplicate any FlowerBlock
                            if (GrowthcraftApiaryConfig.shouldReplicateFlowers()) {
                                // search the surrounding area for FlowerBlock.
                                tryReplicateFlower(level, pos);
                            }
                            break;
                        default:
                            // Add new empty honey comb.
                            int emptySlotID = getEmptySlot();
                            if (emptySlotID > 0) {
                                this.itemStackHandler.setStackInSlot(
                                    emptySlotID,
                                    new ItemStack(GrowthcraftApiaryItems.HONEY_COMB_EMPTY.get())
                                );
                            }

                    }
                }

                this.tickClock = 0;
            } else {
                this.tickClock++;
            }
        }
    }

    private int getSlotWithVanillaHoneyComb() {
        for(int slotID = 1; slotID < this.itemStackHandler.getSlots(); slotID++) {
            if(this.itemStackHandler.getStackInSlot(slotID).getItem() == Items.HONEYCOMB) return slotID;
        }
        return -1;
    }

    private int getSlotWithEmptyHoneyComb() {
        for(int slotID = 1; slotID < this.itemStackHandler.getSlots(); slotID++) {
            if(this.itemStackHandler.getStackInSlot(slotID).getItem() == GrowthcraftApiaryItems.HONEY_COMB_EMPTY.get()) return slotID;
        }
        return -1;
    }

    private int getEmptySlot() {
        for(int slotID = 1; slotID < this.itemStackHandler.getSlots(); slotID++) {
            if(this.itemStackHandler.getStackInSlot(slotID).isEmpty()) return slotID;
        }
        return -1;
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
        this.load(  pkt.getTag());
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

    private void tryReplicateFlower(Level level, BlockPos pos) {
        int beeBoxFlowerRange = GrowthcraftApiaryConfig.getBeeBoxFlowerRange();

        List<BlockState> flowerBlocks = new ArrayList<>();
        List<BlockPos> airBlocks = new ArrayList<>();

        BlockPos lowerBoundPos = pos.below(2).south(beeBoxFlowerRange).west(beeBoxFlowerRange);
        BlockPos upperBoundPos = pos.above(2).north(beeBoxFlowerRange).east(beeBoxFlowerRange);

        for(BlockPos blockpos : BlockPos.betweenClosed(lowerBoundPos, upperBoundPos)) {
            BlockPos surroundingPos = blockpos.immutable();

            if (level.getBlockState(surroundingPos).is(BlockTags.FLOWERS)) {
                flowerBlocks.add(level.getBlockState(surroundingPos));
            } else if (level.getBlockState(surroundingPos).isAir() && level.getBlockState(surroundingPos.below()).is(BlockTags.DIRT)) {
                airBlocks.add(surroundingPos);
            } else {
                // Do nothing
            }

        }

        SecureRandom random = new SecureRandom();
        int rand = random.nextInt(100);

        if(!flowerBlocks.isEmpty() && !airBlocks.isEmpty() && rand <= GrowthcraftApiaryConfig.getChanceToReplicateFlowers()) {
            int randomFlower = level.getRandom().nextInt(flowerBlocks.size());
            int randomAirBlock = level.getRandom().nextInt(airBlocks.size());

            level.setBlock(airBlocks.get(randomAirBlock), flowerBlocks.get(randomFlower), Block.UPDATE_ALL_IMMEDIATE);
        }

    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandlerLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    public void dropItems() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.getLevel(), this.worldPosition, inventory);
    }

}
