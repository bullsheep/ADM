package ru.troq.adm;

import java.io.Serializable;

public class ADM implements Serializable{

    private String name;
    private String app;
    private String pair;

    public ADM(String name) {
        this.name = name;
        switch (name.toUpperCase()) {
            case "МИШАНЯ":
                this.pair = "КСЮША";
                break;
            case "КСЮША":
                this.pair = "МИШАНЯ";
                break;
            case "ИЛЮША":
                this.pair = "ГАЙКА";
                break;
            case "ГАЙКА":
                this.pair = "ИЛЮША";
                break;
            case "МИКОЛА":
                this.pair = "АНЯ";
                break;
            case "АНЯ":
                this.pair = "МИКОЛА";
                break;
            default:
                this.pair = null;
                break;
        }
    }

    public void setApp(String app) {
        this.app = app;
    }

    public void setPair(String pair) {
        this.app = pair;
    }


    public String getName() {
        return name;
    }

    public String getApp() {
        return app;
    }

    public String getPair() {
        return pair;
    }

    public boolean hasPair() {
        return pair != null;
    }

    @Override
    public String toString() {
        return "ADM " + this.getName() + " and his APP " + this.getApp();
    }
}
