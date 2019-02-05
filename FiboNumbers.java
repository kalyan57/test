import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FiboNumbers implements test.Task {

    private final String task;
    private final Scanner in;


    public FiboNumbers(Scanner in) {
        this.task = "Задание 2\n" +
                "Необходимо написать рекурсивный алгоритм, который находит числа Фибоначчи в\n" +
                "пределах от 1 до N. N вводится вручную во время выполнения программы.\n";
        this.in =in;
    }

    @Override
    public String getTask() {
        return this.task;
    }

    @Override
    public void performTask() {
        System.out.println(getTask());

        int N = test.readNumber(in, "Введите целое положительное число больше 1:", 2);
        int first = 0;
        int last = 1;

        List<Integer> fibos = new ArrayList<Integer>();
        fibos.add(first);
        fibos.add(last);
        int next = first + last;
        while (next <= N) {
            fibos.add(next);
            first = last;
            last = next;
            next = first + last;
        }
        System.out.println("Ряд Фибоначчи до " + N + ":");
        System.out.println(fibos.toString());
        System.out.println();

    }
}