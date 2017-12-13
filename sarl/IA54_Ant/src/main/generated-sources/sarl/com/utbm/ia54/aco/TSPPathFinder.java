package com.utbm.ia54.aco;

import com.utbm.ia54.aco.tsp_path;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;

/**
 * ---------------------------
 * CAPACITIES
 * ---------------------------
 */
@FunctionalInterface
@SarlSpecification("0.6")
@SarlElementType(18)
@SuppressWarnings("all")
public interface TSPPathFinder extends Capacity {
  public abstract tsp_path findPath(final short nbNodes, final float[][][] env, final short idFirstNode, final short idLastNode);
  
  public static class ContextAwareCapacityWrapper<C extends TSPPathFinder> extends Capacity.ContextAwareCapacityWrapper<C> implements TSPPathFinder {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public tsp_path findPath(final short nbNodes, final float[][][] env, final short idFirstNode, final short idLastNode) {
      try {
        ensureCallerInLocalThread();
        return this.capacity.findPath(nbNodes, env, idFirstNode, idLastNode);
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
