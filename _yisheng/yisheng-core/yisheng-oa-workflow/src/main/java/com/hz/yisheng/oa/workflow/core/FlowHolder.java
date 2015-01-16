package com.hz.yisheng.oa.workflow.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.javafans.json.JsonUtils;

import com.hz.yisheng.oa.workflow.dto.FlowDTO;
import com.hz.yisheng.oa.workflow.orm.FlowDefine;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.FlowTransition;

/**
 * Flow 处理类
 * @author WeiSky
 *
 */
public class FlowHolder {
	
	/**
	 * 解析json模板 让其转化成流程定义对象,流程节点对象 流程线
	 * @param json
	 * @return
	 */
	public static FlowDTO jsonTemplate2FlowDTO(String json){
		return FlowHolder.jsonTemplate2FlowDTO(json, 0L);
	}

	/**
	 * 解析json模板 让其转化成流程定义对象,流程节点对象 流程线
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static FlowDTO jsonTemplate2FlowDTO(String json,Long projectId){
		FlowDTO dto = new FlowDTO();
		Map<String,Object> jsonMap = JsonUtils.jsonToObject(HashMap.class, json);
		String name = (String) jsonMap.get("name");
		String flowname = (String)jsonMap.get("flowname");
		FlowDefine define = new FlowDefine();
		List<FlowNode> nodes = new ArrayList<FlowNode>();
		define.setName(name);
		define.setFlowname(flowname);
		define.setProjectId(projectId);
		dto.setDefine(define);
		Map<String,Map<String,Object>> nodeMap = (Map<String, Map<String,Object>>) jsonMap.get("nodes");
		Set<Entry<String, Map<String, Object>>>  nodeSet = nodeMap.entrySet();
		for(Entry<String, Map<String,Object>> entry:nodeSet){
			FlowNode node = new FlowNode();
			String key = entry.getKey();
			Map<String,Object> value = entry.getValue();
		    node.setType((String)value.get("type"));
		    node.setName((String) value.get("name"));
		    node.setBeanName((String) value.get("beanName"));
		    node.setDealUrl((String) value.get("dealUrl"));
		    node.setCode(key);
		    nodes.add(node);
		}
		dto.setNodes(nodes);
		
		List<FlowTransition> transitions = new ArrayList<FlowTransition>();
		Map<String,Map<String,Object>> lineMap = (Map<String, Map<String,Object>>) jsonMap.get("lines");
		Set<Entry<String, Map<String, Object>>>  lineSet = lineMap.entrySet();
		for(Entry<String, Map<String,Object>> entry:lineSet){
			Map<String,Object> value = entry.getValue();
			FlowTransition  transition = new FlowTransition();
			transition.setFrom((String)value.get("from"));
			transition.setTo((String)value.get("to"));
			transition.setName((String)value.get("name"));
			transition.setDealname((String)value.get("dealname"));
			transition.setCode(entry.getKey());
			transitions.add(transition);
		}
		dto.setTransitions(transitions);
		return dto;
	}
	
	/**
	 * 针对自定义流程
	 * 解析json模板 让其转化成流程定义对象,流程节点对象 流程线
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static FlowDTO jsonTemplate2FlowDTOCustomize(String json){
		FlowDTO dto = new FlowDTO();
		Map<String,Object> jsonMap = JsonUtils.jsonToObject(HashMap.class, json);
		String name = (String) jsonMap.get("name");
		String flowname = (String)jsonMap.get("flowname");
		String flowTypeId = (String)jsonMap.get("flowtypeid");
		FlowDefine define = new FlowDefine();
		List<FlowNode> nodes = new ArrayList<FlowNode>();
		define.setName(name);
		define.setFlowname(flowname);
		define.setFlowTypeId(Long.parseLong(flowTypeId));
		dto.setDefine(define);
		
		String dealUrl = (String)jsonMap.get("dealUrl");
		Map<String,Map<String,Object>> nodeMap = (Map<String, Map<String,Object>>) jsonMap.get("nodes");
		Set<Entry<String, Map<String, Object>>>  nodeSet = nodeMap.entrySet();
		for(Entry<String, Map<String,Object>> entry:nodeSet){
			FlowNode node = new FlowNode();
			String key = entry.getKey();
			Map<String,Object> value = entry.getValue();
			node.setType((String)value.get("type"));
			node.setName((String) value.get("name"));
			node.setBeanName((String) value.get("beanName"));
			node.setDealUrl(dealUrl);
			node.setCode(key);
			nodes.add(node);
		}
		dto.setNodes(nodes);
		
		List<FlowTransition> transitions = new ArrayList<FlowTransition>();
		Map<String,Map<String,Object>> lineMap = (Map<String, Map<String,Object>>) jsonMap.get("lines");
		Set<Entry<String, Map<String, Object>>>  lineSet = lineMap.entrySet();
		for(Entry<String, Map<String,Object>> entry:lineSet){
			Map<String,Object> value = entry.getValue();
			FlowTransition  transition = new FlowTransition();
			transition.setFrom((String)value.get("from"));
			transition.setTo((String)value.get("to"));
			transition.setName((String)value.get("name"));
			
			String dealname = (String)value.get("name");
			if(dealname != null && dealname.length() >= 4){
				transition.setDealname(dealname.substring(dealname.length()-4, dealname.length()));
			}else{
				transition.setDealname(dealname);
			}
			transition.setCode(entry.getKey());
			transitions.add(transition);
		}
		dto.setTransitions(transitions);
		return dto;
	}
	
}
