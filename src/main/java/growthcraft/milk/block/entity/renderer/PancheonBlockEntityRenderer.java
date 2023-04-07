package growthcraft.milk.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import growthcraft.milk.block.entity.PancheonBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.joml.Matrix3f;

import java.awt.*;

public class PancheonBlockEntityRenderer implements BlockEntityRenderer<PancheonBlockEntity> {

    @Override
    public void render(PancheonBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        if (blockEntity.isFluidEmpty()) {
            return;
        }

        float baseOffset = 1 / 16F;
        float maxFluidHeight = 3.5F / 16F;

        FluidStack inputFluidStack = blockEntity.getFluidStackInTank(0);
        FluidStack outputFluidStackPrimary = blockEntity.getFluidStackInTank(1);
        FluidStack outputFluidStackSecondary = blockEntity.getFluidStackInTank(2);

        if (!inputFluidStack.isEmpty()) {
            float inputFluidStackHeight = baseOffset + (maxFluidHeight - baseOffset) * inputFluidStack.getAmount() / (float) blockEntity.getFluidTank(0).getCapacity();

            renderFluid(inputFluidStack, inputFluidStackHeight, multiBufferSource, poseStack, light, overlay);
        }

        if (!outputFluidStackPrimary.isEmpty()) {
            float outputFluidStackPrimaryHeight = (baseOffset * 3) + (maxFluidHeight - baseOffset) * outputFluidStackPrimary.getAmount() / (float) blockEntity.getFluidTank(1).getCapacity() - (maxFluidHeight / 2);

            renderFluid(outputFluidStackPrimary, outputFluidStackPrimaryHeight, multiBufferSource, poseStack, light, overlay);
        }

        if (!outputFluidStackSecondary.isEmpty()) {
            float outputFluidStackSecondaryHeight = baseOffset + (maxFluidHeight - baseOffset) * outputFluidStackSecondary.getAmount() / (float) blockEntity.getFluidTank(2).getCapacity() - (maxFluidHeight / 2);

            renderFluid(outputFluidStackSecondary, outputFluidStackSecondaryHeight, multiBufferSource, poseStack, light, overlay);
        }
    }

    public void renderFluid(FluidStack fluidStack, float height, MultiBufferSource buffer, PoseStack poseStack, int lightLevel, int overlay) {
        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.translucent());
        IClientFluidTypeExtensions fluidTypeExtensions = IClientFluidTypeExtensions.of(fluidStack.getFluid());
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(fluidTypeExtensions.getStillTexture(fluidStack));

        int tintColor = fluidTypeExtensions.getTintColor();

        Color color = new Color(tintColor);

        renderIcon(poseStack.last().normal(), vertexBuilder, sprite, color, color.getAlpha(), overlay, lightLevel);

    }

    private static void renderIcon(Matrix3f matrixStack, VertexConsumer vertexBuilder, TextureAtlasSprite sprite, Color color, float alpha, float heightPercentage, int light){
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        vertexBuilder.normal(matrixStack, 0, 17, 0)
                .color(red, green, blue, (int) (alpha * 255F))
                .uv(sprite.getU0(), sprite.getV1())
                .normal(0, 0, 1)
                .endVertex();
        vertexBuilder.normal(matrixStack, 17, 17, 0)
                .color(red, green, blue, (int) (alpha * 255F))
                .uv(sprite.getU1(), sprite.getV1())
                .normal(0, 0, 1)
                .endVertex();
        vertexBuilder.normal(matrixStack, 17, 0, 0)
                .color(red, green, blue, (int) (alpha * 255F))
                .uv(sprite.getU1(), sprite.getV0())
                .normal(0, 0, 1)
                .endVertex();
        vertexBuilder.normal(matrixStack, 0, 0, 0)
                .color(red, green, blue, (int) (alpha * 255F))
                .uv(sprite.getU0(), sprite.getV0())
                .normal(0, 0, 1)
                .endVertex();

    }

}
