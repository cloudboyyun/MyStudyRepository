package com.cloudboy.study.newInJDK8.no3;

@FunctionalInterface
public interface PersonFactory<P extends Person> {
	P create(String firstName, String lastName);
}
