<html lang="en">
<#include "base.ftl">
<#macro title>main page</#macro>
<#macro script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        function validPhone() {
            let re = /^\+?[0-9\s\-\(\)]+$/;
            let phone = $("#phone-input").val();
            let valid = re.test(phone);
            return valid;
        }
        $(function () {
            $("#master").change(function () {
                let master = $("#master").val();
                $.get("/ajax/services?master=" + master, function (response) {
                    if (!response) {
                        $("#service").attr("disabled", "")
                    } else {
                        $("#service").removeAttr("disabled");
                    }
                    $("#service").empty();
                    $.each(response, function (index, service) {
                        let option = $("<option></option>").text(service)
                        $("#service").append(option)
                    })
                })
            })
        })
        $(function () {
            $("#phone-input").on(
                "input", function () {
                    if ($("#phone-input").val().length >= 10) {
                        if (!validPhone()) {
                            $("#message").removeAttr("hidden").text("invalid phone number")
                        } else {
                            $("#message").attr("hidden", "")
                        }
                    }
                })
        })
        $(document).on(
            "click", "#ajax-button", function () {
                let phone = $("#phone-input").val();
                let master = $("#master").val();
                let service = $("#service").val();
                let date = $("#date-input").val();
                let time = $("#time-input").val();
                if (!phone || !master || !service || !date || !time) {
                    $("#message").removeAttr("hidden").text("fill out all fields")
                } else if (!validPhone()) {
                    $("#message").removeAttr("hidden").text("invalid phone number")
                } else {
                    $("#message").attr("hidden", "")
                    $.post("/ajax/appointment", {
                            master: master,
                            service: service,
                            date: date,
                            time: time,
                            phone: phone
                        }, function (response) {
                            alert(response)
                        }
                    )
                }
            }
        )
    </script>
</#macro>
<#macro content>
    hello, welcome to our beauty salon!
    <br>
    <br>
    <form>
        <#if masters?has_content>
            <select class="selector" id="master" name="master">
                <option selected disabled value="">choose your master</option>
                <#list masters as m>
                    <option id="master-option" class="master-option">${m.name} ${m.surname}</option>
                </#list>
            </select>
        </#if>
        <select class="selector" id="service" name="service" disabled="disabled">
            <option selected disabled value="">choose service</option>
        </select>
        <br>
        <br>
        <table align="center">
            <tr>
                <td><b>choose date:</b></td>
                <td><input type="date" name="date" id="date-input" required
                           style="font-family: 'Courier New', cursive"/></td>
            </tr>
            <tr>
                <td><b>choose time:</b></td>
                <td><input type="time" name="time" id="time-input" required
                           style="font-family: 'Courier New', cursive"/></td>
            </tr>
            <tr>
                <td><b>your phone number:</b></td>
                <td><input type="tel" name="phone" id="phone-input" required maxlength="15"
                           style="font-family: 'Courier New', cursive"/></td>
            </tr>
        </table>
        <div id="message" style="color: chocolate; font-size: 14px">
        </div>
        <br>
        <input type="button" value="submit" id="ajax-button"
               style="font-family: 'Courier New',cursive; font-size: 15px">
    </form>
</#macro>
</html>