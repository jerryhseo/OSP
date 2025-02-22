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

package kisti.edison.challenge.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import kisti.edison.challenge.model.ChallengeEvaluationScore;
import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;

/**
 * The extended model base implementation for the ChallengeEvaluationScore service. Represents a row in the &quot;challenge_ChallengeEvaluationScore&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ChallengeEvaluationScoreImpl}.
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationScoreImpl
 * @see kisti.edison.challenge.model.ChallengeEvaluationScore
 * @generated
 */
public abstract class ChallengeEvaluationScoreBaseImpl
	extends ChallengeEvaluationScoreModelImpl
	implements ChallengeEvaluationScore {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a challenge evaluation score model instance should use the {@link ChallengeEvaluationScore} interface instead.
	 */
	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ChallengeEvaluationScoreLocalServiceUtil.addChallengeEvaluationScore(this);
		}
		else {
			ChallengeEvaluationScoreLocalServiceUtil.updateChallengeEvaluationScore(this);
		}
	}
}