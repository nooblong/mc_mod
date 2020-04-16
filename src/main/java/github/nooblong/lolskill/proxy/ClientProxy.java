package github.nooblong.lolskill.proxy;

import github.nooblong.lolskill.client.ClientEventHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    public static final KeyBinding KEY_SKILL_1 = new KeyBinding("keybind.lolskill", Keyboard.KEY_F,
            "key.categories.inventory");

    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        ClientRegistry.registerKeyBinding(KEY_SKILL_1);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        registerEventHandlers();
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
