package com.ray.check.dao;

import com.ray.check.entity.ChapterQuestion;
import com.ray.check.entity.ChapterQuestionExample;
import java.util.List;

public interface ChapterQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChapterQuestion record);

    int insertSelective(ChapterQuestion record);

    List<ChapterQuestion> selectByExample(ChapterQuestionExample example);

    ChapterQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChapterQuestion record);

    int updateByPrimaryKey(ChapterQuestion record);
}