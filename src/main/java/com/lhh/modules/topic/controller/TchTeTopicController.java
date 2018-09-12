package com.lhh.modules.topic.controller;

import com.lhh.common.type.DateType;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.topic.domain.ClassWrongItem;
import com.lhh.modules.topic.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
@RestController
@RequestMapping("tch/topic")
public class TchTeTopicController {
    @Autowired
    private TopicService topicService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 班级错题本
     *
     * @return
     */
    @RequestMapping("/classWrongTopics")
    public R classWrongTopics(@RequestBody Map<String, Object> params) throws ParseException {


        String s = sdf.format(new Date());
        Date endDate = sdf.parse(s);
        params.put("endTime", endDate);
        wrongTopicParamsPass(params);


        //查询列表数据
        Query query = new Query(params);
        List<ClassWrongItem> list = topicService.classWrongTopics(query);
        int total = topicService.classWrongTopicsTotal(query);
        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        return R.ok(1).put("data", list).put("page", pageUtil);
    }


    /**
     * 班级错题本 插叙单个学生的错题信息
     *
     */
    /**
     * 班级错题本
     *
     * @return
     */
    @RequestMapping("/stuWrongTopics")
    public R stuWrongTopics(@RequestBody Map<String, Object> params) throws ParseException {

        String s = sdf.format(new Date());
        Date endDate = sdf.parse(s);
        params.put("endTime", endDate);
        wrongTopicParamsPass(params);


        //查询列表数据
        Query query = new Query(params);
        List<StuTopicVo> list = topicService.stuWrongTopics(query);
        int total = topicService.stuWrongTopicsTotal(query);
        PageUtils pageUtil = new PageUtils(total, query.getLimit(), query.getPage());
        return R.ok(1).put("data", list).put("page", pageUtil);
    }

    private void wrongTopicParamsPass(@RequestBody Map<String, Object> params) throws ParseException {
        String dateTypeStr;
        if (params.containsKey("dateType")) {
            dateTypeStr = params.remove("dateType").toString();
            Calendar cal = Calendar.getInstance();
            DateType dateType = null;
            if ("5".equals(dateTypeStr)) {
                dateType = DateType.ONE_WEEK;
            } else if ("6".equals(dateTypeStr)) {
                dateType = DateType.ONE_MONTH;
            } else if ("7".equals(dateTypeStr)) {
                dateType = DateType.THREE_MONTH;
            } else if ("8".equals(dateTypeStr)) {
                dateType = DateType.HALF_YEAR;
            }
            if (dateType != null) {
                cal.add(dateType.getType(), dateType.getNum());
                Date sd = cal.getTime();
                String ss = sdf.format(sd);
                Date startDate = sdf.parse(ss);
                params.put("startTime", startDate);
            }
        }
    }
}
