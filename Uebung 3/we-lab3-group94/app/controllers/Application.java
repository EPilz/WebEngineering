package controllers;

import play.*;
import play.mvc.*;
import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.impl.PlayQuizFactory;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready :))"));
    }

    public static Result sayHello() {
        QuizFactory factory = new PlayQuizFactory(jsonFilePath, user);

        return ok("Hello " + request().remoteAddress());
    }
}
