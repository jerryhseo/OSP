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

import edison.challenge.service.builder.model.Team;

/**
 * The persistence interface for the team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see TeamPersistenceImpl
 * @see TeamUtil
 * @generated
 */
public interface TeamPersistence extends BasePersistence<Team> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TeamUtil} to access the team persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the teams where childid = &#63;.
	*
	* @param childid the childid
	* @return the matching teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Team> findBychildCollect(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the teams where childid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param childid the childid
	* @param start the lower bound of the range of teams
	* @param end the upper bound of the range of teams (not inclusive)
	* @return the range of matching teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Team> findBychildCollect(
		long childid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the teams where childid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param childid the childid
	* @param start the lower bound of the range of teams
	* @param end the upper bound of the range of teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Team> findBychildCollect(
		long childid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first team in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching team
	* @throws edison.challenge.service.builder.NoSuchTeamException if a matching team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team findBychildCollect_First(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchTeamException;

	/**
	* Returns the first team in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching team, or <code>null</code> if a matching team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team fetchBychildCollect_First(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last team in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching team
	* @throws edison.challenge.service.builder.NoSuchTeamException if a matching team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team findBychildCollect_Last(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchTeamException;

	/**
	* Returns the last team in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching team, or <code>null</code> if a matching team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team fetchBychildCollect_Last(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the teams before and after the current team in the ordered set where childid = &#63;.
	*
	* @param teamid the primary key of the current team
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next team
	* @throws edison.challenge.service.builder.NoSuchTeamException if a team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team[] findBychildCollect_PrevAndNext(
		long teamid, long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchTeamException;

	/**
	* Removes all the teams where childid = &#63; from the database.
	*
	* @param childid the childid
	* @throws SystemException if a system exception occurred
	*/
	public void removeBychildCollect(long childid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of teams where childid = &#63;.
	*
	* @param childid the childid
	* @return the number of matching teams
	* @throws SystemException if a system exception occurred
	*/
	public int countBychildCollect(long childid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the teams where teamid = &#63;.
	*
	* @param teamid the teamid
	* @return the matching teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Team> findByteamCollect(
		long teamid) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the teams where teamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param teamid the teamid
	* @param start the lower bound of the range of teams
	* @param end the upper bound of the range of teams (not inclusive)
	* @return the range of matching teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Team> findByteamCollect(
		long teamid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the teams where teamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param teamid the teamid
	* @param start the lower bound of the range of teams
	* @param end the upper bound of the range of teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Team> findByteamCollect(
		long teamid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first team in the ordered set where teamid = &#63;.
	*
	* @param teamid the teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching team
	* @throws edison.challenge.service.builder.NoSuchTeamException if a matching team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team findByteamCollect_First(
		long teamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchTeamException;

	/**
	* Returns the first team in the ordered set where teamid = &#63;.
	*
	* @param teamid the teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching team, or <code>null</code> if a matching team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team fetchByteamCollect_First(
		long teamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last team in the ordered set where teamid = &#63;.
	*
	* @param teamid the teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching team
	* @throws edison.challenge.service.builder.NoSuchTeamException if a matching team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team findByteamCollect_Last(
		long teamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchTeamException;

	/**
	* Returns the last team in the ordered set where teamid = &#63;.
	*
	* @param teamid the teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching team, or <code>null</code> if a matching team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team fetchByteamCollect_Last(
		long teamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the teams where teamid = &#63; from the database.
	*
	* @param teamid the teamid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByteamCollect(long teamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of teams where teamid = &#63;.
	*
	* @param teamid the teamid
	* @return the number of matching teams
	* @throws SystemException if a system exception occurred
	*/
	public int countByteamCollect(long teamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the team in the entity cache if it is enabled.
	*
	* @param team the team
	*/
	public void cacheResult(edison.challenge.service.builder.model.Team team);

	/**
	* Caches the teams in the entity cache if it is enabled.
	*
	* @param teams the teams
	*/
	public void cacheResult(
		java.util.List<edison.challenge.service.builder.model.Team> teams);

	/**
	* Creates a new team with the primary key. Does not add the team to the database.
	*
	* @param teamid the primary key for the new team
	* @return the new team
	*/
	public edison.challenge.service.builder.model.Team create(long teamid);

	/**
	* Removes the team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param teamid the primary key of the team
	* @return the team that was removed
	* @throws edison.challenge.service.builder.NoSuchTeamException if a team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team remove(long teamid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchTeamException;

	public edison.challenge.service.builder.model.Team updateImpl(
		edison.challenge.service.builder.model.Team team)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the team with the primary key or throws a {@link edison.challenge.service.builder.NoSuchTeamException} if it could not be found.
	*
	* @param teamid the primary key of the team
	* @return the team
	* @throws edison.challenge.service.builder.NoSuchTeamException if a team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team findByPrimaryKey(
		long teamid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchTeamException;

	/**
	* Returns the team with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param teamid the primary key of the team
	* @return the team, or <code>null</code> if a team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Team fetchByPrimaryKey(
		long teamid) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the teams.
	*
	* @return the teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Team> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of teams
	* @param end the upper bound of the range of teams (not inclusive)
	* @return the range of teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Team> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of teams
	* @param end the upper bound of the range of teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Team> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the teams from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of teams.
	*
	* @return the number of teams
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}