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

package org.kisti.edison.virtuallaboratory.service.persistence;

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

import org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException;
import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass;
import org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassImpl;
import org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the virtual lab science app link class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see VirtualLabScienceAppLinkClassPersistence
 * @see VirtualLabScienceAppLinkClassUtil
 * @generated
 */
public class VirtualLabScienceAppLinkClassPersistenceImpl
	extends BasePersistenceImpl<VirtualLabScienceAppLinkClass>
	implements VirtualLabScienceAppLinkClassPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VirtualLabScienceAppLinkClassUtil} to access the virtual lab science app link class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VirtualLabScienceAppLinkClassImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSID = new FinderPath(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByClassId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSID =
		new FinderPath(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByClassId",
			new String[] { Long.class.getName() },
			VirtualLabScienceAppLinkClassModelImpl.CLASSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSID = new FinderPath(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByClassId", new String[] { Long.class.getName() });

	/**
	 * Returns all the virtual lab science app link classes where classId = &#63;.
	 *
	 * @param classId the class ID
	 * @return the matching virtual lab science app link classes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLinkClass> findByClassId(long classId)
		throws SystemException {
		return findByClassId(classId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual lab science app link classes where classId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classId the class ID
	 * @param start the lower bound of the range of virtual lab science app link classes
	 * @param end the upper bound of the range of virtual lab science app link classes (not inclusive)
	 * @return the range of matching virtual lab science app link classes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLinkClass> findByClassId(long classId,
		int start, int end) throws SystemException {
		return findByClassId(classId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the virtual lab science app link classes where classId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classId the class ID
	 * @param start the lower bound of the range of virtual lab science app link classes
	 * @param end the upper bound of the range of virtual lab science app link classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching virtual lab science app link classes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLinkClass> findByClassId(long classId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSID;
			finderArgs = new Object[] { classId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSID;
			finderArgs = new Object[] { classId, start, end, orderByComparator };
		}

		List<VirtualLabScienceAppLinkClass> list = (List<VirtualLabScienceAppLinkClass>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass : list) {
				if ((classId != virtualLabScienceAppLinkClass.getClassId())) {
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

			query.append(_SQL_SELECT_VIRTUALLABSCIENCEAPPLINKCLASS_WHERE);

			query.append(_FINDER_COLUMN_CLASSID_CLASSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(VirtualLabScienceAppLinkClassModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classId);

				if (!pagination) {
					list = (List<VirtualLabScienceAppLinkClass>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<VirtualLabScienceAppLinkClass>(list);
				}
				else {
					list = (List<VirtualLabScienceAppLinkClass>)QueryUtil.list(q,
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
	 * Returns the first virtual lab science app link class in the ordered set where classId = &#63;.
	 *
	 * @param classId the class ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual lab science app link class
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a matching virtual lab science app link class could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass findByClassId_First(long classId,
		OrderByComparator orderByComparator)
		throws NoSuchVirtualLabScienceAppLinkClassException, SystemException {
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass = fetchByClassId_First(classId,
				orderByComparator);

		if (virtualLabScienceAppLinkClass != null) {
			return virtualLabScienceAppLinkClass;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classId=");
		msg.append(classId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVirtualLabScienceAppLinkClassException(msg.toString());
	}

	/**
	 * Returns the first virtual lab science app link class in the ordered set where classId = &#63;.
	 *
	 * @param classId the class ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual lab science app link class, or <code>null</code> if a matching virtual lab science app link class could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass fetchByClassId_First(long classId,
		OrderByComparator orderByComparator) throws SystemException {
		List<VirtualLabScienceAppLinkClass> list = findByClassId(classId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last virtual lab science app link class in the ordered set where classId = &#63;.
	 *
	 * @param classId the class ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual lab science app link class
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a matching virtual lab science app link class could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass findByClassId_Last(long classId,
		OrderByComparator orderByComparator)
		throws NoSuchVirtualLabScienceAppLinkClassException, SystemException {
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass = fetchByClassId_Last(classId,
				orderByComparator);

		if (virtualLabScienceAppLinkClass != null) {
			return virtualLabScienceAppLinkClass;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classId=");
		msg.append(classId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVirtualLabScienceAppLinkClassException(msg.toString());
	}

	/**
	 * Returns the last virtual lab science app link class in the ordered set where classId = &#63;.
	 *
	 * @param classId the class ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual lab science app link class, or <code>null</code> if a matching virtual lab science app link class could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass fetchByClassId_Last(long classId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByClassId(classId);

		if (count == 0) {
			return null;
		}

		List<VirtualLabScienceAppLinkClass> list = findByClassId(classId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the virtual lab science app link classes before and after the current virtual lab science app link class in the ordered set where classId = &#63;.
	 *
	 * @param scienceAppClassSeqNo the primary key of the current virtual lab science app link class
	 * @param classId the class ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next virtual lab science app link class
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a virtual lab science app link class with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass[] findByClassId_PrevAndNext(
		long scienceAppClassSeqNo, long classId,
		OrderByComparator orderByComparator)
		throws NoSuchVirtualLabScienceAppLinkClassException, SystemException {
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass = findByPrimaryKey(scienceAppClassSeqNo);

		Session session = null;

		try {
			session = openSession();

			VirtualLabScienceAppLinkClass[] array = new VirtualLabScienceAppLinkClassImpl[3];

			array[0] = getByClassId_PrevAndNext(session,
					virtualLabScienceAppLinkClass, classId, orderByComparator,
					true);

			array[1] = virtualLabScienceAppLinkClass;

			array[2] = getByClassId_PrevAndNext(session,
					virtualLabScienceAppLinkClass, classId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected VirtualLabScienceAppLinkClass getByClassId_PrevAndNext(
		Session session,
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass,
		long classId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VIRTUALLABSCIENCEAPPLINKCLASS_WHERE);

		query.append(_FINDER_COLUMN_CLASSID_CLASSID_2);

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
			query.append(VirtualLabScienceAppLinkClassModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(virtualLabScienceAppLinkClass);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<VirtualLabScienceAppLinkClass> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the virtual lab science app link classes where classId = &#63; from the database.
	 *
	 * @param classId the class ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByClassId(long classId) throws SystemException {
		for (VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass : findByClassId(
				classId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(virtualLabScienceAppLinkClass);
		}
	}

	/**
	 * Returns the number of virtual lab science app link classes where classId = &#63;.
	 *
	 * @param classId the class ID
	 * @return the number of matching virtual lab science app link classes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByClassId(long classId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSID;

		Object[] finderArgs = new Object[] { classId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VIRTUALLABSCIENCEAPPLINKCLASS_WHERE);

			query.append(_FINDER_COLUMN_CLASSID_CLASSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classId);

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

	private static final String _FINDER_COLUMN_CLASSID_CLASSID_2 = "virtualLabScienceAppLinkClass.classId = ?";

	public VirtualLabScienceAppLinkClassPersistenceImpl() {
		setModelClass(VirtualLabScienceAppLinkClass.class);
	}

	/**
	 * Caches the virtual lab science app link class in the entity cache if it is enabled.
	 *
	 * @param virtualLabScienceAppLinkClass the virtual lab science app link class
	 */
	@Override
	public void cacheResult(
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass) {
		EntityCacheUtil.putResult(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassImpl.class,
			virtualLabScienceAppLinkClass.getPrimaryKey(),
			virtualLabScienceAppLinkClass);

		virtualLabScienceAppLinkClass.resetOriginalValues();
	}

	/**
	 * Caches the virtual lab science app link classes in the entity cache if it is enabled.
	 *
	 * @param virtualLabScienceAppLinkClasses the virtual lab science app link classes
	 */
	@Override
	public void cacheResult(
		List<VirtualLabScienceAppLinkClass> virtualLabScienceAppLinkClasses) {
		for (VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass : virtualLabScienceAppLinkClasses) {
			if (EntityCacheUtil.getResult(
						VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
						VirtualLabScienceAppLinkClassImpl.class,
						virtualLabScienceAppLinkClass.getPrimaryKey()) == null) {
				cacheResult(virtualLabScienceAppLinkClass);
			}
			else {
				virtualLabScienceAppLinkClass.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all virtual lab science app link classes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(VirtualLabScienceAppLinkClassImpl.class.getName());
		}

		EntityCacheUtil.clearCache(VirtualLabScienceAppLinkClassImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the virtual lab science app link class.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass) {
		EntityCacheUtil.removeResult(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassImpl.class,
			virtualLabScienceAppLinkClass.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<VirtualLabScienceAppLinkClass> virtualLabScienceAppLinkClasses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass : virtualLabScienceAppLinkClasses) {
			EntityCacheUtil.removeResult(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
				VirtualLabScienceAppLinkClassImpl.class,
				virtualLabScienceAppLinkClass.getPrimaryKey());
		}
	}

	/**
	 * Creates a new virtual lab science app link class with the primary key. Does not add the virtual lab science app link class to the database.
	 *
	 * @param scienceAppClassSeqNo the primary key for the new virtual lab science app link class
	 * @return the new virtual lab science app link class
	 */
	@Override
	public VirtualLabScienceAppLinkClass create(long scienceAppClassSeqNo) {
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass = new VirtualLabScienceAppLinkClassImpl();

		virtualLabScienceAppLinkClass.setNew(true);
		virtualLabScienceAppLinkClass.setPrimaryKey(scienceAppClassSeqNo);

		return virtualLabScienceAppLinkClass;
	}

	/**
	 * Removes the virtual lab science app link class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppClassSeqNo the primary key of the virtual lab science app link class
	 * @return the virtual lab science app link class that was removed
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a virtual lab science app link class with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass remove(long scienceAppClassSeqNo)
		throws NoSuchVirtualLabScienceAppLinkClassException, SystemException {
		return remove((Serializable)scienceAppClassSeqNo);
	}

	/**
	 * Removes the virtual lab science app link class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the virtual lab science app link class
	 * @return the virtual lab science app link class that was removed
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a virtual lab science app link class with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass remove(Serializable primaryKey)
		throws NoSuchVirtualLabScienceAppLinkClassException, SystemException {
		Session session = null;

		try {
			session = openSession();

			VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass = (VirtualLabScienceAppLinkClass)session.get(VirtualLabScienceAppLinkClassImpl.class,
					primaryKey);

			if (virtualLabScienceAppLinkClass == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVirtualLabScienceAppLinkClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(virtualLabScienceAppLinkClass);
		}
		catch (NoSuchVirtualLabScienceAppLinkClassException nsee) {
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
	protected VirtualLabScienceAppLinkClass removeImpl(
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass)
		throws SystemException {
		virtualLabScienceAppLinkClass = toUnwrappedModel(virtualLabScienceAppLinkClass);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(virtualLabScienceAppLinkClass)) {
				virtualLabScienceAppLinkClass = (VirtualLabScienceAppLinkClass)session.get(VirtualLabScienceAppLinkClassImpl.class,
						virtualLabScienceAppLinkClass.getPrimaryKeyObj());
			}

			if (virtualLabScienceAppLinkClass != null) {
				session.delete(virtualLabScienceAppLinkClass);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (virtualLabScienceAppLinkClass != null) {
			clearCache(virtualLabScienceAppLinkClass);
		}

		return virtualLabScienceAppLinkClass;
	}

	@Override
	public VirtualLabScienceAppLinkClass updateImpl(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass)
		throws SystemException {
		virtualLabScienceAppLinkClass = toUnwrappedModel(virtualLabScienceAppLinkClass);

		boolean isNew = virtualLabScienceAppLinkClass.isNew();

		VirtualLabScienceAppLinkClassModelImpl virtualLabScienceAppLinkClassModelImpl =
			(VirtualLabScienceAppLinkClassModelImpl)virtualLabScienceAppLinkClass;

		Session session = null;

		try {
			session = openSession();

			if (virtualLabScienceAppLinkClass.isNew()) {
				session.save(virtualLabScienceAppLinkClass);

				virtualLabScienceAppLinkClass.setNew(false);
			}
			else {
				session.merge(virtualLabScienceAppLinkClass);
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
				!VirtualLabScienceAppLinkClassModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((virtualLabScienceAppLinkClassModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						virtualLabScienceAppLinkClassModelImpl.getOriginalClassId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSID,
					args);

				args = new Object[] {
						virtualLabScienceAppLinkClassModelImpl.getClassId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSID,
					args);
			}
		}

		EntityCacheUtil.putResult(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkClassImpl.class,
			virtualLabScienceAppLinkClass.getPrimaryKey(),
			virtualLabScienceAppLinkClass);

		return virtualLabScienceAppLinkClass;
	}

	protected VirtualLabScienceAppLinkClass toUnwrappedModel(
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass) {
		if (virtualLabScienceAppLinkClass instanceof VirtualLabScienceAppLinkClassImpl) {
			return virtualLabScienceAppLinkClass;
		}

		VirtualLabScienceAppLinkClassImpl virtualLabScienceAppLinkClassImpl = new VirtualLabScienceAppLinkClassImpl();

		virtualLabScienceAppLinkClassImpl.setNew(virtualLabScienceAppLinkClass.isNew());
		virtualLabScienceAppLinkClassImpl.setPrimaryKey(virtualLabScienceAppLinkClass.getPrimaryKey());

		virtualLabScienceAppLinkClassImpl.setScienceAppClassSeqNo(virtualLabScienceAppLinkClass.getScienceAppClassSeqNo());
		virtualLabScienceAppLinkClassImpl.setClassId(virtualLabScienceAppLinkClass.getClassId());
		virtualLabScienceAppLinkClassImpl.setScienceAppSeqNo(virtualLabScienceAppLinkClass.getScienceAppSeqNo());

		return virtualLabScienceAppLinkClassImpl;
	}

	/**
	 * Returns the virtual lab science app link class with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the virtual lab science app link class
	 * @return the virtual lab science app link class
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a virtual lab science app link class with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass findByPrimaryKey(
		Serializable primaryKey)
		throws NoSuchVirtualLabScienceAppLinkClassException, SystemException {
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass = fetchByPrimaryKey(primaryKey);

		if (virtualLabScienceAppLinkClass == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVirtualLabScienceAppLinkClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return virtualLabScienceAppLinkClass;
	}

	/**
	 * Returns the virtual lab science app link class with the primary key or throws a {@link org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException} if it could not be found.
	 *
	 * @param scienceAppClassSeqNo the primary key of the virtual lab science app link class
	 * @return the virtual lab science app link class
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a virtual lab science app link class with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass findByPrimaryKey(
		long scienceAppClassSeqNo)
		throws NoSuchVirtualLabScienceAppLinkClassException, SystemException {
		return findByPrimaryKey((Serializable)scienceAppClassSeqNo);
	}

	/**
	 * Returns the virtual lab science app link class with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the virtual lab science app link class
	 * @return the virtual lab science app link class, or <code>null</code> if a virtual lab science app link class with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass fetchByPrimaryKey(
		Serializable primaryKey) throws SystemException {
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass = (VirtualLabScienceAppLinkClass)EntityCacheUtil.getResult(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
				VirtualLabScienceAppLinkClassImpl.class, primaryKey);

		if (virtualLabScienceAppLinkClass == _nullVirtualLabScienceAppLinkClass) {
			return null;
		}

		if (virtualLabScienceAppLinkClass == null) {
			Session session = null;

			try {
				session = openSession();

				virtualLabScienceAppLinkClass = (VirtualLabScienceAppLinkClass)session.get(VirtualLabScienceAppLinkClassImpl.class,
						primaryKey);

				if (virtualLabScienceAppLinkClass != null) {
					cacheResult(virtualLabScienceAppLinkClass);
				}
				else {
					EntityCacheUtil.putResult(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
						VirtualLabScienceAppLinkClassImpl.class, primaryKey,
						_nullVirtualLabScienceAppLinkClass);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(VirtualLabScienceAppLinkClassModelImpl.ENTITY_CACHE_ENABLED,
					VirtualLabScienceAppLinkClassImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return virtualLabScienceAppLinkClass;
	}

	/**
	 * Returns the virtual lab science app link class with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppClassSeqNo the primary key of the virtual lab science app link class
	 * @return the virtual lab science app link class, or <code>null</code> if a virtual lab science app link class with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLinkClass fetchByPrimaryKey(
		long scienceAppClassSeqNo) throws SystemException {
		return fetchByPrimaryKey((Serializable)scienceAppClassSeqNo);
	}

	/**
	 * Returns all the virtual lab science app link classes.
	 *
	 * @return the virtual lab science app link classes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLinkClass> findAll()
		throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual lab science app link classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of virtual lab science app link classes
	 * @param end the upper bound of the range of virtual lab science app link classes (not inclusive)
	 * @return the range of virtual lab science app link classes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLinkClass> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the virtual lab science app link classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of virtual lab science app link classes
	 * @param end the upper bound of the range of virtual lab science app link classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of virtual lab science app link classes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLinkClass> findAll(int start, int end,
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

		List<VirtualLabScienceAppLinkClass> list = (List<VirtualLabScienceAppLinkClass>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VIRTUALLABSCIENCEAPPLINKCLASS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VIRTUALLABSCIENCEAPPLINKCLASS;

				if (pagination) {
					sql = sql.concat(VirtualLabScienceAppLinkClassModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VirtualLabScienceAppLinkClass>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<VirtualLabScienceAppLinkClass>(list);
				}
				else {
					list = (List<VirtualLabScienceAppLinkClass>)QueryUtil.list(q,
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
	 * Removes all the virtual lab science app link classes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass : findAll()) {
			remove(virtualLabScienceAppLinkClass);
		}
	}

	/**
	 * Returns the number of virtual lab science app link classes.
	 *
	 * @return the number of virtual lab science app link classes
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

				Query q = session.createQuery(_SQL_COUNT_VIRTUALLABSCIENCEAPPLINKCLASS);

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
	 * Initializes the virtual lab science app link class persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<VirtualLabScienceAppLinkClass>> listenersList =
					new ArrayList<ModelListener<VirtualLabScienceAppLinkClass>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<VirtualLabScienceAppLinkClass>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(VirtualLabScienceAppLinkClassImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_VIRTUALLABSCIENCEAPPLINKCLASS = "SELECT virtualLabScienceAppLinkClass FROM VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass";
	private static final String _SQL_SELECT_VIRTUALLABSCIENCEAPPLINKCLASS_WHERE = "SELECT virtualLabScienceAppLinkClass FROM VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass WHERE ";
	private static final String _SQL_COUNT_VIRTUALLABSCIENCEAPPLINKCLASS = "SELECT COUNT(virtualLabScienceAppLinkClass) FROM VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass";
	private static final String _SQL_COUNT_VIRTUALLABSCIENCEAPPLINKCLASS_WHERE = "SELECT COUNT(virtualLabScienceAppLinkClass) FROM VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "virtualLabScienceAppLinkClass.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VirtualLabScienceAppLinkClass exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VirtualLabScienceAppLinkClass exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(VirtualLabScienceAppLinkClassPersistenceImpl.class);
	private static VirtualLabScienceAppLinkClass _nullVirtualLabScienceAppLinkClass =
		new VirtualLabScienceAppLinkClassImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<VirtualLabScienceAppLinkClass> toCacheModel() {
				return _nullVirtualLabScienceAppLinkClassCacheModel;
			}
		};

	private static CacheModel<VirtualLabScienceAppLinkClass> _nullVirtualLabScienceAppLinkClassCacheModel =
		new CacheModel<VirtualLabScienceAppLinkClass>() {
			@Override
			public VirtualLabScienceAppLinkClass toEntityModel() {
				return _nullVirtualLabScienceAppLinkClass;
			}
		};
}