package net.blay09.mods.excompressum.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CompressedBlock extends Block {

    public static final String namePrefix = "compressed_";

    private final CompressedBlockType type;

    public CompressedBlock(CompressedBlockType type) {
        super(Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(4f, 6f));
        this.type = type;
    }

}
