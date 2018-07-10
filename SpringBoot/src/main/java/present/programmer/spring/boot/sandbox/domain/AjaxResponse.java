package present.programmer.spring.boot.sandbox.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AjaxResponse {

    private final String message;

    public AjaxResponse(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @SuppressWarnings("unused") // used in sandbox experiments
    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof AjaxResponse)) return false;
        final AjaxResponse that = (AjaxResponse) o;
        return new EqualsBuilder()
                .append(getMessage(), that.getMessage())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getMessage())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("message", message)
                .toString();
    }
}
