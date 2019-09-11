package com.wyz.activiti7.controller;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskQueryController {

	/** 节点任务相关操作接口 */
	@Autowired
	private TaskService taskService;

	@RequestMapping(value="/findTaskList")
	public List<Task> findTaskListByName(String name) {
		List<Task> taskList = taskService.createTaskQuery()
				.taskAssignee(name)//指定个人任务查询
				.orderByTaskCreateTime().asc().list();
		if (null == taskList || taskList.size() < 1) {// 如果指定个人任务为空，则查询多人任务
			taskList = taskService.createTaskQuery().taskCandidateUser(name).orderByTaskCreateTime().asc().list();
		}
		if (null == taskList || taskList.size() < 1) {// 如果指定多人任务为空在获取组的任务
			taskList = taskService.createTaskQuery().taskCandidateGroup(name).orderByTaskCreateTime().asc().list();
		}
		return taskList;
	}
}
