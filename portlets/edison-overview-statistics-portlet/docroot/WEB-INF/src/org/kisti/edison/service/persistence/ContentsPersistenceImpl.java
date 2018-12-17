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

import org.kisti.edison.NoSuchContentsException;
import org.kisti.edison.model.Contents;
import org.kisti.edison.model.impl.ContentsImpl;
import org.kisti.edison.model.impl.ContentsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the contents service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ContentsPersistence
 * @see ContentsUtil
 * @generated
 */
public class ContentsPersistenceImpl extends BasePersistenceImpl<Contents>
	implements ContentsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContentsUtil} to access the contents persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContentsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContentsModelImpl.ENTITY_CACHE_ENABLED,
			ContentsModelImpl.FINDER_CACHE_ENABLED, ContentsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContentsModelImpl.ENTITY_CACHE_ENABLED,
			ContentsModelImpl.FINDER_CACHE_ENABLED, ContentsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContentsModelImpl.ENTITY_CACHE_ENABLED,
			ContentsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ContentsPersistenceImpl() {
		setModelClass(Contents.class);
	}

	/**
	 * Caches the contents in the entity cache if it is enabled.
	 *
	 * @param contents the contents
	 */
	@Override
	public void cacheResult(Contents contents) {
		EntityCacheUtil.putResult(ContentsModelImpl.ENTITY_CACHE_ENABLED,
			ContentsImpl.class, contents.getPrimaryKey(), contents);

		contents.resetOriginalValues();
	}

	/**
	 * Caches the contentses in the entity cache if it is enabled.
	 *
	 * @param contentses the contentses
	 */
	@Override
	public void cacheResult(List<Contents> contentses) {
		for (Contents contents : contentses) {
			if (EntityCacheUtil.getResult(
						ContentsModelImpl.ENTITY_CACHE_ENABLED,
						ContentsImpl.class, contents.getPrimaryKey()) == null) {
				cacheResult(contents);
			}
			else {
				contents.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contentses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ContentsImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ContentsImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the contents.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Contents contents) {
		EntityCacheUtil.removeResult(ContentsModelImpl.ENTITY_CACHE_ENABLED,
			ContentsImpl.class, contents.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Contents> contentses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Contents contents : contentses) {
			EntityCacheUtil.removeResult(ContentsModelImpl.ENTITY_CACHE_ENABLED,
				ContentsImpl.class, contents.getPrimaryKey());
		}
	}

	/**
	 * Creates a new contents with the primary key. Does not add the contents to the database.
	 *
	 * @param createDate the primary key for the new contents
	 * @return the new contents
	 */
	@Override
	public Contents create(String createDate) {
		Contents contents = new ContentsImpl();

		contents.setNew(true);
		contents.setPrimaryKey(createDate);

		return contents;
	}

	/**
	 * Removes the contents with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param createDate the primary key of the contents
	 * @return the contents that was removed
	 * @throws org.kisti.edison.NoSuchContentsException if a contents with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Contents remove(String createDate)
		throws NoSuchContentsException, SystemException {
		return remove((Serializable)createDate);
	}

	/**
	 * Removes the contents with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the contents
	 * @return the contents that was removed
	 * @throws org.kisti.edison.NoSuchContentsException if a contents with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Contents remove(Serializable primaryKey)
		throws NoSuchContentsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Contents contents = (Contents)session.get(ContentsImpl.class,
					primaryKey);

			if (contents == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContentsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contents);
		}
		catch (NoSuchContentsException nsee) {
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
	protected Contents removeImpl(Contents contents) throws SystemException {
		contents = toUnwrappedModel(contents);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contents)) {
				contents = (Contents)session.get(ContentsImpl.class,
						contents.getPrimaryKeyObj());
			}

			if (contents != null) {
				session.delete(contents);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (contents != null) {
			clearCache(contents);
		}

		return contents;
	}

	@Override
	public Contents updateImpl(org.kisti.edison.model.Contents contents)
		throws SystemException {
		contents = toUnwrappedModel(contents);

		boolean isNew = contents.isNew();

		Session session = null;

		try {
			session = openSession();

			if (contents.isNew()) {
				session.save(contents);

				contents.setNew(false);
			}
			else {
				session.merge(contents);
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

		EntityCacheUtil.putResult(ContentsModelImpl.ENTITY_CACHE_ENABLED,
			ContentsImpl.class, contents.getPrimaryKey(), contents);

		return contents;
	}

	protected Contents toUnwrappedModel(Contents contents) {
		if (contents instanceof ContentsImpl) {
			return contents;
		}

		ContentsImpl contentsImpl = new ContentsImpl();

		contentsImpl.setNew(contents.isNew());
		contentsImpl.setPrimaryKey(contents.getPrimaryKey());

		contentsImpl.setCreateDate(contents.getCreateDate());
		contentsImpl.setCnt(contents.getCnt());

		return contentsImpl;
	}

	/**
	 * Returns the contents with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the contents
	 * @return the contents
	 * @throws org.kisti.edison.NoSuchContentsException if a contents with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Contents findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContentsException, SystemException {
		Contents contents = fetchByPrimaryKey(primaryKey);

		if (contents == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContentsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return contents;
	}

	/**
	 * Returns the contents with the primary key or throws a {@link org.kisti.edison.NoSuchContentsException} if it could not be found.
	 *
	 * @param createDate the primary key of the contents
	 * @return the contents
	 * @throws org.kisti.edison.NoSuchContentsException if a contents with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Contents findByPrimaryKey(String createDate)
		throws NoSuchContentsException, SystemException {
		return findByPrimaryKey((Serializable)createDate);
	}

	/**
	 * Returns the contents with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the contents
	 * @return the contents, or <code>null</code> if a contents with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Contents fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Contents contents = (Contents)EntityCacheUtil.getResult(ContentsModelImpl.ENTITY_CACHE_ENABLED,
				ContentsImpl.class, primaryKey);

		if (contents == _nullContents) {
			return null;
		}

		if (contents == null) {
			Session session = null;

			try {
				session = openSession();

				contents = (Contents)session.get(ContentsImpl.class, primaryKey);

				if (contents != null) {
					cacheResult(contents);
				}
				else {
					EntityCacheUtil.putResult(ContentsModelImpl.ENTITY_CACHE_ENABLED,
						ContentsImpl.class, primaryKey, _nullContents);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ContentsModelImpl.ENTITY_CACHE_ENABLED,
					ContentsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return contents;
	}

	/**
	 * Returns the contents with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param createDate the primary key of the contents
	 * @return the contents, or <code>null</code> if a contents with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Contents fetchByPrimaryKey(String createDate)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)createDate);
	}

	/**
	 * Returns all the contentses.
	 *
	 * @return the contentses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Contents> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contentses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.ContentsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contentses
	 * @param end the upper bound of the range of contentses (not inclusive)
	 * @return the range of contentses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Contents> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contentses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.ContentsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contentses
	 * @param end the upper bound of the range of contentses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contentses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Contents> findAll(int start, int end,
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

		List<Contents> list = (List<Contents>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CONTENTS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTENTS;

				if (pagination) {
					sql = sql.concat(ContentsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Contents>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Contents>(list);
				}
				else {
					list = (List<Contents>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the contentses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Contents contents : findAll()) {
			remove(contents);
		}
	}

	/**
	 * Returns the number of contentses.
	 *
	 * @return the number of contentses
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

				Query q = session.createQuery(_SQL_COUNT_CONTENTS);

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
	 * Initializes the contents persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.Contents")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Contents>> listenersList = new ArrayList<ModelListener<Contents>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Contents>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ContentsImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CONTENTS = "SELECT contents FROM Contents contents";
	private static final String _SQL_COUNT_CONTENTS = "SELECT COUNT(contents) FROM Contents contents";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contents.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Contents exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ContentsPersistenceImpl.class);
	private static Contents _nullContents = new ContentsImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Contents> toCacheModel() {
				return _nullContentsCacheModel;
			}
		};

	private static CacheModel<Contents> _nullContentsCacheModel = new CacheModel<Contents>() {
			@Override
			public Contents toEntityModel() {
				return _nullContents;
			}
		};
}