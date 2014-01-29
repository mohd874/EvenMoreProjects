<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ include file="/commonLayout/taglibs.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<tiles:insert page="/commonLayout/commonLayout.jsp" flush="true">
    <tiles:put name="title" type="string"
               value="Rooms" />
    <tiles:put name="header" value="/commonLayout/header.jsp" />
    <tiles:put name="footer" value="/commonLayout/footer.jsp" />
    <tiles:put name="content" type="string">
        
        <table width="75%" border="1" class="sortable" id="sortabletable">
            <tr> 
                <td>Request #</td>
                <td>Room</td>
                <td>Block</td>
                <td>Reserver</td>
                <td>Course</td>
                <td>Day Of Week</td>
                <td>Semester Week</td>
                <td>Period</td>
            </tr>
            <c:forEach var="req" items="${requestScope.resevationRequests}" varStatus="index">
                <tr> 
                    <td>
                        ${index.count}
                    </td>
                    <td>
                        <c:url value="roomSchedule.do" var="url">
                            <c:param name="room" value="${req.room.id}"/>
                            <c:param name="semesterWeek" value="${req.semesterWeek}"/>
                            <c:param name="semester" value="${req.semester.id}"/>
                        </c:url>
                        <a href="${url}">${req.room.name}</a>
                    </td>
                    <td>${req.room.block.name}</td>
                    <td>${req.reserver.loginName}</td>
                    <td>${req.course.code}</td>
                    <td>${req.dayOfWeekName}</td>
                    <td>${req.semesterWeek}</td>
                    <td>${req.period.periodSegment}</td>
                </tr>
            </c:forEach>
        </table>
    </tiles:put>
</tiles:insert>