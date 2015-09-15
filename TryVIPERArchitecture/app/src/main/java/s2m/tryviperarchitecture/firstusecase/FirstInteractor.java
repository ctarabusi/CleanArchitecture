package s2m.tryviperarchitecture.firstusecase;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import s2m.tryviperarchitecture.firstusecase.entity.DBFirstItem;
import s2m.tryviperarchitecture.firstusecase.entity.FirstEntity;
import s2m.tryviperarchitecture.firstusecase.view.FirstItem;
import s2m.tryviperarchitecture.firstusecase.view.FirstPresenter;

/**
 * Created by cta on 14/09/15.
 */
public class FirstInteractor
{
    private static final String TAG = FirstInteractor.class.getSimpleName();

    private FirstPresenter presenter;
    private FirstEntity firstEntity;

    public FirstInteractor(Context context, FirstPresenter presenter)
    {
        this.presenter = presenter;

        firstEntity = new FirstEntity(context);
        firstEntity.open();
    }

    public void openConnection()
    {
        firstEntity.open();
    }

    public void closeConnection()
    {
        firstEntity.close();
    }

    public void createDBEntry()
    {
        DBFirstItem dbFirstItem = firstEntity.createComment("Created at" + System.currentTimeMillis());
        Log.i(TAG, " createDBEntry " + dbFirstItem.getId());

        presenter.updateItemsInAdapter(getAllEntries());
    }

    public List<FirstItem> getAllEntries()
    {
        // Convert from Entities POJOS to Presenter POJOS
        List<FirstItem> firstItemList = new ArrayList<>();
        for (DBFirstItem dbFirstItem : firstEntity.getAllComments())
        {
            firstItemList.add(new FirstItem(dbFirstItem.getId(), dbFirstItem.getComment()));
        }
        return firstItemList;
    }

    public void deleteItem(FirstItem firstItemToDelete)
    {
        DBFirstItem dbFirstItemToDelete = new DBFirstItem();
        dbFirstItemToDelete.setId(firstItemToDelete.getCommentId());
        firstEntity.deleteComment(dbFirstItemToDelete);
    }
}
