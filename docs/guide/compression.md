# Compression

Four sponges of the same type in a 2×2 grid make one sponge of the next tier. There are five compressed tiers, from 1x to 5x. A 5x sponge is worth 1,024 base sponges.

<CompressionLadder element="water" />
<CompressionLadder element="lava" />
<CompressionLadder element="snow" />
<CompressionLadder element="fire" />

## Compressing

<RecipeCard id="compressed_lava_sponge" />
<RecipeCard id="compressed_lava_sponge_2x" />

The same pattern works for every element and tier. The water line starts from the vanilla sponge:

<RecipeCard id="compressed_sponge" />

## Decompressing

Every step can be undone. Put one compressed sponge in the crafting grid to get four of the tier below.

<RecipeCard id="compressed_lava_sponge_from_compressed_lava_sponge_2x" />
<RecipeCard id="lava_sponge_from_compressed_lava_sponge" />
<RecipeCard id="sponge_from_compressed_sponge" />

::: tip
Only dry sponges can be compressed or split. Reset soaked sponges first ([water](./water-sponges#drying), [lava](./lava-sponges#hot-sponges), [snow](./snow-sponges#frozen-sponges), [fire](./fire-sponges#burnt-sponges)). Soaked sponges of the same tier can be traded, see [Sponge traders](./traders).
:::

## Is it worth it?

Each tier reaches further, and the search limit grows eightfold per tier:

| Tier | Reach | Search limit |
|---|---|---|
| Base | 6 (7 for snow) | 65 (125 for snow) |
| 1x | 10 | 512 |
| 2x | 16 | 4,096 |
| 3x | 22 | 32,768 |
| 4x | 28 | 262,144 |
| 5x | 34 | 2,097,152 |

From 3x up, the reach is the only limit: a 3x sponge can already clear every block within 22 steps in open water. See the [range explorer](./sponges#range-explorer).
