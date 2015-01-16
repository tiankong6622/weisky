package com.hz.yisheng.commondata.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.constants.CommonConstants;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hz.yisheng.webdata.SessionHolder;
import com.hz.yisheng.commondata.annotation.AdminPermissionCheck;
import com.hz.yisheng.commondata.annotation.ProjectPermissionCheck;
import com.hz.yisheng.commondata.bo.TreeCodeBO;
import com.hz.yisheng.commondata.dto.TreeCodesDTO;
import com.hz.yisheng.commondata.orm.TreeCode;

/**
 * 分类控制器
 */
@Controller
@RequestMapping(value = "/tree/code")
public class TreeCodeController extends BaseController {

	@Autowired
	private TreeCodeBO treeCodeBO;	
	
	@RequestMapping("/vm")
	public String projectMenus(HttpServletRequest request,Model model){
		return "/treecode/list";
	}
	
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		List<TreeCode> menus = treeCodeBO.getAllCategorys(0L);
		JsonPageUtils.renderJsonPage(menus!=null&&menus.size()>0?menus.size():0L, menus, response);
	}
	
	@RequestMapping(value = "/listByProject")
	public void listByProject(HttpServletRequest request,HttpServletResponse response){
		Long projectId = SessionHolder.getProjectId();
		List<TreeCode> menus = treeCodeBO.getAllCategorys(projectId);
		JsonPageUtils.renderJsonPage(menus!=null&&menus.size()>0?menus.size():0L, menus, response);
	}
	
	/**
	 * 加载分类tree
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/tree")
	public void tree(HttpServletRequest request,HttpServletResponse response){
		List<TreeCode> all = treeCodeBO.getAllCategorys(0L);
		prepareCodeTree(response, all);
	}
	
	/**
	 * 加载项目分类tree
	 * @author Guorui
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/projectTree")
	public void projectTree(HttpServletRequest request,HttpServletResponse response){
		Long projectId = SessionHolder.getProjectId();
		List<TreeCode> all = treeCodeBO.getAllCategorys(projectId);
		prepareCodeTree(response, all);
	}
	

	private void prepareCodeTree(HttpServletResponse response,List<TreeCode> all) {
		List<TreeCode> roots = treeCodeBO.getRootCategorys(all);
		List<TreeCodesDTO> tree = Lists.newArrayListWithCapacity(roots.size());
		for(TreeCode item:roots){
			TreeCodesDTO menuTree = new TreeCodesDTO();
			menuTree.setId(item.getId());
			menuTree.setType(item.getType());
			menuTree.setKey(item.getKey());
			menuTree.setValue(item.getValue());
			menuTree.setParentId(item.getParentId());
			menuTree.setRoot(true);
			tree.add(menuTree);
		}
		prepareChildren(all, tree);
		AjaxUtils.renderJson(response, tree);
	}
	
	/**
	 * 针对分类比较少的情况下 可以直接一次性load出来 因为数据库load出来的已经按fullidpath拍好序了 所以这样递归时间复杂度是O(n)
	 * @param itemList
	 * @param tree
	 */
	private void prepareChildren(List<TreeCode> itemList,List<TreeCodesDTO> tree) {
		for(TreeCodesDTO uitree:tree){
			for(int i=0,size=itemList.size();i<size;i++){
				TreeCode citem = itemList.get(i);
				if(citem.getParentId().equals(uitree.getId())){
					TreeCodesDTO menuTree = new TreeCodesDTO();
					menuTree.setId(citem.getId());
					menuTree.setType(citem.getType());
					menuTree.setKey(citem.getKey());
					menuTree.setValue(citem.getValue());
					menuTree.setRoot(false);
					menuTree.setParentId(citem.getParentId());
					uitree.getChildren().add(menuTree);
					if(i+1<size && itemList.get(i+1).getParentId().equals(citem.getId())){
						prepareChildren(itemList, uitree.getChildren());//递归
					}
				}
			}
		}
	}
	
	
	@RequestMapping("/getTreeCode/{id}")
	public void getUser(@PathVariable("id") Long id,HttpServletResponse response){
		TreeCode entity = treeCodeBO.getTreeCode(id);
		AjaxUtils.renderJson(response, entity);
	}
	
	
	@RequestMapping("/getChilds/{parentId}")
	public void getChilds(@PathVariable("parentId") Long parentId,HttpServletResponse response){
		List<TreeCode> list = treeCodeBO.getChilidsByParentid(parentId);
		AjaxUtils.renderJson(response, list);
	}
	
	/**
	 * 异步保存
	 */
	@AdminPermissionCheck("treeCode:save")
	@RequestMapping("/save")
	public void save(@ModelAttribute TreeCode entity,HttpServletResponse response){
		try {
			if(entity.getProjectId()==null){
				entity.setProjectId(0L);
			}
			if(entity.getId()==null){
				treeCodeBO.insert(entity);
			}else{
				treeCodeBO.update(entity);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("save or update treeCode fail",e);
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/**
	 * 异步保存项目分类
	 */
	@ProjectPermissionCheck("productCategory:saveByProject")
	@RequestMapping("/saveByProject")
	public void saveByProject(@ModelAttribute TreeCode entity,HttpServletResponse response){
		Long projectId = SessionHolder.getProjectId();
		entity.setProjectId(projectId);
		save(entity, response);
	}
	
	/**
	 * 异步删除
	 */
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			Map<String ,Object> map=Maps.newConcurrentMap();
			map.put("parentId", id);
			List<TreeCode>  treeCodes= treeCodeBO.list(map);
			if(treeCodes.size()>0 ){
				AjaxUtils.renderText(response, "category");	
				return;
			}else{
				treeCodeBO.delete(id);	
				AjaxUtils.renderText(response, CommonConstants.SUCCESS);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("delete treeCode fail",e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
}
