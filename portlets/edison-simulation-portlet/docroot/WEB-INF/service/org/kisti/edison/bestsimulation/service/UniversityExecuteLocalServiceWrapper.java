/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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
 * Provides a wrapper for {@link UniversityExecuteLocalService}.
 *
 * @author EDISON
 * @see UniversityExecuteLocalService
 * @generated
 */
public class UniversityExecuteLocalServiceWrapper
	implements UniversityExecuteLocalService,
		ServiceWrapper<UniversityExecuteLocalService> {
	public UniversityExecuteLocalServiceWrapper(
		UniversityExecuteLocalService universityExecuteLocalService) {
		_universityExecuteLocalService = universityExecuteLocalService;
	}

	/**
	* Adds the university execute to the database. Also notifies the appropriate model listeners.
	*
	* @param universityExecute the university execute
	* @return the university execute that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.UniversityExecute addUniversityExecute(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _universityExecuteLocalService.addUniversityExecute(universityExecute);
	}

	/**
	* Creates a new university execute with the primary key. Does not add the university execute to the database.
	*
	* @param universityExecutePK the primary key for the new university execute
	* @return the new university execute
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.UniversityExecute createUniversityExecute(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK) {
		return _universityExecuteLocalService.createUniversityExecute(universityExecutePK);
	}

	/**
	* Deletes the university execute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param universityExecutePK the primary key of the university execute
	* @return the university execute that was removed
	* @throws PortalException if a university execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.UniversityExecute deleteUniversityExecute(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _universityExecuteLocalService.deleteUniversityExecute(universityExecutePK);
	}

	/**
	* Deletes the university execute from the database. Also notifies the appropriate model listeners.
	*
	* @param universityExecute the university execute
	* @return the university execute that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.UniversityExecute deleteUniversityExecute(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _universityExecuteLocalService.deleteUniversityExecute(universityExecute);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _universityExecuteLocalService.dynamicQuery();
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
		return _universityExecuteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.UniversityExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _universityExecuteLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.UniversityExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _universityExecuteLocalService.dynamicQuery(dynamicQuery, start,
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
		return _universityExecuteLocalService.dynamicQueryCount(dynamicQuery);
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
		return _universityExecuteLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.UniversityExecute fetchUniversityExecute(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _universityExecuteLocalService.fetchUniversityExecute(universityExecutePK);
	}

	/**
	* Returns the university execute with the primary key.
	*
	* @param universityExecutePK the primary key of the university execute
	* @return the university execute
	* @throws PortalException if a university execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.UniversityExecute getUniversityExecute(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _universityExecuteLocalService.getUniversityExecute(universityExecutePK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _universityExecuteLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the university executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.UniversityExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of university executes
	* @param end the upper bound of the range of university executes (not inclusive)
	* @return the range of university executes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.UniversityExecute> getUniversityExecutes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _universityExecuteLocalService.getUniversityExecutes(start, end);
	}

	/**
	* Returns the number of university executes.
	*
	* @return the number of university executes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getUniversityExecutesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _universityExecuteLocalService.getUniversityExecutesCount();
	}

	/**
	* Updates the university execute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param universityExecute the university execute
	* @return the university execute that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.UniversityExecute updateUniversityExecute(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _universityExecuteLocalService.updateUniversityExecute(universityExecute);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _universityExecuteLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_universityExecuteLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _universityExecuteLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsExecTableOrganigation(
		java.util.Locale locale, java.lang.String startDt,
		java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _universityExecuteLocalService.getStatisticsExecTableOrganigation(locale,
			startDt, endDt);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsExecBarChartDate(
		java.lang.String startDt, java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return _universityExecuteLocalService.getStatisticsExecBarChartDate(startDt,
			endDt);
	}

	@Override
	public int insertCustomUniversityExecute(long columnId,
		java.lang.String startDt, java.lang.String endDt)
		throws com.liferay.portal.NoSuchModelException,
			com.liferay.portal.kernel.exception.SystemException,
			java.lang.NumberFormatException {
		return _universityExecuteLocalService.insertCustomUniversityExecute(columnId,
			startDt, endDt);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public UniversityExecuteLocalService getWrappedUniversityExecuteLocalService() {
		return _universityExecuteLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedUniversityExecuteLocalService(
		UniversityExecuteLocalService universityExecuteLocalService) {
		_universityExecuteLocalService = universityExecuteLocalService;
	}

	@Override
	public UniversityExecuteLocalService getWrappedService() {
		return _universityExecuteLocalService;
	}

	@Override
	public void setWrappedService(
		UniversityExecuteLocalService universityExecuteLocalService) {
		_universityExecuteLocalService = universityExecuteLocalService;
	}

	private UniversityExecuteLocalService _universityExecuteLocalService;
}