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
        <html:form action="course">
            <html:hidden property="courseId" />
            <table width="40%" border="0">
                <tr>
                    <th colspan="2" scope="row">Course Information </th>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Code:</div></th>
                    <td width="50%"><html:text property="code"/></td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Title:</div></th>
                    <td width="50%"><html:text property="title"/></td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Description:</div></th>
                    <td width="50%"><html:textarea property="description" rows="4"></html:textarea></td>
                </tr>
                <tr>
                    <th colspan="2" scope="row"><html:submit value="submit" property="action"/></th>
                </tr>
            </table>
        </html:form>
    </tiles:put>
</tiles:insert>