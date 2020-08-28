package com.ray.check.controller;

import com.ray.check.dao.ChapterMapper;
import com.ray.check.dao.ChapterQuestionMapper;
import com.ray.check.dao.QuestionMapper;
import com.ray.check.dto.QuestionDto;
import com.ray.check.entity.*;
import com.ray.check.service.InputService;
import com.ray.check.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    InputService inputService;
    @Autowired
    QuestionService questionService;
    @Resource
    ChapterMapper chapterMapper;

    @RequestMapping("/")
    public String index(Model model){
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.setOrderByClause("number asc");
        List<Chapter> chapters = chapterMapper.selectByExample(chapterExample);
        model.addAttribute("chapters", chapters);
        return "index";
    }

    @RequestMapping("/singleCheck/{chapterId}")
    public String singleCheck(@PathVariable Integer chapterId, Model model){

//        Chapter chapter = chapterMapper.selectByPrimaryKey(chapterId);
//        ChapterQuestionExample chapterQuestionExample = new ChapterQuestionExample();
//        chapterQuestionExample.or().andChapterIdEqualTo(chapterId);
//        List<ChapterQuestion> chapterQuestions = chapterQuestionMapper.selectByExample(chapterQuestionExample);
//        List<Integer> collect = chapterQuestions.stream().map(e -> e.getQuestionId()).collect(Collectors.toList());
//        QuestionExample questionExample = new QuestionExample();
//        questionExample.or().andIdIn(collect);
//        List<Question> questions = questionMapper.selectByExample(questionExample);
//        QuestionDto questionDto = new QuestionDto();
//        questionDto.setChapter(chapter);
//        questionDto.setQuestions(questions);
        QuestionDto questionDto = questionService.getQuestionDtoByChapterId(chapterId);
        model.addAttribute("questionDto", questionDto);
        return "singleCheck";
    }

    @RequestMapping("/allCheck/{chapterId}")
    public String allCheck(@PathVariable Integer chapterId, Model model){
//        Chapter chapter = chapterMapper.selectByPrimaryKey(chapterId);
//        ChapterQuestionExample chapterQuestionExample = new ChapterQuestionExample();
//        chapterQuestionExample.or().andChapterIdEqualTo(chapterId);
//        List<ChapterQuestion> chapterQuestions = chapterQuestionMapper.selectByExample(chapterQuestionExample);
//        List<Integer> collect = chapterQuestions.stream().map(e -> e.getQuestionId()).collect(Collectors.toList());
//        QuestionExample questionExample = new QuestionExample();
//        questionExample.or().andIdIn(collect);
//        List<Question> questions = questionMapper.selectByExample(questionExample);
//        QuestionDto questionDto = new QuestionDto();
//        questionDto.setChapter(chapter);
//        questionDto.setQuestions(questions);
        QuestionDto questionDto = questionService.getQuestionDtoByChapterId(chapterId);
        model.addAttribute("questionDto", questionDto);
        return "allCheck";
    }

    @RequestMapping("/savePage")
    public String savePage(){
        return "save";
    }

    @ResponseBody
    @PostMapping("/saveData")
    public String saveData(HttpServletRequest request){
        List<Question> questions = new ArrayList<>();
        for (int i=1; i<=100; i++){
            Question question = new Question();
            question.setNumber(i);
            question.setAnswer(request.getParameter(i+""));
            questions.add(question);
        }
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestions(questions);
        String a = request.getParameter("0");

        Chapter chapter = new Chapter();
        chapter.setNumber(Integer.parseInt(a));
        questionDto.setChapter(chapter);
        System.out.println(questionDto);

        questionService.save(questionDto);
        return questionDto.toString();
    }

}
