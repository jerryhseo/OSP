/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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
 * Provides the local service utility for ScienceAppCompile. This utility wraps
 * {@link org.kisti.edison.science.service.impl.ScienceAppCompileLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author EDISON
 * @see ScienceAppCompileLocalService
 * @see org.kisti.edison.science.service.base.ScienceAppCompileLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.impl.ScienceAppCompileLocalServiceImpl
 * @generated
 */
public class ScienceAppCompileLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.science.service.impl.ScienceAppCompileLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the science app compile to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppCompile the science app compile
	* @return the science app compile that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppCompile addScienceAppCompile(
		org.kisti.edison.science.model.ScienceAppCompile scienceAppCompile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addScienceAppCompile(scienceAppCompile);
	}

	/**
	* Creates a new science app compile with the primary key. Does not add the science app compile to the database.
	*
	* @param scienceAppId the primary key for the new science app compile
	* @return the new science app compile
	*/
	public static org.kisti.edison.science.model.ScienceAppCompile createScienceAppCompile(
		long scienceAppId) {
		return getService().createScienceAppCompile(scienceAppId);
	}

	/**
	* Deletes the science app compile with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the science app compile
	* @return the science app compile that was removed
	* @throws PortalException if a science app compile with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppCompile deleteScienceAppCompile(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteScienceAppCompile(scienceAppId);
	}

	/**
	* Deletes the science app compile from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppCompile the science app compile
	* @return the science app compile that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppCompile deleteScienceAppCompile(
		org.kisti.edison.science.model.ScienceAppCompile scienceAppCompile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteScienceAppCompile(scienceAppCompile);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppCompileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppCompileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.kisti.edison.science.model.ScienceAppCompile fetchScienceAppCompile(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchScienceAppCompile(scienceAppId);
	}

	/**
	* Returns the science app compile with the primary key.
	*
	* @param scienceAppId the primary key of the science app compile
	* @return the science app compile
	* @throws PortalException if a science app compile with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppCompile getScienceAppCompile(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppCompile(scienceAppId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the science app compiles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppCompileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app compiles
	* @param end the upper bound of the range of science app compiles (not inclusive)
	* @return the range of science app compiles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppCompile> getScienceAppCompiles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppCompiles(start, end);
	}

	/**
	* Returns the number of science app compiles.
	*
	* @return the number of science app compiles
	* @throws SystemException if a system exception occurred
	*/
	public static int getScienceAppCompilesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppCompilesCount();
	}

	/**
	* Updates the science app compile in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceAppCompile the science app compile
	* @return the science app compile that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppCompile updateScienceAppCompile(
		org.kisti.edison.science.model.ScienceAppCompile scienceAppCompile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateScienceAppCompile(scienceAppCompile);
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

	public static org.kisti.edison.science.model.ScienceAppCompile compileFindByScienceAppId(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().compileFindByScienceAppId(scienceAppId);
	}

	public static org.kisti.edison.science.model.ScienceAppCompile updateCompileAndScienceApp(
		long scienceAppId, long userId, java.lang.String gitHubUrl,
		java.lang.String result)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCompileAndScienceApp(scienceAppId, userId, gitHubUrl,
			result);
	}

	public static void deleteCompileAndScienceApp(long companyId,
		long scienceAppId, java.lang.String appName, java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteCompileAndScienceApp(companyId, scienceAppId, appName,
			appVersion);
	}

	/**
	* @param gitHubUrl
	* @throws InterruptedException
	* @throws IOException
	* @throws SystemException
	*/
	public static java.lang.String gitHubCloneToScienceAppFolders(
		long companyId, java.lang.String appName, java.lang.String appVersion,
		java.lang.String gitHubUrl)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return getService()
				   .gitHubCloneToScienceAppFolders(companyId, appName,
			appVersion, gitHubUrl);
	}

	public static java.lang.String cleanMakeFileToTargetScienceApp(
		long companyId, java.lang.String appName, java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return getService()
				   .cleanMakeFileToTargetScienceApp(companyId, appName,
			appVersion);
	}

	public static java.lang.String makeFileToTargetScienceApp(long companyId,
		java.lang.String appName, java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return getService()
				   .makeFileToTargetScienceApp(companyId, appName, appVersion);
	}

	public static java.lang.String retrieveListTartgetDir(long companyId,
		java.lang.String appName, java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return getService()
				   .retrieveListTartgetDir(companyId, appName, appVersion);
	}

	public static void clearService() {
		_service = null;
	}

	public static ScienceAppCompileLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ScienceAppCompileLocalService.class.getName());

			if (invokableLocalService instanceof ScienceAppCompileLocalService) {
				_service = (ScienceAppCompileLocalService)invokableLocalService;
			}
			else {
				_service = new ScienceAppCompileLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ScienceAppCompileLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ScienceAppCompileLocalService service) {
	}

	private static ScienceAppCompileLocalService _service;
}