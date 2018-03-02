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

import kisti.edison.challenge.model.Challenge;

/**
 * The persistence interface for the challenge service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengePersistenceImpl
 * @see ChallengeUtil
 * @generated
 */
public interface ChallengePersistence extends BasePersistence<Challenge> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChallengeUtil} to access the challenge persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the challenges where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenges where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenges where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the first challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the last challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenges before and after the current challenge in the ordered set where uuid = &#63;.
	*
	* @param challengeId the primary key of the current challenge
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge[] findByUuid_PrevAndNext(
		long challengeId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Removes all the challenges where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenges where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the challenge where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the challenge where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the challenge that was removed
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the number of challenges where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenges where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenges where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenges where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the first challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the last challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenges before and after the current challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param challengeId the primary key of the current challenge
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge[] findByUuid_C_PrevAndNext(
		long challengeId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Removes all the challenges where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenges where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenges where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenges where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenges where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the first challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the last challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenges before and after the current challenge in the ordered set where groupId = &#63;.
	*
	* @param challengeId the primary key of the current challenge
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge[] findByGroupId_PrevAndNext(
		long challengeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns all the challenges that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenges that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenges that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenges before and after the current challenge in the ordered set of challenges that the user has permission to view where groupId = &#63;.
	*
	* @param challengeId the primary key of the current challenge
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge[] filterFindByGroupId_PrevAndNext(
		long challengeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Removes all the challenges where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenges where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenges that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenges where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByGroupAndStatus(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenges where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByGroupAndStatus(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenges where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByGroupAndStatus(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByGroupAndStatus_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the first challenge in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByGroupAndStatus_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByGroupAndStatus_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the last challenge in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByGroupAndStatus_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenges before and after the current challenge in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param challengeId the primary key of the current challenge
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge[] findByGroupAndStatus_PrevAndNext(
		long challengeId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns all the challenges that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> filterFindByGroupAndStatus(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenges that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> filterFindByGroupAndStatus(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenges that the user has permissions to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> filterFindByGroupAndStatus(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenges before and after the current challenge in the ordered set of challenges that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param challengeId the primary key of the current challenge
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge[] filterFindByGroupAndStatus_PrevAndNext(
		long challengeId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Removes all the challenges where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupAndStatus(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenges where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupAndStatus(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenges that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupAndStatus(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenges where groupId = &#63; and field LIKE &#63;.
	*
	* @param groupId the group ID
	* @param field the field
	* @return the matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByGroupAndField(
		long groupId, java.lang.String field)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenges where groupId = &#63; and field LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param field the field
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByGroupAndField(
		long groupId, java.lang.String field, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenges where groupId = &#63; and field LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param field the field
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findByGroupAndField(
		long groupId, java.lang.String field, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge in the ordered set where groupId = &#63; and field LIKE &#63;.
	*
	* @param groupId the group ID
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByGroupAndField_First(
		long groupId, java.lang.String field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the first challenge in the ordered set where groupId = &#63; and field LIKE &#63;.
	*
	* @param groupId the group ID
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByGroupAndField_First(
		long groupId, java.lang.String field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge in the ordered set where groupId = &#63; and field LIKE &#63;.
	*
	* @param groupId the group ID
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByGroupAndField_Last(
		long groupId, java.lang.String field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the last challenge in the ordered set where groupId = &#63; and field LIKE &#63;.
	*
	* @param groupId the group ID
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByGroupAndField_Last(
		long groupId, java.lang.String field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenges before and after the current challenge in the ordered set where groupId = &#63; and field LIKE &#63;.
	*
	* @param challengeId the primary key of the current challenge
	* @param groupId the group ID
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge[] findByGroupAndField_PrevAndNext(
		long challengeId, long groupId, java.lang.String field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns all the challenges that the user has permission to view where groupId = &#63; and field LIKE &#63;.
	*
	* @param groupId the group ID
	* @param field the field
	* @return the matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> filterFindByGroupAndField(
		long groupId, java.lang.String field)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenges that the user has permission to view where groupId = &#63; and field LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param field the field
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> filterFindByGroupAndField(
		long groupId, java.lang.String field, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenges that the user has permissions to view where groupId = &#63; and field LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param field the field
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> filterFindByGroupAndField(
		long groupId, java.lang.String field, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenges before and after the current challenge in the ordered set of challenges that the user has permission to view where groupId = &#63; and field LIKE &#63;.
	*
	* @param challengeId the primary key of the current challenge
	* @param groupId the group ID
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge[] filterFindByGroupAndField_PrevAndNext(
		long challengeId, long groupId, java.lang.String field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Removes all the challenges where groupId = &#63; and field LIKE &#63; from the database.
	*
	* @param groupId the group ID
	* @param field the field
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupAndField(long groupId, java.lang.String field)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenges where groupId = &#63; and field LIKE &#63;.
	*
	* @param groupId the group ID
	* @param field the field
	* @return the number of matching challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupAndField(long groupId, java.lang.String field)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenges that the user has permission to view where groupId = &#63; and field LIKE &#63;.
	*
	* @param groupId the group ID
	* @param field the field
	* @return the number of matching challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupAndField(long groupId, java.lang.String field)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the challenge in the entity cache if it is enabled.
	*
	* @param challenge the challenge
	*/
	public void cacheResult(kisti.edison.challenge.model.Challenge challenge);

	/**
	* Caches the challenges in the entity cache if it is enabled.
	*
	* @param challenges the challenges
	*/
	public void cacheResult(
		java.util.List<kisti.edison.challenge.model.Challenge> challenges);

	/**
	* Creates a new challenge with the primary key. Does not add the challenge to the database.
	*
	* @param challengeId the primary key for the new challenge
	* @return the new challenge
	*/
	public kisti.edison.challenge.model.Challenge create(long challengeId);

	/**
	* Removes the challenge with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeId the primary key of the challenge
	* @return the challenge that was removed
	* @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge remove(long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	public kisti.edison.challenge.model.Challenge updateImpl(
		kisti.edison.challenge.model.Challenge challenge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeException} if it could not be found.
	*
	* @param challengeId the primary key of the challenge
	* @return the challenge
	* @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge findByPrimaryKey(
		long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException;

	/**
	* Returns the challenge with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param challengeId the primary key of the challenge
	* @return the challenge, or <code>null</code> if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.Challenge fetchByPrimaryKey(
		long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenges.
	*
	* @return the challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.Challenge> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the challenges from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenges.
	*
	* @return the number of challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}