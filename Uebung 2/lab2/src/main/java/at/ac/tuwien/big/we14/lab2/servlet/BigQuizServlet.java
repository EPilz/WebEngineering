package at.ac.tuwien.big.we14.lab2.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import at.ac.tuwien.big.we14.lab2.api.impl.ServletQuizFactory;

// Your Servlet implementation

public class BigQuizServlet extends HttpServlet{
	
	// ServletContext coming from javax.servlet.GenericServlet or subclass
	ServletContext servletContext = getServletContext();
	QuizFactory factory = ServletQuizFactory.init(servletContext);
	QuestionDataProvider provider = factory.createQuestionDataProvider();
	List<Category> categories = provider.loadCategoryData();
	// category has name and holds questions
	// questions have attributes and choices
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	public String getServletInfo() {
		return "Handles BigQuizServlet Requests";
	}
}