package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class AssistantAccountModel {
    DataBaseHandler db;
    
    public AssistantAccountModel() {
        this.db = DataBaseHandler.getDB();
    }
}
