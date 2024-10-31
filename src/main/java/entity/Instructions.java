package entity;

/**
 * Holds instructions about recipe.
 */
@Deprecated
public class Instructions {
    private final String[] instructionList;

    public Instructions(String rawInstruction) {
        this.instructionList = rawInstruction.split(". ");
    }

    public String[] getInstructionList() {
        return instructionList.clone();
    }
}
