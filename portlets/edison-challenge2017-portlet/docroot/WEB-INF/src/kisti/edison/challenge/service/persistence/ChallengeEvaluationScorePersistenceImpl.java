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

import kisti.edison.challenge.NoSuchChallengeEvaluationScoreException;
import kisti.edison.challenge.model.ChallengeEvaluationScore;
import kisti.edison.challenge.model.impl.ChallengeEvaluationScoreImpl;
import kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the challenge evaluation score service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationScorePersistence
 * @see ChallengeEvaluationScoreUtil
 * @generated
 */
public class ChallengeEvaluationScorePersistenceImpl extends BasePersistenceImpl<ChallengeEvaluationScore>
	implements ChallengeEvaluationScorePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChallengeEvaluationScoreUtil} to access the challenge evaluation score persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChallengeEvaluationScoreImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ChallengeEvaluationScoreModelImpl.UUID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the challenge evaluation scores where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByUuid(String uuid, int start,
		int end) throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByUuid(String uuid, int start,
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

		List<ChallengeEvaluationScore> list = (List<ChallengeEvaluationScore>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluationScore challengeEvaluationScore : list) {
				if (!Validator.equals(uuid, challengeEvaluationScore.getUuid())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

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
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationScore>(list);
				}
				else {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation score in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByUuid_First(uuid,
				orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation score in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeEvaluationScore> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByUuid_Last(uuid,
				orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluationScore> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where uuid = &#63;.
	 *
	 * @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore[] findByUuid_PrevAndNext(
		long challengeEvaluationScoreId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = findByPrimaryKey(challengeEvaluationScoreId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore[] array = new ChallengeEvaluationScoreImpl[3];

			array[0] = getByUuid_PrevAndNext(session, challengeEvaluationScore,
					uuid, orderByComparator, true);

			array[1] = challengeEvaluationScore;

			array[2] = getByUuid_PrevAndNext(session, challengeEvaluationScore,
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

	protected ChallengeEvaluationScore getByUuid_PrevAndNext(Session session,
		ChallengeEvaluationScore challengeEvaluationScore, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

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
			query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationScore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationScore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluation scores where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (ChallengeEvaluationScore challengeEvaluationScore : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluationScore);
		}
	}

	/**
	 * Returns the number of challenge evaluation scores where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching challenge evaluation scores
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "challengeEvaluationScore.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "challengeEvaluationScore.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(challengeEvaluationScore.uuid IS NULL OR challengeEvaluationScore.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeEvaluationScoreModelImpl.UUID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the challenge evaluation score where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationScoreException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByUUID_G(uuid,
				groupId);

		if (challengeEvaluationScore == null) {
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

			throw new NoSuchChallengeEvaluationScoreException(msg.toString());
		}

		return challengeEvaluationScore;
	}

	/**
	 * Returns the challenge evaluation score where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the challenge evaluation score where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ChallengeEvaluationScore) {
			ChallengeEvaluationScore challengeEvaluationScore = (ChallengeEvaluationScore)result;

			if (!Validator.equals(uuid, challengeEvaluationScore.getUuid()) ||
					(groupId != challengeEvaluationScore.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

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

				List<ChallengeEvaluationScore> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ChallengeEvaluationScore challengeEvaluationScore = list.get(0);

					result = challengeEvaluationScore;

					cacheResult(challengeEvaluationScore);

					if ((challengeEvaluationScore.getUuid() == null) ||
							!challengeEvaluationScore.getUuid().equals(uuid) ||
							(challengeEvaluationScore.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, challengeEvaluationScore);
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
			return (ChallengeEvaluationScore)result;
		}
	}

	/**
	 * Removes the challenge evaluation score where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the challenge evaluation score that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore removeByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = findByUUID_G(uuid,
				groupId);

		return remove(challengeEvaluationScore);
	}

	/**
	 * Returns the number of challenge evaluation scores where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching challenge evaluation scores
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "challengeEvaluationScore.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "challengeEvaluationScore.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(challengeEvaluationScore.uuid IS NULL OR challengeEvaluationScore.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "challengeEvaluationScore.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeEvaluationScoreModelImpl.UUID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.COMPANYID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge evaluation scores where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByUuid_C(String uuid,
		long companyId) throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByUuid_C(String uuid,
		long companyId, int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByUuid_C(String uuid,
		long companyId, int start, int end, OrderByComparator orderByComparator)
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

		List<ChallengeEvaluationScore> list = (List<ChallengeEvaluationScore>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluationScore challengeEvaluationScore : list) {
				if (!Validator.equals(uuid, challengeEvaluationScore.getUuid()) ||
						(companyId != challengeEvaluationScore.getCompanyId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

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
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationScore>(list);
				}
				else {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation score in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByUuid_C_First(String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation score in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChallengeEvaluationScore> list = findByUuid_C(uuid, companyId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluationScore> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore[] findByUuid_C_PrevAndNext(
		long challengeEvaluationScoreId, String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = findByPrimaryKey(challengeEvaluationScoreId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore[] array = new ChallengeEvaluationScoreImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					challengeEvaluationScore, uuid, companyId,
					orderByComparator, true);

			array[1] = challengeEvaluationScore;

			array[2] = getByUuid_C_PrevAndNext(session,
					challengeEvaluationScore, uuid, companyId,
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

	protected ChallengeEvaluationScore getByUuid_C_PrevAndNext(
		Session session, ChallengeEvaluationScore challengeEvaluationScore,
		String uuid, long companyId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

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
			query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationScore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationScore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluation scores where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (ChallengeEvaluationScore challengeEvaluationScore : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluationScore);
		}
	}

	/**
	 * Returns the number of challenge evaluation scores where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching challenge evaluation scores
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "challengeEvaluationScore.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "challengeEvaluationScore.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(challengeEvaluationScore.uuid IS NULL OR challengeEvaluationScore.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "challengeEvaluationScore.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ChallengeEvaluationScoreModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the challenge evaluation scores where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupId(long groupId,
		int start, int end) throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupId(long groupId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
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

		List<ChallengeEvaluationScore> list = (List<ChallengeEvaluationScore>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluationScore challengeEvaluationScore : list) {
				if ((groupId != challengeEvaluationScore.getGroupId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationScore>(list);
				}
				else {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation score in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByGroupId_First(groupId,
				orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation score in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeEvaluationScore> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluationScore> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where groupId = &#63;.
	 *
	 * @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore[] findByGroupId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = findByPrimaryKey(challengeEvaluationScoreId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore[] array = new ChallengeEvaluationScoreImpl[3];

			array[0] = getByGroupId_PrevAndNext(session,
					challengeEvaluationScore, groupId, orderByComparator, true);

			array[1] = challengeEvaluationScore;

			array[2] = getByGroupId_PrevAndNext(session,
					challengeEvaluationScore, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeEvaluationScore getByGroupId_PrevAndNext(
		Session session, ChallengeEvaluationScore challengeEvaluationScore,
		long groupId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

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
			query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationScore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationScore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge evaluation scores that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupId(long groupId,
		int start, int end) throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupId(long groupId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					ChallengeEvaluationScoreImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					ChallengeEvaluationScoreImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<ChallengeEvaluationScore>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set of challenge evaluation scores that the user has permission to view where groupId = &#63;.
	 *
	 * @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore[] filterFindByGroupId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(challengeEvaluationScoreId,
				groupId, orderByComparator);
		}

		ChallengeEvaluationScore challengeEvaluationScore = findByPrimaryKey(challengeEvaluationScoreId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore[] array = new ChallengeEvaluationScoreImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session,
					challengeEvaluationScore, groupId, orderByComparator, true);

			array[1] = challengeEvaluationScore;

			array[2] = filterGetByGroupId_PrevAndNext(session,
					challengeEvaluationScore, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeEvaluationScore filterGetByGroupId_PrevAndNext(
		Session session, ChallengeEvaluationScore challengeEvaluationScore,
		long groupId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeEvaluationScoreImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeEvaluationScoreImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationScore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationScore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluation scores where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (ChallengeEvaluationScore challengeEvaluationScore : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluationScore);
		}
	}

	/**
	 * Returns the number of challenge evaluation scores where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenge evaluation scores
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

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
	 * Returns the number of challenge evaluation scores that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "challengeEvaluationScore.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndChallengeTeamId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndChallengeTeamId",
			new String[] { Long.class.getName(), Long.class.getName() },
			ChallengeEvaluationScoreModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.CHALLENGETEAMID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndChallengeTeamId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge evaluation scores where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId) throws SystemException {
		return findByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws SystemException {
		return findByGroupIdAndChallengeTeamId(groupId, challengeTeamId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupIdAndChallengeTeamId(
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

		List<ChallengeEvaluationScore> list = (List<ChallengeEvaluationScore>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluationScore challengeEvaluationScore : list) {
				if ((groupId != challengeEvaluationScore.getGroupId()) ||
						(challengeTeamId != challengeEvaluationScore.getChallengeTeamId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationScore>(list);
				}
				else {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation score in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByGroupIdAndChallengeTeamId_First(groupId,
				challengeTeamId, orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeTeamId=");
		msg.append(challengeTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation score in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChallengeEvaluationScore> list = findByGroupIdAndChallengeTeamId(groupId,
				challengeTeamId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByGroupIdAndChallengeTeamId_Last(groupId,
				challengeTeamId, orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeTeamId=");
		msg.append(challengeTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluationScore> list = findByGroupIdAndChallengeTeamId(groupId,
				challengeTeamId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore[] findByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = findByPrimaryKey(challengeEvaluationScoreId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore[] array = new ChallengeEvaluationScoreImpl[3];

			array[0] = getByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeEvaluationScore, groupId, challengeTeamId,
					orderByComparator, true);

			array[1] = challengeEvaluationScore;

			array[2] = getByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeEvaluationScore, groupId, challengeTeamId,
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

	protected ChallengeEvaluationScore getByGroupIdAndChallengeTeamId_PrevAndNext(
		Session session, ChallengeEvaluationScore challengeEvaluationScore,
		long groupId, long challengeTeamId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

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
			query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationScore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationScore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId) throws SystemException {
		return filterFindByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws SystemException {
		return filterFindByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores that the user has permissions to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamId(
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					ChallengeEvaluationScoreImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					ChallengeEvaluationScoreImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeTeamId);

			return (List<ChallengeEvaluationScore>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set of challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore[] filterFindByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeTeamId_PrevAndNext(challengeEvaluationScoreId,
				groupId, challengeTeamId, orderByComparator);
		}

		ChallengeEvaluationScore challengeEvaluationScore = findByPrimaryKey(challengeEvaluationScoreId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore[] array = new ChallengeEvaluationScoreImpl[3];

			array[0] = filterGetByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeEvaluationScore, groupId, challengeTeamId,
					orderByComparator, true);

			array[1] = challengeEvaluationScore;

			array[2] = filterGetByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeEvaluationScore, groupId, challengeTeamId,
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

	protected ChallengeEvaluationScore filterGetByGroupIdAndChallengeTeamId_PrevAndNext(
		Session session, ChallengeEvaluationScore challengeEvaluationScore,
		long groupId, long challengeTeamId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeEvaluationScoreImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeEvaluationScoreImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationScore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationScore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluation scores where groupId = &#63; and challengeTeamId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId) throws SystemException {
		for (ChallengeEvaluationScore challengeEvaluationScore : findByGroupIdAndChallengeTeamId(
				groupId, challengeTeamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(challengeEvaluationScore);
		}
	}

	/**
	 * Returns the number of challenge evaluation scores where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the number of matching challenge evaluation scores
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

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
	 * Returns the number of challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the number of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
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
		"challengeEvaluationScore.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2 =
		"challengeEvaluationScore.challengeTeamId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndChallengeEvaluationManagementId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndChallengeEvaluationManagementId",
			new String[] { Long.class.getName(), Long.class.getName() },
			ChallengeEvaluationScoreModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.CHALLENGEEVALUATIONMANAGEMENTID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndChallengeEvaluationManagementId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge evaluation scores where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @return the matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws SystemException {
		return findByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId, int start, int end)
		throws SystemException {
		return findByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID;
			finderArgs = new Object[] { groupId, challengeEvaluationManagementId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID;
			finderArgs = new Object[] {
					groupId, challengeEvaluationManagementId,
					
					start, end, orderByComparator
				};
		}

		List<ChallengeEvaluationScore> list = (List<ChallengeEvaluationScore>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluationScore challengeEvaluationScore : list) {
				if ((groupId != challengeEvaluationScore.getGroupId()) ||
						(challengeEvaluationManagementId != challengeEvaluationScore.getChallengeEvaluationManagementId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_CHALLENGEEVALUATIONMANAGEMENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(challengeEvaluationManagementId);

				if (!pagination) {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationScore>(list);
				}
				else {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation score in the ordered set where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByGroupIdAndChallengeEvaluationManagementId_First(
		long groupId, long challengeEvaluationManagementId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByGroupIdAndChallengeEvaluationManagementId_First(groupId,
				challengeEvaluationManagementId, orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeEvaluationManagementId=");
		msg.append(challengeEvaluationManagementId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation score in the ordered set where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByGroupIdAndChallengeEvaluationManagementId_First(
		long groupId, long challengeEvaluationManagementId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeEvaluationScore> list = findByGroupIdAndChallengeEvaluationManagementId(groupId,
				challengeEvaluationManagementId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByGroupIdAndChallengeEvaluationManagementId_Last(
		long groupId, long challengeEvaluationManagementId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByGroupIdAndChallengeEvaluationManagementId_Last(groupId,
				challengeEvaluationManagementId, orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeEvaluationManagementId=");
		msg.append(challengeEvaluationManagementId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByGroupIdAndChallengeEvaluationManagementId_Last(
		long groupId, long challengeEvaluationManagementId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupIdAndChallengeEvaluationManagementId(groupId,
				challengeEvaluationManagementId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluationScore> list = findByGroupIdAndChallengeEvaluationManagementId(groupId,
				challengeEvaluationManagementId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore[] findByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId,
		long challengeEvaluationManagementId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = findByPrimaryKey(challengeEvaluationScoreId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore[] array = new ChallengeEvaluationScoreImpl[3];

			array[0] = getByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(session,
					challengeEvaluationScore, groupId,
					challengeEvaluationManagementId, orderByComparator, true);

			array[1] = challengeEvaluationScore;

			array[2] = getByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(session,
					challengeEvaluationScore, groupId,
					challengeEvaluationManagementId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeEvaluationScore getByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(
		Session session, ChallengeEvaluationScore challengeEvaluationScore,
		long groupId, long challengeEvaluationManagementId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_CHALLENGEEVALUATIONMANAGEMENTID_2);

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
			query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeEvaluationManagementId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationScore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationScore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @return the matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws SystemException {
		return filterFindByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId, int start, int end)
		throws SystemException {
		return filterFindByGroupIdAndChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores that the user has permissions to view where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeEvaluationManagementId(groupId,
				challengeEvaluationManagementId, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_CHALLENGEEVALUATIONMANAGEMENTID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					ChallengeEvaluationScoreImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					ChallengeEvaluationScoreImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeEvaluationManagementId);

			return (List<ChallengeEvaluationScore>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set of challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore[] filterFindByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(
		long challengeEvaluationScoreId, long groupId,
		long challengeEvaluationManagementId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(challengeEvaluationScoreId,
				groupId, challengeEvaluationManagementId, orderByComparator);
		}

		ChallengeEvaluationScore challengeEvaluationScore = findByPrimaryKey(challengeEvaluationScoreId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore[] array = new ChallengeEvaluationScoreImpl[3];

			array[0] = filterGetByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(session,
					challengeEvaluationScore, groupId,
					challengeEvaluationManagementId, orderByComparator, true);

			array[1] = challengeEvaluationScore;

			array[2] = filterGetByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(session,
					challengeEvaluationScore, groupId,
					challengeEvaluationManagementId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeEvaluationScore filterGetByGroupIdAndChallengeEvaluationManagementId_PrevAndNext(
		Session session, ChallengeEvaluationScore challengeEvaluationScore,
		long groupId, long challengeEvaluationManagementId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_CHALLENGEEVALUATIONMANAGEMENTID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeEvaluationScoreImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeEvaluationScoreImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeEvaluationManagementId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationScore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationScore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluation scores where groupId = &#63; and challengeEvaluationManagementId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws SystemException {
		for (ChallengeEvaluationScore challengeEvaluationScore : findByGroupIdAndChallengeEvaluationManagementId(
				groupId, challengeEvaluationManagementId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluationScore);
		}
	}

	/**
	 * Returns the number of challenge evaluation scores where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @return the number of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndChallengeEvaluationManagementId(long groupId,
		long challengeEvaluationManagementId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID;

		Object[] finderArgs = new Object[] {
				groupId, challengeEvaluationManagementId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_CHALLENGEEVALUATIONMANAGEMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(challengeEvaluationManagementId);

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
	 * Returns the number of challenge evaluation scores that the user has permission to view where groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @return the number of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndChallengeEvaluationManagementId(groupId,
				challengeEvaluationManagementId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_CHALLENGEEVALUATIONMANAGEMENTID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeEvaluationManagementId);

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

	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_GROUPID_2 =
		"challengeEvaluationScore.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID_CHALLENGEEVALUATIONMANAGEMENTID_2 =
		"challengeEvaluationScore.challengeEvaluationManagementId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndChallengeTeamIdAndUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndChallengeTeamIdAndUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			ChallengeEvaluationScoreModelImpl.USERID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.CHALLENGETEAMID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndChallengeTeamIdAndUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId)
		throws SystemException {
		return findByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
			challengeTeamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId, int start, int end)
		throws SystemException {
		return findByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
			challengeTeamId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID;
			finderArgs = new Object[] { userId, groupId, challengeTeamId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID;
			finderArgs = new Object[] {
					userId, groupId, challengeTeamId,
					
					start, end, orderByComparator
				};
		}

		List<ChallengeEvaluationScore> list = (List<ChallengeEvaluationScore>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluationScore challengeEvaluationScore : list) {
				if ((userId != challengeEvaluationScore.getUserId()) ||
						(groupId != challengeEvaluationScore.getGroupId()) ||
						(challengeTeamId != challengeEvaluationScore.getChallengeTeamId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_USERID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_CHALLENGETEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(groupId);

				qPos.add(challengeTeamId);

				if (!pagination) {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationScore>(list);
				}
				else {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation score in the ordered set where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByGroupIdAndChallengeTeamIdAndUserId_First(
		long userId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByGroupIdAndChallengeTeamIdAndUserId_First(userId,
				groupId, challengeTeamId, orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", challengeTeamId=");
		msg.append(challengeTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation score in the ordered set where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByGroupIdAndChallengeTeamIdAndUserId_First(
		long userId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeEvaluationScore> list = findByGroupIdAndChallengeTeamIdAndUserId(userId,
				groupId, challengeTeamId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByGroupIdAndChallengeTeamIdAndUserId_Last(
		long userId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByGroupIdAndChallengeTeamIdAndUserId_Last(userId,
				groupId, challengeTeamId, orderByComparator);

		if (challengeEvaluationScore != null) {
			return challengeEvaluationScore;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", challengeTeamId=");
		msg.append(challengeTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationScoreException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation score in the ordered set where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByGroupIdAndChallengeTeamIdAndUserId_Last(
		long userId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
				challengeTeamId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluationScore> list = findByGroupIdAndChallengeTeamIdAndUserId(userId,
				groupId, challengeTeamId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore[] findByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(
		long challengeEvaluationScoreId, long userId, long groupId,
		long challengeTeamId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = findByPrimaryKey(challengeEvaluationScoreId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore[] array = new ChallengeEvaluationScoreImpl[3];

			array[0] = getByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(session,
					challengeEvaluationScore, userId, groupId, challengeTeamId,
					orderByComparator, true);

			array[1] = challengeEvaluationScore;

			array[2] = getByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(session,
					challengeEvaluationScore, userId, groupId, challengeTeamId,
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

	protected ChallengeEvaluationScore getByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(
		Session session, ChallengeEvaluationScore challengeEvaluationScore,
		long userId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_USERID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_CHALLENGETEAMID_2);

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
			query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(groupId);

		qPos.add(challengeTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationScore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationScore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge evaluation scores that the user has permission to view where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId)
		throws SystemException {
		return filterFindByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
			challengeTeamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores that the user has permission to view where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId, int start, int end)
		throws SystemException {
		return filterFindByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
			challengeTeamId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores that the user has permissions to view where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> filterFindByGroupIdAndChallengeTeamIdAndUserId(
		long userId, long groupId, long challengeTeamId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
				challengeTeamId, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_USERID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_CHALLENGETEAMID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					ChallengeEvaluationScoreImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					ChallengeEvaluationScoreImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			qPos.add(groupId);

			qPos.add(challengeTeamId);

			return (List<ChallengeEvaluationScore>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the challenge evaluation scores before and after the current challenge evaluation score in the ordered set of challenge evaluation scores that the user has permission to view where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param challengeEvaluationScoreId the primary key of the current challenge evaluation score
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore[] filterFindByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(
		long challengeEvaluationScoreId, long userId, long groupId,
		long challengeTeamId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(challengeEvaluationScoreId,
				userId, groupId, challengeTeamId, orderByComparator);
		}

		ChallengeEvaluationScore challengeEvaluationScore = findByPrimaryKey(challengeEvaluationScoreId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore[] array = new ChallengeEvaluationScoreImpl[3];

			array[0] = filterGetByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(session,
					challengeEvaluationScore, userId, groupId, challengeTeamId,
					orderByComparator, true);

			array[1] = challengeEvaluationScore;

			array[2] = filterGetByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(session,
					challengeEvaluationScore, userId, groupId, challengeTeamId,
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

	protected ChallengeEvaluationScore filterGetByGroupIdAndChallengeTeamIdAndUserId_PrevAndNext(
		Session session, ChallengeEvaluationScore challengeEvaluationScore,
		long userId, long groupId, long challengeTeamId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_USERID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_CHALLENGETEAMID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationScoreModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeEvaluationScoreImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeEvaluationScoreImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(groupId);

		qPos.add(challengeTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationScore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationScore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeTeamId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndChallengeTeamIdAndUserId(long userId,
		long groupId, long challengeTeamId) throws SystemException {
		for (ChallengeEvaluationScore challengeEvaluationScore : findByGroupIdAndChallengeTeamIdAndUserId(
				userId, groupId, challengeTeamId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluationScore);
		}
	}

	/**
	 * Returns the number of challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the number of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndChallengeTeamIdAndUserId(long userId,
		long groupId, long challengeTeamId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID;

		Object[] finderArgs = new Object[] { userId, groupId, challengeTeamId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_USERID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_CHALLENGETEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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
	 * Returns the number of challenge evaluation scores that the user has permission to view where userId = &#63; and groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the number of matching challenge evaluation scores that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndChallengeTeamIdAndUserId(long userId,
		long groupId, long challengeTeamId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndChallengeTeamIdAndUserId(userId, groupId,
				challengeTeamId);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_USERID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_CHALLENGETEAMID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationScore.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

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

	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_USERID_2 =
		"challengeEvaluationScore.userId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_GROUPID_2 =
		"challengeEvaluationScore.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGETEAMIDANDUSERID_CHALLENGETEAMID_2 =
		"challengeEvaluationScore.challengeTeamId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByGroupIdAndChallengeEvaluatinManagementIdAndUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			ChallengeEvaluationScoreModelImpl.USERID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeEvaluationScoreModelImpl.CHALLENGEEVALUATIONMANAGEMENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID =
		new FinderPath(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndChallengeEvaluatinManagementIdAndUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the challenge evaluation score where userId = &#63; and groupId = &#63; and challengeEvaluationManagementId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationScoreException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @return the matching challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByGroupIdAndChallengeEvaluatinManagementIdAndUserId(
		long userId, long groupId, long challengeEvaluationManagementId)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByGroupIdAndChallengeEvaluatinManagementIdAndUserId(userId,
				groupId, challengeEvaluationManagementId);

		if (challengeEvaluationScore == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", challengeEvaluationManagementId=");
			msg.append(challengeEvaluationManagementId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchChallengeEvaluationScoreException(msg.toString());
		}

		return challengeEvaluationScore;
	}

	/**
	 * Returns the challenge evaluation score where userId = &#63; and groupId = &#63; and challengeEvaluationManagementId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByGroupIdAndChallengeEvaluatinManagementIdAndUserId(
		long userId, long groupId, long challengeEvaluationManagementId)
		throws SystemException {
		return fetchByGroupIdAndChallengeEvaluatinManagementIdAndUserId(userId,
			groupId, challengeEvaluationManagementId, true);
	}

	/**
	 * Returns the challenge evaluation score where userId = &#63; and groupId = &#63; and challengeEvaluationManagementId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByGroupIdAndChallengeEvaluatinManagementIdAndUserId(
		long userId, long groupId, long challengeEvaluationManagementId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, groupId, challengeEvaluationManagementId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
					finderArgs, this);
		}

		if (result instanceof ChallengeEvaluationScore) {
			ChallengeEvaluationScore challengeEvaluationScore = (ChallengeEvaluationScore)result;

			if ((userId != challengeEvaluationScore.getUserId()) ||
					(groupId != challengeEvaluationScore.getGroupId()) ||
					(challengeEvaluationManagementId != challengeEvaluationScore.getChallengeEvaluationManagementId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID_USERID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID_CHALLENGEEVALUATIONMANAGEMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(groupId);

				qPos.add(challengeEvaluationManagementId);

				List<ChallengeEvaluationScore> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"ChallengeEvaluationScorePersistenceImpl.fetchByGroupIdAndChallengeEvaluatinManagementIdAndUserId(long, long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					ChallengeEvaluationScore challengeEvaluationScore = list.get(0);

					result = challengeEvaluationScore;

					cacheResult(challengeEvaluationScore);

					if ((challengeEvaluationScore.getUserId() != userId) ||
							(challengeEvaluationScore.getGroupId() != groupId) ||
							(challengeEvaluationScore.getChallengeEvaluationManagementId() != challengeEvaluationManagementId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
							finderArgs, challengeEvaluationScore);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
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
			return (ChallengeEvaluationScore)result;
		}
	}

	/**
	 * Removes the challenge evaluation score where userId = &#63; and groupId = &#63; and challengeEvaluationManagementId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @return the challenge evaluation score that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore removeByGroupIdAndChallengeEvaluatinManagementIdAndUserId(
		long userId, long groupId, long challengeEvaluationManagementId)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = findByGroupIdAndChallengeEvaluatinManagementIdAndUserId(userId,
				groupId, challengeEvaluationManagementId);

		return remove(challengeEvaluationScore);
	}

	/**
	 * Returns the number of challenge evaluation scores where userId = &#63; and groupId = &#63; and challengeEvaluationManagementId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param challengeEvaluationManagementId the challenge evaluation management ID
	 * @return the number of matching challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndChallengeEvaluatinManagementIdAndUserId(
		long userId, long groupId, long challengeEvaluationManagementId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID;

		Object[] finderArgs = new Object[] {
				userId, groupId, challengeEvaluationManagementId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID_USERID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID_CHALLENGEEVALUATIONMANAGEMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(groupId);

				qPos.add(challengeEvaluationManagementId);

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

	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID_USERID_2 =
		"challengeEvaluationScore.userId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID_GROUPID_2 =
		"challengeEvaluationScore.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID_CHALLENGEEVALUATIONMANAGEMENTID_2 =
		"challengeEvaluationScore.challengeEvaluationManagementId = ?";

	public ChallengeEvaluationScorePersistenceImpl() {
		setModelClass(ChallengeEvaluationScore.class);
	}

	/**
	 * Caches the challenge evaluation score in the entity cache if it is enabled.
	 *
	 * @param challengeEvaluationScore the challenge evaluation score
	 */
	@Override
	public void cacheResult(ChallengeEvaluationScore challengeEvaluationScore) {
		EntityCacheUtil.putResult(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			challengeEvaluationScore.getPrimaryKey(), challengeEvaluationScore);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				challengeEvaluationScore.getUuid(),
				challengeEvaluationScore.getGroupId()
			}, challengeEvaluationScore);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
			new Object[] {
				challengeEvaluationScore.getUserId(),
				challengeEvaluationScore.getGroupId(),
				challengeEvaluationScore.getChallengeEvaluationManagementId()
			}, challengeEvaluationScore);

		challengeEvaluationScore.resetOriginalValues();
	}

	/**
	 * Caches the challenge evaluation scores in the entity cache if it is enabled.
	 *
	 * @param challengeEvaluationScores the challenge evaluation scores
	 */
	@Override
	public void cacheResult(
		List<ChallengeEvaluationScore> challengeEvaluationScores) {
		for (ChallengeEvaluationScore challengeEvaluationScore : challengeEvaluationScores) {
			if (EntityCacheUtil.getResult(
						ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeEvaluationScoreImpl.class,
						challengeEvaluationScore.getPrimaryKey()) == null) {
				cacheResult(challengeEvaluationScore);
			}
			else {
				challengeEvaluationScore.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all challenge evaluation scores.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChallengeEvaluationScoreImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChallengeEvaluationScoreImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the challenge evaluation score.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChallengeEvaluationScore challengeEvaluationScore) {
		EntityCacheUtil.removeResult(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			challengeEvaluationScore.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(challengeEvaluationScore);
	}

	@Override
	public void clearCache(
		List<ChallengeEvaluationScore> challengeEvaluationScores) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChallengeEvaluationScore challengeEvaluationScore : challengeEvaluationScores) {
			EntityCacheUtil.removeResult(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeEvaluationScoreImpl.class,
				challengeEvaluationScore.getPrimaryKey());

			clearUniqueFindersCache(challengeEvaluationScore);
		}
	}

	protected void cacheUniqueFindersCache(
		ChallengeEvaluationScore challengeEvaluationScore) {
		if (challengeEvaluationScore.isNew()) {
			Object[] args = new Object[] {
					challengeEvaluationScore.getUuid(),
					challengeEvaluationScore.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				challengeEvaluationScore);

			args = new Object[] {
					challengeEvaluationScore.getUserId(),
					challengeEvaluationScore.getGroupId(),
					challengeEvaluationScore.getChallengeEvaluationManagementId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
				args, challengeEvaluationScore);
		}
		else {
			ChallengeEvaluationScoreModelImpl challengeEvaluationScoreModelImpl = (ChallengeEvaluationScoreModelImpl)challengeEvaluationScore;

			if ((challengeEvaluationScoreModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationScore.getUuid(),
						challengeEvaluationScore.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					challengeEvaluationScore);
			}

			if ((challengeEvaluationScoreModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationScore.getUserId(),
						challengeEvaluationScore.getGroupId(),
						challengeEvaluationScore.getChallengeEvaluationManagementId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
					args, challengeEvaluationScore);
			}
		}
	}

	protected void clearUniqueFindersCache(
		ChallengeEvaluationScore challengeEvaluationScore) {
		ChallengeEvaluationScoreModelImpl challengeEvaluationScoreModelImpl = (ChallengeEvaluationScoreModelImpl)challengeEvaluationScore;

		Object[] args = new Object[] {
				challengeEvaluationScore.getUuid(),
				challengeEvaluationScore.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((challengeEvaluationScoreModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					challengeEvaluationScoreModelImpl.getOriginalUuid(),
					challengeEvaluationScoreModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				challengeEvaluationScore.getUserId(),
				challengeEvaluationScore.getGroupId(),
				challengeEvaluationScore.getChallengeEvaluationManagementId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
			args);

		if ((challengeEvaluationScoreModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID.getColumnBitmask()) != 0) {
			args = new Object[] {
					challengeEvaluationScoreModelImpl.getOriginalUserId(),
					challengeEvaluationScoreModelImpl.getOriginalGroupId(),
					challengeEvaluationScoreModelImpl.getOriginalChallengeEvaluationManagementId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUPIDANDCHALLENGEEVALUATINMANAGEMENTIDANDUSERID,
				args);
		}
	}

	/**
	 * Creates a new challenge evaluation score with the primary key. Does not add the challenge evaluation score to the database.
	 *
	 * @param challengeEvaluationScoreId the primary key for the new challenge evaluation score
	 * @return the new challenge evaluation score
	 */
	@Override
	public ChallengeEvaluationScore create(long challengeEvaluationScoreId) {
		ChallengeEvaluationScore challengeEvaluationScore = new ChallengeEvaluationScoreImpl();

		challengeEvaluationScore.setNew(true);
		challengeEvaluationScore.setPrimaryKey(challengeEvaluationScoreId);

		String uuid = PortalUUIDUtil.generate();

		challengeEvaluationScore.setUuid(uuid);

		return challengeEvaluationScore;
	}

	/**
	 * Removes the challenge evaluation score with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param challengeEvaluationScoreId the primary key of the challenge evaluation score
	 * @return the challenge evaluation score that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore remove(long challengeEvaluationScoreId)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		return remove((Serializable)challengeEvaluationScoreId);
	}

	/**
	 * Removes the challenge evaluation score with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the challenge evaluation score
	 * @return the challenge evaluation score that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore remove(Serializable primaryKey)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationScore challengeEvaluationScore = (ChallengeEvaluationScore)session.get(ChallengeEvaluationScoreImpl.class,
					primaryKey);

			if (challengeEvaluationScore == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChallengeEvaluationScoreException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(challengeEvaluationScore);
		}
		catch (NoSuchChallengeEvaluationScoreException nsee) {
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
	protected ChallengeEvaluationScore removeImpl(
		ChallengeEvaluationScore challengeEvaluationScore)
		throws SystemException {
		challengeEvaluationScore = toUnwrappedModel(challengeEvaluationScore);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(challengeEvaluationScore)) {
				challengeEvaluationScore = (ChallengeEvaluationScore)session.get(ChallengeEvaluationScoreImpl.class,
						challengeEvaluationScore.getPrimaryKeyObj());
			}

			if (challengeEvaluationScore != null) {
				session.delete(challengeEvaluationScore);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (challengeEvaluationScore != null) {
			clearCache(challengeEvaluationScore);
		}

		return challengeEvaluationScore;
	}

	@Override
	public ChallengeEvaluationScore updateImpl(
		kisti.edison.challenge.model.ChallengeEvaluationScore challengeEvaluationScore)
		throws SystemException {
		challengeEvaluationScore = toUnwrappedModel(challengeEvaluationScore);

		boolean isNew = challengeEvaluationScore.isNew();

		ChallengeEvaluationScoreModelImpl challengeEvaluationScoreModelImpl = (ChallengeEvaluationScoreModelImpl)challengeEvaluationScore;

		if (Validator.isNull(challengeEvaluationScore.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			challengeEvaluationScore.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (challengeEvaluationScore.isNew()) {
				session.save(challengeEvaluationScore);

				challengeEvaluationScore.setNew(false);
			}
			else {
				session.merge(challengeEvaluationScore);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ChallengeEvaluationScoreModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((challengeEvaluationScoreModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationScoreModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { challengeEvaluationScoreModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((challengeEvaluationScoreModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationScoreModelImpl.getOriginalUuid(),
						challengeEvaluationScoreModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						challengeEvaluationScoreModelImpl.getUuid(),
						challengeEvaluationScoreModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((challengeEvaluationScoreModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationScoreModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						challengeEvaluationScoreModelImpl.getGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((challengeEvaluationScoreModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationScoreModelImpl.getOriginalGroupId(),
						challengeEvaluationScoreModelImpl.getOriginalChallengeTeamId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID,
					args);

				args = new Object[] {
						challengeEvaluationScoreModelImpl.getGroupId(),
						challengeEvaluationScoreModelImpl.getChallengeTeamId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID,
					args);
			}

			if ((challengeEvaluationScoreModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationScoreModelImpl.getOriginalGroupId(),
						challengeEvaluationScoreModelImpl.getOriginalChallengeEvaluationManagementId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID,
					args);

				args = new Object[] {
						challengeEvaluationScoreModelImpl.getGroupId(),
						challengeEvaluationScoreModelImpl.getChallengeEvaluationManagementId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGEEVALUATIONMANAGEMENTID,
					args);
			}

			if ((challengeEvaluationScoreModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationScoreModelImpl.getOriginalUserId(),
						challengeEvaluationScoreModelImpl.getOriginalGroupId(),
						challengeEvaluationScoreModelImpl.getOriginalChallengeTeamId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID,
					args);

				args = new Object[] {
						challengeEvaluationScoreModelImpl.getUserId(),
						challengeEvaluationScoreModelImpl.getGroupId(),
						challengeEvaluationScoreModelImpl.getChallengeTeamId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMIDANDUSERID,
					args);
			}
		}

		EntityCacheUtil.putResult(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationScoreImpl.class,
			challengeEvaluationScore.getPrimaryKey(), challengeEvaluationScore);

		clearUniqueFindersCache(challengeEvaluationScore);
		cacheUniqueFindersCache(challengeEvaluationScore);

		return challengeEvaluationScore;
	}

	protected ChallengeEvaluationScore toUnwrappedModel(
		ChallengeEvaluationScore challengeEvaluationScore) {
		if (challengeEvaluationScore instanceof ChallengeEvaluationScoreImpl) {
			return challengeEvaluationScore;
		}

		ChallengeEvaluationScoreImpl challengeEvaluationScoreImpl = new ChallengeEvaluationScoreImpl();

		challengeEvaluationScoreImpl.setNew(challengeEvaluationScore.isNew());
		challengeEvaluationScoreImpl.setPrimaryKey(challengeEvaluationScore.getPrimaryKey());

		challengeEvaluationScoreImpl.setUuid(challengeEvaluationScore.getUuid());
		challengeEvaluationScoreImpl.setChallengeEvaluationScoreId(challengeEvaluationScore.getChallengeEvaluationScoreId());
		challengeEvaluationScoreImpl.setGroupId(challengeEvaluationScore.getGroupId());
		challengeEvaluationScoreImpl.setCompanyId(challengeEvaluationScore.getCompanyId());
		challengeEvaluationScoreImpl.setUserId(challengeEvaluationScore.getUserId());
		challengeEvaluationScoreImpl.setUserName(challengeEvaluationScore.getUserName());
		challengeEvaluationScoreImpl.setCreateDate(challengeEvaluationScore.getCreateDate());
		challengeEvaluationScoreImpl.setModifiedDate(challengeEvaluationScore.getModifiedDate());
		challengeEvaluationScoreImpl.setStatus(challengeEvaluationScore.getStatus());
		challengeEvaluationScoreImpl.setStatusByUserId(challengeEvaluationScore.getStatusByUserId());
		challengeEvaluationScoreImpl.setStatusByUserName(challengeEvaluationScore.getStatusByUserName());
		challengeEvaluationScoreImpl.setStatusDate(challengeEvaluationScore.getStatusDate());
		challengeEvaluationScoreImpl.setScore(challengeEvaluationScore.getScore());
		challengeEvaluationScoreImpl.setChallengeTeamId(challengeEvaluationScore.getChallengeTeamId());
		challengeEvaluationScoreImpl.setChallengeEvaluationManagementId(challengeEvaluationScore.getChallengeEvaluationManagementId());

		return challengeEvaluationScoreImpl;
	}

	/**
	 * Returns the challenge evaluation score with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge evaluation score
	 * @return the challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = fetchByPrimaryKey(primaryKey);

		if (challengeEvaluationScore == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChallengeEvaluationScoreException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return challengeEvaluationScore;
	}

	/**
	 * Returns the challenge evaluation score with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationScoreException} if it could not be found.
	 *
	 * @param challengeEvaluationScoreId the primary key of the challenge evaluation score
	 * @return the challenge evaluation score
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationScoreException if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore findByPrimaryKey(
		long challengeEvaluationScoreId)
		throws NoSuchChallengeEvaluationScoreException, SystemException {
		return findByPrimaryKey((Serializable)challengeEvaluationScoreId);
	}

	/**
	 * Returns the challenge evaluation score with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge evaluation score
	 * @return the challenge evaluation score, or <code>null</code> if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = (ChallengeEvaluationScore)EntityCacheUtil.getResult(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeEvaluationScoreImpl.class, primaryKey);

		if (challengeEvaluationScore == _nullChallengeEvaluationScore) {
			return null;
		}

		if (challengeEvaluationScore == null) {
			Session session = null;

			try {
				session = openSession();

				challengeEvaluationScore = (ChallengeEvaluationScore)session.get(ChallengeEvaluationScoreImpl.class,
						primaryKey);

				if (challengeEvaluationScore != null) {
					cacheResult(challengeEvaluationScore);
				}
				else {
					EntityCacheUtil.putResult(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeEvaluationScoreImpl.class, primaryKey,
						_nullChallengeEvaluationScore);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChallengeEvaluationScoreModelImpl.ENTITY_CACHE_ENABLED,
					ChallengeEvaluationScoreImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return challengeEvaluationScore;
	}

	/**
	 * Returns the challenge evaluation score with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param challengeEvaluationScoreId the primary key of the challenge evaluation score
	 * @return the challenge evaluation score, or <code>null</code> if a challenge evaluation score with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationScore fetchByPrimaryKey(
		long challengeEvaluationScoreId) throws SystemException {
		return fetchByPrimaryKey((Serializable)challengeEvaluationScoreId);
	}

	/**
	 * Returns all the challenge evaluation scores.
	 *
	 * @return the challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation scores.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @return the range of challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation scores.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenge evaluation scores
	 * @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of challenge evaluation scores
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationScore> findAll(int start, int end,
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

		List<ChallengeEvaluationScore> list = (List<ChallengeEvaluationScore>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHALLENGEEVALUATIONSCORE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHALLENGEEVALUATIONSCORE;

				if (pagination) {
					sql = sql.concat(ChallengeEvaluationScoreModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationScore>(list);
				}
				else {
					list = (List<ChallengeEvaluationScore>)QueryUtil.list(q,
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
	 * Removes all the challenge evaluation scores from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ChallengeEvaluationScore challengeEvaluationScore : findAll()) {
			remove(challengeEvaluationScore);
		}
	}

	/**
	 * Returns the number of challenge evaluation scores.
	 *
	 * @return the number of challenge evaluation scores
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

				Query q = session.createQuery(_SQL_COUNT_CHALLENGEEVALUATIONSCORE);

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
	 * Initializes the challenge evaluation score persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.kisti.edison.challenge.model.ChallengeEvaluationScore")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ChallengeEvaluationScore>> listenersList = new ArrayList<ModelListener<ChallengeEvaluationScore>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ChallengeEvaluationScore>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ChallengeEvaluationScoreImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHALLENGEEVALUATIONSCORE = "SELECT challengeEvaluationScore FROM ChallengeEvaluationScore challengeEvaluationScore";
	private static final String _SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE = "SELECT challengeEvaluationScore FROM ChallengeEvaluationScore challengeEvaluationScore WHERE ";
	private static final String _SQL_COUNT_CHALLENGEEVALUATIONSCORE = "SELECT COUNT(challengeEvaluationScore) FROM ChallengeEvaluationScore challengeEvaluationScore";
	private static final String _SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE = "SELECT COUNT(challengeEvaluationScore) FROM ChallengeEvaluationScore challengeEvaluationScore WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "challengeEvaluationScore.challengeEvaluationScoreId";
	private static final String _FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_WHERE =
		"SELECT DISTINCT {challengeEvaluationScore.*} FROM challenge_ChallengeEvaluationScore challengeEvaluationScore WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {challenge_ChallengeEvaluationScore.*} FROM (SELECT DISTINCT challengeEvaluationScore.challengeEvaluationScoreId FROM challenge_ChallengeEvaluationScore challengeEvaluationScore WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGEEVALUATIONSCORE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN challenge_ChallengeEvaluationScore ON TEMP_TABLE.challengeEvaluationScoreId = challenge_ChallengeEvaluationScore.challengeEvaluationScoreId";
	private static final String _FILTER_SQL_COUNT_CHALLENGEEVALUATIONSCORE_WHERE =
		"SELECT COUNT(DISTINCT challengeEvaluationScore.challengeEvaluationScoreId) AS COUNT_VALUE FROM challenge_ChallengeEvaluationScore challengeEvaluationScore WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "challengeEvaluationScore";
	private static final String _FILTER_ENTITY_TABLE = "challenge_ChallengeEvaluationScore";
	private static final String _ORDER_BY_ENTITY_ALIAS = "challengeEvaluationScore.";
	private static final String _ORDER_BY_ENTITY_TABLE = "challenge_ChallengeEvaluationScore.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChallengeEvaluationScore exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ChallengeEvaluationScore exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChallengeEvaluationScorePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static ChallengeEvaluationScore _nullChallengeEvaluationScore = new ChallengeEvaluationScoreImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ChallengeEvaluationScore> toCacheModel() {
				return _nullChallengeEvaluationScoreCacheModel;
			}
		};

	private static CacheModel<ChallengeEvaluationScore> _nullChallengeEvaluationScoreCacheModel =
		new CacheModel<ChallengeEvaluationScore>() {
			@Override
			public ChallengeEvaluationScore toEntityModel() {
				return _nullChallengeEvaluationScore;
			}
		};
}