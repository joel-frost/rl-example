package com.helmetcheck;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class HelmetCheckOverlay extends Overlay {

    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    private HelmetCheckPlugin plugin;

    @Override
    public Dimension render(Graphics2D graphics2D) {
        panelComponent.getChildren().clear();
        String overlayTitle = "Wearing Slayer Helm?";

        // Build overlay title
        panelComponent.getChildren().add(TitleComponent.builder()
                .text(overlayTitle)
                .color(plugin.slayerHelmText.equalsIgnoreCase("Yes") ? Color.GREEN : Color.RED)
                .build());

        // Set the size of the overlay (width)
        panelComponent.setPreferredSize(new Dimension(
                graphics2D.getFontMetrics().stringWidth(overlayTitle) + 30,
                0));

        // Add a line on the overlay for world number
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Wearing:")
                .right(plugin.slayerHelmText)
                .build());

        // Show world type goes here ...

        return panelComponent.render(graphics2D);
    }

}
