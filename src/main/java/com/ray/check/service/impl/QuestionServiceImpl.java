package com.ray.check.service.impl;

import com.ray.check.dao.ChapterMapper;
import com.ray.check.dao.ChapterQuestionMapper;
import com.ray.check.dao.QuestionMapper;
import com.ray.check.dto.QuestionDto;
import com.ray.check.entity.*;
import com.ray.check.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    ChapterMapper chapterMapper;
    @Resource
    QuestionMapper questionMapper;
    @Resource
    ChapterQuestionMapper chapterQuestionMapper;

    @Override
    public QuestionDto getQuestionDtoByChapterId(Integer chapterId) {
        Chapter chapter = chapterMapper.selectByPrimaryKey(chapterId);
        ChapterQuestionExample chapterQuestionExample = new ChapterQuestionExample();
        chapterQuestionExample.or().andChapterIdEqualTo(chapterId);
        List<ChapterQuestion> chapterQuestions = chapterQuestionMapper.selectByExample(chapterQuestionExample);
        List<Integer> collect = chapterQuestions.stream().map(e -> e.getQuestionId()).collect(Collectors.toList());
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("number asc");
        questionExample.or().andIdIn(collect);
        List<Question> questions = questionMapper.selectByExample(questionExample);
        QuestionDto questionDto = new QuestionDto();
        questionDto.setChapter(chapter);
        questionDto.setQuestions(questions);
        return questionDto;
    }

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
