package s2m.tryviperarchitecture.firstusecase.interactor;

/**
 * Created by cta on 16/09/15.
 */
public interface Interactor
{

    void openConnection();

    void closeConnection();

    void createDBEntry();

    void deleteItem(Comment commentToDelete);

    void setOutput(DataChangeListener dataChangeListener);
}
