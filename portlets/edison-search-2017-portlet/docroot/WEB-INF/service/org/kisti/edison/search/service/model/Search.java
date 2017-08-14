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
 * The extended model interface for the Search service. Represents a row in the &quot;EDSEARCH_Search&quot; database table, with each column mapped to a property of this class.
 *
 * @author yjpark
 * @see SearchModel
 * @see org.kisti.edison.search.service.model.impl.SearchImpl
 * @see org.kisti.edison.search.service.model.impl.SearchModelImpl
 * @generated
 */
public interface Search extends SearchModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.kisti.edison.search.service.model.impl.SearchImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public int getAppCount();

	public void setAppCount(int appCount);

	public int getContentCount();

	public void setContentCount(int contentCount);

	public int getProjectCount();

	public void setProjectCount(int projectCount);

	public int getDataCount();

	public void setDataCount(int dataCount);

	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getAppResults();

	public void setAppResults(
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> appResults);

	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getContentResults();

	public void setContentResults(
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> contentResults);

	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getProjectResults();

	public void setProjectResults(
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> projectResults);

	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getDataResults();

	public void setDataResults(
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> dataResults);
}