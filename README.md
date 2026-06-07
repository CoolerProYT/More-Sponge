# More Sponge

A Minecraft mod that adds new sponge variants with unique functionality, each absorbing a different element.

## Blocks

### Water Sponge
- **Compressed Sponge (1x–5x)** — Absorbs a larger area of water than the vanilla sponge.
- **Wet Compressed Sponge (1x–5x)** — The saturated form. Dries instantly when placed in the **Nether** (where water evaporates), or dry it in a furnace.

### Lava Sponge
- **Lava Sponge** — Absorbs lava from the world.
- **Compressed Lava Sponge (1x–5x)** — Absorbs a larger area of lava.
- **Hot Lava Sponge / Hot Compressed Lava Sponge (1x–5x)** — The saturated form after absorbing lava. Dries instantly when placed in a **snowy biome** (e.g. Snowy Taiga).

### Snow Sponge
- **Snow Sponge** — Absorbs snow from the world.
- **Compressed Snow Sponge (1x–5x)** — Absorbs a larger area of snow.
- **Frozen Snow Sponge / Frozen Compressed Snow Sponge (1x–5x)** — The saturated form after absorbing snow. Melts back instantly when placed in **any Nether biome**.

### Fire Sponge
- **Fire Sponge** — Absorbs fire from the world.
- **Compressed Fire Sponge (1x–5x)** — Absorbs a larger area of fire.
- **Burnt Fire Sponge / Burnt Compressed Fire Sponge (1x–5x)** — The saturated form after absorbing fire. Resets instantly when placed in a **cold/snowy biome**, an **ocean biome**, or **adjacent to water**.

### Machine
- **Freezer** — Processes freezing recipes. Used to convert hot/wet sponges back to their dry form, among other recipes.

## Crafting

- Compressed sponges are crafted in a 2×2 pattern from the previous tier.
- Lava Sponge: surround a sponge with ice.
- Snow Sponge: surround a sponge with magma blocks.
- Fire Sponge: surround a sponge with snow blocks.
- The Freezer is crafted from stone and packed ice.

All compression recipes are reversible.

## Loot

Sponges can also be found as loot.

### Chest Loot (0–4)
| Sponge | Found In |
|---|---|
| Sponge (vanilla) | Buried Treasure, Shipwreck (map, supply, treasure) |
| Lava Sponge | Bastion Remnant (all chests), Nether Fortress |
| Snow Sponge | Ancient City Ice Box, Igloo, Snowy Village House |
| Fire Sponge | Ruined Portal, Desert Pyramid, Desert Village House |

### Mob Drops (1–2)
| Sponge | Dropped By |
|---|---|
| Compressed Sponge 2x | Elder Guardian |
| Compressed Lava Sponge 2x | Ender Dragon |
| Compressed Snow Sponge 2x | Warden |
| Compressed Fire Sponge 2x | Wither |

## Sponge Traders

Four new trader mobs that spawn naturally in the world, similar to the Wandering Trader but with a lower spawn chance. Each trader specializes in one sponge type and only appears in biomes matching their element.

| Trader | Spawns In |
|---|---|
| Water Sponge Trader | Ocean, river, and beach biomes |
| Lava Sponge Trader | Nether biomes |
| Fire Sponge Trader | Badlands and desert biomes |
| Snow Sponge Trader | Cold and snowy biomes |

### Trades
Each trader offers three tiers of trades:
- **Buying** — The trader buys your sponges in exchange for emeralds.
- **Common** — Buy base and compressed (1x) sponges with emeralds.
- **Uncommon** — Buy higher-tier compressed sponges (2x–5x) with emeralds or emerald blocks.

Traders despawn after roughly 2.5 in-game days. They can also be spawned manually using their respective **spawn eggs**.

### Game Rule
| Rule                              | Default | Description |
|-----------------------------------|---|---|
| `moresponge:spawn_sponge_traders` | `true` | Controls whether Sponge Traders spawn naturally in the world. |

## JEI Support

Full JEI integration is included — browse all freezing recipes and fuel values directly in-game.
