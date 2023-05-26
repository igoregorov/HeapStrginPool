package org.example;

import static org.example.Utils.nvl;


public class TransportNumberConverter {

    public void transportNumberFormatter(ICaduceusTransportation document) throws TransportNumberFormatException {
        if (document.getTransportNumber() == null && nvl(document.getWithoutTransportation(), false)) {
            return;
        }
        String oldTransportNumber = document.getTransportNumber();
        TransportNumberContainer numberContainer = Transliterator.convertLatinToCyrrilic(oldTransportNumber);
        if (numberContainer.getEx() != null) {
            throw numberContainer.getEx();
        }

        StringBuilder newNumber = new StringBuilder()
                .append(numberContainer.getNewNumber());

        if (isNeedAddCountryCode()) {
            newNumber = newNumber.append(numberContainer.getCountry());
        }

        document.setTransportNumber(newNumber.toString());
        document.setOldTransportNumber(numberContainer.getOldNumber());

    }

    private boolean isNeedAddCountryCode() {
        return true;
    }
}
