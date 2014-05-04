package controllers;

import models.User;
import play.*;
import play.data.Form;
import play.data.validation.ValidationError;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

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

        if (formUser.hasErrors()) {
            return badRequest(authentication.render(formUser));
        } else {
            if(findUser(formUser.get().getName(), formUser.get().getPassword()) != null) {
                System.out.println(formUser.get().getName());
                session().clear();
                session("userName", formUser.get().getName());
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
        String queryString = "SELECT u FROM User u where u.name = :name and u.password = :password";

        TypedQuery<User> query = em.createQuery(queryString, User.class).
                setParameter("name", userName).
                setParameter("password", password);

        List<User> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
