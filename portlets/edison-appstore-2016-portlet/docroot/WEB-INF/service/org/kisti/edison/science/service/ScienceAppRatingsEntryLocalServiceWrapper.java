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
 * Provides a wrapper for {@link ScienceAppRatingsEntryLocalService}.
 *
 * @author EDISON
 * @see ScienceAppRatingsEntryLocalService
 * @generated
 */
public class ScienceAppRatingsEntryLocalServiceWrapper
	implements ScienceAppRatingsEntryLocalService,
		ServiceWrapper<ScienceAppRatingsEntryLocalService> {
	public ScienceAppRatingsEntryLocalServiceWrapper(
		ScienceAppRatingsEntryLocalService scienceAppRatingsEntryLocalService) {
		_scienceAppRatingsEntryLocalService = scienceAppRatingsEntryLocalService;
	}

	/**
	* Adds the science app ratings entry to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppRatingsEntry the science app ratings entry
	* @return the science app ratings entry that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppRatingsEntry addScienceAppRatingsEntry(
		org.kisti.edison.science.model.ScienceAppRatingsEntry scienceAppRatingsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppRatingsEntryLocalService.addScienceAppRatingsEntry(scienceAppRatingsEntry);
	}

	/**
	* Creates a new science app ratings entry with the primary key. Does not add the science app ratings entry to the database.
	*
	* @param scienceAppRatingsEntryPK the primary key for the new science app ratings entry
	* @return the new science app ratings entry
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppRatingsEntry createScienceAppRatingsEntry(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK) {
		return _scienceAppRatingsEntryLocalService.createScienceAppRatingsEntry(scienceAppRatingsEntryPK);
	}

	/**
	* Deletes the science app ratings entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppRatingsEntryPK the primary key of the science app ratings entry
	* @return the science app ratings entry that was removed
	* @throws PortalException if a science app ratings entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppRatingsEntry deleteScienceAppRatingsEntry(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppRatingsEntryLocalService.deleteScienceAppRatingsEntry(scienceAppRatingsEntryPK);
	}

	/**
	* Deletes the science app ratings entry from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppRatingsEntry the science app ratings entry
	* @return the science app ratings entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppRatingsEntry deleteScienceAppRatingsEntry(
		org.kisti.edison.science.model.ScienceAppRatingsEntry scienceAppRatingsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppRatingsEntryLocalService.deleteScienceAppRatingsEntry(scienceAppRatingsEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _scienceAppRatingsEntryLocalService.dynamicQuery();
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
		return _scienceAppRatingsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _scienceAppRatingsEntryLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _scienceAppRatingsEntryLocalService.dynamicQuery(dynamicQuery,
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
		return _scienceAppRatingsEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _scienceAppRatingsEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppRatingsEntry fetchScienceAppRatingsEntry(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppRatingsEntryLocalService.fetchScienceAppRatingsEntry(scienceAppRatingsEntryPK);
	}

	/**
	* Returns the science app ratings entry with the primary key.
	*
	* @param scienceAppRatingsEntryPK the primary key of the science app ratings entry
	* @return the science app ratings entry
	* @throws PortalException if a science app ratings entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppRatingsEntry getScienceAppRatingsEntry(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppRatingsEntryLocalService.getScienceAppRatingsEntry(scienceAppRatingsEntryPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppRatingsEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the science app ratings entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app ratings entries
	* @param end the upper bound of the range of science app ratings entries (not inclusive)
	* @return the range of science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> getScienceAppRatingsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppRatingsEntryLocalService.getScienceAppRatingsEntries(start,
			end);
	}

	/**
	* Returns the number of science app ratings entries.
	*
	* @return the number of science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getScienceAppRatingsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppRatingsEntryLocalService.getScienceAppRatingsEntriesCount();
	}

	/**
	* Updates the science app ratings entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceAppRatingsEntry the science app ratings entry
	* @return the science app ratings entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppRatingsEntry updateScienceAppRatingsEntry(
		org.kisti.edison.science.model.ScienceAppRatingsEntry scienceAppRatingsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppRatingsEntryLocalService.updateScienceAppRatingsEntry(scienceAppRatingsEntry);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _scienceAppRatingsEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_scienceAppRatingsEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _scienceAppRatingsEntryLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ScienceAppRatingsEntryLocalService getWrappedScienceAppRatingsEntryLocalService() {
		return _scienceAppRatingsEntryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedScienceAppRatingsEntryLocalService(
		ScienceAppRatingsEntryLocalService scienceAppRatingsEntryLocalService) {
		_scienceAppRatingsEntryLocalService = scienceAppRatingsEntryLocalService;
	}

	@Override
	public ScienceAppRatingsEntryLocalService getWrappedService() {
		return _scienceAppRatingsEntryLocalService;
	}

	@Override
	public void setWrappedService(
		ScienceAppRatingsEntryLocalService scienceAppRatingsEntryLocalService) {
		_scienceAppRatingsEntryLocalService = scienceAppRatingsEntryLocalService;
	}

	private ScienceAppRatingsEntryLocalService _scienceAppRatingsEntryLocalService;
}