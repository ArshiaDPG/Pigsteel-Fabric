package net.digitalpear.pigsteel.common.datagen.tags;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.tags.PigsteelItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class PigsteelItemTagProvider extends FabricTagProvider<Item> {

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public PigsteelItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, Registries.ITEM.getKey(), registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(PigsteelItemTags.PIGSTEEL_ORES)
                .add(PigsteelBlocks.PORKSLAG.asItem());

        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS).add(PigsteelItems.PIGSTEEL_CHUNK);

        getOrCreateTagBuilder(PigsteelItemTags.C_ORES)
                .forceAddTag(PigsteelItemTags.PIGSTEEL_ORES);

    }
}
