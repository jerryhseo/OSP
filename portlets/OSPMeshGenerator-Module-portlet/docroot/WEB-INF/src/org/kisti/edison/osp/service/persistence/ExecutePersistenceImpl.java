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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.kisti.edison.osp.NoSuchExecuteException;
import org.kisti.edison.osp.model.Execute;
import org.kisti.edison.osp.model.impl.ExecuteImpl;
import org.kisti.edison.osp.model.impl.ExecuteModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the execute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ExecutePersistence
 * @see ExecuteUtil
 * @generated
 */
public class ExecutePersistenceImpl extends BasePersistenceImpl<Execute>
	implements ExecutePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExecuteUtil} to access the execute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExecuteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteModelImpl.FINDER_CACHE_ENABLED, ExecuteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteModelImpl.FINDER_CACHE_ENABLED, ExecuteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECTID =
		new FinderPath(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteModelImpl.FINDER_CACHE_ENABLED, ExecuteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID =
		new FinderPath(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteModelImpl.FINDER_CACHE_ENABLED, ExecuteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProjectId",
			new String[] { Long.class.getName() },
			ExecuteModelImpl.PROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROJECTID = new FinderPath(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the executes where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Execute> findByProjectId(long projectId)
		throws SystemException {
		return findByProjectId(projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the executes where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of executes
	 * @param end the upper bound of the range of executes (not inclusive)
	 * @return the range of matching executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Execute> findByProjectId(long projectId, int start, int end)
		throws SystemException {
		return findByProjectId(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the executes where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of executes
	 * @param end the upper bound of the range of executes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Execute> findByProjectId(long projectId, int start, int end,
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

		List<Execute> list = (List<Execute>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Execute execute : list) {
				if ((projectId != execute.getProjectId())) {
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

			query.append(_SQL_SELECT_EXECUTE_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ExecuteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (!pagination) {
					list = (List<Execute>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Execute>(list);
				}
				else {
					list = (List<Execute>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first execute in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching execute
	 * @throws org.kisti.edison.osp.NoSuchExecuteException if a matching execute could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute findByProjectId_First(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchExecuteException, SystemException {
		Execute execute = fetchByProjectId_First(projectId, orderByComparator);

		if (execute != null) {
			return execute;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExecuteException(msg.toString());
	}

	/**
	 * Returns the first execute in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching execute, or <code>null</code> if a matching execute could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute fetchByProjectId_First(long projectId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Execute> list = findByProjectId(projectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last execute in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching execute
	 * @throws org.kisti.edison.osp.NoSuchExecuteException if a matching execute could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute findByProjectId_Last(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchExecuteException, SystemException {
		Execute execute = fetchByProjectId_Last(projectId, orderByComparator);

		if (execute != null) {
			return execute;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExecuteException(msg.toString());
	}

	/**
	 * Returns the last execute in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching execute, or <code>null</code> if a matching execute could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute fetchByProjectId_Last(long projectId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByProjectId(projectId);

		if (count == 0) {
			return null;
		}

		List<Execute> list = findByProjectId(projectId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the executes before and after the current execute in the ordered set where projectId = &#63;.
	 *
	 * @param executePK the primary key of the current execute
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next execute
	 * @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute[] findByProjectId_PrevAndNext(ExecutePK executePK,
		long projectId, OrderByComparator orderByComparator)
		throws NoSuchExecuteException, SystemException {
		Execute execute = findByPrimaryKey(executePK);

		Session session = null;

		try {
			session = openSession();

			Execute[] array = new ExecuteImpl[3];

			array[0] = getByProjectId_PrevAndNext(session, execute, projectId,
					orderByComparator, true);

			array[1] = execute;

			array[2] = getByProjectId_PrevAndNext(session, execute, projectId,
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

	protected Execute getByProjectId_PrevAndNext(Session session,
		Execute execute, long projectId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXECUTE_WHERE);

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
			query.append(ExecuteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(execute);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Execute> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the executes where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByProjectId(long projectId) throws SystemException {
		for (Execute execute : findByProjectId(projectId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(execute);
		}
	}

	/**
	 * Returns the number of executes where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching executes
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

			query.append(_SQL_COUNT_EXECUTE_WHERE);

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

	private static final String _FINDER_COLUMN_PROJECTID_PROJECTID_2 = "execute.id.projectId = ?";

	public ExecutePersistenceImpl() {
		setModelClass(Execute.class);
	}

	/**
	 * Caches the execute in the entity cache if it is enabled.
	 *
	 * @param execute the execute
	 */
	@Override
	public void cacheResult(Execute execute) {
		EntityCacheUtil.putResult(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteImpl.class, execute.getPrimaryKey(), execute);

		execute.resetOriginalValues();
	}

	/**
	 * Caches the executes in the entity cache if it is enabled.
	 *
	 * @param executes the executes
	 */
	@Override
	public void cacheResult(List<Execute> executes) {
		for (Execute execute : executes) {
			if (EntityCacheUtil.getResult(
						ExecuteModelImpl.ENTITY_CACHE_ENABLED,
						ExecuteImpl.class, execute.getPrimaryKey()) == null) {
				cacheResult(execute);
			}
			else {
				execute.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all executes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExecuteImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ExecuteImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the execute.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Execute execute) {
		EntityCacheUtil.removeResult(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteImpl.class, execute.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Execute> executes) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Execute execute : executes) {
			EntityCacheUtil.removeResult(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
				ExecuteImpl.class, execute.getPrimaryKey());
		}
	}

	/**
	 * Creates a new execute with the primary key. Does not add the execute to the database.
	 *
	 * @param executePK the primary key for the new execute
	 * @return the new execute
	 */
	@Override
	public Execute create(ExecutePK executePK) {
		Execute execute = new ExecuteImpl();

		execute.setNew(true);
		execute.setPrimaryKey(executePK);

		return execute;
	}

	/**
	 * Removes the execute with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param executePK the primary key of the execute
	 * @return the execute that was removed
	 * @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute remove(ExecutePK executePK)
		throws NoSuchExecuteException, SystemException {
		return remove((Serializable)executePK);
	}

	/**
	 * Removes the execute with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the execute
	 * @return the execute that was removed
	 * @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute remove(Serializable primaryKey)
		throws NoSuchExecuteException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Execute execute = (Execute)session.get(ExecuteImpl.class, primaryKey);

			if (execute == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExecuteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(execute);
		}
		catch (NoSuchExecuteException nsee) {
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
	protected Execute removeImpl(Execute execute) throws SystemException {
		execute = toUnwrappedModel(execute);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(execute)) {
				execute = (Execute)session.get(ExecuteImpl.class,
						execute.getPrimaryKeyObj());
			}

			if (execute != null) {
				session.delete(execute);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (execute != null) {
			clearCache(execute);
		}

		return execute;
	}

	@Override
	public Execute updateImpl(org.kisti.edison.osp.model.Execute execute)
		throws SystemException {
		execute = toUnwrappedModel(execute);

		boolean isNew = execute.isNew();

		ExecuteModelImpl executeModelImpl = (ExecuteModelImpl)execute;

		Session session = null;

		try {
			session = openSession();

			if (execute.isNew()) {
				session.save(execute);

				execute.setNew(false);
			}
			else {
				session.merge(execute);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ExecuteModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((executeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						executeModelImpl.getOriginalProjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID,
					args);

				args = new Object[] { executeModelImpl.getProjectId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID,
					args);
			}
		}

		EntityCacheUtil.putResult(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteImpl.class, execute.getPrimaryKey(), execute);

		return execute;
	}

	protected Execute toUnwrappedModel(Execute execute) {
		if (execute instanceof ExecuteImpl) {
			return execute;
		}

		ExecuteImpl executeImpl = new ExecuteImpl();

		executeImpl.setNew(execute.isNew());
		executeImpl.setPrimaryKey(execute.getPrimaryKey());

		executeImpl.setProjectId(execute.getProjectId());
		executeImpl.setAnalyzerId(execute.getAnalyzerId());
		executeImpl.setExecuteDataStructure(execute.getExecuteDataStructure());
		executeImpl.setExecuteDate(execute.getExecuteDate());

		return executeImpl;
	}

	/**
	 * Returns the execute with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the execute
	 * @return the execute
	 * @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute findByPrimaryKey(Serializable primaryKey)
		throws NoSuchExecuteException, SystemException {
		Execute execute = fetchByPrimaryKey(primaryKey);

		if (execute == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchExecuteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return execute;
	}

	/**
	 * Returns the execute with the primary key or throws a {@link org.kisti.edison.osp.NoSuchExecuteException} if it could not be found.
	 *
	 * @param executePK the primary key of the execute
	 * @return the execute
	 * @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute findByPrimaryKey(ExecutePK executePK)
		throws NoSuchExecuteException, SystemException {
		return findByPrimaryKey((Serializable)executePK);
	}

	/**
	 * Returns the execute with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the execute
	 * @return the execute, or <code>null</code> if a execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Execute execute = (Execute)EntityCacheUtil.getResult(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
				ExecuteImpl.class, primaryKey);

		if (execute == _nullExecute) {
			return null;
		}

		if (execute == null) {
			Session session = null;

			try {
				session = openSession();

				execute = (Execute)session.get(ExecuteImpl.class, primaryKey);

				if (execute != null) {
					cacheResult(execute);
				}
				else {
					EntityCacheUtil.putResult(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
						ExecuteImpl.class, primaryKey, _nullExecute);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ExecuteModelImpl.ENTITY_CACHE_ENABLED,
					ExecuteImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return execute;
	}

	/**
	 * Returns the execute with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param executePK the primary key of the execute
	 * @return the execute, or <code>null</code> if a execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Execute fetchByPrimaryKey(ExecutePK executePK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)executePK);
	}

	/**
	 * Returns all the executes.
	 *
	 * @return the executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Execute> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the executes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of executes
	 * @param end the upper bound of the range of executes (not inclusive)
	 * @return the range of executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Execute> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the executes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of executes
	 * @param end the upper bound of the range of executes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Execute> findAll(int start, int end,
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

		List<Execute> list = (List<Execute>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EXECUTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXECUTE;

				if (pagination) {
					sql = sql.concat(ExecuteModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Execute>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Execute>(list);
				}
				else {
					list = (List<Execute>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the executes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Execute execute : findAll()) {
			remove(execute);
		}
	}

	/**
	 * Returns the number of executes.
	 *
	 * @return the number of executes
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

				Query q = session.createQuery(_SQL_COUNT_EXECUTE);

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
	 * Initializes the execute persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.osp.model.Execute")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Execute>> listenersList = new ArrayList<ModelListener<Execute>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Execute>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ExecuteImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_EXECUTE = "SELECT execute FROM Execute execute";
	private static final String _SQL_SELECT_EXECUTE_WHERE = "SELECT execute FROM Execute execute WHERE ";
	private static final String _SQL_COUNT_EXECUTE = "SELECT COUNT(execute) FROM Execute execute";
	private static final String _SQL_COUNT_EXECUTE_WHERE = "SELECT COUNT(execute) FROM Execute execute WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "execute.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Execute exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Execute exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ExecutePersistenceImpl.class);
	private static Execute _nullExecute = new ExecuteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Execute> toCacheModel() {
				return _nullExecuteCacheModel;
			}
		};

	private static CacheModel<Execute> _nullExecuteCacheModel = new CacheModel<Execute>() {
			@Override
			public Execute toEntityModel() {
				return _nullExecute;
			}
		};
}