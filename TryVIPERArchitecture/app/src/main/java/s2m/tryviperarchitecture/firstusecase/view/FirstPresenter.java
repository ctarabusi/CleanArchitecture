package s2m.tryviperarchitecture.firstusecase.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

import s2m.tryviperarchitecture.firstusecase.FirstInteractor;

/**
 * Created by cta on 14/09/15.
 */
public class FirstPresenter
{
    private ArrayAdapter<FirstItem> adapter;

    private FirstInteractor interactor;

    public FirstPresenter(Context context, @NonNull final ArrayAdapter adapter)
    {
        this.adapter = adapter;
        interactor = new FirstInteractor(context, this);
    }

    public void onActivityResumed()
    {
        interactor.openConnection();
        updateItemsInAdapter(interactor.getAllEntries());
    }

    public void onActivityPaused()
    {
        interactor.closeConnection();
    }

    public void updateItemsInAdapter(final List<FirstItem> firstItems)
    {
        adapter.clear();
        adapter.addAll(firstItems);
    }

    public void onCreateEntryButtonClicked()
    {
        interactor.createDBEntry();
    }

    public void onItemClicked(final int position)
    {
        FirstItem firstItemToDelete = adapter.getItem(position);
        interactor.deleteItem(firstItemToDelete);
        updateItemsInAdapter(interactor.getAllEntries());
    }

}
