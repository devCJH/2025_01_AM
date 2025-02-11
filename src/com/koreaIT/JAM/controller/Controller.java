package com.koreaIT.JAM.controller;

import java.util.Scanner;

import com.koreaIT.JAM.dto.Member;

public abstract class Controller {
	public Scanner sc;
	public static Member loginedMember;
	public abstract void doAction(String cmd, String methodName);
	public abstract void makeTestData();
}
