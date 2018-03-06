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

import kisti.edison.challenge.model.ChallengeEvaluationManagement;

/**
 * The persistence interface for the challenge evaluation management service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationManagementPersistenceImpl
 * @see ChallengeEvaluationManagementUtil
 * @generated
 */
public interface ChallengeEvaluationManagementPersistence
	extends BasePersistence<ChallengeEvaluationManagement> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChallengeEvaluationManagementUtil} to access the challenge evaluation management persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the challenge evaluation managements where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluation managements where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @return the range of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluation managements where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge evaluation management in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the first challenge evaluation management in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge evaluation management in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the last challenge evaluation management in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set where uuid = &#63;.
	*
	* @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement[] findByUuid_PrevAndNext(
		long challengeEvaluationManagementId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Removes all the challenge evaluation managements where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluation managements where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation management where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationManagementException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the challenge evaluation management where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation management where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the challenge evaluation management where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the challenge evaluation management that was removed
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the number of challenge evaluation managements where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge evaluation managements where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluation managements where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @return the range of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluation managements where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the first challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the last challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement[] findByUuid_C_PrevAndNext(
		long challengeEvaluationManagementId, java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Removes all the challenge evaluation managements where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluation managements where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge evaluation managements where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluation managements where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @return the range of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluation managements where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge evaluation management in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the first challenge evaluation management in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge evaluation management in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the last challenge evaluation management in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set where groupId = &#63;.
	*
	* @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement[] findByGroupId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns all the challenge evaluation managements that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluation managements that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @return the range of matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluation managements that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set of challenge evaluation managements that the user has permission to view where groupId = &#63;.
	*
	* @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement[] filterFindByGroupId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Removes all the challenge evaluation managements where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluation managements where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluation managements that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge evaluation managements where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @return the matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluation managements where groupId = &#63; and childChallengeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @return the range of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluation managements where groupId = &#63; and childChallengeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement findByGroupIdAndChildChallengeId_First(
		long groupId, long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the first challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByGroupIdAndChildChallengeId_First(
		long groupId, long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement findByGroupIdAndChildChallengeId_Last(
		long groupId, long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the last challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByGroupIdAndChildChallengeId_Last(
		long groupId, long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement[] findByGroupIdAndChildChallengeId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns all the challenge evaluation managements that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @return the matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluation managements that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @return the range of matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluation managements that the user has permissions to view where groupId = &#63; and childChallengeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set of challenge evaluation managements that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement[] filterFindByGroupIdAndChildChallengeId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Removes all the challenge evaluation managements where groupId = &#63; and childChallengeId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupIdAndChildChallengeId(long groupId,
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluation managements where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @return the number of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupIdAndChildChallengeId(long groupId,
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluation managements that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @return the number of matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupIdAndChildChallengeId(long groupId,
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the challenge evaluation management in the entity cache if it is enabled.
	*
	* @param challengeEvaluationManagement the challenge evaluation management
	*/
	public void cacheResult(
		kisti.edison.challenge.model.ChallengeEvaluationManagement challengeEvaluationManagement);

	/**
	* Caches the challenge evaluation managements in the entity cache if it is enabled.
	*
	* @param challengeEvaluationManagements the challenge evaluation managements
	*/
	public void cacheResult(
		java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> challengeEvaluationManagements);

	/**
	* Creates a new challenge evaluation management with the primary key. Does not add the challenge evaluation management to the database.
	*
	* @param challengeEvaluationManagementId the primary key for the new challenge evaluation management
	* @return the new challenge evaluation management
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement create(
		long challengeEvaluationManagementId);

	/**
	* Removes the challenge evaluation management with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	* @return the challenge evaluation management that was removed
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement remove(
		long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	public kisti.edison.challenge.model.ChallengeEvaluationManagement updateImpl(
		kisti.edison.challenge.model.ChallengeEvaluationManagement challengeEvaluationManagement)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation management with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationManagementException} if it could not be found.
	*
	* @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	* @return the challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement findByPrimaryKey(
		long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;

	/**
	* Returns the challenge evaluation management with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	* @return the challenge evaluation management, or <code>null</code> if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByPrimaryKey(
		long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge evaluation managements.
	*
	* @return the challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluation managements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @return the range of challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluation managements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the challenge evaluation managements from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluation managements.
	*
	* @return the number of challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}