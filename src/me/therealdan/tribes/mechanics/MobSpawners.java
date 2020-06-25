package me.therealdan.tribes.mechanics;

import de.dustplanet.util.SilkUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Daniel on 12/10/2016.
 */
public class MobSpawners {

    private SilkUtil silkUtil;

    public MobSpawners() {
        silkUtil = SilkUtil.hookIntoSilkSpanwers();
    }

    public void dropSpawner(Block spawner) {
        if (spawner.getType() != Material.SPAWNER) return;

        CreatureSpawner creatureSpawner = (CreatureSpawner) spawner.getState();
        EntityType entityType = creatureSpawner.getSpawnedType();
        ItemStack itemStack = silkUtil.newSpawnerItem(entityType.getTypeId(), silkUtil.getCustomSpawnerName(entityType.toString()), 1);

        spawner.getWorld().dropItem(spawner.getLocation(), itemStack);
        spawner.setType(Material.AIR);
    }
}