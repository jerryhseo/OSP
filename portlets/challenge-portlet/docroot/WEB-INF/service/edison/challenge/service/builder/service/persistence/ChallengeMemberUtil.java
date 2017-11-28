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

import edison.challenge.service.builder.model.ChallengeMember;

import java.util.List;

/**
 * The persistence utility for the challenge member service. This utility wraps {@link ChallengeMemberPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see ChallengeMemberPersistence
 * @see ChallengeMemberPersistenceImpl
 * @generated
 */
public class ChallengeMemberUtil {
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
	public static void clearCache(ChallengeMember challengeMember) {
		getPersistence().clearCache(challengeMember);
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
	public static List<ChallengeMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChallengeMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChallengeMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ChallengeMember update(ChallengeMember challengeMember)
		throws SystemException {
		return getPersistence().update(challengeMember);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ChallengeMember update(ChallengeMember challengeMember,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(challengeMember, serviceContext);
	}

	/**
	* Returns all the challenge members where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @return the matching challenge members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.ChallengeMember> findByteamCollect(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByteamCollect(chTeamid);
	}

	/**
	* Returns a range of all the challenge members where chTeamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param chTeamid the ch teamid
	* @param start the lower bound of the range of challenge members
	* @param end the upper bound of the range of challenge members (not inclusive)
	* @return the range of matching challenge members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.ChallengeMember> findByteamCollect(
		long chTeamid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByteamCollect(chTeamid, start, end);
	}

	/**
	* Returns an ordered range of all the challenge members where chTeamid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param chTeamid the ch teamid
	* @param start the lower bound of the range of challenge members
	* @param end the upper bound of the range of challenge members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching challenge members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.ChallengeMember> findByteamCollect(
		long chTeamid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByteamCollect(chTeamid, start, end, orderByComparator);
	}

	/**
	* Returns the first challenge member in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge member
	* @throws edison.challenge.service.builder.NoSuchChallengeMemberException if a matching challenge member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeMember findByteamCollect_First(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeMemberException {
		return getPersistence()
				   .findByteamCollect_First(chTeamid, orderByComparator);
	}

	/**
	* Returns the first challenge member in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching challenge member, or <code>null</code> if a matching challenge member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeMember fetchByteamCollect_First(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByteamCollect_First(chTeamid, orderByComparator);
	}

	/**
	* Returns the last challenge member in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge member
	* @throws edison.challenge.service.builder.NoSuchChallengeMemberException if a matching challenge member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeMember findByteamCollect_Last(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeMemberException {
		return getPersistence()
				   .findByteamCollect_Last(chTeamid, orderByComparator);
	}

	/**
	* Returns the last challenge member in the ordered set where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching challenge member, or <code>null</code> if a matching challenge member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeMember fetchByteamCollect_Last(
		long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByteamCollect_Last(chTeamid, orderByComparator);
	}

	/**
	* Returns the challenge members before and after the current challenge member in the ordered set where chTeamid = &#63;.
	*
	* @param chMemberid the primary key of the current challenge member
	* @param chTeamid the ch teamid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next challenge member
	* @throws edison.challenge.service.builder.NoSuchChallengeMemberException if a challenge member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeMember[] findByteamCollect_PrevAndNext(
		long chMemberid, long chTeamid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeMemberException {
		return getPersistence()
				   .findByteamCollect_PrevAndNext(chMemberid, chTeamid,
			orderByComparator);
	}

	/**
	* Removes all the challenge members where chTeamid = &#63; from the database.
	*
	* @param chTeamid the ch teamid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByteamCollect(long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByteamCollect(chTeamid);
	}

	/**
	* Returns the number of challenge members where chTeamid = &#63;.
	*
	* @param chTeamid the ch teamid
	* @return the number of matching challenge members
	* @throws SystemException if a system exception occurred
	*/
	public static int countByteamCollect(long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByteamCollect(chTeamid);
	}

	/**
	* Caches the challenge member in the entity cache if it is enabled.
	*
	* @param challengeMember the challenge member
	*/
	public static void cacheResult(
		edison.challenge.service.builder.model.ChallengeMember challengeMember) {
		getPersistence().cacheResult(challengeMember);
	}

	/**
	* Caches the challenge members in the entity cache if it is enabled.
	*
	* @param challengeMembers the challenge members
	*/
	public static void cacheResult(
		java.util.List<edison.challenge.service.builder.model.ChallengeMember> challengeMembers) {
		getPersistence().cacheResult(challengeMembers);
	}

	/**
	* Creates a new challenge member with the primary key. Does not add the challenge member to the database.
	*
	* @param chMemberid the primary key for the new challenge member
	* @return the new challenge member
	*/
	public static edison.challenge.service.builder.model.ChallengeMember create(
		long chMemberid) {
		return getPersistence().create(chMemberid);
	}

	/**
	* Removes the challenge member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chMemberid the primary key of the challenge member
	* @return the challenge member that was removed
	* @throws edison.challenge.service.builder.NoSuchChallengeMemberException if a challenge member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeMember remove(
		long chMemberid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeMemberException {
		return getPersistence().remove(chMemberid);
	}

	public static edison.challenge.service.builder.model.ChallengeMember updateImpl(
		edison.challenge.service.builder.model.ChallengeMember challengeMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(challengeMember);
	}

	/**
	* Returns the challenge member with the primary key or throws a {@link edison.challenge.service.builder.NoSuchChallengeMemberException} if it could not be found.
	*
	* @param chMemberid the primary key of the challenge member
	* @return the challenge member
	* @throws edison.challenge.service.builder.NoSuchChallengeMemberException if a challenge member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeMember findByPrimaryKey(
		long chMemberid)
		throws com.liferay.portal.kernel.exception.SystemException,
			edison.challenge.service.builder.NoSuchChallengeMemberException {
		return getPersistence().findByPrimaryKey(chMemberid);
	}

	/**
	* Returns the challenge member with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chMemberid the primary key of the challenge member
	* @return the challenge member, or <code>null</code> if a challenge member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeMember fetchByPrimaryKey(
		long chMemberid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(chMemberid);
	}

	/**
	* Returns all the challenge members.
	*
	* @return the challenge members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.ChallengeMember> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the challenge members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge members
	* @param end the upper bound of the range of challenge members (not inclusive)
	* @return the range of challenge members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.ChallengeMember> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the challenge members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge members
	* @param end the upper bound of the range of challenge members (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of challenge members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<edison.challenge.service.builder.model.ChallengeMember> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the challenge members from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of challenge members.
	*
	* @return the number of challenge members
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ChallengeMemberPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ChallengeMemberPersistence)PortletBeanLocatorUtil.locate(edison.challenge.service.builder.service.ClpSerializer.getServletContextName(),
					ChallengeMemberPersistence.class.getName());

			ReferenceRegistry.registerReference(ChallengeMemberUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ChallengeMemberPersistence persistence) {
	}

	private static ChallengeMemberPersistence _persistence;
}