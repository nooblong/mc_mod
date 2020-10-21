//package github.nooblong.mr.sound;
//
//import github.nooblong.mr.init.ModSounds;
//import net.minecraft.client.Minecraft;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SoundEvent;
//import net.minecraftforge.fml.RegistryObject;
//import net.minecraftforge.registries.DeferredRegister;
//
//import java.io.File;
//
//public class SoundProcessor {
//
//    public static void registerAllServerSound(){
//        File music = new File("music");
//        File[] files = music.listFiles();
//        if (files != null) {
//            for (File f : files) {
//                System.out.println(f.getName());
//                ResourceLocation resourcelocation = new ResourceLocation("music/" + f.getName());
//                RegistryObject<SoundEvent> toReg = ModSounds.registerSound();
//            }
//        }
//    }
//
//}
