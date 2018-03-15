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

import org.kisti.edison.bestsimulation.NoSuchSimulationJobException;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.impl.SimulationJobImpl;
import org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl;

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
 * @author EDISON
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONUUID =
		new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBysimulationUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID =
		new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBysimulationUuid", new String[] { String.class.getName() },
			SimulationJobModelImpl.SIMULATIONUUID_COLUMN_BITMASK |
			SimulationJobModelImpl.JOBSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONUUID = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysimulationUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the simulation jobs where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @return the matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBysimulationUuid(String simulationUuid)
		throws SystemException {
		return findBysimulationUuid(simulationUuid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation jobs where simulationUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationUuid the simulation uuid
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @return the range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBysimulationUuid(String simulationUuid,
		int start, int end) throws SystemException {
		return findBysimulationUuid(simulationUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation jobs where simulationUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationUuid the simulation uuid
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBysimulationUuid(String simulationUuid,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID;
			finderArgs = new Object[] { simulationUuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONUUID;
			finderArgs = new Object[] {
					simulationUuid,
					
					start, end, orderByComparator
				};
		}

		List<SimulationJob> list = (List<SimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SimulationJob simulationJob : list) {
				if (!Validator.equals(simulationUuid,
							simulationJob.getSimulationUuid())) {
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

			boolean bindSimulationUuid = false;

			if (simulationUuid == null) {
				query.append(_FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_1);
			}
			else if (simulationUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_3);
			}
			else {
				bindSimulationUuid = true;

				query.append(_FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_2);
			}

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

				if (bindSimulationUuid) {
					qPos.add(simulationUuid);
				}

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
	 * Returns the first simulation job in the ordered set where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findBysimulationUuid_First(String simulationUuid,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchBysimulationUuid_First(simulationUuid,
				orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationUuid=");
		msg.append(simulationUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the first simulation job in the ordered set where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchBysimulationUuid_First(String simulationUuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<SimulationJob> list = findBysimulationUuid(simulationUuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation job in the ordered set where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findBysimulationUuid_Last(String simulationUuid,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchBysimulationUuid_Last(simulationUuid,
				orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationUuid=");
		msg.append(simulationUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the last simulation job in the ordered set where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchBysimulationUuid_Last(String simulationUuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBysimulationUuid(simulationUuid);

		if (count == 0) {
			return null;
		}

		List<SimulationJob> list = findBysimulationUuid(simulationUuid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulation jobs before and after the current simulation job in the ordered set where simulationUuid = &#63;.
	 *
	 * @param simulationJobPK the primary key of the current simulation job
	 * @param simulationUuid the simulation uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob[] findBysimulationUuid_PrevAndNext(
		SimulationJobPK simulationJobPK, String simulationUuid,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = findByPrimaryKey(simulationJobPK);

		Session session = null;

		try {
			session = openSession();

			SimulationJob[] array = new SimulationJobImpl[3];

			array[0] = getBysimulationUuid_PrevAndNext(session, simulationJob,
					simulationUuid, orderByComparator, true);

			array[1] = simulationJob;

			array[2] = getBysimulationUuid_PrevAndNext(session, simulationJob,
					simulationUuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SimulationJob getBysimulationUuid_PrevAndNext(Session session,
		SimulationJob simulationJob, String simulationUuid,
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

		boolean bindSimulationUuid = false;

		if (simulationUuid == null) {
			query.append(_FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_1);
		}
		else if (simulationUuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_3);
		}
		else {
			bindSimulationUuid = true;

			query.append(_FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_2);
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
			query.append(SimulationJobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSimulationUuid) {
			qPos.add(simulationUuid);
		}

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
	 * Removes all the simulation jobs where simulationUuid = &#63; from the database.
	 *
	 * @param simulationUuid the simulation uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBysimulationUuid(String simulationUuid)
		throws SystemException {
		for (SimulationJob simulationJob : findBysimulationUuid(
				simulationUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulationJob);
		}
	}

	/**
	 * Returns the number of simulation jobs where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @return the number of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBysimulationUuid(String simulationUuid)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONUUID;

		Object[] finderArgs = new Object[] { simulationUuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATIONJOB_WHERE);

			boolean bindSimulationUuid = false;

			if (simulationUuid == null) {
				query.append(_FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_1);
			}
			else if (simulationUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_3);
			}
			else {
				bindSimulationUuid = true;

				query.append(_FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSimulationUuid) {
					qPos.add(simulationUuid);
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

	private static final String _FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_1 = "simulationJob.id.simulationUuid IS NULL";
	private static final String _FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_2 = "simulationJob.id.simulationUuid = ?";
	private static final String _FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_3 = "(simulationJob.id.simulationUuid IS NULL OR simulationJob.id.simulationUuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBUUID = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByjobUuid",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUUID =
		new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByjobUuid",
			new String[] { String.class.getName(), String.class.getName() },
			SimulationJobModelImpl.SIMULATIONUUID_COLUMN_BITMASK |
			SimulationJobModelImpl.JOBUUID_COLUMN_BITMASK |
			SimulationJobModelImpl.JOBSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JOBUUID = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByjobUuid",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobUuid the job uuid
	 * @return the matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findByjobUuid(String simulationUuid,
		String jobUuid) throws SystemException {
		return findByjobUuid(simulationUuid, jobUuid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobUuid the job uuid
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @return the range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findByjobUuid(String simulationUuid,
		String jobUuid, int start, int end) throws SystemException {
		return findByjobUuid(simulationUuid, jobUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobUuid the job uuid
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findByjobUuid(String simulationUuid,
		String jobUuid, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUUID;
			finderArgs = new Object[] { simulationUuid, jobUuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBUUID;
			finderArgs = new Object[] {
					simulationUuid, jobUuid,
					
					start, end, orderByComparator
				};
		}

		List<SimulationJob> list = (List<SimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SimulationJob simulationJob : list) {
				if (!Validator.equals(simulationUuid,
							simulationJob.getSimulationUuid()) ||
						!Validator.equals(jobUuid, simulationJob.getJobUuid())) {
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

			query.append(_SQL_SELECT_SIMULATIONJOB_WHERE);

			boolean bindSimulationUuid = false;

			if (simulationUuid == null) {
				query.append(_FINDER_COLUMN_JOBUUID_SIMULATIONUUID_1);
			}
			else if (simulationUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JOBUUID_SIMULATIONUUID_3);
			}
			else {
				bindSimulationUuid = true;

				query.append(_FINDER_COLUMN_JOBUUID_SIMULATIONUUID_2);
			}

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
				query.append(SimulationJobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSimulationUuid) {
					qPos.add(simulationUuid);
				}

				if (bindJobUuid) {
					qPos.add(jobUuid);
				}

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
	 * Returns the first simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobUuid the job uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findByjobUuid_First(String simulationUuid,
		String jobUuid, OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchByjobUuid_First(simulationUuid,
				jobUuid, orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationUuid=");
		msg.append(simulationUuid);

		msg.append(", jobUuid=");
		msg.append(jobUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the first simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobUuid the job uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchByjobUuid_First(String simulationUuid,
		String jobUuid, OrderByComparator orderByComparator)
		throws SystemException {
		List<SimulationJob> list = findByjobUuid(simulationUuid, jobUuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobUuid the job uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findByjobUuid_Last(String simulationUuid,
		String jobUuid, OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchByjobUuid_Last(simulationUuid,
				jobUuid, orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationUuid=");
		msg.append(simulationUuid);

		msg.append(", jobUuid=");
		msg.append(jobUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the last simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobUuid the job uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchByjobUuid_Last(String simulationUuid,
		String jobUuid, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByjobUuid(simulationUuid, jobUuid);

		if (count == 0) {
			return null;
		}

		List<SimulationJob> list = findByjobUuid(simulationUuid, jobUuid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulation jobs before and after the current simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	 *
	 * @param simulationJobPK the primary key of the current simulation job
	 * @param simulationUuid the simulation uuid
	 * @param jobUuid the job uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob[] findByjobUuid_PrevAndNext(
		SimulationJobPK simulationJobPK, String simulationUuid, String jobUuid,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = findByPrimaryKey(simulationJobPK);

		Session session = null;

		try {
			session = openSession();

			SimulationJob[] array = new SimulationJobImpl[3];

			array[0] = getByjobUuid_PrevAndNext(session, simulationJob,
					simulationUuid, jobUuid, orderByComparator, true);

			array[1] = simulationJob;

			array[2] = getByjobUuid_PrevAndNext(session, simulationJob,
					simulationUuid, jobUuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SimulationJob getByjobUuid_PrevAndNext(Session session,
		SimulationJob simulationJob, String simulationUuid, String jobUuid,
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

		boolean bindSimulationUuid = false;

		if (simulationUuid == null) {
			query.append(_FINDER_COLUMN_JOBUUID_SIMULATIONUUID_1);
		}
		else if (simulationUuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_JOBUUID_SIMULATIONUUID_3);
		}
		else {
			bindSimulationUuid = true;

			query.append(_FINDER_COLUMN_JOBUUID_SIMULATIONUUID_2);
		}

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
			query.append(SimulationJobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSimulationUuid) {
			qPos.add(simulationUuid);
		}

		if (bindJobUuid) {
			qPos.add(jobUuid);
		}

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
	 * Removes all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63; from the database.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobUuid the job uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByjobUuid(String simulationUuid, String jobUuid)
		throws SystemException {
		for (SimulationJob simulationJob : findByjobUuid(simulationUuid,
				jobUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulationJob);
		}
	}

	/**
	 * Returns the number of simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobUuid the job uuid
	 * @return the number of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByjobUuid(String simulationUuid, String jobUuid)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JOBUUID;

		Object[] finderArgs = new Object[] { simulationUuid, jobUuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SIMULATIONJOB_WHERE);

			boolean bindSimulationUuid = false;

			if (simulationUuid == null) {
				query.append(_FINDER_COLUMN_JOBUUID_SIMULATIONUUID_1);
			}
			else if (simulationUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JOBUUID_SIMULATIONUUID_3);
			}
			else {
				bindSimulationUuid = true;

				query.append(_FINDER_COLUMN_JOBUUID_SIMULATIONUUID_2);
			}

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

				if (bindSimulationUuid) {
					qPos.add(simulationUuid);
				}

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

	private static final String _FINDER_COLUMN_JOBUUID_SIMULATIONUUID_1 = "simulationJob.id.simulationUuid IS NULL AND ";
	private static final String _FINDER_COLUMN_JOBUUID_SIMULATIONUUID_2 = "simulationJob.id.simulationUuid = ? AND ";
	private static final String _FINDER_COLUMN_JOBUUID_SIMULATIONUUID_3 = "(simulationJob.id.simulationUuid IS NULL OR simulationJob.id.simulationUuid = '') AND ";
	private static final String _FINDER_COLUMN_JOBUUID_JOBUUID_1 = "simulationJob.jobUuid IS NULL";
	private static final String _FINDER_COLUMN_JOBUUID_JOBUUID_2 = "simulationJob.jobUuid = ?";
	private static final String _FINDER_COLUMN_JOBUUID_JOBUUID_3 = "(simulationJob.jobUuid IS NULL OR simulationJob.jobUuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUuid",
			new String[] { String.class.getName() },
			SimulationJobModelImpl.JOBUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns the simulation job where jobUuid = &#63; or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationJobException} if it could not be found.
	 *
	 * @param jobUuid the job uuid
	 * @return the matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findByUuid(String jobUuid)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchByUuid(jobUuid);

		if (simulationJob == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jobUuid=");
			msg.append(jobUuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSimulationJobException(msg.toString());
		}

		return simulationJob;
	}

	/**
	 * Returns the simulation job where jobUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param jobUuid the job uuid
	 * @return the matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchByUuid(String jobUuid) throws SystemException {
		return fetchByUuid(jobUuid, true);
	}

	/**
	 * Returns the simulation job where jobUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param jobUuid the job uuid
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchByUuid(String jobUuid, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { jobUuid };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID,
					finderArgs, this);
		}

		if (result instanceof SimulationJob) {
			SimulationJob simulationJob = (SimulationJob)result;

			if (!Validator.equals(jobUuid, simulationJob.getJobUuid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SIMULATIONJOB_WHERE);

			boolean bindJobUuid = false;

			if (jobUuid == null) {
				query.append(_FINDER_COLUMN_UUID_JOBUUID_1);
			}
			else if (jobUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_JOBUUID_3);
			}
			else {
				bindJobUuid = true;

				query.append(_FINDER_COLUMN_UUID_JOBUUID_2);
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

				List<SimulationJob> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SimulationJobPersistenceImpl.fetchByUuid(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SimulationJob simulationJob = list.get(0);

					result = simulationJob;

					cacheResult(simulationJob);

					if ((simulationJob.getJobUuid() == null) ||
							!simulationJob.getJobUuid().equals(jobUuid)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID,
							finderArgs, simulationJob);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SimulationJob)result;
		}
	}

	/**
	 * Removes the simulation job where jobUuid = &#63; from the database.
	 *
	 * @param jobUuid the job uuid
	 * @return the simulation job that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob removeByUuid(String jobUuid)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = findByUuid(jobUuid);

		return remove(simulationJob);
	}

	/**
	 * Returns the number of simulation jobs where jobUuid = &#63;.
	 *
	 * @param jobUuid the job uuid
	 * @return the number of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid(String jobUuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { jobUuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATIONJOB_WHERE);

			boolean bindJobUuid = false;

			if (jobUuid == null) {
				query.append(_FINDER_COLUMN_UUID_JOBUUID_1);
			}
			else if (jobUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_JOBUUID_3);
			}
			else {
				bindJobUuid = true;

				query.append(_FINDER_COLUMN_UUID_JOBUUID_2);
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

	private static final String _FINDER_COLUMN_UUID_JOBUUID_1 = "simulationJob.jobUuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_JOBUUID_2 = "simulationJob.jobUuid = ?";
	private static final String _FINDER_COLUMN_UUID_JOBUUID_3 = "(simulationJob.jobUuid IS NULL OR simulationJob.jobUuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByStatus",
			new String[] {
				Boolean.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByStatus",
			new String[] { Boolean.class.getName(), Long.class.getName() },
			SimulationJobModelImpl.JOBSUBMIT_COLUMN_BITMASK |
			SimulationJobModelImpl.JOBSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Boolean.class.getName(), Long.class.getName() });

	/**
	 * Returns all the simulation jobs where jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @return the matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findByStatus(boolean jobSubmit, long jobStatus)
		throws SystemException {
		return findByStatus(jobSubmit, jobStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation jobs where jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @return the range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findByStatus(boolean jobSubmit, long jobStatus,
		int start, int end) throws SystemException {
		return findByStatus(jobSubmit, jobStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation jobs where jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findByStatus(boolean jobSubmit, long jobStatus,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { jobSubmit, jobStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] {
					jobSubmit, jobStatus,
					
					start, end, orderByComparator
				};
		}

		List<SimulationJob> list = (List<SimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SimulationJob simulationJob : list) {
				if ((jobSubmit != simulationJob.getJobSubmit()) ||
						(jobStatus != simulationJob.getJobStatus())) {
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

			query.append(_SQL_SELECT_SIMULATIONJOB_WHERE);

			query.append(_FINDER_COLUMN_STATUS_JOBSUBMIT_2);

			query.append(_FINDER_COLUMN_STATUS_JOBSTATUS_2);

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

				qPos.add(jobSubmit);

				qPos.add(jobStatus);

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
	 * Returns the first simulation job in the ordered set where jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findByStatus_First(boolean jobSubmit, long jobStatus,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchByStatus_First(jobSubmit, jobStatus,
				orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobSubmit=");
		msg.append(jobSubmit);

		msg.append(", jobStatus=");
		msg.append(jobStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the first simulation job in the ordered set where jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchByStatus_First(boolean jobSubmit, long jobStatus,
		OrderByComparator orderByComparator) throws SystemException {
		List<SimulationJob> list = findByStatus(jobSubmit, jobStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation job in the ordered set where jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findByStatus_Last(boolean jobSubmit, long jobStatus,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchByStatus_Last(jobSubmit, jobStatus,
				orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobSubmit=");
		msg.append(jobSubmit);

		msg.append(", jobStatus=");
		msg.append(jobStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the last simulation job in the ordered set where jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchByStatus_Last(boolean jobSubmit, long jobStatus,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStatus(jobSubmit, jobStatus);

		if (count == 0) {
			return null;
		}

		List<SimulationJob> list = findByStatus(jobSubmit, jobStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulation jobs before and after the current simulation job in the ordered set where jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param simulationJobPK the primary key of the current simulation job
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob[] findByStatus_PrevAndNext(
		SimulationJobPK simulationJobPK, boolean jobSubmit, long jobStatus,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = findByPrimaryKey(simulationJobPK);

		Session session = null;

		try {
			session = openSession();

			SimulationJob[] array = new SimulationJobImpl[3];

			array[0] = getByStatus_PrevAndNext(session, simulationJob,
					jobSubmit, jobStatus, orderByComparator, true);

			array[1] = simulationJob;

			array[2] = getByStatus_PrevAndNext(session, simulationJob,
					jobSubmit, jobStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SimulationJob getByStatus_PrevAndNext(Session session,
		SimulationJob simulationJob, boolean jobSubmit, long jobStatus,
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

		query.append(_FINDER_COLUMN_STATUS_JOBSUBMIT_2);

		query.append(_FINDER_COLUMN_STATUS_JOBSTATUS_2);

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

		qPos.add(jobSubmit);

		qPos.add(jobStatus);

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
	 * Removes all the simulation jobs where jobSubmit = &#63; and jobStatus = &#63; from the database.
	 *
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByStatus(boolean jobSubmit, long jobStatus)
		throws SystemException {
		for (SimulationJob simulationJob : findByStatus(jobSubmit, jobStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulationJob);
		}
	}

	/**
	 * Returns the number of simulation jobs where jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @return the number of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByStatus(boolean jobSubmit, long jobStatus)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS;

		Object[] finderArgs = new Object[] { jobSubmit, jobStatus };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SIMULATIONJOB_WHERE);

			query.append(_FINDER_COLUMN_STATUS_JOBSUBMIT_2);

			query.append(_FINDER_COLUMN_STATUS_JOBSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jobSubmit);

				qPos.add(jobStatus);

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

	private static final String _FINDER_COLUMN_STATUS_JOBSUBMIT_2 = "simulationJob.jobSubmit = ? AND ";
	private static final String _FINDER_COLUMN_STATUS_JOBSTATUS_2 = "simulationJob.jobStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONUUID_S =
		new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySimulationUuid_S",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID_S =
		new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySimulationUuid_S",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Long.class.getName()
			},
			SimulationJobModelImpl.SIMULATIONUUID_COLUMN_BITMASK |
			SimulationJobModelImpl.JOBSUBMIT_COLUMN_BITMASK |
			SimulationJobModelImpl.JOBSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONUUID_S = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySimulationUuid_S",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the simulation jobs where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @return the matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBySimulationUuid_S(String simulationUuid,
		boolean jobSubmit, long jobStatus) throws SystemException {
		return findBySimulationUuid_S(simulationUuid, jobSubmit, jobStatus,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation jobs where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @return the range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBySimulationUuid_S(String simulationUuid,
		boolean jobSubmit, long jobStatus, int start, int end)
		throws SystemException {
		return findBySimulationUuid_S(simulationUuid, jobSubmit, jobStatus,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation jobs where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBySimulationUuid_S(String simulationUuid,
		boolean jobSubmit, long jobStatus, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID_S;
			finderArgs = new Object[] { simulationUuid, jobSubmit, jobStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONUUID_S;
			finderArgs = new Object[] {
					simulationUuid, jobSubmit, jobStatus,
					
					start, end, orderByComparator
				};
		}

		List<SimulationJob> list = (List<SimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SimulationJob simulationJob : list) {
				if (!Validator.equals(simulationUuid,
							simulationJob.getSimulationUuid()) ||
						(jobSubmit != simulationJob.getJobSubmit()) ||
						(jobStatus != simulationJob.getJobStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_SIMULATIONJOB_WHERE);

			boolean bindSimulationUuid = false;

			if (simulationUuid == null) {
				query.append(_FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_1);
			}
			else if (simulationUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_3);
			}
			else {
				bindSimulationUuid = true;

				query.append(_FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_2);
			}

			query.append(_FINDER_COLUMN_SIMULATIONUUID_S_JOBSUBMIT_2);

			query.append(_FINDER_COLUMN_SIMULATIONUUID_S_JOBSTATUS_2);

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

				if (bindSimulationUuid) {
					qPos.add(simulationUuid);
				}

				qPos.add(jobSubmit);

				qPos.add(jobStatus);

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
	 * Returns the first simulation job in the ordered set where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findBySimulationUuid_S_First(String simulationUuid,
		boolean jobSubmit, long jobStatus, OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchBySimulationUuid_S_First(simulationUuid,
				jobSubmit, jobStatus, orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationUuid=");
		msg.append(simulationUuid);

		msg.append(", jobSubmit=");
		msg.append(jobSubmit);

		msg.append(", jobStatus=");
		msg.append(jobStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the first simulation job in the ordered set where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchBySimulationUuid_S_First(String simulationUuid,
		boolean jobSubmit, long jobStatus, OrderByComparator orderByComparator)
		throws SystemException {
		List<SimulationJob> list = findBySimulationUuid_S(simulationUuid,
				jobSubmit, jobStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation job in the ordered set where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findBySimulationUuid_S_Last(String simulationUuid,
		boolean jobSubmit, long jobStatus, OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchBySimulationUuid_S_Last(simulationUuid,
				jobSubmit, jobStatus, orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationUuid=");
		msg.append(simulationUuid);

		msg.append(", jobSubmit=");
		msg.append(jobSubmit);

		msg.append(", jobStatus=");
		msg.append(jobStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the last simulation job in the ordered set where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchBySimulationUuid_S_Last(String simulationUuid,
		boolean jobSubmit, long jobStatus, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBySimulationUuid_S(simulationUuid, jobSubmit, jobStatus);

		if (count == 0) {
			return null;
		}

		List<SimulationJob> list = findBySimulationUuid_S(simulationUuid,
				jobSubmit, jobStatus, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulation jobs before and after the current simulation job in the ordered set where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param simulationJobPK the primary key of the current simulation job
	 * @param simulationUuid the simulation uuid
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob[] findBySimulationUuid_S_PrevAndNext(
		SimulationJobPK simulationJobPK, String simulationUuid,
		boolean jobSubmit, long jobStatus, OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = findByPrimaryKey(simulationJobPK);

		Session session = null;

		try {
			session = openSession();

			SimulationJob[] array = new SimulationJobImpl[3];

			array[0] = getBySimulationUuid_S_PrevAndNext(session,
					simulationJob, simulationUuid, jobSubmit, jobStatus,
					orderByComparator, true);

			array[1] = simulationJob;

			array[2] = getBySimulationUuid_S_PrevAndNext(session,
					simulationJob, simulationUuid, jobSubmit, jobStatus,
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

	protected SimulationJob getBySimulationUuid_S_PrevAndNext(Session session,
		SimulationJob simulationJob, String simulationUuid, boolean jobSubmit,
		long jobStatus, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATIONJOB_WHERE);

		boolean bindSimulationUuid = false;

		if (simulationUuid == null) {
			query.append(_FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_1);
		}
		else if (simulationUuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_3);
		}
		else {
			bindSimulationUuid = true;

			query.append(_FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_2);
		}

		query.append(_FINDER_COLUMN_SIMULATIONUUID_S_JOBSUBMIT_2);

		query.append(_FINDER_COLUMN_SIMULATIONUUID_S_JOBSTATUS_2);

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

		if (bindSimulationUuid) {
			qPos.add(simulationUuid);
		}

		qPos.add(jobSubmit);

		qPos.add(jobStatus);

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
	 * Removes all the simulation jobs where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63; from the database.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBySimulationUuid_S(String simulationUuid,
		boolean jobSubmit, long jobStatus) throws SystemException {
		for (SimulationJob simulationJob : findBySimulationUuid_S(
				simulationUuid, jobSubmit, jobStatus, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(simulationJob);
		}
	}

	/**
	 * Returns the number of simulation jobs where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param jobSubmit the job submit
	 * @param jobStatus the job status
	 * @return the number of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySimulationUuid_S(String simulationUuid,
		boolean jobSubmit, long jobStatus) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONUUID_S;

		Object[] finderArgs = new Object[] { simulationUuid, jobSubmit, jobStatus };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SIMULATIONJOB_WHERE);

			boolean bindSimulationUuid = false;

			if (simulationUuid == null) {
				query.append(_FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_1);
			}
			else if (simulationUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_3);
			}
			else {
				bindSimulationUuid = true;

				query.append(_FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_2);
			}

			query.append(_FINDER_COLUMN_SIMULATIONUUID_S_JOBSUBMIT_2);

			query.append(_FINDER_COLUMN_SIMULATIONUUID_S_JOBSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSimulationUuid) {
					qPos.add(simulationUuid);
				}

				qPos.add(jobSubmit);

				qPos.add(jobStatus);

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

	private static final String _FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_1 =
		"simulationJob.id.simulationUuid IS NULL AND ";
	private static final String _FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_2 =
		"simulationJob.id.simulationUuid = ? AND ";
	private static final String _FINDER_COLUMN_SIMULATIONUUID_S_SIMULATIONUUID_3 =
		"(simulationJob.id.simulationUuid IS NULL OR simulationJob.id.simulationUuid = '') AND ";
	private static final String _FINDER_COLUMN_SIMULATIONUUID_S_JOBSUBMIT_2 = "simulationJob.jobSubmit = ? AND ";
	private static final String _FINDER_COLUMN_SIMULATIONUUID_S_JOBSTATUS_2 = "simulationJob.jobStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBMITSTATUS =
		new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySubmitStatus",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMITSTATUS =
		new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED,
			SimulationJobImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySubmitStatus", new String[] { Boolean.class.getName() },
			SimulationJobModelImpl.JOBSUBMIT_COLUMN_BITMASK |
			SimulationJobModelImpl.JOBSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUBMITSTATUS = new FinderPath(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySubmitStatus",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the simulation jobs where jobSubmit = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @return the matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBySubmitStatus(boolean jobSubmit)
		throws SystemException {
		return findBySubmitStatus(jobSubmit, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation jobs where jobSubmit = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jobSubmit the job submit
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @return the range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBySubmitStatus(boolean jobSubmit, int start,
		int end) throws SystemException {
		return findBySubmitStatus(jobSubmit, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation jobs where jobSubmit = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jobSubmit the job submit
	 * @param start the lower bound of the range of simulation jobs
	 * @param end the upper bound of the range of simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationJob> findBySubmitStatus(boolean jobSubmit, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMITSTATUS;
			finderArgs = new Object[] { jobSubmit };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBMITSTATUS;
			finderArgs = new Object[] { jobSubmit, start, end, orderByComparator };
		}

		List<SimulationJob> list = (List<SimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SimulationJob simulationJob : list) {
				if ((jobSubmit != simulationJob.getJobSubmit())) {
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

			query.append(_FINDER_COLUMN_SUBMITSTATUS_JOBSUBMIT_2);

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

				qPos.add(jobSubmit);

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
	 * Returns the first simulation job in the ordered set where jobSubmit = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findBySubmitStatus_First(boolean jobSubmit,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchBySubmitStatus_First(jobSubmit,
				orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobSubmit=");
		msg.append(jobSubmit);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the first simulation job in the ordered set where jobSubmit = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchBySubmitStatus_First(boolean jobSubmit,
		OrderByComparator orderByComparator) throws SystemException {
		List<SimulationJob> list = findBySubmitStatus(jobSubmit, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation job in the ordered set where jobSubmit = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob findBySubmitStatus_Last(boolean jobSubmit,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = fetchBySubmitStatus_Last(jobSubmit,
				orderByComparator);

		if (simulationJob != null) {
			return simulationJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobSubmit=");
		msg.append(jobSubmit);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationJobException(msg.toString());
	}

	/**
	 * Returns the last simulation job in the ordered set where jobSubmit = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob fetchBySubmitStatus_Last(boolean jobSubmit,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySubmitStatus(jobSubmit);

		if (count == 0) {
			return null;
		}

		List<SimulationJob> list = findBySubmitStatus(jobSubmit, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulation jobs before and after the current simulation job in the ordered set where jobSubmit = &#63;.
	 *
	 * @param simulationJobPK the primary key of the current simulation job
	 * @param jobSubmit the job submit
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationJob[] findBySubmitStatus_PrevAndNext(
		SimulationJobPK simulationJobPK, boolean jobSubmit,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationJobException, SystemException {
		SimulationJob simulationJob = findByPrimaryKey(simulationJobPK);

		Session session = null;

		try {
			session = openSession();

			SimulationJob[] array = new SimulationJobImpl[3];

			array[0] = getBySubmitStatus_PrevAndNext(session, simulationJob,
					jobSubmit, orderByComparator, true);

			array[1] = simulationJob;

			array[2] = getBySubmitStatus_PrevAndNext(session, simulationJob,
					jobSubmit, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SimulationJob getBySubmitStatus_PrevAndNext(Session session,
		SimulationJob simulationJob, boolean jobSubmit,
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

		query.append(_FINDER_COLUMN_SUBMITSTATUS_JOBSUBMIT_2);

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

		qPos.add(jobSubmit);

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
	 * Removes all the simulation jobs where jobSubmit = &#63; from the database.
	 *
	 * @param jobSubmit the job submit
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBySubmitStatus(boolean jobSubmit)
		throws SystemException {
		for (SimulationJob simulationJob : findBySubmitStatus(jobSubmit,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulationJob);
		}
	}

	/**
	 * Returns the number of simulation jobs where jobSubmit = &#63;.
	 *
	 * @param jobSubmit the job submit
	 * @return the number of matching simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySubmitStatus(boolean jobSubmit) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUBMITSTATUS;

		Object[] finderArgs = new Object[] { jobSubmit };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATIONJOB_WHERE);

			query.append(_FINDER_COLUMN_SUBMITSTATUS_JOBSUBMIT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jobSubmit);

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

	private static final String _FINDER_COLUMN_SUBMITSTATUS_JOBSUBMIT_2 = "simulationJob.jobSubmit = ?";

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

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID,
			new Object[] { simulationJob.getJobUuid() }, simulationJob);

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

		clearUniqueFindersCache(simulationJob);
	}

	@Override
	public void clearCache(List<SimulationJob> simulationJobs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SimulationJob simulationJob : simulationJobs) {
			EntityCacheUtil.removeResult(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
				SimulationJobImpl.class, simulationJob.getPrimaryKey());

			clearUniqueFindersCache(simulationJob);
		}
	}

	protected void cacheUniqueFindersCache(SimulationJob simulationJob) {
		if (simulationJob.isNew()) {
			Object[] args = new Object[] { simulationJob.getJobUuid() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID, args,
				simulationJob);
		}
		else {
			SimulationJobModelImpl simulationJobModelImpl = (SimulationJobModelImpl)simulationJob;

			if ((simulationJobModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { simulationJob.getJobUuid() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID, args,
					simulationJob);
			}
		}
	}

	protected void clearUniqueFindersCache(SimulationJob simulationJob) {
		SimulationJobModelImpl simulationJobModelImpl = (SimulationJobModelImpl)simulationJob;

		Object[] args = new Object[] { simulationJob.getJobUuid() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID, args);

		if ((simulationJobModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID.getColumnBitmask()) != 0) {
			args = new Object[] { simulationJobModelImpl.getOriginalJobUuid() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID, args);
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
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
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
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
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
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob)
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
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationJobModelImpl.getOriginalSimulationUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONUUID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID,
					args);

				args = new Object[] { simulationJobModelImpl.getSimulationUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONUUID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID,
					args);
			}

			if ((simulationJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationJobModelImpl.getOriginalSimulationUuid(),
						simulationJobModelImpl.getOriginalJobUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBUUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUUID,
					args);

				args = new Object[] {
						simulationJobModelImpl.getSimulationUuid(),
						simulationJobModelImpl.getJobUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBUUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUUID,
					args);
			}

			if ((simulationJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationJobModelImpl.getOriginalJobSubmit(),
						simulationJobModelImpl.getOriginalJobStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] {
						simulationJobModelImpl.getJobSubmit(),
						simulationJobModelImpl.getJobStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((simulationJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationJobModelImpl.getOriginalSimulationUuid(),
						simulationJobModelImpl.getOriginalJobSubmit(),
						simulationJobModelImpl.getOriginalJobStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONUUID_S,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID_S,
					args);

				args = new Object[] {
						simulationJobModelImpl.getSimulationUuid(),
						simulationJobModelImpl.getJobSubmit(),
						simulationJobModelImpl.getJobStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONUUID_S,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID_S,
					args);
			}

			if ((simulationJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMITSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationJobModelImpl.getOriginalJobSubmit()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUBMITSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMITSTATUS,
					args);

				args = new Object[] { simulationJobModelImpl.getJobSubmit() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUBMITSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBMITSTATUS,
					args);
			}
		}

		EntityCacheUtil.putResult(SimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			SimulationJobImpl.class, simulationJob.getPrimaryKey(),
			simulationJob);

		clearUniqueFindersCache(simulationJob);
		cacheUniqueFindersCache(simulationJob);

		return simulationJob;
	}

	protected SimulationJob toUnwrappedModel(SimulationJob simulationJob) {
		if (simulationJob instanceof SimulationJobImpl) {
			return simulationJob;
		}

		SimulationJobImpl simulationJobImpl = new SimulationJobImpl();

		simulationJobImpl.setNew(simulationJob.isNew());
		simulationJobImpl.setPrimaryKey(simulationJob.getPrimaryKey());

		simulationJobImpl.setJobSeqNo(simulationJob.getJobSeqNo());
		simulationJobImpl.setSimulationUuid(simulationJob.getSimulationUuid());
		simulationJobImpl.setGroupId(simulationJob.getGroupId());
		simulationJobImpl.setJobUuid(simulationJob.getJobUuid());
		simulationJobImpl.setJobStatus(simulationJob.getJobStatus());
		simulationJobImpl.setJobStartDt(simulationJob.getJobStartDt());
		simulationJobImpl.setJobEndDt(simulationJob.getJobEndDt());
		simulationJobImpl.setJobTitle(simulationJob.getJobTitle());
		simulationJobImpl.setJobExecPath(simulationJob.getJobExecPath());
		simulationJobImpl.setJobPhase(simulationJob.getJobPhase());
		simulationJobImpl.setJobSubmitDt(simulationJob.getJobSubmitDt());
		simulationJobImpl.setJobUniversityField(simulationJob.getJobUniversityField());
		simulationJobImpl.setJobInputDeckYn(simulationJob.isJobInputDeckYn());
		simulationJobImpl.setJobInputDeckName(simulationJob.getJobInputDeckName());
		simulationJobImpl.setJobSubmit(simulationJob.isJobSubmit());

		return simulationJobImpl;
	}

	/**
	 * Returns the simulation job with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation job
	 * @return the simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
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
	 * Returns the simulation job with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationJobException} if it could not be found.
	 *
	 * @param simulationJobPK the primary key of the simulation job
	 * @return the simulation job
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
						"value.object.listener.org.kisti.edison.bestsimulation.model.SimulationJob")));

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