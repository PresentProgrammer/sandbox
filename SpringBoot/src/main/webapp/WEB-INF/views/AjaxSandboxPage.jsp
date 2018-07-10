<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>AjaxSandboxPage</title>
</head>
<body>
<!--suppress JSUnresolvedLibraryURL -->
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script>
    function handleResponse(obj) {
        document.write(obj.message + "<br/>");
    }

    function logSuccess() {
        console.log("Ajax request completed");
    }

    function somethingWentWrong() {
        console.error("Ajax request failed");
    }

    var params = {
        userName: "PresentProgrammer"
    };
    $.getJSON("http://localhost:8080/rest/simple-ajax-response", params)
        .done(logSuccess)
        .done(handleResponse)
        .fail(somethingWentWrong);
    console.log("Right after getJSON");
</script>
</body>
</html>