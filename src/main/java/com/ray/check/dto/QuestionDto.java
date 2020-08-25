package com.ray.check.dto;

import com.ray.check.entity.Question;

import java.util.List;

public class QuestionDto {
    List<Question> questions;
    Integer chapterNumber;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }
}
