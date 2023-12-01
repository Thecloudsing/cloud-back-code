package com.luoanforum.authorization.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class MainController {

//    @Autowired
//    private TestMapper testMapper;
//
//    @GetMapping("/{id}")
////    @GlobalLock
//    @GlobalTransactional
//    @SentinelResource(value = "/", blockHandler = "indexBlockHandler", fallback = "indexFallback")
//    public String index(@PathVariable("id") Integer id) {
//        int i = 1 / id;
//        log.info("index: id={}", id);
//        return "index:MainController-OAuth " + id;
//    }

//    public String indexBlockHandler(@PathVariable("id") Integer id, BlockException blockException) {
//        log.warn("indexBlockHandler: id={}, exception={}", id, blockException.getRule());
//        return "indexBlockHandler:MainController-OAuth " + id;
//    }

    public String indexFallback(Integer id, Throwable throwable) {
        log.error("indexFallback: id={}, exception={}", id, throwable.getMessage());
        return "indexFallback:MainController-OAuth " + id;
    }

    @GetMapping("/idx/{id}")
    public String idx(@PathVariable("id") Integer id) {
        log.info("idx: id={}", id);
        return "idx:MainController-OAuth " + id;
    }


//    /**
//     * Sentinel配置
//     */
//    @PostConstruct
//    public void init() {
//        System.out.println("MainController-OAuth");
//        // 配置限流规则
//        ArrayList<FlowRule> flowRules = new ArrayList<>();
//
//        FlowRule rule = new FlowRule();
//        rule.setResource("/");
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        rule.setCount(2);
//        flowRules.add(rule);
//
//        FlowRuleManager.loadRules(flowRules);
//
//        // 配置熔断降级规则
//        ArrayList<DegradeRule> degradeRules = new ArrayList<>();
//
//        DegradeRule degradeRule = new DegradeRule();
//        degradeRule.setResource("/");
//        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
//        degradeRule.setCount(2);
//        degradeRule.setTimeWindow(10);
//        degradeRule.setMinRequestAmount(2);
//        degradeRules.add(degradeRule);
//
//        DegradeRuleManager.loadRules(degradeRules);
//
//    }
}
