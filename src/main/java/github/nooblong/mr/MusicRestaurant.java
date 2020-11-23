package github.nooblong.mr;

import github.nooblong.mr.net.PacketManager;
import github.nooblong.mr.net.SimpleNetworkHandler;
import github.nooblong.mr.init.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mr")
public class MusicRestaurant {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mr";
    public static PacketManager PACKET_MANAGER;
    public MusicRestaurant() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModContainerTypes.CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        SimpleNetworkHandler.registerMessage();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PACKET_MANAGER = new PacketManager();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    public static final ItemGroup TAB = new ItemGroup("MusicRestaurant") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.RUBY.get());
        }
    };


}
