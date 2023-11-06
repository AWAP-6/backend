package org.example;




public class TouchScreen {
    public static void main(String[] args) {


        int min = 0, max = 9999;
        int openCode = (min + (int)(Math.random() * ((max - min) + 1)));
        if (openCode < 10) {
            System.out.println("000" + openCode);
        } else if (openCode < 100){
            System.out.println("00" + openCode);
        } else  if (openCode < 1000){
            System.out.println("0" + openCode);
        } else {
            System.out.println(openCode);
        }

    }
    public int openCode () {
        int min = 0, max = 9999;
        int openCode = (min + (int)(Math.random() * ((max - min) + 1)));
        if (openCode < 10) {
            openCode = Integer.parseInt("000") + openCode;
        } else if (openCode < 100){
            openCode = Integer.parseInt("00") + openCode;
        } else  if (openCode < 1000){
            openCode = Integer.parseInt("0") + openCode;
        }
        return openCode;
    }
}
