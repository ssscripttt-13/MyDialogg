package com.example.mydialog;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {

    //JSON private strings
    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IDEA = "idea";
    private static final String JSON_TODO = "todo";
    private static final String JSON_IMPORTANT = "important";
    //JSON private strings end :D

    private String title;
    private String description;
    private boolean idea;
    private boolean todo;
    private boolean important;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIdea() {
        return idea;
    }

    public void setIdea(boolean idea) {
        this.idea = idea;
    }

    public boolean isTodo() {
        return todo;
    }

    public void setTodo(boolean todo) {
        this.todo = todo;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    //JSON anonimus class
    public Note(JSONObject jsonObject) throws JSONException{
        title = jsonObject.getString(JSON_TITLE);
        description = jsonObject.getString(JSON_DESCRIPTION);
        idea = jsonObject.getBoolean(JSON_IDEA);
        todo = jsonObject.getBoolean(JSON_TODO);
        important = jsonObject.getBoolean(JSON_IMPORTANT);
    }

    public Note(){

        }

    public JSONObject convertToJson() throws JSONException{
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JSON_TITLE, title);
        jsonObject.put(JSON_DESCRIPTION, description);
        jsonObject.put(JSON_IDEA, idea);
        jsonObject.put(JSON_TODO, todo);
        jsonObject.put(JSON_IMPORTANT, important);

        return jsonObject;
    }


}