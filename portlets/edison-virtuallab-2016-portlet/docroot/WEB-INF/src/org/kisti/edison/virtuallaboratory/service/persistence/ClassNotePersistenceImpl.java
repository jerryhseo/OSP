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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.kisti.edison.virtuallaboratory.NoSuchClassNoteException;
import org.kisti.edison.virtuallaboratory.model.ClassNote;
import org.kisti.edison.virtuallaboratory.model.impl.ClassNoteImpl;
import org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the class note service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ClassNotePersistence
 * @see ClassNoteUtil
 * @generated
 */
public class ClassNotePersistenceImpl extends BasePersistenceImpl<ClassNote>
	implements ClassNotePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ClassNoteUtil} to access the class note persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ClassNoteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteModelImpl.FINDER_CACHE_ENABLED, ClassNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteModelImpl.FINDER_CACHE_ENABLED, ClassNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSIDISCONTENT =
		new FinderPath(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteModelImpl.FINDER_CACHE_ENABLED, ClassNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByclassIdIsContent",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSIDISCONTENT =
		new FinderPath(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteModelImpl.FINDER_CACHE_ENABLED, ClassNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByclassIdIsContent",
			new String[] { Long.class.getName(), String.class.getName() },
			ClassNoteModelImpl.CLASSID_COLUMN_BITMASK |
			ClassNoteModelImpl.ISCONTENT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSIDISCONTENT = new FinderPath(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByclassIdIsContent",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the class notes where classId = &#63; and isContent = &#63;.
	 *
	 * @param classId the class ID
	 * @param isContent the is content
	 * @return the matching class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ClassNote> findByclassIdIsContent(long classId, String isContent)
		throws SystemException {
		return findByclassIdIsContent(classId, isContent, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the class notes where classId = &#63; and isContent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classId the class ID
	 * @param isContent the is content
	 * @param start the lower bound of the range of class notes
	 * @param end the upper bound of the range of class notes (not inclusive)
	 * @return the range of matching class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ClassNote> findByclassIdIsContent(long classId,
		String isContent, int start, int end) throws SystemException {
		return findByclassIdIsContent(classId, isContent, start, end, null);
	}

	/**
	 * Returns an ordered range of all the class notes where classId = &#63; and isContent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classId the class ID
	 * @param isContent the is content
	 * @param start the lower bound of the range of class notes
	 * @param end the upper bound of the range of class notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ClassNote> findByclassIdIsContent(long classId,
		String isContent, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSIDISCONTENT;
			finderArgs = new Object[] { classId, isContent };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSIDISCONTENT;
			finderArgs = new Object[] {
					classId, isContent,
					
					start, end, orderByComparator
				};
		}

		List<ClassNote> list = (List<ClassNote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ClassNote classNote : list) {
				if ((classId != classNote.getClassId()) ||
						!Validator.equals(isContent, classNote.getIsContent())) {
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

			query.append(_SQL_SELECT_CLASSNOTE_WHERE);

			query.append(_FINDER_COLUMN_CLASSIDISCONTENT_CLASSID_2);

			boolean bindIsContent = false;

			if (isContent == null) {
				query.append(_FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_1);
			}
			else if (isContent.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_3);
			}
			else {
				bindIsContent = true;

				query.append(_FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ClassNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classId);

				if (bindIsContent) {
					qPos.add(isContent);
				}

				if (!pagination) {
					list = (List<ClassNote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ClassNote>(list);
				}
				else {
					list = (List<ClassNote>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first class note in the ordered set where classId = &#63; and isContent = &#63;.
	 *
	 * @param classId the class ID
	 * @param isContent the is content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class note
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a matching class note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote findByclassIdIsContent_First(long classId,
		String isContent, OrderByComparator orderByComparator)
		throws NoSuchClassNoteException, SystemException {
		ClassNote classNote = fetchByclassIdIsContent_First(classId, isContent,
				orderByComparator);

		if (classNote != null) {
			return classNote;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classId=");
		msg.append(classId);

		msg.append(", isContent=");
		msg.append(isContent);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchClassNoteException(msg.toString());
	}

	/**
	 * Returns the first class note in the ordered set where classId = &#63; and isContent = &#63;.
	 *
	 * @param classId the class ID
	 * @param isContent the is content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class note, or <code>null</code> if a matching class note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote fetchByclassIdIsContent_First(long classId,
		String isContent, OrderByComparator orderByComparator)
		throws SystemException {
		List<ClassNote> list = findByclassIdIsContent(classId, isContent, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last class note in the ordered set where classId = &#63; and isContent = &#63;.
	 *
	 * @param classId the class ID
	 * @param isContent the is content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class note
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a matching class note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote findByclassIdIsContent_Last(long classId,
		String isContent, OrderByComparator orderByComparator)
		throws NoSuchClassNoteException, SystemException {
		ClassNote classNote = fetchByclassIdIsContent_Last(classId, isContent,
				orderByComparator);

		if (classNote != null) {
			return classNote;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classId=");
		msg.append(classId);

		msg.append(", isContent=");
		msg.append(isContent);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchClassNoteException(msg.toString());
	}

	/**
	 * Returns the last class note in the ordered set where classId = &#63; and isContent = &#63;.
	 *
	 * @param classId the class ID
	 * @param isContent the is content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class note, or <code>null</code> if a matching class note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote fetchByclassIdIsContent_Last(long classId,
		String isContent, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByclassIdIsContent(classId, isContent);

		if (count == 0) {
			return null;
		}

		List<ClassNote> list = findByclassIdIsContent(classId, isContent,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the class notes before and after the current class note in the ordered set where classId = &#63; and isContent = &#63;.
	 *
	 * @param classNoteSeq the primary key of the current class note
	 * @param classId the class ID
	 * @param isContent the is content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class note
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a class note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote[] findByclassIdIsContent_PrevAndNext(long classNoteSeq,
		long classId, String isContent, OrderByComparator orderByComparator)
		throws NoSuchClassNoteException, SystemException {
		ClassNote classNote = findByPrimaryKey(classNoteSeq);

		Session session = null;

		try {
			session = openSession();

			ClassNote[] array = new ClassNoteImpl[3];

			array[0] = getByclassIdIsContent_PrevAndNext(session, classNote,
					classId, isContent, orderByComparator, true);

			array[1] = classNote;

			array[2] = getByclassIdIsContent_PrevAndNext(session, classNote,
					classId, isContent, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ClassNote getByclassIdIsContent_PrevAndNext(Session session,
		ClassNote classNote, long classId, String isContent,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CLASSNOTE_WHERE);

		query.append(_FINDER_COLUMN_CLASSIDISCONTENT_CLASSID_2);

		boolean bindIsContent = false;

		if (isContent == null) {
			query.append(_FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_1);
		}
		else if (isContent.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_3);
		}
		else {
			bindIsContent = true;

			query.append(_FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_2);
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
			query.append(ClassNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classId);

		if (bindIsContent) {
			qPos.add(isContent);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(classNote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ClassNote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the class notes where classId = &#63; and isContent = &#63; from the database.
	 *
	 * @param classId the class ID
	 * @param isContent the is content
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByclassIdIsContent(long classId, String isContent)
		throws SystemException {
		for (ClassNote classNote : findByclassIdIsContent(classId, isContent,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(classNote);
		}
	}

	/**
	 * Returns the number of class notes where classId = &#63; and isContent = &#63;.
	 *
	 * @param classId the class ID
	 * @param isContent the is content
	 * @return the number of matching class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByclassIdIsContent(long classId, String isContent)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSIDISCONTENT;

		Object[] finderArgs = new Object[] { classId, isContent };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CLASSNOTE_WHERE);

			query.append(_FINDER_COLUMN_CLASSIDISCONTENT_CLASSID_2);

			boolean bindIsContent = false;

			if (isContent == null) {
				query.append(_FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_1);
			}
			else if (isContent.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_3);
			}
			else {
				bindIsContent = true;

				query.append(_FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classId);

				if (bindIsContent) {
					qPos.add(isContent);
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

	private static final String _FINDER_COLUMN_CLASSIDISCONTENT_CLASSID_2 = "classNote.classId = ? AND ";
	private static final String _FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_1 = "classNote.isContent IS NULL";
	private static final String _FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_2 = "classNote.isContent = ?";
	private static final String _FINDER_COLUMN_CLASSIDISCONTENT_ISCONTENT_3 = "(classNote.isContent IS NULL OR classNote.isContent = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSIDISCONTENTSEQ =
		new FinderPath(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteModelImpl.FINDER_CACHE_ENABLED, ClassNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByclassIdIsContentSeq",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSIDISCONTENTSEQ =
		new FinderPath(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteModelImpl.FINDER_CACHE_ENABLED, ClassNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByclassIdIsContentSeq",
			new String[] { Long.class.getName(), Long.class.getName() },
			ClassNoteModelImpl.CLASSID_COLUMN_BITMASK |
			ClassNoteModelImpl.CONTENTSEQ_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSIDISCONTENTSEQ = new FinderPath(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByclassIdIsContentSeq",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the class notes where classId = &#63; and contentSeq = &#63;.
	 *
	 * @param classId the class ID
	 * @param contentSeq the content seq
	 * @return the matching class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ClassNote> findByclassIdIsContentSeq(long classId,
		long contentSeq) throws SystemException {
		return findByclassIdIsContentSeq(classId, contentSeq,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the class notes where classId = &#63; and contentSeq = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classId the class ID
	 * @param contentSeq the content seq
	 * @param start the lower bound of the range of class notes
	 * @param end the upper bound of the range of class notes (not inclusive)
	 * @return the range of matching class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ClassNote> findByclassIdIsContentSeq(long classId,
		long contentSeq, int start, int end) throws SystemException {
		return findByclassIdIsContentSeq(classId, contentSeq, start, end, null);
	}

	/**
	 * Returns an ordered range of all the class notes where classId = &#63; and contentSeq = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classId the class ID
	 * @param contentSeq the content seq
	 * @param start the lower bound of the range of class notes
	 * @param end the upper bound of the range of class notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ClassNote> findByclassIdIsContentSeq(long classId,
		long contentSeq, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSIDISCONTENTSEQ;
			finderArgs = new Object[] { classId, contentSeq };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSIDISCONTENTSEQ;
			finderArgs = new Object[] {
					classId, contentSeq,
					
					start, end, orderByComparator
				};
		}

		List<ClassNote> list = (List<ClassNote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ClassNote classNote : list) {
				if ((classId != classNote.getClassId()) ||
						(contentSeq != classNote.getContentSeq())) {
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

			query.append(_SQL_SELECT_CLASSNOTE_WHERE);

			query.append(_FINDER_COLUMN_CLASSIDISCONTENTSEQ_CLASSID_2);

			query.append(_FINDER_COLUMN_CLASSIDISCONTENTSEQ_CONTENTSEQ_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ClassNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classId);

				qPos.add(contentSeq);

				if (!pagination) {
					list = (List<ClassNote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ClassNote>(list);
				}
				else {
					list = (List<ClassNote>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first class note in the ordered set where classId = &#63; and contentSeq = &#63;.
	 *
	 * @param classId the class ID
	 * @param contentSeq the content seq
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class note
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a matching class note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote findByclassIdIsContentSeq_First(long classId,
		long contentSeq, OrderByComparator orderByComparator)
		throws NoSuchClassNoteException, SystemException {
		ClassNote classNote = fetchByclassIdIsContentSeq_First(classId,
				contentSeq, orderByComparator);

		if (classNote != null) {
			return classNote;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classId=");
		msg.append(classId);

		msg.append(", contentSeq=");
		msg.append(contentSeq);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchClassNoteException(msg.toString());
	}

	/**
	 * Returns the first class note in the ordered set where classId = &#63; and contentSeq = &#63;.
	 *
	 * @param classId the class ID
	 * @param contentSeq the content seq
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching class note, or <code>null</code> if a matching class note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote fetchByclassIdIsContentSeq_First(long classId,
		long contentSeq, OrderByComparator orderByComparator)
		throws SystemException {
		List<ClassNote> list = findByclassIdIsContentSeq(classId, contentSeq,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last class note in the ordered set where classId = &#63; and contentSeq = &#63;.
	 *
	 * @param classId the class ID
	 * @param contentSeq the content seq
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class note
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a matching class note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote findByclassIdIsContentSeq_Last(long classId,
		long contentSeq, OrderByComparator orderByComparator)
		throws NoSuchClassNoteException, SystemException {
		ClassNote classNote = fetchByclassIdIsContentSeq_Last(classId,
				contentSeq, orderByComparator);

		if (classNote != null) {
			return classNote;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classId=");
		msg.append(classId);

		msg.append(", contentSeq=");
		msg.append(contentSeq);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchClassNoteException(msg.toString());
	}

	/**
	 * Returns the last class note in the ordered set where classId = &#63; and contentSeq = &#63;.
	 *
	 * @param classId the class ID
	 * @param contentSeq the content seq
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching class note, or <code>null</code> if a matching class note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote fetchByclassIdIsContentSeq_Last(long classId,
		long contentSeq, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByclassIdIsContentSeq(classId, contentSeq);

		if (count == 0) {
			return null;
		}

		List<ClassNote> list = findByclassIdIsContentSeq(classId, contentSeq,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the class notes before and after the current class note in the ordered set where classId = &#63; and contentSeq = &#63;.
	 *
	 * @param classNoteSeq the primary key of the current class note
	 * @param classId the class ID
	 * @param contentSeq the content seq
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next class note
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a class note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote[] findByclassIdIsContentSeq_PrevAndNext(
		long classNoteSeq, long classId, long contentSeq,
		OrderByComparator orderByComparator)
		throws NoSuchClassNoteException, SystemException {
		ClassNote classNote = findByPrimaryKey(classNoteSeq);

		Session session = null;

		try {
			session = openSession();

			ClassNote[] array = new ClassNoteImpl[3];

			array[0] = getByclassIdIsContentSeq_PrevAndNext(session, classNote,
					classId, contentSeq, orderByComparator, true);

			array[1] = classNote;

			array[2] = getByclassIdIsContentSeq_PrevAndNext(session, classNote,
					classId, contentSeq, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ClassNote getByclassIdIsContentSeq_PrevAndNext(Session session,
		ClassNote classNote, long classId, long contentSeq,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CLASSNOTE_WHERE);

		query.append(_FINDER_COLUMN_CLASSIDISCONTENTSEQ_CLASSID_2);

		query.append(_FINDER_COLUMN_CLASSIDISCONTENTSEQ_CONTENTSEQ_2);

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
			query.append(ClassNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classId);

		qPos.add(contentSeq);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(classNote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ClassNote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the class notes where classId = &#63; and contentSeq = &#63; from the database.
	 *
	 * @param classId the class ID
	 * @param contentSeq the content seq
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByclassIdIsContentSeq(long classId, long contentSeq)
		throws SystemException {
		for (ClassNote classNote : findByclassIdIsContentSeq(classId,
				contentSeq, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(classNote);
		}
	}

	/**
	 * Returns the number of class notes where classId = &#63; and contentSeq = &#63;.
	 *
	 * @param classId the class ID
	 * @param contentSeq the content seq
	 * @return the number of matching class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByclassIdIsContentSeq(long classId, long contentSeq)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSIDISCONTENTSEQ;

		Object[] finderArgs = new Object[] { classId, contentSeq };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CLASSNOTE_WHERE);

			query.append(_FINDER_COLUMN_CLASSIDISCONTENTSEQ_CLASSID_2);

			query.append(_FINDER_COLUMN_CLASSIDISCONTENTSEQ_CONTENTSEQ_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classId);

				qPos.add(contentSeq);

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

	private static final String _FINDER_COLUMN_CLASSIDISCONTENTSEQ_CLASSID_2 = "classNote.classId = ? AND ";
	private static final String _FINDER_COLUMN_CLASSIDISCONTENTSEQ_CONTENTSEQ_2 = "classNote.contentSeq = ?";

	public ClassNotePersistenceImpl() {
		setModelClass(ClassNote.class);
	}

	/**
	 * Caches the class note in the entity cache if it is enabled.
	 *
	 * @param classNote the class note
	 */
	@Override
	public void cacheResult(ClassNote classNote) {
		EntityCacheUtil.putResult(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteImpl.class, classNote.getPrimaryKey(), classNote);

		classNote.resetOriginalValues();
	}

	/**
	 * Caches the class notes in the entity cache if it is enabled.
	 *
	 * @param classNotes the class notes
	 */
	@Override
	public void cacheResult(List<ClassNote> classNotes) {
		for (ClassNote classNote : classNotes) {
			if (EntityCacheUtil.getResult(
						ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
						ClassNoteImpl.class, classNote.getPrimaryKey()) == null) {
				cacheResult(classNote);
			}
			else {
				classNote.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all class notes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ClassNoteImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ClassNoteImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the class note.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ClassNote classNote) {
		EntityCacheUtil.removeResult(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteImpl.class, classNote.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ClassNote> classNotes) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ClassNote classNote : classNotes) {
			EntityCacheUtil.removeResult(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
				ClassNoteImpl.class, classNote.getPrimaryKey());
		}
	}

	/**
	 * Creates a new class note with the primary key. Does not add the class note to the database.
	 *
	 * @param classNoteSeq the primary key for the new class note
	 * @return the new class note
	 */
	@Override
	public ClassNote create(long classNoteSeq) {
		ClassNote classNote = new ClassNoteImpl();

		classNote.setNew(true);
		classNote.setPrimaryKey(classNoteSeq);

		return classNote;
	}

	/**
	 * Removes the class note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param classNoteSeq the primary key of the class note
	 * @return the class note that was removed
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a class note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote remove(long classNoteSeq)
		throws NoSuchClassNoteException, SystemException {
		return remove((Serializable)classNoteSeq);
	}

	/**
	 * Removes the class note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the class note
	 * @return the class note that was removed
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a class note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote remove(Serializable primaryKey)
		throws NoSuchClassNoteException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ClassNote classNote = (ClassNote)session.get(ClassNoteImpl.class,
					primaryKey);

			if (classNote == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchClassNoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(classNote);
		}
		catch (NoSuchClassNoteException nsee) {
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
	protected ClassNote removeImpl(ClassNote classNote)
		throws SystemException {
		classNote = toUnwrappedModel(classNote);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(classNote)) {
				classNote = (ClassNote)session.get(ClassNoteImpl.class,
						classNote.getPrimaryKeyObj());
			}

			if (classNote != null) {
				session.delete(classNote);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (classNote != null) {
			clearCache(classNote);
		}

		return classNote;
	}

	@Override
	public ClassNote updateImpl(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws SystemException {
		classNote = toUnwrappedModel(classNote);

		boolean isNew = classNote.isNew();

		ClassNoteModelImpl classNoteModelImpl = (ClassNoteModelImpl)classNote;

		Session session = null;

		try {
			session = openSession();

			if (classNote.isNew()) {
				session.save(classNote);

				classNote.setNew(false);
			}
			else {
				session.merge(classNote);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ClassNoteModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((classNoteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSIDISCONTENT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						classNoteModelImpl.getOriginalClassId(),
						classNoteModelImpl.getOriginalIsContent()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSIDISCONTENT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSIDISCONTENT,
					args);

				args = new Object[] {
						classNoteModelImpl.getClassId(),
						classNoteModelImpl.getIsContent()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSIDISCONTENT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSIDISCONTENT,
					args);
			}

			if ((classNoteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSIDISCONTENTSEQ.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						classNoteModelImpl.getOriginalClassId(),
						classNoteModelImpl.getOriginalContentSeq()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSIDISCONTENTSEQ,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSIDISCONTENTSEQ,
					args);

				args = new Object[] {
						classNoteModelImpl.getClassId(),
						classNoteModelImpl.getContentSeq()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSIDISCONTENTSEQ,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSIDISCONTENTSEQ,
					args);
			}
		}

		EntityCacheUtil.putResult(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
			ClassNoteImpl.class, classNote.getPrimaryKey(), classNote);

		return classNote;
	}

	protected ClassNote toUnwrappedModel(ClassNote classNote) {
		if (classNote instanceof ClassNoteImpl) {
			return classNote;
		}

		ClassNoteImpl classNoteImpl = new ClassNoteImpl();

		classNoteImpl.setNew(classNote.isNew());
		classNoteImpl.setPrimaryKey(classNote.getPrimaryKey());

		classNoteImpl.setClassNoteSeq(classNote.getClassNoteSeq());
		classNoteImpl.setClassId(classNote.getClassId());
		classNoteImpl.setContentSeq(classNote.getContentSeq());
		classNoteImpl.setIsContent(classNote.getIsContent());
		classNoteImpl.setFileEntryId(classNote.getFileEntryId());
		classNoteImpl.setDescription(classNote.getDescription());
		classNoteImpl.setInsertDate(classNote.getInsertDate());
		classNoteImpl.setUpdateDate(classNote.getUpdateDate());
		classNoteImpl.setInsertId(classNote.getInsertId());
		classNoteImpl.setUpdateId(classNote.getUpdateId());

		return classNoteImpl;
	}

	/**
	 * Returns the class note with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the class note
	 * @return the class note
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a class note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote findByPrimaryKey(Serializable primaryKey)
		throws NoSuchClassNoteException, SystemException {
		ClassNote classNote = fetchByPrimaryKey(primaryKey);

		if (classNote == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchClassNoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return classNote;
	}

	/**
	 * Returns the class note with the primary key or throws a {@link org.kisti.edison.virtuallaboratory.NoSuchClassNoteException} if it could not be found.
	 *
	 * @param classNoteSeq the primary key of the class note
	 * @return the class note
	 * @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a class note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote findByPrimaryKey(long classNoteSeq)
		throws NoSuchClassNoteException, SystemException {
		return findByPrimaryKey((Serializable)classNoteSeq);
	}

	/**
	 * Returns the class note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the class note
	 * @return the class note, or <code>null</code> if a class note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ClassNote classNote = (ClassNote)EntityCacheUtil.getResult(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
				ClassNoteImpl.class, primaryKey);

		if (classNote == _nullClassNote) {
			return null;
		}

		if (classNote == null) {
			Session session = null;

			try {
				session = openSession();

				classNote = (ClassNote)session.get(ClassNoteImpl.class,
						primaryKey);

				if (classNote != null) {
					cacheResult(classNote);
				}
				else {
					EntityCacheUtil.putResult(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
						ClassNoteImpl.class, primaryKey, _nullClassNote);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ClassNoteModelImpl.ENTITY_CACHE_ENABLED,
					ClassNoteImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return classNote;
	}

	/**
	 * Returns the class note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param classNoteSeq the primary key of the class note
	 * @return the class note, or <code>null</code> if a class note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ClassNote fetchByPrimaryKey(long classNoteSeq)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)classNoteSeq);
	}

	/**
	 * Returns all the class notes.
	 *
	 * @return the class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ClassNote> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the class notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of class notes
	 * @param end the upper bound of the range of class notes (not inclusive)
	 * @return the range of class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ClassNote> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the class notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of class notes
	 * @param end the upper bound of the range of class notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of class notes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ClassNote> findAll(int start, int end,
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

		List<ClassNote> list = (List<ClassNote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CLASSNOTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CLASSNOTE;

				if (pagination) {
					sql = sql.concat(ClassNoteModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ClassNote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ClassNote>(list);
				}
				else {
					list = (List<ClassNote>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the class notes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ClassNote classNote : findAll()) {
			remove(classNote);
		}
	}

	/**
	 * Returns the number of class notes.
	 *
	 * @return the number of class notes
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

				Query q = session.createQuery(_SQL_COUNT_CLASSNOTE);

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
	 * Initializes the class note persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.virtuallaboratory.model.ClassNote")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ClassNote>> listenersList = new ArrayList<ModelListener<ClassNote>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ClassNote>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ClassNoteImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CLASSNOTE = "SELECT classNote FROM ClassNote classNote";
	private static final String _SQL_SELECT_CLASSNOTE_WHERE = "SELECT classNote FROM ClassNote classNote WHERE ";
	private static final String _SQL_COUNT_CLASSNOTE = "SELECT COUNT(classNote) FROM ClassNote classNote";
	private static final String _SQL_COUNT_CLASSNOTE_WHERE = "SELECT COUNT(classNote) FROM ClassNote classNote WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "classNote.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ClassNote exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ClassNote exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ClassNotePersistenceImpl.class);
	private static ClassNote _nullClassNote = new ClassNoteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ClassNote> toCacheModel() {
				return _nullClassNoteCacheModel;
			}
		};

	private static CacheModel<ClassNote> _nullClassNoteCacheModel = new CacheModel<ClassNote>() {
			@Override
			public ClassNote toEntityModel() {
				return _nullClassNote;
			}
		};
}