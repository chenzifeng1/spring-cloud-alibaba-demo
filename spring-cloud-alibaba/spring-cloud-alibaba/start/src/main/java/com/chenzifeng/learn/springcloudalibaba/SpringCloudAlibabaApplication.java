package com.chenzifeng.learn.springcloudalibaba;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.chenzifeng.learn.springcloudalibaba.api.StaticContract;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringCloudAlibabaApplication {

    public static void main(String[] args) {
//        initFlowRules();
        SpringApplication.run(SpringCloudAlibabaApplication.class, args);
    }


    /**
     * 服务、方法的限流规则
     */
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 设置资源名
        rule.setResource(StaticContract.HELLO_WORLD);
        // 设置限流方式 FLOW_GRADE_QPS 这个是按照qps的方式进行限流，每秒只允许n个请求打进来
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置资源个数
        rule.setCount(1);
        rules.add(rule);
        // 将规则添加到规则管理器中
        FlowRuleManager.loadRules(rules);

    }
}
;