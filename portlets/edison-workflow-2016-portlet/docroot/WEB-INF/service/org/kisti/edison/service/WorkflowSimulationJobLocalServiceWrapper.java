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
 * Provides a wrapper for {@link WorkflowSimulationJobLocalService}.
 *
 * @author EDISON
 * @see WorkflowSimulationJobLocalService
 * @generated
 */
public class WorkflowSimulationJobLocalServiceWrapper
	implements WorkflowSimulationJobLocalService,
		ServiceWrapper<WorkflowSimulationJobLocalService> {
	public WorkflowSimulationJobLocalServiceWrapper(
		WorkflowSimulationJobLocalService workflowSimulationJobLocalService) {
		_workflowSimulationJobLocalService = workflowSimulationJobLocalService;
	}

	/**
	* Adds the workflow simulation job to the database. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulationJob the workflow simulation job
	* @return the workflow simulation job that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulationJob addWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.addWorkflowSimulationJob(workflowSimulationJob);
	}

	/**
	* Creates a new workflow simulation job with the primary key. Does not add the workflow simulation job to the database.
	*
	* @param simulationJobId the primary key for the new workflow simulation job
	* @return the new workflow simulation job
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulationJob createWorkflowSimulationJob(
		long simulationJobId) {
		return _workflowSimulationJobLocalService.createWorkflowSimulationJob(simulationJobId);
	}

	/**
	* Deletes the workflow simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJobId the primary key of the workflow simulation job
	* @return the workflow simulation job that was removed
	* @throws PortalException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulationJob deleteWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.deleteWorkflowSimulationJob(simulationJobId);
	}

	/**
	* Deletes the workflow simulation job from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulationJob the workflow simulation job
	* @return the workflow simulation job that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulationJob deleteWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.deleteWorkflowSimulationJob(workflowSimulationJob);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workflowSimulationJobLocalService.dynamicQuery();
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
		return _workflowSimulationJobLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workflowSimulationJobLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workflowSimulationJobLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _workflowSimulationJobLocalService.dynamicQueryCount(dynamicQuery);
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
		return _workflowSimulationJobLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob fetchWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.fetchWorkflowSimulationJob(simulationJobId);
	}

	/**
	* Returns the workflow simulation job with the primary key.
	*
	* @param simulationJobId the primary key of the workflow simulation job
	* @return the workflow simulation job
	* @throws PortalException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulationJob getWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationJob(simulationJobId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the workflow simulation jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @return the range of workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationJobs(start,
			end);
	}

	/**
	* Returns the number of workflow simulation jobs.
	*
	* @return the number of workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getWorkflowSimulationJobsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationJobsCount();
	}

	/**
	* Updates the workflow simulation job in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulationJob the workflow simulation job
	* @return the workflow simulation job that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulationJob updateWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.updateWorkflowSimulationJob(workflowSimulationJob);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowSimulationWorkflowSimulationJob(long simulationId,
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJobLocalService.addWorkflowSimulationWorkflowSimulationJob(simulationId,
			simulationJobId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowSimulationWorkflowSimulationJob(long simulationId,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJobLocalService.addWorkflowSimulationWorkflowSimulationJob(simulationId,
			workflowSimulationJob);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowSimulationWorkflowSimulationJobs(long simulationId,
		long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJobLocalService.addWorkflowSimulationWorkflowSimulationJobs(simulationId,
			simulationJobIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowSimulationWorkflowSimulationJobs(long simulationId,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> WorkflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJobLocalService.addWorkflowSimulationWorkflowSimulationJobs(simulationId,
			WorkflowSimulationJobs);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void clearWorkflowSimulationWorkflowSimulationJobs(long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJobLocalService.clearWorkflowSimulationWorkflowSimulationJobs(simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowSimulationWorkflowSimulationJob(
		long simulationId, long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJobLocalService.deleteWorkflowSimulationWorkflowSimulationJob(simulationId,
			simulationJobId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowSimulationWorkflowSimulationJob(
		long simulationId,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJobLocalService.deleteWorkflowSimulationWorkflowSimulationJob(simulationId,
			workflowSimulationJob);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJobLocalService.deleteWorkflowSimulationWorkflowSimulationJobs(simulationId,
			simulationJobIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowSimulationWorkflowSimulationJobs(
		long simulationId,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> WorkflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJobLocalService.deleteWorkflowSimulationWorkflowSimulationJobs(simulationId,
			WorkflowSimulationJobs);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationWorkflowSimulationJobs(simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationWorkflowSimulationJobs(simulationId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationWorkflowSimulationJobs(simulationId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getWorkflowSimulationWorkflowSimulationJobsCount(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationWorkflowSimulationJobsCount(simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public boolean hasWorkflowSimulationWorkflowSimulationJob(
		long simulationId, long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.hasWorkflowSimulationWorkflowSimulationJob(simulationId,
			simulationJobId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public boolean hasWorkflowSimulationWorkflowSimulationJobs(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.hasWorkflowSimulationWorkflowSimulationJobs(simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void setWorkflowSimulationWorkflowSimulationJobs(long simulationId,
		long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJobLocalService.setWorkflowSimulationWorkflowSimulationJobs(simulationId,
			simulationJobIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _workflowSimulationJobLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_workflowSimulationJobLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _workflowSimulationJobLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long simulationId, java.lang.String title, long userId, int begin,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationJobs(simulationId,
			title, userId, begin, end);
	}

	@Override
	public int countWorkflowSimulationJobs(long simulationId,
		java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.countWorkflowSimulationJobs(simulationId,
			title, userId);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob updateWorkflowSimulationJob(
		long simulationJobId,
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.updateWorkflowSimulationJob(simulationJobId,
			params);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob copyWorkflowSimulationJob(
		long sourceSimulationJobId,
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.copyWorkflowSimulationJob(sourceSimulationJobId,
			params);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob createWorkflowSimulationJob(
		long simulationId,
		java.util.Map<java.lang.String, java.lang.Object> params,
		javax.servlet.http.HttpServletRequest request)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.createWorkflowSimulationJob(simulationId,
			params, request);
	}

	@Override
	public java.lang.String getSimulationJobSeq(long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getSimulationJobSeq(simulationId);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob createSimulationJob(
		org.kisti.edison.model.WorkflowSimulation simulation,
		org.kisti.edison.model.Workflow workflow,
		java.lang.String simulationJobTitle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.createSimulationJob(simulation,
			workflow, simulationJobTitle);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob createWorkflowSimulationJob()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.createWorkflowSimulationJob();
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob createWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.createWorkflowSimulationJob(simulationJob);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob startWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.startWorkflowSimulationJob(simulationJobId);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob startWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.startWorkflowSimulationJob(simulationJob);
	}

	@Override
	public org.codehaus.jackson.JsonNode askForWorkflowStart(
		java.lang.String workflowUUID)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowSimulationJobLocalService.askForWorkflowStart(workflowUUID);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob getWorkflowStatus(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.getWorkflowStatus(simulationJobId);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulation deleteSimulationAndJobs(
		long simulationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.deleteSimulationAndJobs(simulationId);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob deleteWorkflowSimulationJobWitEngine(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.deleteWorkflowSimulationJobWitEngine(simulationJobId);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob deleteWorkflowSimulationJobWitEngine(
		org.kisti.edison.model.WorkflowSimulationJob job)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.deleteWorkflowSimulationJobWitEngine(job);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob pauseWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.pauseWorkflowSimulationJob(simulationJobId);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob resumeWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.resumeWorkflowSimulationJob(simulationJobId);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob updateWorkflowSimulationJob(
		org.codehaus.jackson.JsonNode workflowStatusJson,
		org.kisti.edison.model.WorkflowSimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.updateWorkflowSimulationJob(workflowStatusJson,
			simulationJob);
	}

	@Override
	public org.codehaus.jackson.JsonNode askForWorkflowStatus(
		java.lang.String workflowUUID)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowSimulationJobLocalService.askForWorkflowStatus(workflowUUID);
	}

	@Override
	public java.lang.String getWorkflowSimulationLog(
		java.lang.String workflowUUID, java.lang.String simulationUUID,
		java.lang.String ibAccessToken) throws java.io.IOException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationLog(workflowUUID,
			simulationUUID, ibAccessToken);
	}

	@Override
	public java.lang.String getWorkflowSimulationErrorLog(
		java.lang.String workflowUUID, java.lang.String simulationUUID,
		java.lang.String ibAccessToken) throws java.io.IOException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationErrorLog(workflowUUID,
			simulationUUID, ibAccessToken);
	}

	@Override
	public java.lang.String getWorkflowJobIntermediateResult(
		java.lang.String jobUuid, java.lang.String ibAccessToken)
		throws java.io.IOException {
		return _workflowSimulationJobLocalService.getWorkflowJobIntermediateResult(jobUuid,
			ibAccessToken);
	}

	@Override
	public java.io.File downloadFileApi(com.liferay.portal.model.User user,
		long appGroupId, com.liferay.portal.kernel.json.JSONObject inputport)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.downloadFileApi(user,
			appGroupId, inputport);
	}

	@Override
	public java.lang.String uploadFileToIcebreaker(long appGroupId,
		java.lang.String icebreakerVcToken, java.io.File uploadFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.net.MalformedURLException {
		return _workflowSimulationJobLocalService.uploadFileToIcebreaker(appGroupId,
			icebreakerVcToken, uploadFile);
	}

	@Override
	public java.lang.String uploadFileToIcebreaker(long appGroupId,
		java.lang.String icebreakerVcToken, java.io.InputStream fileContent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.uploadFileToIcebreaker(appGroupId,
			icebreakerVcToken, fileContent);
	}

	@Override
	public java.lang.String uploadFileToIcebreaker(long appGroupId,
		java.lang.String icebreakerVcToken, java.lang.String fileContent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _workflowSimulationJobLocalService.uploadFileToIcebreaker(appGroupId,
			icebreakerVcToken, fileContent);
	}

	@Override
	public long getCountWorkflowSimulationJobByUserId(
		com.liferay.portal.model.User user,
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getCountWorkflowSimulationJobByUserId(user,
			params);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getWorkflowSimulationJobByUserId(
		com.liferay.portal.model.User user,
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getWorkflowSimulationJobByUserId(user,
			params, locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getDataTypeEditors(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getDataTypeEditors(dataType);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getDataTypeDefaultEditor(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getDataTypeDefaultEditor(dataType);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getDataTypeDefaultAnalyzer(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getDataTypeDefaultAnalyzer(dataType);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getDataTypeAnalyzers(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getDataTypeAnalyzers(dataType);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getRootSiteAssetCategries(
		long companyGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getRootSiteAssetCategries(companyGroupId,
			groupId);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getSiteAssetCategoriesByParentId(
		long companyGroupId, long groupId, long parentCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getSiteAssetCategoriesByParentId(companyGroupId,
			groupId, parentCategoryId);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getLv1Categories(
		long companyGroupId, long groupId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJobLocalService.getLv1Categories(companyGroupId,
			groupId, locale);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WorkflowSimulationJobLocalService getWrappedWorkflowSimulationJobLocalService() {
		return _workflowSimulationJobLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWorkflowSimulationJobLocalService(
		WorkflowSimulationJobLocalService workflowSimulationJobLocalService) {
		_workflowSimulationJobLocalService = workflowSimulationJobLocalService;
	}

	@Override
	public WorkflowSimulationJobLocalService getWrappedService() {
		return _workflowSimulationJobLocalService;
	}

	@Override
	public void setWrappedService(
		WorkflowSimulationJobLocalService workflowSimulationJobLocalService) {
		_workflowSimulationJobLocalService = workflowSimulationJobLocalService;
	}

	private WorkflowSimulationJobLocalService _workflowSimulationJobLocalService;
}