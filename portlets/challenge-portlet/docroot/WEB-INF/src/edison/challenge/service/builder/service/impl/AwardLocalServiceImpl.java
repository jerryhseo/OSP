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

package edison.challenge.service.builder.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;

import edison.challenge.service.builder.model.Award;
import edison.challenge.service.builder.service.base.AwardLocalServiceBaseImpl;

/**
 * The implementation of the award local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link edison.challenge.service.builder.service.AwardLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.base.AwardLocalServiceBaseImpl
 * @see edison.challenge.service.builder.service.AwardLocalServiceUtil
 */
public class AwardLocalServiceImpl extends AwardLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link edison.challenge.service.builder.service.AwardLocalServiceUtil} to access the award local service.
	 */
	
	public List<Award> getAwardByChildCollet(long childChallengeID) throws SystemException{
		return this.awardPersistence.findBychildCollect(childChallengeID);
	}
	
	public int getAwardByChildColletCount(long childChallengeID) throws SystemException{
		return this.awardPersistence.findBychildCollect(childChallengeID).size();
	}
}