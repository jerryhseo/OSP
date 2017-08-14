package com.kisti.osp.icecap.service.persistence;

import java.util.List;
import java.util.Map;

import org.kisti.edison.util.GBatisUtil;

import com.kisti.osp.icecap.model.DataCollection;
import com.kisti.osp.icecap.model.impl.DataCollectionImpl;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class DataCollectionFinderImpl extends BasePersistenceImpl<DataCollection> implements
	DataCollectionFinder{
	public static final String GET_DATACOLLECTION_COUNT_HEADER = DataCollectionFinder.class.getName()
		+ ".retrieveCountDataCollection.header";
	public static final String GET_DATACOLLECTION_LIST_HEADER = DataCollectionFinder.class.getName()
		+ ".retrieveListDataCollection.header";
	public static final String GET_DATACOLLECTION_WHERE = DataCollectionFinder.class.getName()
		+ ".retrieveDataCollection.where";
	public static final String GET_COUNT_RELATED_ASSET_DATACOLLECTION = DataCollectionFinder.class.getName()
		+ ".retrieveCountRelatedAssetDataCollection.where";

	public List<DataCollection> retrieveListDataCollection(Map<String, Object> searchParam)
		throws SystemException{
		Session session = null;

		try{

			String sqlHeader = CustomSQLUtil.get(GET_DATACOLLECTION_LIST_HEADER);
			String sqlWhere = CustomSQLUtil.get(GET_DATACOLLECTION_WHERE);

			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append(sqlHeader);
			sqlSb.append(sqlWhere);

			session = openSession();

			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addEntity("icecap_DataCollection", DataCollectionImpl.class);

			return (List<DataCollection>) query.list();
		}catch (Exception e){
			throw new SystemException(e);
		}finally{
			closeSession(session);
		}
	}

	public int retrieveCountDataCollection(Map<String, Object> searchParam) throws SystemException{
		Session session = null;
		int cnt = 0;
		try{

			String sqlHeader = CustomSQLUtil.get(GET_DATACOLLECTION_COUNT_HEADER);
			String sqlWhere = CustomSQLUtil.get(GET_DATACOLLECTION_WHERE);

			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append(sqlHeader);
			sqlSb.append(sqlWhere);

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
	
	public int retrieveCountRelatedAssetDataCollection(Map<String, Object> searchParam) throws SystemException{
		Session session = null;
		int cnt = 0;
		try{
			
			String sqlHeader = CustomSQLUtil.get(GET_DATACOLLECTION_COUNT_HEADER);
			String sqlWhere = CustomSQLUtil.get(GET_COUNT_RELATED_ASSET_DATACOLLECTION);
			
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append(sqlHeader);
			sqlSb.append(sqlWhere);
			
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
}
