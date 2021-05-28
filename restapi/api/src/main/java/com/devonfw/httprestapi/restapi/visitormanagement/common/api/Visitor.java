package com.devonfw.httprestapi.restapi.visitormanagement.common.api;

import com.devonfw.httprestapi.restapi.general.common.api.ApplicationEntity;

public interface Visitor extends ApplicationEntity {

  /**
   * @return usernameId
   */

  public String getUsername();

  /**
   * @param username setter for username attribute
   */

  public void setUsername(String username);

  /**
   * @return passwordId
   */

  public String getPassword();

  /**
   * @param password setter for password attribute
   */

  public void setPassword(String password);

}
