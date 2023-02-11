package model;

import ui.MainForm;

import javax.swing.*;

public class ShuttleLive {
    public static ShuttleLive shuttlelive;

    public static ShuttleLive getInstance() {
        if(shuttlelive == null)
            shuttlelive = new ShuttleLive();
        else
            System.out.println("Istanza già creata");
        return shuttlelive;
    }



}
