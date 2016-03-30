<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign In</title>

<!-- Bootstrap Core CSS -->
<link href="<c:url value="/webjars/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css"/>" rel="stylesheet">

<!-- Social Buttons CSS -->
<link href="<c:url value="/webjars/bootstrap-social/4.9.0/bootstrap-social.css"/>" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="<c:url value="/webjars/metisMenu/1.1.3-1/metisMenu.min.css"/>" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="/sb-admin-2-1.0.8/dist/css/sb-admin-2.css"/>" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<c:url value="/webjars/font-awesome/4.5.0/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">

<!-- jQuery -->
<script src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>

<!-- tether Uncaught Error: Bootstrap tooltips require Tether (http://github.hubspot.com/tether/) -->
<script src="<c:url value="/webjars/tether/1.1.1/dist/js/tether.min.js"/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/webjars/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"/>"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<c:url value="/webjars/metisMenu/1.1.3-1/metisMenu.min.js"/>"></script>

<!-- Custom Theme JavaScript -->
<script src="<c:url value="/sb-admin-2-1.0.8/dist/js/sb-admin-2.js"/>"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<!-- Kakao -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>

$(function() {
});

</script>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                	<c:url value="/authLogin.do" var="actionUrl"/>
					<form role="form" action="${actionUrl}" method="post">       
                         <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="E-mail" name="username" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="password" type="password" value="">
                            </div>
                            <!-- 
                            <div class="checkbox">
                                <label>
                                    <input name="remember-me" type="checkbox"> Remember Me
                                </label>
                            </div>
                             -->
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
                        </fieldset>
                    </form>
                </div>
            </div>
            <!--// Login panel -->
			<!-- /.panel -->
            <div class="panel panel-default">
                <div class="panel-heading">
                &nbsp;    
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <h4>with Social Logins</h4>
                    <a class="btn btn-block btn-social btn-facebook" style="background-color:#ffff1a;" onclick="document.kakao.submit();">
                        <i class="fa"></i> Sign in with Kakao
                    </a>
                    <a class="btn btn-block btn-social btn-facebook" onclick="document.facebook.submit();">
                        <i class="fa fa-facebook"></i> Sign in with Facebook
                    </a>
                    <a class="btn btn-block btn-social btn-twitter" onclick="document.twitter.submit();">
                        <i class="fa fa-twitter"></i> Sign in with Twitter
                    </a>
                    <a class="btn btn-block btn-social btn-github" onclick="document.github.submit();">
                        <i class="fa fa-github"></i> Sign in with GitHub
                    </a>
                </div>
               <!-- /.panel-body -->
            </div>
            <!-- /.panel -->            
        </div>
    </div>
</div>
		<form action="<c:url value="/kakao/userinfo"/>" name="kakao" method="get">
		</form>
		<form action="<c:url value="/auth/facebook"/>" name="facebook" method="get">
			<input type="hidden" name="scope" value="public_profile,email"></input>
		</form>
		<form action="<c:url value="/auth/twitter"/>" name="twitter" method="get">
			<input type="hidden" name="scope" value="email"/>
		</form>
		<form action="<c:url value="/auth/github"/>" name="github" method="get">
			<input type="hidden" name="scope" value="email"/>
		</form>
</body>
</html>
