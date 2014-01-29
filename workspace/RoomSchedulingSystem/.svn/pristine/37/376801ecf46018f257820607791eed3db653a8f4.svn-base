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
        <html:errors />
        <html:form action="room">
            <html:hidden property="roomId"/>
            <table width="40%" border="0" align="center">
                <tr>
                    <th colspan="2" scope="row">Room Information </th>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Code:</div></th>
                    <td width="50%"><html:text property="name" /></td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Type:</div></th>
                    <td width="50%">
                        <html:select property="selectedType" value="id">
                            <html:optionsCollection name="types" value="id" label="type" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Capacity:</div></th>
                    <td width="50%"><html:text property="capacity" /></td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Block:</div></th>
                    <td width="50%">
                        <html:select property="selectedBlock">
                            <html:optionsCollection name="blocks" value="id" label="name" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <th width="50%" scope="row"><div align="right">Description:</div></th>
                    <td width="50%"><html:textarea property="description" ></html:textarea></td>
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
    