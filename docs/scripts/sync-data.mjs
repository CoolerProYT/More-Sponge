// Pulls wiki data straight from the mod so the docs never drift from the game:
// datagen output (recipes, trades, trader biomes, loot modifiers, lang) plus the sponge ranges and freezer fuels
// read from the Java source. Run `./gradlew :neoforge:runData` first when the mod's data changes.
import { existsSync, mkdirSync, readdirSync, readFileSync, writeFileSync } from 'node:fs'
import { basename, dirname, join } from 'node:path'
import { fileURLToPath } from 'node:url'

const docs = join(dirname(fileURLToPath(import.meta.url)), '..')
const root = join(docs, '..')
const generated = join(root, 'common/src/generated/resources')
const neoforgeGenerated = join(root, 'neoforge/src/generated/resources/data/moresponge')
const java = join(root, 'common/src/main/java/com/coolerpromc/moresponge')
const data = join(generated, 'data/moresponge')

if (!existsSync(generated)) {
  console.error(`No datagen output at ${generated}. Run ./gradlew :neoforge:runData first.`)
  process.exit(1)
}

const readJson = (file) => JSON.parse(readFileSync(file, 'utf8'))
const jsonFiles = (dir) => (existsSync(dir) ? readdirSync(dir).filter((f) => f.endsWith('.json')).sort() : [])
const stack = (value) => ({ id: value.id, count: value.count ?? 1 })

const lang = readJson(join(generated, 'assets/moresponge/lang/en_us.json'))

// Item names for mod items; vanilla names are prettified on the page.
const names = {}
for (const [key, value] of Object.entries(lang)) {
  const match = key.match(/^(item|block|entity)\.moresponge\.([a-z0-9_]+)$/)
  if (match) names[`moresponge:${match[2]}`] = value
}

// Every mod item has an icon hosted at 1024x1024 next to the vanilla renders.
// Block items are rendered with scripts/render_icons.py; upload new ones before syncing.
const HOSTED_TEXTURES = 'https://storage.googleapis.com/coolerpromc/textures/moresponge'
const textures = {}
for (const file of jsonFiles(join(generated, 'assets/moresponge/items'))) {
  const name = basename(file, '.json')
  textures[`moresponge:${name}`] = `${HOSTED_TEXTURES}/${name}.png`
}

const ingredient = (value) => {
  if (typeof value === 'string') return value
  if (Array.isArray(value)) return ingredient(value[0])
  if (value?.item) return value.item
  if (value?.tag) return `#${value.tag}`
  return '?'
}

const readRecipe = (file, id) => {
  const json = readJson(file)
  if (json.type === 'moresponge:freezing') {
    return {
      id,
      type: json.type,
      result: stack(json.output),
      ingredient: ingredient(json.input),
      cookingTime: json.cookingTime,
      experience: json.experience,
    }
  }
  const recipe = { id, type: json.type, result: stack(json.result) }
  if (json.type === 'minecraft:crafting_shaped') {
    recipe.pattern = json.pattern
    recipe.key = Object.fromEntries(Object.entries(json.key).map(([symbol, value]) => [symbol, ingredient(value)]))
  } else if (json.type === 'minecraft:crafting_shapeless') {
    recipe.ingredients = json.ingredients.map(ingredient)
  } else {
    recipe.ingredient = ingredient(json.ingredient)
    recipe.cookingTime = json.cookingtime
    recipe.experience = json.experience
  }
  return recipe
}

const recipes = [
  ...jsonFiles(join(data, 'recipe')).map((file) => readRecipe(join(data, 'recipe', file), `moresponge:${basename(file, '.json')}`)),
  ...jsonFiles(join(data, 'recipe/freezing')).map((file) =>
    readRecipe(join(data, 'recipe/freezing', file), `moresponge:freezing/${basename(file, '.json')}`),
  ),
]

