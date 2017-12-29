package com.utbm.ia54.aco;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.Objects;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.6")
@SarlElementType(14)
@SuppressWarnings("all")
public class tsp_property_changed extends Event {
  public final String property_id;
  
  public final String property_value;
  
  public tsp_property_changed(final String propId, final String propVal) {
    this.property_id = propId;
    this.property_value = propVal;
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
    tsp_property_changed other = (tsp_property_changed) obj;
    if (!Objects.equals(this.property_id, other.property_id)) {
      return false;
    }
    if (!Objects.equals(this.property_value, other.property_value)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.property_id);
    result = prime * result + Objects.hashCode(this.property_value);
    return result;
  }
  
  /**
   * Returns a String representation of the tsp_property_changed event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("property_id  = ").append(this.property_id);
    result.append("property_value  = ").append(this.property_value);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 3088654777L;
}
