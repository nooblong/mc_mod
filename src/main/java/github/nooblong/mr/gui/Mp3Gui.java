package github.nooblong.mr.gui;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.file.MySimpleNetworkHandler;
import github.nooblong.mr.util.OperateFile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.*;
import java.util.HashMap;

@OnlyIn(Dist.CLIENT)
public class Mp3Gui extends Screen {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(
            MusicRestaurant.MOD_ID, "textures/gui/mp3.png"
    );
    private static final ResourceLocation ICON = new ResourceLocation(
            MusicRestaurant.MOD_ID, "textures/gui/icon.png"
    );

    private static final String PATH = "path";

    private static final int BG_WIDTH = 175;
    private static final int BG_HEIGHT = 221;

    public Mp3Gui() {
        super(new StringTextComponent("mr.mp3"));
    }

    protected int configWidth;
    protected int configHeight;
    protected int configStartX;
    protected int configStartY;

    private final HashMap<String, TextFieldWidget> textFields = Maps.newHashMap();

    @Override
    public void init() {
        this.buttons.clear();
        this.textFields.clear();

        configHeight = 175;
        configWidth = 211;
        configStartX = 0;
        configStartY = 0;

//        this.addTextFields(textFields);

        //添加输入框
        TextFieldWidget path = new TextFieldWidget(font, (this.width - BG_WIDTH) / 2 + 5,
                (this.height - BG_HEIGHT) / 2 + 16, BG_WIDTH - 40, 20, "path");
        path.setMaxStringLength(100);
        path.setResponder((s -> {
            MusicRestaurant.LOGGER.info("path.Responder");
        }));
        textFields.put(PATH, path);


        addButton(new ImageButton(((this.width - BG_WIDTH) / 2) + 5 + (BG_WIDTH - 40), (this.height - BG_HEIGHT) / 2 + 16, 30, 20,
                0, 0, 0, ICON,
                (i) -> {
                    if (minecraft != null) {
//                        MySimpleNetworkHandler.sendToServer("file.ogg", "hello".getBytes());//[104, 101, 108, 108, 111]
                        byte[] toSend = OperateFile.readOgg(path.getText());
                        if (toSend != null) {
                            MySimpleNetworkHandler.sendToServer(OperateFile.getFileName(path.getText()), toSend);
                            System.out.println("send packet");
                        }
                    }
                }));

        //第一个位置
        TextFieldWidget pos1x = new TextFieldWidget(font, (this.width - BG_WIDTH) / 2 + 5,
                (this.height - BG_HEIGHT) / 2 + 50, BG_WIDTH/3, 20, "pos1x");
        path.setMaxStringLength(100);
        path.setResponder((s -> {
            MusicRestaurant.LOGGER.info("pos1x");
        }));
        textFields.put("pos1x", pos1x);
        TextFieldWidget pos1y = new TextFieldWidget(font, (this.width - BG_WIDTH) / 2 + 5 + 58,
                (this.height - BG_HEIGHT) / 2 + 50, BG_WIDTH/3, 20, "pos1y");
        path.setMaxStringLength(100);
        path.setResponder((s -> {
            MusicRestaurant.LOGGER.info("pos1y");
        }));
        textFields.put("pos1y", pos1y);
        TextFieldWidget pos1z = new TextFieldWidget(font, (this.width - BG_WIDTH) / 2 + 5 + 58 + 58,
                (this.height - BG_HEIGHT) / 2 + 50, BG_WIDTH/3, 20, "pos1z");
        path.setMaxStringLength(100);
        path.setResponder((s -> {
            MusicRestaurant.LOGGER.info("pos1z");
        }));
        textFields.put("pos1z", pos1z);

        this.children.addAll(textFields.values());
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        // 左侧按钮列表
//        fillGradient(0, 0, 100, height, 0xdd000000, 0xdd000000);
        // 标题背景
//        fillGradient(2, 2, 100 - 2, 20 - 2, 0xdd1f1f1f, 0xdd1f1f1f);

        // drawGradientRect(2, 25 - 4, 98, 25 + 15 - 4, 0xdd339966, 0xdd339966);

        // 右侧弹幕样式显示背景
//        fillGradient(width - 150, 0, width, height, 0xdd1f1f1f, 0xdd1f1f1f);
        // 中间配置界面
//        fillGradient(configStartX, configStartY, configStartX + configWidth,
//                configStartY + configHeight, 0xaa000000, 0xaa000000);

        // 最下方按钮
//        fillGradient(5, height - 50, 95, height - 30, 0xff31343f, 0xff31343f);
//        fillGradient(5, height - 25, 95, height - 5, 0xff31343f, 0xff31343f);

//        drawConfigScreen(mouseX, mouseY, partialTicks);

        font.drawString(".ogg路径", (this.width - BG_WIDTH) / 2.0f + 5, (this.height - BG_HEIGHT) / 2.0f + 3, 0xffffff);
//        font.drawString("房间设置", 12, 25, 0xffffff);
//        font.drawString("弹幕设置", 12, 25 + 15, 0xffffff);
//        font.drawString("送礼设置", 12, 25 + 15 * 2, 0xffffff);
//        font.drawString("进房设置", 12, 25 + 15 * 3, 0xffffff);
//        font.drawString("上舰设置", 12, 25 + 15 * 4, 0xffffff);
//        font.drawString("醒目留言（SC）", 12, 25 + 15 * 5, 0xffffff);

//        String text1 = "保存并重载";
//        font.drawString(text1, (100 - font.getStringWidth(text1)) / 2, height - 44, 0xffffff);
        String text2 = "直接退出";
        font.drawString(text2, (100 - font.getStringWidth(text2)) / 2, height - 19, 0xffffff);

//        int startY = 10;
//        int startX = width - 150 + 8;
//        if (config.getDanmaku().isShow()) {
//            startY = drawExampleMsg(config.getDanmaku().getNormalStyleFormatted(), startX, startY, "酒石酸菌", "我是普通观众");
//            startY = drawExampleMsg(config.getDanmaku().getAdminStyleFormatted(), startX, startY, "琥珀酸", "我是房管");
//            startY = drawExampleMsg(config.getDanmaku().getGuardStyleFormatted(), startX, startY, "帕金伊", "我上舰了");
//        }
//        if (config.getGift().isShow()) {
//            startY = drawExampleMsg(config.getGift().getStyleFormatted(), startX, startY,
//                    "天幂", "投喂了", "风铃", 943);
//        }
//        if (config.getEnter().isShowNormal()) {
//            startY = drawExampleMsg(config.getEnter().getNormalStyleFormatted(), startX, startY, "梨木利亚");
//        }
//        if (config.getEnter().isShowGuard()) {
//            startY = drawExampleMsg(config.getEnter().getGuardStyle1Formatted(), startX, startY, "嚼铝赤城桑");
//            startY = drawExampleMsg(config.getEnter().getGuardStyle2Formatted(), startX, startY, "花玥");
//            startY = drawExampleMsg(config.getEnter().getGuardStyle3Formatted(), startX, startY, "天顶乌");
//        }
//
//        if (config.getGuard().isShow()) {
//            startY = drawExampleMsg(config.getGuard().getGuardStyle1Formatted(), startX, startY, "迺逸夫");
//            startY = drawExampleMsg(config.getGuard().getGuardStyle2Formatted(), startX, startY, "雪尼");
//            startY = drawExampleMsg(config.getGuard().getGuardStyle3Formatted(), startX, startY, "青芙");
//        }
//
//        if (config.getSc().isShow()) {
//            startY = drawExampleMsg(config.getSc().getStyleFormatted(), startX, startY, "秋夕", "这是醒目留言！！！", 943);
//        }

        for (TextFieldWidget textField : textFields.values()) {
            textField.render(mouseX, mouseY, partialTicks);
        }

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderBackground() {
        RenderSystem.color4f(1f, 1f, 1f, 0.5f);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int x = (this.width - BG_WIDTH) / 2;
        int y = (this.height - BG_HEIGHT) / 2;
        this.blit(x, y, 0, 0, 175, 221);
    }
}
