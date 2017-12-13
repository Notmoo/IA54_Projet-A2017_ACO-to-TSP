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
public class find_path extends Event {
  public short nbNodes;
  
  public float[][][] env;
  
  public short idFirstNode;
  
  public short idLastNode;
  
  @SyntheticMember
  public find_path() {
    super();
  }
  
  @SyntheticMember
  public find_path(final Address source) {
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
    find_path other = (find_path) obj;
    if (other.nbNodes != this.nbNodes)
      return false;
    if (other.idFirstNode != this.idFirstNode)
      return false;
    if (other.idLastNode != this.idLastNode)
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
    result = prime * result + this.idFirstNode;
    result = prime * result + this.idLastNode;
    return result;
  }
  
  /**
   * Returns a String representation of the find_path event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("nbNodes  = ").append(this.nbNodes);
    result.append("env  = ").append(this.env);
    result.append("idFirstNode  = ").append(this.idFirstNode);
    result.append("idLastNode  = ").append(this.idLastNode);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 98173432L;
}
