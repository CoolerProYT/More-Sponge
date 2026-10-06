# Configuration

More Sponge keeps its settings in `config/moresponge-common.toml`. The file is created the first time you start the game. Edits are picked up while the game is running, so you don't need to restart.

```toml
["Sponge Trader"]
	# Allow sponge traders to spawn naturally
	spawnSpongeTrader = true

[Loot]
	# Add sponges to vanilla chest and boss loot tables. Requires /reload or a world restart to take effect on Fabric
	enableLootModifier = true
```

| Option | Default | Effect |
|---|---|---|
| `spawnSpongeTrader` | `true` | Set to `false` to stop [sponge traders](./traders) from spawning naturally in every world. Spawn eggs still work. |
| `enableLootModifier` | `true` | Set to `false` to stop sponges being added to chests and boss drops (see [Loot](./loot)). |

::: info
On Fabric, the loot setting only applies when loot tables load. Run `/reload` or restart the world after you change it. On NeoForge it applies straight away.
:::

`spawnSpongeTrader` works alongside the `moresponge:spawn_sponge_traders` game rule. Traders only spawn when both are `true`. Use the config to turn traders off in every world, or the game rule to turn them off in one world.
