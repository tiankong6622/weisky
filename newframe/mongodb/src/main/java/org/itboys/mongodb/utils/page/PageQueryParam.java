package org.itboys.mongodb.utils.page;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 后台分页参数
 * @author WeiSky
 * @param <T>
 *
 */
public class PageQueryParam<T> {
	
	/* 公共变量  */
	public final String ASC = "asc";
	public final String DESC = "desc";
	
	/* 分页参数  */
	/**
	 * 开始位置
	 */
	private Integer pageIndex = 0;
	/**
	 * 偏移量
	 * 默认每页显示20条
	 */
	private Integer pageSize = 20;
	/**
	 * 按字段排序名,默认按ID升序
	 * 若需要按多个字段排序，则格式为“"xxx1,xxx2"
	 * 根据sortOrder字段，判断是升序，还是降序，若为降序则格式为："xxx1,-xxx2",-xxx2表示降序
	 */
	private String sortField = "id";
	/**
	 * 排序类型，升序还是降序
	 * 默认采用降序
	 */
	private String sortOrder = DESC;
	

	private Integer rowstart = 0;
	
	/* 返回结果   */
	protected List<T> result =  Lists.newArrayList();
	protected long totalCount = -1;
	
	/* 构造函数  */
	public PageQueryParam(){}
	
	public PageQueryParam(Integer pageIndex, Integer pageSize){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	
	public PageQueryParam(Integer pageIndex, Integer pageSize, String sortField, String sortOrder){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}
	
	public Integer getPageIndex() {
		return this.pageIndex ;
	}
	
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex ;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 设置排序类型
	 * @param sortField
	 */
	public String getSortField() {
		String lowcaseOrder = StringUtils.lowerCase(getSortOrder());
		if(DESC.equals(lowcaseOrder)){
			sortField = "-" + sortField;
		}
		return sortField;
	}
	
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getRowstart() {
		return (this.pageIndex-1)*this.pageSize;
	}

	public void setRowstart(Integer rowstart) {
		this.rowstart = rowstart;
	}
 
	
}
