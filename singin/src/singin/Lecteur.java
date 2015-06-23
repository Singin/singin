package singin;

import java.io.File;
import java.util.List;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.ugens.SamplePlayer;

public class Lecteur {

    /**
     * *************************************************************************
     */
    /**
     * *********************** PROPRIETES
     * **************************************
     */
    private AudioContext ac;
    private Projet projet;
    private singin.AudioRecorder recorder;
    private int numEnregistrement;

    /**
     * *************************************************************************
     */
    /**
     * ************************ CONSTRUCTEURS
     * **********************************
     */
    public Lecteur() {
        ac = new AudioContext();
        recorder = new singin.AudioRecorder();
        projet = null;
    }

    public Lecteur(Projet p) {
        ac = new AudioContext();
        recorder = new singin.AudioRecorder();
        projet = p;
    }

    /**
     * *************************************************************************
     */
    /**
     * *********************** METHODES
     * ****************************************
     */
    public void addSamples(List<Sample> list) {
        for (Sample s : list) {
            SamplePlayer sply = new SamplePlayer(ac, s);
            sply.setLoopType(SamplePlayer.LoopType.NO_LOOP_FORWARDS);
            ac.out.addInput(sply);
        }
        numEnregistrement = list.size() + 1;
    }

    public void addSample(Sample s) {
        SamplePlayer sply = new SamplePlayer(ac, s);
        sply.setLoopType(SamplePlayer.LoopType.NO_LOOP_FORWARDS);
        ac.out.addInput(sply);
    }

    public void removeSamples() {
        ac.reset();
    }

    public void rec(String fileName) {
        //String nomEnregistrement = "sample" + numEnregistrement + ".wav";
        //File audioRecordFile = new File(nomEnregistrement);
        File audioRecordFile = new File(fileName);
        Thread audioRecorderThread = new Thread(recorder);
        audioRecorderThread.setPriority(10);
        recorder.init(audioRecordFile);
        ac.start();
        //Ne rien coder entre ces deux lignes 
        audioRecorderThread.start();
    }

    public void play() {
        ac.start();
    }

    public void pause() {
        ac.stop();
    }

    public void stop() {
        ac.stop();
        ac.reset();
        try {
            recorder.getLine().stop();
            recorder.getLine().close();
        } catch (NullPointerException e) {
        }
    }
	
	public boolean isPlay(){
	  return ac.isRunning();
	}

    /**
     * *************************************************************************
     */
    /**
     * *** GETTERS
     * *************************************************************
     */
    /**
     * *************************************************************************
     */
    /**
     * *** SETTERS
     * *************************************************************
     */
}
