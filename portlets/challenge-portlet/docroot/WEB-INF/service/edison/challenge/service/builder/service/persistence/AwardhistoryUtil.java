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

import edison.challenge.service.builder.model.Awardhistory;

import java.util.List;

/**
 * The persistence utility for the awardhistory service. This utility wraps {@link AwardhistoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see AwardhistoryPersistence
 * @see AwardhistoryPersistenceImpl
 * @generated
 */
public class AwardhistoryUtil {
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
	public static void clearCache(Awardhistory awardhistory) {
		getPersistence().clearCache(awardhistory);
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
	public static List<Awardhistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Awardhistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Awardhistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Awardhistory update(Awardhistory awardhistory)
		throws SystemException {
		return getPersistence().update(awardhistory);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Awardhistory update(Awardhistory awardhistory,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(awardhistory, serviceContext);
	}

	/**
	* Returns all the awardhistories where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @return the matching awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Awardhistory> findByteamCollect(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByteamCollect(chTeamid);
	}

	/**
	* Returns a range of all the awardhistories where chTeamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardhistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param chTeamid the ch teamid
	* @param start the lower bound of the range of awardhistories
	* @param end the upper bound of the range of awardhistories (not inclusive)
	* @return the range of matching awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Awardhistory> findByteamCollect(
		long chTeamid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByteamCollect(chTeamid, start, end);
	}

	/**
	* Returns an ordered range of all the awardhistories where chTeamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardhistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param chTeamid the ch teamid
	* @param start the lower bound of the range of awardhistories
	* @param end the upper bound of the range of awardhistories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Awardhistory> findByteamCollect(
		long chTeamid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByteamCollect(chTeamid, start, end, orderByComparator);
	}

	/**
	* Returns the first awardhistory in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching awardhistory
	* @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a matching awardhistory could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Awardhistory findByteamCollect_First(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAwardhistoryException {
		return getPersistence()
				   .findByteamCollect_First(chTeamid, orderByComparator);
	}

	/**
	* Returns the first awardhistory in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching awardhistory, or <code>null</code> if a matching awardhistory could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Awardhistory fetchByteamCollect_First(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByteamCollect_First(chTeamid, orderByComparator);
	}

	/**
	* Returns the last awardhistory in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching awardhistory
	* @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a matching awardhistory could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Awardhistory findByteamCollect_Last(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAwardhistoryException {
		return getPersistence()
				   .findByteamCollect_Last(chTeamid, orderByComparator);
	}

	/**
	* Returns the last awardhistory in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching awardhistory, or <code>null</code> if a matching awardhistory could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Awardhistory fetchByteamCollect_Last(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByteamCollect_Last(chTeamid, orderByComparator);
	}

	/**
	* Returns the awardhistories before and after the current awardhistory in the ordered set where chTeamid = &#63;.
	*
	* @param awardhistoryid the primary key of the current awardhistory
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next awardhistory
	* @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a awardhistory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Awardhistory[] findByteamCollect_PrevAndNext(
		long awardhistoryid, long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAwardhistoryException {
		return getPersistence()
				   .findByteamCollect_PrevAndNext(awardhistoryid, chTeamid,
			orderByComparator);
	}

	/**
	* Removes all the awardhistories where chTeamid = &#63; from the database.
	*
	* @param chTeamid the ch teamid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByteamCollect(long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByteamCollect(chTeamid);
	}

	/**
	* Returns the number of awardhistories where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @return the number of matching awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public static int countByteamCollect(long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByteamCollect(chTeamid);
	}

	/**
	* Caches the awardhistory in the entity cache if it is enabled.
	*
	* @param awardhistory the awardhistory
	*/
	public static void cacheResult(
		edison.challenge.service.builder.model.Awardhistory awardhistory) {
		getPersistence().cacheResult(awardhistory);
	}

	/**
	* Caches the awardhistories in the entity cache if it is enabled.
	*
	* @param awardhistories the awardhistories
	*/
	public static void cacheResult(
		java.util.List<edison.challenge.service.builder.model.Awardhistory> awardhistories) {
		getPersistence().cacheResult(awardhistories);
	}

	/**
	* Creates a new awardhistory with the primary key. Does not add the awardhistory to the database.
	*
	* @param awardhistoryid the primary key for the new awardhistory
	* @return the new awardhistory
	*/
	public static edison.challenge.service.builder.model.Awardhistory create(
		long awardhistoryid) {
		return getPersistence().create(awardhistoryid);
	}

	/**
	* Removes the awardhistory with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param awardhistoryid the primary key of the awardhistory
	* @return the awardhistory that was removed
	* @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a awardhistory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Awardhistory remove(
		long awardhistoryid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAwardhistoryException {
		return getPersistence().remove(awardhistoryid);
	}

	public static edison.challenge.service.builder.model.Awardhistory updateImpl(
		edison.challenge.service.builder.model.Awardhistory awardhistory)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(awardhistory);
	}

	/**
	* Returns the awardhistory with the primary key or throws a {@link edison.challenge.service.builder.NoSuchAwardhistoryException} if it could not be found.
	*
	* @param awardhistoryid the primary key of the awardhistory
	* @return the awardhistory
	* @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a awardhistory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Awardhistory findByPrimaryKey(
		long awardhistoryid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAwardhistoryException {
		return getPersistence().findByPrimaryKey(awardhistoryid);
	}

	/**
	* Returns the awardhistory with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param awardhistoryid the primary key of the awardhistory
	* @return the awardhistory, or <code>null</code> if a awardhistory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.Awardhistory fetchByPrimaryKey(
		long awardhistoryid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(awardhistoryid);
	}

	/**
	* Returns all the awardhistories.
	*
	* @return the awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Awardhistory> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the awardhistories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardhistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of awardhistories
	* @param end the upper bound of the range of awardhistories (not inclusive)
	* @return the range of awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Awardhistory> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the awardhistories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardhistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of awardhistories
	* @param end the upper bound of the range of awardhistories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.Awardhistory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the awardhistories from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of awardhistories.
	*
	* @return the number of awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AwardhistoryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AwardhistoryPersistence)PortletBeanLocatorUtil.locate(edison.challenge.service.builder.service.ClpSerializer.getServletContextName(),
					AwardhistoryPersistence.class.getName());

			ReferenceRegistry.registerReference(AwardhistoryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(AwardhistoryPersistence persistence) {
	}

	private static AwardhistoryPersistence _persistence;
}