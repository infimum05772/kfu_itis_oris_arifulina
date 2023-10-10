<html lang="en">
<#include "base.ftl">
<#macro title>exception details</#macro>
<#macro content>
    <h3>exception details</h3>
    <strong>Request uri: </strong>${uri}<br>
    <strong>Status code: </strong>${statusCode}<br>
    <#if message??><strong>Message: </strong>${message}<br></#if>
</#macro>
</html>
