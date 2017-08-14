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
 * Provides a wrapper for {@link ScienceAppLogPortsLocalService}.
 *
 * @author EDISON
 * @see ScienceAppLogPortsLocalService
 * @generated
 */
public class ScienceAppLogPortsLocalServiceWrapper
	implements ScienceAppLogPortsLocalService,
		ServiceWrapper<ScienceAppLogPortsLocalService> {
	public ScienceAppLogPortsLocalServiceWrapper(
		ScienceAppLogPortsLocalService scienceAppLogPortsLocalService) {
		_scienceAppLogPortsLocalService = scienceAppLogPortsLocalService;
	}

	/**
	* Adds the science app log ports to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppLogPorts the science app log ports
	* @return the science app log ports that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppLogPorts addScienceAppLogPorts(
		org.kisti.edison.science.model.ScienceAppLogPorts scienceAppLogPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.addScienceAppLogPorts(scienceAppLogPorts);
	}

	/**
	* Creates a new science app log ports with the primary key. Does not add the science app log ports to the database.
	*
	* @param scienceAppId the primary key for the new science app log ports
	* @return the new science app log ports
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppLogPorts createScienceAppLogPorts(
		long scienceAppId) {
		return _scienceAppLogPortsLocalService.createScienceAppLogPorts(scienceAppId);
	}

	/**
	* Deletes the science app log ports with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the science app log ports
	* @return the science app log ports that was removed
	* @throws PortalException if a science app log ports with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppLogPorts deleteScienceAppLogPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.deleteScienceAppLogPorts(scienceAppId);
	}

	/**
	* Deletes the science app log ports from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppLogPorts the science app log ports
	* @return the science app log ports that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppLogPorts deleteScienceAppLogPorts(
		org.kisti.edison.science.model.ScienceAppLogPorts scienceAppLogPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.deleteScienceAppLogPorts(scienceAppLogPorts);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _scienceAppLogPortsLocalService.dynamicQuery();
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
		return _scienceAppLogPortsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppLogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _scienceAppLogPortsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppLogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _scienceAppLogPortsLocalService.dynamicQuery(dynamicQuery,
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
		return _scienceAppLogPortsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _scienceAppLogPortsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppLogPorts fetchScienceAppLogPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.fetchScienceAppLogPorts(scienceAppId);
	}

	/**
	* Returns the science app log ports with the primary key.
	*
	* @param scienceAppId the primary key of the science app log ports
	* @return the science app log ports
	* @throws PortalException if a science app log ports with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppLogPorts getScienceAppLogPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.getScienceAppLogPorts(scienceAppId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the science app log portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppLogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app log portses
	* @param end the upper bound of the range of science app log portses (not inclusive)
	* @return the range of science app log portses
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceAppLogPorts> getScienceAppLogPortses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.getScienceAppLogPortses(start,
			end);
	}

	/**
	* Returns the number of science app log portses.
	*
	* @return the number of science app log portses
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getScienceAppLogPortsesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.getScienceAppLogPortsesCount();
	}

	/**
	* Updates the science app log ports in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceAppLogPorts the science app log ports
	* @return the science app log ports that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppLogPorts updateScienceAppLogPorts(
		org.kisti.edison.science.model.ScienceAppLogPorts scienceAppLogPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.updateScienceAppLogPorts(scienceAppLogPorts);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _scienceAppLogPortsLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_scienceAppLogPortsLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _scienceAppLogPortsLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppLogPorts create(
		long scienceAppId, java.lang.String logPorts)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.create(scienceAppId, logPorts);
	}

	@Override
	public java.lang.String getLogPortsJsonString(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.getLogPortsJsonString(scienceAppId);
	}

	@Override
	public void removeAllLogPorts()
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLogPortsLocalService.removeAllLogPorts();
	}

	@Override
	public long getScienceAppLogPortsesCount(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLogPortsLocalService.getScienceAppLogPortsesCount(scienceAppId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ScienceAppLogPortsLocalService getWrappedScienceAppLogPortsLocalService() {
		return _scienceAppLogPortsLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedScienceAppLogPortsLocalService(
		ScienceAppLogPortsLocalService scienceAppLogPortsLocalService) {
		_scienceAppLogPortsLocalService = scienceAppLogPortsLocalService;
	}

	@Override
	public ScienceAppLogPortsLocalService getWrappedService() {
		return _scienceAppLogPortsLocalService;
	}

	@Override
	public void setWrappedService(
		ScienceAppLogPortsLocalService scienceAppLogPortsLocalService) {
		_scienceAppLogPortsLocalService = scienceAppLogPortsLocalService;
	}

	private ScienceAppLogPortsLocalService _scienceAppLogPortsLocalService;
}