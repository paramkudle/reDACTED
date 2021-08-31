package me.tastybulb.asylum.api.event.events;

import me.tastybulb.asylum.api.event.Event;

public class EventPlayerJoin extends Event {

	private final String name;

	public EventPlayerJoin(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}