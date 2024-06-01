package com.costin;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("mccfs_contentmod");

	public static final KeycardItem keycardL1 = new KeycardItem(new Item.Settings(), 1);
	public static final KeycardItem keycardL2 = new KeycardItem(new Item.Settings(), 2);
	public static final KeycardItem keycardL3 = new KeycardItem(new Item.Settings(), 3);
	public static final KeypadBlock keypadL1 = new KeypadBlock(Block.Settings.create().strength(4), 1);
	public static final KeypadBlock keypadL2 = new KeypadBlock(Block.Settings.create().strength(4), 2);
	public static final KeypadBlock keypadL3 = new KeypadBlock(Block.Settings.create().strength(4), 3);

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier("mccfs_contentmod", "keycard_lvl1"), keycardL1);
		Registry.register(Registries.ITEM, new Identifier("mccfs_contentmod", "keycard_lvl2"), keycardL2);
		Registry.register(Registries.ITEM, new Identifier("mccfs_contentmod", "keycard_lvl3"), keycardL3);

		Registry.register(Registries.BLOCK, new Identifier("mccfs_contentmod", "keypad_lvl1"), keypadL1);
		Registry.register(Registries.ITEM, new Identifier("mccfs_contentmod", "keypad_lvl1"), new BlockItem(keypadL1, new Item.Settings()));

		Registry.register(Registries.BLOCK, new Identifier("mccfs_contentmod", "keypad_lvl2"), keypadL2);
		Registry.register(Registries.ITEM, new Identifier("mccfs_contentmod", "keypad_lvl2"), new BlockItem(keypadL2, new Item.Settings()));

		Registry.register(Registries.BLOCK, new Identifier("mccfs_contentmod", "keypad_lvl3"), keypadL3);
		Registry.register(Registries.ITEM, new Identifier("mccfs_contentmod", "keypad_lvl3"), new BlockItem(keypadL3, new Item.Settings()));

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
			content.add(keycardL1);
			content.add(keycardL2);
			content.add(keycardL3);

			content.add(keypadL1);
			content.add(keypadL2);
			content.add(keypadL3);
		});
	}
}