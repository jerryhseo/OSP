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

package org.kisti.edison.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import org.kisti.edison.model.WorkflowSimulationJob;
import org.kisti.edison.service.WorkflowSimulationJobLocalService;
import org.kisti.edison.service.persistence.WorkflowFinder;
import org.kisti.edison.service.persistence.WorkflowPersistence;
import org.kisti.edison.service.persistence.WorkflowSimulationJobPersistence;
import org.kisti.edison.service.persistence.WorkflowSimulationPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the workflow simulation job local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.kisti.edison.service.impl.WorkflowSimulationJobLocalServiceImpl}.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.service.impl.WorkflowSimulationJobLocalServiceImpl
 * @see org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil
 * @generated
 */
public abstract class WorkflowSimulationJobLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements WorkflowSimulationJobLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil} to access the workflow simulation job local service.
	 */

	/**
	 * Adds the workflow simulation job to the database. Also notifies the appropriate model listeners.
	 *
	 * @param workflowSimulationJob the workflow simulation job
	 * @return the workflow simulation job that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkflowSimulationJob addWorkflowSimulationJob(
		WorkflowSimulationJob workflowSimulationJob) throws SystemException {
		workflowSimulationJob.setNew(true);

		return workflowSimulationJobPersistence.update(workflowSimulationJob);
	}

	/**
	 * Creates a new workflow simulation job with the primary key. Does not add the workflow simulation job to the database.
	 *
	 * @param simulationJobId the primary key for the new workflow simulation job
	 * @return the new workflow simulation job
	 */
	@Override
	public WorkflowSimulationJob createWorkflowSimulationJob(
		long simulationJobId) {
		return workflowSimulationJobPersistence.create(simulationJobId);
	}

	/**
	 * Deletes the workflow simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulationJobId the primary key of the workflow simulation job
	 * @return the workflow simulation job that was removed
	 * @throws PortalException if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorkflowSimulationJob deleteWorkflowSimulationJob(
		long simulationJobId) throws PortalException, SystemException {
		return workflowSimulationJobPersistence.remove(simulationJobId);
	}

	/**
	 * Deletes the workflow simulation job from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workflowSimulationJob the workflow simulation job
	 * @return the workflow simulation job that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorkflowSimulationJob deleteWorkflowSimulationJob(
		WorkflowSimulationJob workflowSimulationJob) throws SystemException {
		return workflowSimulationJobPersistence.remove(workflowSimulationJob);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(WorkflowSimulationJob.class,
			clazz.getClassLoader());
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
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return workflowSimulationJobPersistence.findWithDynamicQuery(dynamicQuery);
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return workflowSimulationJobPersistence.findWithDynamicQuery(dynamicQuery,
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return workflowSimulationJobPersistence.findWithDynamicQuery(dynamicQuery,
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
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return workflowSimulationJobPersistence.countWithDynamicQuery(dynamicQuery);
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
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return workflowSimulationJobPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public WorkflowSimulationJob fetchWorkflowSimulationJob(
		long simulationJobId) throws SystemException {
		return workflowSimulationJobPersistence.fetchByPrimaryKey(simulationJobId);
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
	public WorkflowSimulationJob getWorkflowSimulationJob(long simulationJobId)
		throws PortalException, SystemException {
		return workflowSimulationJobPersistence.findByPrimaryKey(simulationJobId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return workflowSimulationJobPersistence.findByPrimaryKey(primaryKeyObj);
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
	public List<WorkflowSimulationJob> getWorkflowSimulationJobs(int start,
		int end) throws SystemException {
		return workflowSimulationJobPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of workflow simulation jobs.
	 *
	 * @return the number of workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getWorkflowSimulationJobsCount() throws SystemException {
		return workflowSimulationJobPersistence.countAll();
	}

	/**
	 * Updates the workflow simulation job in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param workflowSimulationJob the workflow simulation job
	 * @return the workflow simulation job that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkflowSimulationJob updateWorkflowSimulationJob(
		WorkflowSimulationJob workflowSimulationJob) throws SystemException {
		return workflowSimulationJobPersistence.update(workflowSimulationJob);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulationWorkflowSimulationJob(long simulationId,
		long simulationJobId) throws SystemException {
		workflowSimulationPersistence.addWorkflowSimulationJob(simulationId,
			simulationJobId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulationWorkflowSimulationJob(long simulationId,
		WorkflowSimulationJob workflowSimulationJob) throws SystemException {
		workflowSimulationPersistence.addWorkflowSimulationJob(simulationId,
			workflowSimulationJob);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulationWorkflowSimulationJobs(long simulationId,
		long[] simulationJobIds) throws SystemException {
		workflowSimulationPersistence.addWorkflowSimulationJobs(simulationId,
			simulationJobIds);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulationWorkflowSimulationJobs(long simulationId,
		List<WorkflowSimulationJob> WorkflowSimulationJobs)
		throws SystemException {
		workflowSimulationPersistence.addWorkflowSimulationJobs(simulationId,
			WorkflowSimulationJobs);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearWorkflowSimulationWorkflowSimulationJobs(long simulationId)
		throws SystemException {
		workflowSimulationPersistence.clearWorkflowSimulationJobs(simulationId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteWorkflowSimulationWorkflowSimulationJob(
		long simulationId, long simulationJobId) throws SystemException {
		workflowSimulationPersistence.removeWorkflowSimulationJob(simulationId,
			simulationJobId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteWorkflowSimulationWorkflowSimulationJob(
		long simulationId, WorkflowSimulationJob workflowSimulationJob)
		throws SystemException {
		workflowSimulationPersistence.removeWorkflowSimulationJob(simulationId,
			workflowSimulationJob);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, long[] simulationJobIds) throws SystemException {
		workflowSimulationPersistence.removeWorkflowSimulationJobs(simulationId,
			simulationJobIds);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, List<WorkflowSimulationJob> WorkflowSimulationJobs)
		throws SystemException {
		workflowSimulationPersistence.removeWorkflowSimulationJobs(simulationId,
			WorkflowSimulationJobs);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId) throws SystemException {
		return workflowSimulationPersistence.getWorkflowSimulationJobs(simulationId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, int start, int end) throws SystemException {
		return workflowSimulationPersistence.getWorkflowSimulationJobs(simulationId,
			start, end);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> getWorkflowSimulationWorkflowSimulationJobs(
		long simulationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return workflowSimulationPersistence.getWorkflowSimulationJobs(simulationId,
			start, end, orderByComparator);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getWorkflowSimulationWorkflowSimulationJobsCount(
		long simulationId) throws SystemException {
		return workflowSimulationPersistence.getWorkflowSimulationJobsSize(simulationId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean hasWorkflowSimulationWorkflowSimulationJob(
		long simulationId, long simulationJobId) throws SystemException {
		return workflowSimulationPersistence.containsWorkflowSimulationJob(simulationId,
			simulationJobId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean hasWorkflowSimulationWorkflowSimulationJobs(
		long simulationId) throws SystemException {
		return workflowSimulationPersistence.containsWorkflowSimulationJobs(simulationId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setWorkflowSimulationWorkflowSimulationJobs(long simulationId,
		long[] simulationJobIds) throws SystemException {
		workflowSimulationPersistence.setWorkflowSimulationJobs(simulationId,
			simulationJobIds);
	}

	/**
	 * Returns the workflow local service.
	 *
	 * @return the workflow local service
	 */
	public org.kisti.edison.service.WorkflowLocalService getWorkflowLocalService() {
		return workflowLocalService;
	}

	/**
	 * Sets the workflow local service.
	 *
	 * @param workflowLocalService the workflow local service
	 */
	public void setWorkflowLocalService(
		org.kisti.edison.service.WorkflowLocalService workflowLocalService) {
		this.workflowLocalService = workflowLocalService;
	}

	/**
	 * Returns the workflow persistence.
	 *
	 * @return the workflow persistence
	 */
	public WorkflowPersistence getWorkflowPersistence() {
		return workflowPersistence;
	}

	/**
	 * Sets the workflow persistence.
	 *
	 * @param workflowPersistence the workflow persistence
	 */
	public void setWorkflowPersistence(WorkflowPersistence workflowPersistence) {
		this.workflowPersistence = workflowPersistence;
	}

	/**
	 * Returns the workflow finder.
	 *
	 * @return the workflow finder
	 */
	public WorkflowFinder getWorkflowFinder() {
		return workflowFinder;
	}

	/**
	 * Sets the workflow finder.
	 *
	 * @param workflowFinder the workflow finder
	 */
	public void setWorkflowFinder(WorkflowFinder workflowFinder) {
		this.workflowFinder = workflowFinder;
	}

	/**
	 * Returns the workflow simulation local service.
	 *
	 * @return the workflow simulation local service
	 */
	public org.kisti.edison.service.WorkflowSimulationLocalService getWorkflowSimulationLocalService() {
		return workflowSimulationLocalService;
	}

	/**
	 * Sets the workflow simulation local service.
	 *
	 * @param workflowSimulationLocalService the workflow simulation local service
	 */
	public void setWorkflowSimulationLocalService(
		org.kisti.edison.service.WorkflowSimulationLocalService workflowSimulationLocalService) {
		this.workflowSimulationLocalService = workflowSimulationLocalService;
	}

	/**
	 * Returns the workflow simulation persistence.
	 *
	 * @return the workflow simulation persistence
	 */
	public WorkflowSimulationPersistence getWorkflowSimulationPersistence() {
		return workflowSimulationPersistence;
	}

	/**
	 * Sets the workflow simulation persistence.
	 *
	 * @param workflowSimulationPersistence the workflow simulation persistence
	 */
	public void setWorkflowSimulationPersistence(
		WorkflowSimulationPersistence workflowSimulationPersistence) {
		this.workflowSimulationPersistence = workflowSimulationPersistence;
	}

	/**
	 * Returns the workflow simulation job local service.
	 *
	 * @return the workflow simulation job local service
	 */
	public org.kisti.edison.service.WorkflowSimulationJobLocalService getWorkflowSimulationJobLocalService() {
		return workflowSimulationJobLocalService;
	}

	/**
	 * Sets the workflow simulation job local service.
	 *
	 * @param workflowSimulationJobLocalService the workflow simulation job local service
	 */
	public void setWorkflowSimulationJobLocalService(
		org.kisti.edison.service.WorkflowSimulationJobLocalService workflowSimulationJobLocalService) {
		this.workflowSimulationJobLocalService = workflowSimulationJobLocalService;
	}

	/**
	 * Returns the workflow simulation job persistence.
	 *
	 * @return the workflow simulation job persistence
	 */
	public WorkflowSimulationJobPersistence getWorkflowSimulationJobPersistence() {
		return workflowSimulationJobPersistence;
	}

	/**
	 * Sets the workflow simulation job persistence.
	 *
	 * @param workflowSimulationJobPersistence the workflow simulation job persistence
	 */
	public void setWorkflowSimulationJobPersistence(
		WorkflowSimulationJobPersistence workflowSimulationJobPersistence) {
		this.workflowSimulationJobPersistence = workflowSimulationJobPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("org.kisti.edison.model.WorkflowSimulationJob",
			workflowSimulationJobLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"org.kisti.edison.model.WorkflowSimulationJob");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return WorkflowSimulationJob.class;
	}

	protected String getModelClassName() {
		return WorkflowSimulationJob.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = workflowSimulationJobPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = org.kisti.edison.service.WorkflowLocalService.class)
	protected org.kisti.edison.service.WorkflowLocalService workflowLocalService;
	@BeanReference(type = WorkflowPersistence.class)
	protected WorkflowPersistence workflowPersistence;
	@BeanReference(type = WorkflowFinder.class)
	protected WorkflowFinder workflowFinder;
	@BeanReference(type = org.kisti.edison.service.WorkflowSimulationLocalService.class)
	protected org.kisti.edison.service.WorkflowSimulationLocalService workflowSimulationLocalService;
	@BeanReference(type = WorkflowSimulationPersistence.class)
	protected WorkflowSimulationPersistence workflowSimulationPersistence;
	@BeanReference(type = org.kisti.edison.service.WorkflowSimulationJobLocalService.class)
	protected org.kisti.edison.service.WorkflowSimulationJobLocalService workflowSimulationJobLocalService;
	@BeanReference(type = WorkflowSimulationJobPersistence.class)
	protected WorkflowSimulationJobPersistence workflowSimulationJobPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private WorkflowSimulationJobLocalServiceClpInvoker _clpInvoker = new WorkflowSimulationJobLocalServiceClpInvoker();
}