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

package org.kisti.edison.content.service.persistence;

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

import org.kisti.edison.content.NoSuchContentException;
import org.kisti.edison.content.model.Content;
import org.kisti.edison.content.model.impl.ContentImpl;
import org.kisti.edison.content.model.impl.ContentModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ContentPersistence
 * @see ContentUtil
 * @generated
 */
public class ContentPersistenceImpl extends BasePersistenceImpl<Content>
	implements ContentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContentUtil} to access the content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ContentModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the contents where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of matching contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> findByUuid(String uuid, int start, int end,
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

		List<Content> list = (List<Content>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Content content : list) {
				if (!Validator.equals(uuid, content.getUuid())) {
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

			query.append(_SQL_SELECT_CONTENT_WHERE);

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
				query.append(ContentModelImpl.ORDER_BY_JPQL);
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
					list = (List<Content>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Content>(list);
				}
				else {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first content in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content
	 * @throws org.kisti.edison.content.NoSuchContentException if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		Content content = fetchByUuid_First(uuid, orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the first content in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content, or <code>null</code> if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Content> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content
	 * @throws org.kisti.edison.content.NoSuchContentException if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		Content content = fetchByUuid_Last(uuid, orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the last content in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content, or <code>null</code> if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Content> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contents before and after the current content in the ordered set where uuid = &#63;.
	 *
	 * @param contentSeq the primary key of the current content
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content
	 * @throws org.kisti.edison.content.NoSuchContentException if a content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content[] findByUuid_PrevAndNext(long contentSeq, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		Content content = findByPrimaryKey(contentSeq);

		Session session = null;

		try {
			session = openSession();

			Content[] array = new ContentImpl[3];

			array[0] = getByUuid_PrevAndNext(session, content, uuid,
					orderByComparator, true);

			array[1] = content;

			array[2] = getByUuid_PrevAndNext(session, content, uuid,
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

	protected Content getByUuid_PrevAndNext(Session session, Content content,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENT_WHERE);

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
			query.append(ContentModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(content);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Content> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the contents that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching contents that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> filterFindByUuid(String uuid)
		throws SystemException {
		return filterFindByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of matching contents that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> filterFindByUuid(String uuid, int start, int end)
		throws SystemException {
		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contents that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> filterFindByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid(uuid, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_CONTENT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CONTENT_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1_SQL);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CONTENT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ContentModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ContentModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Content.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ContentImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ContentImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			return (List<Content>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the contents before and after the current content in the ordered set of contents that the user has permission to view where uuid = &#63;.
	 *
	 * @param contentSeq the primary key of the current content
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content
	 * @throws org.kisti.edison.content.NoSuchContentException if a content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content[] filterFindByUuid_PrevAndNext(long contentSeq, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(contentSeq, uuid, orderByComparator);
		}

		Content content = findByPrimaryKey(contentSeq);

		Session session = null;

		try {
			session = openSession();

			Content[] array = new ContentImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(session, content, uuid,
					orderByComparator, true);

			array[1] = content;

			array[2] = filterGetByUuid_PrevAndNext(session, content, uuid,
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

	protected Content filterGetByUuid_PrevAndNext(Session session,
		Content content, String uuid, OrderByComparator orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_CONTENT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CONTENT_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1_SQL);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CONTENT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ContentModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ContentModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Content.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ContentImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ContentImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(content);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Content> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contents where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (Content content : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(content);
		}
	}

	/**
	 * Returns the number of contents where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching contents
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

			query.append(_SQL_COUNT_CONTENT_WHERE);

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

	/**
	 * Returns the number of contents that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching contents that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByUuid(String uuid) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CONTENT_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1_SQL);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Content.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "content.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "content.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(content.uuid IS NULL OR content.uuid = '')";
	private static final String _FINDER_COLUMN_UUID_UUID_1_SQL = "content.uuid_ IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2_SQL = "content.uuid_ = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3_SQL = "(content.uuid_ IS NULL OR content.uuid_ = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_CONTENTSEQ = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByContentSeq",
			new String[] { Long.class.getName() },
			ContentModelImpl.CONTENTSEQ_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTENTSEQ = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContentSeq",
			new String[] { Long.class.getName() });

	/**
	 * Returns the content where contentSeq = &#63; or throws a {@link org.kisti.edison.content.NoSuchContentException} if it could not be found.
	 *
	 * @param contentSeq the content seq
	 * @return the matching content
	 * @throws org.kisti.edison.content.NoSuchContentException if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content findByContentSeq(long contentSeq)
		throws NoSuchContentException, SystemException {
		Content content = fetchByContentSeq(contentSeq);

		if (content == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("contentSeq=");
			msg.append(contentSeq);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchContentException(msg.toString());
		}

		return content;
	}

	/**
	 * Returns the content where contentSeq = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param contentSeq the content seq
	 * @return the matching content, or <code>null</code> if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content fetchByContentSeq(long contentSeq) throws SystemException {
		return fetchByContentSeq(contentSeq, true);
	}

	/**
	 * Returns the content where contentSeq = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param contentSeq the content seq
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching content, or <code>null</code> if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content fetchByContentSeq(long contentSeq, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { contentSeq };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTENTSEQ,
					finderArgs, this);
		}

		if (result instanceof Content) {
			Content content = (Content)result;

			if ((contentSeq != content.getContentSeq())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CONTENT_WHERE);

			query.append(_FINDER_COLUMN_CONTENTSEQ_CONTENTSEQ_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentSeq);

				List<Content> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTENTSEQ,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"ContentPersistenceImpl.fetchByContentSeq(long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Content content = list.get(0);

					result = content;

					cacheResult(content);

					if ((content.getContentSeq() != contentSeq)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTENTSEQ,
							finderArgs, content);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTENTSEQ,
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
			return (Content)result;
		}
	}

	/**
	 * Removes the content where contentSeq = &#63; from the database.
	 *
	 * @param contentSeq the content seq
	 * @return the content that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content removeByContentSeq(long contentSeq)
		throws NoSuchContentException, SystemException {
		Content content = findByContentSeq(contentSeq);

		return remove(content);
	}

	/**
	 * Returns the number of contents where contentSeq = &#63;.
	 *
	 * @param contentSeq the content seq
	 * @return the number of matching contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByContentSeq(long contentSeq) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTENTSEQ;

		Object[] finderArgs = new Object[] { contentSeq };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENT_WHERE);

			query.append(_FINDER_COLUMN_CONTENTSEQ_CONTENTSEQ_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_CONTENTSEQ_CONTENTSEQ_2 = "content.contentSeq = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTDIV =
		new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBycontentDiv",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTDIV =
		new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBycontentDiv",
			new String[] { Long.class.getName() },
			ContentModelImpl.CONTENTDIV_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTENTDIV = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycontentDiv",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the contents where contentDiv = &#63;.
	 *
	 * @param contentDiv the content div
	 * @return the matching contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> findBycontentDiv(long contentDiv)
		throws SystemException {
		return findBycontentDiv(contentDiv, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents where contentDiv = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentDiv the content div
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of matching contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> findBycontentDiv(long contentDiv, int start, int end)
		throws SystemException {
		return findBycontentDiv(contentDiv, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents where contentDiv = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentDiv the content div
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> findBycontentDiv(long contentDiv, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTDIV;
			finderArgs = new Object[] { contentDiv };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTDIV;
			finderArgs = new Object[] { contentDiv, start, end, orderByComparator };
		}

		List<Content> list = (List<Content>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Content content : list) {
				if ((contentDiv != content.getContentDiv())) {
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

			query.append(_SQL_SELECT_CONTENT_WHERE);

			query.append(_FINDER_COLUMN_CONTENTDIV_CONTENTDIV_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentDiv);

				if (!pagination) {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Content>(list);
				}
				else {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first content in the ordered set where contentDiv = &#63;.
	 *
	 * @param contentDiv the content div
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content
	 * @throws org.kisti.edison.content.NoSuchContentException if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content findBycontentDiv_First(long contentDiv,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		Content content = fetchBycontentDiv_First(contentDiv, orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contentDiv=");
		msg.append(contentDiv);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the first content in the ordered set where contentDiv = &#63;.
	 *
	 * @param contentDiv the content div
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content, or <code>null</code> if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content fetchBycontentDiv_First(long contentDiv,
		OrderByComparator orderByComparator) throws SystemException {
		List<Content> list = findBycontentDiv(contentDiv, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content in the ordered set where contentDiv = &#63;.
	 *
	 * @param contentDiv the content div
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content
	 * @throws org.kisti.edison.content.NoSuchContentException if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content findBycontentDiv_Last(long contentDiv,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		Content content = fetchBycontentDiv_Last(contentDiv, orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contentDiv=");
		msg.append(contentDiv);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the last content in the ordered set where contentDiv = &#63;.
	 *
	 * @param contentDiv the content div
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content, or <code>null</code> if a matching content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content fetchBycontentDiv_Last(long contentDiv,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBycontentDiv(contentDiv);

		if (count == 0) {
			return null;
		}

		List<Content> list = findBycontentDiv(contentDiv, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contents before and after the current content in the ordered set where contentDiv = &#63;.
	 *
	 * @param contentSeq the primary key of the current content
	 * @param contentDiv the content div
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content
	 * @throws org.kisti.edison.content.NoSuchContentException if a content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content[] findBycontentDiv_PrevAndNext(long contentSeq,
		long contentDiv, OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		Content content = findByPrimaryKey(contentSeq);

		Session session = null;

		try {
			session = openSession();

			Content[] array = new ContentImpl[3];

			array[0] = getBycontentDiv_PrevAndNext(session, content,
					contentDiv, orderByComparator, true);

			array[1] = content;

			array[2] = getBycontentDiv_PrevAndNext(session, content,
					contentDiv, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Content getBycontentDiv_PrevAndNext(Session session,
		Content content, long contentDiv, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENT_WHERE);

		query.append(_FINDER_COLUMN_CONTENTDIV_CONTENTDIV_2);

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
			query.append(ContentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(contentDiv);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(content);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Content> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the contents that the user has permission to view where contentDiv = &#63;.
	 *
	 * @param contentDiv the content div
	 * @return the matching contents that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> filterFindBycontentDiv(long contentDiv)
		throws SystemException {
		return filterFindBycontentDiv(contentDiv, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents that the user has permission to view where contentDiv = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentDiv the content div
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of matching contents that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> filterFindBycontentDiv(long contentDiv, int start,
		int end) throws SystemException {
		return filterFindBycontentDiv(contentDiv, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents that the user has permissions to view where contentDiv = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentDiv the content div
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contents that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> filterFindBycontentDiv(long contentDiv, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findBycontentDiv(contentDiv, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_CONTENT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CONTENT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_CONTENTDIV_CONTENTDIV_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CONTENT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ContentModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ContentModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Content.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ContentImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ContentImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(contentDiv);

			return (List<Content>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the contents before and after the current content in the ordered set of contents that the user has permission to view where contentDiv = &#63;.
	 *
	 * @param contentSeq the primary key of the current content
	 * @param contentDiv the content div
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content
	 * @throws org.kisti.edison.content.NoSuchContentException if a content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content[] filterFindBycontentDiv_PrevAndNext(long contentSeq,
		long contentDiv, OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findBycontentDiv_PrevAndNext(contentSeq, contentDiv,
				orderByComparator);
		}

		Content content = findByPrimaryKey(contentSeq);

		Session session = null;

		try {
			session = openSession();

			Content[] array = new ContentImpl[3];

			array[0] = filterGetBycontentDiv_PrevAndNext(session, content,
					contentDiv, orderByComparator, true);

			array[1] = content;

			array[2] = filterGetBycontentDiv_PrevAndNext(session, content,
					contentDiv, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Content filterGetBycontentDiv_PrevAndNext(Session session,
		Content content, long contentDiv, OrderByComparator orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_CONTENT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CONTENT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_CONTENTDIV_CONTENTDIV_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CONTENT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ContentModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ContentModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Content.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ContentImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ContentImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(contentDiv);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(content);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Content> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contents where contentDiv = &#63; from the database.
	 *
	 * @param contentDiv the content div
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBycontentDiv(long contentDiv) throws SystemException {
		for (Content content : findBycontentDiv(contentDiv, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(content);
		}
	}

	/**
	 * Returns the number of contents where contentDiv = &#63;.
	 *
	 * @param contentDiv the content div
	 * @return the number of matching contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBycontentDiv(long contentDiv) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTENTDIV;

		Object[] finderArgs = new Object[] { contentDiv };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENT_WHERE);

			query.append(_FINDER_COLUMN_CONTENTDIV_CONTENTDIV_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentDiv);

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
	 * Returns the number of contents that the user has permission to view where contentDiv = &#63;.
	 *
	 * @param contentDiv the content div
	 * @return the number of matching contents that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountBycontentDiv(long contentDiv)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countBycontentDiv(contentDiv);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CONTENT_WHERE);

		query.append(_FINDER_COLUMN_CONTENTDIV_CONTENTDIV_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Content.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(contentDiv);

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

	private static final String _FINDER_COLUMN_CONTENTDIV_CONTENTDIV_2 = "content.contentDiv = ?";

	public ContentPersistenceImpl() {
		setModelClass(Content.class);
	}

	/**
	 * Caches the content in the entity cache if it is enabled.
	 *
	 * @param content the content
	 */
	@Override
	public void cacheResult(Content content) {
		EntityCacheUtil.putResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentImpl.class, content.getPrimaryKey(), content);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTENTSEQ,
			new Object[] { content.getContentSeq() }, content);

		content.resetOriginalValues();
	}

	/**
	 * Caches the contents in the entity cache if it is enabled.
	 *
	 * @param contents the contents
	 */
	@Override
	public void cacheResult(List<Content> contents) {
		for (Content content : contents) {
			if (EntityCacheUtil.getResult(
						ContentModelImpl.ENTITY_CACHE_ENABLED,
						ContentImpl.class, content.getPrimaryKey()) == null) {
				cacheResult(content);
			}
			else {
				content.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contents.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ContentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ContentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the content.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Content content) {
		EntityCacheUtil.removeResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentImpl.class, content.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(content);
	}

	@Override
	public void clearCache(List<Content> contents) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Content content : contents) {
			EntityCacheUtil.removeResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
				ContentImpl.class, content.getPrimaryKey());

			clearUniqueFindersCache(content);
		}
	}

	protected void cacheUniqueFindersCache(Content content) {
		if (content.isNew()) {
			Object[] args = new Object[] { content.getContentSeq() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTENTSEQ, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTENTSEQ, args,
				content);
		}
		else {
			ContentModelImpl contentModelImpl = (ContentModelImpl)content;

			if ((contentModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CONTENTSEQ.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { content.getContentSeq() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTENTSEQ,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTENTSEQ,
					args, content);
			}
		}
	}

	protected void clearUniqueFindersCache(Content content) {
		ContentModelImpl contentModelImpl = (ContentModelImpl)content;

		Object[] args = new Object[] { content.getContentSeq() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTENTSEQ, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTENTSEQ, args);

		if ((contentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CONTENTSEQ.getColumnBitmask()) != 0) {
			args = new Object[] { contentModelImpl.getOriginalContentSeq() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTENTSEQ, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTENTSEQ, args);
		}
	}

	/**
	 * Creates a new content with the primary key. Does not add the content to the database.
	 *
	 * @param contentSeq the primary key for the new content
	 * @return the new content
	 */
	@Override
	public Content create(long contentSeq) {
		Content content = new ContentImpl();

		content.setNew(true);
		content.setPrimaryKey(contentSeq);

		String uuid = PortalUUIDUtil.generate();

		content.setUuid(uuid);

		return content;
	}

	/**
	 * Removes the content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentSeq the primary key of the content
	 * @return the content that was removed
	 * @throws org.kisti.edison.content.NoSuchContentException if a content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content remove(long contentSeq)
		throws NoSuchContentException, SystemException {
		return remove((Serializable)contentSeq);
	}

	/**
	 * Removes the content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the content
	 * @return the content that was removed
	 * @throws org.kisti.edison.content.NoSuchContentException if a content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content remove(Serializable primaryKey)
		throws NoSuchContentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Content content = (Content)session.get(ContentImpl.class, primaryKey);

			if (content == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(content);
		}
		catch (NoSuchContentException nsee) {
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
	protected Content removeImpl(Content content) throws SystemException {
		content = toUnwrappedModel(content);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(content)) {
				content = (Content)session.get(ContentImpl.class,
						content.getPrimaryKeyObj());
			}

			if (content != null) {
				session.delete(content);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (content != null) {
			clearCache(content);
		}

		return content;
	}

	@Override
	public Content updateImpl(org.kisti.edison.content.model.Content content)
		throws SystemException {
		content = toUnwrappedModel(content);

		boolean isNew = content.isNew();

		ContentModelImpl contentModelImpl = (ContentModelImpl)content;

		if (Validator.isNull(content.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			content.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (content.isNew()) {
				session.save(content);

				content.setNew(false);
			}
			else {
				session.merge(content);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ContentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((contentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { contentModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { contentModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((contentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTDIV.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contentModelImpl.getOriginalContentDiv()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTENTDIV,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTDIV,
					args);

				args = new Object[] { contentModelImpl.getContentDiv() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTENTDIV,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTDIV,
					args);
			}
		}

		EntityCacheUtil.putResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentImpl.class, content.getPrimaryKey(), content);

		clearUniqueFindersCache(content);
		cacheUniqueFindersCache(content);

		return content;
	}

	protected Content toUnwrappedModel(Content content) {
		if (content instanceof ContentImpl) {
			return content;
		}

		ContentImpl contentImpl = new ContentImpl();

		contentImpl.setNew(content.isNew());
		contentImpl.setPrimaryKey(content.getPrimaryKey());

		contentImpl.setUuid(content.getUuid());
		contentImpl.setContentSeq(content.getContentSeq());
		contentImpl.setContentDiv(content.getContentDiv());
		contentImpl.setTitle(content.getTitle());
		contentImpl.setResume(content.getResume());
		contentImpl.setContentFileNm(content.getContentFileNm());
		contentImpl.setAdvancedStartFileNm(content.getAdvancedStartFileNm());
		contentImpl.setServiceLanguage(content.getServiceLanguage());
		contentImpl.setProjectYn(content.getProjectYn());
		contentImpl.setProjectId(content.getProjectId());
		contentImpl.setViewCnt(content.getViewCnt());
		contentImpl.setInsertId(content.getInsertId());
		contentImpl.setInsertDate(content.getInsertDate());
		contentImpl.setUpdateId(content.getUpdateId());
		contentImpl.setUpdateDate(content.getUpdateDate());
		contentImpl.setVersion(content.getVersion());
		contentImpl.setOpenYn(content.getOpenYn());
		contentImpl.setCoverImageFileEntryId(content.getCoverImageFileEntryId());

		return contentImpl;
	}

	/**
	 * Returns the content with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the content
	 * @return the content
	 * @throws org.kisti.edison.content.NoSuchContentException if a content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContentException, SystemException {
		Content content = fetchByPrimaryKey(primaryKey);

		if (content == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return content;
	}

	/**
	 * Returns the content with the primary key or throws a {@link org.kisti.edison.content.NoSuchContentException} if it could not be found.
	 *
	 * @param contentSeq the primary key of the content
	 * @return the content
	 * @throws org.kisti.edison.content.NoSuchContentException if a content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content findByPrimaryKey(long contentSeq)
		throws NoSuchContentException, SystemException {
		return findByPrimaryKey((Serializable)contentSeq);
	}

	/**
	 * Returns the content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the content
	 * @return the content, or <code>null</code> if a content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Content content = (Content)EntityCacheUtil.getResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
				ContentImpl.class, primaryKey);

		if (content == _nullContent) {
			return null;
		}

		if (content == null) {
			Session session = null;

			try {
				session = openSession();

				content = (Content)session.get(ContentImpl.class, primaryKey);

				if (content != null) {
					cacheResult(content);
				}
				else {
					EntityCacheUtil.putResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
						ContentImpl.class, primaryKey, _nullContent);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
					ContentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return content;
	}

	/**
	 * Returns the content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentSeq the primary key of the content
	 * @return the content, or <code>null</code> if a content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Content fetchByPrimaryKey(long contentSeq) throws SystemException {
		return fetchByPrimaryKey((Serializable)contentSeq);
	}

	/**
	 * Returns all the contents.
	 *
	 * @return the contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Content> findAll(int start, int end,
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

		List<Content> list = (List<Content>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CONTENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTENT;

				if (pagination) {
					sql = sql.concat(ContentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Content>(list);
				}
				else {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the contents from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Content content : findAll()) {
			remove(content);
		}
	}

	/**
	 * Returns the number of contents.
	 *
	 * @return the number of contents
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

				Query q = session.createQuery(_SQL_COUNT_CONTENT);

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
	 * Initializes the content persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.kisti.edison.content.model.Content")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Content>> listenersList = new ArrayList<ModelListener<Content>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Content>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ContentImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CONTENT = "SELECT content FROM Content content";
	private static final String _SQL_SELECT_CONTENT_WHERE = "SELECT content FROM Content content WHERE ";
	private static final String _SQL_COUNT_CONTENT = "SELECT COUNT(content) FROM Content content";
	private static final String _SQL_COUNT_CONTENT_WHERE = "SELECT COUNT(content) FROM Content content WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "content.contentSeq";
	private static final String _FILTER_SQL_SELECT_CONTENT_WHERE = "SELECT DISTINCT {content.*} FROM EDMED_Content content WHERE ";
	private static final String _FILTER_SQL_SELECT_CONTENT_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {EDMED_Content.*} FROM (SELECT DISTINCT content.contentSeq FROM EDMED_Content content WHERE ";
	private static final String _FILTER_SQL_SELECT_CONTENT_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN EDMED_Content ON TEMP_TABLE.contentSeq = EDMED_Content.contentSeq";
	private static final String _FILTER_SQL_COUNT_CONTENT_WHERE = "SELECT COUNT(DISTINCT content.contentSeq) AS COUNT_VALUE FROM EDMED_Content content WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "content";
	private static final String _FILTER_ENTITY_TABLE = "EDMED_Content";
	private static final String _ORDER_BY_ENTITY_ALIAS = "content.";
	private static final String _ORDER_BY_ENTITY_TABLE = "EDMED_Content.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Content exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Content exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ContentPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static Content _nullContent = new ContentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Content> toCacheModel() {
				return _nullContentCacheModel;
			}
		};

	private static CacheModel<Content> _nullContentCacheModel = new CacheModel<Content>() {
			@Override
			public Content toEntityModel() {
				return _nullContent;
			}
		};
}