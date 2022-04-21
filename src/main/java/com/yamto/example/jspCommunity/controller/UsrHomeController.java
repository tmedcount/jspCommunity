package com.yamto.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.dto.Member;
import com.yamto.example.jspCommunity.service.MemberService;

public class UsrHomeController {
	public String showMain(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/home/main";
	}
}

