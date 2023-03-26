package growthcraft.milk.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import growthcraft.milk.shared.Reference;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PancheonScreen extends AbstractContainerScreen<PancheonMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
            Reference.MODID,
            "textures/gui/pancheon_screen.png"
    );

    public PancheonScreen(PancheonMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);

        //this.font.draw(poseStack, this.title, 8.0F, 5.0F, 0x404040);
        //this.font.draw(poseStack, this.playerInventoryTitle, 8.0F, this.topPos + 75, 0x404040);
    }

    @Override
    protected void renderLabels(PoseStack p_97808_, int p_97809_, int p_97810_) {
        // Do nothing as this isn't a standard size GUI.
        // We will have to render the GUI title and inventory label ourselves.
        this.font.draw(p_97808_, this.title, (float) this.titleLabelX, (float) this.titleLabelY, 4210752);
        this.font.draw(p_97808_, this.playerInventoryTitle, (float) this.inventoryLabelX, (float) this.inventoryLabelY - 32, 4210752);
    }
}
