package org.kisti.edison.virtuallaboratory.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.util.GBatisUtil;
import org.kisti.edison.virtuallaboratory.model.ClassNote;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class ClassNoteFinderImpl extends BasePersistenceImpl<ClassNote> implements ClassNoteFinder{
	public List<Object[]> getVirtualLabClassNoteList(long classId, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.service.persistence.classNote.getVirtualLabClassNoteList");
			sqlSb.append(sqlQuery);
			String sql = sqlSb.toString();
			session = openSession();
			params.put("languageId", locale.toString());
			params.put("classId", classId);
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("classNoteSeq", Type.LONG);
			query.addScalar("classId", Type.LONG);
			query.addScalar("contentSeq", Type.LONG);
			query.addScalar("isContent", Type.STRING);
			query.addScalar("fileEntryId", Type.LONG);
			query.addScalar("description", Type.STRING);
			query.addScalar("insertDate", Type.DATE);
			query.addScalar("insertId", Type.LONG);
			query.addScalar("resume", Type.STRING);
			
			return (List<Object[]>) query.list();
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}
		return null;
	}
}
