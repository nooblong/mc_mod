package github.nooblong.mr.init;

import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.container.RubyChestContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, MusicRestaurant.MOD_ID);

//    public static final RegistryObject<ContainerType<RubyChestContainer>> RUBY_CHEST = CONTAINER_TYPES.register("ruby_chest",
//            () -> IForgeContainerType.create(RubyChestContainer::new, ModBlocks.RUBY_CHEST.get()));

    public static final RegistryObject<ContainerType<RubyChestContainer>> RUBY_CHEST = CONTAINER_TYPES.register(
            "ruby_chest", new Supplier<ContainerType<RubyChestContainer>>() {
                @Override
                public ContainerType<RubyChestContainer> get() {
                    return IForgeContainerType.create(RubyChestContainer::new);
                }
            });
}
