package github.nooblong.mr.init;

import github.nooblong.mr.MusicRestaurant;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.File;

@Mod.EventBusSubscriber(modid = MusicRestaurant.MOD_ID, value = Dist.CLIENT)
public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, MusicRestaurant.MOD_ID);

//
////    public static final SoundEvent TEST_SOUND = SOUNDS.register("test", ()->new SoundEvent(new ResourceLocation()));
//
//    public static final SoundEvent TEST_SOUND = new SoundEvent(new ResourceLocation(MusicRestaurant.MOD_ID,"sounds/test.ogg")).setRegistryName("test");
//
//    @SubscribeEvent
//    public static void registerSound(RegistryEvent.Register<SoundEvent> event){
//        event.getRegistry().register(TEST_SOUND);
//    }

    public static SoundEvent TEST = registerSound("test");

    private static SoundEvent registerSound(String soundName) {
        SoundEvent event = new SoundEvent(new ResourceLocation(MusicRestaurant.MOD_ID, soundName));
        event.setRegistryName(new ResourceLocation(MusicRestaurant.MOD_ID, soundName));
        return event;
    }

    @SubscribeEvent
    public static void registerSound(RegistryEvent.Register<SoundEvent> event){
        event.getRegistry().registerAll(
                ModSounds.TEST
        );
    }

//    public static void registerAllServerSound(){
//        System.out.println("registerAllServerSound()");
//        File music = new File("music");
//        File[] files = music.listFiles();
//        if (files != null) {
//            for (File f : files) {
//                System.out.println(f.getName());
//                ResourceLocation resourcelocation = new ResourceLocation("music/" + f.getName());
//                RegistryObject<SoundEvent> toReg = SOUNDS.register(f.getName(), () -> new SoundEvent(resourcelocation));
//            }
//            SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
//        }
//    }

}
