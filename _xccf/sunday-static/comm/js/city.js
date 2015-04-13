var province_arr = [];
jQuery.get("/common/city/getProvinces",{},function(data){
	province_arr=data;
});

/**
 * 初始化省市县联动控件
 * @author ChenJunhui
 * @param provinceSelId
 * @param districtSelId
 * @param citySelId
 * @param currentProvinceId 当前省份值 没有不传
 * @param currentdistrictId 当前地区值 没有传不传
 * @param currentCityId 当前城市值没有传不传
 */
function doInitCitySelect(provinceSelId,districtSelId,citySelId,currentProvinceId,currentdistrictId,currentCityId){
	$('#'+provinceSelId).unbind("change");
	$('#'+districtSelId).unbind("change");
	$('#'+citySelId).unbind("change");
	removAlleSelect(provinceSelId);
	var provinceDom = document.getElementById(provinceSelId);
	provinceDom.options[provinceDom.length] = new Option("-选择省份-","");
	for(var i=0;i<province_arr.length;i++){
		provinceDom.options[provinceDom.length] = new Option(province_arr[i].value,province_arr[i].id);
	}
	
	var districtDom = document.getElementById(districtSelId);
	districtDom.options[districtDom.length] = new Option("-选择地区-","");
	var cityDom = document.getElementById(citySelId);
	cityDom.options[cityDom.length] = new Option("-选择县市区-","");
	
	$("#"+provinceSelId).change(function(){
		removAlleSelect(districtSelId);
		districtDom.options[districtDom.length] = new Option("-选择地区-","");
		if($(this).val()!=""){
			jQuery.get("/common/city/getDistricts",{provinceId:$(this).val()},function(data){
				for(var i=0;i<data.length;i++){
					districtDom.options[districtDom.length] = new Option(data[i].value,data[i].id);
				}
				removAlleSelect(citySelId);
				cityDom.options[cityDom.length] = new Option("-选择县市区-","");
			});
		}
	});
	
	$("#"+districtSelId).change(function(){
		removAlleSelect(citySelId);
		if($(this).val()!=""){
			cityDom.options[cityDom.length] = new Option("-选择县市区-","");
			jQuery.get("/common/city/getCitys",{districtId:$(this).val()},function(data){
				for(var i=0;i<data.length;i++){
					cityDom.options[cityDom.length] = new Option(data[i].value,data[i].id);
				}
			});
		}
	});

	if(typeof currentProvinceId!='undefined'){
		provinceDom.value=currentProvinceId;
		jQuery.get("/common/city/getDistricts",{provinceId:currentProvinceId},function(data){
			for(var i=0;i<data.length;i++){
				districtDom.options[districtDom.length] = new Option(data[i].value,data[i].id);
			}
			if(typeof currentdistrictId!='undefined'){
				districtDom.value=currentdistrictId;
				jQuery.get("/common/city/getCitys",{districtId:currentdistrictId},function(data){
					for(var i=0;i<data.length;i++){
						cityDom.options[cityDom.length] = new Option(data[i].value,data[i].id);
					}
					if(typeof currentCityId!='undefined'){
						cityDom.value=currentCityId;
					}
				});
			}
		});
	}
}
