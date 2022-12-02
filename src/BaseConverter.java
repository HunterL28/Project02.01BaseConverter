import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Takes values from a datafile and converts them to a new base
 * @author 24levinson
 * @version 11/18/2022
 */
public class BaseConverter {
    private final String DIGITS = "0123456789ABCDEF";
    /**
     * Convert a String num in fromBase to base-10 int.
     * @param num the original number
     * @param fromBase the original from base
     * @return a base-10 int of num base fromBase
     */
    public int strToInt(String num, String fromBase)    {
        int value = 0, exp = 0;
        for(int i = num.length()-1; i >= 0; i--)    {
            value += DIGITS.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), exp);
            exp++;
        }
        return value;
    }

    /**
     * Convert an int num in base-10 to a String that is in a new base
     * @param num an original base-10 number
     * @param toBase the new base that that number is converting to
     * @return the base-10 number converted to a new base
     */
    public String intToStr(int num, int toBase) {
        String toNum = new String();
        while(num > 0)  {
            toNum = DIGITS.charAt(num % toBase) + toNum;
            num /= toBase;
        }
        return (toNum.equals("")) ? "0" : toNum;
    }

    /**
     * Take values from a datafile values._0.dat
     * convert the values to a new base and write it to converted.dat (using strToInt and intToStr)
     */
    public void inputConvertPrintWrite() {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(new File("datafiles/values30.dat"));
            out = new PrintWriter(new File("datafiles/converted.dat"));
            String[] line;
            String output;
            while(in.hasNext()) {
                line = in.nextLine().split("\t");
                if (Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 16)
                    System.out.println("Invalid input base " + line[1]);
                else if (Integer.parseInt(line[2]) < 2 || Integer.parseInt(line[2]) > 16)
                    System.out.println("Invalid output base " + line[2]);
                else {
                    output = intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2]));
                    out.println(line[0] + "\t" + line[1] + "\t" + output + "\t" + line[2]);
                    System.out.println(line[0] + " base " + line[1] + " = " + output + " base " + line[2]);
                }
            }
            if(out != null)
                out.close();
            if(in != null)
                in.close();
        }
        catch (Exception e) {
            System.out.println("Something bad happened. Details here: " + e.toString());
        }
    }

    /**
     * Main method for class BaseConverter
     * @param args Command line arguments, if needed
     */
    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();
        bc.inputConvertPrintWrite();
    }
}