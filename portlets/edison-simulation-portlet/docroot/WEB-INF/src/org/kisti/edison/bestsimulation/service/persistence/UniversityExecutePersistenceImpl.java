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

import org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException;
import org.kisti.edison.bestsimulation.model.UniversityExecute;
import org.kisti.edison.bestsimulation.model.impl.UniversityExecuteImpl;
import org.kisti.edison.bestsimulation.model.impl.UniversityExecuteModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the university execute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see UniversityExecutePersistence
 * @see UniversityExecuteUtil
 * @generated
 */
public class UniversityExecutePersistenceImpl extends BasePersistenceImpl<UniversityExecute>
	implements UniversityExecutePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UniversityExecuteUtil} to access the university execute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UniversityExecuteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
			UniversityExecuteModelImpl.FINDER_CACHE_ENABLED,
			UniversityExecuteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
			UniversityExecuteModelImpl.FINDER_CACHE_ENABLED,
			UniversityExecuteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
			UniversityExecuteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public UniversityExecutePersistenceImpl() {
		setModelClass(UniversityExecute.class);
	}

	/**
	 * Caches the university execute in the entity cache if it is enabled.
	 *
	 * @param universityExecute the university execute
	 */
	@Override
	public void cacheResult(UniversityExecute universityExecute) {
		EntityCacheUtil.putResult(UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
			UniversityExecuteImpl.class, universityExecute.getPrimaryKey(),
			universityExecute);

		universityExecute.resetOriginalValues();
	}

	/**
	 * Caches the university executes in the entity cache if it is enabled.
	 *
	 * @param universityExecutes the university executes
	 */
	@Override
	public void cacheResult(List<UniversityExecute> universityExecutes) {
		for (UniversityExecute universityExecute : universityExecutes) {
			if (EntityCacheUtil.getResult(
						UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
						UniversityExecuteImpl.class,
						universityExecute.getPrimaryKey()) == null) {
				cacheResult(universityExecute);
			}
			else {
				universityExecute.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all university executes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(UniversityExecuteImpl.class.getName());
		}

		EntityCacheUtil.clearCache(UniversityExecuteImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the university execute.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UniversityExecute universityExecute) {
		EntityCacheUtil.removeResult(UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
			UniversityExecuteImpl.class, universityExecute.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UniversityExecute> universityExecutes) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UniversityExecute universityExecute : universityExecutes) {
			EntityCacheUtil.removeResult(UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
				UniversityExecuteImpl.class, universityExecute.getPrimaryKey());
		}
	}

	/**
	 * Creates a new university execute with the primary key. Does not add the university execute to the database.
	 *
	 * @param universityExecutePK the primary key for the new university execute
	 * @return the new university execute
	 */
	@Override
	public UniversityExecute create(UniversityExecutePK universityExecutePK) {
		UniversityExecute universityExecute = new UniversityExecuteImpl();

		universityExecute.setNew(true);
		universityExecute.setPrimaryKey(universityExecutePK);

		return universityExecute;
	}

	/**
	 * Removes the university execute with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param universityExecutePK the primary key of the university execute
	 * @return the university execute that was removed
	 * @throws org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException if a university execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UniversityExecute remove(UniversityExecutePK universityExecutePK)
		throws NoSuchUniversityExecuteException, SystemException {
		return remove((Serializable)universityExecutePK);
	}

	/**
	 * Removes the university execute with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the university execute
	 * @return the university execute that was removed
	 * @throws org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException if a university execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UniversityExecute remove(Serializable primaryKey)
		throws NoSuchUniversityExecuteException, SystemException {
		Session session = null;

		try {
			session = openSession();

			UniversityExecute universityExecute = (UniversityExecute)session.get(UniversityExecuteImpl.class,
					primaryKey);

			if (universityExecute == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUniversityExecuteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(universityExecute);
		}
		catch (NoSuchUniversityExecuteException nsee) {
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
	protected UniversityExecute removeImpl(UniversityExecute universityExecute)
		throws SystemException {
		universityExecute = toUnwrappedModel(universityExecute);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(universityExecute)) {
				universityExecute = (UniversityExecute)session.get(UniversityExecuteImpl.class,
						universityExecute.getPrimaryKeyObj());
			}

			if (universityExecute != null) {
				session.delete(universityExecute);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (universityExecute != null) {
			clearCache(universityExecute);
		}

		return universityExecute;
	}

	@Override
	public UniversityExecute updateImpl(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute)
		throws SystemException {
		universityExecute = toUnwrappedModel(universityExecute);

		boolean isNew = universityExecute.isNew();

		Session session = null;

		try {
			session = openSession();

			if (universityExecute.isNew()) {
				session.save(universityExecute);

				universityExecute.setNew(false);
			}
			else {
				session.merge(universityExecute);
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

		EntityCacheUtil.putResult(UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
			UniversityExecuteImpl.class, universityExecute.getPrimaryKey(),
			universityExecute);

		return universityExecute;
	}

	protected UniversityExecute toUnwrappedModel(
		UniversityExecute universityExecute) {
		if (universityExecute instanceof UniversityExecuteImpl) {
			return universityExecute;
		}

		UniversityExecuteImpl universityExecuteImpl = new UniversityExecuteImpl();

		universityExecuteImpl.setNew(universityExecute.isNew());
		universityExecuteImpl.setPrimaryKey(universityExecute.getPrimaryKey());

		universityExecuteImpl.setExecuteDate(universityExecute.getExecuteDate());
		universityExecuteImpl.setUniversityField(universityExecute.getUniversityField());
		universityExecuteImpl.setUserCnt(universityExecute.getUserCnt());
		universityExecuteImpl.setAvgExeTime(universityExecute.getAvgExeTime());
		universityExecuteImpl.setExeCnt(universityExecute.getExeCnt());
		universityExecuteImpl.setCpuTime(universityExecute.getCpuTime());

		return universityExecuteImpl;
	}

	/**
	 * Returns the university execute with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the university execute
	 * @return the university execute
	 * @throws org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException if a university execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UniversityExecute findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUniversityExecuteException, SystemException {
		UniversityExecute universityExecute = fetchByPrimaryKey(primaryKey);

		if (universityExecute == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUniversityExecuteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return universityExecute;
	}

	/**
	 * Returns the university execute with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException} if it could not be found.
	 *
	 * @param universityExecutePK the primary key of the university execute
	 * @return the university execute
	 * @throws org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException if a university execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UniversityExecute findByPrimaryKey(
		UniversityExecutePK universityExecutePK)
		throws NoSuchUniversityExecuteException, SystemException {
		return findByPrimaryKey((Serializable)universityExecutePK);
	}

	/**
	 * Returns the university execute with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the university execute
	 * @return the university execute, or <code>null</code> if a university execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UniversityExecute fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		UniversityExecute universityExecute = (UniversityExecute)EntityCacheUtil.getResult(UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
				UniversityExecuteImpl.class, primaryKey);

		if (universityExecute == _nullUniversityExecute) {
			return null;
		}

		if (universityExecute == null) {
			Session session = null;

			try {
				session = openSession();

				universityExecute = (UniversityExecute)session.get(UniversityExecuteImpl.class,
						primaryKey);

				if (universityExecute != null) {
					cacheResult(universityExecute);
				}
				else {
					EntityCacheUtil.putResult(UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
						UniversityExecuteImpl.class, primaryKey,
						_nullUniversityExecute);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(UniversityExecuteModelImpl.ENTITY_CACHE_ENABLED,
					UniversityExecuteImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return universityExecute;
	}

	/**
	 * Returns the university execute with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param universityExecutePK the primary key of the university execute
	 * @return the university execute, or <code>null</code> if a university execute with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UniversityExecute fetchByPrimaryKey(
		UniversityExecutePK universityExecutePK) throws SystemException {
		return fetchByPrimaryKey((Serializable)universityExecutePK);
	}

	/**
	 * Returns all the university executes.
	 *
	 * @return the university executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UniversityExecute> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the university executes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.UniversityExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of university executes
	 * @param end the upper bound of the range of university executes (not inclusive)
	 * @return the range of university executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UniversityExecute> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the university executes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.UniversityExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of university executes
	 * @param end the upper bound of the range of university executes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of university executes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UniversityExecute> findAll(int start, int end,
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

		List<UniversityExecute> list = (List<UniversityExecute>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_UNIVERSITYEXECUTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_UNIVERSITYEXECUTE;

				if (pagination) {
					sql = sql.concat(UniversityExecuteModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UniversityExecute>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<UniversityExecute>(list);
				}
				else {
					list = (List<UniversityExecute>)QueryUtil.list(q,
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
	 * Removes all the university executes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (UniversityExecute universityExecute : findAll()) {
			remove(universityExecute);
		}
	}

	/**
	 * Returns the number of university executes.
	 *
	 * @return the number of university executes
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

				Query q = session.createQuery(_SQL_COUNT_UNIVERSITYEXECUTE);

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
	 * Initializes the university execute persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.bestsimulation.model.UniversityExecute")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<UniversityExecute>> listenersList = new ArrayList<ModelListener<UniversityExecute>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<UniversityExecute>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(UniversityExecuteImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_UNIVERSITYEXECUTE = "SELECT universityExecute FROM UniversityExecute universityExecute";
	private static final String _SQL_COUNT_UNIVERSITYEXECUTE = "SELECT COUNT(universityExecute) FROM UniversityExecute universityExecute";
	private static final String _ORDER_BY_ENTITY_ALIAS = "universityExecute.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UniversityExecute exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(UniversityExecutePersistenceImpl.class);
	private static UniversityExecute _nullUniversityExecute = new UniversityExecuteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<UniversityExecute> toCacheModel() {
				return _nullUniversityExecuteCacheModel;
			}
		};

	private static CacheModel<UniversityExecute> _nullUniversityExecuteCacheModel =
		new CacheModel<UniversityExecute>() {
			@Override
			public UniversityExecute toEntityModel() {
				return _nullUniversityExecute;
			}
		};
}