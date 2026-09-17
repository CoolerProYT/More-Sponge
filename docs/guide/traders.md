<script setup>
import { data, biomeName } from '../.vitepress/theme/moresponge'
const s = data.spawner
const minutes = (ticks) => ticks / 1200
</script>

# Sponge traders

Four wandering traders deal in sponges, one for each element. Each has its own look and only turns up in biomes that match its element.

| Trader | Spawn egg | Spawns in |
|---|---|---|
| Water Sponge Trader | <ItemSlot id="moresponge:water_sponge_trader_spawn_egg" /> | {{ data.traderBiomes.water.map(biomeName).join(', ') }} |
| Lava Sponge Trader | <ItemSlot id="moresponge:lava_sponge_trader_spawn_egg" /> | {{ data.traderBiomes.lava.map(biomeName).join(', ') }} |
| Snow Sponge Trader | <ItemSlot id="moresponge:snow_sponge_trader_spawn_egg" /> | {{ data.traderBiomes.snow.map(biomeName).join(', ') }} |
| Fire Sponge Trader | <ItemSlot id="moresponge:fire_sponge_trader_spawn_egg" /> | {{ data.traderBiomes.fire.map(biomeName).join(', ') }} |

## Spawning

Sponge traders use their own spawn timer, separate from the vanilla wandering trader:

1. Every in-game day ({{ minutes(s.spawnDelay) }} minutes), the game tries to spawn a sponge trader.
2. The first try has a **{{ s.minChance }}%** chance. Each failed try raises the chance by {{ s.chanceIncrease }}%, up to **{{ s.maxChance }}%**. After a trader spawns, it drops back to {{ s.minChance }}%.
3. The trader appears within 48 blocks of a random player, or of a village bell near that player. The biome at that spot decides which trader it is. If the biome has no trader, nobody spawns that day.
4. It wanders near where it spawned and leaves after {{ minutes(s.despawnDelay) }} minutes (two in-game days), unless you are trading with it.

The spawn timer runs in every dimension. In the Nether, Lava Sponge Traders appear on solid ground near the player's height.

### Game rule

| Rule | Default | Effect |
|---|---|---|
| `moresponge:spawn_sponge_traders` | `true` | Set to `false` to stop sponge traders from spawning naturally. Spawn eggs still work. |

```
/gamerule moresponge:spawn_sponge_traders false
```

## Trades

Each trader picks random offers from three pools when you first talk to it: two it **buys**, two **common** sales and one **uncommon** sale.

### Water Sponge Trader

<TradeTable element="water" />

### Lava Sponge Trader

<TradeTable element="lava" />

### Snow Sponge Trader

<TradeTable element="snow" />

### Fire Sponge Trader

<TradeTable element="fire" />

::: tip
Traders sell soaked sponges too, for fewer emeralds than dry ones. Buying soaked and resetting them yourself is the cheaper option.
:::

Trades are datapack files. See [Trader trades](../datapacks/trades) to change them.
