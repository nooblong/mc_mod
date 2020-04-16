package github.nooblong.lolskill.common.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import github.nooblong.lolskill.client.CreativeTab;
import github.nooblong.lolskill.common.particle.FlameParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@Mod.EventBusSubscriber
public class ItemRiYan extends Item implements IBauble {

    @GameRegistry.ObjectHolder("lolskill:riyan")
    public static final Item RIYAN = null;

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (!worldIn.isRemote) {
            EntityPlayer player = (EntityPlayer)entityIn;
//            for (int i = 0; i < 10; i++) {
                Random random = new Random();
                FlameParticle newEffect = new FlameParticle(worldIn,
                        player.posX + random.nextDouble() * 5, player.posY,
                        player.posZ + random.nextDouble() * 5, 0, 0.1, 0);
                Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
                FlameParticle newEffect2 = new FlameParticle(worldIn,
                        player.posX - random.nextDouble() * 5, player.posY,
                        player.posZ - random.nextDouble() * 5, 0, 0.1, 0);
                Minecraft.getMinecraft().effectRenderer.addEffect(newEffect2);
//            }
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.TRINKET;
    }

    public ItemRiYan(){
        setUnlocalizedName("RiYan").setCreativeTab(CreativeTab.skillTab).setRegistryName("riyan");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemRiYan());
    }
}
