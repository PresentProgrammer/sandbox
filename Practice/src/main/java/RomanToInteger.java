/**
 * Problem #13
 * Time complexity: O(1)
 * Space complexity: O(1)
 **/
public class RomanToInteger {

    public int romanToInt(final String str) {
		int result = 0;
        int i = 0;

        while (i < str.length() && str.charAt(i) == 'M') {
		    result += 1000;
		    i++;
        }
		if (i + 1 < str.length() && str.charAt(i) == 'C') {
            if (str.charAt(i + 1) == 'M') {
                result += 900;
                i += 2;
            } else if (str.charAt(i + 1) == 'D') {
                result += 400;
                i += 2;
            }
        } else if (i < str.length() && str.charAt(i) == 'D') {
            result += 500;
            i++;
        }

        while (i < str.length() && str.charAt(i) == 'C') {
            result += 100;
            i++;
        }
        if (i + 1 < str.length() && str.charAt(i) == 'X') {
            if (str.charAt(i + 1) == 'C') {
                result += 90;
                i += 2;
            } else if (str.charAt(i + 1) == 'L') {
                result += 40;
                i += 2;
            }
        } else if (i < str.length() && str.charAt(i) == 'L') {
            result += 50;
            i++;
        }

        while (i < str.length() && str.charAt(i) == 'X') {
            result += 10;
            i++;
        }
        if (i + 1 < str.length() && str.charAt(i) == 'I') {
            if (str.charAt(i + 1) == 'X') {
                result += 9;
                i += 2;
            } else if (str.charAt(i + 1) == 'V') {
                result += 4;
                i += 2;
            }
        } else if (i < str.length() && str.charAt(i) == 'V') {
            result += 5;
            i++;
        }

        while (i < str.length()) {
            result += 1;
            i++;
        }

		return result;
    }
    
    public static void main(final String[] args) {
        System.out.println("3 == " + new RomanToInteger().romanToInt("III"));
        System.out.println("4 == " + new RomanToInteger().romanToInt("IV"));
        System.out.println("9 == " + new RomanToInteger().romanToInt("IX"));
        System.out.println("58 == " + new RomanToInteger().romanToInt("LVIII"));
        System.out.println("1994 == " + new RomanToInteger().romanToInt("MCMXCIV"));
	}
}