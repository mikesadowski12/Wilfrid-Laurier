package stats.ejb;

import javax.ejb.Local;

@Local
public interface MyStatsSingletonLocal {
	public void addData(double d); // adds a Double data value to data set.  
	public int getCount(); // returns the number of data elements in the data set.
	public void stats(); // computes the descriptive statistics
	public void saveModel(); // saves the serializable object, StatsSummary (just contains the attributes of the simple statistics), to file stats.bin in `C:/enterprise/tmp/model/` directory (need to create this directory first).
}
