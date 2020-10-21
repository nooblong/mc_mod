package github.nooblong.mr.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.Main;
import net.minecraft.client.resources.SkinManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.io.File;
import java.lang.reflect.Field;

public class ReflectionUtils {

    public static void getSoundPath(){
        try {

            Minecraft minecraft = Minecraft.getInstance();

            SkinManager skinManager = minecraft.getSkinManager();

            Object skinCacheDir = ObfuscationReflectionHelper.getPrivateValue(SkinManager.class, skinManager, "field_152796_d");

            String path = ((File)skinCacheDir).getPath();


            for (int i = 0; i < 20; i++) {
                System.out.println(path);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
