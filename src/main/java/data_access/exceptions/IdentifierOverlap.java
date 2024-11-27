package data_access.exceptions;

/**
 * Throw this when there are duplicates with identifiers.
 */
public class IdentifierOverlap extends RuntimeException {
    public IdentifierOverlap(String message) {
        super(message);
    }
}
