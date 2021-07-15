/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.service.OpenFeignService;
import com.chenzifeng.serverconsumer.demos.nacosdiscoveryconsumer.service.TestProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/gateway/feign")
public class OpenFeignController {

    static {
        initFlowRules();
    }

    @Autowired
    private TestProvideService testProvideService;

    @Autowired
    private OpenFeignService openFeignService;

    @GetMapping("/test")
    public String feignEcho() throws InterruptedException {
        System.out.println("----------消费者开始消费----------");
        return openFeignService.openFeignTest();
    }


    /***
     *  这里在代码层进行限流，对代码侵入性比较大
     * @return
     */
    @GetMapping("/test1")
    public String test1() {

        System.out.println("----------消费者开始消费----------");
        Entry entry = null;
        try {
            entry = SphU.entry("hello");
            System.out.println("进行业务逻辑处理");
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BlockException e) {
            System.out.println("--------拒绝------");
            return "----blocked----";
        }finally {
            if(entry != null){
                entry.exit();
            }
        }
        return testProvideService.test1();
    }


    public static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule = new FlowRule();
        rule.setResource("hello");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);

    }
}
