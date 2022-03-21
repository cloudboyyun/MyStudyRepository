package com.cloudboy;

import java.awt.Color;

public class Test {

	public static void main(String[] args) {
		float result[] = Color.RGBtoHSB(255, 0, 0, null);
		System.out.println(result[0] * 360);
		System.out.println(result[1] * 100);
		System.out.println(result[2] * 100);
	}

}
