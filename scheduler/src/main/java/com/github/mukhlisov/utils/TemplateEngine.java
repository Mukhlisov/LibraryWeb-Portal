package com.github.mukhlisov.utils;

public class TemplateEngine {

    private final static String TEMPLATE_SUBJECT = "Напоминание: возврат книги %s";

    private final static String TEMPLATE_BODY = """
            %s %s,
            
            Напоминаем, что %s срок возврата книги: %s.
            Пожалуйста, не забудьте вернуть её вовремя, чтобы избежать штрафов за просрочку.
            
            С наилучшими пожеланиями,
            LibHub
            """;

    public static String createSubject(String date){
        return String.format(TEMPLATE_SUBJECT, date);
    }

    public static String createBody(String firstName, String lastName, String title, String bookName){
        return String.format(TEMPLATE_BODY, title, firstName, lastName, bookName);
    }
}
