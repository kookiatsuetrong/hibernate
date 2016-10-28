package web;
import java.util.*;
import org.hibernate.*;
import javax.transaction.*;
import org.springframework.ui.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

import javax.servlet.http.*;

@Controller
public class MyController {

	@RequestMapping("/")
	String index(Model model) {
		Session database = factory.openSession();
		Query   query    = database.createQuery(
			"from Post");
		List<Post> post  = query.list();
		model.addAttribute("post", post);
		return "index";
	}

	@RequestMapping("/test")
	String test() {
		return "test.jsp";
	}
	
	/*
	@RequestMapping("/list") @ResponseBody
	List showCoffee() {
		Session database = factory.openSession();
		Query query = database.createQuery("from Coffee");
		List list = query.list();
		database.close();
		return list;
	}
	*/
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/login")
	String showLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	String checkLogin(HttpSession session, 
			String email, String password) {
		Session database = factory.openSession();
		Query query      = database.createQuery(
			"from Member where email = :e and password = sha2(:p, 512)");
		query.setParameter("e", email);
		query.setParameter("p", password);
		List list = query.list();
		if (list.size() == 0) {
			return "redirect:/login";
		} else {
			session.setAttribute("user", list.get(0));
			return "redirect:/profile";
		}
	}
	
	@RequestMapping("/profile")
	String showProfilePage(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		} else {
			Member user = (Member)session.getAttribute("user");
			model.addAttribute("user", user);
			return "profile";
		}
	}
	
	@RequestMapping("/create")
	String showCreatePage(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		} else {
			return "create";
		}
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	String createPost(String topic, String detail, HttpSession session) {
		Member member = (Member)session.getAttribute("user");
		if (member == null) {
			return "redirect:/login";
		} else {
			Post post   = new Post();
			post.topic  = topic;
			post.detail = detail;
			post.member = member.id;
			Session database = factory.openSession();
			database.save(post);
			return "redirect:/profile";
		}
	}

	@RequestMapping("/test-update") @ResponseBody
	int testUpdate() {
		Session d = factory.openSession();
		Query   q = d.createQuery(
			"update Post set topic=:t where id= :x");
		q.setParameter("t", "ทดสอบ");
		q.setParameter("x", 1L);
		return q.executeUpdate();
	}

}
