package com.example.maker.report_card_app;

public class ReportCard {

    private static final String SCHOOL_NAME = "Udacity";
    public static final double TOTAL = 5.0d;

    private String studentName;
    private int rollNumber;

    private int englishMarks;
    private int mathMarks;
    private int physicsMarks;
    private int chemistryMarks;
    private int socialMarks;

    private int sum;
    private double percentage;

    public ReportCard(int socialMarks,
                      int chemistryMarks,
                      int physicsMarks,
                      int mathMarks,
                      int englishMarks,
                      String studentName,
                      int rollNumber) {
        this.socialMarks = socialMarks;
        this.chemistryMarks = chemistryMarks;
        this.physicsMarks = physicsMarks;
        this.mathMarks = mathMarks;
        this.englishMarks = englishMarks;
        this.studentName = studentName;
        this.rollNumber = rollNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getEnglishMarks() {
        return englishMarks;
    }

    public void setEnglishMarks(int englishMarks) {
        this.englishMarks = englishMarks;
    }

    public int getMathMarks() {
        return mathMarks;
    }

    public void setMathMarks(int mathMarks) {
        this.mathMarks = mathMarks;
    }

    public int getPhysicsMarks() {
        return physicsMarks;
    }

    public void setPhysicsMarks(int physicsMarks) {
        this.physicsMarks = physicsMarks;
    }

    public int getChemistryMarks() {
        return chemistryMarks;
    }

    public void setChemistryMarks(int chemistryMarks) {
        this.chemistryMarks = chemistryMarks;
    }

    public int getSocialMarks() {
        return socialMarks;
    }

    public void setSocialMarks(int socialMarks) {
        this.socialMarks = socialMarks;
    }

    public static String getSchoolName() {
        return SCHOOL_NAME;
    }

    private String getGrade(int eng,
                            int math,
                            int phy,
                            int chem,
                            int social) {
        String grade;
        sum = eng + math + phy + chem + social;
        percentage = sum / TOTAL;

        if (percentage >= 90.0) {
            grade = "A";
        } else if (percentage < 90.0 && percentage >= 80.0) {
            grade = "B";
        } else if (percentage < 80.0 && percentage >= 70.0) {
            grade = "C";
        } else if (percentage < 70.0 && percentage >= 60.0) {
            grade = "D";
        } else if (percentage < 60.0) {
            grade = "Fail";
        } else {
            grade = "error";
        }
        return grade;
    }

    public String displayResult() {
        return "University: " + getSchoolName() + '\n' +
                "Student Name: " + studentName + '\n' +
                "Roll Number: " + rollNumber + '\n' +
                "English Marks: " + englishMarks + '\n' +
                "Math Marks: " + mathMarks + '\n' +
                "Physics Marks: " + physicsMarks + '\n' +
                "Chemistry Marks: " + chemistryMarks + '\n' +
                "Social Marks: " + socialMarks + '\n' +
                "Grade: " + getGrade(englishMarks, mathMarks, physicsMarks, chemistryMarks, socialMarks);
    }

    @Override
    public String toString() {
        return "ReportCard{" +
                "studentName='" + studentName + '\'' +
                ", rollNumber=" + rollNumber +
                ", englishMarks=" + englishMarks +
                ", mathMarks=" + mathMarks +
                ", physicsMarks=" + physicsMarks +
                ", chemistryMarks=" + chemistryMarks +
                ", socialMarks=" + socialMarks +
                ", grade='" + getGrade(englishMarks, mathMarks, physicsMarks, chemistryMarks, socialMarks) + '\'' +
                '}';
    }
}
