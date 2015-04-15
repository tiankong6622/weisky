package com.hz.sunday.xccf.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.sunday.xccf.dao.TicketMemberMapper;
import com.hz.sunday.xccf.orm.TicketMemberBean;

/**
 * 报名管理
 * 
 * @author huanglei
 * @date 2015年4月14日
 * @version V1.0
 */
@Service
public class TicketMemberBO {

	@Autowired
	private TicketMemberMapper ticketMemberMapper;

	/**
	 * 分页查询数据
	 */
	public Page<TicketMemberBean> pageQuery(Page<TicketMemberBean> page,
			final Map<String, Object> queryMap) {
		return PageQueryUtils.pageQuery(page, queryMap,
				new PageQuery<TicketMemberBean>() {

					@Override
					public long count() {
						return ticketMemberMapper.findCount(queryMap);
					}

					@Override
					public List<TicketMemberBean> list() {
						return ticketMemberMapper.findAll(queryMap);
					}

				});
	}

	/**
	 * 根据ID，获取报名信息
	 */
	public TicketMemberBean findById(Long id) {
		return ticketMemberMapper.findById(id);
	}

	/**
	 * 新增报名信息
	 * 
	 * @param ticket
	 */
	public void insert(TicketMemberBean ticket) {
		ticketMemberMapper.insert(ticket);
	}

	/**
	 * 更新报名信息
	 * 
	 * @param ticket
	 */
	public void update(TicketMemberBean ticket) {
		ticketMemberMapper.update(ticket);

	}

	/**
	 * 根据ID，删除报名信息
	 * 
	 * @param id
	 */
	public void deleteById(Long id) {
		ticketMemberMapper.deleteById(id);
	}

}
