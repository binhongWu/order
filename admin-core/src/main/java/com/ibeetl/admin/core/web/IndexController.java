package com.ibeetl.admin.core.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ibeetl.admin.core.entity.CoreOrg;
import com.ibeetl.admin.core.entity.CoreUser;
import com.ibeetl.admin.core.rbac.UserLoginInfo;
import com.ibeetl.admin.core.rbac.tree.MenuItem;
import com.ibeetl.admin.core.service.CorePlatformService;
import com.ibeetl.admin.core.service.CoreUserService;
import com.ibeetl.admin.core.util.HttpRequestLocal;
import com.ibeetl.admin.core.util.PlatformException;

/**
 * 登录
 */
@Controller
public class IndexController {

	@Autowired
	CorePlatformService platformService;

	@Autowired
	CoreUserService userService;

	@Autowired
	HttpRequestLocal httpRequestLocal;

	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView login() {
		ModelAndView view = new ModelAndView("/login.html");
		return view;
	}

	/**
	 * 登录信息填入，验证通过后重定向到首页
	 * @param code
	 * @param password
	 * @return
	 */
	@PostMapping("/login.do")
	public ModelAndView login(String code, String password) {
		UserLoginInfo info = userService.login(code, password);
		if (info == null) {
			throw new PlatformException("用户名或密码错误");
		}
		//得到当前用户信息
		CoreUser user = info.getUser();
		// 请求当前登录人的部门、岗位信息
		CoreOrg currentOrg = info.getOrgs().get(0);
		for (CoreOrg org : info.getOrgs()) {
			if (org.getId() == user.getOrgId()) {
				currentOrg = org;
				break;
			}
		}
		info.setCurrentOrg(currentOrg);
		// 记录登录信息到session   默认时间登录有效时间是30分钟
		this.platformService.setLoginUser(info.getUser(), info.getCurrentOrg(), info.getOrgs());
		// 重定向到首页
		ModelAndView view = new ModelAndView("redirect:/index.do");
		return view;
	}

	/**
	 * 首页  --> 项目登录进来后所见到的页面，此时会加载菜单
	 * @return
	 */
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/index.html");
		CoreUser currentUser = platformService.getCurrentUser();
		if(currentUser == null){
			return new ModelAndView("redirect:/");
		}
		// 根据当前登录人的岗位信息来加载该用户有权限的菜单
		Long orgId = platformService.getCurrentOrgId();
		MenuItem menuItem = platformService.getMenuItem(currentUser.getId(), orgId);
		view.addObject("menus", menuItem);
		return view;

	}

	/**
	 * 退出登录   清除当前登录人的登录信息  并重定向到登录页
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration eum = session.getAttributeNames();
		while(eum.hasMoreElements()) {
			String key = (String)eum.nextElement();
			session.removeAttribute(key);
		}
		ModelAndView view = new ModelAndView("redirect:/");
		return view;
	}






	/**
	 * 不管
	 * @param request
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/changeOrg.do")
	public ModelAndView changeOrg(HttpServletRequest request,Long orgId) {
		platformService.changeOrg(orgId);
		ModelAndView view = new ModelAndView("redirect:/index.do");
		return view;
	}

}
