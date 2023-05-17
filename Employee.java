import java.util.Date;


/**
 * This abstract class is responsible to provide skeleton for all the employees such as farm workers and vets and has all related functionalities.
 *
 *  @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public abstract class Employee implements Payment,Comparable<Employee>, java.io.Serializable {

    private int empID;
    private String gender;
    private Date dateOfBirth;


    /**
     * Instantiates a new Employee.
     *
     * @param empID       the emp id
     * @param gender      the gender
     * @param dateOfBirth the date of birth
     */
    public Employee(int empID, String gender, Date dateOfBirth) {
        this.empID = empID;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Instantiates a new Employee.
     */
    public Employee(){
        dateOfBirth = new Date();
    }


    /**
     * Gets emp id.
     *
     * @return the emp id
     */
    public int getEmpID() {
        return empID;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets emp id.
     *
     * @param empID the emp id
     */
    public void setEmpID(int empID) {
        this.empID = empID;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public int compareTo(Employee obj) {
        if (getSalary() > obj.getSalary())
            return 1;
        else if (getSalary() == obj.getSalary())
            return 0;
        else
            return -1;
    }


}
