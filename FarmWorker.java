import java.util.Date;


/**
 * This class is responsible to provide a farm worker and has all related functionalities.
 *
 *  @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class FarmWorker extends Employee{

    private String previousFarmName;
    private int workexperience;


    /**
     * Instantiates a new Farm worker.
     */
    public FarmWorker(){}

    /**
     * Instantiates a new Farm worker.
     *
     * @param previousFarmName the previous farm name
     * @param workexperience   the workexperience
     */
    public FarmWorker(String previousFarmName,int workexperience){
        this.previousFarmName = previousFarmName;
        this.workexperience =workexperience;
    }

    /**
     * Instantiates a new Farm worker.
     *
     * @param empID            the emp id
     * @param gender           the gender
     * @param dateOfBirth      the date of birth
     * @param previousFarmName the previous farm name
     * @param workexperience   the workexperience
     */
    public FarmWorker(int empID, String gender, Date dateOfBirth, String previousFarmName, int workexperience) {
        super(empID, gender, dateOfBirth);
        this.previousFarmName = previousFarmName;
        this.workexperience = workexperience;
    }


    /**
     * Gets previous farm name.
     *
     * @return the previous farm name
     */
    public String getPreviousFarmName() {
        return previousFarmName;
    }

    /**
     * Gets workexperience.
     *
     * @return the workexperience
     */
    public int getWorkexperience() {
        return workexperience;
    }

    /**
     * Sets previous farm name.
     *
     * @param previousFarmName the previous farm name
     */
    public void setPreviousFarmName(String previousFarmName) {
        this.previousFarmName = previousFarmName;
    }

    /**
     * Sets workexperience.
     *
     * @param workexperience the workexperience
     */
    public void setWorkexperience(int workexperience) {
        this.workexperience = workexperience;
    }


    public double getSalary() {
        return grossSalary + (0.02*grossSalary* getWorkexperience());
    }


}
