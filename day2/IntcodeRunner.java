
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IntcodeRunner {
    public static void main (String[] args) {
        int[] opcodes;
        int index = 0;
        int finalValue = 0;
        int noun = 0;
        int verb = 0;
        int i1, i2, i3;
        boolean finished = false;

        opcodes = loadOpcodes("day2/intcodes.txt");
        
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100 && finalValue != 19690720; j++) {
                opcodes[1] = i;
                opcodes[2] = j;

                while (index < opcodes.length && !finished) {

                    switch(opcodes[index]) {
                        case 1:
                            i1 = opcodes[index + 1];
                            i2 = opcodes[index + 2];
                            i3 = opcodes[index + 3];
                            opcodes[i3] = opcodes[i1] + opcodes[i2];
                            index += 4;
                            break;
                        case 2:
                            i1 = opcodes[index + 1];
                            i2 = opcodes[index + 2];
                            i3 = opcodes[index + 3];
                            opcodes[i3] = opcodes[i1] * opcodes[i2];
                            index += 4;
                            break;
                        case 99:
                            finished = true;
                            finalValue = opcodes[0];
                            noun = opcodes[1];
                            verb = opcodes[2];
                            break;
                        default:
                            System.out.printf("Invalid opcode: [%d] at index: [%d]\n", opcodes[index], index);
                            System.exit(1);
                    }
                }
                // reset & reinitialize
                index = 0;
                opcodes = loadOpcodes("day2/intcodes.txt");
                finished = false;
            }
        }

        System.out.println("Intcode program ran to completion with value at index 0 of: " + finalValue);
        System.out.println("Value of address 1: " + noun);
        System.out.println("Value of address 2: " + verb);
        System.out.println("100 * noun + verb = " + ((100 * noun) + verb));
    }

    public static int[] loadOpcodes(String filename) {
        Scanner infile = null;

        try {
            infile = new Scanner(new File(filename));
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        String untokenizedOpcodes = infile.nextLine();
        String[] strOpcodes = untokenizedOpcodes.split(",");
        int[] opcodes = new int[strOpcodes.length];

        for (int i = 0; i < strOpcodes.length; i++) {
            opcodes[i] = Integer.parseInt(strOpcodes[i]);
        }        
        infile.close();

        return opcodes;
    }

}