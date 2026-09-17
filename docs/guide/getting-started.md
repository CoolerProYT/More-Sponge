# Getting started

More Sponge adds sponges that soak up lava, snow and fire as well as water. Each one can be compressed into stronger versions with a much bigger reach.

## Install

1. Install [Fabric](https://fabricmc.net/) with Fabric API, or [NeoForge](https://neoforged.net/), for Minecraft **26.3**.
2. Put the More Sponge jar in your `mods` folder.
3. Optional: add [JEI](https://modrinth.com/mod/jei) to look up freezing recipes and freezer fuels in game.

## Your first sponges

All four elements start from a regular sponge. Find one in an ocean monument, or buy one from a [Water Sponge Trader](./traders). Surround the sponge with the opposite element:

<RecipeCard id="lava_sponge" />
<RecipeCard id="snow_sponge" />
<RecipeCard id="fire_sponge" />

Place a sponge next to its element and it soaks it up. A soaked sponge changes texture and stops working until you reset it. The [sponge pages](./sponges) show how to reset each type.

::: tip
A Lava Sponge has the same reach as a vanilla sponge. Put four of any sponge in a 2×2 grid to get a [compressed sponge](./compression) with a much bigger reach.
:::

## Resetting soaked sponges

| Soaked sponge | Resets instantly when placed… | Or smelt it in |
|---|---|---|
| <ItemSlot id="moresponge:wet_compressed_sponge" label /> | in the Nether | Furnace |
| <ItemSlot id="moresponge:hot_lava_sponge" label /> | in a snowy biome | [Freezer](./freezer) |
| <ItemSlot id="moresponge:frozen_snow_sponge" label /> | in the Nether | Furnace |
| <ItemSlot id="moresponge:burnt_fire_sponge" label /> | in a snowy or ocean biome, or touching water | [Freezer](./freezer) |

## Where to go next

- [How sponges work](./sponges): reach, search limits and an interactive range comparison
- [Compression](./compression): every tier and how to craft it back down
- [Sponge traders](./traders): where they spawn and what they sell
