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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import edison.challenge.service.builder.NoSuchChildChallengeException;
import edison.challenge.service.builder.model.ChildChallenge;
import edison.challenge.service.builder.model.impl.ChildChallengeImpl;
import edison.challenge.service.builder.model.impl.ChildChallengeModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the child challenge service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see ChildChallengePersistence
 * @see ChildChallengeUtil
 * @generated
 */
public class ChildChallengePersistenceImpl extends BasePersistenceImpl<ChildChallenge>
	implements ChildChallengePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChildChallengeUtil} to access the child challenge persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChildChallengeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CHALLENGECOLLECT =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBychallengeCollect",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHALLENGECOLLECT =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBychallengeCollect", new String[] { Long.class.getName() },
			ChildChallengeModelImpl.CHALLENGEID_COLUMN_BITMASK |
			ChildChallengeModelImpl.CHALLENGEENDDAY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CHALLENGECOLLECT = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBychallengeCollect", new String[] { Long.class.getName() });

	/**
	 * Returns all the child challenges where challengeid = &#63;.
	 *
	 * @param challengeid the challengeid
	 * @return the matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findBychallengeCollect(long challengeid)
		throws SystemException {
		return findBychallengeCollect(challengeid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<ChildChallenge> findBychallengeCollect(long challengeid,
		int start, int end) throws SystemException {
		return findBychallengeCollect(challengeid, start, end, null);
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
	@Override
	public List<ChildChallenge> findBychallengeCollect(long challengeid,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHALLENGECOLLECT;
			finderArgs = new Object[] { challengeid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CHALLENGECOLLECT;
			finderArgs = new Object[] { challengeid, start, end, orderByComparator };
		}

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChildChallenge childChallenge : list) {
				if ((challengeid != childChallenge.getChallengeid())) {
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

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_CHALLENGECOLLECT_CHALLENGEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(challengeid);

				if (!pagination) {
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Returns the first child challenge in the ordered set where challengeid = &#63;.
	 *
	 * @param challengeid the challengeid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge
	 * @throws edison.challenge.service.builder.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findBychallengeCollect_First(long challengeid,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchBychallengeCollect_First(challengeid,
				orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("challengeid=");
		msg.append(challengeid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the first child challenge in the ordered set where challengeid = &#63;.
	 *
	 * @param challengeid the challengeid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchBychallengeCollect_First(long challengeid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChildChallenge> list = findBychallengeCollect(challengeid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ChildChallenge findBychallengeCollect_Last(long challengeid,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchBychallengeCollect_Last(challengeid,
				orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("challengeid=");
		msg.append(challengeid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the last child challenge in the ordered set where challengeid = &#63;.
	 *
	 * @param challengeid the challengeid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchBychallengeCollect_Last(long challengeid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBychallengeCollect(challengeid);

		if (count == 0) {
			return null;
		}

		List<ChildChallenge> list = findBychallengeCollect(challengeid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ChildChallenge[] findBychallengeCollect_PrevAndNext(long childid,
		long challengeid, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = findByPrimaryKey(childid);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = getBychallengeCollect_PrevAndNext(session,
					childChallenge, challengeid, orderByComparator, true);

			array[1] = childChallenge;

			array[2] = getBychallengeCollect_PrevAndNext(session,
					childChallenge, challengeid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChildChallenge getBychallengeCollect_PrevAndNext(
		Session session, ChildChallenge childChallenge, long challengeid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_CHALLENGECOLLECT_CHALLENGEID_2);

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
			query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(challengeid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the child challenges where challengeid = &#63; from the database.
	 *
	 * @param challengeid the challengeid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBychallengeCollect(long challengeid)
		throws SystemException {
		for (ChildChallenge childChallenge : findBychallengeCollect(
				challengeid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges where challengeid = &#63;.
	 *
	 * @param challengeid the challengeid
	 * @return the number of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBychallengeCollect(long challengeid)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CHALLENGECOLLECT;

		Object[] finderArgs = new Object[] { challengeid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_CHALLENGECOLLECT_CHALLENGEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(challengeid);

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

	private static final String _FINDER_COLUMN_CHALLENGECOLLECT_CHALLENGEID_2 = "childChallenge.challengeid = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CHILDCOLLECT =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBychildCollect",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBychildCollect",
			new String[] { Long.class.getName() },
			ChildChallengeModelImpl.CHILDID_COLUMN_BITMASK |
			ChildChallengeModelImpl.CHALLENGEENDDAY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CHILDCOLLECT = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBychildCollect",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the child challenges where childid = &#63;.
	 *
	 * @param childid the childid
	 * @return the matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findBychildCollect(long childid)
		throws SystemException {
		return findBychildCollect(childid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<ChildChallenge> findBychildCollect(long childid, int start,
		int end) throws SystemException {
		return findBychildCollect(childid, start, end, null);
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
	@Override
	public List<ChildChallenge> findBychildCollect(long childid, int start,
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

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChildChallenge childChallenge : list) {
				if ((childid != childChallenge.getChildid())) {
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

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_CHILDCOLLECT_CHILDID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(childid);

				if (!pagination) {
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Returns the first child challenge in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge
	 * @throws edison.challenge.service.builder.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findBychildCollect_First(long childid,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchBychildCollect_First(childid,
				orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("childid=");
		msg.append(childid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the first child challenge in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchBychildCollect_First(long childid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChildChallenge> list = findBychildCollect(childid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ChildChallenge findBychildCollect_Last(long childid,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchBychildCollect_Last(childid,
				orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("childid=");
		msg.append(childid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the last child challenge in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchBychildCollect_Last(long childid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBychildCollect(childid);

		if (count == 0) {
			return null;
		}

		List<ChildChallenge> list = findBychildCollect(childid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the child challenges where childid = &#63; from the database.
	 *
	 * @param childid the childid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBychildCollect(long childid) throws SystemException {
		for (ChildChallenge childChallenge : findBychildCollect(childid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges where childid = &#63;.
	 *
	 * @param childid the childid
	 * @return the number of matching child challenges
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

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

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

	private static final String _FINDER_COLUMN_CHILDCOLLECT_CHILDID_2 = "childChallenge.childid = ?";

	public ChildChallengePersistenceImpl() {
		setModelClass(ChildChallenge.class);
	}

	/**
	 * Caches the child challenge in the entity cache if it is enabled.
	 *
	 * @param childChallenge the child challenge
	 */
	@Override
	public void cacheResult(ChildChallenge childChallenge) {
		EntityCacheUtil.putResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeImpl.class, childChallenge.getPrimaryKey(),
			childChallenge);

		childChallenge.resetOriginalValues();
	}

	/**
	 * Caches the child challenges in the entity cache if it is enabled.
	 *
	 * @param childChallenges the child challenges
	 */
	@Override
	public void cacheResult(List<ChildChallenge> childChallenges) {
		for (ChildChallenge childChallenge : childChallenges) {
			if (EntityCacheUtil.getResult(
						ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
						ChildChallengeImpl.class, childChallenge.getPrimaryKey()) == null) {
				cacheResult(childChallenge);
			}
			else {
				childChallenge.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all child challenges.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChildChallengeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChildChallengeImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the child challenge.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChildChallenge childChallenge) {
		EntityCacheUtil.removeResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeImpl.class, childChallenge.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ChildChallenge> childChallenges) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChildChallenge childChallenge : childChallenges) {
			EntityCacheUtil.removeResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
				ChildChallengeImpl.class, childChallenge.getPrimaryKey());
		}
	}

	/**
	 * Creates a new child challenge with the primary key. Does not add the child challenge to the database.
	 *
	 * @param childid the primary key for the new child challenge
	 * @return the new child challenge
	 */
	@Override
	public ChildChallenge create(long childid) {
		ChildChallenge childChallenge = new ChildChallengeImpl();

		childChallenge.setNew(true);
		childChallenge.setPrimaryKey(childid);

		return childChallenge;
	}

	/**
	 * Removes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param childid the primary key of the child challenge
	 * @return the child challenge that was removed
	 * @throws edison.challenge.service.builder.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge remove(long childid)
		throws NoSuchChildChallengeException, SystemException {
		return remove((Serializable)childid);
	}

	/**
	 * Removes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the child challenge
	 * @return the child challenge that was removed
	 * @throws edison.challenge.service.builder.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge remove(Serializable primaryKey)
		throws NoSuchChildChallengeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ChildChallenge childChallenge = (ChildChallenge)session.get(ChildChallengeImpl.class,
					primaryKey);

			if (childChallenge == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChildChallengeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(childChallenge);
		}
		catch (NoSuchChildChallengeException nsee) {
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
	protected ChildChallenge removeImpl(ChildChallenge childChallenge)
		throws SystemException {
		childChallenge = toUnwrappedModel(childChallenge);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(childChallenge)) {
				childChallenge = (ChildChallenge)session.get(ChildChallengeImpl.class,
						childChallenge.getPrimaryKeyObj());
			}

			if (childChallenge != null) {
				session.delete(childChallenge);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (childChallenge != null) {
			clearCache(childChallenge);
		}

		return childChallenge;
	}

	@Override
	public ChildChallenge updateImpl(
		edison.challenge.service.builder.model.ChildChallenge childChallenge)
		throws SystemException {
		childChallenge = toUnwrappedModel(childChallenge);

		boolean isNew = childChallenge.isNew();

		ChildChallengeModelImpl childChallengeModelImpl = (ChildChallengeModelImpl)childChallenge;

		Session session = null;

		try {
			session = openSession();

			if (childChallenge.isNew()) {
				session.save(childChallenge);

				childChallenge.setNew(false);
			}
			else {
				session.merge(childChallenge);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ChildChallengeModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((childChallengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHALLENGECOLLECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						childChallengeModelImpl.getOriginalChallengeid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHALLENGECOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHALLENGECOLLECT,
					args);

				args = new Object[] { childChallengeModelImpl.getChallengeid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHALLENGECOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHALLENGECOLLECT,
					args);
			}

			if ((childChallengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						childChallengeModelImpl.getOriginalChildid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHILDCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT,
					args);

				args = new Object[] { childChallengeModelImpl.getChildid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHILDCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT,
					args);
			}
		}

		EntityCacheUtil.putResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeImpl.class, childChallenge.getPrimaryKey(),
			childChallenge);

		return childChallenge;
	}

	protected ChildChallenge toUnwrappedModel(ChildChallenge childChallenge) {
		if (childChallenge instanceof ChildChallengeImpl) {
			return childChallenge;
		}

		ChildChallengeImpl childChallengeImpl = new ChildChallengeImpl();

		childChallengeImpl.setNew(childChallenge.isNew());
		childChallengeImpl.setPrimaryKey(childChallenge.getPrimaryKey());

		childChallengeImpl.setChildid(childChallenge.getChildid());
		childChallengeImpl.setNumber(childChallenge.getNumber());
		childChallengeImpl.setPresentationDay(childChallenge.getPresentationDay());
		childChallengeImpl.setPaperStartDay(childChallenge.getPaperStartDay());
		childChallengeImpl.setPaperEndDay(childChallenge.getPaperEndDay());
		childChallengeImpl.setEvaluationStartDay(childChallenge.getEvaluationStartDay());
		childChallengeImpl.setEvaluationEndDay(childChallenge.getEvaluationEndDay());
		childChallengeImpl.setChallengeStartDay(childChallenge.getChallengeStartDay());
		childChallengeImpl.setChallengeEndDay(childChallenge.getChallengeEndDay());
		childChallengeImpl.setStatus(childChallenge.getStatus());
		childChallengeImpl.setChallengeid(childChallenge.getChallengeid());

		return childChallengeImpl;
	}

	/**
	 * Returns the child challenge with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the child challenge
	 * @return the child challenge
	 * @throws edison.challenge.service.builder.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByPrimaryKey(primaryKey);

		if (childChallenge == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChildChallengeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return childChallenge;
	}

	/**
	 * Returns the child challenge with the primary key or throws a {@link edison.challenge.service.builder.NoSuchChildChallengeException} if it could not be found.
	 *
	 * @param childid the primary key of the child challenge
	 * @return the child challenge
	 * @throws edison.challenge.service.builder.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByPrimaryKey(long childid)
		throws NoSuchChildChallengeException, SystemException {
		return findByPrimaryKey((Serializable)childid);
	}

	/**
	 * Returns the child challenge with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the child challenge
	 * @return the child challenge, or <code>null</code> if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ChildChallenge childChallenge = (ChildChallenge)EntityCacheUtil.getResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
				ChildChallengeImpl.class, primaryKey);

		if (childChallenge == _nullChildChallenge) {
			return null;
		}

		if (childChallenge == null) {
			Session session = null;

			try {
				session = openSession();

				childChallenge = (ChildChallenge)session.get(ChildChallengeImpl.class,
						primaryKey);

				if (childChallenge != null) {
					cacheResult(childChallenge);
				}
				else {
					EntityCacheUtil.putResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
						ChildChallengeImpl.class, primaryKey,
						_nullChildChallenge);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
					ChildChallengeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return childChallenge;
	}

	/**
	 * Returns the child challenge with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param childid the primary key of the child challenge
	 * @return the child challenge, or <code>null</code> if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByPrimaryKey(long childid)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)childid);
	}

	/**
	 * Returns all the child challenges.
	 *
	 * @return the child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ChildChallenge> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<ChildChallenge> findAll(int start, int end,
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

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHILDCHALLENGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHILDCHALLENGE;

				if (pagination) {
					sql = sql.concat(ChildChallengeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Removes all the child challenges from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ChildChallenge childChallenge : findAll()) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges.
	 *
	 * @return the number of child challenges
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

				Query q = session.createQuery(_SQL_COUNT_CHILDCHALLENGE);

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

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the child challenge persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.edison.challenge.service.builder.model.ChildChallenge")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ChildChallenge>> listenersList = new ArrayList<ModelListener<ChildChallenge>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ChildChallenge>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ChildChallengeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHILDCHALLENGE = "SELECT childChallenge FROM ChildChallenge childChallenge";
	private static final String _SQL_SELECT_CHILDCHALLENGE_WHERE = "SELECT childChallenge FROM ChildChallenge childChallenge WHERE ";
	private static final String _SQL_COUNT_CHILDCHALLENGE = "SELECT COUNT(childChallenge) FROM ChildChallenge childChallenge";
	private static final String _SQL_COUNT_CHILDCHALLENGE_WHERE = "SELECT COUNT(childChallenge) FROM ChildChallenge childChallenge WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "childChallenge.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChildChallenge exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ChildChallenge exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChildChallengePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"number"
			});
	private static ChildChallenge _nullChildChallenge = new ChildChallengeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ChildChallenge> toCacheModel() {
				return _nullChildChallengeCacheModel;
			}
		};

	private static CacheModel<ChildChallenge> _nullChildChallengeCacheModel = new CacheModel<ChildChallenge>() {
			@Override
			public ChildChallenge toEntityModel() {
				return _nullChildChallenge;
			}
		};
}