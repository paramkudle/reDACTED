package me.tastybulb.asylum.api.event.events;

import me.tastybulb.asylum.api.event.Event;
import net.minecraft.util.EnumHandSide;

public class TransformSideFirstPersonEvent extends Event {

	private final EnumHandSide enumHandSide;

	public TransformSideFirstPersonEvent(EnumHandSide enumHandSide){
		this.enumHandSide = enumHandSide;
	}

	public EnumHandSide getEnumHandSide(){
		return this.enumHandSide;
	}
}