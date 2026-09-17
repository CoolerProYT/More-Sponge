# Trader trades

Sponge trader offers use vanilla's data-driven trade system. There are three layers:

1. **Trades** (`villager_trade`): one offer each, such as "5 emeralds for a sponge".
2. **Trade tags** (`tags/villager_trade/sponge_trader/`): the pool of trades for one trader and one tier.
3. **Trade sets** (`trade_set/sponge_trader/`): how many offers the trader picks from a pool.

Each trader uses three trade sets: `<element>_buying`, `<element>_common` and `<element>_uncommon`, where `<element>` is `water`, `lava`, `snow` or `fire`.

::: warning
Trades and trade sets are loaded when the world starts, so restart the world after changing them. A trader keeps the offers it rolled when you first talked to it, so the changes only show on newly spawned traders.
:::

## Trade files

`data/moresponge/villager_trade/sponge_trader/emerald_sponge.json`:

```json
{
  "wants": {
    "id": "minecraft:emerald",
    "count": 5
  },
  "gives": {
    "id": "minecraft:sponge"
  },
  "max_uses": 2,
  "reputation_discount": 0.05
}
```

| Field | Description |
|---|---|
| `wants` | The item the player pays: `id` and `count`. |
| `additional_wants` | Optional second item the player pays. |
| `gives` | The item the player gets. |
| `max_uses` | How many times the offer can be used. Defaults to 4. |
| `reputation_discount` | Price multiplier from reputation. The mod uses 0.05. |
| `xp` | Villager experience for the trade. Defaults to 1. |

Other vanilla fields, such as `merchant_predicate` and `given_item_modifiers`, also work. The mod's files are named `emerald_<item>` for things the trader sells and `<item>_emerald` for things it buys.

## Trade tags

`data/moresponge/tags/villager_trade/sponge_trader/water_common.json`:

```json
{
  "values": [
    "moresponge:sponge_trader/emerald_sponge",
    "moresponge:sponge_trader/emerald_wet_sponge",
    "moresponge:sponge_trader/emerald_compressed_sponge",
    "moresponge:sponge_trader/emerald_wet_compressed_sponge"
  ]
}
```

A tag in your datapack adds to the mod's list. Set `"replace": true` to use only your own list.

## Trade sets

`data/moresponge/trade_set/sponge_trader/water_uncommon.json`:

```json
{
  "amount": 1,
  "trades": "#moresponge:sponge_trader/water_uncommon",
  "random_sequence": "moresponge:trade_set/sponge_trader/water_uncommon"
}
```

| Field | Description |
|---|---|
| `amount` | How many offers to pick from the pool. |
| `trades` | The trade tag to pick from (or a list of trade ids). |
| `allow_duplicates` | Whether the same trade can be picked twice. Defaults to `false`. |
| `random_sequence` | Optional id for the random sequence. |

## Example: sell a 5x sponge more often

Change `water_uncommon.json` to pick two offers:

```json
{
  "amount": 2,
  "trades": "#moresponge:sponge_trader/water_uncommon"
}
```

## Current trades

See [Sponge traders](../guide/traders#trades) for every trade the mod ships with.
