<html lang="en">
<#include "base.ftl">
<#macro title>Users</#macro>
<#macro content>
    Hello, i love you,
    <br>
    <#if users??>
        <#list users as u>
            ${u}
            <br>
        </#list>
    </#if>
</#macro>
</html>