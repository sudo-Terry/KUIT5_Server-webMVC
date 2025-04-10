package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

public class LogoutUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = MemoryUserRepository.getInstance().findUserById(
                request.getParameter("userId")
        );

        //세션 데이터 삭제
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        return "redirect:/";
    }
}
