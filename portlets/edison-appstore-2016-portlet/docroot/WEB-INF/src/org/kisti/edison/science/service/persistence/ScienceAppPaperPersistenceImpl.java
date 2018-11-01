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

import org.kisti.edison.science.NoSuchScienceAppPaperException;
import org.kisti.edison.science.model.ScienceAppPaper;
import org.kisti.edison.science.model.impl.ScienceAppPaperImpl;
import org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the science app paper service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ScienceAppPaperPersistence
 * @see ScienceAppPaperUtil
 * @generated
 */
public class ScienceAppPaperPersistenceImpl extends BasePersistenceImpl<ScienceAppPaper>
	implements ScienceAppPaperPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScienceAppPaperUtil} to access the science app paper persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScienceAppPaperImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppPaperModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppPaperImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppPaperModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppPaperImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppPaperModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPID =
		new FinderPath(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppPaperModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppPaperImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByScienceAppId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID =
		new FinderPath(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppPaperModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppPaperImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByScienceAppId",
			new String[] { Long.class.getName() },
			ScienceAppPaperModelImpl.SCIENCEAPPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCIENCEAPPID = new FinderPath(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppPaperModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByScienceAppId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the science app papers where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @return the matching science app papers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppPaper> findByScienceAppId(long scienceAppId)
		throws SystemException {
		return findByScienceAppId(scienceAppId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science app papers where scienceAppId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param start the lower bound of the range of science app papers
	 * @param end the upper bound of the range of science app papers (not inclusive)
	 * @return the range of matching science app papers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppPaper> findByScienceAppId(long scienceAppId,
		int start, int end) throws SystemException {
		return findByScienceAppId(scienceAppId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science app papers where scienceAppId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param start the lower bound of the range of science app papers
	 * @param end the upper bound of the range of science app papers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science app papers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppPaper> findByScienceAppId(long scienceAppId,
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

		List<ScienceAppPaper> list = (List<ScienceAppPaper>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ScienceAppPaper scienceAppPaper : list) {
				if ((scienceAppId != scienceAppPaper.getScienceAppId())) {
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

			query.append(_SQL_SELECT_SCIENCEAPPPAPER_WHERE);

			query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppPaperModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scienceAppId);

				if (!pagination) {
					list = (List<ScienceAppPaper>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ScienceAppPaper>(list);
				}
				else {
					list = (List<ScienceAppPaper>)QueryUtil.list(q,
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
	 * Returns the first science app paper in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app paper
	 * @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a matching science app paper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper findByScienceAppId_First(long scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchScienceAppPaperException, SystemException {
		ScienceAppPaper scienceAppPaper = fetchByScienceAppId_First(scienceAppId,
				orderByComparator);

		if (scienceAppPaper != null) {
			return scienceAppPaper;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScienceAppPaperException(msg.toString());
	}

	/**
	 * Returns the first science app paper in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app paper, or <code>null</code> if a matching science app paper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper fetchByScienceAppId_First(long scienceAppId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ScienceAppPaper> list = findByScienceAppId(scienceAppId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app paper in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app paper
	 * @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a matching science app paper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper findByScienceAppId_Last(long scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchScienceAppPaperException, SystemException {
		ScienceAppPaper scienceAppPaper = fetchByScienceAppId_Last(scienceAppId,
				orderByComparator);

		if (scienceAppPaper != null) {
			return scienceAppPaper;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScienceAppPaperException(msg.toString());
	}

	/**
	 * Returns the last science app paper in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app paper, or <code>null</code> if a matching science app paper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper fetchByScienceAppId_Last(long scienceAppId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByScienceAppId(scienceAppId);

		if (count == 0) {
			return null;
		}

		List<ScienceAppPaper> list = findByScienceAppId(scienceAppId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science app papers before and after the current science app paper in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppPaperPK the primary key of the current science app paper
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app paper
	 * @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a science app paper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper[] findByScienceAppId_PrevAndNext(
		ScienceAppPaperPK scienceAppPaperPK, long scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchScienceAppPaperException, SystemException {
		ScienceAppPaper scienceAppPaper = findByPrimaryKey(scienceAppPaperPK);

		Session session = null;

		try {
			session = openSession();

			ScienceAppPaper[] array = new ScienceAppPaperImpl[3];

			array[0] = getByScienceAppId_PrevAndNext(session, scienceAppPaper,
					scienceAppId, orderByComparator, true);

			array[1] = scienceAppPaper;

			array[2] = getByScienceAppId_PrevAndNext(session, scienceAppPaper,
					scienceAppId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceAppPaper getByScienceAppId_PrevAndNext(Session session,
		ScienceAppPaper scienceAppPaper, long scienceAppId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPPPAPER_WHERE);

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
			query.append(ScienceAppPaperModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(scienceAppId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceAppPaper);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceAppPaper> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science app papers where scienceAppId = &#63; from the database.
	 *
	 * @param scienceAppId the science app ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScienceAppId(long scienceAppId)
		throws SystemException {
		for (ScienceAppPaper scienceAppPaper : findByScienceAppId(
				scienceAppId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceAppPaper);
		}
	}

	/**
	 * Returns the number of science app papers where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @return the number of matching science app papers
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

			query.append(_SQL_COUNT_SCIENCEAPPPAPER_WHERE);

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

	private static final String _FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_2 = "scienceAppPaper.id.scienceAppId = ?";

	public ScienceAppPaperPersistenceImpl() {
		setModelClass(ScienceAppPaper.class);
	}

	/**
	 * Caches the science app paper in the entity cache if it is enabled.
	 *
	 * @param scienceAppPaper the science app paper
	 */
	@Override
	public void cacheResult(ScienceAppPaper scienceAppPaper) {
		EntityCacheUtil.putResult(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppPaperImpl.class, scienceAppPaper.getPrimaryKey(),
			scienceAppPaper);

		scienceAppPaper.resetOriginalValues();
	}

	/**
	 * Caches the science app papers in the entity cache if it is enabled.
	 *
	 * @param scienceAppPapers the science app papers
	 */
	@Override
	public void cacheResult(List<ScienceAppPaper> scienceAppPapers) {
		for (ScienceAppPaper scienceAppPaper : scienceAppPapers) {
			if (EntityCacheUtil.getResult(
						ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppPaperImpl.class,
						scienceAppPaper.getPrimaryKey()) == null) {
				cacheResult(scienceAppPaper);
			}
			else {
				scienceAppPaper.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all science app papers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ScienceAppPaperImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ScienceAppPaperImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the science app paper.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ScienceAppPaper scienceAppPaper) {
		EntityCacheUtil.removeResult(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppPaperImpl.class, scienceAppPaper.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ScienceAppPaper> scienceAppPapers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ScienceAppPaper scienceAppPaper : scienceAppPapers) {
			EntityCacheUtil.removeResult(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppPaperImpl.class, scienceAppPaper.getPrimaryKey());
		}
	}

	/**
	 * Creates a new science app paper with the primary key. Does not add the science app paper to the database.
	 *
	 * @param scienceAppPaperPK the primary key for the new science app paper
	 * @return the new science app paper
	 */
	@Override
	public ScienceAppPaper create(ScienceAppPaperPK scienceAppPaperPK) {
		ScienceAppPaper scienceAppPaper = new ScienceAppPaperImpl();

		scienceAppPaper.setNew(true);
		scienceAppPaper.setPrimaryKey(scienceAppPaperPK);

		return scienceAppPaper;
	}

	/**
	 * Removes the science app paper with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppPaperPK the primary key of the science app paper
	 * @return the science app paper that was removed
	 * @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a science app paper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper remove(ScienceAppPaperPK scienceAppPaperPK)
		throws NoSuchScienceAppPaperException, SystemException {
		return remove((Serializable)scienceAppPaperPK);
	}

	/**
	 * Removes the science app paper with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the science app paper
	 * @return the science app paper that was removed
	 * @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a science app paper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper remove(Serializable primaryKey)
		throws NoSuchScienceAppPaperException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ScienceAppPaper scienceAppPaper = (ScienceAppPaper)session.get(ScienceAppPaperImpl.class,
					primaryKey);

			if (scienceAppPaper == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScienceAppPaperException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scienceAppPaper);
		}
		catch (NoSuchScienceAppPaperException nsee) {
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
	protected ScienceAppPaper removeImpl(ScienceAppPaper scienceAppPaper)
		throws SystemException {
		scienceAppPaper = toUnwrappedModel(scienceAppPaper);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scienceAppPaper)) {
				scienceAppPaper = (ScienceAppPaper)session.get(ScienceAppPaperImpl.class,
						scienceAppPaper.getPrimaryKeyObj());
			}

			if (scienceAppPaper != null) {
				session.delete(scienceAppPaper);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scienceAppPaper != null) {
			clearCache(scienceAppPaper);
		}

		return scienceAppPaper;
	}

	@Override
	public ScienceAppPaper updateImpl(
		org.kisti.edison.science.model.ScienceAppPaper scienceAppPaper)
		throws SystemException {
		scienceAppPaper = toUnwrappedModel(scienceAppPaper);

		boolean isNew = scienceAppPaper.isNew();

		ScienceAppPaperModelImpl scienceAppPaperModelImpl = (ScienceAppPaperModelImpl)scienceAppPaper;

		Session session = null;

		try {
			session = openSession();

			if (scienceAppPaper.isNew()) {
				session.save(scienceAppPaper);

				scienceAppPaper.setNew(false);
			}
			else {
				session.merge(scienceAppPaper);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ScienceAppPaperModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((scienceAppPaperModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppPaperModelImpl.getOriginalScienceAppId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID,
					args);

				args = new Object[] { scienceAppPaperModelImpl.getScienceAppId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID,
					args);
			}
		}

		EntityCacheUtil.putResult(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppPaperImpl.class, scienceAppPaper.getPrimaryKey(),
			scienceAppPaper);

		return scienceAppPaper;
	}

	protected ScienceAppPaper toUnwrappedModel(ScienceAppPaper scienceAppPaper) {
		if (scienceAppPaper instanceof ScienceAppPaperImpl) {
			return scienceAppPaper;
		}

		ScienceAppPaperImpl scienceAppPaperImpl = new ScienceAppPaperImpl();

		scienceAppPaperImpl.setNew(scienceAppPaper.isNew());
		scienceAppPaperImpl.setPrimaryKey(scienceAppPaper.getPrimaryKey());

		scienceAppPaperImpl.setScienceAppId(scienceAppPaper.getScienceAppId());
		scienceAppPaperImpl.setPaperSeq(scienceAppPaper.getPaperSeq());
		scienceAppPaperImpl.setPaperType(scienceAppPaper.getPaperType());
		scienceAppPaperImpl.setPaperValue(scienceAppPaper.getPaperValue());

		return scienceAppPaperImpl;
	}

	/**
	 * Returns the science app paper with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app paper
	 * @return the science app paper
	 * @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a science app paper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScienceAppPaperException, SystemException {
		ScienceAppPaper scienceAppPaper = fetchByPrimaryKey(primaryKey);

		if (scienceAppPaper == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScienceAppPaperException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scienceAppPaper;
	}

	/**
	 * Returns the science app paper with the primary key or throws a {@link org.kisti.edison.science.NoSuchScienceAppPaperException} if it could not be found.
	 *
	 * @param scienceAppPaperPK the primary key of the science app paper
	 * @return the science app paper
	 * @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a science app paper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper findByPrimaryKey(ScienceAppPaperPK scienceAppPaperPK)
		throws NoSuchScienceAppPaperException, SystemException {
		return findByPrimaryKey((Serializable)scienceAppPaperPK);
	}

	/**
	 * Returns the science app paper with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app paper
	 * @return the science app paper, or <code>null</code> if a science app paper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ScienceAppPaper scienceAppPaper = (ScienceAppPaper)EntityCacheUtil.getResult(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppPaperImpl.class, primaryKey);

		if (scienceAppPaper == _nullScienceAppPaper) {
			return null;
		}

		if (scienceAppPaper == null) {
			Session session = null;

			try {
				session = openSession();

				scienceAppPaper = (ScienceAppPaper)session.get(ScienceAppPaperImpl.class,
						primaryKey);

				if (scienceAppPaper != null) {
					cacheResult(scienceAppPaper);
				}
				else {
					EntityCacheUtil.putResult(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppPaperImpl.class, primaryKey,
						_nullScienceAppPaper);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ScienceAppPaperModelImpl.ENTITY_CACHE_ENABLED,
					ScienceAppPaperImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scienceAppPaper;
	}

	/**
	 * Returns the science app paper with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppPaperPK the primary key of the science app paper
	 * @return the science app paper, or <code>null</code> if a science app paper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppPaper fetchByPrimaryKey(
		ScienceAppPaperPK scienceAppPaperPK) throws SystemException {
		return fetchByPrimaryKey((Serializable)scienceAppPaperPK);
	}

	/**
	 * Returns all the science app papers.
	 *
	 * @return the science app papers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppPaper> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science app papers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science app papers
	 * @param end the upper bound of the range of science app papers (not inclusive)
	 * @return the range of science app papers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppPaper> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the science app papers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science app papers
	 * @param end the upper bound of the range of science app papers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of science app papers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppPaper> findAll(int start, int end,
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

		List<ScienceAppPaper> list = (List<ScienceAppPaper>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SCIENCEAPPPAPER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCIENCEAPPPAPER;

				if (pagination) {
					sql = sql.concat(ScienceAppPaperModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ScienceAppPaper>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ScienceAppPaper>(list);
				}
				else {
					list = (List<ScienceAppPaper>)QueryUtil.list(q,
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
	 * Removes all the science app papers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ScienceAppPaper scienceAppPaper : findAll()) {
			remove(scienceAppPaper);
		}
	}

	/**
	 * Returns the number of science app papers.
	 *
	 * @return the number of science app papers
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

				Query q = session.createQuery(_SQL_COUNT_SCIENCEAPPPAPER);

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
	 * Initializes the science app paper persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.science.model.ScienceAppPaper")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ScienceAppPaper>> listenersList = new ArrayList<ModelListener<ScienceAppPaper>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ScienceAppPaper>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ScienceAppPaperImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SCIENCEAPPPAPER = "SELECT scienceAppPaper FROM ScienceAppPaper scienceAppPaper";
	private static final String _SQL_SELECT_SCIENCEAPPPAPER_WHERE = "SELECT scienceAppPaper FROM ScienceAppPaper scienceAppPaper WHERE ";
	private static final String _SQL_COUNT_SCIENCEAPPPAPER = "SELECT COUNT(scienceAppPaper) FROM ScienceAppPaper scienceAppPaper";
	private static final String _SQL_COUNT_SCIENCEAPPPAPER_WHERE = "SELECT COUNT(scienceAppPaper) FROM ScienceAppPaper scienceAppPaper WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scienceAppPaper.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ScienceAppPaper exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ScienceAppPaper exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ScienceAppPaperPersistenceImpl.class);
	private static ScienceAppPaper _nullScienceAppPaper = new ScienceAppPaperImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ScienceAppPaper> toCacheModel() {
				return _nullScienceAppPaperCacheModel;
			}
		};

	private static CacheModel<ScienceAppPaper> _nullScienceAppPaperCacheModel = new CacheModel<ScienceAppPaper>() {
			@Override
			public ScienceAppPaper toEntityModel() {
				return _nullScienceAppPaper;
			}
		};
}