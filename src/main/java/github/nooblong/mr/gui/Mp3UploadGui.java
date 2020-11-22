package github.nooblong.mr.gui;

import github.nooblong.mr.net.GuiDataPacket;
import github.nooblong.mr.net.MySimpleNetworkHandler;
import github.nooblong.mr.tileentity.Mp3TileEntity;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ToggleWidget;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;

public class Mp3UploadGui extends Mp3Gui {

    private static final String PATH = "path";
    ToggleWidget upload;

    public Mp3UploadGui(Mp3TileEntity mp3TileEntity) {
        this.mp3TileEntity = mp3TileEntity;
    }

    @Override
    public void init() {
        super.init();
        upload = new ToggleWidget(configStartX + configWidth - 10 - 16, configStartY + 61,
                16, 16, false);
        upload.initTextureValues(16, 0, -16, 16, ICON);
    }

    @Override
    public void addTextFields(HashMap<String, TextFieldWidget> textFields) {
        TextFieldWidget path = new TextFieldWidget(font, configStartX + 10,
                configStartY + 30, configWidth - 20, 15, "");
        path.setMaxStringLength(10);
        path.setText(String.valueOf(mp3TileEntity.getPos1x()));
        path.setValidator((s) -> NumberUtils.isDigits(s) || StringUtils.isEmpty(s));
        path.setResponder((s -> {
            try {
                int i = Integer.parseInt(path.getText());
                mp3TileEntity.setPos1x(i);
            } catch (Exception e){
                System.out.println("e");
            }
        }));
        textFields.put(PATH, path);
    }

    @Override
    public void drawConfigScreen(int mouseX, int mouseY, float partialTicks) {
        font.drawString("文件路径", configStartX + 10, configStartY + 15, 0xffffff);
        font.drawString("上传", configStartX + 10, configStartY + 65, 0xffffff);
        fillGradient(2, 25 - 4, 98, 25 + 15 - 4, 0xdd339966, 0xdd339966);
        upload.render(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouse) {
        if (upload.mouseClicked(mouseX, mouseY, mouse)) {
            upload.setStateTriggered(!upload.isStateTriggered());
            System.out.println("mouse click");
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, mouse);
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public void saveAndQuit() {
        System.out.println("client send GuiPacket");
        MySimpleNetworkHandler.INSTANCE.sendToServer(new GuiDataPacket(
                mp3TileEntity.isEnablePlay(),mp3TileEntity.getPos1x(), mp3TileEntity.getPos1y(),mp3TileEntity.getPos1z(),
                mp3TileEntity.getPos2x(), mp3TileEntity.getPos2y(), mp3TileEntity.getPos2z(), mp3TileEntity.getPos()));
    }
}
