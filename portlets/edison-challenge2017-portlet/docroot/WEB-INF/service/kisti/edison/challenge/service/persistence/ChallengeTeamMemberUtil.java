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

import kisti.edison.challenge.model.ChallengeTeamMember;

import java.util.List;

/**
 * The persistence utility for the challenge team member service. This utility wraps {@link ChallengeTeamMemberPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeTeamMemberPersistence
 * @see ChallengeTeamMemberPersistenceImpl
 * @generated
 */
public class ChallengeTeamMemberUtil {
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
	public static void clearCache(ChallengeTeamMember challengeTeamMember) {
		getPersistence().clearCache(challengeTeamMember);
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
	public static List<ChallengeTeamMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChallengeTeamMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChallengeTeamMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ChallengeTeamMember update(
		ChallengeTeamMember challengeTeamMember) throws SystemException {
		return getPersistence().update(challengeTeamMember);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ChallengeTeamMember update(
		ChallengeTeamMember challengeTeamMember, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(challengeTeamMember, serviceContext);
	}

	/**
	* Returns all the challenge team members where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the challenge team members where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the challenge team members before and after the current challenge team member in the ordered set where uuid = &#63;.
	*
	* @param challengeTeamMemberId the primary key of the current challenge team member
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember[] findByUuid_PrevAndNext(
		long challengeTeamMemberId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByUuid_PrevAndNext(challengeTeamMemberId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the challenge team members where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of challenge team members where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the challenge team member where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeTeamMemberException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the challenge team member where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the challenge team member where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the challenge team member where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the challenge team member that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of challenge team members where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the challenge team members where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the challenge team members where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the challenge team members before and after the current challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param challengeTeamMemberId the primary key of the current challenge team member
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember[] findByUuid_C_PrevAndNext(
		long challengeTeamMemberId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(challengeTeamMemberId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the challenge team members where uuid = &#63; and companyId = &#63; from the database.
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
	* Returns the number of challenge team members where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the challenge team members where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the challenge team members where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the challenge team members before and after the current challenge team member in the ordered set where groupId = &#63;.
	*
	* @param challengeTeamMemberId the primary key of the current challenge team member
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember[] findByGroupId_PrevAndNext(
		long challengeTeamMemberId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(challengeTeamMemberId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the challenge team members that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the challenge team members that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the challenge team members before and after the current challenge team member in the ordered set of challenge team members that the user has permission to view where groupId = &#63;.
	*
	* @param challengeTeamMemberId the primary key of the current challenge team member
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember[] filterFindByGroupId_PrevAndNext(
		long challengeTeamMemberId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(challengeTeamMemberId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the challenge team members where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of challenge team members where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of challenge team members that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the challenge team members where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndStatus(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupIdAndStatus(groupId, status);
	}

	/**
	* Returns a range of all the challenge team members where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndStatus(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndStatus(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndStatus(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndStatus(groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndStatus_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupIdAndStatus_First(groupId, status,
			orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndStatus_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndStatus_First(groupId, status,
			orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndStatus_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupIdAndStatus_Last(groupId, status,
			orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndStatus_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndStatus_Last(groupId, status,
			orderByComparator);
	}

	/**
	* Returns the challenge team members before and after the current challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param challengeTeamMemberId the primary key of the current challenge team member
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember[] findByGroupIdAndStatus_PrevAndNext(
		long challengeTeamMemberId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupIdAndStatus_PrevAndNext(challengeTeamMemberId,
			groupId, status, orderByComparator);
	}

	/**
	* Returns all the challenge team members that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndStatus(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupIdAndStatus(groupId, status);
	}

	/**
	* Returns a range of all the challenge team members that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndStatus(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndStatus(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members that the user has permissions to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndStatus(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndStatus(groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the challenge team members before and after the current challenge team member in the ordered set of challenge team members that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param challengeTeamMemberId the primary key of the current challenge team member
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember[] filterFindByGroupIdAndStatus_PrevAndNext(
		long challengeTeamMemberId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .filterFindByGroupIdAndStatus_PrevAndNext(challengeTeamMemberId,
			groupId, status, orderByComparator);
	}

	/**
	* Removes all the challenge team members where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupIdAndStatus(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupIdAndStatus(groupId, status);
	}

	/**
	* Returns the number of challenge team members where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndStatus(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupIdAndStatus(groupId, status);
	}

	/**
	* Returns the number of challenge team members that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupIdAndStatus(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupIdAndStatus(groupId, status);
	}

	/**
	* Returns all the challenge team members where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}

	/**
	* Returns a range of all the challenge team members where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId_First(groupId,
			challengeTeamId, orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeTeamId_First(groupId,
			challengeTeamId, orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId_Last(groupId,
			challengeTeamId, orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeTeamId_Last(groupId,
			challengeTeamId, orderByComparator);
	}

	/**
	* Returns the challenge team members before and after the current challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param challengeTeamMemberId the primary key of the current challenge team member
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember[] findByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId_PrevAndNext(challengeTeamMemberId,
			groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Returns all the challenge team members that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId(groupId,
			challengeTeamId);
	}

	/**
	* Returns a range of all the challenge team members that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId(groupId,
			challengeTeamId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members that the user has permissions to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId(groupId,
			challengeTeamId, start, end, orderByComparator);
	}

	/**
	* Returns the challenge team members before and after the current challenge team member in the ordered set of challenge team members that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param challengeTeamMemberId the primary key of the current challenge team member
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember[] filterFindByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId_PrevAndNext(challengeTeamMemberId,
			groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Removes all the challenge team members where groupId = &#63; and challengeTeamId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}

	/**
	* Returns the number of challenge team members where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}

	/**
	* Returns the number of challenge team members that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByGroupIdAndChallengeTeamId(groupId,
			challengeTeamId);
	}

	/**
	* Returns all the challenge team members where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndApplyUserId(
		long groupId, long applyUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupIdAndApplyUserId(groupId, applyUserId);
	}

	/**
	* Returns a range of all the challenge team members where groupId = &#63; and applyUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndApplyUserId(
		long groupId, long applyUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndApplyUserId(groupId, applyUserId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members where groupId = &#63; and applyUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndApplyUserId(
		long groupId, long applyUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndApplyUserId(groupId, applyUserId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndApplyUserId_First(
		long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupIdAndApplyUserId_First(groupId, applyUserId,
			orderByComparator);
	}

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndApplyUserId_First(
		long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndApplyUserId_First(groupId, applyUserId,
			orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndApplyUserId_Last(
		long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupIdAndApplyUserId_Last(groupId, applyUserId,
			orderByComparator);
	}

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndApplyUserId_Last(
		long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndApplyUserId_Last(groupId, applyUserId,
			orderByComparator);
	}

	/**
	* Returns the challenge team members before and after the current challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	*
	* @param challengeTeamMemberId the primary key of the current challenge team member
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember[] findByGroupIdAndApplyUserId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupIdAndApplyUserId_PrevAndNext(challengeTeamMemberId,
			groupId, applyUserId, orderByComparator);
	}

	/**
	* Returns all the challenge team members that the user has permission to view where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @return the matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndApplyUserId(
		long groupId, long applyUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndApplyUserId(groupId, applyUserId);
	}

	/**
	* Returns a range of all the challenge team members that the user has permission to view where groupId = &#63; and applyUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndApplyUserId(
		long groupId, long applyUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndApplyUserId(groupId, applyUserId,
			start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members that the user has permissions to view where groupId = &#63; and applyUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndApplyUserId(
		long groupId, long applyUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndApplyUserId(groupId, applyUserId,
			start, end, orderByComparator);
	}

	/**
	* Returns the challenge team members before and after the current challenge team member in the ordered set of challenge team members that the user has permission to view where groupId = &#63; and applyUserId = &#63;.
	*
	* @param challengeTeamMemberId the primary key of the current challenge team member
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember[] filterFindByGroupIdAndApplyUserId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .filterFindByGroupIdAndApplyUserId_PrevAndNext(challengeTeamMemberId,
			groupId, applyUserId, orderByComparator);
	}

	/**
	* Removes all the challenge team members where groupId = &#63; and applyUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupIdAndApplyUserId(long groupId,
		long applyUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupIdAndApplyUserId(groupId, applyUserId);
	}

	/**
	* Returns the number of challenge team members where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndApplyUserId(long groupId,
		long applyUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndApplyUserId(groupId, applyUserId);
	}

	/**
	* Returns the number of challenge team members that the user has permission to view where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @return the number of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupIdAndApplyUserId(long groupId,
		long applyUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByGroupIdAndApplyUserId(groupId, applyUserId);
	}

	/**
	* Returns the challenge team member where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeTeamMemberException} if it could not be found.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .findByGroupIdAndApplyUserIdAndChallengeTeamId(groupId,
			applyUserId, challengeTeamId);
	}

	/**
	* Returns the challenge team member where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndApplyUserIdAndChallengeTeamId(groupId,
			applyUserId, challengeTeamId);
	}

	/**
	* Returns the challenge team member where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param challengeTeamId the challenge team ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndApplyUserIdAndChallengeTeamId(groupId,
			applyUserId, challengeTeamId, retrieveFromCache);
	}

	/**
	* Removes the challenge team member where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param challengeTeamId the challenge team ID
	* @return the challenge team member that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember removeByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence()
				   .removeByGroupIdAndApplyUserIdAndChallengeTeamId(groupId,
			applyUserId, challengeTeamId);
	}

	/**
	* Returns the number of challenge team members where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndApplyUserIdAndChallengeTeamId(groupId,
			applyUserId, challengeTeamId);
	}

	/**
	* Caches the challenge team member in the entity cache if it is enabled.
	*
	* @param challengeTeamMember the challenge team member
	*/
	public static void cacheResult(
		kisti.edison.challenge.model.ChallengeTeamMember challengeTeamMember) {
		getPersistence().cacheResult(challengeTeamMember);
	}

	/**
	* Caches the challenge team members in the entity cache if it is enabled.
	*
	* @param challengeTeamMembers the challenge team members
	*/
	public static void cacheResult(
		java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> challengeTeamMembers) {
		getPersistence().cacheResult(challengeTeamMembers);
	}

	/**
	* Creates a new challenge team member with the primary key. Does not add the challenge team member to the database.
	*
	* @param challengeTeamMemberId the primary key for the new challenge team member
	* @return the new challenge team member
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember create(
		long challengeTeamMemberId) {
		return getPersistence().create(challengeTeamMemberId);
	}

	/**
	* Removes the challenge team member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeamMemberId the primary key of the challenge team member
	* @return the challenge team member that was removed
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember remove(
		long challengeTeamMemberId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence().remove(challengeTeamMemberId);
	}

	public static kisti.edison.challenge.model.ChallengeTeamMember updateImpl(
		kisti.edison.challenge.model.ChallengeTeamMember challengeTeamMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(challengeTeamMember);
	}

	/**
	* Returns the challenge team member with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeTeamMemberException} if it could not be found.
	*
	* @param challengeTeamMemberId the primary key of the challenge team member
	* @return the challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember findByPrimaryKey(
		long challengeTeamMemberId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException {
		return getPersistence().findByPrimaryKey(challengeTeamMemberId);
	}

	/**
	* Returns the challenge team member with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param challengeTeamMemberId the primary key of the challenge team member
	* @return the challenge team member, or <code>null</code> if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchByPrimaryKey(
		long challengeTeamMemberId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(challengeTeamMemberId);
	}

	/**
	* Returns all the challenge team members.
	*
	* @return the challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the challenge team members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the challenge team members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the challenge team members from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of challenge team members.
	*
	* @return the number of challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ChallengeTeamMemberPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ChallengeTeamMemberPersistence)PortletBeanLocatorUtil.locate(kisti.edison.challenge.service.ClpSerializer.getServletContextName(),
					ChallengeTeamMemberPersistence.class.getName());

			ReferenceRegistry.registerReference(ChallengeTeamMemberUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ChallengeTeamMemberPersistence persistence) {
	}

	private static ChallengeTeamMemberPersistence _persistence;
}