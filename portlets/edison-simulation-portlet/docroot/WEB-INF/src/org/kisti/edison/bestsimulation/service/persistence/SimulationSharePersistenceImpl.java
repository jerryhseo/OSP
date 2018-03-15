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

package org.kisti.edison.bestsimulation.service.persistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.kisti.edison.bestsimulation.NoSuchSimulationShareException;
import org.kisti.edison.bestsimulation.model.SimulationShare;
import org.kisti.edison.bestsimulation.model.impl.SimulationShareImpl;
import org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the simulation share service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see SimulationSharePersistence
 * @see SimulationShareUtil
 * @generated
 */
public class SimulationSharePersistenceImpl extends BasePersistenceImpl<SimulationShare>
	implements SimulationSharePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SimulationShareUtil} to access the simulation share persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SimulationShareImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
			SimulationShareModelImpl.FINDER_CACHE_ENABLED,
			SimulationShareImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
			SimulationShareModelImpl.FINDER_CACHE_ENABLED,
			SimulationShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
			SimulationShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBUUID = new FinderPath(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
			SimulationShareModelImpl.FINDER_CACHE_ENABLED,
			SimulationShareImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByjobUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUUID =
		new FinderPath(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
			SimulationShareModelImpl.FINDER_CACHE_ENABLED,
			SimulationShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByjobUuid",
			new String[] { String.class.getName() },
			SimulationShareModelImpl.JOBUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JOBUUID = new FinderPath(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
			SimulationShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByjobUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the simulation shares where jobUuid = &#63;.
	 *
	 * @param jobUuid the job uuid
	 * @return the matching simulation shares
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationShare> findByjobUuid(String jobUuid)
		throws SystemException {
		return findByjobUuid(jobUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation shares where jobUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jobUuid the job uuid
	 * @param start the lower bound of the range of simulation shares
	 * @param end the upper bound of the range of simulation shares (not inclusive)
	 * @return the range of matching simulation shares
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationShare> findByjobUuid(String jobUuid, int start,
		int end) throws SystemException {
		return findByjobUuid(jobUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation shares where jobUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jobUuid the job uuid
	 * @param start the lower bound of the range of simulation shares
	 * @param end the upper bound of the range of simulation shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulation shares
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationShare> findByjobUuid(String jobUuid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUUID;
			finderArgs = new Object[] { jobUuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBUUID;
			finderArgs = new Object[] { jobUuid, start, end, orderByComparator };
		}

		List<SimulationShare> list = (List<SimulationShare>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SimulationShare simulationShare : list) {
				if (!Validator.equals(jobUuid, simulationShare.getJobUuid())) {
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

			query.append(_SQL_SELECT_SIMULATIONSHARE_WHERE);

			boolean bindJobUuid = false;

			if (jobUuid == null) {
				query.append(_FINDER_COLUMN_JOBUUID_JOBUUID_1);
			}
			else if (jobUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JOBUUID_JOBUUID_3);
			}
			else {
				bindJobUuid = true;

				query.append(_FINDER_COLUMN_JOBUUID_JOBUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationShareModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindJobUuid) {
					qPos.add(jobUuid);
				}

				if (!pagination) {
					list = (List<SimulationShare>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SimulationShare>(list);
				}
				else {
					list = (List<SimulationShare>)QueryUtil.list(q,
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
	 * Returns the first simulation share in the ordered set where jobUuid = &#63;.
	 *
	 * @param jobUuid the job uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation share
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationShareException if a matching simulation share could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare findByjobUuid_First(String jobUuid,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationShareException, SystemException {
		SimulationShare simulationShare = fetchByjobUuid_First(jobUuid,
				orderByComparator);

		if (simulationShare != null) {
			return simulationShare;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobUuid=");
		msg.append(jobUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationShareException(msg.toString());
	}

	/**
	 * Returns the first simulation share in the ordered set where jobUuid = &#63;.
	 *
	 * @param jobUuid the job uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation share, or <code>null</code> if a matching simulation share could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare fetchByjobUuid_First(String jobUuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<SimulationShare> list = findByjobUuid(jobUuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation share in the ordered set where jobUuid = &#63;.
	 *
	 * @param jobUuid the job uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation share
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationShareException if a matching simulation share could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare findByjobUuid_Last(String jobUuid,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationShareException, SystemException {
		SimulationShare simulationShare = fetchByjobUuid_Last(jobUuid,
				orderByComparator);

		if (simulationShare != null) {
			return simulationShare;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobUuid=");
		msg.append(jobUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationShareException(msg.toString());
	}

	/**
	 * Returns the last simulation share in the ordered set where jobUuid = &#63;.
	 *
	 * @param jobUuid the job uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation share, or <code>null</code> if a matching simulation share could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare fetchByjobUuid_Last(String jobUuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByjobUuid(jobUuid);

		if (count == 0) {
			return null;
		}

		List<SimulationShare> list = findByjobUuid(jobUuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulation shares before and after the current simulation share in the ordered set where jobUuid = &#63;.
	 *
	 * @param simulationSharePK the primary key of the current simulation share
	 * @param jobUuid the job uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation share
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationShareException if a simulation share with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare[] findByjobUuid_PrevAndNext(
		SimulationSharePK simulationSharePK, String jobUuid,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationShareException, SystemException {
		SimulationShare simulationShare = findByPrimaryKey(simulationSharePK);

		Session session = null;

		try {
			session = openSession();

			SimulationShare[] array = new SimulationShareImpl[3];

			array[0] = getByjobUuid_PrevAndNext(session, simulationShare,
					jobUuid, orderByComparator, true);

			array[1] = simulationShare;

			array[2] = getByjobUuid_PrevAndNext(session, simulationShare,
					jobUuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SimulationShare getByjobUuid_PrevAndNext(Session session,
		SimulationShare simulationShare, String jobUuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATIONSHARE_WHERE);

		boolean bindJobUuid = false;

		if (jobUuid == null) {
			query.append(_FINDER_COLUMN_JOBUUID_JOBUUID_1);
		}
		else if (jobUuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_JOBUUID_JOBUUID_3);
		}
		else {
			bindJobUuid = true;

			query.append(_FINDER_COLUMN_JOBUUID_JOBUUID_2);
		}

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
			query.append(SimulationShareModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindJobUuid) {
			qPos.add(jobUuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulationShare);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SimulationShare> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulation shares where jobUuid = &#63; from the database.
	 *
	 * @param jobUuid the job uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByjobUuid(String jobUuid) throws SystemException {
		for (SimulationShare simulationShare : findByjobUuid(jobUuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulationShare);
		}
	}

	/**
	 * Returns the number of simulation shares where jobUuid = &#63;.
	 *
	 * @param jobUuid the job uuid
	 * @return the number of matching simulation shares
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByjobUuid(String jobUuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JOBUUID;

		Object[] finderArgs = new Object[] { jobUuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATIONSHARE_WHERE);

			boolean bindJobUuid = false;

			if (jobUuid == null) {
				query.append(_FINDER_COLUMN_JOBUUID_JOBUUID_1);
			}
			else if (jobUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JOBUUID_JOBUUID_3);
			}
			else {
				bindJobUuid = true;

				query.append(_FINDER_COLUMN_JOBUUID_JOBUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindJobUuid) {
					qPos.add(jobUuid);
				}

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

	private static final String _FINDER_COLUMN_JOBUUID_JOBUUID_1 = "simulationShare.id.jobUuid IS NULL";
	private static final String _FINDER_COLUMN_JOBUUID_JOBUUID_2 = "simulationShare.id.jobUuid = ?";
	private static final String _FINDER_COLUMN_JOBUUID_JOBUUID_3 = "(simulationShare.id.jobUuid IS NULL OR simulationShare.id.jobUuid = '')";

	public SimulationSharePersistenceImpl() {
		setModelClass(SimulationShare.class);
	}

	/**
	 * Caches the simulation share in the entity cache if it is enabled.
	 *
	 * @param simulationShare the simulation share
	 */
	@Override
	public void cacheResult(SimulationShare simulationShare) {
		EntityCacheUtil.putResult(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
			SimulationShareImpl.class, simulationShare.getPrimaryKey(),
			simulationShare);

		simulationShare.resetOriginalValues();
	}

	/**
	 * Caches the simulation shares in the entity cache if it is enabled.
	 *
	 * @param simulationShares the simulation shares
	 */
	@Override
	public void cacheResult(List<SimulationShare> simulationShares) {
		for (SimulationShare simulationShare : simulationShares) {
			if (EntityCacheUtil.getResult(
						SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
						SimulationShareImpl.class,
						simulationShare.getPrimaryKey()) == null) {
				cacheResult(simulationShare);
			}
			else {
				simulationShare.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all simulation shares.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SimulationShareImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SimulationShareImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the simulation share.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SimulationShare simulationShare) {
		EntityCacheUtil.removeResult(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
			SimulationShareImpl.class, simulationShare.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SimulationShare> simulationShares) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SimulationShare simulationShare : simulationShares) {
			EntityCacheUtil.removeResult(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
				SimulationShareImpl.class, simulationShare.getPrimaryKey());
		}
	}

	/**
	 * Creates a new simulation share with the primary key. Does not add the simulation share to the database.
	 *
	 * @param simulationSharePK the primary key for the new simulation share
	 * @return the new simulation share
	 */
	@Override
	public SimulationShare create(SimulationSharePK simulationSharePK) {
		SimulationShare simulationShare = new SimulationShareImpl();

		simulationShare.setNew(true);
		simulationShare.setPrimaryKey(simulationSharePK);

		return simulationShare;
	}

	/**
	 * Removes the simulation share with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulationSharePK the primary key of the simulation share
	 * @return the simulation share that was removed
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationShareException if a simulation share with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare remove(SimulationSharePK simulationSharePK)
		throws NoSuchSimulationShareException, SystemException {
		return remove((Serializable)simulationSharePK);
	}

	/**
	 * Removes the simulation share with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the simulation share
	 * @return the simulation share that was removed
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationShareException if a simulation share with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare remove(Serializable primaryKey)
		throws NoSuchSimulationShareException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SimulationShare simulationShare = (SimulationShare)session.get(SimulationShareImpl.class,
					primaryKey);

			if (simulationShare == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSimulationShareException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(simulationShare);
		}
		catch (NoSuchSimulationShareException nsee) {
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
	protected SimulationShare removeImpl(SimulationShare simulationShare)
		throws SystemException {
		simulationShare = toUnwrappedModel(simulationShare);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(simulationShare)) {
				simulationShare = (SimulationShare)session.get(SimulationShareImpl.class,
						simulationShare.getPrimaryKeyObj());
			}

			if (simulationShare != null) {
				session.delete(simulationShare);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (simulationShare != null) {
			clearCache(simulationShare);
		}

		return simulationShare;
	}

	@Override
	public SimulationShare updateImpl(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare)
		throws SystemException {
		simulationShare = toUnwrappedModel(simulationShare);

		boolean isNew = simulationShare.isNew();

		SimulationShareModelImpl simulationShareModelImpl = (SimulationShareModelImpl)simulationShare;

		Session session = null;

		try {
			session = openSession();

			if (simulationShare.isNew()) {
				session.save(simulationShare);

				simulationShare.setNew(false);
			}
			else {
				session.merge(simulationShare);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SimulationShareModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((simulationShareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationShareModelImpl.getOriginalJobUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBUUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUUID,
					args);

				args = new Object[] { simulationShareModelImpl.getJobUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBUUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUUID,
					args);
			}
		}

		EntityCacheUtil.putResult(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
			SimulationShareImpl.class, simulationShare.getPrimaryKey(),
			simulationShare);

		return simulationShare;
	}

	protected SimulationShare toUnwrappedModel(SimulationShare simulationShare) {
		if (simulationShare instanceof SimulationShareImpl) {
			return simulationShare;
		}

		SimulationShareImpl simulationShareImpl = new SimulationShareImpl();

		simulationShareImpl.setNew(simulationShare.isNew());
		simulationShareImpl.setPrimaryKey(simulationShare.getPrimaryKey());

		simulationShareImpl.setShareSeqno(simulationShare.getShareSeqno());
		simulationShareImpl.setJobSeqNo(simulationShare.getJobSeqNo());
		simulationShareImpl.setJobUuid(simulationShare.getJobUuid());
		simulationShareImpl.setSimulationUuid(simulationShare.getSimulationUuid());
		simulationShareImpl.setClassId(simulationShare.getClassId());
		simulationShareImpl.setCustomId(simulationShare.getCustomId());
		simulationShareImpl.setSimulationShareDt(simulationShare.getSimulationShareDt());

		return simulationShareImpl;
	}

	/**
	 * Returns the simulation share with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation share
	 * @return the simulation share
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationShareException if a simulation share with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSimulationShareException, SystemException {
		SimulationShare simulationShare = fetchByPrimaryKey(primaryKey);

		if (simulationShare == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSimulationShareException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return simulationShare;
	}

	/**
	 * Returns the simulation share with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationShareException} if it could not be found.
	 *
	 * @param simulationSharePK the primary key of the simulation share
	 * @return the simulation share
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationShareException if a simulation share with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare findByPrimaryKey(SimulationSharePK simulationSharePK)
		throws NoSuchSimulationShareException, SystemException {
		return findByPrimaryKey((Serializable)simulationSharePK);
	}

	/**
	 * Returns the simulation share with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation share
	 * @return the simulation share, or <code>null</code> if a simulation share with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SimulationShare simulationShare = (SimulationShare)EntityCacheUtil.getResult(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
				SimulationShareImpl.class, primaryKey);

		if (simulationShare == _nullSimulationShare) {
			return null;
		}

		if (simulationShare == null) {
			Session session = null;

			try {
				session = openSession();

				simulationShare = (SimulationShare)session.get(SimulationShareImpl.class,
						primaryKey);

				if (simulationShare != null) {
					cacheResult(simulationShare);
				}
				else {
					EntityCacheUtil.putResult(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
						SimulationShareImpl.class, primaryKey,
						_nullSimulationShare);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SimulationShareModelImpl.ENTITY_CACHE_ENABLED,
					SimulationShareImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return simulationShare;
	}

	/**
	 * Returns the simulation share with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param simulationSharePK the primary key of the simulation share
	 * @return the simulation share, or <code>null</code> if a simulation share with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationShare fetchByPrimaryKey(
		SimulationSharePK simulationSharePK) throws SystemException {
		return fetchByPrimaryKey((Serializable)simulationSharePK);
	}

	/**
	 * Returns all the simulation shares.
	 *
	 * @return the simulation shares
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationShare> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulation shares
	 * @param end the upper bound of the range of simulation shares (not inclusive)
	 * @return the range of simulation shares
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationShare> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulation shares
	 * @param end the upper bound of the range of simulation shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of simulation shares
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationShare> findAll(int start, int end,
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

		List<SimulationShare> list = (List<SimulationShare>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SIMULATIONSHARE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SIMULATIONSHARE;

				if (pagination) {
					sql = sql.concat(SimulationShareModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SimulationShare>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SimulationShare>(list);
				}
				else {
					list = (List<SimulationShare>)QueryUtil.list(q,
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
	 * Removes all the simulation shares from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SimulationShare simulationShare : findAll()) {
			remove(simulationShare);
		}
	}

	/**
	 * Returns the number of simulation shares.
	 *
	 * @return the number of simulation shares
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

				Query q = session.createQuery(_SQL_COUNT_SIMULATIONSHARE);

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
	 * Initializes the simulation share persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.bestsimulation.model.SimulationShare")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SimulationShare>> listenersList = new ArrayList<ModelListener<SimulationShare>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SimulationShare>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SimulationShareImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SIMULATIONSHARE = "SELECT simulationShare FROM SimulationShare simulationShare";
	private static final String _SQL_SELECT_SIMULATIONSHARE_WHERE = "SELECT simulationShare FROM SimulationShare simulationShare WHERE ";
	private static final String _SQL_COUNT_SIMULATIONSHARE = "SELECT COUNT(simulationShare) FROM SimulationShare simulationShare";
	private static final String _SQL_COUNT_SIMULATIONSHARE_WHERE = "SELECT COUNT(simulationShare) FROM SimulationShare simulationShare WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "simulationShare.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SimulationShare exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SimulationShare exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SimulationSharePersistenceImpl.class);
	private static SimulationShare _nullSimulationShare = new SimulationShareImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SimulationShare> toCacheModel() {
				return _nullSimulationShareCacheModel;
			}
		};

	private static CacheModel<SimulationShare> _nullSimulationShareCacheModel = new CacheModel<SimulationShare>() {
			@Override
			public SimulationShare toEntityModel() {
				return _nullSimulationShare;
			}
		};
}