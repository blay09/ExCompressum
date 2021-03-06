package net.blay09.mods.excompressum.block;

import net.blay09.mods.excompressum.ExCompressum;
import net.blay09.mods.excompressum.tile.AutoCompressedHammerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;

public class AutoCompressedHammerBlock extends AutoHammerBlock {

    public static final String name = "auto_compressed_hammer";
    public static final ResourceLocation registryName = new ResourceLocation(ExCompressum.MOD_ID, name);

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new AutoCompressedHammerTileEntity();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, UGLY);
    }

}
