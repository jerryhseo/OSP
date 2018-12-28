/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.kisti.edison.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.Workflow;
import org.kisti.edison.service.WorkflowLocalServiceUtil;
import org.kisti.edison.service.base.WorkflowLocalServiceBaseImpl;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonPropsUtil;
import org.springframework.util.StringUtils;

import com.google.common.collect.Ordering;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

/**
 * The implementation of the workflow local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.kisti.edison.service.WorkflowLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.service.base.WorkflowLocalServiceBaseImpl
 * @see org.kisti.edison.service.WorkflowLocalServiceUtil
 */
public class WorkflowLocalServiceImpl extends WorkflowLocalServiceBaseImpl{
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link
     * org.kisti.edison.service.WorkflowLocalServiceUtil} to access the workflow
     * local service.
     */

    private static Log log = LogFactory.getLog(WorkflowLocalServiceImpl.class);

    // 개발서버 icebreaker file :
    // http://150.183.247.210:8080/ldap/api/file/download?id=
    // 실서버 WORKFLOW IP : 150.183.247.103:9000
    private final String ICEBREAKER_UPLOAD_CLUSTER = "EDISON-CFD";
    // private final String WORKFLOW_ENGINE_URL_PRIVATE =
    // "http://150.183.247.210:8090";
    // private final String WORKFLOW_ENGINE_URL_PRIVATE =
    // "http://10.183.100.103:8090/simflow";
    private final String WORKFLOW_ENGINE_URL_PRIVATE = PropsUtil.get(EdisonPropsUtil.WORKFLOW_ENGIN_URL);
    private final String WORKFLOW_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/WORKFLOW_TEMP";
    // private final String WORKFLOW_INSTANCE_PATH = "/EDISON/LDAP/DATA/";
    private static final String DYNAMIC_CONVERTER = "DynamicConverter";
    private static final String CONTROLLER = "Controller";
    private Ordering<String> scriptFirst = new Ordering<String>(){
        @Override
        public int compare(String left, String right){
            if(left.equals("$cmdscript")){
                return -1;
            }
            if(right.equals("$cmdscript")){
                return 1;
            }
            return 0;
        }
    };

    public Workflow createWorkflow(String screenLogic, String title, String description, HttpServletRequest request)
        throws SystemException, PortalException{
        Workflow workflow = WorkflowLocalServiceUtil.createWorkflow();
        User user = PortalUtil.getUser(request);
        workflow.setTitle(title);
        workflow.setScreenLogic(screenLogic);
        workflow.setUserId(user.getUserId());
        workflow.setDescription(description);
        workflow.setCompanyId(PortalUtil.getCompany(request).getCompanyId());
        workflow.setCreateDate(new Date());
        return super.workflowLocalService.addWorkflow(workflow);
    }

    public Workflow createWorkflow() throws SystemException{
        long workflowId = super.counterLocalService.increment();
        return super.workflowLocalService.createWorkflow(workflowId);
    }

    public Workflow copyWorkflow(long sourceWorkflowId, String newTitle, String descrption, HttpServletRequest request)
        throws SystemException, PortalException{

        return copyWorkflow(sourceWorkflowId, newTitle, descrption, null, request);
    }

    public Workflow copyWorkflow(long sourceWorkflowId, String newTitle, String descrption, String screenLogic,
        HttpServletRequest request) throws SystemException, PortalException{
        User user = PortalUtil.getUser(request);
        // Locale locale = PortalUtil.getLocale(request);
        Workflow targetWorkflow = super.workflowLocalService.getWorkflow(sourceWorkflowId);
        targetWorkflow.setParentWorkflowId(sourceWorkflowId);
        targetWorkflow.setTitle(newTitle);
        targetWorkflow.setDescription(descrption);
        if(screenLogic != null && StringUtils.hasText(screenLogic)){
            targetWorkflow.setScreenLogic(screenLogic);
        }
        targetWorkflow.setWorkflowId(super.counterLocalService.increment());
        targetWorkflow.setCreateDate(new Date());
        targetWorkflow.setUserId(user.getUserId());
        targetWorkflow.setCompanyId(PortalUtil.getCompany(request).getCompanyId());
        return super.workflowLocalService.addWorkflow(targetWorkflow);
    }

    public Workflow copyWorkflow(long sourceWorkflowId, String newTitle, HttpServletRequest request)
        throws SystemException, PortalException{
        User user = PortalUtil.getUser(request);
        Locale locale = PortalUtil.getLocale(request);
        Workflow targetWorkflow = super.workflowLocalService.getWorkflow(sourceWorkflowId);
        newTitle = StringUtils.hasText(newTitle) ? newTitle : "copied from " + targetWorkflow.getTitle(locale);
        targetWorkflow.setParentWorkflowId(sourceWorkflowId);
        targetWorkflow.setTitle(newTitle);
        targetWorkflow.setWorkflowId(super.counterLocalService.increment());
        targetWorkflow.setCreateDate(new Date());
        targetWorkflow.setUserId(user.getUserId());
        targetWorkflow.setCompanyId(PortalUtil.getCompany(request).getCompanyId());
        return super.workflowLocalService.addWorkflow(targetWorkflow);
    }

    public Workflow updateWorkflow(long workflowId, Map<String, Object> workflowParam, Locale locale)
        throws SystemException, PortalException{
        Workflow workflow = WorkflowLocalServiceUtil.getWorkflow(workflowId);
        workflow.setWorkflowModelAttributes(workflowParam, locale);
        workflow.setModifiedDate(new Date());
        return super.workflowLocalService.updateWorkflow(workflow);
    }

    public Workflow updateWorkflowTutorial(long workflowId, long tutorialFileEntryId)
        throws PortalException, SystemException{
        Workflow workflow = WorkflowLocalServiceUtil.getWorkflow(workflowId);
        return super.workflowLocalService.updateWorkflow(workflow);
    }

    public Workflow updateWorkflow(Map<String, Object> workflowParam) throws SystemException, PortalException{
        long workflowId = GetterUtil.getLong(workflowParam.get("workflowId"));
        Workflow workflow = WorkflowLocalServiceUtil.getWorkflow(workflowId);
        String serviceLang = "";
        String[] serviceLangArray = CustomUtil.paramToArray(workflowParam.get("select_languageId"));
        if(serviceLangArray != null && serviceLangArray.length > 0){
            for(int i = 0; i < serviceLangArray.length; i++){
                serviceLang += CustomUtil.strNull(serviceLangArray[i]) + ",";
            }
        }
        workflow.setTitleMap(CustomUtil.getLocalizationMap(workflowParam, "title"));
        workflow.setDescriptionMap(CustomUtil.getLocalizationMap(workflowParam, "description"));
        workflow.setModifiedDate(new Date());
        return super.workflowLocalService.updateWorkflow(workflow);
    }

    public List<Map<String, Object>> retrieveWorkflows(Map<String, Object> searchParam, Locale locale)
        throws SystemException{
        return workflowFinder.retrieveWorkflows(searchParam, locale);
    }
    
    @SuppressWarnings("unchecked")
    public List<Workflow> getWorkflowsByLikeSearch(Map<String, Object> searchParam) throws SystemException{
        int begin = GetterUtil.getInteger(searchParam.get("begin"), -1);
        int end = GetterUtil.getInteger(searchParam.get("end"), -1);
        DynamicQuery query = makeWorkflowListWhereClause(searchParam);
        query.addOrder(OrderFactoryUtil.desc("createDate"));
        return (List<Workflow>) super.workflowLocalService.dynamicQuery(query, begin, end);
    }

    public long getCountWorkflowsByLikeSearch(Map<String, Object> serachParam) throws SystemException{
        DynamicQuery query = makeWorkflowListWhereClause(serachParam);
        return workflowLocalService.dynamicQueryCount(query);
    }
    
    private DynamicQuery makeWorkflowListWhereClause(Map<String, Object> serachParam){
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(Workflow.class);
        Iterator<String> keys = serachParam.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            if("begin".equals(key) || "end".equals(key)){
                continue;
            }
            if("title".equals(key)){
                query.add(RestrictionsFactoryUtil.like(key, "%" + serachParam.get(key) + "%"));
            }
            if("userId".equals(key)){
                query.add(RestrictionsFactoryUtil.eq(key, serachParam.get(key)));
            }
        }
        return query;
    }

    // ======================================================================== Workflow
}