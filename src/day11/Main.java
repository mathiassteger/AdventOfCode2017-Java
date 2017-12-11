package day11;

import java.util.ArrayList;

import util.ReadInputHelper;

public class Main {
public static void main(String[] args) {
    ArrayList<String> lines = new ReadInputHelper(11).getLines();
    String line = lines.get(0);
    City city = new City();
    int rslt = 0;
    String[] ins = line.split(",");

    for (int i = 0; i < ins.length; i++) {
        try {
            if (i > 0) {
                city.getClass().getMethod(ins[i], String.class).invoke(city, ins[i - 1]);
            } else {
                city.getClass().getMethod(ins[i], String.class).invoke(city, "");
            }
        } catch (Exception e){
        }

        System.out.println(city.x + ", " + city.y + ", max: " + city.max());
        if (city.max() > rslt)
            rslt = city.max();
    }

    System.out.println(city.x + ", " + city.y);
    System.out.println(city.max());
    System.out.println(rslt);
}
}

class City {
public int x, y = 0;

public void n(String last) {
    this.y--;
}

public void ne(String last) {
    if (!last.equals("nw")) {
        this.y--;
    }
    this.x++;
}

public void se(String last) {
    if (!last.equals("sw"))
        y++;
    this.x++;
}

public void s(String last) {
    y++;
}

public void sw(String last) {
    if (!last.equals("se"))
        y++;
    x--;
}

public void nw(String last) {
    if (!last.equals("ne"))
        y--;
    x--;
}

public int max() {
    if ((x < 0 && y < 0) || (x > 0 && y > 0)) {
        return Math.abs(x + y);
    } else if (Math.abs(x) > Math.abs(y)) {
        return Math.abs(x);
    } else {
        return Math.abs(y);
    }
}
}