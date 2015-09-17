package s2m.tryviperarchitecture.thirdusecase.interactor;

import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by cta on 17/09/15.
 */
public class RecordInteractor
{
    private MediaRecorder audioRecorder;

    private DataChangeListener dataChangeListener;

    public void setOutput(DataChangeListener dataChangeListener)
    {
        this.dataChangeListener = dataChangeListener;
    }

    public void startRecording()
    {
        if (audioRecorder == null)
        {
            File output = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Recording.mp3");

            audioRecorder = new MediaRecorder();
            audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            audioRecorder.setOutputFile(output.getAbsolutePath());

            try
            {
                audioRecorder.prepare();

            } catch (IllegalStateException | IOException e)
            {
                e.printStackTrace();
                dataChangeListener.issueArised(e.getMessage());
            }
        }

        audioRecorder.start();
    }

    public void stopRecording()
    {
        audioRecorder.stop();
        audioRecorder.release();
    }
}
