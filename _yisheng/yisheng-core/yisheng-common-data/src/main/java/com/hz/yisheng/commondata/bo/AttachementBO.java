package com.hz.yisheng.commondata.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.javafans.common.utils.io.FileUtils;
import org.javafans.io.FileManager;
import org.javafans.resources.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hz.yisheng.commondata.dao.AttachementMapper;
import com.hz.yisheng.commondata.orm.Attachement;

/**
 * 通用附件管理
 * @author ChenJunhui
 *
 */
@Service
public class AttachementBO {

	@Autowired
    private 	AttachementMapper attachementMapper;
	
	/**
	 * 根据关联的ID 和关联对象类型查找其附件
	 * @param objId
	 * @param type
	 * @return
	 */
	public List<Attachement> findBy(String objId,String type){
		return attachementMapper.findBy(objId, type);
	}
	
	/**
	 * 根据关联的ID 和关联对象类型查找其附件
	 * @param objId
	 * @param type
	 * @return
	 */
	public List<Attachement> findBy(Long objId,String type){
		return attachementMapper.findBy(objId.toString(), type);
	}
	
	public Attachement findAttachementBy(String objId,String type){
		List<Attachement> list = attachementMapper.findBy(objId, type);
		return list.size()>0?list.get(0):null;
	}
	
	public Attachement findAttachementBy(Long objId,String type){
		return this.findAttachementBy(objId.toString(), type);
	}
	
	/**
	 * 批量插入附件
	 * @param list
	 */
	public void batchInsert(List<Attachement> list){
		attachementMapper.batchInsert(list);
	}
	
	/**
	 * 批量插入附件  返回ID
	 */
	public void batchIn(List<Attachement> list){
		for(Attachement att : list){
			attachementMapper.insert(att);
		}
	}
	
	/**
	 * 单个插入附件
	 * @param list
	 */
	public void insert(Attachement attachement){
		List<Attachement> list = new ArrayList<Attachement>(1);
		list.add(attachement);
		attachementMapper.batchInsert(list);
	}
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	public Attachement getById(long id){
		return attachementMapper.getById(id);
	}
	
	/**
	 * 根据关联的ID 和关联对象类型删除其所有附件
	 * @param objId
	 * @param type
	 */
	public int deleteAll(String objId,String type){
		int result = attachementMapper.deleteAll(objId, type);
		List<Attachement> list = attachementMapper.findBy(objId, type);
		for(Attachement ac:list){
			FileManager.deleteFile(ac.getPath());
		}
		return result;
	}
	
	
	/**
	 * 根据ObjId和Type去删除，type可以传空
	 * */
	public void deleteByObjIdAndType(Attachement att){
		 attachementMapper.deleteByObjIdAndType(att);
	}
	/**
	 * 根据ID删除
	 * @param id
	 */
	public int delete(long id){
		int result = attachementMapper.delete(id);
		Attachement a = attachementMapper.getById(id);
		if(a!=null){
			FileManager.deleteFile(a.getPath());
		}
		return result;
	}
	
	/**
	 * 上传并组装附件信息
	 * @param name
	 * @param fileItem
	 * @return
	 */
	public Attachement prepareAttachement(String name,FileItem fileItem){
		try{
			if(fileItem==null || fileItem.getSize()<=0L){
				return null;
			}
			Attachement att = new Attachement();
			att.setName(name);
			att.setContentType(fileItem.getContentType());
			att.setFileName(fileItem.getName());
			att.setSize(fileItem.getSize());
			String filePath = FileUtils.saveFile(fileItem.getInputStream(), ResourceConfig.getSysConfig("fileUploadPath"), fileItem.getName(), fileItem.getContentType());
			att.setPath(filePath);
			return att;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 上传并组装附件信息
	 * @param fileItem
	 * @return
	 */
	public Attachement prepareAttachement(FileItem fileItem){
		return prepareAttachement(StringUtils.EMPTY, fileItem);
	}
	
	/**
	 * 上传并组装附件信息
	 * @param name
	 * @param fileItem
	 * @return
	 */
	public List<Attachement>  prepareAttachement(String[] name,CommonsMultipartFile[] files){
		List<Attachement> attahs = new ArrayList<Attachement>(files.length);
		int i=0;
		for(CommonsMultipartFile cmf:files){
			if(!cmf.isEmpty()){
				attahs.add(prepareAttachement(name[i], cmf.getFileItem()));
			}
			i++;
		}
		return attahs;
	}
	
	/**
	 * 上传并组装附件信息
	 * @param name
	 * @param fileItem
	 * @return
	 */
	public List<Attachement>  prepareAttachement(CommonsMultipartFile... files){
		List<Attachement> attahs = new ArrayList<Attachement>(files.length);
		for(CommonsMultipartFile cmf:files){
			if(!cmf.isEmpty()){
				attahs.add(prepareAttachement(StringUtils.EMPTY, cmf.getFileItem()));
			}
		}
		return attahs;
	}
	
	public List<Attachement> getByIds(List<Long> ids){
		return attachementMapper.getByIds(ids);
	}
	
	public List<Attachement> list(Map<String,Object> params){
		
		List<Attachement> list = attachementMapper.list(params);
		if(list != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(Attachement a : list){
				try {
					a.setCreateTime2(sdf.format(a.getCreateTime()));
				} catch (Exception e) {
					e.printStackTrace();
					a.setCreateTime2("");
				}
			}
		}
		return list;
	}
	
	public void update(Attachement att){
		attachementMapper.update(att);
	}
}
