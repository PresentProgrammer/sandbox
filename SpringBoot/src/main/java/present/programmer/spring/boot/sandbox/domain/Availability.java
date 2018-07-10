package present.programmer.spring.boot.sandbox.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Availability {

    private final boolean available;
    private final String searchTerm;

    public static Availability userNameIsAvailable() {
        return new Availability(true, "user name");
    }

    public static Availability userNameIsNotAvailable() {
        return new Availability(false, "user name");
    }

    public static Availability emailIsAvailable() {
        return new Availability(true, "e-mail");
    }

    public static Availability emailIsNotAvailable() {
        return new Availability(false, "e-mail");
    }

    private Availability(final boolean available, final String searchTerm) {
        this.available = available;
        this.searchTerm = searchTerm;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Availability)) return false;
        final Availability that = (Availability) o;
        return new EqualsBuilder()
                .append(isAvailable(), that.isAvailable())
                .append(getSearchTerm(), that.getSearchTerm())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(isAvailable())
                .append(getSearchTerm())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("available", available)
                .append("searchTerm", searchTerm)
                .toString();
    }
}
