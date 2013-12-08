package ru.troq.adm;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class UTestADM {

    @Test
    public void test() throws Exception {
        for (int i = 0; i<666; i++)
        {
            System.out.println("Running Test " + (i+1));
            InputActivity.PLAYERS = getDefaultPlayers();
            for (ADM player : InputActivity.PLAYERS) {
                try {
                    String app = AvailableAPPs.getInstance().getAPPFor(player);
                    player.setApp(app);
                } catch (IndexOutOfBoundsException ioobe) {
                    System.out.println(InputActivity.PLAYERS);
                    throw new Exception("");
                }
            }
            HashMap<String, String> testMap = new HashMap<String, String>();
            for (ADM adm : InputActivity.PLAYERS) {
                assertTrue("АДМ не уникальны", testMap.put(adm.getName(), adm.getApp())==null);
                assertFalse("Кто-то дарит сам себе", adm.getName().equalsIgnoreCase(adm.getApp()));
                assertFalse("Кто-то дарит своей паре", adm.getApp().equalsIgnoreCase(adm.getPair()));
            }
            Collection<String> appList = testMap.values();
            Set<String> appSet = new HashSet<String>(testMap.values());
            assertTrue("АПП не уникальны", appList.size() == appSet.size());
        }
    }

    private Set<ADM> getDefaultPlayers() {
        List<String> defaultPlayersNames = Arrays.asList("АНЯ", "ГАЙКА", "ИЛЮША", "КСЮША", "МАКС", "МИКОЛА", "МИШАНЯ");
        Set<ADM> defaultPlayers = new LinkedHashSet<>();
        for (String name : defaultPlayersNames) {
            defaultPlayers.add(new ADM(name));
        }
        return defaultPlayers;
    }
}
