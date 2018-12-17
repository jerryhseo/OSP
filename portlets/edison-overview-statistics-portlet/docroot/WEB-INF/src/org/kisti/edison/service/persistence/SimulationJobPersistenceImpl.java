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

import org.kisti.edison.NoSuchSimulationJobException;
import org.kisti.edison.model.SimulationJob;
import org.kisti.edison.model.impl.SimulationJobImpl;
import org.kisti.edison.model.impl.SimulationJobModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the simulation job service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see SimulationJobPersistence
 * @see SimulationJobUtil
 * @generated
 */
public class SimulationJobPersistenceImpl extends BasePersistenceImpl<SimulationJob>
	implements SimulationJobPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SimulationJobUtil} to access the simulation job persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SimulationJobImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBygroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBygroupId", new String[] { Long.class.getName() },
			SimulationJobModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the simulation jobs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBygroupId(long groupId)
		throws SystemException {
		return findBygroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation jobs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @return the range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBygroupId(long groupId, int start, int end)
		throws SystemException {
		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation jobs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBygroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<SimulationJob> list = (List<SimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SimulationJob simulationJob : list) {
				if ((groupId != simulationJob.getGroupId())) {
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

			query.append(_SQL_SELECT_SIMULATIONJOB_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationJobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<SimulationJob>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SimulationJob>(list);
				}
				else {
					list = (List<SimulationJob>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first simulation job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job
	 * @throws org.kisti.edison.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findBygroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchBygroupId_First(groupId,
				orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the first simulation job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchBygroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SimulationJob> list = findBygroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job
	 * @throws org.kisti.edison.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findBygroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchBygroupId_Last(groupId,
				orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the last simulation job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchBygroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<SimulationJob> list = findBygroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulation jobs before and after the current simulation job in the ordered set where groupId = &#63;.
	 *
	 * @param simulationJobPK the primary key of the current simulation job
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation job
	 * @throws org.kisti.edison.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob[] findBygroupId_PrevAndNext(
		SimulationJobPK simulationJobPK, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = findByPrimaryKey(simulationJobPK);

		Session session = null;

		try {
			session = openSession();

			SimulationJob[] array = new SimulationJobImpl[3];

			array[0] = getBygroupId_PrevAndNext(session, simulationJob,
					groupId, orderByComparator, true);

			array[1] = simulationJob;

			array[2] = getBygroupId_PrevAndNext(session, simulationJob,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SimulationJob getBygroupId_PrevAndNext(Session session,
		SimulationJob simulationJob, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATIONJOB_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(SimulationJobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulationJob);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SimulationJob> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulation jobs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBygroupId(long groupId) throws SystemException {
		for (SimulationJob simulationJob : findBygroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulationJob);
		}
	}

	/**
	 * Returns the number of simulation jobs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBygroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATIONJOB_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "simulationJob.id.groupId = ?";

	public SimulationJobPersistenceImpl() {
		setModelClass(SimulationJob.class);
	}

	/**
	 * Caches the simulation job in the entity cache if it is enabled.
	 *
	 * @param simulationJob the simulation job
	 */
	@Override
	public void cacheResult(SimulationJob simulationJob) {
		EntityCacheUtil.putResult(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobImpl.class, simulationJob.getPrimaryKey(),
			simulationJob);

		simulationJob.resetOriginalValues();
	}

	/**
	 * Caches the simulation jobs in the entity cache if it is enabled.
	 *
	 * @param simulationJobs the simulation jobs
	 */
	@Override
	public void cacheResult(List<SimulationJob> simulationJobs) {
		for (SimulationJob simulationJob : simulationJobs) {
			if (EntityCacheUtil.getResult(
						SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
						SimulationJobImpl.class, simulationJob.getPrimaryKey()) == null) {
				cacheResult(simulationJob);
			}
			else {
				simulationJob.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all simulation jobs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SimulationJobImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SimulationJobImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the simulation job.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SimulationJob simulationJob) {
		EntityCacheUtil.removeResult(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobImpl.class, simulationJob.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SimulationJob> simulationJobs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SimulationJob simulationJob : simulationJobs) {
			EntityCacheUtil.removeResult(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
				SimulationJobImpl.class, simulationJob.getPrimaryKey());
		}
	}

	/**
	 * Creates a new simulation job with the primary key. Does not add the simulation job to the database.
	 *
	 * @param simulationJobPK the primary key for the new simulation job
	 * @return the new simulation job
	 */
	@Override
	public SimulationJob create(SimulationJobPK simulationJobPK) {
		SimulationJob simulationJob = new SimulationJobImpl();

		simulationJob.setNew(true);
		simulationJob.setPrimaryKey(simulationJobPK);

		return simulationJob;
	}

	/**
	 * Removes the simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulationJobPK the primary key of the simulation job
	 * @return the simulation job that was removed
	 * @throws org.kisti.edison.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob remove(SimulationJobPK simulationJobPK)
		throws NoSuchSimulationJobException, SystemException {
		return remove((Serializable)simulationJobPK);
	}

	/**
	 * Removes the simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the simulation job
	 * @return the simulation job that was removed
	 * @throws org.kisti.edison.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob remove(Serializable primaryKey)
		throws NoSuchSimulationJobException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SimulationJob simulationJob = (SimulationJob)session.get(SimulationJobImpl.class,
					primaryKey);

			if (simulationJob == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSimulationJobException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(simulationJob);
		}
		catch (NoSuchSimulationJobException nsee) {
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
	protected SimulationJob removeImpl(SimulationJob simulationJob)
		throws SystemException {
		simulationJob = toUnwrappedModel(simulationJob);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(simulationJob)) {
				simulationJob = (SimulationJob)session.get(SimulationJobImpl.class,
						simulationJob.getPrimaryKeyObj());
			}

			if (simulationJob != null) {
				session.delete(simulationJob);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (simulationJob != null) {
			clearCache(simulationJob);
		}

		return simulationJob;
	}

	@Override
	public SimulationJob updateImpl(
		org.kisti.edison.model.SimulationJob simulationJob)
		throws SystemException {
		simulationJob = toUnwrappedModel(simulationJob);

		boolean isNew = simulationJob.isNew();

		SimulationJobModelImpl simulationJobModelImpl = (SimulationJobModelImpl)simulationJob;

		Session session = null;

		try {
			session = openSession();

			if (simulationJob.isNew()) {
				session.save(simulationJob);

				simulationJob.setNew(false);
			}
			else {
				session.merge(simulationJob);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SimulationJobModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((simulationJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationJobModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { simulationJobModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobImpl.class, simulationJob.getPrimaryKey(),
			simulationJob);

		return simulationJob;
	}

	protected SimulationJob toUnwrappedModel(SimulationJob simulationJob) {
		if (simulationJob instanceof SimulationJobImpl) {
			return simulationJob;
		}

		SimulationJobImpl simulationJobImpl = new SimulationJobImpl();

		simulationJobImpl.setNew(simulationJob.isNew());
		simulationJobImpl.setPrimaryKey(simulationJob.getPrimaryKey());

		simulationJobImpl.setCreateDate(simulationJob.getCreateDate());
		simulationJobImpl.setGroupId(simulationJob.getGroupId());
		simulationJobImpl.setCnt(simulationJob.getCnt());

		return simulationJobImpl;
	}

	/**
	 * Returns the simulation job with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation job
	 * @return the simulation job
	 * @throws org.kisti.edison.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchByPrimaryKey(primaryKey);

		if (simulationJob == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSimulationJobException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return simulationJob;
	}

	/**
	 * Returns the simulation job with the primary key or throws a {@link org.kisti.edison.NoSuchSimulationJobException} if it could not be found.
	 *
	 * @param simulationJobPK the primary key of the simulation job
	 * @return the simulation job
	 * @throws org.kisti.edison.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findByPrimaryKey(SimulationJobPK simulationJobPK)
		throws NoSuchSimulationJobException, SystemException {
		return findByPrimaryKey((Serializable)simulationJobPK);
	}

	/**
	 * Returns the simulation job with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation job
	 * @return the simulation job, or <code>null</code> if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SimulationJob simulationJob = (SimulationJob)EntityCacheUtil.getResult(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
				SimulationJobImpl.class, primaryKey);

		if (simulationJob == _nullSimulationJob) {
			return null;
		}

		if (simulationJob == null) {
			Session session = null;

			try {
				session = openSession();

				simulationJob = (SimulationJob)session.get(SimulationJobImpl.class,
						primaryKey);

				if (simulationJob != null) {
					cacheResult(simulationJob);
				}
				else {
					EntityCacheUtil.putResult(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
						SimulationJobImpl.class, primaryKey, _nullSimulationJob);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
					SimulationJobImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return simulationJob;
	}

	/**
	 * Returns the simulation job with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param simulationJobPK the primary key of the simulation job
	 * @return the simulation job, or <code>null</code> if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchByPrimaryKey(SimulationJobPK simulationJobPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)simulationJobPK);
	}

	/**
	 * Returns all the simulation jobs.
	 *
	 * @return the simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation jobs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @return the range of simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation jobs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findAll(int start, int end,
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

		List<SimulationJob> list = (List<SimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SIMULATIONJOB);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SIMULATIONJOB;

				if (pagination) {
					sql = sql.concat(SimulationJobModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SimulationJob>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SimulationJob>(list);
				}
				else {
					list = (List<SimulationJob>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the simulation jobs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SimulationJob simulationJob : findAll()) {
			remove(simulationJob);
		}
	}

	/**
	 * Returns the number of simulation jobs.
	 *
	 * @return the number of simulation jobs
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

				Query q = session.createQuery(_SQL_COUNT_SIMULATIONJOB);

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
	 * Initializes the simulation job persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.SimulationJob")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SimulationJob>> listenersList = new ArrayList<ModelListener<SimulationJob>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SimulationJob>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SimulationJobImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SIMULATIONJOB = "SELECT simulationJob FROM SimulationJob simulationJob";
	private static final String _SQL_SELECT_SIMULATIONJOB_WHERE = "SELECT simulationJob FROM SimulationJob simulationJob WHERE ";
	private static final String _SQL_COUNT_SIMULATIONJOB = "SELECT COUNT(simulationJob) FROM SimulationJob simulationJob";
	private static final String _SQL_COUNT_SIMULATIONJOB_WHERE = "SELECT COUNT(simulationJob) FROM SimulationJob simulationJob WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "simulationJob.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SimulationJob exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SimulationJob exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SimulationJobPersistenceImpl.class);
	private static SimulationJob _nullSimulationJob = new SimulationJobImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SimulationJob> toCacheModel() {
				return _nullSimulationJobCacheModel;
			}
		};

	private static CacheModel<SimulationJob> _nullSimulationJobCacheModel = new CacheModel<SimulationJob>() {
			@Override
			public SimulationJob toEntityModel() {
				return _nullSimulationJob;
			}
		};
}