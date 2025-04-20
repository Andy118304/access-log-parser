public class UserAgent {
    private final String typeOS;//тип операционной системы
    private final String typeBrowser;//тип браузера
    private boolean isBot;//бот ли делает запрос

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
    public boolean isBot(String userAgentString){
        if (userAgentString.contains("bot")||userAgentString.contains("Bot")){
            this.isBot= true;
        }
        return isBot;
    }

    public String getTypeOS() {
        return typeOS;
    }

    public String getTypeBrowser() {
        return typeBrowser;
    }


}
