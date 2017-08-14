<%@ page import="org.kisti.edison.util.CustomUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://www.edison.re.kr/tld/edison-ui" prefix="edison-ui" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<liferay-theme:defineObjects />
<%
  String selectedGroupId = CustomUtil.strNull(request.getAttribute("selectedGroupId"));
  String icebreakerUrl = CustomUtil.strNull(request.getAttribute("icebreakerUrl"));
  String zipFileId = CustomUtil.strNull(request.getAttribute("zipFileId"));
%>

<style type="text/css">
.aui input[type="text"], .aui input[type="password"], .aui textarea, .aui .edison_select {
 margin-bottom: 0px;
}
.file_tr:hover {
 background-color: #e0e0e0;
}
.controls.result-list, .controls.result-button{margin-left: 5px !important; margin-right: 5px !important;}
#<portlet:namespace/>result-files .button03{font-size: 12px !important;}
#<portlet:namespace/>result-files .table1 th{font-size: 12px !important;}
#<portlet:namespace/>result-files .table1 td{font-size: 11px !important;}
#<portlet:namespace/>result-files .table1 {line-height: 1em;}
</style>
<fieldset id="<portlet:namespace/>result-files">
  <legend>Result Files</legend>
  <c:if test="${!empty zipFileId}">
  <div class="control-group">
    <div class="controls result-button">
        <input type="button" onclick="<portlet:namespace/>allDown();return false;" class="button03" value='<liferay-ui:message key="edison-simulation-monitoring-result-file-all-down"/>' />
    </div>
  </div>
  </c:if>
  
  <div class="control-group">
    <div class="controls result-list">
      <table border="0" cellpadding="0" cellspacing="0" class="table1" style="word-break: break-all;">
        <colgroup>
          <col width="200px" />
          <col width="150px" />
        </colgroup>
        <thead>
          <tr>
            <th scope="col" class="left"><liferay-ui:message key="edison-table-list-header-file-nm"/></th>
            <th scope="col"><liferay-ui:message key="edison-table-list-header-file-size"/></th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${!empty resultList}">
              <c:forEach items="${resultList}" var="model" varStatus="data">
                <tr class="file_tr" style="cursor: pointer;" onclick="<portlet:namespace/>fileDown('${model.fileId}');">
                  <td class="left">
                    ${model.fileName}
                  </td>
                  <td>${model.fileSize}</td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="2" class="TC"><liferay-ui:message key='edison-there-are-no-data'/></td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
  </div>
      
  </div>
</fieldset>

<script>
function <portlet:namespace/>allDown(){
  var zipFileId = "<%=zipFileId%>";
  if(zipFileId != ""){
      window.location.href = '<%=icebreakerUrl%>/api/file/download?id=' + zipFileId;
  }else{
      alert('<liferay-ui:message key="edison-simulation-monitoring-services-are-not-support" />');
  }
}

function <portlet:namespace/>fileDown(fileId){
  var url = '<%=icebreakerUrl%>/api/file/download?id=' + fileId;
  console.log("URL : " + url);
  window.location.href = url;
}
</script>
