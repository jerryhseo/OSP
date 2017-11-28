package org.kisti.edison.portal.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonPropskeys;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.ExcelFileFilter;
import org.kisti.edison.util.ExcelUtil;

import com.liferay.counter.model.Counter;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.NoSuchUserGroupException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.simple.Element;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class EdisonStartupAction extends SimpleAction{
  private static Log _log = LogFactoryUtil.getLog(EdisonStartupAction.class);

  /*
   * (non-Java-doc)
   * 
   * @see com.liferay.portal.kernel.events.SimpleAction#SimpleAction()
   */
  public EdisonStartupAction(){
    super();
  }
  
  
  /*
   * (non-Java-doc)
   * 
   * @see com.liferay.portal.kernel.events.SimpleAction#run(String[] arg0)
   */
  public void run(String[] arg0) throws ActionException{
    try{
		initSysCommonCd();
		initPreprences();
		initUserCreateCustomField();
		initSiteCreateCustomField();
		initPageCreateCustomField();
		initUserCreateCustomField();
		initUserGroupsCreateCustomField();
		initCategory();
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  
  
  protected void initCategory() throws SystemException, PortalException, UnsupportedEncodingException{// user group create
	  long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
	  long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);
	  //Global Domain Create
	  //Vocabluary Create

	  Date date = new Date();
	  Locale[] langArray = LanguageUtil.getAvailableLocales();

	  Group globalGroup = GroupLocalServiceUtil.getCompanyGroup(companyId);
	  
	  List<Map<String, String>> vocabularyList = new ArrayList<Map<String, String>>();
	  Map<String, String> globalVoca = new HashMap<String, String>() {{
    	  put("name", "Global Domain");
    	  put("attr", "multiValued=true selectedClassNameIds=0");
      }};
      Map<String, String> projectVoca = new HashMap<String, String>() {{
    	  put("name", "Project Domain");
    	  put("attr", "multiValued=false selectedClassNameIds=0");
      }};
      vocabularyList.add(globalVoca);
      vocabularyList.add(projectVoca);
      
      
      String[] categoryKoArray = {"전산열유체", "나노물리", "계산화학", "구조동역학", "전산설계"};
	  String[] categoryEnArray = {"Computational Fiuid Dynamics", "Nano Physics", "Computational Chemistry", "Computational Structural Dynamics", "Computer Aided Optimal Design"};
	  
	  //List<String> categoryList = new ArrayList<>(Arrays.asList("xyz", "abc"));

	  Map<String, String[]> subCategoryKoMap = new HashMap<String, String[]>() {{
            put("전산열유체", new String[]{"유동 범용 해석", "최적 설계 및 설계 프레임워크", "일반물리 가상실험", "Stokes 유동 해석", "열전달 해석", "난류 모사", "공력 Data Base", "전처리", "Case 별 유동해석", "다상 유동 해석", "프로펠러 성능 해석 패키지", "선박 저항 해석 패키지"});
            put("나노물리", new String[]{"고전 반도체소자", "신개념 나노소자", "원자수준", "분자수준", "고전물리","양자물리"});
            put("계산화학", new String[]{"몬테 카를로", "양자동력학계산 및 양자제어", "단백질-생체분자 도킹", "양자화학", "분자동역학", "양자역학 계산", "생체시스템에 대한 분자모델링 개발 및 활용"});
            put("구조동역학", new String[]{"대용량 대변형 구조 해석", "대변위 연속체의 구조 해석", "초탄성 및 점탄성 구조물의 거동 해석", "콘크리트 구조물의 정적/동적 해석", "복합재료/회전익 탄성보 구조 해석"});
            put("전산설계", new String[]{"오픈 소스 기반 CAD시스템", "기계부품설계 SW 샘플", "최적화", "최적설계", "전산설계"});
      }};
      Map<String, String[]> subCategoryEnMap = new HashMap<String, String[]>() {{
    	  put("전산열유체", new String[]{"General Flow Analysis", "Optimal Design and Design Framework", "Virtual Test in General Physics", "Stokes Flow Simulation", "Thermal Analysis", "Turbulent Flow Simulation", "Aerodynamic Database", "Pre-processor", "Case Study", "Multiphase Flow Analysis", "Propeller Performance Simulation Package", "Ship Resistance Simulation Package"});
    	  put("나노물리", new String[]{"Classical Semiconductive Device", "Advanced Nano-Device", "Atom Level", "Molecular Level", "classical Physics", "Quantum Physics"});
    	  put("계산화학", new String[]{"Monte Carlo", "Quantum dynamics calculation and quantum control", "Protein-biomolecule docking", "Quantum chemistry", "Molecular dynamics",  "Quantum mechanics calculation", "Molecular dynamics on biosystems"});
    	  put("구조동역학", new String[]{"Large-scale Large- deformation Analysis", "Large-displacement Structural Analysis", "Hyperelastic &amp; Viscoelastic Analysis", "Static/Dynamic Analysis of Concrete Structure", "Composite/Airfoil Beam Analysis"});
    	  put("전산설계", new String[]{"Open-source CAD System", "Mechanical Component Samples" ,"Optimization" ,"Optimal Design" ,"Computer Aided Design"});
      }};
      

      for(Map<String, String> vocaMap : vocabularyList){
		  AssetVocabulary vocabulary =  null;
		  try {
			  vocabulary =  AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroup.getGroupId(), vocaMap.get("name"));
		  }catch (Exception e) {
			  long vocabularyId = CounterLocalServiceUtil.increment(Counter.class.getName());
			  vocabulary = AssetVocabularyLocalServiceUtil.createAssetVocabulary(vocabularyId);
			  
			  vocabulary.setGroupId(globalGroup.getGroupId());
			  vocabulary.setCompanyId(companyId);
			  vocabulary.setUserId(defaultUserId);
			  
			  vocabulary.setCreateDate(date);
			  vocabulary.setModifiedDate(date);
			  vocabulary.setName(vocaMap.get("name"));
			  
			  Map<Locale, String> titleMap = new HashMap<Locale, String>();
			  
			  for(Locale lang : langArray){
				  titleMap.put(lang, vocaMap.get("name"));
			  }
			  vocabulary.setTitleMap(titleMap);
			  vocabulary.setSettings(vocaMap.get("attr"));
			  
			  vocabulary = AssetVocabularyLocalServiceUtil.addAssetVocabulary(vocabulary);
			  
		  }
		  
		  //Category Create
		  if(vocabulary != null){
	
			  for(int i=0; i<categoryKoArray.length; i++){
				  ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();
				  DynamicQuery query2 = DynamicQueryFactoryUtil.forClass(AssetCategory.class,classLoader)
											  .add(PropertyFactoryUtil.forName("name").eq(categoryKoArray[i]))
											  .add(PropertyFactoryUtil.forName("vocabularyId").eq(vocabulary.getVocabularyId()));
	
				  List assetCategories = AssetCategoryLocalServiceUtil.dynamicQuery(query2);
				  AssetCategory category = null;
				  if(assetCategories == null || assetCategories.size() == 0){
						
						  long categoryId = CounterLocalServiceUtil.increment(Counter.class.getName());
						  //long categoryId = CounterLocalServiceUtil.increment(AssetCategory.class.getName());
						  category = AssetCategoryLocalServiceUtil.createAssetCategory(categoryId);
						  category.setGroupId(globalGroup.getGroupId());
						  category.setCompanyId(companyId);
						  category.setUserId(defaultUserId);
				
						  category.setCreateDate(date);
						  category.setModifiedDate(date);
						  category.setParentCategoryId(0);
						  category.setName(categoryKoArray[i]);
						  
						  Map<Locale, String> categoryTitle = new HashMap<Locale, String>();
						  for(Locale lang : langArray){
							  if(lang.toString().equals("ko_KR")){ categoryTitle.put(lang, categoryKoArray[i]); }
							  else if(lang.toString().equals("en_US")){ categoryTitle.put(lang, categoryEnArray[i]); }
						  }
						  
						  category.setTitleMap(categoryTitle);
						  category.setVocabularyId(vocabulary.getVocabularyId());
						  category = AssetCategoryLocalServiceUtil.addAssetCategory(category);
				  }else if(assetCategories != null || assetCategories.size() == 1){
					  category = (AssetCategory) assetCategories.get(0);
				  }
				  
				  if(vocabulary.getName().equals("Global Domain")){
					  //sub category create
					  
					  String[] subCategorykoArray = subCategoryKoMap.get(categoryKoArray[i]);
					  String[] subCategoryEnArray = subCategoryEnMap.get(categoryKoArray[i]); //한글명으로 조회
					  if(subCategorykoArray != null){
						  for(int j=0; j<subCategorykoArray.length; j++){
							  AssetCategory subCategory = null;
							  DynamicQuery subCategoryQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class,classLoader)
														  .add(PropertyFactoryUtil.forName("name").eq(subCategorykoArray[j]))
														  .add(PropertyFactoryUtil.forName("parentCategoryId").eq(category.getCategoryId()))
														  .add(PropertyFactoryUtil.forName("vocabularyId").eq(vocabulary.getVocabularyId()));
		
							  List subCategoryList = AssetCategoryLocalServiceUtil.dynamicQuery(subCategoryQuery);
							  
							  if(subCategoryList == null || subCategoryList.size() == 0){
								  long subCategoryId = CounterLocalServiceUtil.increment(Counter.class.getName());
								  subCategory = AssetCategoryLocalServiceUtil.createAssetCategory(subCategoryId);
								  subCategory.setGroupId(globalGroup.getGroupId());
								  subCategory.setCompanyId(companyId);
								  subCategory.setUserId(defaultUserId);
						
								  subCategory.setCreateDate(date);
								  subCategory.setModifiedDate(date);
								  subCategory.setParentCategoryId(category.getCategoryId());
								  subCategory.setName(subCategorykoArray[j]);
					
								  Map<Locale, String> categoryTitle = new HashMap<Locale, String>();
								  for(Locale lang : langArray){
									  if(lang.toString().equals("ko_KR")){ categoryTitle.put(lang, subCategorykoArray[j]); }
									  else if(lang.toString().equals("en_US")){ categoryTitle.put(lang, subCategoryEnArray[j]); }
								  }
								  
								  subCategory.setTitleMap(categoryTitle);
								  subCategory.setVocabularyId(vocabulary.getVocabularyId());
								  subCategory = AssetCategoryLocalServiceUtil.addAssetCategory(subCategory);
						  	}
						 }
					  } 
				  }
			  }
		  }
	  }
	  
	  //Entry_Category
	  
  }
  protected void initUserGroupsCreateCustomField() throws SystemException, PortalException{// user group create
	  //UserGroup에 추가, Group_에도 추가해줘야 목록에 나옴
	  
	  long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();

	  String[] userGroupArray = new String[] { 
			  EdisonRoleConstants.TUTOR_GROUP, 
			  EdisonRoleConstants.STUDENT_GROUP, 
			  EdisonRoleConstants.DEVELOPER_GROUP, 
			  EdisonRoleConstants.PROJECT_GROUP 
	  };

	  //Role에 등록
	  String[] roleArray = new String[] { 
			  EdisonRoleConstants.TUTOR, 
			  EdisonRoleConstants.STUDENT, 
			  EdisonRoleConstants.DEVELOPER, 
			  EdisonRoleConstants.PROJECT 
	  };
	  
	  long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);
	  for(int i = 0; i < userGroupArray.length; i++){
		  UserGroup userGroup = null;
		  
		  try{
			  userGroup = UserGroupLocalServiceUtil.getUserGroup(companyId, userGroupArray[i]);
		  }catch(NoSuchUserGroupException nsuge){
			  long userGroupId = CounterLocalServiceUtil.increment(Counter.class.getName());
			  userGroup = UserGroupLocalServiceUtil.createUserGroup(userGroupId);

			  userGroup.setCompanyId(companyId);
			  userGroup.setUserId(defaultUserId);
			  userGroup.setName(userGroupArray[i]);
			  userGroup = UserGroupLocalServiceUtil.addUserGroup(userGroup);
			  
			  Date date = new Date();
			  userGroup.setCreateDate(date);
			  userGroup.setModifiedDate(date);
		  }
		  
		  Group newGroup = null;
		  if(userGroup != null){
			  try{
				  newGroup = GroupLocalServiceUtil.getGroup(companyId, String.valueOf(userGroup.getUserGroupId()));
			  }catch(NoSuchGroupException nsge){
				  long newGroupId = CounterLocalServiceUtil.increment(Counter.class.getName());
				  newGroup = GroupLocalServiceUtil.createGroup(newGroupId);
				  
				  newGroup.setGroupId(newGroupId);
				  newGroup.setCompanyId(companyId);
				  newGroup.setCreatorUserId(defaultUserId);
				  newGroup.setClassNameId(ClassNameLocalServiceUtil.getClassNameId(UserGroup.class.getName()));
				  newGroup.setClassPK(userGroup.getUserGroupId());
				  newGroup.setTreePath("/"+newGroupId+"/");
				  newGroup.setName(String.valueOf(userGroup.getUserGroupId()));
				  newGroup.setType(0);
				  newGroup.setManualMembership(true);
				  newGroup.setFriendlyURL("/"+userGroup.getUserGroupId());
				  newGroup.setActive(true);
				  GroupLocalServiceUtil.addGroup(newGroup);
			  }
		  }
		  Role role = null;
		  try{
			  role = RoleLocalServiceUtil.getRole(companyId, roleArray[i]);
		  }catch(NoSuchRoleException nsre){
			  long newRoleId = CounterLocalServiceUtil.increment(Role.class.getName());
			  role = RoleLocalServiceUtil.createRole(newRoleId);

			  role.setCompanyId(companyId);
			  role.setUserId(defaultUserId);
			  Date date = new Date();
			  role.setCreateDate(date);
			  role.setModifiedDate(date);
			  role.setClassNameId(ClassNameLocalServiceUtil.getClassNameId(Role.class.getName()));
			  role.setName(roleArray[i]);
			  
			  Map<Locale, String> map = new HashMap<Locale, String>();
			  Locale[] availLocale = LanguageUtil.getAvailableLocales();
			  for(int lo=0 ; lo<availLocale.length; lo++){
				  map.put(availLocale[lo], userGroupArray[i]);
			  }
			  role.setDescriptionMap(map);
			  role.setType(1);
			  
			  RoleLocalServiceUtil.addRole(role);			  
		  }
		  GroupLocalServiceUtil.addRoleGroup(role.getRoleId(), newGroup.getGroupId());
		  //UserGroupGroupRoleLocalServiceUtil.addUserGroupGroupRoles(userGroup.getUserGroupId(), newGroup.getGroupId(), role.getRoleId());
	  }
	  
  }
  
  protected void initUserCreateCustomField() throws SystemException, PortalException{// user customfields create
	  ExpandoTable table = null;
	  long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();

	  try{
		  table = ExpandoTableLocalServiceUtil.addTable(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	  }catch (DuplicateTableNameException dtne){
		  table = ExpandoTableLocalServiceUtil.getTable(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	  }

	  List<ExpandoColumn> columnList = new ArrayList<ExpandoColumn>();
	  ExpandoColumn uiversityField = null;
	  ExpandoColumn virtualLabId = null;
	  ExpandoColumn classId = null;
	  ExpandoColumn majorField = null;
	  ExpandoColumn visitSite = null;
	  ExpandoColumn edisonTermsOfUseDate = null;
	  ExpandoColumn projectCategoryId = null;
	  
	  //UniversityField
	  try{
		  uiversityField = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY , ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  uiversityField = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY);
	  }
	  
	  //virtualLabId
	  try{
		  virtualLabId = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.USER_LAB_ID , ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  virtualLabId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_LAB_ID);
	  }
	  
	  //classId
	  try{
		  classId = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.USER_CLASS_ID , ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  classId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_CLASS_ID);
	  }
	  
	  //majorField
	  try{
		  majorField = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.USER_MAJOR , ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  majorField = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_MAJOR);
	  }
	  
	  //visitSite
	  try{
		  visitSite = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.USER_VISIT_SITE , ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  visitSite = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_VISIT_SITE);
	  }
	  
	  //edisonTermsOfUseDate Date
	  try{
		  edisonTermsOfUseDate = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.USER_TERMS_OF_USE_DATE , ExpandoColumnConstants.DATE);
	  }catch(DuplicateColumnNameException dcne){
		  edisonTermsOfUseDate = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_TERMS_OF_USE_DATE);
	  }
	  
	  //projectCategory
	  try{
		  projectCategoryId = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), "projectCategoryId" , ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  projectCategoryId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), "projectCategoryId");
	  }

	  columnList.add(uiversityField);
	  columnList.add(virtualLabId);
	  columnList.add(classId);
	  columnList.add(majorField);
	  columnList.add(visitSite);
	  columnList.add(edisonTermsOfUseDate);
	  columnList.add(projectCategoryId);
	  
	  List<Role> roles = RoleLocalServiceUtil.getRoles(1, "");
	  Role adminRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.ADMINISTRATOR);
	  Role ownerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.OWNER);
	  
	  List<Role> newRoles = new ArrayList<Role>();
	  String[] actionIds = new String[] { ActionKeys.UPDATE, ActionKeys.VIEW };
	  
	  Map<Long, String[]> map = new HashMap<Long, String[]>();
	  for(Role role : roles){
		  if(role.getRoleId() != adminRole.getRoleId() && role.getRoleId() != ownerRole.getRoleId()){
			  map.put(role.getRoleId(), actionIds);
			  newRoles.add(role);
		  }
	  }

	  initPermissionCustomField(companyId, map, columnList, newRoles);
  }
  
  protected void initPageCreateCustomField() throws SystemException, PortalException{
	  ExpandoTable table = null;
	  long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();

	  try{
		  table = ExpandoTableLocalServiceUtil.addTable(companyId, Layout.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	  }catch (DuplicateTableNameException dtne){
		  table = ExpandoTableLocalServiceUtil.getTable(companyId, Layout.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	  }

	  List<ExpandoColumn> columnList = new ArrayList<ExpandoColumn>();
	  ExpandoColumn linkMenuEnUs = null;
	  ExpandoColumn linkMenukoKr = null;
	  try{
		  linkMenuEnUs = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), "LINK-MENU-en_US", ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  linkMenuEnUs = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), "LINK-MENU-en_US");
	  }
	  try{
		  linkMenukoKr = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), "LINK-MENU-ko_KR", ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  linkMenukoKr = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), "LINK-MENU-ko_KR");
	  }

	  columnList.add(linkMenuEnUs);
	  columnList.add(linkMenukoKr);
	  
	  List<Role> roles = RoleLocalServiceUtil.getRoles(1, "");
	  Role adminRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.ADMINISTRATOR);
	  Role ownerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.OWNER);
	  Role powerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.POWER_USER);
	  
	  List<Role> newRoles = new ArrayList<Role>();
	  String[] actionIds = new String[] { ActionKeys.VIEW };
	  String[] powerUserActionIds = new String[] { ActionKeys.UPDATE, ActionKeys.VIEW };
	  
	  Map<Long, String[]> map = new HashMap<Long, String[]>();
	  for(Role role : roles){
		  if(role.getRoleId() != adminRole.getRoleId() && role.getRoleId() != ownerRole.getRoleId()){
			  map.put(role.getRoleId(), actionIds);
			  newRoles.add(role);
		  }
		  if(role.getRoleId() == powerRole.getRoleId()){
			  map.put(role.getRoleId(), powerUserActionIds);
		  }
	  }
	  

	  initPermissionCustomField(companyId, map, columnList, newRoles);
	  
  }
  
  protected void initSiteCreateCustomField() throws SystemException, PortalException{// site customfields create
	  ExpandoTable table = null;
	  long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();

	  try{
		  table = ExpandoTableLocalServiceUtil.addTable(companyId, Group.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	  }catch (DuplicateTableNameException dtne){
		  table = ExpandoTableLocalServiceUtil.getTable(companyId, Group.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	  }
	  
	  List<ExpandoColumn> columnList = new ArrayList<ExpandoColumn>();
	  ExpandoColumn icebreakerAdminId = null;
	  ExpandoColumn icebreakerAdminPwd = null;
	  ExpandoColumn icebreakerURL = null;
	  ExpandoColumn icebreakerUrlPublic= null;
	  ExpandoColumn icebreakerZone = null;
	  
	  try{
		  icebreakerAdminId = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.SITE_ICEBREAKER_ADMIN_ID, ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  icebreakerAdminId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.SITE_ICEBREAKER_ADMIN_ID);
	  }
	  
	  //icebreakerAdminPwd
	  try{
		  icebreakerAdminPwd = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.SITE_ICEBREAKER_ADMIN_PWD, ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  icebreakerAdminPwd = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.SITE_ICEBREAKER_ADMIN_PWD);
	  }

	  //icebreakerUrl
	  try{
		  icebreakerURL = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.SITE_ICEBREAKER_URL, ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  icebreakerURL = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.SITE_ICEBREAKER_URL);
	  }

	  //icebreakerUrlPublic
	  try{
		  icebreakerUrlPublic = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.SITE_ICEBREAKER_URL_PUBLIC, ExpandoColumnConstants.STRING);
	  }catch(DuplicateColumnNameException dcne){
		  icebreakerUrlPublic = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.SITE_ICEBREAKER_URL_PUBLIC);
	  }
	  
	  //icebreakerZone
	  try{
		  icebreakerZone = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.SITE_ICEBREAKER_ZONE, ExpandoColumnConstants.DATE);
	  }catch(DuplicateColumnNameException dcne){
		  icebreakerZone = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.SITE_ICEBREAKER_ZONE);
	  }
	  
	  columnList.add(icebreakerAdminId);
	  columnList.add(icebreakerAdminPwd);
	  columnList.add(icebreakerURL);
	  columnList.add(icebreakerUrlPublic);
	  columnList.add(icebreakerZone);
	  
	  
	  List<Role> roles = RoleLocalServiceUtil.getRoles(1, "");
	  List<Role> newRoles = new ArrayList<Role>();
	  
	  Role adminRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.ADMINISTRATOR);
	  Role ownerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.OWNER);
	  
	  String[] actionIds = new String[] { ActionKeys.VIEW };
	  Map<Long, String[]> map = new HashMap<Long, String[]>();
	  for(Role role : roles){
		  if(role.getRoleId() != adminRole.getRoleId() && role.getRoleId() != ownerRole.getRoleId()){
		      map.put(role.getRoleId(), actionIds);
			  newRoles.add(role);
		  }
	  }
	  
	  initPermissionCustomField(companyId, map, columnList, newRoles);
  }

  protected void initPermissionCustomField(long companyId, Map<Long, String[]> map, List<ExpandoColumn> columnList, List<Role> roles) throws SystemException, PortalException{
	  
	  if(map != null && map.size() > 0 ){
		  for(ExpandoColumn column : columnList){
			  if(column != null){
				  for(Role role : roles){
					  if(!ResourcePermissionLocalServiceUtil.hasResourcePermission(
							  companyId, 
							  ExpandoColumn.class.getName(), 
							  ResourceConstants.SCOPE_INDIVIDUAL, 
							  String.valueOf(column.getColumnId()), 
							  role.getRoleId(), 
							  ActionKeys.VIEW
						 )){//role에 대해 column의 actionId가 없다면
						  ResourcePermissionLocalServiceUtil.setResourcePermissions(
							      companyId, 
							      ExpandoColumn.class.getName(), 
							      ResourceConstants.SCOPE_INDIVIDUAL, 
							      String.valueOf(column.getColumnId()), 
							      map
						  );
					  }
				  }
			  }
		  }
	  }
  }
  
  protected void initPreprences()
      throws SystemException, ReadOnlyException, ValidatorException, IOException, PortalException{
    long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
    long ownerId = companyId;
    int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;

    PortletPreferences portletPreferences = PortalPreferencesLocalServiceUtil
        .getPreferences(ownerId, ownerType);
    
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_ADMIN_EMAIL_LAB_REQUEST_SUBJECT);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_ADMIN_EMAIL_LAB_REQUEST_BODY);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_ADMIN_EMAIL_LAB_CONFIRM_SUBJECT);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_ADMIN_EMAIL_LAB_CONFIRM_BODY);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_ADMIN_EMAIL_WORKSPACE_CONFIRM_SUBJECT);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_ADMIN_EMAIL_WORKSPACE_CONFIRM_BODY);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_ADMIN_EMAIL_WORKSPACE_REQUEST_SUBJECT);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_ADMIN_EMAIL_WORKSPACE_REQUEST_BODY);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_ADMIN_ERROR_REPORT_SUBJECT);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_ADMIN_ERROR_REPORT_BODY);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_FORGOT_ID_SUBJECT);
    putPreperences(portletPreferences, EdisonPropskeys.EDISON_FORGOT_ID_BODY);
    
    PortalPreferencesLocalServiceUtil
        .updatePreferences(ownerId, ownerType, toXML(portletPreferences));
  }

  /**
   * EDISON 공통 코드 데이터 등록
   */
  protected void initSysCommonCd() throws SystemException, PortalException{
    String ExcelPath = PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/code";
    File dirFile = new File(ExcelPath);

    // 파일 경로가 없을 경우 생성
    if(!dirFile.exists()){
      dirFile.mkdir();
    }

    if(dirFile.isDirectory()){
      ExcelFileFilter excelFileFilter = new ExcelFileFilter();
      File[] fileList = dirFile.listFiles(excelFileFilter);
      if(fileList.length != 0){
        ExpandoTable table = null;
        long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();

        try{
          table = ExpandoTableLocalServiceUtil.addTable(companyId, ExpandoTable.class.getName(),
              EdisonExpando.TALBE_NAME);
          _log.info("SysCommon Table Create");
        }catch (DuplicateTableNameException dtne){
          table = ExpandoTableLocalServiceUtil.getTable(companyId, ExpandoTable.class.getName(),
              EdisonExpando.TALBE_NAME);
          _log.info("SysCommon Table Exist");
          deleteExpandoSysCommonCd(table);
        }

        // ColumnCreate
        HashMap<String, ExpandoColumn> columnMap = createSysCommonCdColumn(table);

        /*
         * ExcelFileRead
         */
        for(File tempfile : fileList){
          List<HashMap> dataRowList = new ArrayList();
          String fileName = tempfile.getName();
          FileInputStream fs = null;
          try{
            fs = new FileInputStream(tempfile);
          }catch (FileNotFoundException e1){
            e1.printStackTrace();
          }
          _log.info(fileName + "<-- SysCommonCd File Read");

          Workbook workbook = null;
          if(fileName.toLowerCase().endsWith("xlsx")){
            try{
              workbook = new XSSFWorkbook(fs);
            }catch (IOException e){
              e.printStackTrace();
            }
          }else{
            try{
              workbook = new HSSFWorkbook(fs);
            }catch (IOException e){
              e.printStackTrace();
            }
          }

          Sheet sheet = workbook.getSheetAt(0);
          Iterator<Row> rowIterator = sheet.iterator();

          HashMap<String, Object> dataRowMap = null;
          List<String> cellMapKey = new ArrayList<String>();

          while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            if(row.getRowNum() == 0){
              root1: for(int i = 0; i < row.getLastCellNum(); i++){
                String cellKey = CustomUtil.strNull(ExcelUtil.getCellValue(row.getCell(i)));
                if(!cellKey.equals("")){
                  cellMapKey.add(i, ExcelUtil.getCellValue(row.getCell(i)));
                }else{
                  break root1;
                }
              }
            }

            int rowCeltIndex = 0;
            if(row.getRowNum() > 0){
              dataRowMap = new HashMap<String, Object>();
              for(String rowMapKey : cellMapKey){
                dataRowMap.put(rowMapKey, ExcelUtil.getCellValue(row.getCell(rowCeltIndex++)));
              }
              dataRowList.add(dataRowMap);
            }
          }

          insertSysCommonData(table, columnMap, dataRowList);
          tempfile.delete();

        }// end for
        _log.info("SyscommonData Create END");
      }else{
        _log.info("EDISON_SysCommonCd Data File Not Exist");
      }
    }else{
      _log.error("Dir Not Exist");
    }
  }

  // ColumnData
  protected HashMap<String, ExpandoColumn> createSysCommonCdColumn(ExpandoTable table)
      throws PortalException, SystemException{
    ExpandoColumn cdNmColumn = null;
    try{
      cdNmColumn = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), EdisonExpando.CDNM,
          ExpandoColumnConstants.STRING_LOCALIZED);
    }catch (DuplicateColumnNameException dcne){
      cdNmColumn = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.CDNM);
    }

    ExpandoColumn regionColumn = null;
    try{
      regionColumn = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(),
          EdisonExpando.REGION, ExpandoColumnConstants.STRING);
    }catch (DuplicateColumnNameException dcne){
      regionColumn = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),
          EdisonExpando.REGION);
    }

    ExpandoColumn option1Column = null;
    try{
      option1Column = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(),
          EdisonExpando.OPTION1, ExpandoColumnConstants.STRING);
    }catch (DuplicateColumnNameException dcne){
      option1Column = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),
          EdisonExpando.OPTION1);
    }

    ExpandoColumn option2Column = null;
    try{
      option2Column = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(),
          EdisonExpando.OPTION2, ExpandoColumnConstants.STRING);
    }catch (DuplicateColumnNameException dcne){
      option2Column = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),
          EdisonExpando.OPTION2);
    }

    ExpandoColumn option3Column = null;
    try{
      option3Column = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(),
          EdisonExpando.OPTION3, ExpandoColumnConstants.STRING);
    }catch (DuplicateColumnNameException dcne){
      option3Column = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(),
          EdisonExpando.OPTION3);
    }

    HashMap<String, ExpandoColumn> columnMap = new HashMap<String, ExpandoColumn>();
    columnMap.put("cdNmColumn", cdNmColumn);
    columnMap.put("regionColumn", regionColumn);
    columnMap.put("option1Column", option1Column);
    columnMap.put("option2Column", option2Column);
    columnMap.put("option3Column", option3Column);
    return columnMap;
  }

  protected void insertSysCommonData(ExpandoTable table, HashMap<String, ExpandoColumn> columnMap,
      List<HashMap> rowDataList) throws PortalException, SystemException{

    for(HashMap<String, String> rowMap : rowDataList){
      _log.info("CODE_EXCEL_READ_ROW-->" + rowMap);
      Long classPk = Long.parseLong(CustomUtil.strNull(rowMap.get("classPK")));

      ExpandoColumn cdNmColumn = columnMap.get("cdNmColumn");
      Map<java.util.Locale, String> localeMap = new HashMap<java.util.Locale, String>();

      for(Locale locale : LanguageUtil.getAvailableLocales()){
        localeMap.put(locale, CustomUtil.strNull(rowMap.get(locale.toString())));
      }

      ExpandoValueLocalServiceUtil.addValue(table.getCompanyId(), table.getClassName(),
          table.getName(), cdNmColumn.getName(), classPk, localeMap, LocaleUtil.getDefault());

      ExpandoColumn regionColumn = columnMap.get("regionColumn");
      ExpandoValueLocalServiceUtil.addValue(table.getClassNameId(), table.getTableId(),
          regionColumn.getColumnId(), classPk, CustomUtil.strNull(rowMap.get("region")));

      ExpandoColumn option1Column = columnMap.get("option1Column");
      ExpandoValueLocalServiceUtil.addValue(table.getClassNameId(), table.getTableId(),
          option1Column.getColumnId(), classPk, CustomUtil.strNull(rowMap.get("option1")));

      ExpandoColumn option2Column = columnMap.get("option2Column");
      ExpandoValueLocalServiceUtil.addValue(table.getClassNameId(), table.getTableId(),
          option2Column.getColumnId(), classPk, CustomUtil.strNull(rowMap.get("option2")));

      ExpandoColumn option3Column = columnMap.get("option3Column");
      ExpandoValueLocalServiceUtil.addValue(table.getClassNameId(), table.getTableId(),
          option3Column.getColumnId(), classPk, CustomUtil.strNull(rowMap.get("option3")));
    }
  }

  protected void deleteExpandoSysCommonCd(ExpandoTable table)
      throws PortalException, SystemException{
    // DELETE Row
    List<ExpandoRow> rowList = ExpandoRowLocalServiceUtil.getRows(table.getCompanyId(),
        table.getClassNameId(), table.getName(), 0,
        ExpandoRowLocalServiceUtil.getRowsCount(table.getTableId()));
    for(ExpandoRow row : rowList){
      ExpandoValueLocalServiceUtil.deleteValues(table.getClassNameId(), row.getClassPK());
      ExpandoRowLocalServiceUtil.deleteRows(row.getClassPK());
    }

    // DELETE Column
    List<ExpandoColumn> columnList = ExpandoColumnLocalServiceUtil.getColumns(table.getTableId());
    for(ExpandoColumn column : columnList){
      ExpandoColumnLocalServiceUtil.deleteColumn(column);
    }
  }
  
  
  private void putPreperences(PortletPreferences portletPreferences, String key)
      throws ReadOnlyException{
    Map<String, String[]> preferenceMap = portletPreferences.getMap();
    if(preferenceMap == null || !preferenceMap.containsKey(key)){
      if(!portletPreferences.isReadOnly(key)){
        portletPreferences.setValue(key, getHookEmailContent(key));
      }
    }
  }

  private String toXML(PortletPreferences portletPreferences){
    Element portletPreferencesElement = new Element("portlet-preferences", false);
    Enumeration<String> portletPreferenceNames = portletPreferences.getNames();

    while(portletPreferenceNames.hasMoreElements()){
      String key = portletPreferenceNames.nextElement();
      String value = portletPreferences.getValue(key, StringPool.BLANK);
      Element preferenceElement = portletPreferencesElement.addElement("preference");
      preferenceElement.addElement("name", key);
      preferenceElement.addElement("value", value);
      if(portletPreferences.isReadOnly(key)){
        preferenceElement.addElement("read-only", Boolean.TRUE);
      }
    }
    return portletPreferencesElement.toXMLString();
  }
  
  private String getHookEmailContent(String name){
    return emailMap.get(name);
  }
  
  private final static Map<String, String> emailMap = new HashMap<String, String>();
  static{
    emailMap.put(EdisonPropskeys.EDISON_ADMIN_ERROR_REPORT_BODY, " EDISON ERROR LOG<br /><br />\n  \n  에러내용:<br />\n [$ERROR_MESSAGE$]<br />\n");
    emailMap.put(EdisonPropskeys.EDISON_ADMIN_ERROR_REPORT_SUBJECT, "EDISON 에러 Log");
    emailMap.put(EdisonPropskeys.EDISON_FORGOT_ID_BODY, "Dear [$TO_NAME$],<br /><br />\n\n EDISON 에서 요청 하신 ID를 확인 하였습니다.<br /><br />\n \n  이메일 : [$USER_ADDR$]<br /><br />\n 이름 : [$USER_NAME$]<br /><br />\n  아이디 : [$USER_ID$]<br /><br />\n\nSincerely,<br />\n[$FROM_NAME$]<br />\n[$FROM_ADDRESS$]<br />\n[$PORTAL_URL$]<br />");
    emailMap.put(EdisonPropskeys.EDISON_FORGOT_ID_SUBJECT, "EDISON ID 안내");
    emailMap.put(EdisonPropskeys.EDISON_ADMIN_EMAIL_LAB_CONFIRM_BODY, "Dear [$TO_NAME$],<br /><br />\n\n EDISON - [$GROUP_SITE_NAME$]에서 가상실험실에 대하여 안내합니다.<br /><br />\n  \n  처리내역 : [$PROCESSNOTE$]<br /><br />\n  \nSincerely,<br />\n[$FROM_NAME$]<br />\n[$FROM_ADDRESS$]<br />\n[$PORTAL_URL$]<br />");
    emailMap.put(EdisonPropskeys.EDISON_ADMIN_EMAIL_LAB_CONFIRM_SUBJECT, "EDISON 가상실험실 안내.");
    emailMap.put(EdisonPropskeys.EDISON_ADMIN_EMAIL_LAB_REQUEST_BODY, "Dear [$TO_NAME$],<br /><br />\n\n EDISON - [$GROUP_SITE_NAME$]에서 해당 가상실험실을 신청합니다.<br /><br />\n \n  사용자 아이디 : [$USER_SCREENNAME$]<br /><br />\n \n  소속기관 :[$USER_ORG$]<br /><br />\n  \nSincerely,<br />\n[$FROM_NAME$]<br />\n[$FROM_ADDRESS$]<br />\n[$PORTAL_URL$]<br />");
    emailMap.put(EdisonPropskeys.EDISON_ADMIN_EMAIL_LAB_REQUEST_SUBJECT, "EDISON 가상실험실 신청.");
    emailMap.put(EdisonPropskeys.EDISON_ADMIN_EMAIL_WORKSPACE_CONFIRM_BODY, "Dear [$TO_NAME$],<br /><br />\n\n EDISON - [$GROUP_SITE_NAME$]에서 워크스페이스 관련하여 안내합니다.<br /><br />\n \n  처리내역 : [$PROCESSNOTE$]<br /><br />\n\nSincerely,<br />\n[$FROM_NAME$]<br />\n[$FROM_ADDRESS$]<br />\n[$PORTAL_URL$]<br />");
    emailMap.put(EdisonPropskeys.EDISON_ADMIN_EMAIL_WORKSPACE_CONFIRM_SUBJECT, "EDISON 워크스페이스 신청 안내");
    emailMap.put(EdisonPropskeys.EDISON_ADMIN_EMAIL_WORKSPACE_REQUEST_BODY, "Dear [$TO_NAME$],<br /><br />\n\n EDISON - [$GROUP_SITE_NAME$]에서 워크스페이스를 신청합니다.<br /><br />\n \n  사용자 아이디 : [$USER_SCREENNAME$]<br /><br />\n \n  소속기관 :[$USER_ORG$]<br /><br />\n\nSincerely,\n[$FROM_NAME$]<br />\n[$FROM_ADDRESS$]<br />\n[$PORTAL_URL$]<br />");
    emailMap.put(EdisonPropskeys.EDISON_ADMIN_EMAIL_WORKSPACE_REQUEST_SUBJECT, "EDISON 워크스페이스 신청.");
  }
}