package com.lhh.common.type;

public enum TopicType {
    //TODO   在此类添加题目类型  在TopicTypeUtil类中也要添加  分清主观题客观题

//    1 = '单选题', 2 = '多选题', 3 = '判断题', 4 = '填空题', 5 = '解答题', 6 = '完形填空题', 7 = '复合题'
    /**单选题*/
    SingleChoice("单选题", 1),
    /**多选题*/
    MultipleChoice("多选题", 2),
    /**判断题*/
    Judge("判断题", 3),
    /**填空题*/
    Completion("填空题", 4),
//    /**计算题*/
//    CalculationProblem("计算题", 5),
    /**解答题*/
    AnswerQuestions("解答题", 5),
    /**
     * 复合题 *
     */
    Compound("复合题", 7),
    /**问答题*/
    QuestionsAndAnswers("问答题", 9),
    /**作图题*/
    Plotting("作图题", 25),
    /**综合题*/
    ComprehensiveQuestion("综合题", 28),
    /**语音题*/
    VoiceTopic("语音题", 53);

    //TODO   在此类添加题目类型  在TopicTypeUtil类中也要添加  分清主观题客观题

    private String name;

    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    TopicType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }
}
