import java.util.Scanner;

class TextCleaner implements test.Task {

    private final String task;
    private final Scanner in;

    public TextCleaner(Scanner in) {
        this.task = "Задание 6\n" +
                "Необходимо написать программу, которая удаляет из текста числа. Текст вводится\n" +
                "вручную во время выполнения программы.\n";
        this.in = in;
    }


    @Override
    public String getTask() {
        return this.task;
    }

    @Override
    public void performTask() {
        System.out.println(getTask());

        String str = test.readStr(in, "Введите строку, из которой нужно удалить числа:");
        //String res  = str.replaceAll("[-+]?[0-9]*\\.?[0-9]*", "");
        String res  = str.replaceAll("[-+]?[0-9]*\\.?[0-9]*", ""); // реальные числа удаляет только разделенные точкой
        if (res.length() == str.length()) System.out.println("В строке (" + str + ") чисел не обнаружено");
        else System.out.println("В исходной строке (" + str + ") обнаружены числа. После их удаления получена строка: (" + res + ")" );

    }
}