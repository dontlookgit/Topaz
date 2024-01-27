package dev.daydreamers.topaz.client.utils;

import dev.daydreamers.topaz.client.features.combat.Killaura;
import dev.daydreamers.topaz.client.features.movement.*;
import dev.daydreamers.topaz.client.features.player.Nofall;
import dev.daydreamers.topaz.client.features.player.Retard;
import dev.daydreamers.topaz.client.features.player.Sneak;
import dev.daydreamers.topaz.client.features.world.Autotool;

public class Events {

    public static void onPreTick() {
        Command.onCommand();
        Step.onStep();
        Nofall.onNofall();
        //PlayerESP.nametag();
        Retard.onRetard();
        Sprint.onSprint();
        Fly.onFly();
        Sneak.onSneak();
        Autotool.onAutotool();
        Dolphin.onDolphin();
        Killaura.onKillaura();
        BoatFly.onBoatFly();
    }

    public static void onPostTick() {

    }

}
