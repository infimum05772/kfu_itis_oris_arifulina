<html lang="en">
<#include "base.ftl">
<#macro title>login page</#macro>
<#macro content>
    please, introduce yourself ;)
    <br>
    <br>
    <form action="wlogin" method="post">
        <table align="center">
            <tr>
                <td><b>login:</b></td>
                <td><input type="text" name="login" style="font-family: 'Courier New', cursive"/></td>
            </tr>
            <tr>
                <td><b>password:</b></td>
                <td><input type="password" name="password"
                           style="font-family: 'Courier New', cursive"/></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="press me, honey" style="font-family: 'Courier New',cursive; font-size: 15px">
    </form>
</#macro>
</html>
