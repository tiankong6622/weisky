package org.itboys.framework.spring.editor;

public interface EditorHolder {
    
	  public static  DateEditor  dateEditor = new DateEditor();
	  public static  IntegerEditor  integerEditor = new IntegerEditor();
	  public static  LongEditor  longEditor = new LongEditor();
	  public static  BigDecimalEditor  bigDecimalEditor = new BigDecimalEditor();
	
}
