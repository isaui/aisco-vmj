package aisco.program.periodic;

import aisco.program.core.ProgramComponent;
import aisco.program.core.ProgramDecorator;

public class ProgramImpl extends ProgramDecorator {
    private String frequency;

    public ProgramImpl(ProgramComponent program, String frequency) {
        super(program);
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String toString() {
        return program + ", Periodic: " + frequency + ".";
    }
}
