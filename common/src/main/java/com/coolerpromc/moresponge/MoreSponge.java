package com.coolerpromc.moresponge;

import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.block.entity.MSBlockEntities;
import com.coolerpromc.moresponge.item.MSCreativeTabs;
import com.coolerpromc.moresponge.recipe.MSRecipes;
import com.coolerpromc.moresponge.screen.MSMenus;

public class MoreSponge {
    public static void init() {
        MSBlocks.init();
        MSCreativeTabs.init();
        MSRecipes.init();
        MSBlockEntities.init();
        MSMenus.init();
    }
}