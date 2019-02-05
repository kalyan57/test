import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.*;


public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Task[] tasks = new Task[] {
                new SimpleNumbers(sc),
                new FiboNumbers(sc),
                new intSorter(sc),
                new TwoTrues(sc),
                new Palindrome(sc),
                new TextCleaner(sc)
        };
        boolean repeat;
        boolean correctAnswer;
        for (Task task : tasks) {
            repeat  = true;
            while (repeat) {
                try {
                    task.performTask();
                    correctAnswer  = false;
                    while (!correctAnswer) {
                        try {
                            repeat = TwoTrues.parseBool(readStr(sc,"Еще разок? ДА = 1 или true, НЕТ = 0 или false")); // I know that's not a good idea to criss-cross the functions
                            correctAnswer = true;
                        } catch (RuntimeException e ) {
                            System.out.println("Не могу понять ДА или НЕТ? ДА = 1|true, HET = 0|false");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Таки поймано необработанное до сих пор исключение. Косяк...: " + e.getStackTrace());
                    repeat = false;
                }
            }
        }
        sc.close();
        System.out.println("Все задания выполнены. Bye");
    }

    public static String readStr (Scanner in,String invite) {

        System.out.println(invite + " (Чтобы завершить ввод нажмите ENTER)");
        String result = in.nextLine();
        return result;
    }

    public static int readNumber (Scanner in, String invite, int min) {

        Integer result = 0;
        String unParsed = "";
        String token = "";
        while (result < min) {
            System.out.println(invite + " (Чтобы завершить ввод нажмите ENTER)");
            /**
             * Далее создается новый экземпляр Scanner для считанной строки Scanner(System.in).nextLine()
             * Сделано для того чтобы можно было итерировать полученную строку методом Scanner.next() пока Scanner.hasNext() возвращает true
             * Если пытаться сделать тоже самое на Scanner(System.in) - то после считывания последнего в строке токена методом next()
             * последующий вызов любого из методов hasNext() или next() будет приводить к блокировке и ожиданию ввода с консоли,
             * а нам это уже не нужно, с вводом мы закончили
             */
            Scanner sc = new Scanner(new StringReader(in.nextLine()));

            while (sc.hasNext()) {
                try {
                    token = sc.next();
                    result = Integer.parseInt(token);
                    if (sc.hasNext()) {
                        System.out.println("Дано больше данных, чем нужно. Целочисленное значение обнаружено, все остальное игнорируется");
                    }
                    break;
                } catch (RuntimeException e) {
                    unParsed += " (" + token + ")";
                }

            }
            if (!"".equals(unParsed)) {
                System.out.println("Следующие введенные значения не удовлетворяют условиям: " + unParsed);
                unParsed = "";
            }
            sc.close();
            if (result < min) {
                System.out.println("Введенное значение (" + result + ") меньше " + min + ", не удовлетворяет условию");
            } else {
                System.out.println("Получено подходящее значение [" + result + "]");
            }
        }
        return result;
    }

    public interface Task {
        String getTask();
        void performTask();
    }
}









