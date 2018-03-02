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

import kisti.edison.challenge.model.ChildChallenge;

/**
 * The persistence interface for the child challenge service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChildChallengePersistenceImpl
 * @see ChildChallengeUtil
 * @generated
 */
public interface ChildChallengePersistence extends BasePersistence<ChildChallenge> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChildChallengeUtil} to access the child challenge persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the child challenges where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first child challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the first child challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last child challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the last child challenge in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] findByUuid_PrevAndNext(
		long childChallengeId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Removes all the child challenges where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the child challenge where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChildChallengeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the child challenge where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the child challenge where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the child challenge where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the child challenge that was removed
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the number of child challenges where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the child challenges where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the first child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the last child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] findByUuid_C_PrevAndNext(
		long childChallengeId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Removes all the child challenges where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the child challenges where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGourpId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGourpId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGourpId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge findByGourpId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByGourpId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge findByGourpId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByGourpId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] findByGourpId_PrevAndNext(
		long childChallengeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGourpId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGourpId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGourpId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] filterFindByGourpId_PrevAndNext(
		long childChallengeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Removes all the child challenges where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGourpId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByGourpId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGourpId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the child challenges where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByGroupAndChallengeStatus_First(
		long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByGroupAndChallengeStatus_First(
		long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByGroupAndChallengeStatus_Last(
		long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByGroupAndChallengeStatus_Last(
		long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] findByGroupAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupAndChallengeStatus(
		long groupId, java.lang.String challengeStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] filterFindByGroupAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Removes all the child challenges where groupId = &#63; and challengeStatus LIKE &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupAndChallengeStatus(long groupId,
		java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupAndChallengeStatus(long groupId,
		java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeStatus the challenge status
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupAndChallengeStatus(long groupId,
		java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the child challenges where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallengeAndStatus_First(
		long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

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
	public kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallengeAndStatus_First(
		long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallengeAndStatus_Last(
		long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

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
	public kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallengeAndStatus_Last(
		long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] findByGroupIdAndChallengeAndStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] filterFindByGroupIdAndChallengeAndStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Removes all the child challenges where groupId = &#63; and challengeId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupIdAndChallengeAndStatus(long groupId,
		long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupIdAndChallengeAndStatus(long groupId,
		long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param status the status
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupIdAndChallengeAndStatus(long groupId,
		long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the child challenges where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndNumber(
		long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndNumber(
		long groupId, int number, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndNumber(
		long groupId, int number, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByGroupIdAndNumber_First(
		long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndNumber_First(
		long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByGroupIdAndNumber_Last(
		long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndNumber_Last(
		long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] findByGroupIdAndNumber_PrevAndNext(
		long childChallengeId, long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndNumber(
		long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndNumber(
		long groupId, int number, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndNumber(
		long groupId, int number, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] filterFindByGroupIdAndNumber_PrevAndNext(
		long childChallengeId, long groupId, int number,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Removes all the child challenges where groupId = &#63; and number = &#63; from the database.
	*
	* @param groupId the group ID
	* @param number the number
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupIdAndNumber(long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupIdAndNumber(long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63; and number = &#63;.
	*
	* @param groupId the group ID
	* @param number the number
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupIdAndNumber(long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the child challenges where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallenge(
		long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallenge(
		long groupId, long challengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallenge(
		long groupId, long challengeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallenge_First(
		long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallenge_First(
		long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallenge_Last(
		long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallenge_Last(
		long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] findByGroupIdAndChallenge_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallenge(
		long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallenge(
		long groupId, long challengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallenge(
		long groupId, long challengeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] filterFindByGroupIdAndChallenge_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Removes all the child challenges where groupId = &#63; and challengeId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupIdAndChallenge(long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupIdAndChallenge(long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupIdAndChallenge(long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallengeAndChallengeStatus_First(
		long groupId, long challengeId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

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
	public kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallengeAndChallengeStatus_First(
		long groupId, long challengeId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge findByGroupIdAndChallengeAndChallengeStatus_Last(
		long groupId, long challengeId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

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
	public kisti.edison.challenge.model.ChildChallenge fetchByGroupIdAndChallengeAndChallengeStatus_Last(
		long groupId, long challengeId, java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] findByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @return the matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> filterFindByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public kisti.edison.challenge.model.ChildChallenge[] filterFindByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		java.lang.String challengeStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Removes all the child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupIdAndChallengeAndChallengeStatus(long groupId,
		long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupIdAndChallengeAndChallengeStatus(long groupId,
		long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	*
	* @param groupId the group ID
	* @param challengeId the challenge ID
	* @param challengeStatus the challenge status
	* @return the number of matching child challenges that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the child challenge in the entity cache if it is enabled.
	*
	* @param childChallenge the child challenge
	*/
	public void cacheResult(
		kisti.edison.challenge.model.ChildChallenge childChallenge);

	/**
	* Caches the child challenges in the entity cache if it is enabled.
	*
	* @param childChallenges the child challenges
	*/
	public void cacheResult(
		java.util.List<kisti.edison.challenge.model.ChildChallenge> childChallenges);

	/**
	* Creates a new child challenge with the primary key. Does not add the child challenge to the database.
	*
	* @param childChallengeId the primary key for the new child challenge
	* @return the new child challenge
	*/
	public kisti.edison.challenge.model.ChildChallenge create(
		long childChallengeId);

	/**
	* Removes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param childChallengeId the primary key of the child challenge
	* @return the child challenge that was removed
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge remove(
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	public kisti.edison.challenge.model.ChildChallenge updateImpl(
		kisti.edison.challenge.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the child challenge with the primary key or throws a {@link kisti.edison.challenge.NoSuchChildChallengeException} if it could not be found.
	*
	* @param childChallengeId the primary key of the child challenge
	* @return the child challenge
	* @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge findByPrimaryKey(
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChildChallengeException;

	/**
	* Returns the child challenge with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param childChallengeId the primary key of the child challenge
	* @return the child challenge, or <code>null</code> if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChildChallenge fetchByPrimaryKey(
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the child challenges.
	*
	* @return the child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the child challenges from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges.
	*
	* @return the number of child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}