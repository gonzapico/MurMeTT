package com.gonzapico.murmett.exception;

/**
 * Created by gfernandez on 1/03/17.
 */

public class AudioNotFoundException extends Exception {
  public AudioNotFoundException() {
    super();
  }

  public AudioNotFoundException(final String message) {
    super(message);
  }

  public AudioNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public AudioNotFoundException(final Throwable cause) {
    super(cause);
  }
}
