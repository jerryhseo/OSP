<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="serveResourceURL" escapeXml="false" id="serveResource" copyCurrentRenderParameters="false"/>
<style>
 .analyzerContainer {
	overflow: hidden;
    margin:auto;
    padding:0px;
}
.analyzerWrapper {
/*     border: 3px solid #00c7e5; */
	overflow: hidden;
    margin:auto;
    padding:2px;
    width:98%;
    height:98%;
}
.analyzerSectionWrapper{
	padding:0px;
	margin:auto;
    overflow: hidden;
    width: 98%;
    height: 98%;
	display: inline;
}
.analyzerFolderCanvas{
	border: 1px solid #dbdbdb ;
	width : 23%;
    height: 98%;
	padding:1px;
	overflow-x:auto; 
 	overflow-y:auto; 
	float: left;
	margin: 0px 0px 0px 0px;
}
.analyzerFolderCanvas ul, .analyzerFolderCanvas ol {
	font-size: 12px;
	margin: 0 0 10px 3px;
    padding: 0;
}
.analyzerViewerCanvas{
	position: relative;
	border: 1px solid #dbdbdb ;
	width: 100%;
    height: 98%;
/* 	padding:6px 0px 0px 6px; */
	overflow:auto;
	margin: auto;
/* 	margin: 0px 0px 0px 3px auto; */
/* 	float:center; */
}
 </style>
 <div class="analyzerContainer" id="<portlet:namespace/>analyzerContainer">
	<div class="analyzerWrapper">
		<div class="analyzerSectionWrapper">
			<iframe id="<portlet:namespace/>viewerCanvas"  name="<portlet:namespace/>viewerCanvas" class="analyzerViewerCanvas"></iframe>
		</div>
	</div>
</div>

<script>
$(function(){
	<portlet:namespace/>initAnalyzer( 
		$('#<portlet:namespace/>analyzerContainer'),
		$('#<portlet:namespace/>viewerCanvas'),
		'${contextPath}',
		"${shipmentForm}",
		"${lookUpPath}",
		'${filePath}'
	);
})

function <portlet:namespace/>initAnalyzer(
		analyzerContainer, 
		viewerCanvas, 
		contextPath, 
		shipmentForm, 
		lookUpPath,
		filePath 
){
	//analyzerContainer.css('height', Math.floor($(window).innerHeight()*0.98)-4);
	analyzerContainer.css('height', 800);
	analyzerContainer.resize();
//	console.log('filePath: ' + filePath);

	var fileInfos;
	switch (shipmentForm){
	case 'file':
		fileInfos = <portlet:namespace/>loadHtml( viewerCanvas, lookUpPath, filePath );
		break;
	}
}

function <portlet:namespace/>loadHtml( canvas, lookUpPath, filePath ){
	var fileInfos = <portlet:namespace/>getFolderFiles( lookUpPath, filePath, 'getInitialHtmlTempPath');
	//window.<portlet:namespace/>viewerCanvas.location.href = fileInfos.initialHtmlPath;
	
//	canvas.attr('src', 'https://www.edison.re.kr'+fileInfos.initialHtmlPath);
	canvas.attr('src', 'http://localhost:8080'+fileInfos.initialHtmlPath);
	return fileInfos;
}

function <portlet:namespace/>getFolderFiles( lookUpPath, filePath, method ){
	var data = {
			<portlet:namespace/>method: method,
			<portlet:namespace/>lookUpPath: lookUpPath,
			<portlet:namespace/>filePath: filePath
	};
	var fileInfos = null;
	
	$.ajax({
		type: 'POST',
		url: '<%=serveResourceURL.toString()%>',
		async : false,
		data  : data,
		dataType : "json",
		success: function(data) {
			fileInfos = data;
//			console.log(JSON.stringify(fileInfos, null, 4));
		},error:function(data,e){
			console.log(data);
			console.log("textView ERROR-->"+e);
		}
	});
	
	return fileInfos;
}

</script>
