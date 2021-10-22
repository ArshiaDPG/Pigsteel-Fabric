package net.digitalpear.pigsteel.registering;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.util.Identifier;

public class Id {
    static Identifier mod(String path) {
        return new Identifier(PigsteelMod.MOD_ID, path);
    }
}
