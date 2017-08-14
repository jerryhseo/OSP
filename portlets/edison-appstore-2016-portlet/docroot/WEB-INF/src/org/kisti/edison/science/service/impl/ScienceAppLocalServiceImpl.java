/**
 * Copyright (c) 2015-present KISTI. All rights reserved.
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

package org.kisti.edison.science.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.science.NoSuchScienceAppException;
import org.kisti.edison.science.NoSuchScienceAppInputPortsException;
import org.kisti.edison.science.NoSuchScienceAppOutputPortsException;
import org.kisti.edison.science.Exception.ScienceAppException;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppCategoryLink;
import org.kisti.edison.science.model.ScienceAppDescription;
import org.kisti.edison.science.model.ScienceAppInputPorts;
import org.kisti.edison.science.model.ScienceAppManager;
import org.kisti.edison.science.model.ScienceAppOutputPorts;
import org.kisti.edison.science.model.impl.ScienceAppImpl;
import org.kisti.edison.science.service.ScienceAppCategoryLinkLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppDescriptionLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppManagerLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppOutputPortsLocalServiceUtil;
import org.kisti.edison.science.service.base.ScienceAppLocalServiceBaseImpl;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonPropsUtil;
import org.springframework.util.StringUtils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.NoSuchEntryException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLink;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * The implementation of the science app local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.kisti.science.platform.service.ScienceAppLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.base.ScienceAppLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.ScienceAppLocalServiceUtil
 */
public class ScienceAppLocalServiceImpl extends ScienceAppLocalServiceBaseImpl{

	/**
	 * Creates a ScienceApp instance with specified name and version. The new
	 * instance created is not yet saved in the database. Use addScienceApp() to
	 * save the instance.
	 * 
	 * @param appName:
	 *          ScienceApp name to be created
	 * @param appVersion:
	 *          ScienceApp version to be created
	 * @param sc:
	 *          ServiceContext instance for ScienceApp
	 * @return If appName is not follows naming rules or already exists in the
	 *         database, returns null. If appVersion is not follows versioning
	 *         rules, returns null. Otherwise returns a ScienceApp instance with
	 *         initialized data.
	 * 
	 *         Some attributes of the returned instance are set initial value as
	 *         followings: -stage: ScienceAppConstants.EMPTY -authorId: current
	 *         user id of service context instance -createDate: date created of
	 *         service context instance -modifiedDate: date created of service
	 *         context instance -userId: current user id of service context
	 *         instance -recentModifierId: current user id of service context
	 *         instance -groupId: scope group id of service context instance
	 *         -companyId: company id of service context instance
	 * @throws SystemException
	 * @throws PortalException 
	 *
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#createScienceApp(java.lang.String,
	 *      java.lang.String, com.liferay.portal.service.ServiceContext)
	 */
	public ScienceApp createScienceApp(String appName, String appVersion, ServiceContext sc)
		throws SystemException{
		if(!this.verifyScienceAppName(appName))
			return null;
		if(this.existAppName(appName)){
			if(!this.verifyVersionNumber(appName, appVersion))
				return null;
		}

		ScienceApp newApp = null;
		long newAppId = super.counterLocalService.increment();
		newApp = super.scienceAppPersistence.create(newAppId);

		newApp.setName(appName);
		newApp.setVersion(appVersion);
		newApp.setStage(ScienceAppConstants.EMPTY);
		newApp.setAuthorId(sc.getUserId());
		newApp.setCreateDate(sc.getCreateDate());
		newApp.setModifiedDate(sc.getModifiedDate());
		newApp.setUserId(sc.getUserId());
		newApp.setRecentModifierId(sc.getUserId());
		newApp.setGroupId(sc.getScopeGroupId());
		newApp.setCompanyId(sc.getCompanyId());

		return newApp;
	}
  
  public ScienceApp getTextEditorScienceApp() throws SystemException{
    return getEditorScienceApp("Text");
  }
  
  public ScienceApp getFileEditorScienceApp() throws SystemException{
    return getEditorScienceApp("File");
  }
  
  public ScienceApp getStructuredEditorScienceApp() throws SystemException{
    return getEditorScienceApp("Inputdeck");
  }
  
