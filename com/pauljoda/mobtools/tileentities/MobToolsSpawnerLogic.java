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
			x = x + r.nextInt(8);
		else
			x = x - r.nextInt(8);

		mod = r.nextInt(2);
		if(mod == 0)
			z = z + r.nextInt(8);
		else
			z = z - r.nextInt(8);

		entity.setLocationAndAngles(x, y, z, 360F, 0F);
		entity.getEntityData().setBoolean("fromMobTools", true);
		world.spawnEntityInWorld(entity);
	}

	public static Entity getEntityByName(String mobName, World world)
	{

		if(mobName == "Cow") return new EntityCow(world);
		if(mobName == "Chicken") return new EntityChicken(world);
		if(mobName == "Pig") return new EntityPig(world);
		if(mobName == "Sheep") return new EntitySheep(world);
		if(mobName == "Villager") return new EntityVillager(world);
		if(mobName == "Enderman") return new EntityEnderman(world);
		if(mobName == "Spider") return new EntitySpider(world);
		if(mobName == "Blaze") return new EntityBlaze(world);
		if(mobName == "Skeleton") return new EntitySkeleton(world);
		if(mobName == "Slime") return new EntitySlime(world);
		if(mobName == "Creeper") return new EntityCreeper(world);
		if(mobName == "Zombie") return new EntityZombie(world);
		
		return new EntityPig(world);
	}

	public static boolean canSpawn(TileEntityMobToolsSpawner spawner, World world, int x, int y, int z, int tier, String mobName, String dimension)
	{
		if(tier == 5 && world.getBlockPowerInput(x, y, z) == 0)
			return true;
		else if(dimension != "Nether")
		{
			if(tier == 4)
			{
				return true;
			}

			else if(world.getClosestPlayer((double)x + 0.5D, (double)y, (double)z + 0.5D, 10) != null)
			{
				if(tier == 3)
				{
					return true;
				}

				else if(world.getLightBrightness(x, y, z) < 8)
				{
					if(tier == 2)
					{
						return true;
					}
					else if(isAnimal(mobName))
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
		if(mobName == "Cow" || mobName == "Chicken" || mobName == "Pig" || mobName == "Sheep")
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
