// @ts-ignore
import raw from '../data/data.json'

export type Element = 'water' | 'lava' | 'snow' | 'fire'

export interface Stack {
  id: string
  count: number
}

export interface Recipe {
  id: string
  type: string
  result: Stack
  pattern?: string[]
  key?: Record<string, string>
  ingredients?: string[]
  ingredient?: string
  cookingTime?: number
  experience?: number
}

export interface Trade {
  id: string
  wants: Stack
  gives: Stack
  maxUses: number
}

export interface TradeSet {
  amount: number
  trades: Trade[]
}

export interface Loot {
  id: string
  kind: 'chest' | 'mob'
  item: string
  min: number
  max: number
  tables: string[]
}

export interface Sponge {
  id: string
  element: Element
  maxDepth: number
  maxCount: number
  saturated: string
}

export interface Fuel {
  item: string
  ticks: number
}

export const data = raw as unknown as {
  names: Record<string, string>
  textures: Record<string, string>
  recipes: Recipe[]
  tradeSets: Record<string, TradeSet>
  traderBiomes: Record<Element, string[]>
  loot: Loot[]
  sponges: Sponge[]
  fuels: Fuel[]
  spawner: {
    tickDelay: number
    spawnDelay: number
    minChance: number
    maxChance: number
    chanceIncrease: number
    despawnDelay: number
  }
}

export const ELEMENTS: Element[] = ['water', 'lava', 'snow', 'fire']

/** The vanilla sponge is the base tier of the water line; its range is Minecraft's own. */
export const VANILLA_SPONGE: Sponge = {
  id: 'minecraft:sponge',
  element: 'water',
  maxDepth: 6,
  maxCount: 65,
  saturated: 'minecraft:wet_sponge',
}

/** Base sponge of each element, the block every compressed tier is built from. */
export const BASE_SPONGE: Record<Element, string> = {
  water: 'minecraft:sponge',
  lava: 'moresponge:lava_sponge',
  snow: 'moresponge:snow_sponge',
  fire: 'moresponge:fire_sponge',
}

/** Name of the absorbed form, as the mod calls it. */
export const SATURATED_WORD: Record<Element, string> = {
  water: 'Wet',
  lava: 'Hot',
  snow: 'Frozen',
  fire: 'Burnt',
}

/** All tiers of one element, base first. The water line starts with the vanilla sponge. */
export function spongeLine(element: Element): Sponge[] {
  const own = data.sponges.filter((s) => s.element === element)
  const tiers = element === 'water' ? [VANILLA_SPONGE, ...own] : own
  return tiers.sort((a, b) => a.maxCount - b.maxCount)
}

const TAG_NAMES: Record<string, string> = {
  '#minecraft:stone_crafting_materials': 'Any stone crafting material',
}

/** Item shown for a tag ingredient. */
const TAG_ICONS: Record<string, string> = {
  '#minecraft:stone_crafting_materials': 'minecraft:cobblestone',
}

/** Mod items use their in-game name; vanilla ids are turned into readable names. */
export function itemName(id: string): string {
  if (data.names[id]) return data.names[id]
  if (TAG_NAMES[id]) return TAG_NAMES[id]
  return prettify(id)
}

export function prettify(id: string): string {
  const path = id.replace(/^#/, '').split(':').pop()!.split('/').pop()!
  return path
    .split('_')
    .map((word) => (['of', 'the'].includes(word) ? word : word.charAt(0).toUpperCase() + word.slice(1)))
    .join(' ')
}

/** Hosted renders, one 1024x1024 PNG per item id. Mojang's textures are not bundled here. */
const HOSTED = 'https://storage.googleapis.com/coolerpromc/textures'

/** Where to load an item's icon from: the sync script lists mod icons, vanilla items use the hosted renders. */
export function itemIcon(id: string): string | null {
  const itemId = TAG_ICONS[id] ?? id
  if (data.textures[itemId]) return data.textures[itemId]
  const [namespace, path] = itemId.includes(':') ? itemId.split(':') : ['minecraft', itemId]
  if (namespace !== 'minecraft') return null
  return `${HOSTED}/${namespace}/${path}.png`
}

export function seconds(ticks: number): string {
  const value = ticks / 20
  return Number.isInteger(value) ? `${value}` : value.toFixed(1)
}

export function formatNumber(value: number): string {
  return value.toLocaleString('en-US')
}

/** Readable name for a loot table or biome id, e.g. minecraft:chests/village/village_desert_house. */
export function tableName(id: string): string {
  const LOOT_NAMES: Record<string, string> = {
    'minecraft:chests/nether_bridge': 'Nether Fortress',
    'minecraft:chests/igloo_chest': 'Igloo',
    'minecraft:chests/ancient_city_ice_box': 'Ancient City (ice box)',
    'minecraft:chests/village/village_snowy_house': 'Snowy Village house',
    'minecraft:chests/village/village_desert_house': 'Desert Village house',
    'minecraft:chests/bastion_bridge': 'Bastion (bridge)',
    'minecraft:chests/bastion_hoglin_stable': 'Bastion (hoglin stable)',
    'minecraft:chests/bastion_other': 'Bastion (other)',
    'minecraft:chests/bastion_treasure': 'Bastion (treasure)',
    'minecraft:chests/shipwreck_map': 'Shipwreck (map)',
    'minecraft:chests/shipwreck_supply': 'Shipwreck (supply)',
    'minecraft:chests/shipwreck_treasure': 'Shipwreck (treasure)',
  }
  return LOOT_NAMES[id] ?? prettify(id)
}

/** Readable names for the biome tags the traders spawn in. */
export function biomeName(id: string): string {
  const BIOME_NAMES: Record<string, string> = {
    '#minecraft:is_ocean': 'Oceans',
    '#minecraft:is_deep_ocean': 'Deep oceans',
    '#minecraft:is_river': 'Rivers',
    '#minecraft:is_beach': 'Beaches',
    '#minecraft:allows_surface_slime_spawns': 'Swamps',
    '#minecraft:is_nether': 'All Nether biomes',
    '#minecraft:spawns_snow_foxes': 'Snowy biomes',
    '#minecraft:is_badlands': 'Badlands',
    'minecraft:desert': 'Desert',
  }
  return BIOME_NAMES[id] ?? prettify(id)
}
