package org.kisti.edison.content.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kisti.edison.content.model.Content;
import org.kisti.edison.content.model.impl.ContentImpl;
import org.kisti.edison.util.GBatisUtil;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class ContentFinderImpl extends BasePersistenceImpl<Content> implements ContentFinder{

	public int getContentCount(long[] categoryIds, String searchText, long[] contentDiv, String languageId,
		long classNameId, boolean categoryJoin, boolean isTotalSearch){
		Session session = openSession();
		int cnt = 0;
		try{
			String sqlQuerySelect = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getCountContentHeader");
			String sqlQueryFrom = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getContentFromJoin");
			String sqlQueryWhere = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getContentWhere");

			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			sql.append(sqlQueryFrom);
			sql.append(sqlQueryWhere);

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("isAdmin", true);
			params.put("categoryJoin", categoryJoin);
			params.put("classNameId", classNameId);

			if(contentDiv != null){
				params.put("contentDivList", contentDiv);
			}

			if(categoryIds != null && categoryIds.length > 0){
				params.put("categoryIds", categoryIds);
			}

			/*통합검색이면 공개된 콘텐츠만 보임*/
			/*통합검색이면 서비스언어 조건 추가*/
			if(isTotalSearch){ 
				params.put("openYn", "Y");	
				params.put("serviceLanguage", languageId);
			}
			
			params.put("searchText", searchText);
			params.put("languageId", languageId);

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

	public List<Object[]> getContentList(long[] categoryIds, String searchText, long[] contentDiv, int start,
		int end, String languageId, long classNameId, boolean categoryJoin, boolean isTotalSearch){
		Session session = openSession();
		try{
			String sqlQuerySelect = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getListContentHeader");
			String sqlQueryFrom = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getContentFromJoin");
			String sqlQueryWhere = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getContentWhere");

			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			sql.append(sqlQueryFrom);
			sql.append(sqlQueryWhere);

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("isAdmin", true);
			params.put("categoryJoin", categoryJoin);
			params.put("classNameId", classNameId);

			if(contentDiv != null){
				params.put("contentDivList", contentDiv);
			}

			if(categoryIds != null && categoryIds.length > 0){
				params.put("categoryIds", categoryIds);
			}
			/*통합검색이면 공개된 콘텐츠만 보임*/
			/*통합검색이면 서비스언어 조건 추가*/
			if(isTotalSearch){ 
				params.put("openYn", "Y");	
				params.put("serviceLanguage", languageId);
			}
			
			params.put("searchText", searchText);
			params.put("languageId", languageId);
			params.put("begin", start);
			params.put("end", end);

			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addEntity("EDMED_Content", ContentImpl.class);
			query.addScalar("screenName", Type.STRING);
			query.addScalar("viewCnt", Type.LONG);

			return (List<Object[]>) query.list();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}

	public int getContentUserCount(long[] categoryIds, String searchText, String languageId, long classNameId,
		long userId, long roleId){
		Session session = openSession();
		int cnt = 0;
		try{
			String sqlQuerySelect = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getCountContentHeader");
			String sqlQueryFrom = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getContentFromJoin");
			String sqlQueryWhere = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getContentWhere");

			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			sql.append(sqlQueryFrom);
			sql.append(sqlQueryWhere);
			
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("isAdmin", false);

			params.put("roleId", roleId);
			params.put("classNameId", classNameId);

			if(userId != 0){
				params.put("insertId", userId);
			}

			if(categoryIds != null && categoryIds.length > 0){
				params.put("categoryIds", categoryIds);
			}

			params.put("searchText", searchText);
			params.put("languageId", languageId);

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

	public List<Object[]> getContentUserList(long[] categoryIds, String searchText, int start, int end,
		String languageId, long classNameId, long userId, long roleId){
		Session session = openSession();
		try{
			String sqlQuerySelect = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getListContentHeader");
			String sqlQueryFrom = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getContentFromJoin");
			String sqlQueryWhere = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getContentWhere");

			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			sql.append(sqlQueryFrom);
			sql.append(sqlQueryWhere);
			
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("isAdmin", false); //CUSTOM AUTH 테이블 조인.

			params.put("roleId", roleId); //CONTENT_MANAGER ROLE
			params.put("classNameId", classNameId);
			params.put("insertId", userId);
			
			if(categoryIds != null && categoryIds.length > 0){
				params.put("categoryIds", categoryIds);
			}

			params.put("searchText", searchText);
			params.put("languageId", languageId);
			params.put("begin", start);
			params.put("end", end);

			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());

			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addEntity("EDMED_Content", ContentImpl.class);
			query.addScalar("screenName", Type.STRING);

			return (List<Object[]>) query.list();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}

	public List<Object[]> searchTextEntryContentList(Map params){
		Session session = openSession();
		try{
			String sqlQuerySelect = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getAssetEntryModelListHeader");
			String sqlQueryWhere = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getAssetEntryModelWhere");
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			sql.append(sqlQueryWhere);

			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());

			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("contentSeq", Type.LONG);
			query.addScalar("contentDiv", Type.LONG);
			query.addScalar("title", Type.STRING);
			query.addScalar("resume", Type.STRING);
			query.addScalar("version", Type.STRING);

			return (List<Object[]>) query.list();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}

	public Integer searchTextEntryContentCount(Map params){
		Session session = openSession();
		try{
			String sqlQuerySelect = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getCountContentHeader");
			String sqlQueryWhere = CustomSQLUtil.get(
				"org.kisti.edison.content.service.persistence.getAssetEntryModelWhere");
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			sql.append(sqlQueryWhere);

			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());

			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("totalCnt", Type.INTEGER);

			return (Integer) query.uniqueResult();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	public List<Object[]> getContentForProjectList(long userId, String searchText , String projectCategoryId, String languageId, int start, int end){
		Session session=openSession();
		try{
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.content.service.persistence.getListGeneralForProject");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery);
			
			Map params = new HashMap();
			
			if(userId != 0){
				params.put("userId", userId);
			}
			if(!projectCategoryId.equals("0")){
				params.put("projectCategoryId", projectCategoryId);
				
			}
			
			params.put("languageId", languageId);
			params.put("searchText", searchText);
			params.put("begin", start);
			params.put("end",end);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addEntity("EDMED_Content", ContentImpl.class);
			query.addScalar("screenName", Type.STRING); 
			query.addScalar("firstName", Type.STRING); 
			
			return (List<Object[]>) query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	public List<Object[]> getContentDatailList(String projectYn, long columnId,long categoryId, String languageId){
		Session session=openSession();
		try{
			String sqlQuerySelect = CustomSQLUtil.get("org.kisti.edison.content.service.persistence.getContentDetail");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			
			Map param = new HashMap();
			param.put("languageId", languageId);
			param.put("projectYn", projectYn);
			param.put("columnId", columnId);
			
			if(categoryId != 0){
				param.put("categoryId", categoryId);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(param, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("categoryId", Type.LONG); 
			query.addScalar("name", Type.STRING); 
			query.addScalar("key_", Type.STRING); 
			query.addScalar("value", Type.STRING); 
			query.addScalar("title", Type.STRING); 
			query.addScalar("screenName", Type.STRING); 
			query.addScalar("firstName", Type.STRING); 
			query.addScalar("insertId", Type.LONG); 
			query.addScalar("insertDate", Type.STRING); 
			query.addScalar("affiliation", Type.LONG); 
			
			return (List<Object[]>) query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	/*에디슨 프로젝트 통계*/
	public List<Object[]> getContentCenterList(String projectYn, String propertyKey){
		Session session=openSession();
		try{
			String sqlQuerySelect = CustomSQLUtil.get("org.kisti.edison.content.service.persistence.getRegistSwContentHeader");
			String sqlQueryJoin = CustomSQLUtil.get("org.kisti.edison.content.service.persistence.getRegistContentBody");
			String sqlQueryGroupBy = CustomSQLUtil.get("org.kisti.edison.content.service.persistence.getRegistSwContentFrom");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			sql.append(sqlQueryJoin);
			sql.append(sqlQueryGroupBy);
			
			Map param = new HashMap();
			param.put("projectYn", projectYn);
			param.put("propertyKey", propertyKey);
			
			String gBatisQuery = GBatisUtil.getGBatis(param, sql.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("categoryId", Type.LONG); 
			query.addScalar("name", Type.STRING); 
			query.addScalar("key_", Type.STRING); 
			query.addScalar("value", Type.STRING); 
			query.addScalar("CONCNT", Type.INTEGER); 
			
			return (List<Object[]>) query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	public int getGeneralContentForProjectListCount(long userId, String searchText,  String projectCategoryId, String languageId){
		Session session = openSession();
		int cnt = 0;
		try{
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.content.service.persistence.getListGeneralForProjectListCont");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery);
			
			Map params = new HashMap();
			if(userId != 0){
				params.put("userId", userId);
			}

			if(!projectCategoryId.equals("0")){
				params.put("projectCategoryId", projectCategoryId);
				
			}
			params.put("languageId", languageId);
			params.put("searchText", searchText);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("totalCnt", Type.INTEGER);			
			
			cnt = (Integer) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return cnt;
		
	}
}
