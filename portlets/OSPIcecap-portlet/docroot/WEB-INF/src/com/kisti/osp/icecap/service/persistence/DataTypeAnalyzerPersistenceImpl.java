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

import com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException;
import com.kisti.osp.icecap.model.DataTypeAnalyzer;
import com.kisti.osp.icecap.model.impl.DataTypeAnalyzerImpl;
import com.kisti.osp.icecap.model.impl.DataTypeAnalyzerModelImpl;

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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the data type analyzer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeAnalyzerPersistence
 * @see DataTypeAnalyzerUtil
 * @generated
 */
public class DataTypeAnalyzerPersistenceImpl extends BasePersistenceImpl<DataTypeAnalyzer>
	implements DataTypeAnalyzerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DataTypeAnalyzerUtil} to access the data type analyzer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DataTypeAnalyzerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerModelImpl.FINDER_CACHE_ENABLED,
			DataTypeAnalyzerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerModelImpl.FINDER_CACHE_ENABLED,
			DataTypeAnalyzerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ANALYZER = new FinderPath(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerModelImpl.FINDER_CACHE_ENABLED,
			DataTypeAnalyzerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAnalyzer",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANALYZER =
		new FinderPath(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerModelImpl.FINDER_CACHE_ENABLED,
			DataTypeAnalyzerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAnalyzer",
			new String[] { Long.class.getName() },
			DataTypeAnalyzerModelImpl.ANALYZERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ANALYZER = new FinderPath(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAnalyzer",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the data type analyzers where analyzerId = &#63;.
	 *
	 * @param analyzerId the analyzer ID
	 * @return the matching data type analyzers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeAnalyzer> findByAnalyzer(long analyzerId)
		throws SystemException {
		return findByAnalyzer(analyzerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the data type analyzers where analyzerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeAnalyzerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param analyzerId the analyzer ID
	 * @param start the lower bound of the range of data type analyzers
	 * @param end the upper bound of the range of data type analyzers (not inclusive)
	 * @return the range of matching data type analyzers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeAnalyzer> findByAnalyzer(long analyzerId, int start,
		int end) throws SystemException {
		return findByAnalyzer(analyzerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data type analyzers where analyzerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeAnalyzerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param analyzerId the analyzer ID
	 * @param start the lower bound of the range of data type analyzers
	 * @param end the upper bound of the range of data type analyzers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data type analyzers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeAnalyzer> findByAnalyzer(long analyzerId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANALYZER;
			finderArgs = new Object[] { analyzerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ANALYZER;
			finderArgs = new Object[] { analyzerId, start, end, orderByComparator };
		}

		List<DataTypeAnalyzer> list = (List<DataTypeAnalyzer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataTypeAnalyzer dataTypeAnalyzer : list) {
				if ((analyzerId != dataTypeAnalyzer.getAnalyzerId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DATATYPEANALYZER_WHERE);

			query.append(_FINDER_COLUMN_ANALYZER_ANALYZERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataTypeAnalyzerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(analyzerId);

				if (!pagination) {
					list = (List<DataTypeAnalyzer>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataTypeAnalyzer>(list);
				}
				else {
					list = (List<DataTypeAnalyzer>)QueryUtil.list(q,
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
	 * Returns the first data type analyzer in the ordered set where analyzerId = &#63;.
	 *
	 * @param analyzerId the analyzer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data type analyzer
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException if a matching data type analyzer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer findByAnalyzer_First(long analyzerId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeAnalyzerException, SystemException {
		DataTypeAnalyzer dataTypeAnalyzer = fetchByAnalyzer_First(analyzerId,
				orderByComparator);

		if (dataTypeAnalyzer != null) {
			return dataTypeAnalyzer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("analyzerId=");
		msg.append(analyzerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataTypeAnalyzerException(msg.toString());
	}

	/**
	 * Returns the first data type analyzer in the ordered set where analyzerId = &#63;.
	 *
	 * @param analyzerId the analyzer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data type analyzer, or <code>null</code> if a matching data type analyzer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer fetchByAnalyzer_First(long analyzerId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataTypeAnalyzer> list = findByAnalyzer(analyzerId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data type analyzer in the ordered set where analyzerId = &#63;.
	 *
	 * @param analyzerId the analyzer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data type analyzer
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException if a matching data type analyzer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer findByAnalyzer_Last(long analyzerId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeAnalyzerException, SystemException {
		DataTypeAnalyzer dataTypeAnalyzer = fetchByAnalyzer_Last(analyzerId,
				orderByComparator);

		if (dataTypeAnalyzer != null) {
			return dataTypeAnalyzer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("analyzerId=");
		msg.append(analyzerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataTypeAnalyzerException(msg.toString());
	}

	/**
	 * Returns the last data type analyzer in the ordered set where analyzerId = &#63;.
	 *
	 * @param analyzerId the analyzer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data type analyzer, or <code>null</code> if a matching data type analyzer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer fetchByAnalyzer_Last(long analyzerId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAnalyzer(analyzerId);

		if (count == 0) {
			return null;
		}

		List<DataTypeAnalyzer> list = findByAnalyzer(analyzerId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data type analyzers before and after the current data type analyzer in the ordered set where analyzerId = &#63;.
	 *
	 * @param dataTypeAnalyzerPK the primary key of the current data type analyzer
	 * @param analyzerId the analyzer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data type analyzer
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException if a data type analyzer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer[] findByAnalyzer_PrevAndNext(
		DataTypeAnalyzerPK dataTypeAnalyzerPK, long analyzerId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeAnalyzerException, SystemException {
		DataTypeAnalyzer dataTypeAnalyzer = findByPrimaryKey(dataTypeAnalyzerPK);

		Session session = null;

		try {
			session = openSession();

			DataTypeAnalyzer[] array = new DataTypeAnalyzerImpl[3];

			array[0] = getByAnalyzer_PrevAndNext(session, dataTypeAnalyzer,
					analyzerId, orderByComparator, true);

			array[1] = dataTypeAnalyzer;

			array[2] = getByAnalyzer_PrevAndNext(session, dataTypeAnalyzer,
					analyzerId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataTypeAnalyzer getByAnalyzer_PrevAndNext(Session session,
		DataTypeAnalyzer dataTypeAnalyzer, long analyzerId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATATYPEANALYZER_WHERE);

		query.append(_FINDER_COLUMN_ANALYZER_ANALYZERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(DataTypeAnalyzerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(analyzerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataTypeAnalyzer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataTypeAnalyzer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data type analyzers where analyzerId = &#63; from the database.
	 *
	 * @param analyzerId the analyzer ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByAnalyzer(long analyzerId) throws SystemException {
		for (DataTypeAnalyzer dataTypeAnalyzer : findByAnalyzer(analyzerId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataTypeAnalyzer);
		}
	}

	/**
	 * Returns the number of data type analyzers where analyzerId = &#63;.
	 *
	 * @param analyzerId the analyzer ID
	 * @return the number of matching data type analyzers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByAnalyzer(long analyzerId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ANALYZER;

		Object[] finderArgs = new Object[] { analyzerId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATATYPEANALYZER_WHERE);

			query.append(_FINDER_COLUMN_ANALYZER_ANALYZERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(analyzerId);

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

	private static final String _FINDER_COLUMN_ANALYZER_ANALYZERID_2 = "dataTypeAnalyzer.id.analyzerId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPEID = new FinderPath(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerModelImpl.FINDER_CACHE_ENABLED,
			DataTypeAnalyzerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTypeID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID =
		new FinderPath(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerModelImpl.FINDER_CACHE_ENABLED,
			DataTypeAnalyzerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTypeID",
			new String[] { Long.class.getName() },
			DataTypeAnalyzerModelImpl.TYPEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPEID = new FinderPath(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTypeID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the data type analyzers where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the matching data type analyzers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeAnalyzer> findByTypeID(long typeId)
		throws SystemException {
		return findByTypeID(typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data type analyzers where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeAnalyzerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of data type analyzers
	 * @param end the upper bound of the range of data type analyzers (not inclusive)
	 * @return the range of matching data type analyzers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeAnalyzer> findByTypeID(long typeId, int start, int end)
		throws SystemException {
		return findByTypeID(typeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data type analyzers where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeAnalyzerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of data type analyzers
	 * @param end the upper bound of the range of data type analyzers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data type analyzers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeAnalyzer> findByTypeID(long typeId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID;
			finderArgs = new Object[] { typeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPEID;
			finderArgs = new Object[] { typeId, start, end, orderByComparator };
		}

		List<DataTypeAnalyzer> list = (List<DataTypeAnalyzer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataTypeAnalyzer dataTypeAnalyzer : list) {
				if ((typeId != dataTypeAnalyzer.getTypeId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DATATYPEANALYZER_WHERE);

			query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataTypeAnalyzerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

				if (!pagination) {
					list = (List<DataTypeAnalyzer>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataTypeAnalyzer>(list);
				}
				else {
					list = (List<DataTypeAnalyzer>)QueryUtil.list(q,
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
	 * Returns the first data type analyzer in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data type analyzer
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException if a matching data type analyzer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer findByTypeID_First(long typeId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeAnalyzerException, SystemException {
		DataTypeAnalyzer dataTypeAnalyzer = fetchByTypeID_First(typeId,
				orderByComparator);

		if (dataTypeAnalyzer != null) {
			return dataTypeAnalyzer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("typeId=");
		msg.append(typeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataTypeAnalyzerException(msg.toString());
	}

	/**
	 * Returns the first data type analyzer in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data type analyzer, or <code>null</code> if a matching data type analyzer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer fetchByTypeID_First(long typeId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataTypeAnalyzer> list = findByTypeID(typeId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data type analyzer in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data type analyzer
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException if a matching data type analyzer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer findByTypeID_Last(long typeId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeAnalyzerException, SystemException {
		DataTypeAnalyzer dataTypeAnalyzer = fetchByTypeID_Last(typeId,
				orderByComparator);

		if (dataTypeAnalyzer != null) {
			return dataTypeAnalyzer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("typeId=");
		msg.append(typeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataTypeAnalyzerException(msg.toString());
	}

	/**
	 * Returns the last data type analyzer in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data type analyzer, or <code>null</code> if a matching data type analyzer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer fetchByTypeID_Last(long typeId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTypeID(typeId);

		if (count == 0) {
			return null;
		}

		List<DataTypeAnalyzer> list = findByTypeID(typeId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data type analyzers before and after the current data type analyzer in the ordered set where typeId = &#63;.
	 *
	 * @param dataTypeAnalyzerPK the primary key of the current data type analyzer
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data type analyzer
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException if a data type analyzer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer[] findByTypeID_PrevAndNext(
		DataTypeAnalyzerPK dataTypeAnalyzerPK, long typeId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeAnalyzerException, SystemException {
		DataTypeAnalyzer dataTypeAnalyzer = findByPrimaryKey(dataTypeAnalyzerPK);

		Session session = null;

		try {
			session = openSession();

			DataTypeAnalyzer[] array = new DataTypeAnalyzerImpl[3];

			array[0] = getByTypeID_PrevAndNext(session, dataTypeAnalyzer,
					typeId, orderByComparator, true);

			array[1] = dataTypeAnalyzer;

			array[2] = getByTypeID_PrevAndNext(session, dataTypeAnalyzer,
					typeId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataTypeAnalyzer getByTypeID_PrevAndNext(Session session,
		DataTypeAnalyzer dataTypeAnalyzer, long typeId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATATYPEANALYZER_WHERE);

		query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(DataTypeAnalyzerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(typeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataTypeAnalyzer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataTypeAnalyzer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data type analyzers where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTypeID(long typeId) throws SystemException {
		for (DataTypeAnalyzer dataTypeAnalyzer : findByTypeID(typeId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataTypeAnalyzer);
		}
	}

	/**
	 * Returns the number of data type analyzers where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching data type analyzers
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

			query.append(_SQL_COUNT_DATATYPEANALYZER_WHERE);

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

	private static final String _FINDER_COLUMN_TYPEID_TYPEID_2 = "dataTypeAnalyzer.id.typeId = ?";

	public DataTypeAnalyzerPersistenceImpl() {
		setModelClass(DataTypeAnalyzer.class);
	}

	/**
	 * Caches the data type analyzer in the entity cache if it is enabled.
	 *
	 * @param dataTypeAnalyzer the data type analyzer
	 */
	@Override
	public void cacheResult(DataTypeAnalyzer dataTypeAnalyzer) {
		EntityCacheUtil.putResult(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerImpl.class, dataTypeAnalyzer.getPrimaryKey(),
			dataTypeAnalyzer);

		dataTypeAnalyzer.resetOriginalValues();
	}

	/**
	 * Caches the data type analyzers in the entity cache if it is enabled.
	 *
	 * @param dataTypeAnalyzers the data type analyzers
	 */
	@Override
	public void cacheResult(List<DataTypeAnalyzer> dataTypeAnalyzers) {
		for (DataTypeAnalyzer dataTypeAnalyzer : dataTypeAnalyzers) {
			if (EntityCacheUtil.getResult(
						DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
						DataTypeAnalyzerImpl.class,
						dataTypeAnalyzer.getPrimaryKey()) == null) {
				cacheResult(dataTypeAnalyzer);
			}
			else {
				dataTypeAnalyzer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all data type analyzers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DataTypeAnalyzerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DataTypeAnalyzerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the data type analyzer.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DataTypeAnalyzer dataTypeAnalyzer) {
		EntityCacheUtil.removeResult(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerImpl.class, dataTypeAnalyzer.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<DataTypeAnalyzer> dataTypeAnalyzers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DataTypeAnalyzer dataTypeAnalyzer : dataTypeAnalyzers) {
			EntityCacheUtil.removeResult(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
				DataTypeAnalyzerImpl.class, dataTypeAnalyzer.getPrimaryKey());
		}
	}

	/**
	 * Creates a new data type analyzer with the primary key. Does not add the data type analyzer to the database.
	 *
	 * @param dataTypeAnalyzerPK the primary key for the new data type analyzer
	 * @return the new data type analyzer
	 */
	@Override
	public DataTypeAnalyzer create(DataTypeAnalyzerPK dataTypeAnalyzerPK) {
		DataTypeAnalyzer dataTypeAnalyzer = new DataTypeAnalyzerImpl();

		dataTypeAnalyzer.setNew(true);
		dataTypeAnalyzer.setPrimaryKey(dataTypeAnalyzerPK);

		return dataTypeAnalyzer;
	}

	/**
	 * Removes the data type analyzer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dataTypeAnalyzerPK the primary key of the data type analyzer
	 * @return the data type analyzer that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException if a data type analyzer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer remove(DataTypeAnalyzerPK dataTypeAnalyzerPK)
		throws NoSuchDataTypeAnalyzerException, SystemException {
		return remove((Serializable)dataTypeAnalyzerPK);
	}

	/**
	 * Removes the data type analyzer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the data type analyzer
	 * @return the data type analyzer that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException if a data type analyzer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer remove(Serializable primaryKey)
		throws NoSuchDataTypeAnalyzerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DataTypeAnalyzer dataTypeAnalyzer = (DataTypeAnalyzer)session.get(DataTypeAnalyzerImpl.class,
					primaryKey);

			if (dataTypeAnalyzer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataTypeAnalyzerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dataTypeAnalyzer);
		}
		catch (NoSuchDataTypeAnalyzerException nsee) {
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
	protected DataTypeAnalyzer removeImpl(DataTypeAnalyzer dataTypeAnalyzer)
		throws SystemException {
		dataTypeAnalyzer = toUnwrappedModel(dataTypeAnalyzer);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dataTypeAnalyzer)) {
				dataTypeAnalyzer = (DataTypeAnalyzer)session.get(DataTypeAnalyzerImpl.class,
						dataTypeAnalyzer.getPrimaryKeyObj());
			}

			if (dataTypeAnalyzer != null) {
				session.delete(dataTypeAnalyzer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dataTypeAnalyzer != null) {
			clearCache(dataTypeAnalyzer);
		}

		return dataTypeAnalyzer;
	}

	@Override
	public DataTypeAnalyzer updateImpl(
		com.kisti.osp.icecap.model.DataTypeAnalyzer dataTypeAnalyzer)
		throws SystemException {
		dataTypeAnalyzer = toUnwrappedModel(dataTypeAnalyzer);

		boolean isNew = dataTypeAnalyzer.isNew();

		DataTypeAnalyzerModelImpl dataTypeAnalyzerModelImpl = (DataTypeAnalyzerModelImpl)dataTypeAnalyzer;

		Session session = null;

		try {
			session = openSession();

			if (dataTypeAnalyzer.isNew()) {
				session.save(dataTypeAnalyzer);

				dataTypeAnalyzer.setNew(false);
			}
			else {
				session.merge(dataTypeAnalyzer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DataTypeAnalyzerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((dataTypeAnalyzerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANALYZER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataTypeAnalyzerModelImpl.getOriginalAnalyzerId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ANALYZER, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANALYZER,
					args);

				args = new Object[] { dataTypeAnalyzerModelImpl.getAnalyzerId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ANALYZER, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANALYZER,
					args);
			}

			if ((dataTypeAnalyzerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataTypeAnalyzerModelImpl.getOriginalTypeId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID,
					args);

				args = new Object[] { dataTypeAnalyzerModelImpl.getTypeId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID,
					args);
			}
		}

		EntityCacheUtil.putResult(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeAnalyzerImpl.class, dataTypeAnalyzer.getPrimaryKey(),
			dataTypeAnalyzer);

		return dataTypeAnalyzer;
	}

	protected DataTypeAnalyzer toUnwrappedModel(
		DataTypeAnalyzer dataTypeAnalyzer) {
		if (dataTypeAnalyzer instanceof DataTypeAnalyzerImpl) {
			return dataTypeAnalyzer;
		}

		DataTypeAnalyzerImpl dataTypeAnalyzerImpl = new DataTypeAnalyzerImpl();

		dataTypeAnalyzerImpl.setNew(dataTypeAnalyzer.isNew());
		dataTypeAnalyzerImpl.setPrimaryKey(dataTypeAnalyzer.getPrimaryKey());

		dataTypeAnalyzerImpl.setTypeId(dataTypeAnalyzer.getTypeId());
		dataTypeAnalyzerImpl.setAnalyzerId(dataTypeAnalyzer.getAnalyzerId());
		dataTypeAnalyzerImpl.setIsDefault(dataTypeAnalyzer.isIsDefault());

		return dataTypeAnalyzerImpl;
	}

	/**
	 * Returns the data type analyzer with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the data type analyzer
	 * @return the data type analyzer
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException if a data type analyzer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataTypeAnalyzerException, SystemException {
		DataTypeAnalyzer dataTypeAnalyzer = fetchByPrimaryKey(primaryKey);

		if (dataTypeAnalyzer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataTypeAnalyzerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dataTypeAnalyzer;
	}

	/**
	 * Returns the data type analyzer with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException} if it could not be found.
	 *
	 * @param dataTypeAnalyzerPK the primary key of the data type analyzer
	 * @return the data type analyzer
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException if a data type analyzer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer findByPrimaryKey(
		DataTypeAnalyzerPK dataTypeAnalyzerPK)
		throws NoSuchDataTypeAnalyzerException, SystemException {
		return findByPrimaryKey((Serializable)dataTypeAnalyzerPK);
	}

	/**
	 * Returns the data type analyzer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the data type analyzer
	 * @return the data type analyzer, or <code>null</code> if a data type analyzer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		DataTypeAnalyzer dataTypeAnalyzer = (DataTypeAnalyzer)EntityCacheUtil.getResult(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
				DataTypeAnalyzerImpl.class, primaryKey);

		if (dataTypeAnalyzer == _nullDataTypeAnalyzer) {
			return null;
		}

		if (dataTypeAnalyzer == null) {
			Session session = null;

			try {
				session = openSession();

				dataTypeAnalyzer = (DataTypeAnalyzer)session.get(DataTypeAnalyzerImpl.class,
						primaryKey);

				if (dataTypeAnalyzer != null) {
					cacheResult(dataTypeAnalyzer);
				}
				else {
					EntityCacheUtil.putResult(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
						DataTypeAnalyzerImpl.class, primaryKey,
						_nullDataTypeAnalyzer);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DataTypeAnalyzerModelImpl.ENTITY_CACHE_ENABLED,
					DataTypeAnalyzerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dataTypeAnalyzer;
	}

	/**
	 * Returns the data type analyzer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dataTypeAnalyzerPK the primary key of the data type analyzer
	 * @return the data type analyzer, or <code>null</code> if a data type analyzer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeAnalyzer fetchByPrimaryKey(
		DataTypeAnalyzerPK dataTypeAnalyzerPK) throws SystemException {
		return fetchByPrimaryKey((Serializable)dataTypeAnalyzerPK);
	}

	/**
	 * Returns all the data type analyzers.
	 *
	 * @return the data type analyzers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeAnalyzer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data type analyzers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeAnalyzerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data type analyzers
	 * @param end the upper bound of the range of data type analyzers (not inclusive)
	 * @return the range of data type analyzers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeAnalyzer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the data type analyzers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeAnalyzerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data type analyzers
	 * @param end the upper bound of the range of data type analyzers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of data type analyzers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeAnalyzer> findAll(int start, int end,
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

		List<DataTypeAnalyzer> list = (List<DataTypeAnalyzer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DATATYPEANALYZER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DATATYPEANALYZER;

				if (pagination) {
					sql = sql.concat(DataTypeAnalyzerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DataTypeAnalyzer>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataTypeAnalyzer>(list);
				}
				else {
					list = (List<DataTypeAnalyzer>)QueryUtil.list(q,
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
	 * Removes all the data type analyzers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (DataTypeAnalyzer dataTypeAnalyzer : findAll()) {
			remove(dataTypeAnalyzer);
		}
	}

	/**
	 * Returns the number of data type analyzers.
	 *
	 * @return the number of data type analyzers
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

				Query q = session.createQuery(_SQL_COUNT_DATATYPEANALYZER);

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
	 * Initializes the data type analyzer persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.osp.icecap.model.DataTypeAnalyzer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DataTypeAnalyzer>> listenersList = new ArrayList<ModelListener<DataTypeAnalyzer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DataTypeAnalyzer>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DataTypeAnalyzerImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DATATYPEANALYZER = "SELECT dataTypeAnalyzer FROM DataTypeAnalyzer dataTypeAnalyzer";
	private static final String _SQL_SELECT_DATATYPEANALYZER_WHERE = "SELECT dataTypeAnalyzer FROM DataTypeAnalyzer dataTypeAnalyzer WHERE ";
	private static final String _SQL_COUNT_DATATYPEANALYZER = "SELECT COUNT(dataTypeAnalyzer) FROM DataTypeAnalyzer dataTypeAnalyzer";
	private static final String _SQL_COUNT_DATATYPEANALYZER_WHERE = "SELECT COUNT(dataTypeAnalyzer) FROM DataTypeAnalyzer dataTypeAnalyzer WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dataTypeAnalyzer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DataTypeAnalyzer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DataTypeAnalyzer exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DataTypeAnalyzerPersistenceImpl.class);
	private static DataTypeAnalyzer _nullDataTypeAnalyzer = new DataTypeAnalyzerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DataTypeAnalyzer> toCacheModel() {
				return _nullDataTypeAnalyzerCacheModel;
			}
		};

	private static CacheModel<DataTypeAnalyzer> _nullDataTypeAnalyzerCacheModel = new CacheModel<DataTypeAnalyzer>() {
			@Override
			public DataTypeAnalyzer toEntityModel() {
				return _nullDataTypeAnalyzer;
			}
		};
}