/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.wurst_client.mods;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import tk.wurst_client.events.listeners.RenderListener;
import tk.wurst_client.mods.Mod.Bypasses;
import tk.wurst_client.mods.Mod.Category;
import tk.wurst_client.mods.Mod.Info;
import tk.wurst_client.navigator.NavigatorItem;
import tk.wurst_client.utils.RenderUtils;

@Info(category = Category.RENDER,
	description = "Draws lines to players around you.",
	name = "Tracers",
	help = "Mods/Tracers")
@Bypasses
public class TracersMod extends Mod implements RenderListener
{
	@Override
	public NavigatorItem[] getSeeAlso()
	{
		return new NavigatorItem[]{wurst.mods.playerEspMod,
			wurst.mods.playerFinderMod};
	}
	
	@Override
	public void onEnable()
	{
		wurst.events.add(RenderListener.class, this);
	}
	
	@Override
	public void onRender()
	{
		if(wurst.mods.arenaBrawlMod.isActive())
			return;
		for(Object entity : mc.theWorld.loadedEntityList)
			if(entity instanceof EntityPlayer
				&& !((Entity)entity).getName().equals(
					mc.getSession().getUsername()))
				RenderUtils.tracerLine((Entity)entity, wurst.friends
					.contains(((EntityPlayer)entity).getName()) ? 1 : 0);
	}
	
	@Override
	public void onDisable()
	{
		wurst.events.remove(RenderListener.class, this);
	}
}
