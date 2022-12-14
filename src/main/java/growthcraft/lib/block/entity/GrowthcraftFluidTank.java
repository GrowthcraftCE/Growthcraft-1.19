package growthcraft.lib.block.entity;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.Collections;
import java.util.List;

public class GrowthcraftFluidTank extends FluidTank {

    private List<Fluid> fluidsList;
    private boolean isOutputOnly = false;

    public GrowthcraftFluidTank(int capacity) {
        this(capacity, Collections.singletonList(Fluids.EMPTY), false);
    }

    public GrowthcraftFluidTank(int capacity, boolean isOutputOnly) {
        this(capacity, Collections.singletonList(Fluids.EMPTY), isOutputOnly);
    }

    public GrowthcraftFluidTank(int capacity, List<Fluid> validFluids, boolean isOutputOnly) {
        super(capacity);
        this.fluidsList = validFluids;
        this.isOutputOnly = isOutputOnly;
    }

    @Override
    protected void onContentsChanged() {
        super.onContentsChanged();
    }

    @Override
    public boolean isFluidValid(FluidStack stack) {
        return !isOutputOnly && (fluidsList.get(0) == Fluids.EMPTY || fluidsList.contains(stack.getFluid()));
    }

}
