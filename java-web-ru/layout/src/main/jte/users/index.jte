@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
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
`,
footer = @`
    <p>footer: GIT Profile</p>
`
)