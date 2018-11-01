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

package org.kisti.edison.science.service.persistence;

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

import org.kisti.edison.science.NoSuchScienceAppRatingsEntryException;
import org.kisti.edison.science.model.ScienceAppRatingsEntry;
import org.kisti.edison.science.model.impl.ScienceAppRatingsEntryImpl;
import org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the science app ratings entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ScienceAppRatingsEntryPersistence
 * @see ScienceAppRatingsEntryUtil
 * @generated
 */
public class ScienceAppRatingsEntryPersistenceImpl extends BasePersistenceImpl<ScienceAppRatingsEntry>
	implements ScienceAppRatingsEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScienceAppRatingsEntryUtil} to access the science app ratings entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScienceAppRatingsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId",
			new String[] { Long.class.getName() },
			ScienceAppRatingsEntryModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the science app ratings entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findByuserId(long userId)
		throws SystemException {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science app ratings entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of science app ratings entries
	 * @param end the upper bound of the range of science app ratings entries (not inclusive)
	 * @return the range of matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findByuserId(long userId, int start,
		int end) throws SystemException {
		return findByuserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science app ratings entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of science app ratings entries
	 * @param end the upper bound of the range of science app ratings entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findByuserId(long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<ScienceAppRatingsEntry> list = (List<ScienceAppRatingsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ScienceAppRatingsEntry scienceAppRatingsEntry : list) {
				if ((userId != scienceAppRatingsEntry.getUserId())) {
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

			query.append(_SQL_SELECT_SCIENCEAPPRATINGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppRatingsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<ScienceAppRatingsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ScienceAppRatingsEntry>(list);
				}
				else {
					list = (List<ScienceAppRatingsEntry>)QueryUtil.list(q,
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
	 * Returns the first science app ratings entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry findByuserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = fetchByuserId_First(userId,
				orderByComparator);

		if (scienceAppRatingsEntry != null) {
			return scienceAppRatingsEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScienceAppRatingsEntryException(msg.toString());
	}

	/**
	 * Returns the first science app ratings entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry fetchByuserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ScienceAppRatingsEntry> list = findByuserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app ratings entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry findByuserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = fetchByuserId_Last(userId,
				orderByComparator);

		if (scienceAppRatingsEntry != null) {
			return scienceAppRatingsEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScienceAppRatingsEntryException(msg.toString());
	}

	/**
	 * Returns the last science app ratings entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry fetchByuserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<ScienceAppRatingsEntry> list = findByuserId(userId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science app ratings entries before and after the current science app ratings entry in the ordered set where userId = &#63;.
	 *
	 * @param scienceAppRatingsEntryPK the primary key of the current science app ratings entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry[] findByuserId_PrevAndNext(
		ScienceAppRatingsEntryPK scienceAppRatingsEntryPK, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = findByPrimaryKey(scienceAppRatingsEntryPK);

		Session session = null;

		try {
			session = openSession();

			ScienceAppRatingsEntry[] array = new ScienceAppRatingsEntryImpl[3];

			array[0] = getByuserId_PrevAndNext(session, scienceAppRatingsEntry,
					userId, orderByComparator, true);

			array[1] = scienceAppRatingsEntry;

			array[2] = getByuserId_PrevAndNext(session, scienceAppRatingsEntry,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceAppRatingsEntry getByuserId_PrevAndNext(Session session,
		ScienceAppRatingsEntry scienceAppRatingsEntry, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPPRATINGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(ScienceAppRatingsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceAppRatingsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceAppRatingsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science app ratings entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByuserId(long userId) throws SystemException {
		for (ScienceAppRatingsEntry scienceAppRatingsEntry : findByuserId(
				userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceAppRatingsEntry);
		}
	}

	/**
	 * Returns the number of science app ratings entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByuserId(long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPPRATINGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "scienceAppRatingsEntry.id.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPID =
		new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByScienceAppId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID =
		new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByScienceAppId",
			new String[] { Long.class.getName() },
			ScienceAppRatingsEntryModelImpl.SCIENCEAPPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCIENCEAPPID = new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByScienceAppId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the science app ratings entries where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @return the matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findByScienceAppId(long scienceAppId)
		throws SystemException {
		return findByScienceAppId(scienceAppId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science app ratings entries where scienceAppId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param start the lower bound of the range of science app ratings entries
	 * @param end the upper bound of the range of science app ratings entries (not inclusive)
	 * @return the range of matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findByScienceAppId(long scienceAppId,
		int start, int end) throws SystemException {
		return findByScienceAppId(scienceAppId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science app ratings entries where scienceAppId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param start the lower bound of the range of science app ratings entries
	 * @param end the upper bound of the range of science app ratings entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findByScienceAppId(long scienceAppId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID;
			finderArgs = new Object[] { scienceAppId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPID;
			finderArgs = new Object[] {
					scienceAppId,
					
					start, end, orderByComparator
				};
		}

		List<ScienceAppRatingsEntry> list = (List<ScienceAppRatingsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ScienceAppRatingsEntry scienceAppRatingsEntry : list) {
				if ((scienceAppId != scienceAppRatingsEntry.getScienceAppId())) {
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

			query.append(_SQL_SELECT_SCIENCEAPPRATINGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppRatingsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scienceAppId);

				if (!pagination) {
					list = (List<ScienceAppRatingsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ScienceAppRatingsEntry>(list);
				}
				else {
					list = (List<ScienceAppRatingsEntry>)QueryUtil.list(q,
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
	 * Returns the first science app ratings entry in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry findByScienceAppId_First(long scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = fetchByScienceAppId_First(scienceAppId,
				orderByComparator);

		if (scienceAppRatingsEntry != null) {
			return scienceAppRatingsEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScienceAppRatingsEntryException(msg.toString());
	}

	/**
	 * Returns the first science app ratings entry in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry fetchByScienceAppId_First(long scienceAppId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ScienceAppRatingsEntry> list = findByScienceAppId(scienceAppId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app ratings entry in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry findByScienceAppId_Last(long scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = fetchByScienceAppId_Last(scienceAppId,
				orderByComparator);

		if (scienceAppRatingsEntry != null) {
			return scienceAppRatingsEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScienceAppRatingsEntryException(msg.toString());
	}

	/**
	 * Returns the last science app ratings entry in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry fetchByScienceAppId_Last(long scienceAppId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByScienceAppId(scienceAppId);

		if (count == 0) {
			return null;
		}

		List<ScienceAppRatingsEntry> list = findByScienceAppId(scienceAppId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science app ratings entries before and after the current science app ratings entry in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppRatingsEntryPK the primary key of the current science app ratings entry
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry[] findByScienceAppId_PrevAndNext(
		ScienceAppRatingsEntryPK scienceAppRatingsEntryPK, long scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = findByPrimaryKey(scienceAppRatingsEntryPK);

		Session session = null;

		try {
			session = openSession();

			ScienceAppRatingsEntry[] array = new ScienceAppRatingsEntryImpl[3];

			array[0] = getByScienceAppId_PrevAndNext(session,
					scienceAppRatingsEntry, scienceAppId, orderByComparator,
					true);

			array[1] = scienceAppRatingsEntry;

			array[2] = getByScienceAppId_PrevAndNext(session,
					scienceAppRatingsEntry, scienceAppId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceAppRatingsEntry getByScienceAppId_PrevAndNext(
		Session session, ScienceAppRatingsEntry scienceAppRatingsEntry,
		long scienceAppId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPPRATINGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_2);

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
			query.append(ScienceAppRatingsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(scienceAppId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceAppRatingsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceAppRatingsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science app ratings entries where scienceAppId = &#63; from the database.
	 *
	 * @param scienceAppId the science app ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScienceAppId(long scienceAppId)
		throws SystemException {
		for (ScienceAppRatingsEntry scienceAppRatingsEntry : findByScienceAppId(
				scienceAppId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceAppRatingsEntry);
		}
	}

	/**
	 * Returns the number of science app ratings entries where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @return the number of matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByScienceAppId(long scienceAppId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCIENCEAPPID;

		Object[] finderArgs = new Object[] { scienceAppId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPPRATINGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scienceAppId);

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

	private static final String _FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_2 = "scienceAppRatingsEntry.id.scienceAppId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERIDANDSCIENCEAPPID =
		new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByuserIdAndScienceAppId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERIDANDSCIENCEAPPID =
		new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByuserIdAndScienceAppId",
			new String[] { Long.class.getName(), Long.class.getName() },
			ScienceAppRatingsEntryModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppRatingsEntryModelImpl.SCIENCEAPPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERIDANDSCIENCEAPPID = new FinderPath(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByuserIdAndScienceAppId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the science app ratings entries where userId = &#63; and scienceAppId = &#63;.
	 *
	 * @param userId the user ID
	 * @param scienceAppId the science app ID
	 * @return the matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findByuserIdAndScienceAppId(
		long userId, long scienceAppId) throws SystemException {
		return findByuserIdAndScienceAppId(userId, scienceAppId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science app ratings entries where userId = &#63; and scienceAppId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param scienceAppId the science app ID
	 * @param start the lower bound of the range of science app ratings entries
	 * @param end the upper bound of the range of science app ratings entries (not inclusive)
	 * @return the range of matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findByuserIdAndScienceAppId(
		long userId, long scienceAppId, int start, int end)
		throws SystemException {
		return findByuserIdAndScienceAppId(userId, scienceAppId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the science app ratings entries where userId = &#63; and scienceAppId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param scienceAppId the science app ID
	 * @param start the lower bound of the range of science app ratings entries
	 * @param end the upper bound of the range of science app ratings entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findByuserIdAndScienceAppId(
		long userId, long scienceAppId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERIDANDSCIENCEAPPID;
			finderArgs = new Object[] { userId, scienceAppId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERIDANDSCIENCEAPPID;
			finderArgs = new Object[] {
					userId, scienceAppId,
					
					start, end, orderByComparator
				};
		}

		List<ScienceAppRatingsEntry> list = (List<ScienceAppRatingsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ScienceAppRatingsEntry scienceAppRatingsEntry : list) {
				if ((userId != scienceAppRatingsEntry.getUserId()) ||
						(scienceAppId != scienceAppRatingsEntry.getScienceAppId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_SCIENCEAPPRATINGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERIDANDSCIENCEAPPID_USERID_2);

			query.append(_FINDER_COLUMN_USERIDANDSCIENCEAPPID_SCIENCEAPPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppRatingsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(scienceAppId);

				if (!pagination) {
					list = (List<ScienceAppRatingsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ScienceAppRatingsEntry>(list);
				}
				else {
					list = (List<ScienceAppRatingsEntry>)QueryUtil.list(q,
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
	 * Returns the first science app ratings entry in the ordered set where userId = &#63; and scienceAppId = &#63;.
	 *
	 * @param userId the user ID
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry findByuserIdAndScienceAppId_First(
		long userId, long scienceAppId, OrderByComparator orderByComparator)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = fetchByuserIdAndScienceAppId_First(userId,
				scienceAppId, orderByComparator);

		if (scienceAppRatingsEntry != null) {
			return scienceAppRatingsEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", scienceAppId=");
		msg.append(scienceAppId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScienceAppRatingsEntryException(msg.toString());
	}

	/**
	 * Returns the first science app ratings entry in the ordered set where userId = &#63; and scienceAppId = &#63;.
	 *
	 * @param userId the user ID
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry fetchByuserIdAndScienceAppId_First(
		long userId, long scienceAppId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ScienceAppRatingsEntry> list = findByuserIdAndScienceAppId(userId,
				scienceAppId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app ratings entry in the ordered set where userId = &#63; and scienceAppId = &#63;.
	 *
	 * @param userId the user ID
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry findByuserIdAndScienceAppId_Last(
		long userId, long scienceAppId, OrderByComparator orderByComparator)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = fetchByuserIdAndScienceAppId_Last(userId,
				scienceAppId, orderByComparator);

		if (scienceAppRatingsEntry != null) {
			return scienceAppRatingsEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", scienceAppId=");
		msg.append(scienceAppId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScienceAppRatingsEntryException(msg.toString());
	}

	/**
	 * Returns the last science app ratings entry in the ordered set where userId = &#63; and scienceAppId = &#63;.
	 *
	 * @param userId the user ID
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app ratings entry, or <code>null</code> if a matching science app ratings entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry fetchByuserIdAndScienceAppId_Last(
		long userId, long scienceAppId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByuserIdAndScienceAppId(userId, scienceAppId);

		if (count == 0) {
			return null;
		}

		List<ScienceAppRatingsEntry> list = findByuserIdAndScienceAppId(userId,
				scienceAppId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science app ratings entries before and after the current science app ratings entry in the ordered set where userId = &#63; and scienceAppId = &#63;.
	 *
	 * @param scienceAppRatingsEntryPK the primary key of the current science app ratings entry
	 * @param userId the user ID
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry[] findByuserIdAndScienceAppId_PrevAndNext(
		ScienceAppRatingsEntryPK scienceAppRatingsEntryPK, long userId,
		long scienceAppId, OrderByComparator orderByComparator)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = findByPrimaryKey(scienceAppRatingsEntryPK);

		Session session = null;

		try {
			session = openSession();

			ScienceAppRatingsEntry[] array = new ScienceAppRatingsEntryImpl[3];

			array[0] = getByuserIdAndScienceAppId_PrevAndNext(session,
					scienceAppRatingsEntry, userId, scienceAppId,
					orderByComparator, true);

			array[1] = scienceAppRatingsEntry;

			array[2] = getByuserIdAndScienceAppId_PrevAndNext(session,
					scienceAppRatingsEntry, userId, scienceAppId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceAppRatingsEntry getByuserIdAndScienceAppId_PrevAndNext(
		Session session, ScienceAppRatingsEntry scienceAppRatingsEntry,
		long userId, long scienceAppId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPPRATINGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERIDANDSCIENCEAPPID_USERID_2);

		query.append(_FINDER_COLUMN_USERIDANDSCIENCEAPPID_SCIENCEAPPID_2);

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
			query.append(ScienceAppRatingsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(scienceAppId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceAppRatingsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceAppRatingsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science app ratings entries where userId = &#63; and scienceAppId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param scienceAppId the science app ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByuserIdAndScienceAppId(long userId, long scienceAppId)
		throws SystemException {
		for (ScienceAppRatingsEntry scienceAppRatingsEntry : findByuserIdAndScienceAppId(
				userId, scienceAppId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceAppRatingsEntry);
		}
	}

	/**
	 * Returns the number of science app ratings entries where userId = &#63; and scienceAppId = &#63;.
	 *
	 * @param userId the user ID
	 * @param scienceAppId the science app ID
	 * @return the number of matching science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByuserIdAndScienceAppId(long userId, long scienceAppId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERIDANDSCIENCEAPPID;

		Object[] finderArgs = new Object[] { userId, scienceAppId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPPRATINGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERIDANDSCIENCEAPPID_USERID_2);

			query.append(_FINDER_COLUMN_USERIDANDSCIENCEAPPID_SCIENCEAPPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(scienceAppId);

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

	private static final String _FINDER_COLUMN_USERIDANDSCIENCEAPPID_USERID_2 = "scienceAppRatingsEntry.id.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERIDANDSCIENCEAPPID_SCIENCEAPPID_2 =
		"scienceAppRatingsEntry.id.scienceAppId = ?";

	public ScienceAppRatingsEntryPersistenceImpl() {
		setModelClass(ScienceAppRatingsEntry.class);
	}

	/**
	 * Caches the science app ratings entry in the entity cache if it is enabled.
	 *
	 * @param scienceAppRatingsEntry the science app ratings entry
	 */
	@Override
	public void cacheResult(ScienceAppRatingsEntry scienceAppRatingsEntry) {
		EntityCacheUtil.putResult(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			scienceAppRatingsEntry.getPrimaryKey(), scienceAppRatingsEntry);

		scienceAppRatingsEntry.resetOriginalValues();
	}

	/**
	 * Caches the science app ratings entries in the entity cache if it is enabled.
	 *
	 * @param scienceAppRatingsEntries the science app ratings entries
	 */
	@Override
	public void cacheResult(
		List<ScienceAppRatingsEntry> scienceAppRatingsEntries) {
		for (ScienceAppRatingsEntry scienceAppRatingsEntry : scienceAppRatingsEntries) {
			if (EntityCacheUtil.getResult(
						ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppRatingsEntryImpl.class,
						scienceAppRatingsEntry.getPrimaryKey()) == null) {
				cacheResult(scienceAppRatingsEntry);
			}
			else {
				scienceAppRatingsEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all science app ratings entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ScienceAppRatingsEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ScienceAppRatingsEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the science app ratings entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ScienceAppRatingsEntry scienceAppRatingsEntry) {
		EntityCacheUtil.removeResult(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			scienceAppRatingsEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<ScienceAppRatingsEntry> scienceAppRatingsEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ScienceAppRatingsEntry scienceAppRatingsEntry : scienceAppRatingsEntries) {
			EntityCacheUtil.removeResult(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppRatingsEntryImpl.class,
				scienceAppRatingsEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new science app ratings entry with the primary key. Does not add the science app ratings entry to the database.
	 *
	 * @param scienceAppRatingsEntryPK the primary key for the new science app ratings entry
	 * @return the new science app ratings entry
	 */
	@Override
	public ScienceAppRatingsEntry create(
		ScienceAppRatingsEntryPK scienceAppRatingsEntryPK) {
		ScienceAppRatingsEntry scienceAppRatingsEntry = new ScienceAppRatingsEntryImpl();

		scienceAppRatingsEntry.setNew(true);
		scienceAppRatingsEntry.setPrimaryKey(scienceAppRatingsEntryPK);

		return scienceAppRatingsEntry;
	}

	/**
	 * Removes the science app ratings entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppRatingsEntryPK the primary key of the science app ratings entry
	 * @return the science app ratings entry that was removed
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry remove(
		ScienceAppRatingsEntryPK scienceAppRatingsEntryPK)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		return remove((Serializable)scienceAppRatingsEntryPK);
	}

	/**
	 * Removes the science app ratings entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the science app ratings entry
	 * @return the science app ratings entry that was removed
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry remove(Serializable primaryKey)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ScienceAppRatingsEntry scienceAppRatingsEntry = (ScienceAppRatingsEntry)session.get(ScienceAppRatingsEntryImpl.class,
					primaryKey);

			if (scienceAppRatingsEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScienceAppRatingsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scienceAppRatingsEntry);
		}
		catch (NoSuchScienceAppRatingsEntryException nsee) {
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
	protected ScienceAppRatingsEntry removeImpl(
		ScienceAppRatingsEntry scienceAppRatingsEntry)
		throws SystemException {
		scienceAppRatingsEntry = toUnwrappedModel(scienceAppRatingsEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scienceAppRatingsEntry)) {
				scienceAppRatingsEntry = (ScienceAppRatingsEntry)session.get(ScienceAppRatingsEntryImpl.class,
						scienceAppRatingsEntry.getPrimaryKeyObj());
			}

			if (scienceAppRatingsEntry != null) {
				session.delete(scienceAppRatingsEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scienceAppRatingsEntry != null) {
			clearCache(scienceAppRatingsEntry);
		}

		return scienceAppRatingsEntry;
	}

	@Override
	public ScienceAppRatingsEntry updateImpl(
		org.kisti.edison.science.model.ScienceAppRatingsEntry scienceAppRatingsEntry)
		throws SystemException {
		scienceAppRatingsEntry = toUnwrappedModel(scienceAppRatingsEntry);

		boolean isNew = scienceAppRatingsEntry.isNew();

		ScienceAppRatingsEntryModelImpl scienceAppRatingsEntryModelImpl = (ScienceAppRatingsEntryModelImpl)scienceAppRatingsEntry;

		Session session = null;

		try {
			session = openSession();

			if (scienceAppRatingsEntry.isNew()) {
				session.save(scienceAppRatingsEntry);

				scienceAppRatingsEntry.setNew(false);
			}
			else {
				session.merge(scienceAppRatingsEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ScienceAppRatingsEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((scienceAppRatingsEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppRatingsEntryModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { scienceAppRatingsEntryModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((scienceAppRatingsEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppRatingsEntryModelImpl.getOriginalScienceAppId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID,
					args);

				args = new Object[] {
						scienceAppRatingsEntryModelImpl.getScienceAppId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID,
					args);
			}

			if ((scienceAppRatingsEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERIDANDSCIENCEAPPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppRatingsEntryModelImpl.getOriginalUserId(),
						scienceAppRatingsEntryModelImpl.getOriginalScienceAppId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERIDANDSCIENCEAPPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERIDANDSCIENCEAPPID,
					args);

				args = new Object[] {
						scienceAppRatingsEntryModelImpl.getUserId(),
						scienceAppRatingsEntryModelImpl.getScienceAppId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERIDANDSCIENCEAPPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERIDANDSCIENCEAPPID,
					args);
			}
		}

		EntityCacheUtil.putResult(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppRatingsEntryImpl.class,
			scienceAppRatingsEntry.getPrimaryKey(), scienceAppRatingsEntry);

		return scienceAppRatingsEntry;
	}

	protected ScienceAppRatingsEntry toUnwrappedModel(
		ScienceAppRatingsEntry scienceAppRatingsEntry) {
		if (scienceAppRatingsEntry instanceof ScienceAppRatingsEntryImpl) {
			return scienceAppRatingsEntry;
		}

		ScienceAppRatingsEntryImpl scienceAppRatingsEntryImpl = new ScienceAppRatingsEntryImpl();

		scienceAppRatingsEntryImpl.setNew(scienceAppRatingsEntry.isNew());
		scienceAppRatingsEntryImpl.setPrimaryKey(scienceAppRatingsEntry.getPrimaryKey());

		scienceAppRatingsEntryImpl.setUserId(scienceAppRatingsEntry.getUserId());
		scienceAppRatingsEntryImpl.setScienceAppId(scienceAppRatingsEntry.getScienceAppId());
		scienceAppRatingsEntryImpl.setCompanyId(scienceAppRatingsEntry.getCompanyId());
		scienceAppRatingsEntryImpl.setUserName(scienceAppRatingsEntry.getUserName());
		scienceAppRatingsEntryImpl.setCreateDate(scienceAppRatingsEntry.getCreateDate());
		scienceAppRatingsEntryImpl.setModifiedDate(scienceAppRatingsEntry.getModifiedDate());
		scienceAppRatingsEntryImpl.setClassNameId(scienceAppRatingsEntry.getClassNameId());
		scienceAppRatingsEntryImpl.setScore(scienceAppRatingsEntry.getScore());

		return scienceAppRatingsEntryImpl;
	}

	/**
	 * Returns the science app ratings entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app ratings entry
	 * @return the science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = fetchByPrimaryKey(primaryKey);

		if (scienceAppRatingsEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScienceAppRatingsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scienceAppRatingsEntry;
	}

	/**
	 * Returns the science app ratings entry with the primary key or throws a {@link org.kisti.edison.science.NoSuchScienceAppRatingsEntryException} if it could not be found.
	 *
	 * @param scienceAppRatingsEntryPK the primary key of the science app ratings entry
	 * @return the science app ratings entry
	 * @throws org.kisti.edison.science.NoSuchScienceAppRatingsEntryException if a science app ratings entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry findByPrimaryKey(
		ScienceAppRatingsEntryPK scienceAppRatingsEntryPK)
		throws NoSuchScienceAppRatingsEntryException, SystemException {
		return findByPrimaryKey((Serializable)scienceAppRatingsEntryPK);
	}

	/**
	 * Returns the science app ratings entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app ratings entry
	 * @return the science app ratings entry, or <code>null</code> if a science app ratings entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ScienceAppRatingsEntry scienceAppRatingsEntry = (ScienceAppRatingsEntry)EntityCacheUtil.getResult(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppRatingsEntryImpl.class, primaryKey);

		if (scienceAppRatingsEntry == _nullScienceAppRatingsEntry) {
			return null;
		}

		if (scienceAppRatingsEntry == null) {
			Session session = null;

			try {
				session = openSession();

				scienceAppRatingsEntry = (ScienceAppRatingsEntry)session.get(ScienceAppRatingsEntryImpl.class,
						primaryKey);

				if (scienceAppRatingsEntry != null) {
					cacheResult(scienceAppRatingsEntry);
				}
				else {
					EntityCacheUtil.putResult(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppRatingsEntryImpl.class, primaryKey,
						_nullScienceAppRatingsEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ScienceAppRatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
					ScienceAppRatingsEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scienceAppRatingsEntry;
	}

	/**
	 * Returns the science app ratings entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppRatingsEntryPK the primary key of the science app ratings entry
	 * @return the science app ratings entry, or <code>null</code> if a science app ratings entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppRatingsEntry fetchByPrimaryKey(
		ScienceAppRatingsEntryPK scienceAppRatingsEntryPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)scienceAppRatingsEntryPK);
	}

	/**
	 * Returns all the science app ratings entries.
	 *
	 * @return the science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science app ratings entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science app ratings entries
	 * @param end the upper bound of the range of science app ratings entries (not inclusive)
	 * @return the range of science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the science app ratings entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppRatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science app ratings entries
	 * @param end the upper bound of the range of science app ratings entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of science app ratings entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppRatingsEntry> findAll(int start, int end,
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

		List<ScienceAppRatingsEntry> list = (List<ScienceAppRatingsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SCIENCEAPPRATINGSENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCIENCEAPPRATINGSENTRY;

				if (pagination) {
					sql = sql.concat(ScienceAppRatingsEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ScienceAppRatingsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ScienceAppRatingsEntry>(list);
				}
				else {
					list = (List<ScienceAppRatingsEntry>)QueryUtil.list(q,
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
	 * Removes all the science app ratings entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ScienceAppRatingsEntry scienceAppRatingsEntry : findAll()) {
			remove(scienceAppRatingsEntry);
		}
	}

	/**
	 * Returns the number of science app ratings entries.
	 *
	 * @return the number of science app ratings entries
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

				Query q = session.createQuery(_SQL_COUNT_SCIENCEAPPRATINGSENTRY);

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
	 * Initializes the science app ratings entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.science.model.ScienceAppRatingsEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ScienceAppRatingsEntry>> listenersList = new ArrayList<ModelListener<ScienceAppRatingsEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ScienceAppRatingsEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ScienceAppRatingsEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SCIENCEAPPRATINGSENTRY = "SELECT scienceAppRatingsEntry FROM ScienceAppRatingsEntry scienceAppRatingsEntry";
	private static final String _SQL_SELECT_SCIENCEAPPRATINGSENTRY_WHERE = "SELECT scienceAppRatingsEntry FROM ScienceAppRatingsEntry scienceAppRatingsEntry WHERE ";
	private static final String _SQL_COUNT_SCIENCEAPPRATINGSENTRY = "SELECT COUNT(scienceAppRatingsEntry) FROM ScienceAppRatingsEntry scienceAppRatingsEntry";
	private static final String _SQL_COUNT_SCIENCEAPPRATINGSENTRY_WHERE = "SELECT COUNT(scienceAppRatingsEntry) FROM ScienceAppRatingsEntry scienceAppRatingsEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scienceAppRatingsEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ScienceAppRatingsEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ScienceAppRatingsEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ScienceAppRatingsEntryPersistenceImpl.class);
	private static ScienceAppRatingsEntry _nullScienceAppRatingsEntry = new ScienceAppRatingsEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ScienceAppRatingsEntry> toCacheModel() {
				return _nullScienceAppRatingsEntryCacheModel;
			}
		};

	private static CacheModel<ScienceAppRatingsEntry> _nullScienceAppRatingsEntryCacheModel =
		new CacheModel<ScienceAppRatingsEntry>() {
			@Override
			public ScienceAppRatingsEntry toEntityModel() {
				return _nullScienceAppRatingsEntry;
			}
		};
}