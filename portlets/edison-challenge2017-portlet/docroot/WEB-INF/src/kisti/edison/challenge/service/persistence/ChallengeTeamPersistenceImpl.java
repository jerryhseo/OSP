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

import kisti.edison.challenge.NoSuchChallengeTeamException;
import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.impl.ChallengeTeamImpl;
import kisti.edison.challenge.model.impl.ChallengeTeamModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the challenge team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeTeamPersistence
 * @see ChallengeTeamUtil
 * @generated
 */
public class ChallengeTeamPersistenceImpl extends BasePersistenceImpl<ChallengeTeam>
	implements ChallengeTeamPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChallengeTeamUtil} to access the challenge team persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChallengeTeamImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			ChallengeTeamModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the challenge teams where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge teams where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @return the range of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge teams where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByUuid(String uuid, int start, int end,
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

		List<ChallengeTeam> list = (List<ChallengeTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeam challengeTeam : list) {
				if (!Validator.equals(uuid, challengeTeam.getUuid())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

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
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeam>(list);
				}
				else {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first challenge team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByUuid_First(uuid, orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the first challenge team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeTeam> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByUuid_Last(uuid, orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the last challenge team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeam> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge teams before and after the current challenge team in the ordered set where uuid = &#63;.
	 *
	 * @param challengeTeamId the primary key of the current challenge team
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam[] findByUuid_PrevAndNext(long challengeTeamId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = findByPrimaryKey(challengeTeamId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeam[] array = new ChallengeTeamImpl[3];

			array[0] = getByUuid_PrevAndNext(session, challengeTeam, uuid,
					orderByComparator, true);

			array[1] = challengeTeam;

			array[2] = getByUuid_PrevAndNext(session, challengeTeam, uuid,
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

	protected ChallengeTeam getByUuid_PrevAndNext(Session session,
		ChallengeTeam challengeTeam, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

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
			query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge teams where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (ChallengeTeam challengeTeam : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(challengeTeam);
		}
	}

	/**
	 * Returns the number of challenge teams where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching challenge teams
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

			query.append(_SQL_COUNT_CHALLENGETEAM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "challengeTeam.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "challengeTeam.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(challengeTeam.uuid IS NULL OR challengeTeam.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeTeamModelImpl.UUID_COLUMN_BITMASK |
			ChallengeTeamModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the challenge team where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeTeamException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByUUID_G(uuid, groupId);

		if (challengeTeam == null) {
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

			throw new NoSuchChallengeTeamException(msg.toString());
		}

		return challengeTeam;
	}

	/**
	 * Returns the challenge team where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the challenge team where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ChallengeTeam) {
			ChallengeTeam challengeTeam = (ChallengeTeam)result;

			if (!Validator.equals(uuid, challengeTeam.getUuid()) ||
					(groupId != challengeTeam.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

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

				List<ChallengeTeam> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ChallengeTeam challengeTeam = list.get(0);

					result = challengeTeam;

					cacheResult(challengeTeam);

					if ((challengeTeam.getUuid() == null) ||
							!challengeTeam.getUuid().equals(uuid) ||
							(challengeTeam.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, challengeTeam);
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
			return (ChallengeTeam)result;
		}
	}

	/**
	 * Removes the challenge team where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the challenge team that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam removeByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = findByUUID_G(uuid, groupId);

		return remove(challengeTeam);
	}

	/**
	 * Returns the number of challenge teams where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching challenge teams
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

			query.append(_SQL_COUNT_CHALLENGETEAM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "challengeTeam.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "challengeTeam.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(challengeTeam.uuid IS NULL OR challengeTeam.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "challengeTeam.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeTeamModelImpl.UUID_COLUMN_BITMASK |
			ChallengeTeamModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge teams where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge teams where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @return the range of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge teams where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByUuid_C(String uuid, long companyId,
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

		List<ChallengeTeam> list = (List<ChallengeTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeam challengeTeam : list) {
				if (!Validator.equals(uuid, challengeTeam.getUuid()) ||
						(companyId != challengeTeam.getCompanyId())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

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
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeam>(list);
				}
				else {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first challenge team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the first challenge team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeTeam> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the last challenge team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeam> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge teams before and after the current challenge team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param challengeTeamId the primary key of the current challenge team
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam[] findByUuid_C_PrevAndNext(long challengeTeamId,
		String uuid, long companyId, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = findByPrimaryKey(challengeTeamId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeam[] array = new ChallengeTeamImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, challengeTeam, uuid,
					companyId, orderByComparator, true);

			array[1] = challengeTeam;

			array[2] = getByUuid_C_PrevAndNext(session, challengeTeam, uuid,
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

	protected ChallengeTeam getByUuid_C_PrevAndNext(Session session,
		ChallengeTeam challengeTeam, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

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
			query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge teams where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (ChallengeTeam challengeTeam : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeTeam);
		}
	}

	/**
	 * Returns the number of challenge teams where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching challenge teams
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

			query.append(_SQL_COUNT_CHALLENGETEAM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "challengeTeam.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "challengeTeam.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(challengeTeam.uuid IS NULL OR challengeTeam.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "challengeTeam.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupId", new String[] { Long.class.getName() },
			ChallengeTeamModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the challenge teams where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge teams where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @return the range of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge teams where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByGroupId(long groupId, int start, int end,
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

		List<ChallengeTeam> list = (List<ChallengeTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeam challengeTeam : list) {
				if ((groupId != challengeTeam.getGroupId())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeam>(list);
				}
				else {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first challenge team in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByGroupId_First(groupId,
				orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the first challenge team in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeTeam> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the last challenge team in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeam> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge teams before and after the current challenge team in the ordered set where groupId = &#63;.
	 *
	 * @param challengeTeamId the primary key of the current challenge team
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam[] findByGroupId_PrevAndNext(long challengeTeamId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = findByPrimaryKey(challengeTeamId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeam[] array = new ChallengeTeamImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, challengeTeam,
					groupId, orderByComparator, true);

			array[1] = challengeTeam;

			array[2] = getByGroupId_PrevAndNext(session, challengeTeam,
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

	protected ChallengeTeam getByGroupId_PrevAndNext(Session session,
		ChallengeTeam challengeTeam, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

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
			query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge teams that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge teams that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @return the range of matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> filterFindByGroupId(long groupId, int start,
		int end) throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge teams that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> filterFindByGroupId(long groupId, int start,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeam.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<ChallengeTeam>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the challenge teams before and after the current challenge team in the ordered set of challenge teams that the user has permission to view where groupId = &#63;.
	 *
	 * @param challengeTeamId the primary key of the current challenge team
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam[] filterFindByGroupId_PrevAndNext(
		long challengeTeamId, long groupId, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(challengeTeamId, groupId,
				orderByComparator);
		}

		ChallengeTeam challengeTeam = findByPrimaryKey(challengeTeamId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeam[] array = new ChallengeTeamImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, challengeTeam,
					groupId, orderByComparator, true);

			array[1] = challengeTeam;

			array[2] = filterGetByGroupId_PrevAndNext(session, challengeTeam,
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

	protected ChallengeTeam filterGetByGroupId_PrevAndNext(Session session,
		ChallengeTeam challengeTeam, long groupId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeam.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge teams where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (ChallengeTeam challengeTeam : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeTeam);
		}
	}

	/**
	 * Returns the number of challenge teams where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenge teams
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

			query.append(_SQL_COUNT_CHALLENGETEAM_WHERE);

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
	 * Returns the number of challenge teams that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CHALLENGETEAM_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeam.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "challengeTeam.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDSTATUS =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndStatus",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDSTATUS =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndStatus",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ChallengeTeamModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeTeamModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDSTATUS = new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndStatus",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the challenge teams where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByGroupIdAndStatus(long groupId, int status)
		throws SystemException {
		return findByGroupIdAndStatus(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge teams where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @return the range of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByGroupIdAndStatus(long groupId, int status,
		int start, int end) throws SystemException {
		return findByGroupIdAndStatus(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge teams where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByGroupIdAndStatus(long groupId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDSTATUS;
			finderArgs = new Object[] { groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDSTATUS;
			finderArgs = new Object[] {
					groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<ChallengeTeam> list = (List<ChallengeTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeam challengeTeam : list) {
				if ((groupId != challengeTeam.getGroupId()) ||
						(status != challengeTeam.getStatus())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeam>(list);
				}
				else {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first challenge team in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByGroupIdAndStatus_First(long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByGroupIdAndStatus_First(groupId,
				status, orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the first challenge team in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByGroupIdAndStatus_First(long groupId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChallengeTeam> list = findByGroupIdAndStatus(groupId, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByGroupIdAndStatus_Last(long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByGroupIdAndStatus_Last(groupId,
				status, orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the last challenge team in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByGroupIdAndStatus_Last(long groupId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupIdAndStatus(groupId, status);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeam> list = findByGroupIdAndStatus(groupId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge teams before and after the current challenge team in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param challengeTeamId the primary key of the current challenge team
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam[] findByGroupIdAndStatus_PrevAndNext(
		long challengeTeamId, long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = findByPrimaryKey(challengeTeamId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeam[] array = new ChallengeTeamImpl[3];

			array[0] = getByGroupIdAndStatus_PrevAndNext(session,
					challengeTeam, groupId, status, orderByComparator, true);

			array[1] = challengeTeam;

			array[2] = getByGroupIdAndStatus_PrevAndNext(session,
					challengeTeam, groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeTeam getByGroupIdAndStatus_PrevAndNext(Session session,
		ChallengeTeam challengeTeam, long groupId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2);

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
			query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge teams that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> filterFindByGroupIdAndStatus(long groupId,
		int status) throws SystemException {
		return filterFindByGroupIdAndStatus(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge teams that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @return the range of matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> filterFindByGroupIdAndStatus(long groupId,
		int status, int start, int end) throws SystemException {
		return filterFindByGroupIdAndStatus(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge teams that the user has permissions to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> filterFindByGroupIdAndStatus(long groupId,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndStatus(groupId, status, start, end,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeam.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			return (List<ChallengeTeam>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the challenge teams before and after the current challenge team in the ordered set of challenge teams that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param challengeTeamId the primary key of the current challenge team
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam[] filterFindByGroupIdAndStatus_PrevAndNext(
		long challengeTeamId, long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndStatus_PrevAndNext(challengeTeamId, groupId,
				status, orderByComparator);
		}

		ChallengeTeam challengeTeam = findByPrimaryKey(challengeTeamId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeam[] array = new ChallengeTeamImpl[3];

			array[0] = filterGetByGroupIdAndStatus_PrevAndNext(session,
					challengeTeam, groupId, status, orderByComparator, true);

			array[1] = challengeTeam;

			array[2] = filterGetByGroupIdAndStatus_PrevAndNext(session,
					challengeTeam, groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeTeam filterGetByGroupIdAndStatus_PrevAndNext(
		Session session, ChallengeTeam challengeTeam, long groupId, int status,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeam.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge teams where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndStatus(long groupId, int status)
		throws SystemException {
		for (ChallengeTeam challengeTeam : findByGroupIdAndStatus(groupId,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeTeam);
		}
	}

	/**
	 * Returns the number of challenge teams where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndStatus(long groupId, int status)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDSTATUS;

		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHALLENGETEAM_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2);

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
	 * Returns the number of challenge teams that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndStatus(long groupId, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndStatus(groupId, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGETEAM_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeam.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2 = "challengeTeam.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2 = "challengeTeam.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGE =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndChildChallenge",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGE =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndChildChallenge",
			new String[] { Long.class.getName(), Long.class.getName() },
			ChallengeTeamModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeTeamModelImpl.CHILDCHALLENGEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDCHILDCHALLENGE =
		new FinderPath(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndChildChallenge",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge teams where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @return the matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByGroupIdAndChildChallenge(long groupId,
		long childChallengeId) throws SystemException {
		return findByGroupIdAndChildChallenge(groupId, childChallengeId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge teams where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @return the range of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByGroupIdAndChildChallenge(long groupId,
		long childChallengeId, int start, int end) throws SystemException {
		return findByGroupIdAndChildChallenge(groupId, childChallengeId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the challenge teams where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findByGroupIdAndChildChallenge(long groupId,
		long childChallengeId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGE;
			finderArgs = new Object[] { groupId, childChallengeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGE;
			finderArgs = new Object[] {
					groupId, childChallengeId,
					
					start, end, orderByComparator
				};
		}

		List<ChallengeTeam> list = (List<ChallengeTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeam challengeTeam : list) {
				if ((groupId != challengeTeam.getGroupId()) ||
						(childChallengeId != challengeTeam.getChildChallengeId())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_CHILDCHALLENGEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(childChallengeId);

				if (!pagination) {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeam>(list);
				}
				else {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first challenge team in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByGroupIdAndChildChallenge_First(long groupId,
		long childChallengeId, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByGroupIdAndChildChallenge_First(groupId,
				childChallengeId, orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", childChallengeId=");
		msg.append(childChallengeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the first challenge team in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByGroupIdAndChildChallenge_First(long groupId,
		long childChallengeId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChallengeTeam> list = findByGroupIdAndChildChallenge(groupId,
				childChallengeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByGroupIdAndChildChallenge_Last(long groupId,
		long childChallengeId, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByGroupIdAndChildChallenge_Last(groupId,
				childChallengeId, orderByComparator);

		if (challengeTeam != null) {
			return challengeTeam;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", childChallengeId=");
		msg.append(childChallengeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamException(msg.toString());
	}

	/**
	 * Returns the last challenge team in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team, or <code>null</code> if a matching challenge team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByGroupIdAndChildChallenge_Last(long groupId,
		long childChallengeId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGroupIdAndChildChallenge(groupId, childChallengeId);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeam> list = findByGroupIdAndChildChallenge(groupId,
				childChallengeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge teams before and after the current challenge team in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param challengeTeamId the primary key of the current challenge team
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam[] findByGroupIdAndChildChallenge_PrevAndNext(
		long challengeTeamId, long groupId, long childChallengeId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = findByPrimaryKey(challengeTeamId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeam[] array = new ChallengeTeamImpl[3];

			array[0] = getByGroupIdAndChildChallenge_PrevAndNext(session,
					challengeTeam, groupId, childChallengeId,
					orderByComparator, true);

			array[1] = challengeTeam;

			array[2] = getByGroupIdAndChildChallenge_PrevAndNext(session,
					challengeTeam, groupId, childChallengeId,
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

	protected ChallengeTeam getByGroupIdAndChildChallenge_PrevAndNext(
		Session session, ChallengeTeam challengeTeam, long groupId,
		long childChallengeId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAM_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_CHILDCHALLENGEID_2);

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
			query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(childChallengeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge teams that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @return the matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> filterFindByGroupIdAndChildChallenge(
		long groupId, long childChallengeId) throws SystemException {
		return filterFindByGroupIdAndChildChallenge(groupId, childChallengeId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge teams that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @return the range of matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> filterFindByGroupIdAndChildChallenge(
		long groupId, long childChallengeId, int start, int end)
		throws SystemException {
		return filterFindByGroupIdAndChildChallenge(groupId, childChallengeId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge teams that the user has permissions to view where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> filterFindByGroupIdAndChildChallenge(
		long groupId, long childChallengeId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChildChallenge(groupId, childChallengeId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_CHILDCHALLENGEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeam.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(childChallengeId);

			return (List<ChallengeTeam>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the challenge teams before and after the current challenge team in the ordered set of challenge teams that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param challengeTeamId the primary key of the current challenge team
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam[] filterFindByGroupIdAndChildChallenge_PrevAndNext(
		long challengeTeamId, long groupId, long childChallengeId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChildChallenge_PrevAndNext(challengeTeamId,
				groupId, childChallengeId, orderByComparator);
		}

		ChallengeTeam challengeTeam = findByPrimaryKey(challengeTeamId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeam[] array = new ChallengeTeamImpl[3];

			array[0] = filterGetByGroupIdAndChildChallenge_PrevAndNext(session,
					challengeTeam, groupId, childChallengeId,
					orderByComparator, true);

			array[1] = challengeTeam;

			array[2] = filterGetByGroupIdAndChildChallenge_PrevAndNext(session,
					challengeTeam, groupId, childChallengeId,
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

	protected ChallengeTeam filterGetByGroupIdAndChildChallenge_PrevAndNext(
		Session session, ChallengeTeam challengeTeam, long groupId,
		long childChallengeId, OrderByComparator orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_CHILDCHALLENGEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeam.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(childChallengeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge teams where groupId = &#63; and childChallengeId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndChildChallenge(long groupId,
		long childChallengeId) throws SystemException {
		for (ChallengeTeam challengeTeam : findByGroupIdAndChildChallenge(
				groupId, childChallengeId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(challengeTeam);
		}
	}

	/**
	 * Returns the number of challenge teams where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @return the number of matching challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndChildChallenge(long groupId,
		long childChallengeId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDCHILDCHALLENGE;

		Object[] finderArgs = new Object[] { groupId, childChallengeId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHALLENGETEAM_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_CHILDCHALLENGEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(childChallengeId);

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
	 * Returns the number of challenge teams that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @return the number of matching challenge teams that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndChildChallenge(long groupId,
		long childChallengeId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndChildChallenge(groupId, childChallengeId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGETEAM_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_CHILDCHALLENGEID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeam.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(childChallengeId);

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

	private static final String _FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_GROUPID_2 =
		"challengeTeam.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHILDCHALLENGE_CHILDCHALLENGEID_2 =
		"challengeTeam.childChallengeId = ?";

	public ChallengeTeamPersistenceImpl() {
		setModelClass(ChallengeTeam.class);
	}

	/**
	 * Caches the challenge team in the entity cache if it is enabled.
	 *
	 * @param challengeTeam the challenge team
	 */
	@Override
	public void cacheResult(ChallengeTeam challengeTeam) {
		EntityCacheUtil.putResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamImpl.class, challengeTeam.getPrimaryKey(),
			challengeTeam);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { challengeTeam.getUuid(), challengeTeam.getGroupId() },
			challengeTeam);

		challengeTeam.resetOriginalValues();
	}

	/**
	 * Caches the challenge teams in the entity cache if it is enabled.
	 *
	 * @param challengeTeams the challenge teams
	 */
	@Override
	public void cacheResult(List<ChallengeTeam> challengeTeams) {
		for (ChallengeTeam challengeTeam : challengeTeams) {
			if (EntityCacheUtil.getResult(
						ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeTeamImpl.class, challengeTeam.getPrimaryKey()) == null) {
				cacheResult(challengeTeam);
			}
			else {
				challengeTeam.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all challenge teams.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChallengeTeamImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChallengeTeamImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the challenge team.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChallengeTeam challengeTeam) {
		EntityCacheUtil.removeResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamImpl.class, challengeTeam.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(challengeTeam);
	}

	@Override
	public void clearCache(List<ChallengeTeam> challengeTeams) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChallengeTeam challengeTeam : challengeTeams) {
			EntityCacheUtil.removeResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeTeamImpl.class, challengeTeam.getPrimaryKey());

			clearUniqueFindersCache(challengeTeam);
		}
	}

	protected void cacheUniqueFindersCache(ChallengeTeam challengeTeam) {
		if (challengeTeam.isNew()) {
			Object[] args = new Object[] {
					challengeTeam.getUuid(), challengeTeam.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				challengeTeam);
		}
		else {
			ChallengeTeamModelImpl challengeTeamModelImpl = (ChallengeTeamModelImpl)challengeTeam;

			if ((challengeTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeam.getUuid(), challengeTeam.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					challengeTeam);
			}
		}
	}

	protected void clearUniqueFindersCache(ChallengeTeam challengeTeam) {
		ChallengeTeamModelImpl challengeTeamModelImpl = (ChallengeTeamModelImpl)challengeTeam;

		Object[] args = new Object[] {
				challengeTeam.getUuid(), challengeTeam.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((challengeTeamModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					challengeTeamModelImpl.getOriginalUuid(),
					challengeTeamModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new challenge team with the primary key. Does not add the challenge team to the database.
	 *
	 * @param challengeTeamId the primary key for the new challenge team
	 * @return the new challenge team
	 */
	@Override
	public ChallengeTeam create(long challengeTeamId) {
		ChallengeTeam challengeTeam = new ChallengeTeamImpl();

		challengeTeam.setNew(true);
		challengeTeam.setPrimaryKey(challengeTeamId);

		String uuid = PortalUUIDUtil.generate();

		challengeTeam.setUuid(uuid);

		return challengeTeam;
	}

	/**
	 * Removes the challenge team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param challengeTeamId the primary key of the challenge team
	 * @return the challenge team that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam remove(long challengeTeamId)
		throws NoSuchChallengeTeamException, SystemException {
		return remove((Serializable)challengeTeamId);
	}

	/**
	 * Removes the challenge team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the challenge team
	 * @return the challenge team that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam remove(Serializable primaryKey)
		throws NoSuchChallengeTeamException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ChallengeTeam challengeTeam = (ChallengeTeam)session.get(ChallengeTeamImpl.class,
					primaryKey);

			if (challengeTeam == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChallengeTeamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(challengeTeam);
		}
		catch (NoSuchChallengeTeamException nsee) {
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
	protected ChallengeTeam removeImpl(ChallengeTeam challengeTeam)
		throws SystemException {
		challengeTeam = toUnwrappedModel(challengeTeam);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(challengeTeam)) {
				challengeTeam = (ChallengeTeam)session.get(ChallengeTeamImpl.class,
						challengeTeam.getPrimaryKeyObj());
			}

			if (challengeTeam != null) {
				session.delete(challengeTeam);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (challengeTeam != null) {
			clearCache(challengeTeam);
		}

		return challengeTeam;
	}

	@Override
	public ChallengeTeam updateImpl(
		kisti.edison.challenge.model.ChallengeTeam challengeTeam)
		throws SystemException {
		challengeTeam = toUnwrappedModel(challengeTeam);

		boolean isNew = challengeTeam.isNew();

		ChallengeTeamModelImpl challengeTeamModelImpl = (ChallengeTeamModelImpl)challengeTeam;

		if (Validator.isNull(challengeTeam.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			challengeTeam.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (challengeTeam.isNew()) {
				session.save(challengeTeam);

				challengeTeam.setNew(false);
			}
			else {
				session.merge(challengeTeam);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ChallengeTeamModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((challengeTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { challengeTeamModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((challengeTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamModelImpl.getOriginalUuid(),
						challengeTeamModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						challengeTeamModelImpl.getUuid(),
						challengeTeamModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((challengeTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { challengeTeamModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((challengeTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamModelImpl.getOriginalGroupId(),
						challengeTeamModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDSTATUS,
					args);

				args = new Object[] {
						challengeTeamModelImpl.getGroupId(),
						challengeTeamModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDSTATUS,
					args);
			}

			if ((challengeTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamModelImpl.getOriginalGroupId(),
						challengeTeamModelImpl.getOriginalChildChallengeId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHILDCHALLENGE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGE,
					args);

				args = new Object[] {
						challengeTeamModelImpl.getGroupId(),
						challengeTeamModelImpl.getChildChallengeId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHILDCHALLENGE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGE,
					args);
			}
		}

		EntityCacheUtil.putResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamImpl.class, challengeTeam.getPrimaryKey(),
			challengeTeam);

		clearUniqueFindersCache(challengeTeam);
		cacheUniqueFindersCache(challengeTeam);

		return challengeTeam;
	}

	protected ChallengeTeam toUnwrappedModel(ChallengeTeam challengeTeam) {
		if (challengeTeam instanceof ChallengeTeamImpl) {
			return challengeTeam;
		}

		ChallengeTeamImpl challengeTeamImpl = new ChallengeTeamImpl();

		challengeTeamImpl.setNew(challengeTeam.isNew());
		challengeTeamImpl.setPrimaryKey(challengeTeam.getPrimaryKey());

		challengeTeamImpl.setUuid(challengeTeam.getUuid());
		challengeTeamImpl.setChallengeTeamId(challengeTeam.getChallengeTeamId());
		challengeTeamImpl.setGroupId(challengeTeam.getGroupId());
		challengeTeamImpl.setCompanyId(challengeTeam.getCompanyId());
		challengeTeamImpl.setUserId(challengeTeam.getUserId());
		challengeTeamImpl.setUserName(challengeTeam.getUserName());
		challengeTeamImpl.setCreateDate(challengeTeam.getCreateDate());
		challengeTeamImpl.setModifiedDate(challengeTeam.getModifiedDate());
		challengeTeamImpl.setStatus(challengeTeam.getStatus());
		challengeTeamImpl.setStatusByUserId(challengeTeam.getStatusByUserId());
		challengeTeamImpl.setStatusByUserName(challengeTeam.getStatusByUserName());
		challengeTeamImpl.setStatusDate(challengeTeam.getStatusDate());
		challengeTeamImpl.setTeamName(challengeTeam.getTeamName());
		challengeTeamImpl.setSubject(challengeTeam.getSubject());
		challengeTeamImpl.setPaperName(challengeTeam.getPaperName());
		challengeTeamImpl.setPaperAbstract(challengeTeam.getPaperAbstract());
		challengeTeamImpl.setPaperFileName(challengeTeam.getPaperFileName());
		challengeTeamImpl.setPaperSubmissionDay(challengeTeam.getPaperSubmissionDay());
		challengeTeamImpl.setPaperModifyDay(challengeTeam.getPaperModifyDay());
		challengeTeamImpl.setPaperStatusDOC(challengeTeam.isPaperStatusDOC());
		challengeTeamImpl.setPaperPDFFileName(challengeTeam.getPaperPDFFileName());
		challengeTeamImpl.setPaperPDFSubmissionDay(challengeTeam.getPaperPDFSubmissionDay());
		challengeTeamImpl.setPaperPDFModifyDay(challengeTeam.getPaperPDFModifyDay());
		challengeTeamImpl.setPaperStatusPDF(challengeTeam.isPaperStatusPDF());
		challengeTeamImpl.setPresentationName(challengeTeam.getPresentationName());
		challengeTeamImpl.setPresentationFileName(challengeTeam.getPresentationFileName());
		challengeTeamImpl.setPresentationSubmissionDay(challengeTeam.getPresentationSubmissionDay());
		challengeTeamImpl.setPresentationModifyDay(challengeTeam.getPresentationModifyDay());
		challengeTeamImpl.setPresentationStatus(challengeTeam.isPresentationStatus());
		challengeTeamImpl.setFilepath(challengeTeam.getFilepath());
		challengeTeamImpl.setAggrement(challengeTeam.isAggrement());
		challengeTeamImpl.setChildChallengeId(challengeTeam.getChildChallengeId());

		return challengeTeamImpl;
	}

	/**
	 * Returns the challenge team with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge team
	 * @return the challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChallengeTeamException, SystemException {
		ChallengeTeam challengeTeam = fetchByPrimaryKey(primaryKey);

		if (challengeTeam == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChallengeTeamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return challengeTeam;
	}

	/**
	 * Returns the challenge team with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeTeamException} if it could not be found.
	 *
	 * @param challengeTeamId the primary key of the challenge team
	 * @return the challenge team
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamException if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam findByPrimaryKey(long challengeTeamId)
		throws NoSuchChallengeTeamException, SystemException {
		return findByPrimaryKey((Serializable)challengeTeamId);
	}

	/**
	 * Returns the challenge team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge team
	 * @return the challenge team, or <code>null</code> if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ChallengeTeam challengeTeam = (ChallengeTeam)EntityCacheUtil.getResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeTeamImpl.class, primaryKey);

		if (challengeTeam == _nullChallengeTeam) {
			return null;
		}

		if (challengeTeam == null) {
			Session session = null;

			try {
				session = openSession();

				challengeTeam = (ChallengeTeam)session.get(ChallengeTeamImpl.class,
						primaryKey);

				if (challengeTeam != null) {
					cacheResult(challengeTeam);
				}
				else {
					EntityCacheUtil.putResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeTeamImpl.class, primaryKey, _nullChallengeTeam);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChallengeTeamModelImpl.ENTITY_CACHE_ENABLED,
					ChallengeTeamImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return challengeTeam;
	}

	/**
	 * Returns the challenge team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param challengeTeamId the primary key of the challenge team
	 * @return the challenge team, or <code>null</code> if a challenge team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeam fetchByPrimaryKey(long challengeTeamId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)challengeTeamId);
	}

	/**
	 * Returns all the challenge teams.
	 *
	 * @return the challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @return the range of challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenge teams
	 * @param end the upper bound of the range of challenge teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of challenge teams
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeam> findAll(int start, int end,
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

		List<ChallengeTeam> list = (List<ChallengeTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHALLENGETEAM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHALLENGETEAM;

				if (pagination) {
					sql = sql.concat(ChallengeTeamModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeam>(list);
				}
				else {
					list = (List<ChallengeTeam>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the challenge teams from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ChallengeTeam challengeTeam : findAll()) {
			remove(challengeTeam);
		}
	}

	/**
	 * Returns the number of challenge teams.
	 *
	 * @return the number of challenge teams
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

				Query q = session.createQuery(_SQL_COUNT_CHALLENGETEAM);

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
	 * Initializes the challenge team persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.kisti.edison.challenge.model.ChallengeTeam")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ChallengeTeam>> listenersList = new ArrayList<ModelListener<ChallengeTeam>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ChallengeTeam>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ChallengeTeamImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHALLENGETEAM = "SELECT challengeTeam FROM ChallengeTeam challengeTeam";
	private static final String _SQL_SELECT_CHALLENGETEAM_WHERE = "SELECT challengeTeam FROM ChallengeTeam challengeTeam WHERE ";
	private static final String _SQL_COUNT_CHALLENGETEAM = "SELECT COUNT(challengeTeam) FROM ChallengeTeam challengeTeam";
	private static final String _SQL_COUNT_CHALLENGETEAM_WHERE = "SELECT COUNT(challengeTeam) FROM ChallengeTeam challengeTeam WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "challengeTeam.challengeTeamId";
	private static final String _FILTER_SQL_SELECT_CHALLENGETEAM_WHERE = "SELECT DISTINCT {challengeTeam.*} FROM challenge_ChallengeTeam challengeTeam WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {challenge_ChallengeTeam.*} FROM (SELECT DISTINCT challengeTeam.challengeTeamId FROM challenge_ChallengeTeam challengeTeam WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGETEAM_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN challenge_ChallengeTeam ON TEMP_TABLE.challengeTeamId = challenge_ChallengeTeam.challengeTeamId";
	private static final String _FILTER_SQL_COUNT_CHALLENGETEAM_WHERE = "SELECT COUNT(DISTINCT challengeTeam.challengeTeamId) AS COUNT_VALUE FROM challenge_ChallengeTeam challengeTeam WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "challengeTeam";
	private static final String _FILTER_ENTITY_TABLE = "challenge_ChallengeTeam";
	private static final String _ORDER_BY_ENTITY_ALIAS = "challengeTeam.";
	private static final String _ORDER_BY_ENTITY_TABLE = "challenge_ChallengeTeam.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChallengeTeam exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ChallengeTeam exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChallengeTeamPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static ChallengeTeam _nullChallengeTeam = new ChallengeTeamImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ChallengeTeam> toCacheModel() {
				return _nullChallengeTeamCacheModel;
			}
		};

	private static CacheModel<ChallengeTeam> _nullChallengeTeamCacheModel = new CacheModel<ChallengeTeam>() {
			@Override
			public ChallengeTeam toEntityModel() {
				return _nullChallengeTeam;
			}
		};
}