package it.iseed.utils;

public class UserNotLoggedException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotLoggedException(String errorMessage){
        super(errorMessage);
    }
}
