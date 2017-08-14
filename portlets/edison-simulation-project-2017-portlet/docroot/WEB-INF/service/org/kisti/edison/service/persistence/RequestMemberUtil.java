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

package org.kisti.edison.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.model.RequestMember;

import java.util.List;

/**
 * The persistence utility for the request member service. This utility wraps {@link RequestMemberPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see RequestMemberPersistence
 * @see RequestMemberPersistenceImpl
 * @generated
 */
public class RequestMemberUtil {
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
	public static void clearCache(RequestMember requestMember) {
		getPersistence().clearCache(requestMember);
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
	public static List<RequestMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RequestMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RequestMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static RequestMember update(RequestMember requestMember)
		throws SystemException {
		return getPersistence().update(requestMember);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static RequestMember update(RequestMember requestMember,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(requestMember, serviceContext);
	}

	/**
	* Returns all the request members where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @return the matching request members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectId(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySimulationProjectId(simulationProjectId);
	}

	/**
	* Returns a range of all the request members where simulationProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationProjectId the simulation project ID
	* @param start the lower bound of the range of request members
	* @param end the upper bound of the range of request members (not inclusive)
	* @return the range of matching request members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectId(
		long simulationProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationProjectId(simulationProjectId, start, end);
	}

	/**
	* Returns an ordered range of all the request members where simulationProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationProjectId the simulation project ID
	* @param start the lower bound of the range of request members
	* @param end the upper bound of the range of request members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching request members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectId(
		long simulationProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationProjectId(simulationProjectId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first request member in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching request member
	* @throws org.kisti.edison.NoSuchRequestMemberException if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember findBySimulationProjectId_First(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException {
		return getPersistence()
				   .findBySimulationProjectId_First(simulationProjectId,
			orderByComparator);
	}

	/**
	* Returns the first request member in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching request member, or <code>null</code> if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember fetchBySimulationProjectId_First(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySimulationProjectId_First(simulationProjectId,
			orderByComparator);
	}

	/**
	* Returns the last request member in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching request member
	* @throws org.kisti.edison.NoSuchRequestMemberException if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember findBySimulationProjectId_Last(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException {
		return getPersistence()
				   .findBySimulationProjectId_Last(simulationProjectId,
			orderByComparator);
	}

	/**
	* Returns the last request member in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching request member, or <code>null</code> if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember fetchBySimulationProjectId_Last(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySimulationProjectId_Last(simulationProjectId,
			orderByComparator);
	}

	/**
	* Returns the request members before and after the current request member in the ordered set where simulationProjectId = &#63;.
	*
	* @param requestMemberPK the primary key of the current request member
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next request member
	* @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember[] findBySimulationProjectId_PrevAndNext(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK,
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException {
		return getPersistence()
				   .findBySimulationProjectId_PrevAndNext(requestMemberPK,
			simulationProjectId, orderByComparator);
	}

	/**
	* Removes all the request members where simulationProjectId = &#63; from the database.
	*
	* @param simulationProjectId the simulation project ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySimulationProjectId(long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySimulationProjectId(simulationProjectId);
	}

	/**
	* Returns the number of request members where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @return the number of matching request members
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySimulationProjectId(long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySimulationProjectId(simulationProjectId);
	}

	/**
	* Returns all the request members where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @return the matching request members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectIdAndUseId(
		java.lang.Long userId, long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationProjectIdAndUseId(userId,
			simulationProjectId);
	}

	/**
	* Returns a range of all the request members where userId = &#63; and simulationProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @param start the lower bound of the range of request members
	* @param end the upper bound of the range of request members (not inclusive)
	* @return the range of matching request members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectIdAndUseId(
		java.lang.Long userId, long simulationProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationProjectIdAndUseId(userId,
			simulationProjectId, start, end);
	}

	/**
	* Returns an ordered range of all the request members where userId = &#63; and simulationProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @param start the lower bound of the range of request members
	* @param end the upper bound of the range of request members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching request members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectIdAndUseId(
		java.lang.Long userId, long simulationProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationProjectIdAndUseId(userId,
			simulationProjectId, start, end, orderByComparator);
	}

	/**
	* Returns the first request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching request member
	* @throws org.kisti.edison.NoSuchRequestMemberException if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember findBySimulationProjectIdAndUseId_First(
		java.lang.Long userId, long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException {
		return getPersistence()
				   .findBySimulationProjectIdAndUseId_First(userId,
			simulationProjectId, orderByComparator);
	}

	/**
	* Returns the first request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching request member, or <code>null</code> if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember fetchBySimulationProjectIdAndUseId_First(
		java.lang.Long userId, long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySimulationProjectIdAndUseId_First(userId,
			simulationProjectId, orderByComparator);
	}

	/**
	* Returns the last request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching request member
	* @throws org.kisti.edison.NoSuchRequestMemberException if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember findBySimulationProjectIdAndUseId_Last(
		java.lang.Long userId, long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException {
		return getPersistence()
				   .findBySimulationProjectIdAndUseId_Last(userId,
			simulationProjectId, orderByComparator);
	}

	/**
	* Returns the last request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching request member, or <code>null</code> if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember fetchBySimulationProjectIdAndUseId_Last(
		java.lang.Long userId, long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySimulationProjectIdAndUseId_Last(userId,
			simulationProjectId, orderByComparator);
	}

	/**
	* Returns the request members before and after the current request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param requestMemberPK the primary key of the current request member
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next request member
	* @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember[] findBySimulationProjectIdAndUseId_PrevAndNext(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK,
		java.lang.Long userId, long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException {
		return getPersistence()
				   .findBySimulationProjectIdAndUseId_PrevAndNext(requestMemberPK,
			userId, simulationProjectId, orderByComparator);
	}

	/**
	* Removes all the request members where userId = &#63; and simulationProjectId = &#63; from the database.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySimulationProjectIdAndUseId(
		java.lang.Long userId, long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeBySimulationProjectIdAndUseId(userId, simulationProjectId);
	}

	/**
	* Returns the number of request members where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @return the number of matching request members
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySimulationProjectIdAndUseId(
		java.lang.Long userId, long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBySimulationProjectIdAndUseId(userId,
			simulationProjectId);
	}

	/**
	* Caches the request member in the entity cache if it is enabled.
	*
	* @param requestMember the request member
	*/
	public static void cacheResult(
		org.kisti.edison.model.RequestMember requestMember) {
		getPersistence().cacheResult(requestMember);
	}

	/**
	* Caches the request members in the entity cache if it is enabled.
	*
	* @param requestMembers the request members
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.model.RequestMember> requestMembers) {
		getPersistence().cacheResult(requestMembers);
	}

	/**
	* Creates a new request member with the primary key. Does not add the request member to the database.
	*
	* @param requestMemberPK the primary key for the new request member
	* @return the new request member
	*/
	public static org.kisti.edison.model.RequestMember create(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK) {
		return getPersistence().create(requestMemberPK);
	}

	/**
	* Removes the request member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param requestMemberPK the primary key of the request member
	* @return the request member that was removed
	* @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember remove(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException {
		return getPersistence().remove(requestMemberPK);
	}

	public static org.kisti.edison.model.RequestMember updateImpl(
		org.kisti.edison.model.RequestMember requestMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(requestMember);
	}

	/**
	* Returns the request member with the primary key or throws a {@link org.kisti.edison.NoSuchRequestMemberException} if it could not be found.
	*
	* @param requestMemberPK the primary key of the request member
	* @return the request member
	* @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember findByPrimaryKey(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException {
		return getPersistence().findByPrimaryKey(requestMemberPK);
	}

	/**
	* Returns the request member with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param requestMemberPK the primary key of the request member
	* @return the request member, or <code>null</code> if a request member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember fetchByPrimaryKey(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(requestMemberPK);
	}

	/**
	* Returns all the request members.
	*
	* @return the request members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.RequestMember> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the request members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of request members
	* @param end the upper bound of the range of request members (not inclusive)
	* @return the range of request members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.RequestMember> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the request members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of request members
	* @param end the upper bound of the range of request members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of request members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.RequestMember> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the request members from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of request members.
	*
	* @return the number of request members
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static RequestMemberPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (RequestMemberPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					RequestMemberPersistence.class.getName());

			ReferenceRegistry.registerReference(RequestMemberUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(RequestMemberPersistence persistence) {
	}

	private static RequestMemberPersistence _persistence;
}