  @SuppressWarnings("unchecked")
  public ScienceApp getEditorScienceApp(String editorType) throws SystemException{
    DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceApp.class, PortletClassLoaderUtil.getClassLoader());
    query.add(RestrictionsFactoryUtil.eq("editorType", editorType));
    query.add(RestrictionsFactoryUtil.eq("appType", "Editor"));
    query.addOrder(OrderFactoryUtil.desc("version"));
    query.addOrder(OrderFactoryUtil.desc("createDate"));
    List<ScienceApp> apps = getScienceAppLocalService().dynamicQuery(query);
    if(apps != null && apps.size() > 0){
      return apps.get(0); 
    }else{
      throw new SystemException("There is no Text Editor.");
    }
  }

	/**
	 * Saves the specified ScienceApp instance to database.
	 * 
	 * @param scienceApp:
	 *          ScienceApp instance to be saved.
	 * @param sc:
	 *          service context of the ScienceApp class
	 * @return ScienceApp instance saved.
	 * @throws SystemException
	 * @throws PortalException
	 *
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#addScienceApp(com.kisti.science.platform.model.ScienceApp,
	 *      com.liferay.portal.service.ServiceContext)
	 */
	public ScienceApp addScienceApp(ScienceApp scienceApp, ServiceContext sc) throws SystemException,
		PortalException{
		super.resourceLocalService.addResources(scienceApp.getCompanyId(), scienceApp.getGroupId(), scienceApp
			.getUserId(), ScienceApp.class.getName(), scienceApp.getScienceAppId(), false, true, true);

		AssetEntry assetEntry = super.assetEntryLocalService.updateEntry(scienceApp.getUserId(), scienceApp
			.getGroupId(), scienceApp.getCreateDate(), scienceApp.getModifiedDate(), ScienceApp.class.getName(),
			scienceApp.getScienceAppId(), scienceApp.getUuid(), 0, sc.getAssetCategoryIds(), sc.getAssetTagNames(),
			true, null, null, null, ContentTypes.TEXT_HTML, scienceApp.getName(), null, null, null, null, 0, 0,
			null, false);

		assetLinkLocalService.updateLinks(scienceApp.getUserId(), assetEntry.getEntryId(), sc
			.getAssetLinkEntryIds(), AssetLinkConstants.TYPE_RELATED);

		Indexer indexer = IndexerRegistryUtil.getIndexer(ScienceApp.class);
		indexer.reindex(scienceApp);

		return super.addScienceApp(scienceApp);
	}

	public void setScienceAppInputPorts(long scienceAppId, String inputPorts) throws SystemException{
		super.scienceAppInputPortsLocalService.create(scienceAppId, inputPorts);
	}

	public String getScienceAppInputPorts(long scienceAppId) throws SystemException{
		return super.scienceAppInputPortsLocalService.getInputPortsJsonString(scienceAppId);
	}
	
	public void setScienceAppLogPorts(long scienceAppId, String logPorts) throws SystemException, PortalException{
		super.scienceAppLogPortsLocalService.create(scienceAppId, logPorts);
	}

	public String getScienceAppLogPorts(long scienceAppId) throws SystemException{
		return super.scienceAppLogPortsLocalService.getLogPortsJsonString(scienceAppId);
	}

	public void setScienceAppOutputPorts(long scienceAppId, String outputPorts) throws SystemException{
		super.scienceAppOutputPortsLocalService.create(scienceAppId, outputPorts);
	}

	public String getScienceAppOutputPorts(long scienceAppId) throws SystemException{
		return super.scienceAppOutputPortsLocalService.getOutputPortsJsonString(scienceAppId);
	}

	/**
	 * Verifies ScienceApp name if the name follows specified naming rules and
	 * there is no science app in the database already.
	 * 
	 * @param appName:
	 *          science app name to be tesed.
	 * @return true if the name follows naming rules and brand new. false,
	 *         Otherwise.
	 * @throws SystemException
	 *
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#verifyScienceAppName(java.lang.String,
	 *      long)
	 */
	public boolean verifyScienceAppName(String appName) throws SystemException{
		if(!appName.matches(ScienceAppConstants.SCIENCEAPP_VALID_NAME_EXPR))
			return false;

		return !this.existAppName(appName);
	}

	/**
	 * Test if there is a science app name in the database already.
	 * 
	 * @param appName:
	 *          science app name to be tesed.
	 * @return true if the app name already exist in the database, false,
	 *         otherwise
	 * @throws SystemException
	 * 
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#existAppName(java.lang.String)
	 */
	public boolean existAppName(String appName) throws SystemException{
		if(super.scienceAppPersistence.countByName(appName) > 0)
			return true;
		else
			return false;
	}

	/**
	 * Test if there is an science app with the specified name and version in the
	 * database.
	 * 
	 * @param appName:
	 *          science app name to be tesed.
	 * @param appVersion:
	 *          science app version to be tesed.
	 * @return true if a science app with the name and the version already exist
	 *         in the database, false, otherwise.
	 * @throws SystemException
	 * 
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#existApp(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean existApp(String appName, String appVersion) throws SystemException{
		if(super.scienceAppPersistence.countByNameVersion(appName, appVersion) > 0)
			return true;
		else
			return false;
	}

	/**
	 * Get the science app's latest version
	 * 
	 * @param appName:
	 *          science app name
	 * @return the latest version science app instance.
	 * @throws SystemException
	 * 
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#getLatestVersion(java.lang.String)
	 */
	public ScienceApp getLatestVersion(String appName) throws SystemException{
		ScienceApp app = null;
		OrderByComparator orderBy = OrderByComparatorFactoryUtil.create(ScienceAppImpl.TABLE_NAME, "createDate",
			false);
		app = super.scienceAppPersistence.fetchByName_First(appName, orderBy);
		return app;
	}

	/**
	 * Verify version number of a science app. Version number of a science app
	 * should be consisted of 3 sections, {major}.{sub}.{minor}. Major section
	 * number should be increased when a science app changes or added its
	 * architecture or major functions. Sub section number should be increased
	 * when the science app changes functionality. Minor section number should be
	 * increased when the science app fixes errors. Each section must be integer
	 * and be lager than before.
	 * 
	 * @param appName:
	 *          science app name to be verified.
	 * @param appVersion:
	 *          science app version to be verified.
	 * @return true if the version number follows naming rules and valid. false,
	 *         Otherwise.
	 * @throws SystemException
	 * 
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#verifyVersionNumber(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean verifyVersionNumber(String appName, String appVersion) throws SystemException{
		if(!appName.matches(ScienceAppConstants.SCIENCEAPP_VALID_VERSION_EXPR))
			return false;

		ScienceApp app = this.getLatestVersion(appName);
		String[] newVersion = appVersion.split("\\.");
		// System.out.println("Saved version: "+app.getVersion());
		String[] version = app.getVersion().split("\\.");
		// System.out.println("version length:
		// "+version.length+"-"+newVersion.length);

		for(int i = 0; i < newVersion.length && i < version.length; i++){
			// System.out.println("Level["+i+"]: "+ newVersion[i]+":"+version[i]);
			if(Integer.parseInt(newVersion[i]) < Integer.parseInt(version[i]))
				return false;
			else if(Integer.parseInt(newVersion[i]) > Integer.parseInt(version[i]))
				return true;
		}
		// System.out.println("All verified failed.....");

		return false;
	}

	/**
	 * Delete a science app by its id
	 * 
	 * @param scienceAppId:
	 *          science app id to be deleted
	 * @return ScienceApp instance deleted.
	 * @throws SystemException
	 * @throws PortalException
	 *
	 * @see com.kisti.science.platform.service.base.ScienceAppLocalServiceBaseImpl#deleteScienceApp(long)
	 */
	public ScienceApp deleteScienceApp(long scienceAppId) throws SystemException, PortalException{
		this.cleanIntegratedData(scienceAppId);
		return this.deleteScienceApp(super.getScienceApp(scienceAppId));
	}

	/**
	 * Delete a science app by its instance
	 * 
	 * @param scienceApp:
	 *          science app instance to be tesed.
	 * @return ScienceApp instance deleted.
	 * @throws SystemException
	 * @throws PortalException
	 *
	 * @see com.kisti.science.platform.service.base.ScienceAppLocalServiceBaseImpl#deleteScienceApp(com.kisti.science.platform.model.ScienceApp)
	 */
	public ScienceApp deleteScienceApp(ScienceApp scienceApp) throws SystemException, PortalException{
		this.cleanIntegratedData(scienceApp.getScienceAppId());
		super.resourceLocalService.deleteResource(scienceApp.getCompanyId(), ScienceApp.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, scienceApp.getScienceAppId());

		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(ScienceApp.class.getName(), scienceApp
			.getScienceAppId());

		assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

		assetEntryLocalService.deleteEntry(ScienceApp.class.getName(), scienceApp.getScienceAppId());
		super.deleteScienceApp(scienceApp);

		Indexer indexer = IndexerRegistryUtil.getIndexer(ScienceApp.class);
		indexer.reindex(scienceApp);

		return scienceApp;
	}

	public void deleteAllScienceApps() throws SystemException{
		super.scienceAppPersistence.removeAll();
	}
	
	public List<ScienceApp> getAll() throws SystemException{
		return super.scienceAppPersistence.findAll();
	}

	/**
	 * Update a science app
	 * 
	 * @param scienceApp:
	 *          science app instance to be updated.
	 * @param sc:
	 *          ServiceContext instance for the ScienceApp class.
	 * @return ScienceApp instance updated.
	 * @throws SystemException
	 * @throws PortalException
	 *
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#updateScienceApp(com.kisti.science.platform.model.ScienceApp,
	 *      com.liferay.portal.service.ServiceContext)
	 */
	public ScienceApp updateScienceApp(ScienceApp scienceApp, ServiceContext sc) throws SystemException,
		PortalException{

		long userId = sc.getUserId();

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId, scienceApp.getGroupId(), scienceApp
			.getCreateDate(), scienceApp.getModifiedDate(), ScienceApp.class.getName(), scienceApp
				.getScienceAppId(), scienceApp.getUuid(), 0, sc.getAssetCategoryIds(), sc.getAssetTagNames(), true,
			null, null, null, ContentTypes.TEXT_HTML, scienceApp.getName(), null, null, null, null, 0, 0, null,
			false);

		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(), sc.getAssetLinkEntryIds(),
			AssetLinkConstants.TYPE_RELATED);

		Indexer indexer = IndexerRegistryUtil.getIndexer(ScienceApp.class);
		indexer.reindex(scienceApp);

		return super.updateScienceApp(scienceApp);
	}

	/*
	 * From this line, the APIs are just call persistence APIs for convenience.
	 */
	public List<ScienceApp> getScienceAppListByStatus(int status) throws SystemException{
		return super.scienceAppPersistence.findByStatus(status);
	}

	public List<ScienceApp> getScienceAppListByStage(String stage) throws SystemException{
		return super.scienceAppPersistence.findByStage(stage);
	}

	public List<ScienceApp> getScienceAppListByAuthorIdStatus(long authorId, int appStatus)
		throws SystemException{
		return super.scienceAppPersistence.findByAuthorIdStatus(authorId, appStatus);
	}

	public List<ScienceApp> getScienceAppListByAuthorIdStatus(long authorId, int appStatus, int start, int end)
		throws SystemException{
		return super.scienceAppPersistence.findByAuthorIdStatus(authorId, appStatus, start, end);
	}

	public List<ScienceApp> getScienceAppListByAuthorIdAppType(long authorId, String appType)
		throws SystemException{
		return super.scienceAppPersistence.findByAuthorIdAppType(authorId, appType);
	}

	public List<ScienceApp> getScienceAppListByAuthorIdAppType(long authorId, String appClass, int start,
		int end) throws SystemException{
		return super.scienceAppPersistence.findByAuthorIdAppType(authorId, appClass, start, end);
	}

	public List<ScienceApp> getScienceAppListByAuthorId(long authorId) throws SystemException{
		return super.scienceAppPersistence.findByAuthorId(authorId);
	}

	public List<ScienceApp> getScienceAppListByAuthorId(long authorId, int start, int end)
		throws SystemException{
		return super.scienceAppPersistence.findByAuthorId(authorId, start, end);
	}

	public int countScienceAppsByAuthorId(long authorId) throws SystemException{
		return super.scienceAppPersistence.countByAuthorId(authorId);
	}

	public List<ScienceApp> getScienceAppListByOpenLevel(String openLevel) throws SystemException{
		return super.scienceAppPersistence.findByOpenLevel(openLevel);
	}

	public List<ScienceApp> getScienceAppListByOpenLevel(String openLevel, int start, int end)
		throws SystemException{
		return super.scienceAppPersistence.findByOpenLevel(openLevel, start, end);
	}

	public List<ScienceApp> getScienceAppListByManagerId(long managerId) throws SystemException,
		PortalException{
		long[] scienceAppIds = super.scienceAppManagerLocalService.getScienceAppIdsByManagerId(managerId);
		return this.getScienceAppListByScienceAppIds(scienceAppIds);
	}

	public List<ScienceApp> getScienceAppListByManagerId(long managerId, int start, int end)
		throws SystemException, PortalException{
		long[] scienceAppIds = super.scienceAppManagerLocalService.getScienceAppIdsByManagerId(managerId, start,
			end);
		return this.getScienceAppListByScienceAppIds(scienceAppIds);
	}

	public int countScienceAppsByManagerId(long managerId) throws SystemException{
		return super.scienceAppManagerLocalService.countScienceAppIdsByManagerId(managerId);
	}

	public long[] getScienceAppManagerIds(long scienceAppId) throws SystemException{
		return super.scienceAppManagerLocalService.getManagerIdsByScienceAppId(scienceAppId);
	}

	public long[] getScienceAppManagerIds(long scienceAppId, int start, int end) throws SystemException{
		return super.scienceAppManagerLocalService.getManagerIdsByScienceAppId(scienceAppId, start, end);
	}

	public int countScienceAppManagers(long scienceAppId) throws SystemException{
		return super.scienceAppManagerLocalService.countManagersByScienceAppId(scienceAppId);
	}

	public List<ScienceApp> getScienceAppListByCategoryId(long categoryId) throws SystemException,
		PortalException{
		long[] scienceAppIds = super.scienceAppCategoryLinkLocalService.getScienceAppIdsByCategoryId(categoryId);
		return this.getScienceAppListByScienceAppIds(scienceAppIds);
	}

	public List<ScienceApp> getScienceAppListByCategoryId(long categoryId, int start, int end)
		throws SystemException, PortalException{
		long[] scienceAppIds = super.scienceAppCategoryLinkLocalService.getScienceAppIdsByCategoryId(categoryId,
			start, end);
		return this.getScienceAppListByScienceAppIds(scienceAppIds);
	}

	public int countScienceAppsByCategoryId(long categoryId) throws SystemException{
		return super.scienceAppCategoryLinkLocalService.countScienceAppsByCategoryId(categoryId);
	}

	public long[] getScienceAppCategoryIds(long scienceAppId) throws SystemException{
		return super.scienceAppCategoryLinkLocalService.getCategoryIdsByScienceAppId(scienceAppId);
	}

	public long[] getScienceAppCategoryIds(long scienceAppId, int start, int end) throws SystemException{
		return super.scienceAppCategoryLinkLocalService.getCategoryIdsByScienceAppId(scienceAppId, start, end);
	}

	public int countScienceAppCategories(long scienceAppId) throws SystemException{
		return super.scienceAppCategoryLinkLocalService.countCategoriesByScienceAppId(scienceAppId);
	}

	public void assignScienceAppToCategories(long scienceAppId, long[] categoryIds) throws SystemException{
		for(long categoryId : categoryIds){
			this.assignScienceAppToCategory(scienceAppId, categoryId);
		}
	}

	public void assignScienceAppToCategory(long scienceAppId, long categoryId) throws SystemException{
		super.scienceAppCategoryLinkLocalService.addScienceAppCategory(categoryId, scienceAppId);
	}

	public void assignManagersToScienceApp(long scienceAppId, long[] managerIds) throws SystemException{
		for(long managerId : managerIds){
			this.assignManagerToScienceApp(scienceAppId, managerId);
		}
	}

	public void assignManagerToScienceApp(long scienceAppId, long managerId) throws SystemException{
		super.scienceAppManagerLocalService.addScienceAppManager(managerId, scienceAppId);
	}

	/**
	 * Get path of the executable binary file. The full path can be obtained by
	 * adding base directory for science apps.
	 * 
	 * @param scienceAppId
	 * @return String of the path.
	 * @throws PortalException
	 * @throws SystemException
	 */
	public String getScienceAppBinPath(long scienceAppId) throws PortalException, SystemException{
		ScienceApp scienceApp = super.getScienceApp(scienceAppId);
		return scienceApp.getBinPath();
	}

	/**
	 * Get path of the source file. The full path can be obtained by adding base
	 * directory for science apps.
	 * 
	 * @param scienceAppId
	 * @return String of the path.
	 * @throws PortalException
	 * @throws SystemException
	 */
	public String getScienceAppSrcPath(long scienceAppId) throws PortalException, SystemException{
		ScienceApp scienceApp = super.getScienceApp(scienceAppId);
		return scienceApp.getSrcPath();
	}

	/**
	 * Counts all science apps in the database.
	 * 
	 * @return int - count
	 */
	public int countAllScienceApps() throws SystemException{
		return super.scienceAppPersistence.countAll();
	}


	/**
	 * For reserving data integration
	 *
	 * @param scienceAppId
	 *          the science app id
	 * @throws SystemException
	 *           the system exception
	 * @throws PortalException
	 */
	protected void cleanIntegratedData(long scienceAppId) throws SystemException, PortalException{
		super.scienceAppInputPortsLocalService.deleteScienceAppInputPorts(scienceAppId);
		super.scienceAppOutputPortsLocalService.deleteScienceAppOutputPorts(scienceAppId);
		super.scienceAppManagerPersistence.removeByAppId(scienceAppId);
	}

	private List<ScienceApp> getScienceAppListByScienceAppIds(long[] scienceAppIds) throws PortalException,
		SystemException{
		List<ScienceApp> apps = new ArrayList<ScienceApp>();
		for(int i = 0; i < scienceAppIds.length; i++){
			apps.add(this.getScienceApp(scienceAppIds[i]));
		}

		return apps;
	}

	public boolean existScienceAppPath(String targetPath) throws SystemException, IOException,
		InterruptedException{
		File dir = new File(targetPath);

		return dir.exists();
	}

	public void deleteScienceAppDir(String targetDir) throws SystemException, IOException, InterruptedException{
		this.executeCommand("rm -df " + targetDir);
	}

	public void makeScienceAppDir(String targetDir) throws SystemException, IOException, InterruptedException{
		File dir = new File(targetDir);
		if(dir.exists()){
			this.deleteScienceAppDir(targetDir);
		}

		this.executeCommand("mkdir -p " + targetDir);
	}

	/***
	 * Save uploaded file to new location
	 * 
	 * @param uploadedInputStream
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws SystemException
	 */
	public File saveToScienceAppStorage(String targetDir, String fileName, InputStream uploadedInputStream)
		throws SystemException, IOException, InterruptedException{

		// Delete existed file first!
		if(this.existScienceAppPath(targetDir))
			this.executeCommand("rm -rf " + targetDir + "/*");

		this.makeScienceAppDir(targetDir);

		String targetPath = targetDir + "/" + fileName;
		// Declare an output stream.
		File newFile = new File(targetPath);
		OutputStream out = new FileOutputStream(newFile);
		// number of bytes read
		int read = 0;
		// 1024-byte buffer
		byte[] bytes = new byte[1024];

		// Read the file until no more bytes are read.
		while((read = uploadedInputStream.read(bytes)) != -1){
			// Write these bytes.
			out.write(bytes, 0, read);
		}
		// Flush it.
		out.flush();
		// Close it.
		out.close();

		// allow for write and execute
		// executeCommand("sudo chmod 777 " + path);
		return newFile;
	}

	/*****
	 * Unzip an uploaded file into a specified directory.
	 * 
	 * @param strUnzipDirPath
	 *          the directory into which unzipped files go.
	 * @param zipFile
	 *          zip file
	 * @return process output message
	 * @throws SystemException
	 *           system exception
	 * @throws IOException
	 *           io exception
	 * @throws InterruptedException
	 *           interrupted exception
	 */
	public void unzipScienceAppZipFile(String sourceFile, String targetDir) throws SystemException, IOException,
		InterruptedException{
		// Zip file name
		if(this.existScienceAppPath(targetDir))
			this.deleteScienceAppDir(targetDir);

		File zipFile = new File(sourceFile);

		// make directory first
		this.makeScienceAppDir(targetDir);

		String zipFileName = zipFile.getName().trim();
		String command = "";
		if(zipFileName.endsWith("gz")){
			command = "tar -xzf " + sourceFile + " -C " + targetDir;
		}else if(zipFileName.endsWith("tar")){
			command = "tar -xf " + sourceFile + " -C " + targetDir;
		}else if(zipFileName.endsWith("zip")){
			command = "unzip -o " + sourceFile + " -d " + targetDir;
		}else{
			throw new SystemException("file extension not ending with *.gz or *.zip");
		}

		// execute unzipping
		this.executeCommand(command);

		this.executeCommand("chmod 755 " + targetDir + File.separator + "*");
	}

	/****
	 * Execute a given command
	 * 
	 * @param command
	 *          a given command
	 * @return install message
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void executeCommand(String command) throws SystemException, IOException, InterruptedException{
		String[] commandAndArgs = new String[]{"/bin/sh", "-c", command};
		// System.out.println(commandAndArgs);
		// Get Runtime instance.
		String report = "";
		Runtime runTime = Runtime.getRuntime();
		// Declare a process.
		Process process = null;
		// Let's execute the command.
		process = runTime.exec(commandAndArgs);
		// Get any input stream.
		InputStream instd = process.getInputStream();
		// Let's get it through buffered reader.
		BufferedReader buf_reader = new BufferedReader(new InputStreamReader(instd));
		String temp = "";
		// System.out.println("new line executed command: " + command);
		while((temp = buf_reader.readLine()) != null){
			// System.out.println("temp: " + temp);
			report += temp + "\n";
		}
		// Let's close buffered reader
		buf_reader.close();

		// Get any error stream.
		InputStream stderr = process.getErrorStream();
		// Let's get it through buffered reader.
		BufferedReader buf_err_reader = new BufferedReader(new InputStreamReader(stderr));
		// Initialize a temporary variable.
		temp = "";
		// Until there's no more error message,
		while((temp = buf_err_reader.readLine()) != null){
			// Append a current error message to the error message
			// container.
			report += temp + "\n";
		}
		// Close buffered error reader.
		buf_err_reader.close();
		// Let's wait p0 for completion.
		process.waitFor();
	}

	/**********************************
	 * ADD GPLUS SERVICE
	 ******************************/

	public void cleanScienceAppDir(long companyId, String appName, String appVersion) throws SystemException, IOException, InterruptedException{
		String appBasePath = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + appName
			+ File.separator + appVersion;
		
		if(!appName.equals("") && !appVersion.equals("")){
  		this.executeCommand("rm -rf " + appBasePath);
		}
	}
	
  public List<ScienceApp> getScienceAppListByCategoryId2(long categoryId, Locale locale)
      throws SystemException, PortalException{
    long[] scienceAppIds = super.scienceAppCategoryLinkLocalService
        .getScienceAppIdsByCategoryId(categoryId);
    return this.getScienceAppListByScienceAppIdsAndLocale(scienceAppIds, locale);
  }
	
	public List<ScienceApp> getScienceAppListByCategoryId(long categoryId, Locale locale)
		throws SystemException, PortalException{
	  
		long[] scienceAppIds = scienceAppFinder.getScienceAppIdsByCategoryId(categoryId);
		return this.getScienceAppListByScienceAppIdsAndLocale(scienceAppIds, locale);
	}

	public List<ScienceApp> getScienceAppListByCategoryId(long categoryId, Locale locale, int start, int end)
		throws SystemException, PortalException{
		long[] scienceAppIds = super.scienceAppCategoryLinkLocalService.getScienceAppIdsByCategoryId(categoryId);
		return this.getScienceAppListByScienceAppIdsAndLocale(scienceAppIds, locale, start, end);
	}

	public List<ScienceApp> getScienceAppListByScienceAppIdsAndLocale(long[] scienceAppIds, Locale locale)
		throws PortalException, SystemException{
		return getScienceAppListByScienceAppIdsAndLocale(scienceAppIds, locale, -1, -1);
	}

	public List<Map<String, Object>> getLanguageSpecificAssetCategories(List<AssetCategory> categories,
		long parentCategoryId, Locale locale){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentCategoryId", parentCategoryId);
		params.put("targetLanguage", LocaleUtil.toLanguageId(locale));
		Map<Long, Long> targetLanguageCategories = scienceAppFinder.getLanguageSpecificCategories(params);
		List<Map<String, Object>> resultCategories = new ArrayList<Map<String, Object>>();
		for(AssetCategory category : categories){
			long id = category.getCategoryId();
			if(targetLanguageCategories.containsKey(id)){
				Map<String, Object> categoryMap = category.getModelAttributes();
				categoryMap.put("appCnt", targetLanguageCategories.get(id));
				categoryMap.put("data", new HashMap<String, Object>(categoryMap));
				categoryMap.put("id", category.getCategoryId());
				categoryMap.put("text", category.getTitle(locale));
				categoryMap.put("title", category.getTitle(locale));
				categoryMap.put("description", category.getDescription(locale));
				if(category.getParentCategoryId() == 0){
					categoryMap.put("type", "category");
				}else{
					categoryMap.put("type", "subCategory");
				}
				resultCategories.add(categoryMap);
			}
		}
		return resultCategories;
	}

	@SuppressWarnings("unchecked")
	public List<ScienceApp> getScienceAppListByScienceAppIdsAndLocale(long[] scienceAppIds, Locale locale,
		int start, int end) throws PortalException, SystemException{
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceApp.class);
		query.add(RestrictionsFactoryUtil.in("scienceAppId", ArrayUtils.toObject(scienceAppIds)));
		query.add(RestrictionsFactoryUtil.like("targetLanguage", "%" + LocaleUtil.toLanguageId(locale) + "%"));
		query.add(RestrictionsFactoryUtil.eq("status", 1901004));
		query.addOrder(OrderFactoryUtil.desc("createDate"));
		query.addOrder(OrderFactoryUtil.desc("version"));
		return (List<ScienceApp>) super.getScienceAppLocalService().dynamicQuery(query, start, end);
	}

	/**
	 * 
	 * @param sc
	 * @param params
	 * @return
	 * @throws ScienceAppException
	 * @throws SystemException
	 */
	public ScienceApp createScienceApp(ServiceContext sc, Map params) throws SystemException{
		ScienceApp newApp = null;
		long newAppId = CounterLocalServiceUtil.increment(ScienceApp.class.getName());
		newApp = super.scienceAppPersistence.create(newAppId);

		newApp.setGroupId(sc.getScopeGroupId());
		newApp.setCompanyId(sc.getCompanyId());
		newApp.setUserId(sc.getUserId());
		newApp.setCreateDate(new Date());
		newApp.setModifiedDate(new Date());
		newApp.setName(CustomUtil.strNull(params.get("name")));
		newApp.setVersion(CustomUtil.strNull(params.get("version")));
		newApp.setTitleMap(CustomUtil.getLocalizationNotSetLocaleMap(params, "title"));
		// newApp.setPreviousVersionId(previousVersionId);
		newApp.setAuthorId(sc.getUserId());
		newApp.setStage(ScienceAppConstants.EMPTY);
		newApp.setRecentModifierId(sc.getUserId());
		newApp.setLicense(CustomUtil.strNull(params.get("license")));

		if(CustomUtil.strNull(params.get("targetLanguage")).equals("")){
			String localeStr = "";
			for(Locale locale : LanguageUtil.getAvailableLocales()){
				if(localeStr.equals("")){
					localeStr = LocaleUtil.toLanguageId(locale);
				}else{
					localeStr += "," + LocaleUtil.toLanguageId(locale);
				}
			}
			newApp.setTargetLanguage(localeStr);
		}else{
			newApp.setTargetLanguage(CustomUtil.strNull(params.get("targetLanguage")));
		}

		newApp.setStatus(1901001);

		// 확인
		newApp.setDevelopersMap(CustomUtil.getLocalizationNotSetLocaleMap(params, "developers"));
		newApp.setIsPort(GetterUtil.getBoolean(params.get("isPort"),false));

		try{
			// Description 등록
			long descriptionId = CounterLocalServiceUtil.increment(ScienceAppDescription.class.getName());
			ScienceAppDescription scienceAppDescription = ScienceAppDescriptionLocalServiceUtil
				.createScienceAppDescription(descriptionId);
			Locale[] locales = LanguageUtil.getAvailableLocales();
			for(Locale aLocale : locales){
				String languageId = LocaleUtil.toLanguageId(aLocale);
				String description = CustomUtil.strNull(params.get("description_" + languageId));
				if(!description.equals("")){
					scienceAppDescription.setContent(description, aLocale);
				}
			}
			scienceAppDescription.setInsertDt(new Date());
			scienceAppDescription.setInsertId(sc.getUserId());
			ScienceAppDescriptionLocalServiceUtil.updateScienceAppDescription(scienceAppDescription);

			newApp.setDescriptionId(descriptionId);

			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(sc.getLiferayPortletRequest());
			// icon 등록
			String appIconStr = CustomUtil.strNull(upload.getFileName("app_icon"), "");
			if(!appIconStr.equals("")){ // 아이콘이 있는경우
				List<FileEntry> appIcons = EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload, sc
					.getUserId(), sc.getScopeGroupId(), "", String.valueOf(newAppId), "app_icon",
					EdisonFileConstants.SCIENCE_APPS);
				FileEntry appEntry = appIcons.get(0);
				newApp.setIconId(appEntry.getFileEntryId());
			}

			// Manual 등록
			for(Locale locale : locales){
				String languageId = LocaleUtil.toLanguageId(locale);
				String fileParamsNm = "app_manual" + languageId;
				String manualStr = CustomUtil.strNull(upload.getFileName(fileParamsNm), "");

				if(!manualStr.equals("")){
					List<FileEntry> manuals = EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload, sc
						.getUserId(), sc.getScopeGroupId(), "", String.valueOf(newAppId), fileParamsNm,
						EdisonFileConstants.SCIENCE_APPS);
					FileEntry manualsEntry = manuals.get(0);
					newApp.setManualId(String.valueOf(manualsEntry.getFileEntryId()), locale);
				}else{
					newApp.setManualId("0", locale);
				}
			}
			newApp.setProjectCategoryId(0);
			
			// asset 등록
			long entryId = scienceAppAddAssetEntry(sc.getCompanyId(), sc.getScopeGroupId(), newApp);
			
			// 카테고리 등록
			List<String[]> categoryList = new ArrayList<String[]>();
			if(params.get("childrenCategoryCheckbox") instanceof String[]){
				String[] childrenCategoryArray = (String[]) params.get("childrenCategoryCheckbox");
				for(String childrenCategory : childrenCategoryArray){
					String[] childrenCategoryValue = childrenCategory.split("_");
					categoryList.add(childrenCategoryValue);
				}
			}else if(params.get("childrenCategoryCheckbox") instanceof String){
				String[] childrenCategoryValue = CustomUtil.strNull(params.get("childrenCategoryCheckbox")).split("_");
				categoryList.add(childrenCategoryValue);
			}
			
			if(entryId != 0){
				for(String[] categoryArray : categoryList){
					long parentCategoryId = GetterUtil.getLong(categoryArray[0], 0l);
					long categoryId = GetterUtil.getLong(categoryArray[1], 0l);
					AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(entryId, categoryId);
					// AssetEntryLocalServiceUtil.addAssetCategoryAssetEntry(categoryId, entryId);
				}
			}

		}catch (Exception e){
			throw new SystemException(e);
		}
		scienceAppLocalService.updateScienceApp(newApp);

		return newApp;
	}

	public void updateScienceApp(ServiceContext sc, Map params, long scienceAppId)
		throws NoSuchScienceAppException, SystemException{
		long entryId = GetterUtil.getLong(params.get("entryId"));
		String actionType = CustomUtil.strNull(params.get("actionType"));
		
		ScienceApp scienceApp = super.scienceAppPersistence.findByPrimaryKey(scienceAppId);
		if(actionType.equals("appInfomation")){
			scienceApp.setModifiedDate(new Date());
			scienceApp.setVersion(CustomUtil.strNull(params.get("version")));
			scienceApp.setTitleMap(CustomUtil.getLocalizationNotSetLocaleMap(params, "title"));
			String duplicationCheck = CustomUtil.strNull(params.get("duplicationCheck"));
			if(!duplicationCheck.equals("")){
				scienceApp.setPreviousVersionId(GetterUtil.getLong(params.get("previousVersion"), 0));
			}
			scienceApp.setRecentModifierId(sc.getUserId());
	
			if(CustomUtil.strNull(params.get("targetLanguage")).equals("")){
				String localeStr = "";
				for(Locale locale : LanguageUtil.getAvailableLocales()){
					if(localeStr.equals("")){
						localeStr = LocaleUtil.toLanguageId(locale);
					}else{
						localeStr += "," + LocaleUtil.toLanguageId(locale);
					}
				}
				scienceApp.setTargetLanguage(localeStr);
			}else{
				scienceApp.setTargetLanguage(CustomUtil.strNull(params.get("targetLanguage")));
			}
		
			// 카테고리 clean
			AssetCategoryLocalServiceUtil.clearAssetEntryAssetCategories(entryId);
			// 카테고리 등록
			List<String[]> categoryList = new ArrayList<String[]>();
			if(params.get("childrenCategoryCheckbox") instanceof String[]){
				String[] childrenCategoryArray = (String[]) params.get("childrenCategoryCheckbox");
				for(String childrenCategory : childrenCategoryArray){
					String[] childrenCategoryValue = childrenCategory.split("_");
					categoryList.add(childrenCategoryValue);
				}
			}else if(params.get("childrenCategoryCheckbox") instanceof String){
				String[] childrenCategoryValue = CustomUtil.strNull(params.get("childrenCategoryCheckbox")).split("_");
				categoryList.add(childrenCategoryValue);
			}
			
			if(entryId != 0){
				for(String[] categoryArray : categoryList){
					long parentCategoryId = GetterUtil.getLong(categoryArray[0], 0l);
					long categoryId = GetterUtil.getLong(categoryArray[1], 0l);
					
					AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(entryId, categoryId);
					// AssetEntryLocalServiceUtil.addAssetCategoryAssetEntry(categoryId, entryId);
				}
			}
		}else if(actionType.equals("publicData")){
			
			scienceApp.setDevelopersMap(CustomUtil.getLocalizationNotSetLocaleMap(params, "developers"));
			scienceApp.setLicense(CustomUtil.strNull(params.get("license")));
	
			try{
				// Description 수정
				ScienceAppDescription scienceAppDescription = ScienceAppDescriptionLocalServiceUtil
					.getScienceAppDescription(scienceApp.getDescriptionId());
				Locale[] locales = LanguageUtil.getAvailableLocales();
				for(Locale aLocale : locales){
					String languageId = LocaleUtil.toLanguageId(aLocale);
					String description = CustomUtil.strNull(params.get("description_" + languageId));
					if(!description.equals("")){
						scienceAppDescription.setContent(description, aLocale);
					}
				}
				scienceAppDescription.setUpdateDt(new Date());
				scienceAppDescription.setUpdateId(sc.getUserId());
				ScienceAppDescriptionLocalServiceUtil.updateScienceAppDescription(scienceAppDescription);
	
				UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(sc.getLiferayPortletRequest());
				// icon 등록
				String appIconStr = CustomUtil.strNull(upload.getFileName("app_icon"), "");
				if(!appIconStr.equals("")){ // 아이콘이 있는경우
					long iconId = scienceApp.getIconId();
					if(iconId != 0){
						// 기존 파일 삭제
						try{
							DLFileEntryLocalServiceUtil.deleteDLFileEntry(iconId);
						}catch(NoSuchFileEntryException e){}
					}
	
					// 카테고리 별로 APP 이 관리 되기 때문에 ScienceApp 등록시 등록한 분야의 GROUPID 사용
					List<FileEntry> appIcons = EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload, sc
						.getUserId(), scienceApp.getGroupId(), "", String.valueOf(scienceAppId), "app_icon",
						EdisonFileConstants.SCIENCE_APPS);
					FileEntry appEntry = appIcons.get(0);
					scienceApp.setIconId(appEntry.getFileEntryId());
				}
	
				// Manual 등록
				for(Locale locale : locales){
					String languageId = LocaleUtil.toLanguageId(locale);
					String fileParamsNm = "app_manual" + languageId;
					String manualStr = CustomUtil.strNull(upload.getFileName(fileParamsNm), "");
	
					if(!manualStr.equals("")){ // Manual 있는경우
						long manualId = GetterUtil.getLong(scienceApp.getManualId(locale), 0);
						if(manualId != 0){
							// 기존 파일 삭제
							try{
								DLFileEntryLocalServiceUtil.deleteDLFileEntry(manualId);
							}catch(NoSuchFileEntryException e){}
						}
	
						List<FileEntry> manuals = EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload, sc
							.getUserId(), scienceApp.getGroupId(), "", String.valueOf(scienceAppId), fileParamsNm,
							EdisonFileConstants.SCIENCE_APPS);
						FileEntry manualsEntry = manuals.get(0);
						scienceApp.setManualId(String.valueOf(manualsEntry.getFileEntryId()), locale);
					}else{
						long manualId = GetterUtil.getLong(scienceApp.getManualId(locale), 0);
						if(manualId == 0){
							scienceApp.setManualId("0", locale);
						}
					}
				}
			}catch (Exception e){
				throw new SystemException(e);
			}
		}
		scienceAppLocalService.updateScienceApp(scienceApp);
	}
	
	/**
	 * 통합 검색 앱 조회 서비스
	 * @param categoryIds -> Category 조회가 아닐 경우에는 null 입력
	 */
	public List<Map<String, Object>> retrieveListScienceAppFromExplore(long companyGroupId,long groupId, Locale locale, String[] appTypes, long[] categoryIds, String searchText, int begin, int end) throws SystemException, PortalException{
		boolean categorySearch = false;
		if(categoryIds != null && categoryIds.length > 0){
			categorySearch = true;
		}
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("begin", begin);
		param.put("end", end);
		param.put("status", 1901004);
		param.put("appTypes", appTypes);
		param.put("searchText", searchText);
		param.put("categoryIds", categoryIds);
		
		Map<String,Object> searchParam = settingScienceAppParameter(companyGroupId, groupId, locale, param, "SWNM", categorySearch, false,true);
		return retrieveListScienceApp(locale,searchParam, categorySearch, false);
	}
	
	/**
	 * 통합 검색 앱 카운트 서비스
	 * @param categoryIds -> Category 조회가 아닐 경우에는 null 입력
	 */
	public int countScienceAppFromExplore(long companyGroupId,long groupId,Locale locale, String[] appTypes, long[] categoryIds, String searchText) throws SystemException, PortalException{
		boolean categorySearch = false;
		if(categoryIds != null && categoryIds.length > 0){
			categorySearch = true;
		}
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("status", 1901004);
		param.put("appTypes", appTypes);
		param.put("searchText", searchText);
		param.put("categoryIds", categoryIds);
		
		Map<String,Object> searchParam = settingScienceAppParameter(companyGroupId, groupId, locale, param, "SWNM", categorySearch, false,true);
		return countScienceApp(locale,searchParam, categorySearch, false);
	}
	
	/**
	 * EDISON - MAIN 추천 앱 조회
	 */
	public List<Map<String, Object>> retrieveListHotScienceApp(long companyGroupId, long groupId, Locale locale,long[] appIds, int begin, int end) throws SystemException, PortalException{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("status", 1901004);
		String appTypes[] = {"Solver"};
		param.put("appTypes", appTypes);
		if(appIds != null){
			String scienceAppIdstr = ""; 
			for(long appId : appIds){
				if("".equals(scienceAppIdstr)){
					scienceAppIdstr += appId;
				}else{
					scienceAppIdstr += ", " + appId;
				}				
			}
			
			param.put("scienceAppIds", scienceAppIdstr);
		}
		Map<String,Object> searchParam = settingScienceAppParameter(companyGroupId, groupId, locale, param, "", false, false,true);
		return retrieveListScienceApp(locale,searchParam, false, false);
	}
	
	
	public List<Map<String, Object>> retrieveListScienceAppAsManager(long companyGroupId,long groupId,Locale locale, long managerId, String[] appTypes, String[] editorTypes, String searchType,String searchText, String status, boolean categorySearch,int begin, int end) throws SystemException, PortalException{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("managerId", managerId);
		param.put("searchText", searchText);
		param.put("status", status);
		param.put("appTypes", GetterUtil.getStringValues(appTypes));
		param.put("editorTypes", GetterUtil.getStringValues(editorTypes));
		param.put("begin", begin);
		param.put("end", end);
		
		Map<String,Object> searchParam = settingScienceAppParameter(companyGroupId, groupId, locale, param, searchType, categorySearch, true,false);
		return retrieveListScienceApp(locale, searchParam, categorySearch, true);
	}
	
	public int countScienceAppAsManager(long companyGroupId,long groupId,Locale locale, long managerId, String[] appTypes, String[] editorTypes, String searchType,String searchText, String status, boolean categorySearch) throws SystemException, PortalException{
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("managerId", managerId);
		param.put("searchText", searchText);
		param.put("status", status);
		param.put("appTypes", GetterUtil.getStringValues(appTypes));
		param.put("editorTypes", GetterUtil.getStringValues(editorTypes));
		
		Map<String,Object> searchParam = settingScienceAppParameter(companyGroupId, groupId, locale, param, searchType, categorySearch, true,false);
		return countScienceApp(locale, searchParam, categorySearch, true);
	}

	
	public List<Map<String, Object>> retrieveListScienceAppAsCategory(long companyGroupId,long groupId,Locale locale, long authorId, String[] appTypes, String[] editorTypes, String searchType,String searchText, String status,int begin, int end,boolean lanuageSearch) throws SystemException, PortalException{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", authorId);
		param.put("appTypes", GetterUtil.getStringValues(appTypes));
		param.put("editorTypes", GetterUtil.getStringValues(editorTypes));
		param.put("searchType", searchType);
		param.put("searchText", searchText);
		param.put("status", status);
		param.put("begin", begin);
		param.put("end", end);
		Map<String,Object> searchParam = settingScienceAppParameter(companyGroupId, groupId, locale, param, searchType, true, false,lanuageSearch);
		return retrieveListScienceApp(locale, searchParam, true, false);
	}
	
	public int countListScienceAppAsCategory(long companyGroupId,long groupId,Locale locale, long authorId, String[] appTypes, String[] editorTypes, String searchType,String searchText, String status,boolean lanuageSearch) throws SystemException, PortalException{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", authorId);
		param.put("appTypes", GetterUtil.getStringValues(appTypes));
		param.put("editorTypes", GetterUtil.getStringValues(editorTypes));
		param.put("searchType", searchType);
		param.put("searchText", searchText);
		param.put("status", status);
		
		Map<String,Object> searchParam = settingScienceAppParameter(companyGroupId, groupId, locale, param, searchType, true, false,lanuageSearch);
		return countScienceApp(locale,searchParam, true, false);
	}
	
	
	/**
	 * 앱 이름을 통한 앱 LIST 조회
	 * @param companyGroupId - themeDisplay.getCompanyGroupId()
	 * @param groupId        - PortalUtil.getScopeGroupId(request)
	 * @param locale         - themeDisplay.getLocale()
	 * @param appName        - 앱 이름
	 * @param categorySearch - 카테고리 조회 여부(PORTAL->false,SITE->true)
	 * @return List<Map<String, Object>>
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	public List<Map<String, Object>> retrieveListScienceAppByName(long companyGroupId,long groupId,Locale locale,String appName,boolean categorySearch) throws PortalException, SystemException{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("status", 1901004);
		param.put("name", StringUtil.trim(appName));
		
		Map<String,Object> searchParam = settingScienceAppParameter(companyGroupId, groupId, locale, param, "", categorySearch, false, true);
		return retrieveListScienceApp(locale, searchParam, true, false);
	}
	
	/**
	 * 카테고리를 제외한 앱 조회
	 * @param groupId - 조회 하는 분야의 GROUP ID (PortalUtil.getScopeGroupId(request))
	 * @param locale  - 현재 EDISON 포털의 LOCALE (themeDisplay.getLocale())
	 * @param authorId - 소유자 ID
	 * @param appTypes - 앱 종류 (ScienceAppConstants 참조),null 허용
	 * @param editorTypes  - editor 종류 (ScienceAppConstants 참조),null 허용
	 * @param searchType   - 검색타입(APP_MANAGER_SEARCH_ALL,SWORGNM)
	 * @param searchText   - 검색어
	 * @param status       - 앱 상태
	 * @param begin
	 * @param end
	 * @param lanuageSearch - 앱의 서비스 언어별 조회 여부
	 */
	public List<Map<String, Object>> retrieveListScienceApp(long groupId, Locale locale, long authorId, String[] appTypes, String[] editorTypes, String searchType,String searchText, String status,int begin, int end,boolean lanuageSearch) throws SystemException, PortalException{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", authorId);
		param.put("appTypes", GetterUtil.getStringValues(appTypes));
		param.put("editorTypes", GetterUtil.getStringValues(editorTypes));
		param.put("searchType", searchType);
		param.put("searchText", searchText);
		param.put("status", status);
		param.put("begin", begin);
		param.put("end", end);
		
		Map<String,Object> searchParam = settingScienceAppParameter(0, groupId, locale, param, searchType, false, false,lanuageSearch);
		return retrieveListScienceApp(locale, searchParam, false, false);
	}
	
	public int countListScienceApp(long groupId, Locale locale, long authorId, String[] appTypes, String[] editorTypes, String searchType,String searchText, String status,boolean lanuageSearch) throws SystemException, PortalException{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", authorId);
		param.put("appTypes", GetterUtil.getStringValues(appTypes));
		param.put("editorTypes", GetterUtil.getStringValues(editorTypes));
		param.put("searchType", searchType);
		param.put("searchText", searchText);
		param.put("status", status);
		
		Map<String,Object> searchParam = settingScienceAppParameter(0, groupId, locale, param, searchType, false, false,lanuageSearch);
		return countScienceApp(locale, searchParam, false, false);
	}
	
	/**
	 * 조회 Parameter 셋팅
	 * @param companyGroupId - categorySearch parameter가 true 이면서, param.get("categoryIds") 가 null 또는 length ==0 일 경우 해당 분야의 카테고리 조회를 위한 companyGroupId
	 * @param groupId - 분야
	 * @param locale
	 * @param param   - 조회 parameter 셋팅
	 * @param searchType - 검색타입(APP_MANAGER_SEARCH_ALL,SWORGNM)
	 * @param categorySearch - 카테고리 조회 여부
	 * @param managerSearch  - 관리중인 앱 조회 여부
	 * @param lanuageSearch  - 앱의 서비스 언어를 통하여 앱 결과 필터링  여부
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	protected Map<String, Object> settingScienceAppParameter(long companyGroupId,long groupId, Locale locale,Map<String, Object> param, String searchType, boolean categorySearch,boolean managerSearch,boolean lanuageSearch) throws PortalException, SystemException{
		Map<String,Object> searchMap = new HashMap<String,Object>();
		
		Group group = GroupLocalServiceUtil.getGroup(groupId);
		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(group.getCompanyId(), User.class.getName(),ExpandoTableConstants.DEFAULT_TABLE_NAME);
		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),EdisonExpando.USER_UNIVERSITY);
		searchMap.put("columnId", column.getColumnId());
		
		searchMap.put("companyId", GetterUtil.getLong(param.get("companyId")));
		searchMap.put("appTypes", GetterUtil.getStringValues(param.get("appTypes")));
		searchMap.put("editorTypes",GetterUtil.getStringValues(param.get("editorTypes")));
		searchMap.put("scienceAppIds", GetterUtil.getString(param.get("scienceAppIds")));		
		searchMap.put("status", GetterUtil.getLong(param.get("status")));
		searchMap.put("userId", GetterUtil.getLong(param.get("userId")));
		searchMap.put("name", GetterUtil.getString(param.get("name")));
		
		int end = GetterUtil.getInteger(param.get("end"));
		if(end != 0){
			searchMap.put("begin", GetterUtil.getInteger(param.get("begin")));
			searchMap.put("end", GetterUtil.getInteger(param.get("end")));
		}
		
		if(lanuageSearch){
			searchMap.put("targetLanguage", LocaleUtil.toLanguageId(locale));
		}
		
		if(categorySearch){
		  long[] categoryIds = null;
		  Object categoryIdObject = param.get("categoryIds");
		  if(categoryIdObject instanceof long[]){
		    categoryIds = (long[]) categoryIdObject;
		  }
			
			if(categoryIds == null || categoryIds.length == 0){
				String categoryStr = "";
				AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,EdisonAssetCategory.GLOBAL_DOMAIN);
				
				AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
				List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId());
				
				for(AssetCategory aCategory : aCategoryList){
					if(aCategory.getVocabularyId() == aVocabulary.getVocabularyId() && aCategory.getParentCategoryId() != 0){
							if(categoryStr.equals("")){
								categoryStr += aCategory.getCategoryId();
							}else{
								categoryStr += "," + aCategory.getCategoryId();
							}
						}
				}
				searchMap.put("categoryIds", StringUtil.split(categoryStr, 0l));
			}else{
				searchMap.put("categoryIds", categoryIds);
			}
			
			searchMap.put("classNameId",ClassNameLocalServiceUtil.getClassNameId(ScienceApp.class));
		}
		
		if(managerSearch){
			searchMap.put("managerId", param.get("managerId"));
		}
//		searchText
		if(!GetterUtil.getString(searchType).equals("")){
			searchMap.put("searchValue", GetterUtil.getString(param.get("searchText")));
			
			if(searchType.equals("APP_MANAGER_SEARCH_ALL")){
				searchMap.put("APP_MANAGER_SEARCH_ALL", "SET");
				searchMap.put("searchOrgCode", makeOrgCode(locale,GetterUtil.getString(param.get("searchText"))));
			}else if(searchType.equals("SWORGNM")){
				searchMap.put("searchOrgCode", makeOrgCode(locale,GetterUtil.getString(param.get("searchText"))));
				searchMap.put("SWORGNM", "SET");
			}else{
				searchMap.put(searchType, "SET");
			}
		}
		
		return searchMap;
	}
	
	private String[] makeOrgCode(Locale locale, String searchText){
		String searchOrgCodeStr = "";
		// 공통코드에서 Like 조회
		List<Map<String, String>> commonCodeList = EdisonExpndoUtil.getCodeListByUpCode(1501, locale);
		for(Map<String, String> codeMap : commonCodeList){
			String codeName = codeMap.get(EdisonExpando.CDNM);
			if(codeName.indexOf(searchText) > -1){
				String codeValue = codeMap.get(EdisonExpando.CD);

				if(searchOrgCodeStr.equals("")){
					searchOrgCodeStr += codeValue;
				}else{
					searchOrgCodeStr += "," + codeValue;
				}
			}
		}
		
		if(searchOrgCodeStr.equals("")){
			String[] returnArray = {searchText};
			return returnArray;
		}else{
			return StringUtil.split(searchOrgCodeStr, StringPool.COMMA);
		}
	}
	
	
	
	private int countScienceApp(Locale locale, Map<String, Object> searchParam,boolean categorySearch,boolean managerSearch) throws SystemException, PortalException{
		return scienceAppFinder.countScienceApp(searchParam, categorySearch, managerSearch);
	}
	
	private List<Map<String, Object>> retrieveListScienceApp(Locale locale,Map<String, Object> searchParam,boolean categorySearch,boolean managerSearch) throws SystemException, PortalException{
		List<Object[]> scienceAppList = scienceAppFinder.retrieveListScienceApp(searchParam, categorySearch, managerSearch);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < scienceAppList.size(); i++){
			Object[] resultArray = scienceAppList.get(i);
			ScienceApp scienceApp = (ScienceApp) resultArray[0];
			String firstName = (String) resultArray[1];
			String screenName = (String) resultArray[2];
			long userOrgCode = (Long) resultArray[3];
			String affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(userOrgCode, EdisonExpando.CDNM,locale);
			
			Map<String, Object> resultRow = scienceApp.getModelAttributes();
			resultRow.put("title", scienceApp.getTitle(locale));
			
			if(StringUtils.hasText(scienceApp.getManualId(locale))){
			    resultRow.put("current_manualId", scienceApp.getManualId(locale));
			}
			
			resultRow.put("developers", StringUtil.split(scienceApp.getDevelopers(locale), StringPool.NEW_LINE));
			resultRow.put("firstName", firstName);
			resultRow.put("screenName", screenName);
			resultRow.put("affiliation", affiliation);
			
			if(scienceApp.getIconId() != 0){
				resultRow.put("iconId", scienceApp.getIconId());
				try{
					DLFileEntry iconDl = DLFileEntryLocalServiceUtil.getDLFileEntry(scienceApp.getIconId());
					resultRow.put("iconRepositoryId", iconDl.getRepositoryId());
					resultRow.put("iconUuid", iconDl.getUuid());
					resultRow.put("iconTitle", iconDl.getTitle());
				}catch (Exception e){
					if(e instanceof NoSuchFileEntryException){
					}else{
						throw new PortalException(e);
					}
				}
			}
			
			// 메뉴얼
			long manualId = GetterUtil.getLong(scienceApp.getManualId(locale), 0l);
			if(manualId != 0){
				try{
					DLFileEntry manualDl = DLFileEntryLocalServiceUtil.getDLFileEntry(manualId);
					resultRow.put("manualId", manualId);
					resultRow.put("manualRepositoryId", manualDl.getRepositoryId());
					resultRow.put("manualUuid", manualDl.getUuid());
					resultRow.put("manualTitle", manualDl.getTitle());
				}catch (Exception e){
					if(e instanceof NoSuchFileEntryException){
					}else{
						throw new PortalException(e);
					}
				}
			}
			
			returnList.add(resultRow);
		}
		
		return returnList;
	}
	
	public Map<String, Object> getScienceAppReturnObject(long scienceAppId, Locale locale)
			throws PortalException, SystemException, ParseException{
			ScienceApp scienceApp = super.getScienceApp(scienceAppId);
			Map<String, Object> returnMap = scienceApp.getModelAttributes();
			returnMap.put("currentTitle", scienceApp.getTitle(locale));

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			df.setTimeZone(TimeZoneUtil.getDefault());
			returnMap.put("createDate", df.format(scienceApp.getCreateDate()));
			returnMap.put("statusDate", scienceApp.getStatusDate() == null ? "": df.format(scienceApp.getStatusDate()));
			returnMap.put("descriptionId", scienceApp.getDescriptionId());
			
			// 프로젝트
			boolean project = false;
			if(scienceApp.getProjectCategoryId() != 0){
				project = true;
			}
			
			returnMap.put("project", project);

			// 카테고리 & 프로젝트
			long entryId = AssetEntryLocalServiceUtil.getEntry(ScienceApp.class.getName(), scienceAppId).getEntryId();
			List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(entryId);
			
			String parentCategory = "";
			String childrenCategory = "";
			for(AssetCategory categoryLink : aCategoryList){
				if(childrenCategory.equals("")){
					childrenCategory += categoryLink.getParentCategoryId() + "_" + categoryLink.getCategoryId();
				}else{
					childrenCategory += "," + categoryLink.getParentCategoryId() + "_" + categoryLink.getCategoryId();
				}

				if(parentCategory.equals("")){
					parentCategory += categoryLink.getParentCategoryId();
				}else{
					parentCategory += "," + categoryLink.getParentCategoryId();
				}
			}

			returnMap.put("childrenCategory", childrenCategory);
			returnMap.put("parentCategory", parentCategory);

			// User 정보 조회
			User appUser = UserLocalServiceUtil.getUser(scienceApp.getAuthorId());
			returnMap.put("userId", appUser.getUserId());
			returnMap.put("userName", appUser.getFirstName());
			returnMap.put("userScreenName", appUser.getScreenName());

			long classPK = GetterUtil.getLong(appUser.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY),
				0);
			String affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(classPK, EdisonExpando.CDNM, locale);
			returnMap.put("affiliation", affiliation);

			returnMap.put("developers", StringUtil.split(scienceApp.getDevelopers(locale), StringPool.NEW_LINE));
			returnMap.put("developersTextArea", scienceApp.getDevelopers());

			// 파일 - icon
			if(scienceApp.getIconId() != 0){
				try{
					DLFileEntry iconDl = DLFileEntryLocalServiceUtil.getDLFileEntry(scienceApp.getIconId());
					returnMap.put("iconRepositoryId", iconDl.getRepositoryId());
					returnMap.put("iconUuid", iconDl.getUuid());
					returnMap.put("iconTitle", iconDl.getTitle());
				}catch (Exception e){
					if(e instanceof NoSuchFileEntryException){
					}else{
						throw new PortalException(e);
					}
				}
			}

			// 메뉴얼
			for(Locale aLocale : LanguageUtil.getAvailableLocales()){
				long manualId = GetterUtil.getLong(scienceApp.getManualId(aLocale), 0l);
				String languageId = LocaleUtil.toLanguageId(aLocale);
				if(manualId != 0){
					try{
						DLFileEntry manualDl = DLFileEntryLocalServiceUtil.getDLFileEntry(manualId);
						returnMap.put("manualId_" + languageId, manualId);
						returnMap.put("manualRepositoryId_" + languageId, manualDl.getRepositoryId());
						returnMap.put("manualUuid_" + languageId, manualDl.getUuid());
						returnMap.put("manualTitle_" + languageId, manualDl.getTitle());
					}catch (Exception e){
						if(e instanceof NoSuchFileEntryException){
						}else{
							throw new PortalException(e);
						}
					}
				}
			}
			returnMap.put("manualIds", scienceApp.getManualId());

			// 소스파일
			long srcFileId = Long.parseLong(CustomUtil.strNull(scienceApp.getSrcFileName(), "0"));
			if(srcFileId != 0){
				DLFileEntry srcFileDl = DLFileEntryLocalServiceUtil.getDLFileEntry(srcFileId);
				returnMap.put("srcFileId", srcFileId);
				returnMap.put("srcFileTitle", srcFileDl.getTitle());
			}

			ScienceAppDescription description = ScienceAppDescriptionLocalServiceUtil.getScienceAppDescription(scienceApp.getDescriptionId());

			Locale[] availableLocales = LanguageUtil.getAvailableLocales();
			String selectLocaleId = LocaleUtil.toLanguageId(locale);
			Map<String, Object> descriptionMap = new HashMap<String, Object>();
			for(Locale alocale : availableLocales){
				String languageId = LocaleUtil.toLanguageId(alocale);
				descriptionMap.put("description_" + languageId, description.getContent(alocale));
			}
			returnMap.put("description", descriptionMap);
			returnMap.put("selectLocaleId", selectLocaleId);

			// ScienceApp 관리자
			List<ScienceAppManager> mList = ScienceAppManagerLocalServiceUtil.getManagersByScienceAppId(scienceAppId);
			List<Map<String, Object>> managerList = new ArrayList<Map<String, Object>>();
			for(ScienceAppManager manager : mList){
				Map<String, Object> managerMap = new HashMap<String, Object>();
				User managerUser = UserLocalServiceUtil.getUser(manager.getUserId());

				managerMap.put("screenName", managerUser.getScreenName());
				managerMap.put("firstName", managerUser.getFirstName());
				managerMap.put("email", managerUser.getEmailAddress());
				managerMap.put("createDate", manager.getCreateDate());
				managerList.add(managerMap);
			}

			returnMap.put("managerList", managerList);

			return returnMap;
		}
	
	
	public void deleteScienceAppRelation(long scienceAppId) throws PortalException, SystemException{
		ScienceApp scienceApp = scienceAppLocalService.getScienceApp(scienceAppId);

		// INPUT PORT
		try{
			scienceAppInputPortsLocalService.deleteScienceAppInputPorts(scienceAppId);
		}catch (NoSuchScienceAppInputPortsException e){

		}

		// OUTPUT PORT
		try{
			scienceAppOutputPortsLocalService.deleteScienceAppOutputPorts(scienceAppId);
		}catch (NoSuchScienceAppOutputPortsException e){

		}

		// APP MANAGER
		List<ScienceAppManager> scienceAppManagers = scienceAppManagerLocalService.getManagersByScienceAppId(
			scienceAppId);
		for(ScienceAppManager scienceAppManager : scienceAppManagers){
			scienceAppManagerLocalService.deleteScienceAppManager(scienceAppManager);
		}

		// APP Description
		if(scienceApp.getDescriptionId() != 0){
			scienceAppDescriptionLocalService.deleteScienceAppDescription(scienceApp.getDescriptionId());
		}
		
		long entryId = AssetEntryLocalServiceUtil.getEntry(ScienceApp.class.getName(), scienceAppId).getEntryId();
		// 카테고리 삭제
		AssetCategoryLocalServiceUtil.clearAssetEntryAssetCategories(entryId);

		// asset link 테이블 삭제
		List<AssetLink> entryLinkList = AssetLinkLocalServiceUtil.getLinks(entryId);
		for(AssetLink link : entryLinkList){
			AssetLinkLocalServiceUtil.deleteAssetLink(link);
		}

		// asset Entry 테이블 삭제
		AssetEntryLocalServiceUtil.deleteAssetEntry(entryId);

		// FILE FOLDER
		String folderSeq = scienceApp.getGroupId() + "_" + scienceAppId;
		try{
			EdisonFileUtil.deleteGroupEdisonFile(scienceApp.getGroupId(), EdisonFileConstants.SCIENCE_APPS,
				folderSeq);
		}catch (NoSuchFolderException e){

		}

		// APP FAVORITE
		scienceAppFavoriteLocalService.deleteFavoriteApp(scienceAppId);

		// SCIENCEAPP
		// NoSuchScienceAppInputPortsException - 처리 못함
		// NoSuchScienceAppOutputPortsException - 처리 못함
		// scienceAppLocalService.deleteScienceApp(scienceApp);
		super.deleteScienceApp(scienceApp);
	}
	
	
	public ScienceApp updateExeInfomaionScienceApp(ServiceContext sc, Map params, long scienceAppId)
		throws PortalException, SystemException{
		ScienceApp scienceApp = super.getScienceApp(scienceAppId);

		scienceApp.setExeFileName(CustomUtil.strNull(params.get("exeFileName")));
		scienceApp.setOpenLevel(CustomUtil.strNull(params.get("openLevel")));
		scienceApp.setAppType(CustomUtil.strNull(params.get("appType")));
		scienceApp.setRunType(CustomUtil.strNull(params.get("runType")));
		scienceApp.setParallelModule(CustomUtil.strNull(params.get("parallelModule")));
		scienceApp.setEditorType(CustomUtil.strNull(params.get("editorType")));
		scienceApp.setMaxCpus(GetterUtil.getInteger(params.get("maxCpus"), 0));
		scienceApp.setDefaultCpus(GetterUtil.getInteger(params.get("defaultCpus"), 0));
		scienceApp.setModifiedDate(new Date());
		scienceApp.setRecentModifierId(sc.getUserId());

		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(sc.getLiferayPortletRequest());

		// Source File Upload
		String appSoruceFileStr = CustomUtil.strNull(upload.getFileName("sourceFile"), "");
		if(!appSoruceFileStr.equals("")){
			long srcFileId = GetterUtil.getLong(scienceApp.getSrcFileName(), 0);
			if(srcFileId != 0){
				// 기존 파일 삭제
				try{
					DLFileEntryLocalServiceUtil.deleteDLFileEntry(srcFileId);
				}catch(NoSuchFileEntryException e){}
			}

			try{
				List<FileEntry> srcFiles = EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload, sc
					.getUserId(), scienceApp.getGroupId(), "", String.valueOf(scienceAppId), "sourceFile",
					EdisonFileConstants.SCIENCE_APPS);

				FileEntry appEntry = srcFiles.get(0);
				scienceApp.setSrcFileName(String.valueOf(appEntry.getFileEntryId()));
			}catch (Exception e){
				e.printStackTrace();
				throw new SystemException(e);

			}
		}
		// 실행파일 및 파일 업로드 기능 개발

		super.updateScienceApp(scienceApp);

		return scienceApp;
	}
	
	public long copyScienceApp(ServiceContext sc, long scienceAppId) throws SystemException{
		try{
			// APP 정보 조회
			ScienceApp scienceApp = scienceAppLocalService.getScienceApp(scienceAppId);
			ScienceAppDescription scienceAppDescription = ScienceAppDescriptionLocalServiceUtil
				.getScienceAppDescription(scienceApp.getDescriptionId());
			List<ScienceAppManager> scienceAppManagers = ScienceAppManagerLocalServiceUtil
				.getManagersByScienceAppId(scienceAppId);
			long countScienceAppInputPorts = ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPortsesCount(
				scienceAppId);
			long countScienceAppOutputPorts = ScienceAppOutputPortsLocalServiceUtil.getScienceAppOutputPortsesCount(
				scienceAppId);

			long newAppId = CounterLocalServiceUtil.increment(ScienceApp.class.getName());

			// New App
			ScienceApp newScienceApp = (ScienceApp) scienceApp.clone();
			newScienceApp.setUuid(null);
			newScienceApp.setScienceAppId(newAppId);
			newScienceApp.setCreateDate(new Date());
			newScienceApp.setModifiedDate(new Date());
			newScienceApp.setPreviousVersionId(scienceApp.getScienceAppId());
			newScienceApp.setStatus(1901001);
			newScienceApp.setExeFileName(null);
			newScienceApp.setSrcFileName(null);
			newScienceApp.setRecentModifierId(0);
			newScienceApp.setStatusDate(null);
			newScienceApp.setProjectCategoryId(0);

			// New App Version
			String preVersion = scienceApp.getVersion();
			String[] versions = preVersion.split("\\.");
			int lastVersions = (Integer.parseInt(versions[versions.length - 1])) + 1;
			String newVersion = versions[0] + "." + versions[1] + "." + lastVersions;

			while1: while(true){
				boolean isDuplicated = ScienceAppLocalServiceUtil.existApp(scienceApp.getName(), newVersion);
				if(!isDuplicated){
					newScienceApp.setVersion(newVersion);
					break while1;
				}else{
					lastVersions += 1;
					newVersion = versions[0] + "." + versions[1] + "." + lastVersions;
				}
			}

			// copy icon
			if(scienceApp.getIconId() != 0){
				try{
					long fileEntryId = EdisonFileUtil.copyDlFile(sc.getLiferayPortletRequest(), scienceApp.getIconId(),
						"", String.valueOf(newAppId), EdisonFileConstants.SCIENCE_APPS);
					newScienceApp.setIconId(fileEntryId);
				}catch (Exception e){
					if(e instanceof NoSuchFileEntryException){
					}else{
						throw new SystemException(e);
					}
				}
			}
			// copy Manual
			Locale[] locales = LanguageUtil.getAvailableLocales();
			for(Locale locale : locales){
				int manualId = GetterUtil.getInteger(scienceApp.getManualId(locale), 0);
				if(manualId != 0){
					try{
						long fileEntryId = EdisonFileUtil.copyDlFile(sc.getLiferayPortletRequest(), manualId, "", String
							.valueOf(newAppId), EdisonFileConstants.SCIENCE_APPS);
						newScienceApp.setManualId(String.valueOf(fileEntryId), locale);
					}catch (Exception e){
						if(e instanceof NoSuchFileEntryException){
						}else{
							throw new SystemException(e);
						}
					}
				}
			}

			// copy & add ScienceAppDescription
			ScienceAppDescription newScienceAppDescription = (ScienceAppDescription) scienceAppDescription.clone();
			long descriptionId = 0;
			long descriptionCnt = 1;
			while(descriptionCnt!=0){
				DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppDescription.class);
				descriptionId = CounterLocalServiceUtil.increment(ScienceAppDescription.class.getName());
				query.add(RestrictionsFactoryUtil.eq("descriptionId", descriptionId));	
				descriptionCnt = ScienceAppDescriptionLocalServiceUtil.dynamicQueryCount(query);
			}
			
			newScienceAppDescription.setDescriptionId(descriptionId);
			newScienceAppDescription.setInsertDt(new Date());
			newScienceAppDescription.setUpdateId(0);
			newScienceAppDescription.setUpdateDt(null);
			ScienceAppDescriptionLocalServiceUtil.addScienceAppDescription(newScienceAppDescription);

			newScienceApp.setDescriptionId(descriptionId);

			// copy & add scienceAppManagers
			for(ScienceAppManager scienceAppManager : scienceAppManagers){
				ScienceAppManager newScienceAppManager = (ScienceAppManager) scienceAppManager.clone();
				long scienceAppManagerId = CounterLocalServiceUtil.increment(ScienceAppManager.class.getName());
				newScienceAppManager.setScienceAppManagerId(scienceAppManagerId);
				newScienceAppManager.setCreateDate(new Date());
				newScienceAppManager.setScienceAppId(newAppId);
				ScienceAppManagerLocalServiceUtil.addScienceAppManager(newScienceAppManager);
			}

			// copy & add ScienceAppInputPorts
			if(countScienceAppInputPorts != 0){
				ScienceAppInputPorts scienceAppInputPorts = ScienceAppInputPortsLocalServiceUtil
					.getScienceAppInputPorts(scienceAppId);
				ScienceAppInputPorts newScienceAppInputPorts = (ScienceAppInputPorts) scienceAppInputPorts.clone();
				newScienceAppInputPorts.setScienceAppId(newAppId);
				//port sample File 초기화
				newScienceAppInputPorts.setInputPortsSampleFile("");
				ScienceAppInputPortsLocalServiceUtil.addScienceAppInputPorts(newScienceAppInputPorts);
			}

			// copy & add ScienceAppOutputPorts
			if(countScienceAppOutputPorts != 0){
				ScienceAppOutputPorts scienceAppOutputPorts = ScienceAppOutputPortsLocalServiceUtil
					.getScienceAppOutputPorts(scienceAppId);
				ScienceAppOutputPorts newScienceAppOutputPorts = (ScienceAppOutputPorts) scienceAppOutputPorts
					.clone();
				newScienceAppOutputPorts.setScienceAppId(newAppId);
				ScienceAppOutputPortsLocalServiceUtil.addScienceAppOutputPorts(newScienceAppOutputPorts);
			}
			
			//ASSET Entry 등록
			scienceAppAddAssetEntry(sc.getCompanyId(), scienceApp.getGroupId(), newScienceApp);

			// New App 정보 등록
			super.addScienceApp(newScienceApp);

			// 기존 APP 상태값 변경
			scienceApp.setStatus(1901003);
			super.updateScienceApp(scienceApp);
			return newAppId;
		}catch (Exception e){
			throw new SystemException(e);
		}
	}
	
	
	public void addScienceAppFile(long companyId, String appName, String appVersion, String fileName,InputStream uploadedInputStream) throws SystemException{
		try{
			String appBasePath = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + appName
				+ File.separator + appVersion;
			String srcPath = appBasePath + File.separator + "src";
			String binPath = appBasePath + File.separator + "bin";
			this.saveToScienceAppStorage(srcPath, fileName, uploadedInputStream);

			this.unzipScienceAppZipFile(srcPath + File.separator + fileName, binPath);

		}catch (Exception e){
			throw new SystemException(e);
		}
	}
	
	public List<Map<String, Object>> retrieveListAppTest(Map<String, Object> params) throws PortalException,SystemException{
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		List<Object[]> resultList = scienceAppFinder.retrieveListAppTest(params);
	
		if(resultList != null){
			try{
	
				for(Object[] result : resultList){
					Map<String, Object> resultRow = new HashMap<String, Object>();
					resultRow.put("simulationCreateDt", CustomUtil.strNull(result[0]));
					resultRow.put("totalCnt", CustomUtil.strNull(result[1]));
					resultRow.put("queuedCnt", CustomUtil.strNull(result[2]));
					resultRow.put("runCnt", CustomUtil.strNull(result[3]));
					resultRow.put("successCnt", CustomUtil.strNull(result[4]));
					resultRow.put("failedCnt", CustomUtil.strNull(result[5]));
					resultRow.put("sUuid", CustomUtil.strNull(result[6]));
					resultRow.put("jobUuid", CustomUtil.strNull(result[7]));
	
					returnList.add(resultRow);
				}
			}catch (Exception e){
				throw new SystemException(e);
			}
		}
		return returnList;
	}
	
	public int countAppTest(long scienceAppId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("scienceAppId", scienceAppId);
		return scienceAppFinder.countAppTest(params);
	}
	
	public List<Map<String, Object>> getMyAppListWithQna(Map params, Locale locale) throws PortalException,
	SystemException{
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		String isPortal = CustomUtil.strNull(params.get("isPortal"));
		if(isPortal.equals("false")){
			long companyGroupId = Long.parseLong(CustomUtil.strNull(params.get("companyGroupId")));
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId")));
			;
			// 카테고리
			AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,
				EdisonAssetCategory.GLOBAL_DOMAIN);
	
			AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
			List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry
				.getEntryId());
	
			String categoryStr = "";
			for(AssetCategory aCategory : aCategoryList){
				if(aCategory.getVocabularyId() == aVocabulary.getVocabularyId() && aCategory
					.getParentCategoryId() == 0){
					if(categoryStr.equals("")){
						categoryStr += aCategory.getCategoryId();
					}else{
						categoryStr += "," + aCategory.getCategoryId();
					}
				}
			}
	
			long[] parentCategoryId = StringUtil.split(categoryStr, 0l);
			params.put("parentCategoryId", parentCategoryId);
		}
	
		List<Object[]> resultList = scienceAppFinder.getMyAppListWithQna(params);
	
		if(resultList != null){
			try{
				for(Object[] result : resultList){
					ScienceApp scienceApp = (ScienceApp) result[0];
	
					Map<String, Object> resultRow = new HashMap<String, Object>();
	
					resultRow.put("scienceAppId", scienceApp.getScienceAppId());
					resultRow.put("groupId", scienceApp.getGroupId());
					resultRow.put("userId", scienceApp.getAuthorId());
					resultRow.put("title", scienceApp.getTitle(locale));
					resultRow.put("version", scienceApp.getVersion());
					resultRow.put("name", scienceApp.getName());
					resultRow.put("answerCount", GetterUtil.get(CustomUtil.strNull(result[1]), 0));
	
					User appUser = UserLocalServiceUtil.getUser(scienceApp.getAuthorId());
					resultRow.put("userFirstName", appUser.getFirstName());
	
					long classPK = GetterUtil.getLong(appUser.getExpandoBridge().getAttribute(
						EdisonExpando.USER_UNIVERSITY), 0);
					String affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(classPK, EdisonExpando.CDNM,
						locale);
					resultRow.put("affiliation", affiliation);
	
					long appManagerPlid = PortalUtil.getPlidFromPortletId(GetterUtil.get(CustomUtil.strNull(scienceApp
						.getGroupId()), 0L), false, "scienceappmanager_WAR_edisonappstore2016portlet");
					resultRow.put("appManagerPlid", appManagerPlid);
	
					long manualId = GetterUtil.getLong(scienceApp.getManualId(locale), 0l);
					if(manualId != 0){
						resultRow.put("manualId", manualId);
						DLFileEntry manualDl = DLFileEntryLocalServiceUtil.getDLFileEntry(manualId);
						resultRow.put("manualRepositoryId", manualDl.getRepositoryId());
						resultRow.put("manualUuid", manualDl.getUuid());
						resultRow.put("manualTitle", manualDl.getTitle());
					}
	
					returnList.add(resultRow);
				}
			}catch (Exception e){
				throw new SystemException(e);
			}
		}
		return returnList;
	}
	
	
	public List<Map<String, Object>> getListMyAppQna(Map params, Locale locale){
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		List<Object[]> resultList = scienceAppFinder.getListMyAppQna(params);
		if(resultList != null){

			for(Object[] result : resultList){

				Map<String, Object> resultRow = new HashMap<String, Object>();

				resultRow.put("boardSeq", CustomUtil.strNull(result[0]));
				resultRow.put("title", CustomUtil.strNull(LocalizationUtil.getLocalization(CustomUtil.strNull(
					result[1]), LanguageUtil.getLanguageId(locale))));
				resultRow.put("content", CustomUtil.strNull(LocalizationUtil.getLocalization(CustomUtil.strNull(
					result[2]), LanguageUtil.getLanguageId(locale))));
				resultRow.put("writerId", CustomUtil.strNull(result[3]));
				resultRow.put("writerDate", new SimpleDateFormat("yyyy-MM-dd").format((Date) result[4]));
				resultRow.put("readCnt", CustomUtil.strNull(result[5]));
				resultRow.put("replyCount", CustomUtil.strNull(result[6]));
				resultRow.put("groupId", CustomUtil.strNull(result[7]));

				try{
					User writerUser = userPersistence.fetchByPrimaryKey(GetterUtil.get(CustomUtil.strNull(result[3]),
						0L));
					resultRow.put("writerUserFirstName", writerUser.getFirstName());
				}catch (SystemException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				returnList.add(resultRow);
			}
		}
		return returnList;
	}
	
	
	/**
	 * 관련정보(Asset) 포틀릿에서 사용하는 서비스 - 리스트
	 * 입력된 검색어로 사이언스앱을 조회함
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> searchAssetEntryModelAPP(Map params){
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = null;

		List<Object[]> contentLinkEntryList = scienceAppFinder.searchTextEntryScienceAPPList(params);

		for(Object[] content : contentLinkEntryList){
			map = new HashMap<String, Object>();

			map.put("modelSeq", content[0]);
			map.put("name", content[1]);
			map.put("title", content[2]);
			map.put("modelDiv", content[3]);
			map.put("status", content[4]);
			map.put("version", content[5]);

			returnList.add(map);
		}
		return returnList;
	}

	/**
	 * 관련정보(Asset) 포틀릿에서 사용하는 서비스  - 카운트
	 * 입력된 검색어로 사이언스앱을 조회함
	 * @param params
	 * @return
	 */
	public int searchAssetEntryModelAPPCount(Map params){
		return scienceAppFinder.searchTextEntryScienceAPPCount(params);
	}
	
	/**
	 * 관련정보(Asset) 포틀릿에서 사용하는 서비스  - 리스트
	 * entryId(Asset) 와 관련된 모든 사이언스 앱 리스트를 조회함.
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> relatedAssetLinkedEntryScienceAPP(Map params){
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		try{
			Map<String, Object> map = null;
			List<Object[]> contentLinkEntryList = scienceAppFinder.searchTextEntryScienceAPPList(params);

			for(Object[] content : contentLinkEntryList){
				map = new HashMap<String, Object>();
				map.put("modelSeq", content[0]);
				map.put("name", content[1]);
				map.put("title", content[2]);
				map.put("modelDiv", content[3]);
				map.put("status", content[4]);
				map.put("version", content[5]);
				map.put("groupId", content[6]);

				returnList.add(map);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return returnList;
	}
	
	/**
	 * 앱을 ENTRY에 등록
	 * 
	 * @param companyId
	 * @param groupId
	 * @param scienceApp
	 * @return long
	 * @throws PortalException
	 * @throws SystemException
	 */
	private long scienceAppAddAssetEntry(long companyId, long groupId, ScienceApp scienceApp) throws PortalException,
		SystemException{
		try{
			// entry 등록
			AssetEntry assetEntry = assetEntryLocalService.updateEntry(scienceApp.getUserId(), groupId, scienceApp
				.getCreateDate(), scienceApp.getModifiedDate(), ScienceApp.class.getName(), scienceApp.getScienceAppId(), scienceApp
					.getUuid(), 0, null, null, true, null, null, null, ContentTypes.TEXT_PLAIN, scienceApp.getTitle(),
				null, null, null, null, 0, 0, null, false);
			return assetEntry.getEntryId();
		}catch (Exception e){
			e.printStackTrace();
		}

		return 0;
	}
	
	
	/**
	 * ScienceApp Mirgation = 2017-03-23 HKD
	 * 기존 등록된 ScienceApp을 AssetEntry 등록 및 AssetCategory에 등록 
	 * @param scienceApp
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public boolean migrationScienceApp(ScienceApp scienceApp) throws PortalException, SystemException{
		boolean existAssetEntry = true;
		
		//check exist
		try{
			AssetEntryLocalServiceUtil.getEntry(ScienceApp.class.getName(), scienceApp.getScienceAppId());
			
		}catch(NoSuchEntryException e){
			long entryId = CounterLocalServiceUtil.increment(AssetEntry.class.getName());
			AssetEntry newAssetEntry =  AssetEntryLocalServiceUtil.createAssetEntry(entryId);
			newAssetEntry.setGroupId(scienceApp.getGroupId());
			newAssetEntry.setCompanyId(scienceApp.getCompanyId());
			newAssetEntry.setUserId(scienceApp.getUserId());
			newAssetEntry.setUserName(UserLocalServiceUtil.getUser(scienceApp.getAuthorId()).getScreenName());
			newAssetEntry.setCreateDate(scienceApp.getCreateDate());
			newAssetEntry.setModifiedDate(scienceApp.getModifiedDate());
			newAssetEntry.setClassName(ScienceApp.class.getName());
			newAssetEntry.setClassPK(scienceApp.getScienceAppId());
			newAssetEntry.setTitle(scienceApp.getName());
			AssetEntryLocalServiceUtil.addAssetEntry(newAssetEntry);
			
			List<ScienceAppCategoryLink> appCategoryLinks = ScienceAppCategoryLinkLocalServiceUtil.getScienceAppCategorysByscienceAppId(scienceApp.getScienceAppId());
			for(ScienceAppCategoryLink scienceAppCategoryLink : appCategoryLinks){
				AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(entryId, scienceAppCategoryLink.getCategoryId());
			}
			existAssetEntry = false;
		}
		return existAssetEntry;
		
	}

	/**
	 * 에디슨 프로젝트 에서 사용하는 Method
	 * @param params
	 * @param locale
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public List<Map<String, Object>> getMyAppListForProject(Map params, Locale locale) throws PortalException,
		SystemException{
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		List<ScienceApp> resultList = scienceAppFinder.getMyAppListForProject(params);

		if(resultList != null){
			try{
				for(ScienceApp scienceApp : resultList){
					Map<String, Object> resultRow = new HashMap<String, Object>();

					resultRow.put("scienceAppId", scienceApp.getScienceAppId());
					resultRow.put("groupId", scienceApp.getGroupId());
					resultRow.put("userId", scienceApp.getAuthorId());
					resultRow.put("title", scienceApp.getTitle(locale));
					resultRow.put("version", scienceApp.getVersion());
					resultRow.put("name", scienceApp.getName());
					resultRow.put("projectCategoryId", scienceApp.getProjectCategoryId());

					User appUser = UserLocalServiceUtil.getUser(scienceApp.getAuthorId());
					resultRow.put("userFirstName", appUser.getFirstName());

					long classPK = GetterUtil.getLong(appUser.getExpandoBridge().getAttribute(
						EdisonExpando.USER_UNIVERSITY), 0);
					String affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(classPK, EdisonExpando.CDNM,
						locale);
					resultRow.put("affiliation", affiliation);

					returnList.add(resultRow);
				}
			}catch (Exception e){
				throw new SystemException(e);
			}
		}
		return returnList;
	}
	
	public int getMyAppListForProjectCount(Map params, Locale locale){
		return scienceAppFinder.getMyAppListForProjectCount(params, locale);
	}
	
	// Added by Jerry H. Seo
	// Jul. 16, 2017
	public ScienceApp getScienceApp( String scienceAppName, String scienceAppVersion ) throws NoSuchScienceAppException, SystemException{
		return super.scienceAppPersistence.findByNameVersion(scienceAppName, scienceAppVersion);
	}
}