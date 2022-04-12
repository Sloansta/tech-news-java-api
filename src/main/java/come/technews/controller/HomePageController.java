package come.technews.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import come.technews.model.Post;
import come.technews.model.User;
import come.technews.repository.*;

@Controller
public class HomePageController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	VoteRepository voteRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		if(request.getSession(false) != null) {
			return "redirect:/";
		}
		
		model.addAttribute("user", new User());
		return "login";
	}
	
	@GetMapping("/users/logout")
	public String logout(HttpServletRequest request) {
		if(request.getSession(false) != null) {
			request.getSession().invalidate();
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/")
	public String homepageSetUp(Model model, HttpServletRequest request) {
		User sessionUser = new User();
		
		if(request.getSession(false) != null) {
			sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
			model.addAttribute("loggedIn", sessionUser.isLoggedIn());
		} else {
			model.addAttribute("loggedIn", false);
		}
		
		List<Post> postList = postRepository.findAll();
		for(Post p : postList) {
			p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
			User user = userRepository.getById(p.getUserId());
			p.setUserName(user.getUserName());
		}
		
		model.addAttribute("postList", postList);
		model.addAttribute("loggedIn", sessionUser.isLoggedIn());
		model.addAttribute("point", "point");
		model.addAttribute("points", "points");
		
		return "homepage";
	}
}
