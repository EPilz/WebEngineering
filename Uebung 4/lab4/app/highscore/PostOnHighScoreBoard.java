package highscore;

import highscore.data.HighScoreRequestType;
import highscore.generated.Gender;
import highscore.generated.Quiz;
import highscore.generated.User;
import highscore.generated.Users;
import models.QuizGame;
import models.QuizUser;
import play.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;

/**
 * @author Elisabeth
 * @version 25.05.2014.
 */
public class PostOnHighScoreBoard {

    private PostOnHighScoreBoard() {  }

    public static PostOnHighScoreBoard create() {
        return new PostOnHighScoreBoard();
    }

    public String post(QuizGame game) throws Failure {
        HighScoreRequestType highScoreRequestType = new HighScoreRequestType();
        if(game.getWinner() != null) {
            try {
                Quiz quiz = getQuizFromQuizGame(game);
                highScoreRequestType.setQuiz(quiz);
                highScoreRequestType.setUserKey("rkf4394dwqp49x");

                PublishHighScoreEndpoint endpoint = new PublishHighScoreService().getPublishHighScorePort();
                highscore.data.ObjectFactory factory = new highscore.data.ObjectFactory();
                return endpoint.publishHighScore(factory.createHighScoreRequest(highScoreRequestType).getValue());
            } catch (Failure failure) {
                throw failure;
            } catch (Exception e) {
                Logger.error(e.getMessage());
                throw new Failure("Error", new FailureType());
            }
        } else {
            throw new Failure("no Winner, therefore not posted anything", new FailureType());
        }
    }

    private Quiz getQuizFromQuizGame(QuizGame game) throws DatatypeConfigurationException {
        Quiz quiz = new Quiz();
        QuizUser winner = game.getWinner();
        User userWinner = null;
        User userLoser = null;

        if (winner.getName().equals("Spieler 2")) {
            userWinner = getComputer();
        } else {
            userWinner = getHuman(winner);
        }
        userWinner.setName("winner");

        int indexOfWinner = game.getPlayers().indexOf(winner);
        if (indexOfWinner == 0) {
            userLoser = getComputer();
        } else {
            userLoser = getHuman(game.getPlayers().get(0));
        }
        userLoser.setName("loser");

        Users users = new Users();
        users.getUser().add(userWinner);
        users.getUser().add(userLoser);

        quiz.setUsers(users);

        return quiz;
    }

    private User getHuman(QuizUser user) throws DatatypeConfigurationException {
        User human = new User();
        human.setPassword("");
        human.setFirstname(user.getFirstName() == null || user.getFirstName().isEmpty() ? "-" : user.getFirstName());
        human.setLastname(user.getLastName()  == null || user.getLastName().isEmpty() ? "-" : user.getLastName());
        human.setGender(user.getGender() == null ? Gender.MALE : Gender.fromValue(user.getGender().name()));

        if(user.getBirthDate() != null) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(user.getBirthDate());
            human.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
        } else {
            human.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
        }
        return human;
    }

    private User getComputer() throws DatatypeConfigurationException {
        User computer = new User();
        computer.setPassword("");
        computer.setFirstname("Crazy");
        computer.setLastname("PC");
        computer.setGender(Gender.FEMALE);
        computer.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));

        return computer;
    }
}
