package com.coolerpromc.moresponge;

import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.block.entity.MSBlockEntities;
import com.coolerpromc.moresponge.entity.MSEntities;
import com.coolerpromc.moresponge.gamerule.MSGameRules;
import com.coolerpromc.moresponge.item.MSCreativeTabs;
import com.coolerpromc.moresponge.item.MSItems;
import com.coolerpromc.moresponge.recipe.MSRecipes;
import com.coolerpromc.moresponge.screen.MSMenus;

public class MoreSponge {
    public static void init() {
        MSBlocks.init();
        MSItems.init();
        MSCreativeTabs.init();
        MSRecipes.init();
        MSBlockEntities.init();
        MSMenus.init();
        MSEntities.init();
        MSGameRules.init();
    }
}