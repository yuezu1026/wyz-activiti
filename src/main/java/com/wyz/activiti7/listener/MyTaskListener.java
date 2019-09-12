package com.wyz.activiti7.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wyz.activiti7.model.User;
import com.wyz.activiti7.service.UserService;

@Component
public class MyTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;
	
	@Autowired
    private UserService userService;

	@Override
	public void notify(DelegateTask task) {
		String[] candidateUsers = { "a", "b", "c" };
		User user = null;
		if(userService != null) {
			 user = userService.selectById(5);
			 if(user != null) {
				 System.out.println("user->userId:" + user.getId());
				 System.out.println("user->username:" + user.getUsername());
			 }
		}
		//task.setVariable("users", Arrays.asList(candidateUsers));
		task.setAssignee("sss");
	}

}
