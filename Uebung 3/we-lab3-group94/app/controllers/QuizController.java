package controllers;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import models.PlayQuizFactory;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index;

import java.util.List;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class QuizController extends Security.Authenticator {

    public static final int NUM_ROUNDS = 5;
    public static final int NUM_QUESTIONS = 3;

    private static List<Category> categories;

    @Override
    public String getUsername(Context context) {
        System.out.println(context.session().get("username"));
        return context.session().get("username");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.authentication());

    }

    @Security.Authenticated(QuizController.class)
    public static Result index() {
        if(categories == null) {
            System.out.println("init adsflaäsdflasädflaä#sdflasä#dflasä#öd");
            QuizFactory factory = PlayQuizFactory.init();
            QuestionDataProvider provider = factory.createQuestionDataProvider();
            categories = provider.loadCategoryData();
        }
        return ok(index.render());
    }
}
