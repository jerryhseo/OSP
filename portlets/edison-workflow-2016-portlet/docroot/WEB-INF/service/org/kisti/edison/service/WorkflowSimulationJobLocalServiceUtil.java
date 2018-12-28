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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for WorkflowSimulationJob. This utility wraps
 * {@link org.kisti.edison.service.impl.WorkflowSimulationJobLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author EDISON
 * @see WorkflowSimulationJobLocalService
 * @see org.kisti.edison.service.base.WorkflowSimulationJobLocalServiceBaseImpl
 * @see org.kisti.edison.service.impl.WorkflowSimulationJobLocalServiceImpl
 * @generated
 */
public class WorkflowSimulationJobLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.service.impl.WorkflowSimulationJobLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the workflow simulation job to the database. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulationJob the workflow simulation job
	* @return the workflow simulation job that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulationJob addWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addWorkflowSimulationJob(workflowSimulationJob);
	}

	/**
	* Creates a new workflow simulation job with the primary key. Does not add the workflow simulation job to the database.
	*
	* @param simulationJobId the primary key for the new workflow simulation job
	* @return the new workflow simulation job
	*/
	public static org.kisti.edison.model.WorkflowSimulationJob createWorkflowSimulationJob(
		long simulationJobId) {
		return getService().createWorkflowSimulationJob(simulationJobId);
	}

	/**
	* Deletes the workflow simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJobId the primary key of the workflow simulation job
	* @return the workflow simulation job that was removed
	* @throws PortalException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulationJob deleteWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteWorkflowSimulationJob(simulationJobId);
	}

	/**
	* Deletes the workflow simulation job from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulationJob the workflow simulation job
	* @return the workflow simulation job that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulationJob deleteWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteWorkflowSimulationJob(workflowSimulationJob);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob fetchWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchWorkflowSimulationJob(simulationJobId);
	}

	/**
	* Returns the workflow simulation job with the primary key.
	*
	* @param simulationJobId the primary key of the workflow simulation job
	* @return the workflow simulation job
	* @throws PortalException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulationJob getWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getWorkflowSimulationJob(simulationJobId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWorkflowSimulationJobs(start, end);
	}

	/**
	* Returns the number of workflow simulation jobs.
	*
	* @return the number of workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int getWorkflowSimulationJobsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWorkflowSimulationJobsCount();
	}

	/**
	* Updates the workflow simulation job in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulationJob the workflow simulation job
	* @return the workflow simulation job that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulationJob updateWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWorkflowSimulationJob(workflowSimulationJob);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addWorkflowSimulationWorkflowSimulationJob(
		long simulationId, long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addWorkflowSimulationWorkflowSimulationJob(simulationId,
			simulationJobId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addWorkflowSimulationWorkflowSimulationJob(
		long simulationId,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addWorkflowSimulationWorkflowSimulationJob(simulationId,
			workflowSimulationJob);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addWorkflowSimulationWorkflowSimulationJobs(simulationId,
			simulationJobIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addWorkflowSimulationWorkflowSimulationJobs(
		long simulationId,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> WorkflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addWorkflowSimulationWorkflowSimulationJobs(simulationId,
			WorkflowSimulationJobs);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearWorkflowSimulationWorkflowSimulationJobs(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearWorkflowSimulationWorkflowSimulationJobs(simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteWorkflowSimulationWorkflowSimulationJob(
		long simulationId, long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteWorkflowSimulationWorkflowSimulationJob(simulationId,
			simulationJobId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteWorkflowSimulationWorkflowSimulationJob(
		long simulationId,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteWorkflowSimulationWorkflowSimulationJob(simulationId,
			workflowSimulationJob);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteWorkflowSimulationWorkflowSimulationJobs(simulationId,
			simulationJobIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteWorkflowSimulationWorkflowSimulationJobs(
		long simulationId,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> WorkflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteWorkflowSimulationWorkflowSimulationJobs(simulationId,
			WorkflowSimulationJobs);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWorkflowSimulationWorkflowSimulationJobs(simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWorkflowSimulationWorkflowSimulationJobs(simulationId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWorkflowSimulationWorkflowSimulationJobs(simulationId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getWorkflowSimulationWorkflowSimulationJobsCount(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWorkflowSimulationWorkflowSimulationJobsCount(simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasWorkflowSimulationWorkflowSimulationJob(
		long simulationId, long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasWorkflowSimulationWorkflowSimulationJob(simulationId,
			simulationJobId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasWorkflowSimulationWorkflowSimulationJobs(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasWorkflowSimulationWorkflowSimulationJobs(simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setWorkflowSimulationWorkflowSimulationJobs(simulationId,
			simulationJobIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long simulationId, java.lang.String title, long userId, int begin,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWorkflowSimulationJobs(simulationId, title, userId,
			begin, end);
	}

	public static int countWorkflowSimulationJobs(long simulationId,
		java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .countWorkflowSimulationJobs(simulationId, title, userId);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob updateWorkflowSimulationJob(
		long simulationJobId,
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWorkflowSimulationJob(simulationJobId, params);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob createWorkflowSimulationJob(
		long simulationId,
		java.util.Map<java.lang.String, java.lang.Object> params,
		javax.servlet.http.HttpServletRequest request)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .createWorkflowSimulationJob(simulationId, params, request);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob createSimulationJob(
		org.kisti.edison.model.WorkflowSimulation simulation,
		org.kisti.edison.model.Workflow workflow,
		java.lang.String simulationJobTitle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createSimulationJob(simulation, workflow, simulationJobTitle);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob createWorkflowSimulationJob()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().createWorkflowSimulationJob();
	}

	public static org.kisti.edison.model.WorkflowSimulationJob createWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().createWorkflowSimulationJob(simulationJob);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob startWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().startWorkflowSimulationJob(simulationJobId);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob startWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().startWorkflowSimulationJob(simulationJob);
	}

	public static org.codehaus.jackson.JsonNode askForWorkflowStart(
		java.lang.String workflowUUID)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().askForWorkflowStart(workflowUUID);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob getWorkflowStatus(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().getWorkflowStatus(simulationJobId);
	}

	public static org.kisti.edison.model.WorkflowSimulation deleteSimulationAndJobs(
		long simulationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSimulationAndJobs(simulationId);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob deleteWorkflowSimulationJobWitEngine(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().deleteWorkflowSimulationJobWitEngine(simulationJobId);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob deleteWorkflowSimulationJobWitEngine(
		org.kisti.edison.model.WorkflowSimulationJob job)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().deleteWorkflowSimulationJobWitEngine(job);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob pauseWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().pauseWorkflowSimulationJob(simulationJobId);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob resumeWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().resumeWorkflowSimulationJob(simulationJobId);
	}

	public static org.kisti.edison.model.WorkflowSimulationJob updateWorkflowSimulationJob(
		org.codehaus.jackson.JsonNode workflowStatusJson,
		org.kisti.edison.model.WorkflowSimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateWorkflowSimulationJob(workflowStatusJson,
			simulationJob);
	}

	public static org.codehaus.jackson.JsonNode askForWorkflowStatus(
		java.lang.String workflowUUID)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().askForWorkflowStatus(workflowUUID);
	}

	public static java.lang.String getWorkflowSimulationLog(
		java.lang.String workflowUUID, java.lang.String simulationUUID,
		java.lang.String ibAccessToken) throws java.io.IOException {
		return getService()
				   .getWorkflowSimulationLog(workflowUUID, simulationUUID,
			ibAccessToken);
	}

	public static java.lang.String getWorkflowSimulationErrorLog(
		java.lang.String workflowUUID, java.lang.String simulationUUID,
		java.lang.String ibAccessToken) throws java.io.IOException {
		return getService()
				   .getWorkflowSimulationErrorLog(workflowUUID, simulationUUID,
			ibAccessToken);
	}

	public static java.lang.String getWorkflowJobIntermediateResult(
		java.lang.String jobUuid, java.lang.String ibAccessToken)
		throws java.io.IOException {
		return getService()
				   .getWorkflowJobIntermediateResult(jobUuid, ibAccessToken);
	}

	public static java.io.File downloadFileApi(
		com.liferay.portal.model.User user, long appGroupId,
		com.liferay.portal.kernel.json.JSONObject inputport)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().downloadFileApi(user, appGroupId, inputport);
	}

	public static java.lang.String uploadFileToIcebreaker(long appGroupId,
		java.lang.String icebreakerVcToken, java.io.File uploadFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.net.MalformedURLException {
		return getService()
				   .uploadFileToIcebreaker(appGroupId, icebreakerVcToken,
			uploadFile);
	}

	public static java.lang.String uploadFileToIcebreaker(long appGroupId,
		java.lang.String icebreakerVcToken, java.io.InputStream fileContent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .uploadFileToIcebreaker(appGroupId, icebreakerVcToken,
			fileContent);
	}

	public static java.lang.String uploadFileToIcebreaker(long appGroupId,
		java.lang.String icebreakerVcToken, java.lang.String fileContent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .uploadFileToIcebreaker(appGroupId, icebreakerVcToken,
			fileContent);
	}

	public static long getCountWorkflowSimulationJobByUserId(
		com.liferay.portal.model.User user,
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountWorkflowSimulationJobByUserId(user, params);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getWorkflowSimulationJobByUserId(
		com.liferay.portal.model.User user,
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWorkflowSimulationJobByUserId(user, params, locale);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getDataTypeEditors(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataTypeEditors(dataType);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> getDataTypeDefaultEditor(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataTypeDefaultEditor(dataType);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> getDataTypeDefaultAnalyzer(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataTypeDefaultAnalyzer(dataType);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getDataTypeAnalyzers(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataTypeAnalyzers(dataType);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getRootSiteAssetCategries(
		long companyGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getRootSiteAssetCategries(companyGroupId, groupId);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getSiteAssetCategoriesByParentId(
		long companyGroupId, long groupId, long parentCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSiteAssetCategoriesByParentId(companyGroupId, groupId,
			parentCategoryId);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getLv1Categories(
		long companyGroupId, long groupId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLv1Categories(companyGroupId, groupId, locale);
	}

	public static void clearService() {
		_service = null;
	}

	public static WorkflowSimulationJobLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WorkflowSimulationJobLocalService.class.getName());

			if (invokableLocalService instanceof WorkflowSimulationJobLocalService) {
				_service = (WorkflowSimulationJobLocalService)invokableLocalService;
			}
			else {
				_service = new WorkflowSimulationJobLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(WorkflowSimulationJobLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(WorkflowSimulationJobLocalService service) {
	}

	private static WorkflowSimulationJobLocalService _service;
}