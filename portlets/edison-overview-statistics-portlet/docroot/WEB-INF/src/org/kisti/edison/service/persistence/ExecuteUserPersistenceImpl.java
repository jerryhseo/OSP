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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.kisti.edison.NoSuchExecuteUserException;
import org.kisti.edison.model.ExecuteUser;
import org.kisti.edison.model.impl.ExecuteUserImpl;
import org.kisti.edison.model.impl.ExecuteUserModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the execute user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ExecuteUserPersistence
 * @see ExecuteUserUtil
 * @generated
 */
public class ExecuteUserPersistenceImpl extends BasePersistenceImpl<ExecuteUser>
	implements ExecuteUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExecuteUserUtil} to access the execute user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExecuteUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteUserModelImpl.FINDER_CACHE_ENABLED, ExecuteUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteUserModelImpl.FINDER_CACHE_ENABLED, ExecuteUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ExecuteUserPersistenceImpl() {
		setModelClass(ExecuteUser.class);
	}

	/**
	 * Caches the execute user in the entity cache if it is enabled.
	 *
	 * @param executeUser the execute user
	 */
	@Override
	public void cacheResult(ExecuteUser executeUser) {
		EntityCacheUtil.putResult(ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteUserImpl.class, executeUser.getPrimaryKey(), executeUser);

		executeUser.resetOriginalValues();
	}

	/**
	 * Caches the execute users in the entity cache if it is enabled.
	 *
	 * @param executeUsers the execute users
	 */
	@Override
	public void cacheResult(List<ExecuteUser> executeUsers) {
		for (ExecuteUser executeUser : executeUsers) {
			if (EntityCacheUtil.getResult(
						ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
						ExecuteUserImpl.class, executeUser.getPrimaryKey()) == null) {
				cacheResult(executeUser);
			}
			else {
				executeUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all execute users.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExecuteUserImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ExecuteUserImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the execute user.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExecuteUser executeUser) {
		EntityCacheUtil.removeResult(ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteUserImpl.class, executeUser.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ExecuteUser> executeUsers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ExecuteUser executeUser : executeUsers) {
			EntityCacheUtil.removeResult(ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
				ExecuteUserImpl.class, executeUser.getPrimaryKey());
		}
	}

	/**
	 * Creates a new execute user with the primary key. Does not add the execute user to the database.
	 *
	 * @param createDate the primary key for the new execute user
	 * @return the new execute user
	 */
	@Override
	public ExecuteUser create(String createDate) {
		ExecuteUser executeUser = new ExecuteUserImpl();

		executeUser.setNew(true);
		executeUser.setPrimaryKey(createDate);

		return executeUser;
	}

	/**
	 * Removes the execute user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param createDate the primary key of the execute user
	 * @return the execute user that was removed
	 * @throws org.kisti.edison.NoSuchExecuteUserException if a execute user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExecuteUser remove(String createDate)
		throws NoSuchExecuteUserException, SystemException {
		return remove((Serializable)createDate);
	}

	/**
	 * Removes the execute user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the execute user
	 * @return the execute user that was removed
	 * @throws org.kisti.edison.NoSuchExecuteUserException if a execute user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExecuteUser remove(Serializable primaryKey)
		throws NoSuchExecuteUserException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ExecuteUser executeUser = (ExecuteUser)session.get(ExecuteUserImpl.class,
					primaryKey);

			if (executeUser == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExecuteUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(executeUser);
		}
		catch (NoSuchExecuteUserException nsee) {
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
	protected ExecuteUser removeImpl(ExecuteUser executeUser)
		throws SystemException {
		executeUser = toUnwrappedModel(executeUser);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(executeUser)) {
				executeUser = (ExecuteUser)session.get(ExecuteUserImpl.class,
						executeUser.getPrimaryKeyObj());
			}

			if (executeUser != null) {
				session.delete(executeUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (executeUser != null) {
			clearCache(executeUser);
		}

		return executeUser;
	}

	@Override
	public ExecuteUser updateImpl(
		org.kisti.edison.model.ExecuteUser executeUser)
		throws SystemException {
		executeUser = toUnwrappedModel(executeUser);

		boolean isNew = executeUser.isNew();

		Session session = null;

		try {
			session = openSession();

			if (executeUser.isNew()) {
				session.save(executeUser);

				executeUser.setNew(false);
			}
			else {
				session.merge(executeUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
			ExecuteUserImpl.class, executeUser.getPrimaryKey(), executeUser);

		return executeUser;
	}

	protected ExecuteUser toUnwrappedModel(ExecuteUser executeUser) {
		if (executeUser instanceof ExecuteUserImpl) {
			return executeUser;
		}

		ExecuteUserImpl executeUserImpl = new ExecuteUserImpl();

		executeUserImpl.setNew(executeUser.isNew());
		executeUserImpl.setPrimaryKey(executeUser.getPrimaryKey());

		executeUserImpl.setCreateDate(executeUser.getCreateDate());
		executeUserImpl.setCnt(executeUser.getCnt());

		return executeUserImpl;
	}

	/**
	 * Returns the execute user with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the execute user
	 * @return the execute user
	 * @throws org.kisti.edison.NoSuchExecuteUserException if a execute user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExecuteUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchExecuteUserException, SystemException {
		ExecuteUser executeUser = fetchByPrimaryKey(primaryKey);

		if (executeUser == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchExecuteUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return executeUser;
	}

	/**
	 * Returns the execute user with the primary key or throws a {@link org.kisti.edison.NoSuchExecuteUserException} if it could not be found.
	 *
	 * @param createDate the primary key of the execute user
	 * @return the execute user
	 * @throws org.kisti.edison.NoSuchExecuteUserException if a execute user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExecuteUser findByPrimaryKey(String createDate)
		throws NoSuchExecuteUserException, SystemException {
		return findByPrimaryKey((Serializable)createDate);
	}

	/**
	 * Returns the execute user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the execute user
	 * @return the execute user, or <code>null</code> if a execute user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExecuteUser fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ExecuteUser executeUser = (ExecuteUser)EntityCacheUtil.getResult(ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
				ExecuteUserImpl.class, primaryKey);

		if (executeUser == _nullExecuteUser) {
			return null;
		}

		if (executeUser == null) {
			Session session = null;

			try {
				session = openSession();

				executeUser = (ExecuteUser)session.get(ExecuteUserImpl.class,
						primaryKey);

				if (executeUser != null) {
					cacheResult(executeUser);
				}
				else {
					EntityCacheUtil.putResult(ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
						ExecuteUserImpl.class, primaryKey, _nullExecuteUser);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ExecuteUserModelImpl.ENTITY_CACHE_ENABLED,
					ExecuteUserImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return executeUser;
	}

	/**
	 * Returns the execute user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param createDate the primary key of the execute user
	 * @return the execute user, or <code>null</code> if a execute user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExecuteUser fetchByPrimaryKey(String createDate)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)createDate);
	}

	/**
	 * Returns all the execute users.
	 *
	 * @return the execute users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExecuteUser> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the execute users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.ExecuteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of execute users
	 * @param end the upper bound of the range of execute users (not inclusive)
	 * @return the range of execute users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExecuteUser> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the execute users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.ExecuteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of execute users
	 * @param end the upper bound of the range of execute users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of execute users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExecuteUser> findAll(int start, int end,
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

		List<ExecuteUser> list = (List<ExecuteUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EXECUTEUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXECUTEUSER;

				if (pagination) {
					sql = sql.concat(ExecuteUserModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ExecuteUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ExecuteUser>(list);
				}
				else {
					list = (List<ExecuteUser>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the execute users from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ExecuteUser executeUser : findAll()) {
			remove(executeUser);
		}
	}

	/**
	 * Returns the number of execute users.
	 *
	 * @return the number of execute users
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

				Query q = session.createQuery(_SQL_COUNT_EXECUTEUSER);

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
	 * Initializes the execute user persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.ExecuteUser")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ExecuteUser>> listenersList = new ArrayList<ModelListener<ExecuteUser>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ExecuteUser>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ExecuteUserImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_EXECUTEUSER = "SELECT executeUser FROM ExecuteUser executeUser";
	private static final String _SQL_COUNT_EXECUTEUSER = "SELECT COUNT(executeUser) FROM ExecuteUser executeUser";
	private static final String _ORDER_BY_ENTITY_ALIAS = "executeUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExecuteUser exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ExecuteUserPersistenceImpl.class);
	private static ExecuteUser _nullExecuteUser = new ExecuteUserImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ExecuteUser> toCacheModel() {
				return _nullExecuteUserCacheModel;
			}
		};

	private static CacheModel<ExecuteUser> _nullExecuteUserCacheModel = new CacheModel<ExecuteUser>() {
			@Override
			public ExecuteUser toEntityModel() {
				return _nullExecuteUser;
			}
		};
}