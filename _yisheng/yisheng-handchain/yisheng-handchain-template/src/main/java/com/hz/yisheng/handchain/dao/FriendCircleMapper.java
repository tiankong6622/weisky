package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hz.yisheng.handchain.orm.AttachmentBean;
import com.hz.yisheng.handchain.orm.DiscussInfoBean;
import com.hz.yisheng.handchain.orm.friendCircleBean;


public interface FriendCircleMapper {
	/**
	 * 查询朋友圈信息
	 */
	public List<friendCircleBean> findFriendList(Map<String,Object> queryParam);
	//查询记录数
	public Long findCount(Map<String,Object> queryParam);
	//通过id查找记录
	public friendCircleBean findFriendById(Long id);
	//添加记录
	public void insertFriend(friendCircleBean friendCircle);
	//更新记录
	public void updateFriend(friendCircleBean friendCircle);
	//删除记录
	public void deleteFriend(Long id);
	//添加评论记录
	public void insertDiscuss(DiscussInfoBean discussInfo);
	//通过friendid查询所有评论记录
	public List<DiscussInfoBean> findDiscussById(@Param("friendId") Long friendId,@Param("mark") Integer mark);
	//通过friendid查询评论条数、点赞条数
	public Long findCountByFriendId(@Param("friendId") Long friendId,@Param("mark") Integer mark);
	//通过friendid删除评论条数、点赞条数
	public void deleteDiscussByFriendId(@Param("friendId") Long friendId);
	/**
	 * 新增通知公告中的附件列表
	 * */
	public void insertAttachement(List<AttachmentBean> attachlist);

	/**
	 * 删除一条附件
	 */
	public void deleteAttachmentById(Long id);

	/**
	 * 根据obj_id获取所有附件
	 */
	public List<AttachmentBean> getAttachmentByobjId(Long objId);
	/**
	 * 根据objid和path唯一确定图片
	 */
	public AttachmentBean getAttachmentByobjIdAndPath(@Param("objId") String objId,
			@Param("path") String path);
	
	/**
	 * 根据objid和type确定图片
	 */
	public List<AttachmentBean> getAttachmentByobjIdAndType(@Param("objId") Long objId,
			@Param("type") String type);

	/**
	 * 根据obj_ids列表获得所有附件
	 */
	public List<AttachmentBean> getAttachmentByobjIdList(List<Long> objids);

	/**
	 * 根据objid和文件存储路径删除附件
	 */
	public void deleteAttachmentByobjIdAndpath(@Param("objId") String objId,
			@Param("path") String path);
	/**
	 * 根据objid和type删除列表
	 */
	public void deleteAttachmentByobjIdAndType(@Param("objId") Long objId,
			@Param("type") String type);
	/**
	 * 删除记录及其附件
	 */
	public void deleteAll();
	
	
}
