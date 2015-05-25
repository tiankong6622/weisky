package org.itboys.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.member.entity.mongo.MemberFavorite;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.google.common.collect.Maps;

@Service
public class MemberFavoriteService extends BaseMemberService<MemberFavorite, Long> {
	@Resource(name="memberDS")
	private MongoDataSource memberFavoriteDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberFavoriteDataSource;
	}

	@Override
	protected Class<MemberFavorite> getEntityClass() {
		return MemberFavorite.class;
	}
	/**
	 * 根据会员id查找
	 * @param memberId
	 * @return
	 */
	public List<MemberFavorite> getByMemberId(Long memberId){
		
		return findByField("memberId", memberId);
	}
	public List<MemberFavorite> getByMemberIdAndType(Long memberId,Integer type){
		Map<String, Object>param=Maps.newHashMapWithExpectedSize(2);
		param.put("memberId", memberId);
		param.put("type", type);
		return list(param);
	}
	public void doSave(MemberFavorite memberFavorite){
		Query<MemberFavorite> query = memberFavoriteDataSource.createQuery(getEntityClass()).filter("memberId", memberFavorite.getMemberId())
				.filter("objectId", memberFavorite.getObjectId()).filter("type", memberFavorite.getType());
		if(query != null && query.asList().size() > 0){
			update(query.asList().get(0));
		}else {
			memberFavorite.setUt(System.currentTimeMillis());
			save(memberFavorite);
		}
	}
	/**
	 * 通过memberId删除
	 * @param memberId
	 */
	public void deleteByMemberId(Long memberId){
		Query<MemberFavorite> query = memberFavoriteDataSource.createQuery(getEntityClass()).filter("memberId", memberId);
		memberFavoriteDataSource.delete(query);
	}
	
	/**
	 * 检查一个用户是否已经收藏过某条信息
	 * 
	 * @param memberId
	 * @param infoId
	 * @param type
	 * @return
	 */
	public boolean checkIsFavorites(Long memberId, Long infoId, Integer type) {
		Query<MemberFavorite> query = memberFavoriteDataSource.createQuery(getEntityClass()).filter("memberId", memberId)
				.filter("objectId", infoId).filter("type", type);
		return query.asList().size() > 0;
	}

}
