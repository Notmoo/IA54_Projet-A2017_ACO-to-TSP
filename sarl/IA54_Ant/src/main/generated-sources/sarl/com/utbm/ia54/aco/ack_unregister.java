package com.utbm.ia54.aco;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

@SarlSpecification("0.6")
@SarlElementType(14)
@SuppressWarnings("all")
public class ack_unregister extends Event {
  @SyntheticMember
  public ack_unregister() {
    super();
  }
  
  @SyntheticMember
  public ack_unregister(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
