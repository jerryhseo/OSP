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

import edison.challenge.service.builder.model.Challenge;

/**
 * The persistence interface for the challenge service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see ChallengePersistenceImpl
 * @see ChallengeUtil
 * @generated
 */
public interface ChallengePersistence extends BasePersistence<Challenge> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChallengeUtil} to access the challenge persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the challenge in the entity cache if it is enabled.
	*
	* @param challenge the challenge
	*/
	public void cacheResult(
		edison.challenge.service.builder.model.Challenge challenge);

	/**
	* Caches the challenges in the entity cache if it is enabled.
	*
	* @param challenges the challenges
	*/
	public void cacheResult(
		java.util.List<edison.challenge.service.builder.model.Challenge> challenges);

	/**
	* Creates a new challenge with the primary key. Does not add the challenge to the database.
	*
	* @param challengeid the primary key for the new challenge
	* @return the new challenge
	*/
	public edison.challenge.service.builder.model.Challenge create(
		long challengeid);

	/**
	* Removes the challenge with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeid the primary key of the challenge
	* @return the challenge that was removed
	* @throws edison.challenge.service.builder.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Challenge remove(
		long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeException;

	public edison.challenge.service.builder.model.Challenge updateImpl(
		edison.challenge.service.builder.model.Challenge challenge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge with the primary key or throws a {@link edison.challenge.service.builder.NoSuchChallengeException} if it could not be found.
	*
	* @param challengeid the primary key of the challenge
	* @return the challenge
	* @throws edison.challenge.service.builder.NoSuchChallengeException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Challenge findByPrimaryKey(
		long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeException;

	/**
	* Returns the challenge with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param challengeid the primary key of the challenge
	* @return the challenge, or <code>null</code> if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public edison.challenge.service.builder.model.Challenge fetchByPrimaryKey(
		long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the challenges.
	*
	* @return the challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Challenge> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Challenge> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of challenges
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<edison.challenge.service.builder.model.Challenge> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the challenges from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenges.
	*
	* @return the number of challenges
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}