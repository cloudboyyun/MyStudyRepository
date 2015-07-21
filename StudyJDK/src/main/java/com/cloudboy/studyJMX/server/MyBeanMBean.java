package com.cloudboy.studyJMX.server;

public interface MyBeanMBean {
	
	public void setName(String name);

    public String getName();

    public void setAge(int age);

    public int getAge();

    /**
     * any method starting with get and set are considered
     * as attributes getter and setter methods, so I am using do* for operation.
     * @return
     */
    public String doChange();
}