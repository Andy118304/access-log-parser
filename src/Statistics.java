import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {
    int totalTraffic; //суммарный объем траффика
    LocalDateTime minTime;
    LocalDateTime maxTime;
    private final HashSet<String> validPages = new HashSet<>();
    private final HashMap<String, Integer> osCounter = new HashMap<>();

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
        }
        //подсчет ОС из user agent
        UserAgent userAgent = new UserAgent(logEntry.userAgent);
        String os = userAgent.getTypeOS();
        osCounter.put(os,osCounter.getOrDefault(os,0)+1);
    }

    public double getTrafficRate() {
        int hoursGapTime = (int) Duration.between(minTime, maxTime).toHours(); //вычисление разницы во времени в часах
        double trafficRate = (double) totalTraffic / hoursGapTime;
        return trafficRate;
    }

    public HashSet<String> getValidPages() {
        return validPages;
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

    public HashMap<String,Double> getOsStats(){
        HashMap<String,Double>result = new HashMap<>();
        int total = osCounter.values().stream().mapToInt(Integer::intValue).sum();

        for (Map.Entry<String,Integer>entry: osCounter.entrySet()){
            result.put(entry.getKey(),(double) entry.getValue()/total);
        }
        return result;
    }

}
