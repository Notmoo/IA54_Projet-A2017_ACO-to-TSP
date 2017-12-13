package com.utbm.ia54.aco;

import com.utbm.ia54.aco.TSPPathFinder;
import com.utbm.ia54.aco.java.ArrayFactory;
import com.utbm.ia54.aco.tsp_path;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Skill;
import java.util.Random;

/**
 * ---------------------------
 * SKILLS
 * ---------------------------
 */
@SarlSpecification("0.6")
@SarlElementType(20)
@SuppressWarnings("all")
public class SimpleTSPPathFinder extends Skill implements TSPPathFinder {
  public tsp_path findPath(final short nbNodes, final float[][][] env, final short idFirstNode, final short idLastNode) {
    boolean[] visited = ArrayFactory.newBooleanArray(nbNodes);
    short[] solution = ArrayFactory.newShortArray(nbNodes);
    int step = 0;
    double dist = 0;
    solution[0] = idFirstNode;
    for (step = 1; (step < nbNodes); step++) {
      {
        short temp_node_id = ((short) 0);
        temp_node_id--;
        do {
          if (((step == (nbNodes - 1)) && (idLastNode != (-1)))) {
            temp_node_id = idLastNode;
          } else {
            temp_node_id = this.findNext(nbNodes, this.computeProbabilities(nbNodes, env[solution[(step - 1)]], visited, idLastNode));
          }
        } while((temp_node_id == (-1)));
      }
    }
    return new tsp_path(nbNodes, solution, dist);
  }
  
  public double[] computeProbabilities(final short nbNodes, final float[][] destinations, final boolean[] visited, final short idLastNode) {
    int nbNoeudEligibles = 0;
    double totalPheromones = 0;
    double[] proba = ArrayFactory.newDoubleArray(nbNodes);
    int i = 0;
    for (i = 0; (i < nbNodes); i++) {
      if (((!visited[i]) && (i != idLastNode))) {
        nbNoeudEligibles++;
        double _talPheromones = totalPheromones;
        float _get = destinations[i][1];
        totalPheromones = (_talPheromones + _get);
      }
    }
    for (i = 0; (i < nbNodes); i++) {
      if (((!visited[i]) && (i != idLastNode))) {
        if ((i == 0)) {
          float _get = destinations[i][1];
          double _divide = (_get / totalPheromones);
          proba[i] = _divide;
        } else {
          double _get_1 = proba[i];
          float _get_2 = destinations[i][1];
          double _divide_1 = (_get_2 / totalPheromones);
          double _plus = (_get_1 + _divide_1);
          proba[i] = _plus;
        }
      } else {
        if ((i == 0)) {
          proba[0] = 0;
        } else {
          proba[i] = proba[(i - 1)];
        }
      }
    }
    return proba;
  }
  
  public short findNext(final short nbNodes, final double[] proba) {
    short id = ((short) 0);
    id--;
    final double rand = new Random().nextDouble();
    short i = ((short) 0);
    while ((rand >= proba[i])) {
      i++;
    }
    double _get = proba[i];
    boolean _lessThan = (rand < _get);
    if (_lessThan) {
      id = i;
    }
    return id;
  }
  
  @SyntheticMember
  public SimpleTSPPathFinder() {
    super();
  }
  
  @SyntheticMember
  public SimpleTSPPathFinder(final Agent agent) {
    super(agent);
  }
}
