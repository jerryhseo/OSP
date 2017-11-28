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

import edison.challenge.service.builder.model.ChildChallenge;

/**
 * The persistence interface for the child challenge service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see ChildChallengePersistenceImpl
 * @see ChildChallengeUtil
 * @generated
 */
public interface ChildChallengePersistence extends BasePersistence<ChildChallenge> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChildChallengeUtil} to access the child challenge persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the child challenges where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychallengeCollect(
		long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the child challenges where challengeid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param challengeid the challengeid
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychallengeCollect(
		long challengeid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the child challenges where challengeid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param challengeid the challengeid
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychallengeCollect(
		long challengeid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first child challenge in the ordered set where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge findBychallengeCollect_First(
		long challengeid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException;

	/**
	* Returns the first child challenge in the ordered set where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge fetchBychallengeCollect_First(
		long challengeid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last child challenge in the ordered set where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge findBychallengeCollect_Last(
		long challengeid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException;

	/**
	* Returns the last child challenge in the ordered set where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge fetchBychallengeCollect_Last(
		long challengeid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the child challenges before and after the current child challenge in the ordered set where challengeid = &#63;.
	*
	* @param childid the primary key of the current child challenge
	* @param challengeid the challengeid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge[] findBychallengeCollect_PrevAndNext(
		long childid, long challengeid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException;

	/**
	* Removes all the child challenges where challengeid = &#63; from the database.
	*
	* @param challengeid the challengeid
	* @throws SystemException if a system exception occurred
	*/
	public void removeBychallengeCollect(long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countBychallengeCollect(long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the child challenges where childid = &#63;.
	*
	* @param childid the childid
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychildCollect(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the child challenges where childid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param childid the childid
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychildCollect(
		long childid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the child challenges where childid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param childid the childid
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychildCollect(
		long childid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first child challenge in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge findBychildCollect_First(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException;

	/**
	* Returns the first child challenge in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge fetchBychildCollect_First(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last child challenge in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge findBychildCollect_Last(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException;

	/**
	* Returns the last child challenge in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge fetchBychildCollect_Last(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the child challenges where childid = &#63; from the database.
	*
	* @param childid the childid
	* @throws SystemException if a system exception occurred
	*/
	public void removeBychildCollect(long childid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges where childid = &#63;.
	*
	* @param childid the childid
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countBychildCollect(long childid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the child challenge in the entity cache if it is enabled.
	*
	* @param childChallenge the child challenge
	*/
	public void cacheResult(
		edison.challenge.service.builder.model.ChildChallenge childChallenge);

	/**
	* Caches the child challenges in the entity cache if it is enabled.
	*
	* @param childChallenges the child challenges
	*/
	public void cacheResult(
		java.util.List<edison.challenge.service.builder.model.ChildChallenge> childChallenges);

	/**
	* Creates a new child challenge with the primary key. Does not add the child challenge to the database.
	*
	* @param childid the primary key for the new child challenge
	* @return the new child challenge
	*/
	public edison.challenge.service.builder.model.ChildChallenge create(
		long childid);

	/**
	* Removes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param childid the primary key of the child challenge
	* @return the child challenge that was removed
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge remove(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException;

	public edison.challenge.service.builder.model.ChildChallenge updateImpl(
		edison.challenge.service.builder.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the child challenge with the primary key or throws a {@link edison.challenge.service.builder.NoSuchChildChallengeException} if it could not be found.
	*
	* @param childid the primary key of the child challenge
	* @return the child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge findByPrimaryKey(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException;

	/**
	* Returns the child challenge with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param childid the primary key of the child challenge
	* @return the child challenge, or <code>null</code> if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.ChildChallenge fetchByPrimaryKey(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the child challenges.
	*
	* @return the child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the child challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the child challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of child challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the child challenges from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of child challenges.
	*
	* @return the number of child challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}