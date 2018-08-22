package com.enjin.enjincoin.spigot_framework.trade;

import com.enjin.enjincoin.spigot_framework.player.MinecraftPlayer;
import com.enjin.minecraft_commons.spigot.ui.Component;
import com.enjin.minecraft_commons.spigot.ui.Dimension;
import com.enjin.minecraft_commons.spigot.ui.Position;
import com.enjin.minecraft_commons.spigot.ui.menu.ChestMenu;
import com.enjin.minecraft_commons.spigot.ui.menu.component.SimpleMenuComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class TradeView extends ChestMenu {

    private MinecraftPlayer viewer;
    private MinecraftPlayer other;

    private SimpleMenuComponent viewerItemsComponent;
    private SimpleMenuComponent viewerStatusComponent;

    private SimpleMenuComponent otherItemsComponent;
    private SimpleMenuComponent otherStatusComponent;

    public TradeView(MinecraftPlayer viewer, MinecraftPlayer other) {
        super("Trade", 6);
        this.viewer = viewer;
        this.other = other;
        init();
    }

    private void init() {
        this.viewerItemsComponent = new SimpleMenuComponent(new Dimension(4, 4));
        this.viewerStatusComponent = new SimpleMenuComponent(new Dimension(4, 1));

        this.viewerStatusComponent.setItem(Position.of(0, 0), getPlayerHead(viewer.getBukkitPlayer(), true));
        this.viewerStatusComponent.setItem(Position.of(1, 0), new ItemStack(Material.HOPPER));
        this.viewerStatusComponent.setItem(Position.of(2, 0), new ItemStack(Material.BARRIER));
        this.viewerStatusComponent.setItem(Position.of(3, 0), new ItemStack(Material.RED_STAINED_GLASS_PANE));

        this.otherItemsComponent = new SimpleMenuComponent(new Dimension(4, 4));
        this.otherStatusComponent = new SimpleMenuComponent(new Dimension(4, 1));

        this.otherStatusComponent.setItem(Position.of(0, 0), getPlayerHead(other.getBukkitPlayer(), false));
        this.otherStatusComponent.setItem(Position.of(3, 0), new ItemStack(Material.RED_STAINED_GLASS_PANE));

        Component horizontalBarrier = new SimpleMenuComponent(new Dimension(9, 1));
        for (int i = 0; i < horizontalBarrier.getDimension().getWidth(); i++) {
            ((SimpleMenuComponent) horizontalBarrier).setItem(Position.of(i, 0), new ItemStack(Material.IRON_BARS));
        }

        Component verticalBarrierTop = new SimpleMenuComponent(new Dimension(1, 4));
        for (int i = 0; i < verticalBarrierTop.getDimension().getHeight(); i++) {
            ((SimpleMenuComponent) verticalBarrierTop).setItem(Position.of(0, i), new ItemStack(Material.IRON_BARS));
        }

        Component verticalBarrierBottom = new SimpleMenuComponent(new Dimension(1, 1));
        ((SimpleMenuComponent) verticalBarrierBottom).setItem(Position.of(0, 0), new ItemStack(Material.IRON_BARS));

        addComponent(Position.of(0, 0), this.viewerItemsComponent);
        addComponent(Position.of(0, 5), this.viewerStatusComponent);
        addComponent(Position.of(5, 0), this.otherItemsComponent);
        addComponent(Position.of(5, 5), this.otherStatusComponent);
        addComponent(Position.of(4, 0), verticalBarrierTop);
        addComponent(Position.of(4, 5), verticalBarrierBottom);
        addComponent(Position.of(0, 4), horizontalBarrier);

        open(this.viewer.getBukkitPlayer());
    }

    @Override
    protected void onClose(Player player) {
        if (player == this.viewer.getBukkitPlayer()) {
            this.viewer.setActiveTradeView(null);

            TradeView otherTradeView = this.other.getActiveTradeView();
            if (otherTradeView != null) {
                otherTradeView.removePlayer(this.other.getBukkitPlayer());
                otherTradeView.destroy();
            } else {
                // TODO: <Other Player> has cancelled the trade.
            }

            destroy();
        }
    }

    public MinecraftPlayer getViewer() {
        return viewer;
    }

    public MinecraftPlayer getOther() {
        return other;
    }

    public Component getViewerItemsComponent() {
        return viewerItemsComponent;
    }

    public Component getOtherItemsComponent() {
        return otherItemsComponent;
    }

    public SimpleMenuComponent getViewerStatusComponent() {
        return viewerStatusComponent;
    }

    public SimpleMenuComponent getOtherStatusComponent() {
        return otherStatusComponent;
    }

    private ItemStack getPlayerHead(Player player, boolean self) {
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        meta.setOwningPlayer(player);
        meta.setDisplayName(self ? "You" : player.getName());
        stack.setItemMeta(meta);
        return stack;
    }
}