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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ScienceAppExecute. This utility wraps
 * {@link org.kisti.edison.bestsimulation.service.impl.ScienceAppExecuteLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author EDISON
 * @see ScienceAppExecuteLocalService
 * @see org.kisti.edison.bestsimulation.service.base.ScienceAppExecuteLocalServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.impl.ScienceAppExecuteLocalServiceImpl
 * @generated
 */
public class ScienceAppExecuteLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.bestsimulation.service.impl.ScienceAppExecuteLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the science app execute to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppExecute the science app execute
	* @return the science app execute that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute addScienceAppExecute(
		org.kisti.edison.bestsimulation.model.ScienceAppExecute scienceAppExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addScienceAppExecute(scienceAppExecute);
	}

	/**
	* Creates a new science app execute with the primary key. Does not add the science app execute to the database.
	*
	* @param scienceAppExecutePK the primary key for the new science app execute
	* @return the new science app execute
	*/
	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute createScienceAppExecute(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK) {
		return getService().createScienceAppExecute(scienceAppExecutePK);
	}

	/**
	* Deletes the science app execute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppExecutePK the primary key of the science app execute
	* @return the science app execute that was removed
	* @throws PortalException if a science app execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute deleteScienceAppExecute(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteScienceAppExecute(scienceAppExecutePK);
	}

	/**
	* Deletes the science app execute from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppExecute the science app execute
	* @return the science app execute that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute deleteScienceAppExecute(
		org.kisti.edison.bestsimulation.model.ScienceAppExecute scienceAppExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteScienceAppExecute(scienceAppExecute);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute fetchScienceAppExecute(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchScienceAppExecute(scienceAppExecutePK);
	}

	/**
	* Returns the science app execute with the primary key.
	*
	* @param scienceAppExecutePK the primary key of the science app execute
	* @return the science app execute
	* @throws PortalException if a science app execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute getScienceAppExecute(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppExecute(scienceAppExecutePK);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the science app executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app executes
	* @param end the upper bound of the range of science app executes (not inclusive)
	* @return the range of science app executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.ScienceAppExecute> getScienceAppExecutes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppExecutes(start, end);
	}

	/**
	* Returns the number of science app executes.
	*
	* @return the number of science app executes
	* @throws SystemException if a system exception occurred
	*/
	public static int getScienceAppExecutesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppExecutesCount();
	}

	/**
	* Updates the science app execute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceAppExecute the science app execute
	* @return the science app execute that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute updateScienceAppExecute(
		org.kisti.edison.bestsimulation.model.ScienceAppExecute scienceAppExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateScienceAppExecute(scienceAppExecute);
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

	public static java.util.List<java.lang.Long> getSiteCategoryIdList(
		long globalGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSiteCategoryIdList(globalGroupId, groupId);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsSwExeTableOrganigation(
		long companyGroupId, long groupId, java.util.Locale locale,
		long columnId, java.lang.String startDt, java.lang.String endDt,
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> scienceAppList,
		boolean categorySearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getStatisticsSwExeTableOrganigation(companyGroupId,
			groupId, locale, columnId, startDt, endDt, scienceAppList,
			categorySearch);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getStatisticsSwExeBarChartDate(
		long companyGroupId, long groupId, long columnId,
		java.lang.String startDt, java.lang.String endDt,
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> scienceAppList,
		boolean categorySearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.text.ParseException {
		return getService()
				   .getStatisticsSwExeBarChartDate(companyGroupId, groupId,
			columnId, startDt, endDt, scienceAppList, categorySearch);
	}

	public static int insertCustomScienceAppExecute(java.lang.String startDt,
		java.lang.String endDt)
		throws com.liferay.portal.NoSuchModelException,
			com.liferay.portal.kernel.exception.SystemException,
			java.lang.NumberFormatException {
		return getService().insertCustomScienceAppExecute(startDt, endDt);
	}

	public static void clearService() {
		_service = null;
	}

	public static ScienceAppExecuteLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ScienceAppExecuteLocalService.class.getName());

			if (invokableLocalService instanceof ScienceAppExecuteLocalService) {
				_service = (ScienceAppExecuteLocalService)invokableLocalService;
			}
			else {
				_service = new ScienceAppExecuteLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ScienceAppExecuteLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ScienceAppExecuteLocalService service) {
	}

	private static ScienceAppExecuteLocalService _service;
}