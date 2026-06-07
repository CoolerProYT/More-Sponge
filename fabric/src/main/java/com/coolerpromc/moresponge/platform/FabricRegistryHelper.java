package com.coolerpromc.moresponge.platform;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.platform.services.IRegistryHelper;
import com.coolerpromc.moresponge.platform.util.BlockEntityTypeFactory;
import com.coolerpromc.moresponge.platform.util.CreativeTabOutput;
import com.coolerpromc.moresponge.platform.util.MenuFactory;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityDataRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.gamerules.GameRule;

import java.util.Arrays;
import java.util.function.*;

public class FabricRegistryHelper implements IRegistryHelper {
    @Override
    public <T extends Block> RegistryHandler.Blocks<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> func, BlockBehaviour.Properties p) {
        ResourceKey<Block> key = IRegistryHelper.blockKey(name);
        Holder<T> holder = Registry.registerForHolder(BuiltInRegistries.BLOCK, key, func.apply(p.setId(key)));

        return new RegistryHandler.Blocks<>() {
            @Override
            public Holder<Block> holder() {
                return (Holder<Block>) holder;
            }

            @Override
            public T get() {
                return holder.value();
            }
        };
    }

    @Override
    public <T extends Item> RegistryHandler.Items<T> registerItem(String name, Function<Item.Properties, T> func, Item.Properties p) {
        ResourceKey<Item> key = IRegistryHelper.itemKey(name);
        Holder<T> holder = Registry.registerForHolder(BuiltInRegistries.ITEM, key, func.apply(p.setId(key)));

        return new RegistryHandler.Items<T>() {
            @Override
            public Holder<Item> holder() {
                return (Holder<Item>) holder;
            }

            @Override
            public T get() {
                return holder.value();
            }
        };
    }

    @Override
    public <T extends BlockItem> RegistryHandler.Items<T> registerBlockItem(String name, RegistryHandler.Blocks<? extends Block> block, BiFunction<Block, Item.Properties, T> item) {
        return registerItem(name, properties -> item.apply(block.get(), properties));
    }

    @Override
    public RegistryHandler<CreativeModeTab, CreativeModeTab> registerCreativeTab(String name, Supplier<ItemStack> icon, Component title, BiConsumer<CreativeTabOutput, CreativeModeTab.ItemDisplayParameters> entries) {
        Holder<CreativeModeTab> holder = Registry.registerForHolder(BuiltInRegistries.CREATIVE_MODE_TAB, Constants.id(name), FabricCreativeModeTab.builder().icon(icon).title(title).displayItems((parameters, output) -> entries.accept(output::accept, parameters)).build());

        return new RegistryHandler<>() {
            @Override
            public Holder<CreativeModeTab> holder() {
                return holder;
            }

            @Override
            public CreativeModeTab get() {
                return holder.value();
            }
        };
    }

    @Override
    public <T extends BlockEntity> RegistryHandler<BlockEntityType<?>, BlockEntityType<T>> registerBlockEntityType(String name, BlockEntityTypeFactory<T> factory, Supplier<? extends Block>... blocks) {
        Holder<BlockEntityType<T>> holder = Registry.registerForHolder(BuiltInRegistries.BLOCK_ENTITY_TYPE, Constants.id(name), FabricBlockEntityTypeBuilder.create(factory::create, Arrays.stream(blocks).map(Supplier::get).toArray(Block[]::new)).build());

        return new RegistryHandler<>() {
            @Override
            public Holder<BlockEntityType<?>> holder() {
                return (Holder<BlockEntityType<?>>) (Holder<?>) holder;
            }

            @Override
            public BlockEntityType<T> get() {
                return holder.value();
            }
        };
    }

    @Override
    public <T extends Recipe<?>> RegistryHandler<RecipeType<?>, RecipeType<T>> registerRecipeType(String name) {
        Identifier id = Constants.id(name);
        Holder<RecipeType<T>> holder = Registry.registerForHolder(BuiltInRegistries.RECIPE_TYPE, id, new RecipeType<T>() {
            @Override
            public String toString() {
                return id.toString();
            }
        });

        return new RegistryHandler<>() {
            @Override
            public Holder<RecipeType<?>> holder() {
                return (Holder<RecipeType<?>>) (Holder<?>) holder;
            }

            @Override
            public RecipeType<T> get() {
                return holder.value();
            }
        };
    }

    @Override
    public <T extends Recipe<?>> RegistryHandler<RecipeSerializer<?>, RecipeSerializer<T>> registerRecipeSerializer(String name, RecipeSerializer<T> serializer) {
        Holder<RecipeSerializer<T>> holder = Registry.registerForHolder(BuiltInRegistries.RECIPE_SERIALIZER, Constants.id(name), serializer);

        return new RegistryHandler<>() {
            @Override
            public Holder<RecipeSerializer<?>> holder() {
                return (Holder<RecipeSerializer<?>>) (Holder<?>) holder;
            }

            @Override
            public RecipeSerializer<T> get() {
                return holder.value();
            }
        };
    }

    @Override
    public <T extends AbstractContainerMenu, D> RegistryHandler<MenuType<?>, MenuType<T>> registerMenuType(String name, MenuFactory<T, D> factory, StreamCodec<? super RegistryFriendlyByteBuf, D> data) {
        Holder<MenuType<T>> holder = Registry.registerForHolder(BuiltInRegistries.MENU, Constants.id(name), new ExtendedMenuType<>(factory::create, data));

        return new RegistryHandler<>() {
            @Override
            public Holder<MenuType<?>> holder() {
                return (Holder<MenuType<?>>) (Holder<?>) holder;
            }

            @Override
            public MenuType<T> get() {
                return holder.value();
            }
        };
    }

    @Override
    public <T extends Entity> RegistryHandler.Entities<T> registerEntity(String name, EntityType.EntityFactory<T> factory, MobCategory category, UnaryOperator<EntityType.Builder<T>> builder) {
        ResourceKey<EntityType<?>> key = IRegistryHelper.entityKey(name);
        Holder<EntityType<T>> holder = Registry.registerForHolder(BuiltInRegistries.ENTITY_TYPE, key.identifier(), builder.apply(EntityType.Builder.of(factory, category)).build(key));

        return new RegistryHandler.Entities<>() {
            @Override
            public Holder<EntityType<?>> holder() {
                return (Holder<EntityType<?>>) (Holder<?>) holder;
            }

            @Override
            public EntityType<T> get() {
                return holder.value();
            }
        };
    }

    @Override
    public <T> RegistryHandler<EntityDataSerializer<?>, EntityDataSerializer<T>> registerEntityDataSerializer(String name, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        Identifier id = Constants.id(name);
        EntityDataSerializer<T> serializer = EntityDataSerializer.forValueType(streamCodec);
        FabricEntityDataRegistry.register(id, serializer);

        return new RegistryHandler<>() {
            @Override
            public Holder<EntityDataSerializer<?>> holder() {
                return Holder.direct(serializer);
            }

            @Override
            public EntityDataSerializer<T> get() {
                return serializer;
            }
        };
    }

    @Override
    public <T> RegistryHandler<GameRule<?>, GameRule<T>> registerGameRule(String name, GameRule<T> gameRule) {
        Holder<GameRule<T>> holder = Registry.registerForHolder(BuiltInRegistries.GAME_RULE, Constants.id(name), gameRule);

        return new RegistryHandler<>() {
            @Override
            public Holder<GameRule<?>> holder() {
                return (Holder<GameRule<?>>) (Holder<?>) holder;
            }

            @Override
            public GameRule<T> get() {
                return holder.value();
            }
        };
    }
}
