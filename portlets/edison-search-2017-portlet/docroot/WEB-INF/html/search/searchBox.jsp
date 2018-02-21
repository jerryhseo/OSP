<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<portlet:defineObjects />

<style>
	.totalsearchwrap {
		width: 100%;
	}
	
	.totalsearchcon {
		width: 1200px;
		height: 107px;
		margin: 0 auto;
		overflow: hidden;
		background-image: url(${contextPath}/images/search/class-searchicon.png),
						  url(${contextPath}/images/search/class-searchicon2.png);
		background-position: 113px 26px, 1006px 42px;
		background-repeat: no-repeat;
	}
	
	.searchtxt {
		margin-top: 44px;
		margin-left: 225px;
		position: relative;
		width: 758px;
		height: 51px;
		background-color: #f4f4f4;
	}
	
	.txtareasearch {
		width: 90%;
		height: 49px;
		vertical-align: middle;
		border: none;
		background-color: #f4f4f4;
		padding-left: 20px;
		font-size: 17px !important;
	}
	
	.searchbtnedu {
		position: absolute;
		right: 0;
		top: 0;
		width: 51px;
		height: 51px;
		background: url(${contextPath}/images/search/search_btnbg.png) no-repeat center
			center;
		background-color: #01111d;
	}
	
	.searchbtnedu:hover {
		cursor: pointer;
		background-color: #383838;
	}
</style>


<div class="totalsearchwrap">
	<div class="totalsearchcon">
		<div class="searchtxt">
			<input id="<portlet:namespace/>searchField" name="" type="text" placeholder="통합검색" class="txtareasearch" onkeypress="if(event.keyCode==13)<portlet:namespace/>searchAppStore()">
			<div class="searchbtnedu" onclick="<portlet:namespace/>searchAppStore()"></div>
		</div>
	</div>
</div>


<script>

function <portlet:namespace/>searchAppStore() {
	var searchField = $("#<portlet:namespace/>searchField").val();
	if(searchField != "" && searchField != null) {
		window.location.href = "${searchURL}&_edisonsearch_WAR_edisonsearch2017portlet_searchKeyword=" + searchField;

	}
}

</script>