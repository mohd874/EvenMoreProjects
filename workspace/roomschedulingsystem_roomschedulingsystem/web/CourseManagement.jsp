<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ include file="commonLayout/taglibs.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<tiles:insert page="/commonLayout/commonLayout.jsp" flush="true">
    <tiles:put name="title" type="string"
               value="Course Management" />
    <tiles:put name="header" value="/commonLayout/header.jsp" />
    <tiles:put name="footer" value="/commonLayout/footer.jsp" />
    <tiles:put name="content" direct="true">
        <table width="80%" border="0">
            <tr>
                <th scope="col" style="width: 110px;">Action</th>
                <th scope="col">Code </th>
                <th scope="col">Title </th>
                <th scope="col">Description </th>
            </tr>
            <c:forEach var="course" items="${requestScope.courses}">
                <tr> 
                    <td>
                        <a href="<c:url value="courseManagement.do?action=edit&courseId=${course.id}"/>">
                            Edit
                        </a> - 
                        <a href="<c:url value="courseManagement.do?action=delete&courseId=${course.id}"/>">
                            Delete
                        </a>
                    </td>
                    <td>
                        ${course.code}
                    </td>
                    <td>${course.title}</td>
                    <td>${course.description}</td>
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
                    <a href="<c:url value="courseManagement.do?action=add"/>" >
                        Add New Course
                    </a>
                </td>
            </tr>
        </table>
    </tiles:put>
</tiles:insert>