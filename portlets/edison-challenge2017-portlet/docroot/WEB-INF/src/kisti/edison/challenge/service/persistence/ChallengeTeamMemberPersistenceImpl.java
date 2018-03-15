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

import kisti.edison.challenge.NoSuchChallengeTeamMemberException;
import kisti.edison.challenge.model.ChallengeTeamMember;
import kisti.edison.challenge.model.impl.ChallengeTeamMemberImpl;
import kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the challenge team member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeTeamMemberPersistence
 * @see ChallengeTeamMemberUtil
 * @generated
 */
public class ChallengeTeamMemberPersistenceImpl extends BasePersistenceImpl<ChallengeTeamMember>
	implements ChallengeTeamMemberPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChallengeTeamMemberUtil} to access the challenge team member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChallengeTeamMemberImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ChallengeTeamMemberModelImpl.UUID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.CHALLENGETEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the challenge team members where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge team members where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByUuid(String uuid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<ChallengeTeamMember> list = (List<ChallengeTeamMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeamMember challengeTeamMember : list) {
				if (!Validator.equals(uuid, challengeTeamMember.getUuid())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

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
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeamMember>(list);
				}
				else {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
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
	 * Returns the first challenge team member in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByUuid_First(uuid,
				orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the first challenge team member in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeTeamMember> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team member in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByUuid_Last(uuid,
				orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the last challenge team member in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeamMember> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge team members before and after the current challenge team member in the ordered set where uuid = &#63;.
	 *
	 * @param challengeTeamMemberId the primary key of the current challenge team member
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember[] findByUuid_PrevAndNext(
		long challengeTeamMemberId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = findByPrimaryKey(challengeTeamMemberId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember[] array = new ChallengeTeamMemberImpl[3];

			array[0] = getByUuid_PrevAndNext(session, challengeTeamMember,
					uuid, orderByComparator, true);

			array[1] = challengeTeamMember;

			array[2] = getByUuid_PrevAndNext(session, challengeTeamMember,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeTeamMember getByUuid_PrevAndNext(Session session,
		ChallengeTeamMember challengeTeamMember, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

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
			query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeamMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeamMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge team members where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (ChallengeTeamMember challengeTeamMember : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeTeamMember);
		}
	}

	/**
	 * Returns the number of challenge team members where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching challenge team members
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

			query.append(_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "challengeTeamMember.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "challengeTeamMember.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(challengeTeamMember.uuid IS NULL OR challengeTeamMember.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeTeamMemberModelImpl.UUID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the challenge team member where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeTeamMemberException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByUUID_G(uuid, groupId);

		if (challengeTeamMember == null) {
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

			throw new NoSuchChallengeTeamMemberException(msg.toString());
		}

		return challengeTeamMember;
	}

	/**
	 * Returns the challenge team member where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the challenge team member where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ChallengeTeamMember) {
			ChallengeTeamMember challengeTeamMember = (ChallengeTeamMember)result;

			if (!Validator.equals(uuid, challengeTeamMember.getUuid()) ||
					(groupId != challengeTeamMember.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

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

				List<ChallengeTeamMember> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ChallengeTeamMember challengeTeamMember = list.get(0);

					result = challengeTeamMember;

					cacheResult(challengeTeamMember);

					if ((challengeTeamMember.getUuid() == null) ||
							!challengeTeamMember.getUuid().equals(uuid) ||
							(challengeTeamMember.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, challengeTeamMember);
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
			return (ChallengeTeamMember)result;
		}
	}

	/**
	 * Removes the challenge team member where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the challenge team member that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember removeByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = findByUUID_G(uuid, groupId);

		return remove(challengeTeamMember);
	}

	/**
	 * Returns the number of challenge team members where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching challenge team members
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

			query.append(_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "challengeTeamMember.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "challengeTeamMember.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(challengeTeamMember.uuid IS NULL OR challengeTeamMember.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "challengeTeamMember.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeTeamMemberModelImpl.UUID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.COMPANYID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.CHALLENGETEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge team members where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge team members where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByUuid_C(String uuid, long companyId,
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

		List<ChallengeTeamMember> list = (List<ChallengeTeamMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeamMember challengeTeamMember : list) {
				if (!Validator.equals(uuid, challengeTeamMember.getUuid()) ||
						(companyId != challengeTeamMember.getCompanyId())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

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
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeamMember>(list);
				}
				else {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
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
	 * Returns the first challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the first challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeTeamMember> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the last challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeamMember> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge team members before and after the current challenge team member in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param challengeTeamMemberId the primary key of the current challenge team member
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember[] findByUuid_C_PrevAndNext(
		long challengeTeamMemberId, String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = findByPrimaryKey(challengeTeamMemberId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember[] array = new ChallengeTeamMemberImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, challengeTeamMember,
					uuid, companyId, orderByComparator, true);

			array[1] = challengeTeamMember;

			array[2] = getByUuid_C_PrevAndNext(session, challengeTeamMember,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeTeamMember getByUuid_C_PrevAndNext(Session session,
		ChallengeTeamMember challengeTeamMember, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

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
			query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeamMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeamMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge team members where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (ChallengeTeamMember challengeTeamMember : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeTeamMember);
		}
	}

	/**
	 * Returns the number of challenge team members where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching challenge team members
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

			query.append(_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "challengeTeamMember.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "challengeTeamMember.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(challengeTeamMember.uuid IS NULL OR challengeTeamMember.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "challengeTeamMember.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ChallengeTeamMemberModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.CHALLENGETEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the challenge team members where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupId(long groupId, int start,
		int end) throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge team members where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<ChallengeTeamMember> list = (List<ChallengeTeamMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeamMember challengeTeamMember : list) {
				if ((groupId != challengeTeamMember.getGroupId())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeamMember>(list);
				}
				else {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
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
	 * Returns the first challenge team member in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByGroupId_First(groupId,
				orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the first challenge team member in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeTeamMember> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team member in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the last challenge team member in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeamMember> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge team members before and after the current challenge team member in the ordered set where groupId = &#63;.
	 *
	 * @param challengeTeamMemberId the primary key of the current challenge team member
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember[] findByGroupId_PrevAndNext(
		long challengeTeamMemberId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = findByPrimaryKey(challengeTeamMemberId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember[] array = new ChallengeTeamMemberImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, challengeTeamMember,
					groupId, orderByComparator, true);

			array[1] = challengeTeamMember;

			array[2] = getByGroupId_PrevAndNext(session, challengeTeamMember,
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

	protected ChallengeTeamMember getByGroupId_PrevAndNext(Session session,
		ChallengeTeamMember challengeTeamMember, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

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
			query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeamMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeamMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge team members that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupId(long groupId,
		int start, int end) throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge team members that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupId(long groupId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamMemberImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamMemberImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<ChallengeTeamMember>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenge team members before and after the current challenge team member in the ordered set of challenge team members that the user has permission to view where groupId = &#63;.
	 *
	 * @param challengeTeamMemberId the primary key of the current challenge team member
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember[] filterFindByGroupId_PrevAndNext(
		long challengeTeamMemberId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(challengeTeamMemberId, groupId,
				orderByComparator);
		}

		ChallengeTeamMember challengeTeamMember = findByPrimaryKey(challengeTeamMemberId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember[] array = new ChallengeTeamMemberImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session,
					challengeTeamMember, groupId, orderByComparator, true);

			array[1] = challengeTeamMember;

			array[2] = filterGetByGroupId_PrevAndNext(session,
					challengeTeamMember, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeTeamMember filterGetByGroupId_PrevAndNext(
		Session session, ChallengeTeamMember challengeTeamMember, long groupId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamMemberImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamMemberImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeamMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeamMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge team members where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (ChallengeTeamMember challengeTeamMember : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeTeamMember);
		}
	}

	/**
	 * Returns the number of challenge team members where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenge team members
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

			query.append(_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

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
	 * Returns the number of challenge team members that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "challengeTeamMember.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDSTATUS =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupIdAndStatus",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDSTATUS =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndStatus",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ChallengeTeamMemberModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.STATUS_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.CHALLENGETEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDSTATUS = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndStatus",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the challenge team members where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupIdAndStatus(long groupId,
		int status) throws SystemException {
		return findByGroupIdAndStatus(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupIdAndStatus(long groupId,
		int status, int start, int end) throws SystemException {
		return findByGroupIdAndStatus(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge team members where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupIdAndStatus(long groupId,
		int status, int start, int end, OrderByComparator orderByComparator)
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

		List<ChallengeTeamMember> list = (List<ChallengeTeamMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeamMember challengeTeamMember : list) {
				if ((groupId != challengeTeamMember.getGroupId()) ||
						(status != challengeTeamMember.getStatus())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeamMember>(list);
				}
				else {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
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
	 * Returns the first challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByGroupIdAndStatus_First(long groupId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByGroupIdAndStatus_First(groupId,
				status, orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the first challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByGroupIdAndStatus_First(long groupId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChallengeTeamMember> list = findByGroupIdAndStatus(groupId,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByGroupIdAndStatus_Last(long groupId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByGroupIdAndStatus_Last(groupId,
				status, orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the last challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByGroupIdAndStatus_Last(long groupId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGroupIdAndStatus(groupId, status);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeamMember> list = findByGroupIdAndStatus(groupId,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge team members before and after the current challenge team member in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param challengeTeamMemberId the primary key of the current challenge team member
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember[] findByGroupIdAndStatus_PrevAndNext(
		long challengeTeamMemberId, long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = findByPrimaryKey(challengeTeamMemberId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember[] array = new ChallengeTeamMemberImpl[3];

			array[0] = getByGroupIdAndStatus_PrevAndNext(session,
					challengeTeamMember, groupId, status, orderByComparator,
					true);

			array[1] = challengeTeamMember;

			array[2] = getByGroupIdAndStatus_PrevAndNext(session,
					challengeTeamMember, groupId, status, orderByComparator,
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

	protected ChallengeTeamMember getByGroupIdAndStatus_PrevAndNext(
		Session session, ChallengeTeamMember challengeTeamMember, long groupId,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

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
			query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeamMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeamMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge team members that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupIdAndStatus(
		long groupId, int status) throws SystemException {
		return filterFindByGroupIdAndStatus(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupIdAndStatus(
		long groupId, int status, int start, int end) throws SystemException {
		return filterFindByGroupIdAndStatus(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge team members that the user has permissions to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupIdAndStatus(
		long groupId, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamMemberImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamMemberImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			return (List<ChallengeTeamMember>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenge team members before and after the current challenge team member in the ordered set of challenge team members that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param challengeTeamMemberId the primary key of the current challenge team member
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember[] filterFindByGroupIdAndStatus_PrevAndNext(
		long challengeTeamMemberId, long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndStatus_PrevAndNext(challengeTeamMemberId,
				groupId, status, orderByComparator);
		}

		ChallengeTeamMember challengeTeamMember = findByPrimaryKey(challengeTeamMemberId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember[] array = new ChallengeTeamMemberImpl[3];

			array[0] = filterGetByGroupIdAndStatus_PrevAndNext(session,
					challengeTeamMember, groupId, status, orderByComparator,
					true);

			array[1] = challengeTeamMember;

			array[2] = filterGetByGroupIdAndStatus_PrevAndNext(session,
					challengeTeamMember, groupId, status, orderByComparator,
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

	protected ChallengeTeamMember filterGetByGroupIdAndStatus_PrevAndNext(
		Session session, ChallengeTeamMember challengeTeamMember, long groupId,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamMemberImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamMemberImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeamMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeamMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge team members where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndStatus(long groupId, int status)
		throws SystemException {
		for (ChallengeTeamMember challengeTeamMember : findByGroupIdAndStatus(
				groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeTeamMember);
		}
	}

	/**
	 * Returns the number of challenge team members where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching challenge team members
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

			query.append(_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

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
	 * Returns the number of challenge team members that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndStatus(long groupId, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndStatus(groupId, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPIDANDSTATUS_GROUPID_2 = "challengeTeamMember.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDSTATUS_STATUS_2 = "challengeTeamMember.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndChallengeTeamId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndChallengeTeamId",
			new String[] { Long.class.getName(), Long.class.getName() },
			ChallengeTeamMemberModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.CHALLENGETEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndChallengeTeamId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge team members where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId) throws SystemException {
		return findByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws SystemException {
		return findByGroupIdAndChallengeTeamId(groupId, challengeTeamId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the challenge team members where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID;
			finderArgs = new Object[] { groupId, challengeTeamId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID;
			finderArgs = new Object[] {
					groupId, challengeTeamId,
					
					start, end, orderByComparator
				};
		}

		List<ChallengeTeamMember> list = (List<ChallengeTeamMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeamMember challengeTeamMember : list) {
				if ((groupId != challengeTeamMember.getGroupId()) ||
						(challengeTeamId != challengeTeamMember.getChallengeTeamId())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(challengeTeamId);

				if (!pagination) {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeamMember>(list);
				}
				else {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
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
	 * Returns the first challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByGroupIdAndChallengeTeamId_First(groupId,
				challengeTeamId, orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeTeamId=");
		msg.append(challengeTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the first challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChallengeTeamMember> list = findByGroupIdAndChallengeTeamId(groupId,
				challengeTeamId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByGroupIdAndChallengeTeamId_Last(groupId,
				challengeTeamId, orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeTeamId=");
		msg.append(challengeTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the last challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeamMember> list = findByGroupIdAndChallengeTeamId(groupId,
				challengeTeamId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge team members before and after the current challenge team member in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param challengeTeamMemberId the primary key of the current challenge team member
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember[] findByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = findByPrimaryKey(challengeTeamMemberId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember[] array = new ChallengeTeamMemberImpl[3];

			array[0] = getByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeTeamMember, groupId, challengeTeamId,
					orderByComparator, true);

			array[1] = challengeTeamMember;

			array[2] = getByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeTeamMember, groupId, challengeTeamId,
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

	protected ChallengeTeamMember getByGroupIdAndChallengeTeamId_PrevAndNext(
		Session session, ChallengeTeamMember challengeTeamMember, long groupId,
		long challengeTeamId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

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
			query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeamMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeamMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge team members that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId) throws SystemException {
		return filterFindByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws SystemException {
		return filterFindByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge team members that the user has permissions to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamMemberImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamMemberImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeTeamId);

			return (List<ChallengeTeamMember>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenge team members before and after the current challenge team member in the ordered set of challenge team members that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param challengeTeamMemberId the primary key of the current challenge team member
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember[] filterFindByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeTeamId_PrevAndNext(challengeTeamMemberId,
				groupId, challengeTeamId, orderByComparator);
		}

		ChallengeTeamMember challengeTeamMember = findByPrimaryKey(challengeTeamMemberId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember[] array = new ChallengeTeamMemberImpl[3];

			array[0] = filterGetByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeTeamMember, groupId, challengeTeamId,
					orderByComparator, true);

			array[1] = challengeTeamMember;

			array[2] = filterGetByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeTeamMember, groupId, challengeTeamId,
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

	protected ChallengeTeamMember filterGetByGroupIdAndChallengeTeamId_PrevAndNext(
		Session session, ChallengeTeamMember challengeTeamMember, long groupId,
		long challengeTeamId, OrderByComparator orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamMemberImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamMemberImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeamMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeamMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge team members where groupId = &#63; and challengeTeamId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId) throws SystemException {
		for (ChallengeTeamMember challengeTeamMember : findByGroupIdAndChallengeTeamId(
				groupId, challengeTeamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(challengeTeamMember);
		}
	}

	/**
	 * Returns the number of challenge team members where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the number of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMID;

		Object[] finderArgs = new Object[] { groupId, challengeTeamId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(challengeTeamId);

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
	 * Returns the number of challenge team members that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the number of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeTeamId);

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

	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2 =
		"challengeTeamMember.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2 =
		"challengeTeamMember.challengeTeamId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDAPPLYUSERID =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndApplyUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDAPPLYUSERID =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndApplyUserId",
			new String[] { Long.class.getName(), Long.class.getName() },
			ChallengeTeamMemberModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.APPLYUSERID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.CHALLENGETEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDAPPLYUSERID = new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndApplyUserId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge team members where groupId = &#63; and applyUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @return the matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupIdAndApplyUserId(long groupId,
		long applyUserId) throws SystemException {
		return findByGroupIdAndApplyUserId(groupId, applyUserId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members where groupId = &#63; and applyUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupIdAndApplyUserId(long groupId,
		long applyUserId, int start, int end) throws SystemException {
		return findByGroupIdAndApplyUserId(groupId, applyUserId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the challenge team members where groupId = &#63; and applyUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findByGroupIdAndApplyUserId(long groupId,
		long applyUserId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDAPPLYUSERID;
			finderArgs = new Object[] { groupId, applyUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDAPPLYUSERID;
			finderArgs = new Object[] {
					groupId, applyUserId,
					
					start, end, orderByComparator
				};
		}

		List<ChallengeTeamMember> list = (List<ChallengeTeamMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeTeamMember challengeTeamMember : list) {
				if ((groupId != challengeTeamMember.getGroupId()) ||
						(applyUserId != challengeTeamMember.getApplyUserId())) {
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

			query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_APPLYUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(applyUserId);

				if (!pagination) {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeamMember>(list);
				}
				else {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
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
	 * Returns the first challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByGroupIdAndApplyUserId_First(long groupId,
		long applyUserId, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByGroupIdAndApplyUserId_First(groupId,
				applyUserId, orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", applyUserId=");
		msg.append(applyUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the first challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByGroupIdAndApplyUserId_First(
		long groupId, long applyUserId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChallengeTeamMember> list = findByGroupIdAndApplyUserId(groupId,
				applyUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByGroupIdAndApplyUserId_Last(long groupId,
		long applyUserId, OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByGroupIdAndApplyUserId_Last(groupId,
				applyUserId, orderByComparator);

		if (challengeTeamMember != null) {
			return challengeTeamMember;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", applyUserId=");
		msg.append(applyUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeTeamMemberException(msg.toString());
	}

	/**
	 * Returns the last challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByGroupIdAndApplyUserId_Last(long groupId,
		long applyUserId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGroupIdAndApplyUserId(groupId, applyUserId);

		if (count == 0) {
			return null;
		}

		List<ChallengeTeamMember> list = findByGroupIdAndApplyUserId(groupId,
				applyUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge team members before and after the current challenge team member in the ordered set where groupId = &#63; and applyUserId = &#63;.
	 *
	 * @param challengeTeamMemberId the primary key of the current challenge team member
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember[] findByGroupIdAndApplyUserId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long applyUserId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = findByPrimaryKey(challengeTeamMemberId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember[] array = new ChallengeTeamMemberImpl[3];

			array[0] = getByGroupIdAndApplyUserId_PrevAndNext(session,
					challengeTeamMember, groupId, applyUserId,
					orderByComparator, true);

			array[1] = challengeTeamMember;

			array[2] = getByGroupIdAndApplyUserId_PrevAndNext(session,
					challengeTeamMember, groupId, applyUserId,
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

	protected ChallengeTeamMember getByGroupIdAndApplyUserId_PrevAndNext(
		Session session, ChallengeTeamMember challengeTeamMember, long groupId,
		long applyUserId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_APPLYUSERID_2);

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
			query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(applyUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeamMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeamMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge team members that the user has permission to view where groupId = &#63; and applyUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @return the matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupIdAndApplyUserId(
		long groupId, long applyUserId) throws SystemException {
		return filterFindByGroupIdAndApplyUserId(groupId, applyUserId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members that the user has permission to view where groupId = &#63; and applyUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupIdAndApplyUserId(
		long groupId, long applyUserId, int start, int end)
		throws SystemException {
		return filterFindByGroupIdAndApplyUserId(groupId, applyUserId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the challenge team members that the user has permissions to view where groupId = &#63; and applyUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> filterFindByGroupIdAndApplyUserId(
		long groupId, long applyUserId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndApplyUserId(groupId, applyUserId, start,
				end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_APPLYUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamMemberImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamMemberImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(applyUserId);

			return (List<ChallengeTeamMember>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenge team members before and after the current challenge team member in the ordered set of challenge team members that the user has permission to view where groupId = &#63; and applyUserId = &#63;.
	 *
	 * @param challengeTeamMemberId the primary key of the current challenge team member
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember[] filterFindByGroupIdAndApplyUserId_PrevAndNext(
		long challengeTeamMemberId, long groupId, long applyUserId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeTeamMemberException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndApplyUserId_PrevAndNext(challengeTeamMemberId,
				groupId, applyUserId, orderByComparator);
		}

		ChallengeTeamMember challengeTeamMember = findByPrimaryKey(challengeTeamMemberId);

		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember[] array = new ChallengeTeamMemberImpl[3];

			array[0] = filterGetByGroupIdAndApplyUserId_PrevAndNext(session,
					challengeTeamMember, groupId, applyUserId,
					orderByComparator, true);

			array[1] = challengeTeamMember;

			array[2] = filterGetByGroupIdAndApplyUserId_PrevAndNext(session,
					challengeTeamMember, groupId, applyUserId,
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

	protected ChallengeTeamMember filterGetByGroupIdAndApplyUserId_PrevAndNext(
		Session session, ChallengeTeamMember challengeTeamMember, long groupId,
		long applyUserId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_APPLYUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeTeamMemberModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeTeamMemberImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeTeamMemberImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(applyUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeTeamMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeTeamMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge team members where groupId = &#63; and applyUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndApplyUserId(long groupId, long applyUserId)
		throws SystemException {
		for (ChallengeTeamMember challengeTeamMember : findByGroupIdAndApplyUserId(
				groupId, applyUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeTeamMember);
		}
	}

	/**
	 * Returns the number of challenge team members where groupId = &#63; and applyUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @return the number of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndApplyUserId(long groupId, long applyUserId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDAPPLYUSERID;

		Object[] finderArgs = new Object[] { groupId, applyUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_APPLYUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(applyUserId);

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
	 * Returns the number of challenge team members that the user has permission to view where groupId = &#63; and applyUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @return the number of matching challenge team members that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndApplyUserId(long groupId, long applyUserId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndApplyUserId(groupId, applyUserId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERID_APPLYUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeTeamMember.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(applyUserId);

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

	private static final String _FINDER_COLUMN_GROUPIDANDAPPLYUSERID_GROUPID_2 = "challengeTeamMember.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDAPPLYUSERID_APPLYUSERID_2 =
		"challengeTeamMember.applyUserId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByGroupIdAndApplyUserIdAndChallengeTeamId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			ChallengeTeamMemberModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.APPLYUSERID_COLUMN_BITMASK |
			ChallengeTeamMemberModelImpl.CHALLENGETEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndApplyUserIdAndChallengeTeamId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the challenge team member where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeTeamMemberException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param challengeTeamId the challenge team ID
	 * @return the matching challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByGroupIdAndApplyUserIdAndChallengeTeamId(groupId,
				applyUserId, challengeTeamId);

		if (challengeTeamMember == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", applyUserId=");
			msg.append(applyUserId);

			msg.append(", challengeTeamId=");
			msg.append(challengeTeamId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchChallengeTeamMemberException(msg.toString());
		}

		return challengeTeamMember;
	}

	/**
	 * Returns the challenge team member where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param challengeTeamId the challenge team ID
	 * @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId)
		throws SystemException {
		return fetchByGroupIdAndApplyUserIdAndChallengeTeamId(groupId,
			applyUserId, challengeTeamId, true);
	}

	/**
	 * Returns the challenge team member where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param challengeTeamId the challenge team ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, applyUserId, challengeTeamId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
					finderArgs, this);
		}

		if (result instanceof ChallengeTeamMember) {
			ChallengeTeamMember challengeTeamMember = (ChallengeTeamMember)result;

			if ((groupId != challengeTeamMember.getGroupId()) ||
					(applyUserId != challengeTeamMember.getApplyUserId()) ||
					(challengeTeamId != challengeTeamMember.getChallengeTeamId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID_APPLYUSERID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(applyUserId);

				qPos.add(challengeTeamId);

				List<ChallengeTeamMember> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"ChallengeTeamMemberPersistenceImpl.fetchByGroupIdAndApplyUserIdAndChallengeTeamId(long, long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					ChallengeTeamMember challengeTeamMember = list.get(0);

					result = challengeTeamMember;

					cacheResult(challengeTeamMember);

					if ((challengeTeamMember.getGroupId() != groupId) ||
							(challengeTeamMember.getApplyUserId() != applyUserId) ||
							(challengeTeamMember.getChallengeTeamId() != challengeTeamId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
							finderArgs, challengeTeamMember);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
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
			return (ChallengeTeamMember)result;
		}
	}

	/**
	 * Removes the challenge team member where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param challengeTeamId the challenge team ID
	 * @return the challenge team member that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember removeByGroupIdAndApplyUserIdAndChallengeTeamId(
		long groupId, long applyUserId, long challengeTeamId)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = findByGroupIdAndApplyUserIdAndChallengeTeamId(groupId,
				applyUserId, challengeTeamId);

		return remove(challengeTeamMember);
	}

	/**
	 * Returns the number of challenge team members where groupId = &#63; and applyUserId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applyUserId the apply user ID
	 * @param challengeTeamId the challenge team ID
	 * @return the number of matching challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndApplyUserIdAndChallengeTeamId(long groupId,
		long applyUserId, long challengeTeamId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID;

		Object[] finderArgs = new Object[] { groupId, applyUserId, challengeTeamId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID_APPLYUSERID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(applyUserId);

				qPos.add(challengeTeamId);

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

	private static final String _FINDER_COLUMN_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID_GROUPID_2 =
		"challengeTeamMember.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID_APPLYUSERID_2 =
		"challengeTeamMember.applyUserId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID_CHALLENGETEAMID_2 =
		"challengeTeamMember.challengeTeamId = ?";

	public ChallengeTeamMemberPersistenceImpl() {
		setModelClass(ChallengeTeamMember.class);
	}

	/**
	 * Caches the challenge team member in the entity cache if it is enabled.
	 *
	 * @param challengeTeamMember the challenge team member
	 */
	@Override
	public void cacheResult(ChallengeTeamMember challengeTeamMember) {
		EntityCacheUtil.putResult(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class, challengeTeamMember.getPrimaryKey(),
			challengeTeamMember);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				challengeTeamMember.getUuid(), challengeTeamMember.getGroupId()
			}, challengeTeamMember);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
			new Object[] {
				challengeTeamMember.getGroupId(),
				challengeTeamMember.getApplyUserId(),
				challengeTeamMember.getChallengeTeamId()
			}, challengeTeamMember);

		challengeTeamMember.resetOriginalValues();
	}

	/**
	 * Caches the challenge team members in the entity cache if it is enabled.
	 *
	 * @param challengeTeamMembers the challenge team members
	 */
	@Override
	public void cacheResult(List<ChallengeTeamMember> challengeTeamMembers) {
		for (ChallengeTeamMember challengeTeamMember : challengeTeamMembers) {
			if (EntityCacheUtil.getResult(
						ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeTeamMemberImpl.class,
						challengeTeamMember.getPrimaryKey()) == null) {
				cacheResult(challengeTeamMember);
			}
			else {
				challengeTeamMember.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all challenge team members.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChallengeTeamMemberImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChallengeTeamMemberImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the challenge team member.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChallengeTeamMember challengeTeamMember) {
		EntityCacheUtil.removeResult(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class, challengeTeamMember.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(challengeTeamMember);
	}

	@Override
	public void clearCache(List<ChallengeTeamMember> challengeTeamMembers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChallengeTeamMember challengeTeamMember : challengeTeamMembers) {
			EntityCacheUtil.removeResult(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeTeamMemberImpl.class,
				challengeTeamMember.getPrimaryKey());

			clearUniqueFindersCache(challengeTeamMember);
		}
	}

	protected void cacheUniqueFindersCache(
		ChallengeTeamMember challengeTeamMember) {
		if (challengeTeamMember.isNew()) {
			Object[] args = new Object[] {
					challengeTeamMember.getUuid(),
					challengeTeamMember.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				challengeTeamMember);

			args = new Object[] {
					challengeTeamMember.getGroupId(),
					challengeTeamMember.getApplyUserId(),
					challengeTeamMember.getChallengeTeamId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
				args, challengeTeamMember);
		}
		else {
			ChallengeTeamMemberModelImpl challengeTeamMemberModelImpl = (ChallengeTeamMemberModelImpl)challengeTeamMember;

			if ((challengeTeamMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamMember.getUuid(),
						challengeTeamMember.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					challengeTeamMember);
			}

			if ((challengeTeamMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamMember.getGroupId(),
						challengeTeamMember.getApplyUserId(),
						challengeTeamMember.getChallengeTeamId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
					args, challengeTeamMember);
			}
		}
	}

	protected void clearUniqueFindersCache(
		ChallengeTeamMember challengeTeamMember) {
		ChallengeTeamMemberModelImpl challengeTeamMemberModelImpl = (ChallengeTeamMemberModelImpl)challengeTeamMember;

		Object[] args = new Object[] {
				challengeTeamMember.getUuid(), challengeTeamMember.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((challengeTeamMemberModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					challengeTeamMemberModelImpl.getOriginalUuid(),
					challengeTeamMemberModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				challengeTeamMember.getGroupId(),
				challengeTeamMember.getApplyUserId(),
				challengeTeamMember.getChallengeTeamId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
			args);

		if ((challengeTeamMemberModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID.getColumnBitmask()) != 0) {
			args = new Object[] {
					challengeTeamMemberModelImpl.getOriginalGroupId(),
					challengeTeamMemberModelImpl.getOriginalApplyUserId(),
					challengeTeamMemberModelImpl.getOriginalChallengeTeamId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUPIDANDAPPLYUSERIDANDCHALLENGETEAMID,
				args);
		}
	}

	/**
	 * Creates a new challenge team member with the primary key. Does not add the challenge team member to the database.
	 *
	 * @param challengeTeamMemberId the primary key for the new challenge team member
	 * @return the new challenge team member
	 */
	@Override
	public ChallengeTeamMember create(long challengeTeamMemberId) {
		ChallengeTeamMember challengeTeamMember = new ChallengeTeamMemberImpl();

		challengeTeamMember.setNew(true);
		challengeTeamMember.setPrimaryKey(challengeTeamMemberId);

		String uuid = PortalUUIDUtil.generate();

		challengeTeamMember.setUuid(uuid);

		return challengeTeamMember;
	}

	/**
	 * Removes the challenge team member with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param challengeTeamMemberId the primary key of the challenge team member
	 * @return the challenge team member that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember remove(long challengeTeamMemberId)
		throws NoSuchChallengeTeamMemberException, SystemException {
		return remove((Serializable)challengeTeamMemberId);
	}

	/**
	 * Removes the challenge team member with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the challenge team member
	 * @return the challenge team member that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember remove(Serializable primaryKey)
		throws NoSuchChallengeTeamMemberException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ChallengeTeamMember challengeTeamMember = (ChallengeTeamMember)session.get(ChallengeTeamMemberImpl.class,
					primaryKey);

			if (challengeTeamMember == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChallengeTeamMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(challengeTeamMember);
		}
		catch (NoSuchChallengeTeamMemberException nsee) {
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
	protected ChallengeTeamMember removeImpl(
		ChallengeTeamMember challengeTeamMember) throws SystemException {
		challengeTeamMember = toUnwrappedModel(challengeTeamMember);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(challengeTeamMember)) {
				challengeTeamMember = (ChallengeTeamMember)session.get(ChallengeTeamMemberImpl.class,
						challengeTeamMember.getPrimaryKeyObj());
			}

			if (challengeTeamMember != null) {
				session.delete(challengeTeamMember);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (challengeTeamMember != null) {
			clearCache(challengeTeamMember);
		}

		return challengeTeamMember;
	}

	@Override
	public ChallengeTeamMember updateImpl(
		kisti.edison.challenge.model.ChallengeTeamMember challengeTeamMember)
		throws SystemException {
		challengeTeamMember = toUnwrappedModel(challengeTeamMember);

		boolean isNew = challengeTeamMember.isNew();

		ChallengeTeamMemberModelImpl challengeTeamMemberModelImpl = (ChallengeTeamMemberModelImpl)challengeTeamMember;

		if (Validator.isNull(challengeTeamMember.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			challengeTeamMember.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (challengeTeamMember.isNew()) {
				session.save(challengeTeamMember);

				challengeTeamMember.setNew(false);
			}
			else {
				session.merge(challengeTeamMember);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ChallengeTeamMemberModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((challengeTeamMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamMemberModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { challengeTeamMemberModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((challengeTeamMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamMemberModelImpl.getOriginalUuid(),
						challengeTeamMemberModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						challengeTeamMemberModelImpl.getUuid(),
						challengeTeamMemberModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((challengeTeamMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamMemberModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { challengeTeamMemberModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((challengeTeamMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamMemberModelImpl.getOriginalGroupId(),
						challengeTeamMemberModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDSTATUS,
					args);

				args = new Object[] {
						challengeTeamMemberModelImpl.getGroupId(),
						challengeTeamMemberModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDSTATUS,
					args);
			}

			if ((challengeTeamMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamMemberModelImpl.getOriginalGroupId(),
						challengeTeamMemberModelImpl.getOriginalChallengeTeamId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID,
					args);

				args = new Object[] {
						challengeTeamMemberModelImpl.getGroupId(),
						challengeTeamMemberModelImpl.getChallengeTeamId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID,
					args);
			}

			if ((challengeTeamMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDAPPLYUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeTeamMemberModelImpl.getOriginalGroupId(),
						challengeTeamMemberModelImpl.getOriginalApplyUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDAPPLYUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDAPPLYUSERID,
					args);

				args = new Object[] {
						challengeTeamMemberModelImpl.getGroupId(),
						challengeTeamMemberModelImpl.getApplyUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDAPPLYUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDAPPLYUSERID,
					args);
			}
		}

		EntityCacheUtil.putResult(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeTeamMemberImpl.class, challengeTeamMember.getPrimaryKey(),
			challengeTeamMember);

		clearUniqueFindersCache(challengeTeamMember);
		cacheUniqueFindersCache(challengeTeamMember);

		return challengeTeamMember;
	}

	protected ChallengeTeamMember toUnwrappedModel(
		ChallengeTeamMember challengeTeamMember) {
		if (challengeTeamMember instanceof ChallengeTeamMemberImpl) {
			return challengeTeamMember;
		}

		ChallengeTeamMemberImpl challengeTeamMemberImpl = new ChallengeTeamMemberImpl();

		challengeTeamMemberImpl.setNew(challengeTeamMember.isNew());
		challengeTeamMemberImpl.setPrimaryKey(challengeTeamMember.getPrimaryKey());

		challengeTeamMemberImpl.setUuid(challengeTeamMember.getUuid());
		challengeTeamMemberImpl.setChallengeTeamMemberId(challengeTeamMember.getChallengeTeamMemberId());
		challengeTeamMemberImpl.setGroupId(challengeTeamMember.getGroupId());
		challengeTeamMemberImpl.setCompanyId(challengeTeamMember.getCompanyId());
		challengeTeamMemberImpl.setUserId(challengeTeamMember.getUserId());
		challengeTeamMemberImpl.setUserName(challengeTeamMember.getUserName());
		challengeTeamMemberImpl.setCreateDate(challengeTeamMember.getCreateDate());
		challengeTeamMemberImpl.setModifiedDate(challengeTeamMember.getModifiedDate());
		challengeTeamMemberImpl.setStatus(challengeTeamMember.getStatus());
		challengeTeamMemberImpl.setStatusByUserId(challengeTeamMember.getStatusByUserId());
		challengeTeamMemberImpl.setStatusByUserName(challengeTeamMember.getStatusByUserName());
		challengeTeamMemberImpl.setStatusDate(challengeTeamMember.getStatusDate());
		challengeTeamMemberImpl.setApplyUserId(challengeTeamMember.getApplyUserId());
		challengeTeamMemberImpl.setApplyUserName(challengeTeamMember.getApplyUserName());
		challengeTeamMemberImpl.setEmail(challengeTeamMember.getEmail());
		challengeTeamMemberImpl.setInstitute(challengeTeamMember.getInstitute());
		challengeTeamMemberImpl.setMajor(challengeTeamMember.getMajor());
		challengeTeamMemberImpl.setGrade(challengeTeamMember.getGrade());
		challengeTeamMemberImpl.setPhone(challengeTeamMember.getPhone());
		challengeTeamMemberImpl.setLeader(challengeTeamMember.isLeader());
		challengeTeamMemberImpl.setChallengeTeamId(challengeTeamMember.getChallengeTeamId());

		return challengeTeamMemberImpl;
	}

	/**
	 * Returns the challenge team member with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge team member
	 * @return the challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChallengeTeamMemberException, SystemException {
		ChallengeTeamMember challengeTeamMember = fetchByPrimaryKey(primaryKey);

		if (challengeTeamMember == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChallengeTeamMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return challengeTeamMember;
	}

	/**
	 * Returns the challenge team member with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeTeamMemberException} if it could not be found.
	 *
	 * @param challengeTeamMemberId the primary key of the challenge team member
	 * @return the challenge team member
	 * @throws kisti.edison.challenge.NoSuchChallengeTeamMemberException if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember findByPrimaryKey(long challengeTeamMemberId)
		throws NoSuchChallengeTeamMemberException, SystemException {
		return findByPrimaryKey((Serializable)challengeTeamMemberId);
	}

	/**
	 * Returns the challenge team member with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge team member
	 * @return the challenge team member, or <code>null</code> if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ChallengeTeamMember challengeTeamMember = (ChallengeTeamMember)EntityCacheUtil.getResult(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeTeamMemberImpl.class, primaryKey);

		if (challengeTeamMember == _nullChallengeTeamMember) {
			return null;
		}

		if (challengeTeamMember == null) {
			Session session = null;

			try {
				session = openSession();

				challengeTeamMember = (ChallengeTeamMember)session.get(ChallengeTeamMemberImpl.class,
						primaryKey);

				if (challengeTeamMember != null) {
					cacheResult(challengeTeamMember);
				}
				else {
					EntityCacheUtil.putResult(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeTeamMemberImpl.class, primaryKey,
						_nullChallengeTeamMember);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChallengeTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
					ChallengeTeamMemberImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return challengeTeamMember;
	}

	/**
	 * Returns the challenge team member with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param challengeTeamMemberId the primary key of the challenge team member
	 * @return the challenge team member, or <code>null</code> if a challenge team member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeTeamMember fetchByPrimaryKey(long challengeTeamMemberId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)challengeTeamMemberId);
	}

	/**
	 * Returns all the challenge team members.
	 *
	 * @return the challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge team members.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @return the range of challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge team members.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenge team members
	 * @param end the upper bound of the range of challenge team members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of challenge team members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeTeamMember> findAll(int start, int end,
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

		List<ChallengeTeamMember> list = (List<ChallengeTeamMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHALLENGETEAMMEMBER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHALLENGETEAMMEMBER;

				if (pagination) {
					sql = sql.concat(ChallengeTeamMemberModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeTeamMember>(list);
				}
				else {
					list = (List<ChallengeTeamMember>)QueryUtil.list(q,
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
	 * Removes all the challenge team members from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ChallengeTeamMember challengeTeamMember : findAll()) {
			remove(challengeTeamMember);
		}
	}

	/**
	 * Returns the number of challenge team members.
	 *
	 * @return the number of challenge team members
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

				Query q = session.createQuery(_SQL_COUNT_CHALLENGETEAMMEMBER);

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
	 * Initializes the challenge team member persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.kisti.edison.challenge.model.ChallengeTeamMember")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ChallengeTeamMember>> listenersList = new ArrayList<ModelListener<ChallengeTeamMember>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ChallengeTeamMember>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ChallengeTeamMemberImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHALLENGETEAMMEMBER = "SELECT challengeTeamMember FROM ChallengeTeamMember challengeTeamMember";
	private static final String _SQL_SELECT_CHALLENGETEAMMEMBER_WHERE = "SELECT challengeTeamMember FROM ChallengeTeamMember challengeTeamMember WHERE ";
	private static final String _SQL_COUNT_CHALLENGETEAMMEMBER = "SELECT COUNT(challengeTeamMember) FROM ChallengeTeamMember challengeTeamMember";
	private static final String _SQL_COUNT_CHALLENGETEAMMEMBER_WHERE = "SELECT COUNT(challengeTeamMember) FROM ChallengeTeamMember challengeTeamMember WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "challengeTeamMember.challengeTeamMemberId";
	private static final String _FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_WHERE = "SELECT DISTINCT {challengeTeamMember.*} FROM challenge_ChallengeTeamMember challengeTeamMember WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {challenge_ChallengeTeamMember.*} FROM (SELECT DISTINCT challengeTeamMember.challengeTeamMemberId FROM challenge_ChallengeTeamMember challengeTeamMember WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGETEAMMEMBER_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN challenge_ChallengeTeamMember ON TEMP_TABLE.challengeTeamMemberId = challenge_ChallengeTeamMember.challengeTeamMemberId";
	private static final String _FILTER_SQL_COUNT_CHALLENGETEAMMEMBER_WHERE = "SELECT COUNT(DISTINCT challengeTeamMember.challengeTeamMemberId) AS COUNT_VALUE FROM challenge_ChallengeTeamMember challengeTeamMember WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "challengeTeamMember";
	private static final String _FILTER_ENTITY_TABLE = "challenge_ChallengeTeamMember";
	private static final String _ORDER_BY_ENTITY_ALIAS = "challengeTeamMember.";
	private static final String _ORDER_BY_ENTITY_TABLE = "challenge_ChallengeTeamMember.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChallengeTeamMember exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ChallengeTeamMember exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChallengeTeamMemberPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static ChallengeTeamMember _nullChallengeTeamMember = new ChallengeTeamMemberImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ChallengeTeamMember> toCacheModel() {
				return _nullChallengeTeamMemberCacheModel;
			}
		};

	private static CacheModel<ChallengeTeamMember> _nullChallengeTeamMemberCacheModel =
		new CacheModel<ChallengeTeamMember>() {
			@Override
			public ChallengeTeamMember toEntityModel() {
				return _nullChallengeTeamMember;
			}
		};
}