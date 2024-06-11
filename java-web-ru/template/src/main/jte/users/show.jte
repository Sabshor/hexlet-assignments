@import exercise.dto.users.UserPage
@param UserPage user

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>пользователь</title>
</head>
<body>
<div class="col-lg-8 mx-auto p-4 py-md-5">
    <main>
        <h1>Иныормация о пользователе</h1>
        <p>${user.getUser().getFirstName()}</p>
        <p>${user.getUser().getLastName()}</p>
        <p>${user.getUser().getEmail()}</p>
    </main>
</div>
</body>
</html>

