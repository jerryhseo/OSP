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
 * Provides a wrapper for {@link VirtualLabClassStsMigrationLocalService}.
 *
 * @author EDISON
 * @see VirtualLabClassStsMigrationLocalService
 * @generated
 */
public class VirtualLabClassStsMigrationLocalServiceWrapper
	implements VirtualLabClassStsMigrationLocalService,
		ServiceWrapper<VirtualLabClassStsMigrationLocalService> {
	public VirtualLabClassStsMigrationLocalServiceWrapper(
		VirtualLabClassStsMigrationLocalService virtualLabClassStsMigrationLocalService) {
		_virtualLabClassStsMigrationLocalService = virtualLabClassStsMigrationLocalService;
	}

	/**
	* Adds the virtual lab class sts migration to the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStsMigration the virtual lab class sts migration
	* @return the virtual lab class sts migration that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration addVirtualLabClassStsMigration(
		org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration virtualLabClassStsMigration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStsMigrationLocalService.addVirtualLabClassStsMigration(virtualLabClassStsMigration);
	}

	/**
	* Creates a new virtual lab class sts migration with the primary key. Does not add the virtual lab class sts migration to the database.
	*
	* @param virtualLabClassStsMigrationPK the primary key for the new virtual lab class sts migration
	* @return the new virtual lab class sts migration
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration createVirtualLabClassStsMigration(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK) {
		return _virtualLabClassStsMigrationLocalService.createVirtualLabClassStsMigration(virtualLabClassStsMigrationPK);
	}

	/**
	* Deletes the virtual lab class sts migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStsMigrationPK the primary key of the virtual lab class sts migration
	* @return the virtual lab class sts migration that was removed
	* @throws PortalException if a virtual lab class sts migration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration deleteVirtualLabClassStsMigration(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStsMigrationLocalService.deleteVirtualLabClassStsMigration(virtualLabClassStsMigrationPK);
	}

	/**
	* Deletes the virtual lab class sts migration from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStsMigration the virtual lab class sts migration
	* @return the virtual lab class sts migration that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration deleteVirtualLabClassStsMigration(
		org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration virtualLabClassStsMigration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStsMigrationLocalService.deleteVirtualLabClassStsMigration(virtualLabClassStsMigration);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _virtualLabClassStsMigrationLocalService.dynamicQuery();
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
		return _virtualLabClassStsMigrationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabClassStsMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _virtualLabClassStsMigrationLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabClassStsMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _virtualLabClassStsMigrationLocalService.dynamicQuery(dynamicQuery,
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
		return _virtualLabClassStsMigrationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _virtualLabClassStsMigrationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration fetchVirtualLabClassStsMigration(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStsMigrationLocalService.fetchVirtualLabClassStsMigration(virtualLabClassStsMigrationPK);
	}

	/**
	* Returns the virtual lab class sts migration with the primary key.
	*
	* @param virtualLabClassStsMigrationPK the primary key of the virtual lab class sts migration
	* @return the virtual lab class sts migration
	* @throws PortalException if a virtual lab class sts migration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration getVirtualLabClassStsMigration(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStsMigrationLocalService.getVirtualLabClassStsMigration(virtualLabClassStsMigrationPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStsMigrationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the virtual lab class sts migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabClassStsMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual lab class sts migrations
	* @param end the upper bound of the range of virtual lab class sts migrations (not inclusive)
	* @return the range of virtual lab class sts migrations
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration> getVirtualLabClassStsMigrations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStsMigrationLocalService.getVirtualLabClassStsMigrations(start,
			end);
	}

	/**
	* Returns the number of virtual lab class sts migrations.
	*
	* @return the number of virtual lab class sts migrations
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getVirtualLabClassStsMigrationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStsMigrationLocalService.getVirtualLabClassStsMigrationsCount();
	}

	/**
	* Updates the virtual lab class sts migration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStsMigration the virtual lab class sts migration
	* @return the virtual lab class sts migration that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration updateVirtualLabClassStsMigration(
		org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration virtualLabClassStsMigration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _virtualLabClassStsMigrationLocalService.updateVirtualLabClassStsMigration(virtualLabClassStsMigration);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _virtualLabClassStsMigrationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_virtualLabClassStsMigrationLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _virtualLabClassStsMigrationLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public VirtualLabClassStsMigrationLocalService getWrappedVirtualLabClassStsMigrationLocalService() {
		return _virtualLabClassStsMigrationLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedVirtualLabClassStsMigrationLocalService(
		VirtualLabClassStsMigrationLocalService virtualLabClassStsMigrationLocalService) {
		_virtualLabClassStsMigrationLocalService = virtualLabClassStsMigrationLocalService;
	}

	@Override
	public VirtualLabClassStsMigrationLocalService getWrappedService() {
		return _virtualLabClassStsMigrationLocalService;
	}

	@Override
	public void setWrappedService(
		VirtualLabClassStsMigrationLocalService virtualLabClassStsMigrationLocalService) {
		_virtualLabClassStsMigrationLocalService = virtualLabClassStsMigrationLocalService;
	}

	private VirtualLabClassStsMigrationLocalService _virtualLabClassStsMigrationLocalService;
}