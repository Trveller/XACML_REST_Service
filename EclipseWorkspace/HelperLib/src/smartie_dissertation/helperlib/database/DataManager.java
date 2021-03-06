package smartie_dissertation.helperlib.database;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vedran Semenski
 * 
 * Class is to be used as a template for implementing a DataManager
 * 
 * It is important for classes extending this one to have private constructors in order to ensure the singleton quality of them 
 * Creation of given managers is to be done via the DBManagerFactory
 *
 */
public abstract class DataManager {
	
	private DBManagerFactory holder = null;
		
	public DataManager(){
//		holder = DBManagerFactory.getInstance();
		initialize();		
	};
	
	public void setHolder(DBManagerFactory holder){
		this.holder = holder;
	}

	/**
	 * Establishes a connection with the database and calls configureDB() to make initial configurations.
	 */
	protected abstract void initialize();
	
	/**
	 * Configures the database.
	 */
	protected abstract void configureDB();
	
	/**
	 * Closes the connection with the database and destroys the Manager object.
	 */
	public void close(){
		if(holder != null){
			holder.removeFromList(this);
		}		
	}
}
