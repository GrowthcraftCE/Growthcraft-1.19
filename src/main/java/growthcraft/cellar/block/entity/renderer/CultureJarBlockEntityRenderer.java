package growthcraft.cellar.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import growthcraft.cellar.block.entity.CultureJarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.awt.*;

public class CultureJarBlockEntityRenderer implements BlockEntityRenderer<CultureJarBlockEntity> {

    @Override
    public boolean shouldRender(CultureJarBlockEntity p_173568_, Vec3 p_173569_) {
        return true;
    }

    @Override
    public void render(CultureJarBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        if (blockEntity.getFluidStackInTank(0).isEmpty()) {
            return;
        }

        FluidStack inputFluidStack = blockEntity.getFluidStackInTank(0);

        float capacity = (float) blockEntity.getFluidTank(0).getCapacity();
        float amount = inputFluidStack.getAmount();
        float inputFluidHeight = amount / capacity;

        poseStack.popPose();
        renderCubeUsingQuads(poseStack, multiBufferSource, inputFluidStack, inputFluidHeight, light, overlay);
        poseStack.pushPose();

    }

    public void renderCubeUsingQuads(PoseStack poseStack, MultiBufferSource buffer, FluidStack fluidStack, float inputFluidHeight, int lightLevel, int overlay) {
        final Vector3d TRANSLATION_OFFSET = new Vector3d(0, 0.01, 0);

        poseStack.pushPose();
        poseStack.translate(TRANSLATION_OFFSET.x, TRANSLATION_OFFSET.y, TRANSLATION_OFFSET.z);

        Fluid fluid = fluidStack.getFluid();
        ResourceLocation fluidStill = IClientFluidTypeExtensions.of(fluid).getStillTexture();

        TextureAtlasSprite fluidStillTexture = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);

        int color1 = IClientFluidTypeExtensions.of(fluid).getTintColor();
        Color color = new Color(color1);

        drawCubeQuads(poseStack, buffer, inputFluidHeight, fluidStillTexture, color, lightLevel);

