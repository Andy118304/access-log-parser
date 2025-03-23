import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        int count = 0;//счетчик верных файлов
        while (true) {
            System.out.println("Путь файла:");
            String path = new Scanner(System.in).nextLine();//сканер с ввода и объявление переменной path
            //код, который определяет существует ли файл, к которому указан путь или нет
            File file = new File(path);
            boolean fileExist = file.exists(); //переменная будет равна true, если файл существует и false, если не существует

            boolean isDirectory = file.isDirectory();
                if (fileExist == false || isDirectory == false) {
                    System.out.println("Указанный файл не существует или указанный путь является путём к папке, а не файлу");
                    continue; //пропускаем оставшуюся часть цикла
                } else if (fileExist == true && isDirectory == true) {
                    count++;
                    System.out.println("Путь указан верно.Это файл номер"+count);

                }
        }


        /*System.out.println ("Введите первое число:");
        int firstNumber = new Scanner(System.in).nextInt();
                System.out.println ("Введите второе число:");
        int secondNumber = new Scanner(System.in).nextInt();
        double quotient = (double) firstNumber/secondNumber;
        System.out.println ("Разность чисел:"+(firstNumber-secondNumber));
        System.out.println ("Сумма чисел:"+(firstNumber+secondNumber));
        System.out.println ("Произведение чисел:"+(firstNumber*secondNumber));
        System.out.println ("Частное чисел:"+quotient);

         */


    }
}