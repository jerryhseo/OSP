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

import com.kisti.osp.icecap.NoSuchDataEntryProvenanceException;
import com.kisti.osp.icecap.model.DataEntryProvenance;
import com.kisti.osp.icecap.model.impl.DataEntryProvenanceImpl;
import com.kisti.osp.icecap.model.impl.DataEntryProvenanceModelImpl;

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
 * The persistence implementation for the data entry provenance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryProvenancePersistence
 * @see DataEntryProvenanceUtil
 * @generated
 */
public class DataEntryProvenancePersistenceImpl extends BasePersistenceImpl<DataEntryProvenance>
	implements DataEntryProvenancePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DataEntryProvenanceUtil} to access the data entry provenance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DataEntryProvenanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryProvenanceModelImpl.FINDER_CACHE_ENABLED,
			DataEntryProvenanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryProvenanceModelImpl.FINDER_CACHE_ENABLED,
			DataEntryProvenanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryProvenanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_INPUTDATA = new FinderPath(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryProvenanceModelImpl.FINDER_CACHE_ENABLED,
			DataEntryProvenanceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByinputData", new String[] { String.class.getName() },
			DataEntryProvenanceModelImpl.INPUTDATA_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_INPUTDATA = new FinderPath(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryProvenanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByinputData",
			new String[] { String.class.getName() });

	/**
	 * Returns the data entry provenance where inputData = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataEntryProvenanceException} if it could not be found.
	 *
	 * @param inputData the input data
	 * @return the matching data entry provenance
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException if a matching data entry provenance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance findByinputData(String inputData)
		throws NoSuchDataEntryProvenanceException, SystemException {
		DataEntryProvenance dataEntryProvenance = fetchByinputData(inputData);

		if (dataEntryProvenance == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("inputData=");
			msg.append(inputData);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDataEntryProvenanceException(msg.toString());
		}

		return dataEntryProvenance;
	}

	/**
	 * Returns the data entry provenance where inputData = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param inputData the input data
	 * @return the matching data entry provenance, or <code>null</code> if a matching data entry provenance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance fetchByinputData(String inputData)
		throws SystemException {
		return fetchByinputData(inputData, true);
	}

	/**
	 * Returns the data entry provenance where inputData = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param inputData the input data
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching data entry provenance, or <code>null</code> if a matching data entry provenance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance fetchByinputData(String inputData,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { inputData };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_INPUTDATA,
					finderArgs, this);
		}

		if (result instanceof DataEntryProvenance) {
			DataEntryProvenance dataEntryProvenance = (DataEntryProvenance)result;

			if (!Validator.equals(inputData, dataEntryProvenance.getInputData())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DATAENTRYPROVENANCE_WHERE);

			boolean bindInputData = false;

			if (inputData == null) {
				query.append(_FINDER_COLUMN_INPUTDATA_INPUTDATA_1);
			}
			else if (inputData.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_INPUTDATA_INPUTDATA_3);
			}
			else {
				bindInputData = true;

				query.append(_FINDER_COLUMN_INPUTDATA_INPUTDATA_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindInputData) {
					qPos.add(inputData);
				}

				List<DataEntryProvenance> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_INPUTDATA,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"DataEntryProvenancePersistenceImpl.fetchByinputData(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					DataEntryProvenance dataEntryProvenance = list.get(0);

					result = dataEntryProvenance;

					cacheResult(dataEntryProvenance);

					if ((dataEntryProvenance.getInputData() == null) ||
							!dataEntryProvenance.getInputData().equals(inputData)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_INPUTDATA,
							finderArgs, dataEntryProvenance);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_INPUTDATA,
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
			return (DataEntryProvenance)result;
		}
	}

	/**
	 * Removes the data entry provenance where inputData = &#63; from the database.
	 *
	 * @param inputData the input data
	 * @return the data entry provenance that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance removeByinputData(String inputData)
		throws NoSuchDataEntryProvenanceException, SystemException {
		DataEntryProvenance dataEntryProvenance = findByinputData(inputData);

		return remove(dataEntryProvenance);
	}

	/**
	 * Returns the number of data entry provenances where inputData = &#63;.
	 *
	 * @param inputData the input data
	 * @return the number of matching data entry provenances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByinputData(String inputData) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_INPUTDATA;

		Object[] finderArgs = new Object[] { inputData };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATAENTRYPROVENANCE_WHERE);

			boolean bindInputData = false;

			if (inputData == null) {
				query.append(_FINDER_COLUMN_INPUTDATA_INPUTDATA_1);
			}
			else if (inputData.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_INPUTDATA_INPUTDATA_3);
			}
			else {
				bindInputData = true;

				query.append(_FINDER_COLUMN_INPUTDATA_INPUTDATA_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindInputData) {
					qPos.add(inputData);
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

	private static final String _FINDER_COLUMN_INPUTDATA_INPUTDATA_1 = "dataEntryProvenance.inputData IS NULL";
	private static final String _FINDER_COLUMN_INPUTDATA_INPUTDATA_2 = "dataEntryProvenance.inputData = ?";
	private static final String _FINDER_COLUMN_INPUTDATA_INPUTDATA_3 = "(dataEntryProvenance.inputData IS NULL OR dataEntryProvenance.inputData = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_PROVSTRUCTURE = new FinderPath(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryProvenanceModelImpl.FINDER_CACHE_ENABLED,
			DataEntryProvenanceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByPROVStructure", new String[] { String.class.getName() },
			DataEntryProvenanceModelImpl.PROVSTRUCTURE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROVSTRUCTURE = new FinderPath(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryProvenanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPROVStructure",
			new String[] { String.class.getName() });

	/**
	 * Returns the data entry provenance where PROVStructure = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataEntryProvenanceException} if it could not be found.
	 *
	 * @param PROVStructure the p r o v structure
	 * @return the matching data entry provenance
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException if a matching data entry provenance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance findByPROVStructure(String PROVStructure)
		throws NoSuchDataEntryProvenanceException, SystemException {
		DataEntryProvenance dataEntryProvenance = fetchByPROVStructure(PROVStructure);

		if (dataEntryProvenance == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("PROVStructure=");
			msg.append(PROVStructure);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDataEntryProvenanceException(msg.toString());
		}

		return dataEntryProvenance;
	}

	/**
	 * Returns the data entry provenance where PROVStructure = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param PROVStructure the p r o v structure
	 * @return the matching data entry provenance, or <code>null</code> if a matching data entry provenance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance fetchByPROVStructure(String PROVStructure)
		throws SystemException {
		return fetchByPROVStructure(PROVStructure, true);
	}

	/**
	 * Returns the data entry provenance where PROVStructure = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param PROVStructure the p r o v structure
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching data entry provenance, or <code>null</code> if a matching data entry provenance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance fetchByPROVStructure(String PROVStructure,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { PROVStructure };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PROVSTRUCTURE,
					finderArgs, this);
		}

		if (result instanceof DataEntryProvenance) {
			DataEntryProvenance dataEntryProvenance = (DataEntryProvenance)result;

			if (!Validator.equals(PROVStructure,
						dataEntryProvenance.getPROVStructure())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DATAENTRYPROVENANCE_WHERE);

			boolean bindPROVStructure = false;

			if (PROVStructure == null) {
				query.append(_FINDER_COLUMN_PROVSTRUCTURE_PROVSTRUCTURE_1);
			}
			else if (PROVStructure.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PROVSTRUCTURE_PROVSTRUCTURE_3);
			}
			else {
				bindPROVStructure = true;

				query.append(_FINDER_COLUMN_PROVSTRUCTURE_PROVSTRUCTURE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPROVStructure) {
					qPos.add(PROVStructure);
				}

				List<DataEntryProvenance> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROVSTRUCTURE,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"DataEntryProvenancePersistenceImpl.fetchByPROVStructure(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					DataEntryProvenance dataEntryProvenance = list.get(0);

					result = dataEntryProvenance;

					cacheResult(dataEntryProvenance);

					if ((dataEntryProvenance.getPROVStructure() == null) ||
							!dataEntryProvenance.getPROVStructure()
													.equals(PROVStructure)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROVSTRUCTURE,
							finderArgs, dataEntryProvenance);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROVSTRUCTURE,
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
			return (DataEntryProvenance)result;
		}
	}

	/**
	 * Removes the data entry provenance where PROVStructure = &#63; from the database.
	 *
	 * @param PROVStructure the p r o v structure
	 * @return the data entry provenance that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance removeByPROVStructure(String PROVStructure)
		throws NoSuchDataEntryProvenanceException, SystemException {
		DataEntryProvenance dataEntryProvenance = findByPROVStructure(PROVStructure);

		return remove(dataEntryProvenance);
	}

	/**
	 * Returns the number of data entry provenances where PROVStructure = &#63;.
	 *
	 * @param PROVStructure the p r o v structure
	 * @return the number of matching data entry provenances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByPROVStructure(String PROVStructure)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PROVSTRUCTURE;

		Object[] finderArgs = new Object[] { PROVStructure };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATAENTRYPROVENANCE_WHERE);

			boolean bindPROVStructure = false;

			if (PROVStructure == null) {
				query.append(_FINDER_COLUMN_PROVSTRUCTURE_PROVSTRUCTURE_1);
			}
			else if (PROVStructure.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PROVSTRUCTURE_PROVSTRUCTURE_3);
			}
			else {
				bindPROVStructure = true;

				query.append(_FINDER_COLUMN_PROVSTRUCTURE_PROVSTRUCTURE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPROVStructure) {
					qPos.add(PROVStructure);
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

	private static final String _FINDER_COLUMN_PROVSTRUCTURE_PROVSTRUCTURE_1 = "dataEntryProvenance.PROVStructure IS NULL";
	private static final String _FINDER_COLUMN_PROVSTRUCTURE_PROVSTRUCTURE_2 = "dataEntryProvenance.PROVStructure = ?";
	private static final String _FINDER_COLUMN_PROVSTRUCTURE_PROVSTRUCTURE_3 = "(dataEntryProvenance.PROVStructure IS NULL OR dataEntryProvenance.PROVStructure = '')";

	public DataEntryProvenancePersistenceImpl() {
		setModelClass(DataEntryProvenance.class);
	}

	/**
	 * Caches the data entry provenance in the entity cache if it is enabled.
	 *
	 * @param dataEntryProvenance the data entry provenance
	 */
	@Override
	public void cacheResult(DataEntryProvenance dataEntryProvenance) {
		EntityCacheUtil.putResult(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryProvenanceImpl.class, dataEntryProvenance.getPrimaryKey(),
			dataEntryProvenance);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_INPUTDATA,
			new Object[] { dataEntryProvenance.getInputData() },
			dataEntryProvenance);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROVSTRUCTURE,
			new Object[] { dataEntryProvenance.getPROVStructure() },
			dataEntryProvenance);

		dataEntryProvenance.resetOriginalValues();
	}

	/**
	 * Caches the data entry provenances in the entity cache if it is enabled.
	 *
	 * @param dataEntryProvenances the data entry provenances
	 */
	@Override
	public void cacheResult(List<DataEntryProvenance> dataEntryProvenances) {
		for (DataEntryProvenance dataEntryProvenance : dataEntryProvenances) {
			if (EntityCacheUtil.getResult(
						DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
						DataEntryProvenanceImpl.class,
						dataEntryProvenance.getPrimaryKey()) == null) {
				cacheResult(dataEntryProvenance);
			}
			else {
				dataEntryProvenance.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all data entry provenances.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DataEntryProvenanceImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DataEntryProvenanceImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the data entry provenance.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DataEntryProvenance dataEntryProvenance) {
		EntityCacheUtil.removeResult(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryProvenanceImpl.class, dataEntryProvenance.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(dataEntryProvenance);
	}

	@Override
	public void clearCache(List<DataEntryProvenance> dataEntryProvenances) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DataEntryProvenance dataEntryProvenance : dataEntryProvenances) {
			EntityCacheUtil.removeResult(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
				DataEntryProvenanceImpl.class,
				dataEntryProvenance.getPrimaryKey());

			clearUniqueFindersCache(dataEntryProvenance);
		}
	}

	protected void cacheUniqueFindersCache(
		DataEntryProvenance dataEntryProvenance) {
		if (dataEntryProvenance.isNew()) {
			Object[] args = new Object[] { dataEntryProvenance.getInputData() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_INPUTDATA, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_INPUTDATA, args,
				dataEntryProvenance);

			args = new Object[] { dataEntryProvenance.getPROVStructure() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROVSTRUCTURE, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROVSTRUCTURE, args,
				dataEntryProvenance);
		}
		else {
			DataEntryProvenanceModelImpl dataEntryProvenanceModelImpl = (DataEntryProvenanceModelImpl)dataEntryProvenance;

			if ((dataEntryProvenanceModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_INPUTDATA.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { dataEntryProvenance.getInputData() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_INPUTDATA, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_INPUTDATA, args,
					dataEntryProvenance);
			}

			if ((dataEntryProvenanceModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_PROVSTRUCTURE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataEntryProvenance.getPROVStructure()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROVSTRUCTURE,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROVSTRUCTURE,
					args, dataEntryProvenance);
			}
		}
	}

	protected void clearUniqueFindersCache(
		DataEntryProvenance dataEntryProvenance) {
		DataEntryProvenanceModelImpl dataEntryProvenanceModelImpl = (DataEntryProvenanceModelImpl)dataEntryProvenance;

		Object[] args = new Object[] { dataEntryProvenance.getInputData() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_INPUTDATA, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_INPUTDATA, args);

		if ((dataEntryProvenanceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_INPUTDATA.getColumnBitmask()) != 0) {
			args = new Object[] {
					dataEntryProvenanceModelImpl.getOriginalInputData()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_INPUTDATA, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_INPUTDATA, args);
		}

		args = new Object[] { dataEntryProvenance.getPROVStructure() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROVSTRUCTURE, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROVSTRUCTURE, args);

		if ((dataEntryProvenanceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PROVSTRUCTURE.getColumnBitmask()) != 0) {
			args = new Object[] {
					dataEntryProvenanceModelImpl.getOriginalPROVStructure()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROVSTRUCTURE,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROVSTRUCTURE,
				args);
		}
	}

	/**
	 * Creates a new data entry provenance with the primary key. Does not add the data entry provenance to the database.
	 *
	 * @param entryId the primary key for the new data entry provenance
	 * @return the new data entry provenance
	 */
	@Override
	public DataEntryProvenance create(long entryId) {
		DataEntryProvenance dataEntryProvenance = new DataEntryProvenanceImpl();

		dataEntryProvenance.setNew(true);
		dataEntryProvenance.setPrimaryKey(entryId);

		return dataEntryProvenance;
	}

	/**
	 * Removes the data entry provenance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the data entry provenance
	 * @return the data entry provenance that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException if a data entry provenance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance remove(long entryId)
		throws NoSuchDataEntryProvenanceException, SystemException {
		return remove((Serializable)entryId);
	}

	/**
	 * Removes the data entry provenance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the data entry provenance
	 * @return the data entry provenance that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException if a data entry provenance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance remove(Serializable primaryKey)
		throws NoSuchDataEntryProvenanceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DataEntryProvenance dataEntryProvenance = (DataEntryProvenance)session.get(DataEntryProvenanceImpl.class,
					primaryKey);

			if (dataEntryProvenance == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataEntryProvenanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dataEntryProvenance);
		}
		catch (NoSuchDataEntryProvenanceException nsee) {
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
	protected DataEntryProvenance removeImpl(
		DataEntryProvenance dataEntryProvenance) throws SystemException {
		dataEntryProvenance = toUnwrappedModel(dataEntryProvenance);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dataEntryProvenance)) {
				dataEntryProvenance = (DataEntryProvenance)session.get(DataEntryProvenanceImpl.class,
						dataEntryProvenance.getPrimaryKeyObj());
			}

			if (dataEntryProvenance != null) {
				session.delete(dataEntryProvenance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dataEntryProvenance != null) {
			clearCache(dataEntryProvenance);
		}

		return dataEntryProvenance;
	}

	@Override
	public DataEntryProvenance updateImpl(
		com.kisti.osp.icecap.model.DataEntryProvenance dataEntryProvenance)
		throws SystemException {
		dataEntryProvenance = toUnwrappedModel(dataEntryProvenance);

		boolean isNew = dataEntryProvenance.isNew();

		Session session = null;

		try {
			session = openSession();

			if (dataEntryProvenance.isNew()) {
				session.save(dataEntryProvenance);

				dataEntryProvenance.setNew(false);
			}
			else {
				session.merge(dataEntryProvenance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DataEntryProvenanceModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryProvenanceImpl.class, dataEntryProvenance.getPrimaryKey(),
			dataEntryProvenance);

		clearUniqueFindersCache(dataEntryProvenance);
		cacheUniqueFindersCache(dataEntryProvenance);

		return dataEntryProvenance;
	}

	protected DataEntryProvenance toUnwrappedModel(
		DataEntryProvenance dataEntryProvenance) {
		if (dataEntryProvenance instanceof DataEntryProvenanceImpl) {
			return dataEntryProvenance;
		}

		DataEntryProvenanceImpl dataEntryProvenanceImpl = new DataEntryProvenanceImpl();

		dataEntryProvenanceImpl.setNew(dataEntryProvenance.isNew());
		dataEntryProvenanceImpl.setPrimaryKey(dataEntryProvenance.getPrimaryKey());

		dataEntryProvenanceImpl.setEntryId(dataEntryProvenance.getEntryId());
		dataEntryProvenanceImpl.setInputData(dataEntryProvenance.getInputData());
		dataEntryProvenanceImpl.setPROVStructure(dataEntryProvenance.getPROVStructure());

		return dataEntryProvenanceImpl;
	}

	/**
	 * Returns the data entry provenance with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the data entry provenance
	 * @return the data entry provenance
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException if a data entry provenance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataEntryProvenanceException, SystemException {
		DataEntryProvenance dataEntryProvenance = fetchByPrimaryKey(primaryKey);

		if (dataEntryProvenance == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataEntryProvenanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dataEntryProvenance;
	}

	/**
	 * Returns the data entry provenance with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataEntryProvenanceException} if it could not be found.
	 *
	 * @param entryId the primary key of the data entry provenance
	 * @return the data entry provenance
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException if a data entry provenance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance findByPrimaryKey(long entryId)
		throws NoSuchDataEntryProvenanceException, SystemException {
		return findByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns the data entry provenance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the data entry provenance
	 * @return the data entry provenance, or <code>null</code> if a data entry provenance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		DataEntryProvenance dataEntryProvenance = (DataEntryProvenance)EntityCacheUtil.getResult(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
				DataEntryProvenanceImpl.class, primaryKey);

		if (dataEntryProvenance == _nullDataEntryProvenance) {
			return null;
		}

		if (dataEntryProvenance == null) {
			Session session = null;

			try {
				session = openSession();

				dataEntryProvenance = (DataEntryProvenance)session.get(DataEntryProvenanceImpl.class,
						primaryKey);

				if (dataEntryProvenance != null) {
					cacheResult(dataEntryProvenance);
				}
				else {
					EntityCacheUtil.putResult(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
						DataEntryProvenanceImpl.class, primaryKey,
						_nullDataEntryProvenance);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DataEntryProvenanceModelImpl.ENTITY_CACHE_ENABLED,
					DataEntryProvenanceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dataEntryProvenance;
	}

	/**
	 * Returns the data entry provenance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the data entry provenance
	 * @return the data entry provenance, or <code>null</code> if a data entry provenance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntryProvenance fetchByPrimaryKey(long entryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns all the data entry provenances.
	 *
	 * @return the data entry provenances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntryProvenance> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data entry provenances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryProvenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data entry provenances
	 * @param end the upper bound of the range of data entry provenances (not inclusive)
	 * @return the range of data entry provenances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntryProvenance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the data entry provenances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryProvenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data entry provenances
	 * @param end the upper bound of the range of data entry provenances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of data entry provenances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntryProvenance> findAll(int start, int end,
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

		List<DataEntryProvenance> list = (List<DataEntryProvenance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DATAENTRYPROVENANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DATAENTRYPROVENANCE;

				if (pagination) {
					sql = sql.concat(DataEntryProvenanceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DataEntryProvenance>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataEntryProvenance>(list);
				}
				else {
					list = (List<DataEntryProvenance>)QueryUtil.list(q,
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
	 * Removes all the data entry provenances from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (DataEntryProvenance dataEntryProvenance : findAll()) {
			remove(dataEntryProvenance);
		}
	}

	/**
	 * Returns the number of data entry provenances.
	 *
	 * @return the number of data entry provenances
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

				Query q = session.createQuery(_SQL_COUNT_DATAENTRYPROVENANCE);

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
	 * Initializes the data entry provenance persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.osp.icecap.model.DataEntryProvenance")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DataEntryProvenance>> listenersList = new ArrayList<ModelListener<DataEntryProvenance>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DataEntryProvenance>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DataEntryProvenanceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DATAENTRYPROVENANCE = "SELECT dataEntryProvenance FROM DataEntryProvenance dataEntryProvenance";
	private static final String _SQL_SELECT_DATAENTRYPROVENANCE_WHERE = "SELECT dataEntryProvenance FROM DataEntryProvenance dataEntryProvenance WHERE ";
	private static final String _SQL_COUNT_DATAENTRYPROVENANCE = "SELECT COUNT(dataEntryProvenance) FROM DataEntryProvenance dataEntryProvenance";
	private static final String _SQL_COUNT_DATAENTRYPROVENANCE_WHERE = "SELECT COUNT(dataEntryProvenance) FROM DataEntryProvenance dataEntryProvenance WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dataEntryProvenance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DataEntryProvenance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DataEntryProvenance exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DataEntryProvenancePersistenceImpl.class);
	private static DataEntryProvenance _nullDataEntryProvenance = new DataEntryProvenanceImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DataEntryProvenance> toCacheModel() {
				return _nullDataEntryProvenanceCacheModel;
			}
		};

	private static CacheModel<DataEntryProvenance> _nullDataEntryProvenanceCacheModel =
		new CacheModel<DataEntryProvenance>() {
			@Override
			public DataEntryProvenance toEntityModel() {
				return _nullDataEntryProvenance;
			}
		};
}