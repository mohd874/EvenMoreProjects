<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page errorPage="../error.jsp" %>
<%@ page import="ae.hct.admc.domain.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="taglibs.jspf" %>
<html>
    <head>
        <title>
            <tiles:getAsString name="title" ignore="true"/>
        </title>
        <link href="<c:url value="/css/site.css"/>" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="<c:url value="/javascript/sortable.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/javascript/datetimepicker.js"/>"></script>
    </head>
    <body>
        <% User user = (User) session.getAttribute("userSession");%>
        
        <!--Presented by developerWorks, your source for great tutorials ibm.com/developerWorks
        Master the Tiles framework Page 13 of 44-->
        <div id="page" align="center">
            <div id="header">
                <div id="companyname" align="left">
                    <%--tiles:insert attribute="header" ignore="true">
                        <tiles:put name="title"
                                   beanName="title" beanScope="tile"/>
                    </tiles:insert--%>
                </div>
                <div align="right" class="links_menu" id="menu"> 
                    <%
            if (user != null && user.hasPermission("AccessReservation")) {
                    %>
                    <a href="<c:url value="/reservations.do" />">Requests</a> | 
                    <%
            }
                    %>
                    <c:if test="${sessionScope.userSession != null}">
                        <a href="<c:url value="/roomsList.do" />">Rooms</a> | 
                    </c:if>
                    <%
            if (user != null && user.hasPermission("AccessAdministration")) {
                    %>
                    <a href="<c:url value="/Administration.jsp" />">Administration</a> | 
                    <%
            }
                    %>
                    <c:if test="${sessionScope.userSession != null}">
                        <a href="<c:url value="login.do?action=logout"/>">Welcome ${sessionScope.userSession.loginName} (Logout)</a> 
                    </c:if>
                    <c:if test="${sessionScope.userSession == null}">
                        <a href="<c:url value="login.jsp"/>">Login</a> 
                    </c:if>
                </div>
            </div>
            <br />
            <div id="content">
                <%--div id="leftpanel">
                    <!--div class="table_top">
                        <div align="center"><span class="title_panel">Actions</span> </div>
                    </div>
                    <div class="table_content">
                        <div class="table_text">
                            <span class="news_date">October 16, 2006</span> <br />
                            <span class="news_text">Curabitur arcu tellus, suscipit in, aliquam eget, ultricies id, sapien. Nam est.</span><br />
                            <span class="news_more"><a href="#">Read More</a></span><br /><br />
                            <span class="news_date">September 27, 2006</span> <br />
                            <span class="news_text">Curabitur arcu tellus, suscipit in, aliquam eget, ultricies id, sapien. Nam est.</span><br />
                            <span class="news_more"><a href="www.google.com">Add Room</a></span><br>
                            <span class="news_more"><a href="www.google.com">Check Room</a></span><br>
                            <span class="news_more"><a href="www.google.com">Delete Room</a></span><br>
                            <span class="news_more"><a href="www.google.com">Check requests</a></span><br>
                        </div>
                    </div>
                    <div class="table_bottom">
                        <img src="images/table_bottom.jpg" width="204" height="23" border="0" alt="" />
                    </div-->
                    <br />
                    <div class="table_top">
                        <span class="title_panel">Links</span>
                    </div>
                    <div class="table_content">
                        <div class="table_text">
                            <span class="news_more"><a href="https://hct-portal.hct.ac.ae/">HCT Portal </a></span><br />
                            <span class="news_more"><a href="http://www.admc.hct.ac.ae/internet/">ADMC Main Page </a></span><br />
                            <span class="news_more"><a href="http://www.admc.hct.ac.ae/library/">ADMC Library</a></span><br />
                            <span class="news_more"><a href="http://www.hct.ac.ae/">HCT Main Page</a></span><br />
                            <span class="news_more"><a href="http://www.hotmail.com">Hotmail</a></span><br />
                        </div>
                    </div>
                    <div class="table_bottom">
                        <img src="images/table_bottom.jpg" width="204" height="23" border="0" alt="" />
                    </div>
                    <br />
                </div--%>
                <div id="contenttext">
                    <%
                    if (user != null) {
                    %>
                        <tiles:insert attribute="content"/>
                   <%
                    }else{
                    %>
                        <h2 style="color:red">You need to login in order to continue</h2>
                  <%
                    }
                  %>
                </div>
                <div class="footer">
                    <br />
                    <%
            if (user != null && user.hasPermission("AccessReservation")) {
                    %>
                    <a href="<c:url value="/reservations.do" />">Requests</a> | 
                    <%
            }
                    %>
                    <c:if test="${sessionScope.userSession != null}">
                        <a href="<c:url value="/roomsList.do" />">Rooms</a> | 
                    </c:if>
                    <%
            if (user != null && user.hasPermission("AccessAdministration")) {
                    %>
                    <a href="<c:url value="/Administration.jsp" />">Administration</a> | 
                    <%
            }
                    %>
                    <a href="<c:url value="/" />">ADMC Room Booking System</a> |
                      Web Site Created 2008.
                </div>
            </div>
        </div>
    </body>
</html>