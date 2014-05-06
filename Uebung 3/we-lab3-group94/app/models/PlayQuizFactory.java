package models;

import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import play.Application;
import play.api.Play;
import play.i18n.Messages;

import java.io.InputStream;

/**
 * Created by Elisabeth on 06.05.2014.
 */
public class PlayQuizFactory extends SimpleQuizFactory {

    public static QuizFactory init() {
        return new PlayQuizFactory();
    }

    @Override
    public QuestionDataProvider createQuestionDataProvider() {
        return new JSONQuestionDataProvider(this.getClass().getResourceAsStream("/" + Messages.get("json.file")), this);
    }

}
