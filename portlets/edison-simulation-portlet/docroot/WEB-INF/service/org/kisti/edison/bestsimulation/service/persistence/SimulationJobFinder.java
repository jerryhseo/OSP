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

package org.kisti.edison.bestsimulation.service.persistence;

/**
 * @author EDISON
 */
public interface SimulationJobFinder {
	public long getMaxJobSeqNoSimulationJob(java.lang.String simulationUuid);

	public int deleteSimulationJob(long groupId,
		java.lang.String simulationUuid, long jobPhase, long jobSeqNo);

	public int deleteSimulationCommandOption(long groupId,
		java.lang.String simulationUuid, long optionSeq);

	public int deleteSimulationParameter(long groupId,
		java.lang.String simulationUuid, long jobSeqNo);

	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getListSimulationJob(
		long groupId, java.lang.String simulationUuid, long jobPhase,
		long jobSeqNo);

	public java.util.List<java.lang.Object[]> getMonitoringList(long groupId,
		long userId, java.lang.String searchValue, long jobStatus,
		long classId, long customId, int begin, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int getMonitoringCount(long groupId, long userId,
		java.lang.String searchValue, long jobStatus, long classId,
		long customId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> getMonitoringJobList(
		long groupId, java.lang.String simulationUuid, long jobSeqNo)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> getSimulationMonitoringJobList(
		long classId, long customId, int begin, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int getSimulationMonitoringJobCount(long classId, long customId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int updateSimulationJobSetJobSeqNo(long V_jobSeqNo, long jobSeqNo,
		java.lang.String simulationUuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.Long getJobSeqNoSimulationJob(long groupId,
		java.lang.String simulationUuid, java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	public long getStatusSimulationJobStatus(long groupId,
		java.lang.String simulationUuid, java.lang.String jobUuid);

	public long getMaxStatusSeqSimulationJobStatus(long groupId,
		java.lang.String simulationUuid, java.lang.String jobUuid);

	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getMigrationSimulationJobStatus(
		long groupId, long jobStatus, boolean dateUpdate);

	public java.util.List<java.lang.Object[]> getListVirtualLabSearchUni(
		long groupId, java.lang.String languageId, long jobUniversityField)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> getListVirtualClassSearchLab(
		long groupId, java.lang.String languageId, long jobVirtualLabId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> getStatisticsSwTableOrganigation(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> getStatisticsSwBarChartDate(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> getStatisticsUserTableOrganigation(
		long groupId, long columnId, java.lang.String startDt,
		java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> getStatisticsUserBarChartDate(
		long groupId, long columnId, java.lang.String startDt,
		java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> getVirtualClassStatisticsList(
		java.util.Map params, java.util.Locale locale);

	public java.lang.Object[] getVirtualClassStatisticsSimulation(
		java.util.Map params);

	public int getCountVirtualClassStatistics(java.util.Map params,
		java.util.Locale locale);

	public java.util.List<java.lang.Object[]> getMajorAchievementsListForProjectStatistics(
		java.lang.String projectYn, java.lang.String key);

	public java.util.List<java.lang.Object[]> getScienceAppCenterListForProjectStatistics(
		java.lang.String propertyKey);

	public java.util.List<java.lang.Object[]> getAppDetailListForProjectStatistics(
		long jobPhase, long columnId, long categoryId,
		java.lang.String languageId);

	public java.util.List<java.lang.Object[]> getVirtualClassListForInsertStatistics(
		java.util.Map params);

	public java.util.List<java.lang.Object[]> getVirtualLabClassStatisticsList(
		java.util.Map params, java.util.Locale locale);

	public java.util.List<java.lang.Object[]> getClassStatisticsManagementList(
		java.util.Map params, java.util.Locale locale);
}