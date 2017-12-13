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
public class clear_solutions extends Event {
  public boolean clear_best;
  
  public boolean clear_normal;
  
  @SyntheticMember
  public clear_solutions() {
    super();
  }
  
  @SyntheticMember
  public clear_solutions(final Address source) {
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
    clear_solutions other = (clear_solutions) obj;
    if (other.clear_best != this.clear_best)
      return false;
    if (other.clear_normal != this.clear_normal)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (this.clear_best ? 1231 : 1237);
    result = prime * result + (this.clear_normal ? 1231 : 1237);
    return result;
  }
  
  /**
   * Returns a String representation of the clear_solutions event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("clear_best  = ").append(this.clear_best);
    result.append("clear_normal  = ").append(this.clear_normal);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -35297715L;
}
