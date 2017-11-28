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

import edison.challenge.service.builder.NoSuchChallengeTeamException;
import edison.challenge.service.builder.model.ChallengeTeam;
import edison.challenge.service.builder.model.impl.ChallengeTeamImpl;
import edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the challenge team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see ChallengeTeamPersistence
 * @see ChallengeTeamUtil
 * @generated
 */
public class ChallengeTeamPersistenceImpl extends BasePersistenceImpl<ChallengeTeam>
	implements ChallengeTeamPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChallengeTeamUtil} to access the challenge team persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChallengeTeamImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CHILDCOLLECT =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBychildCollect",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBychildCollect", new String[] { Long.class.getName() },
			ChallengeTeamModelImpl.CHILDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CHILDCOLLECT = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBychildCollect",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the challenge teams where childid = &#63;.
	 *
	 * @param childid the childid
	 * @return the matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findBychildCollect(long childid)
		throws SystemException {
		return findBychildCollect(childid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<ChallengeTeam> findBychildCollect(long childid, int start,
		int end) throws SystemException {
		return findBychildCollect(childid, start, end, null);
	}

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
	@Override
	public List<ChallengeTeam> findBychildCollect(long childid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT;
			finderArgs = new Object[] { childid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CHILDCOLLECT;
			finderArgs = new Object[] { childid, start, end, orderByComparator };
		}

		List<ChallengeTeam> list = (List<ChallengeTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeam challengeTeam : list) {
				if ((childid != challengeTeam.getChildid())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

			query.append(_FINDER_COLUMN_CHILDCOLLECT_CHILDID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(childid);

				if (!pagination) {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeam>(list);
				}
				else {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first challenge team in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team
	 * @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findBychildCollect_First(long childid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchBychildCollect_First(childid,
				orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("childid=");
		msg.append(childid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the first challenge team in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchBychildCollect_First(long childid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeTeam> list = findBychildCollect(childid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team
	 * @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findBychildCollect_Last(long childid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchBychildCollect_Last(childid,
				orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("childid=");
		msg.append(childid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the last challenge team in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchBychildCollect_Last(long childid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBychildCollect(childid);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeam> list = findBychildCollect(childid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public ChallengeTeam[] findBychildCollect_PrevAndNext(long chTeamid,
		long childid, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = findByPrimaryKey(chTeamid);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeam[] array = new ChallengeTeamImpl[3];

			array[0] = getBychildCollect_PrevAndNext(session, challengeTeam,
					childid, orderByComparator, true);

			array[1] = challengeTeam;

			array[2] = getBychildCollect_PrevAndNext(session, challengeTeam,
					childid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeTeam getBychildCollect_PrevAndNext(Session session,
		ChallengeTeam challengeTeam, long childid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

		query.append(_FINDER_COLUMN_CHILDCOLLECT_CHILDID_2);

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
			query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(childid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge teams where childid = &#63; from the database.
	 *
	 * @param childid the childid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBychildCollect(long childid) throws SystemException {
		for (ChallengeTeam challengeTeam : findBychildCollect(childid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeTeam);
		}
	}

	/**
	 * Returns the number of challenge teams where childid = &#63;.
	 *
	 * @param childid the childid
	 * @return the number of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBychildCollect(long childid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CHILDCOLLECT;

		Object[] finderArgs = new Object[] { childid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CHALLENGETEAM_WHERE);

			query.append(_FINDER_COLUMN_CHILDCOLLECT_CHILDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(childid);

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

	private static final String _FINDER_COLUMN_CHILDCOLLECT_CHILDID_2 = "challengeTeam.childid = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEAMCOLLECT =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByteamCollect",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByteamCollect", new String[] { Long.class.getName() },
			ChallengeTeamModelImpl.CHTEAMID_COLUMN_BITMASK |
			ChallengeTeamModelImpl.CHILDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEAMCOLLECT = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByteamCollect",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the challenge teams where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @return the matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByteamCollect(long chTeamid)
		throws SystemException {
		return findByteamCollect(chTeamid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<ChallengeTeam> findByteamCollect(long chTeamid, int start,
		int end) throws SystemException {
		return findByteamCollect(chTeamid, start, end, null);
	}

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
	@Override
	public List<ChallengeTeam> findByteamCollect(long chTeamid, int start,
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

		List<ChallengeTeam> list = (List<ChallengeTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeam challengeTeam : list) {
				if ((chTeamid != challengeTeam.getChTeamid())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

			query.append(_FINDER_COLUMN_TEAMCOLLECT_CHTEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(chTeamid);

				if (!pagination) {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeam>(list);
				}
				else {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first challenge team in the ordered set where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team
	 * @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByteamCollect_First(long chTeamid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByteamCollect_First(chTeamid,
				orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("chTeamid=");
		msg.append(chTeamid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the first challenge team in the ordered set where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByteamCollect_First(long chTeamid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeTeam> list = findByteamCollect(chTeamid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team in the ordered set where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team
	 * @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByteamCollect_Last(long chTeamid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByteamCollect_Last(chTeamid,
				orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("chTeamid=");
		msg.append(chTeamid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the last challenge team in the ordered set where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByteamCollect_Last(long chTeamid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByteamCollect(chTeamid);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeam> list = findByteamCollect(chTeamid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the challenge teams where chTeamid = &#63; from the database.
	 *
	 * @param chTeamid the ch teamid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByteamCollect(long chTeamid) throws SystemException {
		for (ChallengeTeam challengeTeam : findByteamCollect(chTeamid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeTeam);
		}
	}

	/**
	 * Returns the number of challenge teams where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @return the number of matching challenge teams
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

			query.append(_SQL_COUNT_CHALLENGETEAM_WHERE);

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

	private static final String _FINDER_COLUMN_TEAMCOLLECT_CHTEAMID_2 = "challengeTeam.chTeamid = ?";

	public ChallengeTeamPersistenceImpl() {
		setModelClass(ChallengeTeam.class);
	}

	/**
	 * Caches the challenge team in the entity cache if it is enabled.
	 *
	 * @param challengeTeam the challenge team
	 */
	@Override
	public void cacheResult(ChallengeTeam challengeTeam) {
		EntityCacheUtil.putResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamImpl.class, challengeTeam.getPrimaryKey(),
			challengeTeam);

		challengeTeam.resetOriginalValues();
	}

	/**
	 * Caches the challenge teams in the entity cache if it is enabled.
	 *
	 * @param challengeTeams the challenge teams
	 */
	@Override
	public void cacheResult(List<ChallengeTeam> challengeTeams) {
		for (ChallengeTeam challengeTeam : challengeTeams) {
			if (EntityCacheUtil.getResult(
						ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeTeamImpl.class, challengeTeam.getPrimaryKey()) == null) {
				cacheResult(challengeTeam);
			}
			else {
				challengeTeam.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all challenge teams.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChallengeTeamImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChallengeTeamImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the challenge team.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChallengeTeam challengeTeam) {
		EntityCacheUtil.removeResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamImpl.class, challengeTeam.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ChallengeTeam> challengeTeams) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChallengeTeam challengeTeam : challengeTeams) {
			EntityCacheUtil.removeResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeTeamImpl.class, challengeTeam.getPrimaryKey());
		}
	}

	/**
	 * Creates a new challenge team with the primary key. Does not add the challenge team to the database.
	 *
	 * @param chTeamid the primary key for the new challenge team
	 * @return the new challenge team
	 */
	@Override
	public ChallengeTeam create(long chTeamid) {
		ChallengeTeam challengeTeam = new ChallengeTeamImpl();

		challengeTeam.setNew(true);
		challengeTeam.setPrimaryKey(chTeamid);

		return challengeTeam;
	}

	/**
	 * Removes the challenge team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chTeamid the primary key of the challenge team
	 * @return the challenge team that was removed
	 * @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam remove(long chTeamid)
		throws NoSuchChallengeTeamException, SystemException {
		return remove((Serializable)chTeamid);
	}

	/**
	 * Removes the challenge team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the challenge team
	 * @return the challenge team that was removed
	 * @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam remove(Serializable primaryKey)
		throws NoSuchChallengeTeamException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ChallengeTeam challengeTeam = (ChallengeTeam)session.get(ChallengeTeamImpl.class,
					primaryKey);

			if (challengeTeam == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChallengeTeamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(challengeTeam);
		}
		catch (NoSuchChallengeTeamException nsee) {
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
	protected ChallengeTeam removeImpl(ChallengeTeam challengeTeam)
		throws SystemException {
		challengeTeam = toUnwrappedModel(challengeTeam);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(challengeTeam)) {
				challengeTeam = (ChallengeTeam)session.get(ChallengeTeamImpl.class,
						challengeTeam.getPrimaryKeyObj());
			}

			if (challengeTeam != null) {
				session.delete(challengeTeam);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (challengeTeam != null) {
			clearCache(challengeTeam);
		}

		return challengeTeam;
	}

	@Override
	public ChallengeTeam updateImpl(
		edison.challenge.service.builder.model.ChallengeTeam challengeTeam)
		throws SystemException {
		challengeTeam = toUnwrappedModel(challengeTeam);

		boolean isNew = challengeTeam.isNew();

		ChallengeTeamModelImpl challengeTeamModelImpl = (ChallengeTeamModelImpl)challengeTeam;

		Session session = null;

		try {
			session = openSession();

			if (challengeTeam.isNew()) {
				session.save(challengeTeam);

				challengeTeam.setNew(false);
			}
			else {
				session.merge(challengeTeam);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ChallengeTeamModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((challengeTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamModelImpl.getOriginalChildid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHILDCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT,
					args);

				args = new Object[] { challengeTeamModelImpl.getChildid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHILDCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT,
					args);
			}

			if ((challengeTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamModelImpl.getOriginalChTeamid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEAMCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT,
					args);

				args = new Object[] { challengeTeamModelImpl.getChTeamid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEAMCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT,
					args);
			}
		}

		EntityCacheUtil.putResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamImpl.class, challengeTeam.getPrimaryKey(),
			challengeTeam);

		return challengeTeam;
	}

	protected ChallengeTeam toUnwrappedModel(ChallengeTeam challengeTeam) {
		if (challengeTeam instanceof ChallengeTeamImpl) {
			return challengeTeam;
		}

		ChallengeTeamImpl challengeTeamImpl = new ChallengeTeamImpl();

		challengeTeamImpl.setNew(challengeTeam.isNew());
		challengeTeamImpl.setPrimaryKey(challengeTeam.getPrimaryKey());

		challengeTeamImpl.setChTeamid(challengeTeam.getChTeamid());
		challengeTeamImpl.setTeamName(challengeTeam.getTeamName());
		challengeTeamImpl.setSubject(challengeTeam.getSubject());
		challengeTeamImpl.setPaperPDFstatus(challengeTeam.isPaperPDFstatus());
		challengeTeamImpl.setPaperstatus(challengeTeam.isPaperstatus());
		challengeTeamImpl.setPresentationstatus(challengeTeam.isPresentationstatus());
		challengeTeamImpl.setRegisterDay(challengeTeam.getRegisterDay());
		challengeTeamImpl.setRegisterid(challengeTeam.getRegisterid());
		challengeTeamImpl.setPaperName(challengeTeam.getPaperName());
		challengeTeamImpl.setPaperAbstract(challengeTeam.getPaperAbstract());
		challengeTeamImpl.setPaperFileName(challengeTeam.getPaperFileName());
		challengeTeamImpl.setPaperSubmissionDay(challengeTeam.getPaperSubmissionDay());
		challengeTeamImpl.setPaperModifyDay(challengeTeam.getPaperModifyDay());
		challengeTeamImpl.setPaperPDFFileName(challengeTeam.getPaperPDFFileName());
		challengeTeamImpl.setPaperPDFSubmissionDay(challengeTeam.getPaperPDFSubmissionDay());
		challengeTeamImpl.setPaperPDFModifyDay(challengeTeam.getPaperPDFModifyDay());
		challengeTeamImpl.setPresentationName(challengeTeam.getPresentationName());
		challengeTeamImpl.setPresentationFileName(challengeTeam.getPresentationFileName());
		challengeTeamImpl.setPresentationSubmissionDay(challengeTeam.getPresentationSubmissionDay());
		challengeTeamImpl.setPresentationModifyDay(challengeTeam.getPresentationModifyDay());
		challengeTeamImpl.setFilepath(challengeTeam.getFilepath());
		challengeTeamImpl.setChildid(challengeTeam.getChildid());

		return challengeTeamImpl;
	}

	/**
	 * Returns the challenge team with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge team
	 * @return the challenge team
	 * @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByPrimaryKey(primaryKey);

		if (challengeTeam == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChallengeTeamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return challengeTeam;
	}

	/**
	 * Returns the challenge team with the primary key or throws a {@link edison.challenge.service.builder.NoSuchChallengeTeamException} if it could not be found.
	 *
	 * @param chTeamid the primary key of the challenge team
	 * @return the challenge team
	 * @throws edison.challenge.service.builder.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByPrimaryKey(long chTeamid)
		throws NoSuchChallengeTeamException, SystemException {
		return findByPrimaryKey((Serializable)chTeamid);
	}

	/**
	 * Returns the challenge team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge team
	 * @return the challenge team, or <code>null</code> if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ChallengeTeam challengeTeam = (ChallengeTeam)EntityCacheUtil.getResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeTeamImpl.class, primaryKey);

		if (challengeTeam == _nullChallengeTeam) {
			return null;
		}

		if (challengeTeam == null) {
			Session session = null;

			try {
				session = openSession();

				challengeTeam = (ChallengeTeam)session.get(ChallengeTeamImpl.class,
						primaryKey);

				if (challengeTeam != null) {
					cacheResult(challengeTeam);
				}
				else {
					EntityCacheUtil.putResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeTeamImpl.class, primaryKey, _nullChallengeTeam);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
					ChallengeTeamImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return challengeTeam;
	}

	/**
	 * Returns the challenge team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chTeamid the primary key of the challenge team
	 * @return the challenge team, or <code>null</code> if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByPrimaryKey(long chTeamid)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)chTeamid);
	}

	/**
	 * Returns all the challenge teams.
	 *
	 * @return the challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<ChallengeTeam> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

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
	@Override
	public List<ChallengeTeam> findAll(int start, int end,
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

		List<ChallengeTeam> list = (List<ChallengeTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHALLENGETEAM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHALLENGETEAM;

				if (pagination) {
					sql = sql.concat(ChallengeTeamModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeam>(list);
				}
				else {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the challenge teams from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ChallengeTeam challengeTeam : findAll()) {
			remove(challengeTeam);
		}
	}

	/**
	 * Returns the number of challenge teams.
	 *
	 * @return the number of challenge teams
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

				Query q = session.createQuery(_SQL_COUNT_CHALLENGETEAM);

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
	 * Initializes the challenge team persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.edison.challenge.service.builder.model.ChallengeTeam")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ChallengeTeam>> listenersList = new ArrayList<ModelListener<ChallengeTeam>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ChallengeTeam>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ChallengeTeamImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHALLENGETEAM = "SELECT challengeTeam FROM ChallengeTeam challengeTeam";
	private static final String _SQL_SELECT_CHALLENGETEAM_WHERE = "SELECT challengeTeam FROM ChallengeTeam challengeTeam WHERE ";
	private static final String _SQL_COUNT_CHALLENGETEAM = "SELECT COUNT(challengeTeam) FROM ChallengeTeam challengeTeam";
	private static final String _SQL_COUNT_CHALLENGETEAM_WHERE = "SELECT COUNT(challengeTeam) FROM ChallengeTeam challengeTeam WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "challengeTeam.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChallengeTeam exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ChallengeTeam exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChallengeTeamPersistenceImpl.class);
	private static ChallengeTeam _nullChallengeTeam = new ChallengeTeamImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ChallengeTeam> toCacheModel() {
				return _nullChallengeTeamCacheModel;
			}
		};

	private static CacheModel<ChallengeTeam> _nullChallengeTeamCacheModel = new CacheModel<ChallengeTeam>() {
			@Override
			public ChallengeTeam toEntityModel() {
				return _nullChallengeTeam;
			}
		};
}