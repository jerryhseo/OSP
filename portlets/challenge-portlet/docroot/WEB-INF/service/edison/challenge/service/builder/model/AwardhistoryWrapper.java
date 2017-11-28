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

package edison.challenge.service.builder.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Awardhistory}.
 * </p>
 *
 * @author kyj
 * @see Awardhistory
 * @generated
 */
public class AwardhistoryWrapper implements Awardhistory,
	ModelWrapper<Awardhistory> {
	public AwardhistoryWrapper(Awardhistory awardhistory) {
		_awardhistory = awardhistory;
	}

	@Override
	public Class<?> getModelClass() {
		return Awardhistory.class;
	}

	@Override
	public String getModelClassName() {
		return Awardhistory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("awardhistoryid", getAwardhistoryid());
		attributes.put("name", getName());
		attributes.put("prize", getPrize());
		attributes.put("chTeamid", getChTeamid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long awardhistoryid = (Long)attributes.get("awardhistoryid");

		if (awardhistoryid != null) {
			setAwardhistoryid(awardhistoryid);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String prize = (String)attributes.get("prize");

		if (prize != null) {
			setPrize(prize);
		}

		Long chTeamid = (Long)attributes.get("chTeamid");

		if (chTeamid != null) {
			setChTeamid(chTeamid);
		}
	}

	/**
	* Returns the primary key of this awardhistory.
	*
	* @return the primary key of this awardhistory
	*/
	@Override
	public long getPrimaryKey() {
		return _awardhistory.getPrimaryKey();
	}

	/**
	* Sets the primary key of this awardhistory.
	*
	* @param primaryKey the primary key of this awardhistory
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_awardhistory.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the awardhistoryid of this awardhistory.
	*
	* @return the awardhistoryid of this awardhistory
	*/
	@Override
	public long getAwardhistoryid() {
		return _awardhistory.getAwardhistoryid();
	}

	/**
	* Sets the awardhistoryid of this awardhistory.
	*
	* @param awardhistoryid the awardhistoryid of this awardhistory
	*/
	@Override
	public void setAwardhistoryid(long awardhistoryid) {
		_awardhistory.setAwardhistoryid(awardhistoryid);
	}

	/**
	* Returns the name of this awardhistory.
	*
	* @return the name of this awardhistory
	*/
	@Override
	public java.lang.String getName() {
		return _awardhistory.getName();
	}

	/**
	* Sets the name of this awardhistory.
	*
	* @param name the name of this awardhistory
	*/
	@Override
	public void setName(java.lang.String name) {
		_awardhistory.setName(name);
	}

	/**
	* Returns the prize of this awardhistory.
	*
	* @return the prize of this awardhistory
	*/
	@Override
	public java.lang.String getPrize() {
		return _awardhistory.getPrize();
	}

	/**
	* Sets the prize of this awardhistory.
	*
	* @param prize the prize of this awardhistory
	*/
	@Override
	public void setPrize(java.lang.String prize) {
		_awardhistory.setPrize(prize);
	}

	/**
	* Returns the ch teamid of this awardhistory.
	*
	* @return the ch teamid of this awardhistory
	*/
	@Override
	public long getChTeamid() {
		return _awardhistory.getChTeamid();
	}

	/**
	* Sets the ch teamid of this awardhistory.
	*
	* @param chTeamid the ch teamid of this awardhistory
	*/
	@Override
	public void setChTeamid(long chTeamid) {
		_awardhistory.setChTeamid(chTeamid);
	}

	@Override
	public boolean isNew() {
		return _awardhistory.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_awardhistory.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _awardhistory.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_awardhistory.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _awardhistory.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _awardhistory.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_awardhistory.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _awardhistory.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_awardhistory.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_awardhistory.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_awardhistory.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AwardhistoryWrapper((Awardhistory)_awardhistory.clone());
	}

	@Override
	public int compareTo(
		edison.challenge.service.builder.model.Awardhistory awardhistory) {
		return _awardhistory.compareTo(awardhistory);
	}

	@Override
	public int hashCode() {
		return _awardhistory.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<edison.challenge.service.builder.model.Awardhistory> toCacheModel() {
		return _awardhistory.toCacheModel();
	}

	@Override
	public edison.challenge.service.builder.model.Awardhistory toEscapedModel() {
		return new AwardhistoryWrapper(_awardhistory.toEscapedModel());
	}

	@Override
	public edison.challenge.service.builder.model.Awardhistory toUnescapedModel() {
		return new AwardhistoryWrapper(_awardhistory.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _awardhistory.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _awardhistory.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_awardhistory.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AwardhistoryWrapper)) {
			return false;
		}

		AwardhistoryWrapper awardhistoryWrapper = (AwardhistoryWrapper)obj;

		if (Validator.equals(_awardhistory, awardhistoryWrapper._awardhistory)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Awardhistory getWrappedAwardhistory() {
		return _awardhistory;
	}

	@Override
	public Awardhistory getWrappedModel() {
		return _awardhistory;
	}

	@Override
	public void resetOriginalValues() {
		_awardhistory.resetOriginalValues();
	}

	private Awardhistory _awardhistory;
}