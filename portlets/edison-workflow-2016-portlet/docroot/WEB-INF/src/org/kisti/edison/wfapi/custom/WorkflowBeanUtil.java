package org.kisti.edison.wfapi.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.kisti.edison.model.Workflow;
import org.kisti.edison.model.WorkflowInstance;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.wfapi.custom.exception.EdisonWorkflowException;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetCategory;
import com.rits.cloning.Cloner;

public class WorkflowBeanUtil{
  private static Cloner cloner = new Cloner();
  
  public static long specificSiteGroupId(PortletRequest request) throws SystemException{
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
      long companyId = themeDisplay.getCompanyId();
      List<Group> parentGroupList = GroupLocalServiceUtil.getGroups(companyId, 0, true);
      Group parentGroup = null;
      for(Group group:parentGroupList){
        if(StringUtil.toUpperCase(group.getName()).equals("GUEST")){
          parentGroup = group;
          break;
        }
      }
      List<Group> groupList = CustomUtil.getGroupIdASC(GroupLocalServiceUtil.getGroups(
          companyId, parentGroup.getGroupId(), true));
      return groupList.size() > 0 ? groupList.get(0).getGroupId() : 0;
  }
  
  public static List<Map<String, Object>> transformToModelAttributes(
      List<?> models) throws EdisonWorkflowException{
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    for(Object obj : models){
      if(!(obj instanceof BaseModel<?>)){
        throw new EdisonWorkflowException();
      }
      BaseModel<?> model = (BaseModel<?>)obj;
      result.add(model.getModelAttributes());
    }
    return result;
  }
  
  public static Map<String, Object> transformToModelAttributes(BaseModel<?> model){
    return model.getModelAttributes();
  }
  
  public static List<Map<String, Object>> scienceAppToJstreeModel(
      List<ScienceApp> scienceApps, Locale locale, long categoryId) {
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    for(ScienceApp scienceApp : scienceApps){
      result.add(scienceAppToJstreeModel(scienceApp, locale, categoryId));
    }
    return result;
  }
  
  public static Map<String, Object> scienceAppToJstreeModel(
      ScienceApp scienceApp, Locale locale, long categoryId){
    Map<String, Object> modelMap = scienceApp.getModelAttributes(); 
    modelMap.put("text", scienceApp.getName() + "(" + scienceApp.getTitle(locale) + ")");
    modelMap.put("data", cloner.deepClone(modelMap));
    modelMap.put("id", categoryId+ "_" +scienceApp.getScienceAppId());
    modelMap.put("parent", categoryId);
    modelMap.put("type", "app");
    return modelMap;
  }
  
  public static List<Map<String, Object>> assetCategoryToJstreeModel(
      List<AssetCategory> assetCategories, Locale locale) {
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    for(AssetCategory assetCategory : assetCategories){
      result.add(assetCategoryToJstreeModel(assetCategory, locale));
    }
    return result;
  }

  public static Map<String, Object> assetCategoryToJstreeModel(
      AssetCategory assetCategory, Locale locale){
    Map<String, Object> modelMap = assetCategory.getModelAttributes(); 
    modelMap.put("data", new HashMap<>(modelMap));
    modelMap.put("id", assetCategory.getCategoryId());
    modelMap.put("parent", "#");
    modelMap.put("text", assetCategory.getTitle(locale));
    if(assetCategory.getParentCategoryId() == 0){
      modelMap.put("type", "category");
    }else{
      modelMap.put("type", "subCategory");
    }
    return modelMap;
  }
  
  public static List<Map<String, Object>> groupToJstreeModel(
      List<Group> groups, Locale locale) {
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    for(Group group : groups){
      result.add(groupToJstreeModel(group, locale));
    }
    return result;
  }
  
  public static Map<String, Object> groupToJstreeModel(
      Group group, Locale locale){
    Map<String, Object> modelMap = group.getModelAttributes(); 
    modelMap.put("data", new HashMap<>(modelMap));
    modelMap.put("id", group.getGroupId());
    modelMap.put("text", LanguageUtil.get(locale, group.getName()));
    modelMap.put("type", "group");
    return modelMap;
  }
  
  public static List<Map<String, Object>> workflowInstanceToJstreeModel(
      List<WorkflowInstance> workflowInstances, Locale locale) {
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    for(WorkflowInstance workflowInstance : workflowInstances){
      result.add(workflowInstanceToJstreeModel(workflowInstance, locale));
    }
    return result;
  }
  
  public static Map<String, Object> workflowInstanceToJstreeModel(WorkflowInstance workflowInstance, Locale locale){
    Map<String, Object> modelMap = workflowInstance.getModelAttributes(); 
    modelMap.put("data", new HashMap<>(modelMap));
    modelMap.put("id", workflowInstance.getWorkflowInstanceId());
    modelMap.put("text", workflowInstance.getTitle(locale) + " - " 
        + StringUtil.upperCaseFirstLetter(workflowInstance.getStatus().toLowerCase()));
    modelMap.put("parent", workflowInstance.getWorkflowId());
    modelMap.put("type", "instance");
    
    return modelMap;
  }
  
  public static List<Map<String, Object>> workflowToJstreeModel(
      List<Workflow> workflows, Locale locale) {
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    for(Workflow workflow : workflows){
      result.add(workflowToJstreeModel(workflow, locale));
    }
    return result;
  }
  
  public static Map<String, Object> workflowToJstreeModel(Workflow workflow, String root, Locale locale){
      Map<String, Object> modelMap = workflow.getModelAttributes(); 
      modelMap.put("data", new HashMap<>(modelMap));
      modelMap.put("id", workflow.getWorkflowId());
      modelMap.put("text", workflow.getTitle(locale));
      modelMap.put("parent", root);
      modelMap.put("type", "workflow");
      return modelMap;
  }
  
  public static Map<String, Object> workflowToJstreeModel(Workflow workflow, Locale locale){
      return workflowToJstreeModel(workflow, "currentWorkflowTop", locale);
  }
  
  public static List<Map<String, Object>> workflowMapToJstreeModel(
      List<Map<String, Object>> workflows, Locale locale) {
      List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
      for(Map<String, Object> workflow : workflows){
          result.add(workflowMapToJstreeModel(workflow, locale));
      }
      return result;
  }
  
  public static Map<String, Object> workflowMapToJstreeModel(Map<String, Object> workflow, Locale locale){
      workflow.put("data", new HashMap<>(workflow));
      workflow.put("id", workflow.get("workflowId"));
      workflow.put("text", workflow.get("title"));
      workflow.put("parent", "currentWorkflowTop");
      workflow.put("type", "workflow");
      return workflow;
  }
}
