import java.util.ArrayList;
import java.util.List;

/**
 * Problem #11
 * Time complexity: O(???)
 * Space complexity: O(???)
 **/
public class RegularExpressionMatching {
	
	private static class PatternSymbol {
		
		private final char character;
		private final boolean singular;
		
		private PatternSymbol(final char character, final boolean singular) {
			this.character = character;
			this.singular = singular;
		}
		
		private char getCharacter() {
			return character;
		}
		
		private boolean isAny() {
			return character == '.';
		}
		
		private boolean isSingular() {
			return singular;
		}
		
		private static List<PatternSymbol> getPatternSymbolsFrom(final String pattern) {
			final List<PatternSymbol> symbols = new ArrayList<>();
			int i = 0;
			while (i < pattern.length()) {
				final char character = pattern.charAt(i);
				i++;
				if (i < pattern.length() && pattern.charAt(i) == '*') {
					symbols.add(new PatternSymbol(character, false));
					i++;
				} else {
					symbols.add(new PatternSymbol(character, true));
				}
			}
			return symbols;
		}
	}

    public boolean isMatch(final String input, final String pattern) {
		return match(asCharacterList(input), PatternSymbol.getPatternSymbolsFrom(pattern));
    }
	
	private static List<Character> asCharacterList(final String str) {
		final List<Character> list = new ArrayList<>(str.length());
		for (int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}
		return list;
	}
	
	private static boolean match(final List<Character> input, final List<PatternSymbol> symbols) {
		if (symbols.isEmpty()) {
            return input.isEmpty();
		}
		final PatternSymbol currSymbol = symbols.get(0);
		if (currSymbol.isSingular()) {
			if (input.isEmpty()) {
				return false;
			} else if (currSymbol.isAny() || currSymbol.getCharacter() == input.get(0)) {
				return match(input.subList(1, input.size()), symbols.subList(1, symbols.size()));
			} else {
				return false;
			}
		} else {
			if (currSymbol.isAny()) {
				for (int i = input.size() - countOfSingular(symbols); i >= 0; i--) {
					if (match(input.subList(i, input.size()), symbols.subList(1, symbols.size()))) {
						return true;
					}
				}
				return false;
			} else {
				int to = 0;
				while (to < input.size() && input.get(to) == currSymbol.getCharacter()) {
					to++;
				}
				for (int i = to; i >= 0; i--) {
					if (match(input.subList(i, input.size()), symbols.subList(1, symbols.size()))) {
						return true;
					}
				}
				return false;
			}
		}
	}
	
	private static int countOfSingular(final List<PatternSymbol> symbols) {
		return (int) symbols.subList(1, symbols.size()).stream()
				.filter(PatternSymbol::isSingular)
				.count();
	}
    
    public static void main(final String[] args) {
        System.out.println("false - " + new RegularExpressionMatching().isMatch("aa", "a"));
        System.out.println("true - " + new RegularExpressionMatching().isMatch("aa", "a*"));
        System.out.println("true - " + new RegularExpressionMatching().isMatch("ab", ".*"));
        System.out.println("true - " + new RegularExpressionMatching().isMatch("aab", "c*a*b"));
        System.out.println("false - " + new RegularExpressionMatching().isMatch("mississippi", "mis*is*p*."));
        System.out.println("true - " + new RegularExpressionMatching().isMatch("xfooxxxxxxfoo", ".*foo"));
	}
}
















