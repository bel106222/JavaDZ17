package org.example.javadz17;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "questionsServlet", value = "/questions-servlet")
public class QuestionsServlet extends HttpServlet {

    // Статический список всех вопросов, заполняется один раз
    private static final List<Question> ALL_QUESTIONS = new ArrayList<>();

    static {
        // История
        ALL_QUESTIONS.add(new Question("История", "В каком году началась Вторая мировая война?", "1937", "1939", "1941", 'B'));
        ALL_QUESTIONS.add(new Question("История", "Кто был первым императором Российской империи?", "Иван Грозный", "Пётр I", "Екатерина II", 'B'));
        ALL_QUESTIONS.add(new Question("История", "Какое древнее сооружение считается одним из Семи чудес света и находится в Гизе?", "Храм Артемиды", "Висячие сады Семирамиды", "Пирамида Хеопса", 'C'));
        ALL_QUESTIONS.add(new Question("История", "Кто написал «Капитал» — фундаментальный труд по политической экономии?", "Фридрих Энгельс", "Карл Маркс", "Владимир Ленин", 'B'));
        ALL_QUESTIONS.add(new Question("История", "Как назывался первый в мире пилотируемый космический корабль, на котором Юрий Гагарин совершил полёт?", "Союз", "Восход", "Восток-1", 'C'));
        ALL_QUESTIONS.add(new Question("История", "В каком веке произошла Куликовская битва?", "XII век", "XIV век", "XVI век", 'B'));
        ALL_QUESTIONS.add(new Question("История", "Какая цивилизация известна своей письменностью в виде клинописи и висячими садами?", "Древний Египет", "Древняя Греция", "Месопотамия (Вавилон)", 'C'));
        ALL_QUESTIONS.add(new Question("История", "Кто был лидером большевиков в 1917 году?", "Лев Троцкий", "Иосиф Сталин", "Владимир Ленин", 'C'));
        ALL_QUESTIONS.add(new Question("История", "Какое событие положило начало Великой французской революции?", "Взятие Бастилии", "Казнь Людовика XVI", "Поход на Версаль", 'A'));
        ALL_QUESTIONS.add(new Question("История", "Как назывался мирный договор, завершивший Первую мировую войну для Германии?", "Версальский договор", "Трианонский договор", "Брестский мир", 'A'));

        // Спорт
        ALL_QUESTIONS.add(new Question("Спорт", "Сколько игроков в одной команде на поле в классическом футболе (включая вратаря)?", "10", "11", "12", 'B'));
        ALL_QUESTIONS.add(new Question("Спорт", "Кто из этих спортсменов удерживает рекорд по количеству золотых олимпийских медалей в истории?", "Майкл Фелпс", "Усэйн Болт", "Лариса Латынина", 'A'));
        ALL_QUESTIONS.add(new Question("Спорт", "Какой теннисный турнир считается самым престижным и проводится на травяных кортах в Лондоне?", "Открытый чемпионат США", "Уимблдон", "Roland Garros", 'B'));
        ALL_QUESTIONS.add(new Question("Спорт", "В каком виде спорта используется снаряд «штанга» и упражнения «рывок» и «толчок»?", "Пауэрлифтинг", "Тяжёлая атлетика", "Бодибилдинг", 'B'));
        ALL_QUESTIONS.add(new Question("Спорт", "Как называется высшая лига по баскетболу в Северной Америке?", "NBA", "NCAA", "FIBA", 'A'));
        ALL_QUESTIONS.add(new Question("Спорт", "Какой футбольный клуб выиграл Лигу чемпионов УЕФА больше всех раз (на 2024 год)?", "Бавария", "Милан", "Реал Мадрид", 'C'));
        ALL_QUESTIONS.add(new Question("Спорт", "Что означает «фальстарт» в лёгкой атлетике?", "Преждевременное начало движения до выстрела стартёра", "Финиш не в своей дорожке", "Падение после старта", 'A'));
        ALL_QUESTIONS.add(new Question("Спорт", "Кто является самым титулованным гонщиком «Формулы-1» по числу чемпионских титулов?", "Айртон Сенна", "Михаэль Шумахер и Льюис Хэмилтон (по 7)", "Себастьян Феттель", 'B'));
        ALL_QUESTIONS.add(new Question("Спорт", "В каком виде спорта используется клюшка, шайба и ворота с сеткой?", "Хоккей на траве", "Хоккей с шайбой", "Кёрлинг", 'B'));
        ALL_QUESTIONS.add(new Question("Спорт", "Какое максимальное количество очков можно набрать за один бросок в дартсе?", "180", "60", "100", 'B'));

        // Поп-культура
        ALL_QUESTIONS.add(new Question("Поп-культура", "Какой певец известен как «Король поп-музыки»?", "Prince", "Michael Jackson", "Freddie Mercury", 'B'));
        ALL_QUESTIONS.add(new Question("Поп-культура", "Кто сыграл роль Железного человека в киновселенной Marvel?", "Крис Эванс", "Роберт Дауни мл.", "Крис Хемсворт", 'B'));
        ALL_QUESTIONS.add(new Question("Поп-культура", "Как называется фэнтези-серия книг Дж. К. Роулинг о юном волшебнике?", "Властелин колец", "Хроники Нарнии", "Гарри Поттер", 'C'));
        ALL_QUESTIONS.add(new Question("Поп-культура", "Какой фильм режиссёра Джеймса Кэмерона стал первым в истории, собравшим более $2 млрд в мировом прокате?", "Титаник", "Аватар", "Терминатор 2", 'A'));
        ALL_QUESTIONS.add(new Question("Поп-культура", "Кто из этих музыкантов был участником группы The Beatles?", "Элтон Джон", "Пол Маккартни", "Дэвид Боуи", 'B'));
        ALL_QUESTIONS.add(new Question("Поп-культура", "Как зовут персонажа «Звёздных войн», который произносит фразу «Да пребудет с тобой Сила»?", "Дарт Вейдер", "Оби-Ван Кеноби", "Хан Соло", 'B'));
        ALL_QUESTIONS.add(new Question("Поп-культура", "Кто исполнил заглавную песню для фильма «Титаник» – «My Heart Will Go On»?", "Мэрайя Кэри", "Селин Дион", "Уитни Хьюстон", 'B'));
        ALL_QUESTIONS.add(new Question("Поп-культура", "Какой мультсериал создал Стивен Хилленберг про морскую губку?", "Губка Боб Квадратные Штаны", "Лагерь Лазер", "Рик и Морти", 'A'));
        ALL_QUESTIONS.add(new Question("Поп-культура", "Какой видеоигрой управляет персонаж по имени Марио?", "Sonic the Hedgehog", "Super Mario Bros.", "The Legend of Zelda", 'B'));
        ALL_QUESTIONS.add(new Question("Поп-культура", "Кто исполнил роль Джокера в фильме «Тёмный рыцарь» (2008)?", "Джаред Лето", "Хоакин Феникс", "Хит Леджер", 'C'));

        // Космос
        ALL_QUESTIONS.add(new Question("Космос", "Какая планета Солнечной системы самая большая по диаметру?", "Сатурн", "Юпитер", "Нептун", 'B'));
        ALL_QUESTIONS.add(new Question("Космос", "Как называется галактика, в которой находится Земля?", "Туманность Андромеды", "Млечный Путь", "Большое Магелланово Облако", 'B'));
        ALL_QUESTIONS.add(new Question("Космос", "Кто был первым человеком, ступившим на Луну?", "Юрий Гагарин", "Нил Армстронг", "Базз Олдрин", 'B'));
        ALL_QUESTIONS.add(new Question("Космос", "Что такое «чёрная дыра» простыми словами?", "Область с очень высокой плотностью и гравитацией, которая не выпускает свет", "Звезда, взорвавшаяся сверхновой", "Пустота между галактиками", 'A'));
        ALL_QUESTIONS.add(new Question("Космос", "Сколько планет в Солнечной системе?", "8", "9", "10", 'A'));
        ALL_QUESTIONS.add(new Question("Космос", "Как называется ближайшая к Солнцу планета?", "Венера", "Меркурий", "Марс", 'B'));
        ALL_QUESTIONS.add(new Question("Космос", "Какое космическое тело потеряло статус планеты в 2006 году?", "Плутон", "Эрида", "Церера", 'A'));
        ALL_QUESTIONS.add(new Question("Космос", "Что означает «сверхновая»?", "Новая планета", "Взрыв звезды в конце её жизни с огромным выделением энергии", "Рождение чёрной дыры без взрыва", 'B'));
        ALL_QUESTIONS.add(new Question("Космос", "Как называется российский (советский) многоразовый космический корабль, похожий на американский шаттл?", "Буран", "Союз", "Прогресс", 'A'));
        ALL_QUESTIONS.add(new Question("Космос", "На какой планете идут «алмазные дожди» (из-за высокой температуры и давления атмосфера может превращать углерод в алмазы)?", "Юпитер", "Сатурн", "Нептун", 'C'));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String category = request.getParameter("category");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta charset='UTF-8'><title>Викторина: " + (category != null ? category : "") + "</title></head>");
        out.println("<body>");

        if (category == null || category.isEmpty()) {
            out.println("<h1>Ошибка: категория не передана.</h1>");
        } else {
            // Фильтрация вопросов по категории
            List<Question> categoryQuestions = ALL_QUESTIONS.stream()
                    .filter(q -> q.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());

            if (categoryQuestions.isEmpty()) {
                out.println("<h1>Вопросы категории \"" + category + "\" не найдены.</h1>");
            } else {
                out.println("<h1>Вопросы по категории: " + category + "</h1>");
                //когда пользователь нажимает кнопку отправки («Проверить ответы»),
                // выбранные radio-кнопки и скрытое поле category уходят POST-запросом
                // на адрес [контекст]/questions-servlet. Там их перехватывает
                // метод doPost этого же класса, который подсчитывает правильные ответы и выводит результат
                out.println("<form method='post' action='" + request.getContextPath() + "/questions-servlet'>");
                // Скрытое поле для передачи категории при отправке
                out.println("<input type='hidden' name='category' value='" + category + "'>");

                int idx = 0;
                for (Question q : categoryQuestions) {
                    out.println("<fieldset style='margin-bottom:15px;'>");
                    out.println("<legend><b>" + (idx + 1) + ". " + q.getText() + "</b></legend>");

                    String radioName = "q_" + idx;
                    out.println("<label><input type='radio' name='" + radioName + "' value='A'> " + q.getOptionA() + "</label><br>");
                    out.println("<label><input type='radio' name='" + radioName + "' value='B'> " + q.getOptionB() + "</label><br>");
                    out.println("<label><input type='radio' name='" + radioName + "' value='C'> " + q.getOptionC() + "</label><br>");
                    out.println("</fieldset>");
                    idx++;
                }
                out.println("<br><input type='submit' value='Проверить ответы'>");
                out.println("</form>");
            }
        }

        out.println("<p><a href='" + request.getContextPath() + "/'>Выбрать другую категорию</a></p>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String category = request.getParameter("category");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta charset='UTF-8'><title>Результаты: " + (category != null ? category : "") + "</title></head>");
        out.println("<body>");

        if (category == null || category.isEmpty()) {
            out.println("<h1>Ошибка: категория не передана.</h1>");
        } else {
            List<Question> categoryQuestions = ALL_QUESTIONS.stream()
                    .filter(q -> q.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());

            if (categoryQuestions.isEmpty()) {
                out.println("<h1>Вопросы категории \"" + category + "\" не найдены.</h1>");
            } else {
                int correctCount = 0;
                out.println("<h1>Результаты по категории: " + category + "</h1>");
                out.println("<ol>");

                int idx = 0;
                for (Question q : categoryQuestions) {
                    String userAnswer = request.getParameter("q_" + idx);
                    boolean isCorrect = userAnswer != null && userAnswer.length() == 1
                            && userAnswer.charAt(0) == q.getCorrect();
                    if (isCorrect) {
                        correctCount++;
                    }

                    out.println("<li>");
                    out.println("<b>" + q.getText() + "</b><br>");
                    out.println(isCorrect ? "V " : "X ");
                    out.println("Ваш ответ: " + (userAnswer != null ? userAnswer : "не выбрано"));
                    out.println(" | Правильный: " + q.getCorrect());
                    out.println("</li><br>");
                    idx++;
                }
                out.println("</ol>");

                out.println("<h2>Всего правильных ответов: " + correctCount + " из " + categoryQuestions.size() + "</h2>");
            }
        }

        out.println("<p><a href='" + request.getContextPath() + "/'>Выбрать другую категорию</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
}