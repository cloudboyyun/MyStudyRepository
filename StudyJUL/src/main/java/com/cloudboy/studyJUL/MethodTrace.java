package com.cloudboy.studyJUL;

class MethodTrace {

	public String getTrueMethod() {
		StackTraceElement elements[] = (new Throwable()).getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement element = elements[i];
			System.out.println("(" + i + ")" + element.getClassName() + "\t"
					+ element.getFileName() + "\t" + element.getLineNumber()
					+ "\t" + element.getMethodName());
		}
		return elements[2].getClassName() + "#" + elements[2].getMethodName()
				+ " " + elements[2].getLineNumber();
	}

	public void mymethod() {
		log("hello");
	}

	public void log(String s) {
		System.out.println(new MethodTrace().getTrueMethod() + "[INFO]: " + s);
	}

	public static void main(String[] args) {
		new MethodTrace().mymethod();
	}
}