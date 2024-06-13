@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(content = @`
    <div class="col-lg-8 mx-auto p-4 py-md-5">
        <h1>Иныормация о пользователе</h1>
        <p>${page.getUser().getFirstName()}</p>
        <p>${page.getUser().getLastName()}</p>
        <p>${page.getUser().getEmail()}</p>
    </div>
`)
