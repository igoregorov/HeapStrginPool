package org.example;


import java.util.Date;

/**
 * Created by admin on 06.09.2017.
 */
public interface ICaduceusTransportation {

    public String getTransportNumber() ;
    public void setTransportNumber(String transportNumber) ;

    public Boolean getWithoutTransportation() ;
    public void setWithoutTransportation( Boolean withoutTransportation ) ;

    public String getOldTransportNumber() ;
    public void setOldTransportNumber( String oldTransportNumber ) ;

}
