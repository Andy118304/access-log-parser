public class UserAgent {
    private final String typeOS;//тип операционной системы
    private final String typeBrowser;//тип браузера

    public UserAgent(String userAgentString) {
        //определение типа ОС
        if (userAgentString.contains("Windows")) {
            this.typeOS = "Windows";
        } else if (userAgentString.contains("macOs")) {
            this.typeOS = "macOs";
        } else if (userAgentString.contains("Linux")) {
            this.typeOS = "Linux";
        } else {
            this.typeOS = "Другой";
        }
        //определение типа браузера
        if (userAgentString.contains("Edge")) {
            this.typeBrowser = "Edge";
        } else if (userAgentString.contains("Firefox")) {
            this.typeBrowser = "Firefox";
        } else if (userAgentString.contains("Chrome")) {
            this.typeBrowser = "Chrome";
        } else if (userAgentString.contains("Opera")) {
            this.typeBrowser = "Opera";
        } else {
            this.typeBrowser = "Другой";
        }

    }

    public String getTypeOS() {
        return typeOS;
    }

    public String getTypeBrowser() {
        return typeBrowser;
    }
}
