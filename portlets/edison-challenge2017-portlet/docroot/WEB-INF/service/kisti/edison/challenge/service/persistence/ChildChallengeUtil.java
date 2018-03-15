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

package kisti.edison.challenge.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import kisti.edison.challenge.model.ChildChallenge;

import java.util.List;

/**
 * The persistence utility for the child challenge service. This utility wraps {@link ChildChallengePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChildChallengePersistence
 * @see ChildChallengePersistenceImpl
 * @generated
 */
public class ChildChallengeUtil {
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
	public static void clearCache(ChildChallenge childChallenge) {
		getPersistence().clearCache(childChallenge);
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
	public static List<ChildChallenge> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChildChallenge> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChildChallenge> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ChildChallenge update(ChildChallenge childChallenge)
		throws SystemException {
		return getPersistence().update(childChallenge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ChildChallenge update(ChildChallenge childChallenge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(childChallenge, serviceContext);
	}

	/**
	* Returns all the child challenges where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the child challenges where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set where uuid = &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] findByUuid_PrevAndNext(
		long childChallengeId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(childChallengeId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the child challenges where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of child challenges where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the child challenge where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChildChallengeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the child challenge where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the child challenge where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the child challenge where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the child challenge that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of child challenges where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the child challenges where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the child challenges where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] findByUuid_C_PrevAndNext(
		long childChallengeId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(childChallengeId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the child challenges where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of child challenges where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the child challenges where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGourpId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGourpId(groupId);
	}

	/**
	* Returns a range of all the child challenges where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGourpId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGourpId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGourpId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGourpId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGourpId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence().findByGourpId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGourpId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGourpId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGourpId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence().findByGourpId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGourpId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGourpId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] findByGourpId_PrevAndNext(
		long childChallengeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGourpId_PrevAndNext(childChallengeId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGourpId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGourpId(groupId);
	}

	/**
	* Returns a range of all the child challenges that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGourpId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGourpId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGourpId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGourpId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] filterFindByGourpId_PrevAndNext(
		long childChallengeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .filterFindByGourpId_PrevAndNext(childChallengeId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the child challenges where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGourpId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGourpId(groupId);
	}

	/**
	* Returns the number of child challenges where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGourpId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGourpId(groupId);
	}

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGourpId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGourpId(groupId);
	}

	/**
	* Returns all the child challenges where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupAndChallengeStatus(groupId, challengeStatus);
	}

	/**
	* Returns a range of all the child challenges where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupAndChallengeStatus(groupId, challengeStatus,
			start, end);
	}

	/**
	* Returns an ordered range of all the child challenges where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupAndChallengeStatus(groupId, challengeStatus,
			start, end, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGroupAndChallengeStatus_First(
		long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupAndChallengeStatus_First(groupId,
			challengeStatus, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGroupAndChallengeStatus_First(
		long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupAndChallengeStatus_First(groupId,
			challengeStatus, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGroupAndChallengeStatus_Last(
		long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupAndChallengeStatus_Last(groupId,
			challengeStatus, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGroupAndChallengeStatus_Last(
		long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupAndChallengeStatus_Last(groupId,
			challengeStatus, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] findByGroupAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupAndChallengeStatus_PrevAndNext(childChallengeId,
			groupId, challengeStatus, orderByComparator);
	}

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupAndChallengeStatus(groupId, challengeStatus);
	}

	/**
	* Returns a range of all the child challenges that the user has permission to view where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupAndChallengeStatus(groupId,
			challengeStatus, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupAndChallengeStatus(groupId,
			challengeStatus, start, end, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] filterFindByGroupAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .filterFindByGroupAndChallengeStatus_PrevAndNext(childChallengeId,
			groupId, challengeStatus, orderByComparator);
	}

	/**
	* Removes all the child challenges where groupId = &#63; and challengeStatus LIKE &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupAndChallengeStatus(long groupId,
		java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByGroupAndChallengeStatus(groupId, challengeStatus);
	}

	/**
	* Returns the number of child challenges where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupAndChallengeStatus(long groupId,
		java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupAndChallengeStatus(groupId, challengeStatus);
	}

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupAndChallengeStatus(long groupId,
		java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByGroupAndChallengeStatus(groupId,
			challengeStatus);
	}

	/**
	* Returns all the child challenges where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndStatus(groupId, challengeId,
			status);
	}

	/**
	* Returns a range of all the child challenges where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndStatus(groupId, challengeId,
			status, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndStatus(groupId, challengeId,
			status, start, end, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallengeAndStatus_First(
		long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndStatus_First(groupId,
			challengeId, status, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallengeAndStatus_First(
		long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeAndStatus_First(groupId,
			challengeId, status, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallengeAndStatus_Last(
		long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndStatus_Last(groupId,
			challengeId, status, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallengeAndStatus_Last(
		long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeAndStatus_Last(groupId,
			challengeId, status, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] findByGroupIdAndChallengeAndStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndStatus_PrevAndNext(childChallengeId,
			groupId, challengeId, status, orderByComparator);
	}

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeAndStatus(groupId,
			challengeId, status);
	}

	/**
	* Returns a range of all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeAndStatus(groupId,
			challengeId, status, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeAndStatus(groupId,
			challengeId, status, start, end, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] filterFindByGroupIdAndChallengeAndStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeAndStatus_PrevAndNext(childChallengeId,
			groupId, challengeId, status, orderByComparator);
	}

	/**
	* Removes all the child challenges where groupId = &#63; and challengeId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupIdAndChallengeAndStatus(long groupId,
		long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByGroupIdAndChallengeAndStatus(groupId, challengeId, status);
	}

	/**
	* Returns the number of child challenges where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndChallengeAndStatus(long groupId,
		long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndChallengeAndStatus(groupId, challengeId,
			status);
	}

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupIdAndChallengeAndStatus(long groupId,
		long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByGroupIdAndChallengeAndStatus(groupId,
			challengeId, status);
	}

	/**
	* Returns all the child challenges where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndNumber(
		long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupIdAndNumber(groupId, number);
	}

	/**
	* Returns a range of all the child challenges where groupId = &#63; and number = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param number the number
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndNumber(
		long groupId, int number, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndNumber(groupId, number, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges where groupId = &#63; and number = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param number the number
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndNumber(
		long groupId, int number, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndNumber(groupId, number, start, end,
			orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGroupIdAndNumber_First(
		long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndNumber_First(groupId, number,
			orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndNumber_First(
		long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndNumber_First(groupId, number,
			orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGroupIdAndNumber_Last(
		long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndNumber_Last(groupId, number,
			orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndNumber_Last(
		long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndNumber_Last(groupId, number,
			orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63; and number = &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param number the number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] findByGroupIdAndNumber_PrevAndNext(
		long childChallengeId, long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndNumber_PrevAndNext(childChallengeId,
			groupId, number, orderByComparator);
	}

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndNumber(
		long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupIdAndNumber(groupId, number);
	}

	/**
	* Returns a range of all the child challenges that the user has permission to view where groupId = &#63; and number = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param number the number
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndNumber(
		long groupId, int number, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndNumber(groupId, number, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63; and number = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param number the number
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndNumber(
		long groupId, int number, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndNumber(groupId, number, start, end,
			orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63; and number = &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param number the number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] filterFindByGroupIdAndNumber_PrevAndNext(
		long childChallengeId, long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .filterFindByGroupIdAndNumber_PrevAndNext(childChallengeId,
			groupId, number, orderByComparator);
	}

	/**
	* Removes all the child challenges where groupId = &#63; and number = &#63; from the database.
	*
	* @param groupId the group ID
	* @param number the number
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupIdAndNumber(long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupIdAndNumber(groupId, number);
	}

	/**
	* Returns the number of child challenges where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndNumber(long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupIdAndNumber(groupId, number);
	}

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupIdAndNumber(long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupIdAndNumber(groupId, number);
	}

	/**
	* Returns all the child challenges where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallenge(
		long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupIdAndChallenge(groupId, challengeId);
	}

	/**
	* Returns a range of all the child challenges where groupId = &#63; and challengeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallenge(
		long groupId, long challengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallenge(groupId, challengeId, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges where groupId = &#63; and challengeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallenge(
		long groupId, long challengeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallenge(groupId, challengeId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallenge_First(
		long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndChallenge_First(groupId, challengeId,
			orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallenge_First(
		long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallenge_First(groupId, challengeId,
			orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallenge_Last(
		long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndChallenge_Last(groupId, challengeId,
			orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallenge_Last(
		long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallenge_Last(groupId, challengeId,
			orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] findByGroupIdAndChallenge_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndChallenge_PrevAndNext(childChallengeId,
			groupId, challengeId, orderByComparator);
	}

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallenge(
		long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallenge(groupId, challengeId);
	}

	/**
	* Returns a range of all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallenge(
		long groupId, long challengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallenge(groupId, challengeId,
			start, end);
	}

	/**
	* Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63; and challengeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallenge(
		long groupId, long challengeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallenge(groupId, challengeId,
			start, end, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] filterFindByGroupIdAndChallenge_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .filterFindByGroupIdAndChallenge_PrevAndNext(childChallengeId,
			groupId, challengeId, orderByComparator);
	}

	/**
	* Removes all the child challenges where groupId = &#63; and challengeId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupIdAndChallenge(long groupId,
		long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupIdAndChallenge(groupId, challengeId);
	}

	/**
	* Returns the number of child challenges where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndChallenge(long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupIdAndChallenge(groupId, challengeId);
	}

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupIdAndChallenge(long groupId,
		long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByGroupIdAndChallenge(groupId, challengeId);
	}

	/**
	* Returns all the child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus);
	}

	/**
	* Returns a range of all the child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus, start, end, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallengeAndChallengeStatus_First(
		long groupId, long challengeId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndChallengeStatus_First(groupId,
			challengeId, challengeStatus, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallengeAndChallengeStatus_First(
		long groupId, long challengeId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeAndChallengeStatus_First(groupId,
			challengeId, challengeStatus, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallengeAndChallengeStatus_Last(
		long groupId, long challengeId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndChallengeStatus_Last(groupId,
			challengeId, challengeStatus, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallengeAndChallengeStatus_Last(
		long groupId, long challengeId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeAndChallengeStatus_Last(groupId,
			challengeId, challengeStatus, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] findByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .findByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(childChallengeId,
			groupId, challengeId, challengeStatus, orderByComparator);
	}

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus);
	}

	/**
	* Returns a range of all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus, start, end);
	}

	/**
	* Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus, start, end, orderByComparator);
	}

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param childChallengeId the primary key of the current child challenge
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge[] filterFindByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(childChallengeId,
			groupId, challengeId, challengeStatus, orderByComparator);
	}

	/**
	* Removes all the child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus);
	}

	/**
	* Returns the number of child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus);
	}

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus);
	}

	/**
	* Caches the child challenge in the entity cache if it is enabled.
	*
	* @param childChallenge the child challenge
	*/
	public static void cacheResult(
		kisti.edison.challenge.model.ChildChallenge childChallenge) {
		getPersistence().cacheResult(childChallenge);
	}

	/**
	* Caches the child challenges in the entity cache if it is enabled.
	*
	* @param childChallenges the child challenges
	*/
	public static void cacheResult(
		java.util.List<kisti.edison.challenge.model.ChildChallenge> childChallenges) {
		getPersistence().cacheResult(childChallenges);
	}

	/**
	* Creates a new child challenge with the primary key. Does not add the child challenge to the database.
	*
	* @param childChallengeId the primary key for the new child challenge
	* @return the new child challenge
	*/
	public static kisti.edison.challenge.model.ChildChallenge create(
		long childChallengeId) {
		return getPersistence().create(childChallengeId);
	}

	/**
	* Removes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param childChallengeId the primary key of the child challenge
	* @return the child challenge that was removed
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge remove(
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence().remove(childChallengeId);
	}

	public static kisti.edison.challenge.model.ChildChallenge updateImpl(
		kisti.edison.challenge.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(childChallenge);
	}

	/**
	* Returns the child challenge with the primary key or throws a {@link kisti.edison.challenge.NoSuchChildChallengeException} if it could not be found.
	*
	* @param childChallengeId the primary key of the child challenge
	* @return the child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge findByPrimaryKey(
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException {
		return getPersistence().findByPrimaryKey(childChallengeId);
	}

	/**
	* Returns the child challenge with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param childChallengeId the primary key of the child challenge
	* @return the child challenge, or <code>null</code> if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchByPrimaryKey(
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(childChallengeId);
	}

	/**
	* Returns all the child challenges.
	*
	* @return the child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the child challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the child challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the child challenges from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of child challenges.
	*
	* @return the number of child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ChildChallengePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ChildChallengePersistence)PortletBeanLocatorUtil.locate(kisti.edison.challenge.service.ClpSerializer.getServletContextName(),
					ChildChallengePersistence.class.getName());

			ReferenceRegistry.registerReference(ChildChallengeUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ChildChallengePersistence persistence) {
	}

	private static ChildChallengePersistence _persistence;
}