        poseStack.popPose();
    }

    private void drawCubeQuads(PoseStack matrixStack, MultiBufferSource renderBuffer, float inputFluidHeight, TextureAtlasSprite textureAtlasSprite, Color color, int lightLevel) {

        VertexConsumer vertexBuilderBlockQuads = renderBuffer.getBuffer(RenderType.entityTranslucent(InventoryMenu.BLOCK_ATLAS));

        Matrix4f matrixPos = matrixStack.last().pose();
        Matrix3f matrixNormal = matrixStack.last().normal();

        Vec2 bottomLeftUV = new Vec2(0.0F, 1.0F);
        float uVwidth = 1.0F;
        float uVheight = 1.0F;

        final float WIDTH = 0.25F;
        final float HEIGHT = 0.25F;

        final Vector3d EAST_FACE_MIDPOINT = new Vector3d(6.0 / 16.0, (2.0 / 16.0) * inputFluidHeight, 0.5);
        final Vector3d WEST_FACE_MIDPOINT = new Vector3d(10.0 / 16.0, (2.0 / 16.0) * inputFluidHeight, 0.5);
        final Vector3d NORTH_FACE_MIDPOINT = new Vector3d(0.5, (2.0 / 16.0) * inputFluidHeight, 6.0 / 16.0);
        final Vector3d SOUTH_FACE_MIDPOINT = new Vector3d(0.5, (2.0 / 16.0) * inputFluidHeight, 10.0 / 16.0);

        final Vector3d UP_FACE_MIDPOINT = new Vector3d(0.5, 0.25 * inputFluidHeight, 0.5);

        final Vector3d DOWN_FACE_MIDPOINT = new Vector3d(0.5, 0.0, 0.5);

        addFace(Direction.EAST, matrixPos, matrixNormal, vertexBuilderBlockQuads, color, EAST_FACE_MIDPOINT, WIDTH, HEIGHT * inputFluidHeight, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.WEST, matrixPos, matrixNormal, vertexBuilderBlockQuads, color, WEST_FACE_MIDPOINT, WIDTH, HEIGHT * inputFluidHeight, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.NORTH, matrixPos, matrixNormal, vertexBuilderBlockQuads, color, NORTH_FACE_MIDPOINT, WIDTH, HEIGHT * inputFluidHeight, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.SOUTH, matrixPos, matrixNormal, vertexBuilderBlockQuads, color, SOUTH_FACE_MIDPOINT, WIDTH, HEIGHT * inputFluidHeight, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.UP, matrixPos, matrixNormal, vertexBuilderBlockQuads, color, UP_FACE_MIDPOINT, WIDTH, HEIGHT, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.DOWN, matrixPos, matrixNormal, vertexBuilderBlockQuads, color, DOWN_FACE_MIDPOINT, WIDTH, HEIGHT, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
    }

    private void addFace(Direction face, Matrix4f matrixPos, Matrix3f matrixNormal, VertexConsumer vertexBuilder, Color color, Vector3d centrePos, float width, float height, TextureAtlasSprite textureAtlasSprite, Vec2 bottomLeftUV, float uVwidth, float uVheight, int lightLevel) {

        Vector3f leftToRightDirection;
        Vector3f bottomToTopDirection;

        switch (face) {
            case NORTH: {
                leftToRightDirection = new Vector3f(-1, 0, 0);
                bottomToTopDirection = new Vector3f(0, 1, 0);
                break;
            }
            case SOUTH: {
                leftToRightDirection = new Vector3f(1, 0, 0);
                bottomToTopDirection = new Vector3f(0, 1, 0);
                break;
            }
            case EAST: {
                leftToRightDirection = new Vector3f(0, 0, -1);
                bottomToTopDirection = new Vector3f(0, 1, 0);
                break;
            }

            case UP: {
                leftToRightDirection = new Vector3f(-1, 0, 0);
                bottomToTopDirection = new Vector3f(0, 0, 1);
                break;
            }
            case DOWN: {
                leftToRightDirection = new Vector3f(1, 0, 0);
                bottomToTopDirection = new Vector3f(0, 0, 1);
                break;
            }
            case WEST:
            default: {
                leftToRightDirection = new Vector3f(0, 0, 1);
                bottomToTopDirection = new Vector3f(0, 1, 0);
                break;
            }
        }

        leftToRightDirection.mul(0.5F * width);
        bottomToTopDirection.mul(0.5F * height);

        Vector3f bottomLeftPos = new Vector3f((float) centrePos.x, (float) centrePos.y, (float) centrePos.z);
        bottomLeftPos.sub(leftToRightDirection);
        bottomLeftPos.sub(bottomToTopDirection);

        Vector3f bottomRightPos = new Vector3f((float) centrePos.x, (float) centrePos.y, (float) centrePos.z);
        bottomRightPos.add(leftToRightDirection);
        bottomRightPos.sub(bottomToTopDirection);

        Vector3f topRightPos = new Vector3f((float) centrePos.x, (float) centrePos.y, (float) centrePos.z);
        topRightPos.add(leftToRightDirection);
        topRightPos.add(bottomToTopDirection);

        Vector3f topLeftPos = new Vector3f((float) centrePos.x, (float) centrePos.y, (float) centrePos.z);
        topLeftPos.sub(leftToRightDirection);
        topLeftPos.add(bottomToTopDirection);

        Vec2 bottomLeftUVpos = new Vec2(bottomLeftUV.x, bottomLeftUV.y);
        Vec2 bottomRightUVpos = new Vec2(bottomLeftUV.x + uVwidth, bottomLeftUV.y);
        Vec2 topLeftUVpos = new Vec2(bottomLeftUV.x + uVwidth, bottomLeftUV.y + uVheight);
        Vec2 topRightUVpos = new Vec2(bottomLeftUV.x, bottomLeftUV.y + uVheight);

        Vector3f normalVector = face.step();

        addQuad(matrixPos, matrixNormal, vertexBuilder, bottomLeftPos, bottomRightPos, topRightPos, topLeftPos, bottomLeftUVpos, bottomRightUVpos, topLeftUVpos, topRightUVpos, normalVector, color, lightLevel);
    }

    private void addQuad(Matrix4f matrixPos, Matrix3f matrixNormal, VertexConsumer vertexBuilder, Vector3f bottomLeftPos, Vector3f bottomRightPos, Vector3f topRightPos, Vector3f topLeftPos, Vec2 bottomLeftUVpos, Vec2 bottomRightUVpos, Vec2 topLeftUVpos, Vec2 topRightUVpos, Vector3f normalVector, Color color, int lightLevel) {

        addQuadVertex(matrixPos, matrixNormal, vertexBuilder, bottomLeftPos, bottomLeftUVpos, normalVector, color, lightLevel);
        addQuadVertex(matrixPos, matrixNormal, vertexBuilder, bottomRightPos, bottomRightUVpos, normalVector, color, lightLevel);
        addQuadVertex(matrixPos, matrixNormal, vertexBuilder, topRightPos, topRightUVpos, normalVector, color, lightLevel);
        addQuadVertex(matrixPos, matrixNormal, vertexBuilder, topLeftPos, topLeftUVpos, normalVector, color, lightLevel);
    }

    private void addQuadVertex(Matrix4f matrixPos, Matrix3f matrixNormal, VertexConsumer vertexBuilder, Vector3f posVector, Vec2 textureUV, Vector3f normalVector, Color color, int lightLevel) {
        vertexBuilder.vertex(matrixPos, posVector.x, posVector.y, posVector.z)
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
                .uv(textureUV.x, textureUV.y)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(lightLevel)
                .normal(matrixNormal, normalVector.x, normalVector.y, normalVector.z)
                .endVertex();
    }
}
