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

package com.kisti.osp.service.persistence;

import com.kisti.osp.NoSuchSystemPropertiesException;
import com.kisti.osp.model.SystemProperties;
import com.kisti.osp.model.impl.SystemPropertiesImpl;
import com.kisti.osp.model.impl.SystemPropertiesModelImpl;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the system properties service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry h. Seo
 * @see SystemPropertiesPersistence
 * @see SystemPropertiesUtil
 * @generated
 */
public class SystemPropertiesPersistenceImpl extends BasePersistenceImpl<SystemProperties>
	implements SystemPropertiesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SystemPropertiesUtil} to access the system properties persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SystemPropertiesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
			SystemPropertiesModelImpl.FINDER_CACHE_ENABLED,
			SystemPropertiesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
			SystemPropertiesModelImpl.FINDER_CACHE_ENABLED,
			SystemPropertiesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
			SystemPropertiesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SystemPropertiesPersistenceImpl() {
		setModelClass(SystemProperties.class);
	}

	/**
	 * Caches the system properties in the entity cache if it is enabled.
	 *
	 * @param systemProperties the system properties
	 */
	@Override
	public void cacheResult(SystemProperties systemProperties) {
		EntityCacheUtil.putResult(SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
			SystemPropertiesImpl.class, systemProperties.getPrimaryKey(),
			systemProperties);

		systemProperties.resetOriginalValues();
	}

	/**
	 * Caches the system propertieses in the entity cache if it is enabled.
	 *
	 * @param systemPropertieses the system propertieses
	 */
	@Override
	public void cacheResult(List<SystemProperties> systemPropertieses) {
		for (SystemProperties systemProperties : systemPropertieses) {
			if (EntityCacheUtil.getResult(
						SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
						SystemPropertiesImpl.class,
						systemProperties.getPrimaryKey()) == null) {
				cacheResult(systemProperties);
			}
			else {
				systemProperties.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all system propertieses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SystemPropertiesImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SystemPropertiesImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the system properties.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SystemProperties systemProperties) {
		EntityCacheUtil.removeResult(SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
			SystemPropertiesImpl.class, systemProperties.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SystemProperties> systemPropertieses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SystemProperties systemProperties : systemPropertieses) {
			EntityCacheUtil.removeResult(SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
				SystemPropertiesImpl.class, systemProperties.getPrimaryKey());
		}
	}

	/**
	 * Creates a new system properties with the primary key. Does not add the system properties to the database.
	 *
	 * @param propertyName the primary key for the new system properties
	 * @return the new system properties
	 */
	@Override
	public SystemProperties create(String propertyName) {
		SystemProperties systemProperties = new SystemPropertiesImpl();

		systemProperties.setNew(true);
		systemProperties.setPrimaryKey(propertyName);

		return systemProperties;
	}

	/**
	 * Removes the system properties with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param propertyName the primary key of the system properties
	 * @return the system properties that was removed
	 * @throws com.kisti.osp.NoSuchSystemPropertiesException if a system properties with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SystemProperties remove(String propertyName)
		throws NoSuchSystemPropertiesException, SystemException {
		return remove((Serializable)propertyName);
	}

	/**
	 * Removes the system properties with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the system properties
	 * @return the system properties that was removed
	 * @throws com.kisti.osp.NoSuchSystemPropertiesException if a system properties with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SystemProperties remove(Serializable primaryKey)
		throws NoSuchSystemPropertiesException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SystemProperties systemProperties = (SystemProperties)session.get(SystemPropertiesImpl.class,
					primaryKey);

			if (systemProperties == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSystemPropertiesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(systemProperties);
		}
		catch (NoSuchSystemPropertiesException nsee) {
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
	protected SystemProperties removeImpl(SystemProperties systemProperties)
		throws SystemException {
		systemProperties = toUnwrappedModel(systemProperties);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(systemProperties)) {
				systemProperties = (SystemProperties)session.get(SystemPropertiesImpl.class,
						systemProperties.getPrimaryKeyObj());
			}

			if (systemProperties != null) {
				session.delete(systemProperties);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (systemProperties != null) {
			clearCache(systemProperties);
		}

		return systemProperties;
	}

	@Override
	public SystemProperties updateImpl(
		com.kisti.osp.model.SystemProperties systemProperties)
		throws SystemException {
		systemProperties = toUnwrappedModel(systemProperties);

		boolean isNew = systemProperties.isNew();

		Session session = null;

		try {
			session = openSession();

			if (systemProperties.isNew()) {
				session.save(systemProperties);

				systemProperties.setNew(false);
			}
			else {
				session.merge(systemProperties);
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

		EntityCacheUtil.putResult(SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
			SystemPropertiesImpl.class, systemProperties.getPrimaryKey(),
			systemProperties);

		return systemProperties;
	}

	protected SystemProperties toUnwrappedModel(
		SystemProperties systemProperties) {
		if (systemProperties instanceof SystemPropertiesImpl) {
			return systemProperties;
		}

		SystemPropertiesImpl systemPropertiesImpl = new SystemPropertiesImpl();

		systemPropertiesImpl.setNew(systemProperties.isNew());
		systemPropertiesImpl.setPrimaryKey(systemProperties.getPrimaryKey());

		systemPropertiesImpl.setPropertyName(systemProperties.getPropertyName());
		systemPropertiesImpl.setPropertyValue(systemProperties.getPropertyValue());
		systemPropertiesImpl.setActive(systemProperties.isActive());

		return systemPropertiesImpl;
	}

	/**
	 * Returns the system properties with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the system properties
	 * @return the system properties
	 * @throws com.kisti.osp.NoSuchSystemPropertiesException if a system properties with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SystemProperties findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSystemPropertiesException, SystemException {
		SystemProperties systemProperties = fetchByPrimaryKey(primaryKey);

		if (systemProperties == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSystemPropertiesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return systemProperties;
	}

	/**
	 * Returns the system properties with the primary key or throws a {@link com.kisti.osp.NoSuchSystemPropertiesException} if it could not be found.
	 *
	 * @param propertyName the primary key of the system properties
	 * @return the system properties
	 * @throws com.kisti.osp.NoSuchSystemPropertiesException if a system properties with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SystemProperties findByPrimaryKey(String propertyName)
		throws NoSuchSystemPropertiesException, SystemException {
		return findByPrimaryKey((Serializable)propertyName);
	}

	/**
	 * Returns the system properties with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the system properties
	 * @return the system properties, or <code>null</code> if a system properties with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SystemProperties fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SystemProperties systemProperties = (SystemProperties)EntityCacheUtil.getResult(SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
				SystemPropertiesImpl.class, primaryKey);

		if (systemProperties == _nullSystemProperties) {
			return null;
		}

		if (systemProperties == null) {
			Session session = null;

			try {
				session = openSession();

				systemProperties = (SystemProperties)session.get(SystemPropertiesImpl.class,
						primaryKey);

				if (systemProperties != null) {
					cacheResult(systemProperties);
				}
				else {
					EntityCacheUtil.putResult(SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
						SystemPropertiesImpl.class, primaryKey,
						_nullSystemProperties);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SystemPropertiesModelImpl.ENTITY_CACHE_ENABLED,
					SystemPropertiesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return systemProperties;
	}

	/**
	 * Returns the system properties with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param propertyName the primary key of the system properties
	 * @return the system properties, or <code>null</code> if a system properties with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SystemProperties fetchByPrimaryKey(String propertyName)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)propertyName);
	}

	/**
	 * Returns all the system propertieses.
	 *
	 * @return the system propertieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SystemProperties> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.SystemPropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of system propertieses
	 * @param end the upper bound of the range of system propertieses (not inclusive)
	 * @return the range of system propertieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SystemProperties> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the system propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.SystemPropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of system propertieses
	 * @param end the upper bound of the range of system propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of system propertieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SystemProperties> findAll(int start, int end,
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

		List<SystemProperties> list = (List<SystemProperties>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SYSTEMPROPERTIES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SYSTEMPROPERTIES;

				if (pagination) {
					sql = sql.concat(SystemPropertiesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SystemProperties>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SystemProperties>(list);
				}
				else {
					list = (List<SystemProperties>)QueryUtil.list(q,
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
	 * Removes all the system propertieses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SystemProperties systemProperties : findAll()) {
			remove(systemProperties);
		}
	}

	/**
	 * Returns the number of system propertieses.
	 *
	 * @return the number of system propertieses
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

				Query q = session.createQuery(_SQL_COUNT_SYSTEMPROPERTIES);

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
	 * Initializes the system properties persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.osp.model.SystemProperties")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SystemProperties>> listenersList = new ArrayList<ModelListener<SystemProperties>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SystemProperties>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SystemPropertiesImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SYSTEMPROPERTIES = "SELECT systemProperties FROM SystemProperties systemProperties";
	private static final String _SQL_COUNT_SYSTEMPROPERTIES = "SELECT COUNT(systemProperties) FROM SystemProperties systemProperties";
	private static final String _ORDER_BY_ENTITY_ALIAS = "systemProperties.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SystemProperties exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SystemPropertiesPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"active"
			});
	private static SystemProperties _nullSystemProperties = new SystemPropertiesImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SystemProperties> toCacheModel() {
				return _nullSystemPropertiesCacheModel;
			}
		};

	private static CacheModel<SystemProperties> _nullSystemPropertiesCacheModel = new CacheModel<SystemProperties>() {
			@Override
			public SystemProperties toEntityModel() {
				return _nullSystemProperties;
			}
		};
}