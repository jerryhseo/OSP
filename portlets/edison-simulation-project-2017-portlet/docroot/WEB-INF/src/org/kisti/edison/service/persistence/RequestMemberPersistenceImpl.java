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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.kisti.edison.NoSuchRequestMemberException;
import org.kisti.edison.model.RequestMember;
import org.kisti.edison.model.impl.RequestMemberImpl;
import org.kisti.edison.model.impl.RequestMemberModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the request member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see RequestMemberPersistence
 * @see RequestMemberUtil
 * @generated
 */
public class RequestMemberPersistenceImpl extends BasePersistenceImpl<RequestMember>
	implements RequestMemberPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RequestMemberUtil} to access the request member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RequestMemberImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberModelImpl.FINDER_CACHE_ENABLED,
			RequestMemberImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberModelImpl.FINDER_CACHE_ENABLED,
			RequestMemberImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONPROJECTID =
		new FinderPath(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberModelImpl.FINDER_CACHE_ENABLED,
			RequestMemberImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySimulationProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTID =
		new FinderPath(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberModelImpl.FINDER_CACHE_ENABLED,
			RequestMemberImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySimulationProjectId", new String[] { Long.class.getName() },
			RequestMemberModelImpl.SIMULATIONPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONPROJECTID = new FinderPath(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySimulationProjectId", new String[] { Long.class.getName() });

	/**
	 * Returns all the request members where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @return the matching request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RequestMember> findBySimulationProjectId(
		long simulationProjectId) throws SystemException {
		return findBySimulationProjectId(simulationProjectId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the request members where simulationProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param start the lower bound of the range of request members
	 * @param end the upper bound of the range of request members (not inclusive)
	 * @return the range of matching request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RequestMember> findBySimulationProjectId(
		long simulationProjectId, int start, int end) throws SystemException {
		return findBySimulationProjectId(simulationProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the request members where simulationProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param start the lower bound of the range of request members
	 * @param end the upper bound of the range of request members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RequestMember> findBySimulationProjectId(
		long simulationProjectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTID;
			finderArgs = new Object[] { simulationProjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONPROJECTID;
			finderArgs = new Object[] {
					simulationProjectId,
					
					start, end, orderByComparator
				};
		}

		List<RequestMember> list = (List<RequestMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (RequestMember requestMember : list) {
				if ((simulationProjectId != requestMember.getSimulationProjectId())) {
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

			query.append(_SQL_SELECT_REQUESTMEMBER_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONPROJECTID_SIMULATIONPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RequestMemberModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationProjectId);

				if (!pagination) {
					list = (List<RequestMember>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<RequestMember>(list);
				}
				else {
					list = (List<RequestMember>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first request member in the ordered set where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request member
	 * @throws org.kisti.edison.NoSuchRequestMemberException if a matching request member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember findBySimulationProjectId_First(
		long simulationProjectId, OrderByComparator orderByComparator)
		throws NoSuchRequestMemberException, SystemException {
		RequestMember requestMember = fetchBySimulationProjectId_First(simulationProjectId,
				orderByComparator);

		if (requestMember != null) {
			return requestMember;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationProjectId=");
		msg.append(simulationProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestMemberException(msg.toString());
	}

	/**
	 * Returns the first request member in the ordered set where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request member, or <code>null</code> if a matching request member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember fetchBySimulationProjectId_First(
		long simulationProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		List<RequestMember> list = findBySimulationProjectId(simulationProjectId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last request member in the ordered set where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request member
	 * @throws org.kisti.edison.NoSuchRequestMemberException if a matching request member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember findBySimulationProjectId_Last(
		long simulationProjectId, OrderByComparator orderByComparator)
		throws NoSuchRequestMemberException, SystemException {
		RequestMember requestMember = fetchBySimulationProjectId_Last(simulationProjectId,
				orderByComparator);

		if (requestMember != null) {
			return requestMember;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationProjectId=");
		msg.append(simulationProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestMemberException(msg.toString());
	}

	/**
	 * Returns the last request member in the ordered set where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request member, or <code>null</code> if a matching request member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember fetchBySimulationProjectId_Last(
		long simulationProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBySimulationProjectId(simulationProjectId);

		if (count == 0) {
			return null;
		}

		List<RequestMember> list = findBySimulationProjectId(simulationProjectId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the request members before and after the current request member in the ordered set where simulationProjectId = &#63;.
	 *
	 * @param requestMemberPK the primary key of the current request member
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next request member
	 * @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember[] findBySimulationProjectId_PrevAndNext(
		RequestMemberPK requestMemberPK, long simulationProjectId,
		OrderByComparator orderByComparator)
		throws NoSuchRequestMemberException, SystemException {
		RequestMember requestMember = findByPrimaryKey(requestMemberPK);

		Session session = null;

		try {
			session = openSession();

			RequestMember[] array = new RequestMemberImpl[3];

			array[0] = getBySimulationProjectId_PrevAndNext(session,
					requestMember, simulationProjectId, orderByComparator, true);

			array[1] = requestMember;

			array[2] = getBySimulationProjectId_PrevAndNext(session,
					requestMember, simulationProjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RequestMember getBySimulationProjectId_PrevAndNext(
		Session session, RequestMember requestMember, long simulationProjectId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REQUESTMEMBER_WHERE);

		query.append(_FINDER_COLUMN_SIMULATIONPROJECTID_SIMULATIONPROJECTID_2);

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
			query.append(RequestMemberModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(simulationProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(requestMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RequestMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the request members where simulationProjectId = &#63; from the database.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBySimulationProjectId(long simulationProjectId)
		throws SystemException {
		for (RequestMember requestMember : findBySimulationProjectId(
				simulationProjectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(requestMember);
		}
	}

	/**
	 * Returns the number of request members where simulationProjectId = &#63;.
	 *
	 * @param simulationProjectId the simulation project ID
	 * @return the number of matching request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySimulationProjectId(long simulationProjectId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONPROJECTID;

		Object[] finderArgs = new Object[] { simulationProjectId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REQUESTMEMBER_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONPROJECTID_SIMULATIONPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationProjectId);

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

	private static final String _FINDER_COLUMN_SIMULATIONPROJECTID_SIMULATIONPROJECTID_2 =
		"requestMember.id.simulationProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONPROJECTIDANDUSEID =
		new FinderPath(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberModelImpl.FINDER_CACHE_ENABLED,
			RequestMemberImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySimulationProjectIdAndUseId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTIDANDUSEID =
		new FinderPath(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberModelImpl.FINDER_CACHE_ENABLED,
			RequestMemberImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySimulationProjectIdAndUseId",
			new String[] { Long.class.getName(), Long.class.getName() },
			RequestMemberModelImpl.USERID_COLUMN_BITMASK |
			RequestMemberModelImpl.SIMULATIONPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONPROJECTIDANDUSEID =
		new FinderPath(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySimulationProjectIdAndUseId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the request members where userId = &#63; and simulationProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param simulationProjectId the simulation project ID
	 * @return the matching request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RequestMember> findBySimulationProjectIdAndUseId(Long userId,
		long simulationProjectId) throws SystemException {
		return findBySimulationProjectIdAndUseId(userId, simulationProjectId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the request members where userId = &#63; and simulationProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param simulationProjectId the simulation project ID
	 * @param start the lower bound of the range of request members
	 * @param end the upper bound of the range of request members (not inclusive)
	 * @return the range of matching request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RequestMember> findBySimulationProjectIdAndUseId(Long userId,
		long simulationProjectId, int start, int end) throws SystemException {
		return findBySimulationProjectIdAndUseId(userId, simulationProjectId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the request members where userId = &#63; and simulationProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param simulationProjectId the simulation project ID
	 * @param start the lower bound of the range of request members
	 * @param end the upper bound of the range of request members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RequestMember> findBySimulationProjectIdAndUseId(Long userId,
		long simulationProjectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTIDANDUSEID;
			finderArgs = new Object[] { userId, simulationProjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONPROJECTIDANDUSEID;
			finderArgs = new Object[] {
					userId, simulationProjectId,
					
					start, end, orderByComparator
				};
		}

		List<RequestMember> list = (List<RequestMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (RequestMember requestMember : list) {
				if (!Validator.equals(userId, requestMember.getUserId()) ||
						(simulationProjectId != requestMember.getSimulationProjectId())) {
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

			query.append(_SQL_SELECT_REQUESTMEMBER_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONPROJECTIDANDUSEID_USERID_2);

			query.append(_FINDER_COLUMN_SIMULATIONPROJECTIDANDUSEID_SIMULATIONPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RequestMemberModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId.longValue());

				qPos.add(simulationProjectId);

				if (!pagination) {
					list = (List<RequestMember>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<RequestMember>(list);
				}
				else {
					list = (List<RequestMember>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request member
	 * @throws org.kisti.edison.NoSuchRequestMemberException if a matching request member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember findBySimulationProjectIdAndUseId_First(Long userId,
		long simulationProjectId, OrderByComparator orderByComparator)
		throws NoSuchRequestMemberException, SystemException {
		RequestMember requestMember = fetchBySimulationProjectIdAndUseId_First(userId,
				simulationProjectId, orderByComparator);

		if (requestMember != null) {
			return requestMember;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", simulationProjectId=");
		msg.append(simulationProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestMemberException(msg.toString());
	}

	/**
	 * Returns the first request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching request member, or <code>null</code> if a matching request member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember fetchBySimulationProjectIdAndUseId_First(Long userId,
		long simulationProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		List<RequestMember> list = findBySimulationProjectIdAndUseId(userId,
				simulationProjectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request member
	 * @throws org.kisti.edison.NoSuchRequestMemberException if a matching request member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember findBySimulationProjectIdAndUseId_Last(Long userId,
		long simulationProjectId, OrderByComparator orderByComparator)
		throws NoSuchRequestMemberException, SystemException {
		RequestMember requestMember = fetchBySimulationProjectIdAndUseId_Last(userId,
				simulationProjectId, orderByComparator);

		if (requestMember != null) {
			return requestMember;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", simulationProjectId=");
		msg.append(simulationProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRequestMemberException(msg.toString());
	}

	/**
	 * Returns the last request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching request member, or <code>null</code> if a matching request member could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember fetchBySimulationProjectIdAndUseId_Last(Long userId,
		long simulationProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBySimulationProjectIdAndUseId(userId,
				simulationProjectId);

		if (count == 0) {
			return null;
		}

		List<RequestMember> list = findBySimulationProjectIdAndUseId(userId,
				simulationProjectId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the request members before and after the current request member in the ordered set where userId = &#63; and simulationProjectId = &#63;.
	 *
	 * @param requestMemberPK the primary key of the current request member
	 * @param userId the user ID
	 * @param simulationProjectId the simulation project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next request member
	 * @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember[] findBySimulationProjectIdAndUseId_PrevAndNext(
		RequestMemberPK requestMemberPK, Long userId, long simulationProjectId,
		OrderByComparator orderByComparator)
		throws NoSuchRequestMemberException, SystemException {
		RequestMember requestMember = findByPrimaryKey(requestMemberPK);

		Session session = null;

		try {
			session = openSession();

			RequestMember[] array = new RequestMemberImpl[3];

			array[0] = getBySimulationProjectIdAndUseId_PrevAndNext(session,
					requestMember, userId, simulationProjectId,
					orderByComparator, true);

			array[1] = requestMember;

			array[2] = getBySimulationProjectIdAndUseId_PrevAndNext(session,
					requestMember, userId, simulationProjectId,
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

	protected RequestMember getBySimulationProjectIdAndUseId_PrevAndNext(
		Session session, RequestMember requestMember, Long userId,
		long simulationProjectId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REQUESTMEMBER_WHERE);

		query.append(_FINDER_COLUMN_SIMULATIONPROJECTIDANDUSEID_USERID_2);

		query.append(_FINDER_COLUMN_SIMULATIONPROJECTIDANDUSEID_SIMULATIONPROJECTID_2);

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
			query.append(RequestMemberModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId.longValue());

		qPos.add(simulationProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(requestMember);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RequestMember> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the request members where userId = &#63; and simulationProjectId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param simulationProjectId the simulation project ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBySimulationProjectIdAndUseId(Long userId,
		long simulationProjectId) throws SystemException {
		for (RequestMember requestMember : findBySimulationProjectIdAndUseId(
				userId, simulationProjectId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(requestMember);
		}
	}

	/**
	 * Returns the number of request members where userId = &#63; and simulationProjectId = &#63;.
	 *
	 * @param userId the user ID
	 * @param simulationProjectId the simulation project ID
	 * @return the number of matching request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySimulationProjectIdAndUseId(Long userId,
		long simulationProjectId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONPROJECTIDANDUSEID;

		Object[] finderArgs = new Object[] { userId, simulationProjectId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REQUESTMEMBER_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONPROJECTIDANDUSEID_USERID_2);

			query.append(_FINDER_COLUMN_SIMULATIONPROJECTIDANDUSEID_SIMULATIONPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId.longValue());

				qPos.add(simulationProjectId);

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

	private static final String _FINDER_COLUMN_SIMULATIONPROJECTIDANDUSEID_USERID_2 =
		"requestMember.userId = ? AND ";
	private static final String _FINDER_COLUMN_SIMULATIONPROJECTIDANDUSEID_SIMULATIONPROJECTID_2 =
		"requestMember.id.simulationProjectId = ?";

	public RequestMemberPersistenceImpl() {
		setModelClass(RequestMember.class);
	}

	/**
	 * Caches the request member in the entity cache if it is enabled.
	 *
	 * @param requestMember the request member
	 */
	@Override
	public void cacheResult(RequestMember requestMember) {
		EntityCacheUtil.putResult(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberImpl.class, requestMember.getPrimaryKey(),
			requestMember);

		requestMember.resetOriginalValues();
	}

	/**
	 * Caches the request members in the entity cache if it is enabled.
	 *
	 * @param requestMembers the request members
	 */
	@Override
	public void cacheResult(List<RequestMember> requestMembers) {
		for (RequestMember requestMember : requestMembers) {
			if (EntityCacheUtil.getResult(
						RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
						RequestMemberImpl.class, requestMember.getPrimaryKey()) == null) {
				cacheResult(requestMember);
			}
			else {
				requestMember.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all request members.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(RequestMemberImpl.class.getName());
		}

		EntityCacheUtil.clearCache(RequestMemberImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the request member.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RequestMember requestMember) {
		EntityCacheUtil.removeResult(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberImpl.class, requestMember.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<RequestMember> requestMembers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RequestMember requestMember : requestMembers) {
			EntityCacheUtil.removeResult(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
				RequestMemberImpl.class, requestMember.getPrimaryKey());
		}
	}

	/**
	 * Creates a new request member with the primary key. Does not add the request member to the database.
	 *
	 * @param requestMemberPK the primary key for the new request member
	 * @return the new request member
	 */
	@Override
	public RequestMember create(RequestMemberPK requestMemberPK) {
		RequestMember requestMember = new RequestMemberImpl();

		requestMember.setNew(true);
		requestMember.setPrimaryKey(requestMemberPK);

		return requestMember;
	}

	/**
	 * Removes the request member with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param requestMemberPK the primary key of the request member
	 * @return the request member that was removed
	 * @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember remove(RequestMemberPK requestMemberPK)
		throws NoSuchRequestMemberException, SystemException {
		return remove((Serializable)requestMemberPK);
	}

	/**
	 * Removes the request member with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the request member
	 * @return the request member that was removed
	 * @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember remove(Serializable primaryKey)
		throws NoSuchRequestMemberException, SystemException {
		Session session = null;

		try {
			session = openSession();

			RequestMember requestMember = (RequestMember)session.get(RequestMemberImpl.class,
					primaryKey);

			if (requestMember == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRequestMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(requestMember);
		}
		catch (NoSuchRequestMemberException nsee) {
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
	protected RequestMember removeImpl(RequestMember requestMember)
		throws SystemException {
		requestMember = toUnwrappedModel(requestMember);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(requestMember)) {
				requestMember = (RequestMember)session.get(RequestMemberImpl.class,
						requestMember.getPrimaryKeyObj());
			}

			if (requestMember != null) {
				session.delete(requestMember);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (requestMember != null) {
			clearCache(requestMember);
		}

		return requestMember;
	}

	@Override
	public RequestMember updateImpl(
		org.kisti.edison.model.RequestMember requestMember)
		throws SystemException {
		requestMember = toUnwrappedModel(requestMember);

		boolean isNew = requestMember.isNew();

		RequestMemberModelImpl requestMemberModelImpl = (RequestMemberModelImpl)requestMember;

		Session session = null;

		try {
			session = openSession();

			if (requestMember.isNew()) {
				session.save(requestMember);

				requestMember.setNew(false);
			}
			else {
				session.merge(requestMember);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !RequestMemberModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((requestMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						requestMemberModelImpl.getOriginalSimulationProjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONPROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTID,
					args);

				args = new Object[] {
						requestMemberModelImpl.getSimulationProjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONPROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTID,
					args);
			}

			if ((requestMemberModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTIDANDUSEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						requestMemberModelImpl.getOriginalUserId(),
						requestMemberModelImpl.getOriginalSimulationProjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONPROJECTIDANDUSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTIDANDUSEID,
					args);

				args = new Object[] {
						requestMemberModelImpl.getUserId(),
						requestMemberModelImpl.getSimulationProjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONPROJECTIDANDUSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONPROJECTIDANDUSEID,
					args);
			}
		}

		EntityCacheUtil.putResult(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
			RequestMemberImpl.class, requestMember.getPrimaryKey(),
			requestMember);

		return requestMember;
	}

	protected RequestMember toUnwrappedModel(RequestMember requestMember) {
		if (requestMember instanceof RequestMemberImpl) {
			return requestMember;
		}

		RequestMemberImpl requestMemberImpl = new RequestMemberImpl();

		requestMemberImpl.setNew(requestMember.isNew());
		requestMemberImpl.setPrimaryKey(requestMember.getPrimaryKey());

		requestMemberImpl.setRequestSeq(requestMember.getRequestSeq());
		requestMemberImpl.setSimulationProjectId(requestMember.getSimulationProjectId());
		requestMemberImpl.setUserId(requestMember.getUserId());
		requestMemberImpl.setRequestDate(requestMember.getRequestDate());
		requestMemberImpl.setProcessDate(requestMember.getProcessDate());
		requestMemberImpl.setRequestState(requestMember.getRequestState());

		return requestMemberImpl;
	}

	/**
	 * Returns the request member with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the request member
	 * @return the request member
	 * @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRequestMemberException, SystemException {
		RequestMember requestMember = fetchByPrimaryKey(primaryKey);

		if (requestMember == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRequestMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return requestMember;
	}

	/**
	 * Returns the request member with the primary key or throws a {@link org.kisti.edison.NoSuchRequestMemberException} if it could not be found.
	 *
	 * @param requestMemberPK the primary key of the request member
	 * @return the request member
	 * @throws org.kisti.edison.NoSuchRequestMemberException if a request member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember findByPrimaryKey(RequestMemberPK requestMemberPK)
		throws NoSuchRequestMemberException, SystemException {
		return findByPrimaryKey((Serializable)requestMemberPK);
	}

	/**
	 * Returns the request member with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the request member
	 * @return the request member, or <code>null</code> if a request member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		RequestMember requestMember = (RequestMember)EntityCacheUtil.getResult(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
				RequestMemberImpl.class, primaryKey);

		if (requestMember == _nullRequestMember) {
			return null;
		}

		if (requestMember == null) {
			Session session = null;

			try {
				session = openSession();

				requestMember = (RequestMember)session.get(RequestMemberImpl.class,
						primaryKey);

				if (requestMember != null) {
					cacheResult(requestMember);
				}
				else {
					EntityCacheUtil.putResult(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
						RequestMemberImpl.class, primaryKey, _nullRequestMember);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(RequestMemberModelImpl.ENTITY_CACHE_ENABLED,
					RequestMemberImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return requestMember;
	}

	/**
	 * Returns the request member with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param requestMemberPK the primary key of the request member
	 * @return the request member, or <code>null</code> if a request member with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RequestMember fetchByPrimaryKey(RequestMemberPK requestMemberPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)requestMemberPK);
	}

	/**
	 * Returns all the request members.
	 *
	 * @return the request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RequestMember> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the request members.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of request members
	 * @param end the upper bound of the range of request members (not inclusive)
	 * @return the range of request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RequestMember> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the request members.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of request members
	 * @param end the upper bound of the range of request members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of request members
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RequestMember> findAll(int start, int end,
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

		List<RequestMember> list = (List<RequestMember>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_REQUESTMEMBER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REQUESTMEMBER;

				if (pagination) {
					sql = sql.concat(RequestMemberModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RequestMember>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<RequestMember>(list);
				}
				else {
					list = (List<RequestMember>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the request members from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (RequestMember requestMember : findAll()) {
			remove(requestMember);
		}
	}

	/**
	 * Returns the number of request members.
	 *
	 * @return the number of request members
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

				Query q = session.createQuery(_SQL_COUNT_REQUESTMEMBER);

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
	 * Initializes the request member persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.model.RequestMember")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<RequestMember>> listenersList = new ArrayList<ModelListener<RequestMember>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<RequestMember>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(RequestMemberImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_REQUESTMEMBER = "SELECT requestMember FROM RequestMember requestMember";
	private static final String _SQL_SELECT_REQUESTMEMBER_WHERE = "SELECT requestMember FROM RequestMember requestMember WHERE ";
	private static final String _SQL_COUNT_REQUESTMEMBER = "SELECT COUNT(requestMember) FROM RequestMember requestMember";
	private static final String _SQL_COUNT_REQUESTMEMBER_WHERE = "SELECT COUNT(requestMember) FROM RequestMember requestMember WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "requestMember.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RequestMember exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RequestMember exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(RequestMemberPersistenceImpl.class);
	private static RequestMember _nullRequestMember = new RequestMemberImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<RequestMember> toCacheModel() {
				return _nullRequestMemberCacheModel;
			}
		};

	private static CacheModel<RequestMember> _nullRequestMemberCacheModel = new CacheModel<RequestMember>() {
			@Override
			public RequestMember toEntityModel() {
				return _nullRequestMember;
			}
		};
}