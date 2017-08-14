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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Young-K. Suh and Jerry H. Seo
 * @generated
 */
public class DataTypeEditorPK implements Comparable<DataTypeEditorPK>,
	Serializable {
	public long typeId;
	public long editorId;

	public DataTypeEditorPK() {
	}

	public DataTypeEditorPK(long typeId, long editorId) {
		this.typeId = typeId;
		this.editorId = editorId;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public long getEditorId() {
		return editorId;
	}

	public void setEditorId(long editorId) {
		this.editorId = editorId;
	}

	@Override
	public int compareTo(DataTypeEditorPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (typeId < pk.typeId) {
			value = -1;
		}
		else if (typeId > pk.typeId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (editorId < pk.editorId) {
			value = -1;
		}
		else if (editorId > pk.editorId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataTypeEditorPK)) {
			return false;
		}

		DataTypeEditorPK pk = (DataTypeEditorPK)obj;

		if ((typeId == pk.typeId) && (editorId == pk.editorId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(typeId) + String.valueOf(editorId)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("typeId");
		sb.append(StringPool.EQUAL);
		sb.append(typeId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("editorId");
		sb.append(StringPool.EQUAL);
		sb.append(editorId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}