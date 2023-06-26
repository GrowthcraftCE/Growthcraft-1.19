package growthcraft.lib.client;

import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import org.apache.commons.lang3.function.TriFunction;

public class ClientFluidTypeExtensions implements IClientFluidTypeExtensions {
    public final String modid;
    public ResourceLocation still;
    public ResourceLocation flowing;
    public ResourceLocation overlay;
    public ResourceLocation renderOverlay;
    public Vector3f fogColor;
    public TriFunction<FluidState, BlockAndTintGetter, BlockPos, Integer> tintFunction;
    public int tintColor;

    public ClientFluidTypeExtensions(String modid, String fluidName) {
        this.modid = modid;
        still(fluidName);
        flowing(fluidName);
        overlay(fluidName);
    }

    public ClientFluidTypeExtensions flowing(String name) {
        return flowing(name, "block/fluid");
    }

    public ClientFluidTypeExtensions flowing(String name, String folder) {
        this.flowing = new ResourceLocation(this.modid, folder + "/" + name + "_flowing");
        return this;
    }

    public ClientFluidTypeExtensions fogColor(float red, float green, float blue) {
        this.fogColor = new Vector3f(red, green, blue);
        return this;
    }

    public ClientFluidTypeExtensions overlay(String name) {
        return overlay(name, "block/fluid");
    }

    public ClientFluidTypeExtensions overlay(String name, String folder) {
        this.overlay = new ResourceLocation(this.modid, folder + "/" + name + "_overlay");
        return renderOverlay(new ResourceLocation(this.modid, "textures/" + folder + "/" + name + "_overlay.png"));
    }

    public ClientFluidTypeExtensions renderOverlay(ResourceLocation path) {
        this.renderOverlay = path;
        return this;
    }

    public ClientFluidTypeExtensions still(String name) {
        return still(name, "block/fluid");
    }

    public ClientFluidTypeExtensions still(String name, String folder) {
        this.still = new ResourceLocation(this.modid, folder + "/" + name + "_still");
        return this;
    }

    public ClientFluidTypeExtensions tint(int tint) {
        this.tintColor = tint;
        this.tintFunction = ($0, $1, $2) -> tint < 0 ? tint * -1 : tint;
        return this;
    }

    public ClientFluidTypeExtensions tint(TriFunction<FluidState, BlockAndTintGetter, BlockPos, Integer> tinter) {
        this.tintFunction = tinter;
        return this;
    }
}
