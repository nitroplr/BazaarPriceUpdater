import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class GUI extends Frame {
    public GUI(){
        //GUI stuff
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 200;
        int height = 200;
        Button raise_buyer_prices = new Button("Raise Buyer Prices"); // Button is a component
        add(raise_buyer_prices);
        LowerBuyerPricesListener buyerListener = new LowerBuyerPricesListener();
        raise_buyer_prices.addActionListener(buyerListener);
        setVisible(true);
        setSize(width,height);
        setBounds(center.x - width / 2, center.y - height / 2, width, height);
        Button lower_seller_prices = new Button("Lower Seller Prices");
        add(lower_seller_prices);
        LowerSellerPricesListener sellerListener = new LowerSellerPricesListener();
        lower_seller_prices.addActionListener(sellerListener);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    public static void main(String[] args) {                // The Panel container adds a Button component
       GUI app = new GUI();
    }

    private class LowerBuyerPricesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String buyerFilePath = "C:\\Users\\Public\\Daybreak Game Company\\Installed Games\\EverQuest\\userdata\\BART_Aabala_xegony.ini";
            File buyerIni = new File(buyerFilePath);
            Scanner scanner = null;
            try {
                scanner = new Scanner(buyerIni);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            StringBuffer newString = new StringBuffer();
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                if (data.length() > 30) {
                    int price = Integer.parseInt(data.substring(getSeventhCarrot(data) + 1, getEighthCarrot(data))) / 1000;

                    if (price < 50) {
                        price++;
                    } else if (price < 100) {
                        price = price + 2;
                    } else if (price < 200) {
                        price = price + 3;
                    } else if (price < 400) {
                        price = price + 4;
                    } else if (price < 1800000) {
                        price = price + 5;
                    }
                    String newLine = data.substring(0, getSeventhCarrot(data) + 1) + price * 1000 + data.substring(getEighthCarrot(data));
                    newString.append(newLine).append(System.lineSeparator());
                } else {
                    newString.append(data).append(System.lineSeparator());
                }
            }
            System.out.println(newString);
            scanner.close();
            try {
                FileWriter fileWriter = new FileWriter(buyerFilePath);
                fileWriter.write(newString.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        private int  getSeventhCarrot(String data) {
            int index = 0;
            for (int i = 0; i < 7; i++) {
                index =  data.indexOf('^', index + 1);
            }
            return index;
        }

        private int getEighthCarrot(String data) {
            int index = 0;
            for (int i = 0; i < 8; i++) {
                index =  data.indexOf('^', index + 1);
            }
            return index;
        }
    }

    private class LowerSellerPricesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String sellerFilePath = "C:\\Users\\Public\\Daybreak Game Company\\Installed Games\\EverQuest\\BZR_Tennisales_xegony.ini";
            File sellerIni = new File(sellerFilePath);
            Scanner scanner = null;
            try {
                scanner = new Scanner(sellerIni);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            StringBuffer newString = new StringBuffer();
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                if (!data.contains("ItemToSell")) {
                    int price = Integer.parseInt(data.substring(data.indexOf('=') + 1)) / 1000;
                    if (price < 1999999 && price > 10) {
                        price = (int)(price * .99);
                    }
                    String newLine = data.substring(0, data.indexOf('=') + 1) + price * 1000;
                    newString.append(newLine).append(System.lineSeparator());
                } else {
                    newString.append(data).append(System.lineSeparator());
                }
            }
            System.out.println(newString);
            scanner.close();
            try {
                FileWriter fileWriter = new FileWriter(sellerFilePath);
                fileWriter.write(newString.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}




