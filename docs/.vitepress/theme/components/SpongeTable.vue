<script setup lang="ts">
import { computed } from 'vue'
import { type Element, formatNumber, spongeLine } from '../moresponge'
import ItemSlot from './ItemSlot.vue'

const props = defineProps<{ element: Element }>()

const rows = computed(() => spongeLine(props.element))
</script>

<template>
  <div class="ms-table-wrap">
    <table class="ms-sponges">
      <thead>
        <tr>
          <th>Sponge</th>
          <th>Reach</th>
          <th>Search limit</th>
          <th>Turns into</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="sponge in rows" :key="sponge.id">
          <td><ItemSlot :id="sponge.id" label /></td>
          <td class="num">{{ sponge.maxDepth }} blocks</td>
          <td class="num">{{ formatNumber(sponge.maxCount) }}</td>
          <td><ItemSlot :id="sponge.saturated" label /></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.ms-table-wrap {
  overflow-x: auto;
}

.ms-sponges td {
  vertical-align: middle;
}

.num {
  font-variant-numeric: tabular-nums;
  white-space: nowrap;
}
</style>
