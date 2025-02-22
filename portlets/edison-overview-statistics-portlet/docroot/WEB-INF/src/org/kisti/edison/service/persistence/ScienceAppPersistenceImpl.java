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

import org.kisti.edison.NoSuchScienceAppException;
import org.kisti.edison.model.ScienceApp;
import org.kisti.edison.model.impl.ScienceAppImpl;
import org.kisti.edison.model.impl.ScienceAppModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the science app service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ScienceAppPersistence
 * @see ScienceAppUtil
 * @generated
 */
public class ScienceAppPersistenceImpl extends BasePersistenceImpl<ScienceApp>
	implements ScienceAppPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScienceAppUtil} to access the science app persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScienceAppImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ScienceAppPersistenceImpl() {
		setModelClass(ScienceApp.class);
	}

	/**
	 * Caches the science app in the entity cache if it is enabled.
	 *
	 * @param scienceApp the science app
	 */
	@Override
	public void cacheResult(ScienceApp scienceApp) {
		EntityCacheUtil.putResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppImpl.class, scienceApp.getPrimaryKey(), scienceApp);

		scienceApp.resetOriginalValues();
	}

	/**
	 * Caches the science apps in the entity cache if it is enabled.
	 *
	 * @param scienceApps the science apps
	 */
	@Override
	public void cacheResult(List<ScienceApp> scienceApps) {
		for (ScienceApp scienceApp : scienceApps) {
			if (EntityCacheUtil.getResult(
						ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppImpl.class, scienceApp.getPrimaryKey()) == null) {
				cacheResult(scienceApp);
			}
			else {
				scienceApp.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all science apps.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ScienceAppImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ScienceAppImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the science app.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ScienceApp scienceApp) {
		EntityCacheUtil.removeResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppImpl.class, scienceApp.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ScienceApp> scienceApps) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ScienceApp scienceApp : scienceApps) {
			EntityCacheUtil.removeResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppImpl.class, scienceApp.getPrimaryKey());
		}
	}

	/**
	 * Creates a new science app with the primary key. Does not add the science app to the database.
	 *
	 * @param createDate the primary key for the new science app
	 * @return the new science app
	 */
	@Override
	public ScienceApp create(String createDate) {
		ScienceApp scienceApp = new ScienceAppImpl();

		scienceApp.setNew(true);
		scienceApp.setPrimaryKey(createDate);

		return scienceApp;
	}

	/**
	 * Removes the science app with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param createDate the primary key of the science app
	 * @return the science app that was removed
	 * @throws org.kisti.edison.NoSuchScienceAppException if a science app with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceApp remove(String createDate)
		throws NoSuchScienceAppException, SystemException {
		return remove((Serializable)createDate);
	}

	/**
	 * Removes the science app with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the science app
	 * @return the science app that was removed
	 * @throws org.kisti.edison.NoSuchScienceAppException if a science app with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceApp remove(Serializable primaryKey)
		throws NoSuchScienceAppException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ScienceApp scienceApp = (ScienceApp)session.get(ScienceAppImpl.class,
					primaryKey);

			if (scienceApp == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScienceAppException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scienceApp);
		}
		catch (NoSuchScienceAppException nsee) {
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
	protected ScienceApp removeImpl(ScienceApp scienceApp)
		throws SystemException {
		scienceApp = toUnwrappedModel(scienceApp);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scienceApp)) {
				scienceApp = (ScienceApp)session.get(ScienceAppImpl.class,
						scienceApp.getPrimaryKeyObj());
			}

			if (scienceApp != null) {
				session.delete(scienceApp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scienceApp != null) {
			clearCache(scienceApp);
		}

		return scienceApp;
	}

	@Override
	public ScienceApp updateImpl(org.kisti.edison.model.ScienceApp scienceApp)
		throws SystemException {
		scienceApp = toUnwrappedModel(scienceApp);

		boolean isNew = scienceApp.isNew();

		Session session = null;

		try {
			session = openSession();

			if (scienceApp.isNew()) {
				session.save(scienceApp);

				scienceApp.setNew(false);
			}
			else {
				session.merge(scienceApp);
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

		EntityCacheUtil.putResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppImpl.class, scienceApp.getPrimaryKey(), scienceApp);

		return scienceApp;
	}

	protected ScienceApp toUnwrappedModel(ScienceApp scienceApp) {
		if (scienceApp instanceof ScienceAppImpl) {
			return scienceApp;
		}

		ScienceAppImpl scienceAppImpl = new ScienceAppImpl();

		scienceAppImpl.setNew(scienceApp.isNew());
		scienceAppImpl.setPrimaryKey(scienceApp.getPrimaryKey());

		scienceAppImpl.setCreateDate(scienceApp.getCreateDate());
		scienceAppImpl.setCnt(scienceApp.getCnt());

		return scienceAppImpl;
	}

	/**
	 * Returns the science app with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app
	 * @return the science app
	 * @throws org.kisti.edison.NoSuchScienceAppException if a science app with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceApp findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScienceAppException, SystemException {
		ScienceApp scienceApp = fetchByPrimaryKey(primaryKey);

		if (scienceApp == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScienceAppException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scienceApp;
	}

	/**
	 * Returns the science app with the primary key or throws a {@link org.kisti.edison.NoSuchScienceAppException} if it could not be found.
	 *
	 * @param createDate the primary key of the science app
	 * @return the science app
	 * @throws org.kisti.edison.NoSuchScienceAppException if a science app with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceApp findByPrimaryKey(String createDate)
		throws NoSuchScienceAppException, SystemException {
		return findByPrimaryKey((Serializable)createDate);
	}

	/**
	 * Returns the science app with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app
	 * @return the science app, or <code>null</code> if a science app with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceApp fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ScienceApp scienceApp = (ScienceApp)EntityCacheUtil.getResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppImpl.class, primaryKey);

		if (scienceApp == _nullScienceApp) {
			return null;
		}

		if (scienceApp == null) {
			Session session = null;

			try {
				session = openSession();

				scienceApp = (ScienceApp)session.get(ScienceAppImpl.class,
						primaryKey);

				if (scienceApp != null) {
					cacheResult(scienceApp);
				}
				else {
					EntityCacheUtil.putResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppImpl.class, primaryKey, _nullScienceApp);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
					ScienceAppImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scienceApp;
	}

	/**
	 * Returns the science app with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param createDate the primary key of the science app
	 * @return the science app, or <code>null</code> if a science app with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceApp fetchByPrimaryKey(String createDate)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)createDate);
	}

	/**
	 * Returns all the science apps.
	 *
	 * @return the science apps
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceApp> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of science apps
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceApp> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of science apps
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceApp> findAll(int start, int end,
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

		List<ScienceApp> list = (List<ScienceApp>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SCIENCEAPP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCIENCEAPP;

				if (pagination) {
					sql = sql.concat(ScienceAppModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ScienceApp>(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the science apps from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ScienceApp scienceApp : findAll()) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps.
	 *
	 * @return the number of science apps
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

				Query q = session.createQuery(_SQL_COUNT_SCIENCEAPP);

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
	 * Initializes the science app persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.ScienceApp")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ScienceApp>> listenersList = new ArrayList<ModelListener<ScienceApp>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ScienceApp>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ScienceAppImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SCIENCEAPP = "SELECT scienceApp FROM ScienceApp scienceApp";
	private static final String _SQL_COUNT_SCIENCEAPP = "SELECT COUNT(scienceApp) FROM ScienceApp scienceApp";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scienceApp.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ScienceApp exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ScienceAppPersistenceImpl.class);
	private static ScienceApp _nullScienceApp = new ScienceAppImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ScienceApp> toCacheModel() {
				return _nullScienceAppCacheModel;
			}
		};

	private static CacheModel<ScienceApp> _nullScienceAppCacheModel = new CacheModel<ScienceApp>() {
			@Override
			public ScienceApp toEntityModel() {
				return _nullScienceApp;
			}
		};
}