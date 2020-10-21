package github.nooblong.mr.blocks;

import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.gui.Mp3Gui;
import github.nooblong.mr.init.ModSounds;
import github.nooblong.mr.tileentity.RubyChestTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockMp3 extends BlockBase {

    @OnlyIn(Dist.CLIENT)
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote){
            //客户端上打开gui
            Mp3Gui mp3Gui = new Mp3Gui();
            Runnable displayGui = () -> Minecraft.getInstance().displayGuiScreen(mp3Gui);
            Thread thread = new Thread(displayGui);
            thread.start();
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity player, ItemStack stack) {
//        worldIn.playSound(null, pos,ModSounds.TEST, SoundCategory.MUSIC, 1.0f, 1.0f);
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
//        if (!worldIn.isRemote) {
            worldIn.playSound(x, y, z, ModSounds.TEST, SoundCategory.MUSIC, 1.0f, 1.0f, true);
            System.out.println("play sound");
//        }
    }


}
