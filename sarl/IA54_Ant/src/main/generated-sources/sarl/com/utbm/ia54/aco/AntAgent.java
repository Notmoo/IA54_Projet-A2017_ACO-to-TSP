package com.utbm.ia54.aco;

import com.utbm.ia54.aco.SimpleTSPPathFinder;
import com.utbm.ia54.aco.TSPPathFinder;
import com.utbm.ia54.aco.find_path;
import com.utbm.ia54.aco.poc;
import com.utbm.ia54.aco.register;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.6")
@SarlElementType(17)
@SuppressWarnings("all")
public class AntAgent extends Agent {
  private UUID envUUID;
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    this.<SimpleTSPPathFinder>setSkill(new SimpleTSPPathFinder(), TSPPathFinder.class);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(new register());
  }
  
  @SyntheticMember
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$find_path$2(final find_path occurrence) {
    if ((occurrence.idFirstNode != (-1))) {
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      TSPPathFinder _$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER$CALLER = this.$castSkill(TSPPathFinder.class, (this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER == null || this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER.get() == null) ? (this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER = this.$getSkill(TSPPathFinder.class)) : this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER);
      final Scope<Address> _function = (Address it) -> {
        UUID _uUID = it.getUUID();
        return (_uUID == this.envUUID);
      };
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER$CALLER.findPath(occurrence.nbNodes, occurrence.env, occurrence.idFirstNode, occurrence.idLastNode), _function);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      final Scope<Address> _function_1 = (Address it) -> {
        UUID _uUID = it.getUUID();
        UUID _uUID_1 = occurrence.getSource().getUUID();
        return (_uUID == _uUID_1);
      };
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(new poc(), _function_1);
    } else {
      int _nextInt = new Random().nextInt((occurrence.nbNodes - 1));
      int _plus = (_nextInt + 1);
      final short idFirstNode = ((short) _plus);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      TSPPathFinder _$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER$CALLER_1 = this.$castSkill(TSPPathFinder.class, (this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER == null || this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER.get() == null) ? (this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER = this.$getSkill(TSPPathFinder.class)) : this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER);
      final Scope<Address> _function_2 = (Address it) -> {
        UUID _uUID = it.getUUID();
        return (_uUID == this.envUUID);
      };
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2.emit(_$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER$CALLER_1.findPath(occurrence.nbNodes, occurrence.env, idFirstNode, occurrence.idLastNode), _function_2);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_3 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      final Scope<Address> _function_3 = (Address it) -> {
        UUID _uUID = it.getUUID();
        UUID _uUID_1 = occurrence.getSource().getUUID();
        return (_uUID == _uUID_1);
      };
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_3.emit(new poc(), _function_3);
    }
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(DefaultContextInteractions.class, ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $0$getSkill(DefaultContextInteractions.class)) : $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS)", imported = DefaultContextInteractions.class)
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @Extension
  @ImportedCapacityFeature(TSPPathFinder.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(TSPPathFinder.class, ($0$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER == null || $0$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER.get() == null) ? ($0$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER = $0$getSkill(TSPPathFinder.class)) : $0$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER)", imported = TSPPathFinder.class)
  private TSPPathFinder $CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER$CALLER() {
    if (this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER == null || this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER.get() == null) {
      this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER = $getSkill(TSPPathFinder.class);
    }
    return $castSkill(TSPPathFinder.class, this.$CAPACITY_USE$COM_UTBM_IA54_ACO_TSPPATHFINDER);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$find_path(final find_path occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$find_path$2(occurrence));
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
    AntAgent other = (AntAgent) obj;
    if (!Objects.equals(this.envUUID, other.envUUID)) {
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
    result = prime * result + Objects.hashCode(this.envUUID);
    return result;
  }
  
  @SyntheticMember
  public AntAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public AntAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public AntAgent(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
