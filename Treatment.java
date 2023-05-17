import java.util.Date;

/**
 * This class is responsible to provide treatment skeleton for all types of treatments and has all related functionalities.
 *
 *  @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class Treatment implements java.io.Serializable {


    private Date dateOfTreatment;


    /**
     * Instantiates a new Treatment.
     */
    public Treatment() {
        dateOfTreatment = new Date();
    }

    /**
     * Instantiates a new Treatment.
     *
     * @param dateOfTreatment the date of treatment
     */
    public Treatment(Date dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }


    /**
     * Gets date of treatment.
     *
     * @return the date of treatment
     */
    public Date getDateOfTreatment() {
        return dateOfTreatment;
    }


    /**
     * Sets date of treatment.
     *
     * @param dateOfTreatment the date of treatment
     */
    public void setDateOfTreatment(Date dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }



}
