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

package edison.challenge.service.builder.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import edison.challenge.service.builder.model.Agency;

import java.util.List;

/**
 * The persistence utility for the agency service. This utility wraps {@link AgencyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see AgencyPersistence
 * @see AgencyPersistenceImpl
 * @generated
 */
public class AgencyUtil {
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
	public static void clearCache(Agency agency) {
		getPersistence().clearCache(agency);
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
	public static List<Agency> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Agency> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Agency> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Agency update(Agency agency) throws SystemException {
		return getPersistence().update(agency);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Agency update(Agency agency, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(agency, serviceContext);
	}

	/**
	* Returns all the agencies where childid = &#63;.
	*
	* @param childid the childid
	* @return the matching agencies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Agency> findBychildCollect(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBychildCollect(childid);
	}

	/**
	* Returns a range of all the agencies where childid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param childid the childid
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @return the range of matching agencies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Agency> findBychildCollect(
		long childid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBychildCollect(childid, start, end);
	}

	/**
	* Returns an ordered range of all the agencies where childid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param childid the childid
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching agencies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Agency> findBychildCollect(
		long childid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBychildCollect(childid, start, end, orderByComparator);
	}

	/**
	* Returns the first agency in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agency
	* @throws edison.challenge.service.builder.NoSuchAgencyException if a matching agency could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Agency findBychildCollect_First(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAgencyException {
		return getPersistence()
				   .findBychildCollect_First(childid, orderByComparator);
	}

	/**
	* Returns the first agency in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agency, or <code>null</code> if a matching agency could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Agency fetchBychildCollect_First(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBychildCollect_First(childid, orderByComparator);
	}

	/**
	* Returns the last agency in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agency
	* @throws edison.challenge.service.builder.NoSuchAgencyException if a matching agency could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Agency findBychildCollect_Last(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAgencyException {
		return getPersistence()
				   .findBychildCollect_Last(childid, orderByComparator);
	}

	/**
	* Returns the last agency in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agency, or <code>null</code> if a matching agency could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Agency fetchBychildCollect_Last(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBychildCollect_Last(childid, orderByComparator);
	}

	/**
	* Returns the agencies before and after the current agency in the ordered set where childid = &#63;.
	*
	* @param agencyid the primary key of the current agency
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agency
	* @throws edison.challenge.service.builder.NoSuchAgencyException if a agency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Agency[] findBychildCollect_PrevAndNext(
		long agencyid, long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAgencyException {
		return getPersistence()
				   .findBychildCollect_PrevAndNext(agencyid, childid,
			orderByComparator);
	}

	/**
	* Removes all the agencies where childid = &#63; from the database.
	*
	* @param childid the childid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBychildCollect(long childid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBychildCollect(childid);
	}

	/**
	* Returns the number of agencies where childid = &#63;.
	*
	* @param childid the childid
	* @return the number of matching agencies
	* @throws SystemException if a system exception occurred
	*/
	public static int countBychildCollect(long childid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBychildCollect(childid);
	}

	/**
	* Caches the agency in the entity cache if it is enabled.
	*
	* @param agency the agency
	*/
	public static void cacheResult(
		edison.challenge.service.builder.model.Agency agency) {
		getPersistence().cacheResult(agency);
	}

	/**
	* Caches the agencies in the entity cache if it is enabled.
	*
	* @param agencies the agencies
	*/
	public static void cacheResult(
		java.util.List<edison.challenge.service.builder.model.Agency> agencies) {
		getPersistence().cacheResult(agencies);
	}

	/**
	* Creates a new agency with the primary key. Does not add the agency to the database.
	*
	* @param agencyid the primary key for the new agency
	* @return the new agency
	*/
	public static edison.challenge.service.builder.model.Agency create(
		long agencyid) {
		return getPersistence().create(agencyid);
	}

	/**
	* Removes the agency with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param agencyid the primary key of the agency
	* @return the agency that was removed
	* @throws edison.challenge.service.builder.NoSuchAgencyException if a agency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Agency remove(
		long agencyid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAgencyException {
		return getPersistence().remove(agencyid);
	}

	public static edison.challenge.service.builder.model.Agency updateImpl(
		edison.challenge.service.builder.model.Agency agency)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(agency);
	}

	/**
	* Returns the agency with the primary key or throws a {@link edison.challenge.service.builder.NoSuchAgencyException} if it could not be found.
	*
	* @param agencyid the primary key of the agency
	* @return the agency
	* @throws edison.challenge.service.builder.NoSuchAgencyException if a agency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Agency findByPrimaryKey(
		long agencyid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAgencyException {
		return getPersistence().findByPrimaryKey(agencyid);
	}

	/**
	* Returns the agency with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param agencyid the primary key of the agency
	* @return the agency, or <code>null</code> if a agency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Agency fetchByPrimaryKey(
		long agencyid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(agencyid);
	}

	/**
	* Returns all the agencies.
	*
	* @return the agencies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Agency> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the agencies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @return the range of agencies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Agency> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the agencies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of agencies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Agency> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the agencies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of agencies.
	*
	* @return the number of agencies
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AgencyPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AgencyPersistence)PortletBeanLocatorUtil.locate(edison.challenge.service.builder.service.ClpSerializer.getServletContextName(),
					AgencyPersistence.class.getName());

			ReferenceRegistry.registerReference(AgencyUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(AgencyPersistence persistence) {
	}

	private static AgencyPersistence _persistence;
}