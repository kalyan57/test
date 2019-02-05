import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TwoTrues implements test.Task {

    private final String task;
    private final Scanner in;


    public TwoTrues(Scanner in) {
        this.task = "Задание 4\nНеобходимо написать программу, считывающую четыре логических значения и\n" +
                "печатающую “True” в том случае, если ровно два из них истинны. Значения вводятся\n" +
                "вручную во время выполнения программы.\n";
        this.in =in;
    }

    @Override
    public String getTask() {
        return this.task;
    }

    @Override
    public void performTask() {
        System.out.println(getTask());
        String invite = "Последовательно введите четыре булевых значения. Принимаются значения TRUE|FALSE и 1|0";
        boolean[] b = new boolean[4];
        b = getBools(in, invite);
        StringBuilder boolsStr = new StringBuilder();
        for (boolean bool : b) boolsStr.append(bool).append(" ");
        System.out.println("Получен корректный массив булевых значений: [ " + boolsStr.toString() + "]" );
        int sum = 0;
        for (boolean bool : b) {
            if (bool) {sum += 1;}
        }
        //boolean result = ((b[0]&&b[1])&&(!b[2]&&!b[3]))||((!b[0]&&!b[1])&&(b[2]&&b[3]))||((b[0]||b[1])&&(b[2]||b[3])); still doesn't work
        boolean result = false;
        if (sum == 2) { result = true; }
        System.out.println("Из них ровно два истинны?: " + result);

    }


    private boolean[] getBools(Scanner in, String invite) {
        boolean[] bools = new boolean[4];
        boolean doRead = true;
        String err;
        List<String> tokens = new ArrayList<>();

        while (doRead) {
            err = "";
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
                tokens.add(sc.next());
            }
            sc.close();
            if (tokens.size() < 4) {err = "Введено меньше черырех значений.";}
            else {
                for (int i = 0; i < 4; i++) {
                    try {
                        bools[i] = parseBool(tokens.get(i));
                    } catch (RuntimeException e) {
                        err += e + "\n";
                    }
                }
            }

            if ("".equals(err)) { doRead = false; }
            else {
                System.out.println("Ошибка ввода данных: \n" + err);
                System.out.println("Повторите ввод. Примеры корректного ввода: \n" +
                        "true false true true\n" +
                        "true 0 1 false\n" +
                        "0 1 0 0\n" +
                        "Система не примет меньше 4 значений и использует первые 4, если будет дано больше\n");
                err = "";
                tokens.removeAll(tokens);
            }
        }
        return bools;
    }

    public static boolean parseBool (String token) {
        if ("true".equalsIgnoreCase(token)) return true;
        else if ("false".equalsIgnoreCase(token)) return false;
        else {
            try {
                int flag = Integer.parseInt(token);
                switch (flag) {
                    case 0:
                        return false;
                    case 1:
                        return true;
                    default:
                        throw new IllegalArgumentException("Цифровые значения принимаются только 1 или 0");
                }
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Значение (" + token + ") невозможно рапознать как БУЛЕВОЕ.");
            }
        }

    }

}