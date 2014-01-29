<html:form action="login">
    <table width="300" border="0" align="center">
        <tr> 
            <td width="17%"><bean:message key="login.name" /></td>
            <td width="50%"><html:text property="name" /></td>
        </tr>
        <tr> 
            <td><bean:message key="login.password" /></td>
            <td width="50%"><html:text property="password" /></td>
        </tr>
        <tr align="center"> 
            <td colspan="2"><html:submit value="Login" />
            <!--a href="OClassTable.htm">Officer</a> <a href="SClassTable.htm">Staff</a--></td>
        </tr>
    </table>
</html:form>