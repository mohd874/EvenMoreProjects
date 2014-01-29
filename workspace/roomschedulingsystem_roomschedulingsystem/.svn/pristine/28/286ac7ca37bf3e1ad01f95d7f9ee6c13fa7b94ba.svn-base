<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="ae.hct.admc.domain.User"%>
<%@ include file="commonLayout/taglibs.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<tiles:insert page="/commonLayout/commonLayout.jsp" flush="true">
    <tiles:put name="title" type="string"
               value="Room ${roomObj.name} Schedule" />
    <tiles:put name="header" value="/commonLayout/header.jsp" />
    <tiles:put name="footer" value="/commonLayout/footer.jsp" />
    <tiles:put name="content" direct="true">
        <script language="javascript">
            function Clickheretoprint(printId)
            { 
                var disp_setting="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
                disp_setting+="scrollbars=yes,resizable"; 
                var content_vlue = document.getElementById(printId).innerHTML; 
                
                var docprint=window.open("","",disp_setting); 
                docprint.document.open(); 
                docprint.document.write('<html><head><title>Print Preview</title>'); 
                docprint.document.write('<style media=print>.hiddenstuff {display:none; visibility:hidden;}</style>');
                docprint.document.write('<style >.notvisiable {display:none; visibility:hidden;}</style>');
                docprint.document.write('<style >.a4table {width:827px;border-collapse:collapse}</style>');
                docprint.document.write('</head><body><center><br><br>');
                docprint.document.write('<div class=hiddenstuff >');
                docprint.document.write('<input name=print type=button id=print value=Print onclick=window.print(); />&nbsp;&nbsp;&nbsp;');
                docprint.document.write('<input name=print type=button id=close value=Close onclick=window.close(); /><br><br><br>');
                docprint.document.write('</div>');
                docprint.document.write(content_vlue);          
                docprint.document.write('</center></body></html>'); 
                docprint.document.close(); 
                docprint.focus(); 
                //window.close();
            }
        </script>
        <%User user = (User) session.getAttribute("userSession");%>
        <div align="left">
            <input name="print" type="button" id="print" value="Print Preview" onclick="javascript:Clickheretoprint('printDiv')" />
        </div>
        <html:errors />
        <html:form action="roomSchedule">
            <html:hidden property="semesterWeek" value="${semesterWeek}"/>
            <html:hidden property="room" value="${room}"/>
            <html:hidden property="semester" value="${semester}"/>
            <html:hidden property="numberOfWeeksForSemester" value="${numberOfWeeksForSemester}"/>
            
            <jsp:useBean id="next" class="java.util.HashMap"/>
            <jsp:useBean id="prev" class="java.util.HashMap"/>
            <c:set target="${next}" property="action" value="next"/>
            <c:set target="${prev}" property="action" value="prev"/>
            
            <table border="0">
                <td>
                    <html:submit value="<< Prev Week" property="action" disabled="${semesterWeek <= 1}"/>
                </td>
                <td width="75%" align="center">
                    <p><b>Name:</b> ${roomObj.name} <b>Section:</b> ${roomObj.block.name}<br> <b>Short Descreption:</b> ${roomObj.description}</p>
                    <p align="center"> <b>Week:</b> ${semesterWeek}</p>
                </td>
                <td>
                    <html:submit value="Next Week >>" property="action" disabled="${semesterWeek >= numberOfWeeksForSemester}"/>
                </td>
            </table>
            
            <div id="printDiv" >
                <table border="1" class="a4table">
                    <tr>
                        <td >Time</td>
                        <td >Saturday</td>
                        <td >Sunday</td>
                        <td >Monday</td>
                        <td >Tuesday</td>
                        <td >Wednesday</td>
                        <td >Thursday</td>
                    </tr>
                    <jsp:useBean id="isEmpty" class="java.util.HashMap"/>
                    <c:forEach var="period" items="${periods}" varStatus="periodIndex">
                        <tr>
                            <td>${period.periodSegment}</td>
                            <c:forEach var="obj" items="${objs}" varStatus="objIndex">
                                <c:set target="${isEmpty}" property="isEmpty" value="true"/>
                                <c:forEach var="classSchedule" items="${period.classSchedules}">
                                    <c:if test="${classSchedule.dayOfWeek == (objIndex.count)}">
                                        <td id="${classSchedule.reservationStatus}">
                                        <c:set target="${isEmpty}" property="isEmpty" value="false"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${isEmpty['isEmpty'] == 'true'}">
                                    <td>
                                </c:if>
                                    <c:set target="${isEmpty}" property="isEmpty" value="true"/>
                                    <c:forEach var="classSchedule" items="${period.classSchedules}">
                                        <c:if test="${classSchedule.dayOfWeek == (objIndex.count)}">
                                            <div style="width:100%;height:100%" id="${classSchedule.reservationStatus}">
                                                ${classSchedule.course.code}<br>
                                                ${classSchedule.course.title}<br>
                                                ${classSchedule.reserver.loginName}<br>
                                            </div>
                                            <c:set target="${isEmpty}" property="isEmpty" value="false"/>
                                            <c:set target="${isEmpty}" property="classSchedule" value="${classSchedule.id}"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach var="eventSchedule" items="${period.eventSchedules}">
                                        <c:if test="${eventSchedule.dayOfWeek == (objIndex.count) && eventSchedule.room.id == roomObj.id && eventSchedule.semesterWeek == semesterWeek}">
                                            <div>
                                                ${eventSchedule.event.name}<br>
                                                ${eventSchedule.event.eventType}<br>
                                                ${eventSchedule.reserver.loginName}<br>
                                            </div>
                                            <c:set target="${isEmpty}" property="isEmpty" value="none"/>
                                            <%--c:if test="${eventSchedule.reserver.id == userSession.id}">
                                            <c:set target="${isEmpty}" property="isOwner" value="true"/>
                                        </c:if--%>
                                        </c:if>
                                    </c:forEach>
                                    <%
            if (user.hasPermission("Accept", "Decline", "Remove", "Reserve")) {
                                    %>
                                    <c:if test="${isEmpty['isEmpty'] == 'true'}">
                                        <html:checkbox  property="periodDay" value="${period.id}-${objIndex.count}" styleClass="notvisiable"/>
                                    </c:if>
                                    <%
            }
                                    %>
                                    <%
            if (user.hasPermission("Remove")) {
                                    %>
                                    <c:if test="${isEmpty['isEmpty'] == 'false'}">
                                        <html:checkbox  property="roomClassSchedule" value="${isEmpty['classSchedule']}" styleClass="notvisiable"/>
                                    </c:if>
                                    <%
            }
                                    %>
                                    &nbsp;
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <p> 
                
                <%
            if (user.hasPermission("Accept")) {
                %>
                <html:submit value="Accept" property="action"/>
                <%
            }
                %>
                <%
            if (user.hasPermission("Decline")) {
                %>
                <html:submit value="Decline" property="action"/>
                <%
            }
                %>
                <%
            if (user.hasPermission("Remove")) {
                %>
                <html:submit value="Remove" property="action"/>
                <%
            }
                %>
                <html:submit value="Reserve" property="action"/>
                <html:hidden property="reservationWeeks" value="ThisWeek" />
                <%--html:radio property="reservationWeeks" value="ThisWeek"/> Only This Week 
                <html:radio property="reservationWeeks" value="ThisSemester"/> Whole Semester--%>
                <html:select property="selectedCourse" value="id">
                    <%--c:forEach var="c" items="${courses}">
                        <html:option value="${c.id}">${c.fullName}</html:option>
                    </c:forEach--%>
                    <html:optionsCollection name="courses" value="id" label="fullName" />
                </html:select>
            </p>
        </html:form>
    </tiles:put>
</tiles:insert>