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

package com.kisti.osp.icecap.service.persistence;

import com.kisti.osp.icecap.NoSuchDataCollectionLayoutException;
import com.kisti.osp.icecap.model.DataCollectionLayout;
import com.kisti.osp.icecap.model.impl.DataCollectionLayoutImpl;
import com.kisti.osp.icecap.model.impl.DataCollectionLayoutModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the data collection layout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataCollectionLayoutPersistence
 * @see DataCollectionLayoutUtil
 * @generated
 */
public class DataCollectionLayoutPersistenceImpl extends BasePersistenceImpl<DataCollectionLayout>
	implements DataCollectionLayoutPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DataCollectionLayoutUtil} to access the data collection layout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DataCollectionLayoutImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionLayoutModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionLayoutModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionLayoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_LAYOUT = new FinderPath(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionLayoutModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionLayoutImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByLayout", new String[] { String.class.getName() },
			DataCollectionLayoutModelImpl.LAYOUTSTR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LAYOUT = new FinderPath(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionLayoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLayout",
			new String[] { String.class.getName() });

	/**
	 * Returns the data collection layout where layoutStr = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionLayoutException} if it could not be found.
	 *
	 * @param layoutStr the layout str
	 * @return the matching data collection layout
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a matching data collection layout could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollectionLayout findByLayout(String layoutStr)
		throws NoSuchDataCollectionLayoutException, SystemException {
		DataCollectionLayout dataCollectionLayout = fetchByLayout(layoutStr);

		if (dataCollectionLayout == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("layoutStr=");
			msg.append(layoutStr);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDataCollectionLayoutException(msg.toString());
		}

		return dataCollectionLayout;
	}

	/**
	 * Returns the data collection layout where layoutStr = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param layoutStr the layout str
	 * @return the matching data collection layout, or <code>null</code> if a matching data collection layout could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollectionLayout fetchByLayout(String layoutStr)
		throws SystemException {
		return fetchByLayout(layoutStr, true);
	}

	/**
	 * Returns the data collection layout where layoutStr = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param layoutStr the layout str
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching data collection layout, or <code>null</code> if a matching data collection layout could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollectionLayout fetchByLayout(String layoutStr,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { layoutStr };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_LAYOUT,
					finderArgs, this);
		}

		if (result instanceof DataCollectionLayout) {
			DataCollectionLayout dataCollectionLayout = (DataCollectionLayout)result;

			if (!Validator.equals(layoutStr, dataCollectionLayout.getLayoutStr())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DATACOLLECTIONLAYOUT_WHERE);

			boolean bindLayoutStr = false;

			if (layoutStr == null) {
				query.append(_FINDER_COLUMN_LAYOUT_LAYOUTSTR_1);
			}
			else if (layoutStr.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LAYOUT_LAYOUTSTR_3);
			}
			else {
				bindLayoutStr = true;

				query.append(_FINDER_COLUMN_LAYOUT_LAYOUTSTR_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLayoutStr) {
					qPos.add(layoutStr);
				}

				List<DataCollectionLayout> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LAYOUT,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"DataCollectionLayoutPersistenceImpl.fetchByLayout(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					DataCollectionLayout dataCollectionLayout = list.get(0);

					result = dataCollectionLayout;

					cacheResult(dataCollectionLayout);

					if ((dataCollectionLayout.getLayoutStr() == null) ||
							!dataCollectionLayout.getLayoutStr()
													 .equals(layoutStr)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LAYOUT,
							finderArgs, dataCollectionLayout);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LAYOUT,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DataCollectionLayout)result;
		}
	}

	/**
	 * Removes the data collection layout where layoutStr = &#63; from the database.
	 *
	 * @param layoutStr the layout str
	 * @return the data collection layout that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollectionLayout removeByLayout(String layoutStr)
		throws NoSuchDataCollectionLayoutException, SystemException {
		DataCollectionLayout dataCollectionLayout = findByLayout(layoutStr);

		return remove(dataCollectionLayout);
	}

	/**
	 * Returns the number of data collection layouts where layoutStr = &#63;.
	 *
	 * @param layoutStr the layout str
	 * @return the number of matching data collection layouts
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByLayout(String layoutStr) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LAYOUT;

		Object[] finderArgs = new Object[] { layoutStr };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATACOLLECTIONLAYOUT_WHERE);

			boolean bindLayoutStr = false;

			if (layoutStr == null) {
				query.append(_FINDER_COLUMN_LAYOUT_LAYOUTSTR_1);
			}
			else if (layoutStr.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LAYOUT_LAYOUTSTR_3);
			}
			else {
				bindLayoutStr = true;

				query.append(_FINDER_COLUMN_LAYOUT_LAYOUTSTR_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLayoutStr) {
					qPos.add(layoutStr);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_LAYOUT_LAYOUTSTR_1 = "dataCollectionLayout.layoutStr IS NULL";
	private static final String _FINDER_COLUMN_LAYOUT_LAYOUTSTR_2 = "dataCollectionLayout.layoutStr = ?";
	private static final String _FINDER_COLUMN_LAYOUT_LAYOUTSTR_3 = "(dataCollectionLayout.layoutStr IS NULL OR dataCollectionLayout.layoutStr = '')";

	public DataCollectionLayoutPersistenceImpl() {
		setModelClass(DataCollectionLayout.class);
	}

	/**
	 * Caches the data collection layout in the entity cache if it is enabled.
	 *
	 * @param dataCollectionLayout the data collection layout
	 */
	@Override
	public void cacheResult(DataCollectionLayout dataCollectionLayout) {
		EntityCacheUtil.putResult(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionLayoutImpl.class,
			dataCollectionLayout.getPrimaryKey(), dataCollectionLayout);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LAYOUT,
			new Object[] { dataCollectionLayout.getLayoutStr() },
			dataCollectionLayout);

		dataCollectionLayout.resetOriginalValues();
	}

	/**
	 * Caches the data collection layouts in the entity cache if it is enabled.
	 *
	 * @param dataCollectionLayouts the data collection layouts
	 */
	@Override
	public void cacheResult(List<DataCollectionLayout> dataCollectionLayouts) {
		for (DataCollectionLayout dataCollectionLayout : dataCollectionLayouts) {
			if (EntityCacheUtil.getResult(
						DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
						DataCollectionLayoutImpl.class,
						dataCollectionLayout.getPrimaryKey()) == null) {
				cacheResult(dataCollectionLayout);
			}
			else {
				dataCollectionLayout.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all data collection layouts.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DataCollectionLayoutImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DataCollectionLayoutImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the data collection layout.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DataCollectionLayout dataCollectionLayout) {
		EntityCacheUtil.removeResult(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionLayoutImpl.class, dataCollectionLayout.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(dataCollectionLayout);
	}

	@Override
	public void clearCache(List<DataCollectionLayout> dataCollectionLayouts) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DataCollectionLayout dataCollectionLayout : dataCollectionLayouts) {
			EntityCacheUtil.removeResult(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
				DataCollectionLayoutImpl.class,
				dataCollectionLayout.getPrimaryKey());

			clearUniqueFindersCache(dataCollectionLayout);
		}
	}

	protected void cacheUniqueFindersCache(
		DataCollectionLayout dataCollectionLayout) {
		if (dataCollectionLayout.isNew()) {
			Object[] args = new Object[] { dataCollectionLayout.getLayoutStr() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_LAYOUT, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LAYOUT, args,
				dataCollectionLayout);
		}
		else {
			DataCollectionLayoutModelImpl dataCollectionLayoutModelImpl = (DataCollectionLayoutModelImpl)dataCollectionLayout;

			if ((dataCollectionLayoutModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_LAYOUT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { dataCollectionLayout.getLayoutStr() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_LAYOUT, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LAYOUT, args,
					dataCollectionLayout);
			}
		}
	}

	protected void clearUniqueFindersCache(
		DataCollectionLayout dataCollectionLayout) {
		DataCollectionLayoutModelImpl dataCollectionLayoutModelImpl = (DataCollectionLayoutModelImpl)dataCollectionLayout;

		Object[] args = new Object[] { dataCollectionLayout.getLayoutStr() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LAYOUT, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LAYOUT, args);

		if ((dataCollectionLayoutModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_LAYOUT.getColumnBitmask()) != 0) {
			args = new Object[] {
					dataCollectionLayoutModelImpl.getOriginalLayoutStr()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LAYOUT, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LAYOUT, args);
		}
	}

	/**
	 * Creates a new data collection layout with the primary key. Does not add the data collection layout to the database.
	 *
	 * @param collectionId the primary key for the new data collection layout
	 * @return the new data collection layout
	 */
	@Override
	public DataCollectionLayout create(long collectionId) {
		DataCollectionLayout dataCollectionLayout = new DataCollectionLayoutImpl();

		dataCollectionLayout.setNew(true);
		dataCollectionLayout.setPrimaryKey(collectionId);

		return dataCollectionLayout;
	}

	/**
	 * Removes the data collection layout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param collectionId the primary key of the data collection layout
	 * @return the data collection layout that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a data collection layout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollectionLayout remove(long collectionId)
		throws NoSuchDataCollectionLayoutException, SystemException {
		return remove((Serializable)collectionId);
	}

	/**
	 * Removes the data collection layout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the data collection layout
	 * @return the data collection layout that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a data collection layout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollectionLayout remove(Serializable primaryKey)
		throws NoSuchDataCollectionLayoutException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DataCollectionLayout dataCollectionLayout = (DataCollectionLayout)session.get(DataCollectionLayoutImpl.class,
					primaryKey);

			if (dataCollectionLayout == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataCollectionLayoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dataCollectionLayout);
		}
		catch (NoSuchDataCollectionLayoutException nsee) {
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
	protected DataCollectionLayout removeImpl(
		DataCollectionLayout dataCollectionLayout) throws SystemException {
		dataCollectionLayout = toUnwrappedModel(dataCollectionLayout);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dataCollectionLayout)) {
				dataCollectionLayout = (DataCollectionLayout)session.get(DataCollectionLayoutImpl.class,
						dataCollectionLayout.getPrimaryKeyObj());
			}

			if (dataCollectionLayout != null) {
				session.delete(dataCollectionLayout);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dataCollectionLayout != null) {
			clearCache(dataCollectionLayout);
		}

		return dataCollectionLayout;
	}

	@Override
	public DataCollectionLayout updateImpl(
		com.kisti.osp.icecap.model.DataCollectionLayout dataCollectionLayout)
		throws SystemException {
		dataCollectionLayout = toUnwrappedModel(dataCollectionLayout);

		boolean isNew = dataCollectionLayout.isNew();

		Session session = null;

		try {
			session = openSession();

			if (dataCollectionLayout.isNew()) {
				session.save(dataCollectionLayout);

				dataCollectionLayout.setNew(false);
			}
			else {
				session.merge(dataCollectionLayout);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DataCollectionLayoutModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionLayoutImpl.class,
			dataCollectionLayout.getPrimaryKey(), dataCollectionLayout);

		clearUniqueFindersCache(dataCollectionLayout);
		cacheUniqueFindersCache(dataCollectionLayout);

		return dataCollectionLayout;
	}

	protected DataCollectionLayout toUnwrappedModel(
		DataCollectionLayout dataCollectionLayout) {
		if (dataCollectionLayout instanceof DataCollectionLayoutImpl) {
			return dataCollectionLayout;
		}

		DataCollectionLayoutImpl dataCollectionLayoutImpl = new DataCollectionLayoutImpl();

		dataCollectionLayoutImpl.setNew(dataCollectionLayout.isNew());
		dataCollectionLayoutImpl.setPrimaryKey(dataCollectionLayout.getPrimaryKey());

		dataCollectionLayoutImpl.setCollectionId(dataCollectionLayout.getCollectionId());
		dataCollectionLayoutImpl.setLayoutStr(dataCollectionLayout.getLayoutStr());

		return dataCollectionLayoutImpl;
	}

	/**
	 * Returns the data collection layout with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the data collection layout
	 * @return the data collection layout
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a data collection layout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollectionLayout findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataCollectionLayoutException, SystemException {
		DataCollectionLayout dataCollectionLayout = fetchByPrimaryKey(primaryKey);

		if (dataCollectionLayout == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataCollectionLayoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dataCollectionLayout;
	}

	/**
	 * Returns the data collection layout with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionLayoutException} if it could not be found.
	 *
	 * @param collectionId the primary key of the data collection layout
	 * @return the data collection layout
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a data collection layout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollectionLayout findByPrimaryKey(long collectionId)
		throws NoSuchDataCollectionLayoutException, SystemException {
		return findByPrimaryKey((Serializable)collectionId);
	}

	/**
	 * Returns the data collection layout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the data collection layout
	 * @return the data collection layout, or <code>null</code> if a data collection layout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollectionLayout fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		DataCollectionLayout dataCollectionLayout = (DataCollectionLayout)EntityCacheUtil.getResult(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
				DataCollectionLayoutImpl.class, primaryKey);

		if (dataCollectionLayout == _nullDataCollectionLayout) {
			return null;
		}

		if (dataCollectionLayout == null) {
			Session session = null;

			try {
				session = openSession();

				dataCollectionLayout = (DataCollectionLayout)session.get(DataCollectionLayoutImpl.class,
						primaryKey);

				if (dataCollectionLayout != null) {
					cacheResult(dataCollectionLayout);
				}
				else {
					EntityCacheUtil.putResult(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
						DataCollectionLayoutImpl.class, primaryKey,
						_nullDataCollectionLayout);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DataCollectionLayoutModelImpl.ENTITY_CACHE_ENABLED,
					DataCollectionLayoutImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dataCollectionLayout;
	}

	/**
	 * Returns the data collection layout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param collectionId the primary key of the data collection layout
	 * @return the data collection layout, or <code>null</code> if a data collection layout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollectionLayout fetchByPrimaryKey(long collectionId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)collectionId);
	}

	/**
	 * Returns all the data collection layouts.
	 *
	 * @return the data collection layouts
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollectionLayout> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collection layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data collection layouts
	 * @param end the upper bound of the range of data collection layouts (not inclusive)
	 * @return the range of data collection layouts
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollectionLayout> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collection layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data collection layouts
	 * @param end the upper bound of the range of data collection layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of data collection layouts
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollectionLayout> findAll(int start, int end,
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

		List<DataCollectionLayout> list = (List<DataCollectionLayout>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DATACOLLECTIONLAYOUT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DATACOLLECTIONLAYOUT;

				if (pagination) {
					sql = sql.concat(DataCollectionLayoutModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DataCollectionLayout>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollectionLayout>(list);
				}
				else {
					list = (List<DataCollectionLayout>)QueryUtil.list(q,
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
	 * Removes all the data collection layouts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (DataCollectionLayout dataCollectionLayout : findAll()) {
			remove(dataCollectionLayout);
		}
	}

	/**
	 * Returns the number of data collection layouts.
	 *
	 * @return the number of data collection layouts
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

				Query q = session.createQuery(_SQL_COUNT_DATACOLLECTIONLAYOUT);

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
	 * Initializes the data collection layout persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.osp.icecap.model.DataCollectionLayout")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DataCollectionLayout>> listenersList = new ArrayList<ModelListener<DataCollectionLayout>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DataCollectionLayout>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DataCollectionLayoutImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DATACOLLECTIONLAYOUT = "SELECT dataCollectionLayout FROM DataCollectionLayout dataCollectionLayout";
	private static final String _SQL_SELECT_DATACOLLECTIONLAYOUT_WHERE = "SELECT dataCollectionLayout FROM DataCollectionLayout dataCollectionLayout WHERE ";
	private static final String _SQL_COUNT_DATACOLLECTIONLAYOUT = "SELECT COUNT(dataCollectionLayout) FROM DataCollectionLayout dataCollectionLayout";
	private static final String _SQL_COUNT_DATACOLLECTIONLAYOUT_WHERE = "SELECT COUNT(dataCollectionLayout) FROM DataCollectionLayout dataCollectionLayout WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dataCollectionLayout.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DataCollectionLayout exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DataCollectionLayout exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DataCollectionLayoutPersistenceImpl.class);
	private static DataCollectionLayout _nullDataCollectionLayout = new DataCollectionLayoutImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DataCollectionLayout> toCacheModel() {
				return _nullDataCollectionLayoutCacheModel;
			}
		};

	private static CacheModel<DataCollectionLayout> _nullDataCollectionLayoutCacheModel =
		new CacheModel<DataCollectionLayout>() {
			@Override
			public DataCollectionLayout toEntityModel() {
				return _nullDataCollectionLayout;
			}
		};
}