package me.tastybulb.asylum.impl.module.modules.combat;

import me.tastybulb.asylum.api.event.events.PlayerUpdateEvent;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.setting.settings.BooleanSetting;
import me.tastybulb.asylum.impl.setting.settings.ModeSetting;
import me.tastybulb.asylum.impl.setting.settings.NumberSetting;
import org.lwjgl.input.Keyboard;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SmartOffHand extends Module {
    public ModeSetting mode = new ModeSetting("Mode", this, "Gapple", "Gapple", "Crystal", "Totem");
    public NumberSetting health = new NumberSetting("Health", this, 14, 0, 20, 1);
    public BooleanSetting check = new BooleanSetting("CrystalCheck", this, true);

    public SmartOffHand() {
        super("SmartOffHand", "Offhand but smart", Keyboard.KEY_NONE, Category.COMBAT);
        this.addSettings(mode, health, check);
    }
    public String currentMode;

    @Override
    public void onEnable() {
        if(mc.player == null || mc.world == null) return;
        currentMode = mode.getMode();
    }

    private void SwitchOffHand(ModeSetting val) {
        Item item = getItem(val);

        if (mc.player.getHeldItemOffhand().getItem() != item) {
            int slot = getItemSlot(item);

            if (slot != -1) {
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP,
                        mc.player);

                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.updateController();

            }
        }
    }

    private void SwitchOffHandTotem() {
        Item item = Items.TOTEM_OF_UNDYING;

        if (mc.player.getHeldItemOffhand().getItem() != item) {
            int slot = getItemSlot(item);

            if (slot != -1) {
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP,
                        mc.player);

                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.updateController();

            }
        }
    }

    @EventHandler
    private Listener<PlayerUpdateEvent> OnPlayerUpdate = new Listener<>(event -> {

        if (mc.currentScreen != null && (!(mc.currentScreen instanceof GuiInventory)))
            return;

        if(check.isEnabled() && !crystalCheck()) {
            mode.setMode(currentMode);
            SwitchOffHand(mode);
        }
        if(check.isEnabled() && crystalCheck()) {
            mode.setMode("Totem");
            SwitchOffHandTotem();
            return;
        }
        if(getHealthWithAbsorption() > health.getValue()) {
            mode.setMode(currentMode);
            SwitchOffHand(mode);
        }else if (getHealthWithAbsorption() <= health.getValue()) {
            mode.setMode("Totem");
            SwitchOffHandTotem();
            return;
        }

    });

    private boolean crystalCheck() {
        for(Entity e : mc.world.loadedEntityList) {
            if (e instanceof EntityEnderCrystal && mc.player.getDistance(e) <= 12) {
                if ((AutoCrystal.calculateDamage(e.posX, e.posY, e.posZ, mc.player)) >= mc.player.getHealth()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static float getHealthWithAbsorption() {
        return mc.player.getHealth() + mc.player.getAbsorptionAmount();
    }

    public static int getItemSlot(Item input) {
        if (mc.player == null)
            return 0;

        for (int i = 0; i < mc.player.inventoryContainer.getInventory().size(); ++i) {
            if (i == 0 || i == 5 || i == 6 || i == 7 || i == 8)
                continue;

            ItemStack s = mc.player.inventoryContainer.getInventory().get(i);

            if (s.isEmpty())
                continue;

            if (s.getItem() == input) {
                return i;
            }
        }
        return -1;
    }

    public Item getItem(ModeSetting val) {
        if(val.is("Crystal")) return Items.END_CRYSTAL;
        if(val.is("Gap")) return Items.GOLDEN_APPLE;
        if(val.is("Totem")) return Items.TOTEM_OF_UNDYING;

        return Items.TOTEM_OF_UNDYING;
    }

}