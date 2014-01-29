<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ include file="commonLayout/taglibs.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<tiles:insert page="/commonLayout/commonLayout.jsp" flush="true">
    <tiles:put name="title" type="string" value="Rooms" />
    <tiles:put name="header" value="/commonLayout/header.jsp" />
    <tiles:put name="footer" value="/commonLayout/footer.jsp" />
    <tiles:put name="content" type="string">
        <html:form action="roomsList">
            <html:hidden property="semester" />
            <table width="75%" border="0">
                <tr>
                    <th scope="col">Semester</th>
                    <th scope="col">Code</th>
                    <th scope="col">Section</th>
                    <th scope="col">Type</th>
                </tr>
                <tr>
                    <td>
                        <html:select property="selectedSemester" value="id">
                            <html:option value="-1" >All</html:option>
                            <html:optionsCollection name="semesters" value="id" label="fullName" />
                        </html:select>
                    </td>
                    <td><html:text property="roomName" /></td>
                    <td>
                        <html:select property="block" value="id">
                            <html:option value="-1" >All</html:option>
                            <html:optionsCollection name="blocks" value="id" label="name" />
                        </html:select>
                    </td>
                    <td>
                        <html:select property="roomType" value="id">
                            <html:option value="-1" >All</html:option>
                            <html:optionsCollection name="roomTypes" value="id" label="type" />
                        </html:select>
                    </td>
                    <td>
                        <html:submit value="Filter" property="action"/>
                    </td>
                </tr>
            </table>
        </html:form>
        <br /><br />
        <table width="100%" border="1">
            <tr> 
                <td>Name</td>
                <td>Section</td>
                <td>Type</td>
                <td>Short Description</td>
                <td>Go To</td>
            </tr>
            <c:forEach var="room" items="${requestScope.rooms}">
                <tr> 
                    <td>
                        ${room.name}
                    </td>
                    <td>${room.block.name}</td>
                    <td>${room.type.type}</td>
                    <td>${room.description}</td>
                    <td>
                        <c:url var="roomUrl" value="/roomSchedule.do">
                            <c:param name="semester" value="${semester}"/>
                            <c:param name="room" value="${room.id}"/>
                        </c:url>
                        <c:url var="eventUrl" value="/eventSchedule.do">
                            <c:param name="semester" value="${semester}"/>
                            <c:param name="room" value="${room.id}"/>
                        </c:url>
                        <a href="${roomUrl}">Courses</a> - 
                        <a href="${eventUrl}">Events</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </tiles:put>
</tiles:insert>