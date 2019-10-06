package uk.revolute.bank.util;
/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
public enum ServiceMessage {
  GENERAL_ERROR("Could not able process your request, please try again later"),
  INVALID_ACCOUNT_NUMBER( "Invalid Account Number"),
  INSUFFICIENT_FUND( "Insufficient fund"),
  ACCOUNT_INACTIVE( "Could not able process your request, please contact near by branch"),
  ACCOUNT_CREATE_SUCCESS("Accounts created successfully, Use this Id number for further communications "),
  TRANSFER_SUCCESS( "Amount transferred successfully");

  private final String description;

  private ServiceMessage( String description) {

    this.description = description;
  }

  public String getDescription() {
     return description;
  }


  @Override
  public String toString() {
    return  description;
  }
}