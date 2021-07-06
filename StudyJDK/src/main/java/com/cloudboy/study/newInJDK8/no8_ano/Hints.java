package com.cloudboy.study.newInJDK8.no8_ano;

public @interface Hints {
	Hint[] value();
}

@interface Hint {
    String value();
}
