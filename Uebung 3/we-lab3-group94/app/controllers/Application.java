package controllers;

import at.ac.tuwien.big.we14.lab2.api.QuizGame;
import models.User;
import play.cache.Cache;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.authentication;
import views.html.registration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result authentication() {
        return ok(authentication.render(form(User.class)));
    }

    @Transactional
    public static Result login() {
        Form<User> formUser = Form.form(User.class).bindFromRequest();
        System.out.println(formUser);
        if (formUser.hasErrors()) {
            return badRequest(authentication.render(formUser));
        } else {
            if (findUser(formUser.get().getUsername(), formUser.get().getPassword()) != null) {
                session().clear();
                session("username", formUser.get().getUsername());
                return redirect(routes.QuizController.index());
            } else {
                formUser.reject("formError", "kein gültiger Login");
                return badRequest(authentication.render(formUser));
            }
        }
    }

    public static Result registration() {
        return ok(registration.render(form(User.class)));
    }

    public static Result logout() {
        String username = session("username");
        Cache.remove(username + "Game");
        session().clear();
        return redirect(routes.Application.authentication());
    }

    @Transactional
    public static Result newUser() {
        Form<User> formUser = Form.form(User.class).bindFromRequest();
        if (formUser.hasErrors()) {
            System.out.println("error");
            return badRequest(registration.render(formUser));
        } else {
            JPA.em().persist(formUser.get());
            return redirect(routes.Application.authentication());
        }
    }

    private static User findUser(String userName, String password) {
        EntityManager em = play.db.jpa.JPA.em();
        String queryString = "SELECT u FROM User u where u.username = :username and u.password = :password";

        TypedQuery<User> query = em.createQuery(queryString, User.class).
                setParameter("username", userName).
                setParameter("password", password);

        List<User> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
