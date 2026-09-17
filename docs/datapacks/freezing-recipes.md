# Freezing recipes

Freezer recipes use the `moresponge:freezing` recipe type. They go in `data/<namespace>/recipe/` like any other recipe, and the mod keeps its own in `data/moresponge/recipe/freezing/`.

## Format

```json
{
  "type": "moresponge:freezing",
  "input": "moresponge:hot_lava_sponge",
  "output": {
    "id": "moresponge:lava_sponge"
  },
  "cookingTime": 200,
  "experience": 0.15
}
```

| Field | Required | Description |
|---|---|---|
| `type` | yes | Always `moresponge:freezing`. |
| `input` | yes | An ingredient: an item id, a list of item ids, or a `#tag`. |
| `output` | yes | The result: `id` and an optional `count`. |
| `cookingTime` | yes | Time in ticks. 20 ticks = 1 second. The mod's recipes use 200. |
| `experience` | yes | Experience stored per item, collected when the result is taken out. |
| `group` | no | Optional group name. The freezer has no recipe book, so it has no visible effect. |

::: tip
Fuel lasts a fixed number of ticks, so recipes with a longer `cookingTime` get fewer items out of each fuel item.
:::

## Example: pack ice

`data/my_pack/recipe/packed_ice_from_ice.json`:

```json
{
  "type": "moresponge:freezing",
  "input": "minecraft:ice",
  "output": {
    "id": "minecraft:packed_ice"
  },
  "cookingTime": 400,
  "experience": 0.1
}
```

The freezer only accepts items in its top slot if a freezing recipe uses them, so new inputs work as soon as you run `/reload`.

::: warning
The freezer does not give back containers. An input like a water bucket is used up completely, bucket included.
:::

## Removing a recipe

On NeoForge, replace the mod's file (for example `data/moresponge/recipe/freezing/lava_sponge.json`) with one that is never loaded:

```json
{
  "neoforge:conditions": [{ "type": "neoforge:false" }]
}
```

On Fabric, use Fabric's resource conditions (`fabric:load_conditions`) the same way.
