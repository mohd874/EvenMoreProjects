<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ include file="commonLayout/taglibs.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<tiles:insert page="/commonLayout/commonLayout.jsp" flush="true">
    <tiles:put name="title" type="string" value="Semesters" />
    <tiles:put name="header" value="/commonLayout/header.jsp" />
    <tiles:put name="footer" value="/commonLayout/footer.jsp" />
    <tiles:put name="content" type="string">
        <html:errors />
        <html:form action="report">
            <table width="75%" border="0">
                <tr>
                  <th rowspan="2"><div align="right">Filter:</div></th>
                    <td>Semester: 
                        <html:select property="selectedSemester" value="id">
                            <html:option value="-1" >All</html:option>
                            <html:optionsCollection name="semesters" value="id" label="fullName" />
                        </html:select>                    </td>
                    <td>Room Code: <html:text property="roomName" /></td>
                    <td rowspan="2">
                        <html:submit value="Filter" property="action"/>                    </td>
                </tr>
                <tr>
                  <td>Block:
                    <html:select property="selectedBlock" value="id">
                        <html:option value="-1" >All</html:option>
                        <html:optionsCollection name="blocks" value="id" label="name" />
                      </html:select>                  </td>
                  <td>Room Type:
                    <html:select property="roomType" value="id">
                        <html:option value="-1" >All</html:option>
                        <html:optionsCollection name="roomTypes" value="id" label="type" />
                      </html:select>                  </td>
                </tr>
                <tr>
                  <th rowspan="3"><div align="right">Report:</div></th>
                    <td><html:checkbox property="showClass">Show Classes</html:checkbox></td>
                    <td><html:checkbox property="showEvent">Show Events</html:checkbox></td>
                    <td rowspan="2"><html:submit value="Generate" property="action"/></td>					
                </tr>
                <tr>
                  <td><html:checkbox property="showAccepted">Show Accepted</html:checkbox></td>
                  <td><html:checkbox property="showPending">Show Pedning</html:checkbox></td>
                </tr>
                <tr>
                  <td>Week:
                     <html:text property="selectedWeek" />                
                  </td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
            </table>
            <table width="75%" border="1">
                <tr> 
                    <th>Select</th>
                    <th>Name</th>
                    <th>Section</th>
                    <th>Type</th>
                    <th>Short Description</th>
                </tr>
                <c:forEach var="room" items="${requestScope.rooms}">
                    <tr>
                        <td>
                            <html:radio property="roomId" value="${room.id}"/>
                        </td>
                        <td>${room.name}</td>
                        <td>${room.block.name}</td>
                        <td>${room.type.type}</td>
                        <td>${room.description}</td>
                    </tr>
                </c:forEach>
            </table>
        </html:form>
    </tiles:put>
</tiles:insert>