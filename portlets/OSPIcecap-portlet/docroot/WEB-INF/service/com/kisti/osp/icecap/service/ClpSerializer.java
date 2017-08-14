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

package com.kisti.osp.icecap.service;

import com.kisti.osp.icecap.model.DataCollectionClp;
import com.kisti.osp.icecap.model.DataCollectionLayoutClp;
import com.kisti.osp.icecap.model.DataEntryClp;
import com.kisti.osp.icecap.model.DataEntryProvenanceClp;
import com.kisti.osp.icecap.model.DataTypeAnalyzerClp;
import com.kisti.osp.icecap.model.DataTypeClp;
import com.kisti.osp.icecap.model.DataTypeEditorClp;
import com.kisti.osp.icecap.model.DataTypeStructureClp;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Young-K. Suh and Jerry H. Seo
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"OSPIcecap-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"OSPIcecap-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "OSPIcecap-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(DataCollectionClp.class.getName())) {
			return translateInputDataCollection(oldModel);
		}

		if (oldModelClassName.equals(DataCollectionLayoutClp.class.getName())) {
			return translateInputDataCollectionLayout(oldModel);
		}

		if (oldModelClassName.equals(DataEntryClp.class.getName())) {
			return translateInputDataEntry(oldModel);
		}

		if (oldModelClassName.equals(DataEntryProvenanceClp.class.getName())) {
			return translateInputDataEntryProvenance(oldModel);
		}

		if (oldModelClassName.equals(DataTypeClp.class.getName())) {
			return translateInputDataType(oldModel);
		}

		if (oldModelClassName.equals(DataTypeAnalyzerClp.class.getName())) {
			return translateInputDataTypeAnalyzer(oldModel);
		}

		if (oldModelClassName.equals(DataTypeEditorClp.class.getName())) {
			return translateInputDataTypeEditor(oldModel);
		}

		if (oldModelClassName.equals(DataTypeStructureClp.class.getName())) {
			return translateInputDataTypeStructure(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputDataCollection(BaseModel<?> oldModel) {
		DataCollectionClp oldClpModel = (DataCollectionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDataCollectionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputDataCollectionLayout(
		BaseModel<?> oldModel) {
		DataCollectionLayoutClp oldClpModel = (DataCollectionLayoutClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDataCollectionLayoutRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputDataEntry(BaseModel<?> oldModel) {
		DataEntryClp oldClpModel = (DataEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDataEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputDataEntryProvenance(
		BaseModel<?> oldModel) {
		DataEntryProvenanceClp oldClpModel = (DataEntryProvenanceClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDataEntryProvenanceRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputDataType(BaseModel<?> oldModel) {
		DataTypeClp oldClpModel = (DataTypeClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDataTypeRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputDataTypeAnalyzer(BaseModel<?> oldModel) {
		DataTypeAnalyzerClp oldClpModel = (DataTypeAnalyzerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDataTypeAnalyzerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputDataTypeEditor(BaseModel<?> oldModel) {
		DataTypeEditorClp oldClpModel = (DataTypeEditorClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDataTypeEditorRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputDataTypeStructure(BaseModel<?> oldModel) {
		DataTypeStructureClp oldClpModel = (DataTypeStructureClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDataTypeStructureRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.kisti.osp.icecap.model.impl.DataCollectionImpl")) {
			return translateOutputDataCollection(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.kisti.osp.icecap.model.impl.DataCollectionLayoutImpl")) {
			return translateOutputDataCollectionLayout(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.kisti.osp.icecap.model.impl.DataEntryImpl")) {
			return translateOutputDataEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.kisti.osp.icecap.model.impl.DataEntryProvenanceImpl")) {
			return translateOutputDataEntryProvenance(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.kisti.osp.icecap.model.impl.DataTypeImpl")) {
			return translateOutputDataType(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.kisti.osp.icecap.model.impl.DataTypeAnalyzerImpl")) {
			return translateOutputDataTypeAnalyzer(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.kisti.osp.icecap.model.impl.DataTypeEditorImpl")) {
			return translateOutputDataTypeEditor(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.kisti.osp.icecap.model.impl.DataTypeStructureImpl")) {
			return translateOutputDataTypeStructure(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Throwable translateThrowable(Throwable throwable) {
		if (_useReflectionToTranslateThrowable) {
			try {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

				objectOutputStream.writeObject(throwable);

				objectOutputStream.flush();
				objectOutputStream.close();

				UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
						0, unsyncByteArrayOutputStream.size());

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader = currentThread.getContextClassLoader();

				ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
						contextClassLoader);

				throwable = (Throwable)objectInputStream.readObject();

				objectInputStream.close();

				return throwable;
			}
			catch (SecurityException se) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (Throwable throwable2) {
				_log.error(throwable2, throwable2);

				return throwable2;
			}
		}

		Class<?> clazz = throwable.getClass();

		String className = clazz.getName();

		if (className.equals(PortalException.class.getName())) {
			return new PortalException();
		}

		if (className.equals(SystemException.class.getName())) {
			return new SystemException();
		}

		if (className.equals("com.kisti.osp.icecap.DataCollectionException")) {
			return new com.kisti.osp.icecap.DataCollectionException();
		}

		if (className.equals("com.kisti.osp.icecap.DataEntryException")) {
			return new com.kisti.osp.icecap.DataEntryException();
		}

		if (className.equals("com.kisti.osp.icecap.DataTypeException")) {
			return new com.kisti.osp.icecap.DataTypeException();
		}

		if (className.equals("com.kisti.osp.icecap.DataTypeAnalyzerException")) {
			return new com.kisti.osp.icecap.DataTypeAnalyzerException();
		}

		if (className.equals("com.kisti.osp.icecap.DataTypeEditorException")) {
			return new com.kisti.osp.icecap.DataTypeEditorException();
		}

		if (className.equals("com.kisti.osp.icecap.DataTypeStructureException")) {
			return new com.kisti.osp.icecap.DataTypeStructureException();
		}

		if (className.equals(
					"com.kisti.osp.icecap.NoSuchDataCollectionException")) {
			return new com.kisti.osp.icecap.NoSuchDataCollectionException();
		}

		if (className.equals(
					"com.kisti.osp.icecap.NoSuchDataCollectionLayoutException")) {
			return new com.kisti.osp.icecap.NoSuchDataCollectionLayoutException();
		}

		if (className.equals("com.kisti.osp.icecap.NoSuchDataEntryException")) {
			return new com.kisti.osp.icecap.NoSuchDataEntryException();
		}

		if (className.equals(
					"com.kisti.osp.icecap.NoSuchDataEntryProvenanceException")) {
			return new com.kisti.osp.icecap.NoSuchDataEntryProvenanceException();
		}

		if (className.equals("com.kisti.osp.icecap.NoSuchDataTypeException")) {
			return new com.kisti.osp.icecap.NoSuchDataTypeException();
		}

		if (className.equals(
					"com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException")) {
			return new com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException();
		}

		if (className.equals(
					"com.kisti.osp.icecap.NoSuchDataTypeEditorException")) {
			return new com.kisti.osp.icecap.NoSuchDataTypeEditorException();
		}

		if (className.equals(
					"com.kisti.osp.icecap.NoSuchDataTypeStructureException")) {
			return new com.kisti.osp.icecap.NoSuchDataTypeStructureException();
		}

		return throwable;
	}

	public static Object translateOutputDataCollection(BaseModel<?> oldModel) {
		DataCollectionClp newModel = new DataCollectionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDataCollectionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputDataCollectionLayout(
		BaseModel<?> oldModel) {
		DataCollectionLayoutClp newModel = new DataCollectionLayoutClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDataCollectionLayoutRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputDataEntry(BaseModel<?> oldModel) {
		DataEntryClp newModel = new DataEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDataEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputDataEntryProvenance(
		BaseModel<?> oldModel) {
		DataEntryProvenanceClp newModel = new DataEntryProvenanceClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDataEntryProvenanceRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputDataType(BaseModel<?> oldModel) {
		DataTypeClp newModel = new DataTypeClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDataTypeRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputDataTypeAnalyzer(BaseModel<?> oldModel) {
		DataTypeAnalyzerClp newModel = new DataTypeAnalyzerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDataTypeAnalyzerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputDataTypeEditor(BaseModel<?> oldModel) {
		DataTypeEditorClp newModel = new DataTypeEditorClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDataTypeEditorRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputDataTypeStructure(BaseModel<?> oldModel) {
		DataTypeStructureClp newModel = new DataTypeStructureClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDataTypeStructureRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}