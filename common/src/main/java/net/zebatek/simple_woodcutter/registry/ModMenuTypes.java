package net.zebatek.simple_woodcutter.registry;

import net.minecraft.world.inventory.MenuType;
import net.zebatek.simple_woodcutter.menu.WoodcutterMenu;
import net.zebatek.simple_woodcutter.platform.Services;

public class ModMenuTypes {
    public static MenuType<WoodcutterMenu> getMENU() {
        return Services.PLATFORM.getMenuType();
    }
}