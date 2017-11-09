<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<portlet:defineObjects />
<c:if test="${searchResults.appCount > 0}">
  <ul>
    <c:forEach items="${searchResults.appResults}" var="element">
      <li>
        <a href="#appdetail" onclick="<portlet:namespace/>moveScienceAppDetail(${element.groupId}, ${element.scienceAppId}); return false;">${element.name}</a>
        <div style="float: right; line-height: 2.3em !important;">
            <c:if test="${!empty element.current_manualId and element.current_manualId ne 0}">
                <img src="${contextPath}/images/search/btn_manual.jpg" style="height: 24px; cursor: pointer;" 
                    onClick="<portlet:namespace/>fileDownload('${element.current_manualId}')" />
            </c:if>
            <c:if test="${empty element.current_manualId or element.current_manualId eq 0}">
                <img src="${contextPath}/images/search/btn_manual_none.jpg" style="height: 24px; cursor: default;"/>
            </c:if>
            
            <c:if test="${workBenchPlid ne 0 and isSignedIn}">
                <img src="${contextPath}/images/search/btn_run.jpg"style="cursor:pointer; height: 24px;" 
                    onClick="<portlet:namespace/>moveWorkBench('${element.scienceAppId}');"/>
            </c:if>
        </div>
        <div>
          <c:if test="${!empty element.title}">
            ${element.title}<br/>version : ${element.version} / <liferay-ui:message key="edison-virtuallab-owner"/> : ${element.screenName}
          </c:if>
          <c:if test="${empty element.title}">
                        상세 정보가 없습니다.
          </c:if>
        </div>
      </li>
    </c:forEach>
  </ul>
</c:if>

<c:if test="${searchResults.contentCount > 0}">
  <ul>
    <c:forEach items="${searchResults.contentResults}" var="element">
      <li>
        <a href="#contentDetail" onclick="<portlet:namespace/>moveContentDetail(${element.contentSeq}, ${element.contentDiv}); return false;">${element.title}</a>
        <div>
          ${element.resume}
          <c:if test="${empty element.resume}">
            상세 정보이 없습니다.
          </c:if>
        </div>
      </li>
    </c:forEach>
  </ul>
</c:if>

<c:if test="${searchResults.projectCount > 0}">
  <ul>
    <c:forEach items="${searchResults.projectResults}" var="element">
      <li>
        <a href="#simulationProjectDetail" onclick="<portlet:namespace/>moveSimulationProjectDetail(${element.simulationProjectId}); return false;">${element.title}</a>
        <div>
          ${element.explain}
          <c:if test="${empty element.explain}">
            상세 정보이 없습니다.
          </c:if>
        </div>
      </li>
    </c:forEach>
  </ul>
</c:if>

<c:if test="${searchResults.dataCount > 0}">
  <ul>
    <c:forEach items="${searchResults.dataResults}" var="element">
      <li>
        <a href="#dataDetail" onclick="<portlet:namespace/>moveDataDetail(${element.collectionId}); return false;">${element.title}</a>
        <div>
          ${element.description}
          <c:if test="${empty element.description}">
            상세 정보가 없습니다.
          </c:if>
        </div>
      </li>
    </c:forEach>
  </ul>
</c:if>

<c:if test="${searchResults.appCount + searchResults.contentCount + searchResults.projectCount + searchResults.dataCount eq 0}">
  <ul>
    <li><span class="result-none">검색 결과가 없습니다.</span></li>
  </ul>

</c:if>
<div class="paging">${paging}</div>
