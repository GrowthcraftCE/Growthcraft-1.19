package growthcraft.milk.block.entity;

import growthcraft.lib.block.entity.GrowthcraftFluidTank;
import growthcraft.milk.init.GrowthcraftMilkRecipes;
import growthcraft.milk.lib.networking.GrowthcraftMilkMessages;
import growthcraft.milk.lib.networking.packet.MixingVatFluidSyncPacket;
import growthcraft.milk.recipe.MixingVatItemRecipe;
import growthcraft.milk.recipe.MixingVatRecipe;
import growthcraft.milk.screen.container.MixingVatMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

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

    private LazyOptional<IItemHandler> itemHandlerLazyOptional = LazyOptional.empty();

    private final GrowthcraftFluidTank INPUT_FLUID_TANK0 = new GrowthcraftFluidTank(4000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!level.isClientSide) {
                GrowthcraftMilkMessages.sendToClients(new MixingVatFluidSyncPacket(0, this.fluid, worldPosition));
            }
        }
    };

    private final GrowthcraftFluidTank REAGENT_FLUID_TANK1 = new GrowthcraftFluidTank(4000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!level.isClientSide) {
                GrowthcraftMilkMessages.sendToClients(new MixingVatFluidSyncPacket(1, this.fluid, worldPosition));
            }
        }
    };

    private final GrowthcraftFluidTank RESULT_FLUID_TANK2 = new GrowthcraftFluidTank(4000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!level.isClientSide) {
                GrowthcraftMilkMessages.sendToClients(new MixingVatFluidSyncPacket(2, this.fluid, worldPosition));
            }
        }
    };

    private LazyOptional<IFluidHandler> lazyInputFluidHandler0 = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyOutputFluidHandler0 = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyOutputFluidHandler1 = LazyOptional.empty();

    public MixingVatBlockEntity(BlockEntityType<?> entityType, BlockPos blockPos, BlockState blockState) {
        super(entityType, blockPos, blockState);

        // TODO: Follow-up on fluid tank allow any fluid.

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
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new MixingVatMenu(containerId, inventory, this, this.data);
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, MixingVatBlockEntity mixingVatBlockEntity) {

    }

    private List<MixingVatItemRecipe> getMatchingRecipes(FluidStack fluidStack) {
        MixingVatRecipe.Category category;
        MixingVatRecipe recipe = null;

        // Place the input slots into a List.
        List<ItemStack> currentItems = new ArrayList<>();
        for (int i = 0; i < itemStackHandler.getSlots() - 1; i++) {
            if (!itemStackHandler.getStackInSlot(i).isEmpty()) currentItems.add(itemStackHandler.getStackInSlot(i));
        }



        List<MixingVatRecipe> recipes = this.level.getRecipeManager().getRecipesForType(GrowthcraftMilkRecipes.MIXING_VAT_RECIPE_SERIALIZER);

        for (MixingVatRecipe recipe : recipes) {
            MixingVatItemRecipe mixingVatRecipe = (MixingVatItemRecipe) recipe;
            if (mixingVatRecipe.matches(fluidStack, ingredients)) return mixingVatRecipe;
        }






        List<MixingVatItemRecipe> matchingRecipes = new ArrayList<>();

        List<MixingVatItemRecipe> recipes = level.getRecipeManager().getAllRecipesFor(
                MixingVatItemRecipe.Type.INSTANCE
        );

        for (MixingVatItemRecipe recipe : recipes) {
            if (recipe.getInputFluidStack().getFluid() == fluidStack.getFluid()) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }

    public void setFluidStackInTank(int tankID, FluidStack fluidStack) {
    }

    public FluidStack getFluidStackInTank(int i) {

    }
}
