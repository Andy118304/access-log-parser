import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
    int totalTraffic; //суммарный объем траффика
    LocalDateTime minTime;
    LocalDateTime maxTime;

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
            System.out.println("MaxT" + maxTime);

        } else if (entryTime.isBefore(minTime)) {
            minTime = entryTime;
            System.out.println("MinT" + minTime);
        }

    }

    public double getTrafficRate() {
        int hoursGapTime = (int) Duration.between(minTime, maxTime).toHours(); //вычисление разницы во времени в часах
        double trafficRate = (double) totalTraffic / hoursGapTime;
        return trafficRate;
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
}
