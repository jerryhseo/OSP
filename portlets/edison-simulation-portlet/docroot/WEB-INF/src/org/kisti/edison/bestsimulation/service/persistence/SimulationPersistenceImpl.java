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

import org.kisti.edison.bestsimulation.NoSuchSimulationException;
import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.impl.SimulationImpl;
import org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the simulation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see SimulationPersistence
 * @see SimulationUtil
 * @generated
 */
public class SimulationPersistenceImpl extends BasePersistenceImpl<Simulation>
	implements SimulationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SimulationUtil} to access the simulation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SimulationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_SIMULATIONUUID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBySimulationUuid",
			new String[] { String.class.getName() },
			SimulationModelImpl.SIMULATIONUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONUUID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySimulationUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns the simulation where simulationUuid = &#63; or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationException} if it could not be found.
	 *
	 * @param simulationUuid the simulation uuid
	 * @return the matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findBySimulationUuid(String simulationUuid)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchBySimulationUuid(simulationUuid);

		if (simulation == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("simulationUuid=");
			msg.append(simulationUuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSimulationException(msg.toString());
		}

		return simulation;
	}

	/**
	 * Returns the simulation where simulationUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param simulationUuid the simulation uuid
	 * @return the matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchBySimulationUuid(String simulationUuid)
		throws SystemException {
		return fetchBySimulationUuid(simulationUuid, true);
	}

	/**
	 * Returns the simulation where simulationUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchBySimulationUuid(String simulationUuid,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { simulationUuid };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SIMULATIONUUID,
					finderArgs, this);
		}

		if (result instanceof Simulation) {
			Simulation simulation = (Simulation)result;

			if (!Validator.equals(simulationUuid, simulation.getSimulationUuid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SIMULATION_WHERE);

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

				List<Simulation> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIMULATIONUUID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SimulationPersistenceImpl.fetchBySimulationUuid(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Simulation simulation = list.get(0);

					result = simulation;

					cacheResult(simulation);

					if ((simulation.getSimulationUuid() == null) ||
							!simulation.getSimulationUuid()
										   .equals(simulationUuid)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIMULATIONUUID,
							finderArgs, simulation);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIMULATIONUUID,
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
			return (Simulation)result;
		}
	}

	/**
	 * Removes the simulation where simulationUuid = &#63; from the database.
	 *
	 * @param simulationUuid the simulation uuid
	 * @return the simulation that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation removeBySimulationUuid(String simulationUuid)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findBySimulationUuid(simulationUuid);

		return remove(simulation);
	}

	/**
	 * Returns the number of simulations where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySimulationUuid(String simulationUuid)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONUUID;

		Object[] finderArgs = new Object[] { simulationUuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

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

	private static final String _FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_1 = "simulation.id.simulationUuid IS NULL";
	private static final String _FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_2 = "simulation.id.simulationUuid = ?";
	private static final String _FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_3 = "(simulation.id.simulationUuid IS NULL OR simulation.id.simulationUuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppId",
			new String[] { String.class.getName() },
			SimulationModelImpl.SCIENCEAPPID_COLUMN_BITMASK |
			SimulationModelImpl.SIMULATIONCREATEDT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the simulations where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @return the matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByAppId(String scienceAppId)
		throws SystemException {
		return findByAppId(scienceAppId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the simulations where scienceAppId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByAppId(String scienceAppId, int start, int end)
		throws SystemException {
		return findByAppId(scienceAppId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations where scienceAppId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByAppId(String scienceAppId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPID;
			finderArgs = new Object[] { scienceAppId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPID;
			finderArgs = new Object[] {
					scienceAppId,
					
					start, end, orderByComparator
				};
		}

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Simulation simulation : list) {
				if (!Validator.equals(scienceAppId, simulation.getScienceAppId())) {
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

			query.append(_SQL_SELECT_SIMULATION_WHERE);

			boolean bindScienceAppId = false;

			if (scienceAppId == null) {
				query.append(_FINDER_COLUMN_APPID_SCIENCEAPPID_1);
			}
			else if (scienceAppId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_APPID_SCIENCEAPPID_3);
			}
			else {
				bindScienceAppId = true;

				query.append(_FINDER_COLUMN_APPID_SCIENCEAPPID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppId) {
					qPos.add(scienceAppId);
				}

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Simulation>(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first simulation in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByAppId_First(String scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByAppId_First(scienceAppId,
				orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the first simulation in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByAppId_First(String scienceAppId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Simulation> list = findByAppId(scienceAppId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByAppId_Last(String scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByAppId_Last(scienceAppId,
				orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByAppId_Last(String scienceAppId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAppId(scienceAppId);

		if (count == 0) {
			return null;
		}

		List<Simulation> list = findByAppId(scienceAppId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulations before and after the current simulation in the ordered set where scienceAppId = &#63;.
	 *
	 * @param simulationPK the primary key of the current simulation
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation[] findByAppId_PrevAndNext(SimulationPK simulationPK,
		String scienceAppId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findByPrimaryKey(simulationPK);

		Session session = null;

		try {
			session = openSession();

			Simulation[] array = new SimulationImpl[3];

			array[0] = getByAppId_PrevAndNext(session, simulation,
					scienceAppId, orderByComparator, true);

			array[1] = simulation;

			array[2] = getByAppId_PrevAndNext(session, simulation,
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

	protected Simulation getByAppId_PrevAndNext(Session session,
		Simulation simulation, String scienceAppId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATION_WHERE);

		boolean bindScienceAppId = false;

		if (scienceAppId == null) {
			query.append(_FINDER_COLUMN_APPID_SCIENCEAPPID_1);
		}
		else if (scienceAppId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_APPID_SCIENCEAPPID_3);
		}
		else {
			bindScienceAppId = true;

			query.append(_FINDER_COLUMN_APPID_SCIENCEAPPID_2);
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
			query.append(SimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindScienceAppId) {
			qPos.add(scienceAppId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Simulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulations where scienceAppId = &#63; from the database.
	 *
	 * @param scienceAppId the science app ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByAppId(String scienceAppId) throws SystemException {
		for (Simulation simulation : findByAppId(scienceAppId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByAppId(String scienceAppId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_APPID;

		Object[] finderArgs = new Object[] { scienceAppId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

			boolean bindScienceAppId = false;

			if (scienceAppId == null) {
				query.append(_FINDER_COLUMN_APPID_SCIENCEAPPID_1);
			}
			else if (scienceAppId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_APPID_SCIENCEAPPID_3);
			}
			else {
				bindScienceAppId = true;

				query.append(_FINDER_COLUMN_APPID_SCIENCEAPPID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppId) {
					qPos.add(scienceAppId);
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

	private static final String _FINDER_COLUMN_APPID_SCIENCEAPPID_1 = "simulation.scienceAppId IS NULL";
	private static final String _FINDER_COLUMN_APPID_SCIENCEAPPID_2 = "simulation.scienceAppId = ?";
	private static final String _FINDER_COLUMN_APPID_SCIENCEAPPID_3 = "(simulation.scienceAppId IS NULL OR simulation.scienceAppId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			SimulationModelImpl.USERID_COLUMN_BITMASK |
			SimulationModelImpl.SIMULATIONCREATEDT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the simulations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Simulation simulation : list) {
				if ((userId != simulation.getUserId())) {
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

			query.append(_SQL_SELECT_SIMULATION_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Simulation>(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first simulation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByUserId_First(userId, orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the first simulation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Simulation> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByUserId_Last(userId, orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the last simulation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<Simulation> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulations before and after the current simulation in the ordered set where userId = &#63;.
	 *
	 * @param simulationPK the primary key of the current simulation
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation[] findByUserId_PrevAndNext(SimulationPK simulationPK,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findByPrimaryKey(simulationPK);

		Session session = null;

		try {
			session = openSession();

			Simulation[] array = new SimulationImpl[3];

			array[0] = getByUserId_PrevAndNext(session, simulation, userId,
					orderByComparator, true);

			array[1] = simulation;

			array[2] = getByUserId_PrevAndNext(session, simulation, userId,
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

	protected Simulation getByUserId_PrevAndNext(Session session,
		Simulation simulation, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATION_WHERE);

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
			query.append(SimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Simulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulations where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId(long userId) throws SystemException {
		for (Simulation simulation : findByUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserId(long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "simulation.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_G = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId_G",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId_G",
			new String[] { Long.class.getName(), Long.class.getName() },
			SimulationModelImpl.GROUPID_COLUMN_BITMASK |
			SimulationModelImpl.USERID_COLUMN_BITMASK |
			SimulationModelImpl.SIMULATIONCREATEDT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID_G = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId_G",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the simulations where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByUserId_G(long groupId, long userId)
		throws SystemException {
		return findByUserId_G(groupId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulations where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByUserId_G(long groupId, long userId,
		int start, int end) throws SystemException {
		return findByUserId_G(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByUserId_G(long groupId, long userId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_G;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Simulation simulation : list) {
				if ((groupId != simulation.getGroupId()) ||
						(userId != simulation.getUserId())) {
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

			query.append(_SQL_SELECT_SIMULATION_WHERE);

			query.append(_FINDER_COLUMN_USERID_G_GROUPID_2);

			query.append(_FINDER_COLUMN_USERID_G_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Simulation>(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first simulation in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByUserId_G_First(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByUserId_G_First(groupId, userId,
				orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the first simulation in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByUserId_G_First(long groupId, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Simulation> list = findByUserId_G(groupId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByUserId_G_Last(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByUserId_G_Last(groupId, userId,
				orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the last simulation in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByUserId_G_Last(long groupId, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId_G(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<Simulation> list = findByUserId_G(groupId, userId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulations before and after the current simulation in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param simulationPK the primary key of the current simulation
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation[] findByUserId_G_PrevAndNext(SimulationPK simulationPK,
		long groupId, long userId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findByPrimaryKey(simulationPK);

		Session session = null;

		try {
			session = openSession();

			Simulation[] array = new SimulationImpl[3];

			array[0] = getByUserId_G_PrevAndNext(session, simulation, groupId,
					userId, orderByComparator, true);

			array[1] = simulation;

			array[2] = getByUserId_G_PrevAndNext(session, simulation, groupId,
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

	protected Simulation getByUserId_G_PrevAndNext(Session session,
		Simulation simulation, long groupId, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATION_WHERE);

		query.append(_FINDER_COLUMN_USERID_G_GROUPID_2);

		query.append(_FINDER_COLUMN_USERID_G_USERID_2);

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
			query.append(SimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Simulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulations where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId_G(long groupId, long userId)
		throws SystemException {
		for (Simulation simulation : findByUserId_G(groupId, userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserId_G(long groupId, long userId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID_G;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

			query.append(_FINDER_COLUMN_USERID_G_GROUPID_2);

			query.append(_FINDER_COLUMN_USERID_G_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_USERID_G_GROUPID_2 = "simulation.id.groupId = ? AND ";
	private static final String _FINDER_COLUMN_USERID_G_USERID_2 = "simulation.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			SimulationModelImpl.GROUPID_COLUMN_BITMASK |
			SimulationModelImpl.SIMULATIONCREATEDT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the simulations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByGroupId(long groupId, int start, int end,
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

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Simulation simulation : list) {
				if ((groupId != simulation.getGroupId())) {
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

			query.append(_SQL_SELECT_SIMULATION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Simulation>(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first simulation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByGroupId_First(groupId, orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the first simulation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Simulation> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByGroupId_Last(groupId, orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the last simulation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Simulation> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulations before and after the current simulation in the ordered set where groupId = &#63;.
	 *
	 * @param simulationPK the primary key of the current simulation
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation[] findByGroupId_PrevAndNext(SimulationPK simulationPK,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findByPrimaryKey(simulationPK);

		Session session = null;

		try {
			session = openSession();

			Simulation[] array = new SimulationImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, simulation, groupId,
					orderByComparator, true);

			array[1] = simulation;

			array[2] = getByGroupId_PrevAndNext(session, simulation, groupId,
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

	protected Simulation getByGroupId_PrevAndNext(Session session,
		Simulation simulation, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATION_WHERE);

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
			query.append(SimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Simulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulations where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (Simulation simulation : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "simulation.id.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPNAME =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByScienceAppName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPNAME =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByScienceAppName",
			new String[] { String.class.getName() },
			SimulationModelImpl.SCIENCEAPPNAME_COLUMN_BITMASK |
			SimulationModelImpl.SIMULATIONCREATEDT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCIENCEAPPNAME = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByScienceAppName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the simulations where scienceAppName = &#63;.
	 *
	 * @param scienceAppName the science app name
	 * @return the matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppName(String scienceAppName)
		throws SystemException {
		return findByScienceAppName(scienceAppName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulations where scienceAppName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppName the science app name
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppName(String scienceAppName,
		int start, int end) throws SystemException {
		return findByScienceAppName(scienceAppName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations where scienceAppName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppName the science app name
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppName(String scienceAppName,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPNAME;
			finderArgs = new Object[] { scienceAppName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPNAME;
			finderArgs = new Object[] {
					scienceAppName,
					
					start, end, orderByComparator
				};
		}

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Simulation simulation : list) {
				if (!Validator.equals(scienceAppName,
							simulation.getScienceAppName())) {
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

			query.append(_SQL_SELECT_SIMULATION_WHERE);

			boolean bindScienceAppName = false;

			if (scienceAppName == null) {
				query.append(_FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_1);
			}
			else if (scienceAppName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_3);
			}
			else {
				bindScienceAppName = true;

				query.append(_FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppName) {
					qPos.add(scienceAppName);
				}

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Simulation>(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first simulation in the ordered set where scienceAppName = &#63;.
	 *
	 * @param scienceAppName the science app name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByScienceAppName_First(String scienceAppName,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByScienceAppName_First(scienceAppName,
				orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppName=");
		msg.append(scienceAppName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the first simulation in the ordered set where scienceAppName = &#63;.
	 *
	 * @param scienceAppName the science app name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByScienceAppName_First(String scienceAppName,
		OrderByComparator orderByComparator) throws SystemException {
		List<Simulation> list = findByScienceAppName(scienceAppName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppName = &#63;.
	 *
	 * @param scienceAppName the science app name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByScienceAppName_Last(String scienceAppName,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByScienceAppName_Last(scienceAppName,
				orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppName=");
		msg.append(scienceAppName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppName = &#63;.
	 *
	 * @param scienceAppName the science app name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByScienceAppName_Last(String scienceAppName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByScienceAppName(scienceAppName);

		if (count == 0) {
			return null;
		}

		List<Simulation> list = findByScienceAppName(scienceAppName, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulations before and after the current simulation in the ordered set where scienceAppName = &#63;.
	 *
	 * @param simulationPK the primary key of the current simulation
	 * @param scienceAppName the science app name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation[] findByScienceAppName_PrevAndNext(
		SimulationPK simulationPK, String scienceAppName,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findByPrimaryKey(simulationPK);

		Session session = null;

		try {
			session = openSession();

			Simulation[] array = new SimulationImpl[3];

			array[0] = getByScienceAppName_PrevAndNext(session, simulation,
					scienceAppName, orderByComparator, true);

			array[1] = simulation;

			array[2] = getByScienceAppName_PrevAndNext(session, simulation,
					scienceAppName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Simulation getByScienceAppName_PrevAndNext(Session session,
		Simulation simulation, String scienceAppName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATION_WHERE);

		boolean bindScienceAppName = false;

		if (scienceAppName == null) {
			query.append(_FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_1);
		}
		else if (scienceAppName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_3);
		}
		else {
			bindScienceAppName = true;

			query.append(_FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_2);
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
			query.append(SimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindScienceAppName) {
			qPos.add(scienceAppName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Simulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulations where scienceAppName = &#63; from the database.
	 *
	 * @param scienceAppName the science app name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScienceAppName(String scienceAppName)
		throws SystemException {
		for (Simulation simulation : findByScienceAppName(scienceAppName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations where scienceAppName = &#63;.
	 *
	 * @param scienceAppName the science app name
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByScienceAppName(String scienceAppName)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCIENCEAPPNAME;

		Object[] finderArgs = new Object[] { scienceAppName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

			boolean bindScienceAppName = false;

			if (scienceAppName == null) {
				query.append(_FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_1);
			}
			else if (scienceAppName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_3);
			}
			else {
				bindScienceAppName = true;

				query.append(_FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppName) {
					qPos.add(scienceAppName);
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

	private static final String _FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_1 = "simulation.scienceAppName IS NULL";
	private static final String _FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_2 = "simulation.scienceAppName = ?";
	private static final String _FINDER_COLUMN_SCIENCEAPPNAME_SCIENCEAPPNAME_3 = "(simulation.scienceAppName IS NULL OR simulation.scienceAppName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPID =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByScienceAppId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByScienceAppId",
			new String[] { String.class.getName() },
			SimulationModelImpl.SCIENCEAPPID_COLUMN_BITMASK |
			SimulationModelImpl.SIMULATIONCREATEDT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCIENCEAPPID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByScienceAppId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the simulations where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @return the matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId(String scienceAppId)
		throws SystemException {
		return findByScienceAppId(scienceAppId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulations where scienceAppId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId(String scienceAppId, int start,
		int end) throws SystemException {
		return findByScienceAppId(scienceAppId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations where scienceAppId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId(String scienceAppId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Simulation simulation : list) {
				if (!Validator.equals(scienceAppId, simulation.getScienceAppId())) {
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

			query.append(_SQL_SELECT_SIMULATION_WHERE);

			boolean bindScienceAppId = false;

			if (scienceAppId == null) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_1);
			}
			else if (scienceAppId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_3);
			}
			else {
				bindScienceAppId = true;

				query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppId) {
					qPos.add(scienceAppId);
				}

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Simulation>(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first simulation in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByScienceAppId_First(String scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByScienceAppId_First(scienceAppId,
				orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the first simulation in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByScienceAppId_First(String scienceAppId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Simulation> list = findByScienceAppId(scienceAppId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByScienceAppId_Last(String scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByScienceAppId_Last(scienceAppId,
				orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByScienceAppId_Last(String scienceAppId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByScienceAppId(scienceAppId);

		if (count == 0) {
			return null;
		}

		List<Simulation> list = findByScienceAppId(scienceAppId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulations before and after the current simulation in the ordered set where scienceAppId = &#63;.
	 *
	 * @param simulationPK the primary key of the current simulation
	 * @param scienceAppId the science app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation[] findByScienceAppId_PrevAndNext(
		SimulationPK simulationPK, String scienceAppId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findByPrimaryKey(simulationPK);

		Session session = null;

		try {
			session = openSession();

			Simulation[] array = new SimulationImpl[3];

			array[0] = getByScienceAppId_PrevAndNext(session, simulation,
					scienceAppId, orderByComparator, true);

			array[1] = simulation;

			array[2] = getByScienceAppId_PrevAndNext(session, simulation,
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

	protected Simulation getByScienceAppId_PrevAndNext(Session session,
		Simulation simulation, String scienceAppId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATION_WHERE);

		boolean bindScienceAppId = false;

		if (scienceAppId == null) {
			query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_1);
		}
		else if (scienceAppId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_3);
		}
		else {
			bindScienceAppId = true;

			query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_2);
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
			query.append(SimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindScienceAppId) {
			qPos.add(scienceAppId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Simulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulations where scienceAppId = &#63; from the database.
	 *
	 * @param scienceAppId the science app ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScienceAppId(String scienceAppId)
		throws SystemException {
		for (Simulation simulation : findByScienceAppId(scienceAppId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations where scienceAppId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByScienceAppId(String scienceAppId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCIENCEAPPID;

		Object[] finderArgs = new Object[] { scienceAppId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

			boolean bindScienceAppId = false;

			if (scienceAppId == null) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_1);
			}
			else if (scienceAppId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_3);
			}
			else {
				bindScienceAppId = true;

				query.append(_FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppId) {
					qPos.add(scienceAppId);
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

	private static final String _FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_1 = "simulation.scienceAppId IS NULL";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_2 = "simulation.scienceAppId = ?";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_SCIENCEAPPID_3 = "(simulation.scienceAppId IS NULL OR simulation.scienceAppId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPID_U =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByScienceAppId_U",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_U =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByScienceAppId_U",
			new String[] { String.class.getName(), Long.class.getName() },
			SimulationModelImpl.SCIENCEAPPID_COLUMN_BITMASK |
			SimulationModelImpl.USERID_COLUMN_BITMASK |
			SimulationModelImpl.SIMULATIONCREATEDT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCIENCEAPPID_U = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByScienceAppId_U",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the simulations where scienceAppId = &#63; and userId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @return the matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId_U(String scienceAppId,
		long userId) throws SystemException {
		return findByScienceAppId_U(scienceAppId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulations where scienceAppId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId_U(String scienceAppId,
		long userId, int start, int end) throws SystemException {
		return findByScienceAppId_U(scienceAppId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations where scienceAppId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId_U(String scienceAppId,
		long userId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_U;
			finderArgs = new Object[] { scienceAppId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPID_U;
			finderArgs = new Object[] {
					scienceAppId, userId,
					
					start, end, orderByComparator
				};
		}

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Simulation simulation : list) {
				if (!Validator.equals(scienceAppId, simulation.getScienceAppId()) ||
						(userId != simulation.getUserId())) {
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

			query.append(_SQL_SELECT_SIMULATION_WHERE);

			boolean bindScienceAppId = false;

			if (scienceAppId == null) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_1);
			}
			else if (scienceAppId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_3);
			}
			else {
				bindScienceAppId = true;

				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_2);
			}

			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppId) {
					qPos.add(scienceAppId);
				}

				qPos.add(userId);

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Simulation>(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first simulation in the ordered set where scienceAppId = &#63; and userId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByScienceAppId_U_First(String scienceAppId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByScienceAppId_U_First(scienceAppId,
				userId, orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the first simulation in the ordered set where scienceAppId = &#63; and userId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByScienceAppId_U_First(String scienceAppId,
		long userId, OrderByComparator orderByComparator)
		throws SystemException {
		List<Simulation> list = findByScienceAppId_U(scienceAppId, userId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppId = &#63; and userId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByScienceAppId_U_Last(String scienceAppId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByScienceAppId_U_Last(scienceAppId,
				userId, orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppId = &#63; and userId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByScienceAppId_U_Last(String scienceAppId,
		long userId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByScienceAppId_U(scienceAppId, userId);

		if (count == 0) {
			return null;
		}

		List<Simulation> list = findByScienceAppId_U(scienceAppId, userId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulations before and after the current simulation in the ordered set where scienceAppId = &#63; and userId = &#63;.
	 *
	 * @param simulationPK the primary key of the current simulation
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation[] findByScienceAppId_U_PrevAndNext(
		SimulationPK simulationPK, String scienceAppId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findByPrimaryKey(simulationPK);

		Session session = null;

		try {
			session = openSession();

			Simulation[] array = new SimulationImpl[3];

			array[0] = getByScienceAppId_U_PrevAndNext(session, simulation,
					scienceAppId, userId, orderByComparator, true);

			array[1] = simulation;

			array[2] = getByScienceAppId_U_PrevAndNext(session, simulation,
					scienceAppId, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Simulation getByScienceAppId_U_PrevAndNext(Session session,
		Simulation simulation, String scienceAppId, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATION_WHERE);

		boolean bindScienceAppId = false;

		if (scienceAppId == null) {
			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_1);
		}
		else if (scienceAppId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_3);
		}
		else {
			bindScienceAppId = true;

			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_2);
		}

		query.append(_FINDER_COLUMN_SCIENCEAPPID_U_USERID_2);

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
			query.append(SimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindScienceAppId) {
			qPos.add(scienceAppId);
		}

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Simulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulations where scienceAppId = &#63; and userId = &#63; from the database.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScienceAppId_U(String scienceAppId, long userId)
		throws SystemException {
		for (Simulation simulation : findByScienceAppId_U(scienceAppId, userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations where scienceAppId = &#63; and userId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByScienceAppId_U(String scienceAppId, long userId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCIENCEAPPID_U;

		Object[] finderArgs = new Object[] { scienceAppId, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

			boolean bindScienceAppId = false;

			if (scienceAppId == null) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_1);
			}
			else if (scienceAppId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_3);
			}
			else {
				bindScienceAppId = true;

				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_2);
			}

			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppId) {
					qPos.add(scienceAppId);
				}

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

	private static final String _FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_1 = "simulation.scienceAppId IS NULL AND ";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_2 = "simulation.scienceAppId = ? AND ";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_U_SCIENCEAPPID_3 = "(simulation.scienceAppId IS NULL OR simulation.scienceAppId = '') AND ";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_U_USERID_2 = "simulation.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPID_G =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByScienceAppId_G",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_G =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByScienceAppId_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SimulationModelImpl.SCIENCEAPPID_COLUMN_BITMASK |
			SimulationModelImpl.GROUPID_COLUMN_BITMASK |
			SimulationModelImpl.SIMULATIONCREATEDT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCIENCEAPPID_G = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByScienceAppId_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the simulations where scienceAppId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param groupId the group ID
	 * @return the matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId_G(String scienceAppId,
		long groupId) throws SystemException {
		return findByScienceAppId_G(scienceAppId, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulations where scienceAppId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId_G(String scienceAppId,
		long groupId, int start, int end) throws SystemException {
		return findByScienceAppId_G(scienceAppId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations where scienceAppId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId_G(String scienceAppId,
		long groupId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_G;
			finderArgs = new Object[] { scienceAppId, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPID_G;
			finderArgs = new Object[] {
					scienceAppId, groupId,
					
					start, end, orderByComparator
				};
		}

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Simulation simulation : list) {
				if (!Validator.equals(scienceAppId, simulation.getScienceAppId()) ||
						(groupId != simulation.getGroupId())) {
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

			query.append(_SQL_SELECT_SIMULATION_WHERE);

			boolean bindScienceAppId = false;

			if (scienceAppId == null) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_1);
			}
			else if (scienceAppId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_3);
			}
			else {
				bindScienceAppId = true;

				query.append(_FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_2);
			}

			query.append(_FINDER_COLUMN_SCIENCEAPPID_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppId) {
					qPos.add(scienceAppId);
				}

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Simulation>(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first simulation in the ordered set where scienceAppId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByScienceAppId_G_First(String scienceAppId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByScienceAppId_G_First(scienceAppId,
				groupId, orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the first simulation in the ordered set where scienceAppId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByScienceAppId_G_First(String scienceAppId,
		long groupId, OrderByComparator orderByComparator)
		throws SystemException {
		List<Simulation> list = findByScienceAppId_G(scienceAppId, groupId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByScienceAppId_G_Last(String scienceAppId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByScienceAppId_G_Last(scienceAppId,
				groupId, orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByScienceAppId_G_Last(String scienceAppId,
		long groupId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByScienceAppId_G(scienceAppId, groupId);

		if (count == 0) {
			return null;
		}

		List<Simulation> list = findByScienceAppId_G(scienceAppId, groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulations before and after the current simulation in the ordered set where scienceAppId = &#63; and groupId = &#63;.
	 *
	 * @param simulationPK the primary key of the current simulation
	 * @param scienceAppId the science app ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation[] findByScienceAppId_G_PrevAndNext(
		SimulationPK simulationPK, String scienceAppId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findByPrimaryKey(simulationPK);

		Session session = null;

		try {
			session = openSession();

			Simulation[] array = new SimulationImpl[3];

			array[0] = getByScienceAppId_G_PrevAndNext(session, simulation,
					scienceAppId, groupId, orderByComparator, true);

			array[1] = simulation;

			array[2] = getByScienceAppId_G_PrevAndNext(session, simulation,
					scienceAppId, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Simulation getByScienceAppId_G_PrevAndNext(Session session,
		Simulation simulation, String scienceAppId, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATION_WHERE);

		boolean bindScienceAppId = false;

		if (scienceAppId == null) {
			query.append(_FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_1);
		}
		else if (scienceAppId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_3);
		}
		else {
			bindScienceAppId = true;

			query.append(_FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_2);
		}

		query.append(_FINDER_COLUMN_SCIENCEAPPID_G_GROUPID_2);

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
			query.append(SimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindScienceAppId) {
			qPos.add(scienceAppId);
		}

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Simulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulations where scienceAppId = &#63; and groupId = &#63; from the database.
	 *
	 * @param scienceAppId the science app ID
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScienceAppId_G(String scienceAppId, long groupId)
		throws SystemException {
		for (Simulation simulation : findByScienceAppId_G(scienceAppId,
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations where scienceAppId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param groupId the group ID
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByScienceAppId_G(String scienceAppId, long groupId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCIENCEAPPID_G;

		Object[] finderArgs = new Object[] { scienceAppId, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

			boolean bindScienceAppId = false;

			if (scienceAppId == null) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_1);
			}
			else if (scienceAppId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_3);
			}
			else {
				bindScienceAppId = true;

				query.append(_FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_2);
			}

			query.append(_FINDER_COLUMN_SCIENCEAPPID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppId) {
					qPos.add(scienceAppId);
				}

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

	private static final String _FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_1 = "simulation.scienceAppId IS NULL AND ";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_2 = "simulation.scienceAppId = ? AND ";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_G_SCIENCEAPPID_3 = "(simulation.scienceAppId IS NULL OR simulation.scienceAppId = '') AND ";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_G_GROUPID_2 = "simulation.id.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPID_U_G =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByScienceAppId_U_G",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_U_G =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByScienceAppId_U_G",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			SimulationModelImpl.SCIENCEAPPID_COLUMN_BITMASK |
			SimulationModelImpl.USERID_COLUMN_BITMASK |
			SimulationModelImpl.GROUPID_COLUMN_BITMASK |
			SimulationModelImpl.SIMULATIONCREATEDT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCIENCEAPPID_U_G = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByScienceAppId_U_G",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the simulations where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId_U_G(String scienceAppId,
		long userId, long groupId) throws SystemException {
		return findByScienceAppId_U_G(scienceAppId, userId, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulations where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId_U_G(String scienceAppId,
		long userId, long groupId, int start, int end)
		throws SystemException {
		return findByScienceAppId_U_G(scienceAppId, userId, groupId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the simulations where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByScienceAppId_U_G(String scienceAppId,
		long userId, long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_U_G;
			finderArgs = new Object[] { scienceAppId, userId, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCIENCEAPPID_U_G;
			finderArgs = new Object[] {
					scienceAppId, userId, groupId,
					
					start, end, orderByComparator
				};
		}

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Simulation simulation : list) {
				if (!Validator.equals(scienceAppId, simulation.getScienceAppId()) ||
						(userId != simulation.getUserId()) ||
						(groupId != simulation.getGroupId())) {
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

			query.append(_SQL_SELECT_SIMULATION_WHERE);

			boolean bindScienceAppId = false;

			if (scienceAppId == null) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_1);
			}
			else if (scienceAppId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_3);
			}
			else {
				bindScienceAppId = true;

				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_2);
			}

			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_USERID_2);

			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppId) {
					qPos.add(scienceAppId);
				}

				qPos.add(userId);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Simulation>(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first simulation in the ordered set where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByScienceAppId_U_G_First(String scienceAppId,
		long userId, long groupId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByScienceAppId_U_G_First(scienceAppId,
				userId, groupId, orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the first simulation in the ordered set where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByScienceAppId_U_G_First(String scienceAppId,
		long userId, long groupId, OrderByComparator orderByComparator)
		throws SystemException {
		List<Simulation> list = findByScienceAppId_U_G(scienceAppId, userId,
				groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByScienceAppId_U_G_Last(String scienceAppId,
		long userId, long groupId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByScienceAppId_U_G_Last(scienceAppId,
				userId, groupId, orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scienceAppId=");
		msg.append(scienceAppId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the last simulation in the ordered set where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByScienceAppId_U_G_Last(String scienceAppId,
		long userId, long groupId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByScienceAppId_U_G(scienceAppId, userId, groupId);

		if (count == 0) {
			return null;
		}

		List<Simulation> list = findByScienceAppId_U_G(scienceAppId, userId,
				groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulations before and after the current simulation in the ordered set where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	 *
	 * @param simulationPK the primary key of the current simulation
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation[] findByScienceAppId_U_G_PrevAndNext(
		SimulationPK simulationPK, String scienceAppId, long userId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findByPrimaryKey(simulationPK);

		Session session = null;

		try {
			session = openSession();

			Simulation[] array = new SimulationImpl[3];

			array[0] = getByScienceAppId_U_G_PrevAndNext(session, simulation,
					scienceAppId, userId, groupId, orderByComparator, true);

			array[1] = simulation;

			array[2] = getByScienceAppId_U_G_PrevAndNext(session, simulation,
					scienceAppId, userId, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Simulation getByScienceAppId_U_G_PrevAndNext(Session session,
		Simulation simulation, String scienceAppId, long userId, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATION_WHERE);

		boolean bindScienceAppId = false;

		if (scienceAppId == null) {
			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_1);
		}
		else if (scienceAppId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_3);
		}
		else {
			bindScienceAppId = true;

			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_2);
		}

		query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_USERID_2);

		query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_GROUPID_2);

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
			query.append(SimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindScienceAppId) {
			qPos.add(scienceAppId);
		}

		qPos.add(userId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Simulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulations where scienceAppId = &#63; and userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByScienceAppId_U_G(String scienceAppId, long userId,
		long groupId) throws SystemException {
		for (Simulation simulation : findByScienceAppId_U_G(scienceAppId,
				userId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	 *
	 * @param scienceAppId the science app ID
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByScienceAppId_U_G(String scienceAppId, long userId,
		long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCIENCEAPPID_U_G;

		Object[] finderArgs = new Object[] { scienceAppId, userId, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

			boolean bindScienceAppId = false;

			if (scienceAppId == null) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_1);
			}
			else if (scienceAppId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_3);
			}
			else {
				bindScienceAppId = true;

				query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_2);
			}

			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_USERID_2);

			query.append(_FINDER_COLUMN_SCIENCEAPPID_U_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindScienceAppId) {
					qPos.add(scienceAppId);
				}

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_1 = "simulation.scienceAppId IS NULL AND ";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_2 = "simulation.scienceAppId = ? AND ";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_U_G_SCIENCEAPPID_3 = "(simulation.scienceAppId IS NULL OR simulation.scienceAppId = '') AND ";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_U_G_USERID_2 = "simulation.userId = ? AND ";
	private static final String _FINDER_COLUMN_SCIENCEAPPID_U_G_GROUPID_2 = "simulation.id.groupId = ?";

	public SimulationPersistenceImpl() {
		setModelClass(Simulation.class);
	}

	/**
	 * Caches the simulation in the entity cache if it is enabled.
	 *
	 * @param simulation the simulation
	 */
	@Override
	public void cacheResult(Simulation simulation) {
		EntityCacheUtil.putResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationImpl.class, simulation.getPrimaryKey(), simulation);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIMULATIONUUID,
			new Object[] { simulation.getSimulationUuid() }, simulation);

		simulation.resetOriginalValues();
	}

	/**
	 * Caches the simulations in the entity cache if it is enabled.
	 *
	 * @param simulations the simulations
	 */
	@Override
	public void cacheResult(List<Simulation> simulations) {
		for (Simulation simulation : simulations) {
			if (EntityCacheUtil.getResult(
						SimulationModelImpl.ENTITY_CACHE_ENABLED,
						SimulationImpl.class, simulation.getPrimaryKey()) == null) {
				cacheResult(simulation);
			}
			else {
				simulation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all simulations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SimulationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SimulationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the simulation.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Simulation simulation) {
		EntityCacheUtil.removeResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationImpl.class, simulation.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(simulation);
	}

	@Override
	public void clearCache(List<Simulation> simulations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Simulation simulation : simulations) {
			EntityCacheUtil.removeResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
				SimulationImpl.class, simulation.getPrimaryKey());

			clearUniqueFindersCache(simulation);
		}
	}

	protected void cacheUniqueFindersCache(Simulation simulation) {
		if (simulation.isNew()) {
			Object[] args = new Object[] { simulation.getSimulationUuid() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SIMULATIONUUID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIMULATIONUUID,
				args, simulation);
		}
		else {
			SimulationModelImpl simulationModelImpl = (SimulationModelImpl)simulation;

			if ((simulationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SIMULATIONUUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { simulation.getSimulationUuid() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SIMULATIONUUID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIMULATIONUUID,
					args, simulation);
			}
		}
	}

	protected void clearUniqueFindersCache(Simulation simulation) {
		SimulationModelImpl simulationModelImpl = (SimulationModelImpl)simulation;

		Object[] args = new Object[] { simulation.getSimulationUuid() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONUUID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIMULATIONUUID, args);

		if ((simulationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SIMULATIONUUID.getColumnBitmask()) != 0) {
			args = new Object[] { simulationModelImpl.getOriginalSimulationUuid() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONUUID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIMULATIONUUID,
				args);
		}
	}

	/**
	 * Creates a new simulation with the primary key. Does not add the simulation to the database.
	 *
	 * @param simulationPK the primary key for the new simulation
	 * @return the new simulation
	 */
	@Override
	public Simulation create(SimulationPK simulationPK) {
		Simulation simulation = new SimulationImpl();

		simulation.setNew(true);
		simulation.setPrimaryKey(simulationPK);

		return simulation;
	}

	/**
	 * Removes the simulation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulationPK the primary key of the simulation
	 * @return the simulation that was removed
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation remove(SimulationPK simulationPK)
		throws NoSuchSimulationException, SystemException {
		return remove((Serializable)simulationPK);
	}

	/**
	 * Removes the simulation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the simulation
	 * @return the simulation that was removed
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation remove(Serializable primaryKey)
		throws NoSuchSimulationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Simulation simulation = (Simulation)session.get(SimulationImpl.class,
					primaryKey);

			if (simulation == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSimulationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(simulation);
		}
		catch (NoSuchSimulationException nsee) {
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
	protected Simulation removeImpl(Simulation simulation)
		throws SystemException {
		simulation = toUnwrappedModel(simulation);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(simulation)) {
				simulation = (Simulation)session.get(SimulationImpl.class,
						simulation.getPrimaryKeyObj());
			}

			if (simulation != null) {
				session.delete(simulation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (simulation != null) {
			clearCache(simulation);
		}

		return simulation;
	}

	@Override
	public Simulation updateImpl(
		org.kisti.edison.bestsimulation.model.Simulation simulation)
		throws SystemException {
		simulation = toUnwrappedModel(simulation);

		boolean isNew = simulation.isNew();

		SimulationModelImpl simulationModelImpl = (SimulationModelImpl)simulation;

		Session session = null;

		try {
			session = openSession();

			if (simulation.isNew()) {
				session.save(simulation);

				simulation.setNew(false);
			}
			else {
				session.merge(simulation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SimulationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((simulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationModelImpl.getOriginalScienceAppId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPID,
					args);

				args = new Object[] { simulationModelImpl.getScienceAppId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPID,
					args);
			}

			if ((simulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { simulationModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((simulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationModelImpl.getOriginalGroupId(),
						simulationModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G,
					args);

				args = new Object[] {
						simulationModelImpl.getGroupId(),
						simulationModelImpl.getUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G,
					args);
			}

			if ((simulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { simulationModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((simulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationModelImpl.getOriginalScienceAppName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPNAME,
					args);

				args = new Object[] { simulationModelImpl.getScienceAppName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPNAME,
					args);
			}

			if ((simulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationModelImpl.getOriginalScienceAppId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID,
					args);

				args = new Object[] { simulationModelImpl.getScienceAppId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID,
					args);
			}

			if ((simulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationModelImpl.getOriginalScienceAppId(),
						simulationModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID_U,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_U,
					args);

				args = new Object[] {
						simulationModelImpl.getScienceAppId(),
						simulationModelImpl.getUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID_U,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_U,
					args);
			}

			if ((simulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationModelImpl.getOriginalScienceAppId(),
						simulationModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID_G,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_G,
					args);

				args = new Object[] {
						simulationModelImpl.getScienceAppId(),
						simulationModelImpl.getGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID_G,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_G,
					args);
			}

			if ((simulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_U_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationModelImpl.getOriginalScienceAppId(),
						simulationModelImpl.getOriginalUserId(),
						simulationModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID_U_G,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_U_G,
					args);

				args = new Object[] {
						simulationModelImpl.getScienceAppId(),
						simulationModelImpl.getUserId(),
						simulationModelImpl.getGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCIENCEAPPID_U_G,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCIENCEAPPID_U_G,
					args);
			}
		}

		EntityCacheUtil.putResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationImpl.class, simulation.getPrimaryKey(), simulation);

		clearUniqueFindersCache(simulation);
		cacheUniqueFindersCache(simulation);

		return simulation;
	}

	protected Simulation toUnwrappedModel(Simulation simulation) {
		if (simulation instanceof SimulationImpl) {
			return simulation;
		}

		SimulationImpl simulationImpl = new SimulationImpl();

		simulationImpl.setNew(simulation.isNew());
		simulationImpl.setPrimaryKey(simulation.getPrimaryKey());

		simulationImpl.setSimulationUuid(simulation.getSimulationUuid());
		simulationImpl.setGroupId(simulation.getGroupId());
		simulationImpl.setUserId(simulation.getUserId());
		simulationImpl.setSimulationTitle(simulation.getSimulationTitle());
		simulationImpl.setScienceAppId(simulation.getScienceAppId());
		simulationImpl.setScienceAppName(simulation.getScienceAppName());
		simulationImpl.setScienceAppVersion(simulation.getScienceAppVersion());
		simulationImpl.setSimulationCreateDt(simulation.getSimulationCreateDt());
		simulationImpl.setCluster(simulation.getCluster());
		simulationImpl.setClassId(simulation.getClassId());
		simulationImpl.setCustomId(simulation.getCustomId());
		simulationImpl.setTestYn(simulation.isTestYn());

		return simulationImpl;
	}

	/**
	 * Returns the simulation with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation
	 * @return the simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByPrimaryKey(primaryKey);

		if (simulation == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSimulationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return simulation;
	}

	/**
	 * Returns the simulation with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationException} if it could not be found.
	 *
	 * @param simulationPK the primary key of the simulation
	 * @return the simulation
	 * @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByPrimaryKey(SimulationPK simulationPK)
		throws NoSuchSimulationException, SystemException {
		return findByPrimaryKey((Serializable)simulationPK);
	}

	/**
	 * Returns the simulation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation
	 * @return the simulation, or <code>null</code> if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Simulation simulation = (Simulation)EntityCacheUtil.getResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
				SimulationImpl.class, primaryKey);

		if (simulation == _nullSimulation) {
			return null;
		}

		if (simulation == null) {
			Session session = null;

			try {
				session = openSession();

				simulation = (Simulation)session.get(SimulationImpl.class,
						primaryKey);

				if (simulation != null) {
					cacheResult(simulation);
				}
				else {
					EntityCacheUtil.putResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
						SimulationImpl.class, primaryKey, _nullSimulation);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
					SimulationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return simulation;
	}

	/**
	 * Returns the simulation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param simulationPK the primary key of the simulation
	 * @return the simulation, or <code>null</code> if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByPrimaryKey(SimulationPK simulationPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)simulationPK);
	}

	/**
	 * Returns all the simulations.
	 *
	 * @return the simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findAll(int start, int end,
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

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SIMULATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SIMULATION;

				if (pagination) {
					sql = sql.concat(SimulationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Simulation>(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the simulations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Simulation simulation : findAll()) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations.
	 *
	 * @return the number of simulations
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

				Query q = session.createQuery(_SQL_COUNT_SIMULATION);

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
	 * Initializes the simulation persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.bestsimulation.model.Simulation")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Simulation>> listenersList = new ArrayList<ModelListener<Simulation>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Simulation>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SimulationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SIMULATION = "SELECT simulation FROM Simulation simulation";
	private static final String _SQL_SELECT_SIMULATION_WHERE = "SELECT simulation FROM Simulation simulation WHERE ";
	private static final String _SQL_COUNT_SIMULATION = "SELECT COUNT(simulation) FROM Simulation simulation";
	private static final String _SQL_COUNT_SIMULATION_WHERE = "SELECT COUNT(simulation) FROM Simulation simulation WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "simulation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Simulation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Simulation exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SimulationPersistenceImpl.class);
	private static Simulation _nullSimulation = new SimulationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Simulation> toCacheModel() {
				return _nullSimulationCacheModel;
			}
		};

	private static CacheModel<Simulation> _nullSimulationCacheModel = new CacheModel<Simulation>() {
			@Override
			public Simulation toEntityModel() {
				return _nullSimulation;
			}
		};
}