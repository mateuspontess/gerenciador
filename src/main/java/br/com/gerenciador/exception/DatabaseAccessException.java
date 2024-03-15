package br.com.gerenciador.exception;

public class DatabaseAccessException extends RuntimeException {
	
	public DatabaseAccessException(String message) {
		super(message);
	}
	public DatabaseAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
