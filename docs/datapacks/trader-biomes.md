# Trader biomes

Which trader spawns where is set by four biome tags in `data/moresponge/tags/worldgen/biome/`:

| Tag | Trader | Default |
|---|---|---|
| `moresponge:can_spawn_water_sponge_trader` | Water Sponge Trader | Oceans, deep oceans, rivers, beaches, swamps |
| `moresponge:can_spawn_lava_sponge_trader` | Lava Sponge Trader | `#minecraft:is_nether` |
| `moresponge:can_spawn_snow_sponge_trader` | Snow Sponge Trader | `#minecraft:spawns_snow_foxes` |
| `moresponge:can_spawn_fire_sponge_trader` | Fire Sponge Trader | `#minecraft:is_badlands`, `minecraft:desert` |

When a trader is due, the spawner picks a spot and checks the tags in this order: **lava, snow, fire, water**. The first tag that contains the biome decides the trader. A biome in none of the tags spawns nothing.

## Example: fire traders in savannas

`data/moresponge/tags/worldgen/biome/can_spawn_fire_sponge_trader.json`:

```json
{
  "values": [
    "#minecraft:is_savanna"
  ]
}
```

This adds savannas to the mod's list. Use `"replace": true` to replace the list instead.

## Example: no water traders in swamps

```json
{
  "replace": true,
  "values": [
    "#minecraft:is_ocean",
    "#minecraft:is_deep_ocean",
    "#minecraft:is_river",
    "#minecraft:is_beach"
  ]
}
```

To stop all natural spawning, use the `moresponge:spawn_sponge_traders` [game rule](../guide/traders#game-rule) instead.
