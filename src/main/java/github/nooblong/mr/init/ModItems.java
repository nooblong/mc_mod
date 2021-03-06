package github.nooblong.mr.init;

import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.blocks.BlockItemBase;
import github.nooblong.mr.items.ItemBase;
import github.nooblong.mr.items.ItemMp3Player;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MusicRestaurant.MOD_ID);

    //items
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);

    public static final RegistryObject<Item> MP3_PLAYER = ITEMS.register("mp3_player", ItemMp3Player::new);

    //blockItem
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block",
            () -> new BlockItemBase(ModBlocks.RUBY_BLOCK.get()));

    public static final RegistryObject<Item> RUBY_CHEST_ITEM = ITEMS.register("ruby_chest",
            ()->new BlockItemBase(ModBlocks.RUBY_CHEST.get()));

    public static final RegistryObject<Item> MP3_ITEM = ITEMS.register("mp3",
            ()->new BlockItemBase(ModBlocks.MP3.get()));

    public static final RegistryObject<Item> SOUND_BLOCK_ITEM = ITEMS.register("sound_block",
            () -> new BlockItemBase(ModBlocks.SOUND_BLOCK.get()));
}
