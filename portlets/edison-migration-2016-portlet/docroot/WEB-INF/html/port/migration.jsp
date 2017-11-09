<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="searchPortURL" id="searchPort" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="updatePortURL" id="updatePort" copyCurrentRenderParameters="false" />

<h3>Port Migration</h3>
<p>2015년 INPUT,OUTPUT PORT DATA를 2017년 JSON FORMAT으로 변경 하는 프로그램</p>
<p>INPUT:${inputTotalCnt}</p>
<p>OUTPUT:${outputTotalCnt}</p>
INPUT 수행건수 : <p id="changeInputCnt">0</p>
OUTPUT 수행건수 : <p id="changeOutputCnt">0</p>
<input type="button" value="INPUT MIR" onclick="search('INPUT');"/>
<input type="button" value="OUTPUT MIR" onclick="search('OUTPUT');"/>


<script type="text/javascript">

	function search(type){
		var dataForm = {
			"<portlet:namespace/>type" : type
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=searchPortURL%>",
			async : false,
			data : dataForm,
			dataType: 'json',
			success: function(msg) {
				var portList = msg.dataList;
				for(var i = 0; i < portList.length; i++) {
					var appId = portList[i].appId;
					var json = JSON.parse(portList[i].JSON);
					var scienceApp = new OSP.ScienceApp();
					if(type=='INPUT'){
// 						console.log(appId+"____PRE-->"+portList[i].JSON);
						var newInputPorts = scienceApp.upgradeInputPorts( json );
// 						console.log("RESULT-->"+JSON.stringify(newInputPorts));
						update(type,appId,JSON.stringify(newInputPorts));
					}else{
// 						console.log(appId+"____PRE-->"+portList[i].JSON);
						var newOutputPorts = scienceApp.upgradeOutputPorts(json);
// 						console.log("RESULT-->"+JSON.stringify(newOutputPorts));
						update(type,appId,JSON.stringify(newOutputPorts));
					}
				}
			},error:function(msg,e){ 
				return false;
			}
		});
	}
	
	
	function update(type,appId,port){
		var dataForm = {
				"<portlet:namespace/>type" : type,
				"<portlet:namespace/>appId" : appId,
				"<portlet:namespace/>port" : port
		};
		
		jQuery.ajax({
			type: "POST",
			async : false,
			data : dataForm,
			url: "<%=updatePortURL%>",
			dataType: 'json',
			success: function(msg) {
				if(type=='INPUT'){
					var num = $("#changeInputCnt").html();
					$("#changeInputCnt").html(Number(num)+1);
				}else{
					var num = $("#changeOutputCnt").html();
					$("#changeOutputCnt").html(Number(num)+1);
				}
			},error:function(msg,e){ 
				return false;
			}
		});
	}
</script>
