package com.ray.check.service.impl;


import com.ray.check.dao.ChapterMapper;
import com.ray.check.dao.ChapterQuestionMapper;
import com.ray.check.dao.QuestionMapper;
import com.ray.check.dto.QuestionDto;
import com.ray.check.entity.Chapter;
import com.ray.check.entity.ChapterQuestion;
import com.ray.check.entity.Question;
import com.ray.check.service.InputService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class InputServiceImpl implements InputService {

    @Resource
    QuestionMapper questionMapper;
    @Resource
    ChapterMapper chapterMapper;
    @Resource
    ChapterQuestionMapper chapterQuestionMapper;

    @Override
    public void save(QuestionDto questionDto) {
        List<Question> questions = questionDto.getQuestions();
        //保存章节
        Chapter chapter = questionDto.getChapter();
        chapterMapper.insert(chapter);
        for (Question question : questions){
            //保存题目
            questionMapper.insert(question);
            //保存题目章节关联
            ChapterQuestion chapterQuestion = new ChapterQuestion();
            chapterQuestion.setChapterId(chapter.getId());
            chapterQuestion.setQuestionId(question.getId());
            chapterQuestionMapper.insert(chapterQuestion);
        }
    }
}
