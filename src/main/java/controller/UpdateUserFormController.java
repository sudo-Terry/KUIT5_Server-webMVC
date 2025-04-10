package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwp.model.User;

import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
        String userId = request.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/update_form.jsp");

        dispatcher.forward(request, response);
    }
}