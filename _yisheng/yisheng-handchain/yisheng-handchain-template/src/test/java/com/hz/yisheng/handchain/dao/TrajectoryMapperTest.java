package com.hz.yisheng.handchain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.handchain.orm.Trajectory;

public class TrajectoryMapperTest extends BaseDAOTest{
	@Autowired
	private TrajectoryMapper trajectoryMapper;
	
	@Test
	public void testList(){
		Map<String,Object> param = new HashMap<String,Object>();
		List<Trajectory> trajectory = trajectoryMapper.list(param);
			System.out.println(trajectory.get(0).getId());
	}
	
	@Test
	public void testInsert(){
		Trajectory trajectory = new Trajectory();
		trajectory.setCustomerId(13l);
		trajectory.setCoordX(11);
		trajectory.setCoordY(11);
		trajectory.setCoordZ(11);
		trajectoryMapper.insert(trajectory);
		System.out.println(trajectory.getId());
	}
}
