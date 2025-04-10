package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;
import java.util.Collection;

@WebServlet("/user/userList")
public class ListUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션에서 로그인 유저 정보 가져오기
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // 로그인하지 않은 경우 로그인 페이지로
        if (user == null) {
            resp.sendRedirect("/user/login.jsp");
            return;
        }

        // 로그인한 유저 로직
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);

        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        // Redirect와는 달리 서버 내에서 다른 Servlet으로 포워딩
        rd.forward(req, resp);
    }
}
