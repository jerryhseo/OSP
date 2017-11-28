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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import edison.challenge.service.builder.model.ChildChallenge;

import java.util.List;

/**
 * The persistence utility for the child challenge service. This utility wraps {@link ChildChallengePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see ChildChallengePersistence
 * @see ChildChallengePersistenceImpl
 * @generated
 */
public class ChildChallengeUtil {
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
	public static void clearCache(ChildChallenge childChallenge) {
		getPersistence().clearCache(childChallenge);
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
	public static List<ChildChallenge> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChildChallenge> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChildChallenge> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ChildChallenge update(ChildChallenge childChallenge)
		throws SystemException {
		return getPersistence().update(childChallenge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ChildChallenge update(ChildChallenge childChallenge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(childChallenge, serviceContext);
	}

	/**
	* Returns all the child challenges where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychallengeCollect(
		long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBychallengeCollect(challengeid);
	}

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
	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychallengeCollect(
		long challengeid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBychallengeCollect(challengeid, start, end);
	}

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
	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychallengeCollect(
		long challengeid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBychallengeCollect(challengeid, start, end,
			orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge findBychallengeCollect_First(
		long challengeid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException {
		return getPersistence()
				   .findBychallengeCollect_First(challengeid, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge fetchBychallengeCollect_First(
		long challengeid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBychallengeCollect_First(challengeid, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge findBychallengeCollect_Last(
		long challengeid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException {
		return getPersistence()
				   .findBychallengeCollect_Last(challengeid, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge fetchBychallengeCollect_Last(
		long challengeid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBychallengeCollect_Last(challengeid, orderByComparator);
	}

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
	public static edison.challenge.service.builder.model.ChildChallenge[] findBychallengeCollect_PrevAndNext(
		long childid, long challengeid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException {
		return getPersistence()
				   .findBychallengeCollect_PrevAndNext(childid, challengeid,
			orderByComparator);
	}

	/**
	* Removes all the child challenges where challengeid = &#63; from the database.
	*
	* @param challengeid the challengeid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBychallengeCollect(long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBychallengeCollect(challengeid);
	}

	/**
	* Returns the number of child challenges where challengeid = &#63;.
	*
	* @param challengeid the challengeid
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countBychallengeCollect(long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBychallengeCollect(challengeid);
	}

	/**
	* Returns all the child challenges where childid = &#63;.
	*
	* @param childid the childid
	* @return the matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychildCollect(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBychildCollect(childid);
	}

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
	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychildCollect(
		long childid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBychildCollect(childid, start, end);
	}

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
	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> findBychildCollect(
		long childid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBychildCollect(childid, start, end, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge findBychildCollect_First(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException {
		return getPersistence()
				   .findBychildCollect_First(childid, orderByComparator);
	}

	/**
	* Returns the first child challenge in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge fetchBychildCollect_First(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBychildCollect_First(childid, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge findBychildCollect_Last(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException {
		return getPersistence()
				   .findBychildCollect_Last(childid, orderByComparator);
	}

	/**
	* Returns the last child challenge in the ordered set where childid = &#63;.
	*
	* @param childid the childid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge fetchBychildCollect_Last(
		long childid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBychildCollect_Last(childid, orderByComparator);
	}

	/**
	* Removes all the child challenges where childid = &#63; from the database.
	*
	* @param childid the childid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBychildCollect(long childid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBychildCollect(childid);
	}

	/**
	* Returns the number of child challenges where childid = &#63;.
	*
	* @param childid the childid
	* @return the number of matching child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countBychildCollect(long childid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBychildCollect(childid);
	}

	/**
	* Caches the child challenge in the entity cache if it is enabled.
	*
	* @param childChallenge the child challenge
	*/
	public static void cacheResult(
		edison.challenge.service.builder.model.ChildChallenge childChallenge) {
		getPersistence().cacheResult(childChallenge);
	}

	/**
	* Caches the child challenges in the entity cache if it is enabled.
	*
	* @param childChallenges the child challenges
	*/
	public static void cacheResult(
		java.util.List<edison.challenge.service.builder.model.ChildChallenge> childChallenges) {
		getPersistence().cacheResult(childChallenges);
	}

	/**
	* Creates a new child challenge with the primary key. Does not add the child challenge to the database.
	*
	* @param childid the primary key for the new child challenge
	* @return the new child challenge
	*/
	public static edison.challenge.service.builder.model.ChildChallenge create(
		long childid) {
		return getPersistence().create(childid);
	}

	/**
	* Removes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param childid the primary key of the child challenge
	* @return the child challenge that was removed
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge remove(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException {
		return getPersistence().remove(childid);
	}

	public static edison.challenge.service.builder.model.ChildChallenge updateImpl(
		edison.challenge.service.builder.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(childChallenge);
	}

	/**
	* Returns the child challenge with the primary key or throws a {@link edison.challenge.service.builder.NoSuchChildChallengeException} if it could not be found.
	*
	* @param childid the primary key of the child challenge
	* @return the child challenge
	* @throws edison.challenge.service.builder.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge findByPrimaryKey(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChildChallengeException {
		return getPersistence().findByPrimaryKey(childid);
	}

	/**
	* Returns the child challenge with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param childid the primary key of the child challenge
	* @return the child challenge, or <code>null</code> if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge fetchByPrimaryKey(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(childid);
	}

	/**
	* Returns all the child challenges.
	*
	* @return the child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the child challenges from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of child challenges.
	*
	* @return the number of child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ChildChallengePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ChildChallengePersistence)PortletBeanLocatorUtil.locate(edison.challenge.service.builder.service.ClpSerializer.getServletContextName(),
					ChildChallengePersistence.class.getName());

			ReferenceRegistry.registerReference(ChildChallengeUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ChildChallengePersistence persistence) {
	}

	private static ChildChallengePersistence _persistence;
}