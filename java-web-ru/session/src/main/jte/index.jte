@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
    content = @`
            <div class="mx-auto p-4 py-md-5">
                <main>
                    <h1>страница логина</h1>
        
                    <div class="mb-3">
                        @if (page.getName() == null || page.getName() == "")
                            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
                        @else
                            <form action="${NamedRoutes.logoutPath()}" method="post">
                                <div class="mb-3">
                                    Добро пожаловать, ${page.getName().toString()}.
                                </div>
                                <button type="submit" name="your_name" value="your_value" class="btn-link">Выйти</button>
                            </form>
                        @endif
                    </div>
                </main>
            </div>
    `
)