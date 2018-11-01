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

package org.kisti.edison.science.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author EDISON
 * @generated
 */
public class ScienceAppPaperPK implements Comparable<ScienceAppPaperPK>,
	Serializable {
	public long scienceAppId;
	public long paperSeq;

	public ScienceAppPaperPK() {
	}

	public ScienceAppPaperPK(long scienceAppId, long paperSeq) {
		this.scienceAppId = scienceAppId;
		this.paperSeq = paperSeq;
	}

	public long getScienceAppId() {
		return scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		this.scienceAppId = scienceAppId;
	}

	public long getPaperSeq() {
		return paperSeq;
	}

	public void setPaperSeq(long paperSeq) {
		this.paperSeq = paperSeq;
	}

	@Override
	public int compareTo(ScienceAppPaperPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (scienceAppId < pk.scienceAppId) {
			value = -1;
		}
		else if (scienceAppId > pk.scienceAppId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (paperSeq < pk.paperSeq) {
			value = -1;
		}
		else if (paperSeq > pk.paperSeq) {
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

		if (!(obj instanceof ScienceAppPaperPK)) {
			return false;
		}

		ScienceAppPaperPK pk = (ScienceAppPaperPK)obj;

		if ((scienceAppId == pk.scienceAppId) && (paperSeq == pk.paperSeq)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(scienceAppId) + String.valueOf(paperSeq)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("scienceAppId");
		sb.append(StringPool.EQUAL);
		sb.append(scienceAppId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("paperSeq");
		sb.append(StringPool.EQUAL);
		sb.append(paperSeq);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}