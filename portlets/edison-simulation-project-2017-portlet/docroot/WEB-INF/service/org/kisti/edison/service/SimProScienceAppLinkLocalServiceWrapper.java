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
 * Provides a wrapper for {@link SimProScienceAppLinkLocalService}.
 *
 * @author edison
 * @see SimProScienceAppLinkLocalService
 * @generated
 */
public class SimProScienceAppLinkLocalServiceWrapper
	implements SimProScienceAppLinkLocalService,
		ServiceWrapper<SimProScienceAppLinkLocalService> {
	public SimProScienceAppLinkLocalServiceWrapper(
		SimProScienceAppLinkLocalService simProScienceAppLinkLocalService) {
		_simProScienceAppLinkLocalService = simProScienceAppLinkLocalService;
	}

	/**
	* Adds the sim pro science app link to the database. Also notifies the appropriate model listeners.
	*
	* @param simProScienceAppLink the sim pro science app link
	* @return the sim pro science app link that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.SimProScienceAppLink addSimProScienceAppLink(
		org.kisti.edison.model.SimProScienceAppLink simProScienceAppLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.addSimProScienceAppLink(simProScienceAppLink);
	}

	/**
	* Creates a new sim pro science app link with the primary key. Does not add the sim pro science app link to the database.
	*
	* @param simProScienceAppLinkPK the primary key for the new sim pro science app link
	* @return the new sim pro science app link
	*/
	@Override
	public org.kisti.edison.model.SimProScienceAppLink createSimProScienceAppLink(
		org.kisti.edison.service.persistence.SimProScienceAppLinkPK simProScienceAppLinkPK) {
		return _simProScienceAppLinkLocalService.createSimProScienceAppLink(simProScienceAppLinkPK);
	}

	/**
	* Deletes the sim pro science app link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simProScienceAppLinkPK the primary key of the sim pro science app link
	* @return the sim pro science app link that was removed
	* @throws PortalException if a sim pro science app link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.SimProScienceAppLink deleteSimProScienceAppLink(
		org.kisti.edison.service.persistence.SimProScienceAppLinkPK simProScienceAppLinkPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.deleteSimProScienceAppLink(simProScienceAppLinkPK);
	}

	/**
	* Deletes the sim pro science app link from the database. Also notifies the appropriate model listeners.
	*
	* @param simProScienceAppLink the sim pro science app link
	* @return the sim pro science app link that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.SimProScienceAppLink deleteSimProScienceAppLink(
		org.kisti.edison.model.SimProScienceAppLink simProScienceAppLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.deleteSimProScienceAppLink(simProScienceAppLink);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _simProScienceAppLinkLocalService.dynamicQuery();
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
		return _simProScienceAppLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _simProScienceAppLinkLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _simProScienceAppLinkLocalService.dynamicQuery(dynamicQuery,
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
		return _simProScienceAppLinkLocalService.dynamicQueryCount(dynamicQuery);
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
		return _simProScienceAppLinkLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.model.SimProScienceAppLink fetchSimProScienceAppLink(
		org.kisti.edison.service.persistence.SimProScienceAppLinkPK simProScienceAppLinkPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.fetchSimProScienceAppLink(simProScienceAppLinkPK);
	}

	/**
	* Returns the sim pro science app link with the primary key.
	*
	* @param simProScienceAppLinkPK the primary key of the sim pro science app link
	* @return the sim pro science app link
	* @throws PortalException if a sim pro science app link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.SimProScienceAppLink getSimProScienceAppLink(
		org.kisti.edison.service.persistence.SimProScienceAppLinkPK simProScienceAppLinkPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.getSimProScienceAppLink(simProScienceAppLinkPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the sim pro science app links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sim pro science app links
	* @param end the upper bound of the range of sim pro science app links (not inclusive)
	* @return the range of sim pro science app links
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.SimProScienceAppLink> getSimProScienceAppLinks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.getSimProScienceAppLinks(start,
			end);
	}

	/**
	* Returns the number of sim pro science app links.
	*
	* @return the number of sim pro science app links
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSimProScienceAppLinksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.getSimProScienceAppLinksCount();
	}

	/**
	* Updates the sim pro science app link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param simProScienceAppLink the sim pro science app link
	* @return the sim pro science app link that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.SimProScienceAppLink updateSimProScienceAppLink(
		org.kisti.edison.model.SimProScienceAppLink simProScienceAppLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.updateSimProScienceAppLink(simProScienceAppLink);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _simProScienceAppLinkLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_simProScienceAppLinkLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _simProScienceAppLinkLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* 사이언스앱 리스트(앱 관리 팝업)
	*
	* @param searchField - 검색어
	* @return List<Map<String, Object>> - 사이언스앱 리스트
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getScienceAppList(
		java.lang.String searchField, java.util.Locale locale, int begin,
		int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.getScienceAppList(searchField,
			locale, begin, end);
	}

	/**
	* 사이언스앱 카운트(앱 관리 팝업)
	*
	* @param searchField - 검색어
	* @return int - 사이언스앱 총 개수
	*/
	@Override
	public int getScienceAppListCount(java.lang.String searchField,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.getScienceAppListCount(searchField,
			locale);
	}

	/**
	* 사이언스앱 리스트
	*
	* @param scienceAppIdList - 사이언스앱 아이디 리스트
	* @return List<Map<String, Object>> - 사이언스앱 리스트
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getScienceAppList(
		java.lang.String[] scienceAppIdList, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.getScienceAppList(scienceAppIdList,
			locale);
	}

	/**
	* 사이언스앱 리스트
	*
	* @param simulationProjectId 시뮬레이션 프로젝트 아이디
	* @return Map<String, Object> - 사이언스앱 리스트
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getScienceAppList(
		long simulationProjectId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simProScienceAppLinkLocalService.getScienceAppList(simulationProjectId,
			locale);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SimProScienceAppLinkLocalService getWrappedSimProScienceAppLinkLocalService() {
		return _simProScienceAppLinkLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSimProScienceAppLinkLocalService(
		SimProScienceAppLinkLocalService simProScienceAppLinkLocalService) {
		_simProScienceAppLinkLocalService = simProScienceAppLinkLocalService;
	}

	@Override
	public SimProScienceAppLinkLocalService getWrappedService() {
		return _simProScienceAppLinkLocalService;
	}

	@Override
	public void setWrappedService(
		SimProScienceAppLinkLocalService simProScienceAppLinkLocalService) {
		_simProScienceAppLinkLocalService = simProScienceAppLinkLocalService;
	}

	private SimProScienceAppLinkLocalService _simProScienceAppLinkLocalService;
}