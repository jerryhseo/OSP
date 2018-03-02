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

import kisti.edison.challenge.model.ChallengeEvaluation;

import java.util.List;

/**
 * The persistence utility for the challenge evaluation service. This utility wraps {@link ChallengeEvaluationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationPersistence
 * @see ChallengeEvaluationPersistenceImpl
 * @generated
 */
public class ChallengeEvaluationUtil {
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
	public static void clearCache(ChallengeEvaluation challengeEvaluation) {
		getPersistence().clearCache(challengeEvaluation);
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
	public static List<ChallengeEvaluation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChallengeEvaluation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChallengeEvaluation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ChallengeEvaluation update(
		ChallengeEvaluation challengeEvaluation) throws SystemException {
		return getPersistence().update(challengeEvaluation);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ChallengeEvaluation update(
		ChallengeEvaluation challengeEvaluation, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(challengeEvaluation, serviceContext);
	}

	/**
	* Returns all the challenge evaluations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluation[] findByUuid_PrevAndNext(
		long challengeEvaluationId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence()
				   .findByUuid_PrevAndNext(challengeEvaluationId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the challenge evaluations where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of challenge evaluations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the challenge evaluation where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the challenge evaluation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the challenge evaluation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the challenge evaluation where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the challenge evaluation that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of challenge evaluations where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the challenge evaluations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluation findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluation findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluation[] findByUuid_C_PrevAndNext(
		long challengeEvaluationId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(challengeEvaluationId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluations where uuid = &#63; and companyId = &#63; from the database.
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
	* Returns the number of challenge evaluations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the challenge evaluations where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluation[] findByGroupId_PrevAndNext(
		long challengeEvaluationId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(challengeEvaluationId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the challenge evaluations that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluation[] filterFindByGroupId_PrevAndNext(
		long challengeEvaluationId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(challengeEvaluationId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluations where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of challenge evaluations where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of challenge evaluations that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the challenge evaluations where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			start, end, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluation findByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId_First(groupId,
			challengeTeamId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeTeamId_First(groupId,
			challengeTeamId, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluation findByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId_Last(groupId,
			challengeTeamId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChallengeTeamId_Last(groupId,
			challengeTeamId, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluation[] findByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeEvaluationId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence()
				   .findByGroupIdAndChallengeTeamId_PrevAndNext(challengeEvaluationId,
			groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Returns all the challenge evaluations that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the matching challenge evaluations that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId(groupId,
			challengeTeamId);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId(groupId,
			challengeTeamId, start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId(groupId,
			challengeTeamId, start, end, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluation[] filterFindByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeEvaluationId, long groupId, long challengeTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence()
				   .filterFindByGroupIdAndChallengeTeamId_PrevAndNext(challengeEvaluationId,
			groupId, challengeTeamId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluations where groupId = &#63; and challengeTeamId = &#63; from the database.
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
	* Returns the number of challenge evaluations where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}

	/**
	* Returns the number of challenge evaluations that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	*
	* @param groupId the group ID
	* @param challengeTeamId the challenge team ID
	* @return the number of matching challenge evaluations that the user has permission to view
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
	* Caches the challenge evaluation in the entity cache if it is enabled.
	*
	* @param challengeEvaluation the challenge evaluation
	*/
	public static void cacheResult(
		kisti.edison.challenge.model.ChallengeEvaluation challengeEvaluation) {
		getPersistence().cacheResult(challengeEvaluation);
	}

	/**
	* Caches the challenge evaluations in the entity cache if it is enabled.
	*
	* @param challengeEvaluations the challenge evaluations
	*/
	public static void cacheResult(
		java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> challengeEvaluations) {
		getPersistence().cacheResult(challengeEvaluations);
	}

	/**
	* Creates a new challenge evaluation with the primary key. Does not add the challenge evaluation to the database.
	*
	* @param challengeEvaluationId the primary key for the new challenge evaluation
	* @return the new challenge evaluation
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation create(
		long challengeEvaluationId) {
		return getPersistence().create(challengeEvaluationId);
	}

	/**
	* Removes the challenge evaluation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationId the primary key of the challenge evaluation
	* @return the challenge evaluation that was removed
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation remove(
		long challengeEvaluationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence().remove(challengeEvaluationId);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluation updateImpl(
		kisti.edison.challenge.model.ChallengeEvaluation challengeEvaluation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(challengeEvaluation);
	}

	/**
	* Returns the challenge evaluation with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationException} if it could not be found.
	*
	* @param challengeEvaluationId the primary key of the challenge evaluation
	* @return the challenge evaluation
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation findByPrimaryKey(
		long challengeEvaluationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationException {
		return getPersistence().findByPrimaryKey(challengeEvaluationId);
	}

	/**
	* Returns the challenge evaluation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param challengeEvaluationId the primary key of the challenge evaluation
	* @return the challenge evaluation, or <code>null</code> if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchByPrimaryKey(
		long challengeEvaluationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(challengeEvaluationId);
	}

	/**
	* Returns all the challenge evaluations.
	*
	* @return the challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the challenge evaluations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of challenge evaluations.
	*
	* @return the number of challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ChallengeEvaluationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ChallengeEvaluationPersistence)PortletBeanLocatorUtil.locate(kisti.edison.challenge.service.ClpSerializer.getServletContextName(),
					ChallengeEvaluationPersistence.class.getName());

			ReferenceRegistry.registerReference(ChallengeEvaluationUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ChallengeEvaluationPersistence persistence) {
	}

	private static ChallengeEvaluationPersistence _persistence;
}