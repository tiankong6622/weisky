package org.itboys.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.member.entity.mongo.MemberExt;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.query.Query;
import com.google.common.collect.Maps;

public class MemberExtService extends BaseMemberService<MemberExt, Long> {
	@Autowired
	private MemberService memberService;
	
	@Resource(name="memberDS")
	private MongoDataSource memberExtDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberExtDataSource;
	}

	@Override
	protected Class<MemberExt> getEntityClass() {
		return MemberExt.class;
	}
	/**
	 * 通过memberID查找 
	 * @param memberId
	 * @return
	 */
	public List<MemberExt> getByMemberId(Long memberId){
		return findByField("memberId", memberId);
	}
	/**
	 * 以map的形式获取某个会员的扩展信息
	 * @param memberId
	 * @return
	 */
	public Map<String, String> getMemberExtMap(Long memberId){
		List<MemberExt> list = findByField("memberId", memberId);
		Map<String,String> map = Maps.newHashMapWithExpectedSize(list.size());
		for(MemberExt me:list){
			map.put(me.getKey(), me.getValue());
		}
		return map;
	}
	
	/**
	 * 根据会员id和key得到对应的value
	 */
	public List<MemberExt> findByMemberAndKey(Long memberId,String key){
		return memberExtDataSource.createQuery(getEntityClass()).filter("memberId", memberId).filter("key", key).asList();
	}
	/**
	 * 根据会员id和key删除
	 * @param memberId
	 * @param key
	 */
	public void deleteByMemberIdAndKey(Long memberId,String key){
		Query<MemberExt> query = memberExtDataSource.createQuery(getEntityClass()).filter("memberId", memberId).filter("key", key);
		memberExtDataSource.delete(query);
	}
	public void insert(List<MemberExt> list){
		for (MemberExt memberExt : list)
		{
			update(memberExt);
		}
	}
}
