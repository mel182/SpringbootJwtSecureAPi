package com.melchior.vrolijk.secure_api.Secure.Web.API.interfaces;

import com.melchior.vrolijk.secure_api.Secure.Web.API.customException.UserAlreadyExistException;

import java.util.List;

/**
 * The Database task interface
 * @param <T> Generic object 1
 * @param <U> Generic object 2
 */
public interface DatabaseTask<T,U>
{
    //region Save object function
    /**
     * Save generic object to database
     * @param object The target generic objects 2
     * @return The generic object 1 containing the information about the object that has been stored
     * @throws UserAlreadyExistException Exception when data already exist in the database
     */
    T save(U object) throws UserAlreadyExistException;
    //endregion

    //region Get object function
    /**
     * Get corresponding generic object 1 based on target ID provided
     * @param id The target Object ID
     * @return The corresponding generic object 1
     */
    T get(long id);
    //endregion

    //region Get all generic object 1 stored in database
    /**
     * Get list of all generic object 1 stored in database
     * @return The list of all generic object 1
     */
    List<T> getAll();
    //endregion

    //region Delete object from database
    /**
     * Delete generic object 1 from the database
     * @param id The target object ID
     * @return Generic object 1 containing data of the object that has been removed
     */
    T delete(long id);
    //endregion
}
