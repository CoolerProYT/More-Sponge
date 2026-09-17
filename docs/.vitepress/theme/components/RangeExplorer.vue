<script setup lang="ts">
import { computed, ref, watch } from 'vue'
import { type Element, ELEMENTS, formatNumber, itemName, spongeLine } from '../moresponge'
import ItemSlot from './ItemSlot.vue'

const props = withDefaults(defineProps<{ element?: Element }>(), { element: 'water' })

const element = ref<Element>(props.element)
const tiers = computed(() => spongeLine(element.value))
const index = ref(0)
watch(element, () => (index.value = Math.min(index.value, tiers.value.length - 1)))
const sponge = computed(() => tiers.value[index.value])

const LABELS: Record<Element, string> = { water: 'Water', lava: 'Lava', snow: 'Snow', fire: 'Fire' }
const MEDIUM: Record<Element, string> = { water: 'water', lava: 'lava', snow: 'snow', fire: 'fire' }

/** Blocks within taxicab distance r of a point, the point included. */
const diamond = (r: number) => ((2 * r + 1) * (2 * r * r + 2 * r + 3)) / 3

/**
 * Radius cleared completely when every block around the sponge is traversable: the search stops at maxDepth steps,
 * or earlier when it has visited maxCount blocks (the sponge itself counts as one).
 */
const fullRadius = computed(() => {
  let r = 0
  while (r < sponge.value.maxDepth && diamond(r + 1) <= sponge.value.maxCount) r++
  return r
})

const depthLimited = computed(() => fullRadius.value === sponge.value.maxDepth)

// Horizontal slice through the sponge, one cell per block.
const size = computed(() => 2 * sponge.value.maxDepth + 1)
const cell = computed(() => Math.max(3, Math.min(14, Math.floor(420 / size.value))))
const cells = computed(() => {
  const d = sponge.value.maxDepth
  const out: { x: number; y: number; zone: 'full' | 'partial' }[] = []
  for (let x = -d; x <= d; x++) {
    for (let y = -d; y <= d; y++) {
      if (x === 0 && y === 0) continue
      const dist = Math.abs(x) + Math.abs(y)
      if (dist > d) continue
      out.push({ x: x + d, y: y + d, zone: dist <= fullRadius.value ? 'full' : 'partial' })
    }
  }
  return out
})
</script>

<template>
  <div class="ms-range" :class="`ms-${element}`">
    <div class="controls">
      <div class="tabs" role="tablist" aria-label="Element">
        <button
          v-for="e in ELEMENTS"
          :key="e"
          role="tab"
          :aria-selected="e === element"
          :class="{ active: e === element }"
          @click="element = e"
        >
          {{ LABELS[e] }}
        </button>
      </div>
      <div class="tiers" role="radiogroup" aria-label="Sponge">
        <button
          v-for="(tier, i) in tiers"
          :key="tier.id"
          role="radio"
          :aria-checked="i === index"
          :class="{ active: i === index }"
          :title="itemName(tier.id)"
          @click="index = i"
        >
          <ItemSlot :id="tier.id" />
        </button>
      </div>
    </div>

    <div class="body">
      <div class="stats">
        <h4><ItemSlot :id="sponge.id" label /></h4>
        <dl>
          <dt>Reach</dt>
          <dd>{{ sponge.maxDepth }} blocks <span class="ms-muted">(walking distance, no diagonals)</span></dd>
          <dt>Removes at most</dt>
          <dd>{{ formatNumber(sponge.maxCount - 1) }} blocks</dd>
          <dt>{{ element === 'fire' ? 'Guaranteed radius' : `Clears open ${MEDIUM[element]} to` }}</dt>
          <dd>
            {{ fullRadius }} blocks
            <span class="ms-muted">
              {{ depthLimited ? '(limited by reach)' : '(limited by the block count)' }}
            </span>
          </dd>
        </dl>
        <p v-if="element === 'fire'" class="ms-muted note">
          The fire sponge searches through air and other blocks too, so every block around it uses up the search limit, not just fire.
        </p>
        <p v-else class="ms-muted note">
          The search only moves through {{ MEDIUM[element] }}. In a narrow channel it follows the {{ MEDIUM[element] }} further, up to the reach.
        </p>
      </div>

      <figure class="slice">
        <svg
          :viewBox="`0 0 ${size * cell} ${size * cell}`"
          :width="size * cell"
          :height="size * cell"
          role="img"
          :aria-label="`Top-down slice of the area ${itemName(sponge.id)} can reach`"
        >
          <rect
            v-for="c in cells"
            :key="`${c.x},${c.y}`"
            :x="c.x * cell"
            :y="c.y * cell"
            :width="cell - (cell > 5 ? 1 : 0)"
            :height="cell - (cell > 5 ? 1 : 0)"
            :class="c.zone"
          />
          <rect
            class="sponge"
            :x="sponge.maxDepth * cell"
            :y="sponge.maxDepth * cell"
            :width="cell - (cell > 5 ? 1 : 0)"
            :height="cell - (cell > 5 ? 1 : 0)"
          />
        </svg>
        <figcaption class="ms-muted">
          <span class="key full" /> always cleared in open {{ MEDIUM[element] }}
          <template v-if="!depthLimited"><span class="key partial" /> only reached if the limit is not used up</template>
        </figcaption>
      </figure>
    </div>
  </div>
</template>

<style scoped>
.ms-range {
  margin: 16px 0;
  padding: 14px 16px;
  border: 1px solid var(--vp-c-divider);
  border-top: 3px solid var(--ms-accent);
  border-radius: 10px;
  background: var(--vp-c-bg-soft);
}

.controls {
  display: flex;
  flex-wrap: wrap;
  gap: 10px 16px;
  align-items: center;
  margin-bottom: 12px;
}

.tabs {
  display: inline-flex;
  border: 1px solid var(--vp-c-divider);
  border-radius: 8px;
  overflow: hidden;
}

.tabs button {
  padding: 4px 12px;
  font-size: 13px;
  font-weight: 500;
  color: var(--vp-c-text-2);
}

.tabs button + button {
  border-left: 1px solid var(--vp-c-divider);
}

.tabs button.active {
  color: var(--vp-c-bg);
  background: var(--ms-accent);
}

.tiers {
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
}

.tiers button {
  padding: 2px;
  border: 2px solid transparent;
  border-radius: 6px;
  line-height: 0;
}

.tiers button.active {
  border-color: var(--ms-accent);
}

.body {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  align-items: flex-start;
}

.stats {
  flex: 1 1 240px;
}

.stats h4 {
  margin: 0 0 8px;
}

dl {
  display: grid;
  grid-template-columns: auto 1fr;
  gap: 4px 12px;
  margin: 0;
}

dt {
  color: var(--vp-c-text-2);
  font-size: 13px;
}

dd {
  margin: 0;
  font-variant-numeric: tabular-nums;
}

.note {
  margin: 10px 0 0;
  line-height: 1.5;
}

.slice {
  flex: 0 1 auto;
  margin: 0;
  max-width: 100%;
}

svg {
  display: block;
  max-width: 100%;
  height: auto;
}

rect.full {
  fill: var(--ms-accent);
}

rect.partial {
  fill: var(--ms-accent);
  opacity: 0.28;
}

rect.sponge {
  fill: var(--vp-c-text-1);
}

figcaption {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 4px 6px;
  margin-top: 6px;
}

.key {
  display: inline-block;
  width: 10px;
  height: 10px;
  margin-left: 6px;
  background: var(--ms-accent);
}

.key:first-child {
  margin-left: 0;
}

.key.partial {
  opacity: 0.28;
}
</style>
