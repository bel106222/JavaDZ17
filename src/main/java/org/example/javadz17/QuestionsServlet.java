package org.example.javadz17;
import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "questionsServlet", value = "/questions-servlet")
public class QuestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Получаем переданную категорию из параметра запроса
        String category = request.getParameter("category");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta charset='UTF-8'><title>Ваш выбор</title></head>");
        out.println("<body>");

        if (category != null && !category.isEmpty()) {
            out.println("<h1>Вы выбрали категорию: " + category + "</h1>");
            // TODO Здесь надо добавить дальнейшую логику
        } else {
            out.println("<h1>Ошибка: категория не передана.</h1>");
        }

        out.println("<p><a href='" + request.getContextPath() + "/'>Выбрать другую категорию</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
}