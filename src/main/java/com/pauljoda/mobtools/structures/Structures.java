package com.pauljoda.mobtools.structures;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


public class Structures 
{

	public static Block[][][] StructureInfuserWood()
	{
		return buildStructureFromBlocks(Blocks.planks, Blocks.oak_stairs, Blocks.log, Blocks.planks);
	}

	public static Block[][][] StructureInfuserStone()
	{
		return buildStructureFromBlocks(Blocks.cobblestone, Blocks.stone_stairs, Blocks.cobblestone, Blocks.stone);
	}

	public static Block[][][] StructureInfuserIron()
	{
		return buildStructureFromBlocks(Blocks.stonebrick, Blocks.stone_brick_stairs, Blocks.stonebrick, Blocks.iron_block);
	}

	public static Block[][][] StructureInfuserGold()
	{
		return buildStructureFromBlocks(Blocks.brick_block, Blocks.brick_stairs, Blocks.brick_block, Blocks.gold_block);
	}

	public static Block[][][] StructureInfuserDiamond()
	{
		return buildStructureFromBlocks(Blocks.quartz_block, Blocks.quartz_stairs, Blocks.quartz_block, Blocks.diamond_block);
	}

	public static Block[][][] buildStructureFromBlocks(Block floor, Block stairs, Block pillars, Block caps)
	{
		Block[][][] structure = new Block[5][5][5];
		structure = fillStructureWithAir();

		int x = 0; 
		int y = 0;
		int z = 0;

		//Place Floor
		for(x = 1; x < 4; x++)
		{
			for(z = 1; z < 4; z++)
			{
				structure[x][0][z] = floor;
			}
		}

		//Place Stairs
		for(x = 1; x < 4; x++)
		{
			structure[x][0][0] = stairs;
			structure[x][0][4] = stairs;
		}

		for(z = 1; z < 4; z++)
		{
			structure[0][0][z] = stairs;
			structure[4][0][z] = stairs;
		}

		//Place Pillars
		for(y = 0; y < 4; y++)
		{
			structure[0][y][0] = pillars;
			structure[4][y][0] = pillars;
			structure[0][y][4] = pillars;
			structure[4][y][4] = pillars;
		}

		//Place Caps
		y = 4;
		structure[0][y][0] = caps;
		structure[4][y][0] = caps;
		structure[0][y][4] = caps;
		structure[4][y][4] = caps;
		

		return structure;
	}

	public static Block[][][] buildStructureFromCoords(World world, int x, int y, int z)
	{
		Block[][][] structure = new Block[5][5][5];
		
		for(int xMod = 0; xMod < 5; xMod++)
		{
			for(int yMod = 0; yMod < 5; yMod++)
			{
				for(int zMod = 0; zMod < 5; zMod++)
				{
					structure[xMod][yMod][zMod] = world.getBlock(x + xMod, y + yMod, z + zMod);
				}
			}
		}
		return structure;
	}

	public static boolean areStructureEqual(Block[][][] structure1, Block[][][] structure2)
	{
		for(int x = 0; x < 5; x++)
		{
			for(int y = 0; y < 5; y++)
			{
				for(int z = 0; z < 5; z++)
				{
					if(structure1[x][y][z] != structure2[x][y][z] && (x != 3 && y != 1 && z != 3))
					{
						return false;
					}
				}
			}
		}
		return true;
	}


	public static Block[][][] fillStructureWithAir()
	{
		Block[][][] structure = new Block[5][5][5];
		for(int x = 0; x < 5; x++)
		{
			for(int y = 0; y < 5; y++)
			{
				for(int z = 0; z < 5; z++)
				{
					structure[x][y][z] = Blocks.air;
				}
			}
		}
		return structure;
	}
}
