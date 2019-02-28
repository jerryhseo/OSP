<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<portlet:defineObjects />
<c:if test="${searchResults.appCount > 0}">
    <div class="search-results box-body">
        <ul class="products-list product-list-in-box">
            <c:forEach items="${searchResults.appResults}" var="element">
                <li class="item">
                    <div class="product-img">
                        <img class="profile-user-img img-responsive" alt="${element.currentTitle}"
                            src="/documents/${element.iconRepositoryId}/${element.iconId}/${element.iconTitle}/${element.iconUuid}?imageThumbnail=2"
                            width="67px" style="height: 50px !important;"
                            onerror="this.src='${contextPath}/images/noimage.png'">
                    </div>
                    <div class="product-info">
                        <a class="product-title" href="#appdetail"
                            onclick="<portlet:namespace/>moveScienceAppDetail(${element.groupId}, ${element.scienceAppId}); return false;">
                            ${element.name}</a>
                        <div class="pull-right hidden-sm hidden-xs"
                            style="line-height: 2.3em !important;">
                            <c:if
                                test="${!empty element.current_manualId and element.current_manualId ne 0}">
                                <img src="${contextPath}/images/search/btn_manual.jpg"
                                    style="height: 24px; cursor: pointer;"
                                    onClick="<portlet:namespace/>fileDownload('${element.current_manualId}')" />
                            </c:if>
                            <c:if
                                test="${empty element.current_manualId or element.current_manualId eq 0}">
                                <img src="${contextPath}/images/search/btn_manual_none.jpg"
                                    style="height: 24px; cursor: default;" />
                            </c:if>
                            <c:if
                                test="${workBenchPlid ne 0 and isSignedIn and element.openLevel ne downloadOnly and element.appType eq 'Solver'}">
                                <img src="${contextPath}/images/search/btn_run.jpg"
                                    style="cursor: pointer; height: 24px;"
                                    onClick="<portlet:namespace/>moveWorkBench('${element.scienceAppId}');" />
                            </c:if>
                            <c:if test="${workBenchPlid ne 0 and element.openLevel eq downloadOnly}">
                                <img src="${contextPath}/images/download_btn.gif"
                                    style="cursor: pointer; height: 24px;"
                                    onClick="<portlet:namespace/>fileDownload('${element.srcFileName}')" />
                            </c:if>
                        </div>
                        <span class="product-description"> <c:if test="${!empty element.title}">
                ${element.title}<br />version : ${element.version} / <liferay-ui:message
                                    key="edison-virtuallab-owner" /> : ${element.screenName}
              </c:if> <c:if test="${empty element.title}">
                                <liferay-ui:message key="edison-search-no-detail" />
                            </c:if>
                        </span>
                        <div class="hidden-md hidden-lg" style="line-height: 2.3em !important;">
                            <c:if
                                test="${!empty element.current_manualId and element.current_manualId ne 0}">
                                <img src="${contextPath}/images/search/btn_manual.jpg"
                                    style="height: 24px; cursor: pointer;"
                                    onClick="<portlet:namespace/>fileDownload('${element.current_manualId}')" />
                            </c:if>
                            <c:if
                                test="${empty element.current_manualId or element.current_manualId eq 0}">
                                <img src="${contextPath}/images/search/btn_manual_none.jpg"
                                    style="height: 24px; cursor: default;" />
                            </c:if>
                            <c:if
                                test="${workBenchPlid ne 0 and isSignedIn and element.openLevel ne downloadOnly and element.appType eq 'Solver'}">
                                <img src="${contextPath}/images/search/btn_run.jpg"
                                    style="cursor: pointer; height: 24px;"
                                    onClick="<portlet:namespace/>moveWorkBench('${element.scienceAppId}');" />
                            </c:if>
                            <c:if test="${workBenchPlid ne 0 and element.openLevel eq downloadOnly}">
                                <img src="${contextPath}/images/download_btn.gif"
                                    style="cursor: pointer; height: 24px;"
                                    onClick="<portlet:namespace/>fileDownload('${element.srcFileName}')" />
                            </c:if>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<c:if test="${searchResults.contentCount > 0}">
    <div class="search-results box-body">
        <ul class="products-list product-list-in-box">
            <c:forEach items="${searchResults.contentResults}" var="element">
                <li class="item">
                    <div class="product-img">
                        <img class="profile-user-img img-responsive" alt="${element.currentTitle}"
                            src="/documents/${element.iconRepositoryId}/${element.iconId}/${element.iconTitle}/${element.iconUuid}?imageThumbnail=2"
                            width="67px" style="height: 50px !important;"
                            onerror="this.src='${contextPath}/images/noimage.png'">
                    </div>
                    <div class="product-info">
                        <a class="product-title" href="#contentDetail"
                            onclick="<portlet:namespace/>moveContentDetail(${element.contentSeq}, ${element.contentDiv}); return false;">
                            ${element.title}</a> <span class="product-description"> ${element.resume} <c:if
                                test="${empty element.resume}">
                                <liferay-ui:message key="edison-search-no-detail" />
                            </c:if>
                        </span>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<c:if test="${searchResults.projectCount > 0}">
    <div class="search-results box-body">
        <ul class="products-list product-list-in-box">
            <c:forEach items="${searchResults.projectResults}" var="element">
                <li class="item">
                    <div class="product-img">
                        <img class="profile-user-img img-responsive" alt="${element.currentTitle}"
                            src="/documents/${element.iconRepositoryId}/${element.iconId}/${element.iconTitle}/${element.iconUuid}?imageThumbnail=2"
                            width="67px" style="height: 50px !important;"
                            onerror="this.src='${contextPath}/images/noimage.png'">
                    </div>
                    <div class="product-info">
                        <a href="#simulationProjectDetail"
                            onclick="<portlet:namespace/>moveSimulationProjectDetail(${element.simulationProjectId}); return false;">${element.title}</a>
                        <span class="product-description"> ${element.explain} <c:if
                                test="${empty element.explain}">
                                <liferay-ui:message key="edison-search-no-detail" />
                            </c:if>
                        </span>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<c:if test="${searchResults.dataCount > 0}">
    <div class="search-results box-body">
        <ul class="products-list product-list-in-box">
            <c:forEach items="${searchResults.dataResults}" var="element">
                <li class="item">
                    <div class="product-img">
                        <img class="profile-user-img img-responsive" alt="${element.vo.title}"
                            src="/documents/${element.iconRepositoryId}/${element.iconId}/${element.iconTitle}/${element.iconUuid}?imageThumbnail=2"
                            width="67px" style="height: 50px !important;"
                            onerror="this.src='${contextPath}/images/noimage.png'">
                    </div>
                    <div class="product-info">
                        <a href="#dataDetail"
                            onclick="<portlet:namespace/>moveDataDetail(${element.vo.collectionId}); return false;">${element.vo.title}
                            <c:if test="${!empty element.dsCnt && element.dsCnt ne 0}">(${element.dsCnt })</c:if> <span
                            class="label label-warning pull-right"> <c:choose>
                                    <c:when test="${empty element.embago}">
                                                    Inavailable
                                                </c:when>
                                    <c:when test="${element.embago == 0}">
                                                    Available now.
                                                </c:when>
                                    <c:otherwise>
                                                    Available in
                                                    <c:if test="${element.embago > 30}">
                                            <fmt:parseNumber value="${element.embago / 30}" integerOnly="true" /> month.
                                                    </c:if>
                                        <c:if test="${element.embago <= 30}">
                                                        ${element.embago} day.
                                                    </c:if>
                                    </c:otherwise>
                                </c:choose>
                        </span>
                        </a> <span class="product-description"> ${element.vo.description} </span> <span
                            class="product-description"> <fmt:formatDate value="${element.vo.createDate }"
                                pattern="yyyy-MM-dd" /> | ${element.vo.doi } | ${element.communityname } <c:choose>
                                <c:when test="${!empty element.contributorNames[0] }">
                                          | ${element.contributorNames[0] }
                                          <c:if test="${fn:length(element.contributorNames) > 1 }">
                                              and ${fn:length(element.contributorNames) - 1} others
                                          </c:if>
                                </c:when>
                                <c:otherwise> | ${element.userName }</c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<c:if test="${searchResults.appCount + searchResults.contentCount + searchResults.projectCount + searchResults.dataCount eq 0}">
    <div class="search-results">
        <ul style="min-height: 100px; margin-top: 20px;">
            <li><span class="result-none"><liferay-ui:message key="edison-search-no-result" /></span></li>
        </ul>
    </div>
</c:if>
<div class="text-center">${paging}</div>
