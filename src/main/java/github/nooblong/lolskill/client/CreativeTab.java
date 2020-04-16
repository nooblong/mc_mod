package github.nooblong.lolskill.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTab {
    public static CreativeTabs skillTab = new CreativeTabs("LOL Skill") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.BOOK);
        }
    };

    public static void tabRegister(){

    }

}
