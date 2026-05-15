package net.zebatek.simple_woodcutter.platform;

import java.util.ServiceLoader;

public class Services {
    public static final IPlatformHelper PLATFORM = ServiceLoader.load(IPlatformHelper.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("IPlatformHelper not found!"));
}
