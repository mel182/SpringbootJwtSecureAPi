package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

/**
 * This is the API response message model
 *
 * @author Melchior Vrolijk
 */
@SuppressWarnings("ALL")
public class ResponseMessage
{
    //region Local variable
    private String message;
    //endregion

    //region Constructor
    /**
     * Constructor
     * @param message The response message
     */
    public ResponseMessage(String message) {
        this.message = message;
    }
    //endregion

    //region Get response message
    /**
     * Get response message
     * @return The response message
     */
    public String getMessage() {
        return message;
    }
    //endregion

    //region Set response message
    /**
     * Set response message
     * @param message The response message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    //endregion
}
