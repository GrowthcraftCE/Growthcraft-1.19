package growthcraft.bamboo.block;

import growthcraft.lib.block.GrowthcraftLogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BambooLogBlock extends GrowthcraftLogBlock {
    protected static final VoxelShape BOUNDING_BOX = Block.box(
            4.0D, 0.0D, 4.0D,
            12.0D, 16.0D, 12.0D
    );

    public BambooLogBlock() {
        super(getInitProperties(Material.BAMBOO));
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material);
        properties.strength(1.0F, 1.0F);
        properties.sound(SoundType.BAMBOO);
        properties.noCollission();
        return properties;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return BOUNDING_BOX;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
        return BOUNDING_BOX;
    }

}

