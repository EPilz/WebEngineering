package controllers;

import at.ac.tuwien.big.we14.lab2.api.*;
import models.PlayQuizFactory;
import models.SimpleQuizGame;
import models.SimpleUser;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index;
import views.html.quiz;

import java.util.List;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class QuizController extends Security.Authenticator {

    public static final int NUM_ROUNDS = 5;
    public static final int NUM_QUESTIONS = 3;

    //private static List<Category> categories;

    @Override
    public String getUsername(Context context) {
        return context.session().get("username");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.authentication());

    }

    @Security.Authenticated(QuizController.class)
    public static Result startGame() {
        QuizFactory quizFactory = PlayQuizFactory.init();
        QuizGame quizGame = quizFactory.createQuizGame();
        User u = new SimpleUser();
        u.setName("PPPP 1");
        User u1 = new SimpleUser();
        u1.setName("PPPP 1");

        ((SimpleQuizGame) quizGame).addPlayer(u);
        ((SimpleQuizGame) quizGame).addPlayer(u1);

        quizGame.startNewRound();

        return ok(quiz.render((SimpleQuizGame)quizGame));
    }

    @Security.Authenticated(QuizController.class)
    public static Result quiz() {
        QuizFactory quizFactory = PlayQuizFactory.init();
        QuizGame quizGame = quizFactory.createQuizGame();

        quizGame.startNewRound();

        return ok(quiz.render((SimpleQuizGame)quizGame));
    }

    @Security.Authenticated(QuizController.class)
    public static Result index() {
        /*if(categories == null) {
            QuizFactory factory = PlayQuizFactory.init();
            QuestionDataProvider provider = factory.createQuestionDataProvider();
            categories = provider.loadCategoryData();
        }*/
        return ok(index.render());
    }
}
