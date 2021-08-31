package me.tastybulb.asylum.api.event.events;

import me.tastybulb.asylum.api.event.Event;
import me.tastybulb.asylum.api.utils.Location;

public class JumpEvent extends Event {

	private Location location;

	public JumpEvent(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}