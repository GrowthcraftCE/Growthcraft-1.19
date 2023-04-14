package growthcraft.lib.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class CraftingUtils {

    public static FluidStack getFluidStack(JsonObject json) {
        ResourceLocation fluidName = new ResourceLocation(GsonHelper.getAsString(json, "fluid"));
        int fluidAmount = GsonHelper.getAsInt(json, "amount", 1000);

        Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidName);

        if(fluid == null) {
            throw new JsonSyntaxException("Unknown fluid '" + fluidName + "' in Json.");
        }

        return new FluidStack(fluid, fluidAmount);
    }

    private CraftingUtils() { /* Prevent automatic public constructor */ }
}
