/**
 * @author Elisabeth
 * @version 23.05.2014.
 */
import models.*;
import org.hibernate.PersistentObjectException;
import org.junit.*;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.test.FakeApplication;
import play.test.Helpers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class QuizJPATest {

    protected static FakeApplication app;
    protected static EntityManager em;

    private static IQuizDAO quizDao;

    @BeforeClass
    public static void startApp() {
        app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
        Helpers.start(app);

        em = app.getWrappedApplication().plugin(JPAPlugin.class).get().em("default");
        JPA.bindForCurrentThread(em);

        quizDao = QuizDAO.INSTANCE;
    }

    @AfterClass
    public static void stopApp() {
        quizDao = null;

        JPA.bindForCurrentThread(null);
        em.close();
        em = null;

        Helpers.stop(app);
    }

    @Before
    public void setUp() {
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    public void createCategory() {
        Category categoryHistory = new Category();
        categoryHistory.setNameDE("Geschichte");
        categoryHistory.setNameEN("History");

        quizDao.persist(categoryHistory);

        em.flush();
        assertNotNull(categoryHistory.getId());
    }

    @Test
    public void createCategoryWithQuestion() {
        Category categoryHistory = new Category();
        categoryHistory.setNameDE("Geschichte");
        categoryHistory.setNameEN("History");

        Question question = new Question();
        question.setTextDE("Test DE");
        question.setTextEN("Test EN");
        question.setMaxTime(new BigDecimal(30));

        categoryHistory.addQuestion(question);

        quizDao.persist(categoryHistory);

        em.flush();
        assertNotNull(categoryHistory.getId());
        assertNotNull(question.getId());
    }

    @Test
    public void createCategoryWithQuestionAndChoice() {
        Category categoryHistory = new Category();
        categoryHistory.setNameDE("Geschichte");
        categoryHistory.setNameEN("History");

        Question question = new Question();
        question.setTextDE("Test DE");
        question.setTextEN("Test EN");
        question.setMaxTime(new BigDecimal(30));

        Choice choiceR = new Choice();
        choiceR.setTextDE("Antwort 1");
        choiceR.setTextEN("Answer 1");

        Choice choiceF = new Choice();
        choiceF.setTextDE("Antwort 1");
        choiceF.setTextEN("Answer 1");

        question.addRightChoice(choiceR);
        question.addWrongChoice(choiceF);

        categoryHistory.addQuestion(question);

        quizDao.persist(categoryHistory);

        em.flush();
        assertNotNull(categoryHistory.getId());
        assertNotNull(question.getId());
        assertNotNull(choiceR.getId());
        assertNotNull(choiceF.getId());
    }

    @Test
    public void findAllQuestions() {
        List<Question> questionList = quizDao.findEntities(Question.class);
        assertEquals(35, questionList.size());
    }

    @Test
    public void findAllCategories() {
        List<Category> questionList = quizDao.findEntities(Category.class);
        assertEquals(5, questionList.size());
    }

    @Test
    public void findQuestionWithId6() {
        Question question = quizDao.findEntity(6L, Question.class);

        assertNotNull(question);
        assertEquals("JavaScript...", question.getTextDE());
    }

    @Test
    public void updateCategory() {
        Category categoryHistory = new Category();

        categoryHistory.setNameDE("Geschichte");
        categoryHistory.setNameEN("History");
        quizDao.persist(categoryHistory);
        em.flush();

        categoryHistory.setNameDE("Test");
        quizDao.persist(categoryHistory);

        Category categoryFind = quizDao.findEntity(categoryHistory.getId(), Category.class);
        assertEquals(categoryHistory, categoryFind);
    }

    @Test(expected = PersistenceException.class)
    public void createDetachedCategoryThrowsException() {
        Category categoryHistory = new Category();

        categoryHistory.setNameDE("Geschichte");
        categoryHistory.setNameEN("History");
        quizDao.persist(categoryHistory);
        em.flush();
        em.detach(categoryHistory);
        quizDao.persist(categoryHistory);
    }

    @Test
    public void editDetachedQuestion() {
        Question question = quizDao.findEntity(6L, Question.class);
        em.detach(question);
        question.setTextDE("Test Test");

        Question questionFind = quizDao.findEntity(6L, Question.class);
        assertNotEquals(question.getTextDE(), questionFind.getTextDE());
    }

    @Test
    public void mergeQuestion() {
        Question question = quizDao.findEntity(6L, Question.class);
        em.detach(question);
        question.setTextDE("Test Test");
        quizDao.merge(question);

        Question questionFind = quizDao.findEntity(6L, Question.class);
        assertEquals(question.getTextDE(), questionFind.getTextDE());
    }




}