<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ include file="commonLayout/taglibs.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<tiles:insert page="/commonLayout/commonLayout.jsp" flush="true">
    <tiles:put name="title" type="string"
               value="Block Management" />
    <tiles:put name="header" value="/commonLayout/header.jsp" />
    <tiles:put name="footer" value="/commonLayout/footer.jsp" />
    <tiles:put name="content" direct="true">
        <table width="70%" border="0">
            <tr>
                <th scope="col" style="width: 110px;">Action</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
            </tr>
            <c:forEach var="block" items="${requestScope.blocks}">
                <tr> 
                    <td>
                        <a href="<c:url value="blockManagement.do?action=edit&blockId=${block.id}"/>">
                            Edit
                        </a> - 
                        <a href="<c:url value="blockManagement.do?action=delete&blockId=${block.id}"/>">
                            Delete
                        </a>
                    </td>
                    <td>
                        ${block.name}
                    </td>
                    <td>${block.description}</td>
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
                    <a href="<c:url value="blockManagement.do?action=add"/>" >
                        Add New block
                    </a>
                </td>
            </tr>
        </table>
    </tiles:put>
</tiles:insert>