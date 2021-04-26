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
        Button lower_buyer_prices = new Button("Raise Buyer Prices"); // Button is a component
        add(lower_buyer_prices);
        LowerBuyerPricesListener listener = new LowerBuyerPricesListener();
        lower_buyer_prices.addActionListener(listener);
        setVisible(true);
        setSize(width,height);
        setBounds(center.x - width / 2, center.y - height / 2, width, height);
    }
    public static void main(String[] args) {                // The Panel container adds a Button component
       GUI app = new GUI();
    }

    private class LowerBuyerPricesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String buyerFilePath = "C:\\Users\\Public\\Daybreak Game Company\\Installed Games\\EverQuest\\userdata\\BART_YourCharacterName_server.ini";
            File aabalaIni = new File(buyerFilePath);
            Scanner scanner = null;
            try {
                scanner = new Scanner(aabalaIni);
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
                        price++;
                        price++;
                    } else if (price < 200) {
                        price++;
                        price++;
                        price++;
                    } else if (price < 400) {
                        price++;
                        price++;
                        price++;
                        price++;
                    } else if (price < 1800000) {
                        price++;
                        price++;
                        price++;
                        price++;
                        price++;
                    }
                    String newLine = data.substring(0, getSeventhCarrot(data) + 1) + price * 1000 + data.substring(getEighthCarrot(data));
                    newString.append(newLine + System.lineSeparator());
                } else {
                    newString.append(data + System.lineSeparator());
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
}




