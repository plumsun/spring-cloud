package demo.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @date: 2022/9/20 10:33
 * @author: LiHaoHan
 * @program: demo.controller
 */
@RestController
@RequestMapping
public class SentinelController {


    @GetMapping("test1")
    public String test1() {
        try (Entry entry = SphU.entry("helloWord")) {
            return "1";
        } catch (BlockException e) {
            e.printStackTrace();
            return "-1";
        }
    }

    @SentinelResource("helloWord")
    @GetMapping("test2")
    public String test2() {
        return "2";
    }


    @PostConstruct
    public void initRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //限流资源
        rule.setResource("helloWord");
        //限流规则类型
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //QPS阈值
        rule.setCount(2);
        rules.add(rule);
        //限流规则加载
        FlowRuleManager.loadRules(rules);
    }
}
