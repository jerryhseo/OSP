package org.kisti.edison.multiboard.controller.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.multiboard.model.Board;
import org.kisti.edison.multiboard.model.BoardDiv;
import org.kisti.edison.multiboard.service.BoardDivLocalServiceUtil;
import org.kisti.edison.multiboard.service.BoardLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class CommentController {
    
    protected static Log  log = LogFactoryUtil.getLog(CommentController.class);
    
    @RequestMapping//default
    public String view(RenderRequest request, RenderResponse response, ModelMap model) {
        
        /* Request Parameter */
        boolean isMember = Boolean.parseBoolean((String) request.getParameter("isMember"));
        String authYn = (String) request.getParameter("authYn");
        String customId = (String) request.getParameter("customId");
        long modelId = Long.parseLong(CustomUtil.strNull(request.getParameter("modelId"), "0"));
        
        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
        
        Group portalGroup = null;
        long portalGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
        long userId = themeDisplay.getUserId();
        
        String currunt_folder;
        try {
            currunt_folder = "/" +portalGroupId+" - " +CompanyLocalServiceUtil.getCompany(PortalUtil.getCompanyId(request)).getName() + "/"+portalGroupId+"_EDISON_FILE"+"/"+EdisonFileConstants.USER_IMAGE+"/"+userId+ "/";
            
            EdisonFileUtil.checkUserFolder(request, userId, portalGroupId, EdisonFileConstants.USER_IMAGE);
            
            model.addAttribute("currentFolder", currunt_folder);
            model.addAttribute("userId", userId);
            model.addAttribute("isMember", isMember);
            model.addAttribute("authYn", authYn);
            model.addAttribute("modelId", modelId);
        } catch (PortalException | SystemException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "list";
    }
    
    /*
     * COMMENT List 출력 
     */
    @ResourceMapping(value="getCommentList")
    public void getCommentList(ResourceRequest request, ResourceResponse response, @RequestParam(value="sendData") String sendData){
        
        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
        Map params = RequestUtil.getParameterMap(request);
        
        //VIEW가 아니면서 (게시물 보기) 로그인이 안되어있을 경우 홈 화면으로 보냄
        if(!themeDisplay.isSignedIn()) {
            return;
        }
        
        //parameter
        int listSize = Integer.parseInt(CustomUtil.strNull(params.get("listSize"), "10"));
        int currentPage = Integer.parseInt(CustomUtil.strNull(params.get("currentPage"), "1"));
        int blockSize = Integer.parseInt(((PortletRequest) request).getPreferences().getValue("blockSize", "10"));
        long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
        String searchValue = "";
        String customId = CustomUtil.strNull(params.get("customId"), "");
        
        String divSort = request.getPreferences().getValue("divSort", "COMMENT");
        
        long divCd = 700;
        
        int start = ((currentPage - 1) * listSize);
        Locale locale = themeDisplay.getLocale();
        
        long groupBoardSeq = Long.parseLong(sendData);
        boolean popupYn = false;
        String siteGroup = "";
        
        try{
            BoardDiv boardDiv = BoardDivLocalServiceUtil.getBoardDiv(divCd);
            
            // divCd : board 구분(700 : commant)      boardGroupId : Portlet Theme에 지정된 boardGroupId      searchValue : 검색 값
            // groupBoardSeq : 댓글이 위치한 게시글의 boardSeq        siteGroupId :
            List<Map<String,Object>> boardList = BoardDivLocalServiceUtil.getCustomListBoard(divCd, start, listSize, boardGroupId, customId, searchValue, locale, groupBoardSeq, popupYn, siteGroup);
            
            // 첨부파일, 댓글 및 comment(reply) 작성자 Img 추출
            String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
            List fileList = null;                                                                           // file 리스트 임시 저장
            Map<String, Object> commentFileMap = new HashMap<String, Object>();                             // file 리스트가 저장되는 Map
            Map<String, Object> commentWriterImgMap = new HashMap<String, Object>();                        // Comment 작성자 Img
            Map<String, Object> replyCntMap = new HashMap<String, Object>();                                // 댓글 갯수
            for(int i=0; i<boardList.size(); i++){
                if(0 < boardList.size()){
                    // 파일 추출
                    fileList=EdisonFileUtil.getListEdisonFile(
                                        boardGroupId, 
                                        preFix, 
                                        GetterUtil.get((String) boardList.get(i).get("boardSeq"),""),
                                        divSort
                                        );
                    if(!fileList.isEmpty()){
                        commentFileMap.put((String) boardList.get(i).get("boardSeq"), fileList);
                    }
                    
                    // 댓글 갯수 추출
                    groupBoardSeq = Long.parseLong((String) boardList.get(i).get("boardSeq"));
                    List<Map<String,Object>> boardReplyList = BoardDivLocalServiceUtil.getCustomListBoard(divCd, start, listSize, boardGroupId, customId, searchValue, locale, groupBoardSeq, popupYn, siteGroup);
                    
                    replyCntMap.put((String) boardList.get(i).get("boardSeq"), boardReplyList.size());
                    
                    //코멘트 작성자 Img 추출
                    User userInfomation = UserLocalServiceUtil.getUser( Long.parseLong((String) boardList.get(i).get("writerId")));
                    commentWriterImgMap.put((String) boardList.get(i).get("boardSeq"), userInfomation.getPortraitURL(themeDisplay));
                }
            }
            
            //JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("divCd", divCd);
            jsonObj.put("boardList", boardList);
            jsonObj.put("replyCntMap", replyCntMap);
            jsonObj.put("commentWriterImgMap", commentWriterImgMap);
            jsonObj.put("commentFileMap", commentFileMap);
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(jsonObj.toString());
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * COMMENT 추가
     */
    @ResourceMapping(value="addCommentList")
    public void addCommentList(ResourceRequest request, ResourceResponse response, @RequestParam(value="saveData") String saveData){
        
        Map params = RequestUtil.getParameterMap(request);
        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
        UploadPortletRequest upload = com.liferay.portal.util.PortalUtil.getUploadPortletRequest(request);
        
        /*comment 추가하거나 댓글 작성할 경우 commant 내용과 해당 게시글 또는 commant의 boardSeq*/
        JSONObject requestJson = JSONObject.fromObject(saveData);
        String comment = (String) requestJson.get("comment");
        String gBoardSeq = (String) requestJson.get("groupBoardSeq");

        
        long userId = themeDisplay.getUserId();
        long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
        String divSort = request.getPreferences().getValue("divSort", "COMMENT");
        long divCd = 700;
        String siteGroup = "";
        
        Locale locale = themeDisplay.getLocale();
        String customId = CustomUtil.strNull(params.get("customId"), "");
        
        /* 다른 게시글의 boardSeq를 groupBoardSeq로 등록할 경우 해당 게시글의 코멘트로 작성됨. */
        long groupBoardSeq = Long.parseLong(gBoardSeq);
        boolean popupYn = false;
        
        /* File 저장 */
        try{
            /* 전달받은 데이터 저장 */
            /* TODO groupBoardSeq값을 통해 특정 게시글에 코멘트로 등록 */
            params.put("groupId", boardGroupId);
            params.put("divCd", divCd);
            params.put("userId", themeDisplay.getUserId());
            params.put("locale", CustomUtil.stringToLocale(CustomUtil.strNull(params.get("select_languageId"))));
            params.put("allNoticeYn", Boolean.parseBoolean(CustomUtil.strNull(params.get("allNoticeYn"), "0")));
            params.put("popupYn", Boolean.parseBoolean(CustomUtil.strNull(params.get("popupYn"), "0")));
            params.put("popupStartDt", CustomUtil.strNull(params.get("popupStartDt")));
            params.put("popupEndDt", CustomUtil.strNull(params.get("popupEndDt")));
            params.put("groupBoardSeq", groupBoardSeq);
            params.put("groupBoardTurn", "0");
            params.put("replyDepth", 0);
            params.put("title", comment);
            params.put("content", comment);     // 코멘트 내용
            params.put("customId", customId);
            
            Board brd = BoardLocalServiceUtil.addBoard(params);
            
            String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
            EdisonFileUtil.insertEdisonFile(request, upload, userId, boardGroupId, preFix, CustomUtil.strNull(String.valueOf(brd.getBoardSeq())), "addfile", divSort);
            
            //JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("result", "save true");
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(jsonObj.toString());
            
        }catch (Exception e) {
        }
    }
    
    @ResourceMapping(value="updateCommentList")
    public void updateCommentList(ResourceRequest request, ResourceResponse response, @RequestParam(value="updateData") String updateData){
        
        try{
            UploadPortletRequest upload = com.liferay.portal.util.PortalUtil.getUploadPortletRequest(request);
            
            Map params = RequestUtil.getParameterMap(request);
            ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
            
            /*수정할 comment 내용과 해당 comment의 seq*/
            JSONObject requestJson = JSONObject.fromObject(updateData);
            String comment = (String) requestJson.get("comment");
            String boardSequence = (String) requestJson.get("boardSeq");
            
            
            long userId = themeDisplay.getUserId();
            long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
            Long boardSeq = Long.parseLong(boardSequence);
            String divSort = request.getPreferences().getValue("divSort", "COMMENT");
            long divCd = 700;
            String siteGroup = "";
            
            Locale locale = themeDisplay.getLocale();
            String customId = CustomUtil.strNull(params.get("customId"), "");
            boolean popupYn = false;
            
            /* 전달받은 데이터 저장 */ 
            params.put("customId", customId);
            params.put("userId", themeDisplay.getUserId());
            params.put("boardSeq", boardSeq);
            params.put("locale", CustomUtil.stringToLocale(CustomUtil.strNull(params.get("current_languageId"), CustomUtil.strNull(params.get("select_languageId")))));
            params.put("allNoticeYn", Boolean.parseBoolean(CustomUtil.strNull(params.get("allNoticeYn"), "0")));
            params.put("popupYn", Boolean.parseBoolean(CustomUtil.strNull(params.get("popupYn"), "0")));
            params.put("popupStartDt", CustomUtil.strNull(params.get("popupStartDt")));
            params.put("popupEndDt", CustomUtil.strNull(params.get("popupEndDt")));
            params.put("title", CustomUtil.strNull(params.get("boardSeq"),"test"));
            params.put("content", comment);
            params.put("siteGroup", siteGroup);
            
            Board brd = BoardLocalServiceUtil.updateBoard(params);
            
            //String preFix = "";
            String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
            EdisonFileUtil.insertEdisonFile(request, upload, userId, boardGroupId, preFix, CustomUtil.strNull(String.valueOf(brd.getBoardSeq())), "addfile", divSort);
            
            //JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("result", "update true");
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(jsonObj.toString());
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @ResourceMapping(value="deleteCommentList")
    public void deleteCommentList(ResourceRequest request, ResourceResponse response, @RequestParam(value="sendData") String sendData){
        
        try {
            Map params = RequestUtil.getParameterMap(request);
            ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
            
            //parameter
            String customId = CustomUtil.strNull(params.get("customId"), "");
            long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
            
            
            String divSort = request.getPreferences().getValue("divSort", "COMMENT");
            Board brd = BoardLocalServiceUtil.deleteBoard(Long.parseLong(sendData));
            
            //JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("result", "delete true");
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(jsonObj.toString());
        } catch (Exception e) {
        }
    }
    
    @ResourceMapping(value="sendMailComment")
    public void sendMailComment(ResourceRequest request, ResourceResponse response, @RequestParam(value="sendMailData") String sendMailData){
        
        try {
            /* 수신/발신자 이메일, 전달할 메시지 */
            JSONObject requestJson = JSONObject.fromObject(sendMailData);
            String toEmail = (String) requestJson.get("toEmail");           // 받는 사람 Email
            String sendMsg = (String) requestJson.get("sendMsg");       // 전달 메시지
            String fromEmail = (String) requestJson.get("fromEmail");   // 보내는 사람 Email
            
            Map params = RequestUtil.getParameterMap(request);
            ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
            
            //parameter - 메일 전송 부분에서는 필요 없읍.
            String customId = CustomUtil.strNull(params.get("customId"), "");
            long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
            String divSort = request.getPreferences().getValue("divSort", "COMMENT");
            
            /* 메일 전송 기능 추가 */
            Properties props = System.getProperties();
            props.setProperty("mail.transport.protocol", "smtp");
            
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.quitwait", "false");

            Authenticator auth = new MyAuthenticatoer();
            
            Session mailSession = Session.getInstance(props,  auth);
            mailSession.setDebug(true);
            
            MimeMessage msg = new MimeMessage(mailSession);
            
            msg.setFrom(new InternetAddress(fromEmail, fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("sub title","utf-8");
            msg.setText(sendMsg, "utf-8");
            msg.setHeader("content-Type", "text/html");
            
            /* 메일 전송 */
            Transport.send(msg);
            
            //JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("result", "Mail Send Success...");
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(jsonObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /* Mail 전송 시  ID(email)와 PW를 통해 인증 획득 */
    static class MyAuthenticatoer extends Authenticator {
        public static String userName = "";        // 현재 접속중인 사용자의 email
        public static String password = "";                               // 현재 접속중인 사용자의 password
        
        public PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(userName, password);
        }
    }
    
    // 첨부파일 삭제
    @ResourceMapping(value="deleteSingleEdisonFile")
    public void deleteSingleEdisonFile(ResourceRequest request, ResourceResponse response) throws SystemException, JSONException, IOException, PortalException, ParseException, PortletModeException{
        
        Map params = RequestUtil.getParameterMap(request);
        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
        long fileEntryId = Long.parseLong(CustomUtil.strNull(params.get("fileEntryId")));
        long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
        String customId = CustomUtil.strNull(params.get("customId"), "");
        
        String resultMsg = "";
        String boardSeq = "";
        List fileList = new ArrayList();
        
        if(EdisonFileUtil.deleteSingleEdisonFile(fileEntryId)){
            
            String divSort = request.getPreferences().getValue("divSort", "COMMENT");
            boardSeq = CustomUtil.strNull(params.get("boardSeq"));
            
            String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
            
            fileList = EdisonFileUtil.getListEdisonFile(
                                                        boardGroupId, 
                                                        preFix, 
                                                        boardSeq,
                                                        divSort);
            
            resultMsg = "SUCCESS";
        }else{
            resultMsg = "DELETE_FAIL";
        }
        
        JSONObject obj = new JSONObject();
        
        obj.put("fileList", fileList);
        obj.put("boardSeq", boardSeq);
        obj.put("resultMsg", resultMsg);
        
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(obj.toString());
    }
    
    
    //첨부파일 다운로드
    @ResourceMapping(value="edisonFileDownload")
    public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws SystemException, JSONException, IOException, PortalException, ParseException, PortletModeException{

        Map paramsMap = RequestUtil.getParameterMap(request);
        long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
        
        EdisonFileUtil.edisonFileDownload(response, fileEntryId);
    }   
    
    /* 타임라인 추가 */
    @ResourceMapping(value="writeTimeLineAboutSharing")
    public void writeTimeLineAboutSharing(ResourceRequest request, ResourceResponse response){
    	try{
    		Map<String, Object> paramsMap = RequestUtil.getParameterMap(request);
    		
    		long currentTime = System.currentTimeMillis();
    		SimpleDateFormat currentTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String thisTime = currentTimeFormat.format(new Date(currentTime));
    		
    		String groupBoardSeq = CustomUtil.strNull(paramsMap.get("groupBoardSeq"), "0");
    		String comment = "[ " + thisTime + " ] " + CustomUtil.strNull(paramsMap.get("comment"), "");
    		
    		JSONObject jsonData = new JSONObject();
    		jsonData.put("comment", comment);
    		jsonData.put("groupBoardSeq", groupBoardSeq);
    		String saveData = jsonData.toString();
    		
    		addCommentList(request, response, saveData);
    		
    	} catch (Exception e) {
    		log.error(e);
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.EVENT_ERROR);
		}
    }
}
