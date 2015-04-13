function openip() {
		var stylediv = document.getElementById("taqgcdtmapip").style.display;
		if (stylediv == "") {
			document.getElementById("taqgcdtmapip").style.display = "none"
		} else {
			document.getElementById("taqgcdtmapip").style.display = ""
		}
		var gcinfodd = document.getElementById("address").value;
		var ip = document.getElementById("faqdtip").value;
		var newContent = "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>活动信息</h4>"
				+ "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>活动地点："
				+ gcinfodd + "</p>" + "</div>";
		var newinfoWindow = new BMap.InfoWindow(newContent); // 创建信息窗口对象 
		// 百度地图API功能
		var map = new BMap.Map("taqgcdtmapip");
		var point = new BMap.Point(120.2, 30.3);
		map.centerAndZoom(point, 13);
		map.enableScrollWheelZoom();
		if (ip != "" && ip != null) {
			var arrip = ip.split(",");
			var mypointClick = new BMap.Point(arrip[0], arrip[1]);
			map.centerAndZoom(mypointClick, 13);
			var mymarker = new BMap.Marker(mypointClick);
			map.addOverlay(mymarker);
			mymarker.enableDragging();
			mymarker.addEventListener("dragend", function(e) {
				setIp(e.point.lng + "," + e.point.lat);
			});
			mymarker.addEventListener("mouseover", function() {
				this.openInfoWindow(newinfoWindow);
			});
			mymarker.addEventListener("mouseout", function() {
				newinfoWindow.close();
			});
		} else {
			var local = new BMap.LocalSearch(map, {
				renderOptions : {
					map : map
				}
			});
			local.search(gcinfodd);
			map.addEventListener("click", function(e) {
				var pointClick = new BMap.Point(e.point.lng, e.point.lat);
				var marker = new BMap.Marker(pointClick);
				map.clearOverlays();
				map.addOverlay(marker);
				setIp(e.point.lng + "," + e.point.lat);
				//点击地图，产生经度纬度，根据经度纬度获取地址
				address(e.point.lng, e.point.lat);
				marker.enableDragging();
				marker.addEventListener("dragend", function(e) {
					setIp(e.point.lng + "," + e.point.lat);
					alert(4);
				     alert(document.getElementById("address").value);
				});
				marker.addEventListener("mouseover", function() {
					this.openInfoWindow(newinfoWindow);
				});
				marker.addEventListener("mouseout", function() {
					newinfoWindow.close();
				});

			});
		}
	};
	function address(lng,lat){
	var gc = new BMap.Geocoder(); 
	var pt = new BMap.Point(lng,lat);     
    var dress = gc.getLocation(pt, function(rs){
    var addComp = rs.addressComponents;
	document.getElementById("address").value=addComp.province+addComp.city+addComp.district+addComp.street+addComp.streetNumber;
});
	}