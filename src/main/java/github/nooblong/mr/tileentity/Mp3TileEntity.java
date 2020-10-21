package github.nooblong.mr.tileentity;

import github.nooblong.mr.init.ModTileEntityTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class Mp3TileEntity extends TileEntity {

    private boolean enablePlay;
    private int pos1x;
    private int pos1y;
    private int pos1z;
    private int pos2x;
    private int pos2y;
    private int pos2z;

    public Mp3TileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public Mp3TileEntity(){
        this(ModTileEntityTypes.MP3.get());
    }


    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        enablePlay = compound.getBoolean("enablePlay");
        pos1x = compound.getInt("pos1x");
        pos1y = compound.getInt("pos1y");
        pos1z = compound.getInt("pos1z");
        pos2x = compound.getInt("pos2x");
        pos2y = compound.getInt("pos2y");
        pos2z = compound.getInt("pos2z");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("enablePlay", enablePlay);
        compound.putInt("pos1x",pos1x);
        compound.putInt("pos1y",pos1y);
        compound.putInt("pos1z",pos1z);
        compound.putInt("pos2x",pos2x);
        compound.putInt("pos2y",pos2y);
        compound.putInt("pos2z",pos2z);

        return super.write(compound);
    }

    public boolean isEnablePlay() {
        return enablePlay;
    }

    public void setEnablePlay(boolean enablePlay) {
        this.enablePlay = enablePlay;
    }

    public int getPos1x() {
        return pos1x;
    }

    public void setPos1x(int pos1x) {
        this.pos1x = pos1x;
    }

    public int getPos1y() {
        return pos1y;
    }

    public void setPos1y(int pos1y) {
        this.pos1y = pos1y;
    }

    public int getPos1z() {
        return pos1z;
    }

    public void setPos1z(int pos1z) {
        this.pos1z = pos1z;
    }

    public int getPos2x() {
        return pos2x;
    }

    public void setPos2x(int pos2x) {
        this.pos2x = pos2x;
    }

    public int getPos2y() {
        return pos2y;
    }

    public void setPos2y(int pos2y) {
        this.pos2y = pos2y;
    }

    public int getPos2z() {
        return pos2z;
    }

    public void setPos2z(int pos2z) {
        this.pos2z = pos2z;
    }
}
