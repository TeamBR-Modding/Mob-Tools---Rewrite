package com.pauljoda.mobtools.rendering;

import net.minecraft.world.World;

public class ParticleHelper {
	
	public static void spawnEnderCircles(World world, int x, int y, int z)
	{
        world.playAuxSFX(2003, (int)Math.round(x), (int)Math.round(y), (int)Math.round(z), 0);
	}

}
