package com.wyz.activiti7.my.process;

import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

public class MyprocessTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MyprocessTest.class);
	
	@Rule
	 public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	@Deployment(resources = "processes/my-process.bpmn")
    public void testStartProcessInstanceByKey() {

       RuntimeService runtimeService = activitiRule.getRuntimeService();

       Map<String, Object> varables = Maps.newHashMap();
       varables.put("name", "zhangxingr");
       varables.put("sex", "man");
       varables.put("age", "21");

       ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", varables);
       logger.info("processInstance = {}", processInstance);
    }

}
