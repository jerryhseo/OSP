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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SiteUser. This utility wraps
 * {@link org.kisti.edison.service.impl.SiteUserLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author edison
 * @see SiteUserLocalService
 * @see org.kisti.edison.service.base.SiteUserLocalServiceBaseImpl
 * @see org.kisti.edison.service.impl.SiteUserLocalServiceImpl
 * @generated
 */
public class SiteUserLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.service.impl.SiteUserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the site user to the database. Also notifies the appropriate model listeners.
	*
	* @param siteUser the site user
	* @return the site user that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SiteUser addSiteUser(
		org.kisti.edison.model.SiteUser siteUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSiteUser(siteUser);
	}

	/**
	* Creates a new site user with the primary key. Does not add the site user to the database.
	*
	* @param siteUserPK the primary key for the new site user
	* @return the new site user
	*/
	public static org.kisti.edison.model.SiteUser createSiteUser(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK) {
		return getService().createSiteUser(siteUserPK);
	}

	/**
	* Deletes the site user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param siteUserPK the primary key of the site user
	* @return the site user that was removed
	* @throws PortalException if a site user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SiteUser deleteSiteUser(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSiteUser(siteUserPK);
	}

	/**
	* Deletes the site user from the database. Also notifies the appropriate model listeners.
	*
	* @param siteUser the site user
	* @return the site user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SiteUser deleteSiteUser(
		org.kisti.edison.model.SiteUser siteUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSiteUser(siteUser);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.kisti.edison.model.SiteUser fetchSiteUser(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSiteUser(siteUserPK);
	}

	/**
	* Returns the site user with the primary key.
	*
	* @param siteUserPK the primary key of the site user
	* @return the site user
	* @throws PortalException if a site user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SiteUser getSiteUser(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSiteUser(siteUserPK);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<org.kisti.edison.model.SiteUser> getSiteUsers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSiteUsers(start, end);
	}

	/**
	* Returns the number of site users.
	*
	* @return the number of site users
	* @throws SystemException if a system exception occurred
	*/
	public static int getSiteUsersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSiteUsersCount();
	}

	/**
	* Updates the site user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param siteUser the site user
	* @return the site user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SiteUser updateSiteUser(
		org.kisti.edison.model.SiteUser siteUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSiteUser(siteUser);
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

	public static int getCategoryTotalCount(
		java.util.Map<java.lang.String, java.lang.Object> searchParam) {
		return getService().getCategoryTotalCount(searchParam);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCategoryCountByDate(
		java.util.Map<java.lang.String, java.lang.Object> searchParam) {
		return getService().getCategoryCountByDate(searchParam);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCategoryCountBySite(
		java.util.Map<java.lang.String, java.lang.Object> searchParam) {
		return getService().getCategoryCountBySite(searchParam);
	}

	public static boolean updateSiteUserStatistics() {
		return getService().updateSiteUserStatistics();
	}

	public static void clearService() {
		_service = null;
	}

	public static SiteUserLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SiteUserLocalService.class.getName());

			if (invokableLocalService instanceof SiteUserLocalService) {
				_service = (SiteUserLocalService)invokableLocalService;
			}
			else {
				_service = new SiteUserLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SiteUserLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SiteUserLocalService service) {
	}

	private static SiteUserLocalService _service;
}