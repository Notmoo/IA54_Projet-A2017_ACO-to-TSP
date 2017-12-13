package com.utbm.ia54.aco;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * ---------------------------
 * EVENTS
 * ---------------------------
 */
@SarlSpecification("0.6")
@SarlElementType(14)
@SuppressWarnings("all")
public class tsp_path extends Event {
  public final short nbNodes;
  
  public final short[] nodes;
  
  public final double dist;
  
  public tsp_path(final short nbNodes, final short[] nodes, final double dist) {
    this.nbNodes = nbNodes;
    this.nodes = nodes;
    this.dist = dist;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    tsp_path other = (tsp_path) obj;
    if (other.nbNodes != this.nbNodes)
      return false;
    if (Double.doubleToLongBits(other.dist) != Double.doubleToLongBits(this.dist))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.nbNodes;
    result = prime * result + (int) (Double.doubleToLongBits(this.dist) ^ (Double.doubleToLongBits(this.dist) >>> 32));
    return result;
  }
  
  /**
   * Returns a String representation of the tsp_path event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("nbNodes  = ").append(this.nbNodes);
    result.append("nodes  = ").append(this.nodes);
    result.append("dist  = ").append(this.dist);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 2744773365L;
}
