package com.costin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class KeypadBlock extends Block {

    private final int minClearanceLevel;
    public KeypadBlock(Settings settings, int minClearanceLevel) {
        super(settings);
        this.minClearanceLevel = minClearanceLevel;
        setDefaultState(this.stateManager.getDefaultState().with(Properties.POWERED, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.POWERED);
    }
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient) {
            if(stack.getItem() instanceof KeycardItem item) {
                if(item.getClearanceLevel() >= minClearanceLevel) {
                    world.setBlockState(pos, state.with(Properties.POWERED, true), 3);
                    world.updateNeighbors(pos, this);
                    world.scheduleBlockTick(pos, this, 20);
                }
            }
        }

        return ItemActionResult.SUCCESS;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return state.get(Properties.POWERED);
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(Properties.POWERED) ? 15 : 0;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(Properties.POWERED) ? 15 : 0;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        if (!world.isClient) {
            world.setBlockState(pos, state.with(Properties.POWERED, false), 3);
            world.updateNeighbors(pos, this);
        }
    }
}
