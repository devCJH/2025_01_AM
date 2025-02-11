package com.koreaIT.JAM.container;

import com.koreaIT.JAM.dao.ArticleDao;
import com.koreaIT.JAM.dao.MemberDao;
import com.koreaIT.JAM.service.ArticleService;
import com.koreaIT.JAM.service.MemberService;

public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static MemberService memberService;
	public static MemberDao memberDao;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		memberService = new MemberService();
		articleService = new ArticleService();
	}
}
