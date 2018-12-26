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

import org.kisti.edison.NoSuchWorkflowSimulationJobException;
import org.kisti.edison.model.WorkflowSimulationJob;
import org.kisti.edison.model.impl.WorkflowSimulationJobImpl;
import org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the workflow simulation job service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see WorkflowSimulationJobPersistence
 * @see WorkflowSimulationJobUtil
 * @generated
 */
public class WorkflowSimulationJobPersistenceImpl extends BasePersistenceImpl<WorkflowSimulationJob>
	implements WorkflowSimulationJobPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkflowSimulationJobUtil} to access the workflow simulation job persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowSimulationJobImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONID_TITLE_USERID =
		new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySimulationId_Title_UserId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_SIMULATIONID_TITLE_USERID =
		new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countBySimulationId_Title_UserId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the workflow simulation jobs where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param title the title
	 * @param userId the user ID
	 * @return the matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findBySimulationId_Title_UserId(
		long simulationId, String title, long userId) throws SystemException {
		return findBySimulationId_Title_UserId(simulationId, title, userId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulation jobs where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationId the simulation ID
	 * @param title the title
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @return the range of matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findBySimulationId_Title_UserId(
		long simulationId, String title, long userId, int start, int end)
		throws SystemException {
		return findBySimulationId_Title_UserId(simulationId, title, userId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulation jobs where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationId the simulation ID
	 * @param title the title
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findBySimulationId_Title_UserId(
		long simulationId, String title, long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONID_TITLE_USERID;
		finderArgs = new Object[] {
				simulationId, title, userId,
				
				start, end, orderByComparator
			};

		List<WorkflowSimulationJob> list = (List<WorkflowSimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkflowSimulationJob workflowSimulationJob : list) {
				if ((simulationId != workflowSimulationJob.getSimulationId()) ||
						!StringUtil.wildcardMatches(
							workflowSimulationJob.getTitle(), title,
							CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true) ||
						(userId != workflowSimulationJob.getUserId())) {
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

			query.append(_SQL_SELECT_WORKFLOWSIMULATIONJOB_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_SIMULATIONID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_2);
			}

			query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowSimulationJobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationId);

				if (bindTitle) {
					qPos.add(title);
				}

				qPos.add(userId);

				if (!pagination) {
					list = (List<WorkflowSimulationJob>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulationJob>(list);
				}
				else {
					list = (List<WorkflowSimulationJob>)QueryUtil.list(q,
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
	 * Returns the first workflow simulation job in the ordered set where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob findBySimulationId_Title_UserId_First(
		long simulationId, String title, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = fetchBySimulationId_Title_UserId_First(simulationId,
				title, userId, orderByComparator);

		if (workflowSimulationJob != null) {
			return workflowSimulationJob;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationId=");
		msg.append(simulationId);

		msg.append(", title=");
		msg.append(title);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationJobException(msg.toString());
	}

	/**
	 * Returns the first workflow simulation job in the ordered set where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob fetchBySimulationId_Title_UserId_First(
		long simulationId, String title, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WorkflowSimulationJob> list = findBySimulationId_Title_UserId(simulationId,
				title, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow simulation job in the ordered set where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob findBySimulationId_Title_UserId_Last(
		long simulationId, String title, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = fetchBySimulationId_Title_UserId_Last(simulationId,
				title, userId, orderByComparator);

		if (workflowSimulationJob != null) {
			return workflowSimulationJob;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationId=");
		msg.append(simulationId);

		msg.append(", title=");
		msg.append(title);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationJobException(msg.toString());
	}

	/**
	 * Returns the last workflow simulation job in the ordered set where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob fetchBySimulationId_Title_UserId_Last(
		long simulationId, String title, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySimulationId_Title_UserId(simulationId, title, userId);

		if (count == 0) {
			return null;
		}

		List<WorkflowSimulationJob> list = findBySimulationId_Title_UserId(simulationId,
				title, userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow simulation jobs before and after the current workflow simulation job in the ordered set where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param simulationJobId the primary key of the current workflow simulation job
	 * @param simulationId the simulation ID
	 * @param title the title
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob[] findBySimulationId_Title_UserId_PrevAndNext(
		long simulationJobId, long simulationId, String title, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = findByPrimaryKey(simulationJobId);

		Session session = null;

		try {
			session = openSession();

			WorkflowSimulationJob[] array = new WorkflowSimulationJobImpl[3];

			array[0] = getBySimulationId_Title_UserId_PrevAndNext(session,
					workflowSimulationJob, simulationId, title, userId,
					orderByComparator, true);

			array[1] = workflowSimulationJob;

			array[2] = getBySimulationId_Title_UserId_PrevAndNext(session,
					workflowSimulationJob, simulationId, title, userId,
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

	protected WorkflowSimulationJob getBySimulationId_Title_UserId_PrevAndNext(
		Session session, WorkflowSimulationJob workflowSimulationJob,
		long simulationId, String title, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWSIMULATIONJOB_WHERE);

		query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_SIMULATIONID_2);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_1);
		}
		else if (title.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_2);
		}

		query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_USERID_2);

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
			query.append(WorkflowSimulationJobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(simulationId);

		if (bindTitle) {
			qPos.add(title);
		}

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowSimulationJob);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowSimulationJob> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow simulation jobs where simulationId = &#63; and title LIKE &#63; and userId = &#63; from the database.
	 *
	 * @param simulationId the simulation ID
	 * @param title the title
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBySimulationId_Title_UserId(long simulationId,
		String title, long userId) throws SystemException {
		for (WorkflowSimulationJob workflowSimulationJob : findBySimulationId_Title_UserId(
				simulationId, title, userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(workflowSimulationJob);
		}
	}

	/**
	 * Returns the number of workflow simulation jobs where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param title the title
	 * @param userId the user ID
	 * @return the number of matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySimulationId_Title_UserId(long simulationId,
		String title, long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_SIMULATIONID_TITLE_USERID;

		Object[] finderArgs = new Object[] { simulationId, title, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_WORKFLOWSIMULATIONJOB_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_SIMULATIONID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_2);
			}

			query.append(_FINDER_COLUMN_SIMULATIONID_TITLE_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationId);

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

	private static final String _FINDER_COLUMN_SIMULATIONID_TITLE_USERID_SIMULATIONID_2 =
		"workflowSimulationJob.simulationId = ? AND ";
	private static final String _FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_1 =
		"workflowSimulationJob.title LIKE NULL AND ";
	private static final String _FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_2 =
		"workflowSimulationJob.title LIKE ? AND ";
	private static final String _FINDER_COLUMN_SIMULATIONID_TITLE_USERID_TITLE_3 =
		"(workflowSimulationJob.title IS NULL OR workflowSimulationJob.title LIKE '') AND ";
	private static final String _FINDER_COLUMN_SIMULATIONID_TITLE_USERID_USERID_2 =
		"workflowSimulationJob.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONID_USERID =
		new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySimulationId_UserId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_USERID =
		new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySimulationId_UserId",
			new String[] { Long.class.getName(), Long.class.getName() },
			WorkflowSimulationJobModelImpl.SIMULATIONID_COLUMN_BITMASK |
			WorkflowSimulationJobModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONID_USERID = new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySimulationId_UserId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the workflow simulation jobs where simulationId = &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param userId the user ID
	 * @return the matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findBySimulationId_UserId(
		long simulationId, long userId) throws SystemException {
		return findBySimulationId_UserId(simulationId, userId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulation jobs where simulationId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationId the simulation ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @return the range of matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findBySimulationId_UserId(
		long simulationId, long userId, int start, int end)
		throws SystemException {
		return findBySimulationId_UserId(simulationId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulation jobs where simulationId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationId the simulation ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findBySimulationId_UserId(
		long simulationId, long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_USERID;
			finderArgs = new Object[] { simulationId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONID_USERID;
			finderArgs = new Object[] {
					simulationId, userId,
					
					start, end, orderByComparator
				};
		}

		List<WorkflowSimulationJob> list = (List<WorkflowSimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkflowSimulationJob workflowSimulationJob : list) {
				if ((simulationId != workflowSimulationJob.getSimulationId()) ||
						(userId != workflowSimulationJob.getUserId())) {
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

			query.append(_SQL_SELECT_WORKFLOWSIMULATIONJOB_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONID_USERID_SIMULATIONID_2);

			query.append(_FINDER_COLUMN_SIMULATIONID_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowSimulationJobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<WorkflowSimulationJob>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulationJob>(list);
				}
				else {
					list = (List<WorkflowSimulationJob>)QueryUtil.list(q,
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
	 * Returns the first workflow simulation job in the ordered set where simulationId = &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob findBySimulationId_UserId_First(
		long simulationId, long userId, OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = fetchBySimulationId_UserId_First(simulationId,
				userId, orderByComparator);

		if (workflowSimulationJob != null) {
			return workflowSimulationJob;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationId=");
		msg.append(simulationId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationJobException(msg.toString());
	}

	/**
	 * Returns the first workflow simulation job in the ordered set where simulationId = &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob fetchBySimulationId_UserId_First(
		long simulationId, long userId, OrderByComparator orderByComparator)
		throws SystemException {
		List<WorkflowSimulationJob> list = findBySimulationId_UserId(simulationId,
				userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow simulation job in the ordered set where simulationId = &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob findBySimulationId_UserId_Last(
		long simulationId, long userId, OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = fetchBySimulationId_UserId_Last(simulationId,
				userId, orderByComparator);

		if (workflowSimulationJob != null) {
			return workflowSimulationJob;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationId=");
		msg.append(simulationId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationJobException(msg.toString());
	}

	/**
	 * Returns the last workflow simulation job in the ordered set where simulationId = &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob fetchBySimulationId_UserId_Last(
		long simulationId, long userId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBySimulationId_UserId(simulationId, userId);

		if (count == 0) {
			return null;
		}

		List<WorkflowSimulationJob> list = findBySimulationId_UserId(simulationId,
				userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow simulation jobs before and after the current workflow simulation job in the ordered set where simulationId = &#63; and userId = &#63;.
	 *
	 * @param simulationJobId the primary key of the current workflow simulation job
	 * @param simulationId the simulation ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob[] findBySimulationId_UserId_PrevAndNext(
		long simulationJobId, long simulationId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = findByPrimaryKey(simulationJobId);

		Session session = null;

		try {
			session = openSession();

			WorkflowSimulationJob[] array = new WorkflowSimulationJobImpl[3];

			array[0] = getBySimulationId_UserId_PrevAndNext(session,
					workflowSimulationJob, simulationId, userId,
					orderByComparator, true);

			array[1] = workflowSimulationJob;

			array[2] = getBySimulationId_UserId_PrevAndNext(session,
					workflowSimulationJob, simulationId, userId,
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

	protected WorkflowSimulationJob getBySimulationId_UserId_PrevAndNext(
		Session session, WorkflowSimulationJob workflowSimulationJob,
		long simulationId, long userId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWSIMULATIONJOB_WHERE);

		query.append(_FINDER_COLUMN_SIMULATIONID_USERID_SIMULATIONID_2);

		query.append(_FINDER_COLUMN_SIMULATIONID_USERID_USERID_2);

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
			query.append(WorkflowSimulationJobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(simulationId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowSimulationJob);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowSimulationJob> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow simulation jobs where simulationId = &#63; and userId = &#63; from the database.
	 *
	 * @param simulationId the simulation ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBySimulationId_UserId(long simulationId, long userId)
		throws SystemException {
		for (WorkflowSimulationJob workflowSimulationJob : findBySimulationId_UserId(
				simulationId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workflowSimulationJob);
		}
	}

	/**
	 * Returns the number of workflow simulation jobs where simulationId = &#63; and userId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param userId the user ID
	 * @return the number of matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySimulationId_UserId(long simulationId, long userId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONID_USERID;

		Object[] finderArgs = new Object[] { simulationId, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKFLOWSIMULATIONJOB_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONID_USERID_SIMULATIONID_2);

			query.append(_FINDER_COLUMN_SIMULATIONID_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationId);

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

	private static final String _FINDER_COLUMN_SIMULATIONID_USERID_SIMULATIONID_2 =
		"workflowSimulationJob.simulationId = ? AND ";
	private static final String _FINDER_COLUMN_SIMULATIONID_USERID_USERID_2 = "workflowSimulationJob.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE = new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTitle",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE = new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTitle",
			new String[] { String.class.getName() });

	/**
	 * Returns all the workflow simulation jobs where title LIKE &#63;.
	 *
	 * @param title the title
	 * @return the matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findByTitle(String title)
		throws SystemException {
		return findByTitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulation jobs where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @return the range of matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findByTitle(String title, int start,
		int end) throws SystemException {
		return findByTitle(title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulation jobs where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findByTitle(String title, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE;
		finderArgs = new Object[] { title, start, end, orderByComparator };

		List<WorkflowSimulationJob> list = (List<WorkflowSimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkflowSimulationJob workflowSimulationJob : list) {
				if (!StringUtil.wildcardMatches(
							workflowSimulationJob.getTitle(), title,
							CharPool.UNDERLINE, CharPool.PERCENT,
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

			query.append(_SQL_SELECT_WORKFLOWSIMULATIONJOB_WHERE);

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
				query.append(WorkflowSimulationJobModelImpl.ORDER_BY_JPQL);
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
					list = (List<WorkflowSimulationJob>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulationJob>(list);
				}
				else {
					list = (List<WorkflowSimulationJob>)QueryUtil.list(q,
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
	 * Returns the first workflow simulation job in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob findByTitle_First(String title,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = fetchByTitle_First(title,
				orderByComparator);

		if (workflowSimulationJob != null) {
			return workflowSimulationJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationJobException(msg.toString());
	}

	/**
	 * Returns the first workflow simulation job in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob fetchByTitle_First(String title,
		OrderByComparator orderByComparator) throws SystemException {
		List<WorkflowSimulationJob> list = findByTitle(title, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow simulation job in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob findByTitle_Last(String title,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = fetchByTitle_Last(title,
				orderByComparator);

		if (workflowSimulationJob != null) {
			return workflowSimulationJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationJobException(msg.toString());
	}

	/**
	 * Returns the last workflow simulation job in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob fetchByTitle_Last(String title,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTitle(title);

		if (count == 0) {
			return null;
		}

		List<WorkflowSimulationJob> list = findByTitle(title, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow simulation jobs before and after the current workflow simulation job in the ordered set where title LIKE &#63;.
	 *
	 * @param simulationJobId the primary key of the current workflow simulation job
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob[] findByTitle_PrevAndNext(
		long simulationJobId, String title, OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = findByPrimaryKey(simulationJobId);

		Session session = null;

		try {
			session = openSession();

			WorkflowSimulationJob[] array = new WorkflowSimulationJobImpl[3];

			array[0] = getByTitle_PrevAndNext(session, workflowSimulationJob,
					title, orderByComparator, true);

			array[1] = workflowSimulationJob;

			array[2] = getByTitle_PrevAndNext(session, workflowSimulationJob,
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

	protected WorkflowSimulationJob getByTitle_PrevAndNext(Session session,
		WorkflowSimulationJob workflowSimulationJob, String title,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWSIMULATIONJOB_WHERE);

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
			query.append(WorkflowSimulationJobModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(workflowSimulationJob);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowSimulationJob> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow simulation jobs where title LIKE &#63; from the database.
	 *
	 * @param title the title
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTitle(String title) throws SystemException {
		for (WorkflowSimulationJob workflowSimulationJob : findByTitle(title,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workflowSimulationJob);
		}
	}

	/**
	 * Returns the number of workflow simulation jobs where title LIKE &#63;.
	 *
	 * @param title the title
	 * @return the number of matching workflow simulation jobs
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

			query.append(_SQL_COUNT_WORKFLOWSIMULATIONJOB_WHERE);

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

	private static final String _FINDER_COLUMN_TITLE_TITLE_1 = "workflowSimulationJob.title LIKE NULL";
	private static final String _FINDER_COLUMN_TITLE_TITLE_2 = "workflowSimulationJob.title LIKE ?";
	private static final String _FINDER_COLUMN_TITLE_TITLE_3 = "(workflowSimulationJob.title IS NULL OR workflowSimulationJob.title LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			WorkflowSimulationJobModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the workflow simulation jobs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulation jobs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @return the range of matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findByUserId(long userId, int start,
		int end) throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulation jobs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findByUserId(long userId, int start,
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

		List<WorkflowSimulationJob> list = (List<WorkflowSimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkflowSimulationJob workflowSimulationJob : list) {
				if ((userId != workflowSimulationJob.getUserId())) {
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

			query.append(_SQL_SELECT_WORKFLOWSIMULATIONJOB_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowSimulationJobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<WorkflowSimulationJob>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulationJob>(list);
				}
				else {
					list = (List<WorkflowSimulationJob>)QueryUtil.list(q,
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
	 * Returns the first workflow simulation job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = fetchByUserId_First(userId,
				orderByComparator);

		if (workflowSimulationJob != null) {
			return workflowSimulationJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationJobException(msg.toString());
	}

	/**
	 * Returns the first workflow simulation job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WorkflowSimulationJob> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow simulation job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = fetchByUserId_Last(userId,
				orderByComparator);

		if (workflowSimulationJob != null) {
			return workflowSimulationJob;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowSimulationJobException(msg.toString());
	}

	/**
	 * Returns the last workflow simulation job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<WorkflowSimulationJob> list = findByUserId(userId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow simulation jobs before and after the current workflow simulation job in the ordered set where userId = &#63;.
	 *
	 * @param simulationJobId the primary key of the current workflow simulation job
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob[] findByUserId_PrevAndNext(
		long simulationJobId, long userId, OrderByComparator orderByComparator)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = findByPrimaryKey(simulationJobId);

		Session session = null;

		try {
			session = openSession();

			WorkflowSimulationJob[] array = new WorkflowSimulationJobImpl[3];

			array[0] = getByUserId_PrevAndNext(session, workflowSimulationJob,
					userId, orderByComparator, true);

			array[1] = workflowSimulationJob;

			array[2] = getByUserId_PrevAndNext(session, workflowSimulationJob,
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

	protected WorkflowSimulationJob getByUserId_PrevAndNext(Session session,
		WorkflowSimulationJob workflowSimulationJob, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWSIMULATIONJOB_WHERE);

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
			query.append(WorkflowSimulationJobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowSimulationJob);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowSimulationJob> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow simulation jobs where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId(long userId) throws SystemException {
		for (WorkflowSimulationJob workflowSimulationJob : findByUserId(
				userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workflowSimulationJob);
		}
	}

	/**
	 * Returns the number of workflow simulation jobs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching workflow simulation jobs
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

			query.append(_SQL_COUNT_WORKFLOWSIMULATIONJOB_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "workflowSimulationJob.userId = ?";

	public WorkflowSimulationJobPersistenceImpl() {
		setModelClass(WorkflowSimulationJob.class);
	}

	/**
	 * Caches the workflow simulation job in the entity cache if it is enabled.
	 *
	 * @param workflowSimulationJob the workflow simulation job
	 */
	@Override
	public void cacheResult(WorkflowSimulationJob workflowSimulationJob) {
		EntityCacheUtil.putResult(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			workflowSimulationJob.getPrimaryKey(), workflowSimulationJob);

		workflowSimulationJob.resetOriginalValues();
	}

	/**
	 * Caches the workflow simulation jobs in the entity cache if it is enabled.
	 *
	 * @param workflowSimulationJobs the workflow simulation jobs
	 */
	@Override
	public void cacheResult(List<WorkflowSimulationJob> workflowSimulationJobs) {
		for (WorkflowSimulationJob workflowSimulationJob : workflowSimulationJobs) {
			if (EntityCacheUtil.getResult(
						WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowSimulationJobImpl.class,
						workflowSimulationJob.getPrimaryKey()) == null) {
				cacheResult(workflowSimulationJob);
			}
			else {
				workflowSimulationJob.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all workflow simulation jobs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WorkflowSimulationJobImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WorkflowSimulationJobImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the workflow simulation job.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkflowSimulationJob workflowSimulationJob) {
		EntityCacheUtil.removeResult(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			workflowSimulationJob.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WorkflowSimulationJob> workflowSimulationJobs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkflowSimulationJob workflowSimulationJob : workflowSimulationJobs) {
			EntityCacheUtil.removeResult(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowSimulationJobImpl.class,
				workflowSimulationJob.getPrimaryKey());
		}
	}

	/**
	 * Creates a new workflow simulation job with the primary key. Does not add the workflow simulation job to the database.
	 *
	 * @param simulationJobId the primary key for the new workflow simulation job
	 * @return the new workflow simulation job
	 */
	@Override
	public WorkflowSimulationJob create(long simulationJobId) {
		WorkflowSimulationJob workflowSimulationJob = new WorkflowSimulationJobImpl();

		workflowSimulationJob.setNew(true);
		workflowSimulationJob.setPrimaryKey(simulationJobId);

		return workflowSimulationJob;
	}

	/**
	 * Removes the workflow simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulationJobId the primary key of the workflow simulation job
	 * @return the workflow simulation job that was removed
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob remove(long simulationJobId)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		return remove((Serializable)simulationJobId);
	}

	/**
	 * Removes the workflow simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the workflow simulation job
	 * @return the workflow simulation job that was removed
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob remove(Serializable primaryKey)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorkflowSimulationJob workflowSimulationJob = (WorkflowSimulationJob)session.get(WorkflowSimulationJobImpl.class,
					primaryKey);

			if (workflowSimulationJob == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkflowSimulationJobException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workflowSimulationJob);
		}
		catch (NoSuchWorkflowSimulationJobException nsee) {
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
	protected WorkflowSimulationJob removeImpl(
		WorkflowSimulationJob workflowSimulationJob) throws SystemException {
		workflowSimulationJob = toUnwrappedModel(workflowSimulationJob);

		workflowSimulationJobToWorkflowSimulationTableMapper.deleteLeftPrimaryKeyTableMappings(workflowSimulationJob.getPrimaryKey());

		workflowSimulationJobToWorkflowSimulationTableMapper.deleteLeftPrimaryKeyTableMappings(workflowSimulationJob.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workflowSimulationJob)) {
				workflowSimulationJob = (WorkflowSimulationJob)session.get(WorkflowSimulationJobImpl.class,
						workflowSimulationJob.getPrimaryKeyObj());
			}

			if (workflowSimulationJob != null) {
				session.delete(workflowSimulationJob);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workflowSimulationJob != null) {
			clearCache(workflowSimulationJob);
		}

		return workflowSimulationJob;
	}

	@Override
	public WorkflowSimulationJob updateImpl(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws SystemException {
		workflowSimulationJob = toUnwrappedModel(workflowSimulationJob);

		boolean isNew = workflowSimulationJob.isNew();

		WorkflowSimulationJobModelImpl workflowSimulationJobModelImpl = (WorkflowSimulationJobModelImpl)workflowSimulationJob;

		Session session = null;

		try {
			session = openSession();

			if (workflowSimulationJob.isNew()) {
				session.save(workflowSimulationJob);

				workflowSimulationJob.setNew(false);
			}
			else {
				session.merge(workflowSimulationJob);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WorkflowSimulationJobModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((workflowSimulationJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workflowSimulationJobModelImpl.getOriginalSimulationId(),
						workflowSimulationJobModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONID_USERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_USERID,
					args);

				args = new Object[] {
						workflowSimulationJobModelImpl.getSimulationId(),
						workflowSimulationJobModelImpl.getUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONID_USERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_USERID,
					args);
			}

			if ((workflowSimulationJobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workflowSimulationJobModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { workflowSimulationJobModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
		}

		EntityCacheUtil.putResult(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowSimulationJobImpl.class,
			workflowSimulationJob.getPrimaryKey(), workflowSimulationJob);

		return workflowSimulationJob;
	}

	protected WorkflowSimulationJob toUnwrappedModel(
		WorkflowSimulationJob workflowSimulationJob) {
		if (workflowSimulationJob instanceof WorkflowSimulationJobImpl) {
			return workflowSimulationJob;
		}

		WorkflowSimulationJobImpl workflowSimulationJobImpl = new WorkflowSimulationJobImpl();

		workflowSimulationJobImpl.setNew(workflowSimulationJob.isNew());
		workflowSimulationJobImpl.setPrimaryKey(workflowSimulationJob.getPrimaryKey());

		workflowSimulationJobImpl.setSimulationJobId(workflowSimulationJob.getSimulationJobId());
		workflowSimulationJobImpl.setSimulationId(workflowSimulationJob.getSimulationId());
		workflowSimulationJobImpl.setGroupId(workflowSimulationJob.getGroupId());
		workflowSimulationJobImpl.setUserId(workflowSimulationJob.getUserId());
		workflowSimulationJobImpl.setCreateDate(workflowSimulationJob.getCreateDate());
		workflowSimulationJobImpl.setModifiedDate(workflowSimulationJob.getModifiedDate());
		workflowSimulationJobImpl.setTitle(workflowSimulationJob.getTitle());
		workflowSimulationJobImpl.setStatus(workflowSimulationJob.getStatus());
		workflowSimulationJobImpl.setStatusResponse(workflowSimulationJob.getStatusResponse());
		workflowSimulationJobImpl.setStartTime(workflowSimulationJob.getStartTime());
		workflowSimulationJobImpl.setEndTime(workflowSimulationJob.getEndTime());
		workflowSimulationJobImpl.setWorkflowId(workflowSimulationJob.getWorkflowId());
		workflowSimulationJobImpl.setWorkflowUUID(workflowSimulationJob.getWorkflowUUID());
		workflowSimulationJobImpl.setReuseWorkflowUUID(workflowSimulationJob.getReuseWorkflowUUID());
		workflowSimulationJobImpl.setScreenLogic(workflowSimulationJob.getScreenLogic());

		return workflowSimulationJobImpl;
	}

	/**
	 * Returns the workflow simulation job with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow simulation job
	 * @return the workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		WorkflowSimulationJob workflowSimulationJob = fetchByPrimaryKey(primaryKey);

		if (workflowSimulationJob == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkflowSimulationJobException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workflowSimulationJob;
	}

	/**
	 * Returns the workflow simulation job with the primary key or throws a {@link org.kisti.edison.NoSuchWorkflowSimulationJobException} if it could not be found.
	 *
	 * @param simulationJobId the primary key of the workflow simulation job
	 * @return the workflow simulation job
	 * @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob findByPrimaryKey(long simulationJobId)
		throws NoSuchWorkflowSimulationJobException, SystemException {
		return findByPrimaryKey((Serializable)simulationJobId);
	}

	/**
	 * Returns the workflow simulation job with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow simulation job
	 * @return the workflow simulation job, or <code>null</code> if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		WorkflowSimulationJob workflowSimulationJob = (WorkflowSimulationJob)EntityCacheUtil.getResult(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowSimulationJobImpl.class, primaryKey);

		if (workflowSimulationJob == _nullWorkflowSimulationJob) {
			return null;
		}

		if (workflowSimulationJob == null) {
			Session session = null;

			try {
				session = openSession();

				workflowSimulationJob = (WorkflowSimulationJob)session.get(WorkflowSimulationJobImpl.class,
						primaryKey);

				if (workflowSimulationJob != null) {
					cacheResult(workflowSimulationJob);
				}
				else {
					EntityCacheUtil.putResult(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowSimulationJobImpl.class, primaryKey,
						_nullWorkflowSimulationJob);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(WorkflowSimulationJobModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowSimulationJobImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workflowSimulationJob;
	}

	/**
	 * Returns the workflow simulation job with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param simulationJobId the primary key of the workflow simulation job
	 * @return the workflow simulation job, or <code>null</code> if a workflow simulation job with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowSimulationJob fetchByPrimaryKey(long simulationJobId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)simulationJobId);
	}

	/**
	 * Returns all the workflow simulation jobs.
	 *
	 * @return the workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow simulation jobs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @return the range of workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulation jobs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow simulation jobs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowSimulationJob> findAll(int start, int end,
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

		List<WorkflowSimulationJob> list = (List<WorkflowSimulationJob>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WORKFLOWSIMULATIONJOB);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKFLOWSIMULATIONJOB;

				if (pagination) {
					sql = sql.concat(WorkflowSimulationJobModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkflowSimulationJob>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowSimulationJob>(list);
				}
				else {
					list = (List<WorkflowSimulationJob>)QueryUtil.list(q,
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
	 * Removes all the workflow simulation jobs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (WorkflowSimulationJob workflowSimulationJob : findAll()) {
			remove(workflowSimulationJob);
		}
	}

	/**
	 * Returns the number of workflow simulation jobs.
	 *
	 * @return the number of workflow simulation jobs
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

				Query q = session.createQuery(_SQL_COUNT_WORKFLOWSIMULATIONJOB);

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
	 * Returns all the workflow simulations associated with the workflow simulation job.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @return the workflow simulations associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk) throws SystemException {
		return getWorkflowSimulations(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the workflow simulations associated with the workflow simulation job.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @return the range of workflow simulations associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk, int start, int end) throws SystemException {
		return getWorkflowSimulations(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulations associated with the workflow simulation job.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow simulations associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return workflowSimulationJobToWorkflowSimulationTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of workflow simulations associated with the workflow simulation job.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @return the number of workflow simulations associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getWorkflowSimulationsSize(long pk) throws SystemException {
		long[] pks = workflowSimulationJobToWorkflowSimulationTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the workflow simulation is associated with the workflow simulation job.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPK the primary key of the workflow simulation
	 * @return <code>true</code> if the workflow simulation is associated with the workflow simulation job; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsWorkflowSimulation(long pk, long workflowSimulationPK)
		throws SystemException {
		return workflowSimulationJobToWorkflowSimulationTableMapper.containsTableMapping(pk,
			workflowSimulationPK);
	}

	/**
	 * Returns <code>true</code> if the workflow simulation job has any workflow simulations associated with it.
	 *
	 * @param pk the primary key of the workflow simulation job to check for associations with workflow simulations
	 * @return <code>true</code> if the workflow simulation job has any workflow simulations associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsWorkflowSimulations(long pk)
		throws SystemException {
		if (getWorkflowSimulationsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPK the primary key of the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulation(long pk, long workflowSimulationPK)
		throws SystemException {
		workflowSimulationJobToWorkflowSimulationTableMapper.addTableMapping(pk,
			workflowSimulationPK);
	}

	/**
	 * Adds an association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulation the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulation(long pk,
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws SystemException {
		workflowSimulationJobToWorkflowSimulationTableMapper.addTableMapping(pk,
			workflowSimulation.getPrimaryKey());
	}

	/**
	 * Adds an association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPKs the primary keys of the workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws SystemException {
		for (long workflowSimulationPK : workflowSimulationPKs) {
			workflowSimulationJobToWorkflowSimulationTableMapper.addTableMapping(pk,
				workflowSimulationPK);
		}
	}

	/**
	 * Adds an association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulations the workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulations(long pk,
		List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws SystemException {
		for (org.kisti.edison.model.WorkflowSimulation workflowSimulation : workflowSimulations) {
			workflowSimulationJobToWorkflowSimulationTableMapper.addTableMapping(pk,
				workflowSimulation.getPrimaryKey());
		}
	}

	/**
	 * Clears all associations between the workflow simulation job and its workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job to clear the associated workflow simulations from
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearWorkflowSimulations(long pk) throws SystemException {
		workflowSimulationJobToWorkflowSimulationTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPK the primary key of the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulation(long pk, long workflowSimulationPK)
		throws SystemException {
		workflowSimulationJobToWorkflowSimulationTableMapper.deleteTableMapping(pk,
			workflowSimulationPK);
	}

	/**
	 * Removes the association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulation the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulation(long pk,
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws SystemException {
		workflowSimulationJobToWorkflowSimulationTableMapper.deleteTableMapping(pk,
			workflowSimulation.getPrimaryKey());
	}

	/**
	 * Removes the association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPKs the primary keys of the workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws SystemException {
		for (long workflowSimulationPK : workflowSimulationPKs) {
			workflowSimulationJobToWorkflowSimulationTableMapper.deleteTableMapping(pk,
				workflowSimulationPK);
		}
	}

	/**
	 * Removes the association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulations the workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulations(long pk,
		List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws SystemException {
		for (org.kisti.edison.model.WorkflowSimulation workflowSimulation : workflowSimulations) {
			workflowSimulationJobToWorkflowSimulationTableMapper.deleteTableMapping(pk,
				workflowSimulation.getPrimaryKey());
		}
	}

	/**
	 * Sets the workflow simulations associated with the workflow simulation job, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPKs the primary keys of the workflow simulations to be associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws SystemException {
		Set<Long> newWorkflowSimulationPKsSet = SetUtil.fromArray(workflowSimulationPKs);
		Set<Long> oldWorkflowSimulationPKsSet = SetUtil.fromArray(workflowSimulationJobToWorkflowSimulationTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeWorkflowSimulationPKsSet = new HashSet<Long>(oldWorkflowSimulationPKsSet);

		removeWorkflowSimulationPKsSet.removeAll(newWorkflowSimulationPKsSet);

		for (long removeWorkflowSimulationPK : removeWorkflowSimulationPKsSet) {
			workflowSimulationJobToWorkflowSimulationTableMapper.deleteTableMapping(pk,
				removeWorkflowSimulationPK);
		}

		newWorkflowSimulationPKsSet.removeAll(oldWorkflowSimulationPKsSet);

		for (long newWorkflowSimulationPK : newWorkflowSimulationPKsSet) {
			workflowSimulationJobToWorkflowSimulationTableMapper.addTableMapping(pk,
				newWorkflowSimulationPK);
		}
	}

	/**
	 * Sets the workflow simulations associated with the workflow simulation job, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulations the workflow simulations to be associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setWorkflowSimulations(long pk,
		List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws SystemException {
		try {
			long[] workflowSimulationPKs = new long[workflowSimulations.size()];

			for (int i = 0; i < workflowSimulations.size(); i++) {
				org.kisti.edison.model.WorkflowSimulation workflowSimulation = workflowSimulations.get(i);

				workflowSimulationPKs[i] = workflowSimulation.getPrimaryKey();
			}

			setWorkflowSimulations(pk, workflowSimulationPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(WorkflowSimulationJobModelImpl.MAPPING_TABLE_EDWF_WORKFLOWSIMULATION_WORKFLOWSIMULATIONJOB_NAME);
		}
	}

	/**
	 * Returns all the workflow simulations associated with the workflow simulation job.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @return the workflow simulations associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk) throws SystemException {
		return getWorkflowSimulations(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the workflow simulations associated with the workflow simulation job.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @return the range of workflow simulations associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk, int start, int end) throws SystemException {
		return getWorkflowSimulations(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow simulations associated with the workflow simulation job.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param start the lower bound of the range of workflow simulation jobs
	 * @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow simulations associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return workflowSimulationJobToWorkflowSimulationTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of workflow simulations associated with the workflow simulation job.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @return the number of workflow simulations associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getWorkflowSimulationsSize(long pk) throws SystemException {
		long[] pks = workflowSimulationJobToWorkflowSimulationTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the workflow simulation is associated with the workflow simulation job.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPK the primary key of the workflow simulation
	 * @return <code>true</code> if the workflow simulation is associated with the workflow simulation job; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsWorkflowSimulation(long pk, long workflowSimulationPK)
		throws SystemException {
		return workflowSimulationJobToWorkflowSimulationTableMapper.containsTableMapping(pk,
			workflowSimulationPK);
	}

	/**
	 * Returns <code>true</code> if the workflow simulation job has any workflow simulations associated with it.
	 *
	 * @param pk the primary key of the workflow simulation job to check for associations with workflow simulations
	 * @return <code>true</code> if the workflow simulation job has any workflow simulations associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsWorkflowSimulations(long pk)
		throws SystemException {
		if (getWorkflowSimulationsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPK the primary key of the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulation(long pk, long workflowSimulationPK)
		throws SystemException {
		workflowSimulationJobToWorkflowSimulationTableMapper.addTableMapping(pk,
			workflowSimulationPK);
	}

	/**
	 * Adds an association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulation the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulation(long pk,
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws SystemException {
		workflowSimulationJobToWorkflowSimulationTableMapper.addTableMapping(pk,
			workflowSimulation.getPrimaryKey());
	}

	/**
	 * Adds an association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPKs the primary keys of the workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws SystemException {
		for (long workflowSimulationPK : workflowSimulationPKs) {
			workflowSimulationJobToWorkflowSimulationTableMapper.addTableMapping(pk,
				workflowSimulationPK);
		}
	}

	/**
	 * Adds an association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulations the workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addWorkflowSimulations(long pk,
		List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws SystemException {
		for (org.kisti.edison.model.WorkflowSimulation workflowSimulation : workflowSimulations) {
			workflowSimulationJobToWorkflowSimulationTableMapper.addTableMapping(pk,
				workflowSimulation.getPrimaryKey());
		}
	}

	/**
	 * Clears all associations between the workflow simulation job and its workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job to clear the associated workflow simulations from
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearWorkflowSimulations(long pk) throws SystemException {
		workflowSimulationJobToWorkflowSimulationTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPK the primary key of the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulation(long pk, long workflowSimulationPK)
		throws SystemException {
		workflowSimulationJobToWorkflowSimulationTableMapper.deleteTableMapping(pk,
			workflowSimulationPK);
	}

	/**
	 * Removes the association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulation the workflow simulation
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulation(long pk,
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws SystemException {
		workflowSimulationJobToWorkflowSimulationTableMapper.deleteTableMapping(pk,
			workflowSimulation.getPrimaryKey());
	}

	/**
	 * Removes the association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPKs the primary keys of the workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws SystemException {
		for (long workflowSimulationPK : workflowSimulationPKs) {
			workflowSimulationJobToWorkflowSimulationTableMapper.deleteTableMapping(pk,
				workflowSimulationPK);
		}
	}

	/**
	 * Removes the association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulations the workflow simulations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeWorkflowSimulations(long pk,
		List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws SystemException {
		for (org.kisti.edison.model.WorkflowSimulation workflowSimulation : workflowSimulations) {
			workflowSimulationJobToWorkflowSimulationTableMapper.deleteTableMapping(pk,
				workflowSimulation.getPrimaryKey());
		}
	}

	/**
	 * Sets the workflow simulations associated with the workflow simulation job, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulationPKs the primary keys of the workflow simulations to be associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws SystemException {
		Set<Long> newWorkflowSimulationPKsSet = SetUtil.fromArray(workflowSimulationPKs);
		Set<Long> oldWorkflowSimulationPKsSet = SetUtil.fromArray(workflowSimulationJobToWorkflowSimulationTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeWorkflowSimulationPKsSet = new HashSet<Long>(oldWorkflowSimulationPKsSet);

		removeWorkflowSimulationPKsSet.removeAll(newWorkflowSimulationPKsSet);

		for (long removeWorkflowSimulationPK : removeWorkflowSimulationPKsSet) {
			workflowSimulationJobToWorkflowSimulationTableMapper.deleteTableMapping(pk,
				removeWorkflowSimulationPK);
		}

		newWorkflowSimulationPKsSet.removeAll(oldWorkflowSimulationPKsSet);

		for (long newWorkflowSimulationPK : newWorkflowSimulationPKsSet) {
			workflowSimulationJobToWorkflowSimulationTableMapper.addTableMapping(pk,
				newWorkflowSimulationPK);
		}
	}

	/**
	 * Sets the workflow simulations associated with the workflow simulation job, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the workflow simulation job
	 * @param workflowSimulations the workflow simulations to be associated with the workflow simulation job
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setWorkflowSimulations(long pk,
		List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws SystemException {
		try {
			long[] workflowSimulationPKs = new long[workflowSimulations.size()];

			for (int i = 0; i < workflowSimulations.size(); i++) {
				org.kisti.edison.model.WorkflowSimulation workflowSimulation = workflowSimulations.get(i);

				workflowSimulationPKs[i] = workflowSimulation.getPrimaryKey();
			}

			setWorkflowSimulations(pk, workflowSimulationPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(WorkflowSimulationJobModelImpl.MAPPING_TABLE_EDWF_WORKFLOWSIMULATION_WORKFLOWSIMULATIONJOB_NAME);
		}
	}

	/**
	 * Initializes the workflow simulation job persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.WorkflowSimulationJob")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorkflowSimulationJob>> listenersList = new ArrayList<ModelListener<WorkflowSimulationJob>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorkflowSimulationJob>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

<<<<<<< HEAD
=======
		workflowSimulationJobToWorkflowTableMapper = TableMapperFactory.getTableMapper("EDWF_Workflow_WorkflowInstance",
				"simulationJobId", "workflowId", this, workflowPersistence);

>>>>>>> 7b21a178c4fc74feebf3336ab3a4473abb54bf29
		workflowSimulationJobToWorkflowSimulationTableMapper = TableMapperFactory.getTableMapper("EDWF_WorkflowSimulation_WorkflowSimulationJob",
				"simulationJobId", "simulationId", this,
				workflowSimulationPersistence);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(WorkflowSimulationJobImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

<<<<<<< HEAD
=======
		TableMapperFactory.removeTableMapper("EDWF_Workflow_WorkflowInstance");
>>>>>>> 7b21a178c4fc74feebf3336ab3a4473abb54bf29
		TableMapperFactory.removeTableMapper(
			"EDWF_WorkflowSimulation_WorkflowSimulationJob");
	}

<<<<<<< HEAD
=======
	@BeanReference(type = WorkflowPersistence.class)
	protected WorkflowPersistence workflowPersistence;
	protected TableMapper<WorkflowSimulationJob, org.kisti.edison.model.Workflow> workflowSimulationJobToWorkflowTableMapper;
>>>>>>> 7b21a178c4fc74feebf3336ab3a4473abb54bf29
	@BeanReference(type = WorkflowSimulationPersistence.class)
	protected WorkflowSimulationPersistence workflowSimulationPersistence;
	protected TableMapper<WorkflowSimulationJob, org.kisti.edison.model.WorkflowSimulation> workflowSimulationJobToWorkflowSimulationTableMapper;
	private static final String _SQL_SELECT_WORKFLOWSIMULATIONJOB = "SELECT workflowSimulationJob FROM WorkflowSimulationJob workflowSimulationJob";
	private static final String _SQL_SELECT_WORKFLOWSIMULATIONJOB_WHERE = "SELECT workflowSimulationJob FROM WorkflowSimulationJob workflowSimulationJob WHERE ";
	private static final String _SQL_COUNT_WORKFLOWSIMULATIONJOB = "SELECT COUNT(workflowSimulationJob) FROM WorkflowSimulationJob workflowSimulationJob";
	private static final String _SQL_COUNT_WORKFLOWSIMULATIONJOB_WHERE = "SELECT COUNT(workflowSimulationJob) FROM WorkflowSimulationJob workflowSimulationJob WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workflowSimulationJob.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkflowSimulationJob exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WorkflowSimulationJob exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(WorkflowSimulationJobPersistenceImpl.class);
	private static WorkflowSimulationJob _nullWorkflowSimulationJob = new WorkflowSimulationJobImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WorkflowSimulationJob> toCacheModel() {
				return _nullWorkflowSimulationJobCacheModel;
			}
		};

	private static CacheModel<WorkflowSimulationJob> _nullWorkflowSimulationJobCacheModel =
		new CacheModel<WorkflowSimulationJob>() {
			@Override
			public WorkflowSimulationJob toEntityModel() {
				return _nullWorkflowSimulationJob;
			}
		};
}