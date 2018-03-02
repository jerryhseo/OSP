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

import kisti.edison.challenge.model.ChallengeEvaluation;

/**
 * The persistence interface for the challenge evaluation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationPersistenceImpl
 * @see ChallengeEvaluationUtil
 * @generated
 */
public interface ChallengeEvaluationPersistence extends BasePersistence<ChallengeEvaluation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChallengeEvaluationUtil} to access the challenge evaluation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the challenge evaluations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @return the range of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the first challenge evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the last challenge evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluations before and after the current challenge evaluation in the ordered set where uuid = &#63;.
	*
	* @param challengeEvaluationId the primary key of the current challenge evaluation
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation[] findByUuid_PrevAndNext(
		long challengeEvaluationId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Removes all the challenge evaluations where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the challenge evaluation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the challenge evaluation where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the challenge evaluation that was removed
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the number of challenge evaluations where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge evaluations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @return the range of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the first challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the last challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluations before and after the current challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param challengeEvaluationId the primary key of the current challenge evaluation
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation[] findByUuid_C_PrevAndNext(
		long challengeEvaluationId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Removes all the challenge evaluations where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge evaluations where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluations where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @return the range of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluations where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge evaluation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the first challenge evaluation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge evaluation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the last challenge evaluation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluations before and after the current challenge evaluation in the ordered set where groupId = &#63;.
	*
	* @param challengeEvaluationId the primary key of the current challenge evaluation
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation[] findByGroupId_PrevAndNext(
		long challengeEvaluationId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns all the challenge evaluations that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluations that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @return the range of matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluations that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluations before and after the current challenge evaluation in the ordered set of challenge evaluations that the user has permission to view where groupId = &#63;.
	*
	* @param challengeEvaluationId the primary key of the current challenge evaluation
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation[] filterFindByGroupId_PrevAndNext(
		long challengeEvaluationId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Removes all the challenge evaluations where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluations where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluations that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge evaluations where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluations where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @return the range of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluations where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation findByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the first challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation findByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the last challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluations before and after the current challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param challengeEvaluationId the primary key of the current challenge evaluation
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation[] findByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeEvaluationId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns all the challenge evaluations that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluations that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @return the range of matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluations that the user has permissions to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluations before and after the current challenge evaluation in the ordered set of challenge evaluations that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param challengeEvaluationId the primary key of the current challenge evaluation
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation[] filterFindByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeEvaluationId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Removes all the challenge evaluations where groupId = &#63; and challengeTeamId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluations where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluations that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the challenge evaluation in the entity cache if it is enabled.
	*
	* @param challengeEvaluation the challenge evaluation
	*/
	public void cacheResult(
		kisti.edison.challenge.model.ChallengeEvaluation challengeEvaluation);

	/**
	* Caches the challenge evaluations in the entity cache if it is enabled.
	*
	* @param challengeEvaluations the challenge evaluations
	*/
	public void cacheResult(
		java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> challengeEvaluations);

	/**
	* Creates a new challenge evaluation with the primary key. Does not add the challenge evaluation to the database.
	*
	* @param challengeEvaluationId the primary key for the new challenge evaluation
	* @return the new challenge evaluation
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation create(
		long challengeEvaluationId);

	/**
	* Removes the challenge evaluation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationId the primary key of the challenge evaluation
	* @return the challenge evaluation that was removed
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation remove(
		long challengeEvaluationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	public kisti.edison.challenge.model.ChallengeEvaluation updateImpl(
		kisti.edison.challenge.model.ChallengeEvaluation challengeEvaluation)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge evaluation with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationException} if it could not be found.
	*
	* @param challengeEvaluationId the primary key of the challenge evaluation
	* @return the challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation findByPrimaryKey(
		long challengeEvaluationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException;

	/**
	* Returns the challenge evaluation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param challengeEvaluationId the primary key of the challenge evaluation
	* @return the challenge evaluation, or <code>null</code> if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public kisti.edison.challenge.model.ChallengeEvaluation fetchByPrimaryKey(
		long challengeEvaluationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge evaluations.
	*
	* @return the challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge evaluations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @return the range of challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge evaluations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the challenge evaluations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge evaluations.
	*
	* @return the number of challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}