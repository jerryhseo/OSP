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

import org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkException;
import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink;
import org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkImpl;
import org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the virtual lab science app link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see VirtualLabScienceAppLinkPersistence
 * @see VirtualLabScienceAppLinkUtil
 * @generated
 */
public class VirtualLabScienceAppLinkPersistenceImpl extends BasePersistenceImpl<VirtualLabScienceAppLink>
	implements VirtualLabScienceAppLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VirtualLabScienceAppLinkUtil} to access the virtual lab science app link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VirtualLabScienceAppLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabScienceAppLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabScienceAppLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_VIRTUALLABID =
		new FinderPath(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabScienceAppLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByVirtualLabId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIRTUALLABID =
		new FinderPath(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkModelImpl.FINDER_CACHE_ENABLED,
			VirtualLabScienceAppLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByVirtualLabId",
			new String[] { Long.class.getName() },
			VirtualLabScienceAppLinkModelImpl.VIRTUALLABID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_VIRTUALLABID = new FinderPath(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByVirtualLabId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the virtual lab science app links where virtualLabId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @return the matching virtual lab science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLink> findByVirtualLabId(long virtualLabId)
		throws SystemException {
		return findByVirtualLabId(virtualLabId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual lab science app links where virtualLabId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param start the lower bound of the range of virtual lab science app links
	 * @param end the upper bound of the range of virtual lab science app links (not inclusive)
	 * @return the range of matching virtual lab science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLink> findByVirtualLabId(
		long virtualLabId, int start, int end) throws SystemException {
		return findByVirtualLabId(virtualLabId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the virtual lab science app links where virtualLabId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param start the lower bound of the range of virtual lab science app links
	 * @param end the upper bound of the range of virtual lab science app links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching virtual lab science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLink> findByVirtualLabId(
		long virtualLabId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIRTUALLABID;
			finderArgs = new Object[] { virtualLabId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_VIRTUALLABID;
			finderArgs = new Object[] {
					virtualLabId,
					
					start, end, orderByComparator
				};
		}

		List<VirtualLabScienceAppLink> list = (List<VirtualLabScienceAppLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (VirtualLabScienceAppLink virtualLabScienceAppLink : list) {
				if ((virtualLabId != virtualLabScienceAppLink.getVirtualLabId())) {
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

			query.append(_SQL_SELECT_VIRTUALLABSCIENCEAPPLINK_WHERE);

			query.append(_FINDER_COLUMN_VIRTUALLABID_VIRTUALLABID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(VirtualLabScienceAppLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(virtualLabId);

				if (!pagination) {
					list = (List<VirtualLabScienceAppLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<VirtualLabScienceAppLink>(list);
				}
				else {
					list = (List<VirtualLabScienceAppLink>)QueryUtil.list(q,
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
	 * Returns the first virtual lab science app link in the ordered set where virtualLabId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual lab science app link
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkException if a matching virtual lab science app link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink findByVirtualLabId_First(
		long virtualLabId, OrderByComparator orderByComparator)
		throws NoSuchVirtualLabScienceAppLinkException, SystemException {
		VirtualLabScienceAppLink virtualLabScienceAppLink = fetchByVirtualLabId_First(virtualLabId,
				orderByComparator);

		if (virtualLabScienceAppLink != null) {
			return virtualLabScienceAppLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("virtualLabId=");
		msg.append(virtualLabId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVirtualLabScienceAppLinkException(msg.toString());
	}

	/**
	 * Returns the first virtual lab science app link in the ordered set where virtualLabId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual lab science app link, or <code>null</code> if a matching virtual lab science app link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink fetchByVirtualLabId_First(
		long virtualLabId, OrderByComparator orderByComparator)
		throws SystemException {
		List<VirtualLabScienceAppLink> list = findByVirtualLabId(virtualLabId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last virtual lab science app link in the ordered set where virtualLabId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual lab science app link
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkException if a matching virtual lab science app link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink findByVirtualLabId_Last(long virtualLabId,
		OrderByComparator orderByComparator)
		throws NoSuchVirtualLabScienceAppLinkException, SystemException {
		VirtualLabScienceAppLink virtualLabScienceAppLink = fetchByVirtualLabId_Last(virtualLabId,
				orderByComparator);

		if (virtualLabScienceAppLink != null) {
			return virtualLabScienceAppLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("virtualLabId=");
		msg.append(virtualLabId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVirtualLabScienceAppLinkException(msg.toString());
	}

	/**
	 * Returns the last virtual lab science app link in the ordered set where virtualLabId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual lab science app link, or <code>null</code> if a matching virtual lab science app link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink fetchByVirtualLabId_Last(
		long virtualLabId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByVirtualLabId(virtualLabId);

		if (count == 0) {
			return null;
		}

		List<VirtualLabScienceAppLink> list = findByVirtualLabId(virtualLabId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the virtual lab science app links before and after the current virtual lab science app link in the ordered set where virtualLabId = &#63;.
	 *
	 * @param scienceAppSeqNo the primary key of the current virtual lab science app link
	 * @param virtualLabId the virtual lab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next virtual lab science app link
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkException if a virtual lab science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink[] findByVirtualLabId_PrevAndNext(
		long scienceAppSeqNo, long virtualLabId,
		OrderByComparator orderByComparator)
		throws NoSuchVirtualLabScienceAppLinkException, SystemException {
		VirtualLabScienceAppLink virtualLabScienceAppLink = findByPrimaryKey(scienceAppSeqNo);

		Session session = null;

		try {
			session = openSession();

			VirtualLabScienceAppLink[] array = new VirtualLabScienceAppLinkImpl[3];

			array[0] = getByVirtualLabId_PrevAndNext(session,
					virtualLabScienceAppLink, virtualLabId, orderByComparator,
					true);

			array[1] = virtualLabScienceAppLink;

			array[2] = getByVirtualLabId_PrevAndNext(session,
					virtualLabScienceAppLink, virtualLabId, orderByComparator,
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

	protected VirtualLabScienceAppLink getByVirtualLabId_PrevAndNext(
		Session session, VirtualLabScienceAppLink virtualLabScienceAppLink,
		long virtualLabId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VIRTUALLABSCIENCEAPPLINK_WHERE);

		query.append(_FINDER_COLUMN_VIRTUALLABID_VIRTUALLABID_2);

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
			query.append(VirtualLabScienceAppLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(virtualLabId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(virtualLabScienceAppLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<VirtualLabScienceAppLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the virtual lab science app links where virtualLabId = &#63; from the database.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByVirtualLabId(long virtualLabId)
		throws SystemException {
		for (VirtualLabScienceAppLink virtualLabScienceAppLink : findByVirtualLabId(
				virtualLabId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(virtualLabScienceAppLink);
		}
	}

	/**
	 * Returns the number of virtual lab science app links where virtualLabId = &#63;.
	 *
	 * @param virtualLabId the virtual lab ID
	 * @return the number of matching virtual lab science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByVirtualLabId(long virtualLabId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_VIRTUALLABID;

		Object[] finderArgs = new Object[] { virtualLabId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VIRTUALLABSCIENCEAPPLINK_WHERE);

			query.append(_FINDER_COLUMN_VIRTUALLABID_VIRTUALLABID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(virtualLabId);

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

	private static final String _FINDER_COLUMN_VIRTUALLABID_VIRTUALLABID_2 = "virtualLabScienceAppLink.virtualLabId = ?";

	public VirtualLabScienceAppLinkPersistenceImpl() {
		setModelClass(VirtualLabScienceAppLink.class);
	}

	/**
	 * Caches the virtual lab science app link in the entity cache if it is enabled.
	 *
	 * @param virtualLabScienceAppLink the virtual lab science app link
	 */
	@Override
	public void cacheResult(VirtualLabScienceAppLink virtualLabScienceAppLink) {
		EntityCacheUtil.putResult(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkImpl.class,
			virtualLabScienceAppLink.getPrimaryKey(), virtualLabScienceAppLink);

		virtualLabScienceAppLink.resetOriginalValues();
	}

	/**
	 * Caches the virtual lab science app links in the entity cache if it is enabled.
	 *
	 * @param virtualLabScienceAppLinks the virtual lab science app links
	 */
	@Override
	public void cacheResult(
		List<VirtualLabScienceAppLink> virtualLabScienceAppLinks) {
		for (VirtualLabScienceAppLink virtualLabScienceAppLink : virtualLabScienceAppLinks) {
			if (EntityCacheUtil.getResult(
						VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
						VirtualLabScienceAppLinkImpl.class,
						virtualLabScienceAppLink.getPrimaryKey()) == null) {
				cacheResult(virtualLabScienceAppLink);
			}
			else {
				virtualLabScienceAppLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all virtual lab science app links.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(VirtualLabScienceAppLinkImpl.class.getName());
		}

		EntityCacheUtil.clearCache(VirtualLabScienceAppLinkImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the virtual lab science app link.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VirtualLabScienceAppLink virtualLabScienceAppLink) {
		EntityCacheUtil.removeResult(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkImpl.class,
			virtualLabScienceAppLink.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<VirtualLabScienceAppLink> virtualLabScienceAppLinks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VirtualLabScienceAppLink virtualLabScienceAppLink : virtualLabScienceAppLinks) {
			EntityCacheUtil.removeResult(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
				VirtualLabScienceAppLinkImpl.class,
				virtualLabScienceAppLink.getPrimaryKey());
		}
	}

	/**
	 * Creates a new virtual lab science app link with the primary key. Does not add the virtual lab science app link to the database.
	 *
	 * @param scienceAppSeqNo the primary key for the new virtual lab science app link
	 * @return the new virtual lab science app link
	 */
	@Override
	public VirtualLabScienceAppLink create(long scienceAppSeqNo) {
		VirtualLabScienceAppLink virtualLabScienceAppLink = new VirtualLabScienceAppLinkImpl();

		virtualLabScienceAppLink.setNew(true);
		virtualLabScienceAppLink.setPrimaryKey(scienceAppSeqNo);

		return virtualLabScienceAppLink;
	}

	/**
	 * Removes the virtual lab science app link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppSeqNo the primary key of the virtual lab science app link
	 * @return the virtual lab science app link that was removed
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkException if a virtual lab science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink remove(long scienceAppSeqNo)
		throws NoSuchVirtualLabScienceAppLinkException, SystemException {
		return remove((Serializable)scienceAppSeqNo);
	}

	/**
	 * Removes the virtual lab science app link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the virtual lab science app link
	 * @return the virtual lab science app link that was removed
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkException if a virtual lab science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink remove(Serializable primaryKey)
		throws NoSuchVirtualLabScienceAppLinkException, SystemException {
		Session session = null;

		try {
			session = openSession();

			VirtualLabScienceAppLink virtualLabScienceAppLink = (VirtualLabScienceAppLink)session.get(VirtualLabScienceAppLinkImpl.class,
					primaryKey);

			if (virtualLabScienceAppLink == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVirtualLabScienceAppLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(virtualLabScienceAppLink);
		}
		catch (NoSuchVirtualLabScienceAppLinkException nsee) {
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
	protected VirtualLabScienceAppLink removeImpl(
		VirtualLabScienceAppLink virtualLabScienceAppLink)
		throws SystemException {
		virtualLabScienceAppLink = toUnwrappedModel(virtualLabScienceAppLink);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(virtualLabScienceAppLink)) {
				virtualLabScienceAppLink = (VirtualLabScienceAppLink)session.get(VirtualLabScienceAppLinkImpl.class,
						virtualLabScienceAppLink.getPrimaryKeyObj());
			}

			if (virtualLabScienceAppLink != null) {
				session.delete(virtualLabScienceAppLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (virtualLabScienceAppLink != null) {
			clearCache(virtualLabScienceAppLink);
		}

		return virtualLabScienceAppLink;
	}

	@Override
	public VirtualLabScienceAppLink updateImpl(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink virtualLabScienceAppLink)
		throws SystemException {
		virtualLabScienceAppLink = toUnwrappedModel(virtualLabScienceAppLink);

		boolean isNew = virtualLabScienceAppLink.isNew();

		VirtualLabScienceAppLinkModelImpl virtualLabScienceAppLinkModelImpl = (VirtualLabScienceAppLinkModelImpl)virtualLabScienceAppLink;

		Session session = null;

		try {
			session = openSession();

			if (virtualLabScienceAppLink.isNew()) {
				session.save(virtualLabScienceAppLink);

				virtualLabScienceAppLink.setNew(false);
			}
			else {
				session.merge(virtualLabScienceAppLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !VirtualLabScienceAppLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((virtualLabScienceAppLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIRTUALLABID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						virtualLabScienceAppLinkModelImpl.getOriginalVirtualLabId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_VIRTUALLABID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIRTUALLABID,
					args);

				args = new Object[] {
						virtualLabScienceAppLinkModelImpl.getVirtualLabId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_VIRTUALLABID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIRTUALLABID,
					args);
			}
		}

		EntityCacheUtil.putResult(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
			VirtualLabScienceAppLinkImpl.class,
			virtualLabScienceAppLink.getPrimaryKey(), virtualLabScienceAppLink);

		return virtualLabScienceAppLink;
	}

	protected VirtualLabScienceAppLink toUnwrappedModel(
		VirtualLabScienceAppLink virtualLabScienceAppLink) {
		if (virtualLabScienceAppLink instanceof VirtualLabScienceAppLinkImpl) {
			return virtualLabScienceAppLink;
		}

		VirtualLabScienceAppLinkImpl virtualLabScienceAppLinkImpl = new VirtualLabScienceAppLinkImpl();

		virtualLabScienceAppLinkImpl.setNew(virtualLabScienceAppLink.isNew());
		virtualLabScienceAppLinkImpl.setPrimaryKey(virtualLabScienceAppLink.getPrimaryKey());

		virtualLabScienceAppLinkImpl.setScienceAppSeqNo(virtualLabScienceAppLink.getScienceAppSeqNo());
		virtualLabScienceAppLinkImpl.setScienceAppId(virtualLabScienceAppLink.getScienceAppId());
		virtualLabScienceAppLinkImpl.setVirtualLabId(virtualLabScienceAppLink.getVirtualLabId());

		return virtualLabScienceAppLinkImpl;
	}

	/**
	 * Returns the virtual lab science app link with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the virtual lab science app link
	 * @return the virtual lab science app link
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkException if a virtual lab science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVirtualLabScienceAppLinkException, SystemException {
		VirtualLabScienceAppLink virtualLabScienceAppLink = fetchByPrimaryKey(primaryKey);

		if (virtualLabScienceAppLink == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVirtualLabScienceAppLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return virtualLabScienceAppLink;
	}

	/**
	 * Returns the virtual lab science app link with the primary key or throws a {@link org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkException} if it could not be found.
	 *
	 * @param scienceAppSeqNo the primary key of the virtual lab science app link
	 * @return the virtual lab science app link
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkException if a virtual lab science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink findByPrimaryKey(long scienceAppSeqNo)
		throws NoSuchVirtualLabScienceAppLinkException, SystemException {
		return findByPrimaryKey((Serializable)scienceAppSeqNo);
	}

	/**
	 * Returns the virtual lab science app link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the virtual lab science app link
	 * @return the virtual lab science app link, or <code>null</code> if a virtual lab science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		VirtualLabScienceAppLink virtualLabScienceAppLink = (VirtualLabScienceAppLink)EntityCacheUtil.getResult(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
				VirtualLabScienceAppLinkImpl.class, primaryKey);

		if (virtualLabScienceAppLink == _nullVirtualLabScienceAppLink) {
			return null;
		}

		if (virtualLabScienceAppLink == null) {
			Session session = null;

			try {
				session = openSession();

				virtualLabScienceAppLink = (VirtualLabScienceAppLink)session.get(VirtualLabScienceAppLinkImpl.class,
						primaryKey);

				if (virtualLabScienceAppLink != null) {
					cacheResult(virtualLabScienceAppLink);
				}
				else {
					EntityCacheUtil.putResult(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
						VirtualLabScienceAppLinkImpl.class, primaryKey,
						_nullVirtualLabScienceAppLink);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(VirtualLabScienceAppLinkModelImpl.ENTITY_CACHE_ENABLED,
					VirtualLabScienceAppLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return virtualLabScienceAppLink;
	}

	/**
	 * Returns the virtual lab science app link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppSeqNo the primary key of the virtual lab science app link
	 * @return the virtual lab science app link, or <code>null</code> if a virtual lab science app link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VirtualLabScienceAppLink fetchByPrimaryKey(long scienceAppSeqNo)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)scienceAppSeqNo);
	}

	/**
	 * Returns all the virtual lab science app links.
	 *
	 * @return the virtual lab science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLink> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual lab science app links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of virtual lab science app links
	 * @param end the upper bound of the range of virtual lab science app links (not inclusive)
	 * @return the range of virtual lab science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLink> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the virtual lab science app links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of virtual lab science app links
	 * @param end the upper bound of the range of virtual lab science app links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of virtual lab science app links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VirtualLabScienceAppLink> findAll(int start, int end,
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

		List<VirtualLabScienceAppLink> list = (List<VirtualLabScienceAppLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VIRTUALLABSCIENCEAPPLINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VIRTUALLABSCIENCEAPPLINK;

				if (pagination) {
					sql = sql.concat(VirtualLabScienceAppLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VirtualLabScienceAppLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<VirtualLabScienceAppLink>(list);
				}
				else {
					list = (List<VirtualLabScienceAppLink>)QueryUtil.list(q,
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
	 * Removes all the virtual lab science app links from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (VirtualLabScienceAppLink virtualLabScienceAppLink : findAll()) {
			remove(virtualLabScienceAppLink);
		}
	}

	/**
	 * Returns the number of virtual lab science app links.
	 *
	 * @return the number of virtual lab science app links
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

				Query q = session.createQuery(_SQL_COUNT_VIRTUALLABSCIENCEAPPLINK);

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
	 * Initializes the virtual lab science app link persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<VirtualLabScienceAppLink>> listenersList = new ArrayList<ModelListener<VirtualLabScienceAppLink>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<VirtualLabScienceAppLink>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(VirtualLabScienceAppLinkImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_VIRTUALLABSCIENCEAPPLINK = "SELECT virtualLabScienceAppLink FROM VirtualLabScienceAppLink virtualLabScienceAppLink";
	private static final String _SQL_SELECT_VIRTUALLABSCIENCEAPPLINK_WHERE = "SELECT virtualLabScienceAppLink FROM VirtualLabScienceAppLink virtualLabScienceAppLink WHERE ";
	private static final String _SQL_COUNT_VIRTUALLABSCIENCEAPPLINK = "SELECT COUNT(virtualLabScienceAppLink) FROM VirtualLabScienceAppLink virtualLabScienceAppLink";
	private static final String _SQL_COUNT_VIRTUALLABSCIENCEAPPLINK_WHERE = "SELECT COUNT(virtualLabScienceAppLink) FROM VirtualLabScienceAppLink virtualLabScienceAppLink WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "virtualLabScienceAppLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VirtualLabScienceAppLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VirtualLabScienceAppLink exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(VirtualLabScienceAppLinkPersistenceImpl.class);
	private static VirtualLabScienceAppLink _nullVirtualLabScienceAppLink = new VirtualLabScienceAppLinkImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<VirtualLabScienceAppLink> toCacheModel() {
				return _nullVirtualLabScienceAppLinkCacheModel;
			}
		};

	private static CacheModel<VirtualLabScienceAppLink> _nullVirtualLabScienceAppLinkCacheModel =
		new CacheModel<VirtualLabScienceAppLink>() {
			@Override
			public VirtualLabScienceAppLink toEntityModel() {
				return _nullVirtualLabScienceAppLink;
			}
		};
}