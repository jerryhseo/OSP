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

package org.kisti.edison.osp.service.persistence;

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

import org.kisti.edison.osp.NoSuchProjectException;
import org.kisti.edison.osp.model.Project;
import org.kisti.edison.osp.model.impl.ProjectImpl;
import org.kisti.edison.osp.model.impl.ProjectModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ProjectPersistence
 * @see ProjectUtil
 * @generated
 */
public class ProjectPersistenceImpl extends BasePersistenceImpl<Project>
	implements ProjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProjectUtil} to access the project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectModelImpl.ENTITY_CACHE_ENABLED,
			ProjectModelImpl.FINDER_CACHE_ENABLED, ProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectModelImpl.ENTITY_CACHE_ENABLED,
			ProjectModelImpl.FINDER_CACHE_ENABLED, ProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectModelImpl.ENTITY_CACHE_ENABLED,
			ProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONUUID =
		new FinderPath(ProjectModelImpl.ENTITY_CACHE_ENABLED,
			ProjectModelImpl.FINDER_CACHE_ENABLED, ProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBysimulationUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID =
		new FinderPath(ProjectModelImpl.ENTITY_CACHE_ENABLED,
			ProjectModelImpl.FINDER_CACHE_ENABLED, ProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBysimulationUuid",
			new String[] { String.class.getName() },
			ProjectModelImpl.SIMULATIONUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONUUID = new FinderPath(ProjectModelImpl.ENTITY_CACHE_ENABLED,
			ProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysimulationUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the projects where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @return the matching projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Project> findBysimulationUuid(String simulationUuid)
		throws SystemException {
		return findBysimulationUuid(simulationUuid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projects where simulationUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationUuid the simulation uuid
	 * @param start the lower bound of the range of projects
	 * @param end the upper bound of the range of projects (not inclusive)
	 * @return the range of matching projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Project> findBysimulationUuid(String simulationUuid, int start,
		int end) throws SystemException {
		return findBysimulationUuid(simulationUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the projects where simulationUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationUuid the simulation uuid
	 * @param start the lower bound of the range of projects
	 * @param end the upper bound of the range of projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Project> findBysimulationUuid(String simulationUuid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<Project> list = (List<Project>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Project project : list) {
				if (!Validator.equals(simulationUuid,
							project.getSimulationUuid())) {
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

			query.append(_SQL_SELECT_PROJECT_WHERE);

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
				query.append(ProjectModelImpl.ORDER_BY_JPQL);
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
					list = (List<Project>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Project>(list);
				}
				else {
					list = (List<Project>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first project in the ordered set where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching project
	 * @throws org.kisti.edison.osp.NoSuchProjectException if a matching project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project findBysimulationUuid_First(String simulationUuid,
		OrderByComparator orderByComparator)
		throws NoSuchProjectException, SystemException {
		Project project = fetchBysimulationUuid_First(simulationUuid,
				orderByComparator);

		if (project != null) {
			return project;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationUuid=");
		msg.append(simulationUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProjectException(msg.toString());
	}

	/**
	 * Returns the first project in the ordered set where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching project, or <code>null</code> if a matching project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project fetchBysimulationUuid_First(String simulationUuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Project> list = findBysimulationUuid(simulationUuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last project in the ordered set where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching project
	 * @throws org.kisti.edison.osp.NoSuchProjectException if a matching project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project findBysimulationUuid_Last(String simulationUuid,
		OrderByComparator orderByComparator)
		throws NoSuchProjectException, SystemException {
		Project project = fetchBysimulationUuid_Last(simulationUuid,
				orderByComparator);

		if (project != null) {
			return project;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationUuid=");
		msg.append(simulationUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProjectException(msg.toString());
	}

	/**
	 * Returns the last project in the ordered set where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching project, or <code>null</code> if a matching project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project fetchBysimulationUuid_Last(String simulationUuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBysimulationUuid(simulationUuid);

		if (count == 0) {
			return null;
		}

		List<Project> list = findBysimulationUuid(simulationUuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the projects before and after the current project in the ordered set where simulationUuid = &#63;.
	 *
	 * @param projectPK the primary key of the current project
	 * @param simulationUuid the simulation uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next project
	 * @throws org.kisti.edison.osp.NoSuchProjectException if a project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project[] findBysimulationUuid_PrevAndNext(ProjectPK projectPK,
		String simulationUuid, OrderByComparator orderByComparator)
		throws NoSuchProjectException, SystemException {
		Project project = findByPrimaryKey(projectPK);

		Session session = null;

		try {
			session = openSession();

			Project[] array = new ProjectImpl[3];

			array[0] = getBysimulationUuid_PrevAndNext(session, project,
					simulationUuid, orderByComparator, true);

			array[1] = project;

			array[2] = getBysimulationUuid_PrevAndNext(session, project,
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

	protected Project getBysimulationUuid_PrevAndNext(Session session,
		Project project, String simulationUuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROJECT_WHERE);

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
			query.append(ProjectModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(project);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Project> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the projects where simulationUuid = &#63; from the database.
	 *
	 * @param simulationUuid the simulation uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBysimulationUuid(String simulationUuid)
		throws SystemException {
		for (Project project : findBysimulationUuid(simulationUuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(project);
		}
	}

	/**
	 * Returns the number of projects where simulationUuid = &#63;.
	 *
	 * @param simulationUuid the simulation uuid
	 * @return the number of matching projects
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

			query.append(_SQL_COUNT_PROJECT_WHERE);

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

	private static final String _FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_1 = "project.id.simulationUuid IS NULL";
	private static final String _FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_2 = "project.id.simulationUuid = ?";
	private static final String _FINDER_COLUMN_SIMULATIONUUID_SIMULATIONUUID_3 = "(project.id.simulationUuid IS NULL OR project.id.simulationUuid = '')";

	public ProjectPersistenceImpl() {
		setModelClass(Project.class);
	}

	/**
	 * Caches the project in the entity cache if it is enabled.
	 *
	 * @param project the project
	 */
	@Override
	public void cacheResult(Project project) {
		EntityCacheUtil.putResult(ProjectModelImpl.ENTITY_CACHE_ENABLED,
			ProjectImpl.class, project.getPrimaryKey(), project);

		project.resetOriginalValues();
	}

	/**
	 * Caches the projects in the entity cache if it is enabled.
	 *
	 * @param projects the projects
	 */
	@Override
	public void cacheResult(List<Project> projects) {
		for (Project project : projects) {
			if (EntityCacheUtil.getResult(
						ProjectModelImpl.ENTITY_CACHE_ENABLED,
						ProjectImpl.class, project.getPrimaryKey()) == null) {
				cacheResult(project);
			}
			else {
				project.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all projects.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ProjectImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ProjectImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the project.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Project project) {
		EntityCacheUtil.removeResult(ProjectModelImpl.ENTITY_CACHE_ENABLED,
			ProjectImpl.class, project.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Project> projects) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Project project : projects) {
			EntityCacheUtil.removeResult(ProjectModelImpl.ENTITY_CACHE_ENABLED,
				ProjectImpl.class, project.getPrimaryKey());
		}
	}

	/**
	 * Creates a new project with the primary key. Does not add the project to the database.
	 *
	 * @param projectPK the primary key for the new project
	 * @return the new project
	 */
	@Override
	public Project create(ProjectPK projectPK) {
		Project project = new ProjectImpl();

		project.setNew(true);
		project.setPrimaryKey(projectPK);

		return project;
	}

	/**
	 * Removes the project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectPK the primary key of the project
	 * @return the project that was removed
	 * @throws org.kisti.edison.osp.NoSuchProjectException if a project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project remove(ProjectPK projectPK)
		throws NoSuchProjectException, SystemException {
		return remove((Serializable)projectPK);
	}

	/**
	 * Removes the project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the project
	 * @return the project that was removed
	 * @throws org.kisti.edison.osp.NoSuchProjectException if a project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project remove(Serializable primaryKey)
		throws NoSuchProjectException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Project project = (Project)session.get(ProjectImpl.class, primaryKey);

			if (project == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(project);
		}
		catch (NoSuchProjectException nsee) {
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
	protected Project removeImpl(Project project) throws SystemException {
		project = toUnwrappedModel(project);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(project)) {
				project = (Project)session.get(ProjectImpl.class,
						project.getPrimaryKeyObj());
			}

			if (project != null) {
				session.delete(project);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (project != null) {
			clearCache(project);
		}

		return project;
	}

	@Override
	public Project updateImpl(org.kisti.edison.osp.model.Project project)
		throws SystemException {
		project = toUnwrappedModel(project);

		boolean isNew = project.isNew();

		ProjectModelImpl projectModelImpl = (ProjectModelImpl)project;

		Session session = null;

		try {
			session = openSession();

			if (project.isNew()) {
				session.save(project);

				project.setNew(false);
			}
			else {
				session.merge(project);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ProjectModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((projectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						projectModelImpl.getOriginalSimulationUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONUUID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID,
					args);

				args = new Object[] { projectModelImpl.getSimulationUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONUUID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONUUID,
					args);
			}
		}

		EntityCacheUtil.putResult(ProjectModelImpl.ENTITY_CACHE_ENABLED,
			ProjectImpl.class, project.getPrimaryKey(), project);

		return project;
	}

	protected Project toUnwrappedModel(Project project) {
		if (project instanceof ProjectImpl) {
			return project;
		}

		ProjectImpl projectImpl = new ProjectImpl();

		projectImpl.setNew(project.isNew());
		projectImpl.setPrimaryKey(project.getPrimaryKey());

		projectImpl.setSimulationUuid(project.getSimulationUuid());
		projectImpl.setPortletNamespace(project.getPortletNamespace());
		projectImpl.setJobSeqNo(project.getJobSeqNo());
		projectImpl.setProjectId(project.getProjectId());
		projectImpl.setProjectStructure(project.getProjectStructure());
		projectImpl.setAnalyzerStructure(project.getAnalyzerStructure());

		return projectImpl;
	}

	/**
	 * Returns the project with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the project
	 * @return the project
	 * @throws org.kisti.edison.osp.NoSuchProjectException if a project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectException, SystemException {
		Project project = fetchByPrimaryKey(primaryKey);

		if (project == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return project;
	}

	/**
	 * Returns the project with the primary key or throws a {@link org.kisti.edison.osp.NoSuchProjectException} if it could not be found.
	 *
	 * @param projectPK the primary key of the project
	 * @return the project
	 * @throws org.kisti.edison.osp.NoSuchProjectException if a project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project findByPrimaryKey(ProjectPK projectPK)
		throws NoSuchProjectException, SystemException {
		return findByPrimaryKey((Serializable)projectPK);
	}

	/**
	 * Returns the project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the project
	 * @return the project, or <code>null</code> if a project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Project project = (Project)EntityCacheUtil.getResult(ProjectModelImpl.ENTITY_CACHE_ENABLED,
				ProjectImpl.class, primaryKey);

		if (project == _nullProject) {
			return null;
		}

		if (project == null) {
			Session session = null;

			try {
				session = openSession();

				project = (Project)session.get(ProjectImpl.class, primaryKey);

				if (project != null) {
					cacheResult(project);
				}
				else {
					EntityCacheUtil.putResult(ProjectModelImpl.ENTITY_CACHE_ENABLED,
						ProjectImpl.class, primaryKey, _nullProject);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ProjectModelImpl.ENTITY_CACHE_ENABLED,
					ProjectImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return project;
	}

	/**
	 * Returns the project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectPK the primary key of the project
	 * @return the project, or <code>null</code> if a project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Project fetchByPrimaryKey(ProjectPK projectPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)projectPK);
	}

	/**
	 * Returns all the projects.
	 *
	 * @return the projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Project> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projects
	 * @param end the upper bound of the range of projects (not inclusive)
	 * @return the range of projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Project> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projects
	 * @param end the upper bound of the range of projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of projects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Project> findAll(int start, int end,
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

		List<Project> list = (List<Project>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PROJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECT;

				if (pagination) {
					sql = sql.concat(ProjectModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Project>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Project>(list);
				}
				else {
					list = (List<Project>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the projects from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Project project : findAll()) {
			remove(project);
		}
	}

	/**
	 * Returns the number of projects.
	 *
	 * @return the number of projects
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

				Query q = session.createQuery(_SQL_COUNT_PROJECT);

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
	 * Initializes the project persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.osp.model.Project")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Project>> listenersList = new ArrayList<ModelListener<Project>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Project>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ProjectImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_PROJECT = "SELECT project FROM Project project";
	private static final String _SQL_SELECT_PROJECT_WHERE = "SELECT project FROM Project project WHERE ";
	private static final String _SQL_COUNT_PROJECT = "SELECT COUNT(project) FROM Project project";
	private static final String _SQL_COUNT_PROJECT_WHERE = "SELECT COUNT(project) FROM Project project WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "project.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Project exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Project exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ProjectPersistenceImpl.class);
	private static Project _nullProject = new ProjectImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Project> toCacheModel() {
				return _nullProjectCacheModel;
			}
		};

	private static CacheModel<Project> _nullProjectCacheModel = new CacheModel<Project>() {
			@Override
			public Project toEntityModel() {
				return _nullProject;
			}
		};
}