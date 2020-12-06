package net.flytre.rplus.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Random;

public class Diode extends AbstractRedstoneGateBlock {

    public static final BooleanProperty LOCKED;

    public Diode(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(LOCKED, false).with(POWERED, false));
    }


    protected int getUpdateDelayInternal(BlockState state) {
        return 0;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = super.getPlacementState(ctx);
        return blockState.with(LOCKED, this.isLocked(ctx.getWorld(), ctx.getBlockPos(), blockState));
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        return !world.isClient() && direction.getAxis() != state.get(FACING).getAxis() ? state.with(LOCKED, this.isLocked(world, pos, state)) : super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    public boolean isLocked(WorldView worldView, BlockPos pos, BlockState state) {
        return this.getMaxInputLevelSides(worldView, pos, state) > 0;
    }

    protected boolean isValidInput(BlockState state) {
        return isRedstoneGate(state);
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(POWERED)) {
            Direction direction = state.get(FACING);
            double d = (double)pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
            double e = (double)pos.getY() + 0.4D + (random.nextDouble() - 0.5D) * 0.2D;
            double f = (double)pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
            float g = -5.0F;
            if (random.nextBoolean()) {
                g = (float)(-1);
            }

            g /= 16.0F;
            double h = g * (float)direction.getOffsetX();
            double i = g * (float)direction.getOffsetZ();
            world.addParticle(DustParticleEffect.RED, d + h, e, f + i, 0.0D, 0.0D, 0.0D);
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LOCKED, POWERED);
    }

    static {
        LOCKED = Properties.LOCKED;
    }
}
