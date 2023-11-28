<html lang="en">
<#include "base.ftl">
<#macro title>city</#macro>
<#macro script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).on(
            "click", "#ajax-button", function () {
                let city = $("#ajax-input").val();
                $.get("/ajax/weather?city=" + city, function (response) {
                        $("#ajax-response").text(response)
                    }
                )
            }
        )
    </script>
</#macro>
<#macro content>
    enter your city, <b>${username!'baby'}</b>
    <br>
    <br>
    <form>
        <table align="center">
            <tr>
                <td><b>city:</b></td>
                <td><input type="text" name="city" id="ajax-input" style="font-family: 'Courier New', cursive"/></td>
            </tr>
        </table>
        <br>
        <input type="button" value="know your weather" id="ajax-button" style="font-family: 'Courier New',cursive; font-size: 15px">
    </form>
    <br>
    <div id="ajax-response">
    </div>
</#macro>
</html>
