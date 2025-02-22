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

package org.kisti.edison.search.service.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the SearchCondition service. Represents a row in the &quot;EDSEARCH_SearchCondition&quot; database table, with each column mapped to a property of this class.
 *
 * @author yjpark
 * @see SearchConditionModel
 * @see org.kisti.edison.search.service.model.impl.SearchConditionImpl
 * @see org.kisti.edison.search.service.model.impl.SearchConditionModelImpl
 * @generated
 */
public interface SearchCondition extends SearchConditionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.kisti.edison.search.service.model.impl.SearchConditionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.Locale getLocale();

	public void setLocale(java.util.Locale locale);

	public int getStart();

	public long[] getContentDivisions();

	public java.lang.String SORT_FIELD_CREATED();

	public java.lang.String SORT_FIELD_NAME();

	public java.lang.String SORT_FIELD_VIEW();

	public java.lang.String SORT_ORDER_ASC();

	public java.lang.String SORT_ORDER_DESC();

	public java.lang.String[] getAppTypes();

	public void setCustomModelAttributes(
		java.util.Map<java.lang.String, java.lang.Object> attributes);
}