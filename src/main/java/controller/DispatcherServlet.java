package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    //post, get을 모두 받아서 처리하고 우선순위가 높음
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        // contextPath 제외한 순수 path 추출
        System.out.println("Request From : " + uri);
        String path = uri.substring(request.getContextPath().length());

        Controller target = RequestMapper.getInstance().getController(path);
        if (target == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청한 경로를 찾을 수 없습니다.");
            return;
        }

        try {
            String viewPath = target.execute(request, response);
            System.out.println("Respond With : " + viewPath);
            if (viewPath == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청한 경로를 찾을 수 없습니다.");
            }

            if (viewPath.startsWith("redirect:")) {
                response.sendRedirect(viewPath.substring("redirect:".length()));
            } else {
                request.getRequestDispatcher(viewPath).forward(request, response);;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
