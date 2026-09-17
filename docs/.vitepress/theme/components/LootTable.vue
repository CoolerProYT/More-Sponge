<script setup lang="ts">
import { computed } from 'vue'
import { data, tableName } from '../moresponge'
import ItemSlot from './ItemSlot.vue'

const props = defineProps<{ kind: 'chest' | 'mob' }>()

const rows = computed(() => data.loot.filter((entry) => entry.kind === props.kind))
</script>

<template>
  <div class="ms-table-wrap">
    <table>
      <thead>
        <tr>
          <th>Item</th>
          <th>Amount</th>
          <th>{{ kind === 'chest' ? 'Found in' : 'Dropped by' }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="entry in rows" :key="entry.id">
          <td><ItemSlot :id="entry.item" label /></td>
          <td class="num">{{ entry.min }}–{{ entry.max }}</td>
          <td>
            <ul class="tables">
              <li v-for="table in entry.tables" :key="table">{{ tableName(table) }}</li>
            </ul>
          </td>
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
  white-space: nowrap;
}

.tables {
  margin: 0;
  padding-left: 1.1em;
}

.tables li + li {
  margin-top: 0;
}
</style>
