package org.itboys.member.service;

import org.itboys.member.entity.mongo.MemberGrade;
import org.springframework.stereotype.Service;
/**
 * 会员等级相关业务层
 * @author GuoRui
 */
@Service
public class MemberGradeService extends BaseMemberService<MemberGrade, Long>{

	public void insert(MemberGrade memberGrade){
		insert(memberGrade);
	}
	
	public void update(MemberGrade memberGrade){
		update(memberGrade);
	}
	

	@Override
	protected Class<MemberGrade> getEntityClass()
	{
		// TODO Auto-generated method stub
		return MemberGrade.class;
	}
	
	
	/*
	//等级权限关联(type 权限类型 )
	public void insertGradePermission(final Long gradeId,List<Long> permissionIds){
		//取消等级具备的权限  (权限中包括ui权限和访问权限)
		Map<String, Object> sqlMap = Maps.newHashMapWithExpectedSize(3);
		sqlMap.put("gradeId", gradeId);
		List<Long> permissionid =this.getPermissionIdByGrade(gradeId);
		if(!permissionid.isEmpty()){
			sqlMap.put("relObjectId", permissionid);
			List<GradePermissionRel> lists = gradePermissionRelMapper.findPermission(sqlMap);
			gradePermissionRelMapper.updateDltByGradeAndAccess(lists);
		}
		
		if(gradeId==null || CommonCollectionUtils.isEmpty(permissionIds)){
			return ;
		}
		
	    final List<GradePermissionRel>gradeRels = Lists.transform(permissionIds,new Function<Long, GradePermissionRel>() {
			
			@Override
			public GradePermissionRel apply(Long permissionId) {
				GradePermissionRel gradeRel = new GradePermissionRel();
				gradeRel.setGradeId(gradeId);
				gradeRel.setRelObjectId(permissionId);
				gradeRel.setRelType(permissionBO.findById(permissionId).getType());
				SessionHolder.prepareMemberLoginData(gradeRel);
				return gradeRel;
			}
		});
		gradePermissionRelMapper.batchInsert(gradeRels);
	}
		
	//某个等级所具备的权限id
	private List<Long> getPermissionIdByGrade(Long gradeId){
		Map<String,Object> sqlMap = Maps.newHashMapWithExpectedSize(2);
		sqlMap.put("gradeId", gradeId);
		return gradePermissionRelMapper.getPermissionIdByGrade(sqlMap);
	}
	*/
	
	
}
