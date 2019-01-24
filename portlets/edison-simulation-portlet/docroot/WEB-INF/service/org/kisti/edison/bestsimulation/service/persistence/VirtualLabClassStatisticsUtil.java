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

package org.kisti.edison.bestsimulation.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics;

import java.util.List;

/**
 * The persistence utility for the virtual lab class statistics service. This utility wraps {@link VirtualLabClassStatisticsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see VirtualLabClassStatisticsPersistence
 * @see VirtualLabClassStatisticsPersistenceImpl
 * @generated
 */
public class VirtualLabClassStatisticsUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(
		VirtualLabClassStatistics virtualLabClassStatistics) {
		getPersistence().clearCache(virtualLabClassStatistics);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<VirtualLabClassStatistics> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VirtualLabClassStatistics> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VirtualLabClassStatistics> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static VirtualLabClassStatistics update(
		VirtualLabClassStatistics virtualLabClassStatistics)
		throws SystemException {
		return getPersistence().update(virtualLabClassStatistics);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static VirtualLabClassStatistics update(
		VirtualLabClassStatistics virtualLabClassStatistics,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(virtualLabClassStatistics, serviceContext);
	}

	/**
	* Returns all the virtual lab class statisticses where virtualLabId = &#63; and classId = &#63;.
	*
	* @param virtualLabId the virtual lab ID
	* @param classId the class ID
	* @return the matching virtual lab class statisticses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics> findByvirtualLabIdAndClassId(
		long virtualLabId, java.lang.String classId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByvirtualLabIdAndClassId(virtualLabId, classId);
	}

	/**
	* Returns a range of all the virtual lab class statisticses where virtualLabId = &#63; and classId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param virtualLabId the virtual lab ID
	* @param classId the class ID
	* @param start the lower bound of the range of virtual lab class statisticses
	* @param end the upper bound of the range of virtual lab class statisticses (not inclusive)
	* @return the range of matching virtual lab class statisticses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics> findByvirtualLabIdAndClassId(
		long virtualLabId, java.lang.String classId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByvirtualLabIdAndClassId(virtualLabId, classId, start,
			end);
	}

	/**
	* Returns an ordered range of all the virtual lab class statisticses where virtualLabId = &#63; and classId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param virtualLabId the virtual lab ID
	* @param classId the class ID
	* @param start the lower bound of the range of virtual lab class statisticses
	* @param end the upper bound of the range of virtual lab class statisticses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching virtual lab class statisticses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics> findByvirtualLabIdAndClassId(
		long virtualLabId, java.lang.String classId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByvirtualLabIdAndClassId(virtualLabId, classId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first virtual lab class statistics in the ordered set where virtualLabId = &#63; and classId = &#63;.
	*
	* @param virtualLabId the virtual lab ID
	* @param classId the class ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching virtual lab class statistics
	* @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a matching virtual lab class statistics could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics findByvirtualLabIdAndClassId_First(
		long virtualLabId, java.lang.String classId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException {
		return getPersistence()
				   .findByvirtualLabIdAndClassId_First(virtualLabId, classId,
			orderByComparator);
	}

	/**
	* Returns the first virtual lab class statistics in the ordered set where virtualLabId = &#63; and classId = &#63;.
	*
	* @param virtualLabId the virtual lab ID
	* @param classId the class ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching virtual lab class statistics, or <code>null</code> if a matching virtual lab class statistics could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics fetchByvirtualLabIdAndClassId_First(
		long virtualLabId, java.lang.String classId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByvirtualLabIdAndClassId_First(virtualLabId, classId,
			orderByComparator);
	}

	/**
	* Returns the last virtual lab class statistics in the ordered set where virtualLabId = &#63; and classId = &#63;.
	*
	* @param virtualLabId the virtual lab ID
	* @param classId the class ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching virtual lab class statistics
	* @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a matching virtual lab class statistics could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics findByvirtualLabIdAndClassId_Last(
		long virtualLabId, java.lang.String classId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException {
		return getPersistence()
				   .findByvirtualLabIdAndClassId_Last(virtualLabId, classId,
			orderByComparator);
	}

	/**
	* Returns the last virtual lab class statistics in the ordered set where virtualLabId = &#63; and classId = &#63;.
	*
	* @param virtualLabId the virtual lab ID
	* @param classId the class ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching virtual lab class statistics, or <code>null</code> if a matching virtual lab class statistics could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics fetchByvirtualLabIdAndClassId_Last(
		long virtualLabId, java.lang.String classId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByvirtualLabIdAndClassId_Last(virtualLabId, classId,
			orderByComparator);
	}

	/**
	* Returns the virtual lab class statisticses before and after the current virtual lab class statistics in the ordered set where virtualLabId = &#63; and classId = &#63;.
	*
	* @param virtualLabClassStatisticsPK the primary key of the current virtual lab class statistics
	* @param virtualLabId the virtual lab ID
	* @param classId the class ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next virtual lab class statistics
	* @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a virtual lab class statistics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics[] findByvirtualLabIdAndClassId_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK virtualLabClassStatisticsPK,
		long virtualLabId, java.lang.String classId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException {
		return getPersistence()
				   .findByvirtualLabIdAndClassId_PrevAndNext(virtualLabClassStatisticsPK,
			virtualLabId, classId, orderByComparator);
	}

	/**
	* Removes all the virtual lab class statisticses where virtualLabId = &#63; and classId = &#63; from the database.
	*
	* @param virtualLabId the virtual lab ID
	* @param classId the class ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByvirtualLabIdAndClassId(long virtualLabId,
		java.lang.String classId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByvirtualLabIdAndClassId(virtualLabId, classId);
	}

	/**
	* Returns the number of virtual lab class statisticses where virtualLabId = &#63; and classId = &#63;.
	*
	* @param virtualLabId the virtual lab ID
	* @param classId the class ID
	* @return the number of matching virtual lab class statisticses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByvirtualLabIdAndClassId(long virtualLabId,
		java.lang.String classId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByvirtualLabIdAndClassId(virtualLabId, classId);
	}

	/**
	* Caches the virtual lab class statistics in the entity cache if it is enabled.
	*
	* @param virtualLabClassStatistics the virtual lab class statistics
	*/
	public static void cacheResult(
		org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics virtualLabClassStatistics) {
		getPersistence().cacheResult(virtualLabClassStatistics);
	}

	/**
	* Caches the virtual lab class statisticses in the entity cache if it is enabled.
	*
	* @param virtualLabClassStatisticses the virtual lab class statisticses
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics> virtualLabClassStatisticses) {
		getPersistence().cacheResult(virtualLabClassStatisticses);
	}

	/**
	* Creates a new virtual lab class statistics with the primary key. Does not add the virtual lab class statistics to the database.
	*
	* @param virtualLabClassStatisticsPK the primary key for the new virtual lab class statistics
	* @return the new virtual lab class statistics
	*/
	public static org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics create(
		org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK virtualLabClassStatisticsPK) {
		return getPersistence().create(virtualLabClassStatisticsPK);
	}

	/**
	* Removes the virtual lab class statistics with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStatisticsPK the primary key of the virtual lab class statistics
	* @return the virtual lab class statistics that was removed
	* @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a virtual lab class statistics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics remove(
		org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK virtualLabClassStatisticsPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException {
		return getPersistence().remove(virtualLabClassStatisticsPK);
	}

	public static org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics updateImpl(
		org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics virtualLabClassStatistics)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(virtualLabClassStatistics);
	}

	/**
	* Returns the virtual lab class statistics with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException} if it could not be found.
	*
	* @param virtualLabClassStatisticsPK the primary key of the virtual lab class statistics
	* @return the virtual lab class statistics
	* @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a virtual lab class statistics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics findByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK virtualLabClassStatisticsPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException {
		return getPersistence().findByPrimaryKey(virtualLabClassStatisticsPK);
	}

	/**
	* Returns the virtual lab class statistics with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param virtualLabClassStatisticsPK the primary key of the virtual lab class statistics
	* @return the virtual lab class statistics, or <code>null</code> if a virtual lab class statistics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics fetchByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK virtualLabClassStatisticsPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(virtualLabClassStatisticsPK);
	}

	/**
	* Returns all the virtual lab class statisticses.
	*
	* @return the virtual lab class statisticses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the virtual lab class statisticses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual lab class statisticses
	* @param end the upper bound of the range of virtual lab class statisticses (not inclusive)
	* @return the range of virtual lab class statisticses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the virtual lab class statisticses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual lab class statisticses
	* @param end the upper bound of the range of virtual lab class statisticses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of virtual lab class statisticses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the virtual lab class statisticses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of virtual lab class statisticses.
	*
	* @return the number of virtual lab class statisticses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static VirtualLabClassStatisticsPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (VirtualLabClassStatisticsPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.bestsimulation.service.ClpSerializer.getServletContextName(),
					VirtualLabClassStatisticsPersistence.class.getName());

			ReferenceRegistry.registerReference(VirtualLabClassStatisticsUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(VirtualLabClassStatisticsPersistence persistence) {
	}

	private static VirtualLabClassStatisticsPersistence _persistence;
}