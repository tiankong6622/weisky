package org.itboys.mongodb.core;


import com.google.code.morphia.Morphia;
import com.mongodb.DB;

public abstract class BaseMorphiaMapper extends Morphia {
	public abstract void mapEntities(DB db);
}
