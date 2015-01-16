package org.javafans.common.dto;

import java.util.List;

public class EasyUiTreeHelper {

	public static boolean isAllChecked(List<EasyUiTreeDO> tree){
		for(EasyUiTreeDO t:tree){
			if(!t.isChecked()){
				return false;
			}
		}
		return true;
	}
}
