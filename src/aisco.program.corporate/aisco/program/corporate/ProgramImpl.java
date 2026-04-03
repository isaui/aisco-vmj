package aisco.program.corporate;

import aisco.program.core.ProgramComponent;
import aisco.program.core.ProgramDecorator;

public class ProgramImpl extends ProgramDecorator {
    private String corporateDonor;

    public ProgramImpl(ProgramComponent program, String corporateDonor) {
        super(program);
        this.corporateDonor = corporateDonor;
    }

    public String getCorporateDonor() {
        return corporateDonor;
    }

    public void setCorporateDonor(String corporateDonor) {
        this.corporateDonor = corporateDonor;
    }

    public String toString() {
        return program + ", Corporate Donor: " + corporateDonor + ".";
    }
}
