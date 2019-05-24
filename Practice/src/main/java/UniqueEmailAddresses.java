import java.util.HashSet;
import java.util.Set;

/**
 * Problem #929
 * Time complexity: O(n * l), where n number of emails, l — max length of email
 * Space complexity: O(n)
 **/
public class UniqueEmailAddresses {

    public int numUniqueEmails(String[] emails) {
		final Set<String> uniqueEmails = new HashSet<>();
		for (final String email : emails) {
		    final int atIndex = email.indexOf('@');
		    final String localName = email.substring(0, atIndex);
		    final int plusIndex = localName.indexOf('+');
		    final int localNameLastExclIndex = plusIndex == -1 ? localName.length() : plusIndex;
		    final String refinedLocalName = removeDots(localName.substring(0, localNameLastExclIndex));
		    final String hostName = email.substring(atIndex);
            uniqueEmails.add(refinedLocalName + hostName);
        }
		return uniqueEmails.size();
    }

    private static String removeDots(final String s) {
        final StringBuilder builder = new StringBuilder();
        for (final char ch : s.toCharArray()) {
            if (ch != '.') {
                builder.append(ch);
            }
        }
        return builder.toString();
    }
    
    public static void main(final String[] args) {
	}
}