package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void show(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();

            var user = UserRepository.find(id).orElseThrow(() -> new NotFoundResponse("User not found"));
            //var userToken = ctx.cookie(String.valueOf(id));
            var userToken = ctx.cookie("utoken");
            if ((userToken == null) || (!StringUtils.equalsIgnoreCase(userToken, user.getToken()))) {
                throw new NotFoundResponse("Token not found");
            }
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        }
        catch (Exception e) {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }

    public static void create(Context ctx) {
        var firstName = ctx.formParamAsClass("firstName", String.class).get();
        var lastName = ctx.formParamAsClass("lastName", String.class).get();
        var email = ctx.formParamAsClass("email", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();
        var token = Security.generateToken();
        var user = new User(firstName.trim(), lastName.trim(), email.trim().toLowerCase(), password, token);
        UserRepository.save(user);
        //ctx.cookie(String.valueOf(user.getId()), token);
        ctx.cookie("utoken", token);
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }
    // END
}
