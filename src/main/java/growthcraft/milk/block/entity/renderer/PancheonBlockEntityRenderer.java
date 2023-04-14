package growthcraft.milk.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
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
import org.joml.Matrix4f;

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
        poseStack.pushPose();
        poseStack.translate(0.5F, height, 0.5F);

        float s = 14 / 256F;
        float v = 1.55F / 8F;
        float w = -(v) * 2.5F;

        int alpha = 2 * 255;

        poseStack.translate(w, 0.0F, w);
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        poseStack.scale(s, s, s);

        IClientFluidTypeExtensions fluidTypeExtensions = IClientFluidTypeExtensions.of(fluidStack.getFluid());
        Color color = new Color(fluidTypeExtensions.getTintColor());

        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(fluidTypeExtensions.getStillTexture(fluidStack));

        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.translucent());

        renderIcon(poseStack, vertexBuilder, sprite, color, alpha, overlay, lightLevel);
        poseStack.popPose();
    }

    private static void renderIcon(PoseStack poseStack, VertexConsumer vertexBuilder, TextureAtlasSprite sprite, Color color, int alpha, int overlay, int light) {

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        Matrix3f matrix3f = poseStack.last().normal();
        Matrix4f matrix4f = poseStack.last().pose();

        vertexBuilder.vertex(matrix4f, 0, 17, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU0(), sprite.getV1())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();
        vertexBuilder.vertex(matrix4f, 17, 17, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU1(), sprite.getV1())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();
        vertexBuilder.vertex(matrix4f, 17, 0, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU1(), sprite.getV0())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();
        vertexBuilder.vertex(matrix4f, 0, 0, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU0(), sprite.getV0())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();

    }

}
