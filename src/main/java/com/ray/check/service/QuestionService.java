package com.ray.check.service;

import com.ray.check.dto.QuestionDto;

public interface QuestionService {

    QuestionDto getQuestionDtoByChapterId(Integer chapterId);

    void save(QuestionDto questionDto);
}
