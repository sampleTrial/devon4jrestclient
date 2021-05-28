package com.devonfw.httprestapi.restapi.visitormanagement.logic.api.to;

import com.devonfw.httprestapi.restapi.visitormanagement.common.api.Visitor;
import com.devonfw.module.basic.common.api.to.AbstractEto;

/**
 * Entity transport object of Visitor
 */
public class VisitorEto extends AbstractEto implements Visitor {

  private static final long serialVersionUID = 1L;

  private String username;

  private String password;

  @Override
  public String getUsername() {

    return username;
  }

  @Override
  public void setUsername(String username) {

    this.username = username;
  }

  @Override
  public String getPassword() {

    return password;
  }

  @Override
  public void setPassword(String password) {

    this.password = password;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
    result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    // class check will be done by super type EntityTo!
    if (!super.equals(obj)) {
      return false;
    }
    VisitorEto other = (VisitorEto) obj;
    if (this.username == null) {
      if (other.username != null) {
        return false;
      }
    } else if (!this.username.equals(other.username)) {
      return false;
    }
    if (this.password == null) {
      if (other.password != null) {
        return false;
      }
    } else if (!this.password.equals(other.password)) {
      return false;
    }
    return true;
  }
}
