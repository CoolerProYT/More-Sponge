<script setup lang="ts">
import { computed } from 'vue'
import { type Element, data } from '../moresponge'
import ItemSlot from './ItemSlot.vue'

const props = defineProps<{ element: Element }>()

const SECTIONS = [
  { key: 'buying', title: 'Buys from you' },
  { key: 'common', title: 'Sells: common' },
  { key: 'uncommon', title: 'Sells: uncommon' },
]

const sections = computed(() =>
  SECTIONS.map((section) => ({ ...section, set: data.tradeSets[`${props.element}_${section.key}`] })).filter((s) => s.set),
)
</script>

<template>
  <div class="ms-trades" :class="`ms-${element}`">
    <section v-for="section in sections" :key="section.key">
      <h4>
        {{ section.title }}
        <span class="ms-muted">· offers {{ section.set.amount }} of {{ section.set.trades.length }}</span>
      </h4>
      <ul>
        <li v-for="trade in section.set.trades" :key="trade.id">
          <ItemSlot :id="trade.wants.id" :count="trade.wants.count" />
          <span class="arrow" aria-hidden="true">➜</span>
          <ItemSlot :id="trade.gives.id" :count="trade.gives.count" label />
          <span class="uses ms-muted">{{ trade.maxUses }}× per trader</span>
        </li>
      </ul>
    </section>
  </div>
</template>

<style scoped>
.ms-trades {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
  gap: 12px;
  margin: 12px 0;
}

section {
  padding: 10px 14px;
  border: 1px solid var(--vp-c-divider);
  border-top: 3px solid var(--ms-accent);
  border-radius: 10px;
  background: var(--vp-c-bg-soft);
}

h4 {
  margin: 0 0 8px;
  font-size: 14px;
}

ul {
  margin: 0;
  padding: 0;
  list-style: none;
}

li {
  display: grid;
  grid-template-columns: auto auto minmax(0, 1fr) auto;
  align-items: center;
  gap: 8px;
  margin: 6px 0;
}

.arrow {
  color: var(--vp-c-text-2);
}

.uses {
  font-size: 12px;
  white-space: nowrap;
}
</style>
