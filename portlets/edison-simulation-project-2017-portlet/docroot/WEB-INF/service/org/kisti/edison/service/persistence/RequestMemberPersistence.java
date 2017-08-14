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

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.model.RequestMember;

/**
 * The persistence interface for the request member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see RequestMemberPersistenceImpl
 * @see RequestMemberUtil
 * @generated
 */
public interface RequestMemberPersistence extends BasePersistence<RequestMember> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RequestMemberUtil} to access the request member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the request members where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @return the matching request members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectId(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectId(
		long simulationProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectId(
		long simulationProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first request member in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching request member
	* @throws org.kisti.edison.NoSuchRequestMemberException if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.RequestMember findBySimulationProjectId_First(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException;

	/**
	* Returns the first request member in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching request member, or <code>null</code> if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.RequestMember fetchBySimulationProjectId_First(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last request member in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching request member
	* @throws org.kisti.edison.NoSuchRequestMemberException if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.RequestMember findBySimulationProjectId_Last(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException;

	/**
	* Returns the last request member in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching request member, or <code>null</code> if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.RequestMember fetchBySimulationProjectId_Last(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public org.kisti.edison.model.RequestMember[] findBySimulationProjectId_PrevAndNext(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK,
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException;

	/**
	* Removes all the request members where simulationProjectId = &#63; from the database.
	*
	* @param simulationProjectId the simulation project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySimulationProjectId(long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of request members where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @return the number of matching request members
	* @throws SystemException if a system exception occurred
	*/
	public int countBySimulationProjectId(long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the request members where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @return the matching request members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectIdAndUseId(
		java.lang.Long userId, long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectIdAndUseId(
		java.lang.Long userId, long simulationProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.RequestMember> findBySimulationProjectIdAndUseId(
		java.lang.Long userId, long simulationProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public org.kisti.edison.model.RequestMember findBySimulationProjectIdAndUseId_First(
		java.lang.Long userId, long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException;

	/**
	* Returns the first request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching request member, or <code>null</code> if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.RequestMember fetchBySimulationProjectIdAndUseId_First(
		java.lang.Long userId, long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public org.kisti.edison.model.RequestMember findBySimulationProjectIdAndUseId_Last(
		java.lang.Long userId, long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException;

	/**
	* Returns the last request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching request member, or <code>null</code> if a matching request member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.RequestMember fetchBySimulationProjectIdAndUseId_Last(
		java.lang.Long userId, long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public org.kisti.edison.model.RequestMember[] findBySimulationProjectIdAndUseId_PrevAndNext(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK,
		java.lang.Long userId, long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException;

	/**
	* Removes all the request members where userId = &#63; and simulationProjectId = &#63; from the database.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySimulationProjectIdAndUseId(java.lang.Long userId,
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of request members where userId = &#63; and simulationProjectId = &#63;.
	*
	* @param userId the user ID
	* @param simulationProjectId the simulation project ID
	* @return the number of matching request members
	* @throws SystemException if a system exception occurred
	*/
	public int countBySimulationProjectIdAndUseId(java.lang.Long userId,
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the request member in the entity cache if it is enabled.
	*
	* @param requestMember the request member
	*/
	public void cacheResult(org.kisti.edison.model.RequestMember requestMember);

	/**
	* Caches the request members in the entity cache if it is enabled.
	*
	* @param requestMembers the request members
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.model.RequestMember> requestMembers);

	/**
	* Creates a new request member with the primary key. Does not add the request member to the database.
	*
	* @param requestMemberPK the primary key for the new request member
	* @return the new request member
	*/
	public org.kisti.edison.model.RequestMember create(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK);

	/**
	* Removes the request member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param requestMemberPK the primary key of the request member
	* @return the request member that was removed
	* @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.RequestMember remove(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException;

	public org.kisti.edison.model.RequestMember updateImpl(
		org.kisti.edison.model.RequestMember requestMember)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the request member with the primary key or throws a {@link org.kisti.edison.NoSuchRequestMemberException} if it could not be found.
	*
	* @param requestMemberPK the primary key of the request member
	* @return the request member
	* @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.RequestMember findByPrimaryKey(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchRequestMemberException;

	/**
	* Returns the request member with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param requestMemberPK the primary key of the request member
	* @return the request member, or <code>null</code> if a request member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.RequestMember fetchByPrimaryKey(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the request members.
	*
	* @return the request members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.RequestMember> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.RequestMember> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.RequestMember> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the request members from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of request members.
	*
	* @return the number of request members
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}