package com.g6jamm.stima.domain.exception;

public class SystemException extends Exception {
  /** @author Mohamad, Mathias */
  public SystemException(Exception e) {
    super("Der opstod en fejl. Kontakt venligst system administratoren.");
    e.printStackTrace();
  }
}
