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

import com.kisti.osp.NoSuchFileException;
import com.kisti.osp.model.OSPFile;
import com.kisti.osp.model.impl.OSPFileImpl;
import com.kisti.osp.model.impl.OSPFileModelImpl;

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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the o s p file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry h. Seo
 * @see OSPFilePersistence
 * @see OSPFileUtil
 * @generated
 */
public class OSPFilePersistenceImpl extends BasePersistenceImpl<OSPFile>
	implements OSPFilePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OSPFileUtil} to access the o s p file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OSPFileImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OSPFileModelImpl.ENTITY_CACHE_ENABLED,
			OSPFileModelImpl.FINDER_CACHE_ENABLED, OSPFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OSPFileModelImpl.ENTITY_CACHE_ENABLED,
			OSPFileModelImpl.FINDER_CACHE_ENABLED, OSPFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OSPFileModelImpl.ENTITY_CACHE_ENABLED,
			OSPFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public OSPFilePersistenceImpl() {
		setModelClass(OSPFile.class);
	}

	/**
	 * Caches the o s p file in the entity cache if it is enabled.
	 *
	 * @param ospFile the o s p file
	 */
	@Override
	public void cacheResult(OSPFile ospFile) {
		EntityCacheUtil.putResult(OSPFileModelImpl.ENTITY_CACHE_ENABLED,
			OSPFileImpl.class, ospFile.getPrimaryKey(), ospFile);

		ospFile.resetOriginalValues();
	}

	/**
	 * Caches the o s p files in the entity cache if it is enabled.
	 *
	 * @param ospFiles the o s p files
	 */
	@Override
	public void cacheResult(List<OSPFile> ospFiles) {
		for (OSPFile ospFile : ospFiles) {
			if (EntityCacheUtil.getResult(
						OSPFileModelImpl.ENTITY_CACHE_ENABLED,
						OSPFileImpl.class, ospFile.getPrimaryKey()) == null) {
				cacheResult(ospFile);
			}
			else {
				ospFile.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all o s p files.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OSPFileImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OSPFileImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the o s p file.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OSPFile ospFile) {
		EntityCacheUtil.removeResult(OSPFileModelImpl.ENTITY_CACHE_ENABLED,
			OSPFileImpl.class, ospFile.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<OSPFile> ospFiles) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OSPFile ospFile : ospFiles) {
			EntityCacheUtil.removeResult(OSPFileModelImpl.ENTITY_CACHE_ENABLED,
				OSPFileImpl.class, ospFile.getPrimaryKey());
		}
	}

	/**
	 * Creates a new o s p file with the primary key. Does not add the o s p file to the database.
	 *
	 * @param propertyName the primary key for the new o s p file
	 * @return the new o s p file
	 */
	@Override
	public OSPFile create(String propertyName) {
		OSPFile ospFile = new OSPFileImpl();

		ospFile.setNew(true);
		ospFile.setPrimaryKey(propertyName);

		return ospFile;
	}

	/**
	 * Removes the o s p file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param propertyName the primary key of the o s p file
	 * @return the o s p file that was removed
	 * @throws com.kisti.osp.NoSuchFileException if a o s p file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OSPFile remove(String propertyName)
		throws NoSuchFileException, SystemException {
		return remove((Serializable)propertyName);
	}

	/**
	 * Removes the o s p file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the o s p file
	 * @return the o s p file that was removed
	 * @throws com.kisti.osp.NoSuchFileException if a o s p file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OSPFile remove(Serializable primaryKey)
		throws NoSuchFileException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OSPFile ospFile = (OSPFile)session.get(OSPFileImpl.class, primaryKey);

			if (ospFile == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ospFile);
		}
		catch (NoSuchFileException nsee) {
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
	protected OSPFile removeImpl(OSPFile ospFile) throws SystemException {
		ospFile = toUnwrappedModel(ospFile);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ospFile)) {
				ospFile = (OSPFile)session.get(OSPFileImpl.class,
						ospFile.getPrimaryKeyObj());
			}

			if (ospFile != null) {
				session.delete(ospFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ospFile != null) {
			clearCache(ospFile);
		}

		return ospFile;
	}

	@Override
	public OSPFile updateImpl(com.kisti.osp.model.OSPFile ospFile)
		throws SystemException {
		ospFile = toUnwrappedModel(ospFile);

		boolean isNew = ospFile.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ospFile.isNew()) {
				session.save(ospFile);

				ospFile.setNew(false);
			}
			else {
				session.merge(ospFile);
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

		EntityCacheUtil.putResult(OSPFileModelImpl.ENTITY_CACHE_ENABLED,
			OSPFileImpl.class, ospFile.getPrimaryKey(), ospFile);

		return ospFile;
	}

	protected OSPFile toUnwrappedModel(OSPFile ospFile) {
		if (ospFile instanceof OSPFileImpl) {
			return ospFile;
		}

		OSPFileImpl ospFileImpl = new OSPFileImpl();

		ospFileImpl.setNew(ospFile.isNew());
		ospFileImpl.setPrimaryKey(ospFile.getPrimaryKey());

		ospFileImpl.setPropertyName(ospFile.getPropertyName());
		ospFileImpl.setPropertyValue(ospFile.getPropertyValue());

		return ospFileImpl;
	}

	/**
	 * Returns the o s p file with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the o s p file
	 * @return the o s p file
	 * @throws com.kisti.osp.NoSuchFileException if a o s p file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OSPFile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFileException, SystemException {
		OSPFile ospFile = fetchByPrimaryKey(primaryKey);

		if (ospFile == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ospFile;
	}

	/**
	 * Returns the o s p file with the primary key or throws a {@link com.kisti.osp.NoSuchFileException} if it could not be found.
	 *
	 * @param propertyName the primary key of the o s p file
	 * @return the o s p file
	 * @throws com.kisti.osp.NoSuchFileException if a o s p file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OSPFile findByPrimaryKey(String propertyName)
		throws NoSuchFileException, SystemException {
		return findByPrimaryKey((Serializable)propertyName);
	}

	/**
	 * Returns the o s p file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o s p file
	 * @return the o s p file, or <code>null</code> if a o s p file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OSPFile fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		OSPFile ospFile = (OSPFile)EntityCacheUtil.getResult(OSPFileModelImpl.ENTITY_CACHE_ENABLED,
				OSPFileImpl.class, primaryKey);

		if (ospFile == _nullOSPFile) {
			return null;
		}

		if (ospFile == null) {
			Session session = null;

			try {
				session = openSession();

				ospFile = (OSPFile)session.get(OSPFileImpl.class, primaryKey);

				if (ospFile != null) {
					cacheResult(ospFile);
				}
				else {
					EntityCacheUtil.putResult(OSPFileModelImpl.ENTITY_CACHE_ENABLED,
						OSPFileImpl.class, primaryKey, _nullOSPFile);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(OSPFileModelImpl.ENTITY_CACHE_ENABLED,
					OSPFileImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ospFile;
	}

	/**
	 * Returns the o s p file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param propertyName the primary key of the o s p file
	 * @return the o s p file, or <code>null</code> if a o s p file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OSPFile fetchByPrimaryKey(String propertyName)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)propertyName);
	}

	/**
	 * Returns all the o s p files.
	 *
	 * @return the o s p files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OSPFile> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o s p files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.OSPFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o s p files
	 * @param end the upper bound of the range of o s p files (not inclusive)
	 * @return the range of o s p files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OSPFile> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the o s p files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.OSPFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o s p files
	 * @param end the upper bound of the range of o s p files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of o s p files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OSPFile> findAll(int start, int end,
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

		List<OSPFile> list = (List<OSPFile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OSPFILE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OSPFILE;

				if (pagination) {
					sql = sql.concat(OSPFileModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OSPFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<OSPFile>(list);
				}
				else {
					list = (List<OSPFile>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the o s p files from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (OSPFile ospFile : findAll()) {
			remove(ospFile);
		}
	}

	/**
	 * Returns the number of o s p files.
	 *
	 * @return the number of o s p files
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

				Query q = session.createQuery(_SQL_COUNT_OSPFILE);

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
	 * Initializes the o s p file persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.osp.model.OSPFile")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OSPFile>> listenersList = new ArrayList<ModelListener<OSPFile>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OSPFile>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(OSPFileImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_OSPFILE = "SELECT ospFile FROM OSPFile ospFile";
	private static final String _SQL_COUNT_OSPFILE = "SELECT COUNT(ospFile) FROM OSPFile ospFile";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ospFile.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OSPFile exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OSPFilePersistenceImpl.class);
	private static OSPFile _nullOSPFile = new OSPFileImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OSPFile> toCacheModel() {
				return _nullOSPFileCacheModel;
			}
		};

	private static CacheModel<OSPFile> _nullOSPFileCacheModel = new CacheModel<OSPFile>() {
			@Override
			public OSPFile toEntityModel() {
				return _nullOSPFile;
			}
		};
}