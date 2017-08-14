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

import com.kisti.osp.icecap.NoSuchDataTypeStructureException;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.model.impl.DataTypeStructureImpl;
import com.kisti.osp.icecap.model.impl.DataTypeStructureModelImpl;

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
 * The persistence implementation for the data type structure service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeStructurePersistence
 * @see DataTypeStructureUtil
 * @generated
 */
public class DataTypeStructurePersistenceImpl extends BasePersistenceImpl<DataTypeStructure>
	implements DataTypeStructurePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DataTypeStructureUtil} to access the data type structure persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DataTypeStructureImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeStructureModelImpl.FINDER_CACHE_ENABLED,
			DataTypeStructureImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeStructureModelImpl.FINDER_CACHE_ENABLED,
			DataTypeStructureImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeStructureModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_TYPEID = new FinderPath(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeStructureModelImpl.FINDER_CACHE_ENABLED,
			DataTypeStructureImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByTypeID", new String[] { Long.class.getName() },
			DataTypeStructureModelImpl.TYPEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPEID = new FinderPath(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeStructureModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTypeID",
			new String[] { Long.class.getName() });

	/**
	 * Returns the data type structure where typeId = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeStructureException} if it could not be found.
	 *
	 * @param typeId the type ID
	 * @return the matching data type structure
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a matching data type structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure findByTypeID(long typeId)
		throws NoSuchDataTypeStructureException, SystemException {
		DataTypeStructure dataTypeStructure = fetchByTypeID(typeId);

		if (dataTypeStructure == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("typeId=");
			msg.append(typeId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDataTypeStructureException(msg.toString());
		}

		return dataTypeStructure;
	}

	/**
	 * Returns the data type structure where typeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param typeId the type ID
	 * @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure fetchByTypeID(long typeId)
		throws SystemException {
		return fetchByTypeID(typeId, true);
	}

	/**
	 * Returns the data type structure where typeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param typeId the type ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure fetchByTypeID(long typeId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { typeId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TYPEID,
					finderArgs, this);
		}

		if (result instanceof DataTypeStructure) {
			DataTypeStructure dataTypeStructure = (DataTypeStructure)result;

			if ((typeId != dataTypeStructure.getTypeId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DATATYPESTRUCTURE_WHERE);

			query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

				List<DataTypeStructure> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TYPEID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"DataTypeStructurePersistenceImpl.fetchByTypeID(long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					DataTypeStructure dataTypeStructure = list.get(0);

					result = dataTypeStructure;

					cacheResult(dataTypeStructure);

					if ((dataTypeStructure.getTypeId() != typeId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TYPEID,
							finderArgs, dataTypeStructure);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TYPEID,
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
			return (DataTypeStructure)result;
		}
	}

	/**
	 * Removes the data type structure where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 * @return the data type structure that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure removeByTypeID(long typeId)
		throws NoSuchDataTypeStructureException, SystemException {
		DataTypeStructure dataTypeStructure = findByTypeID(typeId);

		return remove(dataTypeStructure);
	}

	/**
	 * Returns the number of data type structures where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching data type structures
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByTypeID(long typeId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TYPEID;

		Object[] finderArgs = new Object[] { typeId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATATYPESTRUCTURE_WHERE);

			query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

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

	private static final String _FINDER_COLUMN_TYPEID_TYPEID_2 = "dataTypeStructure.typeId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_STRUCTURE = new FinderPath(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeStructureModelImpl.FINDER_CACHE_ENABLED,
			DataTypeStructureImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByStructure", new String[] { String.class.getName() },
			DataTypeStructureModelImpl.STRUCTURE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STRUCTURE = new FinderPath(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeStructureModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStructure",
			new String[] { String.class.getName() });

	/**
	 * Returns the data type structure where structure = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeStructureException} if it could not be found.
	 *
	 * @param structure the structure
	 * @return the matching data type structure
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a matching data type structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure findByStructure(String structure)
		throws NoSuchDataTypeStructureException, SystemException {
		DataTypeStructure dataTypeStructure = fetchByStructure(structure);

		if (dataTypeStructure == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("structure=");
			msg.append(structure);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDataTypeStructureException(msg.toString());
		}

		return dataTypeStructure;
	}

	/**
	 * Returns the data type structure where structure = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param structure the structure
	 * @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure fetchByStructure(String structure)
		throws SystemException {
		return fetchByStructure(structure, true);
	}

	/**
	 * Returns the data type structure where structure = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param structure the structure
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure fetchByStructure(String structure,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { structure };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_STRUCTURE,
					finderArgs, this);
		}

		if (result instanceof DataTypeStructure) {
			DataTypeStructure dataTypeStructure = (DataTypeStructure)result;

			if (!Validator.equals(structure, dataTypeStructure.getStructure())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DATATYPESTRUCTURE_WHERE);

			boolean bindStructure = false;

			if (structure == null) {
				query.append(_FINDER_COLUMN_STRUCTURE_STRUCTURE_1);
			}
			else if (structure.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STRUCTURE_STRUCTURE_3);
			}
			else {
				bindStructure = true;

				query.append(_FINDER_COLUMN_STRUCTURE_STRUCTURE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStructure) {
					qPos.add(structure);
				}

				List<DataTypeStructure> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_STRUCTURE,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"DataTypeStructurePersistenceImpl.fetchByStructure(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					DataTypeStructure dataTypeStructure = list.get(0);

					result = dataTypeStructure;

					cacheResult(dataTypeStructure);

					if ((dataTypeStructure.getStructure() == null) ||
							!dataTypeStructure.getStructure().equals(structure)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_STRUCTURE,
							finderArgs, dataTypeStructure);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_STRUCTURE,
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
			return (DataTypeStructure)result;
		}
	}

	/**
	 * Removes the data type structure where structure = &#63; from the database.
	 *
	 * @param structure the structure
	 * @return the data type structure that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure removeByStructure(String structure)
		throws NoSuchDataTypeStructureException, SystemException {
		DataTypeStructure dataTypeStructure = findByStructure(structure);

		return remove(dataTypeStructure);
	}

	/**
	 * Returns the number of data type structures where structure = &#63;.
	 *
	 * @param structure the structure
	 * @return the number of matching data type structures
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByStructure(String structure) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STRUCTURE;

		Object[] finderArgs = new Object[] { structure };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATATYPESTRUCTURE_WHERE);

			boolean bindStructure = false;

			if (structure == null) {
				query.append(_FINDER_COLUMN_STRUCTURE_STRUCTURE_1);
			}
			else if (structure.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STRUCTURE_STRUCTURE_3);
			}
			else {
				bindStructure = true;

				query.append(_FINDER_COLUMN_STRUCTURE_STRUCTURE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStructure) {
					qPos.add(structure);
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

	private static final String _FINDER_COLUMN_STRUCTURE_STRUCTURE_1 = "dataTypeStructure.structure IS NULL";
	private static final String _FINDER_COLUMN_STRUCTURE_STRUCTURE_2 = "dataTypeStructure.structure = ?";
	private static final String _FINDER_COLUMN_STRUCTURE_STRUCTURE_3 = "(dataTypeStructure.structure IS NULL OR dataTypeStructure.structure = '')";

	public DataTypeStructurePersistenceImpl() {
		setModelClass(DataTypeStructure.class);
	}

	/**
	 * Caches the data type structure in the entity cache if it is enabled.
	 *
	 * @param dataTypeStructure the data type structure
	 */
	@Override
	public void cacheResult(DataTypeStructure dataTypeStructure) {
		EntityCacheUtil.putResult(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeStructureImpl.class, dataTypeStructure.getPrimaryKey(),
			dataTypeStructure);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TYPEID,
			new Object[] { dataTypeStructure.getTypeId() }, dataTypeStructure);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_STRUCTURE,
			new Object[] { dataTypeStructure.getStructure() }, dataTypeStructure);

		dataTypeStructure.resetOriginalValues();
	}

	/**
	 * Caches the data type structures in the entity cache if it is enabled.
	 *
	 * @param dataTypeStructures the data type structures
	 */
	@Override
	public void cacheResult(List<DataTypeStructure> dataTypeStructures) {
		for (DataTypeStructure dataTypeStructure : dataTypeStructures) {
			if (EntityCacheUtil.getResult(
						DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
						DataTypeStructureImpl.class,
						dataTypeStructure.getPrimaryKey()) == null) {
				cacheResult(dataTypeStructure);
			}
			else {
				dataTypeStructure.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all data type structures.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DataTypeStructureImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DataTypeStructureImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the data type structure.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DataTypeStructure dataTypeStructure) {
		EntityCacheUtil.removeResult(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeStructureImpl.class, dataTypeStructure.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(dataTypeStructure);
	}

	@Override
	public void clearCache(List<DataTypeStructure> dataTypeStructures) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DataTypeStructure dataTypeStructure : dataTypeStructures) {
			EntityCacheUtil.removeResult(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
				DataTypeStructureImpl.class, dataTypeStructure.getPrimaryKey());

			clearUniqueFindersCache(dataTypeStructure);
		}
	}

	protected void cacheUniqueFindersCache(DataTypeStructure dataTypeStructure) {
		if (dataTypeStructure.isNew()) {
			Object[] args = new Object[] { dataTypeStructure.getTypeId() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPEID, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TYPEID, args,
				dataTypeStructure);

			args = new Object[] { dataTypeStructure.getStructure() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_STRUCTURE, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_STRUCTURE, args,
				dataTypeStructure);
		}
		else {
			DataTypeStructureModelImpl dataTypeStructureModelImpl = (DataTypeStructureModelImpl)dataTypeStructure;

			if ((dataTypeStructureModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TYPEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { dataTypeStructure.getTypeId() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPEID, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TYPEID, args,
					dataTypeStructure);
			}

			if ((dataTypeStructureModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_STRUCTURE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { dataTypeStructure.getStructure() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_STRUCTURE, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_STRUCTURE, args,
					dataTypeStructure);
			}
		}
	}

	protected void clearUniqueFindersCache(DataTypeStructure dataTypeStructure) {
		DataTypeStructureModelImpl dataTypeStructureModelImpl = (DataTypeStructureModelImpl)dataTypeStructure;

		Object[] args = new Object[] { dataTypeStructure.getTypeId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TYPEID, args);

		if ((dataTypeStructureModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TYPEID.getColumnBitmask()) != 0) {
			args = new Object[] { dataTypeStructureModelImpl.getOriginalTypeId() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TYPEID, args);
		}

		args = new Object[] { dataTypeStructure.getStructure() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STRUCTURE, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_STRUCTURE, args);

		if ((dataTypeStructureModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_STRUCTURE.getColumnBitmask()) != 0) {
			args = new Object[] {
					dataTypeStructureModelImpl.getOriginalStructure()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STRUCTURE, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_STRUCTURE, args);
		}
	}

	/**
	 * Creates a new data type structure with the primary key. Does not add the data type structure to the database.
	 *
	 * @param typeId the primary key for the new data type structure
	 * @return the new data type structure
	 */
	@Override
	public DataTypeStructure create(long typeId) {
		DataTypeStructure dataTypeStructure = new DataTypeStructureImpl();

		dataTypeStructure.setNew(true);
		dataTypeStructure.setPrimaryKey(typeId);

		return dataTypeStructure;
	}

	/**
	 * Removes the data type structure with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param typeId the primary key of the data type structure
	 * @return the data type structure that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a data type structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure remove(long typeId)
		throws NoSuchDataTypeStructureException, SystemException {
		return remove((Serializable)typeId);
	}

	/**
	 * Removes the data type structure with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the data type structure
	 * @return the data type structure that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a data type structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure remove(Serializable primaryKey)
		throws NoSuchDataTypeStructureException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DataTypeStructure dataTypeStructure = (DataTypeStructure)session.get(DataTypeStructureImpl.class,
					primaryKey);

			if (dataTypeStructure == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataTypeStructureException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dataTypeStructure);
		}
		catch (NoSuchDataTypeStructureException nsee) {
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
	protected DataTypeStructure removeImpl(DataTypeStructure dataTypeStructure)
		throws SystemException {
		dataTypeStructure = toUnwrappedModel(dataTypeStructure);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dataTypeStructure)) {
				dataTypeStructure = (DataTypeStructure)session.get(DataTypeStructureImpl.class,
						dataTypeStructure.getPrimaryKeyObj());
			}

			if (dataTypeStructure != null) {
				session.delete(dataTypeStructure);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dataTypeStructure != null) {
			clearCache(dataTypeStructure);
		}

		return dataTypeStructure;
	}

	@Override
	public DataTypeStructure updateImpl(
		com.kisti.osp.icecap.model.DataTypeStructure dataTypeStructure)
		throws SystemException {
		dataTypeStructure = toUnwrappedModel(dataTypeStructure);

		boolean isNew = dataTypeStructure.isNew();

		Session session = null;

		try {
			session = openSession();

			if (dataTypeStructure.isNew()) {
				session.save(dataTypeStructure);

				dataTypeStructure.setNew(false);
			}
			else {
				session.merge(dataTypeStructure);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DataTypeStructureModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeStructureImpl.class, dataTypeStructure.getPrimaryKey(),
			dataTypeStructure);

		clearUniqueFindersCache(dataTypeStructure);
		cacheUniqueFindersCache(dataTypeStructure);

		return dataTypeStructure;
	}

	protected DataTypeStructure toUnwrappedModel(
		DataTypeStructure dataTypeStructure) {
		if (dataTypeStructure instanceof DataTypeStructureImpl) {
			return dataTypeStructure;
		}

		DataTypeStructureImpl dataTypeStructureImpl = new DataTypeStructureImpl();

		dataTypeStructureImpl.setNew(dataTypeStructure.isNew());
		dataTypeStructureImpl.setPrimaryKey(dataTypeStructure.getPrimaryKey());

		dataTypeStructureImpl.setTypeId(dataTypeStructure.getTypeId());
		dataTypeStructureImpl.setStructure(dataTypeStructure.getStructure());

		return dataTypeStructureImpl;
	}

	/**
	 * Returns the data type structure with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the data type structure
	 * @return the data type structure
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a data type structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataTypeStructureException, SystemException {
		DataTypeStructure dataTypeStructure = fetchByPrimaryKey(primaryKey);

		if (dataTypeStructure == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataTypeStructureException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dataTypeStructure;
	}

	/**
	 * Returns the data type structure with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeStructureException} if it could not be found.
	 *
	 * @param typeId the primary key of the data type structure
	 * @return the data type structure
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a data type structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure findByPrimaryKey(long typeId)
		throws NoSuchDataTypeStructureException, SystemException {
		return findByPrimaryKey((Serializable)typeId);
	}

	/**
	 * Returns the data type structure with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the data type structure
	 * @return the data type structure, or <code>null</code> if a data type structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		DataTypeStructure dataTypeStructure = (DataTypeStructure)EntityCacheUtil.getResult(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
				DataTypeStructureImpl.class, primaryKey);

		if (dataTypeStructure == _nullDataTypeStructure) {
			return null;
		}

		if (dataTypeStructure == null) {
			Session session = null;

			try {
				session = openSession();

				dataTypeStructure = (DataTypeStructure)session.get(DataTypeStructureImpl.class,
						primaryKey);

				if (dataTypeStructure != null) {
					cacheResult(dataTypeStructure);
				}
				else {
					EntityCacheUtil.putResult(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
						DataTypeStructureImpl.class, primaryKey,
						_nullDataTypeStructure);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DataTypeStructureModelImpl.ENTITY_CACHE_ENABLED,
					DataTypeStructureImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dataTypeStructure;
	}

	/**
	 * Returns the data type structure with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param typeId the primary key of the data type structure
	 * @return the data type structure, or <code>null</code> if a data type structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeStructure fetchByPrimaryKey(long typeId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)typeId);
	}

	/**
	 * Returns all the data type structures.
	 *
	 * @return the data type structures
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeStructure> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data type structures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeStructureModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data type structures
	 * @param end the upper bound of the range of data type structures (not inclusive)
	 * @return the range of data type structures
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeStructure> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the data type structures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeStructureModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data type structures
	 * @param end the upper bound of the range of data type structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of data type structures
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeStructure> findAll(int start, int end,
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

		List<DataTypeStructure> list = (List<DataTypeStructure>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DATATYPESTRUCTURE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DATATYPESTRUCTURE;

				if (pagination) {
					sql = sql.concat(DataTypeStructureModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DataTypeStructure>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataTypeStructure>(list);
				}
				else {
					list = (List<DataTypeStructure>)QueryUtil.list(q,
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
	 * Removes all the data type structures from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (DataTypeStructure dataTypeStructure : findAll()) {
			remove(dataTypeStructure);
		}
	}

	/**
	 * Returns the number of data type structures.
	 *
	 * @return the number of data type structures
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

				Query q = session.createQuery(_SQL_COUNT_DATATYPESTRUCTURE);

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
	 * Initializes the data type structure persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.osp.icecap.model.DataTypeStructure")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DataTypeStructure>> listenersList = new ArrayList<ModelListener<DataTypeStructure>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DataTypeStructure>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DataTypeStructureImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DATATYPESTRUCTURE = "SELECT dataTypeStructure FROM DataTypeStructure dataTypeStructure";
	private static final String _SQL_SELECT_DATATYPESTRUCTURE_WHERE = "SELECT dataTypeStructure FROM DataTypeStructure dataTypeStructure WHERE ";
	private static final String _SQL_COUNT_DATATYPESTRUCTURE = "SELECT COUNT(dataTypeStructure) FROM DataTypeStructure dataTypeStructure";
	private static final String _SQL_COUNT_DATATYPESTRUCTURE_WHERE = "SELECT COUNT(dataTypeStructure) FROM DataTypeStructure dataTypeStructure WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dataTypeStructure.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DataTypeStructure exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DataTypeStructure exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DataTypeStructurePersistenceImpl.class);
	private static DataTypeStructure _nullDataTypeStructure = new DataTypeStructureImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DataTypeStructure> toCacheModel() {
				return _nullDataTypeStructureCacheModel;
			}
		};

	private static CacheModel<DataTypeStructure> _nullDataTypeStructureCacheModel =
		new CacheModel<DataTypeStructure>() {
			@Override
			public DataTypeStructure toEntityModel() {
				return _nullDataTypeStructure;
			}
		};
}