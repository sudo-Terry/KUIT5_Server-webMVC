package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser == null || !sessionUser.getUserId().equals(userId)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 접근입니다: 자신의 정보만 수정할 수 있습니다.");
        }

        MemoryUserRepository.getInstance().findUserById(userId).update(
                new User(userId, password, name, email)
        );

        return "redirect:/user/userList";
    }
}