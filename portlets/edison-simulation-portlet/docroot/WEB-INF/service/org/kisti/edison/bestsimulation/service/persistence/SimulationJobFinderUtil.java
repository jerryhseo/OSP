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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author EDISON
 */
public class SimulationJobFinderUtil {
	public static long getMaxJobSeqNoSimulationJob(
		java.lang.String simulationUuid) {
		return getFinder().getMaxJobSeqNoSimulationJob(simulationUuid);
	}

	public static int deleteSimulationJob(long groupId,
		java.lang.String simulationUuid, long jobPhase, long jobSeqNo) {
		return getFinder()
				   .deleteSimulationJob(groupId, simulationUuid, jobPhase,
			jobSeqNo);
	}

	public static int deleteSimulationCommandOption(long groupId,
		java.lang.String simulationUuid, long optionSeq) {
		return getFinder()
				   .deleteSimulationCommandOption(groupId, simulationUuid,
			optionSeq);
	}

	public static int deleteSimulationParameter(long groupId,
		java.lang.String simulationUuid, long jobSeqNo) {
		return getFinder()
				   .deleteSimulationParameter(groupId, simulationUuid, jobSeqNo);
	}

	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getListSimulationJob(
		long groupId, java.lang.String simulationUuid, long jobPhase,
		long jobSeqNo) {
		return getFinder()
				   .getListSimulationJob(groupId, simulationUuid, jobPhase,
			jobSeqNo);
	}

	public static java.util.List<java.lang.Object[]> getMonitoringList(
		long groupId, long userId, java.lang.String searchValue,
		long jobStatus, long classId, long customId, int begin, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .getMonitoringList(groupId, userId, searchValue, jobStatus,
			classId, customId, begin, end);
	}

	public static int getMonitoringCount(long groupId, long userId,
		java.lang.String searchValue, long jobStatus, long classId,
		long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .getMonitoringCount(groupId, userId, searchValue, jobStatus,
			classId, customId);
	}

	public static java.util.List<java.lang.Object[]> getMonitoringJobList(
		long groupId, java.lang.String simulationUuid, long jobSeqNo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .getMonitoringJobList(groupId, simulationUuid, jobSeqNo);
	}

	public static java.util.List<java.lang.Object[]> getSimulationMonitoringJobList(
		long classId, long customId, int begin, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .getSimulationMonitoringJobList(classId, customId, begin, end);
	}

	public static int getSimulationMonitoringJobCount(long classId,
		long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getSimulationMonitoringJobCount(classId, customId);
	}

	public static int updateSimulationJobSetJobSeqNo(long V_jobSeqNo,
		long jobSeqNo, java.lang.String simulationUuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .updateSimulationJobSetJobSeqNo(V_jobSeqNo, jobSeqNo,
			simulationUuid, groupId);
	}

	public static java.lang.Long getJobSeqNoSimulationJob(long groupId,
		java.lang.String simulationUuid, java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .getJobSeqNoSimulationJob(groupId, simulationUuid, jobUuid);
	}

	public static long getStatusSimulationJobStatus(long groupId,
		java.lang.String simulationUuid, java.lang.String jobUuid) {
		return getFinder()
				   .getStatusSimulationJobStatus(groupId, simulationUuid,
			jobUuid);
	}

	public static long getMaxStatusSeqSimulationJobStatus(long groupId,
		java.lang.String simulationUuid, java.lang.String jobUuid) {
		return getFinder()
				   .getMaxStatusSeqSimulationJobStatus(groupId, simulationUuid,
			jobUuid);
	}

	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> getMigrationSimulationJobStatus(
		long groupId, long jobStatus, boolean dateUpdate) {
		return getFinder()
				   .getMigrationSimulationJobStatus(groupId, jobStatus,
			dateUpdate);
	}

	public static java.util.List<java.lang.Object[]> getListVirtualLabSearchUni(
		long groupId, java.lang.String languageId, long jobUniversityField)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .getListVirtualLabSearchUni(groupId, languageId,
			jobUniversityField);
	}

	public static java.util.List<java.lang.Object[]> getListVirtualClassSearchLab(
		long groupId, java.lang.String languageId, long jobVirtualLabId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .getListVirtualClassSearchLab(groupId, languageId,
			jobVirtualLabId);
	}

	public static java.util.List<java.lang.Object[]> getStatisticsSwTableOrganigation(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getStatisticsSwTableOrganigation(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getStatisticsSwBarChartDate(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getStatisticsSwBarChartDate(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getStatisticsUserTableOrganigation(
		long groupId, long columnId, java.lang.String startDt,
		java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .getStatisticsUserTableOrganigation(groupId, columnId,
			startDt, endDt);
	}

	public static java.util.List<java.lang.Object[]> getStatisticsUserBarChartDate(
		long groupId, long columnId, java.lang.String startDt,
		java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .getStatisticsUserBarChartDate(groupId, columnId, startDt,
			endDt);
	}

	public static java.util.List<java.lang.Object[]> getVirtualClassStatisticsList(
		java.util.Map params, java.util.Locale locale) {
		return getFinder().getVirtualClassStatisticsList(params, locale);
	}

	public static java.lang.Object[] getVirtualClassStatisticsSimulation(
		java.util.Map params) {
		return getFinder().getVirtualClassStatisticsSimulation(params);
	}

	public static int getCountVirtualClassStatistics(java.util.Map params,
		java.util.Locale locale) {
		return getFinder().getCountVirtualClassStatistics(params, locale);
	}

	public static java.util.List<java.lang.Object[]> getMajorAchievementsListForProjectStatistics(
		java.lang.String projectYn, java.lang.String key) {
		return getFinder()
				   .getMajorAchievementsListForProjectStatistics(projectYn, key);
	}

	public static java.util.List<java.lang.Object[]> getScienceAppCenterListForProjectStatistics(
		java.lang.String propertyKey) {
		return getFinder()
				   .getScienceAppCenterListForProjectStatistics(propertyKey);
	}

	public static java.util.List<java.lang.Object[]> getAppDetailListForProjectStatistics(
		long jobPhase, long columnId, long categoryId,
		java.lang.String languageId) {
		return getFinder()
				   .getAppDetailListForProjectStatistics(jobPhase, columnId,
			categoryId, languageId);
	}

	public static java.util.List<java.lang.Object[]> getVirtualClassListForInsertStatistics() {
		return getFinder().getVirtualClassListForInsertStatistics();
	}

	public static java.util.List<java.lang.Object[]> getVirtualLabClassStatisticsList(
		java.util.Map params, java.util.Locale locale) {
		return getFinder().getVirtualLabClassStatisticsList(params, locale);
	}

	public static SimulationJobFinder getFinder() {
		if (_finder == null) {
			_finder = (SimulationJobFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.bestsimulation.service.ClpSerializer.getServletContextName(),
					SimulationJobFinder.class.getName());

			ReferenceRegistry.registerReference(SimulationJobFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SimulationJobFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SimulationJobFinderUtil.class,
			"_finder");
	}

	private static SimulationJobFinder _finder;
}