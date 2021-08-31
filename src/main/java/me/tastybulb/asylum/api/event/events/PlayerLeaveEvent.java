package me.tastybulb.asylum.api.event.events;

import me.tastybulb.asylum.api.event.Event;

public class PlayerLeaveEvent extends Event {

	private final String name;

	public PlayerLeaveEvent(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}