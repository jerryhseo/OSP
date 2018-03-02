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

import kisti.edison.challenge.model.ChallengeTeamMember;
import kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil;
import kisti.edison.challenge.service.permission.ChallengeTeamMemberPermission;
import kisti.edison.challenge.service.permission.ChallengeTeamPermission;
import kisti.edison.challenge.service.persistence.ChallengeTeamActionableDynamicQuery;
import kisti.edison.challenge.service.persistence.ChallengeTeamMemberActionableDynamicQuery;
import kisti.edison.challenge.util.PortletKeys;

public class ChallengeTeamMemberIndexer extends BaseIndexer{

	public static final String[] CLASS_NAME = {ChallengeTeamMember.class.getName()};
	public static final String PORTLET_ID = PortletKeys.CHALLENGE_TEAM;
	
	public ChallengeTeamMemberIndexer(){
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
		return ChallengeTeamMemberPermission.contains(permissionChecker, entryClassPK, actionId);
	}
	
	@Override
	protected void doDelete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChallengeTeamMember challengeTeamMember = (ChallengeTeamMember)obj;
		
		deleteDocument(challengeTeamMember.getCompanyId(), challengeTeamMember.getChallengeTeamMemberId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChallengeTeamMember challengeTeamMember = (ChallengeTeamMember)obj;
		
		Document document = getBaseModelDocument(PORTLET_ID, challengeTeamMember);
		document.addDate(Field.MODIFIED_DATE, challengeTeamMember.getModifiedDate());
		document.addText(Field.CONTENT, challengeTeamMember.getApplyUserName());
		document.addText(Field.TITLE, challengeTeamMember.getPhone());
		document.addText("email", challengeTeamMember.getEmail());
		document.addKeyword(Field.GROUP_ID, getSiteGroupId(challengeTeamMember.getGroupId()));
		document.addKeyword(Field.SCOPE_GROUP_ID, challengeTeamMember.getGroupId());
		
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, 
			PortletURL portletURL) throws Exception {
		// TODO Auto-generated method stub
		Summary summary = createSummary(document);
		
		summary.setMaxContentLength(200);
		
		return summary;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChallengeTeamMember challengeTeamMember = (ChallengeTeamMember)obj;
		Document document = getDocument(challengeTeamMember);
		
		SearchEngineUtil.updateDocument(getSearchEngineId(), 
				challengeTeamMember.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		long companyId = GetterUtil.getLong(ids[0]);
		
		reindexChallengeTeamMemberes(companyId);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		// TODO Auto-generated method stub
		ChallengeTeamMember challengeTeamMember = 
				ChallengeTeamMemberLocalServiceUtil.getChallengeTeamMember(classPK);
		
		doReindex(challengeTeamMember);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}

	protected void reindexChallengeTeamMemberes(long companyId) throws SystemException, PortalException{
		final Collection<Document> documents = new ArrayList<Document>();
		
		ActionableDynamicQuery actionableDynamicQuery = new ChallengeTeamMemberActionableDynamicQuery(){

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				
			}
			
			@Override
			protected void performAction(Object object) throws PortalException, SystemException {
				// TODO Auto-generated method stub
				ChallengeTeamMember challengeTeamMember = (ChallengeTeamMember)object;
				
				Document document = getDocument(challengeTeamMember);
				
				documents.add(document);
			}
			
		};
		
		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.performActions();
		SearchEngineUtil.updateDocuments(getSearchEngineId(), 
				companyId, documents);
	}
}
