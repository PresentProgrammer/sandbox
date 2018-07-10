<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Trying out jQuery's Ajax</title>
    <style>
        .fieldName {
            text-align: right;
        }
        .submit {
            text-align: right;
        }
    </style>
</head>
<body>
<form>
    <table>
        <tr>
            <td class="fieldName">User name:</td>
            <td><input type="text" id="userName"/></td>
            <td><a id="userNameAvailability" href="#">Check availability</a></td>
        </tr>
        <tr>
            <td class="fieldName">Email:</td>
            <td><input type="text" id="email"/></td>
            <td><a id="emailAvailability" href="#">Check availability</a></td>
        </tr>
        <tr>
            <td colspan="2" class="submit"><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script>
    function checkUserNameAvailability() {
        var enteredUserName = $("#userName").val();
        if (enteredUserName) {
            var params = {
                userName: enteredUserName
            };
            $.getJSON("http://localhost:8080/rest/availability/user-name", params).done(handleResponse);
        } else {
            alert("Please enter user name!");
        }
    }

    function checkEmailAvailability() {
        var enteredEmail = $("#email").val();
        if (enteredEmail) {
            $.getJSON("http://localhost:8080/rest/availability/email/" + enteredEmail).done(handleResponse);
        } else {
            alert("Please enter email!");
        }
    }

    function handleResponse(json) {
        if (json.available) {
            alert(json.searchTerm + " is available");
        } else {
            alert("Sorry, but entered " + json.searchTerm + " is not available.");
        }
    }

    $("#userNameAvailability").on("click", checkUserNameAvailability);
    $("#emailAvailability").on("click", checkEmailAvailability);
</script>
</html>