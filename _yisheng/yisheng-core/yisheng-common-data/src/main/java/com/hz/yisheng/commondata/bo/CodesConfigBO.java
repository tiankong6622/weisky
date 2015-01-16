package com.hz.yisheng.commondata.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hz.yisheng.commondata.dao.CodesConfigMapper;
import com.hz.yisheng.commondata.dao.CodesMapper;
import com.hz.yisheng.commondata.dto.CodesDTO;
import com.hz.yisheng.commondata.orm.Codes;
import com.hz.yisheng.commondata.orm.CodesConfig;

@Service
public class CodesConfigBO {

	@Autowired
	private CodesConfigMapper codesConfigMapper;
	@Autowired
	private CodesMapper codesMapper;
	
	/**
	 * 
	 * @param projectId
	 * @return
	 */
	public List<CodesDTO> getTree(long projectId){
		List<CodesConfig> list = getAll(projectId);

		Map<String, Object> sqlMap = new HashMap<String, Object>(1);
		sqlMap.put("relObjectId", projectId);
		List<Codes> codes = codesMapper.list(sqlMap);
		List<CodesDTO> tree = Lists.newArrayListWithCapacity(list.size());
		long i=0;
		for(Codes c:codes){
			if(c.getId()>i){
				i=c.getId();
			}
		}
		i++;//那个表没有ID 先用这个
		for(CodesConfig cc:list){
			CodesDTO dto = new CodesDTO();
			dto.setId(i);
			dto.setType(cc.getType());
			dto.setValue(cc.getName());
			dto.setRoot(true);
			tree.add(dto);
			i++;
		}
		List<CodesDTO> tree2 = Lists.newArrayListWithCapacity(list.size());
		for(Codes code:codes){
			CodesDTO dto = new CodesDTO();
			dto.setId(code.getId());
			dto.setType(code.getType());
			dto.setKey(code.getKey());
			dto.setValue(code.getValue());
		   for(CodesDTO cd:tree){
			   if(cd.getType().equals(code.getType())){
				   dto.setParentId(cd.getId());
				   tree2.add(dto);
				   break;
			   }
		   }
		}
		tree.addAll(tree2);
		return tree;
	}
	
	public List<CodesConfig> getAll(long projectId){
		return codesConfigMapper.getAll(projectId);
	}
	
	public void insert(CodesConfig codesConfig){
		codesConfigMapper.insert(codesConfig);
	}
	
	public int update(CodesConfig codesConfig){
		return codesConfigMapper.update(codesConfig);
	}
	
	public CodesConfig getByProjectAndType(long projectId, String type){
		return codesConfigMapper.getByProjectAndType(projectId, type);
	}
	
}
