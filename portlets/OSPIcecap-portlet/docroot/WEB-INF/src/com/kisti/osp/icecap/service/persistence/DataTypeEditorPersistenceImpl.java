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

package com.kisti.osp.icecap.service.persistence;

import com.kisti.osp.icecap.NoSuchDataTypeEditorException;
import com.kisti.osp.icecap.model.DataTypeEditor;
import com.kisti.osp.icecap.model.impl.DataTypeEditorImpl;
import com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl;

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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the data type editor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeEditorPersistence
 * @see DataTypeEditorUtil
 * @generated
 */
public class DataTypeEditorPersistenceImpl extends BasePersistenceImpl<DataTypeEditor>
	implements DataTypeEditorPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DataTypeEditorUtil} to access the data type editor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DataTypeEditorImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorModelImpl.FINDER_CACHE_ENABLED,
			DataTypeEditorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorModelImpl.FINDER_CACHE_ENABLED,
			DataTypeEditorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EDITOR = new FinderPath(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorModelImpl.FINDER_CACHE_ENABLED,
			DataTypeEditorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEditor",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EDITOR =
		new FinderPath(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorModelImpl.FINDER_CACHE_ENABLED,
			DataTypeEditorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEditor",
			new String[] { Long.class.getName() },
			DataTypeEditorModelImpl.EDITORID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EDITOR = new FinderPath(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEditor",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the data type editors where editorId = &#63;.
	 *
	 * @param editorId the editor ID
	 * @return the matching data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeEditor> findByEditor(long editorId)
		throws SystemException {
		return findByEditor(editorId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data type editors where editorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param editorId the editor ID
	 * @param start the lower bound of the range of data type editors
	 * @param end the upper bound of the range of data type editors (not inclusive)
	 * @return the range of matching data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeEditor> findByEditor(long editorId, int start, int end)
		throws SystemException {
		return findByEditor(editorId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data type editors where editorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param editorId the editor ID
	 * @param start the lower bound of the range of data type editors
	 * @param end the upper bound of the range of data type editors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeEditor> findByEditor(long editorId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EDITOR;
			finderArgs = new Object[] { editorId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EDITOR;
			finderArgs = new Object[] { editorId, start, end, orderByComparator };
		}

		List<DataTypeEditor> list = (List<DataTypeEditor>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataTypeEditor dataTypeEditor : list) {
				if ((editorId != dataTypeEditor.getEditorId())) {
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

			query.append(_SQL_SELECT_DATATYPEEDITOR_WHERE);

			query.append(_FINDER_COLUMN_EDITOR_EDITORID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataTypeEditorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(editorId);

				if (!pagination) {
					list = (List<DataTypeEditor>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataTypeEditor>(list);
				}
				else {
					list = (List<DataTypeEditor>)QueryUtil.list(q,
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
	 * Returns the first data type editor in the ordered set where editorId = &#63;.
	 *
	 * @param editorId the editor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data type editor
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor findByEditor_First(long editorId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeEditorException, SystemException {
		DataTypeEditor dataTypeEditor = fetchByEditor_First(editorId,
				orderByComparator);

		if (dataTypeEditor != null) {
			return dataTypeEditor;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("editorId=");
		msg.append(editorId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataTypeEditorException(msg.toString());
	}

	/**
	 * Returns the first data type editor in the ordered set where editorId = &#63;.
	 *
	 * @param editorId the editor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data type editor, or <code>null</code> if a matching data type editor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor fetchByEditor_First(long editorId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataTypeEditor> list = findByEditor(editorId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data type editor in the ordered set where editorId = &#63;.
	 *
	 * @param editorId the editor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data type editor
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor findByEditor_Last(long editorId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeEditorException, SystemException {
		DataTypeEditor dataTypeEditor = fetchByEditor_Last(editorId,
				orderByComparator);

		if (dataTypeEditor != null) {
			return dataTypeEditor;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("editorId=");
		msg.append(editorId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataTypeEditorException(msg.toString());
	}

	/**
	 * Returns the last data type editor in the ordered set where editorId = &#63;.
	 *
	 * @param editorId the editor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data type editor, or <code>null</code> if a matching data type editor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor fetchByEditor_Last(long editorId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByEditor(editorId);

		if (count == 0) {
			return null;
		}

		List<DataTypeEditor> list = findByEditor(editorId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data type editors before and after the current data type editor in the ordered set where editorId = &#63;.
	 *
	 * @param dataTypeEditorPK the primary key of the current data type editor
	 * @param editorId the editor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data type editor
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor[] findByEditor_PrevAndNext(
		DataTypeEditorPK dataTypeEditorPK, long editorId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeEditorException, SystemException {
		DataTypeEditor dataTypeEditor = findByPrimaryKey(dataTypeEditorPK);

		Session session = null;

		try {
			session = openSession();

			DataTypeEditor[] array = new DataTypeEditorImpl[3];

			array[0] = getByEditor_PrevAndNext(session, dataTypeEditor,
					editorId, orderByComparator, true);

			array[1] = dataTypeEditor;

			array[2] = getByEditor_PrevAndNext(session, dataTypeEditor,
					editorId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataTypeEditor getByEditor_PrevAndNext(Session session,
		DataTypeEditor dataTypeEditor, long editorId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATATYPEEDITOR_WHERE);

		query.append(_FINDER_COLUMN_EDITOR_EDITORID_2);

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
			query.append(DataTypeEditorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(editorId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataTypeEditor);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataTypeEditor> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data type editors where editorId = &#63; from the database.
	 *
	 * @param editorId the editor ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByEditor(long editorId) throws SystemException {
		for (DataTypeEditor dataTypeEditor : findByEditor(editorId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataTypeEditor);
		}
	}

	/**
	 * Returns the number of data type editors where editorId = &#63;.
	 *
	 * @param editorId the editor ID
	 * @return the number of matching data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByEditor(long editorId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EDITOR;

		Object[] finderArgs = new Object[] { editorId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATATYPEEDITOR_WHERE);

			query.append(_FINDER_COLUMN_EDITOR_EDITORID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(editorId);

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

	private static final String _FINDER_COLUMN_EDITOR_EDITORID_2 = "dataTypeEditor.id.editorId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPEID = new FinderPath(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorModelImpl.FINDER_CACHE_ENABLED,
			DataTypeEditorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTypeID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID =
		new FinderPath(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorModelImpl.FINDER_CACHE_ENABLED,
			DataTypeEditorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTypeID",
			new String[] { Long.class.getName() },
			DataTypeEditorModelImpl.TYPEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPEID = new FinderPath(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTypeID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the data type editors where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the matching data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeEditor> findByTypeID(long typeId)
		throws SystemException {
		return findByTypeID(typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data type editors where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of data type editors
	 * @param end the upper bound of the range of data type editors (not inclusive)
	 * @return the range of matching data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeEditor> findByTypeID(long typeId, int start, int end)
		throws SystemException {
		return findByTypeID(typeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data type editors where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of data type editors
	 * @param end the upper bound of the range of data type editors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeEditor> findByTypeID(long typeId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID;
			finderArgs = new Object[] { typeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPEID;
			finderArgs = new Object[] { typeId, start, end, orderByComparator };
		}

		List<DataTypeEditor> list = (List<DataTypeEditor>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataTypeEditor dataTypeEditor : list) {
				if ((typeId != dataTypeEditor.getTypeId())) {
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

			query.append(_SQL_SELECT_DATATYPEEDITOR_WHERE);

			query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataTypeEditorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

				if (!pagination) {
					list = (List<DataTypeEditor>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataTypeEditor>(list);
				}
				else {
					list = (List<DataTypeEditor>)QueryUtil.list(q,
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
	 * Returns the first data type editor in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data type editor
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor findByTypeID_First(long typeId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeEditorException, SystemException {
		DataTypeEditor dataTypeEditor = fetchByTypeID_First(typeId,
				orderByComparator);

		if (dataTypeEditor != null) {
			return dataTypeEditor;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("typeId=");
		msg.append(typeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataTypeEditorException(msg.toString());
	}

	/**
	 * Returns the first data type editor in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data type editor, or <code>null</code> if a matching data type editor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor fetchByTypeID_First(long typeId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataTypeEditor> list = findByTypeID(typeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data type editor in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data type editor
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor findByTypeID_Last(long typeId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeEditorException, SystemException {
		DataTypeEditor dataTypeEditor = fetchByTypeID_Last(typeId,
				orderByComparator);

		if (dataTypeEditor != null) {
			return dataTypeEditor;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("typeId=");
		msg.append(typeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataTypeEditorException(msg.toString());
	}

	/**
	 * Returns the last data type editor in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data type editor, or <code>null</code> if a matching data type editor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor fetchByTypeID_Last(long typeId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTypeID(typeId);

		if (count == 0) {
			return null;
		}

		List<DataTypeEditor> list = findByTypeID(typeId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data type editors before and after the current data type editor in the ordered set where typeId = &#63;.
	 *
	 * @param dataTypeEditorPK the primary key of the current data type editor
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data type editor
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor[] findByTypeID_PrevAndNext(
		DataTypeEditorPK dataTypeEditorPK, long typeId,
		OrderByComparator orderByComparator)
		throws NoSuchDataTypeEditorException, SystemException {
		DataTypeEditor dataTypeEditor = findByPrimaryKey(dataTypeEditorPK);

		Session session = null;

		try {
			session = openSession();

			DataTypeEditor[] array = new DataTypeEditorImpl[3];

			array[0] = getByTypeID_PrevAndNext(session, dataTypeEditor, typeId,
					orderByComparator, true);

			array[1] = dataTypeEditor;

			array[2] = getByTypeID_PrevAndNext(session, dataTypeEditor, typeId,
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

	protected DataTypeEditor getByTypeID_PrevAndNext(Session session,
		DataTypeEditor dataTypeEditor, long typeId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATATYPEEDITOR_WHERE);

		query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

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
			query.append(DataTypeEditorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(typeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataTypeEditor);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataTypeEditor> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data type editors where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTypeID(long typeId) throws SystemException {
		for (DataTypeEditor dataTypeEditor : findByTypeID(typeId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataTypeEditor);
		}
	}

	/**
	 * Returns the number of data type editors where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByTypeID(long typeId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TYPEID;

		Object[] finderArgs = new Object[] { typeId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATATYPEEDITOR_WHERE);

			query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

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

	private static final String _FINDER_COLUMN_TYPEID_TYPEID_2 = "dataTypeEditor.id.typeId = ?";

	public DataTypeEditorPersistenceImpl() {
		setModelClass(DataTypeEditor.class);
	}

	/**
	 * Caches the data type editor in the entity cache if it is enabled.
	 *
	 * @param dataTypeEditor the data type editor
	 */
	@Override
	public void cacheResult(DataTypeEditor dataTypeEditor) {
		EntityCacheUtil.putResult(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorImpl.class, dataTypeEditor.getPrimaryKey(),
			dataTypeEditor);

		dataTypeEditor.resetOriginalValues();
	}

	/**
	 * Caches the data type editors in the entity cache if it is enabled.
	 *
	 * @param dataTypeEditors the data type editors
	 */
	@Override
	public void cacheResult(List<DataTypeEditor> dataTypeEditors) {
		for (DataTypeEditor dataTypeEditor : dataTypeEditors) {
			if (EntityCacheUtil.getResult(
						DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
						DataTypeEditorImpl.class, dataTypeEditor.getPrimaryKey()) == null) {
				cacheResult(dataTypeEditor);
			}
			else {
				dataTypeEditor.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all data type editors.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DataTypeEditorImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DataTypeEditorImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the data type editor.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DataTypeEditor dataTypeEditor) {
		EntityCacheUtil.removeResult(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorImpl.class, dataTypeEditor.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<DataTypeEditor> dataTypeEditors) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DataTypeEditor dataTypeEditor : dataTypeEditors) {
			EntityCacheUtil.removeResult(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
				DataTypeEditorImpl.class, dataTypeEditor.getPrimaryKey());
		}
	}

	/**
	 * Creates a new data type editor with the primary key. Does not add the data type editor to the database.
	 *
	 * @param dataTypeEditorPK the primary key for the new data type editor
	 * @return the new data type editor
	 */
	@Override
	public DataTypeEditor create(DataTypeEditorPK dataTypeEditorPK) {
		DataTypeEditor dataTypeEditor = new DataTypeEditorImpl();

		dataTypeEditor.setNew(true);
		dataTypeEditor.setPrimaryKey(dataTypeEditorPK);

		return dataTypeEditor;
	}

	/**
	 * Removes the data type editor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dataTypeEditorPK the primary key of the data type editor
	 * @return the data type editor that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor remove(DataTypeEditorPK dataTypeEditorPK)
		throws NoSuchDataTypeEditorException, SystemException {
		return remove((Serializable)dataTypeEditorPK);
	}

	/**
	 * Removes the data type editor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the data type editor
	 * @return the data type editor that was removed
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor remove(Serializable primaryKey)
		throws NoSuchDataTypeEditorException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DataTypeEditor dataTypeEditor = (DataTypeEditor)session.get(DataTypeEditorImpl.class,
					primaryKey);

			if (dataTypeEditor == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataTypeEditorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dataTypeEditor);
		}
		catch (NoSuchDataTypeEditorException nsee) {
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
	protected DataTypeEditor removeImpl(DataTypeEditor dataTypeEditor)
		throws SystemException {
		dataTypeEditor = toUnwrappedModel(dataTypeEditor);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dataTypeEditor)) {
				dataTypeEditor = (DataTypeEditor)session.get(DataTypeEditorImpl.class,
						dataTypeEditor.getPrimaryKeyObj());
			}

			if (dataTypeEditor != null) {
				session.delete(dataTypeEditor);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dataTypeEditor != null) {
			clearCache(dataTypeEditor);
		}

		return dataTypeEditor;
	}

	@Override
	public DataTypeEditor updateImpl(
		com.kisti.osp.icecap.model.DataTypeEditor dataTypeEditor)
		throws SystemException {
		dataTypeEditor = toUnwrappedModel(dataTypeEditor);

		boolean isNew = dataTypeEditor.isNew();

		DataTypeEditorModelImpl dataTypeEditorModelImpl = (DataTypeEditorModelImpl)dataTypeEditor;

		Session session = null;

		try {
			session = openSession();

			if (dataTypeEditor.isNew()) {
				session.save(dataTypeEditor);

				dataTypeEditor.setNew(false);
			}
			else {
				session.merge(dataTypeEditor);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DataTypeEditorModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((dataTypeEditorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EDITOR.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataTypeEditorModelImpl.getOriginalEditorId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EDITOR, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EDITOR,
					args);

				args = new Object[] { dataTypeEditorModelImpl.getEditorId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EDITOR, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EDITOR,
					args);
			}

			if ((dataTypeEditorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataTypeEditorModelImpl.getOriginalTypeId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID,
					args);

				args = new Object[] { dataTypeEditorModelImpl.getTypeId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPEID,
					args);
			}
		}

		EntityCacheUtil.putResult(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
			DataTypeEditorImpl.class, dataTypeEditor.getPrimaryKey(),
			dataTypeEditor);

		return dataTypeEditor;
	}

	protected DataTypeEditor toUnwrappedModel(DataTypeEditor dataTypeEditor) {
		if (dataTypeEditor instanceof DataTypeEditorImpl) {
			return dataTypeEditor;
		}

		DataTypeEditorImpl dataTypeEditorImpl = new DataTypeEditorImpl();

		dataTypeEditorImpl.setNew(dataTypeEditor.isNew());
		dataTypeEditorImpl.setPrimaryKey(dataTypeEditor.getPrimaryKey());

		dataTypeEditorImpl.setTypeId(dataTypeEditor.getTypeId());
		dataTypeEditorImpl.setEditorId(dataTypeEditor.getEditorId());
		dataTypeEditorImpl.setIsDefault(dataTypeEditor.isIsDefault());

		return dataTypeEditorImpl;
	}

	/**
	 * Returns the data type editor with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the data type editor
	 * @return the data type editor
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataTypeEditorException, SystemException {
		DataTypeEditor dataTypeEditor = fetchByPrimaryKey(primaryKey);

		if (dataTypeEditor == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataTypeEditorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dataTypeEditor;
	}

	/**
	 * Returns the data type editor with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeEditorException} if it could not be found.
	 *
	 * @param dataTypeEditorPK the primary key of the data type editor
	 * @return the data type editor
	 * @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor findByPrimaryKey(DataTypeEditorPK dataTypeEditorPK)
		throws NoSuchDataTypeEditorException, SystemException {
		return findByPrimaryKey((Serializable)dataTypeEditorPK);
	}

	/**
	 * Returns the data type editor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the data type editor
	 * @return the data type editor, or <code>null</code> if a data type editor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		DataTypeEditor dataTypeEditor = (DataTypeEditor)EntityCacheUtil.getResult(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
				DataTypeEditorImpl.class, primaryKey);

		if (dataTypeEditor == _nullDataTypeEditor) {
			return null;
		}

		if (dataTypeEditor == null) {
			Session session = null;

			try {
				session = openSession();

				dataTypeEditor = (DataTypeEditor)session.get(DataTypeEditorImpl.class,
						primaryKey);

				if (dataTypeEditor != null) {
					cacheResult(dataTypeEditor);
				}
				else {
					EntityCacheUtil.putResult(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
						DataTypeEditorImpl.class, primaryKey,
						_nullDataTypeEditor);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DataTypeEditorModelImpl.ENTITY_CACHE_ENABLED,
					DataTypeEditorImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dataTypeEditor;
	}

	/**
	 * Returns the data type editor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dataTypeEditorPK the primary key of the data type editor
	 * @return the data type editor, or <code>null</code> if a data type editor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataTypeEditor fetchByPrimaryKey(DataTypeEditorPK dataTypeEditorPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)dataTypeEditorPK);
	}

	/**
	 * Returns all the data type editors.
	 *
	 * @return the data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeEditor> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data type editors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data type editors
	 * @param end the upper bound of the range of data type editors (not inclusive)
	 * @return the range of data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeEditor> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the data type editors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of data type editors
	 * @param end the upper bound of the range of data type editors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of data type editors
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataTypeEditor> findAll(int start, int end,
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

		List<DataTypeEditor> list = (List<DataTypeEditor>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DATATYPEEDITOR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DATATYPEEDITOR;

				if (pagination) {
					sql = sql.concat(DataTypeEditorModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DataTypeEditor>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataTypeEditor>(list);
				}
				else {
					list = (List<DataTypeEditor>)QueryUtil.list(q,
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
	 * Removes all the data type editors from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (DataTypeEditor dataTypeEditor : findAll()) {
			remove(dataTypeEditor);
		}
	}

	/**
	 * Returns the number of data type editors.
	 *
	 * @return the number of data type editors
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

				Query q = session.createQuery(_SQL_COUNT_DATATYPEEDITOR);

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
	 * Initializes the data type editor persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.osp.icecap.model.DataTypeEditor")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DataTypeEditor>> listenersList = new ArrayList<ModelListener<DataTypeEditor>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DataTypeEditor>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DataTypeEditorImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DATATYPEEDITOR = "SELECT dataTypeEditor FROM DataTypeEditor dataTypeEditor";
	private static final String _SQL_SELECT_DATATYPEEDITOR_WHERE = "SELECT dataTypeEditor FROM DataTypeEditor dataTypeEditor WHERE ";
	private static final String _SQL_COUNT_DATATYPEEDITOR = "SELECT COUNT(dataTypeEditor) FROM DataTypeEditor dataTypeEditor";
	private static final String _SQL_COUNT_DATATYPEEDITOR_WHERE = "SELECT COUNT(dataTypeEditor) FROM DataTypeEditor dataTypeEditor WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dataTypeEditor.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DataTypeEditor exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DataTypeEditor exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DataTypeEditorPersistenceImpl.class);
	private static DataTypeEditor _nullDataTypeEditor = new DataTypeEditorImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DataTypeEditor> toCacheModel() {
				return _nullDataTypeEditorCacheModel;
			}
		};

	private static CacheModel<DataTypeEditor> _nullDataTypeEditorCacheModel = new CacheModel<DataTypeEditor>() {
			@Override
			public DataTypeEditor toEntityModel() {
				return _nullDataTypeEditor;
			}
		};
}