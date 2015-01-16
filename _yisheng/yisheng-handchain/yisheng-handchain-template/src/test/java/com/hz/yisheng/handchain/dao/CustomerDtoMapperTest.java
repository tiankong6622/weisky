package com.hz.yisheng.handchain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.handchain.dto.CustomerDto;

public class CustomerDtoMapperTest extends BaseDAOTest{
	@Autowired
	private CustomerDtoMapper customerDtoMapper;
	
	@Test
	public void testList(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", 12);
		param.put("name", "张三");
	//	param.put("deviceNumber", 12);
		List<CustomerDto> customerDto = customerDtoMapper.list(param);
		for(int i=0;i<customerDto.size();i++){
			System.out.println(customerDto.get(i).getId());
		}
	}
	/*@Test
	public void testListById(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", 12);
		List<CustomerDto> customerDto = customerDtoMapper.listById(param);
		for(int i=0;i<customerDto.size();i++){
			System.out.println(customerDto.get(i).getId());
		}
	}*/
	/*@Test
	public  void getRandomPwd(){
		  Random rd = new Random();
		  String n="";
		  int getNum;
		  if(rd.equals("0")){
			  
		  }
		  do {
		   getNum = Math.abs(rd.nextInt())%10 + 48;//产生数字0-9的随机数
		   System.out.println(getNum);
		   char num1 = (char)getNum;
		   if(num1 == '0'){
			   num1 = '1';
		   }
		   System.out.println(num1);
		   String dn = Character.toString(num1);
		   n += dn;
		  } while (n.length()<6);
		  System.out.println("随机的6位密码是：" + n);
		 }*/

}
