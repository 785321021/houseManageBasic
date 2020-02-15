package com.coins.controller.home;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class HomeController {
	@GetMapping("/c-api/home")
    public ModelAndView index(HttpSession session) {
		String browser ="sdf";
		ModelAndView view = new ModelAndView();
		// 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("/dist/index");
        // 设置属性
        view.addObject("title", "我的第一个WEB页面");
        view.addObject("desc", "欢迎进入battcn-web 系统");
        session.setAttribute("fds", "这个是试试Session可不可以用的");
        
      //取出session中的browser
        Object sessionBrowser = session.getAttribute("browser");
        if (sessionBrowser == null) {
            System.out.println("不存在session，设置browser=" + browser);
            session.setAttribute("browser", browser);
        } else {
            System.out.println("存在session，browser=" + sessionBrowser.toString());
        }
        
        view.addObject("aaa",session.getAttribute("browser").toString());
        return view;
    }
	
}
