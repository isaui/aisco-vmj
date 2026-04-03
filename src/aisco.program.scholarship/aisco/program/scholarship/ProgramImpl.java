package aisco.program.scholarship;

import aisco.program.core.ProgramComponent;
import aisco.program.core.ProgramDecorator;

public class ProgramImpl extends ProgramDecorator {
    private String name;
    private String scholarshipType;
    private String duration;

    public ProgramImpl(ProgramComponent program, String name, String scholarshipType, String duration) {
        super(program);
        this.name = name;
        this.scholarshipType = scholarshipType;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScholarshipType() {
        return scholarshipType;
    }

    public void setScholarshipType(String scholarshipType) {
        this.scholarshipType = scholarshipType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return program + ", Scholarship: " + name + ", Type: " + scholarshipType + ", duration: " + duration + ".";
    }
}