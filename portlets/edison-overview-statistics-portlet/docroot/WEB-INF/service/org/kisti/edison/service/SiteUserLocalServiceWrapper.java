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
 * Provides a wrapper for {@link SiteUserLocalService}.
 *
 * @author edison
 * @see SiteUserLocalService
 * @generated
 */
public class SiteUserLocalServiceWrapper implements SiteUserLocalService,
	ServiceWrapper<SiteUserLocalService> {
	public SiteUserLocalServiceWrapper(
		SiteUserLocalService siteUserLocalService) {
		_siteUserLocalService = siteUserLocalService;
	}

	/**
	* Adds the site user to the database. Also notifies the appropriate model listeners.
	*
	* @param siteUser the site user
	* @return the site user that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.SiteUser addSiteUser(
		org.kisti.edison.model.SiteUser siteUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _siteUserLocalService.addSiteUser(siteUser);
	}

	/**
	* Creates a new site user with the primary key. Does not add the site user to the database.
	*
	* @param siteUserPK the primary key for the new site user
	* @return the new site user
	*/
	@Override
	public org.kisti.edison.model.SiteUser createSiteUser(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK) {
		return _siteUserLocalService.createSiteUser(siteUserPK);
	}

	/**
	* Deletes the site user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param siteUserPK the primary key of the site user
	* @return the site user that was removed
	* @throws PortalException if a site user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.SiteUser deleteSiteUser(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _siteUserLocalService.deleteSiteUser(siteUserPK);
	}

	/**
	* Deletes the site user from the database. Also notifies the appropriate model listeners.
	*
	* @param siteUser the site user
	* @return the site user that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.SiteUser deleteSiteUser(
		org.kisti.edison.model.SiteUser siteUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _siteUserLocalService.deleteSiteUser(siteUser);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _siteUserLocalService.dynamicQuery();
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
		return _siteUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _siteUserLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _siteUserLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _siteUserLocalService.dynamicQueryCount(dynamicQuery);
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
		return _siteUserLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.kisti.edison.model.SiteUser fetchSiteUser(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _siteUserLocalService.fetchSiteUser(siteUserPK);
	}

	/**
	* Returns the site user with the primary key.
	*
	* @param siteUserPK the primary key of the site user
	* @return the site user
	* @throws PortalException if a site user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.SiteUser getSiteUser(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _siteUserLocalService.getSiteUser(siteUserPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _siteUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the site users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of site users
	* @param end the upper bound of the range of site users (not inclusive)
	* @return the range of site users
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.SiteUser> getSiteUsers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _siteUserLocalService.getSiteUsers(start, end);
	}

	/**
	* Returns the number of site users.
	*
	* @return the number of site users
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSiteUsersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _siteUserLocalService.getSiteUsersCount();
	}

	/**
	* Updates the site user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param siteUser the site user
	* @return the site user that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.SiteUser updateSiteUser(
		org.kisti.edison.model.SiteUser siteUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _siteUserLocalService.updateSiteUser(siteUser);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _siteUserLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_siteUserLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _siteUserLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public int getCategoryTotalCount(
		java.util.Map<java.lang.String, java.lang.Object> searchParam) {
		return _siteUserLocalService.getCategoryTotalCount(searchParam);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCategoryCountByDate(
		java.util.Map<java.lang.String, java.lang.Object> searchParam) {
		return _siteUserLocalService.getCategoryCountByDate(searchParam);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCategoryCountBySite(
		java.util.Map<java.lang.String, java.lang.Object> searchParam) {
		return _siteUserLocalService.getCategoryCountBySite(searchParam);
	}

	@Override
	public boolean updateSiteUserStatistics() {
		return _siteUserLocalService.updateSiteUserStatistics();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SiteUserLocalService getWrappedSiteUserLocalService() {
		return _siteUserLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSiteUserLocalService(
		SiteUserLocalService siteUserLocalService) {
		_siteUserLocalService = siteUserLocalService;
	}

	@Override
	public SiteUserLocalService getWrappedService() {
		return _siteUserLocalService;
	}

	@Override
	public void setWrappedService(SiteUserLocalService siteUserLocalService) {
		_siteUserLocalService = siteUserLocalService;
	}

	private SiteUserLocalService _siteUserLocalService;
}