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

package org.kisti.edison.bestsimulation.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VirtualLabClassStatisticsLocalService}.
 *
 * @author EDISON
 * @see VirtualLabClassStatisticsLocalService
 * @generated
 */
public class VirtualLabClassStatisticsLocalServiceWrapper
	implements VirtualLabClassStatisticsLocalService,
		ServiceWrapper<VirtualLabClassStatisticsLocalService> {
	public VirtualLabClassStatisticsLocalServiceWrapper(
		VirtualLabClassStatisticsLocalService virtualLabClassStatisticsLocalService) {
		_virtualLabClassStatisticsLocalService = virtualLabClassStatisticsLocalService;
	}

	/**
	* Adds the virtual lab class statistics to the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStatistics the virtual lab class statistics
	* @return the virtual lab class statistics that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics addVirtualLabClassStatistics(
		org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics virtualLabClassStatistics)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStatisticsLocalService.addVirtualLabClassStatistics(virtualLabClassStatistics);
	}

	/**
	* Creates a new virtual lab class statistics with the primary key. Does not add the virtual lab class statistics to the database.
	*
	* @param virtualLabClassStatisticsPK the primary key for the new virtual lab class statistics
	* @return the new virtual lab class statistics
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics createVirtualLabClassStatistics(
		org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK virtualLabClassStatisticsPK) {
		return _virtualLabClassStatisticsLocalService.createVirtualLabClassStatistics(virtualLabClassStatisticsPK);
	}

	/**
	* Deletes the virtual lab class statistics with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStatisticsPK the primary key of the virtual lab class statistics
	* @return the virtual lab class statistics that was removed
	* @throws PortalException if a virtual lab class statistics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics deleteVirtualLabClassStatistics(
		org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK virtualLabClassStatisticsPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStatisticsLocalService.deleteVirtualLabClassStatistics(virtualLabClassStatisticsPK);
	}

	/**
	* Deletes the virtual lab class statistics from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStatistics the virtual lab class statistics
	* @return the virtual lab class statistics that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics deleteVirtualLabClassStatistics(
		org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics virtualLabClassStatistics)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStatisticsLocalService.deleteVirtualLabClassStatistics(virtualLabClassStatistics);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _virtualLabClassStatisticsLocalService.dynamicQuery();
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
		return _virtualLabClassStatisticsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _virtualLabClassStatisticsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _virtualLabClassStatisticsLocalService.dynamicQuery(dynamicQuery,
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
		return _virtualLabClassStatisticsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _virtualLabClassStatisticsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics fetchVirtualLabClassStatistics(
		org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK virtualLabClassStatisticsPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStatisticsLocalService.fetchVirtualLabClassStatistics(virtualLabClassStatisticsPK);
	}

	/**
	* Returns the virtual lab class statistics with the primary key.
	*
	* @param virtualLabClassStatisticsPK the primary key of the virtual lab class statistics
	* @return the virtual lab class statistics
	* @throws PortalException if a virtual lab class statistics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics getVirtualLabClassStatistics(
		org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK virtualLabClassStatisticsPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStatisticsLocalService.getVirtualLabClassStatistics(virtualLabClassStatisticsPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStatisticsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the virtual lab class statisticses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual lab class statisticses
	* @param end the upper bound of the range of virtual lab class statisticses (not inclusive)
	* @return the range of virtual lab class statisticses
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics> getVirtualLabClassStatisticses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStatisticsLocalService.getVirtualLabClassStatisticses(start,
			end);
	}

	/**
	* Returns the number of virtual lab class statisticses.
	*
	* @return the number of virtual lab class statisticses
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getVirtualLabClassStatisticsesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStatisticsLocalService.getVirtualLabClassStatisticsesCount();
	}

	/**
	* Updates the virtual lab class statistics in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStatistics the virtual lab class statistics
	* @return the virtual lab class statistics that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics updateVirtualLabClassStatistics(
		org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics virtualLabClassStatistics)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStatisticsLocalService.updateVirtualLabClassStatistics(virtualLabClassStatistics);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _virtualLabClassStatisticsLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_virtualLabClassStatisticsLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _virtualLabClassStatisticsLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public VirtualLabClassStatisticsLocalService getWrappedVirtualLabClassStatisticsLocalService() {
		return _virtualLabClassStatisticsLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedVirtualLabClassStatisticsLocalService(
		VirtualLabClassStatisticsLocalService virtualLabClassStatisticsLocalService) {
		_virtualLabClassStatisticsLocalService = virtualLabClassStatisticsLocalService;
	}

	@Override
	public VirtualLabClassStatisticsLocalService getWrappedService() {
		return _virtualLabClassStatisticsLocalService;
	}

	@Override
	public void setWrappedService(
		VirtualLabClassStatisticsLocalService virtualLabClassStatisticsLocalService) {
		_virtualLabClassStatisticsLocalService = virtualLabClassStatisticsLocalService;
	}

	private VirtualLabClassStatisticsLocalService _virtualLabClassStatisticsLocalService;
}