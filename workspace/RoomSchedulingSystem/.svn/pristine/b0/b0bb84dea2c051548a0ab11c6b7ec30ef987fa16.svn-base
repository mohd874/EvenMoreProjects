<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page errorPage="../error.jsp" %>
<%@ page import="ae.hct.admc.domain.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="commonLayout/taglibs.jspf" %>
<html>
    <head>
        <title>
            Login
        </title>
        <link href="<c:url value="/css/site.css"/>" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="<c:url value="/javascript/sortable.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/javascript/datetimepicker.js"/>"></script>
    </head>
    <body>
        <% session.setAttribute("userSession", null); %>
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
                    <a href="<c:url value="login.jsp"/>">Login</a> 
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
                    <html:form action="login">
                        <div align="center" style="padding-top:25%;height:200px;background-image:url(images/hct-logo.gif); background-repeat: no-repeat; background-position: center center">
                            <table width="300" border="0" align="center">
                                <tr>
                                    <td colspan="2">
                                        <html:errors/>
                                    </td>
                                </tr>
                                <tr> 
                                    <td width="50%"><bean:message key="login.name" /></td>
                                    <td width="50%"><html:text property="name" /></td>
                                </tr>
                                <tr> 
                                    <td width="50%"><bean:message key="login.password" /></td>
                                    <td width="50%"><html:password property="password" /></td>
                                </tr>
                                <tr align="center"> 
                                    <td colspan="2"><html:submit value="Login" />
                                    <!--a href="OClassTable.htm">Officer</a> <a href="SClassTable.htm">Staff</a--></td>
                                </tr>
                        </table></div>
                    </html:form>
                </div>
                <div class="footer">
                    <a href="<c:url value="/" />">ADMC Room Booking System</a> |
                      Web Site Created 2008.
                </div>
            </div>
        </div>
    </body>
</html>