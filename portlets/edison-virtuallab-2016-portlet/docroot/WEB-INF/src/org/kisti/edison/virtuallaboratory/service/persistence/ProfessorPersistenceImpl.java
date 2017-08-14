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

package org.kisti.edison.virtuallaboratory.service.persistence;

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

import org.kisti.edison.virtuallaboratory.NoSuchProfessorException;
import org.kisti.edison.virtuallaboratory.model.Professor;
import org.kisti.edison.virtuallaboratory.model.impl.ProfessorImpl;
import org.kisti.edison.virtuallaboratory.model.impl.ProfessorModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the professor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ProfessorPersistence
 * @see ProfessorUtil
 * @generated
 */
public class ProfessorPersistenceImpl extends BasePersistenceImpl<Professor>
	implements ProfessorPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProfessorUtil} to access the professor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProfessorImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
			ProfessorModelImpl.FINDER_CACHE_ENABLED, ProfessorImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
			ProfessorModelImpl.FINDER_CACHE_ENABLED, ProfessorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
			ProfessorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
			ProfessorModelImpl.FINDER_CACHE_ENABLED, ProfessorImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
			ProfessorModelImpl.FINDER_CACHE_ENABLED, ProfessorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			ProfessorModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
			ProfessorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the professors where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching professors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Professor> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the professors where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ProfessorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of professors
	 * @param end the upper bound of the range of professors (not inclusive)
	 * @return the range of matching professors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Professor> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the professors where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ProfessorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of professors
	 * @param end the upper bound of the range of professors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching professors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Professor> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<Professor> list = (List<Professor>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Professor professor : list) {
				if ((userId != professor.getUserId())) {
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

			query.append(_SQL_SELECT_PROFESSOR_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProfessorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Professor>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Professor>(list);
				}
				else {
					list = (List<Professor>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first professor in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching professor
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchProfessorException if a matching professor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchProfessorException, SystemException {
		Professor professor = fetchByUserId_First(userId, orderByComparator);

		if (professor != null) {
			return professor;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProfessorException(msg.toString());
	}

	/**
	 * Returns the first professor in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching professor, or <code>null</code> if a matching professor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Professor> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last professor in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching professor
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchProfessorException if a matching professor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchProfessorException, SystemException {
		Professor professor = fetchByUserId_Last(userId, orderByComparator);

		if (professor != null) {
			return professor;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProfessorException(msg.toString());
	}

	/**
	 * Returns the last professor in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching professor, or <code>null</code> if a matching professor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<Professor> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the professors before and after the current professor in the ordered set where userId = &#63;.
	 *
	 * @param professorSeq the primary key of the current professor
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next professor
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchProfessorException if a professor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor[] findByUserId_PrevAndNext(long professorSeq, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchProfessorException, SystemException {
		Professor professor = findByPrimaryKey(professorSeq);

		Session session = null;

		try {
			session = openSession();

			Professor[] array = new ProfessorImpl[3];

			array[0] = getByUserId_PrevAndNext(session, professor, userId,
					orderByComparator, true);

			array[1] = professor;

			array[2] = getByUserId_PrevAndNext(session, professor, userId,
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

	protected Professor getByUserId_PrevAndNext(Session session,
		Professor professor, long userId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROFESSOR_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(ProfessorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(professor);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Professor> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the professors where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId(long userId) throws SystemException {
		for (Professor professor : findByUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(professor);
		}
	}

	/**
	 * Returns the number of professors where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching professors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserId(long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROFESSOR_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "professor.userId = ?";

	public ProfessorPersistenceImpl() {
		setModelClass(Professor.class);
	}

	/**
	 * Caches the professor in the entity cache if it is enabled.
	 *
	 * @param professor the professor
	 */
	@Override
	public void cacheResult(Professor professor) {
		EntityCacheUtil.putResult(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
			ProfessorImpl.class, professor.getPrimaryKey(), professor);

		professor.resetOriginalValues();
	}

	/**
	 * Caches the professors in the entity cache if it is enabled.
	 *
	 * @param professors the professors
	 */
	@Override
	public void cacheResult(List<Professor> professors) {
		for (Professor professor : professors) {
			if (EntityCacheUtil.getResult(
						ProfessorModelImpl.ENTITY_CACHE_ENABLED,
						ProfessorImpl.class, professor.getPrimaryKey()) == null) {
				cacheResult(professor);
			}
			else {
				professor.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all professors.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ProfessorImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ProfessorImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the professor.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Professor professor) {
		EntityCacheUtil.removeResult(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
			ProfessorImpl.class, professor.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Professor> professors) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Professor professor : professors) {
			EntityCacheUtil.removeResult(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
				ProfessorImpl.class, professor.getPrimaryKey());
		}
	}

	/**
	 * Creates a new professor with the primary key. Does not add the professor to the database.
	 *
	 * @param professorSeq the primary key for the new professor
	 * @return the new professor
	 */
	@Override
	public Professor create(long professorSeq) {
		Professor professor = new ProfessorImpl();

		professor.setNew(true);
		professor.setPrimaryKey(professorSeq);

		return professor;
	}

	/**
	 * Removes the professor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param professorSeq the primary key of the professor
	 * @return the professor that was removed
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchProfessorException if a professor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor remove(long professorSeq)
		throws NoSuchProfessorException, SystemException {
		return remove((Serializable)professorSeq);
	}

	/**
	 * Removes the professor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the professor
	 * @return the professor that was removed
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchProfessorException if a professor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor remove(Serializable primaryKey)
		throws NoSuchProfessorException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Professor professor = (Professor)session.get(ProfessorImpl.class,
					primaryKey);

			if (professor == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProfessorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(professor);
		}
		catch (NoSuchProfessorException nsee) {
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
	protected Professor removeImpl(Professor professor)
		throws SystemException {
		professor = toUnwrappedModel(professor);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(professor)) {
				professor = (Professor)session.get(ProfessorImpl.class,
						professor.getPrimaryKeyObj());
			}

			if (professor != null) {
				session.delete(professor);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (professor != null) {
			clearCache(professor);
		}

		return professor;
	}

	@Override
	public Professor updateImpl(
		org.kisti.edison.virtuallaboratory.model.Professor professor)
		throws SystemException {
		professor = toUnwrappedModel(professor);

		boolean isNew = professor.isNew();

		ProfessorModelImpl professorModelImpl = (ProfessorModelImpl)professor;

		Session session = null;

		try {
			session = openSession();

			if (professor.isNew()) {
				session.save(professor);

				professor.setNew(false);
			}
			else {
				session.merge(professor);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ProfessorModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((professorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						professorModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { professorModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
		}

		EntityCacheUtil.putResult(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
			ProfessorImpl.class, professor.getPrimaryKey(), professor);

		return professor;
	}

	protected Professor toUnwrappedModel(Professor professor) {
		if (professor instanceof ProfessorImpl) {
			return professor;
		}

		ProfessorImpl professorImpl = new ProfessorImpl();

		professorImpl.setNew(professor.isNew());
		professorImpl.setPrimaryKey(professor.getPrimaryKey());

		professorImpl.setProfessorSeq(professor.getProfessorSeq());
		professorImpl.setUserId(professor.getUserId());
		professorImpl.setRecord(professor.getRecord());
		professorImpl.setPaper(professor.getPaper());
		professorImpl.setHomepage(professor.getHomepage());
		professorImpl.setIntroduce(professor.getIntroduce());
		professorImpl.setPhone(professor.getPhone());

		return professorImpl;
	}

	/**
	 * Returns the professor with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the professor
	 * @return the professor
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchProfessorException if a professor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProfessorException, SystemException {
		Professor professor = fetchByPrimaryKey(primaryKey);

		if (professor == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProfessorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return professor;
	}

	/**
	 * Returns the professor with the primary key or throws a {@link org.kisti.edison.virtuallaboratory.NoSuchProfessorException} if it could not be found.
	 *
	 * @param professorSeq the primary key of the professor
	 * @return the professor
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchProfessorException if a professor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor findByPrimaryKey(long professorSeq)
		throws NoSuchProfessorException, SystemException {
		return findByPrimaryKey((Serializable)professorSeq);
	}

	/**
	 * Returns the professor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the professor
	 * @return the professor, or <code>null</code> if a professor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Professor professor = (Professor)EntityCacheUtil.getResult(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
				ProfessorImpl.class, primaryKey);

		if (professor == _nullProfessor) {
			return null;
		}

		if (professor == null) {
			Session session = null;

			try {
				session = openSession();

				professor = (Professor)session.get(ProfessorImpl.class,
						primaryKey);

				if (professor != null) {
					cacheResult(professor);
				}
				else {
					EntityCacheUtil.putResult(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
						ProfessorImpl.class, primaryKey, _nullProfessor);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ProfessorModelImpl.ENTITY_CACHE_ENABLED,
					ProfessorImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return professor;
	}

	/**
	 * Returns the professor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param professorSeq the primary key of the professor
	 * @return the professor, or <code>null</code> if a professor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Professor fetchByPrimaryKey(long professorSeq)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)professorSeq);
	}

	/**
	 * Returns all the professors.
	 *
	 * @return the professors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Professor> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the professors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ProfessorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of professors
	 * @param end the upper bound of the range of professors (not inclusive)
	 * @return the range of professors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Professor> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the professors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ProfessorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of professors
	 * @param end the upper bound of the range of professors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of professors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Professor> findAll(int start, int end,
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

		List<Professor> list = (List<Professor>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PROFESSOR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROFESSOR;

				if (pagination) {
					sql = sql.concat(ProfessorModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Professor>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Professor>(list);
				}
				else {
					list = (List<Professor>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the professors from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Professor professor : findAll()) {
			remove(professor);
		}
	}

	/**
	 * Returns the number of professors.
	 *
	 * @return the number of professors
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

				Query q = session.createQuery(_SQL_COUNT_PROFESSOR);

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
	 * Initializes the professor persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.virtuallaboratory.model.Professor")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Professor>> listenersList = new ArrayList<ModelListener<Professor>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Professor>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ProfessorImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_PROFESSOR = "SELECT professor FROM Professor professor";
	private static final String _SQL_SELECT_PROFESSOR_WHERE = "SELECT professor FROM Professor professor WHERE ";
	private static final String _SQL_COUNT_PROFESSOR = "SELECT COUNT(professor) FROM Professor professor";
	private static final String _SQL_COUNT_PROFESSOR_WHERE = "SELECT COUNT(professor) FROM Professor professor WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "professor.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Professor exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Professor exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ProfessorPersistenceImpl.class);
	private static Professor _nullProfessor = new ProfessorImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Professor> toCacheModel() {
				return _nullProfessorCacheModel;
			}
		};

	private static CacheModel<Professor> _nullProfessorCacheModel = new CacheModel<Professor>() {
			@Override
			public Professor toEntityModel() {
				return _nullProfessor;
			}
		};
}