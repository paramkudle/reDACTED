package me.tastybulb.asylum.api.event.events;

import me.tastybulb.asylum.api.event.Event;

public class EventPlayerLeave extends Event {

	private final String name;

	public EventPlayerLeave(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}