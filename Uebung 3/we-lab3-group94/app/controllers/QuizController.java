package controllers;

import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.impl.PlayQuizFactory;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleUser;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index;
import views.html.quiz;
import views.html.quizover;
import views.html.roundover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class QuizController extends Controller {

    @Security.Authenticated(QuizSecurity.class)
    public static Result startGame() {
        String username = session("username");
        User userHuman = new SimpleUser();
        userHuman.setName(username);

        String path = "conf/" +  Messages.get("json.file");
        QuizFactory quizFactory = new PlayQuizFactory(path, userHuman);

        QuizGame quizGame = quizFactory.createQuizGame();
        quizGame.startNewRound();
        Cache.set(userHuman.getName() + "Game", quizGame);

        return ok(quiz.render(quizGame));
    }

    @Security.Authenticated(QuizSecurity.class)
    public static Result quiz() {
        DynamicForm form = Form.form().bindFromRequest();

        String username = session("username");
        QuizGame quizGame = (QuizGame) Cache.get(username + "Game");
        User userHuman = quizGame.getPlayers().get(0);

        Question question = quizGame.getCurrentRound().getCurrentQuestion(userHuman);
        List<Choice> choices = new ArrayList<Choice>();
        for (Choice c : question.getAllChoices()) {
            String valueChoice = form.data().get("option" + c.getId());
            if (valueChoice != null && valueChoice.equals("on")) {
                choices.add(c);
            }
        }

        long time = Long.valueOf(form.data().get("timeleftvalue"));
        quizGame.answerCurrentQuestion(userHuman, choices, time);

        if(quizGame.isRoundOver())  {
            return roundover();
        } else {
            return ok(quiz.render(quizGame));
        }
    }

    @Security.Authenticated(QuizSecurity.class)
    public static Result roundover() {
        String username = session("username");
        QuizGame quizGame = (QuizGame) Cache.get(username + "Game");
        User userHuman = quizGame.getPlayers().get(0);

        if(quizGame.isGameOver()) {
            User winner = quizGame.getWinner();
            String winnerText = "";
            if(winner == null) {
                winnerText = Messages.get("undecidedGame");
            } else {
                winnerText = Messages.get("userGameWins", winner.getName());
            }
            return ok(quizover.render(quizGame, winnerText));
        } else {
            User winner = quizGame.getCurrentRound().getRoundWinner();
            String winnerText = "";

            if(winner == null) {
                winnerText = Messages.get("undecidedRound", quizGame.getCurrentRoundCount());
            } else {
                winnerText = Messages.get("roundwins", winner.getName(), quizGame.getCurrentRoundCount());
            }
            return ok(roundover.render(quizGame, winnerText));
        }
    }

    @Security.Authenticated(QuizSecurity.class)
    public static Result newRound() {
        String username = session("username");
        QuizGame quizGame = (QuizGame) Cache.get(username + "Game");
        quizGame.startNewRound();
        return ok(quiz.render(quizGame));
    }

    @Security.Authenticated(QuizSecurity.class)
    public static Result index() {
        /*if(categories == null) {
            QuizFactory factory = PlayQuizFactory.init();
            QuestionDataProvider provider = factory.createQuestionDataProvider();
            categories = provider.loadCategoryData();
        }*/
        return ok(index.render());
    }
}
