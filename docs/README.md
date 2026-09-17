# More Sponge wiki

VitePress site for the mod. Recipes, trades, trader biomes, loot, sponge ranges and freezer fuels are read from the mod itself, so regenerate the mod's data before building when it changes.

```bash
./gradlew :neoforge:runData   # from the repository root, when mod data changed
cd docs
npm install
npm run dev                   # syncs data, then serves http://localhost:5173
npm run build                 # syncs data, then builds to .vitepress/dist
```

`npm run sync` (run automatically by `dev` and `build`) writes `.vitepress/data/data.json`, which is git-ignored. It reads the datagen output in `common/src/generated` and `neoforge/src/generated`, plus the sponge ranges from `MSBlocks.java`, the fuels from `FreezerBlockEntity.java` and the spawn timings from `SpongeTraderSpawner.java`.

## Icons

No textures are bundled. Every item loads a 1024x1024 PNG from `https://storage.googleapis.com/coolerpromc/textures/`:

- Vanilla items: `textures/minecraft/<item>.png`
- Mod items: `textures/moresponge/<item>.png`

Block items are rendered as isometric icons that match the vanilla renders. After adding or retexturing a block, render the icons and upload them:

```bash
py docs/scripts/render_icons.py build/wiki-icons
gcloud storage cp build/wiki-icons/*.png gs://coolerpromc/textures/moresponge/
```

The script needs Pillow (`pip install pillow`). Flat item textures such as spawn eggs are scaled up to 1024x1024 with nearest-neighbour.
