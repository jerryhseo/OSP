package org.kisti.edison.science.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.science.model.ScienceAppFavorite;
import org.kisti.edison.util.GBatisUtil;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class ScienceAppFavoriteFinderImpl extends BasePersistenceImpl<ScienceAppFavorite> implements ScienceAppFavoriteFinder{
	public static final String GET_FAVORITE_APPLIST = ScienceAppFavoriteFinder.class.getName() + ".getFavoriteAppList";
	
	
	
	/* 즐겨찾기 앱 목록 조회 */
	public List<Object[]> getFavoriteAppList(long[] categoryIds, long userId, Locale locale){
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String getFavoriteAppList = CustomSQLUtil.get(GET_FAVORITE_APPLIST);

			sqlSb.append(getFavoriteAppList);
			session = openSession();

			Map<String, Object> params = new HashMap<String, Object>();

			if(locale != null){
				params.put("languageId", locale.toString());
			}
			
			if(categoryIds != null && categoryIds.length > 0){
				params.put("categoryIds", categoryIds);
			}
			
			params.put("userId", userId);
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("scienceAppId", Type.LONG);
			query.addScalar("groupId", Type.LONG);
			query.addScalar("name", Type.STRING);
			query.addScalar("version", Type.STRING);
			query.addScalar("title", Type.STRING);
			query.addScalar("manualId", Type.STRING);
			
			return (List<Object[]>) query.list();
		}catch (Exception e){
			try{
				throw new SystemException(e);
			}catch (SystemException se){
				se.printStackTrace();
			}
		}finally{
			super.closeSession(session);
		}
		return null;
	}
	
}
