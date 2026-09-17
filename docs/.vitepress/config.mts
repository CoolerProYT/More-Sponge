import { defineConfig } from 'vitepress'

// GitHub Pages serves a project site from /<repository>/. For a custom domain or a user site, build with DOCS_BASE=/.
const base = process.env.DOCS_BASE ?? '/More-Sponge/'
const SPONGE_ICON = 'https://storage.googleapis.com/coolerpromc/textures/moresponge/compressed_sponge_5x.png'

export default defineConfig({
  title: 'More Sponge',
  description: 'Sponges for water, lava, snow and fire, compressed up to 5x, for Minecraft 26.3 on Fabric and NeoForge.',
  base,
  cleanUrls: true,
  srcExclude: ['README.md', 'scripts/**'],
  head: [['link', { rel: 'icon', type: 'image/png', href: SPONGE_ICON }]],
  themeConfig: {
    logo: { src: SPONGE_ICON, alt: '' },
    nav: [
      { text: 'Guide', link: '/guide/getting-started' },
      { text: 'Sponges', link: '/guide/sponges' },
      { text: 'Traders', link: '/guide/traders' },
      { text: 'Datapacks', link: '/datapacks/' },
    ],
    sidebar: [
      {
        text: 'Guide',
        items: [
          { text: 'Getting started', link: '/guide/getting-started' },
          { text: 'How sponges work', link: '/guide/sponges' },
          { text: 'Compression', link: '/guide/compression' },
        ],
      },
      {
        text: 'Sponges',
        items: [
          { text: 'Water sponges', link: '/guide/water-sponges' },
          { text: 'Lava sponges', link: '/guide/lava-sponges' },
          { text: 'Snow sponges', link: '/guide/snow-sponges' },
          { text: 'Fire sponges', link: '/guide/fire-sponges' },
        ],
      },
      {
        text: 'World',
        items: [
          { text: 'The freezer', link: '/guide/freezer' },
          { text: 'Sponge traders', link: '/guide/traders' },
          { text: 'Loot', link: '/guide/loot' },
          { text: 'JEI', link: '/guide/compat' },
        ],
      },
      {
        text: 'Datapacks',
        items: [
          { text: 'Overview', link: '/datapacks/' },
          { text: 'Freezing recipes', link: '/datapacks/freezing-recipes' },
          { text: 'Trader trades', link: '/datapacks/trades' },
          { text: 'Trader biomes', link: '/datapacks/trader-biomes' },
        ],
      },
      { text: 'FAQ', link: '/faq' },
    ],
    socialLinks: [{ icon: 'github', link: 'https://github.com/CoolerProYT/More-Sponge' }],
    search: { provider: 'local' },
    outline: { level: [2, 3] },
    footer: { message: 'Released under the MIT License.' },
  },
})
