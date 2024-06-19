@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
    content = @`
        @if(page.getPageNumber() > 1)
            <a href="${NamedRoutes.postsPath()}/?page=${page.getPageNumber()-1}">Prev</a>
        @endif
        <a href="${NamedRoutes.postsPath()}/?page=${page.getPageNumber()+1}">Next</a>

        @if(page.getPosts().isEmpty())
            <p>нет постов</p>
        @else
            <table>
                @for(var post : page.getPosts())
                    <tr>
                        <td><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()} ${post.getBody()}</a></td>
                    </tr>
                @endfor
            </table>
        @endif
    `
)
