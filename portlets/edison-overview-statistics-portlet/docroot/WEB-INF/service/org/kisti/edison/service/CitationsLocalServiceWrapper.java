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

package org.kisti.edison.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CitationsLocalService}.
 *
 * @author edison
 * @see CitationsLocalService
 * @generated
 */
public class CitationsLocalServiceWrapper implements CitationsLocalService,
	ServiceWrapper<CitationsLocalService> {
	public CitationsLocalServiceWrapper(
		CitationsLocalService citationsLocalService) {
		_citationsLocalService = citationsLocalService;
	}

	/**
	* Adds the citations to the database. Also notifies the appropriate model listeners.
	*
	* @param citations the citations
	* @return the citations that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.Citations addCitations(
		org.kisti.edison.model.Citations citations)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _citationsLocalService.addCitations(citations);
	}

	/**
	* Creates a new citations with the primary key. Does not add the citations to the database.
	*
	* @param createDate the primary key for the new citations
	* @return the new citations
	*/
	@Override
	public org.kisti.edison.model.Citations createCitations(
		java.lang.String createDate) {
		return _citationsLocalService.createCitations(createDate);
	}

	/**
	* Deletes the citations with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param createDate the primary key of the citations
	* @return the citations that was removed
	* @throws PortalException if a citations with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.Citations deleteCitations(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _citationsLocalService.deleteCitations(createDate);
	}

	/**
	* Deletes the citations from the database. Also notifies the appropriate model listeners.
	*
	* @param citations the citations
	* @return the citations that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.Citations deleteCitations(
		org.kisti.edison.model.Citations citations)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _citationsLocalService.deleteCitations(citations);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _citationsLocalService.dynamicQuery();
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
		return _citationsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.CitationsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _citationsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.CitationsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _citationsLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _citationsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _citationsLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.kisti.edison.model.Citations fetchCitations(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _citationsLocalService.fetchCitations(createDate);
	}

	/**
	* Returns the citations with the primary key.
	*
	* @param createDate the primary key of the citations
	* @return the citations
	* @throws PortalException if a citations with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.Citations getCitations(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _citationsLocalService.getCitations(createDate);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _citationsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the citationses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.CitationsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of citationses
	* @param end the upper bound of the range of citationses (not inclusive)
	* @return the range of citationses
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.model.Citations> getCitationses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _citationsLocalService.getCitationses(start, end);
	}

	/**
	* Returns the number of citationses.
	*
	* @return the number of citationses
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getCitationsesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _citationsLocalService.getCitationsesCount();
	}

	/**
	* Updates the citations in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param citations the citations
	* @return the citations that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.model.Citations updateCitations(
		org.kisti.edison.model.Citations citations)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _citationsLocalService.updateCitations(citations);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _citationsLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_citationsLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _citationsLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public boolean updateCitationsStatistics() {
		return _citationsLocalService.updateCitationsStatistics();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public CitationsLocalService getWrappedCitationsLocalService() {
		return _citationsLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedCitationsLocalService(
		CitationsLocalService citationsLocalService) {
		_citationsLocalService = citationsLocalService;
	}

	@Override
	public CitationsLocalService getWrappedService() {
		return _citationsLocalService;
	}

	@Override
	public void setWrappedService(CitationsLocalService citationsLocalService) {
		_citationsLocalService = citationsLocalService;
	}

	private CitationsLocalService _citationsLocalService;
}