package controllers;

import play.*;
import play.mvc.*;
import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.impl.PlayQuizFactory;

import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result authentication() {
        return ok(authentication.render());
    }

    public static Result index() {
        return ok(index.render());
    }

    public static Result login() {
        System.out.println("login");
        return ok(index.render());
    }
}
