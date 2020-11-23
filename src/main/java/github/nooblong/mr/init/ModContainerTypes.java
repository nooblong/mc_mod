package github.nooblong.mr.init;

import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.container.RubyChestContainer;
import github.nooblong.mr.items.ContainerMp3Player;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, MusicRestaurant.MOD_ID);

    public static final RegistryObject<ContainerType<RubyChestContainer>> RUBY_CHEST = CONTAINER_TYPES.register(
            "ruby_chest", () -> IForgeContainerType.create(RubyChestContainer::new));

    public static ContainerType<ContainerMp3Player> containerTypeMp3Player = IForgeContainerType.create(ContainerMp3Player::createContainerClientSide);

    public static final RegistryObject<ContainerType<ContainerMp3Player>> MP3_PLAYER = CONTAINER_TYPES.register(
            "mp3_player", () -> containerTypeMp3Player
    );
}
