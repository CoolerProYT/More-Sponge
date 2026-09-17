# Datapacks

Most of More Sponge's content is data, so a datapack can change it without touching the mod. Run `/reload` after editing recipes, tags or loot. Trades and trade sets only load when the world starts, so reopen the world after changing them.

| What | Location | Page |
|---|---|---|
| Freezing recipes | `data/<namespace>/recipe/` | [Freezing recipes](./freezing-recipes) |
| Trader trades | `data/moresponge/villager_trade/sponge_trader/` | [Trader trades](./trades) |
| Trade pools | `data/moresponge/trade_set/sponge_trader/` and `data/moresponge/tags/villager_trade/sponge_trader/` | [Trader trades](./trades) |
| Trader biomes | `data/moresponge/tags/worldgen/biome/` | [Trader biomes](./trader-biomes) |
| Crafting and smelting | `data/moresponge/recipe/` | Vanilla recipe format |
| Chest and mob loot (NeoForge) | `data/moresponge/loot_modifiers/` | [Loot](../guide/loot) |

::: warning Not data driven
Sponge reach and search limits, how soaked sponges reset, and freezer fuels are set in the mod's code.
:::

## Setting up a datapack

```
my_sponge_pack/
├── pack.mcmeta
└── data/
    └── moresponge/
        └── villager_trade/
            └── sponge_trader/
                └── emerald_sponge.json
```

Give `pack.mcmeta` the pack format for your Minecraft version (listed on the [Minecraft Wiki](https://minecraft.wiki/w/Pack_format)). Then put the folder or zip in `saves/<world>/datapacks/`.

A file at the same path as one of the mod's files replaces it. Tags such as the trade pools and trader biomes add to the mod's list unless the file sets `"replace": true`.
