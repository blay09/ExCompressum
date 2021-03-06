package net.blay09.mods.excompressum.compat.jei;

import net.blay09.mods.excompressum.api.sievemesh.MeshType;
import net.blay09.mods.excompressum.api.sievemesh.SieveMeshRegistryEntry;
import net.blay09.mods.excompressum.registry.heavysieve.HeavySieveRecipe;
import net.blay09.mods.excompressum.registry.sievemesh.SieveMeshRegistry;
import net.minecraft.item.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class JeiHeavySieveRecipe {

    private final HeavySieveRecipe recipe;
    private final List<List<ItemStack>> inputs;
    private final List<MergedLootTableEntry> outputs;
    private final List<ItemStack> outputItems;
    private final boolean waterlogged;

    public JeiHeavySieveRecipe(HeavySieveRecipe recipe) {
        this.recipe = recipe;
        inputs = new ArrayList<>();
        if (recipe.getMinimumMesh() != null) {
            SieveMeshRegistryEntry minimumMesh = SieveMeshRegistry.getEntry(recipe.getMinimumMesh());
            List<ItemStack> meshItems = new ArrayList<>();
            for (SieveMeshRegistryEntry mesh : SieveMeshRegistry.getEntries().values()) {
                if (mesh.getMeshLevel() >= minimumMesh.getMeshLevel()) {
                    meshItems.add(mesh.getItemStack());
                }
            }
            inputs.add(meshItems);
        } else if (recipe.getMeshes() != null) {
            List<ItemStack> meshItems = new ArrayList<>();
            for (MeshType meshType : recipe.getMeshes()) {
                for (SieveMeshRegistryEntry mesh : SieveMeshRegistry.getEntries().values()) {
                    if (mesh.getMeshType() == meshType) {
                        meshItems.add(mesh.getItemStack());
                    }
                }
            }
            inputs.add(meshItems);
        } else {
            inputs.add(Collections.emptyList());
        }
        inputs.add(Arrays.asList(recipe.getInput().getMatchingStacks()));
        List<LootTableEntry> entries = LootTableUtils.getLootTableEntries(recipe.getId(), recipe.getLootTable());
        outputs = LootTableUtils.mergeLootTableEntries(entries);
        outputItems = outputs.stream().map(MergedLootTableEntry::getItemStack).collect(Collectors.toList());
        waterlogged = recipe.isWaterlogged();
    }

    public HeavySieveRecipe getRecipe() {
        return recipe;
    }

    public List<List<ItemStack>> getInputs() {
        return inputs;
    }

    public List<MergedLootTableEntry> getOutputs() {
        return outputs;
    }

    public List<ItemStack> getOutputItems() {
        return outputItems;
    }

    public boolean isWaterlogged() {
        return waterlogged;
    }
}
