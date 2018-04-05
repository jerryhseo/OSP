package org.kisti.edison.science.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.science.NoSuchScienceAppException;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.impl.ScienceAppCategoryLinkImpl;
import org.kisti.edison.science.model.impl.ScienceAppImpl;
import org.kisti.edison.util.GBatisUtil;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class ScienceAppFinderImpl extends BasePersistenceImpl<ScienceApp> implements ScienceAppFinder{
	public static final String GET_APPLIST_HEADER = ScienceAppFinder.class.getName()+ ".retrieveListScienceApp.header";
	public static final String GET_APPLISTASCATEGORY_HEADER = ScienceAppFinder.class.getName()+ ".retrieveListScienceAppAsCategory.header";
	public static final String GET_APPLISTASMANAGER_HEADER = ScienceAppFinder.class.getName()+ ".retrieveListScienceAppAsManager.header";
	public static final String GET_APPLISTASMANAGERCATEGORY_HEADER = ScienceAppFinder.class.getName()+ ".retrieveListScienceAppAsManagerCategoty.header";
	
	public static final String GET_APPCOUNT_HEADER = ScienceAppFinder.class.getName()+ ".countScienceApp.header";
	public static final String GET_APPCOUNTASCATEGORY_HEADER = ScienceAppFinder.class.getName()+ ".countScienceAppAsCategory.header";
	public static final String GET_APPCOUNTASMANAGER_HEADER = ScienceAppFinder.class.getName()+ ".countScienceAppAsManager.header";
	public static final String GET_APPCOUNTASMANAGERCATEGORY_HEADER = ScienceAppFinder.class.getName()+ ".countScienceAppAsManagerCategoty.header";
	
	public static final String GET_APPLIST_WHERE = ScienceAppFinder.class.getName() + ".ScienceApp.where";

	public static final String GET_TARGET_LANGUAGE_SCIENCE_APP_CATEGORY = ScienceAppFinder.class.getName()
		+ ".getTargetLanguageScienceAppCategory";

	public static final String GET_APP_TEST_LIST = ScienceAppFinder.class.getName() + ".retrieveListAppTest";
	public static final String GET_APP_TEST_COUNT = ScienceAppFinder.class.getName() + ".countAppTest";

	public static final String GET_API_APP_LIST = ScienceAppFinder.class.getName() + ".retrieveApiAppList";
	public static final String GET_API_APP_CATEGORIES = ScienceAppFinder.class.getName()
		+ ".retrieveApiAppCategories";

	public static final String GET_ASSET_APP_LIST_HEADER = ScienceAppFinder.class.getName()
		+ ".getAssetEntryModelListHeader";

	public static final String GET_ASSET_APP_LIST_COUNT_HEADER = ScienceAppFinder.class.getName()
		+ ".getAssetEntryModelCountHeader";

	public static final String GET_ASSET_APP_LIST_WHERE = ScienceAppFinder.class.getName()
		+ ".getAssetEntryModelWhere";
	
	public static final String GET_MY_APP_LIST_WITH_QNA = ScienceAppFinder.class.getName()
		+ ".getMyAppListWithQna";
	public static final String GET_MY_APP_LIST = ScienceAppFinder.class.getName() + ".getListMyAppQna";
	
	/*에디슨 프로젝트 Custom Query*/
	public static final String GET_MY_APP_LIST_FOR_PROJECT = ScienceAppFinder.class.getName()
		+ ".getMyAppListForProject";
	public static final String GET_MY_APP_LIST_FOR_PROJECT_COUNT = ScienceAppFinder.class.getName()
		+ ".getMyAppListForProjectCount";
	
	//ADD
	public static final String GET_MY_MANAGER_APP_COUNT_HEADER =ScienceAppFinder.class.getName() + ".countMyManagerScienceApp.header";
	public static final String GET_APP_COUNT_HEADER = ScienceAppFinder.class.getName() + ".countScienceApp.header";
	public static final String GET_MY_MANAGER_APPLIST_HEADER = ScienceAppFinder.class.getName() + ".retrieveListMyManagerScienceApp.header";
	public static final String GET_APPLIST_HEADER_OLD = ScienceAppFinder.class.getName() + ".retrieveListScienceApp.header.old";
	public static final String GET_APPLIST_WHERE_OLD = ScienceAppFinder.class.getName() + ".ScienceApp.where.old";
	
	public int countScienceApp(Map<String, Object> searchParam,boolean categorySearch,boolean managerSearch) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		int cnt = 0;

		try{
			String sqlQueryHeader = "";
			if(categorySearch){
				if(managerSearch){
					sqlQueryHeader = CustomSQLUtil.get(GET_APPCOUNTASMANAGERCATEGORY_HEADER);
				}else{
					sqlQueryHeader = CustomSQLUtil.get(GET_APPCOUNTASCATEGORY_HEADER);
				}
			}else{
				if(managerSearch){
					sqlQueryHeader = CustomSQLUtil.get(GET_APPCOUNTASMANAGER_HEADER);
				}else{
					sqlQueryHeader = CustomSQLUtil.get(GET_APPCOUNT_HEADER);
				}
			}
			
			String sqlQuery = CustomSQLUtil.get(GET_APPLIST_WHERE);
			sqlSb.append(sqlQueryHeader);
			sqlSb.append(sqlQuery);

			session = openSession();
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("totalCnt", Type.INTEGER);

			cnt = (Integer) query.uniqueResult();
		}catch (Exception e){
			throw new SystemException(e);
		}finally{
			closeSession(session);
		}

		return cnt;
	}

	public List<Object[]> retrieveListScienceApp(Map<String, Object> searchParam,boolean categorySearch,boolean managerSearch) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		// DB Cache Clear
		CacheRegistryUtil.clear();
		try{
			String sqlQueryHeader = "";
			
			if(categorySearch){
				if(managerSearch){
					sqlQueryHeader = CustomSQLUtil.get(GET_APPLISTASMANAGERCATEGORY_HEADER);
				}else{
					sqlQueryHeader = CustomSQLUtil.get(GET_APPLISTASCATEGORY_HEADER);
				}
			}else{
				if(managerSearch){
					sqlQueryHeader = CustomSQLUtil.get(GET_APPLISTASMANAGER_HEADER);
				}else{
					sqlQueryHeader = CustomSQLUtil.get(GET_APPLIST_HEADER);
				}
			}
			
			if(!searchParam.containsKey("defaultSortOrder") && !searchParam.containsKey("sortField")){
			    searchParam.put("defaultSortOrder", "true");
			}
			
			String sqlQuery = CustomSQLUtil.get(GET_APPLIST_WHERE);
			sqlSb.append(sqlQueryHeader);
			sqlSb.append(sqlQuery);

			session = openSession();
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addEntity("EDAPP_ScienceApp", ScienceAppImpl.class);
			query.addScalar("firstName", Type.STRING);
			query.addScalar("screenName", Type.STRING);
			query.addScalar("userOrgCode", Type.LONG);
			return (List<Object[]>) query.list();

		}catch (Exception e){
			e.printStackTrace();
			throw new SystemException(e);
		}finally{
			closeSession(session);
		}
	}
	
	@SuppressWarnings("unchecked")
  public long[] getScienceAppIdsByCategoryId(long categoryId){
    Session session = null;
    try{
      String sql = CustomSQLUtil.get("org.kisti.edison.science.service.persistence.ScienceAppFinder.getScienceAppIdsByCategoryId");
      session = openSession();
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("categoryId", categoryId);
      String gBatisQuery = GBatisUtil.getGBatis(params, sql);
      SQLQuery query = session.createSQLQuery(gBatisQuery);
      query.addScalar("scienceAppId", Type.LONG);

      List<Long> rows = (List<Long>) query.list();
      long[] result = new long[rows.size()];
      for(int i=0; i<rows.size(); i++){
        result[i] = rows.get(i); 
      }
      return result;
    }catch (Exception e){
      try{
        throw new SystemException(e);
      }catch (SystemException se){
        se.printStackTrace();
      }
    }finally{
      closeSession(session);
    }
    return null;
  }

	public Map<Long, Long> getLanguageSpecificCategories(Map<String, Object> params){
		Session session = null;
		try{
			String sql = CustomSQLUtil.get(GET_TARGET_LANGUAGE_SCIENCE_APP_CATEGORY);
			session = openSession();
			String gBatisQuery = GBatisUtil.getGBatis(params, sql);
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("categoryId", Type.LONG);
			query.addScalar("appCnt", Type.LONG);

			List<Object[]> rows = (List<Object[]>) query.list();
			Map<Long, Long> categories = new HashMap<Long, Long>();
			for(Object[] columns : rows){
				categories.put(GetterUtil.getLong(columns[0]), GetterUtil.getLong(columns[1]));
			}
			return categories;
		}catch (Exception e){
			try{
				throw new SystemException(e);
			}catch (SystemException se){
				se.printStackTrace();
			}
		}finally{
			closeSession(session);
		}
		return null;
	}

	public List<Object[]> retrieveListAppTest(Map<String, Object> params){
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;

		try{
			String getMyAppQnaList = CustomSQLUtil.get(GET_APP_TEST_LIST);

			sqlSb.append(getMyAppQnaList);
			session = openSession();
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("simulationCreateDt", Type.STRING);
			query.addScalar("TOTAL_CNT", Type.INTEGER);
			query.addScalar("QUEUED_CNT", Type.INTEGER);
			query.addScalar("RUN_CNT", Type.INTEGER);
			query.addScalar("SUCCESS_CNT", Type.INTEGER);
			query.addScalar("FAILED_CNT", Type.INTEGER);
			query.addScalar("simulationUuid", Type.STRING);
			query.addScalar("jobUuid", Type.STRING);

			return (List<Object[]>) query.list();
		}catch (Exception e){
			try{
				throw new SystemException(e);
			}catch (SystemException se){
				se.printStackTrace();
			}
		}finally{
			closeSession(session);
		}
		return null;
	}

	public int countAppTest(Map<String, Object> params){
		Session session = openSession();
		int cnt = 0;
		try{
			String sqlQuery = CustomSQLUtil.get(GET_APP_TEST_COUNT);

			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery);

			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("totalCnt", Type.INTEGER);

			cnt = (Integer) query.uniqueResult();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return cnt;
	}

	public List<Object[]> retrieveApiAppList(Map<String, Object> params) throws NoSuchScienceAppException,
		PortalException, SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;

		// DB Cache Clear
		CacheRegistryUtil.clear();
		try{
			String sqlQuerySelect = "";
			sqlQuerySelect = CustomSQLUtil.get(GET_API_APP_LIST);
			sqlSb.append(sqlQuerySelect);
			session = openSession();

			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addEntity("EDAPP_ScienceApp", ScienceAppImpl.class);
			query.addScalar("data_", Type.STRING);

			return (List<Object[]>) query.list();
		}catch (Exception e){
			e.printStackTrace();
			throw new SystemException(e);
		}finally{
			closeSession(session);
		}
	}

	public List<Object[]> retrieveApiAppCategories(Map<String, Object> params) throws NoSuchScienceAppException,
		PortalException, SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;

		// DB Cache Clear
		CacheRegistryUtil.clear();
		try{
			String sqlQuerySelect = "";
			sqlQuerySelect = CustomSQLUtil.get(GET_API_APP_CATEGORIES);
			sqlSb.append(sqlQuerySelect);
			session = openSession();

			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("categoryTitle", Type.STRING);
			query.addScalar("parentCategoryTitle", Type.STRING);

			return (List<Object[]>) query.list();
		}catch (Exception e){
			e.printStackTrace();
			throw new SystemException(e);
		}finally{
			closeSession(session);
		}
	}
	
	/* 나의 앱 리스트 and QNA 개수 */
	public List<Object[]> getMyAppListWithQna(Map<String, Object> params){
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;

		try{
			String getMyAppQnaList = CustomSQLUtil.get(GET_MY_APP_LIST_WITH_QNA);

			sqlSb.append(getMyAppQnaList);

			session = openSession();

			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());

			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addEntity("EDAPP_ScienceApp", ScienceAppImpl.class);
			query.addScalar("answerCount", Type.INTEGER);

			return (List<Object[]>) query.list();
		}catch (Exception e){
			try{
				throw new SystemException(e);
			}catch (SystemException se){
				se.printStackTrace();
			}
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	/* 나의 앱 QNA 리스트 */
	public List<Object[]> getListMyAppQna(Map<String, Object> params){
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;

		try{
			String getMyAppQnaList = CustomSQLUtil.get(GET_MY_APP_LIST);

			sqlSb.append(getMyAppQnaList);

			session = openSession();

			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());

			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("boardSeq", Type.LONG);
			query.addScalar("title", Type.STRING);
			query.addScalar("content", Type.STRING);
			query.addScalar("writerId", Type.LONG);
			query.addScalar("writerDate", Type.DATE);
			query.addScalar("readCnt", Type.INTEGER);
			query.addScalar("replyCount", Type.INTEGER);
			query.addScalar("groupId", Type.LONG);

			return (List<Object[]>) query.list();
		}catch (Exception e){
			try{
				throw new SystemException(e);
			}catch (SystemException se){
				se.printStackTrace();
			}
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	// ASSET 포틀릿 조회 메서드
	public List<Object[]> searchTextEntryScienceAPPList(Map params){
		Session session = openSession();
		try{
			String sqlQuerySelect = CustomSQLUtil.get(GET_ASSET_APP_LIST_HEADER);
			String sqlQueryWhere = CustomSQLUtil.get(GET_ASSET_APP_LIST_WHERE);
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			sql.append(sqlQueryWhere);

			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("scienceAppId", Type.LONG);
			query.addScalar("name", Type.STRING);
			query.addScalar("title", Type.STRING);
			query.addScalar("appType", Type.STRING);
			query.addScalar("status", Type.LONG);
			query.addScalar("version", Type.STRING);
			query.addScalar("groupId", Type.LONG);

			return (List<Object[]>) query.list();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}

	public int searchTextEntryScienceAPPCount(Map params){

		Session session = openSession();
		int cnt = 0;
		try{
			String sqlQuerySelect = CustomSQLUtil.get(GET_ASSET_APP_LIST_COUNT_HEADER);
			String sqlQueryWhere = CustomSQLUtil.get(GET_ASSET_APP_LIST_WHERE);
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			sql.append(sqlQueryWhere);

			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("totalCnt", Type.INTEGER);

			cnt = (Integer) query.uniqueResult();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return cnt;
	}
	
	/* 에디슨 프로젝트 앱 목록 */
	public List<ScienceApp> getMyAppListForProject(Map<String, Object> params){
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;

		try{
			String getMyAppForList = CustomSQLUtil.get(GET_MY_APP_LIST_FOR_PROJECT);

			sqlSb.append(getMyAppForList);

			session = openSession();

			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());

			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addEntity("EDAPP_ScienceApp", ScienceAppImpl.class);

			return (List<ScienceApp>) query.list();
		}catch (Exception e){
			try{
				throw new SystemException(e);
			}catch (SystemException se){
				se.printStackTrace();
			}
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	public int getMyAppListForProjectCount(Map params, Locale locale){
		Session session = openSession();
		int cnt = 0;
		try{
			String sqlQuery = CustomSQLUtil.get(GET_MY_APP_LIST_FOR_PROJECT_COUNT);

			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery);

			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("totalCnt", Type.INTEGER);

			cnt = (Integer) query.uniqueResult();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return cnt;
	}
	
	// ADD ScienceAppList source by imJeong at 2018.03.06
	public int countScienceApp(Map<String,Object> searchParam) throws SystemException{
	    StringBuilder sqlSb = new StringBuilder();
	    Session session = null;
	    int cnt = 0;

	    try{
	      String sqlQuerySelect = "";
	      //MyManagerApp 조회 일 경우
	      boolean myAppSearchStatus = GetterUtil.getBoolean(searchParam.get("myManagerAppSearch"),false);

	      if(myAppSearchStatus){
	        sqlQuerySelect = CustomSQLUtil.get(GET_MY_MANAGER_APP_COUNT_HEADER );
	      }else{
	        sqlQuerySelect = CustomSQLUtil.get(GET_APP_COUNT_HEADER);
	      }

	      String sqlQuery = CustomSQLUtil.get(GET_APPLIST_WHERE);
	      sqlSb.append(sqlQuerySelect);
	      sqlSb.append(sqlQuery);

	      session = openSession();
	      String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
	      SQLQuery query = session.createSQLQuery(gBatisQuery);
	      query.addScalar("totalCnt", Type.INTEGER);

	      cnt = (Integer) query.uniqueResult();
	    }catch (Exception e) {
	      throw new SystemException(e);
	    } finally {
	      closeSession(session);
	    }

	    return cnt;
	  }
	
	public List<Object[]> retrieveListScienceApp(Map<String,Object> searchParam) throws SystemException{
	    StringBuilder sqlSb = new StringBuilder();
	    Session session = null;

	    //DB Cache Clear
	    CacheRegistryUtil.clear();
	    try{
	      searchParam.put("listsearch", true);

	      String sqlQuerySelect = "";
	      //MyManagerApp 조회 일 경우
	      boolean myAppSearchStatus = GetterUtil.getBoolean(searchParam.get("myManagerAppSearch"),false);

	      if(myAppSearchStatus){
	        sqlQuerySelect = CustomSQLUtil.get(GET_MY_MANAGER_APPLIST_HEADER);
	      }else{
	        sqlQuerySelect = CustomSQLUtil.get(GET_APPLIST_HEADER_OLD);
	      }

	      String sqlQuery = CustomSQLUtil.get(GET_APPLIST_WHERE_OLD);
	      sqlSb.append(sqlQuerySelect);
	      sqlSb.append(sqlQuery);

	      session = openSession();
	      String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
	      SQLQuery query = session.createSQLQuery(gBatisQuery);
	      query.addEntity("EDAPP_ScienceAppCategoryLink", ScienceAppCategoryLinkImpl.class);
	      query.addEntity("EDAPP_ScienceApp", ScienceAppImpl.class);
	      query.addScalar("appModifiedDate", Type.DATE);

	      return (List<Object[]>) query.list();

	    }catch (Exception e) {
	      e.printStackTrace();
	      throw new SystemException(e);
	    } finally {
	      closeSession(session);
	    }
	  }
}
