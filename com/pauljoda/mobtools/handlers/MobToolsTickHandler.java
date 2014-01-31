package com.pauljoda.mobtools.handlers;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class MobToolsTickHandler implements ITickHandler 
{
	public static int ticks;
	public int seconds;
	public static int cooldown = 0;

	@Override
	public void tickStart (EnumSet<TickType> type, Object... tickData)
	{}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{   
		//timeTicker();

	}


	public void timeTicker()
	{
		ticks++;
		seconds = ticks % 20;
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		//You must return an EnumSet.of();. You will pull an error if you return Null
		return EnumSet.of(TickType.RENDER, TickType.CLIENT, TickType.SERVER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}



}