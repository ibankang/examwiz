package examwiz.ibankang.com.SubAdmin;

public class ExamItemClass {

    String examDate;
    String examDay;
    String examType;
    String examName;
    String examTiming;

    public ExamItemClass(String exam, String exam1, String exam2, String exam3, String exam4) {
        this.examDate = exam;
        this.examDay = exam1;
        this.examType = exam2;
        this.examName = exam3;
        this.examTiming = exam4;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getExamDay() {
        return examDay;
    }

    public String getExamType() {
        return examType;
    }

    public String getExamName() {
        return examName;
    }

    public String getExamTiming() {
        return examTiming;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public void setExamDay(String examDay) {
        this.examDay = examDay;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setExamTiming(String examTiming) {
        this.examTiming = examTiming;
    }
}