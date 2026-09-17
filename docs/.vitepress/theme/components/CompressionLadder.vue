<script setup lang="ts">
import { computed } from 'vue'
import { type Element, formatNumber, spongeLine } from '../moresponge'
import ItemSlot from './ItemSlot.vue'

const props = withDefaults(defineProps<{ element: Element; saturated?: boolean }>(), { saturated: false })

/** Each tier takes four of the one before it, so tier n holds 4^n base sponges. */
const tiers = computed(() =>
  spongeLine(props.element).map((sponge, i) => ({
    id: props.saturated ? sponge.saturated : sponge.id,
    base: 4 ** i,
  })),
)
</script>

<template>
  <div class="ms-ladder" :class="`ms-${element}`">
    <template v-for="(tier, i) in tiers" :key="tier.id">
      <span v-if="i > 0" class="step" aria-hidden="true">
        <span class="times">4 ×</span>
        ⇄
      </span>
      <span class="tier">
        <ItemSlot :id="tier.id" />
        <span class="worth">{{ i === 0 ? 'base' : `= ${formatNumber(tier.base)}` }}</span>
      </span>
    </template>
  </div>
</template>

<style scoped>
.ms-ladder {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 6px 4px;
  margin: 12px 0;
  padding: 12px 16px;
  border: 1px solid var(--vp-c-divider);
  border-left: 4px solid var(--ms-accent);
  border-radius: 10px;
  background: var(--vp-c-bg-soft);
}

.tier {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  gap: 4px;
  min-width: 48px;
}

.worth {
  font-size: 11px;
  color: var(--vp-c-text-2);
  font-variant-numeric: tabular-nums;
}

.step {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 18px;
  font-size: 18px;
  line-height: 1;
  color: var(--vp-c-text-2);
}

.times {
  font-size: 10px;
  letter-spacing: 0.04em;
}
</style>
