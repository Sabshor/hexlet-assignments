package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {

        ctx.render("index.jte", model("page", new MainPage(ctx.sessionAttribute("currentUser"))));
    }
    public static void build(Context ctx) {
        //ctx.render("build.jte");
        ctx.render("build.jte", model("page", new LoginPage("", null)));
    }
    public static void create(Context ctx) {
        var name = ctx.formParamAsClass("name", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();

        User user = UsersRepository.findByName(name.trim());

        if (user == null) {
            var page = new LoginPage(name.trim(), "Wrong username or password.");
            ctx.render("build.jte", model("page", page));
            return;
        }

        if (!user.getPassword().equals(encrypt(password))) {
            var page = new LoginPage(name.trim(), "Wrong username or password.");
            ctx.render("build.jte", model("page", page));
            return;
        }

        ctx.sessionAttribute("currentUser", String.valueOf(user.getName()));
        //var mainPage = new MainPage(ctx.sessionAttribute("currentUser"));
        //ctx.render("index.jte", model("page", mainPage));
        ctx.redirect(NamedRoutes.rootPath());
    }
    public static void delete(Context ctx) {
        ctx.sessionAttribute("currentUser", "");
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
