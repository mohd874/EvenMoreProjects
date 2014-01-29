<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ include file="commonLayout/taglibs.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<tiles:insert page="/commonLayout/commonLayout.jsp" flush="true">
    <tiles:put name="title" type="string"
               value="Event Management" />
    <tiles:put name="header" value="/commonLayout/header.jsp" />
    <tiles:put name="footer" value="/commonLayout/footer.jsp" />
    <tiles:put name="content" direct="true">
        <table width="90%" border="0">
            <tr>
                <th scope="col" style="width: 110px;">Action</th>
                <th scope="col">Name </th>
                <th scope="col">Type </th>
                <th scope="col">No. Of Attendees </th>
                <th scope="col">Description</th>
            </tr>
            <c:forEach var="event" items="${requestScope.events}">
                <tr> 
                    <td>
                        <a href="<c:url value="eventManagement.do?action=edit&eventId=${event.id}"/>">
                            Edit
                        </a> - 
                        <a href="<c:url value="eventManagement.do?action=delete&eventId=${event.id}"/>">
                            Delete
                        </a>
                    </td>
                    <td>
                        ${event.name}
                    </td>
                    <td>${event.eventType}</td>
                    <td>
                        <div align="center">
                            ${event.numberOfAttendees}
                        </div>
                    </td>
                    <td>${event.description}</td>
                </tr>
            </c:forEach>
            <tr> 
                <td colspan="4"/>
            </tr>
            <tr> 
                <td colspan="4"/>
            </tr>
            <tr> 
                <td colspan="4">
                    <a href="<c:url value="eventManagement.do?action=add"/>" >
                        Add New Event
                    </a>
                </td>
            </tr>
        </table>
    </tiles:put>
</tiles:insert>