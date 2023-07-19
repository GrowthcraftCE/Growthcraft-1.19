package growthcraft.cellar.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import growthcraft.cellar.block.entity.BrewKettleBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;

public class BrewKettleBlockEntityRenderer implements BlockEntityRenderer<BrewKettleBlockEntity> {
    @Override
    public boolean shouldRender(BrewKettleBlockEntity blockEntity, Vec3 p_173569_) {
        return true;
    }

    @Override
    public boolean shouldRenderOffScreen(BrewKettleBlockEntity blockEntity) {
        return true;
    }

    @Override
    public void render(BrewKettleBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {

        float baseOffset = 4.0F / 16F;
        float maxFluidHeight = 15.0F / 16F;

        if(!blockEntity.getFluidStackInTank(0).isEmpty()) {

            FluidStack inputFluidStack = blockEntity.getFluidStackInTank(0);

            float inputCapacity = blockEntity.getFluidTank(0).getCapacity();
            float inputAmount = inputFluidStack.getAmount();
            float inputFluidHeight = baseOffset + (maxFluidHeight - baseOffset) * inputAmount / inputCapacity;

            renderFluidSingle(poseStack, multiBufferSource, inputFluidStack, 0.0F, inputFluidHeight, 0.0F, Vector3f.XP.rotationDegrees(90.0F), light, overlay);
        }

        if(!blockEntity.getFluidStackInTank(1).isEmpty()) {
            FluidStack outputFluidStack = blockEntity.getFluidStackInTank(1);

            float outputCapacity = blockEntity.getFluidTank(1).getCapacity();
            float outputAmount = outputFluidStack.getAmount();
            float outputFluidHeight = baseOffset + (maxFluidHeight - baseOffset) * outputAmount / outputCapacity;

            renderFluidSingle(poseStack, multiBufferSource, outputFluidStack, 0.0F, outputFluidHeight, 0.0F, Vector3f.XP.rotationDegrees(90.0F), light, overlay);
        }
    }

    public void renderFluidSingle(PoseStack poseStack, MultiBufferSource buffer, FluidStack fluidStack, float xOffset, float height, float zOffset, Quaternion rotation, int lightLevel, int overlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, height, 0.5F);

        float s = 15 / 256F;
        float v = 1.55F / 8F;
        float w = -(v) * 2.5F;

        int alpha = 2 * 255;

        poseStack.translate(w + xOffset, 0.0F, w + zOffset);
        poseStack.mulPose(rotation);

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

        vertexBuilder.vertex(matrix4f, 0.0F, 16.0F, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU0(), sprite.getV1())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();
        vertexBuilder.vertex(matrix4f, 16.0F, 16.0F, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU1(), sprite.getV1())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();
        vertexBuilder.vertex(matrix4f, 16.0F, 0.0F, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU1(), sprite.getV0())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();
        vertexBuilder.vertex(matrix4f, 1.0F, 0.0F, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU0(), sprite.getV0())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();

    }
}
