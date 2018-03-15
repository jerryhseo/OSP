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

package kisti.edison.challenge.service;

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

import kisti.edison.challenge.model.ChallengeClp;
import kisti.edison.challenge.model.ChallengeEvaluationManagementClp;
import kisti.edison.challenge.model.ChallengeEvaluationScoreClp;
import kisti.edison.challenge.model.ChallengeTeamClp;
import kisti.edison.challenge.model.ChallengeTeamMemberClp;
import kisti.edison.challenge.model.ChildChallengeClp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KYJ
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
						"edison-challenge2017-portlet-deployment-context");

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
							"edison-challenge2017-portlet-deployment-context");

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
				_servletContextName = "edison-challenge2017-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(ChallengeClp.class.getName())) {
			return translateInputChallenge(oldModel);
		}

		if (oldModelClassName.equals(
					ChallengeEvaluationManagementClp.class.getName())) {
			return translateInputChallengeEvaluationManagement(oldModel);
		}

		if (oldModelClassName.equals(
					ChallengeEvaluationScoreClp.class.getName())) {
			return translateInputChallengeEvaluationScore(oldModel);
		}

		if (oldModelClassName.equals(ChallengeTeamClp.class.getName())) {
			return translateInputChallengeTeam(oldModel);
		}

		if (oldModelClassName.equals(ChallengeTeamMemberClp.class.getName())) {
			return translateInputChallengeTeamMember(oldModel);
		}

		if (oldModelClassName.equals(ChildChallengeClp.class.getName())) {
			return translateInputChildChallenge(oldModel);
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

	public static Object translateInputChallenge(BaseModel<?> oldModel) {
		ChallengeClp oldClpModel = (ChallengeClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getChallengeRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputChallengeEvaluationManagement(
		BaseModel<?> oldModel) {
		ChallengeEvaluationManagementClp oldClpModel = (ChallengeEvaluationManagementClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getChallengeEvaluationManagementRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputChallengeEvaluationScore(
		BaseModel<?> oldModel) {
		ChallengeEvaluationScoreClp oldClpModel = (ChallengeEvaluationScoreClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getChallengeEvaluationScoreRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputChallengeTeam(BaseModel<?> oldModel) {
		ChallengeTeamClp oldClpModel = (ChallengeTeamClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getChallengeTeamRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputChallengeTeamMember(
		BaseModel<?> oldModel) {
		ChallengeTeamMemberClp oldClpModel = (ChallengeTeamMemberClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getChallengeTeamMemberRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputChildChallenge(BaseModel<?> oldModel) {
		ChildChallengeClp oldClpModel = (ChildChallengeClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getChildChallengeRemoteModel();

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
					"kisti.edison.challenge.model.impl.ChallengeImpl")) {
			return translateOutputChallenge(oldModel);
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
					"kisti.edison.challenge.model.impl.ChallengeEvaluationManagementImpl")) {
			return translateOutputChallengeEvaluationManagement(oldModel);
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
					"kisti.edison.challenge.model.impl.ChallengeEvaluationScoreImpl")) {
			return translateOutputChallengeEvaluationScore(oldModel);
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
					"kisti.edison.challenge.model.impl.ChallengeTeamImpl")) {
			return translateOutputChallengeTeam(oldModel);
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
					"kisti.edison.challenge.model.impl.ChallengeTeamMemberImpl")) {
			return translateOutputChallengeTeamMember(oldModel);
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
					"kisti.edison.challenge.model.impl.ChildChallengeImpl")) {
			return translateOutputChildChallenge(oldModel);
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
					"kisti.edison.challenge.ChallengeDescriptionException")) {
			return new kisti.edison.challenge.ChallengeDescriptionException();
		}

		if (className.equals("kisti.edison.challenge.ChallengeFieldException")) {
			return new kisti.edison.challenge.ChallengeFieldException();
		}

		if (className.equals("kisti.edison.challenge.ChallengeNameException")) {
			return new kisti.edison.challenge.ChallengeNameException();
		}

		if (className.equals(
					"kisti.edison.challenge.ChildChallengeMessageException")) {
			return new kisti.edison.challenge.ChildChallengeMessageException();
		}

		if (className.equals(
					"kisti.edison.challenge.ChildChallengeStatusException")) {
			return new kisti.edison.challenge.ChildChallengeStatusException();
		}

		if (className.equals("kisti.edison.challenge.NoSuchChallengeException")) {
			return new kisti.edison.challenge.NoSuchChallengeException();
		}

		if (className.equals(
					"kisti.edison.challenge.NoSuchChallengeEvaluationManagementException")) {
			return new kisti.edison.challenge.NoSuchChallengeEvaluationManagementException();
		}

		if (className.equals(
					"kisti.edison.challenge.NoSuchChallengeEvaluationScoreException")) {
			return new kisti.edison.challenge.NoSuchChallengeEvaluationScoreException();
		}

		if (className.equals(
					"kisti.edison.challenge.NoSuchChallengeTeamException")) {
			return new kisti.edison.challenge.NoSuchChallengeTeamException();
		}

		if (className.equals(
					"kisti.edison.challenge.NoSuchChallengeTeamMemberException")) {
			return new kisti.edison.challenge.NoSuchChallengeTeamMemberException();
		}

		if (className.equals(
					"kisti.edison.challenge.NoSuchChildChallengeException")) {
			return new kisti.edison.challenge.NoSuchChildChallengeException();
		}

		return throwable;
	}

	public static Object translateOutputChallenge(BaseModel<?> oldModel) {
		ChallengeClp newModel = new ChallengeClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setChallengeRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputChallengeEvaluationManagement(
		BaseModel<?> oldModel) {
		ChallengeEvaluationManagementClp newModel = new ChallengeEvaluationManagementClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setChallengeEvaluationManagementRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputChallengeEvaluationScore(
		BaseModel<?> oldModel) {
		ChallengeEvaluationScoreClp newModel = new ChallengeEvaluationScoreClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setChallengeEvaluationScoreRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputChallengeTeam(BaseModel<?> oldModel) {
		ChallengeTeamClp newModel = new ChallengeTeamClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setChallengeTeamRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputChallengeTeamMember(
		BaseModel<?> oldModel) {
		ChallengeTeamMemberClp newModel = new ChallengeTeamMemberClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setChallengeTeamMemberRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputChildChallenge(BaseModel<?> oldModel) {
		ChildChallengeClp newModel = new ChildChallengeClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setChildChallengeRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}