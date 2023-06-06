package growthcraft.cellar.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import growthcraft.cellar.block.entity.CultureJarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import java.awt.*;

public class CultureJarBlockEntityRenderer implements BlockEntityRenderer<CultureJarBlockEntity> {

    @Override
    public boolean shouldRender(CultureJarBlockEntity p_173568_, Vec3 p_173569_) {
        return true;
    }

    @Override
    public boolean shouldRenderOffScreen(CultureJarBlockEntity p_112306_) {
        return true;
    }

    @Override
    public void render(CultureJarBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        if (blockEntity.getFluidStackInTank(0).isEmpty()) {
            return;
        }

        float baseOffset = 1 / 16F;
        float maxFluidHeight = 4.0F / 16F;

        FluidStack inputFluidStack = blockEntity.getFluidStackInTank(0);

        float capacity = blockEntity.getFluidTank(0).getCapacity();
        float amount = inputFluidStack.getAmount();
        float inputFluidHeight = baseOffset + (maxFluidHeight - baseOffset) * amount / capacity;

        //poseStack.popPose();
        // TOP
        renderFluidSingle(poseStack, multiBufferSource, inputFluidStack, 0.0F, inputFluidHeight, 0.0F,Axis.XP.rotationDegrees(90.0F), light, overlay);
        // SOUTH FACING
        renderFluidSingle(poseStack, multiBufferSource, inputFluidStack, 0.0F, 0.605F, 0.6F, Axis.XP.rotationDegrees(180.0F), light, overlay);
        // NORTH FACING
        renderFluidSingle(poseStack, multiBufferSource, inputFluidStack, 0.0F, -0.35F, 0.355F, Axis.XP.rotationDegrees(0.0F), light, overlay);
        // BOTTOM FACING
        renderFluidSingle(poseStack, multiBufferSource, inputFluidStack, 0.0F, 0.01F, 0.955F, Axis.XP.rotationDegrees(270.0F), light, overlay);
        // WEST FACING
        renderFluidSingle(poseStack, multiBufferSource, inputFluidStack, 0.355F, -0.35F, 0.956F, Axis.YP.rotationDegrees(90.0F), light, overlay);
        // EAST FACING
        renderFluidSingle(poseStack, multiBufferSource, inputFluidStack, 0.6F, -0.35F, 0.0F, Axis.YP.rotationDegrees(270.0F), light, overlay);

        //poseStack.pushPose();

    }

    public void renderFluidSingle(PoseStack poseStack, MultiBufferSource buffer, FluidStack fluidStack, float xOffset, float height, float zOffset, Quaternionf rotation, int lightLevel, int overlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, height, 0.5F);

        float s = 14 / 256F;
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

        vertexBuilder.vertex(matrix4f, 6.5F, 11.0F, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU0(), sprite.getV1())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();
        vertexBuilder.vertex(matrix4f, 11.0F, 11.0F, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU1(), sprite.getV1())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();
        vertexBuilder.vertex(matrix4f, 11.0F, 6.5F, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU1(), sprite.getV0())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();
        vertexBuilder.vertex(matrix4f, 6.5F, 6.5F, 0)
                .color(red, green, blue, alpha)
                .uv(sprite.getU0(), sprite.getV0())
                .overlayCoords(overlay)
                .uv2(light)
                .normal(matrix3f, 0, 0, 1)
                .endVertex();

    }

}
