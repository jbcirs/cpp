import java.util.Scanner;

public class bfm {

    public static void main(String[] args) {

        Run run = new Run();
        run.start();
    }
}

class Run {

    private String inputOne;
    private String inputTwo;
    private String signOne;
    private String signTwo;
    private String e1;
    private String e2;
    private String answer;
    private String norm;
    private String signOut;
    private String exponent;
    private String round;
    private String overFlow;
    private String exponentNorm;

    public Run() {
        this.inputOne = "0";
        this.inputTwo = "0";
        this.signOne = "0";
        this.signTwo = "0";
        this.e1 = "0";
        this.e2 = "0";
        this.answer = "0";
        this.norm = "0";
        this.signOut = "0";
        this.exponent = "0";
        this.round = "0";
        this.overFlow = "0";
        this.exponentNorm = "0";
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        int inputs = 0;
        while (in.hasNext()) {
            String item = in.nextLine();
            if (inputs == 0) {
                this.inputOne = item;
                inputs++;
            } else {
                this.inputTwo = item;
                output();
                inputs = 0;
            }
        }
    }

    private String findSign(String input) {
        return input.substring(0, 1);
    }

    private String dSignOutput(String s1, String s2) {
        if (s1.equals(s2)) {
            return "0";
        } else {
            return "1";
        }
    }

    private String dExOutput(String s1, String s2) {
        long b1 = Long.parseLong(s1, 2);
        long b2 = Long.parseLong(s2, 2);
        return Long.toBinaryString((b2 + b1) - 127);
    }

    private String mult(String s1, String s2) {
        s1 = 1 + s1.substring(9);
        s2 = 1 + s2.substring(9);
        long b1 = Long.parseLong(s1, 2);
        long b2 = Long.parseLong(s2, 2);
        return Long.toBinaryString((long) b1 * b2);
    }

    private String normalize(String mult, String ex) {
        mult = mult.substring(1);
        if (countOccurrences(ex, '1') < 9) {
            long temp = binaryToDecimal(ex);
        System.out.println((int) temp + 1);
        ex = Integer.toBinaryString((int) temp + 1); 
        }
        return ex + "-" + mult;
    }

    public int countOccurrences(String haystack, char needle) {
        int count = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle) {
                count++;
            }
        }
        return count;
    }

    private long binaryToDecimal(String input) {
        char[] c = input.toCharArray();
        int dec = 0;
        int j = 7;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '1') {
                int free = (int) Math.pow(2, j);
                dec += free;
            }
            j--;
        }
        return dec;
    }

    private String round(String input) {
        return input.substring(0, 23);
    }

    private String ckOverflow(String input) {
        long inputs = binaryToDecimal(input);
        if (input.length() < 9) {
            if (inputs > 1 && inputs < 254) {
                return "0";
            }
        }
        return "1";
    }

    private String finish(String sign, String exponent, String product) {
        return sign + exponent + product;
    }

    private boolean ckInput(String input) {
        String inputs = input.replaceAll("[^\\d]", "");

        if (input.equals(inputs)) {
            if (input.length() == 32) {
                return true;
            }
        }
        return false;
    }

    public void build() {
        this.e1 = this.inputOne.substring(1, 9);
        this.e2 = this.inputTwo.substring(1, 9);
        this.exponent = dExOutput(this.e1, this.e2);
        this.signOne = findSign(this.inputOne);
        this.signTwo = findSign(this.inputTwo);
        this.signOut = dSignOutput(this.signOne, this.signTwo);
        this.answer = mult(this.inputOne, this.inputTwo);
        this.norm = normalize(this.answer, this.exponent);
        String norms[] = this.norm.split("-");
        this.exponentNorm = norms[0];
        this.round = round(norms[1]);
        this.overFlow = ckOverflow(this.exponent);
    }

    public void output() {
        if (ckInput(this.inputOne) == true && ckInput(this.inputTwo) == true) {
            build();
            System.out.println("Input data: " + this.inputOne);
            System.out.println("            " + this.inputTwo);
            System.out.println("Sign unit: ");
            System.out.println("Input: S1 = " + this.signOne + ", S2 = " + this.signTwo);
            System.out.println("Output: S = " + this.signOut);
            System.out.println("Add Exponent unit: ");
            System.out.println("Input: e1 = " + this.e1 + ", e2 = " + this.e2);
            System.out.println("Output: new_e = " + this.exponent);
            System.out.println("Multiplication unit: ");
            System.out.println("Output: " + this.answer);
            System.out.println("Normalization unit: ");
            System.out.println("Output: " + this.norm);
            System.out.println("Rounding unit: ");
            System.out.println("Output: " + this.round);
            System.out.println("Overflow detection unit: ");
            System.out.println("Output: " + this.overFlow);
            System.out.println("Final result: " + finish(this.signOut, this.exponentNorm, this.round));
            System.out.println("**********************************************************************");
        } else {
            System.out.println("Your Input Values were not in IEEE 754 Standard Format !");
        }
    }
}
