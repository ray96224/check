package com.ray.check.dto;

import com.ray.check.entity.Chapter;
import com.ray.check.entity.Question;

import java.util.List;

public class QuestionDto {
    List<Question> questions;
//    Integer chapterNumber;

    Chapter chapter;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "questions=" + questions +
                ", chapter=" + chapter +
                '}';
    }
}
