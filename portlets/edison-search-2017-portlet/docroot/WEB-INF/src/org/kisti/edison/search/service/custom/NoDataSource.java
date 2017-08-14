package org.kisti.edison.search.service.custom;

import javax.sql.DataSource;

public class NoDataSource{
  /**
   * getDataSource: Supposed to return a DataSource.
   * @return DataSource Always returns null.
   */
  public static DataSource getDataSource(){
    return null;
  }
}
