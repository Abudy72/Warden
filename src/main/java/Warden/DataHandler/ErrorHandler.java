package Warden.DataHandler;

public class ErrorHandler {
    private final String ERROR_CODE;
    private final String ERROR_MSG;

    public ErrorHandler(String ERROR_CODE, String ERROR_MSG) {
        this.ERROR_CODE = ERROR_CODE;
        this.ERROR_MSG = ERROR_MSG;
    }

    public String getERROR_CODE() {
        return ERROR_CODE;
    }

    public String getERROR_MSG() {
        return ERROR_MSG;
    }
}

