import java.util.ArrayList;
import java.util.Date;

/**
 * This class is responsible to provide a health treatment related functionalities.
 *
 *  @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class HealthTreatment extends Treatment{

    private boolean emergency;
    private ArrayList<Medication> medications;
    private Veterinary vet;


    /**
     * Instantiates a new Health treatment.
     */
    public HealthTreatment() {
        medications = new ArrayList<Medication>();
        vet = new Veterinary();
    }

    /**
     * Instantiates a new Health treatment.
     *
     * @param dateOfTreatment the date of treatment
     * @param emergency       the emergency
     */
    public HealthTreatment(Date dateOfTreatment, boolean emergency) {
        super(dateOfTreatment);
        this.emergency = emergency;
        this.medications = new ArrayList<Medication>();
        this.vet = vet;
    }

    /**
     * Instantiates a new Health treatment.
     *
     * @param dateOfTreatment the date of treatment
     * @param emergency       the emergency
     * @param medications     the medications
     */
    public HealthTreatment(Date dateOfTreatment, boolean emergency, ArrayList<Medication> medications) {
        super(dateOfTreatment);
        this.emergency = emergency;
        this.medications = medications;
        this.vet = new Veterinary();
    }

    /**
     * Instantiates a new Health treatment.
     *
     * @param dateOfTreatment the date of treatment
     * @param emergency       the emergency
     * @param medications     the medications
     * @param vet             the vet
     */
    public HealthTreatment(Date dateOfTreatment, boolean emergency, ArrayList<Medication> medications, Veterinary vet) {
        super(dateOfTreatment);
        this.emergency = emergency;
        this.medications = medications;
        this.vet = vet;
    }




    /**
     * Is emergency boolean.
     *
     * @return the boolean
     */
    public boolean isEmergency() {
        return emergency;
    }

    /**
     * Sets emergency.
     *
     * @param emergency the emergency
     */
    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    /**
     * Gets medications.
     *
     * @return the medications
     */
    public ArrayList<Medication> getMedications() {
        return medications;
    }

    /**
     * Sets medications.
     *
     * @param medications the medications
     */
    public void setMedications(Medication medications) {
        this.medications.add(medications);
    }

    /**
     * Gets vet.
     *
     * @return the vet
     */
    public Veterinary getVet() {
        return vet;
    }

    /**
     * Sets vet.
     *
     * @param vet the vet
     */
    public void setVet(Veterinary vet) {
        this.vet = vet;
    }


}
