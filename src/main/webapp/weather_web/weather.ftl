<html lang="en">
<#include "base.ftl">
<#macro title>weather</#macro>
<#macro content>
    <#if err??>
        <div style="color: chocolate">${err}</div>
    <#else>
        weather in <b>${city}</b> right now!
        <br>
        <br>
        <table align="center">
            <tr>
                <td><b>temperature:</b></td>
                <td>${temperature}</td>
            </tr>
            <tr>
                <td><b>humidity:</b></td>
                <td>${humidity}</td>
            </tr>
            <tr>
                <td><b>precipitation:</b></td>
                <td>${precipitation}</td>
            </tr>
        </table>
    </#if>
</#macro>
</html>