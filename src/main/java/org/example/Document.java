package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Document implements ICaduceusTransportation {
    private String transportNumber;
    private String oldTransportNumber;
    private Boolean withoutTransportation;

}
