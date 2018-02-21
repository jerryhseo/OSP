<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="getMajorAchievementURL" id="getMajorAchievement" escapeXml="false" copyCurrentRenderParameters="false" />

<style>

.resultwrap{padding-bottom:80px; position:relative; clear:both;}
.resultlist{ padding-top:50px; position:relative; font-family:'nanumgothic', sans-serif;}
.resultlist ul{ padding:0; background-color:#fff; margin:0; border:solid 1px #ddd; border-radius:0 0 7px 7px;}
.resultlist ul li{ list-style:none; font-family:'nanumgothic', sans-serif; font-size:13px; color:#777; padding:10px 15px; text-align:center;}
.resultlist ul li.tit{ font-size:16px; color:#345e9e !important; font-weight:600; padding-top:20px; text-align:center;}
.resultlist ul li:first-child{ padding:0;}
.resultlist ul li:first-child img{ width:100%; max-height:147px;}
.resultlist ul li a{color:#345e9e!important;}
.resultlist ul li a:hover{text-decoration:underline!important;}
.resultlist ul li:last-child{padding-bottom:15px; text-align:center;}

.btn_line02{
	border-radius:20px !important;
	cursor:pointer !important;
	color:#162f40; 
	border:solid 1px #5bc0de; 
	background-color:#fff;
	font-size: 12px !important;
	vertical-align: middle !important;
	line-height: 18px !important;
	display: inline-block !important;
	padding:3px 16px !important;
	font-weight:600; text-align:center;
}
	
.btn_line02 a{color:#162f40 !important;}
.btn_line02 a:hover{color:#162f40; text-decoration:none !important;}
.btn_line02:hover{ background-color:#5bc0de; color:#fff !important;  text-decoration:none !important; transition-duration:0.4s; transition-timing-function:ease;}

</style>

<script type="text/javascript" src="${contextPath}/js/chart/modules/exporting.js"></script>

<h2 class="h2title" style="font-family: 'nanumgothic', sans-serif; padding-top: 12px;">성과데이터 조회</h2>
<div class="resultwrap">
	<div class="container">
		<div id="<portlet:namespace/>achievementsDataList" class="row" style="margin: 0;">
			
		</div>
	</div>
</div>

<script type="text/javascript">

/* 성과데이터 화면 출력 */
<portlet:namespace/>getAchievementsDataList();

/* 성과데이터 화면 출력 */
function <portlet:namespace/>getAchievementsDataList(){
	achievementsDataList = $("#<portlet:namespace/>achievementsDataList");
	
	for(var i=0; i<4; i++){
		
		achievementsDataItem = $("<div/>").addClass("resultlist col-md-3 col-sm-12").css("padding-left","7px").css("padding-right","7px");
		$ul = $("<ul/>");
		
		$("<li/>").append( $("<img/>").attr("src","${contextPath}/images/resultimgbox.jpg").attr("width", "289").attr("height", "147") )
				  .appendTo($ul);
		$("<li/>").addClass("tit").text("경진대회 개최를 통한 저변확대 및 연구활동 촉진").appendTo($ul);
		$("<li/>").html("저자 : 홍길동&nbsp;&nbsp;｜&nbsp;&nbsp;2018.02.08").appendTo($ul);
		$("<li/>").append( $("<span/>").addClass("btn_line02").attr("onclick", "<portlet:namespace/>achievementDataDetailView()").text("더보기 ＞") ).appendTo($ul);
		
		$ul.appendTo(achievementsDataItem);
		achievementsDataItem.appendTo(achievementsDataList);
	}
}

/* 성과데이터 자세히 보기(더보기 Button 클릭) */
function <portlet:namespace/>achievementDataDetailView(){
	console.log("Achievement Data Detail View...");
}

</script>