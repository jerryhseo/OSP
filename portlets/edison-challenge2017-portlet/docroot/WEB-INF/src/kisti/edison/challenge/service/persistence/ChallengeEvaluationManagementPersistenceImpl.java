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

import kisti.edison.challenge.NoSuchChallengeEvaluationManagementException;
import kisti.edison.challenge.model.ChallengeEvaluationManagement;
import kisti.edison.challenge.model.impl.ChallengeEvaluationManagementImpl;
import kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the challenge evaluation management service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationManagementPersistence
 * @see ChallengeEvaluationManagementUtil
 * @generated
 */
public class ChallengeEvaluationManagementPersistenceImpl
	extends BasePersistenceImpl<ChallengeEvaluationManagement>
	implements ChallengeEvaluationManagementPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChallengeEvaluationManagementUtil} to access the challenge evaluation management persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChallengeEvaluationManagementImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ChallengeEvaluationManagementModelImpl.UUID_COLUMN_BITMASK |
			ChallengeEvaluationManagementModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] { String.class.getName() });

	/**
	 * Returns all the challenge evaluation managements where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation managements where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @return the range of matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByUuid(String uuid,
		int start, int end) throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation managements where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByUuid(String uuid,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
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

		List<ChallengeEvaluationManagement> list = (List<ChallengeEvaluationManagement>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluationManagement challengeEvaluationManagement : list) {
				if (!Validator.equals(uuid,
							challengeEvaluationManagement.getUuid())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

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
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationManagement>(list);
				}
				else {
					list = (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation management in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = fetchByUuid_First(uuid,
				orderByComparator);

		if (challengeEvaluationManagement != null) {
			return challengeEvaluationManagement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationManagementException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation management in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeEvaluationManagement> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation management in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = fetchByUuid_Last(uuid,
				orderByComparator);

		if (challengeEvaluationManagement != null) {
			return challengeEvaluationManagement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationManagementException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation management in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluationManagement> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set where uuid = &#63;.
	 *
	 * @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement[] findByUuid_PrevAndNext(
		long challengeEvaluationManagementId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = findByPrimaryKey(challengeEvaluationManagementId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationManagement[] array = new ChallengeEvaluationManagementImpl[3];

			array[0] = getByUuid_PrevAndNext(session,
					challengeEvaluationManagement, uuid, orderByComparator, true);

			array[1] = challengeEvaluationManagement;

			array[2] = getByUuid_PrevAndNext(session,
					challengeEvaluationManagement, uuid, orderByComparator,
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

	protected ChallengeEvaluationManagement getByUuid_PrevAndNext(
		Session session,
		ChallengeEvaluationManagement challengeEvaluationManagement,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

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
			query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationManagement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationManagement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluation managements where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (ChallengeEvaluationManagement challengeEvaluationManagement : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluationManagement);
		}
	}

	/**
	 * Returns the number of challenge evaluation managements where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching challenge evaluation managements
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "challengeEvaluationManagement.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "challengeEvaluationManagement.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(challengeEvaluationManagement.uuid IS NULL OR challengeEvaluationManagement.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeEvaluationManagementModelImpl.UUID_COLUMN_BITMASK |
			ChallengeEvaluationManagementModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the challenge evaluation management where uuid = &#63; and groupId = &#63; or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationManagementException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByUUID_G(String uuid, long groupId)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = fetchByUUID_G(uuid,
				groupId);

		if (challengeEvaluationManagement == null) {
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

			throw new NoSuchChallengeEvaluationManagementException(msg.toString());
		}

		return challengeEvaluationManagement;
	}

	/**
	 * Returns the challenge evaluation management where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the challenge evaluation management where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ChallengeEvaluationManagement) {
			ChallengeEvaluationManagement challengeEvaluationManagement = (ChallengeEvaluationManagement)result;

			if (!Validator.equals(uuid, challengeEvaluationManagement.getUuid()) ||
					(groupId != challengeEvaluationManagement.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

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

				List<ChallengeEvaluationManagement> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ChallengeEvaluationManagement challengeEvaluationManagement = list.get(0);

					result = challengeEvaluationManagement;

					cacheResult(challengeEvaluationManagement);

					if ((challengeEvaluationManagement.getUuid() == null) ||
							!challengeEvaluationManagement.getUuid().equals(uuid) ||
							(challengeEvaluationManagement.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, challengeEvaluationManagement);
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
			return (ChallengeEvaluationManagement)result;
		}
	}

	/**
	 * Removes the challenge evaluation management where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the challenge evaluation management that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement removeByUUID_G(String uuid,
		long groupId)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = findByUUID_G(uuid,
				groupId);

		return remove(challengeEvaluationManagement);
	}

	/**
	 * Returns the number of challenge evaluation managements where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching challenge evaluation managements
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "challengeEvaluationManagement.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "challengeEvaluationManagement.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(challengeEvaluationManagement.uuid IS NULL OR challengeEvaluationManagement.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "challengeEvaluationManagement.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ChallengeEvaluationManagementModelImpl.UUID_COLUMN_BITMASK |
			ChallengeEvaluationManagementModelImpl.COMPANYID_COLUMN_BITMASK |
			ChallengeEvaluationManagementModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge evaluation managements where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByUuid_C(String uuid,
		long companyId) throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation managements where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @return the range of matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByUuid_C(String uuid,
		long companyId, int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation managements where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByUuid_C(String uuid,
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

		List<ChallengeEvaluationManagement> list = (List<ChallengeEvaluationManagement>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluationManagement challengeEvaluationManagement : list) {
				if (!Validator.equals(uuid,
							challengeEvaluationManagement.getUuid()) ||
						(companyId != challengeEvaluationManagement.getCompanyId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

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
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationManagement>(list);
				}
				else {
					list = (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByUuid_C_First(String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (challengeEvaluationManagement != null) {
			return challengeEvaluationManagement;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationManagementException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChallengeEvaluationManagement> list = findByUuid_C(uuid,
				companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (challengeEvaluationManagement != null) {
			return challengeEvaluationManagement;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationManagementException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluationManagement> list = findByUuid_C(uuid,
				companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement[] findByUuid_C_PrevAndNext(
		long challengeEvaluationManagementId, String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = findByPrimaryKey(challengeEvaluationManagementId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationManagement[] array = new ChallengeEvaluationManagementImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					challengeEvaluationManagement, uuid, companyId,
					orderByComparator, true);

			array[1] = challengeEvaluationManagement;

			array[2] = getByUuid_C_PrevAndNext(session,
					challengeEvaluationManagement, uuid, companyId,
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

	protected ChallengeEvaluationManagement getByUuid_C_PrevAndNext(
		Session session,
		ChallengeEvaluationManagement challengeEvaluationManagement,
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

		query.append(_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

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
			query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationManagement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationManagement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluation managements where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (ChallengeEvaluationManagement challengeEvaluationManagement : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluationManagement);
		}
	}

	/**
	 * Returns the number of challenge evaluation managements where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching challenge evaluation managements
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "challengeEvaluationManagement.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "challengeEvaluationManagement.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(challengeEvaluationManagement.uuid IS NULL OR challengeEvaluationManagement.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "challengeEvaluationManagement.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ChallengeEvaluationManagementModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeEvaluationManagementModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupId", new String[] { Long.class.getName() });

	/**
	 * Returns all the challenge evaluation managements where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation managements where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @return the range of matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByGroupId(long groupId,
		int start, int end) throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation managements where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByGroupId(long groupId,
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

		List<ChallengeEvaluationManagement> list = (List<ChallengeEvaluationManagement>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluationManagement challengeEvaluationManagement : list) {
				if ((groupId != challengeEvaluationManagement.getGroupId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationManagement>(list);
				}
				else {
					list = (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation management in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = fetchByGroupId_First(groupId,
				orderByComparator);

		if (challengeEvaluationManagement != null) {
			return challengeEvaluationManagement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationManagementException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation management in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ChallengeEvaluationManagement> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation management in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (challengeEvaluationManagement != null) {
			return challengeEvaluationManagement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationManagementException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation management in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluationManagement> list = findByGroupId(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set where groupId = &#63;.
	 *
	 * @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement[] findByGroupId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = findByPrimaryKey(challengeEvaluationManagementId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationManagement[] array = new ChallengeEvaluationManagementImpl[3];

			array[0] = getByGroupId_PrevAndNext(session,
					challengeEvaluationManagement, groupId, orderByComparator,
					true);

			array[1] = challengeEvaluationManagement;

			array[2] = getByGroupId_PrevAndNext(session,
					challengeEvaluationManagement, groupId, orderByComparator,
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

	protected ChallengeEvaluationManagement getByGroupId_PrevAndNext(
		Session session,
		ChallengeEvaluationManagement challengeEvaluationManagement,
		long groupId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

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
			query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationManagement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationManagement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge evaluation managements that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching challenge evaluation managements that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation managements that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @return the range of matching challenge evaluation managements that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> filterFindByGroupId(
		long groupId, int start, int end) throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation managements that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation managements that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> filterFindByGroupId(
		long groupId, int start, int end, OrderByComparator orderByComparator)
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationManagement.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					ChallengeEvaluationManagementImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					ChallengeEvaluationManagementImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
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
	 * Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set of challenge evaluation managements that the user has permission to view where groupId = &#63;.
	 *
	 * @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement[] filterFindByGroupId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(challengeEvaluationManagementId,
				groupId, orderByComparator);
		}

		ChallengeEvaluationManagement challengeEvaluationManagement = findByPrimaryKey(challengeEvaluationManagementId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationManagement[] array = new ChallengeEvaluationManagementImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session,
					challengeEvaluationManagement, groupId, orderByComparator,
					true);

			array[1] = challengeEvaluationManagement;

			array[2] = filterGetByGroupId_PrevAndNext(session,
					challengeEvaluationManagement, groupId, orderByComparator,
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

	protected ChallengeEvaluationManagement filterGetByGroupId_PrevAndNext(
		Session session,
		ChallengeEvaluationManagement challengeEvaluationManagement,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationManagement.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS,
				ChallengeEvaluationManagementImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE,
				ChallengeEvaluationManagementImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationManagement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationManagement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluation managements where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (ChallengeEvaluationManagement challengeEvaluationManagement : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluationManagement);
		}
	}

	/**
	 * Returns the number of challenge evaluation managements where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenge evaluation managements
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

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

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
	 * Returns the number of challenge evaluation managements that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching challenge evaluation managements that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationManagement.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "challengeEvaluationManagement.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGEID =
		new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndChildChallengeId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGEID =
		new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndChildChallengeId",
			new String[] { Long.class.getName(), Long.class.getName() },
			ChallengeEvaluationManagementModelImpl.GROUPID_COLUMN_BITMASK |
			ChallengeEvaluationManagementModelImpl.CHILDCHALLENGEID_COLUMN_BITMASK |
			ChallengeEvaluationManagementModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDCHILDCHALLENGEID =
		new FinderPath(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndChildChallengeId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the challenge evaluation managements where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @return the matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId) throws SystemException {
		return findByGroupIdAndChildChallengeId(groupId, childChallengeId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation managements where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @return the range of matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end)
		throws SystemException {
		return findByGroupIdAndChildChallengeId(groupId, childChallengeId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation managements where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGEID;
			finderArgs = new Object[] { groupId, childChallengeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGEID;
			finderArgs = new Object[] {
					groupId, childChallengeId,
					
					start, end, orderByComparator
				};
		}

		List<ChallengeEvaluationManagement> list = (List<ChallengeEvaluationManagement>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ChallengeEvaluationManagement challengeEvaluationManagement : list) {
				if ((groupId != challengeEvaluationManagement.getGroupId()) ||
						(childChallengeId != challengeEvaluationManagement.getChildChallengeId())) {
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

			query.append(_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_CHILDCHALLENGEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
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
					list = (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationManagement>(list);
				}
				else {
					list = (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
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
	 * Returns the first challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByGroupIdAndChildChallengeId_First(
		long groupId, long childChallengeId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = fetchByGroupIdAndChildChallengeId_First(groupId,
				childChallengeId, orderByComparator);

		if (challengeEvaluationManagement != null) {
			return challengeEvaluationManagement;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", childChallengeId=");
		msg.append(childChallengeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationManagementException(msg.toString());
	}

	/**
	 * Returns the first challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByGroupIdAndChildChallengeId_First(
		long groupId, long childChallengeId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ChallengeEvaluationManagement> list = findByGroupIdAndChildChallengeId(groupId,
				childChallengeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByGroupIdAndChildChallengeId_Last(
		long groupId, long childChallengeId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = fetchByGroupIdAndChildChallengeId_Last(groupId,
				childChallengeId, orderByComparator);

		if (challengeEvaluationManagement != null) {
			return challengeEvaluationManagement;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", childChallengeId=");
		msg.append(childChallengeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChallengeEvaluationManagementException(msg.toString());
	}

	/**
	 * Returns the last challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByGroupIdAndChildChallengeId_Last(
		long groupId, long childChallengeId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGroupIdAndChildChallengeId(groupId, childChallengeId);

		if (count == 0) {
			return null;
		}

		List<ChallengeEvaluationManagement> list = findByGroupIdAndChildChallengeId(groupId,
				childChallengeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement[] findByGroupIdAndChildChallengeId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		long childChallengeId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = findByPrimaryKey(challengeEvaluationManagementId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationManagement[] array = new ChallengeEvaluationManagementImpl[3];

			array[0] = getByGroupIdAndChildChallengeId_PrevAndNext(session,
					challengeEvaluationManagement, groupId, childChallengeId,
					orderByComparator, true);

			array[1] = challengeEvaluationManagement;

			array[2] = getByGroupIdAndChildChallengeId_PrevAndNext(session,
					challengeEvaluationManagement, groupId, childChallengeId,
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

	protected ChallengeEvaluationManagement getByGroupIdAndChildChallengeId_PrevAndNext(
		Session session,
		ChallengeEvaluationManagement challengeEvaluationManagement,
		long groupId, long childChallengeId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_CHILDCHALLENGEID_2);

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
			query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(childChallengeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationManagement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationManagement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the challenge evaluation managements that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @return the matching challenge evaluation managements that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> filterFindByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId) throws SystemException {
		return filterFindByGroupIdAndChildChallengeId(groupId,
			childChallengeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation managements that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @return the range of matching challenge evaluation managements that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> filterFindByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end)
		throws SystemException {
		return filterFindByGroupIdAndChildChallengeId(groupId,
			childChallengeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation managements that the user has permissions to view where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching challenge evaluation managements that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> filterFindByGroupIdAndChildChallengeId(
		long groupId, long childChallengeId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChildChallengeId(groupId, childChallengeId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_CHILDCHALLENGEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationManagement.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					ChallengeEvaluationManagementImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					ChallengeEvaluationManagementImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(childChallengeId);

			return (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
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
	 * Returns the challenge evaluation managements before and after the current challenge evaluation management in the ordered set of challenge evaluation managements that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param challengeEvaluationManagementId the primary key of the current challenge evaluation management
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement[] filterFindByGroupIdAndChildChallengeId_PrevAndNext(
		long challengeEvaluationManagementId, long groupId,
		long childChallengeId, OrderByComparator orderByComparator)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdAndChildChallengeId_PrevAndNext(challengeEvaluationManagementId,
				groupId, childChallengeId, orderByComparator);
		}

		ChallengeEvaluationManagement challengeEvaluationManagement = findByPrimaryKey(challengeEvaluationManagementId);

		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationManagement[] array = new ChallengeEvaluationManagementImpl[3];

			array[0] = filterGetByGroupIdAndChildChallengeId_PrevAndNext(session,
					challengeEvaluationManagement, groupId, childChallengeId,
					orderByComparator, true);

			array[1] = challengeEvaluationManagement;

			array[2] = filterGetByGroupIdAndChildChallengeId_PrevAndNext(session,
					challengeEvaluationManagement, groupId, childChallengeId,
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

	protected ChallengeEvaluationManagement filterGetByGroupIdAndChildChallengeId_PrevAndNext(
		Session session,
		ChallengeEvaluationManagement challengeEvaluationManagement,
		long groupId, long childChallengeId,
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
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_CHILDCHALLENGEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ChallengeEvaluationManagementModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationManagement.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS,
				ChallengeEvaluationManagementImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE,
				ChallengeEvaluationManagementImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(childChallengeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(challengeEvaluationManagement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ChallengeEvaluationManagement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the challenge evaluation managements where groupId = &#63; and childChallengeId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupIdAndChildChallengeId(long groupId,
		long childChallengeId) throws SystemException {
		for (ChallengeEvaluationManagement challengeEvaluationManagement : findByGroupIdAndChildChallengeId(
				groupId, childChallengeId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(challengeEvaluationManagement);
		}
	}

	/**
	 * Returns the number of challenge evaluation managements where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @return the number of matching challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupIdAndChildChallengeId(long groupId,
		long childChallengeId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDCHILDCHALLENGEID;

		Object[] finderArgs = new Object[] { groupId, childChallengeId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_CHILDCHALLENGEID_2);

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
	 * Returns the number of challenge evaluation managements that the user has permission to view where groupId = &#63; and childChallengeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param childChallengeId the child challenge ID
	 * @return the number of matching challenge evaluation managements that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupIdAndChildChallengeId(long groupId,
		long childChallengeId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdAndChildChallengeId(groupId, childChallengeId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_CHILDCHALLENGEID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ChallengeEvaluationManagement.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_GROUPID_2 =
		"challengeEvaluationManagement.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDCHILDCHALLENGEID_CHILDCHALLENGEID_2 =
		"challengeEvaluationManagement.childChallengeId = ?";

	public ChallengeEvaluationManagementPersistenceImpl() {
		setModelClass(ChallengeEvaluationManagement.class);
	}

	/**
	 * Caches the challenge evaluation management in the entity cache if it is enabled.
	 *
	 * @param challengeEvaluationManagement the challenge evaluation management
	 */
	@Override
	public void cacheResult(
		ChallengeEvaluationManagement challengeEvaluationManagement) {
		EntityCacheUtil.putResult(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			challengeEvaluationManagement.getPrimaryKey(),
			challengeEvaluationManagement);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				challengeEvaluationManagement.getUuid(),
				challengeEvaluationManagement.getGroupId()
			}, challengeEvaluationManagement);

		challengeEvaluationManagement.resetOriginalValues();
	}

	/**
	 * Caches the challenge evaluation managements in the entity cache if it is enabled.
	 *
	 * @param challengeEvaluationManagements the challenge evaluation managements
	 */
	@Override
	public void cacheResult(
		List<ChallengeEvaluationManagement> challengeEvaluationManagements) {
		for (ChallengeEvaluationManagement challengeEvaluationManagement : challengeEvaluationManagements) {
			if (EntityCacheUtil.getResult(
						ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeEvaluationManagementImpl.class,
						challengeEvaluationManagement.getPrimaryKey()) == null) {
				cacheResult(challengeEvaluationManagement);
			}
			else {
				challengeEvaluationManagement.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all challenge evaluation managements.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChallengeEvaluationManagementImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChallengeEvaluationManagementImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the challenge evaluation management.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		ChallengeEvaluationManagement challengeEvaluationManagement) {
		EntityCacheUtil.removeResult(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			challengeEvaluationManagement.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(challengeEvaluationManagement);
	}

	@Override
	public void clearCache(
		List<ChallengeEvaluationManagement> challengeEvaluationManagements) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChallengeEvaluationManagement challengeEvaluationManagement : challengeEvaluationManagements) {
			EntityCacheUtil.removeResult(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeEvaluationManagementImpl.class,
				challengeEvaluationManagement.getPrimaryKey());

			clearUniqueFindersCache(challengeEvaluationManagement);
		}
	}

	protected void cacheUniqueFindersCache(
		ChallengeEvaluationManagement challengeEvaluationManagement) {
		if (challengeEvaluationManagement.isNew()) {
			Object[] args = new Object[] {
					challengeEvaluationManagement.getUuid(),
					challengeEvaluationManagement.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				challengeEvaluationManagement);
		}
		else {
			ChallengeEvaluationManagementModelImpl challengeEvaluationManagementModelImpl =
				(ChallengeEvaluationManagementModelImpl)challengeEvaluationManagement;

			if ((challengeEvaluationManagementModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationManagement.getUuid(),
						challengeEvaluationManagement.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					challengeEvaluationManagement);
			}
		}
	}

	protected void clearUniqueFindersCache(
		ChallengeEvaluationManagement challengeEvaluationManagement) {
		ChallengeEvaluationManagementModelImpl challengeEvaluationManagementModelImpl =
			(ChallengeEvaluationManagementModelImpl)challengeEvaluationManagement;

		Object[] args = new Object[] {
				challengeEvaluationManagement.getUuid(),
				challengeEvaluationManagement.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((challengeEvaluationManagementModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					challengeEvaluationManagementModelImpl.getOriginalUuid(),
					challengeEvaluationManagementModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new challenge evaluation management with the primary key. Does not add the challenge evaluation management to the database.
	 *
	 * @param challengeEvaluationManagementId the primary key for the new challenge evaluation management
	 * @return the new challenge evaluation management
	 */
	@Override
	public ChallengeEvaluationManagement create(
		long challengeEvaluationManagementId) {
		ChallengeEvaluationManagement challengeEvaluationManagement = new ChallengeEvaluationManagementImpl();

		challengeEvaluationManagement.setNew(true);
		challengeEvaluationManagement.setPrimaryKey(challengeEvaluationManagementId);

		String uuid = PortalUUIDUtil.generate();

		challengeEvaluationManagement.setUuid(uuid);

		return challengeEvaluationManagement;
	}

	/**
	 * Removes the challenge evaluation management with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	 * @return the challenge evaluation management that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement remove(
		long challengeEvaluationManagementId)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		return remove((Serializable)challengeEvaluationManagementId);
	}

	/**
	 * Removes the challenge evaluation management with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the challenge evaluation management
	 * @return the challenge evaluation management that was removed
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement remove(Serializable primaryKey)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ChallengeEvaluationManagement challengeEvaluationManagement = (ChallengeEvaluationManagement)session.get(ChallengeEvaluationManagementImpl.class,
					primaryKey);

			if (challengeEvaluationManagement == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChallengeEvaluationManagementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(challengeEvaluationManagement);
		}
		catch (NoSuchChallengeEvaluationManagementException nsee) {
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
	protected ChallengeEvaluationManagement removeImpl(
		ChallengeEvaluationManagement challengeEvaluationManagement)
		throws SystemException {
		challengeEvaluationManagement = toUnwrappedModel(challengeEvaluationManagement);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(challengeEvaluationManagement)) {
				challengeEvaluationManagement = (ChallengeEvaluationManagement)session.get(ChallengeEvaluationManagementImpl.class,
						challengeEvaluationManagement.getPrimaryKeyObj());
			}

			if (challengeEvaluationManagement != null) {
				session.delete(challengeEvaluationManagement);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (challengeEvaluationManagement != null) {
			clearCache(challengeEvaluationManagement);
		}

		return challengeEvaluationManagement;
	}

	@Override
	public ChallengeEvaluationManagement updateImpl(
		kisti.edison.challenge.model.ChallengeEvaluationManagement challengeEvaluationManagement)
		throws SystemException {
		challengeEvaluationManagement = toUnwrappedModel(challengeEvaluationManagement);

		boolean isNew = challengeEvaluationManagement.isNew();

		ChallengeEvaluationManagementModelImpl challengeEvaluationManagementModelImpl =
			(ChallengeEvaluationManagementModelImpl)challengeEvaluationManagement;

		if (Validator.isNull(challengeEvaluationManagement.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			challengeEvaluationManagement.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (challengeEvaluationManagement.isNew()) {
				session.save(challengeEvaluationManagement);

				challengeEvaluationManagement.setNew(false);
			}
			else {
				session.merge(challengeEvaluationManagement);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew ||
				!ChallengeEvaluationManagementModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((challengeEvaluationManagementModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationManagementModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] {
						challengeEvaluationManagementModelImpl.getUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((challengeEvaluationManagementModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationManagementModelImpl.getOriginalUuid(),
						challengeEvaluationManagementModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						challengeEvaluationManagementModelImpl.getUuid(),
						challengeEvaluationManagementModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((challengeEvaluationManagementModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationManagementModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						challengeEvaluationManagementModelImpl.getGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((challengeEvaluationManagementModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						challengeEvaluationManagementModelImpl.getOriginalGroupId(),
						challengeEvaluationManagementModelImpl.getOriginalChildChallengeId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHILDCHALLENGEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGEID,
					args);

				args = new Object[] {
						challengeEvaluationManagementModelImpl.getGroupId(),
						challengeEvaluationManagementModelImpl.getChildChallengeId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDCHILDCHALLENGEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDCHILDCHALLENGEID,
					args);
			}
		}

		EntityCacheUtil.putResult(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
			ChallengeEvaluationManagementImpl.class,
			challengeEvaluationManagement.getPrimaryKey(),
			challengeEvaluationManagement);

		clearUniqueFindersCache(challengeEvaluationManagement);
		cacheUniqueFindersCache(challengeEvaluationManagement);

		return challengeEvaluationManagement;
	}

	protected ChallengeEvaluationManagement toUnwrappedModel(
		ChallengeEvaluationManagement challengeEvaluationManagement) {
		if (challengeEvaluationManagement instanceof ChallengeEvaluationManagementImpl) {
			return challengeEvaluationManagement;
		}

		ChallengeEvaluationManagementImpl challengeEvaluationManagementImpl = new ChallengeEvaluationManagementImpl();

		challengeEvaluationManagementImpl.setNew(challengeEvaluationManagement.isNew());
		challengeEvaluationManagementImpl.setPrimaryKey(challengeEvaluationManagement.getPrimaryKey());

		challengeEvaluationManagementImpl.setUuid(challengeEvaluationManagement.getUuid());
		challengeEvaluationManagementImpl.setChallengeEvaluationManagementId(challengeEvaluationManagement.getChallengeEvaluationManagementId());
		challengeEvaluationManagementImpl.setGroupId(challengeEvaluationManagement.getGroupId());
		challengeEvaluationManagementImpl.setCompanyId(challengeEvaluationManagement.getCompanyId());
		challengeEvaluationManagementImpl.setUserId(challengeEvaluationManagement.getUserId());
		challengeEvaluationManagementImpl.setUserName(challengeEvaluationManagement.getUserName());
		challengeEvaluationManagementImpl.setCreateDate(challengeEvaluationManagement.getCreateDate());
		challengeEvaluationManagementImpl.setModifiedDate(challengeEvaluationManagement.getModifiedDate());
		challengeEvaluationManagementImpl.setStatus(challengeEvaluationManagement.getStatus());
		challengeEvaluationManagementImpl.setStatusByUserId(challengeEvaluationManagement.getStatusByUserId());
		challengeEvaluationManagementImpl.setStatusByUserName(challengeEvaluationManagement.getStatusByUserName());
		challengeEvaluationManagementImpl.setStatusDate(challengeEvaluationManagement.getStatusDate());
		challengeEvaluationManagementImpl.setAssessmentItem(challengeEvaluationManagement.getAssessmentItem());
		challengeEvaluationManagementImpl.setDistribution(challengeEvaluationManagement.getDistribution());
		challengeEvaluationManagementImpl.setChildChallengeId(challengeEvaluationManagement.getChildChallengeId());

		return challengeEvaluationManagementImpl;
	}

	/**
	 * Returns the challenge evaluation management with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge evaluation management
	 * @return the challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByPrimaryKey(
		Serializable primaryKey)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = fetchByPrimaryKey(primaryKey);

		if (challengeEvaluationManagement == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChallengeEvaluationManagementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return challengeEvaluationManagement;
	}

	/**
	 * Returns the challenge evaluation management with the primary key or throws a {@link kisti.edison.challenge.NoSuchChallengeEvaluationManagementException} if it could not be found.
	 *
	 * @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	 * @return the challenge evaluation management
	 * @throws kisti.edison.challenge.NoSuchChallengeEvaluationManagementException if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement findByPrimaryKey(
		long challengeEvaluationManagementId)
		throws NoSuchChallengeEvaluationManagementException, SystemException {
		return findByPrimaryKey((Serializable)challengeEvaluationManagementId);
	}

	/**
	 * Returns the challenge evaluation management with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the challenge evaluation management
	 * @return the challenge evaluation management, or <code>null</code> if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByPrimaryKey(
		Serializable primaryKey) throws SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = (ChallengeEvaluationManagement)EntityCacheUtil.getResult(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
				ChallengeEvaluationManagementImpl.class, primaryKey);

		if (challengeEvaluationManagement == _nullChallengeEvaluationManagement) {
			return null;
		}

		if (challengeEvaluationManagement == null) {
			Session session = null;

			try {
				session = openSession();

				challengeEvaluationManagement = (ChallengeEvaluationManagement)session.get(ChallengeEvaluationManagementImpl.class,
						primaryKey);

				if (challengeEvaluationManagement != null) {
					cacheResult(challengeEvaluationManagement);
				}
				else {
					EntityCacheUtil.putResult(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
						ChallengeEvaluationManagementImpl.class, primaryKey,
						_nullChallengeEvaluationManagement);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChallengeEvaluationManagementModelImpl.ENTITY_CACHE_ENABLED,
					ChallengeEvaluationManagementImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return challengeEvaluationManagement;
	}

	/**
	 * Returns the challenge evaluation management with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	 * @return the challenge evaluation management, or <code>null</code> if a challenge evaluation management with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ChallengeEvaluationManagement fetchByPrimaryKey(
		long challengeEvaluationManagementId) throws SystemException {
		return fetchByPrimaryKey((Serializable)challengeEvaluationManagementId);
	}

	/**
	 * Returns all the challenge evaluation managements.
	 *
	 * @return the challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findAll()
		throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the challenge evaluation managements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @return the range of challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the challenge evaluation managements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of challenge evaluation managements
	 * @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of challenge evaluation managements
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ChallengeEvaluationManagement> findAll(int start, int end,
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

		List<ChallengeEvaluationManagement> list = (List<ChallengeEvaluationManagement>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT;

				if (pagination) {
					sql = sql.concat(ChallengeEvaluationManagementModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ChallengeEvaluationManagement>(list);
				}
				else {
					list = (List<ChallengeEvaluationManagement>)QueryUtil.list(q,
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
	 * Removes all the challenge evaluation managements from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ChallengeEvaluationManagement challengeEvaluationManagement : findAll()) {
			remove(challengeEvaluationManagement);
		}
	}

	/**
	 * Returns the number of challenge evaluation managements.
	 *
	 * @return the number of challenge evaluation managements
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

				Query q = session.createQuery(_SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT);

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
	 * Initializes the challenge evaluation management persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.kisti.edison.challenge.model.ChallengeEvaluationManagement")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ChallengeEvaluationManagement>> listenersList =
					new ArrayList<ModelListener<ChallengeEvaluationManagement>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ChallengeEvaluationManagement>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ChallengeEvaluationManagementImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT = "SELECT challengeEvaluationManagement FROM ChallengeEvaluationManagement challengeEvaluationManagement";
	private static final String _SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE = "SELECT challengeEvaluationManagement FROM ChallengeEvaluationManagement challengeEvaluationManagement WHERE ";
	private static final String _SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT = "SELECT COUNT(challengeEvaluationManagement) FROM ChallengeEvaluationManagement challengeEvaluationManagement";
	private static final String _SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT_WHERE = "SELECT COUNT(challengeEvaluationManagement) FROM ChallengeEvaluationManagement challengeEvaluationManagement WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "challengeEvaluationManagement.challengeEvaluationManagementId";
	private static final String _FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_WHERE =
		"SELECT DISTINCT {challengeEvaluationManagement.*} FROM challenge_ChallengeEvaluationManagement challengeEvaluationManagement WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {challenge_ChallengeEvaluationManagement.*} FROM (SELECT DISTINCT challengeEvaluationManagement.challengeEvaluationManagementId FROM challenge_ChallengeEvaluationManagement challengeEvaluationManagement WHERE ";
	private static final String _FILTER_SQL_SELECT_CHALLENGEEVALUATIONMANAGEMENT_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN challenge_ChallengeEvaluationManagement ON TEMP_TABLE.challengeEvaluationManagementId = challenge_ChallengeEvaluationManagement.challengeEvaluationManagementId";
	private static final String _FILTER_SQL_COUNT_CHALLENGEEVALUATIONMANAGEMENT_WHERE =
		"SELECT COUNT(DISTINCT challengeEvaluationManagement.challengeEvaluationManagementId) AS COUNT_VALUE FROM challenge_ChallengeEvaluationManagement challengeEvaluationManagement WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "challengeEvaluationManagement";
	private static final String _FILTER_ENTITY_TABLE = "challenge_ChallengeEvaluationManagement";
	private static final String _ORDER_BY_ENTITY_ALIAS = "challengeEvaluationManagement.";
	private static final String _ORDER_BY_ENTITY_TABLE = "challenge_ChallengeEvaluationManagement.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChallengeEvaluationManagement exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ChallengeEvaluationManagement exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChallengeEvaluationManagementPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static ChallengeEvaluationManagement _nullChallengeEvaluationManagement =
		new ChallengeEvaluationManagementImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ChallengeEvaluationManagement> toCacheModel() {
				return _nullChallengeEvaluationManagementCacheModel;
			}
		};

	private static CacheModel<ChallengeEvaluationManagement> _nullChallengeEvaluationManagementCacheModel =
		new CacheModel<ChallengeEvaluationManagement>() {
			@Override
			public ChallengeEvaluationManagement toEntityModel() {
				return _nullChallengeEvaluationManagement;
			}
		};
}