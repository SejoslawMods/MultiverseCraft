package com.github.sejoslaw.multiversecraft;

import com.github.sejoslaw.multiversecraft.items.DestinationBookItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MultiverseCraftItems {
    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MultiverseCraft.MOD_ID, "multiverse_group"));

    public static final RegistryKey<Item> DESTINATION_BOOK_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MultiverseCraft.MOD_ID, "destination_book"));
    public static final Item DESTINATION_BOOK_ITEM = new DestinationBookItem(new Item.Settings().maxCount(1).enchantable(1).registryKey(DESTINATION_BOOK_KEY));

    public static void initialize() {
        Registry.register(Registries.ITEM, DESTINATION_BOOK_KEY, DESTINATION_BOOK_ITEM);

        Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, buildModItemGroup());
    }

    private static ItemGroup buildModItemGroup() {
        return FabricItemGroup
                .builder()
                .displayName(Text.literal(MultiverseCraft.MOD_TITLE))
                .icon(() -> new ItemStack(DESTINATION_BOOK_ITEM))
                .entries(MultiverseCraftItems::fillItemGroupEntries)
                .build();
    }

    private static void fillItemGroupEntries(ItemGroup.DisplayContext displayContext, ItemGroup.Entries entries) {
        // TODO: All mod items
        entries.add(new ItemStack(DESTINATION_BOOK_ITEM));

        // TODO: One Destination Book per registered dimension at specific location (maybe 100x100x100 ?)
    }
}
