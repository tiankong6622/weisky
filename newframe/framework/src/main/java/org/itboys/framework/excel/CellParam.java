package org.itboys.framework.excel;

/**
 * excel 某一个单元格的描述
 * @author Chenjunhui
 *
 */
public class CellParam {

	private int x;//x下标
	private int y;//y轴下标
	private String field;//反射属性
	
	public CellParam(){
		
	}
	
	public CellParam(int x,int y,String field){
		this.x=x;
		this.y=y;
		this.field=field;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	
}
