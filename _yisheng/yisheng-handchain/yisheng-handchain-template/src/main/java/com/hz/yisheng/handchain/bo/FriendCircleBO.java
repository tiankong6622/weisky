package com.hz.yisheng.handchain.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.handchain.dao.FriendCircleMapper;
import com.hz.yisheng.handchain.orm.AttachmentBean;
import com.hz.yisheng.handchain.orm.DiscussInfoBean;
import com.hz.yisheng.handchain.orm.friendCircleBean;
/**
 *  朋友圈相关操作
 * @author ­­­­­­­­­­
 *
 */
@Service
@Transactional
public class FriendCircleBO {
	@Autowired
	private FriendCircleMapper friendCircleMapper;

	/**
	 * 分页查询
	 */
	public Page<friendCircleBean> pageQuery(Page<friendCircleBean> page,
			final Map<String, Object> queryMap) {
		return PageQueryUtils.pageQuery(page, queryMap,
				new PageQuery<friendCircleBean>() {
					@Override
					public List<friendCircleBean> list() {
						// TODO Auto-generated method stub
						return friendCircleMapper.findFriendList(queryMap);
					}

					@Override
					public long count() {
						// TODO Auto-generated method stub
						return friendCircleMapper.findCount(queryMap);
					}
				});
	}

	// 通过id查找记录
	public friendCircleBean findFriendById(Long id){
		friendCircleBean friendcircle = null;
		try{
			friendcircle = friendCircleMapper.findFriendById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return friendcircle;
	}
	// 添加记录
	public void insertFriend(friendCircleBean friendCircle) {
		try {
			friendCircleMapper.insertFriend(friendCircle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 更新记录
	public void updateFriend(friendCircleBean friendCircle) {
		try {
			friendCircleMapper.updateFriend(friendCircle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除记录
	public void deleteFriend(Long id) {
		try {
			friendCircleMapper.deleteFriend(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加评论记录
	public void insertDiscuss(DiscussInfoBean discussInfo) {
		try {
			friendCircleMapper.insertDiscuss(discussInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据friendId查询所有的评论记录
	public List<DiscussInfoBean> findDiscussById(Long friendId,Integer mark) {
		List<DiscussInfoBean> discussInfo = null;
		try {
			discussInfo = friendCircleMapper.findDiscussById(friendId,mark);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return discussInfo;
	}
	//根据friendId查询评论条数、点赞条数
	public Long findCountByFriendId(Long friendId,Integer mark){
		return friendCircleMapper.findCountByFriendId(friendId,mark);
	}
	//根据friendId删除评论条数、点赞条数
	public void deleteDiscussByFriendId(Long friendId){
		friendCircleMapper.deleteDiscussByFriendId(friendId);
	}
	/**
	 * 附件业务逻辑处理 新增、查询、删除
	 */
	// 新增附件列表
	public void insertAttachement(List<AttachmentBean> attachlist) {
		friendCircleMapper.insertAttachement(attachlist);
	}

	// 删除一条好人好事记录，连同附件一起删除
	public void deleteAll() {
		friendCircleMapper.deleteAll();
	}

	// 删除一条附件记录
	public void deleteAttachmentById(Long id) {
		friendCircleMapper.deleteAttachmentById(id);
	}
	//依据objid和type删除附件记录
	public void deleteAttachmentByobjIdAndType(Long objId,String type){
		friendCircleMapper.deleteAttachmentByobjIdAndType(objId, type);
	}

	// 根据objid获取所有附件记录
	public List<AttachmentBean> getAttachmentByobjId(Long objId) {
		return friendCircleMapper.getAttachmentByobjId(objId);
	}

	// 根据objid列表获取所有附件
	public List<AttachmentBean> getAttachmentByobjIdList(List<Long> objIds) {
		return friendCircleMapper.getAttachmentByobjIdList(objIds);
	}

	// 根据objid和path删除附件
	public void deleteAttachmentByobjIdAndpath(String objId, String path) {
		friendCircleMapper.deleteAttachmentByobjIdAndpath(objId, path);
	}
	
	//根据objid和path唯一确定图片
public AttachmentBean getAttachmentByobjIdAndPath(String objId,String path){
	return friendCircleMapper.getAttachmentByobjIdAndPath(objId, path);
}
//根据objid和type确定图片
public List<AttachmentBean> getAttachmentByobjIdAndType(Long objId,String type){
	return friendCircleMapper.getAttachmentByobjIdAndType(objId, type);
	
}

}
