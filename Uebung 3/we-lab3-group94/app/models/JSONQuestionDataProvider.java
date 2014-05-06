package models;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import com.google.common.base.Charsets;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Elisabeth on 04.05.2014.
 */
public class JSONQuestionDataProvider implements QuestionDataProvider {

    private InputStream inputStream;
    private QuizFactory factory;

    public JSONQuestionDataProvider(InputStream inputStream, QuizFactory factory) {
        this.inputStream = inputStream;
        this.factory = factory;
    }

    @Override
    public List<Category> loadCategoryData() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Category.class,
                new CategoryDeserializer(factory));

        gsonBuilder.registerTypeAdapter(Question.class,
                new QuestionDeserialzier(factory));

        Gson gson = gsonBuilder.create();

        Type collectionType = new TypeToken<List<Category>>() {}.getType();
        List<Category> categories = gson.fromJson(new InputStreamReader(
                inputStream, Charsets.UTF_8), collectionType);

        return categories;
    }

}

class CategoryDeserializer implements JsonDeserializer<Category> {

    private QuizFactory factory;

    public CategoryDeserializer(QuizFactory factory) {
        this.factory = factory;
    }

    @Override
    public Category deserialize(JsonElement json, Type type,
                                JsonDeserializationContext context) throws JsonParseException {
        Category category = factory.createCategory();
        JsonObject object = json.getAsJsonObject();

        category.setName(object.get("name").getAsString());

        for (JsonElement jsonquestion : object.get("questions")
                .getAsJsonArray()) {
            Question question = context.deserialize(jsonquestion,
                    new TypeToken<Question>() {
                    }.getType()
            );
            category.addQuestion(question);
        }

        return category;
    }

}

class QuestionDeserialzier implements JsonDeserializer<Question> {

    private QuizFactory factory;

    private int lastChoiceId = 0;

    public QuestionDeserialzier(QuizFactory factory) {
        this.factory = factory;
    }

    @Override
    public Question deserialize(JsonElement json, Type type,
                                JsonDeserializationContext context) throws JsonParseException {

        Question question = factory.createQuestion();

        JsonObject object = json.getAsJsonObject();
        question.setId(object.get("id").getAsInt());
        question.setText(object.get("text").getAsString());

        for (JsonElement wrongChoice : object.get("wrongChoices").getAsJsonArray()) {
            SimpleChoice choice = context.deserialize(wrongChoice,
                    new TypeToken<SimpleChoice>() {
                    }.getType()
            );
            choice.setId(lastChoiceId++);
            question.addChoice(choice, false);
        }

        question.setMaxTime(object.get("maxTime").getAsLong());

        for (JsonElement correctChoice : object.get("correctChoices")
                .getAsJsonArray()) {
            SimpleChoice choice = context.deserialize(correctChoice,
                    new TypeToken<SimpleChoice>() {
                    }.getType()
            );
            choice.setId(lastChoiceId++);
            question.addChoice(choice, true);
        }

        return question;
    }

}

