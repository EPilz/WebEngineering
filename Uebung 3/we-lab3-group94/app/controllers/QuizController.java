package controllers;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class QuizController extends Security.Authenticator {

    @Override
    public String getUsername(Context context) {
        return context.session().get("userName");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.authentication());
    }

    @Security.Authenticated(QuizController.class)
    public static Result index() {
        return ok(index.render());
    }
}
