import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    final String ip;
    final LocalDateTime dateAndTime;//[25/Sep/2022:06:34:53
    final MethodsEnum method;
    final String pathRequest;
    final String typeHTTP;
    final int codeResponse;
    final long sizeDate;
    final String referer;
    final String userAgent;

    public LogEntry(String line) {

        String[] splitLine = line.split(" ");
        //System.out.println("1:"+splitLine[1]);
        //System.out.println("2:"+splitLine[2]);
        //System.out.println("3:"+splitLine[3]);
        //System.out.println("4:"+splitLine[4]);
        //System.out.println("5:"+splitLine[5]);
        //System.out.println("6:"+splitLine[6]);
        //System.out.println("7:"+splitLine[7]);
        //System.out.println("8:"+splitLine[8]);
        //System.out.println("9:"+splitLine[9]);
        //System.out.println("10:"+splitLine[10]);
        //System.out.println("11:"+splitLine[11]);
        int ipEnd = line.indexOf(' ');
        this.ip = line.substring(0,ipEnd);
        //System.out.println("IP:"+ip);
        String previousDateAndTime = splitLine[3];//присвоение параметра с квадратными скобками первыми
        previousDateAndTime = previousDateAndTime.substring(1);//обрезание передней скобки
        this.dateAndTime = LocalDateTime.parse(previousDateAndTime, DateTimeFormatter.ofPattern("dd/MMM/yyyy':'HH:mm:ss", Locale.ENGLISH));
        String previousMethod = splitLine[5];
        previousMethod = previousMethod.substring(1);
        this.method = MethodsEnum.valueOf(previousMethod);
        this.pathRequest = splitLine[6];
        this.typeHTTP = splitLine[7];
        this.codeResponse = Integer.parseInt(splitLine[8]);
        this.sizeDate = Long.parseLong(splitLine[9]);
        this.referer = splitLine[10];
        String previousUA = " ";
        for (int i = 11; i < splitLine.length; i++) {
            previousUA = previousUA + splitLine[i];
        }
        this.userAgent = previousUA;
    }

    public String getIp() {
        return ip;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public MethodsEnum getMethod() {
        return method;
    }

    public String getPathRequest() {
        return pathRequest;
    }

    public String getTypeHTTP() {
        return typeHTTP;
    }

    public int getCodeResponse() {
        return codeResponse;
    }

    public long getSizeDate() {
        return sizeDate;
    }

    public String getReferer() {
        return referer;
    }

    public String getUserAgent() {
        return userAgent;
    }
}


    /*
    !!создайте у класса свойство (поле) int totalTraffic,
● в которое в методе addEntry добавляйте объём данных, отданных сервером;
● создайте свойства (поля) minTime и maxTime класса LocalDateTime и
● заполняйте их в методе addEntry, если время в добавляемой записи из лога меньше minTime или больше maxTime соответственно;
● реализуйте в классе метод getTrafficRate, в котором вычисляйте разницу между maxTime и minTime в часах и делите общий объём трафика на эту разницу.

●     Сделайте коммит в ветку master вашего репозитория access-log-parser.
                    while ((line = reader.readLine()) != null) {
        int length = line.length();
        totalLines++; //считаем строки
        if (length > 1024) {
            throw new LineTooLongException("Строка длинной более 1024 символа"); //выбрасывание исключения при превышении 1024
        }


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

     */
