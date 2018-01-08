package com.utbm.ia54.aco.java;

import java.util.EnumSet;

public enum StringDef {

	ENV_DISTANCE_IMPORTANCE_FACTOR_STR("distance importance in env","1"),
	ENV_PHEROMONE_IMPORTANCE_FACTOR_STR("pheromone evaporation factor","0.5"),
	ANT_DISTANCE_IMPORTANCE_FACTOR_STR("distance importance factor for ants","1"),
	ANT_PHEROMONE_IMPORTANCE_FACTOR_STR("pheromone importance factor for ants","1"),
	NB_OF_ANTS_STR("number of ants","5"),
	FIRST_NODE_ID_STR("first node id","-1"),
	LAST_NODE_ID_STR("last node id","-1");
	
	private String label;
	private String defaulValue;
	
	private StringDef(String l, String val) {
		this.label = l;
		this.defaulValue = val;
	}

	public String getLabel() {
		return label;
	}

	public String getDefaulValue() {
		return defaulValue;
	}
	
	public static EnumSet<StringDef> getAll(){
		return EnumSet.allOf(StringDef.class);
	}
}
