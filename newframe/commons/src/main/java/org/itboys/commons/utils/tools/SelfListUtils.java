package org.itboys.commons.utils.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
/**
 * 
 * @author Tangjc
 *
 */
public class SelfListUtils
{
	public static <T, E> List<E> getFiledAsList(List<T> list, String name)
	{
		try
		{
			List<Object>  result = new ArrayList<Object>();
			// if(o instanceof java.util.List){
			for (Object object : list)
			{
				Object value = PropertyUtils.getProperty(object, name);
				result.add(value);
			}
			return (List<E>) result;
		} catch (Exception e)
		{
			e.printStackTrace();

		}
		return null;

	}
	public static <T, E> List<E> getObjectByFiled()
	{
		try
		{
		} catch (Exception e)
		{
			e.printStackTrace();

		}
		return null;

	}
}
