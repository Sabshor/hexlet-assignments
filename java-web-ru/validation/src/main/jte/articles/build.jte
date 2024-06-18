@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
content = @`
    @if(page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif

    <form action="/articles" method="post">
        <div>
            <label>
                Наименование
                <input type="text" name="title" value="${page.getTitle()}"/>
            </label>
        </div>
        <div>
            <label for="msg">Содержимое:</label>
            <textarea id="cont" name="content" rows="5" cols="40">${page.getContent()}</textarea>
        </div>

        <input type="submit" value="Сохранить" />
    </form>
`)