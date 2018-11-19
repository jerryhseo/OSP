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

package org.kisti.edison.science.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.science.model.ScienceAppRatingsEntry;

import java.util.List;

/**
 * The persistence utility for the science app ratings entry service. This utility wraps {@link ScienceAppRatingsEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ScienceAppRatingsEntryPersistence
 * @see ScienceAppRatingsEntryPersistenceImpl
 * @generated
 */
public class ScienceAppRatingsEntryUtil {
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
	public static void clearCache(ScienceAppRatingsEntry scienceAppRatingsEntry) {
		getPersistence().clearCache(scienceAppRatingsEntry);
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
	public static List<ScienceAppRatingsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ScienceAppRatingsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ScienceAppRatingsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ScienceAppRatingsEntry update(
		ScienceAppRatingsEntry scienceAppRatingsEntry)
		throws SystemException {
		return getPersistence().update(scienceAppRatingsEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ScienceAppRatingsEntry update(
		ScienceAppRatingsEntry scienceAppRatingsEntry,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(scienceAppRatingsEntry, serviceContext);
	}

	/**
	* Returns all the science app ratings entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findByuserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByuserId(userId);
	}

	/**
	* Returns a range of all the science app ratings entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of science app ratings entries
	* @param end the upper bound of the range of science app ratings entries (not inclusive)
	* @return the range of matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findByuserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByuserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the science app ratings entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of science app ratings entries
	* @param end the upper bound of the range of science app ratings entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByuserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first science app ratings entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching science app ratings entry
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry findByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first science app ratings entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last science app ratings entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching science app ratings entry
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry findByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last science app ratings entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the science app ratings entries before and after the current science app ratings entry in the ordered set where userId = &#63;.
	*
	* @param scienceAppRatingsEntryPK the primary key of the current science app ratings entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next science app ratings entry
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry[] findByuserId_PrevAndNext(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence()
				   .findByuserId_PrevAndNext(scienceAppRatingsEntryPK, userId,
			orderByComparator);
	}

	/**
	* Removes all the science app ratings entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByuserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByuserId(userId);
	}

	/**
	* Returns the number of science app ratings entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByuserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByuserId(userId);
	}

	/**
	* Returns all the science app ratings entries where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @return the matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findByScienceAppId(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByScienceAppId(scienceAppId);
	}

	/**
	* Returns a range of all the science app ratings entries where scienceAppId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param start the lower bound of the range of science app ratings entries
	* @param end the upper bound of the range of science app ratings entries (not inclusive)
	* @return the range of matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findByScienceAppId(
		long scienceAppId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByScienceAppId(scienceAppId, start, end);
	}

	/**
	* Returns an ordered range of all the science app ratings entries where scienceAppId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param start the lower bound of the range of science app ratings entries
	* @param end the upper bound of the range of science app ratings entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findByScienceAppId(
		long scienceAppId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByScienceAppId(scienceAppId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first science app ratings entry in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching science app ratings entry
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry findByScienceAppId_First(
		long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence()
				   .findByScienceAppId_First(scienceAppId, orderByComparator);
	}

	/**
	* Returns the first science app ratings entry in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry fetchByScienceAppId_First(
		long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppId_First(scienceAppId, orderByComparator);
	}

	/**
	* Returns the last science app ratings entry in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching science app ratings entry
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry findByScienceAppId_Last(
		long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence()
				   .findByScienceAppId_Last(scienceAppId, orderByComparator);
	}

	/**
	* Returns the last science app ratings entry in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry fetchByScienceAppId_Last(
		long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppId_Last(scienceAppId, orderByComparator);
	}

	/**
	* Returns the science app ratings entries before and after the current science app ratings entry in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppRatingsEntryPK the primary key of the current science app ratings entry
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next science app ratings entry
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry[] findByScienceAppId_PrevAndNext(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK,
		long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence()
				   .findByScienceAppId_PrevAndNext(scienceAppRatingsEntryPK,
			scienceAppId, orderByComparator);
	}

	/**
	* Removes all the science app ratings entries where scienceAppId = &#63; from the database.
	*
	* @param scienceAppId the science app ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByScienceAppId(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByScienceAppId(scienceAppId);
	}

	/**
	* Returns the number of science app ratings entries where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @return the number of matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByScienceAppId(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByScienceAppId(scienceAppId);
	}

	/**
	* Returns all the science app ratings entries where userId = &#63; and scienceAppId = &#63;.
	*
	* @param userId the user ID
	* @param scienceAppId the science app ID
	* @return the matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findByuserIdAndScienceAppId(
		long userId, long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByuserIdAndScienceAppId(userId, scienceAppId);
	}

	/**
	* Returns a range of all the science app ratings entries where userId = &#63; and scienceAppId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param scienceAppId the science app ID
	* @param start the lower bound of the range of science app ratings entries
	* @param end the upper bound of the range of science app ratings entries (not inclusive)
	* @return the range of matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findByuserIdAndScienceAppId(
		long userId, long scienceAppId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByuserIdAndScienceAppId(userId, scienceAppId, start, end);
	}

	/**
	* Returns an ordered range of all the science app ratings entries where userId = &#63; and scienceAppId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param scienceAppId the science app ID
	* @param start the lower bound of the range of science app ratings entries
	* @param end the upper bound of the range of science app ratings entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findByuserIdAndScienceAppId(
		long userId, long scienceAppId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByuserIdAndScienceAppId(userId, scienceAppId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first science app ratings entry in the ordered set where userId = &#63; and scienceAppId = &#63;.
	*
	* @param userId the user ID
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching science app ratings entry
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry findByuserIdAndScienceAppId_First(
		long userId, long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence()
				   .findByuserIdAndScienceAppId_First(userId, scienceAppId,
			orderByComparator);
	}

	/**
	* Returns the first science app ratings entry in the ordered set where userId = &#63; and scienceAppId = &#63;.
	*
	* @param userId the user ID
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry fetchByuserIdAndScienceAppId_First(
		long userId, long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByuserIdAndScienceAppId_First(userId, scienceAppId,
			orderByComparator);
	}

	/**
	* Returns the last science app ratings entry in the ordered set where userId = &#63; and scienceAppId = &#63;.
	*
	* @param userId the user ID
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching science app ratings entry
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry findByuserIdAndScienceAppId_Last(
		long userId, long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence()
				   .findByuserIdAndScienceAppId_Last(userId, scienceAppId,
			orderByComparator);
	}

	/**
	* Returns the last science app ratings entry in the ordered set where userId = &#63; and scienceAppId = &#63;.
	*
	* @param userId the user ID
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry fetchByuserIdAndScienceAppId_Last(
		long userId, long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByuserIdAndScienceAppId_Last(userId, scienceAppId,
			orderByComparator);
	}

	/**
	* Returns the science app ratings entries before and after the current science app ratings entry in the ordered set where userId = &#63; and scienceAppId = &#63;.
	*
	* @param scienceAppRatingsEntryPK the primary key of the current science app ratings entry
	* @param userId the user ID
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next science app ratings entry
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry[] findByuserIdAndScienceAppId_PrevAndNext(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK,
		long userId, long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence()
				   .findByuserIdAndScienceAppId_PrevAndNext(scienceAppRatingsEntryPK,
			userId, scienceAppId, orderByComparator);
	}

	/**
	* Removes all the science app ratings entries where userId = &#63; and scienceAppId = &#63; from the database.
	*
	* @param userId the user ID
	* @param scienceAppId the science app ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByuserIdAndScienceAppId(long userId,
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByuserIdAndScienceAppId(userId, scienceAppId);
	}

	/**
	* Returns the number of science app ratings entries where userId = &#63; and scienceAppId = &#63;.
	*
	* @param userId the user ID
	* @param scienceAppId the science app ID
	* @return the number of matching science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByuserIdAndScienceAppId(long userId,
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByuserIdAndScienceAppId(userId, scienceAppId);
	}

	/**
	* Caches the science app ratings entry in the entity cache if it is enabled.
	*
	* @param scienceAppRatingsEntry the science app ratings entry
	*/
	public static void cacheResult(
		org.kisti.edison.science.model.ScienceAppRatingsEntry scienceAppRatingsEntry) {
		getPersistence().cacheResult(scienceAppRatingsEntry);
	}

	/**
	* Caches the science app ratings entries in the entity cache if it is enabled.
	*
	* @param scienceAppRatingsEntries the science app ratings entries
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> scienceAppRatingsEntries) {
		getPersistence().cacheResult(scienceAppRatingsEntries);
	}

	/**
	* Creates a new science app ratings entry with the primary key. Does not add the science app ratings entry to the database.
	*
	* @param scienceAppRatingsEntryPK the primary key for the new science app ratings entry
	* @return the new science app ratings entry
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry create(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK) {
		return getPersistence().create(scienceAppRatingsEntryPK);
	}

	/**
	* Removes the science app ratings entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppRatingsEntryPK the primary key of the science app ratings entry
	* @return the science app ratings entry that was removed
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry remove(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence().remove(scienceAppRatingsEntryPK);
	}

	public static org.kisti.edison.science.model.ScienceAppRatingsEntry updateImpl(
		org.kisti.edison.science.model.ScienceAppRatingsEntry scienceAppRatingsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(scienceAppRatingsEntry);
	}

	/**
	* Returns the science app ratings entry with the primary key or throws a {@link org.kisti.edison.science.NoSuchScienceAppRatingsEntryException} if it could not be found.
	*
	* @param scienceAppRatingsEntryPK the primary key of the science app ratings entry
	* @return the science app ratings entry
	* @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry findByPrimaryKey(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppRatingsEntryException {
		return getPersistence().findByPrimaryKey(scienceAppRatingsEntryPK);
	}

	/**
	* Returns the science app ratings entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scienceAppRatingsEntryPK the primary key of the science app ratings entry
	* @return the science app ratings entry, or <code>null</code> if a science app ratings entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.science.model.ScienceAppRatingsEntry fetchByPrimaryKey(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK scienceAppRatingsEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(scienceAppRatingsEntryPK);
	}

	/**
	* Returns all the science app ratings entries.
	*
	* @return the science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the science app ratings entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app ratings entries
	* @param end the upper bound of the range of science app ratings entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.science.model.ScienceAppRatingsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the science app ratings entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of science app ratings entries.
	*
	* @return the number of science app ratings entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ScienceAppRatingsEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ScienceAppRatingsEntryPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.science.service.ClpSerializer.getServletContextName(),
					ScienceAppRatingsEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(ScienceAppRatingsEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ScienceAppRatingsEntryPersistence persistence) {
	}

	private static ScienceAppRatingsEntryPersistence _persistence;
}