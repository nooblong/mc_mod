package github.nooblong.mr.events;

import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.init.ModBlocks;
import github.nooblong.mr.init.ModItems;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MusicRestaurant.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onJumpWithStick(LivingEvent.LivingJumpEvent event){
        LivingEntity player = event.getEntityLiving();
        if (player.getHeldItemMainhand().getItem() == Items.STICK){
            MusicRestaurant.LOGGER.info("JUMP");
            World world = player.getEntityWorld();
            world.setBlockState(player.getPosition().add(0,-1,0), ModBlocks.RUBY_BLOCK.get().getDefaultState());
        }
    }

    @SubscribeEvent
    public static void onDamageEntity(AttackEntityEvent event){
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.RUBY.get()){
            if (event.getTarget().isAlive()){
                LivingEntity target = (LivingEntity) event.getTarget();
                target.addPotionEffect(new EffectInstance(Effects.POISON,200));
                target.setFire(5);
                target.setGlowing(true);

                if (!event.getPlayer().getEntityWorld().isRemote){
                    String msg = TextFormatting.RED + "FIRE!!!";
                    PlayerEntity player = event.getPlayer();
                    player.sendMessage(new StringTextComponent(msg + " " + player.getUniqueID()));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onCraftingTableOpen(GuiOpenEvent event){
        if (event.isCancelable()){
            if (event.getGui() instanceof CraftingScreen){
                MusicRestaurant.LOGGER.info("OPEN!!!");
                event.setCanceled(true);
            }
        }
    }
}
