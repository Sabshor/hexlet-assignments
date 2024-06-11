@import exercise.dto.users.UsersPage
@param UsersPage page

<html>
<head>
    <title>Хекслет</title>
</head>
<body>
<h1>Список пользователей</h1>
@if(page.getUsers().isEmpty())
    <p>нет пользателей</p>
@else
    <table>
        @for(var user : page.getUsers())
            <tr>
                <td><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></td>
                <td>${user.getEmail()}</td>
            </tr>
        @endfor
    </table>
@endif
</body>
</html>