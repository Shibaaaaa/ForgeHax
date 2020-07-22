package dev.fiki.forgehax.common.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.eventbus.api.Event;

/**
 * Created by Babbaj on 9/20/2017.
 */
@Getter
@AllArgsConstructor
public class SchematicaPlaceBlockEvent extends Event {
  private final ItemStack item;
  private final BlockPos pos;
  private final Vector3d vec;
  private final Direction side;
}
