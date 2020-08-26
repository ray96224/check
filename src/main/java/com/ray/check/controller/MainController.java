package com.ray.check.controller;

import com.ray.check.dto.QuestionDto;
import com.ray.check.entity.Question;
import com.ray.check.service.InputService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    InputService inputService;


    @RequestMapping("/testPage")
    public String testPage(){
        return "test";
    }

    @RequestMapping("/testPage1")
    public String testPage1(){
        return "test1";
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
