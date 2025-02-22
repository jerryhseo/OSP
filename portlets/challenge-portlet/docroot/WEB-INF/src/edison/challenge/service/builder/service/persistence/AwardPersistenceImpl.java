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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import edison.challenge.service.builder.NoSuchAwardException;
import edison.challenge.service.builder.model.Award;
import edison.challenge.service.builder.model.impl.AwardImpl;
import edison.challenge.service.builder.model.impl.AwardModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the award service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author kyj
 * @see AwardPersistence
 * @see AwardUtil
 * @generated
 */
public class AwardPersistenceImpl extends BasePersistenceImpl<Award>
	implements AwardPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AwardUtil} to access the award persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AwardImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AwardModelImpl.ENTITY_CACHE_ENABLED,
			AwardModelImpl.FINDER_CACHE_ENABLED, AwardImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AwardModelImpl.ENTITY_CACHE_ENABLED,
			AwardModelImpl.FINDER_CACHE_ENABLED, AwardImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AwardModelImpl.ENTITY_CACHE_ENABLED,
			AwardModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CHILDCOLLECT =
		new FinderPath(AwardModelImpl.ENTITY_CACHE_ENABLED,
			AwardModelImpl.FINDER_CACHE_ENABLED, AwardImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBychildCollect",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT =
		new FinderPath(AwardModelImpl.ENTITY_CACHE_ENABLED,
			AwardModelImpl.FINDER_CACHE_ENABLED, AwardImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBychildCollect",
			new String[] { Long.class.getName() },
			AwardModelImpl.CHILDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CHILDCOLLECT = new FinderPath(AwardModelImpl.ENTITY_CACHE_ENABLED,
			AwardModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBychildCollect",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the awards where childid = &#63;.
	 *
	 * @param childid the childid
	 * @return the matching awards
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Award> findBychildCollect(long childid)
		throws SystemException {
		return findBychildCollect(childid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the awards where childid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param childid the childid
	 * @param start the lower bound of the range of awards
	 * @param end the upper bound of the range of awards (not inclusive)
	 * @return the range of matching awards
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Award> findBychildCollect(long childid, int start, int end)
		throws SystemException {
		return findBychildCollect(childid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the awards where childid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param childid the childid
	 * @param start the lower bound of the range of awards
	 * @param end the upper bound of the range of awards (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching awards
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Award> findBychildCollect(long childid, int start, int end,
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

		List<Award> list = (List<Award>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Award award : list) {
				if ((childid != award.getChildid())) {
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

			query.append(_SQL_SELECT_AWARD_WHERE);

			query.append(_FINDER_COLUMN_CHILDCOLLECT_CHILDID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AwardModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(childid);

				if (!pagination) {
					list = (List<Award>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Award>(list);
				}
				else {
					list = (List<Award>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first award in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award
	 * @throws edison.challenge.service.builder.NoSuchAwardException if a matching award could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award findBychildCollect_First(long childid,
		OrderByComparator orderByComparator)
		throws NoSuchAwardException, SystemException {
		Award award = fetchBychildCollect_First(childid, orderByComparator);

		if (award != null) {
			return award;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("childid=");
		msg.append(childid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAwardException(msg.toString());
	}

	/**
	 * Returns the first award in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award, or <code>null</code> if a matching award could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award fetchBychildCollect_First(long childid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Award> list = findBychildCollect(childid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last award in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award
	 * @throws edison.challenge.service.builder.NoSuchAwardException if a matching award could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award findBychildCollect_Last(long childid,
		OrderByComparator orderByComparator)
		throws NoSuchAwardException, SystemException {
		Award award = fetchBychildCollect_Last(childid, orderByComparator);

		if (award != null) {
			return award;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("childid=");
		msg.append(childid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAwardException(msg.toString());
	}

	/**
	 * Returns the last award in the ordered set where childid = &#63;.
	 *
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award, or <code>null</code> if a matching award could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award fetchBychildCollect_Last(long childid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBychildCollect(childid);

		if (count == 0) {
			return null;
		}

		List<Award> list = findBychildCollect(childid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the awards before and after the current award in the ordered set where childid = &#63;.
	 *
	 * @param awardid the primary key of the current award
	 * @param childid the childid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next award
	 * @throws edison.challenge.service.builder.NoSuchAwardException if a award with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award[] findBychildCollect_PrevAndNext(long awardid, long childid,
		OrderByComparator orderByComparator)
		throws NoSuchAwardException, SystemException {
		Award award = findByPrimaryKey(awardid);

		Session session = null;

		try {
			session = openSession();

			Award[] array = new AwardImpl[3];

			array[0] = getBychildCollect_PrevAndNext(session, award, childid,
					orderByComparator, true);

			array[1] = award;

			array[2] = getBychildCollect_PrevAndNext(session, award, childid,
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

	protected Award getBychildCollect_PrevAndNext(Session session, Award award,
		long childid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AWARD_WHERE);

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
			query.append(AwardModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(childid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(award);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Award> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the awards where childid = &#63; from the database.
	 *
	 * @param childid the childid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBychildCollect(long childid) throws SystemException {
		for (Award award : findBychildCollect(childid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(award);
		}
	}

	/**
	 * Returns the number of awards where childid = &#63;.
	 *
	 * @param childid the childid
	 * @return the number of matching awards
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

			query.append(_SQL_COUNT_AWARD_WHERE);

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

	private static final String _FINDER_COLUMN_CHILDCOLLECT_CHILDID_2 = "award.childid = ?";

	public AwardPersistenceImpl() {
		setModelClass(Award.class);
	}

	/**
	 * Caches the award in the entity cache if it is enabled.
	 *
	 * @param award the award
	 */
	@Override
	public void cacheResult(Award award) {
		EntityCacheUtil.putResult(AwardModelImpl.ENTITY_CACHE_ENABLED,
			AwardImpl.class, award.getPrimaryKey(), award);

		award.resetOriginalValues();
	}

	/**
	 * Caches the awards in the entity cache if it is enabled.
	 *
	 * @param awards the awards
	 */
	@Override
	public void cacheResult(List<Award> awards) {
		for (Award award : awards) {
			if (EntityCacheUtil.getResult(AwardModelImpl.ENTITY_CACHE_ENABLED,
						AwardImpl.class, award.getPrimaryKey()) == null) {
				cacheResult(award);
			}
			else {
				award.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all awards.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AwardImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AwardImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the award.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Award award) {
		EntityCacheUtil.removeResult(AwardModelImpl.ENTITY_CACHE_ENABLED,
			AwardImpl.class, award.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Award> awards) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Award award : awards) {
			EntityCacheUtil.removeResult(AwardModelImpl.ENTITY_CACHE_ENABLED,
				AwardImpl.class, award.getPrimaryKey());
		}
	}

	/**
	 * Creates a new award with the primary key. Does not add the award to the database.
	 *
	 * @param awardid the primary key for the new award
	 * @return the new award
	 */
	@Override
	public Award create(long awardid) {
		Award award = new AwardImpl();

		award.setNew(true);
		award.setPrimaryKey(awardid);

		return award;
	}

	/**
	 * Removes the award with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param awardid the primary key of the award
	 * @return the award that was removed
	 * @throws edison.challenge.service.builder.NoSuchAwardException if a award with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award remove(long awardid)
		throws NoSuchAwardException, SystemException {
		return remove((Serializable)awardid);
	}

	/**
	 * Removes the award with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the award
	 * @return the award that was removed
	 * @throws edison.challenge.service.builder.NoSuchAwardException if a award with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award remove(Serializable primaryKey)
		throws NoSuchAwardException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Award award = (Award)session.get(AwardImpl.class, primaryKey);

			if (award == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAwardException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(award);
		}
		catch (NoSuchAwardException nsee) {
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
	protected Award removeImpl(Award award) throws SystemException {
		award = toUnwrappedModel(award);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(award)) {
				award = (Award)session.get(AwardImpl.class,
						award.getPrimaryKeyObj());
			}

			if (award != null) {
				session.delete(award);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (award != null) {
			clearCache(award);
		}

		return award;
	}

	@Override
	public Award updateImpl(edison.challenge.service.builder.model.Award award)
		throws SystemException {
		award = toUnwrappedModel(award);

		boolean isNew = award.isNew();

		AwardModelImpl awardModelImpl = (AwardModelImpl)award;

		Session session = null;

		try {
			session = openSession();

			if (award.isNew()) {
				session.save(award);

				award.setNew(false);
			}
			else {
				session.merge(award);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AwardModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((awardModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { awardModelImpl.getOriginalChildid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHILDCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT,
					args);

				args = new Object[] { awardModelImpl.getChildid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHILDCOLLECT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHILDCOLLECT,
					args);
			}
		}

		EntityCacheUtil.putResult(AwardModelImpl.ENTITY_CACHE_ENABLED,
			AwardImpl.class, award.getPrimaryKey(), award);

		return award;
	}

	protected Award toUnwrappedModel(Award award) {
		if (award instanceof AwardImpl) {
			return award;
		}

		AwardImpl awardImpl = new AwardImpl();

		awardImpl.setNew(award.isNew());
		awardImpl.setPrimaryKey(award.getPrimaryKey());

		awardImpl.setAwardid(award.getAwardid());
		awardImpl.setAwardGradeName(award.getAwardGradeName());
		awardImpl.setAwardName(award.getAwardName());
		awardImpl.setPrize(award.getPrize());
		awardImpl.setNumber(award.getNumber());
		awardImpl.setChildid(award.getChildid());

		return awardImpl;
	}

	/**
	 * Returns the award with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the award
	 * @return the award
	 * @throws edison.challenge.service.builder.NoSuchAwardException if a award with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAwardException, SystemException {
		Award award = fetchByPrimaryKey(primaryKey);

		if (award == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAwardException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return award;
	}

	/**
	 * Returns the award with the primary key or throws a {@link edison.challenge.service.builder.NoSuchAwardException} if it could not be found.
	 *
	 * @param awardid the primary key of the award
	 * @return the award
	 * @throws edison.challenge.service.builder.NoSuchAwardException if a award with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award findByPrimaryKey(long awardid)
		throws NoSuchAwardException, SystemException {
		return findByPrimaryKey((Serializable)awardid);
	}

	/**
	 * Returns the award with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the award
	 * @return the award, or <code>null</code> if a award with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Award award = (Award)EntityCacheUtil.getResult(AwardModelImpl.ENTITY_CACHE_ENABLED,
				AwardImpl.class, primaryKey);

		if (award == _nullAward) {
			return null;
		}

		if (award == null) {
			Session session = null;

			try {
				session = openSession();

				award = (Award)session.get(AwardImpl.class, primaryKey);

				if (award != null) {
					cacheResult(award);
				}
				else {
					EntityCacheUtil.putResult(AwardModelImpl.ENTITY_CACHE_ENABLED,
						AwardImpl.class, primaryKey, _nullAward);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AwardModelImpl.ENTITY_CACHE_ENABLED,
					AwardImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return award;
	}

	/**
	 * Returns the award with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param awardid the primary key of the award
	 * @return the award, or <code>null</code> if a award with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Award fetchByPrimaryKey(long awardid) throws SystemException {
		return fetchByPrimaryKey((Serializable)awardid);
	}

	/**
	 * Returns all the awards.
	 *
	 * @return the awards
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Award> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the awards.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of awards
	 * @param end the upper bound of the range of awards (not inclusive)
	 * @return the range of awards
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Award> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the awards.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of awards
	 * @param end the upper bound of the range of awards (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of awards
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Award> findAll(int start, int end,
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

		List<Award> list = (List<Award>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_AWARD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AWARD;

				if (pagination) {
					sql = sql.concat(AwardModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Award>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Award>(list);
				}
				else {
					list = (List<Award>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the awards from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Award award : findAll()) {
			remove(award);
		}
	}

	/**
	 * Returns the number of awards.
	 *
	 * @return the number of awards
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

				Query q = session.createQuery(_SQL_COUNT_AWARD);

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
	 * Initializes the award persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.edison.challenge.service.builder.model.Award")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Award>> listenersList = new ArrayList<ModelListener<Award>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Award>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AwardImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_AWARD = "SELECT award FROM Award award";
	private static final String _SQL_SELECT_AWARD_WHERE = "SELECT award FROM Award award WHERE ";
	private static final String _SQL_COUNT_AWARD = "SELECT COUNT(award) FROM Award award";
	private static final String _SQL_COUNT_AWARD_WHERE = "SELECT COUNT(award) FROM Award award WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "award.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Award exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Award exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AwardPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"number"
			});
	private static Award _nullAward = new AwardImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Award> toCacheModel() {
				return _nullAwardCacheModel;
			}
		};

	private static CacheModel<Award> _nullAwardCacheModel = new CacheModel<Award>() {
			@Override
			public Award toEntityModel() {
				return _nullAward;
			}
		};
}