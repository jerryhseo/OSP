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

import org.kisti.edison.search.service.NoSuchSearchException;
import org.kisti.edison.search.service.model.Search;
import org.kisti.edison.search.service.model.impl.SearchImpl;
import org.kisti.edison.search.service.model.impl.SearchModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the search service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author yjpark
 * @see SearchPersistence
 * @see SearchUtil
 * @generated
 */
public class SearchPersistenceImpl extends BasePersistenceImpl<Search>
	implements SearchPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SearchUtil} to access the search persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SearchImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SearchModelImpl.ENTITY_CACHE_ENABLED,
			SearchModelImpl.FINDER_CACHE_ENABLED, SearchImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SearchModelImpl.ENTITY_CACHE_ENABLED,
			SearchModelImpl.FINDER_CACHE_ENABLED, SearchImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SearchModelImpl.ENTITY_CACHE_ENABLED,
			SearchModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SearchPersistenceImpl() {
		setModelClass(Search.class);
	}

	/**
	 * Caches the search in the entity cache if it is enabled.
	 *
	 * @param search the search
	 */
	@Override
	public void cacheResult(Search search) {
		EntityCacheUtil.putResult(SearchModelImpl.ENTITY_CACHE_ENABLED,
			SearchImpl.class, search.getPrimaryKey(), search);

		search.resetOriginalValues();
	}

	/**
	 * Caches the searchs in the entity cache if it is enabled.
	 *
	 * @param searchs the searchs
	 */
	@Override
	public void cacheResult(List<Search> searchs) {
		for (Search search : searchs) {
			if (EntityCacheUtil.getResult(
						SearchModelImpl.ENTITY_CACHE_ENABLED, SearchImpl.class,
						search.getPrimaryKey()) == null) {
				cacheResult(search);
			}
			else {
				search.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all searchs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SearchImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SearchImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the search.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Search search) {
		EntityCacheUtil.removeResult(SearchModelImpl.ENTITY_CACHE_ENABLED,
			SearchImpl.class, search.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Search> searchs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Search search : searchs) {
			EntityCacheUtil.removeResult(SearchModelImpl.ENTITY_CACHE_ENABLED,
				SearchImpl.class, search.getPrimaryKey());
		}
	}

	/**
	 * Creates a new search with the primary key. Does not add the search to the database.
	 *
	 * @param id the primary key for the new search
	 * @return the new search
	 */
	@Override
	public Search create(long id) {
		Search search = new SearchImpl();

		search.setNew(true);
		search.setPrimaryKey(id);

		return search;
	}

	/**
	 * Removes the search with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the search
	 * @return the search that was removed
	 * @throws org.kisti.edison.search.service.NoSuchSearchException if a search with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Search remove(long id) throws NoSuchSearchException, SystemException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the search with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the search
	 * @return the search that was removed
	 * @throws org.kisti.edison.search.service.NoSuchSearchException if a search with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Search remove(Serializable primaryKey)
		throws NoSuchSearchException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Search search = (Search)session.get(SearchImpl.class, primaryKey);

			if (search == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSearchException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(search);
		}
		catch (NoSuchSearchException nsee) {
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
	protected Search removeImpl(Search search) throws SystemException {
		search = toUnwrappedModel(search);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(search)) {
				search = (Search)session.get(SearchImpl.class,
						search.getPrimaryKeyObj());
			}

			if (search != null) {
				session.delete(search);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (search != null) {
			clearCache(search);
		}

		return search;
	}

	@Override
	public Search updateImpl(
		org.kisti.edison.search.service.model.Search search)
		throws SystemException {
		search = toUnwrappedModel(search);

		boolean isNew = search.isNew();

		Session session = null;

		try {
			session = openSession();

			if (search.isNew()) {
				session.save(search);

				search.setNew(false);
			}
			else {
				session.merge(search);
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

		EntityCacheUtil.putResult(SearchModelImpl.ENTITY_CACHE_ENABLED,
			SearchImpl.class, search.getPrimaryKey(), search);

		return search;
	}

	protected Search toUnwrappedModel(Search search) {
		if (search instanceof SearchImpl) {
			return search;
		}

		SearchImpl searchImpl = new SearchImpl();

		searchImpl.setNew(search.isNew());
		searchImpl.setPrimaryKey(search.getPrimaryKey());

		searchImpl.setId(search.getId());

		return searchImpl;
	}

	/**
	 * Returns the search with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the search
	 * @return the search
	 * @throws org.kisti.edison.search.service.NoSuchSearchException if a search with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Search findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSearchException, SystemException {
		Search search = fetchByPrimaryKey(primaryKey);

		if (search == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSearchException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return search;
	}

	/**
	 * Returns the search with the primary key or throws a {@link org.kisti.edison.search.service.NoSuchSearchException} if it could not be found.
	 *
	 * @param id the primary key of the search
	 * @return the search
	 * @throws org.kisti.edison.search.service.NoSuchSearchException if a search with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Search findByPrimaryKey(long id)
		throws NoSuchSearchException, SystemException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the search with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the search
	 * @return the search, or <code>null</code> if a search with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Search fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Search search = (Search)EntityCacheUtil.getResult(SearchModelImpl.ENTITY_CACHE_ENABLED,
				SearchImpl.class, primaryKey);

		if (search == _nullSearch) {
			return null;
		}

		if (search == null) {
			Session session = null;

			try {
				session = openSession();

				search = (Search)session.get(SearchImpl.class, primaryKey);

				if (search != null) {
					cacheResult(search);
				}
				else {
					EntityCacheUtil.putResult(SearchModelImpl.ENTITY_CACHE_ENABLED,
						SearchImpl.class, primaryKey, _nullSearch);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SearchModelImpl.ENTITY_CACHE_ENABLED,
					SearchImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return search;
	}

	/**
	 * Returns the search with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the search
	 * @return the search, or <code>null</code> if a search with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Search fetchByPrimaryKey(long id) throws SystemException {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the searchs.
	 *
	 * @return the searchs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Search> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the searchs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of searchs
	 * @param end the upper bound of the range of searchs (not inclusive)
	 * @return the range of searchs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Search> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the searchs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of searchs
	 * @param end the upper bound of the range of searchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of searchs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Search> findAll(int start, int end,
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

		List<Search> list = (List<Search>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SEARCH);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SEARCH;

				if (pagination) {
					sql = sql.concat(SearchModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Search>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Search>(list);
				}
				else {
					list = (List<Search>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the searchs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Search search : findAll()) {
			remove(search);
		}
	}

	/**
	 * Returns the number of searchs.
	 *
	 * @return the number of searchs
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

				Query q = session.createQuery(_SQL_COUNT_SEARCH);

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
	 * Initializes the search persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.search.service.model.Search")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Search>> listenersList = new ArrayList<ModelListener<Search>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Search>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SearchImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SEARCH = "SELECT search FROM Search search";
	private static final String _SQL_COUNT_SEARCH = "SELECT COUNT(search) FROM Search search";
	private static final String _ORDER_BY_ENTITY_ALIAS = "search.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Search exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SearchPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"id"
			});
	private static Search _nullSearch = new SearchImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Search> toCacheModel() {
				return _nullSearchCacheModel;
			}
		};

	private static CacheModel<Search> _nullSearchCacheModel = new CacheModel<Search>() {
			@Override
			public Search toEntityModel() {
				return _nullSearch;
			}
		};
}