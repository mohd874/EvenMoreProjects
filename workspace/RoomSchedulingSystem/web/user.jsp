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
        <html:form action="user">
            <html:hidden property="userId" />
            <table width="40%" border="0">
                <tr>
                    <th colspan="2" scope="row">User Information </th>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Login Name: </div></th>
                    <td width="50%">
                        <html:text property="loginName" />
                    </td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Password: </div></th>
                    <td width="50%">
                        <html:password property="password" />
                    </td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Confirm Password: </div></th>
                    <td width="50%">
                        <html:password property="cpassword" />
                    </td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Role: </div></th>
                    <td width="50%">
                        <html:select property="selectedRole">
                            <html:option value="0" >Please Select...</html:option>
                            <c:forEach var="role" items="${roles}">
                                <html:option value="${role.id}" >${role.description}</html:option>
                            </c:forEach>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <th colspan="2" scope="row">
                        <html:submit value="submit" property="action"/>
                    </th>
                </tr>
            </table>
        </html:form>
    </tiles:put>
</tiles:insert>