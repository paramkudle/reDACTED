package me.tastybulb.asylum.api.event.events;

import me.tastybulb.asylum.api.event.Event;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class LiquidCollisionBBEvent extends Event {
	
	private AxisAlignedBB boundingBox;
    private BlockPos blockPos;

    public LiquidCollisionBBEvent() {

    }

    public LiquidCollisionBBEvent(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public AxisAlignedBB getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(AxisAlignedBB boundingBox) {
        this.boundingBox = boundingBox;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }
}
