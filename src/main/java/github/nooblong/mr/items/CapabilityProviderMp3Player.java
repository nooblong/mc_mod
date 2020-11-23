package github.nooblong.mr.items;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

public class CapabilityProviderMp3Player implements ICapabilitySerializable<INBT> {

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return null;
    }

    @Override
    public INBT serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(INBT nbt) {

    }
}
