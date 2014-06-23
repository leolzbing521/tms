<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/head.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>老铭泰综合管理系统</title>

<link rel="stylesheet", href="<c:url value='/css/jq/jquery-ui-1.10.4.custom.min.css'/>">
<link rel="stylesheet", href="<c:url value='/css/bootstrap.css'/>">
<link rel="stylesheet", href="<c:url value='/font-css/fontello.css'/>" >
<link rel="stylesheet", href="<c:url value='/css/style.css'/>" >
<link rel="stylesheet", href="<c:url value='/css/bootstrap-select.min.css'/>" >
<link rel="stylesheet", href="<c:url value='/css/fullcalendar.css'/>" >
<link href="<c:url value='/css/fullcalendar.print.css'/>", rel="stylesheet", media="print" />
<link rel="stylesheet", href="<c:url value='/css/bootstrap-modal-bs3patch.css'/>" >
<link rel="stylesheet", href="<c:url value='/css/bootstrap-modal.css'/>" >
<link rel="stylesheet", href="<c:url value='/css/bootstrap-switch.css'/>" >
<link rel="stylesheet", href="<c:url value='/css/bootstrap-tagsinput.css'/>" >
<link rel="stylesheet", href="<c:url value='/css/toastr.min.css'/>" >
<link rel="stylesheet", href="<c:url value='/css/pagination.css'/>" >
<link rel="stylesheet", href="<c:url value='/css/bootstrap-tagsinput.css'/>" >
<link rel="stylesheet", href="<c:url value='/css/datepicker.css'/>" >

<script type="text/javascript" src="<c:url value='/js/jquery-1.11.0.min.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.min.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/messages_cn.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.bootstrap.popover.min.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap.min.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap-select.min.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/runtime.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.min.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/fullcalendar.min.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap-button.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap-modalmanager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap-modal.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap-switch.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap-tagsinput.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap-filestyle.min.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/toastr.min.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/highcharts.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/baiduTemplate.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/pup.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/utils.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery.pagination.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/template.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap-tagsinput.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery.fileupload.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery.iframe-transport.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.widget.js'/>" ></script>

<script type="text/javascript">
<!--
$(function() {
	pup.template.setup();

    $('ul.nav li.dropdown').hover(function() {
      $(this).find('.dropdown-menu').stop(true, true).show();
      $(this).addClass('open');
    }, function() {
      $(this).find('.dropdown-menu').stop(true, true).hide();
      $(this).removeClass('open');
    });
});
//-->
</script>
</head>
<body>
	<div id="wrap", data-viewgroup='fullScreenLayers', data-viewid='fsl-1'>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<div id="main"></div>
		</div>
	</div>
	<jsp:include page="/jsp/footer.html"></jsp:include>
</body>
</html>