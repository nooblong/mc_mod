package github.nooblong.mr.init;

import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.blocks.BlockItemBase;
import github.nooblong.mr.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MusicRestaurant.MOD_ID);

    //items
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);

    //blockItem
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block",
            () -> new BlockItemBase(ModBlocks.RUBY_BLOCK.get()));

    public static final RegistryObject<Item> RUBY_CHEST_ITEM = ITEMS.register("ruby_chest",
            ()->new BlockItemBase(ModBlocks.RUBY_CHEST.get()));
}
