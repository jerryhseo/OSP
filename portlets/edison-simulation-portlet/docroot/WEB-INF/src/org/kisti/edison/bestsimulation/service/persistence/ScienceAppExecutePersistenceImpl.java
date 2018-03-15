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

package org.kisti.edison.bestsimulation.service.persistence;

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

import org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException;
import org.kisti.edison.bestsimulation.model.ScienceAppExecute;
import org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteImpl;
import org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the science app execute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ScienceAppExecutePersistence
 * @see ScienceAppExecuteUtil
 * @generated
 */
public class ScienceAppExecutePersistenceImpl extends BasePersistenceImpl<ScienceAppExecute>
	implements ScienceAppExecutePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScienceAppExecuteUtil} to access the science app execute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScienceAppExecuteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppExecuteModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppExecuteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppExecuteModelImpl.FINDER_CACHE_ENABLED,
			ScienceAppExecuteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppExecuteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ScienceAppExecutePersistenceImpl() {
		setModelClass(ScienceAppExecute.class);
	}

	/**
	 * Caches the science app execute in the entity cache if it is enabled.
	 *
	 * @param scienceAppExecute the science app execute
	 */
	@Override
	public void cacheResult(ScienceAppExecute scienceAppExecute) {
		EntityCacheUtil.putResult(ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppExecuteImpl.class, scienceAppExecute.getPrimaryKey(),
			scienceAppExecute);

		scienceAppExecute.resetOriginalValues();
	}

	/**
	 * Caches the science app executes in the entity cache if it is enabled.
	 *
	 * @param scienceAppExecutes the science app executes
	 */
	@Override
	public void cacheResult(List<ScienceAppExecute> scienceAppExecutes) {
		for (ScienceAppExecute scienceAppExecute : scienceAppExecutes) {
			if (EntityCacheUtil.getResult(
						ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppExecuteImpl.class,
						scienceAppExecute.getPrimaryKey()) == null) {
				cacheResult(scienceAppExecute);
			}
			else {
				scienceAppExecute.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all science app executes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ScienceAppExecuteImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ScienceAppExecuteImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the science app execute.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ScienceAppExecute scienceAppExecute) {
		EntityCacheUtil.removeResult(ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppExecuteImpl.class, scienceAppExecute.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ScienceAppExecute> scienceAppExecutes) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ScienceAppExecute scienceAppExecute : scienceAppExecutes) {
			EntityCacheUtil.removeResult(ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppExecuteImpl.class, scienceAppExecute.getPrimaryKey());
		}
	}

	/**
	 * Creates a new science app execute with the primary key. Does not add the science app execute to the database.
	 *
	 * @param scienceAppExecutePK the primary key for the new science app execute
	 * @return the new science app execute
	 */
	@Override
	public ScienceAppExecute create(ScienceAppExecutePK scienceAppExecutePK) {
		ScienceAppExecute scienceAppExecute = new ScienceAppExecuteImpl();

		scienceAppExecute.setNew(true);
		scienceAppExecute.setPrimaryKey(scienceAppExecutePK);

		return scienceAppExecute;
	}

	/**
	 * Removes the science app execute with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppExecutePK the primary key of the science app execute
	 * @return the science app execute that was removed
	 * @throws org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException if a science app execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppExecute remove(ScienceAppExecutePK scienceAppExecutePK)
		throws NoSuchScienceAppExecuteException, SystemException {
		return remove((Serializable)scienceAppExecutePK);
	}

	/**
	 * Removes the science app execute with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the science app execute
	 * @return the science app execute that was removed
	 * @throws org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException if a science app execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppExecute remove(Serializable primaryKey)
		throws NoSuchScienceAppExecuteException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ScienceAppExecute scienceAppExecute = (ScienceAppExecute)session.get(ScienceAppExecuteImpl.class,
					primaryKey);

			if (scienceAppExecute == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScienceAppExecuteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scienceAppExecute);
		}
		catch (NoSuchScienceAppExecuteException nsee) {
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
	protected ScienceAppExecute removeImpl(ScienceAppExecute scienceAppExecute)
		throws SystemException {
		scienceAppExecute = toUnwrappedModel(scienceAppExecute);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scienceAppExecute)) {
				scienceAppExecute = (ScienceAppExecute)session.get(ScienceAppExecuteImpl.class,
						scienceAppExecute.getPrimaryKeyObj());
			}

			if (scienceAppExecute != null) {
				session.delete(scienceAppExecute);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scienceAppExecute != null) {
			clearCache(scienceAppExecute);
		}

		return scienceAppExecute;
	}

	@Override
	public ScienceAppExecute updateImpl(
		org.kisti.edison.bestsimulation.model.ScienceAppExecute scienceAppExecute)
		throws SystemException {
		scienceAppExecute = toUnwrappedModel(scienceAppExecute);

		boolean isNew = scienceAppExecute.isNew();

		Session session = null;

		try {
			session = openSession();

			if (scienceAppExecute.isNew()) {
				session.save(scienceAppExecute);

				scienceAppExecute.setNew(false);
			}
			else {
				session.merge(scienceAppExecute);
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

		EntityCacheUtil.putResult(ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppExecuteImpl.class, scienceAppExecute.getPrimaryKey(),
			scienceAppExecute);

		return scienceAppExecute;
	}

	protected ScienceAppExecute toUnwrappedModel(
		ScienceAppExecute scienceAppExecute) {
		if (scienceAppExecute instanceof ScienceAppExecuteImpl) {
			return scienceAppExecute;
		}

		ScienceAppExecuteImpl scienceAppExecuteImpl = new ScienceAppExecuteImpl();

		scienceAppExecuteImpl.setNew(scienceAppExecute.isNew());
		scienceAppExecuteImpl.setPrimaryKey(scienceAppExecute.getPrimaryKey());

		scienceAppExecuteImpl.setExecuteDate(scienceAppExecute.getExecuteDate());
		scienceAppExecuteImpl.setScienceAppId(scienceAppExecute.getScienceAppId());
		scienceAppExecuteImpl.setUserCnt(scienceAppExecute.getUserCnt());
		scienceAppExecuteImpl.setAvgExeTime(scienceAppExecute.getAvgExeTime());
		scienceAppExecuteImpl.setExeCnt(scienceAppExecute.getExeCnt());

		return scienceAppExecuteImpl;
	}

	/**
	 * Returns the science app execute with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app execute
	 * @return the science app execute
	 * @throws org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException if a science app execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppExecute findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScienceAppExecuteException, SystemException {
		ScienceAppExecute scienceAppExecute = fetchByPrimaryKey(primaryKey);

		if (scienceAppExecute == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScienceAppExecuteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scienceAppExecute;
	}

	/**
	 * Returns the science app execute with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException} if it could not be found.
	 *
	 * @param scienceAppExecutePK the primary key of the science app execute
	 * @return the science app execute
	 * @throws org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException if a science app execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppExecute findByPrimaryKey(
		ScienceAppExecutePK scienceAppExecutePK)
		throws NoSuchScienceAppExecuteException, SystemException {
		return findByPrimaryKey((Serializable)scienceAppExecutePK);
	}

	/**
	 * Returns the science app execute with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app execute
	 * @return the science app execute, or <code>null</code> if a science app execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppExecute fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ScienceAppExecute scienceAppExecute = (ScienceAppExecute)EntityCacheUtil.getResult(ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppExecuteImpl.class, primaryKey);

		if (scienceAppExecute == _nullScienceAppExecute) {
			return null;
		}

		if (scienceAppExecute == null) {
			Session session = null;

			try {
				session = openSession();

				scienceAppExecute = (ScienceAppExecute)session.get(ScienceAppExecuteImpl.class,
						primaryKey);

				if (scienceAppExecute != null) {
					cacheResult(scienceAppExecute);
				}
				else {
					EntityCacheUtil.putResult(ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppExecuteImpl.class, primaryKey,
						_nullScienceAppExecute);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ScienceAppExecuteModelImpl.ENTITY_CACHE_ENABLED,
					ScienceAppExecuteImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scienceAppExecute;
	}

	/**
	 * Returns the science app execute with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppExecutePK the primary key of the science app execute
	 * @return the science app execute, or <code>null</code> if a science app execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ScienceAppExecute fetchByPrimaryKey(
		ScienceAppExecutePK scienceAppExecutePK) throws SystemException {
		return fetchByPrimaryKey((Serializable)scienceAppExecutePK);
	}

	/**
	 * Returns all the science app executes.
	 *
	 * @return the science app executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppExecute> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science app executes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science app executes
	 * @param end the upper bound of the range of science app executes (not inclusive)
	 * @return the range of science app executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppExecute> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the science app executes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science app executes
	 * @param end the upper bound of the range of science app executes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of science app executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ScienceAppExecute> findAll(int start, int end,
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

		List<ScienceAppExecute> list = (List<ScienceAppExecute>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SCIENCEAPPEXECUTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCIENCEAPPEXECUTE;

				if (pagination) {
					sql = sql.concat(ScienceAppExecuteModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ScienceAppExecute>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ScienceAppExecute>(list);
				}
				else {
					list = (List<ScienceAppExecute>)QueryUtil.list(q,
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
	 * Removes all the science app executes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ScienceAppExecute scienceAppExecute : findAll()) {
			remove(scienceAppExecute);
		}
	}

	/**
	 * Returns the number of science app executes.
	 *
	 * @return the number of science app executes
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

				Query q = session.createQuery(_SQL_COUNT_SCIENCEAPPEXECUTE);

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
	 * Initializes the science app execute persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.bestsimulation.model.ScienceAppExecute")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ScienceAppExecute>> listenersList = new ArrayList<ModelListener<ScienceAppExecute>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ScienceAppExecute>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ScienceAppExecuteImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SCIENCEAPPEXECUTE = "SELECT scienceAppExecute FROM ScienceAppExecute scienceAppExecute";
	private static final String _SQL_COUNT_SCIENCEAPPEXECUTE = "SELECT COUNT(scienceAppExecute) FROM ScienceAppExecute scienceAppExecute";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scienceAppExecute.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ScienceAppExecute exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ScienceAppExecutePersistenceImpl.class);
	private static ScienceAppExecute _nullScienceAppExecute = new ScienceAppExecuteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ScienceAppExecute> toCacheModel() {
				return _nullScienceAppExecuteCacheModel;
			}
		};

	private static CacheModel<ScienceAppExecute> _nullScienceAppExecuteCacheModel =
		new CacheModel<ScienceAppExecute>() {
			@Override
			public ScienceAppExecute toEntityModel() {
				return _nullScienceAppExecute;
			}
		};
}