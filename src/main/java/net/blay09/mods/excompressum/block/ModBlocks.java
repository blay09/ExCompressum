package net.blay09.mods.excompressum.block;

import net.blay09.mods.excompressum.ExCompressum;
import net.minecraft.block.Block;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = ExCompressum.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

	public static Block compressedBlock;
	public static Block heavySieve;
	public static Block woodenCrucible;
	public static Block bait;
	public static Block autoHammer;
	public static Block autoCompressedHammer;
	public static Block autoHeavySieve;
	public static Block autoSieve;
	public static Block autoCompressor;
	public static Block rationingAutoCompressor;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();
		registry.registerAll(
				new CompressedBlock().setRegistryName(CompressedBlock.name),
				new HeavySieveBlock().setRegistryName(HeavySieveBlock.name),
				new WoodenCrucibleBlock().setRegistryName(WoodenCrucibleBlock.name),
				new BaitBlock().setRegistryName(BaitBlock.name),
				new AutoHammerBlock().setRegistryName(AutoHammerBlock.name),
				new AutoSieveBlock().setRegistryName(AutoSieveBlock.name),
				new AutoCompressedHammerBlock().setRegistryName(AutoCompressedHammerBlock.name),
				new AutoHeavySieveBlock().setRegistryName(AutoHeavySieveBlock.name),
				new AutoCompressorBlock().setRegistryName(AutoCompressorBlock.name),
				new RationingAutoCompressorBlock().setRegistryName(RationingAutoCompressorBlock.name)
		);
	}

	@SubscribeEvent
	public static void registerBlockItems(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		registry.registerAll(
				new ItemBlockCompressed(compressedBlock).setRegistryName(CompressedBlock.name),
				new ItemBlockHeavySieve(heavySieve).setRegistryName(HeavySieveBlock.name),
				new ItemBlockWoodenCrucible(woodenCrucible).setRegistryName(WoodenCrucibleBlock.name),
				new ItemBlockBait(bait).setRegistryName(BaitBlock.name),
				new ItemBlock(autoHammer).setRegistryName(AutoHammerBlock.name),
				new ItemBlock(autoCompressedHammer).setRegistryName(AutoCompressedHammerBlock.name),
				new ItemBlock(autoSieve).setRegistryName(AutoSieveBlock.name),
				new ItemBlock(autoHeavySieve).setRegistryName(AutoHeavySieveBlock.name),
				new ItemBlock(autoCompressor).setRegistryName(AutoCompressorBlock.name),
				new ItemBlock(rationingAutoCompressor).setRegistryName(RationingAutoCompressorBlock.name)
		);
	}

	/*public static void registerModels() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(autoCompressedHammer), 0, new ModelResourceLocation(BlockAutoCompressedHammer.registryName, "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(autoSieve), 0, new ModelResourceLocation(BlockAutoSieve.registryName, "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(autoHeavySieve), 0, new ModelResourceLocation(BlockAutoHeavySieve.registryName, "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(autoCompressor), 0, new ModelResourceLocation(BlockAutoCompressor.registryName, "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(autoCompressorRationing), 0, new ModelResourceLocation(BlockAutoCompressorRationing.registryName, "inventory"));

		// Baits
		ResourceLocation[] baitVariants = new ResourceLocation[BaitType.values.length];
        for(int i = 0; i < baitVariants.length; i++) {
            baitVariants[i] = new ResourceLocation(ExCompressum.MOD_ID, "bait_" + BaitType.values[i].getName());
        }
        Item baitItem = Item.getItemFromBlock(bait);
        ModelBakery.registerItemVariants(baitItem, baitVariants);
        ModelLoader.setCustomMeshDefinition(baitItem, itemStack -> {
			BaitType type = itemStack.getItemDamage() >= 0 && itemStack.getItemDamage() < BaitType.values.length ? BaitType.values[itemStack.getItemDamage()] : null;
			if(type != null) {
				return new ModelResourceLocation(new ResourceLocation(ExCompressum.MOD_ID, "bait_" + type.getName()), "inventory");
			} else {
				return new ModelResourceLocation("missingno");
			}
		});

        // Compressed Blocks
		ResourceLocation[] variants = new ResourceLocation[CompressedBlockType.values.length];
		for(int i = 0; i < variants.length; i++) {
			variants[i] = new ResourceLocation(ExCompressum.MOD_ID, "compressed_" + CompressedBlockType.values[i].getName());
		}
		Item compressedBlockItem = Item.getItemFromBlock(compressedBlock);
		ModelBakery.registerItemVariants(compressedBlockItem, variants);
		ModelLoader.setCustomMeshDefinition(compressedBlockItem, itemStack -> {
			CompressedBlockType type = CompressedBlockType.fromId(itemStack.getItemDamage());
			if(type != null) {
				return new ModelResourceLocation(new ResourceLocation(ExCompressum.MOD_ID, "compressed_" + type.getName()), "inventory");
			} else {
				return new ModelResourceLocation("missingno");
			}
		});
		ModelLoader.setCustomStateMapper(compressedBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(BlockState state) {
				return new ModelResourceLocation(new ResourceLocation(ExCompressum.MOD_ID, "compressed_" + state.getValue(CompressedBlock.VARIANT).getName()), "normal");
			}
		});

		// Wooden Crucible
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(woodenCrucible), itemStack -> {
			WoodenCrucibleType type = itemStack.getItemDamage() >= 0 && itemStack.getItemDamage() < WoodenCrucibleType.values.length ? WoodenCrucibleType.values[itemStack.getItemDamage()] : null;
			if(type != null) {
				return new ModelResourceLocation(WoodenCrucibleBlock.registryName, "variant=" + type.getName());
			} else {
				return new ModelResourceLocation("missingno");
			}
		});

		// Heavy Sieve
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(heavySieve), itemStack -> {
			HeavySieveType type = itemStack.getItemDamage() >= 0 && itemStack.getItemDamage() < HeavySieveType.values.length ? HeavySieveType.values[itemStack.getItemDamage()] : null;
			if (type != null) {
				if (ExRegistro.doMeshesHaveDurability()) {
					return new ModelResourceLocation(HeavySieveBlock.registryName, "variant=" + type.getName() + ",with_mesh=false");
				} else {
					return new ModelResourceLocation(HeavySieveBlock.registryName, "variant=" + type.getName() + ",with_mesh=false"); // it's false here too because it was a dumb idea based on wrong thinking; don't want to remove it now though
				}
			} else {
				return new ModelResourceLocation("missingno");
			}
		});

		// Auto Hammer
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(autoHammer), stack -> new ModelResourceLocation(BlockAutoHammer.registryName, "facing=north,ugly=" + ((stack.getItemDamage() & 8) == 8)));
	}*/

}
