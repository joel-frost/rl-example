package com.helmetcheck;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.Arrays;

import static net.runelite.api.Experience.getXpForLevel;
import static net.runelite.api.ItemID.SLAYER_HELMET;
import static net.runelite.api.ItemID.SLAYER_HELMET_I;

@Slf4j
@PluginDescriptor(
	name = "Slayer Helm Checker"
)
public class HelmetCheckPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private HelmetCheckOverlay helmetCheckOverlay;

	public String slayerHelmText = "No";

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(helmetCheckOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(helmetCheckOverlay);
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		slayerHelmText = isWearingSlayerHelm(event.getItemContainer().getItems()) ? "Yes" : "No";
	}

	private Boolean isWearingSlayerHelm(Item[] items)
	{
		return Arrays.stream(items).anyMatch(item -> SLAYER_HELMET == item.getId() || SLAYER_HELMET_I == item.getId());
	}
}
