<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 9:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="taglib.jsp"%>
<!-- BEGIN # MODAL LOGIN -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" align="center">
                <img id="img_logo" src="<c:url value="/static/img/logo.png"/>">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            </button>
        </div>
        <!-- Begin # DIV Form -->
            <div id="div-forms">
                <!-- Begin # Login Form -->
                <%--action url for login: <c:url var="loginUrl" value="/login/submit" /> --%>
                <form id="login-form">
                    <div class="modal-body">
                        <div id="div-login-msg">
                            <div id="icon-login-msg" class="glyphicon glyphicon-chevron-right"></div>
                            <span id="text-login-msg">Type your username and password.</span>
                        </div>
                        <input id="login_username" name="username" class="form-control" type="text" placeholder="Username (type ERROR for error effect)" required>
                        <input id="login_password" name="password" class="form-control" type="password" placeholder="Password" required>
                        <div class="checkbox">
                            <label><input type="checkbox" name="remember-me"> Remember me</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <button id="login_btn" type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
                        </div>
                        <div>
                            <a id="login_lost_btn" href="<c:url value="/forgot_password"/>" target="blank" class="btn btn-link">Lost Password?</a>
                            <a id="login_register_btn" href="<c:url value="/register"/>" target="blank" class="btn btn-link">Register</a>
                        </div>
                    </div>
                </form>
                <!-- End # Login Form -->
            </div>
            <!-- End # DIV Form -->
        </div>
    </div>
</div>
<!-- END # MODAL LOGIN -->

<script>
    $(function() {

        var $formLogin = $('#login-form');
        var $divForms = $('#div-forms');
        var $modalAnimateTime = 300;
        var $msgAnimateTime = 150;
        var $msgShowTime = 2000;

        $("#login-form").submit(function (e) {
            e.preventDefault();
            $.ajax({
                url: '/login',
                type: "POST",
                data: $("#login-form").serialize(),
                dataType: 'json',
                success: function(result) {
                    if (result.success) {
                        $("#login-modal").modal("hide");
                        $(".login").remove();
                        $(".register").remove();
                        $.get("/get-user-link", function(data) {
                            $("#top-links").prepend(data);
                            $('#top-links li.upload a').attr("data-login", "true");
                        });
                        $.get("/get-logout-form", function(data) {
                            $("#authentication").append(data);
                            $("#login-modal").empty();
                        });
                    } else {
                        msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "error", "glyphicon-remove", "Invalid username or password.");
                    }
                }
            });
        });

        function msgFade ($msgId, $msgText) {
            $msgId.fadeOut($msgAnimateTime, function() {
                $(this).text($msgText).fadeIn($msgAnimateTime);
            });
        }

        function msgChange($divTag, $iconTag, $textTag, $divClass, $iconClass, $msgText) {
            var $msgOld = $divTag.text();
            msgFade($textTag, $msgText);
            $divTag.addClass($divClass);
            $iconTag.removeClass("glyphicon-chevron-right");
            $iconTag.addClass($iconClass + " " + $divClass);
            setTimeout(function() {
                msgFade($textTag, $msgOld);
                $divTag.removeClass($divClass);
                $iconTag.addClass("glyphicon-chevron-right");
                $iconTag.removeClass($iconClass + " " + $divClass);
            }, $msgShowTime);
        }

    });
</script>
