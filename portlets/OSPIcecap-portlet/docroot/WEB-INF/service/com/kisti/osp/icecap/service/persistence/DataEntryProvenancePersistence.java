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

import com.kisti.osp.icecap.model.DataEntryProvenance;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the data entry provenance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryProvenancePersistenceImpl
 * @see DataEntryProvenanceUtil
 * @generated
 */
public interface DataEntryProvenancePersistence extends BasePersistence<DataEntryProvenance> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DataEntryProvenanceUtil} to access the data entry provenance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the data entry provenance where inputData = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataEntryProvenanceException} if it could not be found.
	*
	* @param inputData the input data
	* @return the matching data entry provenance
	* @throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException if a matching data entry provenance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance findByinputData(
		java.lang.String inputData)
		throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data entry provenance where inputData = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param inputData the input data
	* @return the matching data entry provenance, or <code>null</code> if a matching data entry provenance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance fetchByinputData(
		java.lang.String inputData)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data entry provenance where inputData = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param inputData the input data
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data entry provenance, or <code>null</code> if a matching data entry provenance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance fetchByinputData(
		java.lang.String inputData, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the data entry provenance where inputData = &#63; from the database.
	*
	* @param inputData the input data
	* @return the data entry provenance that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance removeByinputData(
		java.lang.String inputData)
		throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data entry provenances where inputData = &#63;.
	*
	* @param inputData the input data
	* @return the number of matching data entry provenances
	* @throws SystemException if a system exception occurred
	*/
	public int countByinputData(java.lang.String inputData)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data entry provenance where PROVStructure = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataEntryProvenanceException} if it could not be found.
	*
	* @param PROVStructure the p r o v structure
	* @return the matching data entry provenance
	* @throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException if a matching data entry provenance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance findByPROVStructure(
		java.lang.String PROVStructure)
		throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data entry provenance where PROVStructure = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param PROVStructure the p r o v structure
	* @return the matching data entry provenance, or <code>null</code> if a matching data entry provenance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance fetchByPROVStructure(
		java.lang.String PROVStructure)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data entry provenance where PROVStructure = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param PROVStructure the p r o v structure
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data entry provenance, or <code>null</code> if a matching data entry provenance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance fetchByPROVStructure(
		java.lang.String PROVStructure, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the data entry provenance where PROVStructure = &#63; from the database.
	*
	* @param PROVStructure the p r o v structure
	* @return the data entry provenance that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance removeByPROVStructure(
		java.lang.String PROVStructure)
		throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data entry provenances where PROVStructure = &#63;.
	*
	* @param PROVStructure the p r o v structure
	* @return the number of matching data entry provenances
	* @throws SystemException if a system exception occurred
	*/
	public int countByPROVStructure(java.lang.String PROVStructure)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the data entry provenance in the entity cache if it is enabled.
	*
	* @param dataEntryProvenance the data entry provenance
	*/
	public void cacheResult(
		com.kisti.osp.icecap.model.DataEntryProvenance dataEntryProvenance);

	/**
	* Caches the data entry provenances in the entity cache if it is enabled.
	*
	* @param dataEntryProvenances the data entry provenances
	*/
	public void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataEntryProvenance> dataEntryProvenances);

	/**
	* Creates a new data entry provenance with the primary key. Does not add the data entry provenance to the database.
	*
	* @param entryId the primary key for the new data entry provenance
	* @return the new data entry provenance
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance create(long entryId);

	/**
	* Removes the data entry provenance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the data entry provenance
	* @return the data entry provenance that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException if a data entry provenance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance remove(long entryId)
		throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.kisti.osp.icecap.model.DataEntryProvenance updateImpl(
		com.kisti.osp.icecap.model.DataEntryProvenance dataEntryProvenance)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data entry provenance with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataEntryProvenanceException} if it could not be found.
	*
	* @param entryId the primary key of the data entry provenance
	* @return the data entry provenance
	* @throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException if a data entry provenance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance findByPrimaryKey(
		long entryId)
		throws com.kisti.osp.icecap.NoSuchDataEntryProvenanceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data entry provenance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the data entry provenance
	* @return the data entry provenance, or <code>null</code> if a data entry provenance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntryProvenance fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data entry provenances.
	*
	* @return the data entry provenances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataEntryProvenance> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data entry provenances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryProvenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data entry provenances
	* @param end the upper bound of the range of data entry provenances (not inclusive)
	* @return the range of data entry provenances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataEntryProvenance> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data entry provenances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryProvenanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data entry provenances
	* @param end the upper bound of the range of data entry provenances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of data entry provenances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataEntryProvenance> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data entry provenances from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data entry provenances.
	*
	* @return the number of data entry provenances
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}