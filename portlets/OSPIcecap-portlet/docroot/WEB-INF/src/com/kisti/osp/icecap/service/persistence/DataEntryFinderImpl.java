package com.kisti.osp.icecap.service.persistence;

import java.util.List;
import java.util.Map;

import org.kisti.edison.util.GBatisUtil;

import com.kisti.osp.icecap.model.DataEntry;
import com.kisti.osp.icecap.model.impl.DataEntryImpl;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class DataEntryFinderImpl extends BasePersistenceImpl<DataEntry> implements DataEntryFinder{

	public static final String GET_DATAENTRY_COUNT_HEADER = DataEntryFinder.class.getName() + ".retrieveCountDataEntry.header";
	public static final String GET_DATAENTRY_LIST_HEADER = DataEntryFinder.class.getName() + ".retrieveListDataEntry.header";
	public static final String GET_DATAENTRY_LIST_WHERE = DataEntryFinder.class.getName() + ".retrieveDataEntry.where";
	
	public List<DataEntry> retrieveListDataEntry(Map<String, Object> searchParam) throws SystemException{
		Session session = null;
		try{
			String sqlHeader = CustomSQLUtil.get(GET_DATAENTRY_LIST_HEADER);
			String sqlWhere = CustomSQLUtil.get(GET_DATAENTRY_LIST_WHERE);

			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append(sqlHeader);
			sqlSb.append(sqlWhere);

			session = openSession();

			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addEntity("icecap_DataEntry", DataEntryImpl.class);

			return (List<DataEntry>) query.list();
		}catch (Exception e){
			throw new SystemException(e);
		}finally{
			closeSession(session);
		}
	}
	
	public int retrieveCountDataEntry(Map<String, Object> searchParam) throws SystemException{
		Session session = null;
		int cnt = 0;
		try{

			String sqlHeader = CustomSQLUtil.get(GET_DATAENTRY_COUNT_HEADER);
			String sqlWhere = CustomSQLUtil.get(GET_DATAENTRY_LIST_WHERE);

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
