package ru.troq.adm;

import java.util.*;

public class Background {

    static final List<Integer> backgrounds = new ArrayList<Integer>() {{
        add(R.drawable.back0);
        add(R.drawable.back1);
        add(R.drawable.back2);
        add(R.drawable.back3);
    }};


    public static int getBackground() {
        Collections.shuffle(backgrounds);
        return backgrounds.get(0);
    }
}
