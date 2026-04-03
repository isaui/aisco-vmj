package aisco.program.scholarship;

import aisco.program.core.ProgramComponent;
import aisco.program.core.ProgramDecorator;

public class ProgramImpl extends ProgramDecorator {
    private String scholarshipName;
    private String scholarshipType;
    private Integer durationInMonths;

    public ProgramImpl(ProgramComponent program, String scholarshipName, String scholarshipType,
            Integer durationInMonths) {
        super(program);
        this.scholarshipName = scholarshipName;
        this.scholarshipType = scholarshipType;
        this.durationInMonths = durationInMonths;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
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
        return program + ", Scholarship: " + scholarshipName + ", Type: " + scholarshipType + ", durationInMonths: "
                + durationInMonths + ".";
    }
}