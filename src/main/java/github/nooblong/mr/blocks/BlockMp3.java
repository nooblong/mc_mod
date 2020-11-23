package github.nooblong.mr.blocks;

import github.nooblong.mr.net.GuiDataPacket;
import github.nooblong.mr.net.SimpleNetworkHandler;
import github.nooblong.mr.gui.Mp3Gui;
import github.nooblong.mr.init.ModSounds;
import github.nooblong.mr.tileentity.Mp3TileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.PacketDistributor;

public class BlockMp3 extends BlockBase {

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote){
            Mp3TileEntity t = (Mp3TileEntity) worldIn.getTileEntity(pos);
            System.out.println("server send Gui packet");
            SimpleNetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)player), new GuiDataPacket(
                    t.isEnablePlay(),t.getPos1x(),t.getPos1y(),t.getPos1z(),t.getPos2x(),t.getPos2y(),t.getPos2z(),t.getPos()
            ));
            return ActionResultType.SUCCESS;
        } else {
            f(worldIn,pos);
        }
        return ActionResultType.SUCCESS;
    }
    @OnlyIn(Dist.CLIENT)
    private static void f(World worldIn, BlockPos pos){
        System.out.println("client open gui");
        Mp3Gui mp3Gui = new Mp3Gui((Mp3TileEntity) worldIn.getTileEntity(pos));
        Minecraft.getInstance().displayGuiScreen(mp3Gui);
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

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new Mp3TileEntity();
    }
}
