import java.util.Scanner;

class Palindrome implements test.Task {

    private final String task;
    private final Scanner in;


    public Palindrome(Scanner in) {
        this.task = "Задание 5\n" +
                "Необходимо написать программу, которая проверяет слово на палиндромность.\n" +
                "Слово для проверки вводится вручную во время выполнения программы.\n";
        this.in =in;
    }

    @Override
    public String getTask() {
        return this.task;
    }

    @Override
    public void performTask() {
        System.out.println(getTask());

        String str = test.readStr(in, "Введите строку для проверки на палиндромность:");
        System.out.println("Является ли строка (" + str + ") палиндромной?: " + checkPalindrome(str));
        str = str.toLowerCase();
        System.out.println("а без учета регистра? (" + str + "): " + checkPalindrome(str));
        str = str.toLowerCase().replaceAll("[^a-z0-9]", "");
        System.out.println("наконец, без учера регистра, занков препинания и пробелов? (" + str + "): " +
                            checkPalindrome(str));


    }

    private boolean checkPalindrome(String s) {
        int len = s.length();
        for (int i =0; i < (len/2); i++) {
            if (s.charAt(i) != s.charAt(len-1-i)) return false;
        }
        return true;
    }
}