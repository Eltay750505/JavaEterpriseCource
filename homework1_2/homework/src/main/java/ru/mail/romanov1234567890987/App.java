package ru.mail.romanov1234567890987;

import ru.mail.romanov1234567890987.service.HomeworkService;
import ru.mail.romanov1234567890987.service.impl.HomeworkServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        HomeworkService homeworkService = new HomeworkServiceImpl();
        // homeworkService.runFirstTask();
        // homeworkService.runSecondTask();
        homeworkService.runThirdTask();
    }
}
