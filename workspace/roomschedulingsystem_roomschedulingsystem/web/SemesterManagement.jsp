<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ include file="commonLayout/taglibs.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<tiles:insert page="/commonLayout/commonLayout.jsp" flush="true">
    <tiles:put name="title" type="string"
               value="Semester Management" />
    <tiles:put name="header" value="/commonLayout/header.jsp" />
    <tiles:put name="footer" value="/commonLayout/footer.jsp" />
    <tiles:put name="content" direct="true">
        <table width="80%" border="0">
            <tr>
                <th scope="col" style="width: 110px;">Action</th>
                <th scope="col">Semester No </th>
                <th scope="col">Date Form </th>
                <th scope="col">Date To </th>
            </tr>
            <c:forEach var="semester" items="${requestScope.semesters}">
                <tr> 
                    <td>
                        <a href="<c:url value="semesterManagement.do?action=edit&semesterId=${semester.id}"/>">
                            Edit
                        </a> - 
                        <a href="<c:url value="semesterManagement.do?action=delete&semesterId=${semester.id}"/>">
                            Delete
                        </a>
                    </td>
                    <td>
                        ${semester.semesterNo}
                    </td>
                    <td>${semester.formatedDateFrom}</td>
                    <td>${semester.formatedDateTo}</td>
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
                    <a href="<c:url value="semesterManagement.do?action=add"/>" >
                        Add New Semester
                    </a>
                </td>
            </tr>
        </table>
    </tiles:put>
</tiles:insert>