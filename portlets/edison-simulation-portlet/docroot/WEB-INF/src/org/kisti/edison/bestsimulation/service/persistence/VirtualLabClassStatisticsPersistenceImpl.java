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

package org.kisti.edison.bestsimulation.service.persistence;

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

import org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException;
import org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics;
import org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsImpl;
import org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the virtual lab class statistics service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see VirtualLabClassStatisticsPersistence
 * @see VirtualLabClassStatisticsUtil
 * @generated
 */
public class VirtualLabClassStatisticsPersistenceImpl
	extends BasePersistenceImpl<VirtualLabClassStatistics>
	implements VirtualLabClassStatisticsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VirtualLabClassStatisticsUtil} to access the virtual lab class statistics persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VirtualLabClassStatisticsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabClassStatisticsModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabClassStatisticsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabClassStatisticsModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabClassStatisticsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabClassStatisticsModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_VIRTUALLABIDANDCLASSID =
		new FinderPath(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabClassStatisticsModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabClassStatisticsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByvirtualLabIdAndClassId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIRTUALLABIDANDCLASSID =
		new FinderPath(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabClassStatisticsModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabClassStatisticsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByvirtualLabIdAndClassId",
			new String[] { Long.class.getName(), String.class.getName() },
			VirtualLabClassStatisticsModelImpl.VIRTUALLABID_COLUMN_BITMASK |
			VirtualLabClassStatisticsModelImpl.CLASSID_COLUMN_BITMASK |
			VirtualLabClassStatisticsModelImpl.CLASSCREATEDT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_VIRTUALLABIDANDCLASSID = new FinderPath(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabClassStatisticsModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByvirtualLabIdAndClassId",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the virtual lab class statisticses where virtualLabId = &#63; and classId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param classId the class ID
	 * @return the matching virtual lab class statisticses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabClassStatistics> findByvirtualLabIdAndClassId(
		long virtualLabId, String classId) throws SystemException {
		return findByvirtualLabIdAndClassId(virtualLabId, classId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual lab class statisticses where virtualLabId = &#63; and classId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param classId the class ID
	 * @param start the lower bound of the range of virtual lab class statisticses
	 * @param end the upper bound of the range of virtual lab class statisticses (not inclusive)
	 * @return the range of matching virtual lab class statisticses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabClassStatistics> findByvirtualLabIdAndClassId(
		long virtualLabId, String classId, int start, int end)
		throws SystemException {
		return findByvirtualLabIdAndClassId(virtualLabId, classId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the virtual lab class statisticses where virtualLabId = &#63; and classId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param classId the class ID
	 * @param start the lower bound of the range of virtual lab class statisticses
	 * @param end the upper bound of the range of virtual lab class statisticses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching virtual lab class statisticses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabClassStatistics> findByvirtualLabIdAndClassId(
		long virtualLabId, String classId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIRTUALLABIDANDCLASSID;
			finderArgs = new Object[] { virtualLabId, classId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_VIRTUALLABIDANDCLASSID;
			finderArgs = new Object[] {
					virtualLabId, classId,
					
					start, end, orderByComparator
				};
		}

		List<VirtualLabClassStatistics> list = (List<VirtualLabClassStatistics>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (VirtualLabClassStatistics virtualLabClassStatistics : list) {
				if ((virtualLabId != virtualLabClassStatistics.getVirtualLabId()) ||
						!Validator.equals(classId,
							virtualLabClassStatistics.getClassId())) {
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

			query.append(_SQL_SELECT_VIRTUALLABCLASSSTATISTICS_WHERE);

			query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_VIRTUALLABID_2);

			boolean bindClassId = false;

			if (classId == null) {
				query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_1);
			}
			else if (classId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_3);
			}
			else {
				bindClassId = true;

				query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(VirtualLabClassStatisticsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(virtualLabId);

				if (bindClassId) {
					qPos.add(classId);
				}

				if (!pagination) {
					list = (List<VirtualLabClassStatistics>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<VirtualLabClassStatistics>(list);
				}
				else {
					list = (List<VirtualLabClassStatistics>)QueryUtil.list(q,
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
	 * Returns the first virtual lab class statistics in the ordered set where virtualLabId = &#63; and classId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param classId the class ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual lab class statistics
	 * @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a matching virtual lab class statistics could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics findByvirtualLabIdAndClassId_First(
		long virtualLabId, String classId, OrderByComparator orderByComparator)
		throws NoSuchVirtualLabClassStatisticsException, SystemException {
		VirtualLabClassStatistics virtualLabClassStatistics = fetchByvirtualLabIdAndClassId_First(virtualLabId,
				classId, orderByComparator);

		if (virtualLabClassStatistics != null) {
			return virtualLabClassStatistics;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("virtualLabId=");
		msg.append(virtualLabId);

		msg.append(", classId=");
		msg.append(classId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVirtualLabClassStatisticsException(msg.toString());
	}

	/**
	 * Returns the first virtual lab class statistics in the ordered set where virtualLabId = &#63; and classId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param classId the class ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual lab class statistics, or <code>null</code> if a matching virtual lab class statistics could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics fetchByvirtualLabIdAndClassId_First(
		long virtualLabId, String classId, OrderByComparator orderByComparator)
		throws SystemException {
		List<VirtualLabClassStatistics> list = findByvirtualLabIdAndClassId(virtualLabId,
				classId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last virtual lab class statistics in the ordered set where virtualLabId = &#63; and classId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param classId the class ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual lab class statistics
	 * @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a matching virtual lab class statistics could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics findByvirtualLabIdAndClassId_Last(
		long virtualLabId, String classId, OrderByComparator orderByComparator)
		throws NoSuchVirtualLabClassStatisticsException, SystemException {
		VirtualLabClassStatistics virtualLabClassStatistics = fetchByvirtualLabIdAndClassId_Last(virtualLabId,
				classId, orderByComparator);

		if (virtualLabClassStatistics != null) {
			return virtualLabClassStatistics;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("virtualLabId=");
		msg.append(virtualLabId);

		msg.append(", classId=");
		msg.append(classId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVirtualLabClassStatisticsException(msg.toString());
	}

	/**
	 * Returns the last virtual lab class statistics in the ordered set where virtualLabId = &#63; and classId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param classId the class ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual lab class statistics, or <code>null</code> if a matching virtual lab class statistics could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics fetchByvirtualLabIdAndClassId_Last(
		long virtualLabId, String classId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByvirtualLabIdAndClassId(virtualLabId, classId);

		if (count == 0) {
			return null;
		}

		List<VirtualLabClassStatistics> list = findByvirtualLabIdAndClassId(virtualLabId,
				classId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the virtual lab class statisticses before and after the current virtual lab class statistics in the ordered set where virtualLabId = &#63; and classId = &#63;.
	 *
	 * @param virtualLabClassStatisticsPK the primary key of the current virtual lab class statistics
	 * @param virtualLabId the virtual lab ID
	 * @param classId the class ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next virtual lab class statistics
	 * @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a virtual lab class statistics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics[] findByvirtualLabIdAndClassId_PrevAndNext(
		VirtualLabClassStatisticsPK virtualLabClassStatisticsPK,
		long virtualLabId, String classId, OrderByComparator orderByComparator)
		throws NoSuchVirtualLabClassStatisticsException, SystemException {
		VirtualLabClassStatistics virtualLabClassStatistics = findByPrimaryKey(virtualLabClassStatisticsPK);

		Session session = null;

		try {
			session = openSession();

			VirtualLabClassStatistics[] array = new VirtualLabClassStatisticsImpl[3];

			array[0] = getByvirtualLabIdAndClassId_PrevAndNext(session,
					virtualLabClassStatistics, virtualLabId, classId,
					orderByComparator, true);

			array[1] = virtualLabClassStatistics;

			array[2] = getByvirtualLabIdAndClassId_PrevAndNext(session,
					virtualLabClassStatistics, virtualLabId, classId,
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

	protected VirtualLabClassStatistics getByvirtualLabIdAndClassId_PrevAndNext(
		Session session, VirtualLabClassStatistics virtualLabClassStatistics,
		long virtualLabId, String classId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VIRTUALLABCLASSSTATISTICS_WHERE);

		query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_VIRTUALLABID_2);

		boolean bindClassId = false;

		if (classId == null) {
			query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_1);
		}
		else if (classId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_3);
		}
		else {
			bindClassId = true;

			query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_2);
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
			query.append(VirtualLabClassStatisticsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(virtualLabId);

		if (bindClassId) {
			qPos.add(classId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(virtualLabClassStatistics);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<VirtualLabClassStatistics> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the virtual lab class statisticses where virtualLabId = &#63; and classId = &#63; from the database.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param classId the class ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByvirtualLabIdAndClassId(long virtualLabId, String classId)
		throws SystemException {
		for (VirtualLabClassStatistics virtualLabClassStatistics : findByvirtualLabIdAndClassId(
				virtualLabId, classId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(virtualLabClassStatistics);
		}
	}

	/**
	 * Returns the number of virtual lab class statisticses where virtualLabId = &#63; and classId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param classId the class ID
	 * @return the number of matching virtual lab class statisticses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByvirtualLabIdAndClassId(long virtualLabId, String classId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_VIRTUALLABIDANDCLASSID;

		Object[] finderArgs = new Object[] { virtualLabId, classId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VIRTUALLABCLASSSTATISTICS_WHERE);

			query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_VIRTUALLABID_2);

			boolean bindClassId = false;

			if (classId == null) {
				query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_1);
			}
			else if (classId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_3);
			}
			else {
				bindClassId = true;

				query.append(_FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(virtualLabId);

				if (bindClassId) {
					qPos.add(classId);
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

	private static final String _FINDER_COLUMN_VIRTUALLABIDANDCLASSID_VIRTUALLABID_2 =
		"virtualLabClassStatistics.id.virtualLabId = ? AND ";
	private static final String _FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_1 = "virtualLabClassStatistics.id.classId IS NULL";
	private static final String _FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_2 = "virtualLabClassStatistics.id.classId = ?";
	private static final String _FINDER_COLUMN_VIRTUALLABIDANDCLASSID_CLASSID_3 = "(virtualLabClassStatistics.id.classId IS NULL OR virtualLabClassStatistics.id.classId = '')";

	public VirtualLabClassStatisticsPersistenceImpl() {
		setModelClass(VirtualLabClassStatistics.class);
	}

	/**
	 * Caches the virtual lab class statistics in the entity cache if it is enabled.
	 *
	 * @param virtualLabClassStatistics the virtual lab class statistics
	 */
	@Override
	public void cacheResult(VirtualLabClassStatistics virtualLabClassStatistics) {
		EntityCacheUtil.putResult(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabClassStatisticsImpl.class,
			virtualLabClassStatistics.getPrimaryKey(), virtualLabClassStatistics);

		virtualLabClassStatistics.resetOriginalValues();
	}

	/**
	 * Caches the virtual lab class statisticses in the entity cache if it is enabled.
	 *
	 * @param virtualLabClassStatisticses the virtual lab class statisticses
	 */
	@Override
	public void cacheResult(
		List<VirtualLabClassStatistics> virtualLabClassStatisticses) {
		for (VirtualLabClassStatistics virtualLabClassStatistics : virtualLabClassStatisticses) {
			if (EntityCacheUtil.getResult(
						VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
						VirtualLabClassStatisticsImpl.class,
						virtualLabClassStatistics.getPrimaryKey()) == null) {
				cacheResult(virtualLabClassStatistics);
			}
			else {
				virtualLabClassStatistics.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all virtual lab class statisticses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(VirtualLabClassStatisticsImpl.class.getName());
		}

		EntityCacheUtil.clearCache(VirtualLabClassStatisticsImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the virtual lab class statistics.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VirtualLabClassStatistics virtualLabClassStatistics) {
		EntityCacheUtil.removeResult(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabClassStatisticsImpl.class,
			virtualLabClassStatistics.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<VirtualLabClassStatistics> virtualLabClassStatisticses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VirtualLabClassStatistics virtualLabClassStatistics : virtualLabClassStatisticses) {
			EntityCacheUtil.removeResult(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
				VirtualLabClassStatisticsImpl.class,
				virtualLabClassStatistics.getPrimaryKey());
		}
	}

	/**
	 * Creates a new virtual lab class statistics with the primary key. Does not add the virtual lab class statistics to the database.
	 *
	 * @param virtualLabClassStatisticsPK the primary key for the new virtual lab class statistics
	 * @return the new virtual lab class statistics
	 */
	@Override
	public VirtualLabClassStatistics create(
		VirtualLabClassStatisticsPK virtualLabClassStatisticsPK) {
		VirtualLabClassStatistics virtualLabClassStatistics = new VirtualLabClassStatisticsImpl();

		virtualLabClassStatistics.setNew(true);
		virtualLabClassStatistics.setPrimaryKey(virtualLabClassStatisticsPK);

		return virtualLabClassStatistics;
	}

	/**
	 * Removes the virtual lab class statistics with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param virtualLabClassStatisticsPK the primary key of the virtual lab class statistics
	 * @return the virtual lab class statistics that was removed
	 * @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a virtual lab class statistics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics remove(
		VirtualLabClassStatisticsPK virtualLabClassStatisticsPK)
		throws NoSuchVirtualLabClassStatisticsException, SystemException {
		return remove((Serializable)virtualLabClassStatisticsPK);
	}

	/**
	 * Removes the virtual lab class statistics with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the virtual lab class statistics
	 * @return the virtual lab class statistics that was removed
	 * @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a virtual lab class statistics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics remove(Serializable primaryKey)
		throws NoSuchVirtualLabClassStatisticsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			VirtualLabClassStatistics virtualLabClassStatistics = (VirtualLabClassStatistics)session.get(VirtualLabClassStatisticsImpl.class,
					primaryKey);

			if (virtualLabClassStatistics == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVirtualLabClassStatisticsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(virtualLabClassStatistics);
		}
		catch (NoSuchVirtualLabClassStatisticsException nsee) {
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
	protected VirtualLabClassStatistics removeImpl(
		VirtualLabClassStatistics virtualLabClassStatistics)
		throws SystemException {
		virtualLabClassStatistics = toUnwrappedModel(virtualLabClassStatistics);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(virtualLabClassStatistics)) {
				virtualLabClassStatistics = (VirtualLabClassStatistics)session.get(VirtualLabClassStatisticsImpl.class,
						virtualLabClassStatistics.getPrimaryKeyObj());
			}

			if (virtualLabClassStatistics != null) {
				session.delete(virtualLabClassStatistics);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (virtualLabClassStatistics != null) {
			clearCache(virtualLabClassStatistics);
		}

		return virtualLabClassStatistics;
	}

	@Override
	public VirtualLabClassStatistics updateImpl(
		org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics virtualLabClassStatistics)
		throws SystemException {
		virtualLabClassStatistics = toUnwrappedModel(virtualLabClassStatistics);

		boolean isNew = virtualLabClassStatistics.isNew();

		VirtualLabClassStatisticsModelImpl virtualLabClassStatisticsModelImpl = (VirtualLabClassStatisticsModelImpl)virtualLabClassStatistics;

		Session session = null;

		try {
			session = openSession();

			if (virtualLabClassStatistics.isNew()) {
				session.save(virtualLabClassStatistics);

				virtualLabClassStatistics.setNew(false);
			}
			else {
				session.merge(virtualLabClassStatistics);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew ||
				!VirtualLabClassStatisticsModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((virtualLabClassStatisticsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIRTUALLABIDANDCLASSID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						virtualLabClassStatisticsModelImpl.getOriginalVirtualLabId(),
						virtualLabClassStatisticsModelImpl.getOriginalClassId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_VIRTUALLABIDANDCLASSID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIRTUALLABIDANDCLASSID,
					args);

				args = new Object[] {
						virtualLabClassStatisticsModelImpl.getVirtualLabId(),
						virtualLabClassStatisticsModelImpl.getClassId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_VIRTUALLABIDANDCLASSID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIRTUALLABIDANDCLASSID,
					args);
			}
		}

		EntityCacheUtil.putResult(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabClassStatisticsImpl.class,
			virtualLabClassStatistics.getPrimaryKey(), virtualLabClassStatistics);

		return virtualLabClassStatistics;
	}

	protected VirtualLabClassStatistics toUnwrappedModel(
		VirtualLabClassStatistics virtualLabClassStatistics) {
		if (virtualLabClassStatistics instanceof VirtualLabClassStatisticsImpl) {
			return virtualLabClassStatistics;
		}

		VirtualLabClassStatisticsImpl virtualLabClassStatisticsImpl = new VirtualLabClassStatisticsImpl();

		virtualLabClassStatisticsImpl.setNew(virtualLabClassStatistics.isNew());
		virtualLabClassStatisticsImpl.setPrimaryKey(virtualLabClassStatistics.getPrimaryKey());

		virtualLabClassStatisticsImpl.setVirtualLabId(virtualLabClassStatistics.getVirtualLabId());
		virtualLabClassStatisticsImpl.setClassId(virtualLabClassStatistics.getClassId());
		virtualLabClassStatisticsImpl.setVirtualLabTitle(virtualLabClassStatistics.getVirtualLabTitle());
		virtualLabClassStatisticsImpl.setClassTitle(virtualLabClassStatistics.getClassTitle());
		virtualLabClassStatisticsImpl.setVirtualLabPersonName(virtualLabClassStatistics.getVirtualLabPersonName());
		virtualLabClassStatisticsImpl.setRegisterStudentCnt(virtualLabClassStatistics.getRegisterStudentCnt());
		virtualLabClassStatisticsImpl.setVirtualLabUsersId(virtualLabClassStatistics.getVirtualLabUsersId());
		virtualLabClassStatisticsImpl.setScienceAppId(virtualLabClassStatistics.getScienceAppId());
		virtualLabClassStatisticsImpl.setExecuteUserCnt(virtualLabClassStatistics.getExecuteUserCnt());
		virtualLabClassStatisticsImpl.setExecuteCnt(virtualLabClassStatistics.getExecuteCnt());
		virtualLabClassStatisticsImpl.setCputime(virtualLabClassStatistics.getCputime());
		virtualLabClassStatisticsImpl.setGroupId(virtualLabClassStatistics.getGroupId());
		virtualLabClassStatisticsImpl.setUniversity(virtualLabClassStatistics.getUniversity());
		virtualLabClassStatisticsImpl.setClassCreateDt(virtualLabClassStatistics.getClassCreateDt());
		virtualLabClassStatisticsImpl.setVirtualLabUseYn(virtualLabClassStatistics.getVirtualLabUseYn());
		virtualLabClassStatisticsImpl.setClassUseYn(virtualLabClassStatistics.getClassUseYn());
		virtualLabClassStatisticsImpl.setLastModifiedDt(virtualLabClassStatistics.getLastModifiedDt());

		return virtualLabClassStatisticsImpl;
	}

	/**
	 * Returns the virtual lab class statistics with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the virtual lab class statistics
	 * @return the virtual lab class statistics
	 * @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a virtual lab class statistics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVirtualLabClassStatisticsException, SystemException {
		VirtualLabClassStatistics virtualLabClassStatistics = fetchByPrimaryKey(primaryKey);

		if (virtualLabClassStatistics == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVirtualLabClassStatisticsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return virtualLabClassStatistics;
	}

	/**
	 * Returns the virtual lab class statistics with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException} if it could not be found.
	 *
	 * @param virtualLabClassStatisticsPK the primary key of the virtual lab class statistics
	 * @return the virtual lab class statistics
	 * @throws org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException if a virtual lab class statistics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics findByPrimaryKey(
		VirtualLabClassStatisticsPK virtualLabClassStatisticsPK)
		throws NoSuchVirtualLabClassStatisticsException, SystemException {
		return findByPrimaryKey((Serializable)virtualLabClassStatisticsPK);
	}

	/**
	 * Returns the virtual lab class statistics with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the virtual lab class statistics
	 * @return the virtual lab class statistics, or <code>null</code> if a virtual lab class statistics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		VirtualLabClassStatistics virtualLabClassStatistics = (VirtualLabClassStatistics)EntityCacheUtil.getResult(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
				VirtualLabClassStatisticsImpl.class, primaryKey);

		if (virtualLabClassStatistics == _nullVirtualLabClassStatistics) {
			return null;
		}

		if (virtualLabClassStatistics == null) {
			Session session = null;

			try {
				session = openSession();

				virtualLabClassStatistics = (VirtualLabClassStatistics)session.get(VirtualLabClassStatisticsImpl.class,
						primaryKey);

				if (virtualLabClassStatistics != null) {
					cacheResult(virtualLabClassStatistics);
				}
				else {
					EntityCacheUtil.putResult(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
						VirtualLabClassStatisticsImpl.class, primaryKey,
						_nullVirtualLabClassStatistics);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(VirtualLabClassStatisticsModelImpl.ENTITY_CACHE_ENABLED,
					VirtualLabClassStatisticsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return virtualLabClassStatistics;
	}

	/**
	 * Returns the virtual lab class statistics with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param virtualLabClassStatisticsPK the primary key of the virtual lab class statistics
	 * @return the virtual lab class statistics, or <code>null</code> if a virtual lab class statistics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabClassStatistics fetchByPrimaryKey(
		VirtualLabClassStatisticsPK virtualLabClassStatisticsPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)virtualLabClassStatisticsPK);
	}

	/**
	 * Returns all the virtual lab class statisticses.
	 *
	 * @return the virtual lab class statisticses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabClassStatistics> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual lab class statisticses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of virtual lab class statisticses
	 * @param end the upper bound of the range of virtual lab class statisticses (not inclusive)
	 * @return the range of virtual lab class statisticses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabClassStatistics> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the virtual lab class statisticses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of virtual lab class statisticses
	 * @param end the upper bound of the range of virtual lab class statisticses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of virtual lab class statisticses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabClassStatistics> findAll(int start, int end,
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

		List<VirtualLabClassStatistics> list = (List<VirtualLabClassStatistics>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VIRTUALLABCLASSSTATISTICS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VIRTUALLABCLASSSTATISTICS;

				if (pagination) {
					sql = sql.concat(VirtualLabClassStatisticsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VirtualLabClassStatistics>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<VirtualLabClassStatistics>(list);
				}
				else {
					list = (List<VirtualLabClassStatistics>)QueryUtil.list(q,
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
	 * Removes all the virtual lab class statisticses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (VirtualLabClassStatistics virtualLabClassStatistics : findAll()) {
			remove(virtualLabClassStatistics);
		}
	}

	/**
	 * Returns the number of virtual lab class statisticses.
	 *
	 * @return the number of virtual lab class statisticses
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

				Query q = session.createQuery(_SQL_COUNT_VIRTUALLABCLASSSTATISTICS);

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
	 * Initializes the virtual lab class statistics persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<VirtualLabClassStatistics>> listenersList = new ArrayList<ModelListener<VirtualLabClassStatistics>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<VirtualLabClassStatistics>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(VirtualLabClassStatisticsImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_VIRTUALLABCLASSSTATISTICS = "SELECT virtualLabClassStatistics FROM VirtualLabClassStatistics virtualLabClassStatistics";
	private static final String _SQL_SELECT_VIRTUALLABCLASSSTATISTICS_WHERE = "SELECT virtualLabClassStatistics FROM VirtualLabClassStatistics virtualLabClassStatistics WHERE ";
	private static final String _SQL_COUNT_VIRTUALLABCLASSSTATISTICS = "SELECT COUNT(virtualLabClassStatistics) FROM VirtualLabClassStatistics virtualLabClassStatistics";
	private static final String _SQL_COUNT_VIRTUALLABCLASSSTATISTICS_WHERE = "SELECT COUNT(virtualLabClassStatistics) FROM VirtualLabClassStatistics virtualLabClassStatistics WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "virtualLabClassStatistics.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VirtualLabClassStatistics exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VirtualLabClassStatistics exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(VirtualLabClassStatisticsPersistenceImpl.class);
	private static VirtualLabClassStatistics _nullVirtualLabClassStatistics = new VirtualLabClassStatisticsImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<VirtualLabClassStatistics> toCacheModel() {
				return _nullVirtualLabClassStatisticsCacheModel;
			}
		};

	private static CacheModel<VirtualLabClassStatistics> _nullVirtualLabClassStatisticsCacheModel =
		new CacheModel<VirtualLabClassStatistics>() {
			@Override
			public VirtualLabClassStatistics toEntityModel() {
				return _nullVirtualLabClassStatistics;
			}
		};
}