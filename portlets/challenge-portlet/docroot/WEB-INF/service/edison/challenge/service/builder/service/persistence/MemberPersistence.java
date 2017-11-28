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

package edison.challenge.service.builder.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import edison.challenge.service.builder.model.Member;

/**
 * The persistence interface for the member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see MemberPersistenceImpl
 * @see MemberUtil
 * @generated
 */
public interface MemberPersistence extends BasePersistence<Member> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MemberUtil} to access the member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the members where teamid = &#63;.
	*
	* @param teamid the teamid
	* @return the matching members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Member> findByteamCollect(
		long teamid) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the members where teamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.MemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param teamid the teamid
	* @param start the lower bound of the range of members
	* @param end the upper bound of the range of members (not inclusive)
	* @return the range of matching members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Member> findByteamCollect(
		long teamid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the members where teamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.MemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param teamid the teamid
	* @param start the lower bound of the range of members
	* @param end the upper bound of the range of members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Member> findByteamCollect(
		long teamid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first member in the ordered set where teamid = &#63;.
	*
	* @param teamid the teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member
	* @throws edison.challenge.service.builder.NoSuchMemberException if a matching member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Member findByteamCollect_First(
		long teamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchMemberException;

	/**
	* Returns the first member in the ordered set where teamid = &#63;.
	*
	* @param teamid the teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member, or <code>null</code> if a matching member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Member fetchByteamCollect_First(
		long teamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last member in the ordered set where teamid = &#63;.
	*
	* @param teamid the teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member
	* @throws edison.challenge.service.builder.NoSuchMemberException if a matching member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Member findByteamCollect_Last(
		long teamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchMemberException;

	/**
	* Returns the last member in the ordered set where teamid = &#63;.
	*
	* @param teamid the teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member, or <code>null</code> if a matching member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Member fetchByteamCollect_Last(
		long teamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the members before and after the current member in the ordered set where teamid = &#63;.
	*
	* @param memberid the primary key of the current member
	* @param teamid the teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next member
	* @throws edison.challenge.service.builder.NoSuchMemberException if a member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Member[] findByteamCollect_PrevAndNext(
		java.lang.String memberid, long teamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchMemberException;

	/**
	* Removes all the members where teamid = &#63; from the database.
	*
	* @param teamid the teamid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByteamCollect(long teamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of members where teamid = &#63;.
	*
	* @param teamid the teamid
	* @return the number of matching members
	* @throws SystemException if a system exception occurred
	*/
	public int countByteamCollect(long teamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the member in the entity cache if it is enabled.
	*
	* @param member the member
	*/
	public void cacheResult(
		edison.challenge.service.builder.model.Member member);

	/**
	* Caches the members in the entity cache if it is enabled.
	*
	* @param members the members
	*/
	public void cacheResult(
		java.util.List<edison.challenge.service.builder.model.Member> members);

	/**
	* Creates a new member with the primary key. Does not add the member to the database.
	*
	* @param memberid the primary key for the new member
	* @return the new member
	*/
	public edison.challenge.service.builder.model.Member create(
		java.lang.String memberid);

	/**
	* Removes the member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberid the primary key of the member
	* @return the member that was removed
	* @throws edison.challenge.service.builder.NoSuchMemberException if a member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Member remove(
		java.lang.String memberid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchMemberException;

	public edison.challenge.service.builder.model.Member updateImpl(
		edison.challenge.service.builder.model.Member member)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the member with the primary key or throws a {@link edison.challenge.service.builder.NoSuchMemberException} if it could not be found.
	*
	* @param memberid the primary key of the member
	* @return the member
	* @throws edison.challenge.service.builder.NoSuchMemberException if a member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Member findByPrimaryKey(
		java.lang.String memberid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchMemberException;

	/**
	* Returns the member with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param memberid the primary key of the member
	* @return the member, or <code>null</code> if a member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Member fetchByPrimaryKey(
		java.lang.String memberid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the members.
	*
	* @return the members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Member> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.MemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of members
	* @param end the upper bound of the range of members (not inclusive)
	* @return the range of members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Member> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.MemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of members
	* @param end the upper bound of the range of members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Member> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the members from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of members.
	*
	* @return the number of members
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}