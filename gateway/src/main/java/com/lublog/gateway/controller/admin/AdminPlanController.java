package com.lublog.gateway.controller.admin;

import com.alibaba.fastjson.JSON;
import com.lublog.po.Plan;
import com.lublog.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: java类作用描述LifeController
 * @Author: lxy
 * @time: 2020/7/29 1:03
 */
@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminPlanController {
    private static final String LineFeed = "/r";
    private static final String htmlLineFeed = "<br>";

    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/addPlan", method = RequestMethod.POST)
    @ResponseBody
    public String updateMyPlan(String planContent, String planDetail, String planValue, Integer planYear, Integer planMonth, Integer planDay, Integer planHour,
                               Integer planMinute, Integer planSecond) {
        String msg = "增加计划成功，一定要完成";
        try {
            planService.addPlan(planContent, planDetail, planValue, planYear, planMonth, planDay, planHour, planMinute, planSecond);
            log.info("planContent is {}, planDetail is {},planValue is {},planYear is {}, planMonth is {}, planDay is {}, planHour is {}, planMinute is {}, planSecond is {}",
                    planContent, planDetail, planValue, planYear, planMonth, planDay, planHour, planMinute, planSecond);
//            planService.updateLineFeed();
        } catch (Exception e) {
            msg = "增加计划失败，做事要仔细";
            log.error("add plan fail is {}", e);
            return msg;
        }
        return msg;
    }

    @RequestMapping(value = "/showLastPlan", method = RequestMethod.GET)
    @ResponseBody
    public List<Plan> showLastPlan() {
        List<Plan> plans = planService.getAllPlans();
        log.info("plans is {}", JSON.toJSONString(plans));
        return plans;
    }
}
