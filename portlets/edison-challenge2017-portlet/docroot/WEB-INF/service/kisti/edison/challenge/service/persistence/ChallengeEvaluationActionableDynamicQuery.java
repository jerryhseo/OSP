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

package kisti.edison.challenge.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import kisti.edison.challenge.model.ChallengeEvaluation;
import kisti.edison.challenge.service.ChallengeEvaluationLocalServiceUtil;

/**
 * @author KYJ
 * @generated
 */
public abstract class ChallengeEvaluationActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public ChallengeEvaluationActionableDynamicQuery()
		throws SystemException {
		setBaseLocalService(ChallengeEvaluationLocalServiceUtil.getService());
		setClass(ChallengeEvaluation.class);

		setClassLoader(kisti.edison.challenge.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("challengeEvaluationId");
	}
}