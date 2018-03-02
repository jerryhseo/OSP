package kisti.edison.challenge.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;

import kisti.edison.challenge.model.ChallengeEvaluationScore;
import kisti.edison.challenge.service.ChallengeEvaluationLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;
import kisti.edison.challenge.service.persistence.ChallengeEvaluationScoreActionableDynamicQuery;
import kisti.edison.challenge.util.PortletKeys;

public class ChallengeEvaluationScoreIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {ChallengeEvaluationScore.class.getName() };
	
	public static final String PORTLET_ID = PortletKeys.CHALLENGE_EVALUATION;
	
	public ChallengeEvaluationScoreIndexer(){
		setPermissionAware(true);
	}
	
	@Override
	public String[] getClassNames() {
		// TODO Auto-generated method stub
		return CLASS_NAMES;
	}

	@Override
	public String getPortletId() {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChallengeEvaluationScore challengeEvaluationScore = (ChallengeEvaluationScore)obj;
		
		deleteDocument(challengeEvaluationScore.getChallengeEvaluationScoreId(), challengeEvaluationScore.getChallengeTeamId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChallengeEvaluationScore challengeEvaluationScore = (ChallengeEvaluationScore)obj;
		
		Document document = getBaseModelDocument(PORTLET_ID, challengeEvaluationScore);
		document.addDate(Field.MODIFIED_DATE, challengeEvaluationScore.getModifiedDate());
		document.addText(Field.TITLE, String.valueOf(challengeEvaluationScore.getScore()));
		document.addKeyword(Field.GROUP_ID, getSiteGroupId(challengeEvaluationScore.getGroupId()));
		document.addKeyword(Field.SCOPE_GROUP_ID, challengeEvaluationScore.getGroupId());
		
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletURL portletURL) throws Exception {
		// TODO Auto-generated method stub
		Summary summary = createSummary(document);
		
		summary.setMaxContentLength(200);
		
		return summary;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChallengeEvaluationScore challengeEvaluationScore = (ChallengeEvaluationScore)obj;
		Document document = getDocument(challengeEvaluationScore);
		
		SearchEngineUtil.updateDocument(getSearchEngineId(), challengeEvaluationScore.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		long companyId = GetterUtil.getLong(ids[0]);
		
		reindexChallengeEvaluationScores(companyId);
	}
	
	protected void reindexChallengeEvaluationScores(long companyId) throws SystemException, PortalException{
		final Collection<Document> documents = new ArrayList<Document>();
		
		ActionableDynamicQuery actionableDynamicQuery = new ChallengeEvaluationScoreActionableDynamicQuery(){

			@Override
			protected void performAction(Object obj) throws PortalException, SystemException {
				// TODO Auto-generated method stub
				ChallengeEvaluationScore challengeEvaluationScore = (ChallengeEvaluationScore)obj;
				
				Document document = getDocument(challengeEvaluationScore);
				
				documents.add(document);
			}
			
		};
		
		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.performActions();
		
		SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId, documents);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		// TODO Auto-generated method stub
		ChallengeEvaluationScore challengeEvaluationScore = ChallengeEvaluationScoreLocalServiceUtil.getChallengeEvaluationScore(classPK);
		
		doReindex(challengeEvaluationScore);
	}

	@Override
	protected String getPortletId(SearchContext arg0) {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}

}
