/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

package org.kisti.edison.science.service.persistence;

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

import org.kisti.edison.science.NoSuchScienceAppCompileException;
import org.kisti.edison.science.model.ScienceAppCompile;
import org.kisti.edison.science.model.impl.ScienceAppCompileImpl;
import org.kisti.edison.science.model.impl.ScienceAppCompileModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the science app compile service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ScienceAppCompilePersistence
 * @see ScienceAppCompileUtil
 * @generated
 */
public class ScienceAppCompilePersistenceImpl extends BasePersistenceImpl<ScienceAppCompile>
	implements ScienceAppCompilePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScienceAppCompileUtil} to access the science app compile persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScienceAppCompileImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppCompileModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppCompileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppCompileModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppCompileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppCompileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ScienceAppCompilePersistenceImpl() {
		setModelClass(ScienceAppCompile.class);
	}

	/**
	 * Caches the science app compile in the entity cache if it is enabled.
	 *
	 * @param scienceAppCompile the science app compile
	 */
	@Override
	public void cacheResult(ScienceAppCompile scienceAppCompile) {
		EntityCacheUtil.putResult(ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppCompileImpl.class, scienceAppCompile.getPrimaryKey(),
			scienceAppCompile);

		scienceAppCompile.resetOriginalValues();
	}

	/**
	 * Caches the science app compiles in the entity cache if it is enabled.
	 *
	 * @param scienceAppCompiles the science app compiles
	 */
	@Override
	public void cacheResult(List<ScienceAppCompile> scienceAppCompiles) {
		for (ScienceAppCompile scienceAppCompile : scienceAppCompiles) {
			if (EntityCacheUtil.getResult(
						ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppCompileImpl.class,
						scienceAppCompile.getPrimaryKey()) == null) {
				cacheResult(scienceAppCompile);
			}
			else {
				scienceAppCompile.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all science app compiles.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ScienceAppCompileImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ScienceAppCompileImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the science app compile.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ScienceAppCompile scienceAppCompile) {
		EntityCacheUtil.removeResult(ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppCompileImpl.class, scienceAppCompile.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ScienceAppCompile> scienceAppCompiles) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ScienceAppCompile scienceAppCompile : scienceAppCompiles) {
			EntityCacheUtil.removeResult(ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppCompileImpl.class, scienceAppCompile.getPrimaryKey());
		}
	}

	/**
	 * Creates a new science app compile with the primary key. Does not add the science app compile to the database.
	 *
	 * @param scienceAppId the primary key for the new science app compile
	 * @return the new science app compile
	 */
	@Override
	public ScienceAppCompile create(long scienceAppId) {
		ScienceAppCompile scienceAppCompile = new ScienceAppCompileImpl();

		scienceAppCompile.setNew(true);
		scienceAppCompile.setPrimaryKey(scienceAppId);

		return scienceAppCompile;
	}

	/**
	 * Removes the science app compile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppId the primary key of the science app compile
	 * @return the science app compile that was removed
	 * @throws org.kisti.edison.science.NoSuchScienceAppCompileException if a science app compile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppCompile remove(long scienceAppId)
		throws NoSuchScienceAppCompileException, SystemException {
		return remove((Serializable)scienceAppId);
	}

	/**
	 * Removes the science app compile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the science app compile
	 * @return the science app compile that was removed
	 * @throws org.kisti.edison.science.NoSuchScienceAppCompileException if a science app compile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppCompile remove(Serializable primaryKey)
		throws NoSuchScienceAppCompileException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ScienceAppCompile scienceAppCompile = (ScienceAppCompile)session.get(ScienceAppCompileImpl.class,
					primaryKey);

			if (scienceAppCompile == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScienceAppCompileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scienceAppCompile);
		}
		catch (NoSuchScienceAppCompileException nsee) {
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
	protected ScienceAppCompile removeImpl(ScienceAppCompile scienceAppCompile)
		throws SystemException {
		scienceAppCompile = toUnwrappedModel(scienceAppCompile);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scienceAppCompile)) {
				scienceAppCompile = (ScienceAppCompile)session.get(ScienceAppCompileImpl.class,
						scienceAppCompile.getPrimaryKeyObj());
			}

			if (scienceAppCompile != null) {
				session.delete(scienceAppCompile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scienceAppCompile != null) {
			clearCache(scienceAppCompile);
		}

		return scienceAppCompile;
	}

	@Override
	public ScienceAppCompile updateImpl(
		org.kisti.edison.science.model.ScienceAppCompile scienceAppCompile)
		throws SystemException {
		scienceAppCompile = toUnwrappedModel(scienceAppCompile);

		boolean isNew = scienceAppCompile.isNew();

		Session session = null;

		try {
			session = openSession();

			if (scienceAppCompile.isNew()) {
				session.save(scienceAppCompile);

				scienceAppCompile.setNew(false);
			}
			else {
				session.merge(scienceAppCompile);
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

		EntityCacheUtil.putResult(ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppCompileImpl.class, scienceAppCompile.getPrimaryKey(),
			scienceAppCompile);

		return scienceAppCompile;
	}

	protected ScienceAppCompile toUnwrappedModel(
		ScienceAppCompile scienceAppCompile) {
		if (scienceAppCompile instanceof ScienceAppCompileImpl) {
			return scienceAppCompile;
		}

		ScienceAppCompileImpl scienceAppCompileImpl = new ScienceAppCompileImpl();

		scienceAppCompileImpl.setNew(scienceAppCompile.isNew());
		scienceAppCompileImpl.setPrimaryKey(scienceAppCompile.getPrimaryKey());

		scienceAppCompileImpl.setScienceAppId(scienceAppCompile.getScienceAppId());
		scienceAppCompileImpl.setUserId(scienceAppCompile.getUserId());
		scienceAppCompileImpl.setCompileUrl(scienceAppCompile.getCompileUrl());
		scienceAppCompileImpl.setResult(scienceAppCompile.getResult());
		scienceAppCompileImpl.setCreateDate(scienceAppCompile.getCreateDate());

		return scienceAppCompileImpl;
	}

	/**
	 * Returns the science app compile with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app compile
	 * @return the science app compile
	 * @throws org.kisti.edison.science.NoSuchScienceAppCompileException if a science app compile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppCompile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScienceAppCompileException, SystemException {
		ScienceAppCompile scienceAppCompile = fetchByPrimaryKey(primaryKey);

		if (scienceAppCompile == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScienceAppCompileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scienceAppCompile;
	}

	/**
	 * Returns the science app compile with the primary key or throws a {@link org.kisti.edison.science.NoSuchScienceAppCompileException} if it could not be found.
	 *
	 * @param scienceAppId the primary key of the science app compile
	 * @return the science app compile
	 * @throws org.kisti.edison.science.NoSuchScienceAppCompileException if a science app compile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppCompile findByPrimaryKey(long scienceAppId)
		throws NoSuchScienceAppCompileException, SystemException {
		return findByPrimaryKey((Serializable)scienceAppId);
	}

	/**
	 * Returns the science app compile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app compile
	 * @return the science app compile, or <code>null</code> if a science app compile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppCompile fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ScienceAppCompile scienceAppCompile = (ScienceAppCompile)EntityCacheUtil.getResult(ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppCompileImpl.class, primaryKey);

		if (scienceAppCompile == _nullScienceAppCompile) {
			return null;
		}

		if (scienceAppCompile == null) {
			Session session = null;

			try {
				session = openSession();

				scienceAppCompile = (ScienceAppCompile)session.get(ScienceAppCompileImpl.class,
						primaryKey);

				if (scienceAppCompile != null) {
					cacheResult(scienceAppCompile);
				}
				else {
					EntityCacheUtil.putResult(ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppCompileImpl.class, primaryKey,
						_nullScienceAppCompile);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ScienceAppCompileModelImpl.ENTITY_CACHE_ENABLED,
					ScienceAppCompileImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scienceAppCompile;
	}

	/**
	 * Returns the science app compile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppId the primary key of the science app compile
	 * @return the science app compile, or <code>null</code> if a science app compile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppCompile fetchByPrimaryKey(long scienceAppId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)scienceAppId);
	}

	/**
	 * Returns all the science app compiles.
	 *
	 * @return the science app compiles
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppCompile> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science app compiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppCompileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science app compiles
	 * @param end the upper bound of the range of science app compiles (not inclusive)
	 * @return the range of science app compiles
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppCompile> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the science app compiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppCompileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science app compiles
	 * @param end the upper bound of the range of science app compiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of science app compiles
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppCompile> findAll(int start, int end,
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

		List<ScienceAppCompile> list = (List<ScienceAppCompile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SCIENCEAPPCOMPILE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCIENCEAPPCOMPILE;

				if (pagination) {
					sql = sql.concat(ScienceAppCompileModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ScienceAppCompile>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ScienceAppCompile>(list);
				}
				else {
					list = (List<ScienceAppCompile>)QueryUtil.list(q,
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
	 * Removes all the science app compiles from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ScienceAppCompile scienceAppCompile : findAll()) {
			remove(scienceAppCompile);
		}
	}

	/**
	 * Returns the number of science app compiles.
	 *
	 * @return the number of science app compiles
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

				Query q = session.createQuery(_SQL_COUNT_SCIENCEAPPCOMPILE);

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
	 * Initializes the science app compile persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.science.model.ScienceAppCompile")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ScienceAppCompile>> listenersList = new ArrayList<ModelListener<ScienceAppCompile>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ScienceAppCompile>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ScienceAppCompileImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SCIENCEAPPCOMPILE = "SELECT scienceAppCompile FROM ScienceAppCompile scienceAppCompile";
	private static final String _SQL_COUNT_SCIENCEAPPCOMPILE = "SELECT COUNT(scienceAppCompile) FROM ScienceAppCompile scienceAppCompile";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scienceAppCompile.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ScienceAppCompile exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ScienceAppCompilePersistenceImpl.class);
	private static ScienceAppCompile _nullScienceAppCompile = new ScienceAppCompileImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ScienceAppCompile> toCacheModel() {
				return _nullScienceAppCompileCacheModel;
			}
		};

	private static CacheModel<ScienceAppCompile> _nullScienceAppCompileCacheModel =
		new CacheModel<ScienceAppCompile>() {
			@Override
			public ScienceAppCompile toEntityModel() {
				return _nullScienceAppCompile;
			}
		};
}