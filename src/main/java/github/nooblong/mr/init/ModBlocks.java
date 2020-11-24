package github.nooblong.mr.init;

import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.blocks.BlockBase;
import github.nooblong.mr.blocks.BlockMp3;
import github.nooblong.mr.blocks.BlockSound;
import github.nooblong.mr.blocks.RubyChestBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MusicRestaurant.MOD_ID);

    //blocks
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", BlockBase::new);
    public static final RegistryObject<Block> RUBY_CHEST = BLOCKS.register("ruby_chest", RubyChestBlock::new);
    public static final RegistryObject<Block> SOUND_BLOCK = BLOCKS.register("sound_block", BlockSound::new);

    public static final RegistryObject<Block> MP3 = BLOCKS.register("mp3", BlockMp3::new);
}
