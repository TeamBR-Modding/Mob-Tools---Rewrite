package com.pauljoda.mobtools.handlers;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
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
	


	public static MovingObjectPosition raytraceFromEntity (World world, Entity player, boolean par3, double range)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + 1.62D - (double) player.yOffset;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
		Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;
		if (player instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
		}
		Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
		return world.rayTraceBlocks_do_do(vec3, vec31, par3, !par3);
	}

}
