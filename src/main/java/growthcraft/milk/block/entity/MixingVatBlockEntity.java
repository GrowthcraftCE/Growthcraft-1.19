package growthcraft.milk.block.entity;

import growthcraft.lib.block.entity.GrowthcraftFluidTank;
import growthcraft.lib.utils.DirectionUtils;
import growthcraft.lib.utils.RecipeUtils;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import growthcraft.milk.lib.networking.GrowthcraftMilkMessages;
import growthcraft.milk.lib.networking.packet.MixingVatFluidSyncPacket;
import growthcraft.milk.recipe.MixingVatFluidRecipe;
import growthcraft.milk.recipe.MixingVatItemRecipe;
import growthcraft.milk.screen.container.MixingVatMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MixingVatBlockEntity extends BlockEntity implements BlockEntityTicker<MixingVatBlockEntity>, MenuProvider {
    //TODO[5]: Implement MixingVatBlockEntity
    private int tickClock = 0;
    private int tickMax = -1;

    protected final ContainerData data;

    private Component customName;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> inventoryHandler = LazyOptional.empty();

    private final GrowthcraftFluidTank inputFluidTank = new GrowthcraftFluidTank(4000) {
        @Override
        public void onContentsChanged() {
            setChanged();
            if (level != null && !level.isClientSide) {
                GrowthcraftMilkMessages.sendToClients(new MixingVatFluidSyncPacket(0, this.fluid, worldPosition));
            }
        }
    };

    private final GrowthcraftFluidTank reagentFluidTank = new GrowthcraftFluidTank(1000) {
        @Override
        public void onContentsChanged() {
            setChanged();
            if (level != null && !level.isClientSide) {
                GrowthcraftMilkMessages.sendToClients(new MixingVatFluidSyncPacket(1, this.fluid, worldPosition));
            }
        }
    };

    private LazyOptional<IFluidHandler> inputFluidHandler = LazyOptional.empty();
    private LazyOptional<IFluidHandler> reagentFluidHandler = LazyOptional.empty();

    public MixingVatBlockEntity(BlockPos blockPos, BlockState state) {
        super(GrowthcraftMilkBlockEntities.MIXING_VAT_BLOCK_ENTITY.get(), blockPos, state);

        this.inputFluidTank.allowAnyFluid(true);
        this.reagentFluidTank.allowAnyFluid(true);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MixingVatBlockEntity.this.tickClock;
                    case 1 -> MixingVatBlockEntity.this.tickMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> MixingVatBlockEntity.this.tickClock = value;
                    case 1 -> MixingVatBlockEntity.this.tickMax = value;
                    default -> {
                        // Do Nothing
                    }
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull Component getDisplayName() {
        return this.customName != null
                ? this.customName
                : Component.translatable("container.growthcraft_milk.mixing_vat");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new MixingVatMenu(containerId, inventory, this, this.data);
    }

    public boolean canActivateProcessing(ItemStack itemStack) {
        List<MixingVatFluidRecipe> fluidRecipes = this.getMatchingFluidRecipes();
        MixingVatFluidRecipe fluidRecipe = fluidRecipes.isEmpty() ? null : fluidRecipes.get(0);

        if(fluidRecipe != null) {
            return fluidRecipe.getActivationTool().is(itemStack.getItem());
        }

        List<MixingVatItemRecipe> itemRecipes = this.getMatchingItemRecipes();
        MixingVatItemRecipe itemRecipe = itemRecipes.isEmpty() ? null : itemRecipes.get(0);

        if(itemRecipe != null) {
            // Then we have an Item recipe.
            return itemRecipe.getActivationTool().is(itemStack.getItem());
        }

        return false;
    }

    public boolean canActivateResultItem(ItemStack itemStack) {
        List<MixingVatItemRecipe> itemRecipes = this.getMatchingItemRecipes();
        MixingVatItemRecipe itemRecipe = itemRecipes.isEmpty() ? null : itemRecipes.get(0);

        if(itemRecipe != null) {
            // Then we have an Item recipe.
            return itemRecipe.getResultActivationTool().is(itemStack.getItem());
        }
        return false;
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, MixingVatBlockEntity mixingVatBlockEntity) {
        if (level.isClientSide) return;

        RecipeUtils.Category recipeCategory = RecipeUtils.Category.NULL;

        // First check for a MixingVatFluidRecipe
        List<MixingVatFluidRecipe> fluidRecipes = this.getMatchingFluidRecipes();
        MixingVatFluidRecipe fluidRecipe = fluidRecipes.isEmpty() ? null : fluidRecipes.get(0);

        if(fluidRecipe != null) {
            // Then we have a Fluid recipe.
            recipeCategory = fluidRecipe.getCategory();
        }

        List<MixingVatItemRecipe> itemRecipes = this.getMatchingItemRecipes();
        MixingVatItemRecipe itemRecipe = itemRecipes.isEmpty() ? null : itemRecipes.get(0);

        if(fluidRecipe == null && itemRecipe != null) {
            // Then we have an Item recipe.
            recipeCategory = itemRecipe.getCategory();
        }

        if (RecipeUtils.isNotNull(recipeCategory) && this.tickClock <= this.tickMax) {
            this.tickClock++;
        } else if (RecipeUtils.isNotNull(recipeCategory) && this.tickMax > 0) {

            if(fluidRecipe != null) {
                // Consume all of the inventory items.
                this.itemStackHandler.setStackInSlot(0, ItemStack.EMPTY);
                this.itemStackHandler.setStackInSlot(1, ItemStack.EMPTY);
                this.itemStackHandler.setStackInSlot(2, ItemStack.EMPTY);

                // Set the output fluid and any reagent/waste fluid.
                this.inputFluidTank.setFluid(fluidRecipe.getReagentFluidStack().copy());
                this.reagentFluidTank.setFluid(fluidRecipe.getWasteFluidStack().copy());

            } else if(itemRecipe != null) {
                // Consume all the inventory items and fluid.
                this.itemStackHandler.setStackInSlot(0, ItemStack.EMPTY);
                this.itemStackHandler.setStackInSlot(1, ItemStack.EMPTY);
                this.itemStackHandler.setStackInSlot(2, itemRecipe.getResultItemStack().copy());
                this.inputFluidTank.setFluid(FluidStack.EMPTY);
                this.reagentFluidTank.setFluid(FluidStack.EMPTY);


            }

            this.resetTickClock();
        } else if (RecipeUtils.isNotNull(recipeCategory) && this.tickMax == -1) {
            // Then we have a new recipe that needs to start processing.
            this.tickMax = switch(recipeCategory) {
                case ITEM -> itemRecipe.getProcessingTime();
                case FLUID -> fluidRecipe.getProcessingTime();
                default -> -1;
            };
        } else {
            this.resetTickClock();
        }


    }

    public int getTickClock(String type) {
        return switch (type) {
            case "current" -> this.tickClock;
            case "max" -> this.tickMax;
            default -> 0;
        };
    }

    private void resetTickClock() {
        this.tickClock = 0;
        this.tickMax = -1;
    }

    private List<MixingVatFluidRecipe> getMatchingFluidRecipes() {
        if (level == null) return Collections.emptyList();

        List<MixingVatFluidRecipe> matchingRecipes = new ArrayList<>();

        // Place the input slots into a List.
        List<ItemStack> currentItems = new ArrayList<>();
        for (int i = 0; i < itemStackHandler.getSlots() - 1; i++) {
            if (!itemStackHandler.getStackInSlot(i).isEmpty()) currentItems.add(itemStackHandler.getStackInSlot(i));
        }

        List<MixingVatFluidRecipe> recipes = this.level.getRecipeManager().getAllRecipesFor(
                MixingVatFluidRecipe.Type.INSTANCE
        );

        for(MixingVatFluidRecipe recipe : recipes) {
            if (recipe.matches(
                    this.inputFluidTank.getFluid().copy(),
                    this.reagentFluidTank.getFluid().copy(),
                    currentItems)
            ) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;
    }

    private List<MixingVatItemRecipe> getMatchingItemRecipes() {
        if (level == null) return Collections.emptyList();

        List<MixingVatItemRecipe> matchingRecipes = new ArrayList<>();

        // Place the input slots into a List.
        List<ItemStack> currentItems = new ArrayList<>();
        for (int i = 0; i < itemStackHandler.getSlots() - 1; i++) {
            if (!itemStackHandler.getStackInSlot(i).isEmpty()) currentItems.add(itemStackHandler.getStackInSlot(i));
        }

        if(!inputFluidTank.isEmpty() && !currentItems.isEmpty()) {
           // Then we need to try and match a MixingVatItemRecipe.
            List<MixingVatItemRecipe> recipes = this.level.getRecipeManager().getAllRecipesFor(
                    MixingVatItemRecipe.Type.INSTANCE
            );

            for(MixingVatItemRecipe recipe : recipes) {


                if (recipe.matches(
                        this.inputFluidTank.getFluid(),
                        currentItems)
                ) {
                    matchingRecipes.add(recipe);
                }
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

        this.tickClock = nbt.getInt("CurrentProcessTicks");
        this.tickMax = nbt.getInt("MaxProcessTicks");

        this.itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.inputFluidTank.readFromNBT(nbt.getCompound("InputFluidTank"));
        this.reagentFluidTank.readFromNBT(nbt.getCompound("ReagentFluidTank"));

        if (nbt.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void saveAdditional(CompoundTag nbt) {

        nbt.putInt("CurrentProcessTicks", this.tickClock);
        nbt.putInt("MaxProcessTicks", this.tickMax);

        nbt.put("inventory", itemStackHandler.serializeNBT());
        nbt.put("InputFluidTank", inputFluidTank.writeToNBT(new CompoundTag()));
        nbt.put("ReagentFluidTank", reagentFluidTank.writeToNBT(new CompoundTag()));

        if (this.customName != null) {
            nbt.putString("CustomName", Component.Serializer.toJson(this.customName));
        }

        super.saveAdditional(nbt);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        inputFluidHandler = LazyOptional.of(() -> inputFluidTank);
        reagentFluidHandler = LazyOptional.of(() -> reagentFluidTank);
        inventoryHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        inputFluidHandler.invalidate();
        reagentFluidHandler.invalidate();
        inventoryHandler.invalidate();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            if (DirectionUtils.isSide(side)) {
                return this.reagentFluidHandler.cast();
            } else if (DirectionUtils.isTop(side)) {
                return this.inputFluidHandler.cast();
            }
        } else if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return this.inventoryHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    public void setFluidStackInTank(int tankID, FluidStack fluidStack) {
        switch (tankID) {
            case 0 -> this.inputFluidTank.setFluid(fluidStack);
            case 1 -> this.reagentFluidTank.setFluid(fluidStack);
            default -> {
                throw new IndexOutOfBoundsException(
                        String.format("MixingVatBlockEntity at %s: setFluidStackInTank(int %d, FluidStack %s) is not a valid tank ID.",
                                this.getBlockPos(), tankID, fluidStack.getFluid().toString()));
            }
        }
    }

    public FluidStack getFluidStackInTank(String tankName) {
        return switch (tankName) {
            case "input" -> this.getFluidStackInTank(0);
            case "reagent" -> this.getFluidStackInTank(1);
            default -> {
                throw new IndexOutOfBoundsException(
                        String.format("MixingVatBlockEntity at %s: %s is not a valid tank name.",
                                this.getBlockPos(), tankName));
            }
        };
    }

    public FluidStack getFluidStackInTank(int tankID) {
        return switch (tankID) {
            case 0 -> this.inputFluidTank.getFluid().copy();
            case 1 -> this.reagentFluidTank.getFluid().copy();
            default -> {
                throw new IndexOutOfBoundsException(
                        String.format("MixingVatBlockEntity at %s: %d is not a valid tank ID.",
                                this.getBlockPos(), tankID));
            }
        };
    }

    public FluidTank getFluidTank(String tankName) {
        return switch (tankName) {
            case "input" -> this.getFluidTank(0);
            case "reagent" -> this.getFluidTank(1);
            default -> {
                throw new IndexOutOfBoundsException(
                        String.format("MixingVatBlockEntity at %s: %s is not a valid tank name.",
                                this.getBlockPos(), tankName));
            }
        };
    }

    private FluidTank getFluidTank(int tankID) {
        return switch (tankID) {
            case 0 -> this.inputFluidTank;
            case 1 -> this.reagentFluidTank;
            default -> {
                throw new IndexOutOfBoundsException(
                        String.format("MixingVatBlockEntity at %s: %d is not a valid tank ID.",
                                this.getBlockPos(), tankID));
            }
        };
    }

    public void playSound(String sound) {
        if (Objects.equals(sound, "open") && this.level != null) {
            this.level.playSound(null, this.getBlockPos(), SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS);
        }
    }


}
