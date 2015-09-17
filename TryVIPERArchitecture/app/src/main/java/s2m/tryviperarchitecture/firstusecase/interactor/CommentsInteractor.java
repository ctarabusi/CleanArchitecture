package s2m.tryviperarchitecture.firstusecase.interactor;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import s2m.tryviperarchitecture.firstusecase.entity.CommentEntity;
import s2m.tryviperarchitecture.firstusecase.entity.CommentsDataStore;

/**
 * Created by cta on 14/09/15.
 */
public class CommentsInteractor implements Interactor
{
    private static final String TAG = CommentsInteractor.class.getSimpleName();

    private DataChangeListener dataChangeListener;

    private CommentsDataStore commentsDataStore;

    private Subscription timerSubscription;

    @Inject
    public CommentsInteractor(CommentsDataStore commentsDataStore)
    {
        this.commentsDataStore = commentsDataStore;
    }

    @Override
    public void openConnection()
    {
        commentsDataStore.open();
        dataChanged();

        // While the connection is opened I want to log every 5 second something
        timerSubscription = Observable.interval(2, TimeUnit.SECONDS).
                subscribe(new Observer<Long>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onNext(Long aLong)
                    {
                        Log.i(TAG, "Connection is opened");
                    }
                });
    }

    @Override
    public void closeConnection()
    {
        commentsDataStore.close();
        timerSubscription.unsubscribe();
    }

    @Override
    public void createDBEntry()
    {
        String commentsValue = "Rx Created at" + System.currentTimeMillis();
        Observable.just(commentsValue).map(new Func1<String, CommentEntity>()
        {
            @Override
            public CommentEntity call(String s)
            {
                return commentsDataStore.createComment(s);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommentEntity>()
        {
            @Override
            public void onCompleted()
            {
                dataChanged();
            }

            @Override
            public void onError(Throwable e)
            {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onNext(CommentEntity createdCommentEntity)
            {
                Log.i(TAG, " createDBEntry " + createdCommentEntity.getId());
            }
        });
    }

    public void dataChanged()
    {
        final List<Comment> commentList = new ArrayList<>();
        Observable.from(commentsDataStore.getAllComments()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommentEntity>()
        {
            @Override
            public void onCompleted()
            {
                if (dataChangeListener != null)
                {
                    dataChangeListener.dataChanged(commentList);
                }
            }

            @Override
            public void onError(Throwable e)
            {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onNext(CommentEntity commentEntity)
            {
                commentList.add(new Comment(commentEntity.getId(), commentEntity.getComment()));
            }
        });
    }

    @Override
    public void deleteItem(Comment commentToDelete)
    {
        Observable.just(commentToDelete.getCommentId()).map(new Func1<Long, Object>()
        {
            @Override
            public Object call(Long commentId)
            {
                CommentEntity commentEntityToDelete = new CommentEntity();
                commentEntityToDelete.setId(commentId);
                commentsDataStore.deleteComment(commentEntityToDelete);
                return null;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>()
        {
            @Override
            public void onCompleted()
            {
                dataChanged();
            }

            @Override
            public void onError(Throwable e)
            {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onNext(Object o)
            {

            }
        });
    }

    @Override
    public void setOutput(DataChangeListener dataChangeListener)
    {
        this.dataChangeListener = dataChangeListener;
    }
}
