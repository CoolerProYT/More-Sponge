# How sponges work

Every sponge in the mod works like the vanilla sponge. The only differences are what it soaks up and how far it reaches.

## Soaking up

A sponge checks the area around it when you place it, and again whenever a block next to it changes. If it removes at least one block, it turns into its soaked form and plays the sponge sound.

The search spreads out one block at a time, like water flowing. Two numbers limit it:

- **Reach**: how many steps away from the sponge the search can go. Steps go up, down and sideways, never diagonally.
- **Search limit**: how many blocks the search can visit in total, counting the sponge itself. A sponge removes at most one block less than this.

Water, lava and snow sponges only spread through their own element. In a long, narrow channel they follow it all the way to their full reach. In a big open lake the search limit runs out first on the lower tiers.

| Sponge | Removes | Also removes |
|---|---|---|
| Water | Water source and flowing blocks, and waterlogged blocks lose their water | Kelp and seagrass (dropped as items) |
| Lava | Lava source and flowing blocks | |
| Snow | Snow layers, snow blocks and powder snow | |
| Fire | Fire and soul fire | |

## Range explorer

Pick an element and a tier. The diagram is a top-down slice through the sponge, one square per block.

<RangeExplorer />

## Every tier

::: details Water
<SpongeTable element="water" />
:::

::: details Lava
<SpongeTable element="lava" />
:::

::: details Snow
<SpongeTable element="snow" />
:::

::: details Fire
<SpongeTable element="fire" />
:::

## Resetting

A soaked sponge does nothing until it is reset. Each element resets in the world when placed somewhere that undoes what it absorbed, and each can also be reset in a furnace or [freezer](./freezer). The element pages have the details:

- [Water sponges](./water-sponges): dry in the Nether, or in a furnace
- [Lava sponges](./lava-sponges): cool in snowy biomes, or in a freezer
- [Snow sponges](./snow-sponges): thaw in the Nether, or in a furnace
- [Fire sponges](./fire-sponges): cool in snowy or ocean biomes, next to water, or in a freezer

Soaked sponges drop themselves when mined, so you can carry them to the right place. A hoe mines every sponge fastest.
