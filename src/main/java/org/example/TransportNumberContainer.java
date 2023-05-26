package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransportNumberContainer {
    private String oldNumber;
    private String newNumber;
    private String country;
    private TransportNumberFormatException ex;
}
