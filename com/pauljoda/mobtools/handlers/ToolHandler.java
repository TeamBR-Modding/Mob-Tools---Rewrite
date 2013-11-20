package com.pauljoda.mobtools.handlers;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ToolHandler {
	
	public static void randomTeleport(EntityPlayer player, World world)
	{
		Random r = new Random();
		int x, y, z;
		boolean canSpawn = false;
		while(!canSpawn)
		{
			int mod;
			
			mod = r.nextInt(2);
			if(mod == 0)
			x = (int)player.posX + r.nextInt(15);
			else
			x = (int)player.posX - r.nextInt(15);
			
			mod = r.nextInt(2);
			if(mod == 0)
			z = (int)player.posZ + r.nextInt(15);
			else
			z = (int)player.posZ - r.nextInt(15);
			
			y = (int)player.posY + r.nextInt(3);
			
			if(world.isAirBlock(x, y, z) || world.isAirBlock(x, y - 1, z))
			{
				player.setPositionAndUpdate(x, y, z);
				player.playSound("mob.endermen.portal", 2.0F, 1.0F);
				canSpawn = true;
			}
			
		}
		
	}

	public static void swordEffect(int swordType, ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
	{
		//Sword Types:
		//1: Creeper
		//2: Ender
		//3: Spider
		//4: Blaze

		switch(swordType)
		{
		case (1) : break;
		case (2) : break;
		case (3) : break;
		case (4) : 	par2EntityLivingBase.setFire(10);
					par1ItemStack.damageItem(1, par3EntityLivingBase);
					break;

		default : break;


		}
	}

	public static void swordBlockEffect(int swordType, ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		//Sword Types:
		//1: Creeper
		//2: Ender
		//3: Spider
		//4: Blaze

		switch(swordType)
		{
		case (1) : par2World.createExplosion(par3EntityPlayer, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, 5.0F, false);
				   par1ItemStack.damageItem(5, par3EntityPlayer);
				   break;
				   
		case (2) : randomTeleport(par3EntityPlayer, par2World);
				   par1ItemStack.damageItem(5, par3EntityPlayer);
				   break;
				   
		case (3) : if(par3EntityPlayer.isSneaking())
				   {
				   	if(par3EntityPlayer.isInvisible())
				   	{
				   		par3EntityPlayer.setInvisible(false);
				   		par3EntityPlayer.playSound("random.pop", 2.0F, 1.0F);
				   	}
				   	else
				   	{
				   		par3EntityPlayer.setInvisible(true);
				   		par3EntityPlayer.playSound("random.pop", 2.0F, 1.0F);
				   	}
				   }
				   par1ItemStack.damageItem(1, par3EntityPlayer);
				   break;
				   
		case (4) : if(par3EntityPlayer.isSneaking())
				   {
						par3EntityPlayer.extinguish();
						par3EntityPlayer.playSound("random.extinguish", 2.0F, 1.0F);
						par1ItemStack.damageItem(1, par3EntityPlayer);
				   }
				   break;

		default : break;



		}
	
	}

}
