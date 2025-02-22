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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ScienceAppOutputPorts. This utility wraps
 * {@link org.kisti.edison.science.service.impl.ScienceAppOutputPortsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author EDISON
 * @see ScienceAppOutputPortsLocalService
 * @see org.kisti.edison.science.service.base.ScienceAppOutputPortsLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.impl.ScienceAppOutputPortsLocalServiceImpl
 * @generated
 */
public class ScienceAppOutputPortsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.science.service.impl.ScienceAppOutputPortsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the science app output ports to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppOutputPorts the science app output ports
	* @return the science app output ports that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppOutputPorts addScienceAppOutputPorts(
		org.kisti.edison.science.model.ScienceAppOutputPorts scienceAppOutputPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addScienceAppOutputPorts(scienceAppOutputPorts);
	}

	/**
	* Creates a new science app output ports with the primary key. Does not add the science app output ports to the database.
	*
	* @param scienceAppId the primary key for the new science app output ports
	* @return the new science app output ports
	*/
	public static org.kisti.edison.science.model.ScienceAppOutputPorts createScienceAppOutputPorts(
		long scienceAppId) {
		return getService().createScienceAppOutputPorts(scienceAppId);
	}

	/**
	* Deletes the science app output ports with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the science app output ports
	* @return the science app output ports that was removed
	* @throws PortalException if a science app output ports with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppOutputPorts deleteScienceAppOutputPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteScienceAppOutputPorts(scienceAppId);
	}

	/**
	* Deletes the science app output ports from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppOutputPorts the science app output ports
	* @return the science app output ports that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppOutputPorts deleteScienceAppOutputPorts(
		org.kisti.edison.science.model.ScienceAppOutputPorts scienceAppOutputPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteScienceAppOutputPorts(scienceAppOutputPorts);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppOutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppOutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.kisti.edison.science.model.ScienceAppOutputPorts fetchScienceAppOutputPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchScienceAppOutputPorts(scienceAppId);
	}

	/**
	* Returns the science app output ports with the primary key.
	*
	* @param scienceAppId the primary key of the science app output ports
	* @return the science app output ports
	* @throws PortalException if a science app output ports with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppOutputPorts getScienceAppOutputPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppOutputPorts(scienceAppId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the science app output portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppOutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app output portses
	* @param end the upper bound of the range of science app output portses (not inclusive)
	* @return the range of science app output portses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppOutputPorts> getScienceAppOutputPortses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppOutputPortses(start, end);
	}

	/**
	* Returns the number of science app output portses.
	*
	* @return the number of science app output portses
	* @throws SystemException if a system exception occurred
	*/
	public static int getScienceAppOutputPortsesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppOutputPortsesCount();
	}

	/**
	* Updates the science app output ports in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceAppOutputPorts the science app output ports
	* @return the science app output ports that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppOutputPorts updateScienceAppOutputPorts(
		org.kisti.edison.science.model.ScienceAppOutputPorts scienceAppOutputPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateScienceAppOutputPorts(scienceAppOutputPorts);
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

	public static org.kisti.edison.science.model.ScienceAppOutputPorts create(
		long scienceAppId, java.lang.String outputPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().create(scienceAppId, outputPorts);
	}

	public static java.lang.String getOutputPortsJsonString(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOutputPortsJsonString(scienceAppId);
	}

	public static void removeAllOutputPorts()
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().removeAllOutputPorts();
	}

	/**
	* ADD GPLUS SERVICE
	*/
	public static long getScienceAppOutputPortsesCount(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getScienceAppOutputPortsesCount(scienceAppId);
	}

	public static java.lang.Boolean isScienceAppOutPortsValus(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().isScienceAppOutPortsValus(scienceAppId);
	}

	public static long getOutPortsCountByPotyTypeId(long portTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOutPortsCountByPotyTypeId(portTypeId);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> portAppList(
		long scienceAppId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().portAppList(scienceAppId, locale);
	}

	public static void clearService() {
		_service = null;
	}

	public static ScienceAppOutputPortsLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ScienceAppOutputPortsLocalService.class.getName());

			if (invokableLocalService instanceof ScienceAppOutputPortsLocalService) {
				_service = (ScienceAppOutputPortsLocalService)invokableLocalService;
			}
			else {
				_service = new ScienceAppOutputPortsLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ScienceAppOutputPortsLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ScienceAppOutputPortsLocalService service) {
	}

	private static ScienceAppOutputPortsLocalService _service;
}