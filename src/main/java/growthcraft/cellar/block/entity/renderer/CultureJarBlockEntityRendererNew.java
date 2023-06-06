package growthcraft.cellar.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import growthcraft.cellar.block.entity.CultureJarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;


public class CultureJarBlockEntityRendererNew implements BlockEntityRenderer<CultureJarBlockEntity> {

    private final Minecraft minecraft;

    public CultureJarBlockEntityRendererNew(BlockEntityRendererProvider.Context context) {
        this.minecraft = Minecraft.getInstance();

    }

    @Override
    public void render(CultureJarBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        FluidStack fluidState = blockEntity.getFluidStackInTank(0);

        if (fluidState.isEmpty()) {
            return;
        }

        poseStack.pushPose();

        // Set up the rendering position and scale
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.scale(0.8f, 0.8f, 0.8f);

        // Get the current block's state for context-dependent rendering
        BlockState blockState = blockEntity.getBlockState();

        // Get the fluid type
        Fluid fluid = fluidState.getFluid();

        ResourceLocation fluidStill = IClientFluidTypeExtensions.of(fluid).getStillTexture();

        TextureAtlasSprite fluidSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);

        // Render the fluid
        BlockPos blockPos = blockEntity.getBlockPos();
        //minecraft.getTextureManager().bind(fluidTexture);
        minecraft.getBlockRenderer().renderModel(
                blockEntity.getLevel(),
                minecraft.getModelManager().getModel(fluidStill),
                blockState,
                blockPos,
                poseStack,
                buffer,
                false,
                blockEntity.getLevel().random,
                0,
                overlay
        );

        poseStack.popPose();
    }}
