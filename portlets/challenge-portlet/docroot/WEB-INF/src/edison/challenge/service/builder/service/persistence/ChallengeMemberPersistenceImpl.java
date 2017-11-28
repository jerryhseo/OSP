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

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import edison.challenge.service.builder.NoSuchChallengeMemberException;
import edison.challenge.service.builder.model.ChallengeMember;
import edison.challenge.service.builder.model.impl.ChallengeMemberImpl;
import edison.challenge.service.builder.model.impl.ChallengeMemberModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the challenge member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see ChallengeMemberPersistence
 * @see ChallengeMemberUtil
 * @generated
 */
public class ChallengeMemberPersistenceImpl extends BasePersistenceImpl<ChallengeMember>
	implements ChallengeMemberPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChallengeMemberUtil} to access the challenge member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChallengeMemberImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeMemberImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEAMCOLLECT =
		new FinderPath(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeMemberImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByteamCollect",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT =
		new FinderPath(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByteamCollect",
			new String[] { Long.class.getName() },
			ChallengeMemberModelImpl.CHTEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEAMCOLLECT = new FinderPath(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByteamCollect",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the challenge members where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @return the matching challenge members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeMember> findByteamCollect(long chTeamid)
		throws SystemException {
		return findByteamCollect(chTeamid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<ChallengeMember> findByteamCollect(long chTeamid, int start,
		int end) throws SystemException {
		return findByteamCollect(chTeamid, start, end, null);
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
	@Override
	public List<ChallengeMember> findByteamCollect(long chTeamid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT;
			finderArgs = new Object[] { chTeamid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEAMCOLLECT;
			finderArgs = new Object[] { chTeamid, start, end, orderByComparator };
		}

		List<ChallengeMember> list = (List<ChallengeMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeMember challengeMember : list) {
				if ((chTeamid != challengeMember.getChTeamid())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CHALLENGEMEMBER_WHERE);

			query.append(_FINDER_COLUMN_TEAMCOLLECT_CHTEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeMemberModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(chTeamid);

				if (!pagination) {
					list = (List<ChallengeMember>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeMember>(list);
				}
				else {
					list = (List<ChallengeMember>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public ChallengeMember findByteamCollect_First(long chTeamid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeMemberException, SystemException {
		ChallengeMember challengeMember = fetchByteamCollect_First(chTeamid,
				orderByComparator);

		if (challengeMember != null) {
			return challengeMember;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("chTeamid=");
		msg.append(chTeamid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeMemberException(msg.toString());
	}

	/**
	 * Returns the first challenge member in the ordered set where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge member, or <code>null</code> if a matching challenge member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeMember fetchByteamCollect_First(long chTeamid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeMember> list = findByteamCollect(chTeamid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ChallengeMember findByteamCollect_Last(long chTeamid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeMemberException, SystemException {
		ChallengeMember challengeMember = fetchByteamCollect_Last(chTeamid,
				orderByComparator);

		if (challengeMember != null) {
			return challengeMember;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("chTeamid=");
		msg.append(chTeamid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeMemberException(msg.toString());
	}

	/**
	 * Returns the last challenge member in the ordered set where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge member, or <code>null</code> if a matching challenge member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeMember fetchByteamCollect_Last(long chTeamid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByteamCollect(chTeamid);

		if (count == 0) {
			return null;
		}

		List<ChallengeMember> list = findByteamCollect(chTeamid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ChallengeMember[] findByteamCollect_PrevAndNext(long chMemberid,
		long chTeamid, OrderByComparator orderByComparator)
		throws NoSuchChallengeMemberException, SystemException {
		ChallengeMember challengeMember = findByPrimaryKey(chMemberid);

		Session session = null;

		try {
			session = openSession();

			ChallengeMember[] array = new ChallengeMemberImpl[3];

			array[0] = getByteamCollect_PrevAndNext(session, challengeMember,
					chTeamid, orderByComparator, true);

			array[1] = challengeMember;

			array[2] = getByteamCollect_PrevAndNext(session, challengeMember,
					chTeamid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeMember getByteamCollect_PrevAndNext(Session session,
		ChallengeMember challengeMember, long chTeamid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEMEMBER_WHERE);

		query.append(_FINDER_COLUMN_TEAMCOLLECT_CHTEAMID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ChallengeMemberModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(chTeamid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge members where chTeamid = &#63; from the database.
	 *
	 * @param chTeamid the ch teamid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByteamCollect(long chTeamid) throws SystemException {
		for (ChallengeMember challengeMember : findByteamCollect(chTeamid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeMember);
		}
	}

	/**
	 * Returns the number of challenge members where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @return the number of matching challenge members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByteamCollect(long chTeamid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEAMCOLLECT;

		Object[] finderArgs = new Object[] { chTeamid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CHALLENGEMEMBER_WHERE);

			query.append(_FINDER_COLUMN_TEAMCOLLECT_CHTEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(chTeamid);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TEAMCOLLECT_CHTEAMID_2 = "challengeMember.chTeamid = ?";

	public ChallengeMemberPersistenceImpl() {
		setModelClass(ChallengeMember.class);
	}

	/**
	 * Caches the challenge member in the entity cache if it is enabled.
	 *
	 * @param challengeMember the challenge member
	 */
	@Override
	public void cacheResult(ChallengeMember challengeMember) {
		EntityCacheUtil.putResult(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeMemberImpl.class, challengeMember.getPrimaryKey(),
			challengeMember);

		challengeMember.resetOriginalValues();
	}

	/**
	 * Caches the challenge members in the entity cache if it is enabled.
	 *
	 * @param challengeMembers the challenge members
	 */
	@Override
	public void cacheResult(List<ChallengeMember> challengeMembers) {
		for (ChallengeMember challengeMember : challengeMembers) {
			if (EntityCacheUtil.getResult(
						ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeMemberImpl.class,
						challengeMember.getPrimaryKey()) == null) {
				cacheResult(challengeMember);
			}
			else {
				challengeMember.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all challenge members.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChallengeMemberImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChallengeMemberImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the challenge member.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChallengeMember challengeMember) {
		EntityCacheUtil.removeResult(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeMemberImpl.class, challengeMember.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ChallengeMember> challengeMembers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChallengeMember challengeMember : challengeMembers) {
			EntityCacheUtil.removeResult(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeMemberImpl.class, challengeMember.getPrimaryKey());
		}
	}

	/**
	 * Creates a new challenge member with the primary key. Does not add the challenge member to the database.
	 *
	 * @param chMemberid the primary key for the new challenge member
	 * @return the new challenge member
	 */
	@Override
	public ChallengeMember create(long chMemberid) {
		ChallengeMember challengeMember = new ChallengeMemberImpl();

		challengeMember.setNew(true);
		challengeMember.setPrimaryKey(chMemberid);

		return challengeMember;
	}

	/**
	 * Removes the challenge member with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chMemberid the primary key of the challenge member
	 * @return the challenge member that was removed
	 * @throws edison.challenge.service.builder.NoSuchChallengeMemberException if a challenge member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeMember remove(long chMemberid)
		throws NoSuchChallengeMemberException, SystemException {
		return remove((Serializable)chMemberid);
	}

	/**
	 * Removes the challenge member with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the challenge member
	 * @return the challenge member that was removed
	 * @throws edison.challenge.service.builder.NoSuchChallengeMemberException if a challenge member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeMember remove(Serializable primaryKey)
		throws NoSuchChallengeMemberException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ChallengeMember challengeMember = (ChallengeMember)session.get(ChallengeMemberImpl.class,
					primaryKey);

			if (challengeMember == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChallengeMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(challengeMember);
		}
		catch (NoSuchChallengeMemberException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ChallengeMember removeImpl(ChallengeMember challengeMember)
		throws SystemException {
		challengeMember = toUnwrappedModel(challengeMember);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(challengeMember)) {
				challengeMember = (ChallengeMember)session.get(ChallengeMemberImpl.class,
						challengeMember.getPrimaryKeyObj());
			}

			if (challengeMember != null) {
				session.delete(challengeMember);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (challengeMember != null) {
			clearCache(challengeMember);
		}

		return challengeMember;
	}

	@Override
	public ChallengeMember updateImpl(
		edison.challenge.service.builder.model.ChallengeMember challengeMember)
		throws SystemException {
		challengeMember = toUnwrappedModel(challengeMember);

		boolean isNew = challengeMember.isNew();

		ChallengeMemberModelImpl challengeMemberModelImpl = (ChallengeMemberModelImpl)challengeMember;

		Session session = null;

		try {
			session = openSession();

			if (challengeMember.isNew()) {
				session.save(challengeMember);

				challengeMember.setNew(false);
			}
			else {
				session.merge(challengeMember);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ChallengeMemberModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((challengeMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeMemberModelImpl.getOriginalChTeamid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEAMCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT,
					args);

				args = new Object[] { challengeMemberModelImpl.getChTeamid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEAMCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT,
					args);
			}
		}

		EntityCacheUtil.putResult(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeMemberImpl.class, challengeMember.getPrimaryKey(),
			challengeMember);

		return challengeMember;
	}

	protected ChallengeMember toUnwrappedModel(ChallengeMember challengeMember) {
		if (challengeMember instanceof ChallengeMemberImpl) {
			return challengeMember;
		}

		ChallengeMemberImpl challengeMemberImpl = new ChallengeMemberImpl();

		challengeMemberImpl.setNew(challengeMember.isNew());
		challengeMemberImpl.setPrimaryKey(challengeMember.getPrimaryKey());

		challengeMemberImpl.setChMemberid(challengeMember.getChMemberid());
		challengeMemberImpl.setFullName(challengeMember.getFullName());
		challengeMemberImpl.setScreenName(challengeMember.getScreenName());
		challengeMemberImpl.setEmail(challengeMember.getEmail());
		challengeMemberImpl.setLeader(challengeMember.isLeader());
		challengeMemberImpl.setFalculty(challengeMember.getFalculty());
		challengeMemberImpl.setMajor(challengeMember.getMajor());
		challengeMemberImpl.setGrade(challengeMember.getGrade());
		challengeMemberImpl.setChTeamid(challengeMember.getChTeamid());

		return challengeMemberImpl;
	}

	/**
	 * Returns the challenge member with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge member
	 * @return the challenge member
	 * @throws edison.challenge.service.builder.NoSuchChallengeMemberException if a challenge member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeMember findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChallengeMemberException, SystemException {
		ChallengeMember challengeMember = fetchByPrimaryKey(primaryKey);

		if (challengeMember == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChallengeMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return challengeMember;
	}

	/**
	 * Returns the challenge member with the primary key or throws a {@link edison.challenge.service.builder.NoSuchChallengeMemberException} if it could not be found.
	 *
	 * @param chMemberid the primary key of the challenge member
	 * @return the challenge member
	 * @throws edison.challenge.service.builder.NoSuchChallengeMemberException if a challenge member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeMember findByPrimaryKey(long chMemberid)
		throws NoSuchChallengeMemberException, SystemException {
		return findByPrimaryKey((Serializable)chMemberid);
	}

	/**
	 * Returns the challenge member with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge member
	 * @return the challenge member, or <code>null</code> if a challenge member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeMember fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ChallengeMember challengeMember = (ChallengeMember)EntityCacheUtil.getResult(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeMemberImpl.class, primaryKey);

		if (challengeMember == _nullChallengeMember) {
			return null;
		}

		if (challengeMember == null) {
			Session session = null;

			try {
				session = openSession();

				challengeMember = (ChallengeMember)session.get(ChallengeMemberImpl.class,
						primaryKey);

				if (challengeMember != null) {
					cacheResult(challengeMember);
				}
				else {
					EntityCacheUtil.putResult(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeMemberImpl.class, primaryKey,
						_nullChallengeMember);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChallengeMemberModelImpl.ENTITY_CACHE_ENABLED,
					ChallengeMemberImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return challengeMember;
	}

	/**
	 * Returns the challenge member with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chMemberid the primary key of the challenge member
	 * @return the challenge member, or <code>null</code> if a challenge member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeMember fetchByPrimaryKey(long chMemberid)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)chMemberid);
	}

	/**
	 * Returns all the challenge members.
	 *
	 * @return the challenge members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeMember> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ChallengeMember> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<ChallengeMember> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ChallengeMember> list = (List<ChallengeMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHALLENGEMEMBER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHALLENGEMEMBER;

				if (pagination) {
					sql = sql.concat(ChallengeMemberModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChallengeMember>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeMember>(list);
				}
				else {
					list = (List<ChallengeMember>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the challenge members from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ChallengeMember challengeMember : findAll()) {
			remove(challengeMember);
		}
	}

	/**
	 * Returns the number of challenge members.
	 *
	 * @return the number of challenge members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHALLENGEMEMBER);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the challenge member persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.edison.challenge.service.builder.model.ChallengeMember")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ChallengeMember>> listenersList = new ArrayList<ModelListener<ChallengeMember>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ChallengeMember>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ChallengeMemberImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHALLENGEMEMBER = "SELECT challengeMember FROM ChallengeMember challengeMember";
	private static final String _SQL_SELECT_CHALLENGEMEMBER_WHERE = "SELECT challengeMember FROM ChallengeMember challengeMember WHERE ";
	private static final String _SQL_COUNT_CHALLENGEMEMBER = "SELECT COUNT(challengeMember) FROM ChallengeMember challengeMember";
	private static final String _SQL_COUNT_CHALLENGEMEMBER_WHERE = "SELECT COUNT(challengeMember) FROM ChallengeMember challengeMember WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "challengeMember.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChallengeMember exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ChallengeMember exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChallengeMemberPersistenceImpl.class);
	private static ChallengeMember _nullChallengeMember = new ChallengeMemberImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ChallengeMember> toCacheModel() {
				return _nullChallengeMemberCacheModel;
			}
		};

	private static CacheModel<ChallengeMember> _nullChallengeMemberCacheModel = new CacheModel<ChallengeMember>() {
			@Override
			public ChallengeMember toEntityModel() {
				return _nullChallengeMember;
			}
		};
}