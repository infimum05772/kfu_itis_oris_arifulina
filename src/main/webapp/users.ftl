<html lang="en">
<#include "base.ftl">
<#macro title>Users</#macro>
<#macro content>
    Hello, i love you,
    <br>
    <#if users?has_content>
        <#list users as u>
            ${u.firstname} ${u.lastname}
            <br>
        </#list>
    </#if>
</#macro>
</html>