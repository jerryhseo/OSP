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

package org.kisti.edison.search.service.service.persistence;

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

import org.kisti.edison.search.service.NoSuchSearchConditionException;
import org.kisti.edison.search.service.model.SearchCondition;
import org.kisti.edison.search.service.model.impl.SearchConditionImpl;
import org.kisti.edison.search.service.model.impl.SearchConditionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the search condition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author yjpark
 * @see SearchConditionPersistence
 * @see SearchConditionUtil
 * @generated
 */
public class SearchConditionPersistenceImpl extends BasePersistenceImpl<SearchCondition>
	implements SearchConditionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SearchConditionUtil} to access the search condition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SearchConditionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
			SearchConditionModelImpl.FINDER_CACHE_ENABLED,
			SearchConditionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
			SearchConditionModelImpl.FINDER_CACHE_ENABLED,
			SearchConditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
			SearchConditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SearchConditionPersistenceImpl() {
		setModelClass(SearchCondition.class);
	}

	/**
	 * Caches the search condition in the entity cache if it is enabled.
	 *
	 * @param searchCondition the search condition
	 */
	@Override
	public void cacheResult(SearchCondition searchCondition) {
		EntityCacheUtil.putResult(SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
			SearchConditionImpl.class, searchCondition.getPrimaryKey(),
			searchCondition);

		searchCondition.resetOriginalValues();
	}

	/**
	 * Caches the search conditions in the entity cache if it is enabled.
	 *
	 * @param searchConditions the search conditions
	 */
	@Override
	public void cacheResult(List<SearchCondition> searchConditions) {
		for (SearchCondition searchCondition : searchConditions) {
			if (EntityCacheUtil.getResult(
						SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
						SearchConditionImpl.class,
						searchCondition.getPrimaryKey()) == null) {
				cacheResult(searchCondition);
			}
			else {
				searchCondition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all search conditions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SearchConditionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SearchConditionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the search condition.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SearchCondition searchCondition) {
		EntityCacheUtil.removeResult(SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
			SearchConditionImpl.class, searchCondition.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SearchCondition> searchConditions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SearchCondition searchCondition : searchConditions) {
			EntityCacheUtil.removeResult(SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
				SearchConditionImpl.class, searchCondition.getPrimaryKey());
		}
	}

	/**
	 * Creates a new search condition with the primary key. Does not add the search condition to the database.
	 *
	 * @param id the primary key for the new search condition
	 * @return the new search condition
	 */
	@Override
	public SearchCondition create(long id) {
		SearchCondition searchCondition = new SearchConditionImpl();

		searchCondition.setNew(true);
		searchCondition.setPrimaryKey(id);

		return searchCondition;
	}

	/**
	 * Removes the search condition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the search condition
	 * @return the search condition that was removed
	 * @throws org.kisti.edison.search.service.NoSuchSearchConditionException if a search condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SearchCondition remove(long id)
		throws NoSuchSearchConditionException, SystemException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the search condition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the search condition
	 * @return the search condition that was removed
	 * @throws org.kisti.edison.search.service.NoSuchSearchConditionException if a search condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SearchCondition remove(Serializable primaryKey)
		throws NoSuchSearchConditionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SearchCondition searchCondition = (SearchCondition)session.get(SearchConditionImpl.class,
					primaryKey);

			if (searchCondition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSearchConditionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(searchCondition);
		}
		catch (NoSuchSearchConditionException nsee) {
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
	protected SearchCondition removeImpl(SearchCondition searchCondition)
		throws SystemException {
		searchCondition = toUnwrappedModel(searchCondition);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(searchCondition)) {
				searchCondition = (SearchCondition)session.get(SearchConditionImpl.class,
						searchCondition.getPrimaryKeyObj());
			}

			if (searchCondition != null) {
				session.delete(searchCondition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (searchCondition != null) {
			clearCache(searchCondition);
		}

		return searchCondition;
	}

	@Override
	public SearchCondition updateImpl(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws SystemException {
		searchCondition = toUnwrappedModel(searchCondition);

		boolean isNew = searchCondition.isNew();

		Session session = null;

		try {
			session = openSession();

			if (searchCondition.isNew()) {
				session.save(searchCondition);

				searchCondition.setNew(false);
			}
			else {
				session.merge(searchCondition);
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

		EntityCacheUtil.putResult(SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
			SearchConditionImpl.class, searchCondition.getPrimaryKey(),
			searchCondition);

		return searchCondition;
	}

	protected SearchCondition toUnwrappedModel(SearchCondition searchCondition) {
		if (searchCondition instanceof SearchConditionImpl) {
			return searchCondition;
		}

		SearchConditionImpl searchConditionImpl = new SearchConditionImpl();

		searchConditionImpl.setNew(searchCondition.isNew());
		searchConditionImpl.setPrimaryKey(searchCondition.getPrimaryKey());

		searchConditionImpl.setId(searchCondition.getId());
		searchConditionImpl.setSearchKeyword(searchCondition.getSearchKeyword());
		searchConditionImpl.setAreaScienceApp(searchCondition.isAreaScienceApp());
		searchConditionImpl.setAreaContents(searchCondition.isAreaContents());
		searchConditionImpl.setAreaSimulationProject(searchCondition.isAreaSimulationProject());
		searchConditionImpl.setAreaScienceData(searchCondition.isAreaScienceData());
		searchConditionImpl.setParentCategory(searchCondition.isParentCategory());
		searchConditionImpl.setCategoryId(searchCondition.getCategoryId());
		searchConditionImpl.setCompanyGroupId(searchCondition.getCompanyGroupId());
		searchConditionImpl.setGroupId(searchCondition.getGroupId());
		searchConditionImpl.setCurrentPage(searchCondition.getCurrentPage());
		searchConditionImpl.setListSize(searchCondition.getListSize());
		searchConditionImpl.setBlockSize(searchCondition.getBlockSize());
		searchConditionImpl.setClassnote(searchCondition.isClassnote());
		searchConditionImpl.setManual(searchCondition.isManual());
		searchConditionImpl.setReference(searchCondition.isReference());
		searchConditionImpl.setAdvanced(searchCondition.isAdvanced());
		searchConditionImpl.setSolver(searchCondition.isSolver());
		searchConditionImpl.setConverter(searchCondition.isConverter());
		searchConditionImpl.setEditor(searchCondition.isEditor());
		searchConditionImpl.setAnalyzer(searchCondition.isAnalyzer());

		return searchConditionImpl;
	}

	/**
	 * Returns the search condition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the search condition
	 * @return the search condition
	 * @throws org.kisti.edison.search.service.NoSuchSearchConditionException if a search condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SearchCondition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSearchConditionException, SystemException {
		SearchCondition searchCondition = fetchByPrimaryKey(primaryKey);

		if (searchCondition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSearchConditionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return searchCondition;
	}

	/**
	 * Returns the search condition with the primary key or throws a {@link org.kisti.edison.search.service.NoSuchSearchConditionException} if it could not be found.
	 *
	 * @param id the primary key of the search condition
	 * @return the search condition
	 * @throws org.kisti.edison.search.service.NoSuchSearchConditionException if a search condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SearchCondition findByPrimaryKey(long id)
		throws NoSuchSearchConditionException, SystemException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the search condition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the search condition
	 * @return the search condition, or <code>null</code> if a search condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SearchCondition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SearchCondition searchCondition = (SearchCondition)EntityCacheUtil.getResult(SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
				SearchConditionImpl.class, primaryKey);

		if (searchCondition == _nullSearchCondition) {
			return null;
		}

		if (searchCondition == null) {
			Session session = null;

			try {
				session = openSession();

				searchCondition = (SearchCondition)session.get(SearchConditionImpl.class,
						primaryKey);

				if (searchCondition != null) {
					cacheResult(searchCondition);
				}
				else {
					EntityCacheUtil.putResult(SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
						SearchConditionImpl.class, primaryKey,
						_nullSearchCondition);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SearchConditionModelImpl.ENTITY_CACHE_ENABLED,
					SearchConditionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return searchCondition;
	}

	/**
	 * Returns the search condition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the search condition
	 * @return the search condition, or <code>null</code> if a search condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SearchCondition fetchByPrimaryKey(long id) throws SystemException {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the search conditions.
	 *
	 * @return the search conditions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SearchCondition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the search conditions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of search conditions
	 * @param end the upper bound of the range of search conditions (not inclusive)
	 * @return the range of search conditions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SearchCondition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the search conditions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of search conditions
	 * @param end the upper bound of the range of search conditions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of search conditions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SearchCondition> findAll(int start, int end,
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

		List<SearchCondition> list = (List<SearchCondition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SEARCHCONDITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SEARCHCONDITION;

				if (pagination) {
					sql = sql.concat(SearchConditionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SearchCondition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SearchCondition>(list);
				}
				else {
					list = (List<SearchCondition>)QueryUtil.list(q,
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
	 * Removes all the search conditions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SearchCondition searchCondition : findAll()) {
			remove(searchCondition);
		}
	}

	/**
	 * Returns the number of search conditions.
	 *
	 * @return the number of search conditions
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

				Query q = session.createQuery(_SQL_COUNT_SEARCHCONDITION);

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
	 * Initializes the search condition persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.search.service.model.SearchCondition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SearchCondition>> listenersList = new ArrayList<ModelListener<SearchCondition>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SearchCondition>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SearchConditionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SEARCHCONDITION = "SELECT searchCondition FROM SearchCondition searchCondition";
	private static final String _SQL_COUNT_SEARCHCONDITION = "SELECT COUNT(searchCondition) FROM SearchCondition searchCondition";
	private static final String _ORDER_BY_ENTITY_ALIAS = "searchCondition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SearchCondition exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SearchConditionPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"id"
			});
	private static SearchCondition _nullSearchCondition = new SearchConditionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SearchCondition> toCacheModel() {
				return _nullSearchConditionCacheModel;
			}
		};

	private static CacheModel<SearchCondition> _nullSearchConditionCacheModel = new CacheModel<SearchCondition>() {
			@Override
			public SearchCondition toEntityModel() {
				return _nullSearchCondition;
			}
		};
}