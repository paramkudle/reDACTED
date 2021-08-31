package me.tastybulb.asylum.api.event.events;

import me.tastybulb.asylum.api.event.Event;

public class SpawnEffectEvent extends Event {

    private int particleID;

    public SpawnEffectEvent(int particleID) {
        this.particleID = particleID;
    }

    public int getParticleID() {
        return particleID;
    }

    public void setParticleID(int particleID) {
        this.particleID = particleID;
    }
}