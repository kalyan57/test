import java.io.BufferedInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class intSorter implements test.Task {

    private final String task;
    private final Scanner in;


    public intSorter(Scanner in) {
        this.task = "Задание 3\n" +
                "Необходимо написать алгоритм, выполняющий сортировку массива целых чисел по\n" +
                "убыванию. Массив вводится вручную во время выполнения программы.\n";
        this.in =in;
    }
    @Override
    public String getTask() {
        return this.task;
    }

    @Override
    public void performTask() {
        System.out.println(getTask());

        List<Integer> intList = getIntegers (in, "Введите массив целочисленных значений, разделенных пробелами:");
        intList.sort(Comparator.reverseOrder());
        System.out.println("Результат сортировки по убыванию:\n" + intList.toString());
    }

    private List<Integer> getIntegers (Scanner in, String invite) {

        List<Integer> ints = new ArrayList<>();
        String unParsed = "";
        String token = "";
        boolean listIsGot = false;

        while (!listIsGot) {
            System.out.println(invite);
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
                    ints.add(Integer.parseInt(token));
                } catch (RuntimeException e) {
                    unParsed += "(" + token + ")";
                }
            }
            if (!"".equals(unParsed)) System.out.println("Следующие значения не являются целочиленными: " + unParsed);
            sc.close();
            if (ints.size() > 1) {
                listIsGot = true;
                System.out.println("Получен массив подходящих значений: " + ints.toString());
            } else {
                System.out.println("Полученный массив (" + ints.toString() + ") содержит меньше двух значений. Здесь нечего сортировать");
                unParsed = "";
                ints.removeAll(ints);
            }
        }

        return ints;
    }
}