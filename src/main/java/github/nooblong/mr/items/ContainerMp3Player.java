package github.nooblong.mr.items;

import github.nooblong.mr.init.ModContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ContainerMp3Player extends Container {

    public static ContainerMp3Player createContainerServerSide(int windowId, PlayerInventory playerInventory, ItemStackHandlerMp3Player mp3PlayerContents, ItemStack mp3Player){
        return new ContainerMp3Player(windowId, playerInventory, mp3PlayerContents, mp3Player);
    }

    public static ContainerMp3Player createContainerClientSide(int windowID, PlayerInventory playerInventory, PacketBuffer extraData){
        ItemStackHandlerMp3Player itemStackHandlerMp3Player = new ItemStackHandlerMp3Player();
        return new ContainerMp3Player(windowID, playerInventory, itemStackHandlerMp3Player, ItemStack.EMPTY);
    }

    private final ItemStackHandlerMp3Player itemStackHandlerMp3Player;
    private final ItemStack itemStackBeingHeld;

    private ContainerMp3Player(int windowId, PlayerInventory playerInventory, ItemStackHandlerMp3Player itemStackHandlerMp3Player, ItemStack itemStackBeingHeld){
        super(ModContainerTypes.containerTypeMp3Player, windowId);
        this.itemStackHandlerMp3Player = itemStackHandlerMp3Player;
        this.itemStackBeingHeld = itemStackBeingHeld;

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        ItemStack main = playerIn.getHeldItemMainhand();
        ItemStack off = playerIn.getHeldItemOffhand();
        return (!main.isEmpty() && main == itemStackBeingHeld) ||
                (!off.isEmpty() && off == itemStackBeingHeld);
    }

}
