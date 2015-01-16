package com.hz.yisheng.nio.dao;

import java.io.Serializable;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.hz.yisheng.nio.conf.NettyRFIDConstant;
import com.hz.yisheng.nio.orm.BabyPosition;
import com.mongodb.Mongo;

public class BabyPositionDAO extends BasicDAO<BabyPosition, Serializable>{

	public BabyPositionDAO(Morphia morphia, Mongo mongo){
		super(BabyPosition.class, mongo, morphia, NettyRFIDConstant.MongoDB);
	}
}
