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

package kisti.edison.challenge.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import kisti.edison.challenge.NoSuchChildChallengeException;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.model.impl.ChildChallengeImpl;
import kisti.edison.challenge.model.impl.ChildChallengeModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the child challenge service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChildChallengePersistence
 * @see ChildChallengeUtil
 * @generated
 */
public class ChildChallengePersistenceImpl extends BasePersistenceImpl<ChildChallenge>
	implements ChildChallengePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChildChallengeUtil} to access the child challenge persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChildChallengeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ChildChallengeModelImpl.UUID_COLUMN_BITMASK |
			ChildChallengeModelImpl.CHALLENGEENDDAY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the child challenges where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByUuid(String uuid, int start, int end,
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

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChildChallenge childChallenge : list) {
				if (!Validator.equals(uuid, childChallenge.getUuid())) {
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

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

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
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Returns the first child challenge in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByUuid_First(uuid,
				orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the first child challenge in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChildChallenge> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last child challenge in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByUuid_Last(uuid, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the last child challenge in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ChildChallenge> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set where uuid = &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] findByUuid_PrevAndNext(long childChallengeId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, childChallenge, uuid,
					orderByComparator, true);

			array[1] = childChallenge;

			array[2] = getByUuid_PrevAndNext(session, childChallenge, uuid,
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

	protected ChildChallenge getByUuid_PrevAndNext(Session session,
		ChildChallenge childChallenge, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

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
			query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the child challenges where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (ChildChallenge childChallenge : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching child challenges
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

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "childChallenge.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "childChallenge.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(childChallenge.uuid IS NULL OR childChallenge.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ChildChallengeModelImpl.UUID_COLUMN_BITMASK |
			ChildChallengeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the child challenge where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChildChallengeException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByUUID_G(String uuid, long groupId)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByUUID_G(uuid, groupId);

		if (childChallenge == null) {
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

			throw new NoSuchChildChallengeException(msg.toString());
		}

		return childChallenge;
	}

	/**
	 * Returns the child challenge where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the child challenge where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ChildChallenge) {
			ChildChallenge childChallenge = (ChildChallenge)result;

			if (!Validator.equals(uuid, childChallenge.getUuid()) ||
					(groupId != childChallenge.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

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

				List<ChildChallenge> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ChildChallenge childChallenge = list.get(0);

					result = childChallenge;

					cacheResult(childChallenge);

					if ((childChallenge.getUuid() == null) ||
							!childChallenge.getUuid().equals(uuid) ||
							(childChallenge.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, childChallenge);
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
			return (ChildChallenge)result;
		}
	}

	/**
	 * Removes the child challenge where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the child challenge that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge removeByUUID_G(String uuid, long groupId)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = findByUUID_G(uuid, groupId);

		return remove(childChallenge);
	}

	/**
	 * Returns the number of child challenges where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching child challenges
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

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "childChallenge.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "childChallenge.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(childChallenge.uuid IS NULL OR childChallenge.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "childChallenge.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ChildChallengeModelImpl.UUID_COLUMN_BITMASK |
			ChildChallengeModelImpl.COMPANYID_COLUMN_BITMASK |
			ChildChallengeModelImpl.CHALLENGEENDDAY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the child challenges where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByUuid_C(String uuid, long companyId,
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

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChildChallenge childChallenge : list) {
				if (!Validator.equals(uuid, childChallenge.getUuid()) ||
						(companyId != childChallenge.getCompanyId())) {
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

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

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
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Returns the first child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the first child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChildChallenge> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the last child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ChildChallenge> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] findByUuid_C_PrevAndNext(long childChallengeId,
		String uuid, long companyId, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, childChallenge, uuid,
					companyId, orderByComparator, true);

			array[1] = childChallenge;

			array[2] = getByUuid_C_PrevAndNext(session, childChallenge, uuid,
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

	protected ChildChallenge getByUuid_C_PrevAndNext(Session session,
		ChildChallenge childChallenge, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

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
			query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the child challenges where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (ChildChallenge childChallenge : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching child challenges
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

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "childChallenge.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "childChallenge.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(childChallenge.uuid IS NULL OR childChallenge.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "childChallenge.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GOURPID = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGourpId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOURPID =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGourpId",
			new String[] { Long.class.getName() },
			ChildChallengeModelImpl.GROUPID_COLUMN_BITMASK |
			ChildChallengeModelImpl.CHALLENGEENDDAY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GOURPID = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGourpId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the child challenges where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGourpId(long groupId)
		throws SystemException {
		return findByGourpId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGourpId(long groupId, int start, int end)
		throws SystemException {
		return findByGourpId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGourpId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOURPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GOURPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChildChallenge childChallenge : list) {
				if ((groupId != childChallenge.getGroupId())) {
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

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GOURPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Returns the first child challenge in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGourpId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGourpId_First(groupId,
				orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the first child challenge in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGourpId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChildChallenge> list = findByGourpId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGourpId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGourpId_Last(groupId,
				orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGourpId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGourpId(groupId);

		if (count == 0) {
			return null;
		}

		List<ChildChallenge> list = findByGourpId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] findByGourpId_PrevAndNext(long childChallengeId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = getByGourpId_PrevAndNext(session, childChallenge,
					groupId, orderByComparator, true);

			array[1] = childChallenge;

			array[2] = getByGourpId_PrevAndNext(session, childChallenge,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChildChallenge getByGourpId_PrevAndNext(Session session,
		ChildChallenge childChallenge, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GOURPID_GROUPID_2);

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
			query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the child challenges that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGourpId(long groupId)
		throws SystemException {
		return filterFindByGourpId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGourpId(long groupId, int start,
		int end) throws SystemException {
		return filterFindByGourpId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGourpId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGourpId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GOURPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<ChildChallenge>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] filterFindByGourpId_PrevAndNext(
		long childChallengeId, long groupId, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGourpId_PrevAndNext(childChallengeId, groupId,
				orderByComparator);
		}

		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = filterGetByGourpId_PrevAndNext(session, childChallenge,
					groupId, orderByComparator, true);

			array[1] = childChallenge;

			array[2] = filterGetByGourpId_PrevAndNext(session, childChallenge,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChildChallenge filterGetByGourpId_PrevAndNext(Session session,
		ChildChallenge childChallenge, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GOURPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the child challenges where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGourpId(long groupId) throws SystemException {
		for (ChildChallenge childChallenge : findByGourpId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGourpId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GOURPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GOURPID_GROUPID_2);

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

	/**
	 * Returns the number of child challenges that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGourpId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGourpId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GOURPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GOURPID_GROUPID_2 = "childChallenge.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPANDCHALLENGESTATUS =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupAndChallengeStatus",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPANDCHALLENGESTATUS =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByGroupAndChallengeStatus",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the child challenges where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @return the matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupAndChallengeStatus(long groupId,
		String challengeStatus) throws SystemException {
		return findByGroupAndChallengeStatus(groupId, challengeStatus,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupAndChallengeStatus(long groupId,
		String challengeStatus, int start, int end) throws SystemException {
		return findByGroupAndChallengeStatus(groupId, challengeStatus, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupAndChallengeStatus(long groupId,
		String challengeStatus, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPANDCHALLENGESTATUS;
		finderArgs = new Object[] {
				groupId, challengeStatus,
				
				start, end, orderByComparator
			};

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChildChallenge childChallenge : list) {
				if ((groupId != childChallenge.getGroupId()) ||
						!StringUtil.wildcardMatches(
							childChallenge.getChallengeStatus(),
							challengeStatus, CharPool.UNDERLINE,
							CharPool.PERCENT, CharPool.BACK_SLASH, true)) {
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

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_GROUPID_2);

			boolean bindChallengeStatus = false;

			if (challengeStatus == null) {
				query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_1);
			}
			else if (challengeStatus.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_3);
			}
			else {
				bindChallengeStatus = true;

				query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindChallengeStatus) {
					qPos.add(challengeStatus);
				}

				if (!pagination) {
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Returns the first child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGroupAndChallengeStatus_First(long groupId,
		String challengeStatus, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGroupAndChallengeStatus_First(groupId,
				challengeStatus, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeStatus=");
		msg.append(challengeStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the first child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGroupAndChallengeStatus_First(long groupId,
		String challengeStatus, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChildChallenge> list = findByGroupAndChallengeStatus(groupId,
				challengeStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGroupAndChallengeStatus_Last(long groupId,
		String challengeStatus, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGroupAndChallengeStatus_Last(groupId,
				challengeStatus, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeStatus=");
		msg.append(challengeStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGroupAndChallengeStatus_Last(long groupId,
		String challengeStatus, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGroupAndChallengeStatus(groupId, challengeStatus);

		if (count == 0) {
			return null;
		}

		List<ChildChallenge> list = findByGroupAndChallengeStatus(groupId,
				challengeStatus, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] findByGroupAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, String challengeStatus,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = getByGroupAndChallengeStatus_PrevAndNext(session,
					childChallenge, groupId, challengeStatus,
					orderByComparator, true);

			array[1] = childChallenge;

			array[2] = getByGroupAndChallengeStatus_PrevAndNext(session,
					childChallenge, groupId, challengeStatus,
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

	protected ChildChallenge getByGroupAndChallengeStatus_PrevAndNext(
		Session session, ChildChallenge childChallenge, long groupId,
		String challengeStatus, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_GROUPID_2);

		boolean bindChallengeStatus = false;

		if (challengeStatus == null) {
			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_1);
		}
		else if (challengeStatus.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_3);
		}
		else {
			bindChallengeStatus = true;

			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_2);
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
			query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindChallengeStatus) {
			qPos.add(challengeStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @return the matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupAndChallengeStatus(
		long groupId, String challengeStatus) throws SystemException {
		return filterFindByGroupAndChallengeStatus(groupId, challengeStatus,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges that the user has permission to view where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupAndChallengeStatus(
		long groupId, String challengeStatus, int start, int end)
		throws SystemException {
		return filterFindByGroupAndChallengeStatus(groupId, challengeStatus,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupAndChallengeStatus(
		long groupId, String challengeStatus, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupAndChallengeStatus(groupId, challengeStatus,
				start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_GROUPID_2);

		boolean bindChallengeStatus = false;

		if (challengeStatus == null) {
			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_1);
		}
		else if (challengeStatus.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_3);
		}
		else {
			bindChallengeStatus = true;

			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindChallengeStatus) {
				qPos.add(challengeStatus);
			}

			return (List<ChildChallenge>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] filterFindByGroupAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, String challengeStatus,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupAndChallengeStatus_PrevAndNext(childChallengeId,
				groupId, challengeStatus, orderByComparator);
		}

		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = filterGetByGroupAndChallengeStatus_PrevAndNext(session,
					childChallenge, groupId, challengeStatus,
					orderByComparator, true);

			array[1] = childChallenge;

			array[2] = filterGetByGroupAndChallengeStatus_PrevAndNext(session,
					childChallenge, groupId, challengeStatus,
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

	protected ChildChallenge filterGetByGroupAndChallengeStatus_PrevAndNext(
		Session session, ChildChallenge childChallenge, long groupId,
		String challengeStatus, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_GROUPID_2);

		boolean bindChallengeStatus = false;

		if (challengeStatus == null) {
			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_1);
		}
		else if (challengeStatus.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_3);
		}
		else {
			bindChallengeStatus = true;

			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindChallengeStatus) {
			qPos.add(challengeStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the child challenges where groupId = &#63; and challengeStatus LIKE &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupAndChallengeStatus(long groupId,
		String challengeStatus) throws SystemException {
		for (ChildChallenge childChallenge : findByGroupAndChallengeStatus(
				groupId, challengeStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @return the number of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupAndChallengeStatus(long groupId,
		String challengeStatus) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPANDCHALLENGESTATUS;

		Object[] finderArgs = new Object[] { groupId, challengeStatus };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_GROUPID_2);

			boolean bindChallengeStatus = false;

			if (challengeStatus == null) {
				query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_1);
			}
			else if (challengeStatus.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_3);
			}
			else {
				bindChallengeStatus = true;

				query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindChallengeStatus) {
					qPos.add(challengeStatus);
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

	/**
	 * Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeStatus the challenge status
	 * @return the number of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupAndChallengeStatus(long groupId,
		String challengeStatus) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupAndChallengeStatus(groupId, challengeStatus);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_GROUPID_2);

		boolean bindChallengeStatus = false;

		if (challengeStatus == null) {
			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_1);
		}
		else if (challengeStatus.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_3);
		}
		else {
			bindChallengeStatus = true;

			query.append(_FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindChallengeStatus) {
				qPos.add(challengeStatus);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPANDCHALLENGESTATUS_GROUPID_2 =
		"childChallenge.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_1 =
		"childChallenge.challengeStatus LIKE NULL";
	private static final String _FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_2 =
		"childChallenge.challengeStatus LIKE ?";
	private static final String _FINDER_COLUMN_GROUPANDCHALLENGESTATUS_CHALLENGESTATUS_3 =
		"(childChallenge.challengeStatus IS NULL OR childChallenge.challengeStatus LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEANDSTATUS =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndChallengeAndStatus",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEANDSTATUS =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndChallengeAndStatus",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			ChildChallengeModelImpl.GROUPID_COLUMN_BITMASK |
			ChildChallengeModelImpl.CHALLENGEID_COLUMN_BITMASK |
			ChildChallengeModelImpl.STATUS_COLUMN_BITMASK |
			ChildChallengeModelImpl.CHALLENGEENDDAY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEANDSTATUS =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndChallengeAndStatus",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the child challenges where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @return the matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status) throws SystemException {
		return findByGroupIdAndChallengeAndStatus(groupId, challengeId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end)
		throws SystemException {
		return findByGroupIdAndChallengeAndStatus(groupId, challengeId, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEANDSTATUS;
			finderArgs = new Object[] { groupId, challengeId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEANDSTATUS;
			finderArgs = new Object[] {
					groupId, challengeId, status,
					
					start, end, orderByComparator
				};
		}

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChildChallenge childChallenge : list) {
				if ((groupId != childChallenge.getGroupId()) ||
						(challengeId != childChallenge.getChallengeId()) ||
						(status != childChallenge.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_CHALLENGEID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(challengeId);

				qPos.add(status);

				if (!pagination) {
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGroupIdAndChallengeAndStatus_First(
		long groupId, long challengeId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGroupIdAndChallengeAndStatus_First(groupId,
				challengeId, status, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeId=");
		msg.append(challengeId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGroupIdAndChallengeAndStatus_First(
		long groupId, long challengeId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChildChallenge> list = findByGroupIdAndChallengeAndStatus(groupId,
				challengeId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGroupIdAndChallengeAndStatus_Last(
		long groupId, long challengeId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGroupIdAndChallengeAndStatus_Last(groupId,
				challengeId, status, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeId=");
		msg.append(challengeId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGroupIdAndChallengeAndStatus_Last(
		long groupId, long challengeId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupIdAndChallengeAndStatus(groupId, challengeId,
				status);

		if (count == 0) {
			return null;
		}

		List<ChildChallenge> list = findByGroupIdAndChallengeAndStatus(groupId,
				challengeId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] findByGroupIdAndChallengeAndStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = getByGroupIdAndChallengeAndStatus_PrevAndNext(session,
					childChallenge, groupId, challengeId, status,
					orderByComparator, true);

			array[1] = childChallenge;

			array[2] = getByGroupIdAndChallengeAndStatus_PrevAndNext(session,
					childChallenge, groupId, challengeId, status,
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

	protected ChildChallenge getByGroupIdAndChallengeAndStatus_PrevAndNext(
		Session session, ChildChallenge childChallenge, long groupId,
		long challengeId, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_CHALLENGEID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_STATUS_2);

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
			query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @return the matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status) throws SystemException {
		return filterFindByGroupIdAndChallengeAndStatus(groupId, challengeId,
			status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end)
		throws SystemException {
		return filterFindByGroupIdAndChallengeAndStatus(groupId, challengeId,
			status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndChallengeAndStatus(
		long groupId, long challengeId, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeAndStatus(groupId, challengeId,
				status, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_CHALLENGEID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeId);

			qPos.add(status);

			return (List<ChildChallenge>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] filterFindByGroupIdAndChallengeAndStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeAndStatus_PrevAndNext(childChallengeId,
				groupId, challengeId, status, orderByComparator);
		}

		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = filterGetByGroupIdAndChallengeAndStatus_PrevAndNext(session,
					childChallenge, groupId, challengeId, status,
					orderByComparator, true);

			array[1] = childChallenge;

			array[2] = filterGetByGroupIdAndChallengeAndStatus_PrevAndNext(session,
					childChallenge, groupId, challengeId, status,
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

	protected ChildChallenge filterGetByGroupIdAndChallengeAndStatus_PrevAndNext(
		Session session, ChildChallenge childChallenge, long groupId,
		long challengeId, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_CHALLENGEID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the child challenges where groupId = &#63; and challengeId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndChallengeAndStatus(long groupId,
		long challengeId, int status) throws SystemException {
		for (ChildChallenge childChallenge : findByGroupIdAndChallengeAndStatus(
				groupId, challengeId, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @return the number of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndChallengeAndStatus(long groupId,
		long challengeId, int status) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEANDSTATUS;

		Object[] finderArgs = new Object[] { groupId, challengeId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_CHALLENGEID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(challengeId);

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

	/**
	 * Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param status the status
	 * @return the number of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndChallengeAndStatus(long groupId,
		long challengeId, int status) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndChallengeAndStatus(groupId, challengeId,
				status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_CHALLENGEID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeId);

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_GROUPID_2 =
		"childChallenge.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_CHALLENGEID_2 =
		"childChallenge.challengeId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEANDSTATUS_STATUS_2 =
		"childChallenge.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDNUMBER =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndNumber",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDNUMBER =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndNumber",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ChildChallengeModelImpl.GROUPID_COLUMN_BITMASK |
			ChildChallengeModelImpl.NUMBER_COLUMN_BITMASK |
			ChildChallengeModelImpl.CHALLENGEENDDAY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDNUMBER = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndNumber",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the child challenges where groupId = &#63; and number = &#63;.
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @return the matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndNumber(long groupId, int number)
		throws SystemException {
		return findByGroupIdAndNumber(groupId, number, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges where groupId = &#63; and number = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndNumber(long groupId,
		int number, int start, int end) throws SystemException {
		return findByGroupIdAndNumber(groupId, number, start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges where groupId = &#63; and number = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndNumber(long groupId,
		int number, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDNUMBER;
			finderArgs = new Object[] { groupId, number };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDNUMBER;
			finderArgs = new Object[] {
					groupId, number,
					
					start, end, orderByComparator
				};
		}

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChildChallenge childChallenge : list) {
				if ((groupId != childChallenge.getGroupId()) ||
						(number != childChallenge.getNumber())) {
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

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_NUMBER_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(number);

				if (!pagination) {
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Returns the first child challenge in the ordered set where groupId = &#63; and number = &#63;.
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGroupIdAndNumber_First(long groupId,
		int number, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGroupIdAndNumber_First(groupId,
				number, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", number=");
		msg.append(number);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the first child challenge in the ordered set where groupId = &#63; and number = &#63;.
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGroupIdAndNumber_First(long groupId,
		int number, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChildChallenge> list = findByGroupIdAndNumber(groupId, number, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63; and number = &#63;.
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGroupIdAndNumber_Last(long groupId, int number,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGroupIdAndNumber_Last(groupId,
				number, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", number=");
		msg.append(number);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63; and number = &#63;.
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGroupIdAndNumber_Last(long groupId,
		int number, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGroupIdAndNumber(groupId, number);

		if (count == 0) {
			return null;
		}

		List<ChildChallenge> list = findByGroupIdAndNumber(groupId, number,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63; and number = &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] findByGroupIdAndNumber_PrevAndNext(
		long childChallengeId, long groupId, int number,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = getByGroupIdAndNumber_PrevAndNext(session,
					childChallenge, groupId, number, orderByComparator, true);

			array[1] = childChallenge;

			array[2] = getByGroupIdAndNumber_PrevAndNext(session,
					childChallenge, groupId, number, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChildChallenge getByGroupIdAndNumber_PrevAndNext(
		Session session, ChildChallenge childChallenge, long groupId,
		int number, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_NUMBER_2);

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
			query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(number);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the child challenges that the user has permission to view where groupId = &#63; and number = &#63;.
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @return the matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndNumber(long groupId,
		int number) throws SystemException {
		return filterFindByGroupIdAndNumber(groupId, number, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges that the user has permission to view where groupId = &#63; and number = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndNumber(long groupId,
		int number, int start, int end) throws SystemException {
		return filterFindByGroupIdAndNumber(groupId, number, start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63; and number = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndNumber(long groupId,
		int number, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndNumber(groupId, number, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_NUMBER_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(number);

			return (List<ChildChallenge>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63; and number = &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] filterFindByGroupIdAndNumber_PrevAndNext(
		long childChallengeId, long groupId, int number,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndNumber_PrevAndNext(childChallengeId,
				groupId, number, orderByComparator);
		}

		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = filterGetByGroupIdAndNumber_PrevAndNext(session,
					childChallenge, groupId, number, orderByComparator, true);

			array[1] = childChallenge;

			array[2] = filterGetByGroupIdAndNumber_PrevAndNext(session,
					childChallenge, groupId, number, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChildChallenge filterGetByGroupIdAndNumber_PrevAndNext(
		Session session, ChildChallenge childChallenge, long groupId,
		int number, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_NUMBER_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(number);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the child challenges where groupId = &#63; and number = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndNumber(long groupId, int number)
		throws SystemException {
		for (ChildChallenge childChallenge : findByGroupIdAndNumber(groupId,
				number, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges where groupId = &#63; and number = &#63;.
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @return the number of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndNumber(long groupId, int number)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDNUMBER;

		Object[] finderArgs = new Object[] { groupId, number };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_NUMBER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(number);

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

	/**
	 * Returns the number of child challenges that the user has permission to view where groupId = &#63; and number = &#63;.
	 *
	 * @param groupId the group ID
	 * @param number the number
	 * @return the number of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndNumber(long groupId, int number)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndNumber(groupId, number);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDNUMBER_NUMBER_2_SQL);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(number);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPIDANDNUMBER_GROUPID_2 = "childChallenge.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDNUMBER_NUMBER_2 = "childChallenge.number = ?";
	private static final String _FINDER_COLUMN_GROUPIDANDNUMBER_NUMBER_2_SQL = "childChallenge.number_ = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGE =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndChallenge",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGE =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndChallenge",
			new String[] { Long.class.getName(), Long.class.getName() },
			ChildChallengeModelImpl.GROUPID_COLUMN_BITMASK |
			ChildChallengeModelImpl.CHALLENGEID_COLUMN_BITMASK |
			ChildChallengeModelImpl.CHALLENGEENDDAY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGE = new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndChallenge",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the child challenges where groupId = &#63; and challengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @return the matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndChallenge(long groupId,
		long challengeId) throws SystemException {
		return findByGroupIdAndChallenge(groupId, challengeId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges where groupId = &#63; and challengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndChallenge(long groupId,
		long challengeId, int start, int end) throws SystemException {
		return findByGroupIdAndChallenge(groupId, challengeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges where groupId = &#63; and challengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndChallenge(long groupId,
		long challengeId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGE;
			finderArgs = new Object[] { groupId, challengeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGE;
			finderArgs = new Object[] {
					groupId, challengeId,
					
					start, end, orderByComparator
				};
		}

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChildChallenge childChallenge : list) {
				if ((groupId != childChallenge.getGroupId()) ||
						(challengeId != childChallenge.getChallengeId())) {
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

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_CHALLENGEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(challengeId);

				if (!pagination) {
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGroupIdAndChallenge_First(long groupId,
		long challengeId, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGroupIdAndChallenge_First(groupId,
				challengeId, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeId=");
		msg.append(challengeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGroupIdAndChallenge_First(long groupId,
		long challengeId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChildChallenge> list = findByGroupIdAndChallenge(groupId,
				challengeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGroupIdAndChallenge_Last(long groupId,
		long challengeId, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGroupIdAndChallenge_Last(groupId,
				challengeId, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeId=");
		msg.append(challengeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGroupIdAndChallenge_Last(long groupId,
		long challengeId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGroupIdAndChallenge(groupId, challengeId);

		if (count == 0) {
			return null;
		}

		List<ChildChallenge> list = findByGroupIdAndChallenge(groupId,
				challengeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63; and challengeId = &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] findByGroupIdAndChallenge_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = getByGroupIdAndChallenge_PrevAndNext(session,
					childChallenge, groupId, challengeId, orderByComparator,
					true);

			array[1] = childChallenge;

			array[2] = getByGroupIdAndChallenge_PrevAndNext(session,
					childChallenge, groupId, challengeId, orderByComparator,
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

	protected ChildChallenge getByGroupIdAndChallenge_PrevAndNext(
		Session session, ChildChallenge childChallenge, long groupId,
		long challengeId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_CHALLENGEID_2);

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
			query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @return the matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndChallenge(long groupId,
		long challengeId) throws SystemException {
		return filterFindByGroupIdAndChallenge(groupId, challengeId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndChallenge(long groupId,
		long challengeId, int start, int end) throws SystemException {
		return filterFindByGroupIdAndChallenge(groupId, challengeId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63; and challengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndChallenge(long groupId,
		long challengeId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallenge(groupId, challengeId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_CHALLENGEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeId);

			return (List<ChildChallenge>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] filterFindByGroupIdAndChallenge_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallenge_PrevAndNext(childChallengeId,
				groupId, challengeId, orderByComparator);
		}

		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = filterGetByGroupIdAndChallenge_PrevAndNext(session,
					childChallenge, groupId, challengeId, orderByComparator,
					true);

			array[1] = childChallenge;

			array[2] = filterGetByGroupIdAndChallenge_PrevAndNext(session,
					childChallenge, groupId, challengeId, orderByComparator,
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

	protected ChildChallenge filterGetByGroupIdAndChallenge_PrevAndNext(
		Session session, ChildChallenge childChallenge, long groupId,
		long challengeId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_CHALLENGEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the child challenges where groupId = &#63; and challengeId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndChallenge(long groupId, long challengeId)
		throws SystemException {
		for (ChildChallenge childChallenge : findByGroupIdAndChallenge(
				groupId, challengeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges where groupId = &#63; and challengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @return the number of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndChallenge(long groupId, long challengeId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGE;

		Object[] finderArgs = new Object[] { groupId, challengeId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_CHALLENGEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(challengeId);

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

	/**
	 * Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @return the number of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndChallenge(long groupId, long challengeId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndChallenge(groupId, challengeId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGE_CHALLENGEID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGE_GROUPID_2 = "childChallenge.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGE_CHALLENGEID_2 =
		"childChallenge.challengeId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEANDCHALLENGESTATUS =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED,
			ChildChallengeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndChallengeAndChallengeStatus",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPIDANDCHALLENGEANDCHALLENGESTATUS =
		new FinderPath(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByGroupIdAndChallengeAndChallengeStatus",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @return the matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, String challengeStatus)
		throws SystemException {
		return findByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, String challengeStatus, int start,
		int end) throws SystemException {
		return findByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, String challengeStatus, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEANDCHALLENGESTATUS;
		finderArgs = new Object[] {
				groupId, challengeId, challengeStatus,
				
				start, end, orderByComparator
			};

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChildChallenge childChallenge : list) {
				if ((groupId != childChallenge.getGroupId()) ||
						(challengeId != childChallenge.getChallengeId()) ||
						!StringUtil.wildcardMatches(
							childChallenge.getChallengeStatus(),
							challengeStatus, CharPool.UNDERLINE,
							CharPool.PERCENT, CharPool.BACK_SLASH, true)) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGEID_2);

			boolean bindChallengeStatus = false;

			if (challengeStatus == null) {
				query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_1);
			}
			else if (challengeStatus.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_3);
			}
			else {
				bindChallengeStatus = true;

				query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(challengeId);

				if (bindChallengeStatus) {
					qPos.add(challengeStatus);
				}

				if (!pagination) {
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGroupIdAndChallengeAndChallengeStatus_First(
		long groupId, long challengeId, String challengeStatus,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGroupIdAndChallengeAndChallengeStatus_First(groupId,
				challengeId, challengeStatus, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeId=");
		msg.append(challengeId);

		msg.append(", challengeStatus=");
		msg.append(challengeStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the first child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGroupIdAndChallengeAndChallengeStatus_First(
		long groupId, long challengeId, String challengeStatus,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChildChallenge> list = findByGroupIdAndChallengeAndChallengeStatus(groupId,
				challengeId, challengeStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByGroupIdAndChallengeAndChallengeStatus_Last(
		long groupId, long challengeId, String challengeStatus,
		OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByGroupIdAndChallengeAndChallengeStatus_Last(groupId,
				challengeId, challengeStatus, orderByComparator);

		if (childChallenge != null) {
			return childChallenge;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeId=");
		msg.append(challengeId);

		msg.append(", challengeStatus=");
		msg.append(challengeStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChildChallengeException(msg.toString());
	}

	/**
	 * Returns the last child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching child challenge, or <code>null</code> if a matching child challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByGroupIdAndChallengeAndChallengeStatus_Last(
		long groupId, long challengeId, String challengeStatus,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupIdAndChallengeAndChallengeStatus(groupId,
				challengeId, challengeStatus);

		if (count == 0) {
			return null;
		}

		List<ChildChallenge> list = findByGroupIdAndChallengeAndChallengeStatus(groupId,
				challengeId, challengeStatus, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] findByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		String challengeStatus, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = getByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(session,
					childChallenge, groupId, challengeId, challengeStatus,
					orderByComparator, true);

			array[1] = childChallenge;

			array[2] = getByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(session,
					childChallenge, groupId, challengeId, challengeStatus,
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

	protected ChildChallenge getByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(
		Session session, ChildChallenge childChallenge, long groupId,
		long challengeId, String challengeStatus,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGEID_2);

		boolean bindChallengeStatus = false;

		if (challengeStatus == null) {
			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_1);
		}
		else if (challengeStatus.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_3);
		}
		else {
			bindChallengeStatus = true;

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_2);
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
			query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeId);

		if (bindChallengeStatus) {
			qPos.add(challengeStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @return the matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, String challengeStatus)
		throws SystemException {
		return filterFindByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, String challengeStatus, int start,
		int end) throws SystemException {
		return filterFindByGroupIdAndChallengeAndChallengeStatus(groupId,
			challengeId, challengeStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges that the user has permissions to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> filterFindByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, String challengeStatus, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeAndChallengeStatus(groupId,
				challengeId, challengeStatus, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGEID_2);

		boolean bindChallengeStatus = false;

		if (challengeStatus == null) {
			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_1);
		}
		else if (challengeStatus.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_3);
		}
		else {
			bindChallengeStatus = true;

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeId);

			if (bindChallengeStatus) {
				qPos.add(challengeStatus);
			}

			return (List<ChildChallenge>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the child challenges before and after the current child challenge in the ordered set of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param childChallengeId the primary key of the current child challenge
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge[] filterFindByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(
		long childChallengeId, long groupId, long challengeId,
		String challengeStatus, OrderByComparator orderByComparator)
		throws NoSuchChildChallengeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(childChallengeId,
				groupId, challengeId, challengeStatus, orderByComparator);
		}

		ChildChallenge childChallenge = findByPrimaryKey(childChallengeId);

		Session session = null;

		try {
			session = openSession();

			ChildChallenge[] array = new ChildChallengeImpl[3];

			array[0] = filterGetByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(session,
					childChallenge, groupId, challengeId, challengeStatus,
					orderByComparator, true);

			array[1] = childChallenge;

			array[2] = filterGetByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(session,
					childChallenge, groupId, challengeId, challengeStatus,
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

	protected ChildChallenge filterGetByGroupIdAndChallengeAndChallengeStatus_PrevAndNext(
		Session session, ChildChallenge childChallenge, long groupId,
		long challengeId, String challengeStatus,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGEID_2);

		boolean bindChallengeStatus = false;

		if (challengeStatus == null) {
			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_1);
		}
		else if (challengeStatus.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_3);
		}
		else {
			bindChallengeStatus = true;

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ChildChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChildChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChildChallengeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChildChallengeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeId);

		if (bindChallengeStatus) {
			qPos.add(challengeStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(childChallenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChildChallenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndChallengeAndChallengeStatus(long groupId,
		long challengeId, String challengeStatus) throws SystemException {
		for (ChildChallenge childChallenge : findByGroupIdAndChallengeAndChallengeStatus(
				groupId, challengeId, challengeStatus, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @return the number of matching child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndChallengeAndChallengeStatus(long groupId,
		long challengeId, String challengeStatus) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPIDANDCHALLENGEANDCHALLENGESTATUS;

		Object[] finderArgs = new Object[] { groupId, challengeId, challengeStatus };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CHILDCHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGEID_2);

			boolean bindChallengeStatus = false;

			if (challengeStatus == null) {
				query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_1);
			}
			else if (challengeStatus.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_3);
			}
			else {
				bindChallengeStatus = true;

				query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(challengeId);

				if (bindChallengeStatus) {
					qPos.add(challengeStatus);
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

	/**
	 * Returns the number of child challenges that the user has permission to view where groupId = &#63; and challengeId = &#63; and challengeStatus LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeId the challenge ID
	 * @param challengeStatus the challenge status
	 * @return the number of matching child challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndChallengeAndChallengeStatus(
		long groupId, long challengeId, String challengeStatus)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndChallengeAndChallengeStatus(groupId,
				challengeId, challengeStatus);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_CHILDCHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGEID_2);

		boolean bindChallengeStatus = false;

		if (challengeStatus == null) {
			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_1);
		}
		else if (challengeStatus.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_3);
		}
		else {
			bindChallengeStatus = true;

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChildChallenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeId);

			if (bindChallengeStatus) {
				qPos.add(challengeStatus);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_GROUPID_2 =
		"childChallenge.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGEID_2 =
		"childChallenge.challengeId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_1 =
		"childChallenge.challengeStatus LIKE NULL";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_2 =
		"childChallenge.challengeStatus LIKE ?";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEANDCHALLENGESTATUS_CHALLENGESTATUS_3 =
		"(childChallenge.challengeStatus IS NULL OR childChallenge.challengeStatus LIKE '')";

	public ChildChallengePersistenceImpl() {
		setModelClass(ChildChallenge.class);
	}

	/**
	 * Caches the child challenge in the entity cache if it is enabled.
	 *
	 * @param childChallenge the child challenge
	 */
	@Override
	public void cacheResult(ChildChallenge childChallenge) {
		EntityCacheUtil.putResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeImpl.class, childChallenge.getPrimaryKey(),
			childChallenge);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { childChallenge.getUuid(), childChallenge.getGroupId() },
			childChallenge);

		childChallenge.resetOriginalValues();
	}

	/**
	 * Caches the child challenges in the entity cache if it is enabled.
	 *
	 * @param childChallenges the child challenges
	 */
	@Override
	public void cacheResult(List<ChildChallenge> childChallenges) {
		for (ChildChallenge childChallenge : childChallenges) {
			if (EntityCacheUtil.getResult(
						ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
						ChildChallengeImpl.class, childChallenge.getPrimaryKey()) == null) {
				cacheResult(childChallenge);
			}
			else {
				childChallenge.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all child challenges.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChildChallengeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChildChallengeImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the child challenge.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChildChallenge childChallenge) {
		EntityCacheUtil.removeResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeImpl.class, childChallenge.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(childChallenge);
	}

	@Override
	public void clearCache(List<ChildChallenge> childChallenges) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChildChallenge childChallenge : childChallenges) {
			EntityCacheUtil.removeResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
				ChildChallengeImpl.class, childChallenge.getPrimaryKey());

			clearUniqueFindersCache(childChallenge);
		}
	}

	protected void cacheUniqueFindersCache(ChildChallenge childChallenge) {
		if (childChallenge.isNew()) {
			Object[] args = new Object[] {
					childChallenge.getUuid(), childChallenge.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				childChallenge);
		}
		else {
			ChildChallengeModelImpl childChallengeModelImpl = (ChildChallengeModelImpl)childChallenge;

			if ((childChallengeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						childChallenge.getUuid(), childChallenge.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					childChallenge);
			}
		}
	}

	protected void clearUniqueFindersCache(ChildChallenge childChallenge) {
		ChildChallengeModelImpl childChallengeModelImpl = (ChildChallengeModelImpl)childChallenge;

		Object[] args = new Object[] {
				childChallenge.getUuid(), childChallenge.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((childChallengeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					childChallengeModelImpl.getOriginalUuid(),
					childChallengeModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new child challenge with the primary key. Does not add the child challenge to the database.
	 *
	 * @param childChallengeId the primary key for the new child challenge
	 * @return the new child challenge
	 */
	@Override
	public ChildChallenge create(long childChallengeId) {
		ChildChallenge childChallenge = new ChildChallengeImpl();

		childChallenge.setNew(true);
		childChallenge.setPrimaryKey(childChallengeId);

		String uuid = PortalUUIDUtil.generate();

		childChallenge.setUuid(uuid);

		return childChallenge;
	}

	/**
	 * Removes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param childChallengeId the primary key of the child challenge
	 * @return the child challenge that was removed
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge remove(long childChallengeId)
		throws NoSuchChildChallengeException, SystemException {
		return remove((Serializable)childChallengeId);
	}

	/**
	 * Removes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the child challenge
	 * @return the child challenge that was removed
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge remove(Serializable primaryKey)
		throws NoSuchChildChallengeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ChildChallenge childChallenge = (ChildChallenge)session.get(ChildChallengeImpl.class,
					primaryKey);

			if (childChallenge == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChildChallengeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(childChallenge);
		}
		catch (NoSuchChildChallengeException nsee) {
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
	protected ChildChallenge removeImpl(ChildChallenge childChallenge)
		throws SystemException {
		childChallenge = toUnwrappedModel(childChallenge);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(childChallenge)) {
				childChallenge = (ChildChallenge)session.get(ChildChallengeImpl.class,
						childChallenge.getPrimaryKeyObj());
			}

			if (childChallenge != null) {
				session.delete(childChallenge);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (childChallenge != null) {
			clearCache(childChallenge);
		}

		return childChallenge;
	}

	@Override
	public ChildChallenge updateImpl(
		kisti.edison.challenge.model.ChildChallenge childChallenge)
		throws SystemException {
		childChallenge = toUnwrappedModel(childChallenge);

		boolean isNew = childChallenge.isNew();

		ChildChallengeModelImpl childChallengeModelImpl = (ChildChallengeModelImpl)childChallenge;

		if (Validator.isNull(childChallenge.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			childChallenge.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (childChallenge.isNew()) {
				session.save(childChallenge);

				childChallenge.setNew(false);
			}
			else {
				session.merge(childChallenge);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ChildChallengeModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((childChallengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						childChallengeModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { childChallengeModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((childChallengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						childChallengeModelImpl.getOriginalUuid(),
						childChallengeModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						childChallengeModelImpl.getUuid(),
						childChallengeModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((childChallengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOURPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						childChallengeModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GOURPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOURPID,
					args);

				args = new Object[] { childChallengeModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GOURPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOURPID,
					args);
			}

			if ((childChallengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEANDSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						childChallengeModelImpl.getOriginalGroupId(),
						childChallengeModelImpl.getOriginalChallengeId(),
						childChallengeModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEANDSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEANDSTATUS,
					args);

				args = new Object[] {
						childChallengeModelImpl.getGroupId(),
						childChallengeModelImpl.getChallengeId(),
						childChallengeModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEANDSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEANDSTATUS,
					args);
			}

			if ((childChallengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDNUMBER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						childChallengeModelImpl.getOriginalGroupId(),
						childChallengeModelImpl.getOriginalNumber()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDNUMBER,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDNUMBER,
					args);

				args = new Object[] {
						childChallengeModelImpl.getGroupId(),
						childChallengeModelImpl.getNumber()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDNUMBER,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDNUMBER,
					args);
			}

			if ((childChallengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						childChallengeModelImpl.getOriginalGroupId(),
						childChallengeModelImpl.getOriginalChallengeId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGE,
					args);

				args = new Object[] {
						childChallengeModelImpl.getGroupId(),
						childChallengeModelImpl.getChallengeId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGE,
					args);
			}
		}

		EntityCacheUtil.putResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChildChallengeImpl.class, childChallenge.getPrimaryKey(),
			childChallenge);

		clearUniqueFindersCache(childChallenge);
		cacheUniqueFindersCache(childChallenge);

		return childChallenge;
	}

	protected ChildChallenge toUnwrappedModel(ChildChallenge childChallenge) {
		if (childChallenge instanceof ChildChallengeImpl) {
			return childChallenge;
		}

		ChildChallengeImpl childChallengeImpl = new ChildChallengeImpl();

		childChallengeImpl.setNew(childChallenge.isNew());
		childChallengeImpl.setPrimaryKey(childChallenge.getPrimaryKey());

		childChallengeImpl.setUuid(childChallenge.getUuid());
		childChallengeImpl.setChildChallengeId(childChallenge.getChildChallengeId());
		childChallengeImpl.setGroupId(childChallenge.getGroupId());
		childChallengeImpl.setCompanyId(childChallenge.getCompanyId());
		childChallengeImpl.setUserId(childChallenge.getUserId());
		childChallengeImpl.setUserName(childChallenge.getUserName());
		childChallengeImpl.setCreateDate(childChallenge.getCreateDate());
		childChallengeImpl.setModifiedDate(childChallenge.getModifiedDate());
		childChallengeImpl.setStatus(childChallenge.getStatus());
		childChallengeImpl.setStatusByUserId(childChallenge.getStatusByUserId());
		childChallengeImpl.setStatusByUserName(childChallenge.getStatusByUserName());
		childChallengeImpl.setStatusDate(childChallenge.getStatusDate());
		childChallengeImpl.setNumber(childChallenge.getNumber());
		childChallengeImpl.setPresentationDay(childChallenge.getPresentationDay());
		childChallengeImpl.setPaperStartDay(childChallenge.getPaperStartDay());
		childChallengeImpl.setPaperEndDay(childChallenge.getPaperEndDay());
		childChallengeImpl.setEvaluationDay(childChallenge.getEvaluationDay());
		childChallengeImpl.setChallengeStartDay(childChallenge.getChallengeStartDay());
		childChallengeImpl.setChallengeEndDay(childChallenge.getChallengeEndDay());
		childChallengeImpl.setChallengeStatus(childChallenge.getChallengeStatus());
		childChallengeImpl.setChallengeId(childChallenge.getChallengeId());

		return childChallengeImpl;
	}

	/**
	 * Returns the child challenge with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the child challenge
	 * @return the child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChildChallengeException, SystemException {
		ChildChallenge childChallenge = fetchByPrimaryKey(primaryKey);

		if (childChallenge == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChildChallengeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return childChallenge;
	}

	/**
	 * Returns the child challenge with the primary key or throws a {@link kisti.edison.challenge.NoSuchChildChallengeException} if it could not be found.
	 *
	 * @param childChallengeId the primary key of the child challenge
	 * @return the child challenge
	 * @throws kisti.edison.challenge.NoSuchChildChallengeException if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge findByPrimaryKey(long childChallengeId)
		throws NoSuchChildChallengeException, SystemException {
		return findByPrimaryKey((Serializable)childChallengeId);
	}

	/**
	 * Returns the child challenge with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the child challenge
	 * @return the child challenge, or <code>null</code> if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ChildChallenge childChallenge = (ChildChallenge)EntityCacheUtil.getResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
				ChildChallengeImpl.class, primaryKey);

		if (childChallenge == _nullChildChallenge) {
			return null;
		}

		if (childChallenge == null) {
			Session session = null;

			try {
				session = openSession();

				childChallenge = (ChildChallenge)session.get(ChildChallengeImpl.class,
						primaryKey);

				if (childChallenge != null) {
					cacheResult(childChallenge);
				}
				else {
					EntityCacheUtil.putResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
						ChildChallengeImpl.class, primaryKey,
						_nullChildChallenge);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChildChallengeModelImpl.ENTITY_CACHE_ENABLED,
					ChildChallengeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return childChallenge;
	}

	/**
	 * Returns the child challenge with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param childChallengeId the primary key of the child challenge
	 * @return the child challenge, or <code>null</code> if a child challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChildChallenge fetchByPrimaryKey(long childChallengeId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)childChallengeId);
	}

	/**
	 * Returns all the child challenges.
	 *
	 * @return the child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the child challenges.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @return the range of child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the child challenges.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of child challenges
	 * @param end the upper bound of the range of child challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of child challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChildChallenge> findAll(int start, int end,
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

		List<ChildChallenge> list = (List<ChildChallenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHILDCHALLENGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHILDCHALLENGE;

				if (pagination) {
					sql = sql.concat(ChildChallengeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChildChallenge>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChildChallenge>(list);
				}
				else {
					list = (List<ChildChallenge>)QueryUtil.list(q,
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
	 * Removes all the child challenges from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ChildChallenge childChallenge : findAll()) {
			remove(childChallenge);
		}
	}

	/**
	 * Returns the number of child challenges.
	 *
	 * @return the number of child challenges
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

				Query q = session.createQuery(_SQL_COUNT_CHILDCHALLENGE);

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
	 * Initializes the child challenge persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.kisti.edison.challenge.model.ChildChallenge")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ChildChallenge>> listenersList = new ArrayList<ModelListener<ChildChallenge>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ChildChallenge>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ChildChallengeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHILDCHALLENGE = "SELECT childChallenge FROM ChildChallenge childChallenge";
	private static final String _SQL_SELECT_CHILDCHALLENGE_WHERE = "SELECT childChallenge FROM ChildChallenge childChallenge WHERE ";
	private static final String _SQL_COUNT_CHILDCHALLENGE = "SELECT COUNT(childChallenge) FROM ChildChallenge childChallenge";
	private static final String _SQL_COUNT_CHILDCHALLENGE_WHERE = "SELECT COUNT(childChallenge) FROM ChildChallenge childChallenge WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "childChallenge.childChallengeId";
	private static final String _FILTER_SQL_SELECT_CHILDCHALLENGE_WHERE = "SELECT DISTINCT {childChallenge.*} FROM challenge_ChildChallenge childChallenge WHERE ";
	private static final String _FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {challenge_ChildChallenge.*} FROM (SELECT DISTINCT childChallenge.childChallengeId FROM challenge_ChildChallenge childChallenge WHERE ";
	private static final String _FILTER_SQL_SELECT_CHILDCHALLENGE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN challenge_ChildChallenge ON TEMP_TABLE.childChallengeId = challenge_ChildChallenge.childChallengeId";
	private static final String _FILTER_SQL_COUNT_CHILDCHALLENGE_WHERE = "SELECT COUNT(DISTINCT childChallenge.childChallengeId) AS COUNT_VALUE FROM challenge_ChildChallenge childChallenge WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "childChallenge";
	private static final String _FILTER_ENTITY_TABLE = "challenge_ChildChallenge";
	private static final String _ORDER_BY_ENTITY_ALIAS = "childChallenge.";
	private static final String _ORDER_BY_ENTITY_TABLE = "challenge_ChildChallenge.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChildChallenge exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ChildChallenge exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChildChallengePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "number"
			});
	private static ChildChallenge _nullChildChallenge = new ChildChallengeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ChildChallenge> toCacheModel() {
				return _nullChildChallengeCacheModel;
			}
		};

	private static CacheModel<ChildChallenge> _nullChildChallengeCacheModel = new CacheModel<ChildChallenge>() {
			@Override
			public ChildChallenge toEntityModel() {
				return _nullChildChallenge;
			}
		};
}