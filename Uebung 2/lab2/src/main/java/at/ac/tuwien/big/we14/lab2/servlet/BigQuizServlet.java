package at.ac.tuwien.big.we14.lab2.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.ac.tuwien.big.we14.lab2.api.Answer;
import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Choice;
import at.ac.tuwien.big.we14.lab2.api.Game;
import at.ac.tuwien.big.we14.lab2.api.Player;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import at.ac.tuwien.big.we14.lab2.api.Round;
import at.ac.tuwien.big.we14.lab2.api.impl.ServletQuizFactory;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleGame;
import at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleRound;

public class BigQuizServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final int NUM_ROUNDS = 5;
	public static final int NUM_QUESTIONS = 3;
	
	List<Category> categories;
	
	@Override
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext servletContext = config.getServletContext();
		QuizFactory factory = ServletQuizFactory.init(servletContext);
		QuestionDataProvider provider = factory.createQuestionDataProvider();
		categories = provider.loadCategoryData();	 
    }	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp"); 
		HttpSession session = request.getSession(true);
		Game game = (Game)session.getAttribute("game");		
		
		String action = request.getParameter("action");
		if(action == null && game == null) {
			dispatcher = getServletContext().getRequestDispatcher("/start.jsp"); 	
		}

        if(game == null) {
        	Player player1 = new SimplePlayer("Spieler 1");
         	Player player2 = new SimplePlayer("Spieler 2");
         	game = new SimpleGame(player1, player2);   
         	session.setAttribute("player1", player1);
         	session.setAttribute("player2", player2);  	
        } else if(game.getRounds().size() == NUM_ROUNDS) {
        		game.clearRounds();   	
        }
        
	    Category category = chooseCategory(game);
		Round round = new SimpleRound(category, game, game.getRounds().size() + 1);
	 	Question question = nextQuestion(round);
	 	round.addQuestion(question);
	 	session.setAttribute("game", game);
	 	session.setAttribute("currentRound", round);
	 	session.setAttribute("currentQuestion", question);
        
        dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);    
		Question currentQuestion =(Question)session.getAttribute("currentQuestion");
		if(currentQuestion != null) {		
			List<Choice> answers = new ArrayList<>();

			for (Choice choice : currentQuestion.getAllChoices()) {
				String on = request.getParameter("option" + choice.getId());
				if(on != null) {
					answers.add(choice);
				}
			}
			
			Round currentRound = (Round)session.getAttribute("currentRound");
			int numQuestion = currentRound.getQuestions().size() - 1;
			
			if(currentQuestion.getCorrectChoices().containsAll(answers) && 
					currentQuestion.getCorrectChoices().size() == answers.size()) {	
				currentRound.getAnswersPlayer1()[numQuestion] = Answer.CORRECT;
			} else {
				currentRound.getAnswersPlayer1()[numQuestion] = Answer.INCORRECT;
			}

			if(new Random().nextInt(2) == 1) {
				currentRound.getAnswersPlayer2()[numQuestion] = Answer.CORRECT;
			} else {
				currentRound.getAnswersPlayer2()[numQuestion] = Answer.INCORRECT;
			}
			
			currentRound.addTimePlayer1(Integer.parseInt(request.getParameter("timeleftvalue")));
			currentRound.addTimePlayer2(new Random().nextInt(31));
			
			RequestDispatcher dispatcher = null;
			if(numQuestion < 2) {
				Question nextQuestion = nextQuestion(currentRound);
				currentRound.addQuestion(nextQuestion);
				session.setAttribute("currentRound", currentRound);
	         	session.setAttribute("currentQuestion", nextQuestion);
	         	dispatcher = getServletContext().getRequestDispatcher("/question.jsp");	            
			} else {
				Game game = (Game)session.getAttribute("game");
				game.addRound(currentRound);
				if(game.getRounds().size() == NUM_ROUNDS) {
					dispatcher = getServletContext().getRequestDispatcher("/finish.jsp");
				} else {
					dispatcher = getServletContext().getRequestDispatcher("/roundcomplete.jsp");
				}
			}
			dispatcher.forward(request, response);
		}
	}
	
	private Category chooseCategory(Game game) {
		List<Category> leftCategories = new ArrayList<>();
		leftCategories.addAll(categories);
		for (Round round : game.getRounds()) {
			leftCategories.remove(round.getCategory());
		}
		Random random = new Random();
		return leftCategories.get(random.nextInt(leftCategories.size()));
	}
	
	private Question nextQuestion(Round round) {
		List<Question> leftQuestion = new ArrayList<>();
		leftQuestion.addAll(round.getCategory().getQuestions());
		for (Question q : round.getQuestions()) {
			leftQuestion.remove(q);
		}
		Random random = new Random();
		return leftQuestion.get(random.nextInt(leftQuestion.size()));
	}

	@Override
	public String getServletInfo() {
		return "Handles BigQuizServlet Requests";
	}
}