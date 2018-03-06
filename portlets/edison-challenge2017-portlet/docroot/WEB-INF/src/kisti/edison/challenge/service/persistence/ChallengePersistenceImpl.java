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

import kisti.edison.challenge.NoSuchChallengeException;
import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.impl.ChallengeImpl;
import kisti.edison.challenge.model.impl.ChallengeModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the challenge service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengePersistence
 * @see ChallengeUtil
 * @generated
 */
public class ChallengePersistenceImpl extends BasePersistenceImpl<Challenge>
	implements ChallengePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChallengeUtil} to access the challenge persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChallengeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ChallengeModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the challenges where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenges where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @return the range of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenges where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByUuid(String uuid, int start, int end,
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

		List<Challenge> list = (List<Challenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Challenge challenge : list) {
				if (!Validator.equals(uuid, challenge.getUuid())) {
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

			query.append(_SQL_SELECT_CHALLENGE_WHERE);

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
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Challenge>(list);
				}
				else {
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first challenge in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByUuid_First(uuid, orderByComparator);

		if (challenge != null) {
			return challenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeException(msg.toString());
	}

	/**
	 * Returns the first challenge in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Challenge> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByUuid_Last(uuid, orderByComparator);

		if (challenge != null) {
			return challenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeException(msg.toString());
	}

	/**
	 * Returns the last challenge in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Challenge> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenges before and after the current challenge in the ordered set where uuid = &#63;.
	 *
	 * @param challengeId the primary key of the current challenge
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge[] findByUuid_PrevAndNext(long challengeId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = findByPrimaryKey(challengeId);

		Session session = null;

		try {
			session = openSession();

			Challenge[] array = new ChallengeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, challenge, uuid,
					orderByComparator, true);

			array[1] = challenge;

			array[2] = getByUuid_PrevAndNext(session, challenge, uuid,
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

	protected Challenge getByUuid_PrevAndNext(Session session,
		Challenge challenge, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGE_WHERE);

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
			query.append(ChallengeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Challenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenges where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (Challenge challenge : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(challenge);
		}
	}

	/**
	 * Returns the number of challenges where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching challenges
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

			query.append(_SQL_COUNT_CHALLENGE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "challenge.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "challenge.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(challenge.uuid IS NULL OR challenge.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeModelImpl.UUID_COLUMN_BITMASK |
			ChallengeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the challenge where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByUUID_G(uuid, groupId);

		if (challenge == null) {
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

			throw new NoSuchChallengeException(msg.toString());
		}

		return challenge;
	}

	/**
	 * Returns the challenge where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the challenge where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Challenge) {
			Challenge challenge = (Challenge)result;

			if (!Validator.equals(uuid, challenge.getUuid()) ||
					(groupId != challenge.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CHALLENGE_WHERE);

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

				List<Challenge> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Challenge challenge = list.get(0);

					result = challenge;

					cacheResult(challenge);

					if ((challenge.getUuid() == null) ||
							!challenge.getUuid().equals(uuid) ||
							(challenge.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, challenge);
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
			return (Challenge)result;
		}
	}

	/**
	 * Removes the challenge where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the challenge that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge removeByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = findByUUID_G(uuid, groupId);

		return remove(challenge);
	}

	/**
	 * Returns the number of challenges where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching challenges
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

			query.append(_SQL_COUNT_CHALLENGE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "challenge.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "challenge.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(challenge.uuid IS NULL OR challenge.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "challenge.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeModelImpl.UUID_COLUMN_BITMASK |
			ChallengeModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenges where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenges where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @return the range of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByUuid_C(String uuid, long companyId, int start,
		int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenges where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<Challenge> list = (List<Challenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Challenge challenge : list) {
				if (!Validator.equals(uuid, challenge.getUuid()) ||
						(companyId != challenge.getCompanyId())) {
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

			query.append(_SQL_SELECT_CHALLENGE_WHERE);

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
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Challenge>(list);
				}
				else {
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (challenge != null) {
			return challenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeException(msg.toString());
	}

	/**
	 * Returns the first challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Challenge> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (challenge != null) {
			return challenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeException(msg.toString());
	}

	/**
	 * Returns the last challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Challenge> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenges before and after the current challenge in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param challengeId the primary key of the current challenge
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge[] findByUuid_C_PrevAndNext(long challengeId, String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = findByPrimaryKey(challengeId);

		Session session = null;

		try {
			session = openSession();

			Challenge[] array = new ChallengeImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, challenge, uuid,
					companyId, orderByComparator, true);

			array[1] = challenge;

			array[2] = getByUuid_C_PrevAndNext(session, challenge, uuid,
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

	protected Challenge getByUuid_C_PrevAndNext(Session session,
		Challenge challenge, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGE_WHERE);

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
			query.append(ChallengeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Challenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenges where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (Challenge challenge : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challenge);
		}
	}

	/**
	 * Returns the number of challenges where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching challenges
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

			query.append(_SQL_COUNT_CHALLENGE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "challenge.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "challenge.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(challenge.uuid IS NULL OR challenge.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "challenge.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ChallengeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the challenges where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenges where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @return the range of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenges where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByGroupId(long groupId, int start, int end,
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

		List<Challenge> list = (List<Challenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Challenge challenge : list) {
				if ((groupId != challenge.getGroupId())) {
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

			query.append(_SQL_SELECT_CHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Challenge>(list);
				}
				else {
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first challenge in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByGroupId_First(groupId, orderByComparator);

		if (challenge != null) {
			return challenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeException(msg.toString());
	}

	/**
	 * Returns the first challenge in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Challenge> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByGroupId_Last(groupId, orderByComparator);

		if (challenge != null) {
			return challenge;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeException(msg.toString());
	}

	/**
	 * Returns the last challenge in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Challenge> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenges before and after the current challenge in the ordered set where groupId = &#63;.
	 *
	 * @param challengeId the primary key of the current challenge
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge[] findByGroupId_PrevAndNext(long challengeId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = findByPrimaryKey(challengeId);

		Session session = null;

		try {
			session = openSession();

			Challenge[] array = new ChallengeImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, challenge, groupId,
					orderByComparator, true);

			array[1] = challenge;

			array[2] = getByGroupId_PrevAndNext(session, challenge, groupId,
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

	protected Challenge getByGroupId_PrevAndNext(Session session,
		Challenge challenge, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGE_WHERE);

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
			query.append(ChallengeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Challenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenges that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenges that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @return the range of matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> filterFindByGroupId(long groupId, int start, int end)
		throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenges that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_CHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Challenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<Challenge>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenges before and after the current challenge in the ordered set of challenges that the user has permission to view where groupId = &#63;.
	 *
	 * @param challengeId the primary key of the current challenge
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge[] filterFindByGroupId_PrevAndNext(long challengeId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(challengeId, groupId,
				orderByComparator);
		}

		Challenge challenge = findByPrimaryKey(challengeId);

		Session session = null;

		try {
			session = openSession();

			Challenge[] array = new ChallengeImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, challenge,
					groupId, orderByComparator, true);

			array[1] = challenge;

			array[2] = filterGetByGroupId_PrevAndNext(session, challenge,
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

	protected Challenge filterGetByGroupId_PrevAndNext(Session session,
		Challenge challenge, long groupId, OrderByComparator orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Challenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Challenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenges where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (Challenge challenge : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(challenge);
		}
	}

	/**
	 * Returns the number of challenges where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CHALLENGE_WHERE);

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

	/**
	 * Returns the number of challenges that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Challenge.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "challenge.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPANDSTATUS =
		new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupAndStatus",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPANDSTATUS =
		new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupAndStatus",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ChallengeModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPANDSTATUS = new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupAndStatus",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the challenges where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByGroupAndStatus(long groupId, int status)
		throws SystemException {
		return findByGroupAndStatus(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenges where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @return the range of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByGroupAndStatus(long groupId, int status,
		int start, int end) throws SystemException {
		return findByGroupAndStatus(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenges where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByGroupAndStatus(long groupId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPANDSTATUS;
			finderArgs = new Object[] { groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPANDSTATUS;
			finderArgs = new Object[] {
					groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<Challenge> list = (List<Challenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Challenge challenge : list) {
				if ((groupId != challenge.getGroupId()) ||
						(status != challenge.getStatus())) {
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

			query.append(_SQL_SELECT_CHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPANDSTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPANDSTATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Challenge>(list);
				}
				else {
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first challenge in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByGroupAndStatus_First(long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByGroupAndStatus_First(groupId, status,
				orderByComparator);

		if (challenge != null) {
			return challenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeException(msg.toString());
	}

	/**
	 * Returns the first challenge in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByGroupAndStatus_First(long groupId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<Challenge> list = findByGroupAndStatus(groupId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByGroupAndStatus_Last(long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByGroupAndStatus_Last(groupId, status,
				orderByComparator);

		if (challenge != null) {
			return challenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeException(msg.toString());
	}

	/**
	 * Returns the last challenge in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByGroupAndStatus_Last(long groupId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupAndStatus(groupId, status);

		if (count == 0) {
			return null;
		}

		List<Challenge> list = findByGroupAndStatus(groupId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenges before and after the current challenge in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param challengeId the primary key of the current challenge
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge[] findByGroupAndStatus_PrevAndNext(long challengeId,
		long groupId, int status, OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = findByPrimaryKey(challengeId);

		Session session = null;

		try {
			session = openSession();

			Challenge[] array = new ChallengeImpl[3];

			array[0] = getByGroupAndStatus_PrevAndNext(session, challenge,
					groupId, status, orderByComparator, true);

			array[1] = challenge;

			array[2] = getByGroupAndStatus_PrevAndNext(session, challenge,
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

	protected Challenge getByGroupAndStatus_PrevAndNext(Session session,
		Challenge challenge, long groupId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPANDSTATUS_STATUS_2);

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
			query.append(ChallengeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Challenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenges that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> filterFindByGroupAndStatus(long groupId, int status)
		throws SystemException {
		return filterFindByGroupAndStatus(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenges that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @return the range of matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> filterFindByGroupAndStatus(long groupId, int status,
		int start, int end) throws SystemException {
		return filterFindByGroupAndStatus(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenges that the user has permissions to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> filterFindByGroupAndStatus(long groupId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupAndStatus(groupId, status, start, end,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPANDSTATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Challenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			return (List<Challenge>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenges before and after the current challenge in the ordered set of challenges that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param challengeId the primary key of the current challenge
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge[] filterFindByGroupAndStatus_PrevAndNext(
		long challengeId, long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupAndStatus_PrevAndNext(challengeId, groupId,
				status, orderByComparator);
		}

		Challenge challenge = findByPrimaryKey(challengeId);

		Session session = null;

		try {
			session = openSession();

			Challenge[] array = new ChallengeImpl[3];

			array[0] = filterGetByGroupAndStatus_PrevAndNext(session,
					challenge, groupId, status, orderByComparator, true);

			array[1] = challenge;

			array[2] = filterGetByGroupAndStatus_PrevAndNext(session,
					challenge, groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Challenge filterGetByGroupAndStatus_PrevAndNext(Session session,
		Challenge challenge, long groupId, int status,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPANDSTATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Challenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Challenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenges where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupAndStatus(long groupId, int status)
		throws SystemException {
		for (Challenge challenge : findByGroupAndStatus(groupId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challenge);
		}
	}

	/**
	 * Returns the number of challenges where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupAndStatus(long groupId, int status)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPANDSTATUS;

		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPANDSTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPANDSTATUS_STATUS_2);

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

	/**
	 * Returns the number of challenges that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupAndStatus(long groupId, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupAndStatus(groupId, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPANDSTATUS_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Challenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPANDSTATUS_GROUPID_2 = "challenge.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPANDSTATUS_STATUS_2 = "challenge.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPANDFIELD =
		new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, ChallengeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupAndField",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPANDFIELD =
		new FinderPath(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGroupAndField",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the challenges where groupId = &#63; and field LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @return the matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByGroupAndField(long groupId, String field)
		throws SystemException {
		return findByGroupAndField(groupId, field, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenges where groupId = &#63; and field LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @return the range of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByGroupAndField(long groupId, String field,
		int start, int end) throws SystemException {
		return findByGroupAndField(groupId, field, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenges where groupId = &#63; and field LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findByGroupAndField(long groupId, String field,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPANDFIELD;
		finderArgs = new Object[] { groupId, field, start, end, orderByComparator };

		List<Challenge> list = (List<Challenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Challenge challenge : list) {
				if ((groupId != challenge.getGroupId()) ||
						!StringUtil.wildcardMatches(challenge.getField(),
							field, CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true)) {
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

			query.append(_SQL_SELECT_CHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPANDFIELD_GROUPID_2);

			boolean bindField = false;

			if (field == null) {
				query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_1);
			}
			else if (field.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_3);
			}
			else {
				bindField = true;

				query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindField) {
					qPos.add(field);
				}

				if (!pagination) {
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Challenge>(list);
				}
				else {
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first challenge in the ordered set where groupId = &#63; and field LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByGroupAndField_First(long groupId, String field,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByGroupAndField_First(groupId, field,
				orderByComparator);

		if (challenge != null) {
			return challenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", field=");
		msg.append(field);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeException(msg.toString());
	}

	/**
	 * Returns the first challenge in the ordered set where groupId = &#63; and field LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByGroupAndField_First(long groupId, String field,
		OrderByComparator orderByComparator) throws SystemException {
		List<Challenge> list = findByGroupAndField(groupId, field, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge in the ordered set where groupId = &#63; and field LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByGroupAndField_Last(long groupId, String field,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByGroupAndField_Last(groupId, field,
				orderByComparator);

		if (challenge != null) {
			return challenge;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", field=");
		msg.append(field);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeException(msg.toString());
	}

	/**
	 * Returns the last challenge in the ordered set where groupId = &#63; and field LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge, or <code>null</code> if a matching challenge could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByGroupAndField_Last(long groupId, String field,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupAndField(groupId, field);

		if (count == 0) {
			return null;
		}

		List<Challenge> list = findByGroupAndField(groupId, field, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenges before and after the current challenge in the ordered set where groupId = &#63; and field LIKE &#63;.
	 *
	 * @param challengeId the primary key of the current challenge
	 * @param groupId the group ID
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge[] findByGroupAndField_PrevAndNext(long challengeId,
		long groupId, String field, OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = findByPrimaryKey(challengeId);

		Session session = null;

		try {
			session = openSession();

			Challenge[] array = new ChallengeImpl[3];

			array[0] = getByGroupAndField_PrevAndNext(session, challenge,
					groupId, field, orderByComparator, true);

			array[1] = challenge;

			array[2] = getByGroupAndField_PrevAndNext(session, challenge,
					groupId, field, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Challenge getByGroupAndField_PrevAndNext(Session session,
		Challenge challenge, long groupId, String field,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPANDFIELD_GROUPID_2);

		boolean bindField = false;

		if (field == null) {
			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_1);
		}
		else if (field.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_3);
		}
		else {
			bindField = true;

			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_2);
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
			query.append(ChallengeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindField) {
			qPos.add(field);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Challenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenges that the user has permission to view where groupId = &#63; and field LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @return the matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> filterFindByGroupAndField(long groupId, String field)
		throws SystemException {
		return filterFindByGroupAndField(groupId, field, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenges that the user has permission to view where groupId = &#63; and field LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @return the range of matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> filterFindByGroupAndField(long groupId,
		String field, int start, int end) throws SystemException {
		return filterFindByGroupAndField(groupId, field, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenges that the user has permissions to view where groupId = &#63; and field LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> filterFindByGroupAndField(long groupId,
		String field, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupAndField(groupId, field, start, end,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPANDFIELD_GROUPID_2);

		boolean bindField = false;

		if (field == null) {
			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_1);
		}
		else if (field.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_3);
		}
		else {
			bindField = true;

			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Challenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindField) {
				qPos.add(field);
			}

			return (List<Challenge>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenges before and after the current challenge in the ordered set of challenges that the user has permission to view where groupId = &#63; and field LIKE &#63;.
	 *
	 * @param challengeId the primary key of the current challenge
	 * @param groupId the group ID
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge[] filterFindByGroupAndField_PrevAndNext(long challengeId,
		long groupId, String field, OrderByComparator orderByComparator)
		throws NoSuchChallengeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupAndField_PrevAndNext(challengeId, groupId, field,
				orderByComparator);
		}

		Challenge challenge = findByPrimaryKey(challengeId);

		Session session = null;

		try {
			session = openSession();

			Challenge[] array = new ChallengeImpl[3];

			array[0] = filterGetByGroupAndField_PrevAndNext(session, challenge,
					groupId, field, orderByComparator, true);

			array[1] = challenge;

			array[2] = filterGetByGroupAndField_PrevAndNext(session, challenge,
					groupId, field, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Challenge filterGetByGroupAndField_PrevAndNext(Session session,
		Challenge challenge, long groupId, String field,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPANDFIELD_GROUPID_2);

		boolean bindField = false;

		if (field == null) {
			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_1);
		}
		else if (field.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_3);
		}
		else {
			bindField = true;

			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Challenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindField) {
			qPos.add(field);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challenge);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Challenge> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenges where groupId = &#63; and field LIKE &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupAndField(long groupId, String field)
		throws SystemException {
		for (Challenge challenge : findByGroupAndField(groupId, field,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challenge);
		}
	}

	/**
	 * Returns the number of challenges where groupId = &#63; and field LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @return the number of matching challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupAndField(long groupId, String field)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPANDFIELD;

		Object[] finderArgs = new Object[] { groupId, field };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHALLENGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPANDFIELD_GROUPID_2);

			boolean bindField = false;

			if (field == null) {
				query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_1);
			}
			else if (field.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_3);
			}
			else {
				bindField = true;

				query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindField) {
					qPos.add(field);
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
	 * Returns the number of challenges that the user has permission to view where groupId = &#63; and field LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param field the field
	 * @return the number of matching challenges that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupAndField(long groupId, String field)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupAndField(groupId, field);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPANDFIELD_GROUPID_2);

		boolean bindField = false;

		if (field == null) {
			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_1);
		}
		else if (field.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_3);
		}
		else {
			bindField = true;

			query.append(_FINDER_COLUMN_GROUPANDFIELD_FIELD_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Challenge.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindField) {
				qPos.add(field);
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

	private static final String _FINDER_COLUMN_GROUPANDFIELD_GROUPID_2 = "challenge.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPANDFIELD_FIELD_1 = "challenge.field LIKE NULL";
	private static final String _FINDER_COLUMN_GROUPANDFIELD_FIELD_2 = "challenge.field LIKE ?";
	private static final String _FINDER_COLUMN_GROUPANDFIELD_FIELD_3 = "(challenge.field IS NULL OR challenge.field LIKE '')";

	public ChallengePersistenceImpl() {
		setModelClass(Challenge.class);
	}

	/**
	 * Caches the challenge in the entity cache if it is enabled.
	 *
	 * @param challenge the challenge
	 */
	@Override
	public void cacheResult(Challenge challenge) {
		EntityCacheUtil.putResult(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeImpl.class, challenge.getPrimaryKey(), challenge);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { challenge.getUuid(), challenge.getGroupId() },
			challenge);

		challenge.resetOriginalValues();
	}

	/**
	 * Caches the challenges in the entity cache if it is enabled.
	 *
	 * @param challenges the challenges
	 */
	@Override
	public void cacheResult(List<Challenge> challenges) {
		for (Challenge challenge : challenges) {
			if (EntityCacheUtil.getResult(
						ChallengeModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeImpl.class, challenge.getPrimaryKey()) == null) {
				cacheResult(challenge);
			}
			else {
				challenge.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all challenges.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChallengeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChallengeImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the challenge.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Challenge challenge) {
		EntityCacheUtil.removeResult(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeImpl.class, challenge.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(challenge);
	}

	@Override
	public void clearCache(List<Challenge> challenges) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Challenge challenge : challenges) {
			EntityCacheUtil.removeResult(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeImpl.class, challenge.getPrimaryKey());

			clearUniqueFindersCache(challenge);
		}
	}

	protected void cacheUniqueFindersCache(Challenge challenge) {
		if (challenge.isNew()) {
			Object[] args = new Object[] {
					challenge.getUuid(), challenge.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				challenge);
		}
		else {
			ChallengeModelImpl challengeModelImpl = (ChallengeModelImpl)challenge;

			if ((challengeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challenge.getUuid(), challenge.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					challenge);
			}
		}
	}

	protected void clearUniqueFindersCache(Challenge challenge) {
		ChallengeModelImpl challengeModelImpl = (ChallengeModelImpl)challenge;

		Object[] args = new Object[] { challenge.getUuid(), challenge.getGroupId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((challengeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					challengeModelImpl.getOriginalUuid(),
					challengeModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new challenge with the primary key. Does not add the challenge to the database.
	 *
	 * @param challengeId the primary key for the new challenge
	 * @return the new challenge
	 */
	@Override
	public Challenge create(long challengeId) {
		Challenge challenge = new ChallengeImpl();

		challenge.setNew(true);
		challenge.setPrimaryKey(challengeId);

		String uuid = PortalUUIDUtil.generate();

		challenge.setUuid(uuid);

		return challenge;
	}

	/**
	 * Removes the challenge with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param challengeId the primary key of the challenge
	 * @return the challenge that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge remove(long challengeId)
		throws NoSuchChallengeException, SystemException {
		return remove((Serializable)challengeId);
	}

	/**
	 * Removes the challenge with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the challenge
	 * @return the challenge that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge remove(Serializable primaryKey)
		throws NoSuchChallengeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Challenge challenge = (Challenge)session.get(ChallengeImpl.class,
					primaryKey);

			if (challenge == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChallengeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(challenge);
		}
		catch (NoSuchChallengeException nsee) {
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
	protected Challenge removeImpl(Challenge challenge)
		throws SystemException {
		challenge = toUnwrappedModel(challenge);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(challenge)) {
				challenge = (Challenge)session.get(ChallengeImpl.class,
						challenge.getPrimaryKeyObj());
			}

			if (challenge != null) {
				session.delete(challenge);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (challenge != null) {
			clearCache(challenge);
		}

		return challenge;
	}

	@Override
	public Challenge updateImpl(
		kisti.edison.challenge.model.Challenge challenge)
		throws SystemException {
		challenge = toUnwrappedModel(challenge);

		boolean isNew = challenge.isNew();

		ChallengeModelImpl challengeModelImpl = (ChallengeModelImpl)challenge;

		if (Validator.isNull(challenge.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			challenge.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (challenge.isNew()) {
				session.save(challenge);

				challenge.setNew(false);
			}
			else {
				session.merge(challenge);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ChallengeModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((challengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { challengeModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((challengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeModelImpl.getOriginalUuid(),
						challengeModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						challengeModelImpl.getUuid(),
						challengeModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((challengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { challengeModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((challengeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPANDSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeModelImpl.getOriginalGroupId(),
						challengeModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPANDSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPANDSTATUS,
					args);

				args = new Object[] {
						challengeModelImpl.getGroupId(),
						challengeModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPANDSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPANDSTATUS,
					args);
			}
		}

		EntityCacheUtil.putResult(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeImpl.class, challenge.getPrimaryKey(), challenge);

		clearUniqueFindersCache(challenge);
		cacheUniqueFindersCache(challenge);

		return challenge;
	}

	protected Challenge toUnwrappedModel(Challenge challenge) {
		if (challenge instanceof ChallengeImpl) {
			return challenge;
		}

		ChallengeImpl challengeImpl = new ChallengeImpl();

		challengeImpl.setNew(challenge.isNew());
		challengeImpl.setPrimaryKey(challenge.getPrimaryKey());

		challengeImpl.setUuid(challenge.getUuid());
		challengeImpl.setChallengeId(challenge.getChallengeId());
		challengeImpl.setGroupId(challenge.getGroupId());
		challengeImpl.setCompanyId(challenge.getCompanyId());
		challengeImpl.setUserId(challenge.getUserId());
		challengeImpl.setUserName(challenge.getUserName());
		challengeImpl.setCreateDate(challenge.getCreateDate());
		challengeImpl.setModifiedDate(challenge.getModifiedDate());
		challengeImpl.setStatus(challenge.getStatus());
		challengeImpl.setStatusByUserId(challenge.getStatusByUserId());
		challengeImpl.setStatusByUserName(challenge.getStatusByUserName());
		challengeImpl.setStatusDate(challenge.getStatusDate());
		challengeImpl.setName(challenge.getName());
		challengeImpl.setField(challenge.getField());
		challengeImpl.setDescription(challenge.getDescription());

		return challengeImpl;
	}

	/**
	 * Returns the challenge with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge
	 * @return the challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChallengeException, SystemException {
		Challenge challenge = fetchByPrimaryKey(primaryKey);

		if (challenge == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChallengeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return challenge;
	}

	/**
	 * Returns the challenge with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeException} if it could not be found.
	 *
	 * @param challengeId the primary key of the challenge
	 * @return the challenge
	 * @throws kisti.edison.challenge.NoSuchChallengeException if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge findByPrimaryKey(long challengeId)
		throws NoSuchChallengeException, SystemException {
		return findByPrimaryKey((Serializable)challengeId);
	}

	/**
	 * Returns the challenge with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge
	 * @return the challenge, or <code>null</code> if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Challenge challenge = (Challenge)EntityCacheUtil.getResult(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeImpl.class, primaryKey);

		if (challenge == _nullChallenge) {
			return null;
		}

		if (challenge == null) {
			Session session = null;

			try {
				session = openSession();

				challenge = (Challenge)session.get(ChallengeImpl.class,
						primaryKey);

				if (challenge != null) {
					cacheResult(challenge);
				}
				else {
					EntityCacheUtil.putResult(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeImpl.class, primaryKey, _nullChallenge);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChallengeModelImpl.ENTITY_CACHE_ENABLED,
					ChallengeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return challenge;
	}

	/**
	 * Returns the challenge with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param challengeId the primary key of the challenge
	 * @return the challenge, or <code>null</code> if a challenge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Challenge fetchByPrimaryKey(long challengeId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)challengeId);
	}

	/**
	 * Returns all the challenges.
	 *
	 * @return the challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenges.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @return the range of challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenges.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenges
	 * @param end the upper bound of the range of challenges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of challenges
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Challenge> findAll(int start, int end,
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

		List<Challenge> list = (List<Challenge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHALLENGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHALLENGE;

				if (pagination) {
					sql = sql.concat(ChallengeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Challenge>(list);
				}
				else {
					list = (List<Challenge>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the challenges from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Challenge challenge : findAll()) {
			remove(challenge);
		}
	}

	/**
	 * Returns the number of challenges.
	 *
	 * @return the number of challenges
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

				Query q = session.createQuery(_SQL_COUNT_CHALLENGE);

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
	 * Initializes the challenge persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.kisti.edison.challenge.model.Challenge")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Challenge>> listenersList = new ArrayList<ModelListener<Challenge>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Challenge>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ChallengeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHALLENGE = "SELECT challenge FROM Challenge challenge";
	private static final String _SQL_SELECT_CHALLENGE_WHERE = "SELECT challenge FROM Challenge challenge WHERE ";
	private static final String _SQL_COUNT_CHALLENGE = "SELECT COUNT(challenge) FROM Challenge challenge";
	private static final String _SQL_COUNT_CHALLENGE_WHERE = "SELECT COUNT(challenge) FROM Challenge challenge WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "challenge.challengeId";
	private static final String _FILTER_SQL_SELECT_CHALLENGE_WHERE = "SELECT DISTINCT {challenge.*} FROM challenge_Challenge challenge WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {challenge_Challenge.*} FROM (SELECT DISTINCT challenge.challengeId FROM challenge_Challenge challenge WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN challenge_Challenge ON TEMP_TABLE.challengeId = challenge_Challenge.challengeId";
	private static final String _FILTER_SQL_COUNT_CHALLENGE_WHERE = "SELECT COUNT(DISTINCT challenge.challengeId) AS COUNT_VALUE FROM challenge_Challenge challenge WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "challenge";
	private static final String _FILTER_ENTITY_TABLE = "challenge_Challenge";
	private static final String _ORDER_BY_ENTITY_ALIAS = "challenge.";
	private static final String _ORDER_BY_ENTITY_TABLE = "challenge_Challenge.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Challenge exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Challenge exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChallengePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static Challenge _nullChallenge = new ChallengeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Challenge> toCacheModel() {
				return _nullChallengeCacheModel;
			}
		};

	private static CacheModel<Challenge> _nullChallengeCacheModel = new CacheModel<Challenge>() {
			@Override
			public Challenge toEntityModel() {
				return _nullChallenge;
			}
		};
}