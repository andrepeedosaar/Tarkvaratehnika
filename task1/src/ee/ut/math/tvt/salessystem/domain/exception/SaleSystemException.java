package ee.ut.math.tvt.salessystem.domain.exception;

public class SaleSystemException extends Exception{
	
	private String message;

	private static final long serialVersionUID = -4211156645500262486L;
	public SaleSystemException() {
	}
	
	
	public SaleSystemException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
