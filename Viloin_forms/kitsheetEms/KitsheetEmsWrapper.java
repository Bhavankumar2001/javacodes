package com.altrocks.forms.kitsheetEms;

import java.util.List;

public class KitsheetEmsWrapper {
    private List<ProductionHeaderEntity> entityList;
    private String finalProdOrdersQty;
    private String finalReservations;

    public KitsheetEmsWrapper(List<ProductionHeaderEntity> entityList, String finalProdOrdersQty, String finalReservations) {
        this.entityList = entityList;
        this.finalProdOrdersQty = finalProdOrdersQty;
        this.finalReservations = finalReservations;
    }

    public List<ProductionHeaderEntity> getEntityList() {
        return entityList;
    }

    public String getFinalProdOrdersQty() {
        return finalProdOrdersQty;
    }

    public String getFinalReservations() {
        return finalReservations;
    }
}
