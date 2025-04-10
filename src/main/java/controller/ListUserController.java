package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.util.Collection;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에서 로그인 유저 정보 가져오기
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // 로그인하지 않은 경우 로그인 페이지로
        if (user == null) {
            return "redirect:/user/login.jsp";
        }

        // 로그인한 유저 로직
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        request.setAttribute("users", users);

        return "/user/list.jsp";
    }
}
