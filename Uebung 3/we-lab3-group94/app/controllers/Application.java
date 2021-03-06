package controllers;

import models.User;
import play.cache.Cache;
import play.data.Form;
import play.data.format.Formatters;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.authentication;
import views.html.registration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if (findUserByUsernameAndPassword(formUser.get().getUsername(), formUser.get().getPassword()) != null) {
                session().clear();
                session("username", formUser.get().getUsername());
                return redirect(routes.QuizController.index());
            } else {
                formUser.reject("formError", Messages.get("passwordUsernameWrong"));
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
        Formatters.register(Date.class, new Formatters.SimpleFormatter<Date>() {

            private List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {{
                add(new SimpleDateFormat("yyyy-MM-dd"));
                add(new SimpleDateFormat("dd.MM.yyyy"));
            }
            };

            @Override
            public Date parse(String input, Locale l) throws ParseException {
                if(input == null || input.isEmpty()) {
                    return null;
                }

                Date date = null;

                for (SimpleDateFormat format : dateFormats) {
                    try {
                        format.setLenient(false);
                        date = format.parse(input);
                    } catch (ParseException e) { }
                    if (date != null) {
                        break;
                    }
                }
                if(date == null) {
                    throw new ParseException(Messages.get("birthdateFormat"), 0);
                }
                return date;
            }

            @Override
            public String print(Date date, Locale l) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                return sdf.format(date);
            }
        });

        Form<User> formUser = Form.form(User.class).bindFromRequest();
        if (formUser.hasErrors()) {
            return badRequest(registration.render(formUser));
        } else {
            if(findUserByUsername(formUser.get().getUsername()) != null) {
                formUser.reject("formError", Messages.get("usernameAlreadyExists"));
                return badRequest(registration.render(formUser));
            } else {
                JPA.em().persist(formUser.get());
                return redirect(routes.Application.authentication());
            }
        }
    }

    private static User findUserByUsername(String userName) {
        EntityManager em = play.db.jpa.JPA.em();
        String queryString = "SELECT u FROM User u where u.username = :username";

        TypedQuery<User> query = em.createQuery(queryString, User.class).setParameter("username", userName);

        List<User> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    private static User findUserByUsernameAndPassword(String userName, String password) {
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
