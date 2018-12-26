package org.kisti.edison.wfapi.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kisti.edison.science.service.PortTypeAnalyzerLinkLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppOutputPortsLocalServiceUtil;
import org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil;
import org.kisti.edison.wfapi.custom.WorkflowBeanUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.rits.cloning.Cloner;

@Controller
@RequestMapping("/services/app")
public class ScienceAppController{
  private Log log = LogFactoryUtil.getLog(getClass());
  private Cloner cloner = new Cloner();
  
  @RequestMapping(value = "/all", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> all(
      @RequestParam(value="companyGroupId", required=false) String strCompayGroupId,
      @RequestParam(value="groupId", required=false) String strGroupId,
      HttpServletRequest request)
      throws Exception{
    try{
      List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
      List<Map<String, Object>> apps = new ArrayList<Map<String, Object>>();
      long groupId = GetterUtil.getLong(strGroupId);
      long companyGroupId = GetterUtil.getLong(strCompayGroupId);
      Locale locale = PortalUtil.getLocale(request);
      List<AssetCategory> lv1Categories = WorkflowSimulationJobLocalServiceUtil.getLv1Categories(companyGroupId, groupId, locale);
      List<Map<String, Object>> rootCategories = WorkflowBeanUtil
          .assetCategoryToJstreeModel(lv1Categories, locale);
      results.addAll(rootCategories);
      for(Map<String, Object> lv1 : rootCategories){
        long lv1CategoryId = GetterUtil.getLong(lv1.get("categoryId"));
        List<AssetCategory> childrenAssetCategories = getReOrderedChildrenCategories(lv1CategoryId);
        List<Map<String, Object>> subCategories = 
            ScienceAppLocalServiceUtil
              .getLanguageSpecificAssetCategories(childrenAssetCategories, lv1CategoryId, locale);
        for(Map<String, Object> lv2 : subCategories){
          if(lv2.isEmpty() || lv2.get("categoryId") == null){
            continue;
          }
          long lv2CategoryId = GetterUtil.getLong(lv2.get("categoryId"));
          lv2.put("id", lv2CategoryId);
          lv2.put("data", cloner.deepClone(lv2));
          lv2.put("parent", lv1CategoryId);
          List<Map<String, Object>> app = WorkflowBeanUtil.scienceAppToJstreeModel(
              ScienceAppLocalServiceUtil.getScienceAppListByCategoryId(lv2CategoryId, locale),
              locale, lv2CategoryId);
          apps.addAll(app);
        }
        results.addAll(subCategories);
      }
      results.addAll(apps);
      
      return results;
    }catch (Exception e){
      log.error("error", e);
      throw e;
    }
  }

  private List<AssetCategory> getReOrderedChildrenCategories(long lv1CategoryId)
      throws SystemException{
    List<AssetCategory> childrenAssetCategories = Lists
        .newArrayList(AssetCategoryLocalServiceUtil.getChildCategories(lv1CategoryId)); 
    Collections.sort(childrenAssetCategories, new Ordering<AssetCategory>(){
      @Override
      public int compare(AssetCategory left, AssetCategory right){
        return Long.compare(left.getCategoryId(), right.getCategoryId());
      }
    });
    return childrenAssetCategories;
  }
  
  @RequestMapping(value="/{scienceAppId}/inputports")
  public @ResponseBody String getScienceAppInputPorts(
      @PathVariable("scienceAppId") long scienceAppId,
      HttpServletRequest request)
      throws SystemException, PortalException{
    try{
      long inputportCnt = ScienceAppInputPortsLocalServiceUtil
          .getScienceAppInputPortsesCount(scienceAppId);
      if(inputportCnt > 0){
        return ScienceAppInputPortsLocalServiceUtil.getInputPortsJsonString(scienceAppId);
      }else{
        return "{}";
      }
    }catch (Exception e){
      log.error("error", e);
      throw e;
    }
  }
  
  @RequestMapping(value="/{scienceAppId}/outputports")
  public @ResponseBody String getScienceAppOutputPorts(
      @PathVariable("scienceAppId") long scienceAppId,
      HttpServletRequest request)
          throws SystemException, PortalException{
    try{
      long outputportCnt = ScienceAppOutputPortsLocalServiceUtil
          .getScienceAppOutputPortsesCount(scienceAppId);
      if(outputportCnt > 0){
        return ScienceAppOutputPortsLocalServiceUtil.getOutputPortsJsonString(scienceAppId);
      }else{
        return "{}";
      }
    }catch (Exception e){
      log.error("error", e);
      throw e;
    }
  }
  
  @RequestMapping(value = {"/inputports/editor"})
  public @ResponseBody List<Map<String, Object>> getScienceAppPortEditors(
      @RequestParam("name") String name,
      @RequestParam("version") String version,
      HttpServletRequest request)
          throws SystemException, PortalException{
    try{
      DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(name, version);
      return WorkflowSimulationJobLocalServiceUtil.getDataTypeEditors(dataType);
    }catch (Exception e){
      log.error("error", e);
      throw e;
    }
  }
  
  @RequestMapping(value = {"/outputports/analyzer"})
  public @ResponseBody List<Map<String, Object>> getScienceAppPortAnalyzers(
      @RequestParam("name") String name,
      @RequestParam("version") String version,
      HttpServletRequest request)
          throws SystemException, PortalException{
    try{
      DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(name, version);
      return WorkflowSimulationJobLocalServiceUtil.getDataTypeAnalyzers(dataType);
    }catch (Exception e){
      log.error("error", e);
      throw e;
    }
  }
  
  @RequestMapping(value = {"/{scienceAppId}/inputports/editor/default"})
  public @ResponseBody Map<String, Object> getScienceAppPortDefaultEditors(
      @PathVariable("scienceAppId") long scienceAppId,
      @RequestParam("name") String name,
      @RequestParam("version") String version,
      HttpServletRequest request)
          throws SystemException, PortalException{
    DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(name, version);
    return WorkflowSimulationJobLocalServiceUtil.getDataTypeDefaultEditor(dataType);
  }
  
  @RequestMapping(value = {"/{scienceAppId}/outputports/analyzer/default"})
  public @ResponseBody Map<String, Object> getScienceAppPortDefaultAnalyzer(
      @PathVariable("scienceAppId") long scienceAppId,
      @RequestParam("name") String name,
      @RequestParam("version") String version,
      HttpServletRequest request)
          throws SystemException, PortalException{
    DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(name, version);
    return WorkflowSimulationJobLocalServiceUtil.getDataTypeDefaultAnalyzer(dataType);
  }
  
  @RequestMapping(value = {
      "/{scienceAppId}/outputports/{portTypeId}"})
  public @ResponseBody List<Map<String, Object>> getScienceAppAnalyzers(
      @PathVariable("scienceAppId") long scienceAppId,
      @PathVariable("portTypeId") long portTypeId,
      HttpServletRequest request)
          throws SystemException, PortalException{
    try{
      return PortTypeAnalyzerLinkLocalServiceUtil
          .findByPortTypeId(portTypeId, PortalUtil.getLocale(request));
    }catch (Exception e){
      log.error("error", e);
      throw e;
    }
  }
}
