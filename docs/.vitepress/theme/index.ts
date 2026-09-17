import DefaultTheme from 'vitepress/theme'
import type { Theme } from 'vitepress'
import CompressionLadder from './components/CompressionLadder.vue'
import FuelTable from './components/FuelTable.vue'
import ItemSlot from './components/ItemSlot.vue'
import LootTable from './components/LootTable.vue'
import RangeExplorer from './components/RangeExplorer.vue'
import RecipeCard from './components/RecipeCard.vue'
import SpongeTable from './components/SpongeTable.vue'
import TradeTable from './components/TradeTable.vue'
import './style.css'

export default {
  extends: DefaultTheme,
  enhanceApp({ app }) {
    app.component('CompressionLadder', CompressionLadder)
    app.component('FuelTable', FuelTable)
    app.component('ItemSlot', ItemSlot)
    app.component('LootTable', LootTable)
    app.component('RangeExplorer', RangeExplorer)
    app.component('RecipeCard', RecipeCard)
    app.component('SpongeTable', SpongeTable)
    app.component('TradeTable', TradeTable)
  },
} satisfies Theme
