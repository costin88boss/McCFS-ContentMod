package com.costin;

import net.minecraft.item.Item;

public class KeycardItem extends Item {
    public int getClearanceLevel() {
        return clearanceLevel;
    }

    private final int clearanceLevel;

    public KeycardItem(Settings settings, int clearanceLevel) {
        super(settings);
        this.clearanceLevel = clearanceLevel;
    }
}
