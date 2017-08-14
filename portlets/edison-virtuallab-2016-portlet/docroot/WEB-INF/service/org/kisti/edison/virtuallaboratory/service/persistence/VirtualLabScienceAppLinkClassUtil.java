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

package org.kisti.edison.virtuallaboratory.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass;

import java.util.List;

/**
 * The persistence utility for the virtual lab science app link class service. This utility wraps {@link VirtualLabScienceAppLinkClassPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see VirtualLabScienceAppLinkClassPersistence
 * @see VirtualLabScienceAppLinkClassPersistenceImpl
 * @generated
 */
public class VirtualLabScienceAppLinkClassUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass) {
		getPersistence().clearCache(virtualLabScienceAppLinkClass);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<VirtualLabScienceAppLinkClass> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VirtualLabScienceAppLinkClass> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VirtualLabScienceAppLinkClass> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static VirtualLabScienceAppLinkClass update(
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass)
		throws SystemException {
		return getPersistence().update(virtualLabScienceAppLinkClass);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static VirtualLabScienceAppLinkClass update(
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(virtualLabScienceAppLinkClass, serviceContext);
	}

	/**
	* Returns all the virtual lab science app link classes where classId = &#63;.
	*
	* @param classId the class ID
	* @return the matching virtual lab science app link classes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass> findByClassId(
		long classId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByClassId(classId);
	}

	/**
	* Returns a range of all the virtual lab science app link classes where classId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classId the class ID
	* @param start the lower bound of the range of virtual lab science app link classes
	* @param end the upper bound of the range of virtual lab science app link classes (not inclusive)
	* @return the range of matching virtual lab science app link classes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass> findByClassId(
		long classId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByClassId(classId, start, end);
	}

	/**
	* Returns an ordered range of all the virtual lab science app link classes where classId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classId the class ID
	* @param start the lower bound of the range of virtual lab science app link classes
	* @param end the upper bound of the range of virtual lab science app link classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching virtual lab science app link classes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass> findByClassId(
		long classId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByClassId(classId, start, end, orderByComparator);
	}

	/**
	* Returns the first virtual lab science app link class in the ordered set where classId = &#63;.
	*
	* @param classId the class ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching virtual lab science app link class
	* @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a matching virtual lab science app link class could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass findByClassId_First(
		long classId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException {
		return getPersistence().findByClassId_First(classId, orderByComparator);
	}

	/**
	* Returns the first virtual lab science app link class in the ordered set where classId = &#63;.
	*
	* @param classId the class ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching virtual lab science app link class, or <code>null</code> if a matching virtual lab science app link class could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass fetchByClassId_First(
		long classId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByClassId_First(classId, orderByComparator);
	}

	/**
	* Returns the last virtual lab science app link class in the ordered set where classId = &#63;.
	*
	* @param classId the class ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching virtual lab science app link class
	* @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a matching virtual lab science app link class could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass findByClassId_Last(
		long classId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException {
		return getPersistence().findByClassId_Last(classId, orderByComparator);
	}

	/**
	* Returns the last virtual lab science app link class in the ordered set where classId = &#63;.
	*
	* @param classId the class ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching virtual lab science app link class, or <code>null</code> if a matching virtual lab science app link class could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass fetchByClassId_Last(
		long classId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByClassId_Last(classId, orderByComparator);
	}

	/**
	* Returns the virtual lab science app link classes before and after the current virtual lab science app link class in the ordered set where classId = &#63;.
	*
	* @param scienceAppClassSeqNo the primary key of the current virtual lab science app link class
	* @param classId the class ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next virtual lab science app link class
	* @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a virtual lab science app link class with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass[] findByClassId_PrevAndNext(
		long scienceAppClassSeqNo, long classId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException {
		return getPersistence()
				   .findByClassId_PrevAndNext(scienceAppClassSeqNo, classId,
			orderByComparator);
	}

	/**
	* Removes all the virtual lab science app link classes where classId = &#63; from the database.
	*
	* @param classId the class ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByClassId(long classId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByClassId(classId);
	}

	/**
	* Returns the number of virtual lab science app link classes where classId = &#63;.
	*
	* @param classId the class ID
	* @return the number of matching virtual lab science app link classes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByClassId(long classId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByClassId(classId);
	}

	/**
	* Caches the virtual lab science app link class in the entity cache if it is enabled.
	*
	* @param virtualLabScienceAppLinkClass the virtual lab science app link class
	*/
	public static void cacheResult(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass) {
		getPersistence().cacheResult(virtualLabScienceAppLinkClass);
	}

	/**
	* Caches the virtual lab science app link classes in the entity cache if it is enabled.
	*
	* @param virtualLabScienceAppLinkClasses the virtual lab science app link classes
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass> virtualLabScienceAppLinkClasses) {
		getPersistence().cacheResult(virtualLabScienceAppLinkClasses);
	}

	/**
	* Creates a new virtual lab science app link class with the primary key. Does not add the virtual lab science app link class to the database.
	*
	* @param scienceAppClassSeqNo the primary key for the new virtual lab science app link class
	* @return the new virtual lab science app link class
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass create(
		long scienceAppClassSeqNo) {
		return getPersistence().create(scienceAppClassSeqNo);
	}

	/**
	* Removes the virtual lab science app link class with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppClassSeqNo the primary key of the virtual lab science app link class
	* @return the virtual lab science app link class that was removed
	* @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a virtual lab science app link class with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass remove(
		long scienceAppClassSeqNo)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException {
		return getPersistence().remove(scienceAppClassSeqNo);
	}

	public static org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass updateImpl(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(virtualLabScienceAppLinkClass);
	}

	/**
	* Returns the virtual lab science app link class with the primary key or throws a {@link org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException} if it could not be found.
	*
	* @param scienceAppClassSeqNo the primary key of the virtual lab science app link class
	* @return the virtual lab science app link class
	* @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException if a virtual lab science app link class with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass findByPrimaryKey(
		long scienceAppClassSeqNo)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchVirtualLabScienceAppLinkClassException {
		return getPersistence().findByPrimaryKey(scienceAppClassSeqNo);
	}

	/**
	* Returns the virtual lab science app link class with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scienceAppClassSeqNo the primary key of the virtual lab science app link class
	* @return the virtual lab science app link class, or <code>null</code> if a virtual lab science app link class with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass fetchByPrimaryKey(
		long scienceAppClassSeqNo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(scienceAppClassSeqNo);
	}

	/**
	* Returns all the virtual lab science app link classes.
	*
	* @return the virtual lab science app link classes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the virtual lab science app link classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual lab science app link classes
	* @param end the upper bound of the range of virtual lab science app link classes (not inclusive)
	* @return the range of virtual lab science app link classes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the virtual lab science app link classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabScienceAppLinkClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual lab science app link classes
	* @param end the upper bound of the range of virtual lab science app link classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of virtual lab science app link classes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the virtual lab science app link classes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of virtual lab science app link classes.
	*
	* @return the number of virtual lab science app link classes
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static VirtualLabScienceAppLinkClassPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (VirtualLabScienceAppLinkClassPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.virtuallaboratory.service.ClpSerializer.getServletContextName(),
					VirtualLabScienceAppLinkClassPersistence.class.getName());

			ReferenceRegistry.registerReference(VirtualLabScienceAppLinkClassUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(
		VirtualLabScienceAppLinkClassPersistence persistence) {
	}

	private static VirtualLabScienceAppLinkClassPersistence _persistence;
}