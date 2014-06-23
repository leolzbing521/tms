<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/head.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet", href="<c:url value='/css/bootstrap.css'/>">
<link rel="stylesheet", href="<c:url value='/font-css/fontello.css'/>">
<link rel="stylesheet", href="<c:url value='/css/style.css'/>">
<style type="text/css">
	body {
       padding-top: 40px;
       background-color: #eee;
   }

   .form-signin {
       max-width: 330px;
       padding: 15px;
       margin: 0 auto;
   }

   .form-signin .form-signin-heading,
   .form-signin .checkbox {
       margin-bottom: 10px;
   }

   .form-signin .checkbox {
       font-weight: normal;
   }

   .form-signin .form-control {
       position: relative;
       font-size: 16px;
       height: auto;
       padding: 10px;
       -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
       box-sizing: border-box;
   }

   .form-signin .form-control:focus {
       z-index: 2;
   }

   .form-signin input[type="text"] {
       margin-bottom: -1px;
       border-bottom-left-radius: 0;
       border-bottom-right-radius: 0;
   }

   .form-signin input[type="password"] {
       margin-bottom: 10px;
       border-top-left-radius: 0;
       border-top-right-radius: 0;
   }
</style>
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap.min.js'/>" ></script>
<script type="text/javascript">
	$(function() {
	    $('#submit').click(function() {
	        $('#login').submit();
	    });
	});
</script>

<title>老铭泰综合管理系统</title>
</head>
<body>
	<div id="wrap">
		<div class="container">
			<form id="login", action="<c:url value='/web/login'/>", method="post", class="form-signin">
				<input type="hidden" name="_method" value="post"/>
				<h2 class="form-signin-heading">请登录</h2>
				<input class="form-control", type='text', name='username', placeholder='帐号', autofocus='autofocus', required='required'>
				<input class="form-control", type='password', name='password', placeholder='密码', autofocus='autofocus', required='required'>
				<label class="checkbox">
					<input type='checkbox', value='remember'> 自动登录
				</label>
				<button type="submit", id="submit", class="btn btn-lg btn-primary btn-block">
					<i class="icon-key", style="margin-right:10px">登录</i>
				</button>
			</form>
		</div>
	</div>
	<jsp:include page="/jsp/footer.html"></jsp:include>
</body>
</html>