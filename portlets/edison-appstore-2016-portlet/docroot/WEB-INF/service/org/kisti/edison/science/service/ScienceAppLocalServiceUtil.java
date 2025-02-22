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
 * Provides the local service utility for ScienceApp. This utility wraps
 * {@link org.kisti.edison.science.service.impl.ScienceAppLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author EDISON
 * @see ScienceAppLocalService
 * @see org.kisti.edison.science.service.base.ScienceAppLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.impl.ScienceAppLocalServiceImpl
 * @generated
 */
public class ScienceAppLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.science.service.impl.ScienceAppLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the science app to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceApp addScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addScienceApp(scienceApp);
	}

	/**
	* Creates a new science app with the primary key. Does not add the science app to the database.
	*
	* @param scienceAppId the primary key for the new science app
	* @return the new science app
	*/
	public static org.kisti.edison.science.model.ScienceApp createScienceApp(
		long scienceAppId) {
		return getService().createScienceApp(scienceAppId);
	}

	/**
	* Deletes the science app with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the science app
	* @return the science app that was removed
	* @throws PortalException if a science app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceApp deleteScienceApp(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteScienceApp(scienceAppId);
	}

	/**
	* Deletes the science app from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceApp deleteScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteScienceApp(scienceApp);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.kisti.edison.science.model.ScienceApp fetchScienceApp(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchScienceApp(scienceAppId);
	}

	/**
	* Returns the science app with the matching UUID and company.
	*
	* @param uuid the science app's UUID
	* @param companyId the primary key of the company
	* @return the matching science app, or <code>null</code> if a matching science app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceApp fetchScienceAppByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchScienceAppByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the science app matching the UUID and group.
	*
	* @param uuid the science app's UUID
	* @param groupId the primary key of the group
	* @return the matching science app, or <code>null</code> if a matching science app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceApp fetchScienceAppByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchScienceAppByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the science app with the primary key.
	*
	* @param scienceAppId the primary key of the science app
	* @return the science app
	* @throws PortalException if a science app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceApp getScienceApp(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceApp(scienceAppId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the science app with the matching UUID and company.
	*
	* @param uuid the science app's UUID
	* @param companyId the primary key of the company
	* @return the matching science app
	* @throws PortalException if a matching science app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceApp getScienceAppByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the science app matching the UUID and group.
	*
	* @param uuid the science app's UUID
	* @param groupId the primary key of the group
	* @return the matching science app
	* @throws PortalException if a matching science app could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceApp getScienceAppByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the science apps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science apps
	* @param end the upper bound of the range of science apps (not inclusive)
	* @return the range of science apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceApps(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceApps(start, end);
	}

	/**
	* Returns the number of science apps.
	*
	* @return the number of science apps
	* @throws SystemException if a system exception occurred
	*/
	public static int getScienceAppsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppsCount();
	}

	/**
	* Updates the science app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceApp updateScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateScienceApp(scienceApp);
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

	/**
	* Creates a ScienceApp instance with specified name and version. The new
	* instance created is not yet saved in the database. Use addScienceApp() to
	* save the instance.
	*
	* @param appName:
	ScienceApp name to be created
	* @param appVersion:
	ScienceApp version to be created
	* @param sc:
	ServiceContext instance for ScienceApp
	* @return If appName is not follows naming rules or already exists in the
	database, returns null. If appVersion is not follows versioning
	rules, returns null. Otherwise returns a ScienceApp instance with
	initialized data.
	
	Some attributes of the returned instance are set initial value as
	followings: -stage: ScienceAppConstants.EMPTY -authorId: current
	user id of service context instance -createDate: date created of
	service context instance -modifiedDate: date created of service
	context instance -userId: current user id of service context
	instance -recentModifierId: current user id of service context
	instance -groupId: scope group id of service context instance
	-companyId: company id of service context instance
	* @throws SystemException
	* @throws PortalException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#createScienceApp(java.lang.String,
	java.lang.String, com.liferay.portal.service.ServiceContext)
	*/
	public static org.kisti.edison.science.model.ScienceApp createScienceApp(
		java.lang.String appName, java.lang.String appVersion,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().createScienceApp(appName, appVersion, sc);
	}

	public static org.kisti.edison.science.model.ScienceApp getTextEditorScienceApp()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTextEditorScienceApp();
	}

	public static org.kisti.edison.science.model.ScienceApp getFileEditorScienceApp()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFileEditorScienceApp();
	}

	public static org.kisti.edison.science.model.ScienceApp getStructuredEditorScienceApp()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getStructuredEditorScienceApp();
	}

	public static org.kisti.edison.science.model.ScienceApp getEditorScienceApp(
		java.lang.String editorType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getEditorScienceApp(editorType);
	}

	/**
	* Saves the specified ScienceApp instance to database.
	*
	* @param scienceApp:
	ScienceApp instance to be saved.
	* @param sc:
	service context of the ScienceApp class
	* @return ScienceApp instance saved.
	* @throws SystemException
	* @throws PortalException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#addScienceApp(com.kisti.science.platform.model.ScienceApp,
	com.liferay.portal.service.ServiceContext)
	*/
	public static org.kisti.edison.science.model.ScienceApp addScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addScienceApp(scienceApp, sc);
	}

	public static void setScienceAppInputPorts(long scienceAppId,
		java.lang.String inputPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setScienceAppInputPorts(scienceAppId, inputPorts);
	}

	public static java.lang.String getScienceAppInputPorts(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppInputPorts(scienceAppId);
	}

	public static void setScienceAppLogPorts(long scienceAppId,
		java.lang.String logPorts)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().setScienceAppLogPorts(scienceAppId, logPorts);
	}

	public static java.lang.String getScienceAppLogPorts(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppLogPorts(scienceAppId);
	}

	public static void setScienceAppOutputPorts(long scienceAppId,
		java.lang.String outputPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setScienceAppOutputPorts(scienceAppId, outputPorts);
	}

	public static java.lang.String getScienceAppOutputPorts(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppOutputPorts(scienceAppId);
	}

	/**
	* Verifies ScienceApp name if the name follows specified naming rules and
	* there is no science app in the database already.
	*
	* @param appName:
	science app name to be tesed.
	* @return true if the name follows naming rules and brand new. false,
	Otherwise.
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#verifyScienceAppName(java.lang.String,
	long)
	*/
	public static boolean verifyScienceAppName(java.lang.String appName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().verifyScienceAppName(appName);
	}

	/**
	* Test if there is a science app name in the database already.
	*
	* @param appName:
	science app name to be tesed.
	* @return true if the app name already exist in the database, false,
	otherwise
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#existAppName(java.lang.String)
	*/
	public static boolean existAppName(java.lang.String appName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().existAppName(appName);
	}

	/**
	* Test if there is an science app with the specified name and version in the
	* database.
	*
	* @param appName:
	science app name to be tesed.
	* @param appVersion:
	science app version to be tesed.
	* @return true if a science app with the name and the version already exist
	in the database, false, otherwise.
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#existApp(java.lang.String,
	java.lang.String)
	*/
	public static boolean existApp(java.lang.String appName,
		java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().existApp(appName, appVersion);
	}

	/**
	* Get the science app's latest version
	*
	* @param appName:
	science app name
	* @return the latest version science app instance.
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#getLatestVersion(java.lang.String)
	*/
	public static org.kisti.edison.science.model.ScienceApp getLatestVersion(
		java.lang.String appName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLatestVersion(appName);
	}

	/**
	* Verify version number of a science app. Version number of a science app
	* should be consisted of 3 sections, {major}.{sub}.{minor}. Major section
	* number should be increased when a science app changes or added its
	* architecture or major functions. Sub section number should be increased
	* when the science app changes functionality. Minor section number should be
	* increased when the science app fixes errors. Each section must be integer
	* and be lager than before.
	*
	* @param appName:
	science app name to be verified.
	* @param appVersion:
	science app version to be verified.
	* @return true if the version number follows naming rules and valid. false,
	Otherwise.
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#verifyVersionNumber(java.lang.String,
	java.lang.String)
	*/
	public static boolean verifyVersionNumber(java.lang.String appName,
		java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().verifyVersionNumber(appName, appVersion);
	}

	public static void deleteAllScienceApps()
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAllScienceApps();
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAll();
	}

	/**
	* Update a science app
	*
	* @param scienceApp:
	science app instance to be updated.
	* @param sc:
	ServiceContext instance for the ScienceApp class.
	* @return ScienceApp instance updated.
	* @throws SystemException
	* @throws PortalException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#updateScienceApp(com.kisti.science.platform.model.ScienceApp,
	com.liferay.portal.service.ServiceContext)
	*/
	public static org.kisti.edison.science.model.ScienceApp updateScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateScienceApp(scienceApp, sc);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByStatus(status);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByStage(
		java.lang.String stage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByStage(stage);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorIdStatus(
		long authorId, int appStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getScienceAppListByAuthorIdStatus(authorId, appStatus);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorIdStatus(
		long authorId, int appStatus, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getScienceAppListByAuthorIdStatus(authorId, appStatus,
			start, end);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorIdAppType(
		long authorId, java.lang.String appType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByAuthorIdAppType(authorId, appType);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorIdAppType(
		long authorId, java.lang.String appClass, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getScienceAppListByAuthorIdAppType(authorId, appClass,
			start, end);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorId(
		long authorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByAuthorId(authorId);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorId(
		long authorId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByAuthorId(authorId, start, end);
	}

	public static int countScienceAppsByAuthorId(long authorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countScienceAppsByAuthorId(authorId);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByOpenLevel(
		java.lang.String openLevel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByOpenLevel(openLevel);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByOpenLevel(
		java.lang.String openLevel, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByOpenLevel(openLevel, start, end);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByManagerId(
		long managerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByManagerId(managerId);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByManagerId(
		long managerId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByManagerId(managerId, start, end);
	}

	public static int countScienceAppsByManagerId(long managerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countScienceAppsByManagerId(managerId);
	}

	public static long[] getScienceAppManagerIds(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppManagerIds(scienceAppId);
	}

	public static long[] getScienceAppManagerIds(long scienceAppId, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppManagerIds(scienceAppId, start, end);
	}

	public static int countScienceAppManagers(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countScienceAppManagers(scienceAppId);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByCategoryId(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByCategoryId(categoryId);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByCategoryId(
		long categoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByCategoryId(categoryId, start, end);
	}

	public static int countScienceAppsByCategoryId(long categoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countScienceAppsByCategoryId(categoryId);
	}

	public static long[] getScienceAppCategoryIds(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppCategoryIds(scienceAppId);
	}

	public static long[] getScienceAppCategoryIds(long scienceAppId, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppCategoryIds(scienceAppId, start, end);
	}

	public static int countScienceAppCategories(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countScienceAppCategories(scienceAppId);
	}

	public static void assignScienceAppToCategories(long scienceAppId,
		long[] categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().assignScienceAppToCategories(scienceAppId, categoryIds);
	}

	public static void assignScienceAppToCategory(long scienceAppId,
		long categoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().assignScienceAppToCategory(scienceAppId, categoryId);
	}

	public static void assignManagersToScienceApp(long scienceAppId,
		long[] managerIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().assignManagersToScienceApp(scienceAppId, managerIds);
	}

	public static void assignManagerToScienceApp(long scienceAppId,
		long managerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().assignManagerToScienceApp(scienceAppId, managerId);
	}

	/**
	* Get path of the executable binary file. The full path can be obtained by
	* adding base directory for science apps.
	*
	* @param scienceAppId
	* @return String of the path.
	* @throws PortalException
	* @throws SystemException
	*/
	public static java.lang.String getScienceAppBinPath(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppBinPath(scienceAppId);
	}

	/**
	* Get path of the source file. The full path can be obtained by adding base
	* directory for science apps.
	*
	* @param scienceAppId
	* @return String of the path.
	* @throws PortalException
	* @throws SystemException
	*/
	public static java.lang.String getScienceAppSrcPath(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppSrcPath(scienceAppId);
	}

	/**
	* Counts all science apps in the database.
	*
	* @return int - count
	*/
	public static int countAllScienceApps()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countAllScienceApps();
	}

	public static boolean existScienceAppPath(java.lang.String targetPath)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return getService().existScienceAppPath(targetPath);
	}

	public static void deleteScienceAppDir(java.lang.String targetDir)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		getService().deleteScienceAppDir(targetDir);
	}

	public static void makeScienceAppDir(java.lang.String targetDir)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		getService().makeScienceAppDir(targetDir);
	}

	/**
	* Save uploaded file to new location
	*
	* @param uploadedInputStream
	* @return
	* @throws InterruptedException
	* @throws IOException
	* @throws SystemException
	*/
	public static java.io.File saveToScienceAppStorage(
		java.lang.String targetDir, java.lang.String fileName,
		java.io.InputStream uploadedInputStream)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return getService()
				   .saveToScienceAppStorage(targetDir, fileName,
			uploadedInputStream);
	}

	/**
	* Unzip an uploaded file into a specified directory.
	*
	* @param strUnzipDirPath
	the directory into which unzipped files go.
	* @param zipFile
	zip file
	* @return process output message
	* @throws SystemException
	system exception
	* @throws IOException
	io exception
	* @throws InterruptedException
	interrupted exception
	*/
	public static void unzipScienceAppZipFile(java.lang.String sourceFile,
		java.lang.String targetDir)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		getService().unzipScienceAppZipFile(sourceFile, targetDir);
	}

	/**
	* ADD GPLUS SERVICE
	*      ********************
	*/
	public static void cleanScienceAppDir(long companyId,
		java.lang.String appName, java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		getService().cleanScienceAppDir(companyId, appName, appVersion);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByCategoryId2(
		long categoryId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByCategoryId2(categoryId, locale);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByCategoryId(
		long categoryId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppListByCategoryId(categoryId, locale);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByCategoryId(
		long categoryId, java.util.Locale locale, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getScienceAppListByCategoryId(categoryId, locale, start, end);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByScienceAppIdsAndLocale(
		long[] scienceAppIds, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getScienceAppListByScienceAppIdsAndLocale(scienceAppIds,
			locale);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getLanguageSpecificAssetCategories(
		java.util.List<com.liferay.portlet.asset.model.AssetCategory> categories,
		long parentCategoryId, java.util.Locale locale) {
		return getService()
				   .getLanguageSpecificAssetCategories(categories,
			parentCategoryId, locale);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByScienceAppIdsAndLocale(
		long[] scienceAppIds, java.util.Locale locale, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getScienceAppListByScienceAppIdsAndLocale(scienceAppIds,
			locale, start, end);
	}

	/**
	* @param sc
	* @param params
	* @return
	* @throws ScienceAppException
	* @throws SystemException
	*/
	public static org.kisti.edison.science.model.ScienceApp createScienceApp(
		com.liferay.portal.service.ServiceContext sc, java.util.Map params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().createScienceApp(sc, params);
	}

	public static void updateScienceApp(
		com.liferay.portal.service.ServiceContext sc, java.util.Map params,
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppException {
		getService().updateScienceApp(sc, params, scienceAppId);
	}

	/**
	* 통합 검색 앱 조회 서비스
	*
	* @param categoryIds -> Category 조회가 아닐 경우에는 null 입력
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceAppFromExplore(
		long companyGroupId, long groupId, java.util.Locale locale,
		java.lang.String[] appTypes, long[] categoryIds,
		java.lang.String searchText, int begin, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .retrieveListScienceAppFromExplore(companyGroupId, groupId,
			locale, appTypes, categoryIds, searchText, begin, end);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceAppFromExplore(
		long companyGroupId, long groupId, java.util.Locale locale,
		java.lang.String[] appTypes, long[] categoryIds,
		java.lang.String searchText, java.lang.String searchOrgCode, int begin,
		int end, java.lang.String sortField, java.lang.String sortOrder)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .retrieveListScienceAppFromExplore(companyGroupId, groupId,
			locale, appTypes, categoryIds, searchText, searchOrgCode, begin,
			end, sortField, sortOrder);
	}

	/**
	* 통합 검색 앱 카운트 서비스
	*
	* @param categoryIds -> Category 조회가 아닐 경우에는 null 입력
	*/
	public static int countScienceAppFromExplore(long companyGroupId,
		long groupId, java.util.Locale locale, java.lang.String[] appTypes,
		long[] categoryIds, java.lang.String searchText)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .countScienceAppFromExplore(companyGroupId, groupId, locale,
			appTypes, categoryIds, searchText);
	}

	public static int countScienceAppFromExplore(long companyGroupId,
		long groupId, java.util.Locale locale, java.lang.String[] appTypes,
		long[] categoryIds, java.lang.String searchText,
		java.lang.String searchOrgCode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .countScienceAppFromExplore(companyGroupId, groupId, locale,
			appTypes, categoryIds, searchText, searchOrgCode);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceAppAsManager(
		long companyGroupId, long groupId, java.util.Locale locale,
		long managerId, java.lang.String[] appTypes,
		java.lang.String[] editorTypes,
		java.util.Map<java.lang.String, java.lang.Object> searchMap,
		java.lang.String status, boolean categorySearch, int begin, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .retrieveListScienceAppAsManager(companyGroupId, groupId,
			locale, managerId, appTypes, editorTypes, searchMap, status,
			categorySearch, begin, end);
	}

	public static int countScienceAppAsManager(long companyGroupId,
		long groupId, java.util.Locale locale, long managerId,
		java.lang.String[] appTypes, java.lang.String[] editorTypes,
		java.util.Map<java.lang.String, java.lang.Object> searchMap,
		java.lang.String status, boolean categorySearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .countScienceAppAsManager(companyGroupId, groupId, locale,
			managerId, appTypes, editorTypes, searchMap, status, categorySearch);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceAppAsCategory(
		long companyGroupId, long groupId, java.util.Locale locale,
		long authorId, java.lang.String[] appTypes,
		java.lang.String[] editorTypes,
		java.util.Map<java.lang.String, java.lang.Object> searchMap,
		java.lang.String status, int begin, int end, boolean lanuageSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .retrieveListScienceAppAsCategory(companyGroupId, groupId,
			locale, authorId, appTypes, editorTypes, searchMap, status, begin,
			end, lanuageSearch);
	}

	public static int countListScienceAppAsCategory(long companyGroupId,
		long groupId, java.util.Locale locale, long authorId,
		java.lang.String[] appTypes, java.lang.String[] editorTypes,
		java.util.Map<java.lang.String, java.lang.Object> searchMap,
		java.lang.String status, boolean lanuageSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .countListScienceAppAsCategory(companyGroupId, groupId,
			locale, authorId, appTypes, editorTypes, searchMap, status,
			lanuageSearch);
	}

	/**
	* 앱 이름을 통한 앱 LIST 조회
	*
	* @param companyGroupId - themeDisplay.getCompanyGroupId()
	* @param groupId        - PortalUtil.getScopeGroupId(request)
	* @param locale         - themeDisplay.getLocale()
	* @param appName        - 앱 이름
	* @param categorySearch - 카테고리 조회 여부(PORTAL->false,SITE->true)
	* @return List<Map<String, Object>>
	* @throws SystemException
	* @throws PortalException
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceAppByName(
		long companyGroupId, long groupId, java.util.Locale locale,
		java.lang.String appName, boolean categorySearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .retrieveListScienceAppByName(companyGroupId, groupId,
			locale, appName, categorySearch);
	}

	/**
	* 카테고리를 제외한 앱 조회
	*
	* @param groupId - 조회 하는 분야의 GROUP ID (PortalUtil.getScopeGroupId(request))
	* @param locale  - 현재 EDISON 포털의 LOCALE (themeDisplay.getLocale())
	* @param authorId - 소유자 ID
	* @param appTypes - 앱 종류 (ScienceAppConstants 참조),null 허용
	* @param editorTypes  - editor 종류 (ScienceAppConstants 참조),null 허용
	* @param searchType   - 검색타입(APP_MANAGER_SEARCH_ALL,SWORGNM)
	* @param searchText   - 검색어
	* @param status       - 앱 상태
	* @param begin
	* @param end
	* @param lanuageSearch - 앱의 서비스 언어별 조회 여부
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceApp(
		long groupId, java.util.Locale locale, long authorId,
		java.lang.String[] appTypes, java.lang.String[] editorTypes,
		java.util.Map<java.lang.String, java.lang.Object> searchMap,
		java.lang.String status, int begin, int end, boolean lanuageSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .retrieveListScienceApp(groupId, locale, authorId, appTypes,
			editorTypes, searchMap, status, begin, end, lanuageSearch);
	}

	public static int countListScienceApp(long groupId,
		java.util.Locale locale, long authorId, java.lang.String[] appTypes,
		java.lang.String[] editorTypes,
		java.util.Map<java.lang.String, java.lang.Object> searchMap,
		java.lang.String status, boolean lanuageSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .countListScienceApp(groupId, locale, authorId, appTypes,
			editorTypes, searchMap, status, lanuageSearch);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> getScienceAppReturnObject(
		long scienceAppId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService().getScienceAppReturnObject(scienceAppId, locale);
	}

	public static void deleteScienceAppRelation(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteScienceAppRelation(scienceAppId);
	}

	public static org.kisti.edison.science.model.ScienceApp updateExeInfomaionScienceApp(
		com.liferay.portal.service.ServiceContext sc, java.util.Map params,
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateExeInfomaionScienceApp(sc, params, scienceAppId);
	}

	public static long copyScienceApp(
		com.liferay.portal.service.ServiceContext sc, long scienceAppId,
		java.lang.String newVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().copyScienceApp(sc, scienceAppId, newVersion);
	}

	public static void addScienceAppFile(long companyId,
		java.lang.String appName, java.lang.String appVersion,
		java.lang.String fileName, java.io.InputStream uploadedInputStream)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addScienceAppFile(companyId, appName, appVersion, fileName,
			uploadedInputStream);
	}

	public static void addScienceAppFile(long companyId,
		java.lang.String appName, java.lang.String appVersion,
		java.lang.String fileName, java.io.InputStream uploadedInputStream,
		boolean isCompile)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addScienceAppFile(companyId, appName, appVersion, fileName,
			uploadedInputStream, isCompile);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListAppTest(
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().retrieveListAppTest(params);
	}

	public static int countAppTest(long scienceAppId) {
		return getService().countAppTest(scienceAppId);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getMyAppListWithQna(
		java.util.Map params, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMyAppListWithQna(params, locale);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getListMyAppQna(
		java.util.Map params, java.util.Locale locale) {
		return getService().getListMyAppQna(params, locale);
	}

	/**
	* 관련정보(Asset) 포틀릿에서 사용하는 서비스 - 리스트
	* 입력된 검색어로 사이언스앱을 조회함
	*
	* @param params
	* @return
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> searchAssetEntryModelAPP(
		java.util.Map params) {
		return getService().searchAssetEntryModelAPP(params);
	}

	/**
	* 관련정보(Asset) 포틀릿에서 사용하는 서비스  - 카운트
	* 입력된 검색어로 사이언스앱을 조회함
	*
	* @param params
	* @return
	*/
	public static int searchAssetEntryModelAPPCount(java.util.Map params) {
		return getService().searchAssetEntryModelAPPCount(params);
	}

	/**
	* 관련정보(Asset) 포틀릿에서 사용하는 서비스  - 리스트
	* entryId(Asset) 와 관련된 모든 사이언스 앱 리스트를 조회함.
	*
	* @param params
	* @return
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> relatedAssetLinkedEntryScienceAPP(
		java.util.Map params) {
		return getService().relatedAssetLinkedEntryScienceAPP(params);
	}

	public static org.kisti.edison.science.model.ScienceApp updateScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateScienceApp(scienceApp, status);
	}

	/**
	* ScienceApp Mirgation = 2017-03-23 HKD
	* 기존 등록된 ScienceApp을 AssetEntry 등록 및 AssetCategory에 등록
	*
	* @param scienceApp
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	public static boolean migrationScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().migrationScienceApp(scienceApp);
	}

	/**
	* 에디슨 프로젝트 에서 사용하는 Method
	*
	* @param params
	* @param locale
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getMyAppListForProject(
		java.util.Map params, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMyAppListForProject(params, locale);
	}

	public static int getMyAppListForProjectCount(java.util.Map params,
		java.util.Locale locale) {
		return getService().getMyAppListForProjectCount(params, locale);
	}

	public static org.kisti.edison.science.model.ScienceApp getScienceApp(
		java.lang.String scienceAppName, java.lang.String scienceAppVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppException {
		return getService().getScienceApp(scienceAppName, scienceAppVersion);
	}

	public static int countScienceApp(long companyGroupId, long groupId,
		long categoryId, java.util.Locale locale,
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .countScienceApp(companyGroupId, groupId, categoryId,
			locale, searchParam);
	}

	/**
	* ScienceApp List 조회
	*
	* @param companyGroupId
	* @param groupId
	* @param categoryId - 0일 경우 groupId를 통하여 전체 카테고리를 조회
	* @param locale
	* @param searchParam - 조회할 parameter Map
	* @param begin - 시작 0 LIMIT를 사용
	* @param end
	* @param widthFile - 목록에서 파일이 필요 할 경우 true
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceApp(
		long companyGroupId, long groupId, long categoryId,
		java.util.Locale locale,
		java.util.Map<java.lang.String, java.lang.Object> searchParam,
		int begin, int end, boolean widthFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .retrieveListScienceApp(companyGroupId, groupId, categoryId,
			locale, searchParam, begin, end, widthFile);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> retrieveListByTemplateId(
		java.lang.String templateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().retrieveListByTemplateId(templateId);
	}

	public static com.liferay.portlet.ratings.model.RatingsStats getScienceAppRatingsStats(
		long scienceAppId, long classNameId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getScienceAppRatingsStats(scienceAppId, classNameId,
			className);
	}

	public static boolean deleteScienceAppRatingsStats(long scienceAppId,
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteScienceAppRatingsStats(scienceAppId, className);
	}

	public static org.kisti.edison.science.model.ScienceAppRatingsEntry getScienceAppMyRatingsEntry(
		long scienceAppId, java.lang.String className,
		com.liferay.portal.model.User user)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getScienceAppMyRatingsEntry(scienceAppId, className, user);
	}

	public static org.kisti.edison.science.model.ScienceAppRatingsEntry setScienceAppMyRatingsEntry(
		long scienceAppId, java.lang.String className,
		com.liferay.portal.model.User user, long score)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .setScienceAppMyRatingsEntry(scienceAppId, className, user,
			score);
	}

	public static boolean deleteScienceAppMyRatingsEntry(long scienceAppId,
		com.liferay.portal.model.User user)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteScienceAppMyRatingsEntry(scienceAppId, user);
	}

	public static boolean deleteScienceAppPaperItem(long scienceAppId,
		long paperSeq, java.lang.String paperType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteScienceAppPaperItem(scienceAppId, paperSeq, paperType);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getScienceAppPaperList(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppPaperList(scienceAppId);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> getScienceAppExecuteStatistics(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppExecuteStatistics(scienceAppId);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getScienceAppHistoryList(
		java.util.Map<java.lang.String, java.lang.Object> searchParamMap)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppHistoryList(searchParamMap);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getScienceAppReviewList(
		java.util.Map<java.lang.String, java.lang.Object> searchParamMap)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppReviewList(searchParamMap);
	}

	public static int getSimulationUsersOfScienceApp(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSimulationUsersOfScienceApp(scienceAppId);
	}

	public static int countScienceAppByWorkflowId(long workflowId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().countScienceAppByWorkflowId(workflowId);
	}

	public static org.kisti.edison.science.model.ScienceApp getScienceAppByWorkflowId(
		long workflowId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			javax.persistence.NonUniqueResultException,
			org.kisti.edison.science.NoSuchScienceAppException {
		return getService().getScienceAppByWorkflowId(workflowId);
	}

	public static java.util.List<java.lang.Long> getOrganizationRegisteredWithApp()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			javax.persistence.NonUniqueResultException,
			org.kisti.edison.science.NoSuchScienceAppException {
		return getService().getOrganizationRegisteredWithApp();
	}

	public static void clearService() {
		_service = null;
	}

	public static ScienceAppLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ScienceAppLocalService.class.getName());

			if (invokableLocalService instanceof ScienceAppLocalService) {
				_service = (ScienceAppLocalService)invokableLocalService;
			}
			else {
				_service = new ScienceAppLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ScienceAppLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ScienceAppLocalService service) {
	}

	private static ScienceAppLocalService _service;
}