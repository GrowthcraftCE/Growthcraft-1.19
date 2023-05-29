package growthcraft.milk.screen.container;

import growthcraft.milk.block.PancheonBlock;
import growthcraft.milk.block.entity.PancheonBlockEntity;
import growthcraft.milk.init.GrowthcraftMilkMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

public class PancheonMenu extends AbstractContainerMenu {
    private final PancheonBlockEntity blockEntity;
    private final PancheonBlock block;
    private final Level level;
    private final ContainerData data;

    private FluidStack fluidStack0;
    private FluidStack fluidStack1;
    private FluidStack fluidStack2;

    public PancheonMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public PancheonMenu(int containerId, Inventory inventory, BlockEntity blockEntity , ContainerData data) {
        super(GrowthcraftMilkMenus.PANCHEON_MENU.get(), containerId);

        this.blockEntity = (PancheonBlockEntity) blockEntity;
        this.block = (PancheonBlock) inventory.player.level.getBlockEntity(this.blockEntity.getBlockPos()).getBlockState().getBlock();
        this.level = inventory.player.level;
        this.data = data;

        this.fluidStack0 = this.blockEntity.getFluidStackInTank(0);
        this.fluidStack1 = this.blockEntity.getFluidStackInTank(1);
        this.fluidStack2 = this.blockEntity.getFluidStackInTank(2);

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        addDataSlots(data);
    }

    public PancheonBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots, the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = 0;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(
                ContainerLevelAccess.create(
                        this.level,
                        this.blockEntity.getBlockPos()
                ),
                player,
                this.block
        );
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public void setFluid(int tankID, FluidStack fluidStack) throws NullPointerException {
        switch (tankID) {
            case 0 -> this.fluidStack0 = fluidStack;
            case 1 -> this.fluidStack1 = fluidStack;
            case 2 -> this.fluidStack2 = fluidStack;
            default ->
                    throw new NullPointerException(String.format("PancheonMenu setFluidStack at <%s> does not have a fluid tank with the ID of %d!", blockEntity.getBlockPos().toString(), tankID));
        }
    }
    
    public FluidStack getFluidStack(int tankID) {
        return switch (tankID) {
            case 0 -> this.blockEntity.getFluidStackInTank(0);
            case 1 -> this.blockEntity.getFluidStackInTank(1);
            case 2 -> this.blockEntity.getFluidStackInTank(2);
            default ->
                    throw new NullPointerException(String.format("PancheonMenu getFluidStack at <%s> does not have a fluid tank with the ID of %d!", blockEntity.getBlockPos().toString(), tankID));
        };
    }

    @OnlyIn(Dist.CLIENT)
    public int getProgressionScaled(int size) {
        return this.blockEntity.getTickClock("current") != 0 && this.blockEntity.getTickClock("max") != 0
                ? this.blockEntity.getTickClock("current") * size / this.blockEntity.getTickClock("max")
                : 0;
    }


}
