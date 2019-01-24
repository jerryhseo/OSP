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

package org.kisti.edison.bestsimulation.service;

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

import org.kisti.edison.bestsimulation.model.ScienceAppExecuteClp;
import org.kisti.edison.bestsimulation.model.SimulationClp;
import org.kisti.edison.bestsimulation.model.SimulationExeStsMigrationClp;
import org.kisti.edison.bestsimulation.model.SimulationJobClp;
import org.kisti.edison.bestsimulation.model.SimulationJobDataClp;
import org.kisti.edison.bestsimulation.model.SimulationJobStatusClp;
import org.kisti.edison.bestsimulation.model.SimulationShareClp;
import org.kisti.edison.bestsimulation.model.UniversityExecuteClp;
import org.kisti.edison.bestsimulation.model.VirtualLabClassStatisticsClp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EDISON
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
						"edison-simulation-portlet-deployment-context");

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
							"edison-simulation-portlet-deployment-context");

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
				_servletContextName = "edison-simulation-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(ScienceAppExecuteClp.class.getName())) {
			return translateInputScienceAppExecute(oldModel);
		}

		if (oldModelClassName.equals(SimulationClp.class.getName())) {
			return translateInputSimulation(oldModel);
		}

		if (oldModelClassName.equals(
					SimulationExeStsMigrationClp.class.getName())) {
			return translateInputSimulationExeStsMigration(oldModel);
		}

		if (oldModelClassName.equals(SimulationJobClp.class.getName())) {
			return translateInputSimulationJob(oldModel);
		}

		if (oldModelClassName.equals(SimulationJobDataClp.class.getName())) {
			return translateInputSimulationJobData(oldModel);
		}

		if (oldModelClassName.equals(SimulationJobStatusClp.class.getName())) {
			return translateInputSimulationJobStatus(oldModel);
		}

		if (oldModelClassName.equals(SimulationShareClp.class.getName())) {
			return translateInputSimulationShare(oldModel);
		}

		if (oldModelClassName.equals(UniversityExecuteClp.class.getName())) {
			return translateInputUniversityExecute(oldModel);
		}

		if (oldModelClassName.equals(
					VirtualLabClassStatisticsClp.class.getName())) {
			return translateInputVirtualLabClassStatistics(oldModel);
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

	public static Object translateInputScienceAppExecute(BaseModel<?> oldModel) {
		ScienceAppExecuteClp oldClpModel = (ScienceAppExecuteClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getScienceAppExecuteRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSimulation(BaseModel<?> oldModel) {
		SimulationClp oldClpModel = (SimulationClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSimulationRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSimulationExeStsMigration(
		BaseModel<?> oldModel) {
		SimulationExeStsMigrationClp oldClpModel = (SimulationExeStsMigrationClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSimulationExeStsMigrationRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSimulationJob(BaseModel<?> oldModel) {
		SimulationJobClp oldClpModel = (SimulationJobClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSimulationJobRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSimulationJobData(BaseModel<?> oldModel) {
		SimulationJobDataClp oldClpModel = (SimulationJobDataClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSimulationJobDataRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSimulationJobStatus(
		BaseModel<?> oldModel) {
		SimulationJobStatusClp oldClpModel = (SimulationJobStatusClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSimulationJobStatusRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSimulationShare(BaseModel<?> oldModel) {
		SimulationShareClp oldClpModel = (SimulationShareClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSimulationShareRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputUniversityExecute(BaseModel<?> oldModel) {
		UniversityExecuteClp oldClpModel = (UniversityExecuteClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getUniversityExecuteRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputVirtualLabClassStatistics(
		BaseModel<?> oldModel) {
		VirtualLabClassStatisticsClp oldClpModel = (VirtualLabClassStatisticsClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getVirtualLabClassStatisticsRemoteModel();

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
					"org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteImpl")) {
			return translateOutputScienceAppExecute(oldModel);
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
					"org.kisti.edison.bestsimulation.model.impl.SimulationImpl")) {
			return translateOutputSimulation(oldModel);
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
					"org.kisti.edison.bestsimulation.model.impl.SimulationExeStsMigrationImpl")) {
			return translateOutputSimulationExeStsMigration(oldModel);
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
					"org.kisti.edison.bestsimulation.model.impl.SimulationJobImpl")) {
			return translateOutputSimulationJob(oldModel);
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
					"org.kisti.edison.bestsimulation.model.impl.SimulationJobDataImpl")) {
			return translateOutputSimulationJobData(oldModel);
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
					"org.kisti.edison.bestsimulation.model.impl.SimulationJobStatusImpl")) {
			return translateOutputSimulationJobStatus(oldModel);
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
					"org.kisti.edison.bestsimulation.model.impl.SimulationShareImpl")) {
			return translateOutputSimulationShare(oldModel);
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
					"org.kisti.edison.bestsimulation.model.impl.UniversityExecuteImpl")) {
			return translateOutputUniversityExecute(oldModel);
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
					"org.kisti.edison.bestsimulation.model.impl.VirtualLabClassStatisticsImpl")) {
			return translateOutputVirtualLabClassStatistics(oldModel);
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

		if (className.equals(
					"org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException")) {
			return new org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException();
		}

		if (className.equals(
					"org.kisti.edison.bestsimulation.NoSuchSimulationException")) {
			return new org.kisti.edison.bestsimulation.NoSuchSimulationException();
		}

		if (className.equals(
					"org.kisti.edison.bestsimulation.NoSuchSimulationExeStsMigrationException")) {
			return new org.kisti.edison.bestsimulation.NoSuchSimulationExeStsMigrationException();
		}

		if (className.equals(
					"org.kisti.edison.bestsimulation.NoSuchSimulationJobException")) {
			return new org.kisti.edison.bestsimulation.NoSuchSimulationJobException();
		}

		if (className.equals(
					"org.kisti.edison.bestsimulation.NoSuchSimulationJobDataException")) {
			return new org.kisti.edison.bestsimulation.NoSuchSimulationJobDataException();
		}

		if (className.equals(
					"org.kisti.edison.bestsimulation.NoSuchSimulationJobStatusException")) {
			return new org.kisti.edison.bestsimulation.NoSuchSimulationJobStatusException();
		}

		if (className.equals(
					"org.kisti.edison.bestsimulation.NoSuchSimulationShareException")) {
			return new org.kisti.edison.bestsimulation.NoSuchSimulationShareException();
		}

		if (className.equals(
					"org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException")) {
			return new org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException();
		}

		if (className.equals(
					"org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException")) {
			return new org.kisti.edison.bestsimulation.NoSuchVirtualLabClassStatisticsException();
		}

		return throwable;
	}

	public static Object translateOutputScienceAppExecute(BaseModel<?> oldModel) {
		ScienceAppExecuteClp newModel = new ScienceAppExecuteClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setScienceAppExecuteRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSimulation(BaseModel<?> oldModel) {
		SimulationClp newModel = new SimulationClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSimulationRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSimulationExeStsMigration(
		BaseModel<?> oldModel) {
		SimulationExeStsMigrationClp newModel = new SimulationExeStsMigrationClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSimulationExeStsMigrationRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSimulationJob(BaseModel<?> oldModel) {
		SimulationJobClp newModel = new SimulationJobClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSimulationJobRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSimulationJobData(BaseModel<?> oldModel) {
		SimulationJobDataClp newModel = new SimulationJobDataClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSimulationJobDataRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSimulationJobStatus(
		BaseModel<?> oldModel) {
		SimulationJobStatusClp newModel = new SimulationJobStatusClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSimulationJobStatusRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSimulationShare(BaseModel<?> oldModel) {
		SimulationShareClp newModel = new SimulationShareClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSimulationShareRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputUniversityExecute(BaseModel<?> oldModel) {
		UniversityExecuteClp newModel = new UniversityExecuteClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setUniversityExecuteRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputVirtualLabClassStatistics(
		BaseModel<?> oldModel) {
		VirtualLabClassStatisticsClp newModel = new VirtualLabClassStatisticsClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setVirtualLabClassStatisticsRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}