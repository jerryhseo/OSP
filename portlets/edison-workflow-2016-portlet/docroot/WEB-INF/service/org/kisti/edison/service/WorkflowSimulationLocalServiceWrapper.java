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
 * Provides a wrapper for {@link WorkflowSimulationLocalService}.
 *
 * @author EDISON
 * @see WorkflowSimulationLocalService
 * @generated
 */
public class WorkflowSimulationLocalServiceWrapper
	implements WorkflowSimulationLocalService,
		ServiceWrapper<WorkflowSimulationLocalService> {
	public WorkflowSimulationLocalServiceWrapper(
		WorkflowSimulationLocalService workflowSimulationLocalService) {
		_workflowSimulationLocalService = workflowSimulationLocalService;
	}

	/**
	* Adds the workflow simulation to the database. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulation the workflow simulation
	* @return the workflow simulation that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulation addWorkflowSimulation(
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.addWorkflowSimulation(workflowSimulation);
	}

	/**
	* Creates a new workflow simulation with the primary key. Does not add the workflow simulation to the database.
	*
	* @param simulationId the primary key for the new workflow simulation
	* @return the new workflow simulation
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulation createWorkflowSimulation(
		long simulationId) {
		return _workflowSimulationLocalService.createWorkflowSimulation(simulationId);
	}

	/**
	* Deletes the workflow simulation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationId the primary key of the workflow simulation
	* @return the workflow simulation that was removed
	* @throws PortalException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulation deleteWorkflowSimulation(
		long simulationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.deleteWorkflowSimulation(simulationId);
	}

	/**
	* Deletes the workflow simulation from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulation the workflow simulation
	* @return the workflow simulation that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulation deleteWorkflowSimulation(
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.deleteWorkflowSimulation(workflowSimulation);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workflowSimulationLocalService.dynamicQuery();
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
		return _workflowSimulationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workflowSimulationLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workflowSimulationLocalService.dynamicQuery(dynamicQuery,
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
		return _workflowSimulationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _workflowSimulationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulation fetchWorkflowSimulation(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.fetchWorkflowSimulation(simulationId);
	}

	/**
	* Returns the workflow simulation with the primary key.
	*
	* @param simulationId the primary key of the workflow simulation
	* @return the workflow simulation
	* @throws PortalException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulation getWorkflowSimulation(
		long simulationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.getWorkflowSimulation(simulationId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the workflow simulations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @return the range of workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.getWorkflowSimulations(start, end);
	}

	/**
	* Returns the number of workflow simulations.
	*
	* @return the number of workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getWorkflowSimulationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.getWorkflowSimulationsCount();
	}

	/**
	* Updates the workflow simulation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulation the workflow simulation
	* @return the workflow simulation that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.WorkflowSimulation updateWorkflowSimulation(
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.updateWorkflowSimulation(workflowSimulation);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowSimulationJobWorkflowSimulation(
		long simulationJobId, long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationLocalService.addWorkflowSimulationJobWorkflowSimulation(simulationJobId,
			simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowSimulationJobWorkflowSimulation(
		long simulationJobId,
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationLocalService.addWorkflowSimulationJobWorkflowSimulation(simulationJobId,
			workflowSimulation);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowSimulationJobWorkflowSimulations(
		long simulationJobId, long[] simulationIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationLocalService.addWorkflowSimulationJobWorkflowSimulations(simulationJobId,
			simulationIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void addWorkflowSimulationJobWorkflowSimulations(
		long simulationJobId,
		java.util.List<org.kisti.edison.model.WorkflowSimulation> WorkflowSimulations)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationLocalService.addWorkflowSimulationJobWorkflowSimulations(simulationJobId,
			WorkflowSimulations);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void clearWorkflowSimulationJobWorkflowSimulations(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationLocalService.clearWorkflowSimulationJobWorkflowSimulations(simulationJobId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowSimulationJobWorkflowSimulation(
		long simulationJobId, long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationLocalService.deleteWorkflowSimulationJobWorkflowSimulation(simulationJobId,
			simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowSimulationJobWorkflowSimulation(
		long simulationJobId,
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationLocalService.deleteWorkflowSimulationJobWorkflowSimulation(simulationJobId,
			workflowSimulation);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowSimulationJobWorkflowSimulations(
		long simulationJobId, long[] simulationIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationLocalService.deleteWorkflowSimulationJobWorkflowSimulations(simulationJobId,
			simulationIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteWorkflowSimulationJobWorkflowSimulations(
		long simulationJobId,
		java.util.List<org.kisti.edison.model.WorkflowSimulation> WorkflowSimulations)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationLocalService.deleteWorkflowSimulationJobWorkflowSimulations(simulationJobId,
			WorkflowSimulations);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulationJobWorkflowSimulations(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.getWorkflowSimulationJobWorkflowSimulations(simulationJobId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulationJobWorkflowSimulations(
		long simulationJobId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.getWorkflowSimulationJobWorkflowSimulations(simulationJobId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulationJobWorkflowSimulations(
		long simulationJobId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.getWorkflowSimulationJobWorkflowSimulations(simulationJobId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getWorkflowSimulationJobWorkflowSimulationsCount(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.getWorkflowSimulationJobWorkflowSimulationsCount(simulationJobId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public boolean hasWorkflowSimulationJobWorkflowSimulation(
		long simulationJobId, long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.hasWorkflowSimulationJobWorkflowSimulation(simulationJobId,
			simulationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public boolean hasWorkflowSimulationJobWorkflowSimulations(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.hasWorkflowSimulationJobWorkflowSimulations(simulationJobId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void setWorkflowSimulationJobWorkflowSimulations(
		long simulationJobId, long[] simulationIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationLocalService.setWorkflowSimulationJobWorkflowSimulations(simulationJobId,
			simulationIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _workflowSimulationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_workflowSimulationLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _workflowSimulationLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getWorkflowMonitoringJobList(
		long simulationId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.getWorkflowMonitoringJobList(simulationId,
			locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getWorkflowMonitoringList(
		long userId, java.lang.String searchValue, long simulationId,
		long jobStatus, long classId, int begin, int end,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationLocalService.getWorkflowMonitoringList(userId,
			searchValue, simulationId, jobStatus, classId, begin, end, locale);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WorkflowSimulationLocalService getWrappedWorkflowSimulationLocalService() {
		return _workflowSimulationLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWorkflowSimulationLocalService(
		WorkflowSimulationLocalService workflowSimulationLocalService) {
		_workflowSimulationLocalService = workflowSimulationLocalService;
	}

	@Override
	public WorkflowSimulationLocalService getWrappedService() {
		return _workflowSimulationLocalService;
	}

	@Override
	public void setWrappedService(
		WorkflowSimulationLocalService workflowSimulationLocalService) {
		_workflowSimulationLocalService = workflowSimulationLocalService;
	}

	private WorkflowSimulationLocalService _workflowSimulationLocalService;
}