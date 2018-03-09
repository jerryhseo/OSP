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

package org.kisti.edison.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WorkflowLocalService}.
 *
 * @author EDISON
 * @see WorkflowLocalService
 * @generated
 */
public class WorkflowLocalServiceWrapper implements WorkflowLocalService,
	ServiceWrapper<WorkflowLocalService> {
	public WorkflowLocalServiceWrapper(
		WorkflowLocalService workflowLocalService) {
		_workflowLocalService = workflowLocalService;
	}

	/**
	* Adds the workflow to the database. Also notifies the appropriate model listeners.
	*
	* @param workflow the workflow
	* @return the workflow that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.Workflow addWorkflow(
		org.kisti.edison.model.Workflow workflow)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.addWorkflow(workflow);
	}

	/**
	* Creates a new workflow with the primary key. Does not add the workflow to the database.
	*
	* @param workflowId the primary key for the new workflow
	* @return the new workflow
	*/
	@Override
	public org.kisti.edison.model.Workflow createWorkflow(long workflowId) {
		return _workflowLocalService.createWorkflow(workflowId);
	}

	/**
	* Deletes the workflow with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowId the primary key of the workflow
	* @return the workflow that was removed
	* @throws PortalException if a workflow with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.Workflow deleteWorkflow(long workflowId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.deleteWorkflow(workflowId);
	}

	/**
	* Deletes the workflow from the database. Also notifies the appropriate model listeners.
	*
	* @param workflow the workflow
	* @return the workflow that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.Workflow deleteWorkflow(
		org.kisti.edison.model.Workflow workflow)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.deleteWorkflow(workflow);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workflowLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.kisti.edison.model.Workflow fetchWorkflow(long workflowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.fetchWorkflow(workflowId);
	}

	/**
	* Returns the workflow with the primary key.
	*
	* @param workflowId the primary key of the workflow
	* @return the workflow
	* @throws PortalException if a workflow with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.Workflow getWorkflow(long workflowId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getWorkflow(workflowId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the workflows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflows
	* @param end the upper bound of the range of workflows (not inclusive)
	* @return the range of workflows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.Workflow> getWorkflows(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getWorkflows(start, end);
	}

	/**
	* Returns the number of workflows.
	*
	* @return the number of workflows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getWorkflowsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getWorkflowsCount();
	}

	/**
	* Updates the workflow in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workflow the workflow
	* @return the workflow that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.Workflow updateWorkflow(
		org.kisti.edison.model.Workflow workflow)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.updateWorkflow(workflow);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowInstanceWorkflow(long workflowInstanceId,
		long workflowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowLocalService.addWorkflowInstanceWorkflow(workflowInstanceId,
			workflowId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowInstanceWorkflow(long workflowInstanceId,
		org.kisti.edison.model.Workflow workflow)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowLocalService.addWorkflowInstanceWorkflow(workflowInstanceId,
			workflow);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowInstanceWorkflows(long workflowInstanceId,
		long[] workflowIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowLocalService.addWorkflowInstanceWorkflows(workflowInstanceId,
			workflowIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowInstanceWorkflows(long workflowInstanceId,
		java.util.List<org.kisti.edison.model.Workflow> Workflows)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowLocalService.addWorkflowInstanceWorkflows(workflowInstanceId,
			Workflows);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void clearWorkflowInstanceWorkflows(long workflowInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowLocalService.clearWorkflowInstanceWorkflows(workflowInstanceId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowInstanceWorkflow(long workflowInstanceId,
		long workflowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowLocalService.deleteWorkflowInstanceWorkflow(workflowInstanceId,
			workflowId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowInstanceWorkflow(long workflowInstanceId,
		org.kisti.edison.model.Workflow workflow)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowLocalService.deleteWorkflowInstanceWorkflow(workflowInstanceId,
			workflow);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowInstanceWorkflows(long workflowInstanceId,
		long[] workflowIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowLocalService.deleteWorkflowInstanceWorkflows(workflowInstanceId,
			workflowIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowInstanceWorkflows(long workflowInstanceId,
		java.util.List<org.kisti.edison.model.Workflow> Workflows)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowLocalService.deleteWorkflowInstanceWorkflows(workflowInstanceId,
			Workflows);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.Workflow> getWorkflowInstanceWorkflows(
		long workflowInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getWorkflowInstanceWorkflows(workflowInstanceId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.Workflow> getWorkflowInstanceWorkflows(
		long workflowInstanceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getWorkflowInstanceWorkflows(workflowInstanceId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.Workflow> getWorkflowInstanceWorkflows(
		long workflowInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getWorkflowInstanceWorkflows(workflowInstanceId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getWorkflowInstanceWorkflowsCount(long workflowInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getWorkflowInstanceWorkflowsCount(workflowInstanceId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public boolean hasWorkflowInstanceWorkflow(long workflowInstanceId,
		long workflowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.hasWorkflowInstanceWorkflow(workflowInstanceId,
			workflowId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public boolean hasWorkflowInstanceWorkflows(long workflowInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.hasWorkflowInstanceWorkflows(workflowInstanceId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void setWorkflowInstanceWorkflows(long workflowInstanceId,
		long[] workflowIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowLocalService.setWorkflowInstanceWorkflows(workflowInstanceId,
			workflowIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _workflowLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_workflowLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _workflowLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public org.kisti.edison.model.Workflow createWorkflow(
		java.lang.String screenLogic, java.lang.String title,
		java.lang.String description,
		javax.servlet.http.HttpServletRequest request)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.createWorkflow(screenLogic, title,
			description, request);
	}

	@Override
	public org.kisti.edison.model.Workflow createWorkflow()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.createWorkflow();
	}

	@Override
	public org.kisti.edison.model.Workflow copyWorkflow(long sourceWorkflowId,
		java.lang.String newTitle, java.lang.String descrption,
		javax.servlet.http.HttpServletRequest request)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.copyWorkflow(sourceWorkflowId, newTitle,
			descrption, request);
	}

	@Override
	public org.kisti.edison.model.Workflow copyWorkflow(long sourceWorkflowId,
		java.lang.String newTitle, javax.servlet.http.HttpServletRequest request)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.copyWorkflow(sourceWorkflowId, newTitle,
			request);
	}

	@Override
	public org.kisti.edison.model.Workflow updateWorkflow(long workflowId,
		java.util.Map<java.lang.String, java.lang.Object> workflowParam,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.updateWorkflow(workflowId, workflowParam,
			locale);
	}

	@Override
	public org.kisti.edison.model.Workflow updateWorkflowTutorial(
		long workflowId, long tutorialFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.updateWorkflowTutorial(workflowId,
			tutorialFileEntryId);
	}

	@Override
	public org.kisti.edison.model.Workflow updateWorkflow(
		java.util.Map<java.lang.String, java.lang.Object> workflowParam)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.updateWorkflow(workflowParam);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveWorkflows(
		java.util.Map<java.lang.String, java.lang.Object> searchParam,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.retrieveWorkflows(searchParam, locale);
	}

	@Override
	public org.kisti.edison.model.WorkflowInstance pauseWorkflowInstance(
		long workflowInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.pauseWorkflowInstance(workflowInstanceId);
	}

	@Override
	public org.kisti.edison.model.WorkflowInstance resumeWorkflowInstance(
		long workflowInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.resumeWorkflowInstance(workflowInstanceId);
	}

	@Override
	public org.kisti.edison.model.WorkflowInstance deleteWorkflowInstance(
		long workflowInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.deleteWorkflowInstance(workflowInstanceId);
	}

	@Override
	public org.kisti.edison.model.Workflow deleteWorkflowAndInstances(
		long workflowId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.deleteWorkflowAndInstances(workflowId);
	}

	@Override
	public org.kisti.edison.model.WorkflowInstance runWorkflow(
		long workflowId,
		java.util.Map<java.lang.String, java.lang.Object> workflowParams,
		javax.servlet.http.HttpServletRequest request)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.runWorkflow(workflowId, workflowParams,
			request);
	}

	@Override
	public org.kisti.edison.model.WorkflowInstance runWorkflowInstance(
		long workflowInstanceId,
		java.util.Map<java.lang.String, java.lang.Object> workflowParams,
		javax.servlet.http.HttpServletRequest request)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.runWorkflowInstance(workflowInstanceId,
			workflowParams, request);
	}

	@Override
	public int startWorkflowInstance(long workflowInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.startWorkflowInstance(workflowInstanceId);
	}

	@Override
	public org.kisti.edison.model.WorkflowInstance createWorkflowInstance(
		java.lang.String workflowUUID,
		org.kisti.edison.model.Workflow workflow,
		javax.servlet.http.HttpServletRequest request)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.createWorkflowInstance(workflowUUID,
			workflow, request);
	}

	@Override
	public org.kisti.edison.model.WorkflowInstance getWorkflowStatus(
		long workflowInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.getWorkflowStatus(workflowInstanceId);
	}

	@Override
	public org.kisti.edison.model.WorkflowInstance updateWorkflowInstance(
		org.codehaus.jackson.JsonNode workflowStatusJson,
		org.kisti.edison.model.WorkflowInstance workflowInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.updateWorkflowInstance(workflowStatusJson,
			workflowInstance);
	}

	@Override
	public org.codehaus.jackson.JsonNode askForWorkflowStart(
		java.lang.String workflowUUID) throws java.io.IOException {
		return _workflowLocalService.askForWorkflowStart(workflowUUID);
	}

	@Override
	public org.codehaus.jackson.JsonNode askForWorkflowStatus(
		java.lang.String workflowUUID) throws java.io.IOException {
		return _workflowLocalService.askForWorkflowStatus(workflowUUID);
	}

	@Override
	public java.lang.String getWorkflowSimulationLog(
		java.lang.String workflowUUID, java.lang.String simulationUUID,
		java.lang.String ibAccessToken) throws java.io.IOException {
		return _workflowLocalService.getWorkflowSimulationLog(workflowUUID,
			simulationUUID, ibAccessToken);
	}

	@Override
	public java.lang.String getWorkflowSimulationErrorLog(
		java.lang.String workflowUUID, java.lang.String simulationUUID,
		java.lang.String ibAccessToken) throws java.io.IOException {
		return _workflowLocalService.getWorkflowSimulationErrorLog(workflowUUID,
			simulationUUID, ibAccessToken);
	}

	@Override
	public java.lang.String getWorkflowJobIntermediateResult(
		java.lang.String jobUuid, java.lang.String ibAccessToken)
		throws java.io.IOException {
		return _workflowLocalService.getWorkflowJobIntermediateResult(jobUuid,
			ibAccessToken);
	}

	@Override
	public java.io.File downloadFileApi(com.liferay.portal.model.User user,
		long appGroupId, com.liferay.portal.kernel.json.JSONObject inputport)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.downloadFileApi(user, appGroupId, inputport);
	}

	@Override
	public java.lang.String uploadFileToIcebreaker(long appGroupId,
		java.lang.String icebreakerVcToken, java.io.File uploadFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.net.MalformedURLException {
		return _workflowLocalService.uploadFileToIcebreaker(appGroupId,
			icebreakerVcToken, uploadFile);
	}

	@Override
	public java.lang.String uploadFileToIcebreaker(long appGroupId,
		java.lang.String icebreakerVcToken, java.io.InputStream fileContent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.uploadFileToIcebreaker(appGroupId,
			icebreakerVcToken, fileContent);
	}

	@Override
	public java.lang.String uploadFileToIcebreaker(long appGroupId,
		java.lang.String icebreakerVcToken, java.lang.String fileContent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowLocalService.uploadFileToIcebreaker(appGroupId,
			icebreakerVcToken, fileContent);
	}

	@Override
	public java.util.List<org.kisti.edison.model.Workflow> getWorkflowsByLikeSearch(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getWorkflowsByLikeSearch(searchParam);
	}

	@Override
	public long getCountWorkflowsByLikeSearch(
		java.util.Map<java.lang.String, java.lang.Object> serachParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getCountWorkflowsByLikeSearch(serachParam);
	}

	@Override
	public long getCountWorkflowInstanceByUserId(
		com.liferay.portal.model.User user,
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getCountWorkflowInstanceByUserId(user,
			params);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getWorkflowInstanceByUserId(
		com.liferay.portal.model.User user,
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getWorkflowInstanceByUserId(user, params,
			locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getDataTypeEditors(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getDataTypeEditors(dataType);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getDataTypeDefaultEditor(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getDataTypeDefaultEditor(dataType);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getDataTypeDefaultAnalyzer(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getDataTypeDefaultAnalyzer(dataType);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getDataTypeAnalyzers(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getDataTypeAnalyzers(dataType);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getRootSiteAssetCategries(
		long companyGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getRootSiteAssetCategries(companyGroupId,
			groupId);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getSiteAssetCategoriesByParentId(
		long companyGroupId, long groupId, long parentCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getSiteAssetCategoriesByParentId(companyGroupId,
			groupId, parentCategoryId);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getLv1Categories(
		long companyGroupId, long groupId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowLocalService.getLv1Categories(companyGroupId, groupId,
			locale);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WorkflowLocalService getWrappedWorkflowLocalService() {
		return _workflowLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWorkflowLocalService(
		WorkflowLocalService workflowLocalService) {
		_workflowLocalService = workflowLocalService;
	}

	@Override
	public WorkflowLocalService getWrappedService() {
		return _workflowLocalService;
	}

	@Override
	public void setWrappedService(WorkflowLocalService workflowLocalService) {
		_workflowLocalService = workflowLocalService;
	}

	private WorkflowLocalService _workflowLocalService;
}