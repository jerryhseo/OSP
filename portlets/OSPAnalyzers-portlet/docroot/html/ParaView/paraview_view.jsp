<%@include file="../init.jsp"%>

<iframe id="<portlet:namespace/>canvas" ></iframe>

<script>
$('#<portlet:namespace/>canvas').attr( 'width', '100%');
$('#<portlet:namespace/>canvas').attr('height', '740px');
$('#<portlet:namespace/>canvas').attr('src', '<%=request.getContextPath()%>/html/paraview/pv5/start.jsp');
</script>
