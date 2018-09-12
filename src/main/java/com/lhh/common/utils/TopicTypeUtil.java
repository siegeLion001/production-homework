package com.lhh.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.lhh.common.type.TopicType;

/**
 * 主观题,客观题获取工具类
 *
 * @ClassName: TopicTypeUtil
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.common.utils
 * @CreateTime: 2018-07-21
 */
public class TopicTypeUtil {
    /**
     * 获取客观题类型集合
     * @name: getObjectiveList
     * @params: []
     * @return: java.util.List<java.lang.Integer>
     * @Author: cuihp
     * @Date: 2018/7/21
     */
    public static List<Integer> getObjectiveList(){
        ArrayList<Integer> typeList = new ArrayList<>();
        //判断题
        typeList.add(TopicType.Judge.getCode());
        //多选题
        typeList.add(TopicType.MultipleChoice.getCode());
        //单选题
        typeList.add(TopicType.SingleChoice.getCode());
        return typeList;
    }
    /**
     * 获取主观题类型集合
     * @name: getSubjectiveList 
     * @params: []
     * @return: java.util.List<java.lang.Integer>
     * @Author: cuihp
     * @Date: 2018/7/21
     */
    public static List<Integer> getSubjectiveList(){
        ArrayList<Integer> typeList = new ArrayList<>();
        /**解答题*/
        typeList.add(TopicType.AnswerQuestions.getCode());
//        typeList.add(TopicType.CalculationProblem.getCode());
        /**填空题*/
        typeList.add(TopicType.Completion.getCode());
        /**综合题*/
        typeList.add(TopicType.ComprehensiveQuestion.getCode());
        /**作图题*/
        typeList.add(TopicType.Plotting.getCode());
        /**复合题 */
        typeList.add(TopicType.Compound.getCode());
        /**问答题*/
        typeList.add(TopicType.QuestionsAndAnswers.getCode());
        /**语音题*/
        typeList.add(TopicType.VoiceTopic.getCode());
        return typeList;
    }

}