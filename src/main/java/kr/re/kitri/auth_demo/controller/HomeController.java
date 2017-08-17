package kr.re.kitri.auth_demo.controller;

import kr.re.kitri.auth_demo.domain.Member;
import kr.re.kitri.auth_demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpSession session, Model model,
                        @RequestParam("user_id") String userId, @RequestParam("pw") String pw) {

        Member member = this.authService.authenticate(userId, pw);

        if (member != null) {
            session.setAttribute("_MEMBER_SESSION_", member);   // 클라이언트마다 하나씩 독립적으로 생성되는 객체
            model.addAttribute("member", member);               // main.html 에 member 전달
        }

        return "main";
    }

    /**
     *  Ajax 처리
     */
    @GetMapping("/products")
    @ResponseBody               // JSON 처리
    public List<Member> products() {

        List<Member> members = new ArrayList<>();
        members.add(new Member("kim", "soongon"));
        members.add(new Member("park", "chanho"));
        members.add(new Member("moon", "jaein"));
        members.add(new Member("kim", "youngsam"));

        return members;
    }

    @GetMapping("/viewProducts")
    public String viewProducts(HttpSession session, Model model) {

        Member member = (Member)session.getAttribute("_MEMBER_SESSION_");   // Object 타입으로 리턴되기 때문에 타입 맞춰준다.

        if (member == null) {
            return "index";
        }
        model.addAttribute("member", member);
        return "products";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}
