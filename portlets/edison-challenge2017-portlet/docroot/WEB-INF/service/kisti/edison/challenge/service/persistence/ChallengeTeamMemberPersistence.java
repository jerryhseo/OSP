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

import com.liferay.portal.service.persistence.BasePersistence;

import kisti.edison.challenge.model.ChallengeTeamMember;

/**
 * The persistence interface for the challenge team member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeTeamMemberPersistenceImpl
 * @see ChallengeTeamMemberUtil
 * @generated
 */
public interface ChallengeTeamMemberPersistence extends BasePersistence<ChallengeTeamMember> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChallengeTeamMemberUtil} to access the challenge team member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the challenge team members where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge team member in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the first challenge team member in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge team member in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the last challenge team member in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember[] findByUuid_PrevAndNext(
		long challengeTeamMemberId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Removes all the challenge team members where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge team member where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeTeamMemberException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the challenge team member where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge team member where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the challenge team member where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the challenge team member that was removed
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the number of challenge team members where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge team members where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the first challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the last challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember[] findByUuid_C_PrevAndNext(
		long challengeTeamMemberId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Removes all the challenge team members where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge team members where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember[] findByGroupId_PrevAndNext(
		long challengeTeamMemberId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns all the challenge team members that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember[] filterFindByGroupId_PrevAndNext(
		long challengeTeamMemberId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Removes all the challenge team members where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge team members where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndStatus(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndStatus(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndStatus(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndStatus_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndStatus_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndStatus_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndStatus_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember[] findByGroupIdAndStatus_PrevAndNext(
		long challengeTeamMemberId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns all the challenge team members that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndStatus(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndStatus(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndStatus(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember[] filterFindByGroupIdAndStatus_PrevAndNext(
		long challengeTeamMemberId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Removes all the challenge team members where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupIdAndStatus(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupIdAndStatus(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupIdAndStatus(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge team members where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember[] findByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns all the challenge team members that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember[] filterFindByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Removes all the challenge team members where groupId = &#63; and challengeTeamId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge team members where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @return the matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndApplyUserId(
		long groupId, long applyUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndApplyUserId(
		long groupId, long applyUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findByGroupIdAndApplyUserId(
		long groupId, long applyUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndApplyUserId_First(
		long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the first challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndApplyUserId_First(
		long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndApplyUserId_Last(
		long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the last challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndApplyUserId_Last(
		long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember[] findByGroupIdAndApplyUserId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns all the challenge team members that the user has permission to view where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @return the matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndApplyUserId(
		long groupId, long applyUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndApplyUserId(
		long groupId, long applyUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> filterFindByGroupIdAndApplyUserId(
		long groupId, long applyUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember[] filterFindByGroupIdAndApplyUserId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long applyUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Removes all the challenge team members where groupId = &#63; and applyUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupIdAndApplyUserId(long groupId, long applyUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupIdAndApplyUserId(long groupId, long applyUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members that the user has permission to view where groupId = &#63; and applyUserId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @return the number of matching challenge team members that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupIdAndApplyUserId(long groupId, long applyUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember findByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the challenge team member where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the challenge team member where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param challengeTeamId the challenge team ID
	* @return the challenge team member that was removed
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember removeByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the number of challenge team members where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param applyUserId the apply user ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupIdAndApplyUserIdAndChallengeTeamId(long groupId,
		long applyUserId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the challenge team member in the entity cache if it is enabled.
	*
	* @param challengeTeamMember the challenge team member
	*/
	public void cacheResult(
		kisti.edison.challenge.model.ChallengeTeamMember challengeTeamMember);

	/**
	* Caches the challenge team members in the entity cache if it is enabled.
	*
	* @param challengeTeamMembers the challenge team members
	*/
	public void cacheResult(
		java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> challengeTeamMembers);

	/**
	* Creates a new challenge team member with the primary key. Does not add the challenge team member to the database.
	*
	* @param challengeTeamMemberId the primary key for the new challenge team member
	* @return the new challenge team member
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember create(
		long challengeTeamMemberId);

	/**
	* Removes the challenge team member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeamMemberId the primary key of the challenge team member
	* @return the challenge team member that was removed
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember remove(
		long challengeTeamMemberId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	public kisti.edison.challenge.model.ChallengeTeamMember updateImpl(
		kisti.edison.challenge.model.ChallengeTeamMember challengeTeamMember)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge team member with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeTeamMemberException} if it could not be found.
	*
	* @param challengeTeamMemberId the primary key of the challenge team member
	* @return the challenge team member
	* @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember findByPrimaryKey(
		long challengeTeamMemberId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeTeamMemberException;

	/**
	* Returns the challenge team member with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param challengeTeamMemberId the primary key of the challenge team member
	* @return the challenge team member, or <code>null</code> if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeTeamMember fetchByPrimaryKey(
		long challengeTeamMemberId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge team members.
	*
	* @return the challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the challenge team members from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge team members.
	*
	* @return the number of challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}