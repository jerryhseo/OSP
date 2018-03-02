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

import kisti.edison.challenge.model.ChallengeEvaluationScore;

import java.util.List;

/**
 * The persistence utility for the challenge evaluation score service. This utility wraps {@link ChallengeEvaluationScorePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationScorePersistence
 * @see ChallengeEvaluationScorePersistenceImpl
 * @generated
 */
public class ChallengeEvaluationScoreUtil {
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
	public static void clearCache(
		ChallengeEvaluationScore challengeEvaluationScore) {
		getPersistence().clearCache(challengeEvaluationScore);
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
	public static List<ChallengeEvaluationScore> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChallengeEvaluationScore> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChallengeEvaluationScore> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ChallengeEvaluationScore update(
		ChallengeEvaluationScore challengeEvaluationScore)
		throws SystemException {
		return getPersistence().update(challengeEvaluationScore);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ChallengeEvaluationScore update(
		ChallengeEvaluationScore challengeEvaluationScore,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(challengeEvaluationScore, serviceContext);
	}

	/**
	* Returns all the challenge evaluation scores where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the challenge evaluation scores where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where uuid = &#63;.
	*
	* @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore[] findByUuid_PrevAndNext(
		long challengeEvaluationScoreId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByUuid_PrevAndNext(challengeEvaluationScoreId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the challenge evaluation scores where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of challenge evaluation scores where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the challenge evaluation score where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationScoreException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the challenge evaluation score where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the challenge evaluation score where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the challenge evaluation score where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the challenge evaluation score that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of challenge evaluation scores where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the challenge evaluation scores where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the challenge evaluation scores where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore[] findByUuid_C_PrevAndNext(
		long challengeEvaluationScoreId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(challengeEvaluationScoreId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation scores where uuid = &#63; and companyId = &#63; from the database.
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
	* Returns the number of challenge evaluation scores where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the challenge evaluation scores where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the challenge evaluation scores where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where groupId = &#63;.
	*
	* @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore[] findByGroupId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(challengeEvaluationScoreId,
			groupId, orderByComparator);
	}

	/**
	* Returns all the challenge evaluation scores that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the challenge evaluation scores that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set of challenge evaluation scores that the user has permission to view where groupId = &#63;.
	*
	* @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore[] filterFindByGroupId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(challengeEvaluationScoreId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation scores where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of challenge evaluation scores where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of challenge evaluation scores that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the challenge evaluation scores where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}

	/**
	* Returns a range of all the challenge evaluation scores where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId_First(groupId,
			challengeTeamId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeTeamId_First(groupId,
			challengeTeamId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId_Last(groupId,
			challengeTeamId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeTeamId_Last(groupId,
			challengeTeamId, orderByComparator);
	}

	/**
	* Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore[] findByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId_PrevAndNext(challengeEvaluationScoreId,
			groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Returns all the challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId(groupId,
			challengeTeamId);
	}

	/**
	* Returns a range of all the challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId(groupId,
			challengeTeamId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores that the user has permissions to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId(groupId,
			challengeTeamId, start, end, orderByComparator);
	}

	/**
	* Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set of challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore[] filterFindByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId_PrevAndNext(challengeEvaluationScoreId,
			groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation scores where groupId = &#63; and challengeTeamId = &#63; from the database.
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
	* Returns the number of challenge evaluation scores where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}

	/**
	* Returns the number of challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge evaluation scores that the user has permission to view
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
	* Returns all the challenge evaluation scores where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @return the matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId);
	}

	/**
	* Returns a range of all the challenge evaluation scores where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByGroupIdAndChallengeEvaluationManagementId_First(
		long groupId, long challengeEvaluationManagementId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupIdAndChallengeEvaluationManagementId_First(groupId,
			challengeEvaluationManagementId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByGroupIdAndChallengeEvaluationManagementId_First(
		long groupId, long challengeEvaluationManagementId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeEvaluationManagementId_First(groupId,
			challengeEvaluationManagementId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByGroupIdAndChallengeEvaluationManagementId_Last(
		long groupId, long challengeEvaluationManagementId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupIdAndChallengeEvaluationManagementId_Last(groupId,
			challengeEvaluationManagementId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByGroupIdAndChallengeEvaluationManagementId_Last(
		long groupId, long challengeEvaluationManagementId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeEvaluationManagementId_Last(groupId,
			challengeEvaluationManagementId, orderByComparator);
	}

	/**
	* Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore[] findByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId,
		long challengeEvaluationManagementId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(challengeEvaluationScoreId,
			groupId, challengeEvaluationManagementId, orderByComparator);
	}

	/**
	* Returns all the challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @return the matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId);
	}

	/**
	* Returns a range of all the challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores that the user has permissions to view where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId, start, end, orderByComparator);
	}

	/**
	* Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set of challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore[] filterFindByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId,
		long challengeEvaluationManagementId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(challengeEvaluationScoreId,
			groupId, challengeEvaluationManagementId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation scores where groupId = &#63; and challengeEvaluationManagementId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId);
	}

	/**
	* Returns the number of challenge evaluation scores where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @return the number of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId);
	}

	/**
	* Returns the number of challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @return the number of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId);
	}

	/**
	* Returns all the challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
			challengeTeamId);
	}

	/**
	* Returns a range of all the challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
			challengeTeamId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
			challengeTeamId, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByGroupIdAndChallengeTeamIdAndUserId_First(
		long userId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamIdAndUserId_First(userId,
			groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation score in the ordered set where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByGroupIdAndChallengeTeamIdAndUserId_First(
		long userId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeTeamIdAndUserId_First(userId,
			groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByGroupIdAndChallengeTeamIdAndUserId_Last(
		long userId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamIdAndUserId_Last(userId,
			groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation score in the ordered set where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByGroupIdAndChallengeTeamIdAndUserId_Last(
		long userId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeTeamIdAndUserId_Last(userId,
			groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore[] findByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(
		long challengeEvaluationScoreId, long userId, long groupId,
		long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(challengeEvaluationScoreId,
			userId, groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Returns all the challenge evaluation scores that the user has permission to view where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamIdAndUserId(userId,
			groupId, challengeTeamId);
	}

	/**
	* Returns a range of all the challenge evaluation scores that the user has permission to view where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamIdAndUserId(userId,
			groupId, challengeTeamId, start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores that the user has permissions to view where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamIdAndUserId(userId,
			groupId, challengeTeamId, start, end, orderByComparator);
	}

	/**
	* Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set of challenge evaluation scores that the user has permission to view where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore[] filterFindByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(
		long challengeEvaluationScoreId, long userId, long groupId,
		long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(challengeEvaluationScoreId,
			userId, groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeTeamId = &#63; from the database.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupIdAndChallengeTeamIdAndUserId(long userId,
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
			challengeTeamId);
	}

	/**
	* Returns the number of challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndChallengeTeamIdAndUserId(long userId,
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
			challengeTeamId);
	}

	/**
	* Returns the number of challenge evaluation scores that the user has permission to view where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge evaluation scores that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByGroupIdAndChallengeTeamIdAndUserId(userId,
			groupId, challengeTeamId);
	}

	/**
	* Returns the challenge evaluation score where userId = &#63; and groupId = &#63; and challengeEvaluationManagementId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationScoreException} if it could not be found.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @return the matching challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByGroupIdAndChallengeEvaluatinManagementIdAndUserId(
		long userId, long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .findByGroupIdAndChallengeEvaluatinManagementIdAndUserId(userId,
			groupId, challengeEvaluationManagementId);
	}

	/**
	* Returns the challenge evaluation score where userId = &#63; and groupId = &#63; and challengeEvaluationManagementId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByGroupIdAndChallengeEvaluatinManagementIdAndUserId(
		long userId, long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeEvaluatinManagementIdAndUserId(userId,
			groupId, challengeEvaluationManagementId);
	}

	/**
	* Returns the challenge evaluation score where userId = &#63; and groupId = &#63; and challengeEvaluationManagementId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByGroupIdAndChallengeEvaluatinManagementIdAndUserId(
		long userId, long groupId, long challengeEvaluationManagementId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeEvaluatinManagementIdAndUserId(userId,
			groupId, challengeEvaluationManagementId, retrieveFromCache);
	}

	/**
	* Removes the challenge evaluation score where userId = &#63; and groupId = &#63; and challengeEvaluationManagementId = &#63; from the database.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @return the challenge evaluation score that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore removeByGroupIdAndChallengeEvaluatinManagementIdAndUserId(
		long userId, long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence()
				   .removeByGroupIdAndChallengeEvaluatinManagementIdAndUserId(userId,
			groupId, challengeEvaluationManagementId);
	}

	/**
	* Returns the number of challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeEvaluationManagementId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param challengeEvaluationManagementId the challenge evaluation management ID
	* @return the number of matching challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndChallengeEvaluatinManagementIdAndUserId(
		long userId, long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndChallengeEvaluatinManagementIdAndUserId(userId,
			groupId, challengeEvaluationManagementId);
	}

	/**
	* Caches the challenge evaluation score in the entity cache if it is enabled.
	*
	* @param challengeEvaluationScore the challenge evaluation score
	*/
	public static void cacheResult(
		kisti.edison.challenge.model.ChallengeEvaluationScore challengeEvaluationScore) {
		getPersistence().cacheResult(challengeEvaluationScore);
	}

	/**
	* Caches the challenge evaluation scores in the entity cache if it is enabled.
	*
	* @param challengeEvaluationScores the challenge evaluation scores
	*/
	public static void cacheResult(
		java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> challengeEvaluationScores) {
		getPersistence().cacheResult(challengeEvaluationScores);
	}

	/**
	* Creates a new challenge evaluation score with the primary key. Does not add the challenge evaluation score to the database.
	*
	* @param challengeEvaluationScoreId the primary key for the new challenge evaluation score
	* @return the new challenge evaluation score
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore create(
		long challengeEvaluationScoreId) {
		return getPersistence().create(challengeEvaluationScoreId);
	}

	/**
	* Removes the challenge evaluation score with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationScoreId the primary key of the challenge evaluation score
	* @return the challenge evaluation score that was removed
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore remove(
		long challengeEvaluationScoreId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence().remove(challengeEvaluationScoreId);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScore updateImpl(
		kisti.edison.challenge.model.ChallengeEvaluationScore challengeEvaluationScore)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(challengeEvaluationScore);
	}

	/**
	* Returns the challenge evaluation score with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationScoreException} if it could not be found.
	*
	* @param challengeEvaluationScoreId the primary key of the challenge evaluation score
	* @return the challenge evaluation score
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore findByPrimaryKey(
		long challengeEvaluationScoreId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationScoreException {
		return getPersistence().findByPrimaryKey(challengeEvaluationScoreId);
	}

	/**
	* Returns the challenge evaluation score with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param challengeEvaluationScoreId the primary key of the challenge evaluation score
	* @return the challenge evaluation score, or <code>null</code> if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchByPrimaryKey(
		long challengeEvaluationScoreId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(challengeEvaluationScoreId);
	}

	/**
	* Returns all the challenge evaluation scores.
	*
	* @return the challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the challenge evaluation scores.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the challenge evaluation scores.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation scores from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of challenge evaluation scores.
	*
	* @return the number of challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ChallengeEvaluationScorePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ChallengeEvaluationScorePersistence)PortletBeanLocatorUtil.locate(kisti.edison.challenge.service.ClpSerializer.getServletContextName(),
					ChallengeEvaluationScorePersistence.class.getName());

			ReferenceRegistry.registerReference(ChallengeEvaluationScoreUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ChallengeEvaluationScorePersistence persistence) {
	}

	private static ChallengeEvaluationScorePersistence _persistence;
}