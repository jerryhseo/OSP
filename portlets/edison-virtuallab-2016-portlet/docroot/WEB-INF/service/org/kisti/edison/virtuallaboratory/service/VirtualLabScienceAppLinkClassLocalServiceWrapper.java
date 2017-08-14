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

package org.kisti.edison.virtuallaboratory.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VirtualLabScienceAppLinkClassLocalService}.
 *
 * @author EDISON
 * @see VirtualLabScienceAppLinkClassLocalService
 * @generated
 */
public class VirtualLabScienceAppLinkClassLocalServiceWrapper
	implements VirtualLabScienceAppLinkClassLocalService,
		ServiceWrapper<VirtualLabScienceAppLinkClassLocalService> {
	public VirtualLabScienceAppLinkClassLocalServiceWrapper(
		VirtualLabScienceAppLinkClassLocalService virtualLabScienceAppLinkClassLocalService) {
		_virtualLabScienceAppLinkClassLocalService = virtualLabScienceAppLinkClassLocalService;
	}

	/**
	* Adds the virtual lab science app link class to the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabScienceAppLinkClass the virtual lab science app link class
	* @return the virtual lab science app link class that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass addVirtualLabScienceAppLinkClass(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkClassLocalService.addVirtualLabScienceAppLinkClass(virtualLabScienceAppLinkClass);
	}

	/**
	* Creates a new virtual lab science app link class with the primary key. Does not add the virtual lab science app link class to the database.
	*
	* @param scienceAppClassSeqNo the primary key for the new virtual lab science app link class
	* @return the new virtual lab science app link class
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass createVirtualLabScienceAppLinkClass(
		long scienceAppClassSeqNo) {
		return _virtualLabScienceAppLinkClassLocalService.createVirtualLabScienceAppLinkClass(scienceAppClassSeqNo);
	}

	/**
	* Deletes the virtual lab science app link class with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppClassSeqNo the primary key of the virtual lab science app link class
	* @return the virtual lab science app link class that was removed
	* @throws PortalException if a virtual lab science app link class with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass deleteVirtualLabScienceAppLinkClass(
		long scienceAppClassSeqNo)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkClassLocalService.deleteVirtualLabScienceAppLinkClass(scienceAppClassSeqNo);
	}

	/**
	* Deletes the virtual lab science app link class from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabScienceAppLinkClass the virtual lab science app link class
	* @return the virtual lab science app link class that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass deleteVirtualLabScienceAppLinkClass(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkClassLocalService.deleteVirtualLabScienceAppLinkClass(virtualLabScienceAppLinkClass);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _virtualLabScienceAppLinkClassLocalService.dynamicQuery();
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
		return _virtualLabScienceAppLinkClassLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _virtualLabScienceAppLinkClassLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _virtualLabScienceAppLinkClassLocalService.dynamicQuery(dynamicQuery,
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
		return _virtualLabScienceAppLinkClassLocalService.dynamicQueryCount(dynamicQuery);
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
		return _virtualLabScienceAppLinkClassLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass fetchVirtualLabScienceAppLinkClass(
		long scienceAppClassSeqNo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkClassLocalService.fetchVirtualLabScienceAppLinkClass(scienceAppClassSeqNo);
	}

	/**
	* Returns the virtual lab science app link class with the primary key.
	*
	* @param scienceAppClassSeqNo the primary key of the virtual lab science app link class
	* @return the virtual lab science app link class
	* @throws PortalException if a virtual lab science app link class with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass getVirtualLabScienceAppLinkClass(
		long scienceAppClassSeqNo)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkClassLocalService.getVirtualLabScienceAppLinkClass(scienceAppClassSeqNo);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkClassLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the virtual lab science app link classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual lab science app link classes
	* @param end the upper bound of the range of virtual lab science app link classes (not inclusive)
	* @return the range of virtual lab science app link classes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass> getVirtualLabScienceAppLinkClasses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkClassLocalService.getVirtualLabScienceAppLinkClasses(start,
			end);
	}

	/**
	* Returns the number of virtual lab science app link classes.
	*
	* @return the number of virtual lab science app link classes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getVirtualLabScienceAppLinkClassesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkClassLocalService.getVirtualLabScienceAppLinkClassesCount();
	}

	/**
	* Updates the virtual lab science app link class in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param virtualLabScienceAppLinkClass the virtual lab science app link class
	* @return the virtual lab science app link class that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass updateVirtualLabScienceAppLinkClass(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkClassLocalService.updateVirtualLabScienceAppLinkClass(virtualLabScienceAppLinkClass);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _virtualLabScienceAppLinkClassLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_virtualLabScienceAppLinkClassLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _virtualLabScienceAppLinkClassLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public VirtualLabScienceAppLinkClassLocalService getWrappedVirtualLabScienceAppLinkClassLocalService() {
		return _virtualLabScienceAppLinkClassLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedVirtualLabScienceAppLinkClassLocalService(
		VirtualLabScienceAppLinkClassLocalService virtualLabScienceAppLinkClassLocalService) {
		_virtualLabScienceAppLinkClassLocalService = virtualLabScienceAppLinkClassLocalService;
	}

	@Override
	public VirtualLabScienceAppLinkClassLocalService getWrappedService() {
		return _virtualLabScienceAppLinkClassLocalService;
	}

	@Override
	public void setWrappedService(
		VirtualLabScienceAppLinkClassLocalService virtualLabScienceAppLinkClassLocalService) {
		_virtualLabScienceAppLinkClassLocalService = virtualLabScienceAppLinkClassLocalService;
	}

	private VirtualLabScienceAppLinkClassLocalService _virtualLabScienceAppLinkClassLocalService;
}