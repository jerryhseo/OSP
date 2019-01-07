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
 * Provides a wrapper for {@link SimulationLocalService}.
 *
 * @author EDISON
 * @see SimulationLocalService
 * @generated
 */
public class SimulationLocalServiceWrapper implements SimulationLocalService,
	ServiceWrapper<SimulationLocalService> {
	public SimulationLocalServiceWrapper(
		SimulationLocalService simulationLocalService) {
		_simulationLocalService = simulationLocalService;
	}

	/**
	* Adds the simulation to the database. Also notifies the appropriate model listeners.
	*
	* @param simulation the simulation
	* @return the simulation that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.Simulation addSimulation(
		org.kisti.edison.bestsimulation.model.Simulation simulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.addSimulation(simulation);
	}

	/**
	* Creates a new simulation with the primary key. Does not add the simulation to the database.
	*
	* @param simulationPK the primary key for the new simulation
	* @return the new simulation
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.Simulation createSimulation(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK) {
		return _simulationLocalService.createSimulation(simulationPK);
	}

	/**
	* Deletes the simulation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationPK the primary key of the simulation
	* @return the simulation that was removed
	* @throws PortalException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.Simulation deleteSimulation(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.deleteSimulation(simulationPK);
	}

	/**
	* Deletes the simulation from the database. Also notifies the appropriate model listeners.
	*
	* @param simulation the simulation
	* @return the simulation that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.Simulation deleteSimulation(
		org.kisti.edison.bestsimulation.model.Simulation simulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.deleteSimulation(simulation);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _simulationLocalService.dynamicQuery();
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
		return _simulationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _simulationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _simulationLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _simulationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _simulationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation fetchSimulation(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.fetchSimulation(simulationPK);
	}

	/**
	* Returns the simulation with the primary key.
	*
	* @param simulationPK the primary key of the simulation
	* @return the simulation
	* @throws PortalException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.Simulation getSimulation(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getSimulation(simulationPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the simulations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of simulations
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> getSimulations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getSimulations(start, end);
	}

	/**
	* Returns the number of simulations.
	*
	* @return the number of simulations
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSimulationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getSimulationsCount();
	}

	/**
	* Updates the simulation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param simulation the simulation
	* @return the simulation that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.Simulation updateSimulation(
		org.kisti.edison.bestsimulation.model.Simulation simulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.updateSimulation(simulation);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _simulationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_simulationLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _simulationLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> getSimulationsWithScienceAppId(
		long scienceAppId, long userId, boolean isTest, long classId,
		long customId, int begin, int size)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getSimulationsWithScienceAppId(scienceAppId,
			userId, isTest, classId, customId, begin, size);
	}

	@Override
	public long getSimulationsCountWithScienceAppId(long scienceAppId,
		long userId, boolean isTest, long classId, long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getSimulationsCountWithScienceAppId(scienceAppId,
			userId, isTest, classId, customId);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> getSimulationsWithJobUuid(
		long scienceAppId, long userId, java.lang.String jobUuid, long classId,
		long customId, int begin, int size)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getSimulationsWithJobUuid(scienceAppId,
			userId, jobUuid, classId, customId, begin, size);
	}

	@Override
	public long getSimulationsCountWithJobUuid(long scienceAppId, long userId,
		java.lang.String jobUuid, long classId, long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getSimulationsCountWithJobUuid(scienceAppId,
			userId, jobUuid, classId, customId);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getSimulationBySimulationUuid(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return _simulationLocalService.getSimulationBySimulationUuid(simulationUuid);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation getSimulationByUUID(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return _simulationLocalService.getSimulationByUUID(uuid);
	}

	/**
	* New Simulation With SimulationJob
	*
	* @param user                - 현재 접속한 User 객체, EX)User user = PortalUtil.getUser(request);
	* @param groupId             - Portal 실행 일 경우 APP의 groupId를 참고, 그외 siteGroupId 셋팅
	* @param simulationTitle
	* @param scienceAppId
	* @param scienceAppName
	* @param classId
	* @param customId
	* @param testYn
	* @return
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> createSimulationWithJob(
		com.liferay.portal.model.User user, long groupId,
		java.lang.String simulationTitle, long scienceAppId,
		java.lang.String scienceAppName, long classId, long customId,
		boolean testYn)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.createSimulationWithJob(user, groupId,
			simulationTitle, scienceAppId, scienceAppName, classId, customId,
			testYn);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> createSimulationWithJob(
		com.liferay.portal.model.User user, long groupId,
		java.lang.String appName, java.lang.String appVersion,
		java.lang.String simulationTitle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.createSimulationWithJob(user, groupId,
			appName, appVersion, simulationTitle);
	}

	/**
	* New Simulation With SimulationJob
	*
	* @param user                - 현재 접속한 User 객체, EX)User user = PortalUtil.getUser(request);
	* @param groupId             - Portal GroupId
	* @param scienceAppName
	* @param scienceAppVersion
	* @param simulationUuid
	* @param jobUuid
	* @param jobData
	* @return
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> createSimulationWithJob(
		com.liferay.portal.model.User user,
		com.liferay.portal.model.Group group, java.lang.String appName,
		java.lang.String appVersion, java.lang.String simulationUuid,
		java.lang.String jobUuid, java.lang.String jobData,
		boolean hasSimulation)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.createSimulationWithJob(user, group,
			appName, appVersion, simulationUuid, jobUuid, jobData, hasSimulation);
	}

	@Override
	public org.kisti.edison.model.IcebreakerVcToken getOrCreateToken(
		long thisGroupId, com.liferay.portal.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.net.MalformedURLException,
			java.text.ParseException {
		return _simulationLocalService.getOrCreateToken(thisGroupId, user);
	}

	/**
	* 시뮬레이션 수행
	*
	* @simulationUuid
	* @fileId
	* @Token
	* @title
	* @description
	* @scienceApp_name
	* @return
	* @throws ParserConfigurationException
	* @throws SAXException
	*/
	@Override
	public java.util.Map executeJob(java.lang.String icebreakerUrl,
		java.util.Map params)
		throws java.io.IOException, java.net.MalformedURLException,
			javax.xml.parsers.ParserConfigurationException,
			org.xml.sax.SAXException {
		return _simulationLocalService.executeJob(icebreakerUrl, params);
	}

	/**
	* 시뮬레이션 수행
	*
	* @simulationUuid
	* @job_uuid
	* @Token
	* @return status
	*/
	@Override
	public java.util.Map statusJob(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String simulationUuid,
		java.lang.String job_uuid)
		throws java.io.IOException, java.net.MalformedURLException {
		return _simulationLocalService.statusJob(icebreakerUrl, vcToken,
			simulationUuid, job_uuid);
	}

	/**
	* 시뮬레이션 수정
	*
	* @throws JSONException
	* @Token : 인증 토큰
	* @uuid : 시뮬레이션 아이디
	* @return int resultCode
	*/
	@Override
	public int updateSimulation(java.lang.String icebreakerUrl,
		java.lang.String simulationUuid, java.lang.String vcToken,
		java.lang.String title, java.lang.String description)
		throws java.io.IOException, java.net.MalformedURLException {
		return _simulationLocalService.updateSimulation(icebreakerUrl,
			simulationUuid, vcToken, title, description);
	}

	/**
	* 파일 업로드
	*
	* @param params
	String    Token
	File        file
	* @throws MalformedURLException
	* @throws IOException
	* @throws JSONException
	* @throws InterruptedException
	*/
	@Override
	public java.util.Map uploadFile(java.lang.String cluster,
		java.lang.String icebreakerUrl, java.lang.String vcToken,
		java.io.File file)
		throws java.io.IOException, java.lang.InterruptedException,
			java.net.MalformedURLException {
		return _simulationLocalService.uploadFile(cluster, icebreakerUrl,
			vcToken, file);
	}

	/**
	* 파일 삭제
	*
	* @param icebreakerUrl
	* @param vcToken
	* @param fileId
	* @throws IOException
	*/
	@Override
	public void deleteFile(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String fileId)
		throws java.io.IOException {
		_simulationLocalService.deleteFile(icebreakerUrl, vcToken, fileId);
	}

	/**
	* serverFile 목록
	*/
	@Override
	public java.lang.String getServerFileList(java.lang.String icebreakerUrl,
		java.lang.String cmd_directory, java.lang.String cluster,
		java.lang.String vcToken)
		throws java.io.IOException, java.net.MalformedURLException {
		return _simulationLocalService.getServerFileList(icebreakerUrl,
			cmd_directory, cluster, vcToken);
	}

	/**
	* job별 결과 파일 zip형태로 다운로드
	*
	* @simulationUuid
	* @job_uuid
	* @Token
	* @return status
	*/
	@Override
	public java.io.InputStream downloadFileJob(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String job_uuid)
		throws java.io.IOException, java.net.MalformedURLException {
		return _simulationLocalService.downloadFileJob(icebreakerUrl, vcToken,
			job_uuid);
	}

	/**
	* job error 보기
	*
	* @param params
	* @throws IOException
	* @throws JSONException
	*/
	@Override
	public java.lang.String errorView(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String job_uuid)
		throws java.io.IOException {
		return _simulationLocalService.errorView(icebreakerUrl, vcToken,
			job_uuid);
	}

	/**
	* 파일 ID 확인
	*
	* @throws IOException
	*/
	@Override
	public java.lang.String retrieveFileId(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String job_uuid,
		java.lang.String fileName) throws java.io.IOException {
		return _simulationLocalService.retrieveFileId(icebreakerUrl, vcToken,
			job_uuid, fileName);
	}

	/**
	* 디렉토리 파일 조회
	* 일반적인 후처리기 목록은 dir = result
	*
	* @throws IOException
	*/
	@Override
	public java.lang.String retrievePostProcessor(
		java.lang.String icebreakerUrl, java.lang.String vcToken,
		java.lang.String jobUuid) throws java.io.IOException {
		return _simulationLocalService.retrievePostProcessor(icebreakerUrl,
			vcToken, jobUuid);
	}

	@Override
	public java.lang.String retrieveRemoteDir(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String jobUuid,
		java.lang.String dirPath) throws java.io.IOException {
		return _simulationLocalService.retrieveRemoteDir(icebreakerUrl,
			vcToken, jobUuid, dirPath);
	}

	@Override
	public java.lang.String retrieveAllPostProcessor(
		java.lang.String icebreakerUrl, java.lang.String vcToken,
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _simulationLocalService.retrieveAllPostProcessor(icebreakerUrl,
			vcToken, jobUuid);
	}

	@Override
	public java.lang.String retrieveAllRemoteDir(
		java.lang.String icebreakerUrl, java.lang.String vcToken,
		java.lang.String jobUuid, java.lang.String dirPath)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _simulationLocalService.retrieveAllRemoteDir(icebreakerUrl,
			vcToken, jobUuid, dirPath);
	}

	/**
	* simulation job 중지
	*
	* @param params
	* @return
	* @throws IOException
	*/
	@Override
	public int cancleJob(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String simulationUuid,
		java.lang.String job_uuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _simulationLocalService.cancleJob(icebreakerUrl, vcToken,
			simulationUuid, job_uuid);
	}

	/**
	* Cluster List 조회
	*
	* @param params
	* @return
	* @throws IOException
	* @throws JSONException
	*/
	@Override
	public net.sf.json.JSONObject retrieveCluster(
		java.lang.String icebreakerUrl, java.lang.String vcToken)
		throws java.io.IOException {
		return _simulationLocalService.retrieveCluster(icebreakerUrl, vcToken);
	}

	/**
	* job error 보기
	*
	* @param params
	* @throws IOException
	* @throws JSONException
	*/
	@Override
	public java.lang.String getResultRead(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String fileId)
		throws java.io.IOException {
		return _simulationLocalService.getResultRead(icebreakerUrl, vcToken,
			fileId);
	}

	/**
	* file 조회
	*
	* @param params
	* @throws IOException
	* @throws JSONException
	*/
	@Override
	public java.io.InputStream getFileRead(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String fileId)
		throws java.io.IOException {
		return _simulationLocalService.getFileRead(icebreakerUrl, vcToken,
			fileId);
	}

	/**
	* webgl을 위한 파일 생성 후 url 리턴(임시 사용-추후 수정 예정)
	*
	* @param params
	* @throws IOException
	* @throws JSONException
	*/
	@Override
	public java.lang.String getResultFile(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String simulationUuid,
		java.lang.String fileName) throws java.io.IOException {
		return _simulationLocalService.getResultFile(icebreakerUrl, vcToken,
			simulationUuid, fileName);
	}

	/**
	* Get Current Assignd Core Count
	*
	* @param params
	* @throws IOException
	*/
	@Override
	public java.lang.String getCurrentAssignedCoresByUser(
		java.lang.String icebreakerUrl, java.lang.String vcToken)
		throws java.io.IOException {
		return _simulationLocalService.getCurrentAssignedCoresByUser(icebreakerUrl,
			vcToken);
	}

	/**
	* getUserRepositorySize 조회
	*
	* @param params
	* @return
	* @throws IOException
	* @throws JSONException
	*/
	@Override
	public java.lang.String getUserRepositorySize(
		java.lang.String icebreakerUrl, java.lang.String vcToken)
		throws java.io.IOException {
		return _simulationLocalService.getUserRepositorySize(icebreakerUrl,
			vcToken);
	}

	@Override
	public int deleteSimulationJob(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String simulationUuid,
		java.lang.String jobUuid) throws java.io.IOException {
		return _simulationLocalService.deleteSimulationJob(icebreakerUrl,
			vcToken, simulationUuid, jobUuid);
	}

	/**
	* ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	* ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	* ■■■■■■■■■ Icebreaker Service End ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	* ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	* ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	*/
	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserIdAndGroupId(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.findByUserIdAndGroupId(groupId, userId);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.findByGroupId(groupId, start, end);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserIdAndGroupId(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.findByUserIdAndGroupId(groupId, userId,
			start, end);
	}

	@Override
	public int countByUserIdAndGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.countByUserIdAndGroupId(groupId);
	}

	@Override
	public int countByUserIdAndGroupId(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.countByUserIdAndGroupId(groupId, userId);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserIdAndTitle(
		long groupId, long userId, java.lang.String tittleKeyword, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.findByUserIdAndTitle(groupId, userId,
			tittleKeyword, start, end);
	}

	@Override
	public long countByUserIdAndTitle(long groupId, long userId,
		java.lang.String tittleKeyword)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.countByUserIdAndTitle(groupId, userId,
			tittleKeyword);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByTitle(
		long groupId, java.lang.String tittleKeyword, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.findByTitle(groupId, tittleKeyword,
			start, end);
	}

	@Override
	public long countByTitle(long groupId, java.lang.String tittleKeyword)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.countByTitle(groupId, tittleKeyword);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserIdAndGroupIdAndTitle(
		long groupId, long userId, java.lang.String tittleKeyword, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.findByUserIdAndGroupIdAndTitle(groupId,
			userId, tittleKeyword, start, end);
	}

	@Override
	public long countByUserIdAndGroupIdAndTitle(long groupId, long userId,
		java.lang.String tittleKeyword)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.countByUserIdAndGroupIdAndTitle(groupId,
			userId, tittleKeyword);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByGroupIdAndTitle(
		long groupId, java.lang.String tittleKeyword, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.findByGroupIdAndTitle(groupId,
			tittleKeyword, start, end);
	}

	@Override
	public long countByGroupIdAndTitle(long groupId,
		java.lang.String tittleKeyword)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.countByGroupIdAndTitle(groupId,
			tittleKeyword);
	}

	/**
	* Added By Jerry H. Seo
	*
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.Simulation addSimulation(
		java.lang.String uuid, java.lang.String title, long scienceAppId,
		java.lang.String scienceAppName, java.lang.String scienceAppVersion,
		long srcClassCode, long srcClassId,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.addSimulation(uuid, title, scienceAppId,
			scienceAppName, scienceAppVersion, srcClassCode, srcClassId, sc);
	}

	@Override
	public void deleteSimulation(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		_simulationLocalService.deleteSimulation(simulationUuid);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob addJob(
		java.lang.String simulationUUID,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.addJob(simulationUUID, sc);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob addJob(
		java.lang.String simulationUUID, java.lang.String scienceAppName,
		java.lang.String scienceAppVersion,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.addJob(simulationUUID, scienceAppName,
			scienceAppVersion, sc);
	}

	@Override
	public void deleteJob(java.lang.String simulationUuid,
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationLocalService.deleteJob(simulationUuid, jobUuid);
	}

	@Override
	public void deleteAllJobs(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationLocalService.deleteAllJobs(simulationUuid);
	}

	@Override
	public java.lang.String getJobData(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getJobData(jobUuid);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> getSimulationsByAppId(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getSimulationsByAppId(scienceAppId);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> getSimulationsByAppId(
		long scienceAppId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.getSimulationsByAppId(scienceAppId,
			start, end);
	}

	@Override
	public int countSimulationsByAppId(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationLocalService.countSimulationsByAppId(scienceAppId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SimulationLocalService getWrappedSimulationLocalService() {
		return _simulationLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSimulationLocalService(
		SimulationLocalService simulationLocalService) {
		_simulationLocalService = simulationLocalService;
	}

	@Override
	public SimulationLocalService getWrappedService() {
		return _simulationLocalService;
	}

	@Override
	public void setWrappedService(SimulationLocalService simulationLocalService) {
		_simulationLocalService = simulationLocalService;
	}

	private SimulationLocalService _simulationLocalService;
}