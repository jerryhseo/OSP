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

package com.kisti.osp.icecap.service.base;

import com.kisti.osp.icecap.model.DataCollection;
import com.kisti.osp.icecap.service.DataCollectionService;
import com.kisti.osp.icecap.service.persistence.DataCollectionFinder;
import com.kisti.osp.icecap.service.persistence.DataCollectionLayoutPersistence;
import com.kisti.osp.icecap.service.persistence.DataCollectionPersistence;
import com.kisti.osp.icecap.service.persistence.DataEntryFinder;
import com.kisti.osp.icecap.service.persistence.DataEntryPersistence;
import com.kisti.osp.icecap.service.persistence.DataEntryProvenancePersistence;
import com.kisti.osp.icecap.service.persistence.DataTypeAnalyzerPersistence;
import com.kisti.osp.icecap.service.persistence.DataTypeEditorPersistence;
import com.kisti.osp.icecap.service.persistence.DataTypePersistence;
import com.kisti.osp.icecap.service.persistence.DataTypeStructurePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetLinkPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the data collection remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.kisti.osp.icecap.service.impl.DataCollectionServiceImpl}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.impl.DataCollectionServiceImpl
 * @see com.kisti.osp.icecap.service.DataCollectionServiceUtil
 * @generated
 */
public abstract class DataCollectionServiceBaseImpl extends BaseServiceImpl
	implements DataCollectionService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.kisti.osp.icecap.service.DataCollectionServiceUtil} to access the data collection remote service.
	 */

	/**
	 * Returns the data collection local service.
	 *
	 * @return the data collection local service
	 */
	public com.kisti.osp.icecap.service.DataCollectionLocalService getDataCollectionLocalService() {
		return dataCollectionLocalService;
	}

	/**
	 * Sets the data collection local service.
	 *
	 * @param dataCollectionLocalService the data collection local service
	 */
	public void setDataCollectionLocalService(
		com.kisti.osp.icecap.service.DataCollectionLocalService dataCollectionLocalService) {
		this.dataCollectionLocalService = dataCollectionLocalService;
	}

	/**
	 * Returns the data collection remote service.
	 *
	 * @return the data collection remote service
	 */
	public com.kisti.osp.icecap.service.DataCollectionService getDataCollectionService() {
		return dataCollectionService;
	}

	/**
	 * Sets the data collection remote service.
	 *
	 * @param dataCollectionService the data collection remote service
	 */
	public void setDataCollectionService(
		com.kisti.osp.icecap.service.DataCollectionService dataCollectionService) {
		this.dataCollectionService = dataCollectionService;
	}

	/**
	 * Returns the data collection persistence.
	 *
	 * @return the data collection persistence
	 */
	public DataCollectionPersistence getDataCollectionPersistence() {
		return dataCollectionPersistence;
	}

	/**
	 * Sets the data collection persistence.
	 *
	 * @param dataCollectionPersistence the data collection persistence
	 */
	public void setDataCollectionPersistence(
		DataCollectionPersistence dataCollectionPersistence) {
		this.dataCollectionPersistence = dataCollectionPersistence;
	}

	/**
	 * Returns the data collection finder.
	 *
	 * @return the data collection finder
	 */
	public DataCollectionFinder getDataCollectionFinder() {
		return dataCollectionFinder;
	}

	/**
	 * Sets the data collection finder.
	 *
	 * @param dataCollectionFinder the data collection finder
	 */
	public void setDataCollectionFinder(
		DataCollectionFinder dataCollectionFinder) {
		this.dataCollectionFinder = dataCollectionFinder;
	}

	/**
	 * Returns the data collection layout local service.
	 *
	 * @return the data collection layout local service
	 */
	public com.kisti.osp.icecap.service.DataCollectionLayoutLocalService getDataCollectionLayoutLocalService() {
		return dataCollectionLayoutLocalService;
	}

	/**
	 * Sets the data collection layout local service.
	 *
	 * @param dataCollectionLayoutLocalService the data collection layout local service
	 */
	public void setDataCollectionLayoutLocalService(
		com.kisti.osp.icecap.service.DataCollectionLayoutLocalService dataCollectionLayoutLocalService) {
		this.dataCollectionLayoutLocalService = dataCollectionLayoutLocalService;
	}

	/**
	 * Returns the data collection layout remote service.
	 *
	 * @return the data collection layout remote service
	 */
	public com.kisti.osp.icecap.service.DataCollectionLayoutService getDataCollectionLayoutService() {
		return dataCollectionLayoutService;
	}

	/**
	 * Sets the data collection layout remote service.
	 *
	 * @param dataCollectionLayoutService the data collection layout remote service
	 */
	public void setDataCollectionLayoutService(
		com.kisti.osp.icecap.service.DataCollectionLayoutService dataCollectionLayoutService) {
		this.dataCollectionLayoutService = dataCollectionLayoutService;
	}

	/**
	 * Returns the data collection layout persistence.
	 *
	 * @return the data collection layout persistence
	 */
	public DataCollectionLayoutPersistence getDataCollectionLayoutPersistence() {
		return dataCollectionLayoutPersistence;
	}

	/**
	 * Sets the data collection layout persistence.
	 *
	 * @param dataCollectionLayoutPersistence the data collection layout persistence
	 */
	public void setDataCollectionLayoutPersistence(
		DataCollectionLayoutPersistence dataCollectionLayoutPersistence) {
		this.dataCollectionLayoutPersistence = dataCollectionLayoutPersistence;
	}

	/**
	 * Returns the data entry local service.
	 *
	 * @return the data entry local service
	 */
	public com.kisti.osp.icecap.service.DataEntryLocalService getDataEntryLocalService() {
		return dataEntryLocalService;
	}

	/**
	 * Sets the data entry local service.
	 *
	 * @param dataEntryLocalService the data entry local service
	 */
	public void setDataEntryLocalService(
		com.kisti.osp.icecap.service.DataEntryLocalService dataEntryLocalService) {
		this.dataEntryLocalService = dataEntryLocalService;
	}

	/**
	 * Returns the data entry remote service.
	 *
	 * @return the data entry remote service
	 */
	public com.kisti.osp.icecap.service.DataEntryService getDataEntryService() {
		return dataEntryService;
	}

	/**
	 * Sets the data entry remote service.
	 *
	 * @param dataEntryService the data entry remote service
	 */
	public void setDataEntryService(
		com.kisti.osp.icecap.service.DataEntryService dataEntryService) {
		this.dataEntryService = dataEntryService;
	}

	/**
	 * Returns the data entry persistence.
	 *
	 * @return the data entry persistence
	 */
	public DataEntryPersistence getDataEntryPersistence() {
		return dataEntryPersistence;
	}

	/**
	 * Sets the data entry persistence.
	 *
	 * @param dataEntryPersistence the data entry persistence
	 */
	public void setDataEntryPersistence(
		DataEntryPersistence dataEntryPersistence) {
		this.dataEntryPersistence = dataEntryPersistence;
	}

	/**
	 * Returns the data entry finder.
	 *
	 * @return the data entry finder
	 */
	public DataEntryFinder getDataEntryFinder() {
		return dataEntryFinder;
	}

	/**
	 * Sets the data entry finder.
	 *
	 * @param dataEntryFinder the data entry finder
	 */
	public void setDataEntryFinder(DataEntryFinder dataEntryFinder) {
		this.dataEntryFinder = dataEntryFinder;
	}

	/**
	 * Returns the data entry provenance local service.
	 *
	 * @return the data entry provenance local service
	 */
	public com.kisti.osp.icecap.service.DataEntryProvenanceLocalService getDataEntryProvenanceLocalService() {
		return dataEntryProvenanceLocalService;
	}

	/**
	 * Sets the data entry provenance local service.
	 *
	 * @param dataEntryProvenanceLocalService the data entry provenance local service
	 */
	public void setDataEntryProvenanceLocalService(
		com.kisti.osp.icecap.service.DataEntryProvenanceLocalService dataEntryProvenanceLocalService) {
		this.dataEntryProvenanceLocalService = dataEntryProvenanceLocalService;
	}

	/**
	 * Returns the data entry provenance remote service.
	 *
	 * @return the data entry provenance remote service
	 */
	public com.kisti.osp.icecap.service.DataEntryProvenanceService getDataEntryProvenanceService() {
		return dataEntryProvenanceService;
	}

	/**
	 * Sets the data entry provenance remote service.
	 *
	 * @param dataEntryProvenanceService the data entry provenance remote service
	 */
	public void setDataEntryProvenanceService(
		com.kisti.osp.icecap.service.DataEntryProvenanceService dataEntryProvenanceService) {
		this.dataEntryProvenanceService = dataEntryProvenanceService;
	}

	/**
	 * Returns the data entry provenance persistence.
	 *
	 * @return the data entry provenance persistence
	 */
	public DataEntryProvenancePersistence getDataEntryProvenancePersistence() {
		return dataEntryProvenancePersistence;
	}

	/**
	 * Sets the data entry provenance persistence.
	 *
	 * @param dataEntryProvenancePersistence the data entry provenance persistence
	 */
	public void setDataEntryProvenancePersistence(
		DataEntryProvenancePersistence dataEntryProvenancePersistence) {
		this.dataEntryProvenancePersistence = dataEntryProvenancePersistence;
	}

	/**
	 * Returns the data type local service.
	 *
	 * @return the data type local service
	 */
	public com.kisti.osp.icecap.service.DataTypeLocalService getDataTypeLocalService() {
		return dataTypeLocalService;
	}

	/**
	 * Sets the data type local service.
	 *
	 * @param dataTypeLocalService the data type local service
	 */
	public void setDataTypeLocalService(
		com.kisti.osp.icecap.service.DataTypeLocalService dataTypeLocalService) {
		this.dataTypeLocalService = dataTypeLocalService;
	}

	/**
	 * Returns the data type remote service.
	 *
	 * @return the data type remote service
	 */
	public com.kisti.osp.icecap.service.DataTypeService getDataTypeService() {
		return dataTypeService;
	}

	/**
	 * Sets the data type remote service.
	 *
	 * @param dataTypeService the data type remote service
	 */
	public void setDataTypeService(
		com.kisti.osp.icecap.service.DataTypeService dataTypeService) {
		this.dataTypeService = dataTypeService;
	}

	/**
	 * Returns the data type persistence.
	 *
	 * @return the data type persistence
	 */
	public DataTypePersistence getDataTypePersistence() {
		return dataTypePersistence;
	}

	/**
	 * Sets the data type persistence.
	 *
	 * @param dataTypePersistence the data type persistence
	 */
	public void setDataTypePersistence(DataTypePersistence dataTypePersistence) {
		this.dataTypePersistence = dataTypePersistence;
	}

	/**
	 * Returns the data type analyzer local service.
	 *
	 * @return the data type analyzer local service
	 */
	public com.kisti.osp.icecap.service.DataTypeAnalyzerLocalService getDataTypeAnalyzerLocalService() {
		return dataTypeAnalyzerLocalService;
	}

	/**
	 * Sets the data type analyzer local service.
	 *
	 * @param dataTypeAnalyzerLocalService the data type analyzer local service
	 */
	public void setDataTypeAnalyzerLocalService(
		com.kisti.osp.icecap.service.DataTypeAnalyzerLocalService dataTypeAnalyzerLocalService) {
		this.dataTypeAnalyzerLocalService = dataTypeAnalyzerLocalService;
	}

	/**
	 * Returns the data type analyzer remote service.
	 *
	 * @return the data type analyzer remote service
	 */
	public com.kisti.osp.icecap.service.DataTypeAnalyzerService getDataTypeAnalyzerService() {
		return dataTypeAnalyzerService;
	}

	/**
	 * Sets the data type analyzer remote service.
	 *
	 * @param dataTypeAnalyzerService the data type analyzer remote service
	 */
	public void setDataTypeAnalyzerService(
		com.kisti.osp.icecap.service.DataTypeAnalyzerService dataTypeAnalyzerService) {
		this.dataTypeAnalyzerService = dataTypeAnalyzerService;
	}

	/**
	 * Returns the data type analyzer persistence.
	 *
	 * @return the data type analyzer persistence
	 */
	public DataTypeAnalyzerPersistence getDataTypeAnalyzerPersistence() {
		return dataTypeAnalyzerPersistence;
	}

	/**
	 * Sets the data type analyzer persistence.
	 *
	 * @param dataTypeAnalyzerPersistence the data type analyzer persistence
	 */
	public void setDataTypeAnalyzerPersistence(
		DataTypeAnalyzerPersistence dataTypeAnalyzerPersistence) {
		this.dataTypeAnalyzerPersistence = dataTypeAnalyzerPersistence;
	}

	/**
	 * Returns the data type editor local service.
	 *
	 * @return the data type editor local service
	 */
	public com.kisti.osp.icecap.service.DataTypeEditorLocalService getDataTypeEditorLocalService() {
		return dataTypeEditorLocalService;
	}

	/**
	 * Sets the data type editor local service.
	 *
	 * @param dataTypeEditorLocalService the data type editor local service
	 */
	public void setDataTypeEditorLocalService(
		com.kisti.osp.icecap.service.DataTypeEditorLocalService dataTypeEditorLocalService) {
		this.dataTypeEditorLocalService = dataTypeEditorLocalService;
	}

	/**
	 * Returns the data type editor remote service.
	 *
	 * @return the data type editor remote service
	 */
	public com.kisti.osp.icecap.service.DataTypeEditorService getDataTypeEditorService() {
		return dataTypeEditorService;
	}

	/**
	 * Sets the data type editor remote service.
	 *
	 * @param dataTypeEditorService the data type editor remote service
	 */
	public void setDataTypeEditorService(
		com.kisti.osp.icecap.service.DataTypeEditorService dataTypeEditorService) {
		this.dataTypeEditorService = dataTypeEditorService;
	}

	/**
	 * Returns the data type editor persistence.
	 *
	 * @return the data type editor persistence
	 */
	public DataTypeEditorPersistence getDataTypeEditorPersistence() {
		return dataTypeEditorPersistence;
	}

	/**
	 * Sets the data type editor persistence.
	 *
	 * @param dataTypeEditorPersistence the data type editor persistence
	 */
	public void setDataTypeEditorPersistence(
		DataTypeEditorPersistence dataTypeEditorPersistence) {
		this.dataTypeEditorPersistence = dataTypeEditorPersistence;
	}

	/**
	 * Returns the data type structure local service.
	 *
	 * @return the data type structure local service
	 */
	public com.kisti.osp.icecap.service.DataTypeStructureLocalService getDataTypeStructureLocalService() {
		return dataTypeStructureLocalService;
	}

	/**
	 * Sets the data type structure local service.
	 *
	 * @param dataTypeStructureLocalService the data type structure local service
	 */
	public void setDataTypeStructureLocalService(
		com.kisti.osp.icecap.service.DataTypeStructureLocalService dataTypeStructureLocalService) {
		this.dataTypeStructureLocalService = dataTypeStructureLocalService;
	}

	/**
	 * Returns the data type structure remote service.
	 *
	 * @return the data type structure remote service
	 */
	public com.kisti.osp.icecap.service.DataTypeStructureService getDataTypeStructureService() {
		return dataTypeStructureService;
	}

	/**
	 * Sets the data type structure remote service.
	 *
	 * @param dataTypeStructureService the data type structure remote service
	 */
	public void setDataTypeStructureService(
		com.kisti.osp.icecap.service.DataTypeStructureService dataTypeStructureService) {
		this.dataTypeStructureService = dataTypeStructureService;
	}

	/**
	 * Returns the data type structure persistence.
	 *
	 * @return the data type structure persistence
	 */
	public DataTypeStructurePersistence getDataTypeStructurePersistence() {
		return dataTypeStructurePersistence;
	}

	/**
	 * Sets the data type structure persistence.
	 *
	 * @param dataTypeStructurePersistence the data type structure persistence
	 */
	public void setDataTypeStructurePersistence(
		DataTypeStructurePersistence dataTypeStructurePersistence) {
		this.dataTypeStructurePersistence = dataTypeStructurePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public com.liferay.portlet.asset.service.AssetEntryLocalService getAssetEntryLocalService() {
		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		com.liferay.portlet.asset.service.AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Returns the asset entry remote service.
	 *
	 * @return the asset entry remote service
	 */
	public com.liferay.portlet.asset.service.AssetEntryService getAssetEntryService() {
		return assetEntryService;
	}

	/**
	 * Sets the asset entry remote service.
	 *
	 * @param assetEntryService the asset entry remote service
	 */
	public void setAssetEntryService(
		com.liferay.portlet.asset.service.AssetEntryService assetEntryService) {
		this.assetEntryService = assetEntryService;
	}

	/**
	 * Returns the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {
		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Returns the asset link local service.
	 *
	 * @return the asset link local service
	 */
	public com.liferay.portlet.asset.service.AssetLinkLocalService getAssetLinkLocalService() {
		return assetLinkLocalService;
	}

	/**
	 * Sets the asset link local service.
	 *
	 * @param assetLinkLocalService the asset link local service
	 */
	public void setAssetLinkLocalService(
		com.liferay.portlet.asset.service.AssetLinkLocalService assetLinkLocalService) {
		this.assetLinkLocalService = assetLinkLocalService;
	}

	/**
	 * Returns the asset link persistence.
	 *
	 * @return the asset link persistence
	 */
	public AssetLinkPersistence getAssetLinkPersistence() {
		return assetLinkPersistence;
	}

	/**
	 * Sets the asset link persistence.
	 *
	 * @param assetLinkPersistence the asset link persistence
	 */
	public void setAssetLinkPersistence(
		AssetLinkPersistence assetLinkPersistence) {
		this.assetLinkPersistence = assetLinkPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();
	}

	public void destroy() {
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return DataCollection.class;
	}

	protected String getModelClassName() {
		return DataCollection.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = dataCollectionPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.kisti.osp.icecap.service.DataCollectionLocalService.class)
	protected com.kisti.osp.icecap.service.DataCollectionLocalService dataCollectionLocalService;
	@BeanReference(type = com.kisti.osp.icecap.service.DataCollectionService.class)
	protected com.kisti.osp.icecap.service.DataCollectionService dataCollectionService;
	@BeanReference(type = DataCollectionPersistence.class)
	protected DataCollectionPersistence dataCollectionPersistence;
	@BeanReference(type = DataCollectionFinder.class)
	protected DataCollectionFinder dataCollectionFinder;
	@BeanReference(type = com.kisti.osp.icecap.service.DataCollectionLayoutLocalService.class)
	protected com.kisti.osp.icecap.service.DataCollectionLayoutLocalService dataCollectionLayoutLocalService;
	@BeanReference(type = com.kisti.osp.icecap.service.DataCollectionLayoutService.class)
	protected com.kisti.osp.icecap.service.DataCollectionLayoutService dataCollectionLayoutService;
	@BeanReference(type = DataCollectionLayoutPersistence.class)
	protected DataCollectionLayoutPersistence dataCollectionLayoutPersistence;
	@BeanReference(type = com.kisti.osp.icecap.service.DataEntryLocalService.class)
	protected com.kisti.osp.icecap.service.DataEntryLocalService dataEntryLocalService;
	@BeanReference(type = com.kisti.osp.icecap.service.DataEntryService.class)
	protected com.kisti.osp.icecap.service.DataEntryService dataEntryService;
	@BeanReference(type = DataEntryPersistence.class)
	protected DataEntryPersistence dataEntryPersistence;
	@BeanReference(type = DataEntryFinder.class)
	protected DataEntryFinder dataEntryFinder;
	@BeanReference(type = com.kisti.osp.icecap.service.DataEntryProvenanceLocalService.class)
	protected com.kisti.osp.icecap.service.DataEntryProvenanceLocalService dataEntryProvenanceLocalService;
	@BeanReference(type = com.kisti.osp.icecap.service.DataEntryProvenanceService.class)
	protected com.kisti.osp.icecap.service.DataEntryProvenanceService dataEntryProvenanceService;
	@BeanReference(type = DataEntryProvenancePersistence.class)
	protected DataEntryProvenancePersistence dataEntryProvenancePersistence;
	@BeanReference(type = com.kisti.osp.icecap.service.DataTypeLocalService.class)
	protected com.kisti.osp.icecap.service.DataTypeLocalService dataTypeLocalService;
	@BeanReference(type = com.kisti.osp.icecap.service.DataTypeService.class)
	protected com.kisti.osp.icecap.service.DataTypeService dataTypeService;
	@BeanReference(type = DataTypePersistence.class)
	protected DataTypePersistence dataTypePersistence;
	@BeanReference(type = com.kisti.osp.icecap.service.DataTypeAnalyzerLocalService.class)
	protected com.kisti.osp.icecap.service.DataTypeAnalyzerLocalService dataTypeAnalyzerLocalService;
	@BeanReference(type = com.kisti.osp.icecap.service.DataTypeAnalyzerService.class)
	protected com.kisti.osp.icecap.service.DataTypeAnalyzerService dataTypeAnalyzerService;
	@BeanReference(type = DataTypeAnalyzerPersistence.class)
	protected DataTypeAnalyzerPersistence dataTypeAnalyzerPersistence;
	@BeanReference(type = com.kisti.osp.icecap.service.DataTypeEditorLocalService.class)
	protected com.kisti.osp.icecap.service.DataTypeEditorLocalService dataTypeEditorLocalService;
	@BeanReference(type = com.kisti.osp.icecap.service.DataTypeEditorService.class)
	protected com.kisti.osp.icecap.service.DataTypeEditorService dataTypeEditorService;
	@BeanReference(type = DataTypeEditorPersistence.class)
	protected DataTypeEditorPersistence dataTypeEditorPersistence;
	@BeanReference(type = com.kisti.osp.icecap.service.DataTypeStructureLocalService.class)
	protected com.kisti.osp.icecap.service.DataTypeStructureLocalService dataTypeStructureLocalService;
	@BeanReference(type = com.kisti.osp.icecap.service.DataTypeStructureService.class)
	protected com.kisti.osp.icecap.service.DataTypeStructureService dataTypeStructureService;
	@BeanReference(type = DataTypeStructurePersistence.class)
	protected DataTypeStructurePersistence dataTypeStructurePersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetEntryLocalService.class)
	protected com.liferay.portlet.asset.service.AssetEntryLocalService assetEntryLocalService;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetEntryService.class)
	protected com.liferay.portlet.asset.service.AssetEntryService assetEntryService;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetLinkLocalService.class)
	protected com.liferay.portlet.asset.service.AssetLinkLocalService assetLinkLocalService;
	@BeanReference(type = AssetLinkPersistence.class)
	protected AssetLinkPersistence assetLinkPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private DataCollectionServiceClpInvoker _clpInvoker = new DataCollectionServiceClpInvoker();
}