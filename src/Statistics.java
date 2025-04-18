import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {
    int totalTraffic; //суммарный объем траффика
    LocalDateTime minTime;
    LocalDateTime maxTime;
    private final HashSet<String> validPages = new HashSet<>();//спиосок валидных страниц
    private final HashMap<String, Integer> osCounter = new HashMap<>();//HashMap для операционных систем
    private final HashSet<String> unValidPages = new HashSet();//список некорректных страниц
    private final HashMap<String, Integer> browserCounter = new HashMap<>(); //HashMap для браузеров
    int errorRequestCount; //переменная для подсчета заходов по некорректному пути
    int notBotVisitCount;//переменная для расчета не ботов
    private HashSet<String> uniqueRealUsers = new HashSet<>();//IP адреса обычных пользователей
      HashMap<Integer,Integer> usersPerSeconds= new HashMap<>();//HashMap посещения пользователей сайта по секундам


    public Statistics() {
        int totalTraffic = 0;
        this.minTime = LocalDateTime.MAX;
        this.maxTime = LocalDateTime.MIN;
    }

    public void addEntry(LogEntry logEntry) {
        totalTraffic += logEntry.getSizeDate();

        LocalDateTime entryTime = logEntry.getDateAndTime();
        if (entryTime.isAfter(maxTime)) {
            maxTime = entryTime;
            //System.out.println("MaxT" + maxTime);

        } else if (entryTime.isBefore(minTime)) {
            minTime = entryTime;
            //System.out.println("MinT" + minTime);
        }

        //добавление URL страниц с кодом 200
        if (logEntry.getCodeResponse() == 200) {
            validPages.add(logEntry.getPathRequest());
        } else if (logEntry.getCodeResponse() >= 400 && logEntry.getCodeResponse() < 600) {
            errorRequestCount++;
        }
        //подсчет ОС из user agent
        UserAgent userAgent = new UserAgent(logEntry.userAgent);
        String os = userAgent.getTypeOS();
        osCounter.put(os, osCounter.getOrDefault(os, 0) + 1);

        //добавление URL страниц с кодом 404
        if (logEntry.getCodeResponse() == 404) {
            unValidPages.add(logEntry.getPathRequest());
        }
        //подсчет браузеров из user agent
        String browser = userAgent.getTypeBrowser();
        browserCounter.put(browser, browserCounter.getOrDefault(os, 0) + 1);

        if (!userAgent.isBot(logEntry.userAgent)){
            notBotVisitCount++;
            uniqueRealUsers.add(logEntry.getIp());
        }
    }

    //метод расчета среднего количества посетителей за час
    public Double getAverageVisitorsPerHour() {
        int hours = (int) Duration.between(minTime,maxTime).toHours();
        return (double) notBotVisitCount / hours;
    }

    //метод расчета среднего количества ошибок за час
    public Double getAverageErrorsPerHour() {
        int hours = (int) Duration.between(minTime,maxTime).toHours();
        return (double)errorRequestCount / hours;
    }

    //метод расчета среднего количества посещений от юзера
    public Double getAverageVisitorsPerUser() {
        return (double) notBotVisitCount / uniqueRealUsers.size();
    }

    public double getTrafficRate() {
        int hoursGapTime = (int) Duration.between(minTime, maxTime).toHours(); //вычисление разницы во времени в часах
        double trafficRate = (double) totalTraffic / hoursGapTime;
        return trafficRate;
    }

    public HashSet<String> getValidPages() {
        return validPages;
    }

    public HashSet<String> getUnValidPages() {
        return unValidPages;
    }

    public int getTotalTraffic() {
        return totalTraffic;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }

    public HashMap<String, Double> getOsStats() {
        HashMap<String, Double> result = new HashMap<>();
        int total = osCounter.values().stream().mapToInt(Integer::intValue).sum();

        for (Map.Entry<String, Integer> entry : osCounter.entrySet()) {
            result.put(entry.getKey(), (double) entry.getValue() / total);
        }

        return result;
    }



    public HashMap<String, Double> getbrowserStats() {
        HashMap<String, Double> resultBrowser = new HashMap<>();
        int total = browserCounter.values().stream().mapToInt(Integer::intValue).sum();

        for (Map.Entry<String, Integer> entry : browserCounter.entrySet()) {
            resultBrowser.put(entry.getKey(), (double) entry.getValue() / total);
        }
        return resultBrowser;
    }

}