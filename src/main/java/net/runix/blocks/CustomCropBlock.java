package net.runix.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.runix.CustomCrops;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.BlockView;

public class CustomCropBlock extends CropBlock {
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(0.0d,0.0d,0.0d,16.0d,2.0d,16.0d),
            Block.createCuboidShape(0.0d,0.0d,0.0d,16.0d,3.0d,16.0d),
            Block.createCuboidShape(0.0d,0.0d,0.0d,16.0d,4.0d,16.0d),
            Block.createCuboidShape(0.0d,0.0d,0.0d,16.d,5.0d,16.0d),
            Block.createCuboidShape(0.0d,0.0d,0.0d,16.d,6.0d,16.0d),
            Block.createCuboidShape(0.0d,0.0d,0.0d,16.d,7.0d,16.0d),
            Block.createCuboidShape(0.0d,0.0d,0.0d,16.d,8.0d,16.0d),
            Block.createCuboidShape(0.0d,0.0d,0.0d,16.d,9.0d,16.0d)};


    public CustomCropBlock(Settings settings) {
        super(settings);
    }
    public ItemConvertible getSeedsItem(){
        return CustomCrops.BroccoliItem;
    }


    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context){
        return AGE_TO_SHAPE[(Integer)state.get(this.getAgeProperty())];
    }


}
