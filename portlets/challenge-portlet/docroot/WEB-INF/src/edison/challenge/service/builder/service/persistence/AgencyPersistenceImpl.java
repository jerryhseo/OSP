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

package edison.challenge.service.builder.service.persistence;

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

import edison.challenge.service.builder.NoSuchAgencyException;
import edison.challenge.service.builder.model.Agency;
import edison.challenge.service.builder.model.impl.AgencyImpl;
import edison.challenge.service.builder.model.impl.AgencyModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the agency service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see AgencyPersistence
 * @see AgencyUtil
 * @generated
 */
public class AgencyPersistenceImpl extends BasePersistenceImpl<Agency>
	implements AgencyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AgencyUtil} to access the agency persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AgencyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AgencyModelImpl.ENTITY_CACHE_ENABLED,
			AgencyModelImpl.FINDER_CACHE_ENABLED, AgencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AgencyModelImpl.ENTITY_CACHE_ENABLED,
			AgencyModelImpl.FINDER_CACHE_ENABLED, AgencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AgencyModelImpl.ENTITY_CACHE_ENABLED,
			AgencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CHILDCOLLECT =
		new FinderPath(AgencyModelImpl.ENTITY_CACHE_ENABLED,
			AgencyModelImpl.FINDER_CACHE_ENABLED, AgencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBychildCollect",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT =
		new FinderPath(AgencyModelImpl.ENTITY_CACHE_ENABLED,
			AgencyModelImpl.FINDER_CACHE_ENABLED, AgencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBychildCollect",
			new String[] { Long.class.getName() },
			AgencyModelImpl.CHILDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CHILDCOLLECT = new FinderPath(AgencyModelImpl.ENTITY_CACHE_ENABLED,
			AgencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBychildCollect",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the agencies where childid = &#63;.
	 *
	 * @param childid the childid
	 * @return the matching agencies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Agency> findBychildCollect(long childid)
		throws SystemException {
		return findBychildCollect(childid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the agencies where childid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param childid the childid
	 * @param start the lower bound of the range of agencies
	 * @param end the upper bound of the range of agencies (not inclusive)
	 * @return the range of matching agencies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Agency> findBychildCollect(long childid, int start, int end)
		throws SystemException {
		return findBychildCollect(childid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the agencies where childid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param childid the childid
	 * @param start the lower bound of the range of agencies
	 * @param end the upper bound of the range of agencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching agencies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Agency> findBychildCollect(long childid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT;
			finderArgs = new Object[] { childid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CHILDCOLLECT;
			finderArgs = new Object[] { childid, start, end, orderByComparator };
		}

		List<Agency> list = (List<Agency>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Agency agency : list) {
				if ((childid != agency.getChildid())) {
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

			query.append(_SQL_SELECT_AGENCY_WHERE);

			query.append(_FINDER_COLUMN_CHILDCOLLECT_CHILDID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AgencyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(childid);

				if (!pagination) {
					list = (List<Agency>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Agency>(list);
				}
				else {
					list = (List<Agency>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first agency in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agency
	 * @throws edison.challenge.service.builder.NoSuchAgencyException if a matching agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency findBychildCollect_First(long childid,
		OrderByComparator orderByComparator)
		throws NoSuchAgencyException, SystemException {
		Agency agency = fetchBychildCollect_First(childid, orderByComparator);

		if (agency != null) {
			return agency;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("childid=");
		msg.append(childid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAgencyException(msg.toString());
	}

	/**
	 * Returns the first agency in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agency, or <code>null</code> if a matching agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency fetchBychildCollect_First(long childid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Agency> list = findBychildCollect(childid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last agency in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agency
	 * @throws edison.challenge.service.builder.NoSuchAgencyException if a matching agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency findBychildCollect_Last(long childid,
		OrderByComparator orderByComparator)
		throws NoSuchAgencyException, SystemException {
		Agency agency = fetchBychildCollect_Last(childid, orderByComparator);

		if (agency != null) {
			return agency;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("childid=");
		msg.append(childid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAgencyException(msg.toString());
	}

	/**
	 * Returns the last agency in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agency, or <code>null</code> if a matching agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency fetchBychildCollect_Last(long childid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBychildCollect(childid);

		if (count == 0) {
			return null;
		}

		List<Agency> list = findBychildCollect(childid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the agencies before and after the current agency in the ordered set where childid = &#63;.
	 *
	 * @param agencyid the primary key of the current agency
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next agency
	 * @throws edison.challenge.service.builder.NoSuchAgencyException if a agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency[] findBychildCollect_PrevAndNext(long agencyid, long childid,
		OrderByComparator orderByComparator)
		throws NoSuchAgencyException, SystemException {
		Agency agency = findByPrimaryKey(agencyid);

		Session session = null;

		try {
			session = openSession();

			Agency[] array = new AgencyImpl[3];

			array[0] = getBychildCollect_PrevAndNext(session, agency, childid,
					orderByComparator, true);

			array[1] = agency;

			array[2] = getBychildCollect_PrevAndNext(session, agency, childid,
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

	protected Agency getBychildCollect_PrevAndNext(Session session,
		Agency agency, long childid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AGENCY_WHERE);

		query.append(_FINDER_COLUMN_CHILDCOLLECT_CHILDID_2);

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
			query.append(AgencyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(childid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(agency);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Agency> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the agencies where childid = &#63; from the database.
	 *
	 * @param childid the childid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBychildCollect(long childid) throws SystemException {
		for (Agency agency : findBychildCollect(childid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(agency);
		}
	}

	/**
	 * Returns the number of agencies where childid = &#63;.
	 *
	 * @param childid the childid
	 * @return the number of matching agencies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBychildCollect(long childid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CHILDCOLLECT;

		Object[] finderArgs = new Object[] { childid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AGENCY_WHERE);

			query.append(_FINDER_COLUMN_CHILDCOLLECT_CHILDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(childid);

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

	private static final String _FINDER_COLUMN_CHILDCOLLECT_CHILDID_2 = "agency.childid = ?";

	public AgencyPersistenceImpl() {
		setModelClass(Agency.class);
	}

	/**
	 * Caches the agency in the entity cache if it is enabled.
	 *
	 * @param agency the agency
	 */
	@Override
	public void cacheResult(Agency agency) {
		EntityCacheUtil.putResult(AgencyModelImpl.ENTITY_CACHE_ENABLED,
			AgencyImpl.class, agency.getPrimaryKey(), agency);

		agency.resetOriginalValues();
	}

	/**
	 * Caches the agencies in the entity cache if it is enabled.
	 *
	 * @param agencies the agencies
	 */
	@Override
	public void cacheResult(List<Agency> agencies) {
		for (Agency agency : agencies) {
			if (EntityCacheUtil.getResult(
						AgencyModelImpl.ENTITY_CACHE_ENABLED, AgencyImpl.class,
						agency.getPrimaryKey()) == null) {
				cacheResult(agency);
			}
			else {
				agency.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all agencies.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AgencyImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AgencyImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the agency.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Agency agency) {
		EntityCacheUtil.removeResult(AgencyModelImpl.ENTITY_CACHE_ENABLED,
			AgencyImpl.class, agency.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Agency> agencies) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Agency agency : agencies) {
			EntityCacheUtil.removeResult(AgencyModelImpl.ENTITY_CACHE_ENABLED,
				AgencyImpl.class, agency.getPrimaryKey());
		}
	}

	/**
	 * Creates a new agency with the primary key. Does not add the agency to the database.
	 *
	 * @param agencyid the primary key for the new agency
	 * @return the new agency
	 */
	@Override
	public Agency create(long agencyid) {
		Agency agency = new AgencyImpl();

		agency.setNew(true);
		agency.setPrimaryKey(agencyid);

		return agency;
	}

	/**
	 * Removes the agency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param agencyid the primary key of the agency
	 * @return the agency that was removed
	 * @throws edison.challenge.service.builder.NoSuchAgencyException if a agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency remove(long agencyid)
		throws NoSuchAgencyException, SystemException {
		return remove((Serializable)agencyid);
	}

	/**
	 * Removes the agency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the agency
	 * @return the agency that was removed
	 * @throws edison.challenge.service.builder.NoSuchAgencyException if a agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency remove(Serializable primaryKey)
		throws NoSuchAgencyException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Agency agency = (Agency)session.get(AgencyImpl.class, primaryKey);

			if (agency == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAgencyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(agency);
		}
		catch (NoSuchAgencyException nsee) {
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
	protected Agency removeImpl(Agency agency) throws SystemException {
		agency = toUnwrappedModel(agency);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(agency)) {
				agency = (Agency)session.get(AgencyImpl.class,
						agency.getPrimaryKeyObj());
			}

			if (agency != null) {
				session.delete(agency);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (agency != null) {
			clearCache(agency);
		}

		return agency;
	}

	@Override
	public Agency updateImpl(
		edison.challenge.service.builder.model.Agency agency)
		throws SystemException {
		agency = toUnwrappedModel(agency);

		boolean isNew = agency.isNew();

		AgencyModelImpl agencyModelImpl = (AgencyModelImpl)agency;

		Session session = null;

		try {
			session = openSession();

			if (agency.isNew()) {
				session.save(agency);

				agency.setNew(false);
			}
			else {
				session.merge(agency);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AgencyModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((agencyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						agencyModelImpl.getOriginalChildid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHILDCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT,
					args);

				args = new Object[] { agencyModelImpl.getChildid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHILDCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT,
					args);
			}
		}

		EntityCacheUtil.putResult(AgencyModelImpl.ENTITY_CACHE_ENABLED,
			AgencyImpl.class, agency.getPrimaryKey(), agency);

		return agency;
	}

	protected Agency toUnwrappedModel(Agency agency) {
		if (agency instanceof AgencyImpl) {
			return agency;
		}

		AgencyImpl agencyImpl = new AgencyImpl();

		agencyImpl.setNew(agency.isNew());
		agencyImpl.setPrimaryKey(agency.getPrimaryKey());

		agencyImpl.setAgencyid(agency.getAgencyid());
		agencyImpl.setName(agency.getName());
		agencyImpl.setLevel(agency.getLevel());
		agencyImpl.setUrl(agency.getUrl());
		agencyImpl.setChildid(agency.getChildid());

		return agencyImpl;
	}

	/**
	 * Returns the agency with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the agency
	 * @return the agency
	 * @throws edison.challenge.service.builder.NoSuchAgencyException if a agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAgencyException, SystemException {
		Agency agency = fetchByPrimaryKey(primaryKey);

		if (agency == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAgencyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return agency;
	}

	/**
	 * Returns the agency with the primary key or throws a {@link edison.challenge.service.builder.NoSuchAgencyException} if it could not be found.
	 *
	 * @param agencyid the primary key of the agency
	 * @return the agency
	 * @throws edison.challenge.service.builder.NoSuchAgencyException if a agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency findByPrimaryKey(long agencyid)
		throws NoSuchAgencyException, SystemException {
		return findByPrimaryKey((Serializable)agencyid);
	}

	/**
	 * Returns the agency with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the agency
	 * @return the agency, or <code>null</code> if a agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Agency agency = (Agency)EntityCacheUtil.getResult(AgencyModelImpl.ENTITY_CACHE_ENABLED,
				AgencyImpl.class, primaryKey);

		if (agency == _nullAgency) {
			return null;
		}

		if (agency == null) {
			Session session = null;

			try {
				session = openSession();

				agency = (Agency)session.get(AgencyImpl.class, primaryKey);

				if (agency != null) {
					cacheResult(agency);
				}
				else {
					EntityCacheUtil.putResult(AgencyModelImpl.ENTITY_CACHE_ENABLED,
						AgencyImpl.class, primaryKey, _nullAgency);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AgencyModelImpl.ENTITY_CACHE_ENABLED,
					AgencyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return agency;
	}

	/**
	 * Returns the agency with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param agencyid the primary key of the agency
	 * @return the agency, or <code>null</code> if a agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Agency fetchByPrimaryKey(long agencyid) throws SystemException {
		return fetchByPrimaryKey((Serializable)agencyid);
	}

	/**
	 * Returns all the agencies.
	 *
	 * @return the agencies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Agency> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the agencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of agencies
	 * @param end the upper bound of the range of agencies (not inclusive)
	 * @return the range of agencies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Agency> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the agencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of agencies
	 * @param end the upper bound of the range of agencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of agencies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Agency> findAll(int start, int end,
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

		List<Agency> list = (List<Agency>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_AGENCY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AGENCY;

				if (pagination) {
					sql = sql.concat(AgencyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Agency>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Agency>(list);
				}
				else {
					list = (List<Agency>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the agencies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Agency agency : findAll()) {
			remove(agency);
		}
	}

	/**
	 * Returns the number of agencies.
	 *
	 * @return the number of agencies
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

				Query q = session.createQuery(_SQL_COUNT_AGENCY);

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
	 * Initializes the agency persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.edison.challenge.service.builder.model.Agency")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Agency>> listenersList = new ArrayList<ModelListener<Agency>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Agency>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AgencyImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_AGENCY = "SELECT agency FROM Agency agency";
	private static final String _SQL_SELECT_AGENCY_WHERE = "SELECT agency FROM Agency agency WHERE ";
	private static final String _SQL_COUNT_AGENCY = "SELECT COUNT(agency) FROM Agency agency";
	private static final String _SQL_COUNT_AGENCY_WHERE = "SELECT COUNT(agency) FROM Agency agency WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "agency.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Agency exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Agency exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AgencyPersistenceImpl.class);
	private static Agency _nullAgency = new AgencyImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Agency> toCacheModel() {
				return _nullAgencyCacheModel;
			}
		};

	private static CacheModel<Agency> _nullAgencyCacheModel = new CacheModel<Agency>() {
			@Override
			public Agency toEntityModel() {
				return _nullAgency;
			}
		};
}