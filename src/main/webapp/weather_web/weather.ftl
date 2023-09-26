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
        <#if weather?has_content>
            <table align="center">
                <tr>
                    <td><b>temperature:</b></td>
                    <td>${weather.temperature} C</td>
                </tr>
                <tr>
                    <td><b>humidity:</b></td>
                    <td>${weather.humidity} %</td>
                </tr>
                <tr>
                    <td><b>precipitation:</b></td>
                    <td>${weather.precipitation}</td>
                </tr>
            </table>
        </#if>
    </#if>
</#macro>
</html>
