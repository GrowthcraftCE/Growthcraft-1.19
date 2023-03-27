package growthcraft.apiary.screen;

import growthcraft.apiary.block.BeeBoxBlock;
import growthcraft.apiary.block.entity.BeeBoxBlockEntity;
import growthcraft.apiary.init.GrowthcraftApiaryMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class BeeBoxMenu extends AbstractContainerMenu {

    private final BeeBoxBlockEntity blockEntity;
    private final BeeBoxBlock block;
    private final int PLAYER_HOTBAR_BASE_X = 8;
    private final int PLAYER_HOTBAR_BASE_Y = 176;
    private final int PLAYER_INVENTORY_BASE_X = 8;
    private final int PLAYER_INVENTORY_BASE_Y = 118;

    private final Level level;

    public BeeBoxMenu(int containerID, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerID, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public BeeBoxMenu(int containerID, Inventory inventory, BlockEntity blockEntity) {
        super(GrowthcraftApiaryMenus.BEE_BOX_MENU.get(), containerID);

        this.blockEntity = (BeeBoxBlockEntity) blockEntity;
        this.block = (BeeBoxBlock) inventory.player.level.getBlockEntity(this.blockEntity.getBlockPos()).getBlockState().getBlock();
        this.level = inventory.player.level;

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                int index = 0;
                int itemSlotWidth = 18;

                // Bee Slot
                this.addSlot(new SlotItemHandler(handler, 0, 80, 18));
                index++;

                // Honey Comb Slots
                int outputSlotBaseY = 50;
                int outputSlotBaseX = 8;
                for (int row = 0; row < 3; row++) {
                    for (int column = 0; column < 9; column++) {
                        int slotIndex = 9 + (row * 9) + column;

                        SlotItemHandler slot = new SlotItemHandler(
                                handler,
                                index,
                                outputSlotBaseX + (column * itemSlotWidth),
                                outputSlotBaseY + (row * itemSlotWidth)
                        );

                        this.addSlot(slot);
                        index++;
                    }
                }
            }
        );
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
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

    private static final int TE_INVENTORY_SLOT_COUNT = 28;  // must be the number of slots you have!

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
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, PLAYER_INVENTORY_BASE_X + l * 18, PLAYER_INVENTORY_BASE_Y + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, PLAYER_HOTBAR_BASE_X + i * 18, PLAYER_HOTBAR_BASE_Y));
        }
    }
}
