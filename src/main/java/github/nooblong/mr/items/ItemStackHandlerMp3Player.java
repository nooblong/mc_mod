package github.nooblong.mr.items;

import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerMp3Player extends ItemStackHandler {

    public static final int SLOTS = 0;

    private boolean isDirty = true;

    public boolean isDirty(){
        boolean currentState = isDirty;
        isDirty = false;
        return currentState;
    }

    @Override
    protected void onContentsChanged(int slot) {
        isDirty = true;
    }
}
