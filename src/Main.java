import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        int count = 0;//счетчик верных файлов
        int googleBot = 0; //счетчик гуглбота
        int yandexBot = 0; //счетчик яндексбота
        while (true) {
            System.out.println("Путь файла:");
            String path = new Scanner(System.in).nextLine();//сканер с ввода и объявление переменной path
            //код, который определяет существует ли файл, к которому указан путь или нет
            File file = new File(path);
            boolean fileExist = file.exists(); //переменная будет равна true, если файл существует и false, если не существует
            boolean isDirectory = file.isDirectory();

            if (fileExist == false /*|| isDirectory == false*/) {
                System.out.println("Указанный файл не существует или указанный путь является путём к папке, а не файлу");
                // Проверка по переменным System.out.println(fileExist + " " + isDirectory);
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
                    if (length < minLength) minLength = length;//подсчет минимальной строки

                    String[] splitLine = line.split(" ");
                    if (splitLine.length >= 11) {
                        String userAgent = extractUserAgent(splitLine[11]);
                        if (userAgent != null) {
                            if (userAgent.contains("Googlebot")) {
                                googleBot++;
                            }
                            if (userAgent.contains("YandexBot")) {
                                yandexBot++;
                            }
                        }
                    }
                    // проверка System.out.println(Arrays.stream(splitLine).toList());

                }
                //вывод сообщений
                System.out.println("Общее количество строк" + totalLines);
                //System.out.println("Самая длинная строка" + maxLength);
                System.out.println("Доля запросов YandexBot:" + yandexBot / totalLines);
                System.out.println("Доля запросов GoogleBot:" + googleBot / totalLines);
                //System.out.println("Самая короткая строка" + minLength);
            } catch (LineTooLongException e) {
                System.out.println("Ошибка программы. Слишком длинная строка в файле");
                break;
            } catch (Exception ex) {
                System.out.println("Другие ошибки программы.Обратитесь к разработчику при этой ошибке" + ex);
            }
        }

    }

    //метод для извлечения части в первых скобках и выделения нужного фрагмента
    String userAgentString = "";

    private static String extractUserAgent(String userAgentString) {
        int firstB = userAgentString.indexOf("(");
        int lastB = userAgentString.lastIndexOf(")");
        String firstBrackets = userAgentString.substring(firstB + 1, lastB);
        String[] parts = firstBrackets.split(";");
        if (parts.length >= 2) {
            String fragment = parts[1];
            //System.out.println("Найденный фрагмент: "+ fragment);
            return firstBrackets;
        }
        return null;
    }
}
/*Напишите код, который будет разделять каждую строку на составляющие.
Компоненты:
1.IP-адрес клиента, который сделал запрос к серверу (в примере выше — 37.231.123.209).
2юДва пропущенных свойства, на месте которых обычно стоят дефисы,
но могут встречаться также и пустые строки ("").
3.Дата и время запроса в квадратных скобках.
4.Метод запроса (в примере выше — GET) и путь, по которому сделан запрос.
5.Код HTTP-ответа (в примере выше — 200).
6.Размер отданных данных в байтах (в примере выше — 61096).
7.Путь к странице, с которой перешли на текущую страницу, — referer
(в примере выше — “https://nova-news.ru/search/?rss=1&lg=1”).
8.User-Agent — информация о браузере или другом клиенте, который выполнил запрос.


В рамках текущего задания вам потребуется работать с фрагментами “User-Agent”,
в которых содержится информация о браузерах или других программах, обращавшихся к веб-сайту.

Задача программы — определять долю запросов к сайту от двух самых популярных поисковых
ботов — Googlebot и YandexBot. Информация о ботах содержится во фрагменте, описывающем User-Agent,
в следующем виде: "Mozilla/5.0 (compatible; YandexBot/3.0; +http://yandex.com/bots)"

●     Обработайте фрагмент User-Agent(!!!-1бал) следующим образом:

выделите часть, которая находится в первых скобках;
разделите эту часть по точке с запятой:
String[] parts = firstBrackets.split(";");
if (parts.length >= 2) {
   String fragment = parts[1];
}
Получившийся фрагмент будет соответствовать используемой программе, которая производит запросы.
●     Определяя равенство найденного фрагмента строкам Googlebot или YandexBot(!!!-5бал),
подсчитывайте количество строк в файле, соответствующих запросам от данных ботов.
●     Выведите в консоль долю запросов от YandexBot и Googlebot к веб-сайту
относительно общего числа сделанных запросов. (!!!-4бал)
●     Сделайте коммит в ветку master вашего репозитория access-log-parser.

//План работ:
в рамках if который лазит по строкам вытащить User-Agent фрагмент
фрагмент сложить в Аррей Лист
Обработка User-Апутеы блоком кода, который предлагаетя
Циклом пройти по коду искать GoogleBot или Яндекс Бот, делать подсчет
Расчетать долю запросов
Вывести в консоль долю запросов
Коммит
         */


