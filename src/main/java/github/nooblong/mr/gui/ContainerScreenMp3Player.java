package github.nooblong.mr.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import github.nooblong.mr.items.ContainerMp3Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ContainerScreenMp3Player extends ContainerScreen<ContainerMp3Player> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("mr", "textures/gui/mp3_player.png");

    private static final ResourceLocation BUTTON = new ResourceLocation("mr", "textures/items/ruby.png");

    public ContainerScreenMp3Player(ContainerMp3Player screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {

        addButton(new ImageButton(drawX(10), drawY(63), 24, 20,
                16, 16, 0, BUTTON,
                (i) -> {
                    System.out.println("button");
                }));
        addButton(new ImageButton(drawX(10), drawY(87), 24, 20,
                16, 16, 0, BUTTON,
                (i) -> {
                    System.out.println("button");
                }));
        addButton(new ImageButton(drawX(10), drawY(111), 24, 20,
                16, 16, 0, BUTTON,
                (i) -> {
                    System.out.println("button");
                }));
        addButton(new ImageButton(drawX(10), drawY(135), 24, 20,
                0, 0, 0, BUTTON,
                (i) -> {
                    System.out.println("button");
                }));
    }

    private int drawX(int x){
        int edgeSpacingX = (this.width - this.xSize)/2;
        return x + edgeSpacingX;
    }
    private int drawY(int y){
        int edgeSpacingY = (this.height - this.ySize)/2;
        return y + edgeSpacingY;
    }

    @Override
    public void render(int mouseX, int mouseY, float mouseZ) {
        this.renderBackground();
        super.render(mouseX, mouseY, mouseZ);
        this.renderHoveredToolTip(mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
        GlStateManager.color4f(1f,1f,1f,1f);
        mc.getTextureManager().bindTexture(TEXTURE);

        int edgeSpacingX = (this.width - this.xSize)/2;
        int edgeSpacingY = (this.height - this.ySize)/2;
        this.blit(edgeSpacingX,edgeSpacingY,0,0,this.xSize,this.ySize);
    }
}
