<script setup lang="ts">
import { computed } from 'vue'
import { data, itemName, seconds } from '../moresponge'
import ItemSlot from './ItemSlot.vue'

/** Every freezing recipe takes the same time, so a fuel's burn time maps to a fixed item count. */
const cookTime = computed(() => {
  const times = data.recipes.filter((r) => r.type === 'moresponge:freezing').map((r) => r.cookingTime ?? 200)
  return times.length ? Math.max(...times) : 200
})

const rows = computed(() =>
  data.fuels.map((fuel) => ({
    ...fuel,
    name: itemName(fuel.item),
    items: fuel.ticks / cookTime.value,
  })),
)

const format = (value: number) => (Number.isInteger(value) ? `${value}` : value.toFixed(2).replace(/0$/, ''))
</script>

<template>
  <div class="ms-table-wrap">
    <table>
      <thead>
        <tr>
          <th>Fuel</th>
          <th>Burns for</th>
          <th>Items frozen</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="fuel in rows" :key="fuel.item">
          <td><ItemSlot :id="fuel.item" label /></td>
          <td class="num">{{ seconds(fuel.ticks) }} s</td>
          <td class="num">{{ format(fuel.items) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.ms-table-wrap {
  overflow-x: auto;
}

td {
  vertical-align: middle;
}

.num {
  font-variant-numeric: tabular-nums;
}
</style>
