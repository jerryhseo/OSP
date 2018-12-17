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

import org.kisti.edison.NoSuchSiteUserException;
import org.kisti.edison.model.SiteUser;
import org.kisti.edison.model.impl.SiteUserImpl;
import org.kisti.edison.model.impl.SiteUserModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the site user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see SiteUserPersistence
 * @see SiteUserUtil
 * @generated
 */
public class SiteUserPersistenceImpl extends BasePersistenceImpl<SiteUser>
	implements SiteUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SiteUserUtil} to access the site user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SiteUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
			SiteUserModelImpl.FINDER_CACHE_ENABLED, SiteUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
			SiteUserModelImpl.FINDER_CACHE_ENABLED, SiteUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
			SiteUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
			SiteUserModelImpl.FINDER_CACHE_ENABLED, SiteUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
			SiteUserModelImpl.FINDER_CACHE_ENABLED, SiteUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygroupId",
			new String[] { Long.class.getName() },
			SiteUserModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
			SiteUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the site users where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching site users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SiteUser> findBygroupId(long groupId) throws SystemException {
		return findBygroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the site users where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of site users
	 * @param end the upper bound of the range of site users (not inclusive)
	 * @return the range of matching site users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SiteUser> findBygroupId(long groupId, int start, int end)
		throws SystemException {
		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the site users where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of site users
	 * @param end the upper bound of the range of site users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching site users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SiteUser> findBygroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<SiteUser> list = (List<SiteUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SiteUser siteUser : list) {
				if ((groupId != siteUser.getGroupId())) {
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

			query.append(_SQL_SELECT_SITEUSER_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SiteUserModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<SiteUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SiteUser>(list);
				}
				else {
					list = (List<SiteUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first site user in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching site user
	 * @throws org.kisti.edison.NoSuchSiteUserException if a matching site user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser findBygroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSiteUserException, SystemException {
		SiteUser siteUser = fetchBygroupId_First(groupId, orderByComparator);

		if (siteUser != null) {
			return siteUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteUserException(msg.toString());
	}

	/**
	 * Returns the first site user in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching site user, or <code>null</code> if a matching site user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser fetchBygroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SiteUser> list = findBygroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last site user in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching site user
	 * @throws org.kisti.edison.NoSuchSiteUserException if a matching site user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser findBygroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSiteUserException, SystemException {
		SiteUser siteUser = fetchBygroupId_Last(groupId, orderByComparator);

		if (siteUser != null) {
			return siteUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteUserException(msg.toString());
	}

	/**
	 * Returns the last site user in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching site user, or <code>null</code> if a matching site user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser fetchBygroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<SiteUser> list = findBygroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the site users before and after the current site user in the ordered set where groupId = &#63;.
	 *
	 * @param siteUserPK the primary key of the current site user
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next site user
	 * @throws org.kisti.edison.NoSuchSiteUserException if a site user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser[] findBygroupId_PrevAndNext(SiteUserPK siteUserPK,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchSiteUserException, SystemException {
		SiteUser siteUser = findByPrimaryKey(siteUserPK);

		Session session = null;

		try {
			session = openSession();

			SiteUser[] array = new SiteUserImpl[3];

			array[0] = getBygroupId_PrevAndNext(session, siteUser, groupId,
					orderByComparator, true);

			array[1] = siteUser;

			array[2] = getBygroupId_PrevAndNext(session, siteUser, groupId,
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

	protected SiteUser getBygroupId_PrevAndNext(Session session,
		SiteUser siteUser, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SITEUSER_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(SiteUserModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(siteUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SiteUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the site users where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBygroupId(long groupId) throws SystemException {
		for (SiteUser siteUser : findBygroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(siteUser);
		}
	}

	/**
	 * Returns the number of site users where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching site users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBygroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SITEUSER_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "siteUser.id.groupId = ?";

	public SiteUserPersistenceImpl() {
		setModelClass(SiteUser.class);
	}

	/**
	 * Caches the site user in the entity cache if it is enabled.
	 *
	 * @param siteUser the site user
	 */
	@Override
	public void cacheResult(SiteUser siteUser) {
		EntityCacheUtil.putResult(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
			SiteUserImpl.class, siteUser.getPrimaryKey(), siteUser);

		siteUser.resetOriginalValues();
	}

	/**
	 * Caches the site users in the entity cache if it is enabled.
	 *
	 * @param siteUsers the site users
	 */
	@Override
	public void cacheResult(List<SiteUser> siteUsers) {
		for (SiteUser siteUser : siteUsers) {
			if (EntityCacheUtil.getResult(
						SiteUserModelImpl.ENTITY_CACHE_ENABLED,
						SiteUserImpl.class, siteUser.getPrimaryKey()) == null) {
				cacheResult(siteUser);
			}
			else {
				siteUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all site users.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SiteUserImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SiteUserImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the site user.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SiteUser siteUser) {
		EntityCacheUtil.removeResult(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
			SiteUserImpl.class, siteUser.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SiteUser> siteUsers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SiteUser siteUser : siteUsers) {
			EntityCacheUtil.removeResult(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
				SiteUserImpl.class, siteUser.getPrimaryKey());
		}
	}

	/**
	 * Creates a new site user with the primary key. Does not add the site user to the database.
	 *
	 * @param siteUserPK the primary key for the new site user
	 * @return the new site user
	 */
	@Override
	public SiteUser create(SiteUserPK siteUserPK) {
		SiteUser siteUser = new SiteUserImpl();

		siteUser.setNew(true);
		siteUser.setPrimaryKey(siteUserPK);

		return siteUser;
	}

	/**
	 * Removes the site user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param siteUserPK the primary key of the site user
	 * @return the site user that was removed
	 * @throws org.kisti.edison.NoSuchSiteUserException if a site user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser remove(SiteUserPK siteUserPK)
		throws NoSuchSiteUserException, SystemException {
		return remove((Serializable)siteUserPK);
	}

	/**
	 * Removes the site user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the site user
	 * @return the site user that was removed
	 * @throws org.kisti.edison.NoSuchSiteUserException if a site user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser remove(Serializable primaryKey)
		throws NoSuchSiteUserException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SiteUser siteUser = (SiteUser)session.get(SiteUserImpl.class,
					primaryKey);

			if (siteUser == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSiteUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(siteUser);
		}
		catch (NoSuchSiteUserException nsee) {
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
	protected SiteUser removeImpl(SiteUser siteUser) throws SystemException {
		siteUser = toUnwrappedModel(siteUser);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(siteUser)) {
				siteUser = (SiteUser)session.get(SiteUserImpl.class,
						siteUser.getPrimaryKeyObj());
			}

			if (siteUser != null) {
				session.delete(siteUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (siteUser != null) {
			clearCache(siteUser);
		}

		return siteUser;
	}

	@Override
	public SiteUser updateImpl(org.kisti.edison.model.SiteUser siteUser)
		throws SystemException {
		siteUser = toUnwrappedModel(siteUser);

		boolean isNew = siteUser.isNew();

		SiteUserModelImpl siteUserModelImpl = (SiteUserModelImpl)siteUser;

		Session session = null;

		try {
			session = openSession();

			if (siteUser.isNew()) {
				session.save(siteUser);

				siteUser.setNew(false);
			}
			else {
				session.merge(siteUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SiteUserModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((siteUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						siteUserModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { siteUserModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
			SiteUserImpl.class, siteUser.getPrimaryKey(), siteUser);

		return siteUser;
	}

	protected SiteUser toUnwrappedModel(SiteUser siteUser) {
		if (siteUser instanceof SiteUserImpl) {
			return siteUser;
		}

		SiteUserImpl siteUserImpl = new SiteUserImpl();

		siteUserImpl.setNew(siteUser.isNew());
		siteUserImpl.setPrimaryKey(siteUser.getPrimaryKey());

		siteUserImpl.setCreateDate(siteUser.getCreateDate());
		siteUserImpl.setGroupId(siteUser.getGroupId());
		siteUserImpl.setCnt(siteUser.getCnt());

		return siteUserImpl;
	}

	/**
	 * Returns the site user with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the site user
	 * @return the site user
	 * @throws org.kisti.edison.NoSuchSiteUserException if a site user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSiteUserException, SystemException {
		SiteUser siteUser = fetchByPrimaryKey(primaryKey);

		if (siteUser == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSiteUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return siteUser;
	}

	/**
	 * Returns the site user with the primary key or throws a {@link org.kisti.edison.NoSuchSiteUserException} if it could not be found.
	 *
	 * @param siteUserPK the primary key of the site user
	 * @return the site user
	 * @throws org.kisti.edison.NoSuchSiteUserException if a site user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser findByPrimaryKey(SiteUserPK siteUserPK)
		throws NoSuchSiteUserException, SystemException {
		return findByPrimaryKey((Serializable)siteUserPK);
	}

	/**
	 * Returns the site user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the site user
	 * @return the site user, or <code>null</code> if a site user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SiteUser siteUser = (SiteUser)EntityCacheUtil.getResult(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
				SiteUserImpl.class, primaryKey);

		if (siteUser == _nullSiteUser) {
			return null;
		}

		if (siteUser == null) {
			Session session = null;

			try {
				session = openSession();

				siteUser = (SiteUser)session.get(SiteUserImpl.class, primaryKey);

				if (siteUser != null) {
					cacheResult(siteUser);
				}
				else {
					EntityCacheUtil.putResult(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
						SiteUserImpl.class, primaryKey, _nullSiteUser);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SiteUserModelImpl.ENTITY_CACHE_ENABLED,
					SiteUserImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return siteUser;
	}

	/**
	 * Returns the site user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param siteUserPK the primary key of the site user
	 * @return the site user, or <code>null</code> if a site user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SiteUser fetchByPrimaryKey(SiteUserPK siteUserPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)siteUserPK);
	}

	/**
	 * Returns all the site users.
	 *
	 * @return the site users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SiteUser> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the site users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of site users
	 * @param end the upper bound of the range of site users (not inclusive)
	 * @return the range of site users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SiteUser> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the site users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of site users
	 * @param end the upper bound of the range of site users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of site users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SiteUser> findAll(int start, int end,
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

		List<SiteUser> list = (List<SiteUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SITEUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SITEUSER;

				if (pagination) {
					sql = sql.concat(SiteUserModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SiteUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SiteUser>(list);
				}
				else {
					list = (List<SiteUser>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the site users from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SiteUser siteUser : findAll()) {
			remove(siteUser);
		}
	}

	/**
	 * Returns the number of site users.
	 *
	 * @return the number of site users
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

				Query q = session.createQuery(_SQL_COUNT_SITEUSER);

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
	 * Initializes the site user persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.SiteUser")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SiteUser>> listenersList = new ArrayList<ModelListener<SiteUser>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SiteUser>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SiteUserImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SITEUSER = "SELECT siteUser FROM SiteUser siteUser";
	private static final String _SQL_SELECT_SITEUSER_WHERE = "SELECT siteUser FROM SiteUser siteUser WHERE ";
	private static final String _SQL_COUNT_SITEUSER = "SELECT COUNT(siteUser) FROM SiteUser siteUser";
	private static final String _SQL_COUNT_SITEUSER_WHERE = "SELECT COUNT(siteUser) FROM SiteUser siteUser WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "siteUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SiteUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SiteUser exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SiteUserPersistenceImpl.class);
	private static SiteUser _nullSiteUser = new SiteUserImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SiteUser> toCacheModel() {
				return _nullSiteUserCacheModel;
			}
		};

	private static CacheModel<SiteUser> _nullSiteUserCacheModel = new CacheModel<SiteUser>() {
			@Override
			public SiteUser toEntityModel() {
				return _nullSiteUser;
			}
		};
}