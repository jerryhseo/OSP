<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@include file="../init.jsp"%>

<%
PortletPreferences preferences = portletDisplay.getPortletSetup();
preferences.setValue("portletSetupShowBorders", String.valueOf(Boolean.FALSE));
preferences.store();

String inputData = ParamUtil.getString(request, "inputData", "");
String connector = ParamUtil.getString(request, "connector", "BROADCAST");
boolean eventEnable = ParamUtil.getBoolean(request, "eventEnable", true);
String mode = ParamUtil.getString(request, "mode", "VIEW");
boolean isPopup = LiferayWindowState.isExclusive(request);

String launcherURL = (String)renderRequest.getAttribute("launcherURL");
%>
<portlet:resourceURL var="serveResourceURL"></portlet:resourceURL>
<%--
<a href="<%=request.getContextPath()%>/html/pv5/start.jsp" target="_blank">Connect using Visualizer 5</a>
<br/>
<a href="<%=request.getContextPath()%>/html/pv4/start.jsp" target="_blank">Connect using Visualizer 4</a>
 --%>
<iframe id="<portlet:namespace/>canvas"  style="width:100%; height:100%;"></iframe>

<script>
/***********************************************************************
 * Global variables section
 ***********************************************************************/
var <portlet:namespace/>connector = '<%=connector%>';
var <portlet:namespace/>eventEnable = JSON.parse('<%=eventEnable%>');
var <portlet:namespace/>mode = '<%=mode%>';
var <portlet:namespace/>launcherURL = '<%=launcherURL%>';


/***********************************************************************
 * Initailization section using parameters
 ***********************************************************************/
 //for test...
 //<portlet:namespace/>eventEnable = false;

if(!<portlet:namespace/>eventEnable){
    var inputData = '<%=inputData%>';
    
    var initData;
    if( !inputData ){
        initData = new OSP.InputData();
    }
    else{
        initData = new OSP.InputData(JSON.parse(inputData));
    }
    
	//	String dataDirectoryStr = "/EDISON/TEST/DATA/test/jobs/a65aff7d-643e-4e5b-8a9d-42b79c974553/20d9391d-1f9b-4353-bc26-ae137fba5b70.job/result/NACA0012_familyII_3";
	//String fileToLoad = "pre_rec_d08.vtk";

    //for test
    //initData.type('folder');
    //initData.parent('a65aff7d-643e-4e5b-8a9d-42b79c974553/20d9391d-1f9b-4353-bc26-ae137fba5b70.job/result/NACA0012_familyII_3');
    //initData.name('');
    
    <portlet:namespace/>loadParaView( initData );
}

/***********************************************************************
 * Handling OSP Events
 ***********************************************************************/
Liferay.on(
  'OSP_HANDSHAKE',
  function(e){
    var myId = '<%=portletDisplay.getId()%>';
    if(e.targetPortlet === myId){
      <portlet:namespace/>connector = e.portletId;
      <portlet:namespace/>mode = e.mode;
      var events = [ 
          'OSP_EVENTS_REGISTERED', 
          'OSP_LOAD_DATA'
        ];
      var eventData = {
          portletId : myId,
          targetPortlet : <portlet:namespace/>connector,
          data : events
        };
      Liferay.fire('OSP_REGISTER_EVENTS', eventData);
    }
  });

Liferay.on(
  'OSP_EVENTS_REGISTERED', 
  function(e) {
    
    var myId = '<%=portletDisplay.getId()%>';
    if(e.targetPortlet === myId){
    	console.log(e.portletId+' activated. '+new Date()+']');
    }
  });
 
Liferay.on('OSP_LOAD_DATA', function(e){
  var myId = '<%=portletDisplay.getId()%>';
  if( e.targetPortlet === myId ){
    if( e.data){
      <portlet:namespace/>loadParaView( e.data );
    }
  }
});

Liferay.on(
		'OSP_REFRESH_OUTPUT_VIEW',
		function(e){
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet: <portlet:namespace/>connector
			};

			Liferay.fire('OSP_REQUEST_OUTPUT_PATH', eventData);
		}
);


/***********************************************************************
 * Golbal functions
 ***********************************************************************/
function <portlet:namespace/>loadParaView( inputData ){
    var dataDirectory;
    var fileToLoad = '';

    if( inputData.type_ === 'folder'){
		dataDirectory = <portlet:namespace/>mergePath( inputData.parent_, inputData.name_ ); 
    }
    else{
		dataDirectory = inputData.parent_;
    }
    
    $.ajax({
			url: '<%=serveResourceURL.toString()%>',
			type:'POST',
			dataType:'text',
			data:{
			    <portlet:namespace/>command: 'GET_ABSOLUTE_PATH',
			    <portlet:namespace/>targetPath: dataDirectory,
			    <portlet:namespace/>repositoryType: inputData.repositoryType_
			},
			success: function( result ){
			    dataDirectory = result;
			    if( inputData.type_ === 'file' ){
					fileToLoad = <portlet:namespace/>mergePath( dataDirectory, inputData.name_ );
			    }
			    
			    var paraViewSrc = '<%=request.getContextPath()%>/html/pv5/start.jsp?';
			    paraViewSrc += 'launcherURL='+<portlet:namespace/>launcherURL+
			    						  '&dataDirectory='+dataDirectory;
			    if( fileToLoad ){
			    	paraViewSrc += '&fileToLoad='+fileToLoad;
			    }
			    
			    $('#<portlet:namespace/>canvas').attr('src', paraViewSrc);
			},
			error: function( data, e){
				console.log('ParaView AJAX ERROR-->'+e);
			}
    });
}

function <portlet:namespace/>mergePath( parent, child ){
	if( !parent && !child )	return '';
	if( !parent )
		return child;
	if( !child )
		return parent;
	
	return parent+'/'+child;
}

</script>
