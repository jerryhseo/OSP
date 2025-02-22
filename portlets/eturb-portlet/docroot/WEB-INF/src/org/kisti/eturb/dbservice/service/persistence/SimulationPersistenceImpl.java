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

package org.kisti.eturb.dbservice.service.persistence;

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

import org.kisti.eturb.dbservice.NoSuchSimulationException;
import org.kisti.eturb.dbservice.model.Simulation;
import org.kisti.eturb.dbservice.model.impl.SimulationImpl;
import org.kisti.eturb.dbservice.model.impl.SimulationModelImpl;

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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECTID =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID =
		new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProjectId",
			new String[] { Long.class.getName() },
			SimulationModelImpl.PROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROJECTID = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the simulations where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByProjectId(long projectId)
		throws SystemException {
		return findByProjectId(projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the simulations where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.eturb.dbservice.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByProjectId(long projectId, int start, int end)
		throws SystemException {
		return findByProjectId(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.eturb.dbservice.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Simulation> findByProjectId(long projectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID;
			finderArgs = new Object[] { projectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECTID;
			finderArgs = new Object[] { projectId, start, end, orderByComparator };
		}

		List<Simulation> list = (List<Simulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Simulation simulation : list) {
				if ((projectId != simulation.getProjectId())) {
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

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

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

				qPos.add(projectId);

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
	 * Returns the first simulation in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation
	 * @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByProjectId_First(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByProjectId_First(projectId,
				orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the first simulation in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByProjectId_First(long projectId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Simulation> list = findByProjectId(projectId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation
	 * @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation findByProjectId_Last(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = fetchByProjectId_Last(projectId,
				orderByComparator);

		if (simulation != null) {
			return simulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationException(msg.toString());
	}

	/**
	 * Returns the last simulation in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation fetchByProjectId_Last(long projectId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByProjectId(projectId);

		if (count == 0) {
			return null;
		}

		List<Simulation> list = findByProjectId(projectId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulations before and after the current simulation in the ordered set where projectId = &#63;.
	 *
	 * @param simulationPK the primary key of the current simulation
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation
	 * @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Simulation[] findByProjectId_PrevAndNext(SimulationPK simulationPK,
		long projectId, OrderByComparator orderByComparator)
		throws NoSuchSimulationException, SystemException {
		Simulation simulation = findByPrimaryKey(simulationPK);

		Session session = null;

		try {
			session = openSession();

			Simulation[] array = new SimulationImpl[3];

			array[0] = getByProjectId_PrevAndNext(session, simulation,
					projectId, orderByComparator, true);

			array[1] = simulation;

			array[2] = getByProjectId_PrevAndNext(session, simulation,
					projectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Simulation getByProjectId_PrevAndNext(Session session,
		Simulation simulation, long projectId,
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

		query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

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

		qPos.add(projectId);

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
	 * Removes all the simulations where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByProjectId(long projectId) throws SystemException {
		for (Simulation simulation : findByProjectId(projectId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByProjectId(long projectId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PROJECTID;

		Object[] finderArgs = new Object[] { projectId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATION_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

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

	private static final String _FINDER_COLUMN_PROJECTID_PROJECTID_2 = "simulation.id.projectId = ?";

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
	}

	@Override
	public void clearCache(List<Simulation> simulations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Simulation simulation : simulations) {
			EntityCacheUtil.removeResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
				SimulationImpl.class, simulation.getPrimaryKey());
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
	 * @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a simulation with the primary key could not be found
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
	 * @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a simulation with the primary key could not be found
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
		org.kisti.eturb.dbservice.model.Simulation simulation)
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
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationModelImpl.getOriginalProjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID,
					args);

				args = new Object[] { simulationModelImpl.getProjectId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID,
					args);
			}
		}

		EntityCacheUtil.putResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationImpl.class, simulation.getPrimaryKey(), simulation);

		return simulation;
	}

	protected Simulation toUnwrappedModel(Simulation simulation) {
		if (simulation instanceof SimulationImpl) {
			return simulation;
		}

		SimulationImpl simulationImpl = new SimulationImpl();

		simulationImpl.setNew(simulation.isNew());
		simulationImpl.setPrimaryKey(simulation.getPrimaryKey());

		simulationImpl.setProjectId(simulation.getProjectId());
		simulationImpl.setExecuteId(simulation.getExecuteId());
		simulationImpl.setExecuteDataStructure(simulation.getExecuteDataStructure());
		simulationImpl.setExecuteDate(simulation.getExecuteDate());

		return simulationImpl;
	}

	/**
	 * Returns the simulation with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation
	 * @return the simulation
	 * @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a simulation with the primary key could not be found
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
	 * Returns the simulation with the primary key or throws a {@link org.kisti.eturb.dbservice.NoSuchSimulationException} if it could not be found.
	 *
	 * @param simulationPK the primary key of the simulation
	 * @return the simulation
	 * @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a simulation with the primary key could not be found
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.eturb.dbservice.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.eturb.dbservice.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
						"value.object.listener.org.kisti.eturb.dbservice.model.Simulation")));

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