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

package org.kisti.edison.science.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for DeveloperRequest. This utility wraps
 * {@link org.kisti.edison.science.service.impl.DeveloperRequestLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author EDISON
 * @see DeveloperRequestLocalService
 * @see org.kisti.edison.science.service.base.DeveloperRequestLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.impl.DeveloperRequestLocalServiceImpl
 * @generated
 */
public class DeveloperRequestLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.science.service.impl.DeveloperRequestLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the developer request to the database. Also notifies the appropriate model listeners.
	*
	* @param developerRequest the developer request
	* @return the developer request that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.DeveloperRequest addDeveloperRequest(
		org.kisti.edison.science.model.DeveloperRequest developerRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addDeveloperRequest(developerRequest);
	}

	/**
	* Creates a new developer request with the primary key. Does not add the developer request to the database.
	*
	* @param developerRequestPK the primary key for the new developer request
	* @return the new developer request
	*/
	public static org.kisti.edison.science.model.DeveloperRequest createDeveloperRequest(
		org.kisti.edison.science.service.persistence.DeveloperRequestPK developerRequestPK) {
		return getService().createDeveloperRequest(developerRequestPK);
	}

	/**
	* Deletes the developer request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param developerRequestPK the primary key of the developer request
	* @return the developer request that was removed
	* @throws PortalException if a developer request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.DeveloperRequest deleteDeveloperRequest(
		org.kisti.edison.science.service.persistence.DeveloperRequestPK developerRequestPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteDeveloperRequest(developerRequestPK);
	}

	/**
	* Deletes the developer request from the database. Also notifies the appropriate model listeners.
	*
	* @param developerRequest the developer request
	* @return the developer request that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.DeveloperRequest deleteDeveloperRequest(
		org.kisti.edison.science.model.DeveloperRequest developerRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteDeveloperRequest(developerRequest);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.DeveloperRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.DeveloperRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.kisti.edison.science.model.DeveloperRequest fetchDeveloperRequest(
		org.kisti.edison.science.service.persistence.DeveloperRequestPK developerRequestPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchDeveloperRequest(developerRequestPK);
	}

	/**
	* Returns the developer request with the primary key.
	*
	* @param developerRequestPK the primary key of the developer request
	* @return the developer request
	* @throws PortalException if a developer request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.DeveloperRequest getDeveloperRequest(
		org.kisti.edison.science.service.persistence.DeveloperRequestPK developerRequestPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDeveloperRequest(developerRequestPK);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the developer requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.DeveloperRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of developer requests
	* @param end the upper bound of the range of developer requests (not inclusive)
	* @return the range of developer requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.DeveloperRequest> getDeveloperRequests(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDeveloperRequests(start, end);
	}

	/**
	* Returns the number of developer requests.
	*
	* @return the number of developer requests
	* @throws SystemException if a system exception occurred
	*/
	public static int getDeveloperRequestsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDeveloperRequestsCount();
	}

	/**
	* Updates the developer request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param developerRequest the developer request
	* @return the developer request that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.DeveloperRequest updateDeveloperRequest(
		org.kisti.edison.science.model.DeveloperRequest developerRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDeveloperRequest(developerRequest);
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

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getListCustomDeveloperRequest(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.lang.NumberFormatException {
		return getService().getListCustomDeveloperRequest(params, locale);
	}

	public static org.kisti.edison.science.model.DeveloperRequest insertCustomDeveloperRequest(
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.NoSuchModelException,
			com.liferay.portal.kernel.exception.SystemException,
			java.lang.NumberFormatException {
		return getService().insertCustomDeveloperRequest(params);
	}

	public static org.kisti.edison.science.model.DeveloperRequest deleteCustomDeveloperRequest(
		java.util.Map<java.lang.String, java.lang.String> params)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.lang.NumberFormatException,
			org.kisti.edison.science.NoSuchDeveloperRequestException {
		return getService().deleteCustomDeveloperRequest(params);
	}

	public static void updateDeveloperRequestStatus(long userId,
		java.lang.String workspaceStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().updateDeveloperRequestStatus(userId, workspaceStatus);
	}

	public static void clearService() {
		_service = null;
	}

	public static DeveloperRequestLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					DeveloperRequestLocalService.class.getName());

			if (invokableLocalService instanceof DeveloperRequestLocalService) {
				_service = (DeveloperRequestLocalService)invokableLocalService;
			}
			else {
				_service = new DeveloperRequestLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(DeveloperRequestLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(DeveloperRequestLocalService service) {
	}

	private static DeveloperRequestLocalService _service;
}