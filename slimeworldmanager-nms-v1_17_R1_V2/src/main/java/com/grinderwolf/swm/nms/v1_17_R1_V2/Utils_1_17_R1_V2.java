package com.grinderwolf.swm.nms.v1_17_R1_V2;

import net.minecraft.server.MinecraftServer;

public class Utils_1_17_R1_V2 {
    public static java.util.concurrent.Executor getExecutor(MinecraftServer minecraftServer) {
        return minecraftServer.az;
    }
}
