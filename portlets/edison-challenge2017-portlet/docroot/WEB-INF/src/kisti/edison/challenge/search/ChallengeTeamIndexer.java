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

import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.permission.ChallengeTeamPermission;
import kisti.edison.challenge.service.persistence.ChallengeTeamActionableDynamicQuery;
import kisti.edison.challenge.util.PortletKeys;

public class ChallengeTeamIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {ChallengeTeam.class.getName()};
	public static final String PORTLET_ID = PortletKeys.CHALLENGE_TEAM;
	
	
	public ChallengeTeamIndexer(){
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
	public boolean hasPermission(PermissionChecker permissionChecker, String entryClassName, 
			long entryClassPK, String actionId) throws Exception {
		// TODO Auto-generated method stub
		return ChallengeTeamPermission.contains(permissionChecker, entryClassPK, actionId);
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChallengeTeam challengeTeam = (ChallengeTeam)obj;
		
		deleteDocument(challengeTeam.getCompanyId(), challengeTeam.getChallengeTeamId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		// TODO Auto-generated method stub.
		ChallengeTeam challengeTeam = (ChallengeTeam)obj;
		
		Document document = getBaseModelDocument(PORTLET_ID, challengeTeam);
		document.addDate(Field.MODIFIED_DATE, challengeTeam.getModifiedDate());
		document.addText(Field.CONTENT, challengeTeam.getPaperAbstract());
		document.addText(Field.TITLE, challengeTeam.getTeamName());
		document.addKeyword(Field.GROUP_ID, getSiteGroupId(challengeTeam.getGroupId()));
		document.addKeyword(Field.SCOPE_GROUP_ID, challengeTeam.getGroupId());
		
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
		ChallengeTeam challengeTeam = (ChallengeTeam)obj;
		
		Document document = getDocument(challengeTeam);
		
		SearchEngineUtil.updateDocument(getSearchEngineId(), 
				challengeTeam.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		long companyId = GetterUtil.getLong(ids[0]);
	
		reindexChallengeTeames(companyId);
	}
	
	protected void reindexChallengeTeames(long companyId) throws SystemException, PortalException{
		final Collection<Document> documents = new ArrayList<Document>();
		
		ActionableDynamicQuery actionableDynamicQuery = new ChallengeTeamActionableDynamicQuery(){

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
			}
			
			@Override
			protected void performAction(Object obj) throws PortalException, SystemException {
				// TODO Auto-generated method stub
				ChallengeTeam challengeTeam = (ChallengeTeam)obj;
				
				Document document = getDocument(challengeTeam);
				
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
		ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(classPK);
		
		doReindex(challengeTeam);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}

}
