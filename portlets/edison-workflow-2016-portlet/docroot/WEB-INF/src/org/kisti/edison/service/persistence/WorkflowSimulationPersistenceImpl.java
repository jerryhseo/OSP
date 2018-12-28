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

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.util.CharPool;
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
import com.liferay.portal.service.persistence.impl.TableMapper;
import com.liferay.portal.service.persistence.impl.TableMapperFactory;

import org.kisti.edison.NoSuchWorkflowSimulationException;
import org.kisti.edison.model.WorkflowSimulation;
import org.kisti.edison.model.impl.WorkflowSimulationImpl;
import org.kisti.edison.model.impl.WorkflowSimulationModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the workflow simulation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see WorkflowSimulationPersistence
 * @see WorkflowSimulationUtil
 * @generated
 */
public class WorkflowSimulationPersistenceImpl extends BasePersistenceImpl<WorkflowSimulation>
	implements WorkflowSimulationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkflowSimulationUtil} to access the workflow simulation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowSimulationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE_USERID =
		new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTitle_UserId",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE_USERID =
		new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTitle_UserId",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the workflow simulations where title LIKE &#63; and userId = &#63;.
	 *
	 * @param title the title
	 * @param userId the user ID
	 * @return the matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByTitle_UserId(String title, long userId)
		throws SystemException {
		return findByTitle_UserId(title, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulations where title LIKE &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @return the range of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByTitle_UserId(String title,
		long userId, int start, int end) throws SystemException {
		return findByTitle_UserId(title, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulations where title LIKE &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByTitle_UserId(String title,
		long userId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE_USERID;
		finderArgs = new Object[] { title, userId, start, end, orderByComparator };

		List<WorkflowSimulation> list = (List<WorkflowSimulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkflowSimulation workflowSimulation : list) {
				if (!StringUtil.wildcardMatches(workflowSimulation.getTitle(),
							title, CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true) ||
						(userId != workflowSimulation.getUserId())) {
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

			query.append(_SQL_SELECT_WORKFLOWSIMULATION_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_USERID_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_USERID_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_USERID_TITLE_2);
			}

			query.append(_FINDER_COLUMN_TITLE_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
				}

				qPos.add(userId);

				if (!pagination) {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulation>(list);
				}
				else {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
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
	 * Returns the first workflow simulation in the ordered set where title LIKE &#63; and userId = &#63;.
	 *
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByTitle_UserId_First(String title,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByTitle_UserId_First(title,
				userId, orderByComparator);

		if (workflowSimulation != null) {
			return workflowSimulation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationException(msg.toString());
	}

	/**
	 * Returns the first workflow simulation in the ordered set where title LIKE &#63; and userId = &#63;.
	 *
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByTitle_UserId_First(String title,
		long userId, OrderByComparator orderByComparator)
		throws SystemException {
		List<WorkflowSimulation> list = findByTitle_UserId(title, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow simulation in the ordered set where title LIKE &#63; and userId = &#63;.
	 *
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByTitle_UserId_Last(String title,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByTitle_UserId_Last(title,
				userId, orderByComparator);

		if (workflowSimulation != null) {
			return workflowSimulation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationException(msg.toString());
	}

	/**
	 * Returns the last workflow simulation in the ordered set where title LIKE &#63; and userId = &#63;.
	 *
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByTitle_UserId_Last(String title,
		long userId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTitle_UserId(title, userId);

		if (count == 0) {
			return null;
		}

		List<WorkflowSimulation> list = findByTitle_UserId(title, userId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow simulations before and after the current workflow simulation in the ordered set where title LIKE &#63; and userId = &#63;.
	 *
	 * @param simulationId the primary key of the current workflow simulation
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation[] findByTitle_UserId_PrevAndNext(
		long simulationId, String title, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = findByPrimaryKey(simulationId);

		Session session = null;

		try {
			session = openSession();

			WorkflowSimulation[] array = new WorkflowSimulationImpl[3];

			array[0] = getByTitle_UserId_PrevAndNext(session,
					workflowSimulation, title, userId, orderByComparator, true);

			array[1] = workflowSimulation;

			array[2] = getByTitle_UserId_PrevAndNext(session,
					workflowSimulation, title, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkflowSimulation getByTitle_UserId_PrevAndNext(
		Session session, WorkflowSimulation workflowSimulation, String title,
		long userId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWSIMULATION_WHERE);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLE_USERID_TITLE_1);
		}
		else if (title.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TITLE_USERID_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_TITLE_USERID_TITLE_2);
		}

		query.append(_FINDER_COLUMN_TITLE_USERID_USERID_2);

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
			query.append(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTitle) {
			qPos.add(title);
		}

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowSimulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowSimulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow simulations where title LIKE &#63; and userId = &#63; from the database.
	 *
	 * @param title the title
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTitle_UserId(String title, long userId)
		throws SystemException {
		for (WorkflowSimulation workflowSimulation : findByTitle_UserId(title,
				userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workflowSimulation);
		}
	}

	/**
	 * Returns the number of workflow simulations where title LIKE &#63; and userId = &#63;.
	 *
	 * @param title the title
	 * @param userId the user ID
	 * @return the number of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByTitle_UserId(String title, long userId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE_USERID;

		Object[] finderArgs = new Object[] { title, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKFLOWSIMULATION_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_USERID_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_USERID_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_USERID_TITLE_2);
			}

			query.append(_FINDER_COLUMN_TITLE_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
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

	private static final String _FINDER_COLUMN_TITLE_USERID_TITLE_1 = "workflowSimulation.title LIKE NULL AND ";
	private static final String _FINDER_COLUMN_TITLE_USERID_TITLE_2 = "workflowSimulation.title LIKE ? AND ";
	private static final String _FINDER_COLUMN_TITLE_USERID_TITLE_3 = "(workflowSimulation.title IS NULL OR workflowSimulation.title LIKE '') AND ";
	private static final String _FINDER_COLUMN_TITLE_USERID_USERID_2 = "workflowSimulation.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE = new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTitle",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE = new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTitle",
			new String[] { String.class.getName() });

	/**
	 * Returns all the workflow simulations where title LIKE &#63;.
	 *
	 * @param title the title
	 * @return the matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByTitle(String title)
		throws SystemException {
		return findByTitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulations where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @return the range of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByTitle(String title, int start, int end)
		throws SystemException {
		return findByTitle(title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulations where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByTitle(String title, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE;
		finderArgs = new Object[] { title, start, end, orderByComparator };

		List<WorkflowSimulation> list = (List<WorkflowSimulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkflowSimulation workflowSimulation : list) {
				if (!StringUtil.wildcardMatches(workflowSimulation.getTitle(),
							title, CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true)) {
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

			query.append(_SQL_SELECT_WORKFLOWSIMULATION_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
				}

				if (!pagination) {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulation>(list);
				}
				else {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
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
	 * Returns the first workflow simulation in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByTitle_First(String title,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByTitle_First(title,
				orderByComparator);

		if (workflowSimulation != null) {
			return workflowSimulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationException(msg.toString());
	}

	/**
	 * Returns the first workflow simulation in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByTitle_First(String title,
		OrderByComparator orderByComparator) throws SystemException {
		List<WorkflowSimulation> list = findByTitle(title, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow simulation in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByTitle_Last(String title,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByTitle_Last(title,
				orderByComparator);

		if (workflowSimulation != null) {
			return workflowSimulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationException(msg.toString());
	}

	/**
	 * Returns the last workflow simulation in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByTitle_Last(String title,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTitle(title);

		if (count == 0) {
			return null;
		}

		List<WorkflowSimulation> list = findByTitle(title, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow simulations before and after the current workflow simulation in the ordered set where title LIKE &#63;.
	 *
	 * @param simulationId the primary key of the current workflow simulation
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation[] findByTitle_PrevAndNext(long simulationId,
		String title, OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = findByPrimaryKey(simulationId);

		Session session = null;

		try {
			session = openSession();

			WorkflowSimulation[] array = new WorkflowSimulationImpl[3];

			array[0] = getByTitle_PrevAndNext(session, workflowSimulation,
					title, orderByComparator, true);

			array[1] = workflowSimulation;

			array[2] = getByTitle_PrevAndNext(session, workflowSimulation,
					title, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkflowSimulation getByTitle_PrevAndNext(Session session,
		WorkflowSimulation workflowSimulation, String title,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWSIMULATION_WHERE);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLE_TITLE_1);
		}
		else if (title.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TITLE_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_TITLE_TITLE_2);
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
			query.append(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTitle) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowSimulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowSimulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow simulations where title LIKE &#63; from the database.
	 *
	 * @param title the title
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTitle(String title) throws SystemException {
		for (WorkflowSimulation workflowSimulation : findByTitle(title,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workflowSimulation);
		}
	}

	/**
	 * Returns the number of workflow simulations where title LIKE &#63;.
	 *
	 * @param title the title
	 * @return the number of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByTitle(String title) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE;

		Object[] finderArgs = new Object[] { title };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WORKFLOWSIMULATION_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
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

	private static final String _FINDER_COLUMN_TITLE_TITLE_1 = "workflowSimulation.title LIKE NULL";
	private static final String _FINDER_COLUMN_TITLE_TITLE_2 = "workflowSimulation.title LIKE ?";
	private static final String _FINDER_COLUMN_TITLE_TITLE_3 = "(workflowSimulation.title IS NULL OR workflowSimulation.title LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			WorkflowSimulationModelImpl.USERID_COLUMN_BITMASK |
			WorkflowSimulationModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the workflow simulations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @return the range of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByUserId(long userId, int start,
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

		List<WorkflowSimulation> list = (List<WorkflowSimulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkflowSimulation workflowSimulation : list) {
				if ((userId != workflowSimulation.getUserId())) {
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

			query.append(_SQL_SELECT_WORKFLOWSIMULATION_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulation>(list);
				}
				else {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
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
	 * Returns the first workflow simulation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByUserId_First(userId,
				orderByComparator);

		if (workflowSimulation != null) {
			return workflowSimulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationException(msg.toString());
	}

	/**
	 * Returns the first workflow simulation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WorkflowSimulation> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow simulation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByUserId_Last(userId,
				orderByComparator);

		if (workflowSimulation != null) {
			return workflowSimulation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationException(msg.toString());
	}

	/**
	 * Returns the last workflow simulation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<WorkflowSimulation> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow simulations before and after the current workflow simulation in the ordered set where userId = &#63;.
	 *
	 * @param simulationId the primary key of the current workflow simulation
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation[] findByUserId_PrevAndNext(long simulationId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = findByPrimaryKey(simulationId);

		Session session = null;

		try {
			session = openSession();

			WorkflowSimulation[] array = new WorkflowSimulationImpl[3];

			array[0] = getByUserId_PrevAndNext(session, workflowSimulation,
					userId, orderByComparator, true);

			array[1] = workflowSimulation;

			array[2] = getByUserId_PrevAndNext(session, workflowSimulation,
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

	protected WorkflowSimulation getByUserId_PrevAndNext(Session session,
		WorkflowSimulation workflowSimulation, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWSIMULATION_WHERE);

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
			query.append(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowSimulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowSimulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow simulations where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId(long userId) throws SystemException {
		for (WorkflowSimulation workflowSimulation : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workflowSimulation);
		}
	}

	/**
	 * Returns the number of workflow simulations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching workflow simulations
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

			query.append(_SQL_COUNT_WORKFLOWSIMULATION_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "workflowSimulation.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_WORKFLOWID_TITLE_USERID =
		new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByWorkflowId_Title_UserId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_WORKFLOWID_TITLE_USERID =
		new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByWorkflowId_Title_UserId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the workflow simulations where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param title the title
	 * @param userId the user ID
	 * @return the matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByWorkflowId_Title_UserId(
		long workflowId, String title, long userId) throws SystemException {
		return findByWorkflowId_Title_UserId(workflowId, title, userId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulations where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param workflowId the workflow ID
	 * @param title the title
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @return the range of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByWorkflowId_Title_UserId(
		long workflowId, String title, long userId, int start, int end)
		throws SystemException {
		return findByWorkflowId_Title_UserId(workflowId, title, userId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulations where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param workflowId the workflow ID
	 * @param title the title
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByWorkflowId_Title_UserId(
		long workflowId, String title, long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_WORKFLOWID_TITLE_USERID;
		finderArgs = new Object[] {
				workflowId, title, userId,
				
				start, end, orderByComparator
			};

		List<WorkflowSimulation> list = (List<WorkflowSimulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkflowSimulation workflowSimulation : list) {
				if ((workflowId != workflowSimulation.getWorkflowId()) ||
						!StringUtil.wildcardMatches(
							workflowSimulation.getTitle(), title,
							CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true) ||
						(userId != workflowSimulation.getUserId())) {
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

			query.append(_SQL_SELECT_WORKFLOWSIMULATION_WHERE);

			query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_WORKFLOWID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_2);
			}

			query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowId);

				if (bindTitle) {
					qPos.add(title);
				}

				qPos.add(userId);

				if (!pagination) {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulation>(list);
				}
				else {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
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
	 * Returns the first workflow simulation in the ordered set where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByWorkflowId_Title_UserId_First(
		long workflowId, String title, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByWorkflowId_Title_UserId_First(workflowId,
				title, userId, orderByComparator);

		if (workflowSimulation != null) {
			return workflowSimulation;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("workflowId=");
		msg.append(workflowId);

		msg.append(", title=");
		msg.append(title);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationException(msg.toString());
	}

	/**
	 * Returns the first workflow simulation in the ordered set where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByWorkflowId_Title_UserId_First(
		long workflowId, String title, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WorkflowSimulation> list = findByWorkflowId_Title_UserId(workflowId,
				title, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow simulation in the ordered set where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByWorkflowId_Title_UserId_Last(
		long workflowId, String title, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByWorkflowId_Title_UserId_Last(workflowId,
				title, userId, orderByComparator);

		if (workflowSimulation != null) {
			return workflowSimulation;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("workflowId=");
		msg.append(workflowId);

		msg.append(", title=");
		msg.append(title);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationException(msg.toString());
	}

	/**
	 * Returns the last workflow simulation in the ordered set where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByWorkflowId_Title_UserId_Last(
		long workflowId, String title, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByWorkflowId_Title_UserId(workflowId, title, userId);

		if (count == 0) {
			return null;
		}

		List<WorkflowSimulation> list = findByWorkflowId_Title_UserId(workflowId,
				title, userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow simulations before and after the current workflow simulation in the ordered set where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param simulationId the primary key of the current workflow simulation
	 * @param workflowId the workflow ID
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation[] findByWorkflowId_Title_UserId_PrevAndNext(
		long simulationId, long workflowId, String title, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = findByPrimaryKey(simulationId);

		Session session = null;

		try {
			session = openSession();

			WorkflowSimulation[] array = new WorkflowSimulationImpl[3];

			array[0] = getByWorkflowId_Title_UserId_PrevAndNext(session,
					workflowSimulation, workflowId, title, userId,
					orderByComparator, true);

			array[1] = workflowSimulation;

			array[2] = getByWorkflowId_Title_UserId_PrevAndNext(session,
					workflowSimulation, workflowId, title, userId,
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

	protected WorkflowSimulation getByWorkflowId_Title_UserId_PrevAndNext(
		Session session, WorkflowSimulation workflowSimulation,
		long workflowId, String title, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWSIMULATION_WHERE);

		query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_WORKFLOWID_2);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_1);
		}
		else if (title.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_2);
		}

		query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_USERID_2);

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
			query.append(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(workflowId);

		if (bindTitle) {
			qPos.add(title);
		}

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowSimulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowSimulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow simulations where workflowId = &#63; and title LIKE &#63; and userId = &#63; from the database.
	 *
	 * @param workflowId the workflow ID
	 * @param title the title
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByWorkflowId_Title_UserId(long workflowId, String title,
		long userId) throws SystemException {
		for (WorkflowSimulation workflowSimulation : findByWorkflowId_Title_UserId(
				workflowId, title, userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(workflowSimulation);
		}
	}

	/**
	 * Returns the number of workflow simulations where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param title the title
	 * @param userId the user ID
	 * @return the number of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByWorkflowId_Title_UserId(long workflowId, String title,
		long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_WORKFLOWID_TITLE_USERID;

		Object[] finderArgs = new Object[] { workflowId, title, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_WORKFLOWSIMULATION_WHERE);

			query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_WORKFLOWID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_2);
			}

			query.append(_FINDER_COLUMN_WORKFLOWID_TITLE_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowId);

				if (bindTitle) {
					qPos.add(title);
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

	private static final String _FINDER_COLUMN_WORKFLOWID_TITLE_USERID_WORKFLOWID_2 =
		"workflowSimulation.workflowId = ? AND ";
	private static final String _FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_1 = "workflowSimulation.title LIKE NULL AND ";
	private static final String _FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_2 = "workflowSimulation.title LIKE ? AND ";
	private static final String _FINDER_COLUMN_WORKFLOWID_TITLE_USERID_TITLE_3 = "(workflowSimulation.title IS NULL OR workflowSimulation.title LIKE '') AND ";
	private static final String _FINDER_COLUMN_WORKFLOWID_TITLE_USERID_USERID_2 = "workflowSimulation.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_WORKFLOWID_USERID =
		new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByWorkflowId_UserId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_WORKFLOWID_USERID =
		new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByWorkflowId_UserId",
			new String[] { Long.class.getName(), Long.class.getName() },
			WorkflowSimulationModelImpl.WORKFLOWID_COLUMN_BITMASK |
			WorkflowSimulationModelImpl.USERID_COLUMN_BITMASK |
			WorkflowSimulationModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_WORKFLOWID_USERID = new FinderPath(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByWorkflowId_UserId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the workflow simulations where workflowId = &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param userId the user ID
	 * @return the matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByWorkflowId_UserId(long workflowId,
		long userId) throws SystemException {
		return findByWorkflowId_UserId(workflowId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulations where workflowId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param workflowId the workflow ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @return the range of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByWorkflowId_UserId(long workflowId,
		long userId, int start, int end) throws SystemException {
		return findByWorkflowId_UserId(workflowId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulations where workflowId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param workflowId the workflow ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findByWorkflowId_UserId(long workflowId,
		long userId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_WORKFLOWID_USERID;
			finderArgs = new Object[] { workflowId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_WORKFLOWID_USERID;
			finderArgs = new Object[] {
					workflowId, userId,
					
					start, end, orderByComparator
				};
		}

		List<WorkflowSimulation> list = (List<WorkflowSimulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkflowSimulation workflowSimulation : list) {
				if ((workflowId != workflowSimulation.getWorkflowId()) ||
						(userId != workflowSimulation.getUserId())) {
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

			query.append(_SQL_SELECT_WORKFLOWSIMULATION_WHERE);

			query.append(_FINDER_COLUMN_WORKFLOWID_USERID_WORKFLOWID_2);

			query.append(_FINDER_COLUMN_WORKFLOWID_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulation>(list);
				}
				else {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
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
	 * Returns the first workflow simulation in the ordered set where workflowId = &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByWorkflowId_UserId_First(long workflowId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByWorkflowId_UserId_First(workflowId,
				userId, orderByComparator);

		if (workflowSimulation != null) {
			return workflowSimulation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("workflowId=");
		msg.append(workflowId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationException(msg.toString());
	}

	/**
	 * Returns the first workflow simulation in the ordered set where workflowId = &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByWorkflowId_UserId_First(long workflowId,
		long userId, OrderByComparator orderByComparator)
		throws SystemException {
		List<WorkflowSimulation> list = findByWorkflowId_UserId(workflowId,
				userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow simulation in the ordered set where workflowId = &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByWorkflowId_UserId_Last(long workflowId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByWorkflowId_UserId_Last(workflowId,
				userId, orderByComparator);

		if (workflowSimulation != null) {
			return workflowSimulation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("workflowId=");
		msg.append(workflowId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationException(msg.toString());
	}

	/**
	 * Returns the last workflow simulation in the ordered set where workflowId = &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByWorkflowId_UserId_Last(long workflowId,
		long userId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByWorkflowId_UserId(workflowId, userId);

		if (count == 0) {
			return null;
		}

		List<WorkflowSimulation> list = findByWorkflowId_UserId(workflowId,
				userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow simulations before and after the current workflow simulation in the ordered set where workflowId = &#63; and userId = &#63;.
	 *
	 * @param simulationId the primary key of the current workflow simulation
	 * @param workflowId the workflow ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation[] findByWorkflowId_UserId_PrevAndNext(
		long simulationId, long workflowId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = findByPrimaryKey(simulationId);

		Session session = null;

		try {
			session = openSession();

			WorkflowSimulation[] array = new WorkflowSimulationImpl[3];

			array[0] = getByWorkflowId_UserId_PrevAndNext(session,
					workflowSimulation, workflowId, userId, orderByComparator,
					true);

			array[1] = workflowSimulation;

			array[2] = getByWorkflowId_UserId_PrevAndNext(session,
					workflowSimulation, workflowId, userId, orderByComparator,
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

	protected WorkflowSimulation getByWorkflowId_UserId_PrevAndNext(
		Session session, WorkflowSimulation workflowSimulation,
		long workflowId, long userId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWSIMULATION_WHERE);

		query.append(_FINDER_COLUMN_WORKFLOWID_USERID_WORKFLOWID_2);

		query.append(_FINDER_COLUMN_WORKFLOWID_USERID_USERID_2);

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
			query.append(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(workflowId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowSimulation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowSimulation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow simulations where workflowId = &#63; and userId = &#63; from the database.
	 *
	 * @param workflowId the workflow ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByWorkflowId_UserId(long workflowId, long userId)
		throws SystemException {
		for (WorkflowSimulation workflowSimulation : findByWorkflowId_UserId(
				workflowId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workflowSimulation);
		}
	}

	/**
	 * Returns the number of workflow simulations where workflowId = &#63; and userId = &#63;.
	 *
	 * @param workflowId the workflow ID
	 * @param userId the user ID
	 * @return the number of matching workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByWorkflowId_UserId(long workflowId, long userId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_WORKFLOWID_USERID;

		Object[] finderArgs = new Object[] { workflowId, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKFLOWSIMULATION_WHERE);

			query.append(_FINDER_COLUMN_WORKFLOWID_USERID_WORKFLOWID_2);

			query.append(_FINDER_COLUMN_WORKFLOWID_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowId);

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

	private static final String _FINDER_COLUMN_WORKFLOWID_USERID_WORKFLOWID_2 = "workflowSimulation.workflowId = ? AND ";
	private static final String _FINDER_COLUMN_WORKFLOWID_USERID_USERID_2 = "workflowSimulation.userId = ?";

	public WorkflowSimulationPersistenceImpl() {
		setModelClass(WorkflowSimulation.class);
	}

	/**
	 * Caches the workflow simulation in the entity cache if it is enabled.
	 *
	 * @param workflowSimulation the workflow simulation
	 */
	@Override
	public void cacheResult(WorkflowSimulation workflowSimulation) {
		EntityCacheUtil.putResult(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationImpl.class, workflowSimulation.getPrimaryKey(),
			workflowSimulation);

		workflowSimulation.resetOriginalValues();
	}

	/**
	 * Caches the workflow simulations in the entity cache if it is enabled.
	 *
	 * @param workflowSimulations the workflow simulations
	 */
	@Override
	public void cacheResult(List<WorkflowSimulation> workflowSimulations) {
		for (WorkflowSimulation workflowSimulation : workflowSimulations) {
			if (EntityCacheUtil.getResult(
						WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowSimulationImpl.class,
						workflowSimulation.getPrimaryKey()) == null) {
				cacheResult(workflowSimulation);
			}
			else {
				workflowSimulation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all workflow simulations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WorkflowSimulationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WorkflowSimulationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the workflow simulation.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkflowSimulation workflowSimulation) {
		EntityCacheUtil.removeResult(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationImpl.class, workflowSimulation.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WorkflowSimulation> workflowSimulations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkflowSimulation workflowSimulation : workflowSimulations) {
			EntityCacheUtil.removeResult(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowSimulationImpl.class, workflowSimulation.getPrimaryKey());
		}
	}

	/**
	 * Creates a new workflow simulation with the primary key. Does not add the workflow simulation to the database.
	 *
	 * @param simulationId the primary key for the new workflow simulation
	 * @return the new workflow simulation
	 */
	@Override
	public WorkflowSimulation create(long simulationId) {
		WorkflowSimulation workflowSimulation = new WorkflowSimulationImpl();

		workflowSimulation.setNew(true);
		workflowSimulation.setPrimaryKey(simulationId);

		return workflowSimulation;
	}

	/**
	 * Removes the workflow simulation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulationId the primary key of the workflow simulation
	 * @return the workflow simulation that was removed
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation remove(long simulationId)
		throws NoSuchWorkflowSimulationException, SystemException {
		return remove((Serializable)simulationId);
	}

	/**
	 * Removes the workflow simulation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the workflow simulation
	 * @return the workflow simulation that was removed
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation remove(Serializable primaryKey)
		throws NoSuchWorkflowSimulationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorkflowSimulation workflowSimulation = (WorkflowSimulation)session.get(WorkflowSimulationImpl.class,
					primaryKey);

			if (workflowSimulation == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkflowSimulationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workflowSimulation);
		}
		catch (NoSuchWorkflowSimulationException nsee) {
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
	protected WorkflowSimulation removeImpl(
		WorkflowSimulation workflowSimulation) throws SystemException {
		workflowSimulation = toUnwrappedModel(workflowSimulation);

		workflowSimulationToWorkflowSimulationJobTableMapper.deleteLeftPrimaryKeyTableMappings(workflowSimulation.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workflowSimulation)) {
				workflowSimulation = (WorkflowSimulation)session.get(WorkflowSimulationImpl.class,
						workflowSimulation.getPrimaryKeyObj());
			}

			if (workflowSimulation != null) {
				session.delete(workflowSimulation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workflowSimulation != null) {
			clearCache(workflowSimulation);
		}

		return workflowSimulation;
	}

	@Override
	public WorkflowSimulation updateImpl(
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws SystemException {
		workflowSimulation = toUnwrappedModel(workflowSimulation);

		boolean isNew = workflowSimulation.isNew();

		WorkflowSimulationModelImpl workflowSimulationModelImpl = (WorkflowSimulationModelImpl)workflowSimulation;

		Session session = null;

		try {
			session = openSession();

			if (workflowSimulation.isNew()) {
				session.save(workflowSimulation);

				workflowSimulation.setNew(false);
			}
			else {
				session.merge(workflowSimulation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WorkflowSimulationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((workflowSimulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workflowSimulationModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { workflowSimulationModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((workflowSimulationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_WORKFLOWID_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workflowSimulationModelImpl.getOriginalWorkflowId(),
						workflowSimulationModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_WORKFLOWID_USERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_WORKFLOWID_USERID,
					args);

				args = new Object[] {
						workflowSimulationModelImpl.getWorkflowId(),
						workflowSimulationModelImpl.getUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_WORKFLOWID_USERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_WORKFLOWID_USERID,
					args);
			}
		}

		EntityCacheUtil.putResult(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationImpl.class, workflowSimulation.getPrimaryKey(),
			workflowSimulation);

		return workflowSimulation;
	}

	protected WorkflowSimulation toUnwrappedModel(
		WorkflowSimulation workflowSimulation) {
		if (workflowSimulation instanceof WorkflowSimulationImpl) {
			return workflowSimulation;
		}

		WorkflowSimulationImpl workflowSimulationImpl = new WorkflowSimulationImpl();

		workflowSimulationImpl.setNew(workflowSimulation.isNew());
		workflowSimulationImpl.setPrimaryKey(workflowSimulation.getPrimaryKey());

		workflowSimulationImpl.setSimulationId(workflowSimulation.getSimulationId());
		workflowSimulationImpl.setUserId(workflowSimulation.getUserId());
		workflowSimulationImpl.setCreateDate(workflowSimulation.getCreateDate());
		workflowSimulationImpl.setModifiedDate(workflowSimulation.getModifiedDate());
		workflowSimulationImpl.setClassId(workflowSimulation.getClassId());
		workflowSimulationImpl.setCustomId(workflowSimulation.getCustomId());
		workflowSimulationImpl.setTitle(workflowSimulation.getTitle());
		workflowSimulationImpl.setTestYn(workflowSimulation.isTestYn());
		workflowSimulationImpl.setWorkflowId(workflowSimulation.getWorkflowId());

		return workflowSimulationImpl;
	}

	/**
	 * Returns the workflow simulation with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow simulation
	 * @return the workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkflowSimulationException, SystemException {
		WorkflowSimulation workflowSimulation = fetchByPrimaryKey(primaryKey);

		if (workflowSimulation == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkflowSimulationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workflowSimulation;
	}

	/**
	 * Returns the workflow simulation with the primary key or throws a {@link org.kisti.edison.NoSuchWorkflowSimulationException} if it could not be found.
	 *
	 * @param simulationId the primary key of the workflow simulation
	 * @return the workflow simulation
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation findByPrimaryKey(long simulationId)
		throws NoSuchWorkflowSimulationException, SystemException {
		return findByPrimaryKey((Serializable)simulationId);
	}

	/**
	 * Returns the workflow simulation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow simulation
	 * @return the workflow simulation, or <code>null</code> if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		WorkflowSimulation workflowSimulation = (WorkflowSimulation)EntityCacheUtil.getResult(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowSimulationImpl.class, primaryKey);

		if (workflowSimulation == _nullWorkflowSimulation) {
			return null;
		}

		if (workflowSimulation == null) {
			Session session = null;

			try {
				session = openSession();

				workflowSimulation = (WorkflowSimulation)session.get(WorkflowSimulationImpl.class,
						primaryKey);

				if (workflowSimulation != null) {
					cacheResult(workflowSimulation);
				}
				else {
					EntityCacheUtil.putResult(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowSimulationImpl.class, primaryKey,
						_nullWorkflowSimulation);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(WorkflowSimulationModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowSimulationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workflowSimulation;
	}

	/**
	 * Returns the workflow simulation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param simulationId the primary key of the workflow simulation
	 * @return the workflow simulation, or <code>null</code> if a workflow simulation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulation fetchByPrimaryKey(long simulationId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)simulationId);
	}

	/**
	 * Returns all the workflow simulations.
	 *
	 * @return the workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @return the range of workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulation> findAll(int start, int end,
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

		List<WorkflowSimulation> list = (List<WorkflowSimulation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WORKFLOWSIMULATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKFLOWSIMULATION;

				if (pagination) {
					sql = sql.concat(WorkflowSimulationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulation>(list);
				}
				else {
					list = (List<WorkflowSimulation>)QueryUtil.list(q,
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
	 * Removes all the workflow simulations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (WorkflowSimulation workflowSimulation : findAll()) {
			remove(workflowSimulation);
		}
	}

	/**
	 * Returns the number of workflow simulations.
	 *
	 * @return the number of workflow simulations
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

				Query q = session.createQuery(_SQL_COUNT_WORKFLOWSIMULATION);

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
	 * Returns all the workflow simulation jobs associated with the workflow simulation.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @return the workflow simulation jobs associated with the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long pk) throws SystemException {
		return getWorkflowSimulationJobs(pk, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the workflow simulation jobs associated with the workflow simulation.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @return the range of workflow simulation jobs associated with the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long pk, int start, int end) throws SystemException {
		return getWorkflowSimulationJobs(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulation jobs associated with the workflow simulation.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param start the lower bound of the range of workflow simulations
	 * @param end the upper bound of the range of workflow simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow simulation jobs associated with the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return workflowSimulationToWorkflowSimulationJobTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of workflow simulation jobs associated with the workflow simulation.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @return the number of workflow simulation jobs associated with the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getWorkflowSimulationJobsSize(long pk) throws SystemException {
		long[] pks = workflowSimulationToWorkflowSimulationJobTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the workflow simulation job is associated with the workflow simulation.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJobPK the primary key of the workflow simulation job
	 * @return <code>true</code> if the workflow simulation job is associated with the workflow simulation; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsWorkflowSimulationJob(long pk,
		long workflowSimulationJobPK) throws SystemException {
		return workflowSimulationToWorkflowSimulationJobTableMapper.containsTableMapping(pk,
			workflowSimulationJobPK);
	}

	/**
	 * Returns <code>true</code> if the workflow simulation has any workflow simulation jobs associated with it.
	 *
	 * @param pk the primary key of the workflow simulation to check for associations with workflow simulation jobs
	 * @return <code>true</code> if the workflow simulation has any workflow simulation jobs associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsWorkflowSimulationJobs(long pk)
		throws SystemException {
		if (getWorkflowSimulationJobsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJobPK the primary key of the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulationJob(long pk, long workflowSimulationJobPK)
		throws SystemException {
		workflowSimulationToWorkflowSimulationJobTableMapper.addTableMapping(pk,
			workflowSimulationJobPK);
	}

	/**
	 * Adds an association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJob the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulationJob(long pk,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws SystemException {
		workflowSimulationToWorkflowSimulationJobTableMapper.addTableMapping(pk,
			workflowSimulationJob.getPrimaryKey());
	}

	/**
	 * Adds an association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJobPKs the primary keys of the workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulationJobs(long pk,
		long[] workflowSimulationJobPKs) throws SystemException {
		for (long workflowSimulationJobPK : workflowSimulationJobPKs) {
			workflowSimulationToWorkflowSimulationJobTableMapper.addTableMapping(pk,
				workflowSimulationJobPK);
		}
	}

	/**
	 * Adds an association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJobs the workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulationJobs(long pk,
		List<org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationJobs)
		throws SystemException {
		for (org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob : workflowSimulationJobs) {
			workflowSimulationToWorkflowSimulationJobTableMapper.addTableMapping(pk,
				workflowSimulationJob.getPrimaryKey());
		}
	}

	/**
	 * Clears all associations between the workflow simulation and its workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation to clear the associated workflow simulation jobs from
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearWorkflowSimulationJobs(long pk) throws SystemException {
		workflowSimulationToWorkflowSimulationJobTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJobPK the primary key of the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulationJob(long pk,
		long workflowSimulationJobPK) throws SystemException {
		workflowSimulationToWorkflowSimulationJobTableMapper.deleteTableMapping(pk,
			workflowSimulationJobPK);
	}

	/**
	 * Removes the association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJob the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulationJob(long pk,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws SystemException {
		workflowSimulationToWorkflowSimulationJobTableMapper.deleteTableMapping(pk,
			workflowSimulationJob.getPrimaryKey());
	}

	/**
	 * Removes the association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJobPKs the primary keys of the workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulationJobs(long pk,
		long[] workflowSimulationJobPKs) throws SystemException {
		for (long workflowSimulationJobPK : workflowSimulationJobPKs) {
			workflowSimulationToWorkflowSimulationJobTableMapper.deleteTableMapping(pk,
				workflowSimulationJobPK);
		}
	}

	/**
	 * Removes the association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJobs the workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulationJobs(long pk,
		List<org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationJobs)
		throws SystemException {
		for (org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob : workflowSimulationJobs) {
			workflowSimulationToWorkflowSimulationJobTableMapper.deleteTableMapping(pk,
				workflowSimulationJob.getPrimaryKey());
		}
	}

	/**
	 * Sets the workflow simulation jobs associated with the workflow simulation, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJobPKs the primary keys of the workflow simulation jobs to be associated with the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setWorkflowSimulationJobs(long pk,
		long[] workflowSimulationJobPKs) throws SystemException {
		Set<Long> newWorkflowSimulationJobPKsSet = SetUtil.fromArray(workflowSimulationJobPKs);
		Set<Long> oldWorkflowSimulationJobPKsSet = SetUtil.fromArray(workflowSimulationToWorkflowSimulationJobTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeWorkflowSimulationJobPKsSet = new HashSet<Long>(oldWorkflowSimulationJobPKsSet);

		removeWorkflowSimulationJobPKsSet.removeAll(newWorkflowSimulationJobPKsSet);

		for (long removeWorkflowSimulationJobPK : removeWorkflowSimulationJobPKsSet) {
			workflowSimulationToWorkflowSimulationJobTableMapper.deleteTableMapping(pk,
				removeWorkflowSimulationJobPK);
		}

		newWorkflowSimulationJobPKsSet.removeAll(oldWorkflowSimulationJobPKsSet);

		for (long newWorkflowSimulationJobPK : newWorkflowSimulationJobPKsSet) {
			workflowSimulationToWorkflowSimulationJobTableMapper.addTableMapping(pk,
				newWorkflowSimulationJobPK);
		}
	}

	/**
	 * Sets the workflow simulation jobs associated with the workflow simulation, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation
	 * @param workflowSimulationJobs the workflow simulation jobs to be associated with the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setWorkflowSimulationJobs(long pk,
		List<org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationJobs)
		throws SystemException {
		try {
			long[] workflowSimulationJobPKs = new long[workflowSimulationJobs.size()];

			for (int i = 0; i < workflowSimulationJobs.size(); i++) {
				org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob =
					workflowSimulationJobs.get(i);

				workflowSimulationJobPKs[i] = workflowSimulationJob.getPrimaryKey();
			}

			setWorkflowSimulationJobs(pk, workflowSimulationJobPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(WorkflowSimulationModelImpl.MAPPING_TABLE_EDWF_WORKFLOWSIMULATION_WORKFLOWSIMULATIONJOB_NAME);
		}
	}

	/**
	 * Initializes the workflow simulation persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.WorkflowSimulation")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorkflowSimulation>> listenersList = new ArrayList<ModelListener<WorkflowSimulation>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorkflowSimulation>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		workflowSimulationToWorkflowSimulationJobTableMapper = TableMapperFactory.getTableMapper("EDWF_WorkflowSimulation_WorkflowSimulationJob",
				"simulationId", "simulationJobId", this,
				workflowSimulationJobPersistence);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(WorkflowSimulationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"EDWF_WorkflowSimulation_WorkflowSimulationJob");
	}

	@BeanReference(type = WorkflowSimulationJobPersistence.class)
	protected WorkflowSimulationJobPersistence workflowSimulationJobPersistence;
	protected TableMapper<WorkflowSimulation, org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationToWorkflowSimulationJobTableMapper;
	private static final String _SQL_SELECT_WORKFLOWSIMULATION = "SELECT workflowSimulation FROM WorkflowSimulation workflowSimulation";
	private static final String _SQL_SELECT_WORKFLOWSIMULATION_WHERE = "SELECT workflowSimulation FROM WorkflowSimulation workflowSimulation WHERE ";
	private static final String _SQL_COUNT_WORKFLOWSIMULATION = "SELECT COUNT(workflowSimulation) FROM WorkflowSimulation workflowSimulation";
	private static final String _SQL_COUNT_WORKFLOWSIMULATION_WHERE = "SELECT COUNT(workflowSimulation) FROM WorkflowSimulation workflowSimulation WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workflowSimulation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkflowSimulation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WorkflowSimulation exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(WorkflowSimulationPersistenceImpl.class);
	private static WorkflowSimulation _nullWorkflowSimulation = new WorkflowSimulationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WorkflowSimulation> toCacheModel() {
				return _nullWorkflowSimulationCacheModel;
			}
		};

	private static CacheModel<WorkflowSimulation> _nullWorkflowSimulationCacheModel =
		new CacheModel<WorkflowSimulation>() {
			@Override
			public WorkflowSimulation toEntityModel() {
				return _nullWorkflowSimulation;
			}
		};
}