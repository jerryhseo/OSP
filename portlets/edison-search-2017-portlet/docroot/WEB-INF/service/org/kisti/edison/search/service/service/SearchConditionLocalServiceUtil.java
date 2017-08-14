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

package org.kisti.edison.search.service.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SearchCondition. This utility wraps
 * {@link org.kisti.edison.search.service.service.impl.SearchConditionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author yjpark
 * @see SearchConditionLocalService
 * @see org.kisti.edison.search.service.service.base.SearchConditionLocalServiceBaseImpl
 * @see org.kisti.edison.search.service.service.impl.SearchConditionLocalServiceImpl
 * @generated
 */
public class SearchConditionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.search.service.service.impl.SearchConditionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the search condition to the database. Also notifies the appropriate model listeners.
	*
	* @param searchCondition the search condition
	* @return the search condition that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.search.service.model.SearchCondition addSearchCondition(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSearchCondition(searchCondition);
	}

	/**
	* Creates a new search condition with the primary key. Does not add the search condition to the database.
	*
	* @param id the primary key for the new search condition
	* @return the new search condition
	*/
	public static org.kisti.edison.search.service.model.SearchCondition createSearchCondition(
		long id) {
		return getService().createSearchCondition(id);
	}

	/**
	* Deletes the search condition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the search condition
	* @return the search condition that was removed
	* @throws PortalException if a search condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.search.service.model.SearchCondition deleteSearchCondition(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSearchCondition(id);
	}

	/**
	* Deletes the search condition from the database. Also notifies the appropriate model listeners.
	*
	* @param searchCondition the search condition
	* @return the search condition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.search.service.model.SearchCondition deleteSearchCondition(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSearchCondition(searchCondition);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.kisti.edison.search.service.model.SearchCondition fetchSearchCondition(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSearchCondition(id);
	}

	/**
	* Returns the search condition with the primary key.
	*
	* @param id the primary key of the search condition
	* @return the search condition
	* @throws PortalException if a search condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.search.service.model.SearchCondition getSearchCondition(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSearchCondition(id);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the search conditions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search conditions
	* @param end the upper bound of the range of search conditions (not inclusive)
	* @return the range of search conditions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.search.service.model.SearchCondition> getSearchConditions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSearchConditions(start, end);
	}

	/**
	* Returns the number of search conditions.
	*
	* @return the number of search conditions
	* @throws SystemException if a system exception occurred
	*/
	public static int getSearchConditionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSearchConditionsCount();
	}

	/**
	* Updates the search condition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param searchCondition the search condition
	* @return the search condition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.search.service.model.SearchCondition updateSearchCondition(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSearchCondition(searchCondition);
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

	public static org.kisti.edison.search.service.model.SearchCondition createSearchCondition(
		java.util.Map<java.lang.String, java.lang.Object> modelAttribute) {
		return getService().createSearchCondition(modelAttribute);
	}

	public static org.kisti.edison.search.service.model.SearchCondition createSearchCondition(
		java.util.Map<java.lang.String, java.lang.Object> modelAttribute,
		long companyGroupId, long groupId, java.util.Locale locale) {
		return getService()
				   .createSearchCondition(modelAttribute, companyGroupId,
			groupId, locale);
	}

	public static java.lang.String createSearchParameters(
		org.kisti.edison.search.service.model.SearchCondition searchCondition,
		java.lang.String namespace, java.lang.String p_p_id) {
		return getService()
				   .createSearchParameters(searchCondition, namespace, p_p_id);
	}

	public static org.kisti.edison.search.service.model.SearchCondition createSearchCondition(
		javax.portlet.PortletRequest request)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().createSearchCondition(request);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> transformParameterToModelAttribute(
		java.util.Map<java.lang.String, java.lang.String[]> params) {
		return getService().transformParameterToModelAttribute(params);
	}

	public static void clearService() {
		_service = null;
	}

	public static SearchConditionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SearchConditionLocalService.class.getName());

			if (invokableLocalService instanceof SearchConditionLocalService) {
				_service = (SearchConditionLocalService)invokableLocalService;
			}
			else {
				_service = new SearchConditionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SearchConditionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SearchConditionLocalService service) {
	}

	private static SearchConditionLocalService _service;
}