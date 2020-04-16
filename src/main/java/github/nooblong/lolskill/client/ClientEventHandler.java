package github.nooblong.lolskill.client;

import github.nooblong.lolskill.common.items.ItemRiYan;
import github.nooblong.lolskill.common.items.ItemTuituibang;
import github.nooblong.lolskill.common.particle.FlameParticle;
import github.nooblong.lolskill.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovementInput;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.Random;

public class ClientEventHandler {
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(ItemTuituibang.TUITUIBANG, 0,
                new ModelResourceLocation("lolskill:tuituibang", "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemRiYan.RIYAN, 0,
                new ModelResourceLocation("lolskill:riyan", "inventory"));

    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (ClientProxy.KEY_SKILL_1.isPressed()) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            World world = Minecraft.getMinecraft().world;
            world.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, player.posX, player.posY, player.posZ, 1, 1, 1);
//            FlameParticle newEffect = new FlameParticle(world, player.posX, player.posY + 1, player.posZ, 0, 0.1, 0);
//            Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
            Vec3d face = player.getLookVec();
//            player.setLocationAndAngles(player.posX,player.posY,player.posZ,(float)face.y,(float)face.z);
//            System.out.println(world.rayTraceBlocks(player.getPositionVector(), player.getPositionVector().add(face50)));
//            System.out.println(face);
//            System.out.println(player.getPositionVector());

            //new Vec2f(this.rotationPitch, this.rotationYaw);
            double pitch = ((player.getPitchYaw().x + 90)*Math.PI)/180;
            double yaw = ((player.getPitchYaw().y + 90)*Math.PI)/180;

            double x = Math.sin(pitch) * Math.cos(yaw);
            double y = Math.sin(pitch) * Math.sin(yaw);
            double z = Math.cos(pitch);

            System.out.println(x + " " + y + " " + z);

            Vec3d vector = new Vec3d(x, z, y);
        }
    }

    @SubscribeEvent
    public void stitcherEventPre(TextureStitchEvent.Pre event) {
        ResourceLocation flameRL = new ResourceLocation("lolskill:entity/flame_fx");
        event.getMap().registerSprite(flameRL);
    }

}
