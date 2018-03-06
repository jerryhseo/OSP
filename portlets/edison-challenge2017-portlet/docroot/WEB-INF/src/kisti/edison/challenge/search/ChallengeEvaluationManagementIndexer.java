package kisti.edison.challenge.search;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.security.permission.PermissionChecker;

import kisti.edison.challenge.model.ChallengeEvaluationManagement;
import kisti.edison.challenge.service.ChallengeEvaluationManagementLocalServiceUtil;
import kisti.edison.challenge.service.permission.ChallengeEvaluationManagementPermission;
import kisti.edison.challenge.service.persistence.ChallengeTeamActionableDynamicQuery;
import kisti.edison.challenge.util.PortletKeys;

public class ChallengeEvaluationManagementIndexer extends BaseIndexer{

	public static final String[] CLASS_NAME = {ChallengeEvaluationManagement.class.getName()};
	public static final String PORTLET_ID = PortletKeys.CHALLENGE_EVALUATION_MANAGEMENT;
	
	public ChallengeEvaluationManagementIndexer(){
		setPermissionAware(true);
	}
	
	@Override
	public String[] getClassNames() {
		// TODO Auto-generated method stub
		return CLASS_NAME;
	}

	@Override
	public String getPortletId() {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}
	
	@Override
	public boolean hasPermission(PermissionChecker permissionChecker, String entryClassName, 
			long entryClassPK, String actionId) throws Exception {
		// TODO Auto-generated method stub
		return ChallengeEvaluationManagementPermission.contains(permissionChecker, entryClassPK, actionId);
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChallengeEvaluationManagement challengeEvaluationManagement = (ChallengeEvaluationManagement)obj;
		
		deleteDocument(challengeEvaluationManagement.getCompanyId(), challengeEvaluationManagement.getChallengeEvaluationManagementId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChallengeEvaluationManagement challengeEvaluationManagement = (ChallengeEvaluationManagement)obj;
		
		Document document = getBaseModelDocument(PORTLET_ID, challengeEvaluationManagement);
		document.addDate(Field.MODIFIED_DATE, challengeEvaluationManagement.getModifiedDate());
		document.addText(Field.CONTENT, String.valueOf(challengeEvaluationManagement.getDistribution()));
		document.addText(Field.TITLE, challengeEvaluationManagement.getAssessmentItem());
		document.addKeyword(Field.GROUP_ID, getSiteGroupId(challengeEvaluationManagement.getGroupId()));
		document.addKeyword(Field.SCOPE_GROUP_ID, challengeEvaluationManagement.getGroupId());
		
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletURL portletURL) throws Exception {
		// TODO Auto-generated method stub
		Summary summary = createSummary(document);
		summary.setMaxContentLength(200);
		return null;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChallengeEvaluationManagement challengeEvaluationManagement = (ChallengeEvaluationManagement)obj;
		
		Document document = getDocument(challengeEvaluationManagement);
		
		SearchEngineUtil.updateDocument(getSearchEngineId(), challengeEvaluationManagement.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		long companyId = GetterUtil.getLong(ids[0]);
		
		reindexChallengeEvaluations(companyId);
	}

	protected void reindexChallengeEvaluations(long companyId) throws SystemException, PortalException{
		final Collection<Document> documents = new ArrayList<Document>();
		
		ActionableDynamicQuery actionableDynamicQuery = new ChallengeTeamActionableDynamicQuery(){

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
			}
			
			@Override
			protected void performAction(Object obj) throws PortalException, SystemException {
				// TODO Auto-generated method stub
				ChallengeEvaluationManagement challengeEvaluationManagement = (ChallengeEvaluationManagement)obj;
				
				Document document = getDocument(challengeEvaluationManagement);
				
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
		ChallengeEvaluationManagement challengeEvaluationManagement = ChallengeEvaluationManagementLocalServiceUtil.getChallengeEvaluationManagement(classPK);
		
		doReindex(challengeEvaluationManagement);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}

}
