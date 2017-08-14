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
 * Provides a wrapper for {@link ScienceAppCompileLocalService}.
 *
 * @author EDISON
 * @see ScienceAppCompileLocalService
 * @generated
 */
public class ScienceAppCompileLocalServiceWrapper
	implements ScienceAppCompileLocalService,
		ServiceWrapper<ScienceAppCompileLocalService> {
	public ScienceAppCompileLocalServiceWrapper(
		ScienceAppCompileLocalService scienceAppCompileLocalService) {
		_scienceAppCompileLocalService = scienceAppCompileLocalService;
	}

	/**
	* Adds the science app compile to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppCompile the science app compile
	* @return the science app compile that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppCompile addScienceAppCompile(
		org.kisti.edison.science.model.ScienceAppCompile scienceAppCompile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.addScienceAppCompile(scienceAppCompile);
	}

	/**
	* Creates a new science app compile with the primary key. Does not add the science app compile to the database.
	*
	* @param scienceAppId the primary key for the new science app compile
	* @return the new science app compile
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppCompile createScienceAppCompile(
		long scienceAppId) {
		return _scienceAppCompileLocalService.createScienceAppCompile(scienceAppId);
	}

	/**
	* Deletes the science app compile with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the science app compile
	* @return the science app compile that was removed
	* @throws PortalException if a science app compile with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppCompile deleteScienceAppCompile(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.deleteScienceAppCompile(scienceAppId);
	}

	/**
	* Deletes the science app compile from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppCompile the science app compile
	* @return the science app compile that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppCompile deleteScienceAppCompile(
		org.kisti.edison.science.model.ScienceAppCompile scienceAppCompile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.deleteScienceAppCompile(scienceAppCompile);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _scienceAppCompileLocalService.dynamicQuery();
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
		return _scienceAppCompileLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _scienceAppCompileLocalService.dynamicQueryCount(dynamicQuery);
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
		return _scienceAppCompileLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppCompile fetchScienceAppCompile(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.fetchScienceAppCompile(scienceAppId);
	}

	/**
	* Returns the science app compile with the primary key.
	*
	* @param scienceAppId the primary key of the science app compile
	* @return the science app compile
	* @throws PortalException if a science app compile with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppCompile getScienceAppCompile(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.getScienceAppCompile(scienceAppId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceAppCompile> getScienceAppCompiles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.getScienceAppCompiles(start, end);
	}

	/**
	* Returns the number of science app compiles.
	*
	* @return the number of science app compiles
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getScienceAppCompilesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.getScienceAppCompilesCount();
	}

	/**
	* Updates the science app compile in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceAppCompile the science app compile
	* @return the science app compile that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppCompile updateScienceAppCompile(
		org.kisti.edison.science.model.ScienceAppCompile scienceAppCompile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.updateScienceAppCompile(scienceAppCompile);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _scienceAppCompileLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_scienceAppCompileLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _scienceAppCompileLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppCompile compileFindByScienceAppId(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.compileFindByScienceAppId(scienceAppId);
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppCompile updateCompileAndScienceApp(
		long scienceAppId, long userId, java.lang.String gitHubUrl,
		java.lang.String result)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppCompileLocalService.updateCompileAndScienceApp(scienceAppId,
			userId, gitHubUrl, result);
	}

	@Override
	public void deleteCompileAndScienceApp(long companyId, long scienceAppId,
		java.lang.String appName, java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_scienceAppCompileLocalService.deleteCompileAndScienceApp(companyId,
			scienceAppId, appName, appVersion);
	}

	/**
	* @param gitHubUrl
	* @throws InterruptedException
	* @throws IOException
	* @throws SystemException
	*/
	@Override
	public java.lang.String gitHubCloneToScienceAppFolders(long companyId,
		java.lang.String appName, java.lang.String appVersion,
		java.lang.String gitHubUrl)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return _scienceAppCompileLocalService.gitHubCloneToScienceAppFolders(companyId,
			appName, appVersion, gitHubUrl);
	}

	@Override
	public java.lang.String cleanMakeFileToTargetScienceApp(long companyId,
		java.lang.String appName, java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return _scienceAppCompileLocalService.cleanMakeFileToTargetScienceApp(companyId,
			appName, appVersion);
	}

	@Override
	public java.lang.String makeFileToTargetScienceApp(long companyId,
		java.lang.String appName, java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return _scienceAppCompileLocalService.makeFileToTargetScienceApp(companyId,
			appName, appVersion);
	}

	@Override
	public java.lang.String retrieveListTartgetDir(long companyId,
		java.lang.String appName, java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.lang.InterruptedException {
		return _scienceAppCompileLocalService.retrieveListTartgetDir(companyId,
			appName, appVersion);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ScienceAppCompileLocalService getWrappedScienceAppCompileLocalService() {
		return _scienceAppCompileLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedScienceAppCompileLocalService(
		ScienceAppCompileLocalService scienceAppCompileLocalService) {
		_scienceAppCompileLocalService = scienceAppCompileLocalService;
	}

	@Override
	public ScienceAppCompileLocalService getWrappedService() {
		return _scienceAppCompileLocalService;
	}

	@Override
	public void setWrappedService(
		ScienceAppCompileLocalService scienceAppCompileLocalService) {
		_scienceAppCompileLocalService = scienceAppCompileLocalService;
	}

	private ScienceAppCompileLocalService _scienceAppCompileLocalService;
}