// Trades: villager_trade files grouped by the tags their trade sets draw from.
const tradeDir = join(data, 'villager_trade/sponge_trader')
const trades = Object.fromEntries(
  jsonFiles(tradeDir).map((file) => {
    const json = readJson(join(tradeDir, file))
    return [
      `moresponge:sponge_trader/${basename(file, '.json')}`,
      { wants: stack(json.wants), gives: stack(json.gives), maxUses: json.max_uses ?? 1 },
    ]
  }),
)
const tradeSets = {}
for (const file of jsonFiles(join(data, 'trade_set/sponge_trader'))) {
  const name = basename(file, '.json')
  const json = readJson(join(data, 'trade_set/sponge_trader', file))
  const tag = json.trades.replace(/^#moresponge:/, '')
  const values = readJson(join(data, 'tags/villager_trade', `${tag}.json`)).values
  tradeSets[name] = { amount: json.amount, trades: values.map((id) => ({ id, ...trades[id] })) }
}

const traderBiomes = Object.fromEntries(
  jsonFiles(join(data, 'tags/worldgen/biome')).map((file) => [
    basename(file, '.json').replace(/^can_spawn_|_sponge_trader$/g, ''),
    readJson(join(data, 'tags/worldgen/biome', file)).values,
  ]),
)

// Chest and mob loot added by the NeoForge global loot modifiers (Fabric adds the same pools in code).
const loot = jsonFiles(join(neoforgeGenerated, 'loot_modifiers')).map((file) => {
  const json = readJson(join(neoforgeGenerated, 'loot_modifiers', file))
  // A single-term any_of is written as an object rather than a list.
  const terms = [json.condition?.terms ?? json.condition].flat()
  return {
    id: basename(file, '.json'),
    kind: file.includes('_entity_') ? 'mob' : 'chest',
    item: json.item,
    min: json.min,
    max: json.max,
    tables: terms.map((term) => term.loot_table_id).filter(Boolean),
  }
})

// Absorption range of every sponge, from the constructor arguments in MSBlocks.
const blocksSource = readFileSync(join(java, 'block/MSBlocks.java'), 'utf8')
const sponges = [...blocksSource.matchAll(/registerBlock\("([a-z0-9_]+)", p -> new (\w+)SpongeBlock\(p, (\d+), (\d+), \(\) -> ([A-Z0-9_]+)\)/g)].map(
  ([, name, kind, maxDepth, maxCount, saturated]) => ({
    id: `moresponge:${name}`,
    element: { Water: 'water', Lava: 'lava', Snow: 'snow', Fire: 'fire' }[kind],
    maxDepth: Number(maxDepth),
    maxCount: Number(maxCount),
    saturated: `moresponge:${saturated.toLowerCase()}`,
  }),
)

// Freezer fuels and how long each one burns, from FreezerBlockEntity.
const freezerSource = readFileSync(join(java, 'block/entity/custom/FreezerBlockEntity.java'), 'utf8')
const fuelBlock = freezerSource.match(/FUEL_VALUES = Map\.of\(([\s\S]*?)\);/)?.[1] ?? ''
const fuels = [...fuelBlock.matchAll(/Items\.([A-Z0-9_]+),\s*(\d+)/g)]
  .map(([, item, ticks]) => ({ item: `minecraft:${item.toLowerCase()}`, ticks: Number(ticks) }))
  .sort((a, b) => a.ticks - b.ticks)

const spawnerSource = readFileSync(join(java, 'entity/spawner/SpongeTraderSpawner.java'), 'utf8')
const constant = (name) => Number(spawnerSource.match(new RegExp(`${name} = (\\d+)`))?.[1])
const spawner = {
  tickDelay: constant('DEFAULT_TICK_DELAY'),
  spawnDelay: constant('DEFAULT_SPAWN_DELAY'),
  minChance: constant('MIN_SPAWN_CHANCE'),
  maxChance: constant('MAX_SPAWN_CHANCE'),
  chanceIncrease: constant('SPAWN_CHANCE_INCREASE'),
  despawnDelay: Number(spawnerSource.match(/setDespawnDelay\((\d+)\)/)?.[1]),
}

for (const [label, list] of Object.entries({ sponges, fuels })) {
  if (list.length === 0) {
    console.error(`Could not read any ${label} from the Java source; update the patterns in scripts/sync-data.mjs.`)
    process.exit(1)
  }
}

mkdirSync(join(docs, '.vitepress/data'), { recursive: true })
writeFileSync(
  join(docs, '.vitepress/data/data.json'),
  JSON.stringify({ names, textures, recipes, tradeSets, traderBiomes, loot, sponges, fuels, spawner }, null, 2),
)
console.log(
  `Synced ${sponges.length} sponges, ${recipes.length} recipes, ${Object.keys(tradeSets).length} trade sets, ${loot.length} loot modifiers, ${fuels.length} fuels, ${Object.keys(textures).length} textures.`,
)
