<html lang="en">
<#include "base.ftl">
<#macro title>city</#macro>
<#macro content>
    enter your city, <b>${username!'baby'}</b>
    <br>
    <br>
    <form action="city" method="post">
        <table align="center">
            <tr>
                <td><b>city:</b></td>
                <td><input type="text" name="city" style="font-family: 'Courier New', cursive"/></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="know your weather" style="font-family: 'Courier New',cursive; font-size: 15px">
    </form>
</#macro>
</html>