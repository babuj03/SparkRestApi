package uk.revolute.bank.util;

/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
public enum ServiceError {
  GENERAL_ERROR("0", "Could not able process your request, please try again later"),
  INVALID_ACCOUNT_NUMBER("1", "Invalid Account Number"),
  INSUFFICIENT_FUND("2", "Insufficient fund"),
  ACCOUNT_INACTIVE("3", "Could not able process your request, please contact near by branch");

  private final String code;
  private final String description;

  private ServiceError(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getDescription() {
     return description;
  }

  public String getCode() {
     return code;
  }

  @Override
  public String toString() {
    return code + ": " + description;
  }
}