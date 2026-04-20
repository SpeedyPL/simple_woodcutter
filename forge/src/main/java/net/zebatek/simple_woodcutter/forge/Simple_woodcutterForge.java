package net.zebatek.simple_woodcutter.forge;

import net.zebatek.simple_woodcutter.Simple_woodcutter;
import net.minecraftforge.fml.common.Mod;

@Mod(Simple_woodcutter.MOD_ID)
public final class Simple_woodcutterForge {
    public Simple_woodcutterForge() {
        // Run our common setup.
        Simple_woodcutter.init();
    }
}
