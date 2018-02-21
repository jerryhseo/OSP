<%@page import="com.liferay.portlet.asset.model.AssetCategory"%>
<%@page import="org.kisti.edison.search.domain.SearchConstants"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
<portlet:defineObjects />
<%  
boolean areaScienceApp = GetterUtil.getBoolean(portletPreferences.getValue(SearchConstants.AREA_SCIENCE_APP, StringPool.TRUE));
boolean areaContents = GetterUtil.getBoolean(portletPreferences.getValue(SearchConstants.AREA_CONTENTS, StringPool.TRUE));
boolean areaSimulationProject = GetterUtil.getBoolean(portletPreferences.getValue(SearchConstants.AREA_SIMULATION_PROJECT, StringPool.TRUE));
boolean areaScienceData = GetterUtil.getBoolean(portletPreferences.getValue(SearchConstants.AREA_SCIENCE_DATA, StringPool.TRUE));
%>
<aui:form action="<%= configurationURL %>" method="post" name="fm">
  <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
  <!-- Preference control -->
  <aui:fieldset label="edison-search-area" >
    <aui:input name="<%= \"preferences--\" + SearchConstants.AREA_SCIENCE_APP + \"--\"%>"  label="edison-search-science-app" type="checkbox" value="<%= areaScienceApp  %>"/>
    <aui:input name="<%= \"preferences--\" + SearchConstants.AREA_CONTENTS + \"--\"%>"  label="edison-search-contents" type="checkbox" value="<%= areaContents  %>"/>
    <aui:input name="<%= \"preferences--\" + SearchConstants.AREA_SIMULATION_PROJECT + \"--\"%>"  label="edison-search-simulation-project" type="checkbox" value="<%= areaSimulationProject  %>"/>
    <aui:input name="<%= \"preferences--\" + SearchConstants.AREA_SCIENCE_DATA + \"--\"%>"  label="edison-search-science-data" type="checkbox" value="<%= areaScienceData  %>"/>
  </aui:fieldset>
  <br/>
  <aui:fieldset label="edison-search-category-image">
  <aui:field-wrapper inlineField="true" >
    <%
    Object categoryAttr = request.getAttribute("rootCategories");
    Map<String, String> categoryImages = new HashMap<String, String>(){{
      put("전산열유체", "CFD");
      put("Computational Fiuid Dynamics", "CFD");
      put("나노물리", "NANO");
      put("계산화학", "CHEM");
      put("구조동역학", "CSD");
      put("전산설계", "DESIGN");
      put("전산의학", "CMED");
      put("Computational Medicine", "CMED");
    }};
    if(categoryAttr != null){
      List<AssetCategory> rootCategories = (List<AssetCategory>)categoryAttr;
      for(AssetCategory category : rootCategories){
       %>
        <aui:input name="<%= \"preferences--\" + category.getCategoryId() + \"--\"%>"  label="<%=category.getTitle(request.getLocale())%>" type="input" 
          value="<%= GetterUtil.getString(portletPreferences.getValue(String.valueOf(category.getCategoryId()), categoryImages.get(category.getName())))%>"/>.png
       <% 
      }
    }else{
      %>
      <p><liferay-ui:message key="edison-search-category-none"/></p>
      <%
    }
    %>
  </aui:field-wrapper>
  </aui:fieldset>
  <aui:button-row>
    <aui:button type="submit" />
  </aui:button-row>
</aui:form>

