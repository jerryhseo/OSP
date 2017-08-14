/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.kisti.osp.icecap;

import com.liferay.portal.NoSuchModelException;

/**
 * @author Young-K. Suh and Jerry H. Seo
 */
public class NoSuchDataCollectionException extends NoSuchModelException {

	public NoSuchDataCollectionException() {
		super();
	}

	public NoSuchDataCollectionException(String msg) {
		super(msg);
	}

	public NoSuchDataCollectionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchDataCollectionException(Throwable cause) {
		super(cause);
	}

}