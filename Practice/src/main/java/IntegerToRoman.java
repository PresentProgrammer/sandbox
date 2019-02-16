/**
 * Problem #12
 * Time complexity: O(1)
 * Space complexity: O(1)
 **/
public class IntegerToRoman {

    public String intToRoman(final int num) {
		final StringBuilder strBuilder = new StringBuilder();
        int x = num;
		while (x / 1000 != 0) {
            strBuilder.append('M');
            x -= 1000;
        }
		if (x / 900 != 0) {
		    strBuilder.append("CM");
		    x -= 900;
        } else if (x / 500 != 0) {
		    strBuilder.append('D');
		    x -= 500;
        } else if (x / 400 != 0) {
		    strBuilder.append("CD");
		    x -= 400;
        }

		while (x / 100 != 0) {
		    strBuilder.append('C');
		    x -= 100;
        }
        if (x / 90 != 0) {
            strBuilder.append("XC");
            x -= 90;
        } else if (x / 50 != 0) {
            strBuilder.append('L');
            x -= 50;
        } else if (x / 40 != 0) {
            strBuilder.append("XL");
            x -= 40;
        }

        while (x / 10 != 0) {
            strBuilder.append('X');
            x -= 10;
        }
        if (x / 9 != 0) {
            strBuilder.append("IX");
            x -= 9;
        } else if (x / 5 != 0) {
            strBuilder.append('V');
            x -= 5;
        } else if (x / 4 != 0) {
            strBuilder.append("IV");
            x -= 4;
        }

        while (x != 0) {
            strBuilder.append('I');
            x -= 1;
        }

		return strBuilder.toString();
    }
    
    public static void main(final String[] args) {
        System.out.println("III == " + new IntegerToRoman().intToRoman(3));
        System.out.println("IV == " + new IntegerToRoman().intToRoman(4));
        System.out.println("IX == " + new IntegerToRoman().intToRoman(9));
        System.out.println("LVIII == " + new IntegerToRoman().intToRoman(58));
        System.out.println("MCMXCIV == " + new IntegerToRoman().intToRoman(1994));
	}
}