package com.pauljoda.mobtools.tileentities;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class MobToolsSpawnerLogic {

	public static void randomSpawn(Entity entity, World world, int x, int y, int z)
	{
		Random r = new Random();
		int mod;
		mod = r.nextInt(2);
		if(mod == 0)
			x = x + r.nextInt(4);
		else
			x = x - r.nextInt(4);

		mod = r.nextInt(2);
		if(mod == 0)
			z = z + r.nextInt(4);
		else
			z = z - r.nextInt(4);

		entity.setLocationAndAngles(x, y, z, 360F, 0F);
		entity.getEntityData().setBoolean("fromMobTools", true);
		world.spawnEntityInWorld(entity);
	}

	public static Entity getEntityByName(String mobName, World world)
	{

		if(mobName.equals("Cow")) return new EntityCow(world);
		if(mobName.equals("Chicken")) return new EntityChicken(world);
		if(mobName.equals("Pig")) return new EntityPig(world);
		if(mobName.equals("Sheep")) return new EntitySheep(world);
		if(mobName.equals("Villager")) return new EntityVillager(world);
		if(mobName.equals("Enderman")) return new EntityEnderman(world);
		if(mobName.equals("Spider")) return new EntitySpider(world);
		if(mobName.equals("Blaze")) return new EntityBlaze(world);
		if(mobName.equals("Skeleton")) return new EntitySkeleton(world);
		if(mobName.equals("Slime")) return new EntitySlime(world);
		if(mobName.equals("Creeper")) return new EntityCreeper(world);
		if(mobName.equals("Zombie")) return new EntityZombie(world);

		return new EntityPig(world);
	}

	public static boolean canSpawn(TileEntityMobToolsSpawner spawner, World world, int x, int y, int z, int tier, String mobName, String dimension)
	{
		if(tier == 5 && world.getBlockPowerInput(x, y, z) == 0)
			return true;
		else if(!dimension.equals("Nether"))
		{
			if(tier == 4)
			{
				return true;
			}

			if(world.getClosestPlayer((double)x + 0.5D, (double)y, (double)z + 0.5D, 10) != null)
			{
				if(tier == 3)
				{
					return true;
				}

				if(world.getLightBrightness(x, y, z) < 0.5)
				{
					if(tier == 2)
					{
						return true;
					}
					if(isAnimal(mobName))
					{

						if(tier == 1)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean isAnimal(String mobName)
	{
		if(mobName.equals("Cow") || mobName.equals("Chicken") || mobName.equals("Pig") || mobName.equals("Sheep"))
			return true;

		return false;
	}

	public static int getMaxCoolDown(int tier)
	{
		switch(tier)
		{
		case 1: return 2400;
		case 2: return 1200;
		case 3: return 900;
		case 4: return 600;
		case 5: return 200;
		}
		return 600;
	}

}
