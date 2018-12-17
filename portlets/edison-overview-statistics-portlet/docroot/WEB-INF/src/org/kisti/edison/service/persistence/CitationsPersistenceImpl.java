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

import org.kisti.edison.NoSuchCitationsException;
import org.kisti.edison.model.Citations;
import org.kisti.edison.model.impl.CitationsImpl;
import org.kisti.edison.model.impl.CitationsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the citations service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see CitationsPersistence
 * @see CitationsUtil
 * @generated
 */
public class CitationsPersistenceImpl extends BasePersistenceImpl<Citations>
	implements CitationsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CitationsUtil} to access the citations persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CitationsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CitationsModelImpl.ENTITY_CACHE_ENABLED,
			CitationsModelImpl.FINDER_CACHE_ENABLED, CitationsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CitationsModelImpl.ENTITY_CACHE_ENABLED,
			CitationsModelImpl.FINDER_CACHE_ENABLED, CitationsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CitationsModelImpl.ENTITY_CACHE_ENABLED,
			CitationsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CitationsPersistenceImpl() {
		setModelClass(Citations.class);
	}

	/**
	 * Caches the citations in the entity cache if it is enabled.
	 *
	 * @param citations the citations
	 */
	@Override
	public void cacheResult(Citations citations) {
		EntityCacheUtil.putResult(CitationsModelImpl.ENTITY_CACHE_ENABLED,
			CitationsImpl.class, citations.getPrimaryKey(), citations);

		citations.resetOriginalValues();
	}

	/**
	 * Caches the citationses in the entity cache if it is enabled.
	 *
	 * @param citationses the citationses
	 */
	@Override
	public void cacheResult(List<Citations> citationses) {
		for (Citations citations : citationses) {
			if (EntityCacheUtil.getResult(
						CitationsModelImpl.ENTITY_CACHE_ENABLED,
						CitationsImpl.class, citations.getPrimaryKey()) == null) {
				cacheResult(citations);
			}
			else {
				citations.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all citationses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CitationsImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CitationsImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the citations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Citations citations) {
		EntityCacheUtil.removeResult(CitationsModelImpl.ENTITY_CACHE_ENABLED,
			CitationsImpl.class, citations.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Citations> citationses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Citations citations : citationses) {
			EntityCacheUtil.removeResult(CitationsModelImpl.ENTITY_CACHE_ENABLED,
				CitationsImpl.class, citations.getPrimaryKey());
		}
	}

	/**
	 * Creates a new citations with the primary key. Does not add the citations to the database.
	 *
	 * @param createDate the primary key for the new citations
	 * @return the new citations
	 */
	@Override
	public Citations create(String createDate) {
		Citations citations = new CitationsImpl();

		citations.setNew(true);
		citations.setPrimaryKey(createDate);

		return citations;
	}

	/**
	 * Removes the citations with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param createDate the primary key of the citations
	 * @return the citations that was removed
	 * @throws org.kisti.edison.NoSuchCitationsException if a citations with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Citations remove(String createDate)
		throws NoSuchCitationsException, SystemException {
		return remove((Serializable)createDate);
	}

	/**
	 * Removes the citations with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the citations
	 * @return the citations that was removed
	 * @throws org.kisti.edison.NoSuchCitationsException if a citations with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Citations remove(Serializable primaryKey)
		throws NoSuchCitationsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Citations citations = (Citations)session.get(CitationsImpl.class,
					primaryKey);

			if (citations == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCitationsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(citations);
		}
		catch (NoSuchCitationsException nsee) {
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
	protected Citations removeImpl(Citations citations)
		throws SystemException {
		citations = toUnwrappedModel(citations);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(citations)) {
				citations = (Citations)session.get(CitationsImpl.class,
						citations.getPrimaryKeyObj());
			}

			if (citations != null) {
				session.delete(citations);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (citations != null) {
			clearCache(citations);
		}

		return citations;
	}

	@Override
	public Citations updateImpl(org.kisti.edison.model.Citations citations)
		throws SystemException {
		citations = toUnwrappedModel(citations);

		boolean isNew = citations.isNew();

		Session session = null;

		try {
			session = openSession();

			if (citations.isNew()) {
				session.save(citations);

				citations.setNew(false);
			}
			else {
				session.merge(citations);
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

		EntityCacheUtil.putResult(CitationsModelImpl.ENTITY_CACHE_ENABLED,
			CitationsImpl.class, citations.getPrimaryKey(), citations);

		return citations;
	}

	protected Citations toUnwrappedModel(Citations citations) {
		if (citations instanceof CitationsImpl) {
			return citations;
		}

		CitationsImpl citationsImpl = new CitationsImpl();

		citationsImpl.setNew(citations.isNew());
		citationsImpl.setPrimaryKey(citations.getPrimaryKey());

		citationsImpl.setCreateDate(citations.getCreateDate());
		citationsImpl.setCnt(citations.getCnt());

		return citationsImpl;
	}

	/**
	 * Returns the citations with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the citations
	 * @return the citations
	 * @throws org.kisti.edison.NoSuchCitationsException if a citations with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Citations findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCitationsException, SystemException {
		Citations citations = fetchByPrimaryKey(primaryKey);

		if (citations == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCitationsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return citations;
	}

	/**
	 * Returns the citations with the primary key or throws a {@link org.kisti.edison.NoSuchCitationsException} if it could not be found.
	 *
	 * @param createDate the primary key of the citations
	 * @return the citations
	 * @throws org.kisti.edison.NoSuchCitationsException if a citations with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Citations findByPrimaryKey(String createDate)
		throws NoSuchCitationsException, SystemException {
		return findByPrimaryKey((Serializable)createDate);
	}

	/**
	 * Returns the citations with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the citations
	 * @return the citations, or <code>null</code> if a citations with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Citations fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Citations citations = (Citations)EntityCacheUtil.getResult(CitationsModelImpl.ENTITY_CACHE_ENABLED,
				CitationsImpl.class, primaryKey);

		if (citations == _nullCitations) {
			return null;
		}

		if (citations == null) {
			Session session = null;

			try {
				session = openSession();

				citations = (Citations)session.get(CitationsImpl.class,
						primaryKey);

				if (citations != null) {
					cacheResult(citations);
				}
				else {
					EntityCacheUtil.putResult(CitationsModelImpl.ENTITY_CACHE_ENABLED,
						CitationsImpl.class, primaryKey, _nullCitations);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CitationsModelImpl.ENTITY_CACHE_ENABLED,
					CitationsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return citations;
	}

	/**
	 * Returns the citations with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param createDate the primary key of the citations
	 * @return the citations, or <code>null</code> if a citations with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Citations fetchByPrimaryKey(String createDate)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)createDate);
	}

	/**
	 * Returns all the citationses.
	 *
	 * @return the citationses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Citations> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the citationses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.CitationsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of citationses
	 * @param end the upper bound of the range of citationses (not inclusive)
	 * @return the range of citationses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Citations> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the citationses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.CitationsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of citationses
	 * @param end the upper bound of the range of citationses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of citationses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Citations> findAll(int start, int end,
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

		List<Citations> list = (List<Citations>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CITATIONS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CITATIONS;

				if (pagination) {
					sql = sql.concat(CitationsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Citations>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Citations>(list);
				}
				else {
					list = (List<Citations>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the citationses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Citations citations : findAll()) {
			remove(citations);
		}
	}

	/**
	 * Returns the number of citationses.
	 *
	 * @return the number of citationses
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

				Query q = session.createQuery(_SQL_COUNT_CITATIONS);

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
	 * Initializes the citations persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.Citations")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Citations>> listenersList = new ArrayList<ModelListener<Citations>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Citations>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CitationsImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CITATIONS = "SELECT citations FROM Citations citations";
	private static final String _SQL_COUNT_CITATIONS = "SELECT COUNT(citations) FROM Citations citations";
	private static final String _ORDER_BY_ENTITY_ALIAS = "citations.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Citations exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CitationsPersistenceImpl.class);
	private static Citations _nullCitations = new CitationsImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Citations> toCacheModel() {
				return _nullCitationsCacheModel;
			}
		};

	private static CacheModel<Citations> _nullCitationsCacheModel = new CacheModel<Citations>() {
			@Override
			public Citations toEntityModel() {
				return _nullCitations;
			}
		};
}