import java.time.*;
import java.util.Date;

/**
 * This class is responsible to provide a veterinary and has all related functionalities.
 *
 * @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class Veterinary extends Employee{

    private boolean BScDegree;
    private Date dateOfGraduation;
    private int expertiseLevel;


    /**
     * Instantiates a new Veterinary.
     */
    public Veterinary(){
    }

    /**
     * Instantiates a new Veterinary.
     *
     * @param BScDegree        the b sc degree
     * @param dateOfGraduation the date of graduation
     * @param expertiseLevel   the expertise level
     */
    public Veterinary(boolean BScDegree, Date dateOfGraduation, int expertiseLevel) {
        this.BScDegree = BScDegree;
        this.dateOfGraduation = dateOfGraduation;
        this.expertiseLevel = expertiseLevel;
    }

    /**
     * Instantiates a new Veterinary.
     *
     * @param empID            the emp id
     * @param gender           the gender
     * @param dateOfBirth      the date of birth
     * @param BScDegree        the b sc degree
     * @param dateOfGraduation the date of graduation
     * @param expertiseLevel   the expertise level
     */
    public Veterinary(int empID, String gender, Date dateOfBirth, boolean BScDegree, Date dateOfGraduation, int expertiseLevel) {
        super(empID, gender, dateOfBirth);
        this.BScDegree = BScDegree;
        this.dateOfGraduation = dateOfGraduation;
        this.expertiseLevel = expertiseLevel;
    }


    /**
     * Is b sc degree boolean.
     *
     * @return the boolean
     */
    public boolean isBScDegree() {
        return BScDegree;
    }

    /**
     * Gets date of graduation.
     *
     * @return the date of graduation
     */
    public Date getDateOfGraduation() {
        return dateOfGraduation;
    }

    /**
     * Gets expertise level.
     *
     * @return the expertise level
     */
    public int getExpertiseLevel() {
        return expertiseLevel;
    }

    /**
     * Sets b sc degree.
     *
     * @param BScDegree the b sc degree
     */
    public void setBScDegree(boolean BScDegree) {
        this.BScDegree = BScDegree;
    }

    /**
     * Sets date of graduation.
     *
     * @param dateOfGraduation the date of graduation
     */
    public void setDateOfGraduation(Date dateOfGraduation) {
        this.dateOfGraduation = dateOfGraduation;
    }

    /**
     * Sets expertise level.
     *
     * @param expertiseLevel the expertise level
     */
    public void setExpertiseLevel(int expertiseLevel) {
        this.expertiseLevel = expertiseLevel;
    }


    public double getSalary(){

        int diffInGrad;

        Instant I = getDateOfGraduation().toInstant();
        ZonedDateTime Z = I.atZone(ZoneId.systemDefault());
        LocalDate L = Z.toLocalDate();
        Period P =Period.between(L, LocalDate.now());


        diffInGrad= LocalDate.now().getYear() - P.getYears();


        return grossSalary + (0.1*grossSalary* diffInGrad);
    }

}
