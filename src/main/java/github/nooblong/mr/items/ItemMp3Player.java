package github.nooblong.mr.items;

import github.nooblong.mr.MusicRestaurant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ItemMp3Player extends ItemBase {

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        ItemStack itemStackIn = context.getItem();
        CompoundNBT nbtTagCompound = itemStackIn.getTag();
        PlayerEntity playerIn = context.getPlayer();
        if (playerIn != null) {
            if (playerIn.isSneaking()) {
                if (nbtTagCompound == null) {
                    nbtTagCompound = new CompoundNBT();
                    itemStackIn.setTag(nbtTagCompound);
                }
                BlockPos voiceBox = context.getPos();
                nbtTagCompound.putBoolean("bound", true);
                nbtTagCompound.putDouble("x", voiceBox.getX());
                nbtTagCompound.putDouble("y", voiceBox.getY());
                nbtTagCompound.putDouble("z", voiceBox.getZ());
                if (playerIn.world.isRemote) {
                    playerIn.sendMessage(new StringTextComponent("bound " + voiceBox.getX() + " " + voiceBox.getY() + " " + voiceBox.getZ() + " "));
                }
                return ActionResultType.SUCCESS;
            }
            boolean bound = false;
            if (nbtTagCompound != null && nbtTagCompound.contains("bound")) {
                bound = nbtTagCompound.getBoolean("bound");
            }
            if (bound) {
                double x = nbtTagCompound.getDouble("x");
                double y = nbtTagCompound.getDouble("y");
                double z = nbtTagCompound.getDouble("z");
                if (playerIn.world.isRemote) {
                    playerIn.sendMessage(new StringTextComponent("has bounded! " + x + " " + y + " " + z + " "));
                }
            } else {
                if (playerIn.world.isRemote) {
                    playerIn.sendMessage(new StringTextComponent("not bound"));
                }
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote){
            INamedContainerProvider containerProviderMp3Player = new ContainerProviderMp3Player(this, stack);
            final int NUMBER_OF_SLOTS = 0;
            NetworkHooks.openGui((ServerPlayerEntity)playerIn, containerProviderMp3Player, packetBuffer -> packetBuffer.writeInt(NUMBER_OF_SLOTS));
        }
        return ActionResult.resultSuccess(stack);
    }

    private ItemStackHandlerMp3Player getItemStackHandlerMp3Player(ItemStack itemStack){
        IItemHandler mp3Player = itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
        if (mp3Player == null || !(mp3Player instanceof ItemStackHandlerMp3Player)){
            MusicRestaurant.LOGGER.error("item mp3 player did not have the expected ITEM_HANDLER_CAPABILITY");
            return new ItemStackHandlerMp3Player();
        }
        return (ItemStackHandlerMp3Player)mp3Player;
    }

    private static class ContainerProviderMp3Player implements INamedContainerProvider{

        private ItemMp3Player itemMp3Player;
        private ItemStack itemStackMp3Player;

        public ContainerProviderMp3Player(ItemMp3Player itemMp3Player, ItemStack itemStackMp3Player){
            this.itemMp3Player = itemMp3Player;
            this.itemStackMp3Player = itemStackMp3Player;
        }

        @Override
        public ITextComponent getDisplayName() {
            return itemStackMp3Player.getDisplayName();
        }

        @Override
        public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
            ContainerMp3Player newContainerServerSide =
                    ContainerMp3Player.createContainerServerSide(windowId, playerInventory,
                            itemMp3Player.getItemStackHandlerMp3Player(itemStackMp3Player),
                            itemStackMp3Player);
            return newContainerServerSide;
        }
    }
}
