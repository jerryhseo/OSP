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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for UniversityExecute. This utility wraps
 * {@link org.kisti.edison.bestsimulation.service.impl.UniversityExecuteLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author EDISON
 * @see UniversityExecuteLocalService
 * @see org.kisti.edison.bestsimulation.service.base.UniversityExecuteLocalServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.impl.UniversityExecuteLocalServiceImpl
 * @generated
 */
public class UniversityExecuteLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.bestsimulation.service.impl.UniversityExecuteLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the university execute to the database. Also notifies the appropriate model listeners.
	*
	* @param universityExecute the university execute
	* @return the university execute that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.UniversityExecute addUniversityExecute(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addUniversityExecute(universityExecute);
	}

	/**
	* Creates a new university execute with the primary key. Does not add the university execute to the database.
	*
	* @param universityExecutePK the primary key for the new university execute
	* @return the new university execute
	*/
	public static org.kisti.edison.bestsimulation.model.UniversityExecute createUniversityExecute(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK) {
		return getService().createUniversityExecute(universityExecutePK);
	}

	/**
	* Deletes the university execute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param universityExecutePK the primary key of the university execute
	* @return the university execute that was removed
	* @throws PortalException if a university execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.UniversityExecute deleteUniversityExecute(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteUniversityExecute(universityExecutePK);
	}

	/**
	* Deletes the university execute from the database. Also notifies the appropriate model listeners.
	*
	* @param universityExecute the university execute
	* @return the university execute that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.UniversityExecute deleteUniversityExecute(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteUniversityExecute(universityExecute);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.kisti.edison.bestsimulation.model.UniversityExecute fetchUniversityExecute(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchUniversityExecute(universityExecutePK);
	}

	/**
	* Returns the university execute with the primary key.
	*
	* @param universityExecutePK the primary key of the university execute
	* @return the university execute
	* @throws PortalException if a university execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.UniversityExecute getUniversityExecute(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUniversityExecute(universityExecutePK);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<org.kisti.edison.bestsimulation.model.UniversityExecute> getUniversityExecutes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUniversityExecutes(start, end);
	}

	/**
	* Returns the number of university executes.
	*
	* @return the number of university executes
	* @throws SystemException if a system exception occurred
	*/
	public static int getUniversityExecutesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUniversityExecutesCount();
	}

	/**
	* Updates the university execute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param universityExecute the university execute
	* @return the university execute that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.UniversityExecute updateUniversityExecute(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateUniversityExecute(universityExecute);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsExecTableOrganigation(
		java.util.Locale locale, java.lang.String startDt,
		java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getStatisticsExecTableOrganigation(locale, startDt, endDt);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsExecBarChartDate(
		java.lang.String startDt, java.lang.String endDt)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService().getStatisticsExecBarChartDate(startDt, endDt);
	}

	public static int insertCustomUniversityExecute(long columnId,
		java.lang.String startDt, java.lang.String endDt)
		throws com.liferay.portal.NoSuchModelException,
			com.liferay.portal.kernel.exception.SystemException,
			java.lang.NumberFormatException {
		return getService()
				   .insertCustomUniversityExecute(columnId, startDt, endDt);
	}

	public static void clearService() {
		_service = null;
	}

	public static UniversityExecuteLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					UniversityExecuteLocalService.class.getName());

			if (invokableLocalService instanceof UniversityExecuteLocalService) {
				_service = (UniversityExecuteLocalService)invokableLocalService;
			}
			else {
				_service = new UniversityExecuteLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(UniversityExecuteLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(UniversityExecuteLocalService service) {
	}

	private static UniversityExecuteLocalService _service;
}