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

import edison.challenge.service.builder.NoSuchAwardhistoryException;
import edison.challenge.service.builder.model.Awardhistory;
import edison.challenge.service.builder.model.impl.AwardhistoryImpl;
import edison.challenge.service.builder.model.impl.AwardhistoryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the awardhistory service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see AwardhistoryPersistence
 * @see AwardhistoryUtil
 * @generated
 */
public class AwardhistoryPersistenceImpl extends BasePersistenceImpl<Awardhistory>
	implements AwardhistoryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AwardhistoryUtil} to access the awardhistory persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AwardhistoryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
			AwardhistoryModelImpl.FINDER_CACHE_ENABLED, AwardhistoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
			AwardhistoryModelImpl.FINDER_CACHE_ENABLED, AwardhistoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
			AwardhistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEAMCOLLECT =
		new FinderPath(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
			AwardhistoryModelImpl.FINDER_CACHE_ENABLED, AwardhistoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByteamCollect",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT =
		new FinderPath(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
			AwardhistoryModelImpl.FINDER_CACHE_ENABLED, AwardhistoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByteamCollect",
			new String[] { Long.class.getName() },
			AwardhistoryModelImpl.CHTEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEAMCOLLECT = new FinderPath(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
			AwardhistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByteamCollect",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the awardhistories where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @return the matching awardhistories
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Awardhistory> findByteamCollect(long chTeamid)
		throws SystemException {
		return findByteamCollect(chTeamid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Awardhistory> findByteamCollect(long chTeamid, int start,
		int end) throws SystemException {
		return findByteamCollect(chTeamid, start, end, null);
	}

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
	@Override
	public List<Awardhistory> findByteamCollect(long chTeamid, int start,
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

		List<Awardhistory> list = (List<Awardhistory>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Awardhistory awardhistory : list) {
				if ((chTeamid != awardhistory.getChTeamid())) {
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

			query.append(_SQL_SELECT_AWARDHISTORY_WHERE);

			query.append(_FINDER_COLUMN_TEAMCOLLECT_CHTEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AwardhistoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(chTeamid);

				if (!pagination) {
					list = (List<Awardhistory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Awardhistory>(list);
				}
				else {
					list = (List<Awardhistory>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first awardhistory in the ordered set where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching awardhistory
	 * @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a matching awardhistory could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Awardhistory findByteamCollect_First(long chTeamid,
		OrderByComparator orderByComparator)
		throws NoSuchAwardhistoryException, SystemException {
		Awardhistory awardhistory = fetchByteamCollect_First(chTeamid,
				orderByComparator);

		if (awardhistory != null) {
			return awardhistory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("chTeamid=");
		msg.append(chTeamid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAwardhistoryException(msg.toString());
	}

	/**
	 * Returns the first awardhistory in the ordered set where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching awardhistory, or <code>null</code> if a matching awardhistory could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Awardhistory fetchByteamCollect_First(long chTeamid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Awardhistory> list = findByteamCollect(chTeamid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last awardhistory in the ordered set where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching awardhistory
	 * @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a matching awardhistory could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Awardhistory findByteamCollect_Last(long chTeamid,
		OrderByComparator orderByComparator)
		throws NoSuchAwardhistoryException, SystemException {
		Awardhistory awardhistory = fetchByteamCollect_Last(chTeamid,
				orderByComparator);

		if (awardhistory != null) {
			return awardhistory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("chTeamid=");
		msg.append(chTeamid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAwardhistoryException(msg.toString());
	}

	/**
	 * Returns the last awardhistory in the ordered set where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching awardhistory, or <code>null</code> if a matching awardhistory could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Awardhistory fetchByteamCollect_Last(long chTeamid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByteamCollect(chTeamid);

		if (count == 0) {
			return null;
		}

		List<Awardhistory> list = findByteamCollect(chTeamid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public Awardhistory[] findByteamCollect_PrevAndNext(long awardhistoryid,
		long chTeamid, OrderByComparator orderByComparator)
		throws NoSuchAwardhistoryException, SystemException {
		Awardhistory awardhistory = findByPrimaryKey(awardhistoryid);

		Session session = null;

		try {
			session = openSession();

			Awardhistory[] array = new AwardhistoryImpl[3];

			array[0] = getByteamCollect_PrevAndNext(session, awardhistory,
					chTeamid, orderByComparator, true);

			array[1] = awardhistory;

			array[2] = getByteamCollect_PrevAndNext(session, awardhistory,
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

	protected Awardhistory getByteamCollect_PrevAndNext(Session session,
		Awardhistory awardhistory, long chTeamid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AWARDHISTORY_WHERE);

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
			query.append(AwardhistoryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(chTeamid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(awardhistory);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Awardhistory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the awardhistories where chTeamid = &#63; from the database.
	 *
	 * @param chTeamid the ch teamid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByteamCollect(long chTeamid) throws SystemException {
		for (Awardhistory awardhistory : findByteamCollect(chTeamid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(awardhistory);
		}
	}

	/**
	 * Returns the number of awardhistories where chTeamid = &#63;.
	 *
	 * @param chTeamid the ch teamid
	 * @return the number of matching awardhistories
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

			query.append(_SQL_COUNT_AWARDHISTORY_WHERE);

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

	private static final String _FINDER_COLUMN_TEAMCOLLECT_CHTEAMID_2 = "awardhistory.chTeamid = ?";

	public AwardhistoryPersistenceImpl() {
		setModelClass(Awardhistory.class);
	}

	/**
	 * Caches the awardhistory in the entity cache if it is enabled.
	 *
	 * @param awardhistory the awardhistory
	 */
	@Override
	public void cacheResult(Awardhistory awardhistory) {
		EntityCacheUtil.putResult(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
			AwardhistoryImpl.class, awardhistory.getPrimaryKey(), awardhistory);

		awardhistory.resetOriginalValues();
	}

	/**
	 * Caches the awardhistories in the entity cache if it is enabled.
	 *
	 * @param awardhistories the awardhistories
	 */
	@Override
	public void cacheResult(List<Awardhistory> awardhistories) {
		for (Awardhistory awardhistory : awardhistories) {
			if (EntityCacheUtil.getResult(
						AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
						AwardhistoryImpl.class, awardhistory.getPrimaryKey()) == null) {
				cacheResult(awardhistory);
			}
			else {
				awardhistory.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all awardhistories.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AwardhistoryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AwardhistoryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the awardhistory.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Awardhistory awardhistory) {
		EntityCacheUtil.removeResult(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
			AwardhistoryImpl.class, awardhistory.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Awardhistory> awardhistories) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Awardhistory awardhistory : awardhistories) {
			EntityCacheUtil.removeResult(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
				AwardhistoryImpl.class, awardhistory.getPrimaryKey());
		}
	}

	/**
	 * Creates a new awardhistory with the primary key. Does not add the awardhistory to the database.
	 *
	 * @param awardhistoryid the primary key for the new awardhistory
	 * @return the new awardhistory
	 */
	@Override
	public Awardhistory create(long awardhistoryid) {
		Awardhistory awardhistory = new AwardhistoryImpl();

		awardhistory.setNew(true);
		awardhistory.setPrimaryKey(awardhistoryid);

		return awardhistory;
	}

	/**
	 * Removes the awardhistory with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param awardhistoryid the primary key of the awardhistory
	 * @return the awardhistory that was removed
	 * @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a awardhistory with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Awardhistory remove(long awardhistoryid)
		throws NoSuchAwardhistoryException, SystemException {
		return remove((Serializable)awardhistoryid);
	}

	/**
	 * Removes the awardhistory with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the awardhistory
	 * @return the awardhistory that was removed
	 * @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a awardhistory with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Awardhistory remove(Serializable primaryKey)
		throws NoSuchAwardhistoryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Awardhistory awardhistory = (Awardhistory)session.get(AwardhistoryImpl.class,
					primaryKey);

			if (awardhistory == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAwardhistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(awardhistory);
		}
		catch (NoSuchAwardhistoryException nsee) {
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
	protected Awardhistory removeImpl(Awardhistory awardhistory)
		throws SystemException {
		awardhistory = toUnwrappedModel(awardhistory);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(awardhistory)) {
				awardhistory = (Awardhistory)session.get(AwardhistoryImpl.class,
						awardhistory.getPrimaryKeyObj());
			}

			if (awardhistory != null) {
				session.delete(awardhistory);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (awardhistory != null) {
			clearCache(awardhistory);
		}

		return awardhistory;
	}

	@Override
	public Awardhistory updateImpl(
		edison.challenge.service.builder.model.Awardhistory awardhistory)
		throws SystemException {
		awardhistory = toUnwrappedModel(awardhistory);

		boolean isNew = awardhistory.isNew();

		AwardhistoryModelImpl awardhistoryModelImpl = (AwardhistoryModelImpl)awardhistory;

		Session session = null;

		try {
			session = openSession();

			if (awardhistory.isNew()) {
				session.save(awardhistory);

				awardhistory.setNew(false);
			}
			else {
				session.merge(awardhistory);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AwardhistoryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((awardhistoryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						awardhistoryModelImpl.getOriginalChTeamid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEAMCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT,
					args);

				args = new Object[] { awardhistoryModelImpl.getChTeamid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEAMCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEAMCOLLECT,
					args);
			}
		}

		EntityCacheUtil.putResult(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
			AwardhistoryImpl.class, awardhistory.getPrimaryKey(), awardhistory);

		return awardhistory;
	}

	protected Awardhistory toUnwrappedModel(Awardhistory awardhistory) {
		if (awardhistory instanceof AwardhistoryImpl) {
			return awardhistory;
		}

		AwardhistoryImpl awardhistoryImpl = new AwardhistoryImpl();

		awardhistoryImpl.setNew(awardhistory.isNew());
		awardhistoryImpl.setPrimaryKey(awardhistory.getPrimaryKey());

		awardhistoryImpl.setAwardhistoryid(awardhistory.getAwardhistoryid());
		awardhistoryImpl.setName(awardhistory.getName());
		awardhistoryImpl.setPrize(awardhistory.getPrize());
		awardhistoryImpl.setChTeamid(awardhistory.getChTeamid());

		return awardhistoryImpl;
	}

	/**
	 * Returns the awardhistory with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the awardhistory
	 * @return the awardhistory
	 * @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a awardhistory with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Awardhistory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAwardhistoryException, SystemException {
		Awardhistory awardhistory = fetchByPrimaryKey(primaryKey);

		if (awardhistory == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAwardhistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return awardhistory;
	}

	/**
	 * Returns the awardhistory with the primary key or throws a {@link edison.challenge.service.builder.NoSuchAwardhistoryException} if it could not be found.
	 *
	 * @param awardhistoryid the primary key of the awardhistory
	 * @return the awardhistory
	 * @throws edison.challenge.service.builder.NoSuchAwardhistoryException if a awardhistory with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Awardhistory findByPrimaryKey(long awardhistoryid)
		throws NoSuchAwardhistoryException, SystemException {
		return findByPrimaryKey((Serializable)awardhistoryid);
	}

	/**
	 * Returns the awardhistory with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the awardhistory
	 * @return the awardhistory, or <code>null</code> if a awardhistory with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Awardhistory fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Awardhistory awardhistory = (Awardhistory)EntityCacheUtil.getResult(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
				AwardhistoryImpl.class, primaryKey);

		if (awardhistory == _nullAwardhistory) {
			return null;
		}

		if (awardhistory == null) {
			Session session = null;

			try {
				session = openSession();

				awardhistory = (Awardhistory)session.get(AwardhistoryImpl.class,
						primaryKey);

				if (awardhistory != null) {
					cacheResult(awardhistory);
				}
				else {
					EntityCacheUtil.putResult(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
						AwardhistoryImpl.class, primaryKey, _nullAwardhistory);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AwardhistoryModelImpl.ENTITY_CACHE_ENABLED,
					AwardhistoryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return awardhistory;
	}

	/**
	 * Returns the awardhistory with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param awardhistoryid the primary key of the awardhistory
	 * @return the awardhistory, or <code>null</code> if a awardhistory with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Awardhistory fetchByPrimaryKey(long awardhistoryid)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)awardhistoryid);
	}

	/**
	 * Returns all the awardhistories.
	 *
	 * @return the awardhistories
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Awardhistory> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Awardhistory> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

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
	@Override
	public List<Awardhistory> findAll(int start, int end,
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

		List<Awardhistory> list = (List<Awardhistory>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_AWARDHISTORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AWARDHISTORY;

				if (pagination) {
					sql = sql.concat(AwardhistoryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Awardhistory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Awardhistory>(list);
				}
				else {
					list = (List<Awardhistory>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the awardhistories from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Awardhistory awardhistory : findAll()) {
			remove(awardhistory);
		}
	}

	/**
	 * Returns the number of awardhistories.
	 *
	 * @return the number of awardhistories
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

				Query q = session.createQuery(_SQL_COUNT_AWARDHISTORY);

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
	 * Initializes the awardhistory persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.edison.challenge.service.builder.model.Awardhistory")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Awardhistory>> listenersList = new ArrayList<ModelListener<Awardhistory>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Awardhistory>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AwardhistoryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_AWARDHISTORY = "SELECT awardhistory FROM Awardhistory awardhistory";
	private static final String _SQL_SELECT_AWARDHISTORY_WHERE = "SELECT awardhistory FROM Awardhistory awardhistory WHERE ";
	private static final String _SQL_COUNT_AWARDHISTORY = "SELECT COUNT(awardhistory) FROM Awardhistory awardhistory";
	private static final String _SQL_COUNT_AWARDHISTORY_WHERE = "SELECT COUNT(awardhistory) FROM Awardhistory awardhistory WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "awardhistory.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Awardhistory exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Awardhistory exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AwardhistoryPersistenceImpl.class);
	private static Awardhistory _nullAwardhistory = new AwardhistoryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Awardhistory> toCacheModel() {
				return _nullAwardhistoryCacheModel;
			}
		};

	private static CacheModel<Awardhistory> _nullAwardhistoryCacheModel = new CacheModel<Awardhistory>() {
			@Override
			public Awardhistory toEntityModel() {
				return _nullAwardhistory;
			}
		};
}