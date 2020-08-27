package com.ray.check.controller;

import com.ray.check.dao.ChapterMapper;
import com.ray.check.dao.ChapterQuestionMapper;
import com.ray.check.dao.QuestionMapper;
import com.ray.check.dto.QuestionDto;
import com.ray.check.entity.Chapter;
import com.ray.check.entity.Question;
import com.ray.check.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    InputService inputService;
    @Resource
    ChapterMapper chapterMapper;
    @Resource
    QuestionMapper questionMapper;
    @Resource
    ChapterQuestionMapper chapterQuestionMapper;

    @RequestMapping("/")
    public String index(Model model){
        List<Chapter> chapters = chapterMapper.selectByExample(null);
        model.addAttribute("chapters", chapters);
        return "index";
    }

    @RequestMapping("/singleCheck/{chapterId}")
    public String singleCheck(@PathVariable Integer chapterId){

        return "singleCheck";
    }

    @RequestMapping("/testPage2")
    public String testPage2(){
        return "test2";
    }

    @RequestMapping("/savePage")
    public String savePage(){
        return "save";
    }

    @ResponseBody
    @PostMapping("/saveData")
    public String saveData(HttpServletRequest request){
        List<Question> questions = new ArrayList<>();
        for (int i=1; i<=5; i++){
            Question question = new Question();
            question.setNumber(i);
            question.setAnswer(request.getParameter(i+""));
            questions.add(question);
        }
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestions(questions);
        String a = request.getParameter("0");
        questionDto.setChapterNumber(Integer.parseInt(a));
        System.out.println(questionDto);

        inputService.save(questionDto);
        return questionDto.toString();
    }

}
