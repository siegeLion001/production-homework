package com.lhh.modules.cnjy21.model.paper;


import com.lhh.modules.cnjy21.model.question.Question;

import java.util.List;

/**
 * 试题信息
 *
 * @author zhoushubing
 */
public class PaperView extends Paper {

    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
