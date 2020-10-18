package github.nooblong.mr.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {
    public BlockBase() {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f,5.0f)
        .sound(SoundType.METAL).harvestLevel(0));
    }
}
