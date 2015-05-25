package org.itboys.member.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.itboys.member.entity.mongo.MemberActiveCode;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
@Service
public class MemberActiveCodeService extends BaseMemberService<MemberActiveCode, Long> {
	@Resource(name="memberDS")
	private MongoDataSource macsMongoDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return macsMongoDataSource;
	}

	@Override
	protected Class<MemberActiveCode> getEntityClass() {
		return MemberActiveCode.class;
	}
	/**
	 * 查找会员规定时间之内的验证码
	 * @param memberId
	 * @param seconds
	 * @return
	 */
	public MemberActiveCode getRecentlyActiveCode(Long memberId , int seconds){
		Long time = System.currentTimeMillis()-Long.valueOf(seconds+"");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Query<MemberActiveCode> query = macsMongoDataSource.createQuery(getEntityClass()).filter("memberId", memberId).filter("createTime >=", format.format(time)).order("-createTime");
		if(query.asList().isEmpty()){
			return null;
		}else {
			return query.asList().get(0);
		}
	}
	/**
	 * 根据手机号或邮箱查找规定时间之内的验证码
	 * @param value
	 * @param seconds
	 * @return
	 */
	public List<MemberActiveCode> getRecentlyActiveCodeByValue(String value , int seconds){
		Long timetemp=System.currentTimeMillis();
		Date date=new Date(timetemp);
		Date time =new Date(timetemp-Long.valueOf(seconds*1000));
		Query<MemberActiveCode> query = macsMongoDataSource.createQuery(getEntityClass()).filter("value", value).filter("createTime >=", time).order("-createTime");
		if(query == null){
			return null;
		}else {
			return query.asList();
		}
	}
	
	public void memberActiveCodeSave(MemberActiveCode memberActiveCode){
		memberActiveCode.setMemberId(0l);
		memberActiveCode.setCreateTime(new Date());
		memberActiveCode.setCt(System.currentTimeMillis());
		memberActiveCode.setUt(System.currentTimeMillis());
		save(memberActiveCode);
	}
	
	public void memberActiveCodeUpdate(MemberActiveCode memberActiveCode){
		memberActiveCode.setUt(System.currentTimeMillis());
		update(memberActiveCode);
	}
	public void memberActiveCodeUsed(Long codeId){
		MemberActiveCode memberActiveCode2=getById(codeId);
		memberActiveCode2.setActiveTime(new Date());
	}
}
