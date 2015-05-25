package org.itboys.admin.tools;

import java.util.List;

import org.itboys.admin.dto.EasyUiTreeDO;

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
