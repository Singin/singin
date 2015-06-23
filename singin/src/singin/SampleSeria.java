package singin;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 *
 * @author matt
 */
public class SampleSeria extends net.beadsproject.beads.data.Sample implements Serializable {

  /**
   * *************************************************************************
   */
  /**
   * *** CONSTRUCTEURS *******************************************************
   */
  
  public SampleSeria() {
	super(4294967296.0);
  }

  public SampleSeria(double d) {
	super(d);
  }

  public SampleSeria(double d, int i) {
	super(d, i);
  }

  public SampleSeria(double d, int i, float f) {
	super(d, i, f);
  }

  public SampleSeria(String string) throws IOException {
	super(string);
  }
  
}
