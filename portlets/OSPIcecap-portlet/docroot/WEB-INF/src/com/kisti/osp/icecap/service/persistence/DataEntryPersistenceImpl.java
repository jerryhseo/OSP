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

import com.kisti.osp.icecap.NoSuchDataEntryException;
import com.kisti.osp.icecap.model.DataEntry;
import com.kisti.osp.icecap.model.impl.DataEntryImpl;
import com.kisti.osp.icecap.model.impl.DataEntryModelImpl;

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
import com.liferay.portal.kernel.util.SetUtil;
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
import java.util.Set;

/**
 * The persistence implementation for the data entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryPersistence
 * @see DataEntryUtil
 * @generated
 */
public class DataEntryPersistenceImpl extends BasePersistenceImpl<DataEntry>
	implements DataEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DataEntryUtil} to access the data entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DataEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryModelImpl.FINDER_CACHE_ENABLED, DataEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryModelImpl.FINDER_CACHE_ENABLED, DataEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONSUBJECT =
		new FinderPath(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryModelImpl.FINDER_CACHE_ENABLED, DataEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySimulationSubject",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONSUBJECT =
		new FinderPath(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryModelImpl.FINDER_CACHE_ENABLED, DataEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySimulationSubject", new String[] { Long.class.getName() },
			DataEntryModelImpl.SIMULATIONSUBJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONSUBJECT = new FinderPath(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySimulationSubject", new String[] { Long.class.getName() });

	/**
	 * Returns all the data entries where simulationSubjectId = &#63;.
	 *
	 * @param simulationSubjectId the simulation subject ID
	 * @return the matching data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntry> findBySimulationSubject(long simulationSubjectId)
		throws SystemException {
		return findBySimulationSubject(simulationSubjectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data entries where simulationSubjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationSubjectId the simulation subject ID
	 * @param start the lower bound of the range of data entries
	 * @param end the upper bound of the range of data entries (not inclusive)
	 * @return the range of matching data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntry> findBySimulationSubject(long simulationSubjectId,
		int start, int end) throws SystemException {
		return findBySimulationSubject(simulationSubjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data entries where simulationSubjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationSubjectId the simulation subject ID
	 * @param start the lower bound of the range of data entries
	 * @param end the upper bound of the range of data entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntry> findBySimulationSubject(long simulationSubjectId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONSUBJECT;
			finderArgs = new Object[] { simulationSubjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONSUBJECT;
			finderArgs = new Object[] {
					simulationSubjectId,
					
					start, end, orderByComparator
				};
		}

		List<DataEntry> list = (List<DataEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataEntry dataEntry : list) {
				if ((simulationSubjectId != dataEntry.getSimulationSubjectId())) {
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

			query.append(_SQL_SELECT_DATAENTRY_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONSUBJECT_SIMULATIONSUBJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationSubjectId);

				if (!pagination) {
					list = (List<DataEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataEntry>(list);
				}
				else {
					list = (List<DataEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first data entry in the ordered set where simulationSubjectId = &#63;.
	 *
	 * @param simulationSubjectId the simulation subject ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data entry
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry findBySimulationSubject_First(long simulationSubjectId,
		OrderByComparator orderByComparator)
		throws NoSuchDataEntryException, SystemException {
		DataEntry dataEntry = fetchBySimulationSubject_First(simulationSubjectId,
				orderByComparator);

		if (dataEntry != null) {
			return dataEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationSubjectId=");
		msg.append(simulationSubjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataEntryException(msg.toString());
	}

	/**
	 * Returns the first data entry in the ordered set where simulationSubjectId = &#63;.
	 *
	 * @param simulationSubjectId the simulation subject ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data entry, or <code>null</code> if a matching data entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry fetchBySimulationSubject_First(long simulationSubjectId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataEntry> list = findBySimulationSubject(simulationSubjectId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data entry in the ordered set where simulationSubjectId = &#63;.
	 *
	 * @param simulationSubjectId the simulation subject ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data entry
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry findBySimulationSubject_Last(long simulationSubjectId,
		OrderByComparator orderByComparator)
		throws NoSuchDataEntryException, SystemException {
		DataEntry dataEntry = fetchBySimulationSubject_Last(simulationSubjectId,
				orderByComparator);

		if (dataEntry != null) {
			return dataEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationSubjectId=");
		msg.append(simulationSubjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataEntryException(msg.toString());
	}

	/**
	 * Returns the last data entry in the ordered set where simulationSubjectId = &#63;.
	 *
	 * @param simulationSubjectId the simulation subject ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data entry, or <code>null</code> if a matching data entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry fetchBySimulationSubject_Last(long simulationSubjectId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySimulationSubject(simulationSubjectId);

		if (count == 0) {
			return null;
		}

		List<DataEntry> list = findBySimulationSubject(simulationSubjectId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data entries before and after the current data entry in the ordered set where simulationSubjectId = &#63;.
	 *
	 * @param entryId the primary key of the current data entry
	 * @param simulationSubjectId the simulation subject ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data entry
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry[] findBySimulationSubject_PrevAndNext(long entryId,
		long simulationSubjectId, OrderByComparator orderByComparator)
		throws NoSuchDataEntryException, SystemException {
		DataEntry dataEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			DataEntry[] array = new DataEntryImpl[3];

			array[0] = getBySimulationSubject_PrevAndNext(session, dataEntry,
					simulationSubjectId, orderByComparator, true);

			array[1] = dataEntry;

			array[2] = getBySimulationSubject_PrevAndNext(session, dataEntry,
					simulationSubjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataEntry getBySimulationSubject_PrevAndNext(Session session,
		DataEntry dataEntry, long simulationSubjectId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATAENTRY_WHERE);

		query.append(_FINDER_COLUMN_SIMULATIONSUBJECT_SIMULATIONSUBJECTID_2);

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
			query.append(DataEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(simulationSubjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data entries where simulationSubjectId = &#63; from the database.
	 *
	 * @param simulationSubjectId the simulation subject ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBySimulationSubject(long simulationSubjectId)
		throws SystemException {
		for (DataEntry dataEntry : findBySimulationSubject(
				simulationSubjectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataEntry);
		}
	}

	/**
	 * Returns the number of data entries where simulationSubjectId = &#63;.
	 *
	 * @param simulationSubjectId the simulation subject ID
	 * @return the number of matching data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySimulationSubject(long simulationSubjectId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONSUBJECT;

		Object[] finderArgs = new Object[] { simulationSubjectId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATAENTRY_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONSUBJECT_SIMULATIONSUBJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationSubjectId);

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

	private static final String _FINDER_COLUMN_SIMULATIONSUBJECT_SIMULATIONSUBJECTID_2 =
		"dataEntry.simulationSubjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COLLECTIONID =
		new FinderPath(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryModelImpl.FINDER_CACHE_ENABLED, DataEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCollectionID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COLLECTIONID =
		new FinderPath(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryModelImpl.FINDER_CACHE_ENABLED, DataEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCollectionID",
			new String[] { Long.class.getName() },
			DataEntryModelImpl.COLLECTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COLLECTIONID = new FinderPath(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCollectionID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the data entries where collectionId = &#63;.
	 *
	 * @param collectionId the collection ID
	 * @return the matching data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntry> findByCollectionID(long collectionId)
		throws SystemException {
		return findByCollectionID(collectionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data entries where collectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param collectionId the collection ID
	 * @param start the lower bound of the range of data entries
	 * @param end the upper bound of the range of data entries (not inclusive)
	 * @return the range of matching data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntry> findByCollectionID(long collectionId, int start,
		int end) throws SystemException {
		return findByCollectionID(collectionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data entries where collectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param collectionId the collection ID
	 * @param start the lower bound of the range of data entries
	 * @param end the upper bound of the range of data entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntry> findByCollectionID(long collectionId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COLLECTIONID;
			finderArgs = new Object[] { collectionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COLLECTIONID;
			finderArgs = new Object[] {
					collectionId,
					
					start, end, orderByComparator
				};
		}

		List<DataEntry> list = (List<DataEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataEntry dataEntry : list) {
				if ((collectionId != dataEntry.getCollectionId())) {
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

			query.append(_SQL_SELECT_DATAENTRY_WHERE);

			query.append(_FINDER_COLUMN_COLLECTIONID_COLLECTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(collectionId);

				if (!pagination) {
					list = (List<DataEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataEntry>(list);
				}
				else {
					list = (List<DataEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first data entry in the ordered set where collectionId = &#63;.
	 *
	 * @param collectionId the collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data entry
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry findByCollectionID_First(long collectionId,
		OrderByComparator orderByComparator)
		throws NoSuchDataEntryException, SystemException {
		DataEntry dataEntry = fetchByCollectionID_First(collectionId,
				orderByComparator);

		if (dataEntry != null) {
			return dataEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("collectionId=");
		msg.append(collectionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataEntryException(msg.toString());
	}

	/**
	 * Returns the first data entry in the ordered set where collectionId = &#63;.
	 *
	 * @param collectionId the collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data entry, or <code>null</code> if a matching data entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry fetchByCollectionID_First(long collectionId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataEntry> list = findByCollectionID(collectionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data entry in the ordered set where collectionId = &#63;.
	 *
	 * @param collectionId the collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data entry
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry findByCollectionID_Last(long collectionId,
		OrderByComparator orderByComparator)
		throws NoSuchDataEntryException, SystemException {
		DataEntry dataEntry = fetchByCollectionID_Last(collectionId,
				orderByComparator);

		if (dataEntry != null) {
			return dataEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("collectionId=");
		msg.append(collectionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataEntryException(msg.toString());
	}

	/**
	 * Returns the last data entry in the ordered set where collectionId = &#63;.
	 *
	 * @param collectionId the collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data entry, or <code>null</code> if a matching data entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry fetchByCollectionID_Last(long collectionId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCollectionID(collectionId);

		if (count == 0) {
			return null;
		}

		List<DataEntry> list = findByCollectionID(collectionId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data entries before and after the current data entry in the ordered set where collectionId = &#63;.
	 *
	 * @param entryId the primary key of the current data entry
	 * @param collectionId the collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data entry
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry[] findByCollectionID_PrevAndNext(long entryId,
		long collectionId, OrderByComparator orderByComparator)
		throws NoSuchDataEntryException, SystemException {
		DataEntry dataEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			DataEntry[] array = new DataEntryImpl[3];

			array[0] = getByCollectionID_PrevAndNext(session, dataEntry,
					collectionId, orderByComparator, true);

			array[1] = dataEntry;

			array[2] = getByCollectionID_PrevAndNext(session, dataEntry,
					collectionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataEntry getByCollectionID_PrevAndNext(Session session,
		DataEntry dataEntry, long collectionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATAENTRY_WHERE);

		query.append(_FINDER_COLUMN_COLLECTIONID_COLLECTIONID_2);

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
			query.append(DataEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(collectionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data entries where collectionId = &#63; from the database.
	 *
	 * @param collectionId the collection ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCollectionID(long collectionId)
		throws SystemException {
		for (DataEntry dataEntry : findByCollectionID(collectionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataEntry);
		}
	}

	/**
	 * Returns the number of data entries where collectionId = &#63;.
	 *
	 * @param collectionId the collection ID
	 * @return the number of matching data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCollectionID(long collectionId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COLLECTIONID;

		Object[] finderArgs = new Object[] { collectionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATAENTRY_WHERE);

			query.append(_FINDER_COLUMN_COLLECTIONID_COLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(collectionId);

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

	private static final String _FINDER_COLUMN_COLLECTIONID_COLLECTIONID_2 = "dataEntry.collectionId = ?";

	public DataEntryPersistenceImpl() {
		setModelClass(DataEntry.class);
	}

	/**
	 * Caches the data entry in the entity cache if it is enabled.
	 *
	 * @param dataEntry the data entry
	 */
	@Override
	public void cacheResult(DataEntry dataEntry) {
		EntityCacheUtil.putResult(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryImpl.class, dataEntry.getPrimaryKey(), dataEntry);

		dataEntry.resetOriginalValues();
	}

	/**
	 * Caches the data entries in the entity cache if it is enabled.
	 *
	 * @param dataEntries the data entries
	 */
	@Override
	public void cacheResult(List<DataEntry> dataEntries) {
		for (DataEntry dataEntry : dataEntries) {
			if (EntityCacheUtil.getResult(
						DataEntryModelImpl.ENTITY_CACHE_ENABLED,
						DataEntryImpl.class, dataEntry.getPrimaryKey()) == null) {
				cacheResult(dataEntry);
			}
			else {
				dataEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all data entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DataEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DataEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the data entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DataEntry dataEntry) {
		EntityCacheUtil.removeResult(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryImpl.class, dataEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<DataEntry> dataEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DataEntry dataEntry : dataEntries) {
			EntityCacheUtil.removeResult(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
				DataEntryImpl.class, dataEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new data entry with the primary key. Does not add the data entry to the database.
	 *
	 * @param entryId the primary key for the new data entry
	 * @return the new data entry
	 */
	@Override
	public DataEntry create(long entryId) {
		DataEntry dataEntry = new DataEntryImpl();

		dataEntry.setNew(true);
		dataEntry.setPrimaryKey(entryId);

		return dataEntry;
	}

	/**
	 * Removes the data entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the data entry
	 * @return the data entry that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry remove(long entryId)
		throws NoSuchDataEntryException, SystemException {
		return remove((Serializable)entryId);
	}

	/**
	 * Removes the data entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the data entry
	 * @return the data entry that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry remove(Serializable primaryKey)
		throws NoSuchDataEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DataEntry dataEntry = (DataEntry)session.get(DataEntryImpl.class,
					primaryKey);

			if (dataEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dataEntry);
		}
		catch (NoSuchDataEntryException nsee) {
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
	protected DataEntry removeImpl(DataEntry dataEntry)
		throws SystemException {
		dataEntry = toUnwrappedModel(dataEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dataEntry)) {
				dataEntry = (DataEntry)session.get(DataEntryImpl.class,
						dataEntry.getPrimaryKeyObj());
			}

			if (dataEntry != null) {
				session.delete(dataEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dataEntry != null) {
			clearCache(dataEntry);
		}

		return dataEntry;
	}

	@Override
	public DataEntry updateImpl(com.kisti.osp.icecap.model.DataEntry dataEntry)
		throws SystemException {
		dataEntry = toUnwrappedModel(dataEntry);

		boolean isNew = dataEntry.isNew();

		DataEntryModelImpl dataEntryModelImpl = (DataEntryModelImpl)dataEntry;

		Session session = null;

		try {
			session = openSession();

			if (dataEntry.isNew()) {
				session.save(dataEntry);

				dataEntry.setNew(false);
			}
			else {
				session.merge(dataEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DataEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((dataEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONSUBJECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataEntryModelImpl.getOriginalSimulationSubjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONSUBJECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONSUBJECT,
					args);

				args = new Object[] { dataEntryModelImpl.getSimulationSubjectId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONSUBJECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONSUBJECT,
					args);
			}

			if ((dataEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COLLECTIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataEntryModelImpl.getOriginalCollectionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COLLECTIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COLLECTIONID,
					args);

				args = new Object[] { dataEntryModelImpl.getCollectionId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COLLECTIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COLLECTIONID,
					args);
			}
		}

		EntityCacheUtil.putResult(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
			DataEntryImpl.class, dataEntry.getPrimaryKey(), dataEntry);

		return dataEntry;
	}

	protected DataEntry toUnwrappedModel(DataEntry dataEntry) {
		if (dataEntry instanceof DataEntryImpl) {
			return dataEntry;
		}

		DataEntryImpl dataEntryImpl = new DataEntryImpl();

		dataEntryImpl.setNew(dataEntry.isNew());
		dataEntryImpl.setPrimaryKey(dataEntry.getPrimaryKey());

		dataEntryImpl.setEntryId(dataEntry.getEntryId());
		dataEntryImpl.setCollectionId(dataEntry.getCollectionId());
		dataEntryImpl.setGroupId(dataEntry.getGroupId());
		dataEntryImpl.setCompanyId(dataEntry.getCompanyId());
		dataEntryImpl.setUserId(dataEntry.getUserId());
		dataEntryImpl.setCreateDate(dataEntry.getCreateDate());
		dataEntryImpl.setComments(dataEntry.getComments());
		dataEntryImpl.setPath(dataEntry.getPath());
		dataEntryImpl.setSimulationSubjectId(dataEntry.getSimulationSubjectId());
		dataEntryImpl.setProductionTime(dataEntry.getProductionTime());
		dataEntryImpl.setViewCount(dataEntry.getViewCount());
		dataEntryImpl.setDownloadCount(dataEntry.getDownloadCount());
		dataEntryImpl.setLastAccessedDate(dataEntry.getLastAccessedDate());
		dataEntryImpl.setFileEntryId(dataEntry.getFileEntryId());

		return dataEntryImpl;
	}

	/**
	 * Returns the data entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the data entry
	 * @return the data entry
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataEntryException, SystemException {
		DataEntry dataEntry = fetchByPrimaryKey(primaryKey);

		if (dataEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dataEntry;
	}

	/**
	 * Returns the data entry with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataEntryException} if it could not be found.
	 *
	 * @param entryId the primary key of the data entry
	 * @return the data entry
	 * @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry findByPrimaryKey(long entryId)
		throws NoSuchDataEntryException, SystemException {
		return findByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns the data entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the data entry
	 * @return the data entry, or <code>null</code> if a data entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		DataEntry dataEntry = (DataEntry)EntityCacheUtil.getResult(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
				DataEntryImpl.class, primaryKey);

		if (dataEntry == _nullDataEntry) {
			return null;
		}

		if (dataEntry == null) {
			Session session = null;

			try {
				session = openSession();

				dataEntry = (DataEntry)session.get(DataEntryImpl.class,
						primaryKey);

				if (dataEntry != null) {
					cacheResult(dataEntry);
				}
				else {
					EntityCacheUtil.putResult(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
						DataEntryImpl.class, primaryKey, _nullDataEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DataEntryModelImpl.ENTITY_CACHE_ENABLED,
					DataEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dataEntry;
	}

	/**
	 * Returns the data entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the data entry
	 * @return the data entry, or <code>null</code> if a data entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataEntry fetchByPrimaryKey(long entryId) throws SystemException {
		return fetchByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns all the data entries.
	 *
	 * @return the data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data entries
	 * @param end the upper bound of the range of data entries (not inclusive)
	 * @return the range of data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the data entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data entries
	 * @param end the upper bound of the range of data entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of data entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataEntry> findAll(int start, int end,
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

		List<DataEntry> list = (List<DataEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DATAENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DATAENTRY;

				if (pagination) {
					sql = sql.concat(DataEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DataEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataEntry>(list);
				}
				else {
					list = (List<DataEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the data entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (DataEntry dataEntry : findAll()) {
			remove(dataEntry);
		}
	}

	/**
	 * Returns the number of data entries.
	 *
	 * @return the number of data entries
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

				Query q = session.createQuery(_SQL_COUNT_DATAENTRY);

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
	 * Initializes the data entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.osp.icecap.model.DataEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DataEntry>> listenersList = new ArrayList<ModelListener<DataEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DataEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DataEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DATAENTRY = "SELECT dataEntry FROM DataEntry dataEntry";
	private static final String _SQL_SELECT_DATAENTRY_WHERE = "SELECT dataEntry FROM DataEntry dataEntry WHERE ";
	private static final String _SQL_COUNT_DATAENTRY = "SELECT COUNT(dataEntry) FROM DataEntry dataEntry";
	private static final String _SQL_COUNT_DATAENTRY_WHERE = "SELECT COUNT(dataEntry) FROM DataEntry dataEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dataEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DataEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DataEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DataEntryPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"path"
			});
	private static DataEntry _nullDataEntry = new DataEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DataEntry> toCacheModel() {
				return _nullDataEntryCacheModel;
			}
		};

	private static CacheModel<DataEntry> _nullDataEntryCacheModel = new CacheModel<DataEntry>() {
			@Override
			public DataEntry toEntityModel() {
				return _nullDataEntry;
			}
		};
}