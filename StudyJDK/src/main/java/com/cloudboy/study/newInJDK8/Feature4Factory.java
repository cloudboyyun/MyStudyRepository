package com.cloudboy.study.newInJDK8;

public interface Feature4Factory<P extends Feature4> {
	P create(String firstName, String lastName);
}
