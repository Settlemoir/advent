
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FuelCounter {
    public static void main (String[] args) {
        int fuelRequirement = 0;
        ArrayList<Integer> masses = new ArrayList<Integer>();
        File massFile = new File("Day1/input.txt");

        Scanner infile = openFile(massFile);

        while (infile.hasNext()) {
            masses.add(infile.nextInt());
        }

        for (int mass : masses) {
            fuelRequirement += fuelCalc(mass);               // this alone for Part 1
            fuelRequirement += fuelForFuel(fuelCalc(mass));  // add this for Part 2
        }
        System.out.println("Total fuel requirement: " + fuelRequirement);
    }

    public static int fuelForFuel(int fuel) {
        if (fuelCalc(fuel) <= 0) {
            return 0;
        }
        else {
            return (fuelCalc(fuel) + fuelForFuel(fuelCalc(fuel)));
        }
    }

    public static int fuelCalc(int mass) {
        return ((mass/3) - 2);
    }

    public static Scanner openFile(File file) {
        Scanner in = null;
        try {
            in = new Scanner(file);
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        return in;
    }

}