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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SimulationJob. This utility wraps
 * {@link org.kisti.edison.bestsimulation.service.impl.SimulationJobLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author EDISON
 * @see SimulationJobLocalService
 * @see org.kisti.edison.bestsimulation.service.base.SimulationJobLocalServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.impl.SimulationJobLocalServiceImpl
 * @generated
 */
public class SimulationJobLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.bestsimulation.service.impl.SimulationJobLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the simulation job to the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJob the simulation job
	* @return the simulation job that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob addSimulationJob(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSimulationJob(simulationJob);
	}

	/**
	* Creates a new simulation job with the primary key. Does not add the simulation job to the database.
	*
	* @param simulationJobPK the primary key for the new simulation job
	* @return the new simulation job
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob createSimulationJob(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK) {
		return getService().createSimulationJob(simulationJobPK);
	}

	/**
	* Deletes the simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJobPK the primary key of the simulation job
	* @return the simulation job that was removed
	* @throws PortalException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob deleteSimulationJob(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSimulationJob(simulationJobPK);
	}

	/**
	* Deletes the simulation job from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJob the simulation job
	* @return the simulation job that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob deleteSimulationJob(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSimulationJob(simulationJob);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchSimulationJob(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSimulationJob(simulationJobPK);
	}

	/**
	* Returns the simulation job with the primary key.
	*
	* @param simulationJobPK the primary key of the simulation job
	* @return the simulation job
	* @throws PortalException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob getSimulationJob(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSimulationJob(simulationJobPK);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getSimulationJobs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSimulationJobs(start, end);
	}

	/**
	* Returns the number of simulation jobs.
	*
	* @return the number of simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int getSimulationJobsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSimulationJobsCount();
	}

	/**
	* Updates the simulation job in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param simulationJob the simulation job
	* @return the simulation job that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob updateSimulationJob(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSimulationJob(simulationJob);
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

	public static org.kisti.edison.bestsimulation.model.SimulationJob getSimulationJobWithJobUuid(
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getService().getSimulationJobWithJobUuid(jobUuid);
	}

	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsWithSimulationUuid(
		java.lang.String simulationUuid, int start, int size)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getJobsWithSimulationUuid(simulationUuid, start, size);
	}

	public static long getJobsCountWithSimulationUuid(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJobsCountWithSimulationUuid(simulationUuid);
	}

	public static org.kisti.edison.bestsimulation.model.SimulationJob createSimulationJob(
		java.lang.String simulationUuid, long groupId, java.lang.String jobTitle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createSimulationJob(simulationUuid, groupId, jobTitle);
	}

	public static org.kisti.edison.bestsimulation.model.SimulationJob createSimulationJobWithJobData(
		java.lang.String simulationUuid, long groupId,
		java.lang.String jobTitle, java.lang.String jobData)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createSimulationJobWithJobData(simulationUuid, groupId,
			jobTitle, jobData);
	}

	public static void deleteSimulationJob(java.lang.String simulationUuid,
		long jobSeqNo, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		getService().deleteSimulationJob(simulationUuid, jobSeqNo, groupId);
	}

	public static void deleteSimulationJobByJobUuid(
		java.lang.String simulationUuid, java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSimulationJobByJobUuid(simulationUuid, jobUuid);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> submitSimulationJob(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		java.lang.String syncCallBackURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .submitSimulationJob(simulationUuid, jobUuid, syncCallBackURL);
	}

	public static long getMaxJobSeqNoSimulationJob(
		java.lang.String simulationUuid) {
		return getService().getMaxJobSeqNoSimulationJob(simulationUuid);
	}

	public static int deleteSimulationJob(long groupId,
		java.lang.String simulationUuid, long jobPhase, long jobSeqNo) {
		return getService()
				   .deleteSimulationJob(groupId, simulationUuid, jobPhase,
			jobSeqNo);
	}

	public static int deleteSimulationParameter(long groupId,
		java.lang.String simulationUuid, long jobSeqNo) {
		return getService()
				   .deleteSimulationParameter(groupId, simulationUuid, jobSeqNo);
	}

	public static int deleteSimulationCommandOption(long groupId,
		java.lang.String simulationUuid, long optionSeq) {
		return getService()
				   .deleteSimulationCommandOption(groupId, simulationUuid,
			optionSeq);
	}

	public static java.util.List getListSimulationJob(long groupId,
		java.lang.String simulationUuid, long jobPhase, long jobSeqNo,
		java.util.Locale locale) {
		return getService()
				   .getListSimulationJob(groupId, simulationUuid, jobPhase,
			jobSeqNo, locale);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getMonitoringList(
		long groupId, long userId, java.lang.String searchValue,
		long jobStatus, long classId, long customId, int begin, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getMonitoringList(groupId, userId, searchValue, jobStatus,
			classId, customId, begin, end);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getMonitoringJobList(
		long groupId, java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService().getMonitoringJobList(groupId, simulationUuid);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> getMonitoringJob(
		long groupId, java.lang.String simulationUuid, long jobSeqNo)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService().getMonitoringJob(groupId, simulationUuid, jobSeqNo);
	}

	public static int getMonitoringCount(long groupId, long userId,
		java.lang.String searchValue, long jobStatus, long classId,
		long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getMonitoringCount(groupId, userId, searchValue, jobStatus,
			classId, customId);
	}

	public static java.lang.String deleteMonitoring(
		java.lang.String simulationUuid, long groupId, long jobSeqNo,
		com.liferay.portal.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteMonitoring(simulationUuid, groupId, jobSeqNo, user);
	}

	public static long getMaxStatusSeqSimulationJobStatus(long groupId,
		java.lang.String simulationUuid, java.lang.String jobUuid) {
		return getService()
				   .getMaxStatusSeqSimulationJobStatus(groupId, simulationUuid,
			jobUuid);
	}

	public static long getStatusSimulationJobStatus(long groupId,
		java.lang.String simulationUuid, java.lang.String jobUuid) {
		return getService()
				   .getStatusSimulationJobStatus(groupId, simulationUuid,
			jobUuid);
	}

	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getMigrationSimulationJobStatus(
		long groupId, long jobStatus, boolean dateUpdate) {
		return getService()
				   .getMigrationSimulationJobStatus(groupId, jobStatus,
			dateUpdate);
	}

	/**
	* 占쎌뮆占쏙옙�됱뵠占쏙옙占쎄쑬以덌옙�븍뱜 筌뤴뫀�뀐옙怨뺤춦 �귐딅뮞占쏙옙     *
	*
	* @param classId - 占쎌뮆占쏙옙�됱뵠占쏙옙占쎄쑬以덌옙�븍뱜 筌뤴뫀��占쎄쑴�좑옙占�    * @param customId - 占쎌뮆占쏙옙�됱뵠占쏙옙占쎄쑬以덌옙�븍뱜 占쎄쑴�좑옙占�    * @return
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getSimulationMonitoringJobList(
		long classId, long customId, int begin, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getSimulationMonitoringJobList(classId, customId, begin, end);
	}

	/**
	* 占쎌뮆占쏙옙�됱뵠占쏙옙占쎄쑬以덌옙�븍뱜 筌뤴뫀�뀐옙怨뺤춦 燁삳똻�ワ옙占�    *
	*
	* @param simulationProjectId - 占쎌뮆占쏙옙�됱뵠占쏙옙占쎄쑬以덌옙�븍뱜 占쎄쑴�좑옙占�    * @return
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	public static int getSimulationMonitoringJobCount(long classId,
		long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSimulationMonitoringJobCount(classId, customId);
	}

	/**
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ##### 占쎈벀��Service #########################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getListVirtualLabSearchUni(
		long groupId, java.lang.String languageId, long jobUniversityField)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getListVirtualLabSearchUni(groupId, languageId,
			jobUniversityField);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getListVirtualClassSearchLab(
		long groupId, java.lang.String languageId, long jobVirtualLabId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getListVirtualClassSearchLab(groupId, languageId,
			jobVirtualLabId);
	}

	/**
	* ##### 2. Sw START ###################################################################################################################################################
	*/
	public static java.util.List<java.lang.Long> getSiteCategoryIdList(
		long globalGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSiteCategoryIdList(globalGroupId, groupId);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsSwTableOrganigation(
		long companyGroupId, long groupId, java.util.Locale locale,
		long columnId, java.lang.String startDt, java.lang.String endDt,
		boolean categorySearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getStatisticsSwTableOrganigation(companyGroupId, groupId,
			locale, columnId, startDt, endDt, categorySearch);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsSwBarChartDate(
		long companyGroupId, long groupId, java.util.Locale locale,
		long columnId, java.lang.String startDt, java.lang.String endDt,
		boolean categorySearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getStatisticsSwBarChartDate(companyGroupId, groupId,
			locale, columnId, startDt, endDt, categorySearch);
	}

	/**
	* ##### 5. User START ###################################################################################################################################################
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsUserTableOrganigation(
		long groupId, java.util.Locale locale, long columnId,
		java.lang.String startDt, java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getStatisticsUserTableOrganigation(groupId, locale,
			columnId, startDt, endDt);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsUserBarChartDate(
		long groupId, long columnId, java.lang.String startDt,
		java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getStatisticsUserBarChartDate(groupId, columnId, startDt,
			endDt);
	}

	/**
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ##### EDISON 占쎄쑬以덌옙�븍뱜 占쏙옙 占쎌뮆占쏙옙�됱뵠占쏙옙占쎈벀��Service #########################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	* ###########################################################################################################################################################################
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getMajorAchievementsListForProjectStatistics(
		java.lang.String projectYn, java.lang.String key, long groupId)
		throws java.lang.Exception {
		return getService()
				   .getMajorAchievementsListForProjectStatistics(projectYn,
			key, groupId);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getScienceAppCenterListForProjectStatistics(
		java.lang.String propertyKey, java.util.Locale locale)
		throws java.lang.Exception {
		return getService()
				   .getScienceAppCenterListForProjectStatistics(propertyKey,
			locale);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getAppDetailListForProjectStatistics(
		long jobPhase, long columnId, long categoryId,
		java.lang.String languageId, java.util.Locale locale)
		throws java.lang.Exception {
		return getService()
				   .getAppDetailListForProjectStatistics(jobPhase, columnId,
			categoryId, languageId, locale);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> getVirtualClassStatisticsSimulation(
		java.lang.String scienceAppIdObj, java.lang.String virtualLabUsersIdObj) {
		return getService()
				   .getVirtualClassStatisticsSimulation(scienceAppIdObj,
			virtualLabUsersIdObj);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getVirtualClassStatisticsList(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale, boolean excelFile) {
		return getService()
				   .getVirtualClassStatisticsList(params, locale, excelFile);
	}

	public static int getCountVirtualClassStatistics(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale) {
		return getService().getCountVirtualClassStatistics(params, locale);
	}

	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsBySimulationUuidWithAdditionalCondition(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getJobsBySimulationUuidWithAdditionalCondition(simulationUuid);
	}

	/**
	* Added By Jerry H. Seo
	*
	* @throws SystemException
	* @throws PortalException
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob addJob(
		java.lang.String simulationUuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addJob(simulationUuid, groupId);
	}

	public static org.kisti.edison.bestsimulation.model.SimulationJob getJob(
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getService().getJob(jobUuid);
	}

	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsByAppUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJobsByAppUuid(uuid);
	}

	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsByAppUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJobsByAppUuid(uuid, start, end);
	}

	public static int countJobsByAppUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countJobsByAppUuid(uuid);
	}

	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsBySimulationUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJobsBySimulationUuid(uuid);
	}

	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getJobsBySimulationUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJobsBySimulationUuid(uuid, start, end);
	}

	public static int countJobsBySimulationUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countJobsBySimulationUuid(uuid);
	}

	public static java.lang.String getJobInputData(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobDataException {
		return getService().getJobInputData(jobUuid);
	}

	public static void deleteJobsBySimulationUuid(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteJobsBySimulationUuid(simulationUuid);
	}

	public static java.lang.String getJobWorkingDirPath(
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getService().getJobWorkingDirPath(jobUuid);
	}

	public static void deleteJob(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		getService().deleteJob(jobUuid);
	}

	public static void clearService() {
		_service = null;
	}

	public static SimulationJobLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SimulationJobLocalService.class.getName());

			if (invokableLocalService instanceof SimulationJobLocalService) {
				_service = (SimulationJobLocalService)invokableLocalService;
			}
			else {
				_service = new SimulationJobLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SimulationJobLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SimulationJobLocalService service) {
	}

	private static SimulationJobLocalService _service;
}