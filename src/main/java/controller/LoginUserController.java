package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

public class LoginUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션 정보 저장
        User user = MemoryUserRepository.getInstance().findUserById(
                request.getParameter("userId")
        );

        if (user != null && user.getPassword().equals(request.getParameter("password"))) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            return "redirect:/";
        }else {
            return "redirect:/user/login_failed.jsp";
        }
    }
}
