@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(
content = @`
        <a href="${NamedRoutes.postsPath()}">Все посты</a>
        <h1>Информация о посте</h1>
        <p>${page.getPost().getName()}</p>
        <p>${page.getPost().getBody()}</p>
    `
)
