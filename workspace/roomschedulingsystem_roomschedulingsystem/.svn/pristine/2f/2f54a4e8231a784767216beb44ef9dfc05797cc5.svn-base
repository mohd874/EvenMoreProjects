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
        <html:form action="semester">
            <html:hidden property="semesterId" />
            <table width="60%" border="0">
                <tr>
                    <th colspan="2" scope="row">Semester Information </th>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Semester No: </div></th>
                    <td width="50%">
                        <html:select property="semesterNo">
                            <html:option value="1" >1</html:option>
                            <html:option value="2" >2</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Date From: </div></th>
                    <td width="50%"><html:text property="dateFrom" styleId="dateFrom" />
                        <a href="javascript:NewCal('dateFrom','ddmmyyyy',false,24)">
                        <img src="<c:url value="/images/cal.gif"/>" width="16" height="16" border="0" alt="Pick a date"></a>
                    </td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Date To: </div></th>
                    <td width="50%"><html:text property="dateTo" styleId="dateTo" />
                        <a href="javascript:NewCal('dateTo','ddmmyyyy',false,24)">
                        <img src="<c:url value="/images/cal.gif"/>" width="16" height="16" border="0" alt="Pick a date"></a>
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