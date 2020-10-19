package github.nooblong.mr.init;

import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.tileentity.RubyChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MusicRestaurant.MOD_ID);

    public static final RegistryObject<TileEntityType<RubyChestTileEntity>> RUBY_CHEST = TILE_ENTITY_TYPES.register("ruby_chest",
            ()-> TileEntityType.Builder.create(RubyChestTileEntity::new, ModBlocks.RUBY_CHEST.get()).build(null));
}
