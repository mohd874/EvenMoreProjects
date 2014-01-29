<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ include file="commonLayout/taglibs.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<tiles:insert page="/commonLayout/commonLayout.jsp" flush="true">
    <tiles:put name="title" type="string"
               value="Room Management" />
    <tiles:put name="header" value="/commonLayout/header.jsp" />
    <tiles:put name="footer" value="/commonLayout/footer.jsp" />
    <tiles:put name="content" direct="true">
        <table width="70%" border="0">
            <tr>
                <th scope="col" style="width: 110px;">Action</th>
                <th scope="col">Name</th>
                <th scope="col">Section</th>
                <th scope="col">Type</th>
                <th scope="col">Description</th>
            </tr>
            <c:forEach var="room" items="${requestScope.rooms}">
                <tr> 
                    <td>
                        <a href="<c:url value="roomManagement.do?action=edit&roomId=${room.id}"/>">
                            Edit
                        </a> - 
                        <a href="<c:url value="roomManagement.do?action=delete&roomId=${room.id}"/>">
                            Delete
                        </a>
                    </td>
                    <td>
                        ${room.name}
                    </td>
                    <td>${room.block.name}</td>
                    <td>${room.type.type}</td>
                    <td>${room.description}</td>
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
                    <a href="<c:url value="roomManagement.do?action=add"/>" >
                        Add New Room
                    </a>
                </td>
            </tr>
        </table>
    </tiles:put>
</tiles:insert>