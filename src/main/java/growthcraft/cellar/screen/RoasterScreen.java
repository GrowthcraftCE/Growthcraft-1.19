package growthcraft.cellar.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import growthcraft.cellar.screen.container.RoasterMenu;
import growthcraft.cellar.shared.Reference;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class RoasterScreen extends AbstractContainerScreen<RoasterMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
            Reference.MODID, "textures/gui/roaster_screen.png"
    );

    public RoasterScreen(RoasterMenu menu, Inventory inventory, Component component){
        super(menu, inventory, component);
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Full background image
        blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

        // TODO[9]: Implement Roaster Progress Bar
        /*
        blit(poseStack,
                x + 51, y + 48 - menu.getProgressionScaled(28),
                188, 28 - menu.getProgressionScaled(28),
                8, menu.getProgressionScaled(28)
        );
        */

        // Heat indicator
        if(this.menu.isHeated()) {
            this.blit(poseStack,
                    x + 81, y + 57,
                    176, 28,
                    13, 13
            );
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        // Render any tooltips for this mouse over location.
        renderTooltip(poseStack, mouseX, mouseY);    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        // Screen Title
        this.font.draw(poseStack, this.title, (float) this.titleLabelX, (float) this.titleLabelY, 4210752);
        // Inventory Title
        this.font.draw(poseStack, this.playerInventoryTitle, (float) this.inventoryLabelX, (float) this.inventoryLabelY, 4210752);
    }

}
