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
    
        <table border="1" align="center" style="width:1000px">
            <tr>
                <td >Time</td>
                <td >Saturday</td>
                <td >Sunday</td>
                <td >Monday</td>
                <td >Tuesday</td>
                <td >Wednesday</td>
                <td >Thursday</td>
            </tr>
            <c:forEach var="period" items="${periods}" varStatus="periodIndex">
                <tr>
                    <td>${period.periodSegment}</td>
                    <c:forEach var="obj" items="${objs}" varStatus="objIndex">
                        <td>
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
                                <c:if test="${eventSchedule.dayOfWeek == (objIndex.count) && eventSchedule.room.id == roomObj.id}">
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
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        
        
    </tiles:put>
</tiles:insert>