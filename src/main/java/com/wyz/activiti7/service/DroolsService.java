package com.wyz.activiti7.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

 
public class DroolsService implements JavaDelegate
{
	public void execute(DelegateExecution execution)
	{	
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		System.out.println(execution.getVariable("reason"));
		System.out.println("++++++++++++++++++++++++++++++++++++++");
	}
}
