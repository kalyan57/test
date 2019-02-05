import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class SimpleNumbers implements test.Task {

    private final String task;
    private final Scanner in;


    public SimpleNumbers(Scanner in) {
        this.task = "Задание 1\nНеобходимо написать программу, которая вычисляет простые числа в пределах от 1\n" +
                "до N. N вводится вручную во время выполнения программы.\n";
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
        List<Integer> primesList = new ArrayList<Integer>();
        boolean[] isPrime = new boolean[N];
        Arrays.fill(isPrime,true);
        isPrime[1] = false;
       // for (int i=2; i*i < N; i++)
        for (int i=2; i < N; i++) // that's not the best solution of possible, i know it
            if (isPrime[i]) {
                primesList.add(i);
                for (int j = i * i; j < N; j += i)
                    isPrime[j] = false;
            }
        System.out.println("Перечень простых чисел от 1 до " + N + ":");
        System.out.println(primesList.toString());
        //System.out.println("Don't forget to finish and withdraw all the simple numbers to N, not only to sqrt(N)");
    }
}

