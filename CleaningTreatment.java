import java.util.Date;

/**
 * This class is responsible to provide all cleaning details functionalities.
 *
 *  @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class CleaningTreatment extends Treatment {
    private String materialsused;
    private FarmWorker farmWorkerObj;


    /**
     * Instantiates a new Cleaning treatment.
     */
    public CleaningTreatment(){
        farmWorkerObj = new FarmWorker();
    }

    /**
     * Instantiates a new Cleaning treatment.
     *
     * @param dateOfTreatment the date of treatment
     * @param materialsused   the materialsused
     */
    public CleaningTreatment(Date dateOfTreatment, String materialsused) {
        super(dateOfTreatment);
        this.materialsused = materialsused;
        this.farmWorkerObj = new FarmWorker();
    }

    /**
     * Instantiates a new Cleaning treatment.
     *
     * @param dateOfTreatment the date of treatment
     * @param materialsused   the materialsused
     * @param farmWorkerObj   the farm worker obj
     */
    public CleaningTreatment(Date dateOfTreatment, String materialsused, FarmWorker farmWorkerObj) {
        super(dateOfTreatment);
        this.materialsused = materialsused;
        this.farmWorkerObj = farmWorkerObj;
    }

    /**
     * Gets materialsused.
     *
     * @return the materialsused
     */
    public String getMaterialsused() {
        return materialsused;
    }

    /**
     * Sets materialsused.
     *
     * @param materialsused the materialsused
     */
    public void setMaterialsused(String materialsused) {
        this.materialsused = materialsused;
    }



}
