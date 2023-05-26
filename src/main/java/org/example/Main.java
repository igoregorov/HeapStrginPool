package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TransportNumberConverter converter = new TransportNumberConverter();
        Document document = new Document();
        document.setTransportNumber("А973ХУ799");
        document.setWithoutTransportation(false);
        try {
            //converter.transportNumberFormatter(document);
            Transliterator.correspondFormatNumber(document.getTransportNumber());
        } catch (TransportNumberFormatException e) {
            System.out.println("Exception " + e.getMessage());
        }



        System.out.println(document.getTransportNumber());
        System.out.println(document.getOldTransportNumber());
    }
}