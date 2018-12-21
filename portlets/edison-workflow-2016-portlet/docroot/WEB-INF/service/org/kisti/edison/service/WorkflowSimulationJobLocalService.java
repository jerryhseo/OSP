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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for WorkflowSimulationJob. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author EDISON
 * @see WorkflowSimulationJobLocalServiceUtil
 * @see org.kisti.edison.service.base.WorkflowSimulationJobLocalServiceBaseImpl
 * @see org.kisti.edison.service.impl.WorkflowSimulationJobLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WorkflowSimulationJobLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkflowSimulationJobLocalServiceUtil} to access the workflow simulation job local service. Add custom service methods to {@link org.kisti.edison.service.impl.WorkflowSimulationJobLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the workflow simulation job to the database. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulationJob the workflow simulation job
	* @return the workflow simulation job that was added
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public org.kisti.edison.model.WorkflowSimulationJob addWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new workflow simulation job with the primary key. Does not add the workflow simulation job to the database.
	*
	* @param simulationJobId the primary key for the new workflow simulation job
	* @return the new workflow simulation job
	*/
	public org.kisti.edison.model.WorkflowSimulationJob createWorkflowSimulationJob(
		long simulationJobId);

	/**
	* Deletes the workflow simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJobId the primary key of the workflow simulation job
	* @return the workflow simulation job that was removed
	* @throws PortalException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public org.kisti.edison.model.WorkflowSimulationJob deleteWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the workflow simulation job from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulationJob the workflow simulation job
	* @return the workflow simulation job that was removed
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public org.kisti.edison.model.WorkflowSimulationJob deleteWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public org.kisti.edison.model.WorkflowSimulationJob fetchWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the workflow simulation job with the primary key.
	*
	* @param simulationJobId the primary key of the workflow simulation job
	* @return the workflow simulation job
	* @throws PortalException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public org.kisti.edison.model.WorkflowSimulationJob getWorkflowSimulationJob(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulation jobs.
	*
	* @return the number of workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWorkflowSimulationJobsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the workflow simulation job in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workflowSimulationJob the workflow simulation job
	* @return the workflow simulation job that was updated
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public org.kisti.edison.model.WorkflowSimulationJob updateWorkflowSimulationJob(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowWorkflowSimulationJob(long workflowId,
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowWorkflowSimulationJob(long workflowId,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowWorkflowSimulationJobs(long workflowId,
		long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowWorkflowSimulationJobs(long workflowId,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> WorkflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearWorkflowWorkflowSimulationJobs(long workflowId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWorkflowWorkflowSimulationJob(long workflowId,
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWorkflowWorkflowSimulationJob(long workflowId,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWorkflowWorkflowSimulationJobs(long workflowId,
		long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWorkflowWorkflowSimulationJobs(long workflowId,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> WorkflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowWorkflowSimulationJobs(
		long workflowId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowWorkflowSimulationJobs(
		long workflowId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowWorkflowSimulationJobs(
		long workflowId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWorkflowWorkflowSimulationJobsCount(long workflowId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWorkflowWorkflowSimulationJob(long workflowId,
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWorkflowWorkflowSimulationJobs(long workflowId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setWorkflowWorkflowSimulationJobs(long workflowId,
		long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulationWorkflowSimulationJob(long simulationId,
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulationWorkflowSimulationJob(long simulationId,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulationWorkflowSimulationJobs(long simulationId,
		long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulationWorkflowSimulationJobs(long simulationId,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> WorkflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearWorkflowSimulationWorkflowSimulationJobs(long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWorkflowSimulationWorkflowSimulationJob(
		long simulationId, long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWorkflowSimulationWorkflowSimulationJob(
		long simulationId,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWorkflowSimulationWorkflowSimulationJobs(
		long simulationId,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> WorkflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWorkflowSimulationWorkflowSimulationJobsCount(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWorkflowSimulationWorkflowSimulationJob(
		long simulationId, long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWorkflowSimulationWorkflowSimulationJobs(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setWorkflowSimulationWorkflowSimulationJobs(long simulationId,
		long[] simulationJobIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;
}