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

import kisti.edison.challenge.NoSuchChallengeEvaluationException;
import kisti.edison.challenge.model.ChallengeEvaluation;
import kisti.edison.challenge.model.impl.ChallengeEvaluationImpl;
import kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the challenge evaluation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationPersistence
 * @see ChallengeEvaluationUtil
 * @generated
 */
public class ChallengeEvaluationPersistenceImpl extends BasePersistenceImpl<ChallengeEvaluation>
	implements ChallengeEvaluationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChallengeEvaluationUtil} to access the challenge evaluation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChallengeEvaluationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ChallengeEvaluationModelImpl.UUID_COLUMN_BITMASK |
			ChallengeEvaluationModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the challenge evaluations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @return the range of matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByUuid(String uuid, int start,
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

		List<ChallengeEvaluation> list = (List<ChallengeEvaluation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluation challengeEvaluation : list) {
				if (!Validator.equals(uuid, challengeEvaluation.getUuid())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATION_WHERE);

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
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeEvaluation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluation>(list);
				}
				else {
					list = (List<ChallengeEvaluation>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = fetchByUuid_First(uuid,
				orderByComparator);

		if (challengeEvaluation != null) {
			return challengeEvaluation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeEvaluation> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = fetchByUuid_Last(uuid,
				orderByComparator);

		if (challengeEvaluation != null) {
			return challengeEvaluation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluation> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluations before and after the current challenge evaluation in the ordered set where uuid = &#63;.
	 *
	 * @param challengeEvaluationId the primary key of the current challenge evaluation
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation[] findByUuid_PrevAndNext(
		long challengeEvaluationId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = findByPrimaryKey(challengeEvaluationId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluation[] array = new ChallengeEvaluationImpl[3];

			array[0] = getByUuid_PrevAndNext(session, challengeEvaluation,
					uuid, orderByComparator, true);

			array[1] = challengeEvaluation;

			array[2] = getByUuid_PrevAndNext(session, challengeEvaluation,
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

	protected ChallengeEvaluation getByUuid_PrevAndNext(Session session,
		ChallengeEvaluation challengeEvaluation, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATION_WHERE);

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
			query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (ChallengeEvaluation challengeEvaluation : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluation);
		}
	}

	/**
	 * Returns the number of challenge evaluations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching challenge evaluations
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "challengeEvaluation.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "challengeEvaluation.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(challengeEvaluation.uuid IS NULL OR challengeEvaluation.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeEvaluationModelImpl.UUID_COLUMN_BITMASK |
			ChallengeEvaluationModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the challenge evaluation where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = fetchByUUID_G(uuid, groupId);

		if (challengeEvaluation == null) {
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

			throw new NoSuchChallengeEvaluationException(msg.toString());
		}

		return challengeEvaluation;
	}

	/**
	 * Returns the challenge evaluation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the challenge evaluation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ChallengeEvaluation) {
			ChallengeEvaluation challengeEvaluation = (ChallengeEvaluation)result;

			if (!Validator.equals(uuid, challengeEvaluation.getUuid()) ||
					(groupId != challengeEvaluation.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CHALLENGEEVALUATION_WHERE);

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

				List<ChallengeEvaluation> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ChallengeEvaluation challengeEvaluation = list.get(0);

					result = challengeEvaluation;

					cacheResult(challengeEvaluation);

					if ((challengeEvaluation.getUuid() == null) ||
							!challengeEvaluation.getUuid().equals(uuid) ||
							(challengeEvaluation.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, challengeEvaluation);
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
			return (ChallengeEvaluation)result;
		}
	}

	/**
	 * Removes the challenge evaluation where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the challenge evaluation that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation removeByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = findByUUID_G(uuid, groupId);

		return remove(challengeEvaluation);
	}

	/**
	 * Returns the number of challenge evaluations where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching challenge evaluations
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "challengeEvaluation.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "challengeEvaluation.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(challengeEvaluation.uuid IS NULL OR challengeEvaluation.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "challengeEvaluation.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeEvaluationModelImpl.UUID_COLUMN_BITMASK |
			ChallengeEvaluationModelImpl.COMPANYID_COLUMN_BITMASK |
			ChallengeEvaluationModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge evaluations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @return the range of matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByUuid_C(String uuid, long companyId,
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

		List<ChallengeEvaluation> list = (List<ChallengeEvaluation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluation challengeEvaluation : list) {
				if (!Validator.equals(uuid, challengeEvaluation.getUuid()) ||
						(companyId != challengeEvaluation.getCompanyId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATION_WHERE);

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
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeEvaluation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluation>(list);
				}
				else {
					list = (List<ChallengeEvaluation>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (challengeEvaluation != null) {
			return challengeEvaluation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeEvaluation> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (challengeEvaluation != null) {
			return challengeEvaluation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluation> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluations before and after the current challenge evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param challengeEvaluationId the primary key of the current challenge evaluation
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation[] findByUuid_C_PrevAndNext(
		long challengeEvaluationId, String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = findByPrimaryKey(challengeEvaluationId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluation[] array = new ChallengeEvaluationImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, challengeEvaluation,
					uuid, companyId, orderByComparator, true);

			array[1] = challengeEvaluation;

			array[2] = getByUuid_C_PrevAndNext(session, challengeEvaluation,
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

	protected ChallengeEvaluation getByUuid_C_PrevAndNext(Session session,
		ChallengeEvaluation challengeEvaluation, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATION_WHERE);

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
			query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluations where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (ChallengeEvaluation challengeEvaluation : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluation);
		}
	}

	/**
	 * Returns the number of challenge evaluations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching challenge evaluations
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "challengeEvaluation.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "challengeEvaluation.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(challengeEvaluation.uuid IS NULL OR challengeEvaluation.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "challengeEvaluation.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ChallengeEvaluationModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeEvaluationModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the challenge evaluations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @return the range of matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByGroupId(long groupId, int start,
		int end) throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByGroupId(long groupId, int start,
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

		List<ChallengeEvaluation> list = (List<ChallengeEvaluation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluation challengeEvaluation : list) {
				if ((groupId != challengeEvaluation.getGroupId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ChallengeEvaluation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluation>(list);
				}
				else {
					list = (List<ChallengeEvaluation>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = fetchByGroupId_First(groupId,
				orderByComparator);

		if (challengeEvaluation != null) {
			return challengeEvaluation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeEvaluation> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (challengeEvaluation != null) {
			return challengeEvaluation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluation> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluations before and after the current challenge evaluation in the ordered set where groupId = &#63;.
	 *
	 * @param challengeEvaluationId the primary key of the current challenge evaluation
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation[] findByGroupId_PrevAndNext(
		long challengeEvaluationId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = findByPrimaryKey(challengeEvaluationId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluation[] array = new ChallengeEvaluationImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, challengeEvaluation,
					groupId, orderByComparator, true);

			array[1] = challengeEvaluation;

			array[2] = getByGroupId_PrevAndNext(session, challengeEvaluation,
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

	protected ChallengeEvaluation getByGroupId_PrevAndNext(Session session,
		ChallengeEvaluation challengeEvaluation, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATION_WHERE);

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
			query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge evaluations that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenge evaluations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluations that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @return the range of matching challenge evaluations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> filterFindByGroupId(long groupId,
		int start, int end) throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluations that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> filterFindByGroupId(long groupId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluation.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeEvaluationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeEvaluationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<ChallengeEvaluation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the challenge evaluations before and after the current challenge evaluation in the ordered set of challenge evaluations that the user has permission to view where groupId = &#63;.
	 *
	 * @param challengeEvaluationId the primary key of the current challenge evaluation
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation[] filterFindByGroupId_PrevAndNext(
		long challengeEvaluationId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(challengeEvaluationId, groupId,
				orderByComparator);
		}

		ChallengeEvaluation challengeEvaluation = findByPrimaryKey(challengeEvaluationId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluation[] array = new ChallengeEvaluationImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session,
					challengeEvaluation, groupId, orderByComparator, true);

			array[1] = challengeEvaluation;

			array[2] = filterGetByGroupId_PrevAndNext(session,
					challengeEvaluation, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChallengeEvaluation filterGetByGroupId_PrevAndNext(
		Session session, ChallengeEvaluation challengeEvaluation, long groupId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluation.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeEvaluationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeEvaluationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluations where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (ChallengeEvaluation challengeEvaluation : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluation);
		}
	}

	/**
	 * Returns the number of challenge evaluations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenge evaluations
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATION_WHERE);

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
	 * Returns the number of challenge evaluations that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenge evaluations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CHALLENGEEVALUATION_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluation.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "challengeEvaluation.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndChallengeTeamId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndChallengeTeamId",
			new String[] { Long.class.getName(), Long.class.getName() },
			ChallengeEvaluationModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeEvaluationModelImpl.CHALLENGETEAMID_COLUMN_BITMASK |
			ChallengeEvaluationModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMID =
		new FinderPath(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndChallengeTeamId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge evaluations where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId) throws SystemException {
		return findByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluations where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @return the range of matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws SystemException {
		return findByGroupIdAndChallengeTeamId(groupId, challengeTeamId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluations where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findByGroupIdAndChallengeTeamId(
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

		List<ChallengeEvaluation> list = (List<ChallengeEvaluation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluation challengeEvaluation : list) {
				if ((groupId != challengeEvaluation.getGroupId()) ||
						(challengeTeamId != challengeEvaluation.getChallengeTeamId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATION_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeEvaluation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluation>(list);
				}
				else {
					list = (List<ChallengeEvaluation>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = fetchByGroupIdAndChallengeTeamId_First(groupId,
				challengeTeamId, orderByComparator);

		if (challengeEvaluation != null) {
			return challengeEvaluation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeTeamId=");
		msg.append(challengeTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByGroupIdAndChallengeTeamId_First(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChallengeEvaluation> list = findByGroupIdAndChallengeTeamId(groupId,
				challengeTeamId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = fetchByGroupIdAndChallengeTeamId_Last(groupId,
				challengeTeamId, orderByComparator);

		if (challengeEvaluation != null) {
			return challengeEvaluation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", challengeTeamId=");
		msg.append(challengeTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByGroupIdAndChallengeTeamId_Last(
		long groupId, long challengeTeamId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluation> list = findByGroupIdAndChallengeTeamId(groupId,
				challengeTeamId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluations before and after the current challenge evaluation in the ordered set where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param challengeEvaluationId the primary key of the current challenge evaluation
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation[] findByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeEvaluationId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = findByPrimaryKey(challengeEvaluationId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluation[] array = new ChallengeEvaluationImpl[3];

			array[0] = getByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeEvaluation, groupId, challengeTeamId,
					orderByComparator, true);

			array[1] = challengeEvaluation;

			array[2] = getByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeEvaluation, groupId, challengeTeamId,
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

	protected ChallengeEvaluation getByGroupIdAndChallengeTeamId_PrevAndNext(
		Session session, ChallengeEvaluation challengeEvaluation, long groupId,
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

		query.append(_SQL_SELECT_CHALLENGEEVALUATION_WHERE);

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
			query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge evaluations that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the matching challenge evaluations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId) throws SystemException {
		return filterFindByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluations that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @return the range of matching challenge evaluations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> filterFindByGroupIdAndChallengeTeamId(
		long groupId, long challengeTeamId, int start, int end)
		throws SystemException {
		return filterFindByGroupIdAndChallengeTeamId(groupId, challengeTeamId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluations that the user has permissions to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> filterFindByGroupIdAndChallengeTeamId(
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluation.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeEvaluationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ChallengeEvaluationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(challengeTeamId);

			return (List<ChallengeEvaluation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the challenge evaluations before and after the current challenge evaluation in the ordered set of challenge evaluations that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param challengeEvaluationId the primary key of the current challenge evaluation
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation[] filterFindByGroupIdAndChallengeTeamId_PrevAndNext(
		long challengeEvaluationId, long groupId, long challengeTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChallengeTeamId_PrevAndNext(challengeEvaluationId,
				groupId, challengeTeamId, orderByComparator);
		}

		ChallengeEvaluation challengeEvaluation = findByPrimaryKey(challengeEvaluationId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluation[] array = new ChallengeEvaluationImpl[3];

			array[0] = filterGetByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeEvaluation, groupId, challengeTeamId,
					orderByComparator, true);

			array[1] = challengeEvaluation;

			array[2] = filterGetByGroupIdAndChallengeTeamId_PrevAndNext(session,
					challengeEvaluation, groupId, challengeTeamId,
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

	protected ChallengeEvaluation filterGetByGroupIdAndChallengeTeamId_PrevAndNext(
		Session session, ChallengeEvaluation challengeEvaluation, long groupId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluation.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ChallengeEvaluationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ChallengeEvaluationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(challengeTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluations where groupId = &#63; and challengeTeamId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId) throws SystemException {
		for (ChallengeEvaluation challengeEvaluation : findByGroupIdAndChallengeTeamId(
				groupId, challengeTeamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(challengeEvaluation);
		}
	}

	/**
	 * Returns the number of challenge evaluations where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the number of matching challenge evaluations
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATION_WHERE);

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
	 * Returns the number of challenge evaluations that the user has permission to view where groupId = &#63; and challengeTeamId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param challengeTeamId the challenge team ID
	 * @return the number of matching challenge evaluations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndChallengeTeamId(long groupId,
		long challengeTeamId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGEEVALUATION_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluation.class.getName(),
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
		"challengeEvaluation.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHALLENGETEAMID_CHALLENGETEAMID_2 =
		"challengeEvaluation.challengeTeamId = ?";

	public ChallengeEvaluationPersistenceImpl() {
		setModelClass(ChallengeEvaluation.class);
	}

	/**
	 * Caches the challenge evaluation in the entity cache if it is enabled.
	 *
	 * @param challengeEvaluation the challenge evaluation
	 */
	@Override
	public void cacheResult(ChallengeEvaluation challengeEvaluation) {
		EntityCacheUtil.putResult(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationImpl.class, challengeEvaluation.getPrimaryKey(),
			challengeEvaluation);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				challengeEvaluation.getUuid(), challengeEvaluation.getGroupId()
			}, challengeEvaluation);

		challengeEvaluation.resetOriginalValues();
	}

	/**
	 * Caches the challenge evaluations in the entity cache if it is enabled.
	 *
	 * @param challengeEvaluations the challenge evaluations
	 */
	@Override
	public void cacheResult(List<ChallengeEvaluation> challengeEvaluations) {
		for (ChallengeEvaluation challengeEvaluation : challengeEvaluations) {
			if (EntityCacheUtil.getResult(
						ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeEvaluationImpl.class,
						challengeEvaluation.getPrimaryKey()) == null) {
				cacheResult(challengeEvaluation);
			}
			else {
				challengeEvaluation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all challenge evaluations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChallengeEvaluationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChallengeEvaluationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the challenge evaluation.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChallengeEvaluation challengeEvaluation) {
		EntityCacheUtil.removeResult(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationImpl.class, challengeEvaluation.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(challengeEvaluation);
	}

	@Override
	public void clearCache(List<ChallengeEvaluation> challengeEvaluations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChallengeEvaluation challengeEvaluation : challengeEvaluations) {
			EntityCacheUtil.removeResult(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeEvaluationImpl.class,
				challengeEvaluation.getPrimaryKey());

			clearUniqueFindersCache(challengeEvaluation);
		}
	}

	protected void cacheUniqueFindersCache(
		ChallengeEvaluation challengeEvaluation) {
		if (challengeEvaluation.isNew()) {
			Object[] args = new Object[] {
					challengeEvaluation.getUuid(),
					challengeEvaluation.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				challengeEvaluation);
		}
		else {
			ChallengeEvaluationModelImpl challengeEvaluationModelImpl = (ChallengeEvaluationModelImpl)challengeEvaluation;

			if ((challengeEvaluationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluation.getUuid(),
						challengeEvaluation.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					challengeEvaluation);
			}
		}
	}

	protected void clearUniqueFindersCache(
		ChallengeEvaluation challengeEvaluation) {
		ChallengeEvaluationModelImpl challengeEvaluationModelImpl = (ChallengeEvaluationModelImpl)challengeEvaluation;

		Object[] args = new Object[] {
				challengeEvaluation.getUuid(), challengeEvaluation.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((challengeEvaluationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					challengeEvaluationModelImpl.getOriginalUuid(),
					challengeEvaluationModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new challenge evaluation with the primary key. Does not add the challenge evaluation to the database.
	 *
	 * @param challengeEvaluationId the primary key for the new challenge evaluation
	 * @return the new challenge evaluation
	 */
	@Override
	public ChallengeEvaluation create(long challengeEvaluationId) {
		ChallengeEvaluation challengeEvaluation = new ChallengeEvaluationImpl();

		challengeEvaluation.setNew(true);
		challengeEvaluation.setPrimaryKey(challengeEvaluationId);

		String uuid = PortalUUIDUtil.generate();

		challengeEvaluation.setUuid(uuid);

		return challengeEvaluation;
	}

	/**
	 * Removes the challenge evaluation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param challengeEvaluationId the primary key of the challenge evaluation
	 * @return the challenge evaluation that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation remove(long challengeEvaluationId)
		throws NoSuchChallengeEvaluationException, SystemException {
		return remove((Serializable)challengeEvaluationId);
	}

	/**
	 * Removes the challenge evaluation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the challenge evaluation
	 * @return the challenge evaluation that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation remove(Serializable primaryKey)
		throws NoSuchChallengeEvaluationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluation challengeEvaluation = (ChallengeEvaluation)session.get(ChallengeEvaluationImpl.class,
					primaryKey);

			if (challengeEvaluation == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChallengeEvaluationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(challengeEvaluation);
		}
		catch (NoSuchChallengeEvaluationException nsee) {
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
	protected ChallengeEvaluation removeImpl(
		ChallengeEvaluation challengeEvaluation) throws SystemException {
		challengeEvaluation = toUnwrappedModel(challengeEvaluation);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(challengeEvaluation)) {
				challengeEvaluation = (ChallengeEvaluation)session.get(ChallengeEvaluationImpl.class,
						challengeEvaluation.getPrimaryKeyObj());
			}

			if (challengeEvaluation != null) {
				session.delete(challengeEvaluation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (challengeEvaluation != null) {
			clearCache(challengeEvaluation);
		}

		return challengeEvaluation;
	}

	@Override
	public ChallengeEvaluation updateImpl(
		kisti.edison.challenge.model.ChallengeEvaluation challengeEvaluation)
		throws SystemException {
		challengeEvaluation = toUnwrappedModel(challengeEvaluation);

		boolean isNew = challengeEvaluation.isNew();

		ChallengeEvaluationModelImpl challengeEvaluationModelImpl = (ChallengeEvaluationModelImpl)challengeEvaluation;

		if (Validator.isNull(challengeEvaluation.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			challengeEvaluation.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (challengeEvaluation.isNew()) {
				session.save(challengeEvaluation);

				challengeEvaluation.setNew(false);
			}
			else {
				session.merge(challengeEvaluation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ChallengeEvaluationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((challengeEvaluationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { challengeEvaluationModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((challengeEvaluationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationModelImpl.getOriginalUuid(),
						challengeEvaluationModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						challengeEvaluationModelImpl.getUuid(),
						challengeEvaluationModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((challengeEvaluationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { challengeEvaluationModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((challengeEvaluationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationModelImpl.getOriginalGroupId(),
						challengeEvaluationModelImpl.getOriginalChallengeTeamId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID,
					args);

				args = new Object[] {
						challengeEvaluationModelImpl.getGroupId(),
						challengeEvaluationModelImpl.getChallengeTeamId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHALLENGETEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHALLENGETEAMID,
					args);
			}
		}

		EntityCacheUtil.putResult(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationImpl.class, challengeEvaluation.getPrimaryKey(),
			challengeEvaluation);

		clearUniqueFindersCache(challengeEvaluation);
		cacheUniqueFindersCache(challengeEvaluation);

		return challengeEvaluation;
	}

	protected ChallengeEvaluation toUnwrappedModel(
		ChallengeEvaluation challengeEvaluation) {
		if (challengeEvaluation instanceof ChallengeEvaluationImpl) {
			return challengeEvaluation;
		}

		ChallengeEvaluationImpl challengeEvaluationImpl = new ChallengeEvaluationImpl();

		challengeEvaluationImpl.setNew(challengeEvaluation.isNew());
		challengeEvaluationImpl.setPrimaryKey(challengeEvaluation.getPrimaryKey());

		challengeEvaluationImpl.setUuid(challengeEvaluation.getUuid());
		challengeEvaluationImpl.setChallengeEvaluationId(challengeEvaluation.getChallengeEvaluationId());
		challengeEvaluationImpl.setGroupId(challengeEvaluation.getGroupId());
		challengeEvaluationImpl.setCompanyId(challengeEvaluation.getCompanyId());
		challengeEvaluationImpl.setUserId(challengeEvaluation.getUserId());
		challengeEvaluationImpl.setUserName(challengeEvaluation.getUserName());
		challengeEvaluationImpl.setCreateDate(challengeEvaluation.getCreateDate());
		challengeEvaluationImpl.setModifiedDate(challengeEvaluation.getModifiedDate());
		challengeEvaluationImpl.setStatus(challengeEvaluation.getStatus());
		challengeEvaluationImpl.setStatusByUserId(challengeEvaluation.getStatusByUserId());
		challengeEvaluationImpl.setStatusByUserName(challengeEvaluation.getStatusByUserName());
		challengeEvaluationImpl.setStatusDate(challengeEvaluation.getStatusDate());
		challengeEvaluationImpl.setAssessmentItem(challengeEvaluation.getAssessmentItem());
		challengeEvaluationImpl.setDistribution(challengeEvaluation.getDistribution());
		challengeEvaluationImpl.setScore(challengeEvaluation.getScore());
		challengeEvaluationImpl.setChallengeTeamId(challengeEvaluation.getChallengeTeamId());

		return challengeEvaluationImpl;
	}

	/**
	 * Returns the challenge evaluation with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge evaluation
	 * @return the challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChallengeEvaluationException, SystemException {
		ChallengeEvaluation challengeEvaluation = fetchByPrimaryKey(primaryKey);

		if (challengeEvaluation == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChallengeEvaluationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return challengeEvaluation;
	}

	/**
	 * Returns the challenge evaluation with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationException} if it could not be found.
	 *
	 * @param challengeEvaluationId the primary key of the challenge evaluation
	 * @return the challenge evaluation
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationException if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation findByPrimaryKey(long challengeEvaluationId)
		throws NoSuchChallengeEvaluationException, SystemException {
		return findByPrimaryKey((Serializable)challengeEvaluationId);
	}

	/**
	 * Returns the challenge evaluation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge evaluation
	 * @return the challenge evaluation, or <code>null</code> if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ChallengeEvaluation challengeEvaluation = (ChallengeEvaluation)EntityCacheUtil.getResult(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeEvaluationImpl.class, primaryKey);

		if (challengeEvaluation == _nullChallengeEvaluation) {
			return null;
		}

		if (challengeEvaluation == null) {
			Session session = null;

			try {
				session = openSession();

				challengeEvaluation = (ChallengeEvaluation)session.get(ChallengeEvaluationImpl.class,
						primaryKey);

				if (challengeEvaluation != null) {
					cacheResult(challengeEvaluation);
				}
				else {
					EntityCacheUtil.putResult(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeEvaluationImpl.class, primaryKey,
						_nullChallengeEvaluation);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChallengeEvaluationModelImpl.ENTITY_CACHE_ENABLED,
					ChallengeEvaluationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return challengeEvaluation;
	}

	/**
	 * Returns the challenge evaluation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param challengeEvaluationId the primary key of the challenge evaluation
	 * @return the challenge evaluation, or <code>null</code> if a challenge evaluation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluation fetchByPrimaryKey(long challengeEvaluationId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)challengeEvaluationId);
	}

	/**
	 * Returns all the challenge evaluations.
	 *
	 * @return the challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @return the range of challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenge evaluations
	 * @param end the upper bound of the range of challenge evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of challenge evaluations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluation> findAll(int start, int end,
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

		List<ChallengeEvaluation> list = (List<ChallengeEvaluation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHALLENGEEVALUATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHALLENGEEVALUATION;

				if (pagination) {
					sql = sql.concat(ChallengeEvaluationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChallengeEvaluation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluation>(list);
				}
				else {
					list = (List<ChallengeEvaluation>)QueryUtil.list(q,
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
	 * Removes all the challenge evaluations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ChallengeEvaluation challengeEvaluation : findAll()) {
			remove(challengeEvaluation);
		}
	}

	/**
	 * Returns the number of challenge evaluations.
	 *
	 * @return the number of challenge evaluations
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

				Query q = session.createQuery(_SQL_COUNT_CHALLENGEEVALUATION);

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
	 * Initializes the challenge evaluation persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.kisti.edison.challenge.model.ChallengeEvaluation")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ChallengeEvaluation>> listenersList = new ArrayList<ModelListener<ChallengeEvaluation>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ChallengeEvaluation>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ChallengeEvaluationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHALLENGEEVALUATION = "SELECT challengeEvaluation FROM ChallengeEvaluation challengeEvaluation";
	private static final String _SQL_SELECT_CHALLENGEEVALUATION_WHERE = "SELECT challengeEvaluation FROM ChallengeEvaluation challengeEvaluation WHERE ";
	private static final String _SQL_COUNT_CHALLENGEEVALUATION = "SELECT COUNT(challengeEvaluation) FROM ChallengeEvaluation challengeEvaluation";
	private static final String _SQL_COUNT_CHALLENGEEVALUATION_WHERE = "SELECT COUNT(challengeEvaluation) FROM ChallengeEvaluation challengeEvaluation WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "challengeEvaluation.challengeEvaluationId";
	private static final String _FILTER_SQL_SELECT_CHALLENGEEVALUATION_WHERE = "SELECT DISTINCT {challengeEvaluation.*} FROM challenge_ChallengeEvaluation challengeEvaluation WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGEEVALUATION_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {challenge_ChallengeEvaluation.*} FROM (SELECT DISTINCT challengeEvaluation.challengeEvaluationId FROM challenge_ChallengeEvaluation challengeEvaluation WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGEEVALUATION_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN challenge_ChallengeEvaluation ON TEMP_TABLE.challengeEvaluationId = challenge_ChallengeEvaluation.challengeEvaluationId";
	private static final String _FILTER_SQL_COUNT_CHALLENGEEVALUATION_WHERE = "SELECT COUNT(DISTINCT challengeEvaluation.challengeEvaluationId) AS COUNT_VALUE FROM challenge_ChallengeEvaluation challengeEvaluation WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "challengeEvaluation";
	private static final String _FILTER_ENTITY_TABLE = "challenge_ChallengeEvaluation";
	private static final String _ORDER_BY_ENTITY_ALIAS = "challengeEvaluation.";
	private static final String _ORDER_BY_ENTITY_TABLE = "challenge_ChallengeEvaluation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChallengeEvaluation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ChallengeEvaluation exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChallengeEvaluationPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static ChallengeEvaluation _nullChallengeEvaluation = new ChallengeEvaluationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ChallengeEvaluation> toCacheModel() {
				return _nullChallengeEvaluationCacheModel;
			}
		};

	private static CacheModel<ChallengeEvaluation> _nullChallengeEvaluationCacheModel =
		new CacheModel<ChallengeEvaluation>() {
			@Override
			public ChallengeEvaluation toEntityModel() {
				return _nullChallengeEvaluation;
			}
		};
}