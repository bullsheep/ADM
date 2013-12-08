package ru.troq.adm;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class AvailableAPPs {
    private static List<String> availableAPPs;
    private ArrayList<ADM> playersRemain;
    private static AvailableAPPs Instance = null;

    public static AvailableAPPs getInstance() {
        if (Instance == null || availableAPPs.isEmpty()) {
            Instance = new AvailableAPPs();
        }
        return Instance;
    }

    private AvailableAPPs() {
        availableAPPs = new ArrayList<String>();
        for (ADM adm : InputActivity.PLAYERS) {
            availableAPPs.add(adm.getName());
        }
        playersRemain = new ArrayList<>(InputActivity.PLAYERS);
    }

    public String getAPPFor(ADM adm)
    {
        playersRemain.remove(adm.getName());
        boolean selfDel = availableAPPs.remove(adm.getName());
        boolean pairDel = false;
        if (adm.hasPair()) {
            pairDel = availableAPPs.remove(adm.getPair());
        }
        Collections.shuffle(availableAPPs);
        String app = availableAPPs.get(0);
        availableAPPs.remove(app);
        if (playersRemain.size() == 1 && availableAPPs.size() != 0) {
            String lastOne = playersRemain.get(0).getName();
            if (lastOne.equals(availableAPPs.get(0)) || availableAPPs.get(0).equals(playersRemain.get(0).getPair())) {
                availableAPPs.add(app);
                availableAPPs.remove(lastOne);
                app = lastOne;
            }
        }
        if (selfDel) {
            availableAPPs.add(adm.getName());
        }
        if (pairDel) {
            availableAPPs.add(adm.getPair());
        }

        return app;
    }

}
