package com.hz.yisheng.commondata.bo;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.javafans.common.utils.io.ImageWatermark;
import org.javafans.resources.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.yisheng.commondata.dao.WatermarkMapper;
import com.hz.yisheng.commondata.orm.Watermark;
/**
 * service - 水印
 * @author lenovo
 */
@Service
public class WatermarkBO {

	public static final String IMG_PATH=ResourceConfig.getSysConfig("fileUploadPath");
	
	@Autowired
	private WatermarkMapper watermarkMapper;
	
	@Autowired
	private KVConfigBO kVConfigBO;
	
	/**
	 * 根据配置项处理水印图片
	 * @param waterMark KV_CONFIG表里使用哪个水印ID的配置
	 * @param filePath 对哪个物理路径的图片的处理
	 */
	public void prepareWaterMark(String waterMark, String filePath) {
		if(StringUtils.isNotBlank(waterMark)){
			String	value=kVConfigBO.getConfigValue(waterMark);
			if(NumberUtils.isDigits(value)){
				long waterMarkId = Long.parseLong(value);
				Watermark wm = getById(waterMarkId);
				if(wm!=null&&wm.getStatus()==1){
					if(wm.getType()==Watermark.TYPE_IMG){
						ImageWatermark.pressImage(IMG_PATH+filePath, IMG_PATH+wm.getWatermark(), wm.getX(), wm.getY(),wm.getAlpha().floatValue(), wm.getFlag());
					}else{
						String[] colors = StringUtils.split(StringUtils.trim(wm.getColor()),"_");
						ImageWatermark.pressText(IMG_PATH+ filePath,  IMG_PATH+wm.getWatermark(), wm.getFontName(), wm.getFontStyle(), wm.getFontSize(), 
								new Color(Integer.parseInt(colors[0]),Integer.parseInt(colors[1]),Integer.parseInt(colors[2])),
								wm.getX(), wm.getY(),wm.getAlpha().floatValue(), wm.getFlag());
					}
				}
			}
		}
	}
	
	public List<Watermark>list(Map<String,Object>sqlMap){
		return watermarkMapper.list(sqlMap);
	}
	
	
	/**
	 * 添加水印
	 */
	public void insert(Watermark watermark){
		watermarkMapper.insert(watermark);
	}
	/**
	 * 修改
	 */
	public void update(Watermark watermark){
		watermarkMapper.update(watermark);
	}
	/**
	 * 删除
	 */
	public int delete(Long id){
		return watermarkMapper.delete(id);
	}
	/**
	 * 根据id获取水印信息
	 */
	public Watermark getById(Long id){
		return watermarkMapper.getById(id);
	}
	
	/**
	 * 获取所有的水印信息
	 * @param projectId
	 * @return
	 */
	public List<Watermark>getWatermarkList(){
		return watermarkMapper.getWatermarkList();
	}
	
	public Watermark getWatermark(Long projectId){
		return watermarkMapper.getWatermark(projectId);
	}
	
	public Watermark getWatermark(Long projectId,Long objectId){
		return null;
	}
	
}
