package dev.daydreamers.topaz.client.utils;

import dev.daydreamers.topaz.client.features.movement.*;
import dev.daydreamers.topaz.client.features.player.*;
import dev.daydreamers.topaz.client.features.render.*;
import dev.daydreamers.topaz.client.features.world.*;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import static com.mojang.brigadier.arguments.FloatArgumentType.floatArg;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;


public class Command {


    public static void onCommand() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".sprint").executes(context -> {
                        Sprint.toggle = !Sprint.toggle;
                        return 1;
                    })
            );
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".nofall").executes(context -> {
                        Nofall.toggle = !Nofall.toggle;
                        return 1;
                    })
            );
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".retard").executes(context -> {
                        Retard.toggle = !Retard.toggle;
                        return 1;
                    })
            );
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".step").executes(context -> {
                        Step.toggle = !Step.toggle;
                        return 1;
                    })
            );
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".autotool").executes(context -> {
                        Autotool.toggle = !Autotool.toggle;
                        return 1;
                    })
            );
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".fly").executes(context -> {
                        Fly.toggle = !Fly.toggle;
                        return 1;
                    })
            );
        });
        /*ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".fly boatfly").executes(context -> {
                        BoatFly.toggle = !BoatFly.toggle;
                        return 1;
                    })
            );
        });*/
        /*ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".fly").then(argument("speed", floatArg(0.0f, 5.0f))).executes(context -> {
                        float speed = context.getArgument("speed", Float.class);
                        Fly.speed = speed;
                        BoatFly.speed = speed;
                        return 1;
                    })
            );
        });*/
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".speedmine").executes(context -> {
                        Speedmine.toggle = !Speedmine.toggle;
                        return 1;
                    })
            );
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".fullbright").executes(context -> {
                        Fullbright.toggle = !Fullbright.toggle;
                        return 1;
                    })
            );
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".sneak").executes(context -> {
                        Sneak.toggle = !Sneak.toggle;
                        return 1;
                    })
            );
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".dolphin").executes(context -> {
                        Dolphin.toggle = !Dolphin.toggle;
                        return 1;
                    })
            );
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal(".up").then(argument("height", integer(-10, 10)).executes(context -> {
                int height = getInteger(context, "height");
                assert Wrapper.getMC().player != null;
                int x = (int) Wrapper.getMC().player.getX();
                int z = (int) Wrapper.getMC().player.getZ();
                Wrapper.getMC().player.setPos(x, height, z);
                return 1;
                    })
            ));
        });
    }
}
