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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScienceAppLocalService}.
 *
 * @author EDISON
 * @see ScienceAppLocalService
 * @generated
 */
public class ScienceAppLocalServiceWrapper implements ScienceAppLocalService,
	ServiceWrapper<ScienceAppLocalService> {
	public ScienceAppLocalServiceWrapper(
		ScienceAppLocalService scienceAppLocalService) {
		_scienceAppLocalService = scienceAppLocalService;
	}

	/**
	* Adds the science app to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceApp addScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.addScienceApp(scienceApp);
	}

	/**
	* Creates a new science app with the primary key. Does not add the science app to the database.
	*
	* @param scienceAppId the primary key for the new science app
	* @return the new science app
	*/
	@Override
	public org.kisti.edison.science.model.ScienceApp createScienceApp(
		long scienceAppId) {
		return _scienceAppLocalService.createScienceApp(scienceAppId);
	}

	/**
	* Deletes the science app with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the science app
	* @return the science app that was removed
	* @throws PortalException if a science app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceApp deleteScienceApp(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.deleteScienceApp(scienceAppId);
	}

	/**
	* Deletes the science app from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceApp deleteScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.deleteScienceApp(scienceApp);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _scienceAppLocalService.dynamicQuery();
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
		return _scienceAppLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _scienceAppLocalService.dynamicQueryCount(dynamicQuery);
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
		return _scienceAppLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.science.model.ScienceApp fetchScienceApp(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.fetchScienceApp(scienceAppId);
	}

	/**
	* Returns the science app with the matching UUID and company.
	*
	* @param uuid the science app's UUID
	* @param companyId the primary key of the company
	* @return the matching science app, or <code>null</code> if a matching science app could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceApp fetchScienceAppByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.fetchScienceAppByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the science app matching the UUID and group.
	*
	* @param uuid the science app's UUID
	* @param groupId the primary key of the group
	* @return the matching science app, or <code>null</code> if a matching science app could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceApp fetchScienceAppByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.fetchScienceAppByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the science app with the primary key.
	*
	* @param scienceAppId the primary key of the science app
	* @return the science app
	* @throws PortalException if a science app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceApp getScienceApp(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceApp(scienceAppId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public org.kisti.edison.science.model.ScienceApp getScienceAppByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public org.kisti.edison.science.model.ScienceApp getScienceAppByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceApps(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceApps(start, end);
	}

	/**
	* Returns the number of science apps.
	*
	* @return the number of science apps
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getScienceAppsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppsCount();
	}

	/**
	* Updates the science app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceApp updateScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.updateScienceApp(scienceApp);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _scienceAppLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_scienceAppLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _scienceAppLocalService.invokeMethod(name, parameterTypes,
			arguments);
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
	@Override
	public org.kisti.edison.science.model.ScienceApp createScienceApp(
		java.lang.String appName, java.lang.String appVersion,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.createScienceApp(appName, appVersion, sc);
	}

	@Override
	public org.kisti.edison.science.model.ScienceApp getTextEditorScienceApp()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getTextEditorScienceApp();
	}

	@Override
	public org.kisti.edison.science.model.ScienceApp getFileEditorScienceApp()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getFileEditorScienceApp();
	}

	@Override
	public org.kisti.edison.science.model.ScienceApp getStructuredEditorScienceApp()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getStructuredEditorScienceApp();
	}

	@Override
	public org.kisti.edison.science.model.ScienceApp getEditorScienceApp(
		java.lang.String editorType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getEditorScienceApp(editorType);
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
	@Override
	public org.kisti.edison.science.model.ScienceApp addScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.addScienceApp(scienceApp, sc);
	}

	@Override
	public void setScienceAppInputPorts(long scienceAppId,
		java.lang.String inputPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.setScienceAppInputPorts(scienceAppId, inputPorts);
	}

	@Override
	public java.lang.String getScienceAppInputPorts(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppInputPorts(scienceAppId);
	}

	@Override
	public void setScienceAppLogPorts(long scienceAppId,
		java.lang.String logPorts)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.setScienceAppLogPorts(scienceAppId, logPorts);
	}

	@Override
	public java.lang.String getScienceAppLogPorts(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppLogPorts(scienceAppId);
	}

	@Override
	public void setScienceAppOutputPorts(long scienceAppId,
		java.lang.String outputPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.setScienceAppOutputPorts(scienceAppId,
			outputPorts);
	}

	@Override
	public java.lang.String getScienceAppOutputPorts(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppOutputPorts(scienceAppId);
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
	@Override
	public boolean verifyScienceAppName(java.lang.String appName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.verifyScienceAppName(appName);
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
	@Override
	public boolean existAppName(java.lang.String appName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.existAppName(appName);
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
	@Override
	public boolean existApp(java.lang.String appName,
		java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.existApp(appName, appVersion);
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
	@Override
	public org.kisti.edison.science.model.ScienceApp getLatestVersion(
		java.lang.String appName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getLatestVersion(appName);
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
	@Override
	public boolean verifyVersionNumber(java.lang.String appName,
		java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.verifyVersionNumber(appName, appVersion);
	}

	@Override
	public void deleteAllScienceApps()
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.deleteAllScienceApps();
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getAll();
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
	@Override
	public org.kisti.edison.science.model.ScienceApp updateScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.updateScienceApp(scienceApp, sc);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByStatus(status);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByStage(
		java.lang.String stage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByStage(stage);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorIdStatus(
		long authorId, int appStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorIdStatus(authorId,
			appStatus);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorIdStatus(
		long authorId, int appStatus, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorIdStatus(authorId,
			appStatus, start, end);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorIdAppType(
		long authorId, java.lang.String appType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorIdAppType(authorId,
			appType);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorIdAppType(
		long authorId, java.lang.String appClass, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorIdAppType(authorId,
			appClass, start, end);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorId(
		long authorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorId(authorId);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByAuthorId(
		long authorId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorId(authorId,
			start, end);
	}

	@Override
	public int countScienceAppsByAuthorId(long authorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countScienceAppsByAuthorId(authorId);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByOpenLevel(
		java.lang.String openLevel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByOpenLevel(openLevel);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByOpenLevel(
		java.lang.String openLevel, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByOpenLevel(openLevel,
			start, end);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByManagerId(
		long managerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByManagerId(managerId);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByManagerId(
		long managerId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByManagerId(managerId,
			start, end);
	}

	@Override
	public int countScienceAppsByManagerId(long managerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countScienceAppsByManagerId(managerId);
	}

	@Override
	public long[] getScienceAppManagerIds(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppManagerIds(scienceAppId);
	}

	@Override
	public long[] getScienceAppManagerIds(long scienceAppId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppManagerIds(scienceAppId,
			start, end);
	}

	@Override
	public int countScienceAppManagers(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countScienceAppManagers(scienceAppId);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByCategoryId(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByCategoryId(categoryId);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByCategoryId(
		long categoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByCategoryId(categoryId,
			start, end);
	}

	@Override
	public int countScienceAppsByCategoryId(long categoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countScienceAppsByCategoryId(categoryId);
	}

	@Override
	public long[] getScienceAppCategoryIds(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppCategoryIds(scienceAppId);
	}

	@Override
	public long[] getScienceAppCategoryIds(long scienceAppId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppCategoryIds(scienceAppId,
			start, end);
	}

	@Override
	public int countScienceAppCategories(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countScienceAppCategories(scienceAppId);
	}

	@Override
	public void assignScienceAppToCategories(long scienceAppId,
		long[] categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.assignScienceAppToCategories(scienceAppId,
			categoryIds);
	}

	@Override
	public void assignScienceAppToCategory(long scienceAppId, long categoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.assignScienceAppToCategory(scienceAppId,
			categoryId);
	}

	@Override
	public void assignManagersToScienceApp(long scienceAppId, long[] managerIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.assignManagersToScienceApp(scienceAppId,
			managerIds);
	}

	@Override
	public void assignManagerToScienceApp(long scienceAppId, long managerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.assignManagerToScienceApp(scienceAppId,
			managerId);
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
	@Override
	public java.lang.String getScienceAppBinPath(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppBinPath(scienceAppId);
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
	@Override
	public java.lang.String getScienceAppSrcPath(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppSrcPath(scienceAppId);
	}

	/**
	* Counts all science apps in the database.
	*
	* @return int - count
	*/
	@Override
	public int countAllScienceApps()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countAllScienceApps();
	}

	@Override
	public boolean existScienceAppPath(java.lang.String targetPath)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return _scienceAppLocalService.existScienceAppPath(targetPath);
	}

	@Override
	public void deleteScienceAppDir(java.lang.String targetDir)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		_scienceAppLocalService.deleteScienceAppDir(targetDir);
	}

	@Override
	public void makeScienceAppDir(java.lang.String targetDir)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		_scienceAppLocalService.makeScienceAppDir(targetDir);
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
	@Override
	public java.io.File saveToScienceAppStorage(java.lang.String targetDir,
		java.lang.String fileName, java.io.InputStream uploadedInputStream)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return _scienceAppLocalService.saveToScienceAppStorage(targetDir,
			fileName, uploadedInputStream);
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
	@Override
	public void unzipScienceAppZipFile(java.lang.String sourceFile,
		java.lang.String targetDir)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		_scienceAppLocalService.unzipScienceAppZipFile(sourceFile, targetDir);
	}

	/**
	* ADD GPLUS SERVICE
	*      ********************
	*/
	@Override
	public void cleanScienceAppDir(long companyId, java.lang.String appName,
		java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		_scienceAppLocalService.cleanScienceAppDir(companyId, appName,
			appVersion);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByCategoryId2(
		long categoryId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByCategoryId2(categoryId,
			locale);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByCategoryId(
		long categoryId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByCategoryId(categoryId,
			locale);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByCategoryId(
		long categoryId, java.util.Locale locale, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByCategoryId(categoryId,
			locale, start, end);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByScienceAppIdsAndLocale(
		long[] scienceAppIds, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByScienceAppIdsAndLocale(scienceAppIds,
			locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getLanguageSpecificAssetCategories(
		java.util.List<com.liferay.portlet.asset.model.AssetCategory> categories,
		long parentCategoryId, java.util.Locale locale) {
		return _scienceAppLocalService.getLanguageSpecificAssetCategories(categories,
			parentCategoryId, locale);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceApp> getScienceAppListByScienceAppIdsAndLocale(
		long[] scienceAppIds, java.util.Locale locale, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByScienceAppIdsAndLocale(scienceAppIds,
			locale, start, end);
	}

	/**
	* @param sc
	* @param params
	* @return
	* @throws ScienceAppException
	* @throws SystemException
	*/
	@Override
	public org.kisti.edison.science.model.ScienceApp createScienceApp(
		com.liferay.portal.service.ServiceContext sc, java.util.Map params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.createScienceApp(sc, params);
	}

	@Override
	public void updateScienceApp(com.liferay.portal.service.ServiceContext sc,
		java.util.Map params, long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppException {
		_scienceAppLocalService.updateScienceApp(sc, params, scienceAppId);
	}

	/**
	* 통합 검색 앱 조회 서비스
	*
	* @param categoryIds -> Category 조회가 아닐 경우에는 null 입력
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceAppFromExplore(
		long companyGroupId, long groupId, java.util.Locale locale,
		java.lang.String[] appTypes, long[] categoryIds,
		java.lang.String searchText, int begin, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.retrieveListScienceAppFromExplore(companyGroupId,
			groupId, locale, appTypes, categoryIds, searchText, begin, end);
	}

	/**
	* 통합 검색 앱 카운트 서비스
	*
	* @param categoryIds -> Category 조회가 아닐 경우에는 null 입력
	*/
	@Override
	public int countScienceAppFromExplore(long companyGroupId, long groupId,
		java.util.Locale locale, java.lang.String[] appTypes,
		long[] categoryIds, java.lang.String searchText)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countScienceAppFromExplore(companyGroupId,
			groupId, locale, appTypes, categoryIds, searchText);
	}

	/**
	* EDISON - MAIN 추천 앱 조회
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListHotScienceApp(
		long companyGroupId, long groupId, java.util.Locale locale,
		long[] appIds, int begin, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.retrieveListHotScienceApp(companyGroupId,
			groupId, locale, appIds, begin, end);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceAppAsManager(
		long companyGroupId, long groupId, java.util.Locale locale,
		long managerId, java.lang.String[] appTypes,
		java.lang.String[] editorTypes, java.lang.String searchType,
		java.lang.String searchText, java.lang.String status,
		boolean categorySearch, int begin, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.retrieveListScienceAppAsManager(companyGroupId,
			groupId, locale, managerId, appTypes, editorTypes, searchType,
			searchText, status, categorySearch, begin, end);
	}

	@Override
	public int countScienceAppAsManager(long companyGroupId, long groupId,
		java.util.Locale locale, long managerId, java.lang.String[] appTypes,
		java.lang.String[] editorTypes, java.lang.String searchType,
		java.lang.String searchText, java.lang.String status,
		boolean categorySearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countScienceAppAsManager(companyGroupId,
			groupId, locale, managerId, appTypes, editorTypes, searchType,
			searchText, status, categorySearch);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceAppAsCategory(
		long companyGroupId, long groupId, java.util.Locale locale,
		long authorId, java.lang.String[] appTypes,
		java.lang.String[] editorTypes, java.lang.String searchType,
		java.lang.String searchText, java.lang.String status, int begin,
		int end, boolean lanuageSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.retrieveListScienceAppAsCategory(companyGroupId,
			groupId, locale, authorId, appTypes, editorTypes, searchType,
			searchText, status, begin, end, lanuageSearch);
	}

	@Override
	public int countListScienceAppAsCategory(long companyGroupId, long groupId,
		java.util.Locale locale, long authorId, java.lang.String[] appTypes,
		java.lang.String[] editorTypes, java.lang.String searchType,
		java.lang.String searchText, java.lang.String status,
		boolean lanuageSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countListScienceAppAsCategory(companyGroupId,
			groupId, locale, authorId, appTypes, editorTypes, searchType,
			searchText, status, lanuageSearch);
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
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceAppByName(
		long companyGroupId, long groupId, java.util.Locale locale,
		java.lang.String appName, boolean categorySearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.retrieveListScienceAppByName(companyGroupId,
			groupId, locale, appName, categorySearch);
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
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListScienceApp(
		long groupId, java.util.Locale locale, long authorId,
		java.lang.String[] appTypes, java.lang.String[] editorTypes,
		java.lang.String searchType, java.lang.String searchText,
		java.lang.String status, int begin, int end, boolean lanuageSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.retrieveListScienceApp(groupId, locale,
			authorId, appTypes, editorTypes, searchType, searchText, status,
			begin, end, lanuageSearch);
	}

	@Override
	public int countListScienceApp(long groupId, java.util.Locale locale,
		long authorId, java.lang.String[] appTypes,
		java.lang.String[] editorTypes, java.lang.String searchType,
		java.lang.String searchText, java.lang.String status,
		boolean lanuageSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countListScienceApp(groupId, locale,
			authorId, appTypes, editorTypes, searchType, searchText, status,
			lanuageSearch);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getScienceAppReturnObject(
		long scienceAppId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _scienceAppLocalService.getScienceAppReturnObject(scienceAppId,
			locale);
	}

	@Override
	public void deleteScienceAppRelation(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.deleteScienceAppRelation(scienceAppId);
	}

	@Override
	public org.kisti.edison.science.model.ScienceApp updateExeInfomaionScienceApp(
		com.liferay.portal.service.ServiceContext sc, java.util.Map params,
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.updateExeInfomaionScienceApp(sc, params,
			scienceAppId);
	}

	@Override
	public long copyScienceApp(com.liferay.portal.service.ServiceContext sc,
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.copyScienceApp(sc, scienceAppId);
	}

	@Override
	public void addScienceAppFile(long companyId, java.lang.String appName,
		java.lang.String appVersion, java.lang.String fileName,
		java.io.InputStream uploadedInputStream)
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.addScienceAppFile(companyId, appName,
			appVersion, fileName, uploadedInputStream);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListAppTest(
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.retrieveListAppTest(params);
	}

	@Override
	public int countAppTest(long scienceAppId) {
		return _scienceAppLocalService.countAppTest(scienceAppId);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getMyAppListWithQna(
		java.util.Map params, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getMyAppListWithQna(params, locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getListMyAppQna(
		java.util.Map params, java.util.Locale locale) {
		return _scienceAppLocalService.getListMyAppQna(params, locale);
	}

	/**
	* 관련정보(Asset) 포틀릿에서 사용하는 서비스 - 리스트
	* 입력된 검색어로 사이언스앱을 조회함
	*
	* @param params
	* @return
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> searchAssetEntryModelAPP(
		java.util.Map params) {
		return _scienceAppLocalService.searchAssetEntryModelAPP(params);
	}

	/**
	* 관련정보(Asset) 포틀릿에서 사용하는 서비스  - 카운트
	* 입력된 검색어로 사이언스앱을 조회함
	*
	* @param params
	* @return
	*/
	@Override
	public int searchAssetEntryModelAPPCount(java.util.Map params) {
		return _scienceAppLocalService.searchAssetEntryModelAPPCount(params);
	}

	/**
	* 관련정보(Asset) 포틀릿에서 사용하는 서비스  - 리스트
	* entryId(Asset) 와 관련된 모든 사이언스 앱 리스트를 조회함.
	*
	* @param params
	* @return
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> relatedAssetLinkedEntryScienceAPP(
		java.util.Map params) {
		return _scienceAppLocalService.relatedAssetLinkedEntryScienceAPP(params);
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
	@Override
	public boolean migrationScienceApp(
		org.kisti.edison.science.model.ScienceApp scienceApp)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.migrationScienceApp(scienceApp);
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
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getMyAppListForProject(
		java.util.Map params, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getMyAppListForProject(params, locale);
	}

	@Override
	public int getMyAppListForProjectCount(java.util.Map params,
		java.util.Locale locale) {
		return _scienceAppLocalService.getMyAppListForProjectCount(params,
			locale);
	}

	@Override
	public org.kisti.edison.science.model.ScienceApp getScienceApp(
		java.lang.String scienceAppName, java.lang.String scienceAppVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppException {
		return _scienceAppLocalService.getScienceApp(scienceAppName,
			scienceAppVersion);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ScienceAppLocalService getWrappedScienceAppLocalService() {
		return _scienceAppLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedScienceAppLocalService(
		ScienceAppLocalService scienceAppLocalService) {
		_scienceAppLocalService = scienceAppLocalService;
	}

	@Override
	public ScienceAppLocalService getWrappedService() {
		return _scienceAppLocalService;
	}

	@Override
	public void setWrappedService(ScienceAppLocalService scienceAppLocalService) {
		_scienceAppLocalService = scienceAppLocalService;
	}

	private ScienceAppLocalService _scienceAppLocalService;
}