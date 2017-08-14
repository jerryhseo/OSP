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

package org.kisti.edison.service.persistence;

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

import org.kisti.edison.NoSuchSimProScienceAppLinkException;
import org.kisti.edison.model.SimProScienceAppLink;
import org.kisti.edison.model.impl.SimProScienceAppLinkImpl;
import org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the sim pro science app link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see SimProScienceAppLinkPersistence
 * @see SimProScienceAppLinkUtil
 * @generated
 */
public class SimProScienceAppLinkPersistenceImpl extends BasePersistenceImpl<SimProScienceAppLink>
	implements SimProScienceAppLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SimProScienceAppLinkUtil} to access the sim pro science app link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SimProScienceAppLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			SimProScienceAppLinkModelImpl.FINDER_CACHE_ENABLED,
			SimProScienceAppLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			SimProScienceAppLinkModelImpl.FINDER_CACHE_ENABLED,
			SimProScienceAppLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			SimProScienceAppLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONPROJECTID =
		new FinderPath(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			SimProScienceAppLinkModelImpl.FINDER_CACHE_ENABLED,
			SimProScienceAppLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySimulationProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTID =
		new FinderPath(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			SimProScienceAppLinkModelImpl.FINDER_CACHE_ENABLED,
			SimProScienceAppLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySimulationProjectId", new String[] { Long.class.getName() },
			SimProScienceAppLinkModelImpl.SIMULATIONPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONPROJECTID = new FinderPath(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			SimProScienceAppLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySimulationProjectId", new String[] { Long.class.getName() });

	/**
	 * Returns all the sim pro science app links where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @return the matching sim pro science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimProScienceAppLink> findBySimulationProjectId(
		long simulationProjectId) throws SystemException {
		return findBySimulationProjectId(simulationProjectId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sim pro science app links where simulationProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param start the lower bound of the range of sim pro science app links
	 * @param end the upper bound of the range of sim pro science app links (not inclusive)
	 * @return the range of matching sim pro science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimProScienceAppLink> findBySimulationProjectId(
		long simulationProjectId, int start, int end) throws SystemException {
		return findBySimulationProjectId(simulationProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sim pro science app links where simulationProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param start the lower bound of the range of sim pro science app links
	 * @param end the upper bound of the range of sim pro science app links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sim pro science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimProScienceAppLink> findBySimulationProjectId(
		long simulationProjectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTID;
			finderArgs = new Object[] { simulationProjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONPROJECTID;
			finderArgs = new Object[] {
					simulationProjectId,
					
					start, end, orderByComparator
				};
		}

		List<SimProScienceAppLink> list = (List<SimProScienceAppLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SimProScienceAppLink simProScienceAppLink : list) {
				if ((simulationProjectId != simProScienceAppLink.getSimulationProjectId())) {
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

			query.append(_SQL_SELECT_SIMPROSCIENCEAPPLINK_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONPROJECTID_SIMULATIONPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimProScienceAppLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationProjectId);

				if (!pagination) {
					list = (List<SimProScienceAppLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SimProScienceAppLink>(list);
				}
				else {
					list = (List<SimProScienceAppLink>)QueryUtil.list(q,
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
	 * Returns the first sim pro science app link in the ordered set where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sim pro science app link
	 * @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a matching sim pro science app link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink findBySimulationProjectId_First(
		long simulationProjectId, OrderByComparator orderByComparator)
		throws NoSuchSimProScienceAppLinkException, SystemException {
		SimProScienceAppLink simProScienceAppLink = fetchBySimulationProjectId_First(simulationProjectId,
				orderByComparator);

		if (simProScienceAppLink != null) {
			return simProScienceAppLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationProjectId=");
		msg.append(simulationProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimProScienceAppLinkException(msg.toString());
	}

	/**
	 * Returns the first sim pro science app link in the ordered set where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sim pro science app link, or <code>null</code> if a matching sim pro science app link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink fetchBySimulationProjectId_First(
		long simulationProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		List<SimProScienceAppLink> list = findBySimulationProjectId(simulationProjectId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sim pro science app link in the ordered set where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sim pro science app link
	 * @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a matching sim pro science app link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink findBySimulationProjectId_Last(
		long simulationProjectId, OrderByComparator orderByComparator)
		throws NoSuchSimProScienceAppLinkException, SystemException {
		SimProScienceAppLink simProScienceAppLink = fetchBySimulationProjectId_Last(simulationProjectId,
				orderByComparator);

		if (simProScienceAppLink != null) {
			return simProScienceAppLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationProjectId=");
		msg.append(simulationProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimProScienceAppLinkException(msg.toString());
	}

	/**
	 * Returns the last sim pro science app link in the ordered set where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sim pro science app link, or <code>null</code> if a matching sim pro science app link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink fetchBySimulationProjectId_Last(
		long simulationProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBySimulationProjectId(simulationProjectId);

		if (count == 0) {
			return null;
		}

		List<SimProScienceAppLink> list = findBySimulationProjectId(simulationProjectId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sim pro science app links before and after the current sim pro science app link in the ordered set where simulationProjectId = &#63;.
	 *
	 * @param simProScienceAppLinkPK the primary key of the current sim pro science app link
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sim pro science app link
	 * @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a sim pro science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink[] findBySimulationProjectId_PrevAndNext(
		SimProScienceAppLinkPK simProScienceAppLinkPK,
		long simulationProjectId, OrderByComparator orderByComparator)
		throws NoSuchSimProScienceAppLinkException, SystemException {
		SimProScienceAppLink simProScienceAppLink = findByPrimaryKey(simProScienceAppLinkPK);

		Session session = null;

		try {
			session = openSession();

			SimProScienceAppLink[] array = new SimProScienceAppLinkImpl[3];

			array[0] = getBySimulationProjectId_PrevAndNext(session,
					simProScienceAppLink, simulationProjectId,
					orderByComparator, true);

			array[1] = simProScienceAppLink;

			array[2] = getBySimulationProjectId_PrevAndNext(session,
					simProScienceAppLink, simulationProjectId,
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

	protected SimProScienceAppLink getBySimulationProjectId_PrevAndNext(
		Session session, SimProScienceAppLink simProScienceAppLink,
		long simulationProjectId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMPROSCIENCEAPPLINK_WHERE);

		query.append(_FINDER_COLUMN_SIMULATIONPROJECTID_SIMULATIONPROJECTID_2);

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
			query.append(SimProScienceAppLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(simulationProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simProScienceAppLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SimProScienceAppLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sim pro science app links where simulationProjectId = &#63; from the database.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBySimulationProjectId(long simulationProjectId)
		throws SystemException {
		for (SimProScienceAppLink simProScienceAppLink : findBySimulationProjectId(
				simulationProjectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simProScienceAppLink);
		}
	}

	/**
	 * Returns the number of sim pro science app links where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @return the number of matching sim pro science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySimulationProjectId(long simulationProjectId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONPROJECTID;

		Object[] finderArgs = new Object[] { simulationProjectId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMPROSCIENCEAPPLINK_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONPROJECTID_SIMULATIONPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationProjectId);

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

	private static final String _FINDER_COLUMN_SIMULATIONPROJECTID_SIMULATIONPROJECTID_2 =
		"simProScienceAppLink.id.simulationProjectId = ?";

	public SimProScienceAppLinkPersistenceImpl() {
		setModelClass(SimProScienceAppLink.class);
	}

	/**
	 * Caches the sim pro science app link in the entity cache if it is enabled.
	 *
	 * @param simProScienceAppLink the sim pro science app link
	 */
	@Override
	public void cacheResult(SimProScienceAppLink simProScienceAppLink) {
		EntityCacheUtil.putResult(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			SimProScienceAppLinkImpl.class,
			simProScienceAppLink.getPrimaryKey(), simProScienceAppLink);

		simProScienceAppLink.resetOriginalValues();
	}

	/**
	 * Caches the sim pro science app links in the entity cache if it is enabled.
	 *
	 * @param simProScienceAppLinks the sim pro science app links
	 */
	@Override
	public void cacheResult(List<SimProScienceAppLink> simProScienceAppLinks) {
		for (SimProScienceAppLink simProScienceAppLink : simProScienceAppLinks) {
			if (EntityCacheUtil.getResult(
						SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
						SimProScienceAppLinkImpl.class,
						simProScienceAppLink.getPrimaryKey()) == null) {
				cacheResult(simProScienceAppLink);
			}
			else {
				simProScienceAppLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sim pro science app links.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SimProScienceAppLinkImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SimProScienceAppLinkImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sim pro science app link.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SimProScienceAppLink simProScienceAppLink) {
		EntityCacheUtil.removeResult(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			SimProScienceAppLinkImpl.class, simProScienceAppLink.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SimProScienceAppLink> simProScienceAppLinks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SimProScienceAppLink simProScienceAppLink : simProScienceAppLinks) {
			EntityCacheUtil.removeResult(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
				SimProScienceAppLinkImpl.class,
				simProScienceAppLink.getPrimaryKey());
		}
	}

	/**
	 * Creates a new sim pro science app link with the primary key. Does not add the sim pro science app link to the database.
	 *
	 * @param simProScienceAppLinkPK the primary key for the new sim pro science app link
	 * @return the new sim pro science app link
	 */
	@Override
	public SimProScienceAppLink create(
		SimProScienceAppLinkPK simProScienceAppLinkPK) {
		SimProScienceAppLink simProScienceAppLink = new SimProScienceAppLinkImpl();

		simProScienceAppLink.setNew(true);
		simProScienceAppLink.setPrimaryKey(simProScienceAppLinkPK);

		return simProScienceAppLink;
	}

	/**
	 * Removes the sim pro science app link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simProScienceAppLinkPK the primary key of the sim pro science app link
	 * @return the sim pro science app link that was removed
	 * @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a sim pro science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink remove(
		SimProScienceAppLinkPK simProScienceAppLinkPK)
		throws NoSuchSimProScienceAppLinkException, SystemException {
		return remove((Serializable)simProScienceAppLinkPK);
	}

	/**
	 * Removes the sim pro science app link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sim pro science app link
	 * @return the sim pro science app link that was removed
	 * @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a sim pro science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink remove(Serializable primaryKey)
		throws NoSuchSimProScienceAppLinkException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SimProScienceAppLink simProScienceAppLink = (SimProScienceAppLink)session.get(SimProScienceAppLinkImpl.class,
					primaryKey);

			if (simProScienceAppLink == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSimProScienceAppLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(simProScienceAppLink);
		}
		catch (NoSuchSimProScienceAppLinkException nsee) {
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
	protected SimProScienceAppLink removeImpl(
		SimProScienceAppLink simProScienceAppLink) throws SystemException {
		simProScienceAppLink = toUnwrappedModel(simProScienceAppLink);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(simProScienceAppLink)) {
				simProScienceAppLink = (SimProScienceAppLink)session.get(SimProScienceAppLinkImpl.class,
						simProScienceAppLink.getPrimaryKeyObj());
			}

			if (simProScienceAppLink != null) {
				session.delete(simProScienceAppLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (simProScienceAppLink != null) {
			clearCache(simProScienceAppLink);
		}

		return simProScienceAppLink;
	}

	@Override
	public SimProScienceAppLink updateImpl(
		org.kisti.edison.model.SimProScienceAppLink simProScienceAppLink)
		throws SystemException {
		simProScienceAppLink = toUnwrappedModel(simProScienceAppLink);

		boolean isNew = simProScienceAppLink.isNew();

		SimProScienceAppLinkModelImpl simProScienceAppLinkModelImpl = (SimProScienceAppLinkModelImpl)simProScienceAppLink;

		Session session = null;

		try {
			session = openSession();

			if (simProScienceAppLink.isNew()) {
				session.save(simProScienceAppLink);

				simProScienceAppLink.setNew(false);
			}
			else {
				session.merge(simProScienceAppLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SimProScienceAppLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((simProScienceAppLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simProScienceAppLinkModelImpl.getOriginalSimulationProjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONPROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTID,
					args);

				args = new Object[] {
						simProScienceAppLinkModelImpl.getSimulationProjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONPROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTID,
					args);
			}
		}

		EntityCacheUtil.putResult(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			SimProScienceAppLinkImpl.class,
			simProScienceAppLink.getPrimaryKey(), simProScienceAppLink);

		return simProScienceAppLink;
	}

	protected SimProScienceAppLink toUnwrappedModel(
		SimProScienceAppLink simProScienceAppLink) {
		if (simProScienceAppLink instanceof SimProScienceAppLinkImpl) {
			return simProScienceAppLink;
		}

		SimProScienceAppLinkImpl simProScienceAppLinkImpl = new SimProScienceAppLinkImpl();

		simProScienceAppLinkImpl.setNew(simProScienceAppLink.isNew());
		simProScienceAppLinkImpl.setPrimaryKey(simProScienceAppLink.getPrimaryKey());

		simProScienceAppLinkImpl.setSimProScienceAppLinkId(simProScienceAppLink.getSimProScienceAppLinkId());
		simProScienceAppLinkImpl.setSimulationProjectId(simProScienceAppLink.getSimulationProjectId());
		simProScienceAppLinkImpl.setScienceAppId(simProScienceAppLink.getScienceAppId());

		return simProScienceAppLinkImpl;
	}

	/**
	 * Returns the sim pro science app link with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sim pro science app link
	 * @return the sim pro science app link
	 * @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a sim pro science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSimProScienceAppLinkException, SystemException {
		SimProScienceAppLink simProScienceAppLink = fetchByPrimaryKey(primaryKey);

		if (simProScienceAppLink == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSimProScienceAppLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return simProScienceAppLink;
	}

	/**
	 * Returns the sim pro science app link with the primary key or throws a {@link org.kisti.edison.NoSuchSimProScienceAppLinkException} if it could not be found.
	 *
	 * @param simProScienceAppLinkPK the primary key of the sim pro science app link
	 * @return the sim pro science app link
	 * @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a sim pro science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink findByPrimaryKey(
		SimProScienceAppLinkPK simProScienceAppLinkPK)
		throws NoSuchSimProScienceAppLinkException, SystemException {
		return findByPrimaryKey((Serializable)simProScienceAppLinkPK);
	}

	/**
	 * Returns the sim pro science app link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sim pro science app link
	 * @return the sim pro science app link, or <code>null</code> if a sim pro science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SimProScienceAppLink simProScienceAppLink = (SimProScienceAppLink)EntityCacheUtil.getResult(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
				SimProScienceAppLinkImpl.class, primaryKey);

		if (simProScienceAppLink == _nullSimProScienceAppLink) {
			return null;
		}

		if (simProScienceAppLink == null) {
			Session session = null;

			try {
				session = openSession();

				simProScienceAppLink = (SimProScienceAppLink)session.get(SimProScienceAppLinkImpl.class,
						primaryKey);

				if (simProScienceAppLink != null) {
					cacheResult(simProScienceAppLink);
				}
				else {
					EntityCacheUtil.putResult(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
						SimProScienceAppLinkImpl.class, primaryKey,
						_nullSimProScienceAppLink);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SimProScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
					SimProScienceAppLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return simProScienceAppLink;
	}

	/**
	 * Returns the sim pro science app link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param simProScienceAppLinkPK the primary key of the sim pro science app link
	 * @return the sim pro science app link, or <code>null</code> if a sim pro science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimProScienceAppLink fetchByPrimaryKey(
		SimProScienceAppLinkPK simProScienceAppLinkPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)simProScienceAppLinkPK);
	}

	/**
	 * Returns all the sim pro science app links.
	 *
	 * @return the sim pro science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimProScienceAppLink> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sim pro science app links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sim pro science app links
	 * @param end the upper bound of the range of sim pro science app links (not inclusive)
	 * @return the range of sim pro science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimProScienceAppLink> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sim pro science app links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sim pro science app links
	 * @param end the upper bound of the range of sim pro science app links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sim pro science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimProScienceAppLink> findAll(int start, int end,
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

		List<SimProScienceAppLink> list = (List<SimProScienceAppLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SIMPROSCIENCEAPPLINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SIMPROSCIENCEAPPLINK;

				if (pagination) {
					sql = sql.concat(SimProScienceAppLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SimProScienceAppLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SimProScienceAppLink>(list);
				}
				else {
					list = (List<SimProScienceAppLink>)QueryUtil.list(q,
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
	 * Removes all the sim pro science app links from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SimProScienceAppLink simProScienceAppLink : findAll()) {
			remove(simProScienceAppLink);
		}
	}

	/**
	 * Returns the number of sim pro science app links.
	 *
	 * @return the number of sim pro science app links
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

				Query q = session.createQuery(_SQL_COUNT_SIMPROSCIENCEAPPLINK);

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
	 * Initializes the sim pro science app link persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.SimProScienceAppLink")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SimProScienceAppLink>> listenersList = new ArrayList<ModelListener<SimProScienceAppLink>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SimProScienceAppLink>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SimProScienceAppLinkImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SIMPROSCIENCEAPPLINK = "SELECT simProScienceAppLink FROM SimProScienceAppLink simProScienceAppLink";
	private static final String _SQL_SELECT_SIMPROSCIENCEAPPLINK_WHERE = "SELECT simProScienceAppLink FROM SimProScienceAppLink simProScienceAppLink WHERE ";
	private static final String _SQL_COUNT_SIMPROSCIENCEAPPLINK = "SELECT COUNT(simProScienceAppLink) FROM SimProScienceAppLink simProScienceAppLink";
	private static final String _SQL_COUNT_SIMPROSCIENCEAPPLINK_WHERE = "SELECT COUNT(simProScienceAppLink) FROM SimProScienceAppLink simProScienceAppLink WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "simProScienceAppLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SimProScienceAppLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SimProScienceAppLink exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SimProScienceAppLinkPersistenceImpl.class);
	private static SimProScienceAppLink _nullSimProScienceAppLink = new SimProScienceAppLinkImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SimProScienceAppLink> toCacheModel() {
				return _nullSimProScienceAppLinkCacheModel;
			}
		};

	private static CacheModel<SimProScienceAppLink> _nullSimProScienceAppLinkCacheModel =
		new CacheModel<SimProScienceAppLink>() {
			@Override
			public SimProScienceAppLink toEntityModel() {
				return _nullSimProScienceAppLink;
			}
		};
}