package main;

import main.model.Labels;
import main.service.Szamolo;
import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

public class SzamoloTeszt {

    private Szamolo szamolo = new Szamolo();

    @Test
    public void szamoloTeszt1() {
        LinkedList<Labels> labels = new LinkedList<>();
        labels.add(new Labels("3", "5"));
        labels.add(new Labels("4", "5"));

        double ertek = szamolo.szamolKredit(labels);

        assertEquals(3.5, ertek);
    }

    @Test
    public void szamoloTeszt2() {

        LinkedList<Labels> labels = new LinkedList<>();
        labels.add(new Labels("5", "5"));
        labels.add(new Labels("1", "5"));

        double ertek = szamolo.szamolKredit(labels);

        assertEquals(3.0, ertek);
    }

    @Test
    public void szamoloTeszt3() {

        LinkedList<Labels> labels = new LinkedList<>();
        labels.add(new Labels("1", "5"));
        labels.add(new Labels("1", "5"));

        double ertek = szamolo.szamolKredit(labels);

        assertEquals(1.0, ertek);
    }
}
