package com.utbm.ia54.aco;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.6")
@SarlElementType(14)
@SuppressWarnings("all")
public class display_nodes extends Event {
  public short nbNodes;
  
  public float[][][] env;
  
  public long dist;
  
  public boolean isBest;
  
  @SyntheticMember
  public display_nodes() {
    super();
  }
  
  @SyntheticMember
  public display_nodes(final Address source) {
    super(source);
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
    display_nodes other = (display_nodes) obj;
    if (other.nbNodes != this.nbNodes)
      return false;
    if (other.dist != this.dist)
      return false;
    if (other.isBest != this.isBest)
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
    result = prime * result + (int) (this.dist ^ (this.dist >>> 32));
    result = prime * result + (this.isBest ? 1231 : 1237);
    return result;
  }
  
  /**
   * Returns a String representation of the display_nodes event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("nbNodes  = ").append(this.nbNodes);
    result.append("env  = ").append(this.env);
    result.append("dist  = ").append(this.dist);
    result.append("isBest  = ").append(this.isBest);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -283325042L;
}
