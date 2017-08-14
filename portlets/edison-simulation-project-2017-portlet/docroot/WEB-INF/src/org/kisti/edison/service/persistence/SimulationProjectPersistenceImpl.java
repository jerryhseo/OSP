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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.kisti.edison.NoSuchSimulationProjectException;
import org.kisti.edison.model.SimulationProject;
import org.kisti.edison.model.impl.SimulationProjectImpl;
import org.kisti.edison.model.impl.SimulationProjectModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the simulation project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see SimulationProjectPersistence
 * @see SimulationProjectUtil
 * @generated
 */
public class SimulationProjectPersistenceImpl extends BasePersistenceImpl<SimulationProject>
	implements SimulationProjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SimulationProjectUtil} to access the simulation project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SimulationProjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
			SimulationProjectModelImpl.FINDER_CACHE_ENABLED,
			SimulationProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
			SimulationProjectModelImpl.FINDER_CACHE_ENABLED,
			SimulationProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
			SimulationProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OWNERID = new FinderPath(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
			SimulationProjectModelImpl.FINDER_CACHE_ENABLED,
			SimulationProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOwnerId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OWNERID =
		new FinderPath(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
			SimulationProjectModelImpl.FINDER_CACHE_ENABLED,
			SimulationProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOwnerId",
			new String[] { Long.class.getName() },
			SimulationProjectModelImpl.OWNERID_COLUMN_BITMASK |
			SimulationProjectModelImpl.INSERTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OWNERID = new FinderPath(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
			SimulationProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOwnerId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the simulation projects where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @return the matching simulation projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationProject> findByOwnerId(long ownerId)
		throws SystemException {
		return findByOwnerId(ownerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation projects where ownerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param start the lower bound of the range of simulation projects
	 * @param end the upper bound of the range of simulation projects (not inclusive)
	 * @return the range of matching simulation projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationProject> findByOwnerId(long ownerId, int start,
		int end) throws SystemException {
		return findByOwnerId(ownerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation projects where ownerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param start the lower bound of the range of simulation projects
	 * @param end the upper bound of the range of simulation projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching simulation projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationProject> findByOwnerId(long ownerId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OWNERID;
			finderArgs = new Object[] { ownerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OWNERID;
			finderArgs = new Object[] { ownerId, start, end, orderByComparator };
		}

		List<SimulationProject> list = (List<SimulationProject>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SimulationProject simulationProject : list) {
				if ((ownerId != simulationProject.getOwnerId())) {
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

			query.append(_SQL_SELECT_SIMULATIONPROJECT_WHERE);

			query.append(_FINDER_COLUMN_OWNERID_OWNERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SimulationProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerId);

				if (!pagination) {
					list = (List<SimulationProject>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SimulationProject>(list);
				}
				else {
					list = (List<SimulationProject>)QueryUtil.list(q,
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
	 * Returns the first simulation project in the ordered set where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation project
	 * @throws org.kisti.edison.NoSuchSimulationProjectException if a matching simulation project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject findByOwnerId_First(long ownerId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationProjectException, SystemException {
		SimulationProject simulationProject = fetchByOwnerId_First(ownerId,
				orderByComparator);

		if (simulationProject != null) {
			return simulationProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ownerId=");
		msg.append(ownerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationProjectException(msg.toString());
	}

	/**
	 * Returns the first simulation project in the ordered set where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching simulation project, or <code>null</code> if a matching simulation project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject fetchByOwnerId_First(long ownerId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SimulationProject> list = findByOwnerId(ownerId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last simulation project in the ordered set where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation project
	 * @throws org.kisti.edison.NoSuchSimulationProjectException if a matching simulation project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject findByOwnerId_Last(long ownerId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationProjectException, SystemException {
		SimulationProject simulationProject = fetchByOwnerId_Last(ownerId,
				orderByComparator);

		if (simulationProject != null) {
			return simulationProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ownerId=");
		msg.append(ownerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSimulationProjectException(msg.toString());
	}

	/**
	 * Returns the last simulation project in the ordered set where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching simulation project, or <code>null</code> if a matching simulation project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject fetchByOwnerId_Last(long ownerId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOwnerId(ownerId);

		if (count == 0) {
			return null;
		}

		List<SimulationProject> list = findByOwnerId(ownerId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the simulation projects before and after the current simulation project in the ordered set where ownerId = &#63;.
	 *
	 * @param simulationProjectId the primary key of the current simulation project
	 * @param ownerId the owner ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next simulation project
	 * @throws org.kisti.edison.NoSuchSimulationProjectException if a simulation project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject[] findByOwnerId_PrevAndNext(
		long simulationProjectId, long ownerId,
		OrderByComparator orderByComparator)
		throws NoSuchSimulationProjectException, SystemException {
		SimulationProject simulationProject = findByPrimaryKey(simulationProjectId);

		Session session = null;

		try {
			session = openSession();

			SimulationProject[] array = new SimulationProjectImpl[3];

			array[0] = getByOwnerId_PrevAndNext(session, simulationProject,
					ownerId, orderByComparator, true);

			array[1] = simulationProject;

			array[2] = getByOwnerId_PrevAndNext(session, simulationProject,
					ownerId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SimulationProject getByOwnerId_PrevAndNext(Session session,
		SimulationProject simulationProject, long ownerId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SIMULATIONPROJECT_WHERE);

		query.append(_FINDER_COLUMN_OWNERID_OWNERID_2);

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
			query.append(SimulationProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ownerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(simulationProject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SimulationProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the simulation projects where ownerId = &#63; from the database.
	 *
	 * @param ownerId the owner ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByOwnerId(long ownerId) throws SystemException {
		for (SimulationProject simulationProject : findByOwnerId(ownerId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(simulationProject);
		}
	}

	/**
	 * Returns the number of simulation projects where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @return the number of matching simulation projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByOwnerId(long ownerId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_OWNERID;

		Object[] finderArgs = new Object[] { ownerId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SIMULATIONPROJECT_WHERE);

			query.append(_FINDER_COLUMN_OWNERID_OWNERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerId);

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

	private static final String _FINDER_COLUMN_OWNERID_OWNERID_2 = "simulationProject.ownerId = ?";

	public SimulationProjectPersistenceImpl() {
		setModelClass(SimulationProject.class);
	}

	/**
	 * Caches the simulation project in the entity cache if it is enabled.
	 *
	 * @param simulationProject the simulation project
	 */
	@Override
	public void cacheResult(SimulationProject simulationProject) {
		EntityCacheUtil.putResult(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
			SimulationProjectImpl.class, simulationProject.getPrimaryKey(),
			simulationProject);

		simulationProject.resetOriginalValues();
	}

	/**
	 * Caches the simulation projects in the entity cache if it is enabled.
	 *
	 * @param simulationProjects the simulation projects
	 */
	@Override
	public void cacheResult(List<SimulationProject> simulationProjects) {
		for (SimulationProject simulationProject : simulationProjects) {
			if (EntityCacheUtil.getResult(
						SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
						SimulationProjectImpl.class,
						simulationProject.getPrimaryKey()) == null) {
				cacheResult(simulationProject);
			}
			else {
				simulationProject.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all simulation projects.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SimulationProjectImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SimulationProjectImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the simulation project.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SimulationProject simulationProject) {
		EntityCacheUtil.removeResult(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
			SimulationProjectImpl.class, simulationProject.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SimulationProject> simulationProjects) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SimulationProject simulationProject : simulationProjects) {
			EntityCacheUtil.removeResult(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
				SimulationProjectImpl.class, simulationProject.getPrimaryKey());
		}
	}

	/**
	 * Creates a new simulation project with the primary key. Does not add the simulation project to the database.
	 *
	 * @param simulationProjectId the primary key for the new simulation project
	 * @return the new simulation project
	 */
	@Override
	public SimulationProject create(long simulationProjectId) {
		SimulationProject simulationProject = new SimulationProjectImpl();

		simulationProject.setNew(true);
		simulationProject.setPrimaryKey(simulationProjectId);

		return simulationProject;
	}

	/**
	 * Removes the simulation project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulationProjectId the primary key of the simulation project
	 * @return the simulation project that was removed
	 * @throws org.kisti.edison.NoSuchSimulationProjectException if a simulation project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject remove(long simulationProjectId)
		throws NoSuchSimulationProjectException, SystemException {
		return remove((Serializable)simulationProjectId);
	}

	/**
	 * Removes the simulation project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the simulation project
	 * @return the simulation project that was removed
	 * @throws org.kisti.edison.NoSuchSimulationProjectException if a simulation project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject remove(Serializable primaryKey)
		throws NoSuchSimulationProjectException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SimulationProject simulationProject = (SimulationProject)session.get(SimulationProjectImpl.class,
					primaryKey);

			if (simulationProject == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSimulationProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(simulationProject);
		}
		catch (NoSuchSimulationProjectException nsee) {
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
	protected SimulationProject removeImpl(SimulationProject simulationProject)
		throws SystemException {
		simulationProject = toUnwrappedModel(simulationProject);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(simulationProject)) {
				simulationProject = (SimulationProject)session.get(SimulationProjectImpl.class,
						simulationProject.getPrimaryKeyObj());
			}

			if (simulationProject != null) {
				session.delete(simulationProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (simulationProject != null) {
			clearCache(simulationProject);
		}

		return simulationProject;
	}

	@Override
	public SimulationProject updateImpl(
		org.kisti.edison.model.SimulationProject simulationProject)
		throws SystemException {
		simulationProject = toUnwrappedModel(simulationProject);

		boolean isNew = simulationProject.isNew();

		SimulationProjectModelImpl simulationProjectModelImpl = (SimulationProjectModelImpl)simulationProject;

		Session session = null;

		try {
			session = openSession();

			if (simulationProject.isNew()) {
				session.save(simulationProject);

				simulationProject.setNew(false);
			}
			else {
				session.merge(simulationProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SimulationProjectModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((simulationProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OWNERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						simulationProjectModelImpl.getOriginalOwnerId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OWNERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OWNERID,
					args);

				args = new Object[] { simulationProjectModelImpl.getOwnerId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OWNERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OWNERID,
					args);
			}
		}

		EntityCacheUtil.putResult(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
			SimulationProjectImpl.class, simulationProject.getPrimaryKey(),
			simulationProject);

		return simulationProject;
	}

	protected SimulationProject toUnwrappedModel(
		SimulationProject simulationProject) {
		if (simulationProject instanceof SimulationProjectImpl) {
			return simulationProject;
		}

		SimulationProjectImpl simulationProjectImpl = new SimulationProjectImpl();

		simulationProjectImpl.setNew(simulationProject.isNew());
		simulationProjectImpl.setPrimaryKey(simulationProject.getPrimaryKey());

		simulationProjectImpl.setSimulationProjectId(simulationProject.getSimulationProjectId());
		simulationProjectImpl.setTitle(simulationProject.getTitle());
		simulationProjectImpl.setServiceLanguage(simulationProject.getServiceLanguage());
		simulationProjectImpl.setProjectOpenYn(simulationProject.isProjectOpenYn());
		simulationProjectImpl.setExplain(simulationProject.getExplain());
		simulationProjectImpl.setIconId(simulationProject.getIconId());
		simulationProjectImpl.setImageFolderId(simulationProject.getImageFolderId());
		simulationProjectImpl.setOwnerId(simulationProject.getOwnerId());
		simulationProjectImpl.setInsertId(simulationProject.getInsertId());
		simulationProjectImpl.setInsertDate(simulationProject.getInsertDate());
		simulationProjectImpl.setUpdateId(simulationProject.getUpdateId());
		simulationProjectImpl.setUpdateDate(simulationProject.getUpdateDate());
		simulationProjectImpl.setTempletId(simulationProject.getTempletId());

		return simulationProjectImpl;
	}

	/**
	 * Returns the simulation project with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation project
	 * @return the simulation project
	 * @throws org.kisti.edison.NoSuchSimulationProjectException if a simulation project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSimulationProjectException, SystemException {
		SimulationProject simulationProject = fetchByPrimaryKey(primaryKey);

		if (simulationProject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSimulationProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return simulationProject;
	}

	/**
	 * Returns the simulation project with the primary key or throws a {@link org.kisti.edison.NoSuchSimulationProjectException} if it could not be found.
	 *
	 * @param simulationProjectId the primary key of the simulation project
	 * @return the simulation project
	 * @throws org.kisti.edison.NoSuchSimulationProjectException if a simulation project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject findByPrimaryKey(long simulationProjectId)
		throws NoSuchSimulationProjectException, SystemException {
		return findByPrimaryKey((Serializable)simulationProjectId);
	}

	/**
	 * Returns the simulation project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation project
	 * @return the simulation project, or <code>null</code> if a simulation project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SimulationProject simulationProject = (SimulationProject)EntityCacheUtil.getResult(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
				SimulationProjectImpl.class, primaryKey);

		if (simulationProject == _nullSimulationProject) {
			return null;
		}

		if (simulationProject == null) {
			Session session = null;

			try {
				session = openSession();

				simulationProject = (SimulationProject)session.get(SimulationProjectImpl.class,
						primaryKey);

				if (simulationProject != null) {
					cacheResult(simulationProject);
				}
				else {
					EntityCacheUtil.putResult(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
						SimulationProjectImpl.class, primaryKey,
						_nullSimulationProject);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SimulationProjectModelImpl.ENTITY_CACHE_ENABLED,
					SimulationProjectImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return simulationProject;
	}

	/**
	 * Returns the simulation project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param simulationProjectId the primary key of the simulation project
	 * @return the simulation project, or <code>null</code> if a simulation project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SimulationProject fetchByPrimaryKey(long simulationProjectId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)simulationProjectId);
	}

	/**
	 * Returns all the simulation projects.
	 *
	 * @return the simulation projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationProject> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulation projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulation projects
	 * @param end the upper bound of the range of simulation projects (not inclusive)
	 * @return the range of simulation projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationProject> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulation projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulation projects
	 * @param end the upper bound of the range of simulation projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of simulation projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SimulationProject> findAll(int start, int end,
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

		List<SimulationProject> list = (List<SimulationProject>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SIMULATIONPROJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SIMULATIONPROJECT;

				if (pagination) {
					sql = sql.concat(SimulationProjectModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SimulationProject>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SimulationProject>(list);
				}
				else {
					list = (List<SimulationProject>)QueryUtil.list(q,
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
	 * Removes all the simulation projects from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SimulationProject simulationProject : findAll()) {
			remove(simulationProject);
		}
	}

	/**
	 * Returns the number of simulation projects.
	 *
	 * @return the number of simulation projects
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

				Query q = session.createQuery(_SQL_COUNT_SIMULATIONPROJECT);

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
	 * Initializes the simulation project persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.SimulationProject")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SimulationProject>> listenersList = new ArrayList<ModelListener<SimulationProject>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SimulationProject>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SimulationProjectImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SIMULATIONPROJECT = "SELECT simulationProject FROM SimulationProject simulationProject";
	private static final String _SQL_SELECT_SIMULATIONPROJECT_WHERE = "SELECT simulationProject FROM SimulationProject simulationProject WHERE ";
	private static final String _SQL_COUNT_SIMULATIONPROJECT = "SELECT COUNT(simulationProject) FROM SimulationProject simulationProject";
	private static final String _SQL_COUNT_SIMULATIONPROJECT_WHERE = "SELECT COUNT(simulationProject) FROM SimulationProject simulationProject WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "simulationProject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SimulationProject exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SimulationProject exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SimulationProjectPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"explain"
			});
	private static SimulationProject _nullSimulationProject = new SimulationProjectImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SimulationProject> toCacheModel() {
				return _nullSimulationProjectCacheModel;
			}
		};

	private static CacheModel<SimulationProject> _nullSimulationProjectCacheModel =
		new CacheModel<SimulationProject>() {
			@Override
			public SimulationProject toEntityModel() {
				return _nullSimulationProject;
			}
		};
}