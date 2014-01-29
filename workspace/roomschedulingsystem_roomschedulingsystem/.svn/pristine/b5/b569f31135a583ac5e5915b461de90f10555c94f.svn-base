<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ include file="commonLayout/taglibs.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<tiles:insert page="/commonLayout/commonLayout.jsp" flush="true">
    <tiles:put name="title" type="string"
               value="User Management" />
    <tiles:put name="header" value="/commonLayout/header.jsp" />
    <tiles:put name="footer" value="/commonLayout/footer.jsp" />
    <tiles:put name="content" direct="true">
        <c:if test="${users == null}">
            <% response.reset(); %>
            <jsp:forward page="userManagement.do" />
        </c:if>
        <table width="70%" border="0">
            <tr>
                <th scope="col" style="width: 110px;">Action</th>
                <th scope="col">Login Name </th>
                <th scope="col">Role </th>
            </tr>
            <c:forEach var="user" items="${requestScope.users}">
                <tr> 
                    <td>
                        <a href="<c:url value="userManagement.do?action=edit&userId=${user.id}"/>">
                            Edit
                        </a> - 
                        <a href="<c:url value="userManagement.do?action=delete&userId=${user.id}"/>">
                            Delete
                        </a>
                    </td>
                    <td>
                        ${user.loginName}
                    </td>
                    <td>${user.role.description}</td>
                </tr>
            </c:forEach>
            <tr> 
                <td colspan="3"/>
            </tr>
            <tr> 
                <td colspan="3"/>
            </tr>
            <tr> 
                <td colspan="3">
                    <a href="<c:url value="userManagement.do?action=add"/>" >
                        Add New User
                    </a>
                </td>
            </tr>
        </table>
    </tiles:put>
</tiles:insert>