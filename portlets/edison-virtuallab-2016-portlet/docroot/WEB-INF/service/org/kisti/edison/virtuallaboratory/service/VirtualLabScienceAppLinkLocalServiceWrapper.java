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
 * Provides a wrapper for {@link VirtualLabScienceAppLinkLocalService}.
 *
 * @author EDISON
 * @see VirtualLabScienceAppLinkLocalService
 * @generated
 */
public class VirtualLabScienceAppLinkLocalServiceWrapper
	implements VirtualLabScienceAppLinkLocalService,
		ServiceWrapper<VirtualLabScienceAppLinkLocalService> {
	public VirtualLabScienceAppLinkLocalServiceWrapper(
		VirtualLabScienceAppLinkLocalService virtualLabScienceAppLinkLocalService) {
		_virtualLabScienceAppLinkLocalService = virtualLabScienceAppLinkLocalService;
	}

	/**
	* Adds the virtual lab science app link to the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabScienceAppLink the virtual lab science app link
	* @return the virtual lab science app link that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink addVirtualLabScienceAppLink(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink virtualLabScienceAppLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkLocalService.addVirtualLabScienceAppLink(virtualLabScienceAppLink);
	}

	/**
	* Creates a new virtual lab science app link with the primary key. Does not add the virtual lab science app link to the database.
	*
	* @param scienceAppSeqNo the primary key for the new virtual lab science app link
	* @return the new virtual lab science app link
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink createVirtualLabScienceAppLink(
		long scienceAppSeqNo) {
		return _virtualLabScienceAppLinkLocalService.createVirtualLabScienceAppLink(scienceAppSeqNo);
	}

	/**
	* Deletes the virtual lab science app link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppSeqNo the primary key of the virtual lab science app link
	* @return the virtual lab science app link that was removed
	* @throws PortalException if a virtual lab science app link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink deleteVirtualLabScienceAppLink(
		long scienceAppSeqNo)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkLocalService.deleteVirtualLabScienceAppLink(scienceAppSeqNo);
	}

	/**
	* Deletes the virtual lab science app link from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabScienceAppLink the virtual lab science app link
	* @return the virtual lab science app link that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink deleteVirtualLabScienceAppLink(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink virtualLabScienceAppLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkLocalService.deleteVirtualLabScienceAppLink(virtualLabScienceAppLink);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _virtualLabScienceAppLinkLocalService.dynamicQuery();
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
		return _virtualLabScienceAppLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _virtualLabScienceAppLinkLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _virtualLabScienceAppLinkLocalService.dynamicQuery(dynamicQuery,
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
		return _virtualLabScienceAppLinkLocalService.dynamicQueryCount(dynamicQuery);
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
		return _virtualLabScienceAppLinkLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink fetchVirtualLabScienceAppLink(
		long scienceAppSeqNo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkLocalService.fetchVirtualLabScienceAppLink(scienceAppSeqNo);
	}

	/**
	* Returns the virtual lab science app link with the primary key.
	*
	* @param scienceAppSeqNo the primary key of the virtual lab science app link
	* @return the virtual lab science app link
	* @throws PortalException if a virtual lab science app link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink getVirtualLabScienceAppLink(
		long scienceAppSeqNo)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkLocalService.getVirtualLabScienceAppLink(scienceAppSeqNo);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the virtual lab science app links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual lab science app links
	* @param end the upper bound of the range of virtual lab science app links (not inclusive)
	* @return the range of virtual lab science app links
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink> getVirtualLabScienceAppLinks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkLocalService.getVirtualLabScienceAppLinks(start,
			end);
	}

	/**
	* Returns the number of virtual lab science app links.
	*
	* @return the number of virtual lab science app links
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getVirtualLabScienceAppLinksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkLocalService.getVirtualLabScienceAppLinksCount();
	}

	/**
	* Updates the virtual lab science app link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param virtualLabScienceAppLink the virtual lab science app link
	* @return the virtual lab science app link that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink updateVirtualLabScienceAppLink(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink virtualLabScienceAppLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabScienceAppLinkLocalService.updateVirtualLabScienceAppLink(virtualLabScienceAppLink);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _virtualLabScienceAppLinkLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_virtualLabScienceAppLinkLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _virtualLabScienceAppLinkLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public VirtualLabScienceAppLinkLocalService getWrappedVirtualLabScienceAppLinkLocalService() {
		return _virtualLabScienceAppLinkLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedVirtualLabScienceAppLinkLocalService(
		VirtualLabScienceAppLinkLocalService virtualLabScienceAppLinkLocalService) {
		_virtualLabScienceAppLinkLocalService = virtualLabScienceAppLinkLocalService;
	}

	@Override
	public VirtualLabScienceAppLinkLocalService getWrappedService() {
		return _virtualLabScienceAppLinkLocalService;
	}

	@Override
	public void setWrappedService(
		VirtualLabScienceAppLinkLocalService virtualLabScienceAppLinkLocalService) {
		_virtualLabScienceAppLinkLocalService = virtualLabScienceAppLinkLocalService;
	}

	private VirtualLabScienceAppLinkLocalService _virtualLabScienceAppLinkLocalService;
}