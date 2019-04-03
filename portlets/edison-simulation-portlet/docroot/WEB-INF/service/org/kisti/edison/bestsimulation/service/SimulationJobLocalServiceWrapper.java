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

package org.kisti.edison.bestsimulation.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SimulationJobLocalService}.
 *
 * @author EDISON
 * @see SimulationJobLocalService
 * @generated
 */
public class SimulationJobLocalServiceWrapper
	implements SimulationJobLocalService,
		ServiceWrapper<SimulationJobLocalService> {
	public SimulationJobLocalServiceWrapper(
		SimulationJobLocalService simulationJobLocalService) {
		_simulationJobLocalService = simulationJobLocalService;
	}

	/**
	* Adds the simulation job to the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJob the simulation job
	* @return the simulation job that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob addSimulationJob(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.addSimulationJob(simulationJob);
	}

	/**
	* Creates a new simulation job with the primary key. Does not add the simulation job to the database.
	*
	* @param simulationJobPK the primary key for the new simulation job
	* @return the new simulation job
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob createSimulationJob(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK) {
		return _simulationJobLocalService.createSimulationJob(simulationJobPK);
	}

	/**
	* Deletes the simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJobPK the primary key of the simulation job
	* @return the simulation job that was removed
	* @throws PortalException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob deleteSimulationJob(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.deleteSimulationJob(simulationJobPK);
	}

	/**
	* Deletes the simulation job from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJob the simulation job
	* @return the simulation job that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob deleteSimulationJob(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.deleteSimulationJob(simulationJob);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _simulationJobLocalService.dynamicQuery();
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
		return _simulationJobLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _simulationJobLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _simulationJobLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _simulationJobLocalService.dynamicQueryCount(dynamicQuery);
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
		return _simulationJobLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob fetchSimulationJob(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.fetchSimulationJob(simulationJobPK);
	}

	/**
	* Returns the simulation job with the primary key.
	*
	* @param simulationJobPK the primary key of the simulation job
	* @return the simulation job
	* @throws PortalException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob getSimulationJob(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getSimulationJob(simulationJobPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the simulation jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @return the range of simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getSimulationJobs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getSimulationJobs(start, end);
	}

	/**
	* Returns the number of simulation jobs.
	*
	* @return the number of simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSimulationJobsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getSimulationJobsCount();
	}

	/**
	* Updates the simulation job in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param simulationJob the simulation job
	* @return the simulation job that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob updateSimulationJob(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.updateSimulationJob(simulationJob);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _simulationJobLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_simulationJobLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _simulationJobLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob getSimulationJobWithJobUuid(
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return _simulationJobLocalService.getSimulationJobWithJobUuid(jobUuid);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsWithSimulationUuid(
		java.lang.String simulationUuid, int start, int size)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getJobsWithSimulationUuid(simulationUuid,
			start, size);
	}

	@Override
	public long getJobsCountWithSimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getJobsCountWithSimulationUuid(simulationUuid);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob createSimulationJob(
		java.lang.String simulationUuid, long groupId, java.lang.String jobTitle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.createSimulationJob(simulationUuid,
			groupId, jobTitle);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob createSimulationJobWithJobData(
		java.lang.String simulationUuid, long groupId,
		java.lang.String jobTitle, java.lang.String jobData)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.createSimulationJobWithJobData(simulationUuid,
			groupId, jobTitle, jobData);
	}

	@Override
	public void deleteSimulationJob(java.lang.String simulationUuid,
		long jobSeqNo, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		_simulationJobLocalService.deleteSimulationJob(simulationUuid,
			jobSeqNo, groupId);
	}

	@Override
	public void deleteSimulationJobByJobUuid(java.lang.String simulationUuid,
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationJobLocalService.deleteSimulationJobByJobUuid(simulationUuid,
			jobUuid);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> submitSimulationJob(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		java.lang.String syncCallBackURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.submitSimulationJob(simulationUuid,
			jobUuid, syncCallBackURL);
	}

	@Override
	public long getMaxJobSeqNoSimulationJob(java.lang.String simulationUuid) {
		return _simulationJobLocalService.getMaxJobSeqNoSimulationJob(simulationUuid);
	}

	@Override
	public int deleteSimulationJob(long groupId,
		java.lang.String simulationUuid, long jobPhase, long jobSeqNo) {
		return _simulationJobLocalService.deleteSimulationJob(groupId,
			simulationUuid, jobPhase, jobSeqNo);
	}

	@Override
	public int deleteSimulationParameter(long groupId,
		java.lang.String simulationUuid, long jobSeqNo) {
		return _simulationJobLocalService.deleteSimulationParameter(groupId,
			simulationUuid, jobSeqNo);
	}

	@Override
	public int deleteSimulationCommandOption(long groupId,
		java.lang.String simulationUuid, long optionSeq) {
		return _simulationJobLocalService.deleteSimulationCommandOption(groupId,
			simulationUuid, optionSeq);
	}

	@Override
	public java.util.List getListSimulationJob(long groupId,
		java.lang.String simulationUuid, long jobPhase, long jobSeqNo,
		java.util.Locale locale) {
		return _simulationJobLocalService.getListSimulationJob(groupId,
			simulationUuid, jobPhase, jobSeqNo, locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getMonitoringList(
		long groupId, long userId, java.lang.String searchValue,
		long jobStatus, long classId, long customId, int begin, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _simulationJobLocalService.getMonitoringList(groupId, userId,
			searchValue, jobStatus, classId, customId, begin, end);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getMonitoringJobList(
		long groupId, java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _simulationJobLocalService.getMonitoringJobList(groupId,
			simulationUuid);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getMonitoringJob(
		long groupId, java.lang.String simulationUuid, long jobSeqNo)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _simulationJobLocalService.getMonitoringJob(groupId,
			simulationUuid, jobSeqNo);
	}

	@Override
	public int getMonitoringCount(long groupId, long userId,
		java.lang.String searchValue, long jobStatus, long classId,
		long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getMonitoringCount(groupId, userId,
			searchValue, jobStatus, classId, customId);
	}

	@Override
	public java.lang.String deleteMonitoring(java.lang.String simulationUuid,
		long groupId, long jobSeqNo, com.liferay.portal.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.deleteMonitoring(simulationUuid,
			groupId, jobSeqNo, user);
	}

	@Override
	public long getMaxStatusSeqSimulationJobStatus(long groupId,
		java.lang.String simulationUuid, java.lang.String jobUuid) {
		return _simulationJobLocalService.getMaxStatusSeqSimulationJobStatus(groupId,
			simulationUuid, jobUuid);
	}

	@Override
	public long getStatusSimulationJobStatus(long groupId,
		java.lang.String simulationUuid, java.lang.String jobUuid) {
		return _simulationJobLocalService.getStatusSimulationJobStatus(groupId,
			simulationUuid, jobUuid);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getMigrationSimulationJobStatus(
		long groupId, long jobStatus, boolean dateUpdate) {
		return _simulationJobLocalService.getMigrationSimulationJobStatus(groupId,
			jobStatus, dateUpdate);
	}

	/**
	* 시뮬레이션 프로젝트 모니터링 리스트
	*
	* @param classId - 시뮬레이션 프로젝트 모델 아이디
	* @param customId - 시뮬레이션 프로젝트 아이디
	* @return
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getSimulationMonitoringJobList(
		long classId, long customId, int begin, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _simulationJobLocalService.getSimulationMonitoringJobList(classId,
			customId, begin, end);
	}

	/**
	* 시뮬레이션 프로젝트 모니터링 카운트
	*
	* @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	* @return
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Override
	public int getSimulationMonitoringJobCount(long classId, long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getSimulationMonitoringJobCount(classId,
			customId);
	}

	/**
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ##### 통계 Service #########################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getListVirtualLabSearchUni(
		long groupId, java.lang.String languageId, long jobUniversityField)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _simulationJobLocalService.getListVirtualLabSearchUni(groupId,
			languageId, jobUniversityField);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getListVirtualClassSearchLab(
		long groupId, java.lang.String languageId, long jobVirtualLabId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _simulationJobLocalService.getListVirtualClassSearchLab(groupId,
			languageId, jobVirtualLabId);
	}

	/**
	* ##### 2. Sw START ###################################################################################################################################################
	*/
	@Override
	public java.util.List<java.lang.Long> getSiteCategoryIdList(
		long globalGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getSiteCategoryIdList(globalGroupId,
			groupId);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsSwTableOrganigation(
		long companyGroupId, long groupId, java.util.Locale locale,
		long columnId, java.lang.String startDt, java.lang.String endDt,
		boolean categorySearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _simulationJobLocalService.getStatisticsSwTableOrganigation(companyGroupId,
			groupId, locale, columnId, startDt, endDt, categorySearch);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsSwBarChartDate(
		long companyGroupId, long groupId, java.util.Locale locale,
		long columnId, java.lang.String startDt, java.lang.String endDt,
		boolean categorySearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _simulationJobLocalService.getStatisticsSwBarChartDate(companyGroupId,
			groupId, locale, columnId, startDt, endDt, categorySearch);
	}

	/**
	* ##### 5. User START ###################################################################################################################################################
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsUserTableOrganigation(
		long groupId, java.util.Locale locale, long columnId,
		java.lang.String startDt, java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _simulationJobLocalService.getStatisticsUserTableOrganigation(groupId,
			locale, columnId, startDt, endDt);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsUserBarChartDate(
		long groupId, long columnId, java.lang.String startDt,
		java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _simulationJobLocalService.getStatisticsUserBarChartDate(groupId,
			columnId, startDt, endDt);
	}

	/**
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ##### EDISON 프로젝트 앱, 시뮬레이션 통계 Service #########################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getMajorAchievementsListForProjectStatistics(
		java.lang.String projectYn, java.lang.String key, long groupId)
		throws java.lang.Exception {
		return _simulationJobLocalService.getMajorAchievementsListForProjectStatistics(projectYn,
			key, groupId);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getScienceAppCenterListForProjectStatistics(
		java.lang.String propertyKey, java.util.Locale locale)
		throws java.lang.Exception {
		return _simulationJobLocalService.getScienceAppCenterListForProjectStatistics(propertyKey,
			locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getAppDetailListForProjectStatistics(
		long jobPhase, long columnId, long categoryId,
		java.lang.String languageId, java.util.Locale locale)
		throws java.lang.Exception {
		return _simulationJobLocalService.getAppDetailListForProjectStatistics(jobPhase,
			columnId, categoryId, languageId, locale);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getVirtualClassStatisticsSimulation(
		java.lang.String scienceAppIdObj, java.lang.String virtualLabUsersIdObj) {
		return _simulationJobLocalService.getVirtualClassStatisticsSimulation(scienceAppIdObj,
			virtualLabUsersIdObj);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getVirtualClassStatisticsList(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale, boolean excelFile) {
		return _simulationJobLocalService.getVirtualClassStatisticsList(params,
			locale, excelFile);
	}

	@Override
	public int getCountVirtualClassStatistics(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale) {
		return _simulationJobLocalService.getCountVirtualClassStatistics(params,
			locale);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsBySimulationUuidWithAdditionalCondition(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getJobsBySimulationUuidWithAdditionalCondition(simulationUuid);
	}

	/**
	* Added By Jerry H. Seo
	*
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob addJob(
		java.lang.String simulationUuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.addJob(simulationUuid, groupId);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob getJob(
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return _simulationJobLocalService.getJob(jobUuid);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsByAppUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getJobsByAppUuid(uuid);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsByAppUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getJobsByAppUuid(uuid, start, end);
	}

	@Override
	public int countJobsByAppUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.countJobsByAppUuid(uuid);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsBySimulationUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getJobsBySimulationUuid(uuid);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsBySimulationUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.getJobsBySimulationUuid(uuid, start,
			end);
	}

	@Override
	public int countJobsBySimulationUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationJobLocalService.countJobsBySimulationUuid(uuid);
	}

	@Override
	public java.lang.String getJobInputData(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobDataException {
		return _simulationJobLocalService.getJobInputData(jobUuid);
	}

	@Override
	public void deleteJobsBySimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationJobLocalService.deleteJobsBySimulationUuid(simulationUuid);
	}

	@Override
	public java.lang.String getJobWorkingDirPath(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return _simulationJobLocalService.getJobWorkingDirPath(jobUuid);
	}

	@Override
	public void deleteJob(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		_simulationJobLocalService.deleteJob(jobUuid);
	}

	@Override
	public void executeSchedulerOfClassStatistics(java.util.Map params)
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationJobLocalService.executeSchedulerOfClassStatistics(params);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getVirtualLabClassStatisticsList(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale, boolean excelFile) {
		return _simulationJobLocalService.getVirtualLabClassStatisticsList(params,
			locale, excelFile);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getClassStatisticsManagementList(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale, boolean excelFile) {
		return _simulationJobLocalService.getClassStatisticsManagementList(params,
			locale, excelFile);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SimulationJobLocalService getWrappedSimulationJobLocalService() {
		return _simulationJobLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSimulationJobLocalService(
		SimulationJobLocalService simulationJobLocalService) {
		_simulationJobLocalService = simulationJobLocalService;
	}

	@Override
	public SimulationJobLocalService getWrappedService() {
		return _simulationJobLocalService;
	}

	@Override
	public void setWrappedService(
		SimulationJobLocalService simulationJobLocalService) {
		_simulationJobLocalService = simulationJobLocalService;
	}

	private SimulationJobLocalService _simulationJobLocalService;
}