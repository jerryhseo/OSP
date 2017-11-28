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

import edison.challenge.service.builder.model.ChallengeTeam;

/**
 * The persistence interface for the challenge team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see ChallengeTeamPersistenceImpl
 * @see ChallengeTeamUtil
 * @generated
 */
public interface ChallengeTeamPersistence extends BasePersistence<ChallengeTeam> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChallengeTeamUtil} to access the challenge team persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the challenge teams where childid = &#63;.
	*
	* @param childid the childid
	* @return the matching challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> findBychildCollect(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge teams where childid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param childid the childid
	* @param start the lower bound of the range of challenge teams
	* @param end the upper bound of the range of challenge teams (not inclusive)
	* @return the range of matching challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> findBychildCollect(
		long childid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge teams where childid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param childid the childid
	* @param start the lower bound of the range of challenge teams
	* @param end the upper bound of the range of challenge teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> findBychildCollect(
		long childid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge team in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team
	* @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam findBychildCollect_First(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeTeamException;

	/**
	* Returns the first challenge team in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team, or <code>null</code> if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam fetchBychildCollect_First(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge team in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team
	* @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam findBychildCollect_Last(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeTeamException;

	/**
	* Returns the last challenge team in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team, or <code>null</code> if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam fetchBychildCollect_Last(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge teams before and after the current challenge team in the ordered set where childid = &#63;.
	*
	* @param chTeamid the primary key of the current challenge team
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge team
	* @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam[] findBychildCollect_PrevAndNext(
		long chTeamid, long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeTeamException;

	/**
	* Removes all the challenge teams where childid = &#63; from the database.
	*
	* @param childid the childid
	* @throws SystemException if a system exception occurred
	*/
	public void removeBychildCollect(long childid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge teams where childid = &#63;.
	*
	* @param childid the childid
	* @return the number of matching challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public int countBychildCollect(long childid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge teams where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @return the matching challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> findByteamCollect(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge teams where chTeamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param chTeamid the ch teamid
	* @param start the lower bound of the range of challenge teams
	* @param end the upper bound of the range of challenge teams (not inclusive)
	* @return the range of matching challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> findByteamCollect(
		long chTeamid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge teams where chTeamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param chTeamid the ch teamid
	* @param start the lower bound of the range of challenge teams
	* @param end the upper bound of the range of challenge teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> findByteamCollect(
		long chTeamid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first challenge team in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team
	* @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam findByteamCollect_First(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeTeamException;

	/**
	* Returns the first challenge team in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge team, or <code>null</code> if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam fetchByteamCollect_First(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last challenge team in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team
	* @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam findByteamCollect_Last(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeTeamException;

	/**
	* Returns the last challenge team in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge team, or <code>null</code> if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam fetchByteamCollect_Last(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the challenge teams where chTeamid = &#63; from the database.
	*
	* @param chTeamid the ch teamid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByteamCollect(long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge teams where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @return the number of matching challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public int countByteamCollect(long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the challenge team in the entity cache if it is enabled.
	*
	* @param challengeTeam the challenge team
	*/
	public void cacheResult(
		edison.challenge.service.builder.model.ChallengeTeam challengeTeam);

	/**
	* Caches the challenge teams in the entity cache if it is enabled.
	*
	* @param challengeTeams the challenge teams
	*/
	public void cacheResult(
		java.util.List<edison.challenge.service.builder.model.ChallengeTeam> challengeTeams);

	/**
	* Creates a new challenge team with the primary key. Does not add the challenge team to the database.
	*
	* @param chTeamid the primary key for the new challenge team
	* @return the new challenge team
	*/
	public edison.challenge.service.builder.model.ChallengeTeam create(
		long chTeamid);

	/**
	* Removes the challenge team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chTeamid the primary key of the challenge team
	* @return the challenge team that was removed
	* @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam remove(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeTeamException;

	public edison.challenge.service.builder.model.ChallengeTeam updateImpl(
		edison.challenge.service.builder.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge team with the primary key or throws a {@link edison.challenge.service.builder.NoSuchChallengeTeamException} if it could not be found.
	*
	* @param chTeamid the primary key of the challenge team
	* @return the challenge team
	* @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam findByPrimaryKey(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeTeamException;

	/**
	* Returns the challenge team with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chTeamid the primary key of the challenge team
	* @return the challenge team, or <code>null</code> if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChallengeTeam fetchByPrimaryKey(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenge teams.
	*
	* @return the challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenge teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge teams
	* @param end the upper bound of the range of challenge teams (not inclusive)
	* @return the range of challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenge teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge teams
	* @param end the upper bound of the range of challenge teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the challenge teams from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge teams.
	*
	* @return the number of challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}