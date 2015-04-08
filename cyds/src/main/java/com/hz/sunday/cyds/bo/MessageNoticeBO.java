package com.hz.sunday.cyds.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hz.sunday.commondata.bo.AttachementBO;
import com.hz.sunday.commondata.orm.Attachement;
import com.hz.sunday.cyds.dao.MessageNoticeMapper;
import com.hz.sunday.cyds.orm.MessageNotice;

/**
 * 新闻、通知公告、相关资料
 * @author WeiSky
 *
 */
@Service
@Transactional
public class MessageNoticeBO {

	@Autowired
	private MessageNoticeMapper messageNoticeMapper;
	@Autowired
	private AttachementBO attachementBO;
	
	/**
	 * 新增
	 * @param MessageNotice
	 */
	public void insert(MessageNotice messageNotice){
		messageNoticeMapper.insert(messageNotice);
	}
	
	/**
	 * 编辑
	 * @param MessageNotice
	 */
	public void update(MessageNotice messageNotice){
		messageNoticeMapper.update(messageNotice);
	}
	
	/**
	 * 根id，删除一条信息
	 * @param id
	 */
	public void delete(Long id){
		messageNoticeMapper.delete(id);
	}
	
	/**
	 * 根据条件查询符合条件的信息条数
	 * @param param
	 * @return
	 */
	public Long getCount(Map<String, Object> param){
		return messageNoticeMapper.getCount(param);
	}
	
	/**
	 * 根据条件，查询符合条件的信息，返回的是一个集合
	 * @param param
	 * @return
	 */
	public List<MessageNotice> getList(Map<String, Object> param){
		return messageNoticeMapper.getList(param);
	}
	
	/**
	 * 根户id，获取一条详情
	 * @param id
	 * @return
	 */
	public MessageNotice getSingleDetail(Long id){
		return messageNoticeMapper.getSingleDetail(id);
	}
	
	/**
	 * 根据信息类型获取
	 * @param mtype
	 * @return
	 */
	public List<MessageNotice> getByMtype(int mtype){
		List<MessageNotice> list = messageNoticeMapper.getByMtype(mtype);
		//加载新闻的标题图片   只限一张  并且是最后一张
		for(MessageNotice mn : list){
			String mtypeFile = "messnoti" + mn.getMtype();
			List<Attachement> attachList = attachementBO.findBy(String.valueOf(mn.getId()), mtypeFile);
			if(attachList != null && attachList.size() > 0){
				mn.setFilePath(attachList.get(attachList.size() - 1).getPath());
			}
		}
		return list;
	}
	
	/**
	 * 根据信息类型，取一定条数的信息
	 * @param mtype
	 * @param limit
	 * @return
	 */
	public List<MessageNotice> getByMtypeLimit(int mtype, int limit){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(3);
		param.put("mtype", mtype);
		param.put("rowStart", 0);
		param.put("pageSize", limit);
		List<MessageNotice> list = getList(param);
		//加载新闻的标题图片   只限一张  并且是最后一张
		for(MessageNotice mn : list){
			String mtypeFile = "messnoti" + mn.getMtype();
			List<Attachement> attachList = attachementBO.findBy(String.valueOf(mn.getId()), mtypeFile);
			if(attachList != null && attachList.size() > 0){
				mn.setFilePath(attachList.get(attachList.size() - 1).getPath());
			}
		}
		return list;
	}
}
