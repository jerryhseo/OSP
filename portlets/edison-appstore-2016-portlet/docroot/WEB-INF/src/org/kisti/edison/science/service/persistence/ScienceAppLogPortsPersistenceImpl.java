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

import org.kisti.edison.science.NoSuchScienceAppLogPortsException;
import org.kisti.edison.science.model.ScienceAppLogPorts;
import org.kisti.edison.science.model.impl.ScienceAppLogPortsImpl;
import org.kisti.edison.science.model.impl.ScienceAppLogPortsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the science app log ports service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ScienceAppLogPortsPersistence
 * @see ScienceAppLogPortsUtil
 * @generated
 */
public class ScienceAppLogPortsPersistenceImpl extends BasePersistenceImpl<ScienceAppLogPorts>
	implements ScienceAppLogPortsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScienceAppLogPortsUtil} to access the science app log ports persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScienceAppLogPortsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppLogPortsModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppLogPortsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppLogPortsModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppLogPortsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppLogPortsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ScienceAppLogPortsPersistenceImpl() {
		setModelClass(ScienceAppLogPorts.class);
	}

	/**
	 * Caches the science app log ports in the entity cache if it is enabled.
	 *
	 * @param scienceAppLogPorts the science app log ports
	 */
	@Override
	public void cacheResult(ScienceAppLogPorts scienceAppLogPorts) {
		EntityCacheUtil.putResult(ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppLogPortsImpl.class, scienceAppLogPorts.getPrimaryKey(),
			scienceAppLogPorts);

		scienceAppLogPorts.resetOriginalValues();
	}

	/**
	 * Caches the science app log portses in the entity cache if it is enabled.
	 *
	 * @param scienceAppLogPortses the science app log portses
	 */
	@Override
	public void cacheResult(List<ScienceAppLogPorts> scienceAppLogPortses) {
		for (ScienceAppLogPorts scienceAppLogPorts : scienceAppLogPortses) {
			if (EntityCacheUtil.getResult(
						ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppLogPortsImpl.class,
						scienceAppLogPorts.getPrimaryKey()) == null) {
				cacheResult(scienceAppLogPorts);
			}
			else {
				scienceAppLogPorts.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all science app log portses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ScienceAppLogPortsImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ScienceAppLogPortsImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the science app log ports.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ScienceAppLogPorts scienceAppLogPorts) {
		EntityCacheUtil.removeResult(ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppLogPortsImpl.class, scienceAppLogPorts.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ScienceAppLogPorts> scienceAppLogPortses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ScienceAppLogPorts scienceAppLogPorts : scienceAppLogPortses) {
			EntityCacheUtil.removeResult(ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppLogPortsImpl.class, scienceAppLogPorts.getPrimaryKey());
		}
	}

	/**
	 * Creates a new science app log ports with the primary key. Does not add the science app log ports to the database.
	 *
	 * @param scienceAppId the primary key for the new science app log ports
	 * @return the new science app log ports
	 */
	@Override
	public ScienceAppLogPorts create(long scienceAppId) {
		ScienceAppLogPorts scienceAppLogPorts = new ScienceAppLogPortsImpl();

		scienceAppLogPorts.setNew(true);
		scienceAppLogPorts.setPrimaryKey(scienceAppId);

		return scienceAppLogPorts;
	}

	/**
	 * Removes the science app log ports with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppId the primary key of the science app log ports
	 * @return the science app log ports that was removed
	 * @throws org.kisti.edison.science.NoSuchScienceAppLogPortsException if a science app log ports with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppLogPorts remove(long scienceAppId)
		throws NoSuchScienceAppLogPortsException, SystemException {
		return remove((Serializable)scienceAppId);
	}

	/**
	 * Removes the science app log ports with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the science app log ports
	 * @return the science app log ports that was removed
	 * @throws org.kisti.edison.science.NoSuchScienceAppLogPortsException if a science app log ports with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppLogPorts remove(Serializable primaryKey)
		throws NoSuchScienceAppLogPortsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ScienceAppLogPorts scienceAppLogPorts = (ScienceAppLogPorts)session.get(ScienceAppLogPortsImpl.class,
					primaryKey);

			if (scienceAppLogPorts == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScienceAppLogPortsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scienceAppLogPorts);
		}
		catch (NoSuchScienceAppLogPortsException nsee) {
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
	protected ScienceAppLogPorts removeImpl(
		ScienceAppLogPorts scienceAppLogPorts) throws SystemException {
		scienceAppLogPorts = toUnwrappedModel(scienceAppLogPorts);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scienceAppLogPorts)) {
				scienceAppLogPorts = (ScienceAppLogPorts)session.get(ScienceAppLogPortsImpl.class,
						scienceAppLogPorts.getPrimaryKeyObj());
			}

			if (scienceAppLogPorts != null) {
				session.delete(scienceAppLogPorts);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scienceAppLogPorts != null) {
			clearCache(scienceAppLogPorts);
		}

		return scienceAppLogPorts;
	}

	@Override
	public ScienceAppLogPorts updateImpl(
		org.kisti.edison.science.model.ScienceAppLogPorts scienceAppLogPorts)
		throws SystemException {
		scienceAppLogPorts = toUnwrappedModel(scienceAppLogPorts);

		boolean isNew = scienceAppLogPorts.isNew();

		Session session = null;

		try {
			session = openSession();

			if (scienceAppLogPorts.isNew()) {
				session.save(scienceAppLogPorts);

				scienceAppLogPorts.setNew(false);
			}
			else {
				session.merge(scienceAppLogPorts);
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

		EntityCacheUtil.putResult(ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppLogPortsImpl.class, scienceAppLogPorts.getPrimaryKey(),
			scienceAppLogPorts);

		return scienceAppLogPorts;
	}

	protected ScienceAppLogPorts toUnwrappedModel(
		ScienceAppLogPorts scienceAppLogPorts) {
		if (scienceAppLogPorts instanceof ScienceAppLogPortsImpl) {
			return scienceAppLogPorts;
		}

		ScienceAppLogPortsImpl scienceAppLogPortsImpl = new ScienceAppLogPortsImpl();

		scienceAppLogPortsImpl.setNew(scienceAppLogPorts.isNew());
		scienceAppLogPortsImpl.setPrimaryKey(scienceAppLogPorts.getPrimaryKey());

		scienceAppLogPortsImpl.setScienceAppId(scienceAppLogPorts.getScienceAppId());
		scienceAppLogPortsImpl.setLogPorts(scienceAppLogPorts.getLogPorts());

		return scienceAppLogPortsImpl;
	}

	/**
	 * Returns the science app log ports with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app log ports
	 * @return the science app log ports
	 * @throws org.kisti.edison.science.NoSuchScienceAppLogPortsException if a science app log ports with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppLogPorts findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScienceAppLogPortsException, SystemException {
		ScienceAppLogPorts scienceAppLogPorts = fetchByPrimaryKey(primaryKey);

		if (scienceAppLogPorts == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScienceAppLogPortsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scienceAppLogPorts;
	}

	/**
	 * Returns the science app log ports with the primary key or throws a {@link org.kisti.edison.science.NoSuchScienceAppLogPortsException} if it could not be found.
	 *
	 * @param scienceAppId the primary key of the science app log ports
	 * @return the science app log ports
	 * @throws org.kisti.edison.science.NoSuchScienceAppLogPortsException if a science app log ports with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppLogPorts findByPrimaryKey(long scienceAppId)
		throws NoSuchScienceAppLogPortsException, SystemException {
		return findByPrimaryKey((Serializable)scienceAppId);
	}

	/**
	 * Returns the science app log ports with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app log ports
	 * @return the science app log ports, or <code>null</code> if a science app log ports with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppLogPorts fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ScienceAppLogPorts scienceAppLogPorts = (ScienceAppLogPorts)EntityCacheUtil.getResult(ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppLogPortsImpl.class, primaryKey);

		if (scienceAppLogPorts == _nullScienceAppLogPorts) {
			return null;
		}

		if (scienceAppLogPorts == null) {
			Session session = null;

			try {
				session = openSession();

				scienceAppLogPorts = (ScienceAppLogPorts)session.get(ScienceAppLogPortsImpl.class,
						primaryKey);

				if (scienceAppLogPorts != null) {
					cacheResult(scienceAppLogPorts);
				}
				else {
					EntityCacheUtil.putResult(ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppLogPortsImpl.class, primaryKey,
						_nullScienceAppLogPorts);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ScienceAppLogPortsModelImpl.ENTITY_CACHE_ENABLED,
					ScienceAppLogPortsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scienceAppLogPorts;
	}

	/**
	 * Returns the science app log ports with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppId the primary key of the science app log ports
	 * @return the science app log ports, or <code>null</code> if a science app log ports with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppLogPorts fetchByPrimaryKey(long scienceAppId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)scienceAppId);
	}

	/**
	 * Returns all the science app log portses.
	 *
	 * @return the science app log portses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppLogPorts> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science app log portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppLogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science app log portses
	 * @param end the upper bound of the range of science app log portses (not inclusive)
	 * @return the range of science app log portses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppLogPorts> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the science app log portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppLogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science app log portses
	 * @param end the upper bound of the range of science app log portses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of science app log portses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppLogPorts> findAll(int start, int end,
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

		List<ScienceAppLogPorts> list = (List<ScienceAppLogPorts>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SCIENCEAPPLOGPORTS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCIENCEAPPLOGPORTS;

				if (pagination) {
					sql = sql.concat(ScienceAppLogPortsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ScienceAppLogPorts>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ScienceAppLogPorts>(list);
				}
				else {
					list = (List<ScienceAppLogPorts>)QueryUtil.list(q,
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
	 * Removes all the science app log portses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ScienceAppLogPorts scienceAppLogPorts : findAll()) {
			remove(scienceAppLogPorts);
		}
	}

	/**
	 * Returns the number of science app log portses.
	 *
	 * @return the number of science app log portses
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

				Query q = session.createQuery(_SQL_COUNT_SCIENCEAPPLOGPORTS);

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
	 * Initializes the science app log ports persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.science.model.ScienceAppLogPorts")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ScienceAppLogPorts>> listenersList = new ArrayList<ModelListener<ScienceAppLogPorts>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ScienceAppLogPorts>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ScienceAppLogPortsImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SCIENCEAPPLOGPORTS = "SELECT scienceAppLogPorts FROM ScienceAppLogPorts scienceAppLogPorts";
	private static final String _SQL_COUNT_SCIENCEAPPLOGPORTS = "SELECT COUNT(scienceAppLogPorts) FROM ScienceAppLogPorts scienceAppLogPorts";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scienceAppLogPorts.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ScienceAppLogPorts exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ScienceAppLogPortsPersistenceImpl.class);
	private static ScienceAppLogPorts _nullScienceAppLogPorts = new ScienceAppLogPortsImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ScienceAppLogPorts> toCacheModel() {
				return _nullScienceAppLogPortsCacheModel;
			}
		};

	private static CacheModel<ScienceAppLogPorts> _nullScienceAppLogPortsCacheModel =
		new CacheModel<ScienceAppLogPorts>() {
			@Override
			public ScienceAppLogPorts toEntityModel() {
				return _nullScienceAppLogPorts;
			}
		};
}