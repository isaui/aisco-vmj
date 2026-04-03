package aisco.program.scholarship;

import aisco.program.core.ProgramComponent;
import aisco.program.core.ProgramDecorator;

public class ProgramImpl extends ProgramDecorator {
    private String name;
    private String scholarshipType;
    private Integer durationInMonths;

    public ProgramImpl(ProgramComponent program, String name, String scholarshipType, Integer durationInMonths) {
        super(program);
        this.name = name;
        this.scholarshipType = scholarshipType;
        this.durationInMonths = durationInMonths;
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

    public Integer getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(Integer durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    @Override
    public String toString() {
        return program + ", Scholarship: " + name + ", Type: " + scholarshipType + ", durationInMonths: "
                + durationInMonths + ".";
    }
}