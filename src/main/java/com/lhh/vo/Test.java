package com.lhh.vo;

import com.alibaba.fastjson.JSON;

public class Test {

    public static void main(String[] args) {
        AnswerSheet answerSheet = new AnswerSheet();

        answerSheet.setName("课后作业1");
        Part part1 = new Part();
        part1.setbNum("1");
        part1.setType("选择题");
        AnswerItem answerItem1 = new AnswerItem();
        answerItem1.setbNum("1");
        answerItem1.setType("选择题");
        answerItem1.setsNum("1");
        answerItem1.setAnswerJson(new String[4]);

        AnswerItem answerItem2 = new AnswerItem();
        answerItem2.setbNum("1");
        answerItem2.setType("选择题");
        answerItem2.setsNum("2");
        answerItem2.setAnswerJson(new String[4]);

        AnswerItem answerItem3 = new AnswerItem();
        answerItem3.setbNum("1");
        answerItem3.setType("选择题");
        answerItem3.setsNum("3");
        answerItem3.setAnswerJson(new String[4]);

        AnswerItem answerItem4 = new AnswerItem();
        answerItem4.setbNum("1");
        answerItem4.setType("选择题");
        answerItem4.setsNum("4");
        answerItem4.setAnswerJson(new String[4]);

        AnswerItem answerItem5 = new AnswerItem();
        answerItem5.setbNum("1");
        answerItem5.setType("选择题");
        answerItem5.setsNum("1");
        answerItem5.setAnswerJson(new String[4]);
        part1.setAnswers(answerItem1, answerItem2, answerItem3, answerItem4, answerItem5);


        Part part2 = new Part();
        part2.setbNum("2");
        part2.setType("判断题");

        AnswerItem answerItem6 = new AnswerItem();
        answerItem6.setbNum("2");
        answerItem6.setsNum("1");
        answerItem6.setType("判断题");
        answerItem6.setAnswerJson(new String[2]);

        AnswerItem answerItem7 = new AnswerItem();
        answerItem7.setbNum("2");
        answerItem7.setsNum("1");
        answerItem7.setType("判断题");
        answerItem7.setAnswerJson(new String[2]);
        part2.setAnswers(answerItem6, answerItem7);


        Part part3 = new Part();
        part3.setbNum("3");
        part3.setType("简答题");


        AnswerItem answerItem8 = new AnswerItem();
        answerItem8.setbNum("3");
        answerItem8.setsNum("1");
        answerItem8.setType("简答题");
        answerItem8.setImageUrl();

        part3.setAnswers(answerItem8);
        answerSheet.setParts(part1, part2, part3);
        System.out.println(JSON.toJSON(answerSheet));

    }
}
