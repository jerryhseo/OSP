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

import edison.challenge.service.builder.model.Awardhistory;

/**
 * The persistence interface for the awardhistory service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see AwardhistoryPersistenceImpl
 * @see AwardhistoryUtil
 * @generated
 */
public interface AwardhistoryPersistence extends BasePersistence<Awardhistory> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AwardhistoryUtil} to access the awardhistory persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the awardhistories where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @return the matching awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Awardhistory> findByteamCollect(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the awardhistories where chTeamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardhistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param chTeamid the ch teamid
	* @param start the lower bound of the range of awardhistories
	* @param end the upper bound of the range of awardhistories (not inclusive)
	* @return the range of matching awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Awardhistory> findByteamCollect(
		long chTeamid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the awardhistories where chTeamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardhistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param chTeamid the ch teamid
	* @param start the lower bound of the range of awardhistories
	* @param end the upper bound of the range of awardhistories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Awardhistory> findByteamCollect(
		long chTeamid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first awardhistory in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching awardhistory
	* @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a matching awardhistory could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Awardhistory findByteamCollect_First(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAwardhistoryException;

	/**
	* Returns the first awardhistory in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching awardhistory, or <code>null</code> if a matching awardhistory could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Awardhistory fetchByteamCollect_First(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last awardhistory in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching awardhistory
	* @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a matching awardhistory could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Awardhistory findByteamCollect_Last(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAwardhistoryException;

	/**
	* Returns the last awardhistory in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching awardhistory, or <code>null</code> if a matching awardhistory could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Awardhistory fetchByteamCollect_Last(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the awardhistories before and after the current awardhistory in the ordered set where chTeamid = &#63;.
	*
	* @param awardhistoryid the primary key of the current awardhistory
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next awardhistory
	* @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a awardhistory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Awardhistory[] findByteamCollect_PrevAndNext(
		long awardhistoryid, long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAwardhistoryException;

	/**
	* Removes all the awardhistories where chTeamid = &#63; from the database.
	*
	* @param chTeamid the ch teamid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByteamCollect(long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of awardhistories where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @return the number of matching awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public int countByteamCollect(long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the awardhistory in the entity cache if it is enabled.
	*
	* @param awardhistory the awardhistory
	*/
	public void cacheResult(
		edison.challenge.service.builder.model.Awardhistory awardhistory);

	/**
	* Caches the awardhistories in the entity cache if it is enabled.
	*
	* @param awardhistories the awardhistories
	*/
	public void cacheResult(
		java.util.List<edison.challenge.service.builder.model.Awardhistory> awardhistories);

	/**
	* Creates a new awardhistory with the primary key. Does not add the awardhistory to the database.
	*
	* @param awardhistoryid the primary key for the new awardhistory
	* @return the new awardhistory
	*/
	public edison.challenge.service.builder.model.Awardhistory create(
		long awardhistoryid);

	/**
	* Removes the awardhistory with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param awardhistoryid the primary key of the awardhistory
	* @return the awardhistory that was removed
	* @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a awardhistory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Awardhistory remove(
		long awardhistoryid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAwardhistoryException;

	public edison.challenge.service.builder.model.Awardhistory updateImpl(
		edison.challenge.service.builder.model.Awardhistory awardhistory)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the awardhistory with the primary key or throws a {@link edison.challenge.service.builder.NoSuchAwardhistoryException} if it could not be found.
	*
	* @param awardhistoryid the primary key of the awardhistory
	* @return the awardhistory
	* @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a awardhistory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Awardhistory findByPrimaryKey(
		long awardhistoryid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchAwardhistoryException;

	/**
	* Returns the awardhistory with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param awardhistoryid the primary key of the awardhistory
	* @return the awardhistory, or <code>null</code> if a awardhistory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Awardhistory fetchByPrimaryKey(
		long awardhistoryid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the awardhistories.
	*
	* @return the awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Awardhistory> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the awardhistories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardhistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of awardhistories
	* @param end the upper bound of the range of awardhistories (not inclusive)
	* @return the range of awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Awardhistory> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the awardhistories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardhistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of awardhistories
	* @param end the upper bound of the range of awardhistories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Awardhistory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the awardhistories from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of awardhistories.
	*
	* @return the number of awardhistories
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}