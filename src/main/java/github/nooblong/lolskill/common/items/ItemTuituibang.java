package github.nooblong.lolskill.common.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import github.nooblong.lolskill.client.CreativeTab;
import github.nooblong.lolskill.client.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class ItemTuituibang extends Item implements IBauble {

    @GameRegistry.ObjectHolder("lolskill:tuituibang")
    public static final Item TUITUIBANG = null;

    public ItemTuituibang(){
        setUnlocalizedName("TuiTuibang").setCreativeTab(CreativeTab.skillTab).setRegistryName("tuituibang");
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.TRINKET;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemTuituibang());
    }
}
