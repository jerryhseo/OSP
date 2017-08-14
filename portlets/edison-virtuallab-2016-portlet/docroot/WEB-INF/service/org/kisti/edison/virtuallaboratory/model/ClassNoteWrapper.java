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

package org.kisti.edison.virtuallaboratory.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ClassNote}.
 * </p>
 *
 * @author EDISON
 * @see ClassNote
 * @generated
 */
public class ClassNoteWrapper implements ClassNote, ModelWrapper<ClassNote> {
	public ClassNoteWrapper(ClassNote classNote) {
		_classNote = classNote;
	}

	@Override
	public Class<?> getModelClass() {
		return ClassNote.class;
	}

	@Override
	public String getModelClassName() {
		return ClassNote.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("classNoteSeq", getClassNoteSeq());
		attributes.put("classId", getClassId());
		attributes.put("contentSeq", getContentSeq());
		attributes.put("isContent", getIsContent());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("description", getDescription());
		attributes.put("insertDate", getInsertDate());
		attributes.put("updateDate", getUpdateDate());
		attributes.put("insertId", getInsertId());
		attributes.put("updateId", getUpdateId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long classNoteSeq = (Long)attributes.get("classNoteSeq");

		if (classNoteSeq != null) {
			setClassNoteSeq(classNoteSeq);
		}

		Long classId = (Long)attributes.get("classId");

		if (classId != null) {
			setClassId(classId);
		}

		Long contentSeq = (Long)attributes.get("contentSeq");

		if (contentSeq != null) {
			setContentSeq(contentSeq);
		}

		String isContent = (String)attributes.get("isContent");

		if (isContent != null) {
			setIsContent(isContent);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date insertDate = (Date)attributes.get("insertDate");

		if (insertDate != null) {
			setInsertDate(insertDate);
		}

		Date updateDate = (Date)attributes.get("updateDate");

		if (updateDate != null) {
			setUpdateDate(updateDate);
		}

		Long insertId = (Long)attributes.get("insertId");

		if (insertId != null) {
			setInsertId(insertId);
		}

		Long updateId = (Long)attributes.get("updateId");

		if (updateId != null) {
			setUpdateId(updateId);
		}
	}

	/**
	* Returns the primary key of this class note.
	*
	* @return the primary key of this class note
	*/
	@Override
	public long getPrimaryKey() {
		return _classNote.getPrimaryKey();
	}

	/**
	* Sets the primary key of this class note.
	*
	* @param primaryKey the primary key of this class note
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_classNote.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the class note seq of this class note.
	*
	* @return the class note seq of this class note
	*/
	@Override
	public long getClassNoteSeq() {
		return _classNote.getClassNoteSeq();
	}

	/**
	* Sets the class note seq of this class note.
	*
	* @param classNoteSeq the class note seq of this class note
	*/
	@Override
	public void setClassNoteSeq(long classNoteSeq) {
		_classNote.setClassNoteSeq(classNoteSeq);
	}

	/**
	* Returns the class ID of this class note.
	*
	* @return the class ID of this class note
	*/
	@Override
	public long getClassId() {
		return _classNote.getClassId();
	}

	/**
	* Sets the class ID of this class note.
	*
	* @param classId the class ID of this class note
	*/
	@Override
	public void setClassId(long classId) {
		_classNote.setClassId(classId);
	}

	/**
	* Returns the content seq of this class note.
	*
	* @return the content seq of this class note
	*/
	@Override
	public long getContentSeq() {
		return _classNote.getContentSeq();
	}

	/**
	* Sets the content seq of this class note.
	*
	* @param contentSeq the content seq of this class note
	*/
	@Override
	public void setContentSeq(long contentSeq) {
		_classNote.setContentSeq(contentSeq);
	}

	/**
	* Returns the is content of this class note.
	*
	* @return the is content of this class note
	*/
	@Override
	public java.lang.String getIsContent() {
		return _classNote.getIsContent();
	}

	/**
	* Sets the is content of this class note.
	*
	* @param isContent the is content of this class note
	*/
	@Override
	public void setIsContent(java.lang.String isContent) {
		_classNote.setIsContent(isContent);
	}

	/**
	* Returns the file entry ID of this class note.
	*
	* @return the file entry ID of this class note
	*/
	@Override
	public long getFileEntryId() {
		return _classNote.getFileEntryId();
	}

	/**
	* Sets the file entry ID of this class note.
	*
	* @param fileEntryId the file entry ID of this class note
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_classNote.setFileEntryId(fileEntryId);
	}

	/**
	* Returns the description of this class note.
	*
	* @return the description of this class note
	*/
	@Override
	public java.lang.String getDescription() {
		return _classNote.getDescription();
	}

	/**
	* Returns the localized description of this class note in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this class note
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _classNote.getDescription(locale);
	}

	/**
	* Returns the localized description of this class note in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this class note. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _classNote.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this class note in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this class note
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _classNote.getDescription(languageId);
	}

	/**
	* Returns the localized description of this class note in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this class note
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _classNote.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _classNote.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _classNote.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this class note.
	*
	* @return the locales and localized descriptions of this class note
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _classNote.getDescriptionMap();
	}

	/**
	* Sets the description of this class note.
	*
	* @param description the description of this class note
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_classNote.setDescription(description);
	}

	/**
	* Sets the localized description of this class note in the language.
	*
	* @param description the localized description of this class note
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_classNote.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this class note in the language, and sets the default locale.
	*
	* @param description the localized description of this class note
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_classNote.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_classNote.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this class note from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this class note
	*/
	@Override
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_classNote.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this class note from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this class note
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_classNote.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Returns the insert date of this class note.
	*
	* @return the insert date of this class note
	*/
	@Override
	public java.util.Date getInsertDate() {
		return _classNote.getInsertDate();
	}

	/**
	* Sets the insert date of this class note.
	*
	* @param insertDate the insert date of this class note
	*/
	@Override
	public void setInsertDate(java.util.Date insertDate) {
		_classNote.setInsertDate(insertDate);
	}

	/**
	* Returns the update date of this class note.
	*
	* @return the update date of this class note
	*/
	@Override
	public java.util.Date getUpdateDate() {
		return _classNote.getUpdateDate();
	}

	/**
	* Sets the update date of this class note.
	*
	* @param updateDate the update date of this class note
	*/
	@Override
	public void setUpdateDate(java.util.Date updateDate) {
		_classNote.setUpdateDate(updateDate);
	}

	/**
	* Returns the insert ID of this class note.
	*
	* @return the insert ID of this class note
	*/
	@Override
	public long getInsertId() {
		return _classNote.getInsertId();
	}

	/**
	* Sets the insert ID of this class note.
	*
	* @param insertId the insert ID of this class note
	*/
	@Override
	public void setInsertId(long insertId) {
		_classNote.setInsertId(insertId);
	}

	/**
	* Returns the update ID of this class note.
	*
	* @return the update ID of this class note
	*/
	@Override
	public long getUpdateId() {
		return _classNote.getUpdateId();
	}

	/**
	* Sets the update ID of this class note.
	*
	* @param updateId the update ID of this class note
	*/
	@Override
	public void setUpdateId(long updateId) {
		_classNote.setUpdateId(updateId);
	}

	@Override
	public boolean isNew() {
		return _classNote.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_classNote.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _classNote.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_classNote.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _classNote.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _classNote.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_classNote.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _classNote.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_classNote.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_classNote.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_classNote.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _classNote.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _classNote.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_classNote.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_classNote.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new ClassNoteWrapper((ClassNote)_classNote.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote) {
		return _classNote.compareTo(classNote);
	}

	@Override
	public int hashCode() {
		return _classNote.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.virtuallaboratory.model.ClassNote> toCacheModel() {
		return _classNote.toCacheModel();
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote toEscapedModel() {
		return new ClassNoteWrapper(_classNote.toEscapedModel());
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote toUnescapedModel() {
		return new ClassNoteWrapper(_classNote.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _classNote.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _classNote.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_classNote.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ClassNoteWrapper)) {
			return false;
		}

		ClassNoteWrapper classNoteWrapper = (ClassNoteWrapper)obj;

		if (Validator.equals(_classNote, classNoteWrapper._classNote)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ClassNote getWrappedClassNote() {
		return _classNote;
	}

	@Override
	public ClassNote getWrappedModel() {
		return _classNote;
	}

	@Override
	public void resetOriginalValues() {
		_classNote.resetOriginalValues();
	}

	private ClassNote _classNote;
}