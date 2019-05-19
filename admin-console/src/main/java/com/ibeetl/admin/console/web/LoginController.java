package com.ibeetl.admin.console.web;

import com.ibeetl.admin.console.conf.PlatformService;
import com.ibeetl.admin.core.util.PlatformException;
import com.ibeetl.cms.entity.CustomerInfor;
import com.ibeetl.cms.entity.ProductInfor;
import com.ibeetl.cms.service.CustomerInforService;
import com.ibeetl.cms.service.ProductInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

/**
 * 没有使用了  但是代码删不掉，所以只能放着
 * @author Administrator
 */
@Controller
@RequestMapping("/index")
public class LoginController {

    @Autowired
    private CustomerInforService customerInforService;
    @Autowired
    PlatformService platformService;
    @Autowired
    private ProductInforService productInforService;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login2")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("/login2.html");
        return view;
    }


    /**
     * 首页
     * @return
     */
    @RequestMapping("/index2.do")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/index2.html");
        CustomerInfor currentUser = platformService.getCurrentUser();
        if(currentUser == null){
            return new ModelAndView("redirect:/login2");
        }
        return view;
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/logout2.do")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration eum = session.getAttributeNames();
        while(eum.hasMoreElements()) {
            String key = (String)eum.nextElement();
            session.removeAttribute(key);
        }
        ModelAndView view = new ModelAndView("redirect:/index/login2");
        return view;
    }

    @RequestMapping("/getUserInfo.json")
    public ModelAndView getCurrentUserInfo(){
        CustomerInfor currentUser = platformService.getCurrentUser();
        ModelAndView view = new ModelAndView("redirect:/index2.do");
        view.addObject("currentUser",currentUser);
        return view;
    }
}
