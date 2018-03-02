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

import kisti.edison.challenge.model.ChallengeEvaluationManagement;

import java.util.List;

/**
 * The persistence utility for the challenge evaluation management service. This utility wraps {@link ChallengeEvaluationManagementPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationManagementPersistence
 * @see ChallengeEvaluationManagementPersistenceImpl
 * @generated
 */
public class ChallengeEvaluationManagementUtil {
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
		ChallengeEvaluationManagement challengeEvaluationManagement) {
		getPersistence().clearCache(challengeEvaluationManagement);
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
	public static List<ChallengeEvaluationManagement> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChallengeEvaluationManagement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChallengeEvaluationManagement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ChallengeEvaluationManagement update(
		ChallengeEvaluationManagement challengeEvaluationManagement)
		throws SystemException {
		return getPersistence().update(challengeEvaluationManagement);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ChallengeEvaluationManagement update(
		ChallengeEvaluationManagement challengeEvaluationManagement,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(challengeEvaluationManagement, serviceContext);
	}

	/**
	* Returns all the challenge evaluation managements where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation management in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation management in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation management in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation management in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement[] findByUuid_PrevAndNext(
		long challengeEvaluationManagementId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence()
				   .findByUuid_PrevAndNext(challengeEvaluationManagementId,
			uuid, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation managements where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of challenge evaluation managements where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the challenge evaluation management where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationManagementException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the challenge evaluation management where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the challenge evaluation management where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the challenge evaluation management where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the challenge evaluation management that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of challenge evaluation managements where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the challenge evaluation managements where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement[] findByUuid_C_PrevAndNext(
		long challengeEvaluationManagementId, java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(challengeEvaluationManagementId,
			uuid, companyId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation managements where uuid = &#63; and companyId = &#63; from the database.
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
	* Returns the number of challenge evaluation managements where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the challenge evaluation managements where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation management in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation management in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation management in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation management in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement[] findByGroupId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(challengeEvaluationManagementId,
			groupId, orderByComparator);
	}

	/**
	* Returns all the challenge evaluation managements that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement[] filterFindByGroupId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(challengeEvaluationManagementId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation managements where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of challenge evaluation managements where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of challenge evaluation managements that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the challenge evaluation managements where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @return the matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChildChallengeId(groupId, childChallengeId);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChildChallengeId(groupId, childChallengeId,
			start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupIdAndChildChallengeId(groupId, childChallengeId,
			start, end, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement findByGroupIdAndChildChallengeId_First(
		long groupId, long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence()
				   .findByGroupIdAndChildChallengeId_First(groupId,
			childChallengeId, orderByComparator);
	}

	/**
	* Returns the first challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByGroupIdAndChildChallengeId_First(
		long groupId, long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChildChallengeId_First(groupId,
			childChallengeId, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement findByGroupIdAndChildChallengeId_Last(
		long groupId, long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence()
				   .findByGroupIdAndChildChallengeId_Last(groupId,
			childChallengeId, orderByComparator);
	}

	/**
	* Returns the last challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByGroupIdAndChildChallengeId_Last(
		long groupId, long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroupIdAndChildChallengeId_Last(groupId,
			childChallengeId, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement[] findByGroupIdAndChildChallengeId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence()
				   .findByGroupIdAndChildChallengeId_PrevAndNext(challengeEvaluationManagementId,
			groupId, childChallengeId, orderByComparator);
	}

	/**
	* Returns all the challenge evaluation managements that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @return the matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChildChallengeId(groupId,
			childChallengeId);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChildChallengeId(groupId,
			childChallengeId, start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> filterFindByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupIdAndChildChallengeId(groupId,
			childChallengeId, start, end, orderByComparator);
	}

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
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement[] filterFindByGroupIdAndChildChallengeId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		long childChallengeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence()
				   .filterFindByGroupIdAndChildChallengeId_PrevAndNext(challengeEvaluationManagementId,
			groupId, childChallengeId, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation managements where groupId = &#63; and childChallengeId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupIdAndChildChallengeId(long groupId,
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByGroupIdAndChildChallengeId(groupId, childChallengeId);
	}

	/**
	* Returns the number of challenge evaluation managements where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @return the number of matching challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupIdAndChildChallengeId(long groupId,
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroupIdAndChildChallengeId(groupId, childChallengeId);
	}

	/**
	* Returns the number of challenge evaluation managements that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	*
	* @param groupId the group ID
	* @param childChallengeId the child challenge ID
	* @return the number of matching challenge evaluation managements that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupIdAndChildChallengeId(long groupId,
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByGroupIdAndChildChallengeId(groupId,
			childChallengeId);
	}

	/**
	* Caches the challenge evaluation management in the entity cache if it is enabled.
	*
	* @param challengeEvaluationManagement the challenge evaluation management
	*/
	public static void cacheResult(
		kisti.edison.challenge.model.ChallengeEvaluationManagement challengeEvaluationManagement) {
		getPersistence().cacheResult(challengeEvaluationManagement);
	}

	/**
	* Caches the challenge evaluation managements in the entity cache if it is enabled.
	*
	* @param challengeEvaluationManagements the challenge evaluation managements
	*/
	public static void cacheResult(
		java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> challengeEvaluationManagements) {
		getPersistence().cacheResult(challengeEvaluationManagements);
	}

	/**
	* Creates a new challenge evaluation management with the primary key. Does not add the challenge evaluation management to the database.
	*
	* @param challengeEvaluationManagementId the primary key for the new challenge evaluation management
	* @return the new challenge evaluation management
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement create(
		long challengeEvaluationManagementId) {
		return getPersistence().create(challengeEvaluationManagementId);
	}

	/**
	* Removes the challenge evaluation management with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	* @return the challenge evaluation management that was removed
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement remove(
		long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence().remove(challengeEvaluationManagementId);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationManagement updateImpl(
		kisti.edison.challenge.model.ChallengeEvaluationManagement challengeEvaluationManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(challengeEvaluationManagement);
	}

	/**
	* Returns the challenge evaluation management with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationManagementException} if it could not be found.
	*
	* @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	* @return the challenge evaluation management
	* @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement findByPrimaryKey(
		long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeEvaluationManagementException {
		return getPersistence().findByPrimaryKey(challengeEvaluationManagementId);
	}

	/**
	* Returns the challenge evaluation management with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	* @return the challenge evaluation management, or <code>null</code> if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchByPrimaryKey(
		long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPrimaryKey(challengeEvaluationManagementId);
	}

	/**
	* Returns all the challenge evaluation managements.
	*
	* @return the challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the challenge evaluation managements from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of challenge evaluation managements.
	*
	* @return the number of challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ChallengeEvaluationManagementPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ChallengeEvaluationManagementPersistence)PortletBeanLocatorUtil.locate(kisti.edison.challenge.service.ClpSerializer.getServletContextName(),
					ChallengeEvaluationManagementPersistence.class.getName());

			ReferenceRegistry.registerReference(ChallengeEvaluationManagementUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(
		ChallengeEvaluationManagementPersistence persistence) {
	}

	private static ChallengeEvaluationManagementPersistence _persistence;
}