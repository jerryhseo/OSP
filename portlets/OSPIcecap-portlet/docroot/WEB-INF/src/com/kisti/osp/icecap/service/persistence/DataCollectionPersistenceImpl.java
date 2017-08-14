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

import com.kisti.osp.icecap.NoSuchDataCollectionException;
import com.kisti.osp.icecap.model.DataCollection;
import com.kisti.osp.icecap.model.impl.DataCollectionImpl;
import com.kisti.osp.icecap.model.impl.DataCollectionModelImpl;

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
import com.liferay.portal.kernel.util.CharPool;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the data collection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataCollectionPersistence
 * @see DataCollectionUtil
 * @generated
 */
public class DataCollectionPersistenceImpl extends BasePersistenceImpl<DataCollection>
	implements DataCollectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DataCollectionUtil} to access the data collection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DataCollectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DataCollectionModelImpl.UUID_COLUMN_BITMASK |
			DataCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the data collections where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if (!Validator.equals(uuid, dataCollection.getUuid())) {
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

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByUuid_First(uuid,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByUuid_Last(uuid, orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where uuid = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByUuid_PrevAndNext(long collectionId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dataCollection, uuid,
					orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByUuid_PrevAndNext(session, dataCollection, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByUuid_PrevAndNext(Session session,
		DataCollection dataCollection, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (DataCollection dataCollection : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid(String uuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dataCollection.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dataCollection.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dataCollection.uuid IS NULL OR dataCollection.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DataCollectionModelImpl.UUID_COLUMN_BITMASK |
			DataCollectionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the data collection where uuid = &#63; and groupId = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByUUID_G(String uuid, long groupId)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByUUID_G(uuid, groupId);

		if (dataCollection == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDataCollectionException(msg.toString());
		}

		return dataCollection;
	}

	/**
	 * Returns the data collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the data collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DataCollection) {
			DataCollection dataCollection = (DataCollection)result;

			if (!Validator.equals(uuid, dataCollection.getUuid()) ||
					(groupId != dataCollection.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<DataCollection> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DataCollection dataCollection = list.get(0);

					result = dataCollection;

					cacheResult(dataCollection);

					if ((dataCollection.getUuid() == null) ||
							!dataCollection.getUuid().equals(uuid) ||
							(dataCollection.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, dataCollection);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
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
			return (DataCollection)result;
		}
	}

	/**
	 * Removes the data collection where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the data collection that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection removeByUUID_G(String uuid, long groupId)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByUUID_G(uuid, groupId);

		return remove(dataCollection);
	}

	/**
	 * Returns the number of data collections where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dataCollection.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dataCollection.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dataCollection.uuid IS NULL OR dataCollection.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dataCollection.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DataCollectionModelImpl.UUID_COLUMN_BITMASK |
			DataCollectionModelImpl.COMPANYID_COLUMN_BITMASK |
			DataCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the data collections where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if (!Validator.equals(uuid, dataCollection.getUuid()) ||
						(companyId != dataCollection.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByUuid_C_PrevAndNext(long collectionId,
		String uuid, long companyId, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dataCollection, uuid,
					companyId, orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByUuid_C_PrevAndNext(session, dataCollection, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByUuid_C_PrevAndNext(Session session,
		DataCollection dataCollection, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (DataCollection dataCollection : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dataCollection.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dataCollection.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dataCollection.uuid IS NULL OR dataCollection.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dataCollection.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETLANGUAGE =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTargetLanguage",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETLANGUAGE =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTargetLanguage",
			new String[] { String.class.getName() },
			DataCollectionModelImpl.TARGETLANGUAGE_COLUMN_BITMASK |
			DataCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TARGETLANGUAGE = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTargetLanguage",
			new String[] { String.class.getName() });

	/**
	 * Returns all the data collections where targetLanguage = &#63;.
	 *
	 * @param targetLanguage the target language
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTargetLanguage(String targetLanguage)
		throws SystemException {
		return findByTargetLanguage(targetLanguage, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where targetLanguage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param targetLanguage the target language
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTargetLanguage(String targetLanguage,
		int start, int end) throws SystemException {
		return findByTargetLanguage(targetLanguage, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where targetLanguage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param targetLanguage the target language
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTargetLanguage(String targetLanguage,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETLANGUAGE;
			finderArgs = new Object[] { targetLanguage };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETLANGUAGE;
			finderArgs = new Object[] {
					targetLanguage,
					
					start, end, orderByComparator
				};
		}

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if (!Validator.equals(targetLanguage,
							dataCollection.getTargetLanguage())) {
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

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			boolean bindTargetLanguage = false;

			if (targetLanguage == null) {
				query.append(_FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_1);
			}
			else if (targetLanguage.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_3);
			}
			else {
				bindTargetLanguage = true;

				query.append(_FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTargetLanguage) {
					qPos.add(targetLanguage);
				}

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where targetLanguage = &#63;.
	 *
	 * @param targetLanguage the target language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByTargetLanguage_First(String targetLanguage,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByTargetLanguage_First(targetLanguage,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("targetLanguage=");
		msg.append(targetLanguage);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where targetLanguage = &#63;.
	 *
	 * @param targetLanguage the target language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByTargetLanguage_First(String targetLanguage,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByTargetLanguage(targetLanguage, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where targetLanguage = &#63;.
	 *
	 * @param targetLanguage the target language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByTargetLanguage_Last(String targetLanguage,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByTargetLanguage_Last(targetLanguage,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("targetLanguage=");
		msg.append(targetLanguage);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where targetLanguage = &#63;.
	 *
	 * @param targetLanguage the target language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByTargetLanguage_Last(String targetLanguage,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTargetLanguage(targetLanguage);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByTargetLanguage(targetLanguage,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where targetLanguage = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param targetLanguage the target language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByTargetLanguage_PrevAndNext(
		long collectionId, String targetLanguage,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByTargetLanguage_PrevAndNext(session, dataCollection,
					targetLanguage, orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByTargetLanguage_PrevAndNext(session, dataCollection,
					targetLanguage, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByTargetLanguage_PrevAndNext(Session session,
		DataCollection dataCollection, String targetLanguage,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		boolean bindTargetLanguage = false;

		if (targetLanguage == null) {
			query.append(_FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_1);
		}
		else if (targetLanguage.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_3);
		}
		else {
			bindTargetLanguage = true;

			query.append(_FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_2);
		}

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTargetLanguage) {
			qPos.add(targetLanguage);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where targetLanguage = &#63; from the database.
	 *
	 * @param targetLanguage the target language
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTargetLanguage(String targetLanguage)
		throws SystemException {
		for (DataCollection dataCollection : findByTargetLanguage(
				targetLanguage, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where targetLanguage = &#63;.
	 *
	 * @param targetLanguage the target language
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByTargetLanguage(String targetLanguage)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TARGETLANGUAGE;

		Object[] finderArgs = new Object[] { targetLanguage };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			boolean bindTargetLanguage = false;

			if (targetLanguage == null) {
				query.append(_FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_1);
			}
			else if (targetLanguage.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_3);
			}
			else {
				bindTargetLanguage = true;

				query.append(_FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTargetLanguage) {
					qPos.add(targetLanguage);
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

	private static final String _FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_1 = "dataCollection.targetLanguage IS NULL";
	private static final String _FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_2 = "dataCollection.targetLanguage = ?";
	private static final String _FINDER_COLUMN_TARGETLANGUAGE_TARGETLANGUAGE_3 = "(dataCollection.targetLanguage IS NULL OR dataCollection.targetLanguage = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			DataCollectionModelImpl.STATUS_COLUMN_BITMASK |
			DataCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the data collections where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByStatus(int status)
		throws SystemException {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByStatus(int status, int start, int end)
		throws SystemException {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByStatus(int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if ((status != dataCollection.getStatus())) {
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

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByStatus_First(int status,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByStatus_First(status,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByStatus_First(int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByStatus_Last(int status,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByStatus_Last(status,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByStatus_Last(int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where status = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByStatus_PrevAndNext(long collectionId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByStatus_PrevAndNext(session, dataCollection, status,
					orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByStatus_PrevAndNext(session, dataCollection, status,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByStatus_PrevAndNext(Session session,
		DataCollection dataCollection, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where status = &#63; from the database.
	 *
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByStatus(int status) throws SystemException {
		for (DataCollection dataCollection : findByStatus(status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByStatus(int status) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS;

		Object[] finderArgs = new Object[] { status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "dataCollection.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
			new String[] { String.class.getName() },
			DataCollectionModelImpl.NAME_COLUMN_BITMASK |
			DataCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the data collections where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByName(String name)
		throws SystemException {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByName(String name, int start, int end)
		throws SystemException {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByName(String name, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name, start, end, orderByComparator };
		}

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if (!Validator.equals(name, dataCollection.getName())) {
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

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByName_First(String name,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByName_First(name,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByName_First(String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByName_Last(String name,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByName_Last(name, orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByName_Last(String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByName(name);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where name = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByName_PrevAndNext(long collectionId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByName_PrevAndNext(session, dataCollection, name,
					orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByName_PrevAndNext(session, dataCollection, name,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByName_PrevAndNext(Session session,
		DataCollection dataCollection, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_NAME_NAME_2);
		}

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where name = &#63; from the database.
	 *
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByName(String name) throws SystemException {
		for (DataCollection dataCollection : findByName(name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByName(String name) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "dataCollection.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "dataCollection.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(dataCollection.name IS NULL OR dataCollection.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTitle",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTitle",
			new String[] { String.class.getName() });

	/**
	 * Returns all the data collections where title LIKE &#63;.
	 *
	 * @param title the title
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTitle(String title)
		throws SystemException {
		return findByTitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTitle(String title, int start, int end)
		throws SystemException {
		return findByTitle(title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTitle(String title, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE;
		finderArgs = new Object[] { title, start, end, orderByComparator };

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if (!StringUtil.wildcardMatches(dataCollection.getTitle(),
							title, CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true)) {
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

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
				}

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByTitle_First(String title,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByTitle_First(title,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByTitle_First(String title,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByTitle(title, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByTitle_Last(String title,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByTitle_Last(title,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByTitle_Last(String title,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTitle(title);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByTitle(title, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where title LIKE &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByTitle_PrevAndNext(long collectionId,
		String title, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByTitle_PrevAndNext(session, dataCollection, title,
					orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByTitle_PrevAndNext(session, dataCollection, title,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByTitle_PrevAndNext(Session session,
		DataCollection dataCollection, String title,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLE_TITLE_1);
		}
		else if (title.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TITLE_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_TITLE_TITLE_2);
		}

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTitle) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where title LIKE &#63; from the database.
	 *
	 * @param title the title
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTitle(String title) throws SystemException {
		for (DataCollection dataCollection : findByTitle(title,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where title LIKE &#63;.
	 *
	 * @param title the title
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByTitle(String title) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE;

		Object[] finderArgs = new Object[] { title };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
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

	private static final String _FINDER_COLUMN_TITLE_TITLE_1 = "dataCollection.title LIKE NULL";
	private static final String _FINDER_COLUMN_TITLE_TITLE_2 = "dataCollection.title LIKE ?";
	private static final String _FINDER_COLUMN_TITLE_TITLE_3 = "(dataCollection.title IS NULL OR dataCollection.title LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE_TL = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTitle_TL",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE_TL =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTitle_TL",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the data collections where title LIKE &#63; and targetLanguage = &#63;.
	 *
	 * @param title the title
	 * @param targetLanguage the target language
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTitle_TL(String title,
		String targetLanguage) throws SystemException {
		return findByTitle_TL(title, targetLanguage, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where title LIKE &#63; and targetLanguage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param targetLanguage the target language
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTitle_TL(String title,
		String targetLanguage, int start, int end) throws SystemException {
		return findByTitle_TL(title, targetLanguage, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where title LIKE &#63; and targetLanguage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param targetLanguage the target language
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTitle_TL(String title,
		String targetLanguage, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE_TL;
		finderArgs = new Object[] {
				title, targetLanguage,
				
				start, end, orderByComparator
			};

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if (!StringUtil.wildcardMatches(dataCollection.getTitle(),
							title, CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true) ||
						!Validator.equals(targetLanguage,
							dataCollection.getTargetLanguage())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TL_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TL_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TL_TITLE_2);
			}

			boolean bindTargetLanguage = false;

			if (targetLanguage == null) {
				query.append(_FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_1);
			}
			else if (targetLanguage.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_3);
			}
			else {
				bindTargetLanguage = true;

				query.append(_FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
				}

				if (bindTargetLanguage) {
					qPos.add(targetLanguage);
				}

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where title LIKE &#63; and targetLanguage = &#63;.
	 *
	 * @param title the title
	 * @param targetLanguage the target language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByTitle_TL_First(String title,
		String targetLanguage, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByTitle_TL_First(title,
				targetLanguage, orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(", targetLanguage=");
		msg.append(targetLanguage);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where title LIKE &#63; and targetLanguage = &#63;.
	 *
	 * @param title the title
	 * @param targetLanguage the target language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByTitle_TL_First(String title,
		String targetLanguage, OrderByComparator orderByComparator)
		throws SystemException {
		List<DataCollection> list = findByTitle_TL(title, targetLanguage, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where title LIKE &#63; and targetLanguage = &#63;.
	 *
	 * @param title the title
	 * @param targetLanguage the target language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByTitle_TL_Last(String title,
		String targetLanguage, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByTitle_TL_Last(title,
				targetLanguage, orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append(", targetLanguage=");
		msg.append(targetLanguage);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where title LIKE &#63; and targetLanguage = &#63;.
	 *
	 * @param title the title
	 * @param targetLanguage the target language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByTitle_TL_Last(String title,
		String targetLanguage, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTitle_TL(title, targetLanguage);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByTitle_TL(title, targetLanguage,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where title LIKE &#63; and targetLanguage = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param title the title
	 * @param targetLanguage the target language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByTitle_TL_PrevAndNext(long collectionId,
		String title, String targetLanguage, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByTitle_TL_PrevAndNext(session, dataCollection,
					title, targetLanguage, orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByTitle_TL_PrevAndNext(session, dataCollection,
					title, targetLanguage, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByTitle_TL_PrevAndNext(Session session,
		DataCollection dataCollection, String title, String targetLanguage,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLE_TL_TITLE_1);
		}
		else if (title.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TITLE_TL_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_TITLE_TL_TITLE_2);
		}

		boolean bindTargetLanguage = false;

		if (targetLanguage == null) {
			query.append(_FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_1);
		}
		else if (targetLanguage.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_3);
		}
		else {
			bindTargetLanguage = true;

			query.append(_FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_2);
		}

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTitle) {
			qPos.add(title);
		}

		if (bindTargetLanguage) {
			qPos.add(targetLanguage);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where title LIKE &#63; and targetLanguage = &#63; from the database.
	 *
	 * @param title the title
	 * @param targetLanguage the target language
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTitle_TL(String title, String targetLanguage)
		throws SystemException {
		for (DataCollection dataCollection : findByTitle_TL(title,
				targetLanguage, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where title LIKE &#63; and targetLanguage = &#63;.
	 *
	 * @param title the title
	 * @param targetLanguage the target language
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByTitle_TL(String title, String targetLanguage)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE_TL;

		Object[] finderArgs = new Object[] { title, targetLanguage };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TL_TITLE_1);
			}
			else if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TL_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TL_TITLE_2);
			}

			boolean bindTargetLanguage = false;

			if (targetLanguage == null) {
				query.append(_FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_1);
			}
			else if (targetLanguage.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_3);
			}
			else {
				bindTargetLanguage = true;

				query.append(_FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
				}

				if (bindTargetLanguage) {
					qPos.add(targetLanguage);
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

	private static final String _FINDER_COLUMN_TITLE_TL_TITLE_1 = "dataCollection.title LIKE NULL AND ";
	private static final String _FINDER_COLUMN_TITLE_TL_TITLE_2 = "dataCollection.title LIKE ? AND ";
	private static final String _FINDER_COLUMN_TITLE_TL_TITLE_3 = "(dataCollection.title IS NULL OR dataCollection.title LIKE '') AND ";
	private static final String _FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_1 = "dataCollection.targetLanguage IS NULL";
	private static final String _FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_2 = "dataCollection.targetLanguage = ?";
	private static final String _FINDER_COLUMN_TITLE_TL_TARGETLANGUAGE_3 = "(dataCollection.targetLanguage IS NULL OR dataCollection.targetLanguage = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAMEVERSION =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByNameVersion",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAMEVERSION =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByNameVersion",
			new String[] { String.class.getName(), String.class.getName() },
			DataCollectionModelImpl.NAME_COLUMN_BITMASK |
			DataCollectionModelImpl.VERSION_COLUMN_BITMASK |
			DataCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAMEVERSION = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNameVersion",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the data collections where name = &#63; and version = &#63;.
	 *
	 * @param name the name
	 * @param version the version
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByNameVersion(String name, String version)
		throws SystemException {
		return findByNameVersion(name, version, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where name = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param version the version
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByNameVersion(String name, String version,
		int start, int end) throws SystemException {
		return findByNameVersion(name, version, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where name = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param version the version
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByNameVersion(String name, String version,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAMEVERSION;
			finderArgs = new Object[] { name, version };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAMEVERSION;
			finderArgs = new Object[] {
					name, version,
					
					start, end, orderByComparator
				};
		}

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if (!Validator.equals(name, dataCollection.getName()) ||
						!Validator.equals(version, dataCollection.getVersion())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_2);
			}

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_1);
			}
			else if (version.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (bindVersion) {
					qPos.add(version);
				}

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where name = &#63; and version = &#63;.
	 *
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByNameVersion_First(String name, String version,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByNameVersion_First(name, version,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", version=");
		msg.append(version);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where name = &#63; and version = &#63;.
	 *
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByNameVersion_First(String name, String version,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByNameVersion(name, version, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where name = &#63; and version = &#63;.
	 *
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByNameVersion_Last(String name, String version,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByNameVersion_Last(name, version,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", version=");
		msg.append(version);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where name = &#63; and version = &#63;.
	 *
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByNameVersion_Last(String name, String version,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByNameVersion(name, version);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByNameVersion(name, version, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where name = &#63; and version = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByNameVersion_PrevAndNext(long collectionId,
		String name, String version, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByNameVersion_PrevAndNext(session, dataCollection,
					name, version, orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByNameVersion_PrevAndNext(session, dataCollection,
					name, version, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByNameVersion_PrevAndNext(Session session,
		DataCollection dataCollection, String name, String version,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_NAMEVERSION_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NAMEVERSION_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_NAMEVERSION_NAME_2);
		}

		boolean bindVersion = false;

		if (version == null) {
			query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_1);
		}
		else if (version.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_3);
		}
		else {
			bindVersion = true;

			query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_2);
		}

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(name);
		}

		if (bindVersion) {
			qPos.add(version);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where name = &#63; and version = &#63; from the database.
	 *
	 * @param name the name
	 * @param version the version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByNameVersion(String name, String version)
		throws SystemException {
		for (DataCollection dataCollection : findByNameVersion(name, version,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where name = &#63; and version = &#63;.
	 *
	 * @param name the name
	 * @param version the version
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByNameVersion(String name, String version)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAMEVERSION;

		Object[] finderArgs = new Object[] { name, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_2);
			}

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_1);
			}
			else if (version.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (bindVersion) {
					qPos.add(version);
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

	private static final String _FINDER_COLUMN_NAMEVERSION_NAME_1 = "dataCollection.name IS NULL AND ";
	private static final String _FINDER_COLUMN_NAMEVERSION_NAME_2 = "dataCollection.name = ? AND ";
	private static final String _FINDER_COLUMN_NAMEVERSION_NAME_3 = "(dataCollection.name IS NULL OR dataCollection.name = '') AND ";
	private static final String _FINDER_COLUMN_NAMEVERSION_VERSION_1 = "dataCollection.version IS NULL";
	private static final String _FINDER_COLUMN_NAMEVERSION_VERSION_2 = "dataCollection.version = ?";
	private static final String _FINDER_COLUMN_NAMEVERSION_VERSION_3 = "(dataCollection.version IS NULL OR dataCollection.version = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPEID = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTypeID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTypeID",
			new String[] { Long.class.getName() },
			DataCollectionModelImpl.TYPEID_COLUMN_BITMASK |
			DataCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPEID = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTypeID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the data collections where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTypeID(long typeId)
		throws SystemException {
		return findByTypeID(typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTypeID(long typeId, int start, int end)
		throws SystemException {
		return findByTypeID(typeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByTypeID(long typeId, int start, int end,
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

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if ((typeId != dataCollection.getTypeId())) {
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

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByTypeID_First(long typeId,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByTypeID_First(typeId,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("typeId=");
		msg.append(typeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByTypeID_First(long typeId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByTypeID(typeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByTypeID_Last(long typeId,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByTypeID_Last(typeId,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("typeId=");
		msg.append(typeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByTypeID_Last(long typeId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTypeID(typeId);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByTypeID(typeId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where typeId = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByTypeID_PrevAndNext(long collectionId,
		long typeId, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByTypeID_PrevAndNext(session, dataCollection, typeId,
					orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByTypeID_PrevAndNext(session, dataCollection, typeId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByTypeID_PrevAndNext(Session session,
		DataCollection dataCollection, long typeId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(typeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTypeID(long typeId) throws SystemException {
		for (DataCollection dataCollection : findByTypeID(typeId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching data collections
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

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

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

	private static final String _FINDER_COLUMN_TYPEID_TYPEID_2 = "dataCollection.typeId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NAME = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_Name",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_NAME =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_Name",
			new String[] { Long.class.getName(), String.class.getName() },
			DataCollectionModelImpl.GROUPID_COLUMN_BITMASK |
			DataCollectionModelImpl.NAME_COLUMN_BITMASK |
			DataCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_NAME = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_Name",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the data collections where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByG_Name(long groupId, String name)
		throws SystemException {
		return findByG_Name(groupId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where groupId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByG_Name(long groupId, String name,
		int start, int end) throws SystemException {
		return findByG_Name(groupId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where groupId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByG_Name(long groupId, String name,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_NAME;
			finderArgs = new Object[] { groupId, name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NAME;
			finderArgs = new Object[] {
					groupId, name,
					
					start, end, orderByComparator
				};
		}

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if ((groupId != dataCollection.getGroupId()) ||
						!Validator.equals(name, dataCollection.getName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_G_NAME_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByG_Name_First(long groupId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByG_Name_First(groupId, name,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByG_Name_First(long groupId, String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByG_Name(groupId, name, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByG_Name_Last(long groupId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByG_Name_Last(groupId, name,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByG_Name_Last(long groupId, String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_Name(groupId, name);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByG_Name(groupId, name, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where groupId = &#63; and name = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param groupId the group ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByG_Name_PrevAndNext(long collectionId,
		long groupId, String name, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByG_Name_PrevAndNext(session, dataCollection,
					groupId, name, orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByG_Name_PrevAndNext(session, dataCollection,
					groupId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByG_Name_PrevAndNext(Session session,
		DataCollection dataCollection, long groupId, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		query.append(_FINDER_COLUMN_G_NAME_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_NAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_NAME_NAME_2);
		}

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where groupId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_Name(long groupId, String name)
		throws SystemException {
		for (DataCollection dataCollection : findByG_Name(groupId, name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_Name(long groupId, String name)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_NAME;

		Object[] finderArgs = new Object[] { groupId, name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_G_NAME_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_G_NAME_GROUPID_2 = "dataCollection.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_NAME_NAME_1 = "dataCollection.name IS NULL";
	private static final String _FINDER_COLUMN_G_NAME_NAME_2 = "dataCollection.name = ?";
	private static final String _FINDER_COLUMN_G_NAME_NAME_3 = "(dataCollection.name IS NULL OR dataCollection.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_STATUS = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_Status",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_STATUS =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_Status",
			new String[] { Long.class.getName(), Integer.class.getName() },
			DataCollectionModelImpl.GROUPID_COLUMN_BITMASK |
			DataCollectionModelImpl.STATUS_COLUMN_BITMASK |
			DataCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_STATUS = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_Status",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the data collections where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByG_Status(long groupId, int status)
		throws SystemException {
		return findByG_Status(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByG_Status(long groupId, int status,
		int start, int end) throws SystemException {
		return findByG_Status(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByG_Status(long groupId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_STATUS;
			finderArgs = new Object[] { groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_STATUS;
			finderArgs = new Object[] {
					groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if ((groupId != dataCollection.getGroupId()) ||
						(status != dataCollection.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_G_STATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_G_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByG_Status_First(long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByG_Status_First(groupId, status,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByG_Status_First(long groupId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByG_Status(groupId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByG_Status_Last(long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByG_Status_Last(groupId, status,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByG_Status_Last(long groupId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_Status(groupId, status);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByG_Status(groupId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByG_Status_PrevAndNext(long collectionId,
		long groupId, int status, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByG_Status_PrevAndNext(session, dataCollection,
					groupId, status, orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByG_Status_PrevAndNext(session, dataCollection,
					groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByG_Status_PrevAndNext(Session session,
		DataCollection dataCollection, long groupId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		query.append(_FINDER_COLUMN_G_STATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_G_STATUS_STATUS_2);

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_Status(long groupId, int status)
		throws SystemException {
		for (DataCollection dataCollection : findByG_Status(groupId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_Status(long groupId, int status)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_STATUS;

		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_G_STATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_G_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_G_STATUS_GROUPID_2 = "dataCollection.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_STATUS_STATUS_2 = "dataCollection.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			DataCollectionModelImpl.USERID_COLUMN_BITMASK |
			DataCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the data collections where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataCollection dataCollection : list) {
				if ((userId != dataCollection.getUserId())) {
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

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Returns the first data collection in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByUserId_First(userId,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the first data collection in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataCollection> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data collection in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByUserId_Last(userId,
				orderByComparator);

		if (dataCollection != null) {
			return dataCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataCollectionException(msg.toString());
	}

	/**
	 * Returns the last data collection in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<DataCollection> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data collections before and after the current data collection in the ordered set where userId = &#63;.
	 *
	 * @param collectionId the primary key of the current data collection
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection[] findByUserId_PrevAndNext(long collectionId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByPrimaryKey(collectionId);

		Session session = null;

		try {
			session = openSession();

			DataCollection[] array = new DataCollectionImpl[3];

			array[0] = getByUserId_PrevAndNext(session, dataCollection, userId,
					orderByComparator, true);

			array[1] = dataCollection;

			array[2] = getByUserId_PrevAndNext(session, dataCollection, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataCollection getByUserId_PrevAndNext(Session session,
		DataCollection dataCollection, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(DataCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data collections where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId(long userId) throws SystemException {
		for (DataCollection dataCollection : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserId(long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "dataCollection.userId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_T_NAMEVERSION = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED,
			DataCollectionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByT_NameVersion",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			DataCollectionModelImpl.TYPEID_COLUMN_BITMASK |
			DataCollectionModelImpl.NAME_COLUMN_BITMASK |
			DataCollectionModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_T_NAMEVERSION = new FinderPath(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByT_NameVersion",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the data collection where typeId = &#63; and name = &#63; and version = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionException} if it could not be found.
	 *
	 * @param typeId the type ID
	 * @param name the name
	 * @param version the version
	 * @return the matching data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByT_NameVersion(long typeId, String name,
		String version) throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByT_NameVersion(typeId, name,
				version);

		if (dataCollection == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("typeId=");
			msg.append(typeId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDataCollectionException(msg.toString());
		}

		return dataCollection;
	}

	/**
	 * Returns the data collection where typeId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param typeId the type ID
	 * @param name the name
	 * @param version the version
	 * @return the matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByT_NameVersion(long typeId, String name,
		String version) throws SystemException {
		return fetchByT_NameVersion(typeId, name, version, true);
	}

	/**
	 * Returns the data collection where typeId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param typeId the type ID
	 * @param name the name
	 * @param version the version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching data collection, or <code>null</code> if a matching data collection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByT_NameVersion(long typeId, String name,
		String version, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { typeId, name, version };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_T_NAMEVERSION,
					finderArgs, this);
		}

		if (result instanceof DataCollection) {
			DataCollection dataCollection = (DataCollection)result;

			if ((typeId != dataCollection.getTypeId()) ||
					!Validator.equals(name, dataCollection.getName()) ||
					!Validator.equals(version, dataCollection.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_T_NAMEVERSION_TYPEID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_T_NAMEVERSION_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_NAMEVERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_T_NAMEVERSION_NAME_2);
			}

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_T_NAMEVERSION_VERSION_1);
			}
			else if (version.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_NAMEVERSION_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_T_NAMEVERSION_VERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

				if (bindName) {
					qPos.add(name);
				}

				if (bindVersion) {
					qPos.add(version);
				}

				List<DataCollection> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_T_NAMEVERSION,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"DataCollectionPersistenceImpl.fetchByT_NameVersion(long, String, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					DataCollection dataCollection = list.get(0);

					result = dataCollection;

					cacheResult(dataCollection);

					if ((dataCollection.getTypeId() != typeId) ||
							(dataCollection.getName() == null) ||
							!dataCollection.getName().equals(name) ||
							(dataCollection.getVersion() == null) ||
							!dataCollection.getVersion().equals(version)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_T_NAMEVERSION,
							finderArgs, dataCollection);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_T_NAMEVERSION,
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
			return (DataCollection)result;
		}
	}

	/**
	 * Removes the data collection where typeId = &#63; and name = &#63; and version = &#63; from the database.
	 *
	 * @param typeId the type ID
	 * @param name the name
	 * @param version the version
	 * @return the data collection that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection removeByT_NameVersion(long typeId, String name,
		String version) throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = findByT_NameVersion(typeId, name,
				version);

		return remove(dataCollection);
	}

	/**
	 * Returns the number of data collections where typeId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param typeId the type ID
	 * @param name the name
	 * @param version the version
	 * @return the number of matching data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByT_NameVersion(long typeId, String name, String version)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_T_NAMEVERSION;

		Object[] finderArgs = new Object[] { typeId, name, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DATACOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_T_NAMEVERSION_TYPEID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_T_NAMEVERSION_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_NAMEVERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_T_NAMEVERSION_NAME_2);
			}

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_T_NAMEVERSION_VERSION_1);
			}
			else if (version.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_NAMEVERSION_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_T_NAMEVERSION_VERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

				if (bindName) {
					qPos.add(name);
				}

				if (bindVersion) {
					qPos.add(version);
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

	private static final String _FINDER_COLUMN_T_NAMEVERSION_TYPEID_2 = "dataCollection.typeId = ? AND ";
	private static final String _FINDER_COLUMN_T_NAMEVERSION_NAME_1 = "dataCollection.name IS NULL AND ";
	private static final String _FINDER_COLUMN_T_NAMEVERSION_NAME_2 = "dataCollection.name = ? AND ";
	private static final String _FINDER_COLUMN_T_NAMEVERSION_NAME_3 = "(dataCollection.name IS NULL OR dataCollection.name = '') AND ";
	private static final String _FINDER_COLUMN_T_NAMEVERSION_VERSION_1 = "dataCollection.version IS NULL";
	private static final String _FINDER_COLUMN_T_NAMEVERSION_VERSION_2 = "dataCollection.version = ?";
	private static final String _FINDER_COLUMN_T_NAMEVERSION_VERSION_3 = "(dataCollection.version IS NULL OR dataCollection.version = '')";

	public DataCollectionPersistenceImpl() {
		setModelClass(DataCollection.class);
	}

	/**
	 * Caches the data collection in the entity cache if it is enabled.
	 *
	 * @param dataCollection the data collection
	 */
	@Override
	public void cacheResult(DataCollection dataCollection) {
		EntityCacheUtil.putResult(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionImpl.class, dataCollection.getPrimaryKey(),
			dataCollection);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dataCollection.getUuid(), dataCollection.getGroupId() },
			dataCollection);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_T_NAMEVERSION,
			new Object[] {
				dataCollection.getTypeId(), dataCollection.getName(),
				dataCollection.getVersion()
			}, dataCollection);

		dataCollection.resetOriginalValues();
	}

	/**
	 * Caches the data collections in the entity cache if it is enabled.
	 *
	 * @param dataCollections the data collections
	 */
	@Override
	public void cacheResult(List<DataCollection> dataCollections) {
		for (DataCollection dataCollection : dataCollections) {
			if (EntityCacheUtil.getResult(
						DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
						DataCollectionImpl.class, dataCollection.getPrimaryKey()) == null) {
				cacheResult(dataCollection);
			}
			else {
				dataCollection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all data collections.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DataCollectionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DataCollectionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the data collection.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DataCollection dataCollection) {
		EntityCacheUtil.removeResult(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionImpl.class, dataCollection.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(dataCollection);
	}

	@Override
	public void clearCache(List<DataCollection> dataCollections) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DataCollection dataCollection : dataCollections) {
			EntityCacheUtil.removeResult(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
				DataCollectionImpl.class, dataCollection.getPrimaryKey());

			clearUniqueFindersCache(dataCollection);
		}
	}

	protected void cacheUniqueFindersCache(DataCollection dataCollection) {
		if (dataCollection.isNew()) {
			Object[] args = new Object[] {
					dataCollection.getUuid(), dataCollection.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				dataCollection);

			args = new Object[] {
					dataCollection.getTypeId(), dataCollection.getName(),
					dataCollection.getVersion()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_NAMEVERSION, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_T_NAMEVERSION, args,
				dataCollection);
		}
		else {
			DataCollectionModelImpl dataCollectionModelImpl = (DataCollectionModelImpl)dataCollection;

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollection.getUuid(), dataCollection.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					dataCollection);
			}

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_T_NAMEVERSION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollection.getTypeId(), dataCollection.getName(),
						dataCollection.getVersion()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_NAMEVERSION,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_T_NAMEVERSION,
					args, dataCollection);
			}
		}
	}

	protected void clearUniqueFindersCache(DataCollection dataCollection) {
		DataCollectionModelImpl dataCollectionModelImpl = (DataCollectionModelImpl)dataCollection;

		Object[] args = new Object[] {
				dataCollection.getUuid(), dataCollection.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((dataCollectionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					dataCollectionModelImpl.getOriginalUuid(),
					dataCollectionModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				dataCollection.getTypeId(), dataCollection.getName(),
				dataCollection.getVersion()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_NAMEVERSION, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_T_NAMEVERSION, args);

		if ((dataCollectionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_T_NAMEVERSION.getColumnBitmask()) != 0) {
			args = new Object[] {
					dataCollectionModelImpl.getOriginalTypeId(),
					dataCollectionModelImpl.getOriginalName(),
					dataCollectionModelImpl.getOriginalVersion()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_NAMEVERSION,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_T_NAMEVERSION,
				args);
		}
	}

	/**
	 * Creates a new data collection with the primary key. Does not add the data collection to the database.
	 *
	 * @param collectionId the primary key for the new data collection
	 * @return the new data collection
	 */
	@Override
	public DataCollection create(long collectionId) {
		DataCollection dataCollection = new DataCollectionImpl();

		dataCollection.setNew(true);
		dataCollection.setPrimaryKey(collectionId);

		String uuid = PortalUUIDUtil.generate();

		dataCollection.setUuid(uuid);

		return dataCollection;
	}

	/**
	 * Removes the data collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param collectionId the primary key of the data collection
	 * @return the data collection that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection remove(long collectionId)
		throws NoSuchDataCollectionException, SystemException {
		return remove((Serializable)collectionId);
	}

	/**
	 * Removes the data collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the data collection
	 * @return the data collection that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection remove(Serializable primaryKey)
		throws NoSuchDataCollectionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DataCollection dataCollection = (DataCollection)session.get(DataCollectionImpl.class,
					primaryKey);

			if (dataCollection == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataCollectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dataCollection);
		}
		catch (NoSuchDataCollectionException nsee) {
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
	protected DataCollection removeImpl(DataCollection dataCollection)
		throws SystemException {
		dataCollection = toUnwrappedModel(dataCollection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dataCollection)) {
				dataCollection = (DataCollection)session.get(DataCollectionImpl.class,
						dataCollection.getPrimaryKeyObj());
			}

			if (dataCollection != null) {
				session.delete(dataCollection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dataCollection != null) {
			clearCache(dataCollection);
		}

		return dataCollection;
	}

	@Override
	public DataCollection updateImpl(
		com.kisti.osp.icecap.model.DataCollection dataCollection)
		throws SystemException {
		dataCollection = toUnwrappedModel(dataCollection);

		boolean isNew = dataCollection.isNew();

		DataCollectionModelImpl dataCollectionModelImpl = (DataCollectionModelImpl)dataCollection;

		if (Validator.isNull(dataCollection.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dataCollection.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (dataCollection.isNew()) {
				session.save(dataCollection);

				dataCollection.setNew(false);
			}
			else {
				session.merge(dataCollection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DataCollectionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollectionModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dataCollectionModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollectionModelImpl.getOriginalUuid(),
						dataCollectionModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dataCollectionModelImpl.getUuid(),
						dataCollectionModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETLANGUAGE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollectionModelImpl.getOriginalTargetLanguage()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETLANGUAGE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETLANGUAGE,
					args);

				args = new Object[] { dataCollectionModelImpl.getTargetLanguage() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETLANGUAGE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETLANGUAGE,
					args);
			}

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollectionModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] { dataCollectionModelImpl.getStatus() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollectionModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { dataCollectionModelImpl.getName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAMEVERSION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollectionModelImpl.getOriginalName(),
						dataCollectionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAMEVERSION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAMEVERSION,
					args);

				args = new Object[] {
						dataCollectionModelImpl.getName(),
						dataCollectionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAMEVERSION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAMEVERSION,
					args);
			}

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollectionModelImpl.getOriginalTypeId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID,
					args);

				args = new Object[] { dataCollectionModelImpl.getTypeId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID,
					args);
			}

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollectionModelImpl.getOriginalGroupId(),
						dataCollectionModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_NAME,
					args);

				args = new Object[] {
						dataCollectionModelImpl.getGroupId(),
						dataCollectionModelImpl.getName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_NAME,
					args);
			}

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollectionModelImpl.getOriginalGroupId(),
						dataCollectionModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_STATUS,
					args);

				args = new Object[] {
						dataCollectionModelImpl.getGroupId(),
						dataCollectionModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_STATUS,
					args);
			}

			if ((dataCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataCollectionModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { dataCollectionModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
		}

		EntityCacheUtil.putResult(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DataCollectionImpl.class, dataCollection.getPrimaryKey(),
			dataCollection);

		clearUniqueFindersCache(dataCollection);
		cacheUniqueFindersCache(dataCollection);

		return dataCollection;
	}

	protected DataCollection toUnwrappedModel(DataCollection dataCollection) {
		if (dataCollection instanceof DataCollectionImpl) {
			return dataCollection;
		}

		DataCollectionImpl dataCollectionImpl = new DataCollectionImpl();

		dataCollectionImpl.setNew(dataCollection.isNew());
		dataCollectionImpl.setPrimaryKey(dataCollection.getPrimaryKey());

		dataCollectionImpl.setUuid(dataCollection.getUuid());
		dataCollectionImpl.setName(dataCollection.getName());
		dataCollectionImpl.setVersion(dataCollection.getVersion());
		dataCollectionImpl.setTitle(dataCollection.getTitle());
		dataCollectionImpl.setGroupId(dataCollection.getGroupId());
		dataCollectionImpl.setCompanyId(dataCollection.getCompanyId());
		dataCollectionImpl.setUserId(dataCollection.getUserId());
		dataCollectionImpl.setCreateDate(dataCollection.getCreateDate());
		dataCollectionImpl.setModifiedDate(dataCollection.getModifiedDate());
		dataCollectionImpl.setDescription(dataCollection.getDescription());
		dataCollectionImpl.setTargetLanguage(dataCollection.getTargetLanguage());
		dataCollectionImpl.setStatus(dataCollection.getStatus());
		dataCollectionImpl.setAccessMethod(dataCollection.getAccessMethod());
		dataCollectionImpl.setCollectionId(dataCollection.getCollectionId());
		dataCollectionImpl.setTypeId(dataCollection.getTypeId());
		dataCollectionImpl.setFolderId(dataCollection.getFolderId());

		return dataCollectionImpl;
	}

	/**
	 * Returns the data collection with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the data collection
	 * @return the data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataCollectionException, SystemException {
		DataCollection dataCollection = fetchByPrimaryKey(primaryKey);

		if (dataCollection == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataCollectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dataCollection;
	}

	/**
	 * Returns the data collection with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionException} if it could not be found.
	 *
	 * @param collectionId the primary key of the data collection
	 * @return the data collection
	 * @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection findByPrimaryKey(long collectionId)
		throws NoSuchDataCollectionException, SystemException {
		return findByPrimaryKey((Serializable)collectionId);
	}

	/**
	 * Returns the data collection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the data collection
	 * @return the data collection, or <code>null</code> if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		DataCollection dataCollection = (DataCollection)EntityCacheUtil.getResult(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
				DataCollectionImpl.class, primaryKey);

		if (dataCollection == _nullDataCollection) {
			return null;
		}

		if (dataCollection == null) {
			Session session = null;

			try {
				session = openSession();

				dataCollection = (DataCollection)session.get(DataCollectionImpl.class,
						primaryKey);

				if (dataCollection != null) {
					cacheResult(dataCollection);
				}
				else {
					EntityCacheUtil.putResult(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
						DataCollectionImpl.class, primaryKey,
						_nullDataCollection);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DataCollectionModelImpl.ENTITY_CACHE_ENABLED,
					DataCollectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dataCollection;
	}

	/**
	 * Returns the data collection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param collectionId the primary key of the data collection
	 * @return the data collection, or <code>null</code> if a data collection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataCollection fetchByPrimaryKey(long collectionId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)collectionId);
	}

	/**
	 * Returns all the data collections.
	 *
	 * @return the data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @return the range of data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the data collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data collections
	 * @param end the upper bound of the range of data collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of data collections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataCollection> findAll(int start, int end,
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

		List<DataCollection> list = (List<DataCollection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DATACOLLECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DATACOLLECTION;

				if (pagination) {
					sql = sql.concat(DataCollectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DataCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataCollection>(list);
				}
				else {
					list = (List<DataCollection>)QueryUtil.list(q,
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
	 * Removes all the data collections from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (DataCollection dataCollection : findAll()) {
			remove(dataCollection);
		}
	}

	/**
	 * Returns the number of data collections.
	 *
	 * @return the number of data collections
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

				Query q = session.createQuery(_SQL_COUNT_DATACOLLECTION);

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
	 * Initializes the data collection persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.osp.icecap.model.DataCollection")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DataCollection>> listenersList = new ArrayList<ModelListener<DataCollection>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DataCollection>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DataCollectionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DATACOLLECTION = "SELECT dataCollection FROM DataCollection dataCollection";
	private static final String _SQL_SELECT_DATACOLLECTION_WHERE = "SELECT dataCollection FROM DataCollection dataCollection WHERE ";
	private static final String _SQL_COUNT_DATACOLLECTION = "SELECT COUNT(dataCollection) FROM DataCollection dataCollection";
	private static final String _SQL_COUNT_DATACOLLECTION_WHERE = "SELECT COUNT(dataCollection) FROM DataCollection dataCollection WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dataCollection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DataCollection exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DataCollection exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DataCollectionPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static DataCollection _nullDataCollection = new DataCollectionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DataCollection> toCacheModel() {
				return _nullDataCollectionCacheModel;
			}
		};

	private static CacheModel<DataCollection> _nullDataCollectionCacheModel = new CacheModel<DataCollection>() {
			@Override
			public DataCollection toEntityModel() {
				return _nullDataCollection;
			}
		};
}