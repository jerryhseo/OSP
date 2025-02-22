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

package org.kisti.edison.simulation.service.persistence;

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

import org.kisti.edison.simulation.NoSuchBatchMonitoringException;
import org.kisti.edison.simulation.model.BatchMonitoring;
import org.kisti.edison.simulation.model.impl.BatchMonitoringImpl;
import org.kisti.edison.simulation.model.impl.BatchMonitoringModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the batch monitoring service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see BatchMonitoringPersistence
 * @see BatchMonitoringUtil
 * @generated
 */
public class BatchMonitoringPersistenceImpl extends BasePersistenceImpl<BatchMonitoring>
	implements BatchMonitoringPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BatchMonitoringUtil} to access the batch monitoring persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BatchMonitoringImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
			BatchMonitoringModelImpl.FINDER_CACHE_ENABLED,
			BatchMonitoringImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
			BatchMonitoringModelImpl.FINDER_CACHE_ENABLED,
			BatchMonitoringImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
			BatchMonitoringModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public BatchMonitoringPersistenceImpl() {
		setModelClass(BatchMonitoring.class);
	}

	/**
	 * Caches the batch monitoring in the entity cache if it is enabled.
	 *
	 * @param batchMonitoring the batch monitoring
	 */
	@Override
	public void cacheResult(BatchMonitoring batchMonitoring) {
		EntityCacheUtil.putResult(BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
			BatchMonitoringImpl.class, batchMonitoring.getPrimaryKey(),
			batchMonitoring);

		batchMonitoring.resetOriginalValues();
	}

	/**
	 * Caches the batch monitorings in the entity cache if it is enabled.
	 *
	 * @param batchMonitorings the batch monitorings
	 */
	@Override
	public void cacheResult(List<BatchMonitoring> batchMonitorings) {
		for (BatchMonitoring batchMonitoring : batchMonitorings) {
			if (EntityCacheUtil.getResult(
						BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
						BatchMonitoringImpl.class,
						batchMonitoring.getPrimaryKey()) == null) {
				cacheResult(batchMonitoring);
			}
			else {
				batchMonitoring.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all batch monitorings.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BatchMonitoringImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BatchMonitoringImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the batch monitoring.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BatchMonitoring batchMonitoring) {
		EntityCacheUtil.removeResult(BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
			BatchMonitoringImpl.class, batchMonitoring.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BatchMonitoring> batchMonitorings) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BatchMonitoring batchMonitoring : batchMonitorings) {
			EntityCacheUtil.removeResult(BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
				BatchMonitoringImpl.class, batchMonitoring.getPrimaryKey());
		}
	}

	/**
	 * Creates a new batch monitoring with the primary key. Does not add the batch monitoring to the database.
	 *
	 * @param batSeqNo the primary key for the new batch monitoring
	 * @return the new batch monitoring
	 */
	@Override
	public BatchMonitoring create(long batSeqNo) {
		BatchMonitoring batchMonitoring = new BatchMonitoringImpl();

		batchMonitoring.setNew(true);
		batchMonitoring.setPrimaryKey(batSeqNo);

		return batchMonitoring;
	}

	/**
	 * Removes the batch monitoring with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param batSeqNo the primary key of the batch monitoring
	 * @return the batch monitoring that was removed
	 * @throws org.kisti.edison.simulation.NoSuchBatchMonitoringException if a batch monitoring with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BatchMonitoring remove(long batSeqNo)
		throws NoSuchBatchMonitoringException, SystemException {
		return remove((Serializable)batSeqNo);
	}

	/**
	 * Removes the batch monitoring with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the batch monitoring
	 * @return the batch monitoring that was removed
	 * @throws org.kisti.edison.simulation.NoSuchBatchMonitoringException if a batch monitoring with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BatchMonitoring remove(Serializable primaryKey)
		throws NoSuchBatchMonitoringException, SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchMonitoring batchMonitoring = (BatchMonitoring)session.get(BatchMonitoringImpl.class,
					primaryKey);

			if (batchMonitoring == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBatchMonitoringException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(batchMonitoring);
		}
		catch (NoSuchBatchMonitoringException nsee) {
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
	protected BatchMonitoring removeImpl(BatchMonitoring batchMonitoring)
		throws SystemException {
		batchMonitoring = toUnwrappedModel(batchMonitoring);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(batchMonitoring)) {
				batchMonitoring = (BatchMonitoring)session.get(BatchMonitoringImpl.class,
						batchMonitoring.getPrimaryKeyObj());
			}

			if (batchMonitoring != null) {
				session.delete(batchMonitoring);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (batchMonitoring != null) {
			clearCache(batchMonitoring);
		}

		return batchMonitoring;
	}

	@Override
	public BatchMonitoring updateImpl(
		org.kisti.edison.simulation.model.BatchMonitoring batchMonitoring)
		throws SystemException {
		batchMonitoring = toUnwrappedModel(batchMonitoring);

		boolean isNew = batchMonitoring.isNew();

		Session session = null;

		try {
			session = openSession();

			if (batchMonitoring.isNew()) {
				session.save(batchMonitoring);

				batchMonitoring.setNew(false);
			}
			else {
				session.merge(batchMonitoring);
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

		EntityCacheUtil.putResult(BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
			BatchMonitoringImpl.class, batchMonitoring.getPrimaryKey(),
			batchMonitoring);

		return batchMonitoring;
	}

	protected BatchMonitoring toUnwrappedModel(BatchMonitoring batchMonitoring) {
		if (batchMonitoring instanceof BatchMonitoringImpl) {
			return batchMonitoring;
		}

		BatchMonitoringImpl batchMonitoringImpl = new BatchMonitoringImpl();

		batchMonitoringImpl.setNew(batchMonitoring.isNew());
		batchMonitoringImpl.setPrimaryKey(batchMonitoring.getPrimaryKey());

		batchMonitoringImpl.setBatSeqNo(batchMonitoring.getBatSeqNo());
		batchMonitoringImpl.setBatDiv(batchMonitoring.getBatDiv());
		batchMonitoringImpl.setManualYN(batchMonitoring.getManualYN());
		batchMonitoringImpl.setStatus(batchMonitoring.getStatus());
		batchMonitoringImpl.setMessage(batchMonitoring.getMessage());
		batchMonitoringImpl.setInsertId(batchMonitoring.getInsertId());
		batchMonitoringImpl.setExeDate(batchMonitoring.getExeDate());

		return batchMonitoringImpl;
	}

	/**
	 * Returns the batch monitoring with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the batch monitoring
	 * @return the batch monitoring
	 * @throws org.kisti.edison.simulation.NoSuchBatchMonitoringException if a batch monitoring with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BatchMonitoring findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBatchMonitoringException, SystemException {
		BatchMonitoring batchMonitoring = fetchByPrimaryKey(primaryKey);

		if (batchMonitoring == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBatchMonitoringException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return batchMonitoring;
	}

	/**
	 * Returns the batch monitoring with the primary key or throws a {@link org.kisti.edison.simulation.NoSuchBatchMonitoringException} if it could not be found.
	 *
	 * @param batSeqNo the primary key of the batch monitoring
	 * @return the batch monitoring
	 * @throws org.kisti.edison.simulation.NoSuchBatchMonitoringException if a batch monitoring with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BatchMonitoring findByPrimaryKey(long batSeqNo)
		throws NoSuchBatchMonitoringException, SystemException {
		return findByPrimaryKey((Serializable)batSeqNo);
	}

	/**
	 * Returns the batch monitoring with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the batch monitoring
	 * @return the batch monitoring, or <code>null</code> if a batch monitoring with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BatchMonitoring fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		BatchMonitoring batchMonitoring = (BatchMonitoring)EntityCacheUtil.getResult(BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
				BatchMonitoringImpl.class, primaryKey);

		if (batchMonitoring == _nullBatchMonitoring) {
			return null;
		}

		if (batchMonitoring == null) {
			Session session = null;

			try {
				session = openSession();

				batchMonitoring = (BatchMonitoring)session.get(BatchMonitoringImpl.class,
						primaryKey);

				if (batchMonitoring != null) {
					cacheResult(batchMonitoring);
				}
				else {
					EntityCacheUtil.putResult(BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
						BatchMonitoringImpl.class, primaryKey,
						_nullBatchMonitoring);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(BatchMonitoringModelImpl.ENTITY_CACHE_ENABLED,
					BatchMonitoringImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return batchMonitoring;
	}

	/**
	 * Returns the batch monitoring with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param batSeqNo the primary key of the batch monitoring
	 * @return the batch monitoring, or <code>null</code> if a batch monitoring with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BatchMonitoring fetchByPrimaryKey(long batSeqNo)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)batSeqNo);
	}

	/**
	 * Returns all the batch monitorings.
	 *
	 * @return the batch monitorings
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BatchMonitoring> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the batch monitorings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.simulation.model.impl.BatchMonitoringModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of batch monitorings
	 * @param end the upper bound of the range of batch monitorings (not inclusive)
	 * @return the range of batch monitorings
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BatchMonitoring> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the batch monitorings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.simulation.model.impl.BatchMonitoringModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of batch monitorings
	 * @param end the upper bound of the range of batch monitorings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of batch monitorings
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BatchMonitoring> findAll(int start, int end,
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

		List<BatchMonitoring> list = (List<BatchMonitoring>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BATCHMONITORING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BATCHMONITORING;

				if (pagination) {
					sql = sql.concat(BatchMonitoringModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BatchMonitoring>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BatchMonitoring>(list);
				}
				else {
					list = (List<BatchMonitoring>)QueryUtil.list(q,
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
	 * Removes all the batch monitorings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (BatchMonitoring batchMonitoring : findAll()) {
			remove(batchMonitoring);
		}
	}

	/**
	 * Returns the number of batch monitorings.
	 *
	 * @return the number of batch monitorings
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

				Query q = session.createQuery(_SQL_COUNT_BATCHMONITORING);

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
	 * Initializes the batch monitoring persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.simulation.model.BatchMonitoring")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<BatchMonitoring>> listenersList = new ArrayList<ModelListener<BatchMonitoring>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<BatchMonitoring>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(BatchMonitoringImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_BATCHMONITORING = "SELECT batchMonitoring FROM BatchMonitoring batchMonitoring";
	private static final String _SQL_COUNT_BATCHMONITORING = "SELECT COUNT(batchMonitoring) FROM BatchMonitoring batchMonitoring";
	private static final String _ORDER_BY_ENTITY_ALIAS = "batchMonitoring.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BatchMonitoring exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(BatchMonitoringPersistenceImpl.class);
	private static BatchMonitoring _nullBatchMonitoring = new BatchMonitoringImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<BatchMonitoring> toCacheModel() {
				return _nullBatchMonitoringCacheModel;
			}
		};

	private static CacheModel<BatchMonitoring> _nullBatchMonitoringCacheModel = new CacheModel<BatchMonitoring>() {
			@Override
			public BatchMonitoring toEntityModel() {
				return _nullBatchMonitoring;
			}
		};
}