import java.io.BufferedReader;
import java.io.FileReader;
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
                System.out.println("Путь указан верно.Это файл номер" + count);

            }

            int totalLines = 0;  //переменная - количество строк в файле
            int maxLength = Integer.MAX_VALUE; // переменная - длина самой длинной строки в файле
            int minLength = Integer.MIN_VALUE;// переменная - длина самой короткой строки в файле
            //исключение, условие в задании
            try {
                //код, который будет построчно читать указанный файл
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    totalLines++; //считаем строки
                    if (length > 1024) {
                        throw new LineTooLongException("Строка длинной более 1024 символа"); //выбрасывание исключения при превышении 1024
                    }
                    if (length > maxLength) maxLength = length; //подсчет максимальной строки
                    if (length > minLength) minLength = length;//подсчет минимальной строки
                }
                //вывод сообщений
                System.out.println("Общее количество строк" + totalLines);
                System.out.println("Самая длинная строка" + maxLength);
                System.out.println("Самая короткая строка" + minLength);
            } catch (LineTooLongException e) {
                System.out.println("Ошибка программы. Слишком длинная строка в файле");
                break;
            } catch (Exception ex) {
                System.out.println("Другие ошибки программы.Обратитесь к разработчику при этой ошибке");
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