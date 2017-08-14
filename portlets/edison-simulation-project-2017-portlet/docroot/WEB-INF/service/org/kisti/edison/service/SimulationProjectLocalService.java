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
 * Provides the local service interface for SimulationProject. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author edison
 * @see SimulationProjectLocalServiceUtil
 * @see org.kisti.edison.service.base.SimulationProjectLocalServiceBaseImpl
 * @see org.kisti.edison.service.impl.SimulationProjectLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SimulationProjectLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SimulationProjectLocalServiceUtil} to access the simulation project local service. Add custom service methods to {@link org.kisti.edison.service.impl.SimulationProjectLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the simulation project to the database. Also notifies the appropriate model listeners.
	*
	* @param simulationProject the simulation project
	* @return the simulation project that was added
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public org.kisti.edison.model.SimulationProject addSimulationProject(
		org.kisti.edison.model.SimulationProject simulationProject)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new simulation project with the primary key. Does not add the simulation project to the database.
	*
	* @param simulationProjectId the primary key for the new simulation project
	* @return the new simulation project
	*/
	public org.kisti.edison.model.SimulationProject createSimulationProject(
		long simulationProjectId);

	/**
	* Deletes the simulation project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationProjectId the primary key of the simulation project
	* @return the simulation project that was removed
	* @throws PortalException if a simulation project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public org.kisti.edison.model.SimulationProject deleteSimulationProject(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the simulation project from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationProject the simulation project
	* @return the simulation project that was removed
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public org.kisti.edison.model.SimulationProject deleteSimulationProject(
		org.kisti.edison.model.SimulationProject simulationProject)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public org.kisti.edison.model.SimulationProject fetchSimulationProject(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the simulation project with the primary key.
	*
	* @param simulationProjectId the primary key of the simulation project
	* @return the simulation project
	* @throws PortalException if a simulation project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public org.kisti.edison.model.SimulationProject getSimulationProject(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the simulation projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation projects
	* @param end the upper bound of the range of simulation projects (not inclusive)
	* @return the range of simulation projects
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.kisti.edison.model.SimulationProject> getSimulationProjects(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of simulation projects.
	*
	* @return the number of simulation projects
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSimulationProjectsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the simulation project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param simulationProject the simulation project
	* @return the simulation project that was updated
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public org.kisti.edison.model.SimulationProject updateSimulationProject(
		org.kisti.edison.model.SimulationProject simulationProject)
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.lang.Long> getSiteCategoryIdList(
		long globalGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Simulation Project List Get (Portal - Admin)
	* Use Simulation Project
	*
	* @param begin
	* @param end
	* @param searchValue - 검색어
	* @param locale
	* @return List<Map<String,Object>>
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCustomMySimulationProjectList(
		int begin, int end, java.lang.String searchValue,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Get (Portal - User)
	* Use Simulation Project
	*
	* @param ownerId - 프로젝트 소유자 아이디
	* @param begin
	* @param end
	* @param searchValue - 검색어
	* @param locale
	* @return List<Map<String,Object>>
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCustomMySimulationProjectList(
		long ownerId, int begin, int end, java.lang.String searchValue,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Get (Site - Admin, Site Admin)
	* Use Simulation Project
	*
	* @param begin
	* @param end
	* @param searchValue - 검색어
	* @param locale
	* @return List<Map<String,Object>>
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCustomMySimulationProjectList(
		int begin, int end, java.lang.String searchValue,
		java.util.Locale locale,
		java.util.List<java.lang.Long> siteCategoryIdList)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Get (Site - User)
	* Use Simulation Project
	*
	* @param ownerId - 프로젝트 소유자 아이디
	* @param begin
	* @param end
	* @param searchValue - 검색어
	* @param locale
	* @return List<Map<String,Object>>
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCustomMySimulationProjectList(
		long ownerId, int begin, int end, java.lang.String searchValue,
		java.util.Locale locale,
		java.util.List<java.lang.Long> siteCategoryIdList)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Count (Portal - Admin)
	* Use Simulation Project or Integrated Search
	*
	* @param searchValue - 검색어
	* @return int - count
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCustomMySimulationProjectCount(java.lang.String searchValue,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Count (Portal - User)
	* Use Simulation Project
	*
	* @param ownerId - 프로젝트 소유자 아이디
	* @param searchValue - 검색어
	* @return int - count
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCustomMySimulationProjectCount(long ownerId,
		java.lang.String searchValue, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Count (Site - Admin, Site Admin)
	* Use Simulation Project
	*
	* @param searchValue - 검색어
	* @param siteCategoryIdList - 카테고리 아이디 리스트
	* @return int - count
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCustomMySimulationProjectCount(java.lang.String searchValue,
		java.util.List<java.lang.Long> siteCategoryIdList,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Count (Site - User)
	* Use Simulation Project
	*
	* @param ownerId - 프로젝트 소유자 아이디
	* @param siteCategoryIdList - 카테고리 아이디 리스트
	* @return int - count
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCustomMySimulationProjectCount(long ownerId,
		java.lang.String searchValue,
		java.util.List<java.lang.Long> siteCategoryIdList,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Get
	* Use Integrated Search
	*
	* @param begin
	* @param end
	* @param searchValue - 검색어
	* @param locale
	* @param siteCategoryIdList - 카테고리 아이디 리스트
	* @return List<Map<String,Object>>
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCustomIntegratedSearchSimulationProjectList(
		int begin, int end, java.lang.String searchValue,
		java.util.Locale locale,
		java.util.List<java.lang.Long> siteCategoryIdList)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Count
	* Use Integrated Search
	*
	* @param ownerId - 프로젝트 소유자 아이디
	* @param siteCategoryIdList - 카테고리 아이디 리스트
	* @return int
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCustomIntegratedSearchSimulationProjectCount(
		java.lang.String searchValue,
		java.util.List<java.lang.Long> siteCategoryIdList,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Get
	* Use Link
	*
	* @param begin
	* @param end
	* @param searchValue - 검색어
	* @param simProIdList - 시뮬레이션 프로젝트 아이디 리스트
	* @param locale
	* @return List<Map<String,Object>>
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCustomLinkSimulationProjectList(
		int begin, int end, java.lang.String searchValue,
		java.util.List<java.lang.Long> simProIdList, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Get
	* Use Link
	*
	* @param searchValue - 검색어
	* @param simProIdList - 시뮬레이션 프로젝트 아이디 리스트
	* @param locale
	* @return List<Map<String,Object>>
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCustomLinkSimulationProjectList(
		java.lang.String searchValue,
		java.util.List<java.lang.Long> simProIdList, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	/**
	* Simulation Project List Count
	* Use Link
	*
	* @param searchValue - 검색어
	* @param simProIdList - 시뮬레이션 프로젝트 아이디 리스트
	* @return int - count
	* @throws SystemException
	* @throws PortalException
	* @throws ParseException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCustomLinkSimulationProjectCount(
		java.lang.String searchValue,
		java.util.List<java.lang.Long> simProIdList, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchAssetEntryModelAppCount(
		java.util.Map<java.lang.String, java.lang.Object> params);

	/**
	* Simulation Project 생성
	*
	* @param upload
	* @param request
	* @param portalGroupId - 팀생성을 위한 포탈 그룹 아이디
	* @param companyId - 팀생성을 위한 컴퍼니 아이디
	* @param user - 시뮬레이션 프로젝트 생성 유저 객체
	* @param params - 생성화면 form 에서 넘긴 데이터
	* @return simulationProjectId
	* @throws SystemException
	*/
	public long insertCustomSimulationProject(
		com.liferay.portal.kernel.upload.UploadPortletRequest upload,
		javax.portlet.PortletRequest request, long portalGroupId,
		long companyId, com.liferay.portal.model.User user, java.util.Map params)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Simulation Project 수정
	*
	* @param upload
	* @param request
	* @param portalGroupId - 팀생성을 위한 포탈 그룹 아이디
	* @param companyId - 팀생성을 위한 컴퍼니 아이디
	* @param user - 시뮬레이션 프로젝트 생성 유저 객체
	* @param params - 생성화면 form 에서 넘긴 데이터
	* @return simulationProjectId
	* @throws SystemException
	*/
	public long updateCustomSimulationProject(
		com.liferay.portal.kernel.upload.UploadPortletRequest upload,
		javax.portlet.PortletRequest request, long portalGroupId,
		long companyId, com.liferay.portal.model.User user, java.util.Map params)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Simulation Project 삭제
	*
	* @param portalGroupId - 팀생성을 위한 포탈 그룹 아이디
	* @param companyId - 팀생성을 위한 컴퍼니 아이디
	* @param simulationProjectId - 삭제할 시뮬레이션프로젝트 아이디
	* @throws SystemException
	*/
	public void deleteCustomSimulationProject(long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* 시뮬레이션 프로젝트 기본정보
	*
	* @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	* @param portalGroupId - 포탈 그룹 아이디
	* @param companyGroupId - 전역의 그룹 아이디
	* @return Map<String, Object> - 시뮬레이션 프로젝트 기본정보
	* @throws SystemException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.Map<java.lang.String, java.lang.Object> getCustomDefaultInfoSimulationProject(
		long simulationProjectId, long portalGroupId, long companyGroupId,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException;
}