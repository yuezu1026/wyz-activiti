package com.wyz.activiti7.listener;

import java.util.Arrays;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MyTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask task) {
		String[] candidateUsers = { "a", "b", "c" };
		task.setVariable("users", Arrays.asList(candidateUsers));
	}

}
