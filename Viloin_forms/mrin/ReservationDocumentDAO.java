package com.altrocks.forms.mrin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDocumentDAO {

    public String Reservation;
    public String GoodsMovementType;
    public String CostCenter;
    public String AssetNumber;
    public String AssetSubNumber;
    public String IssuingOrReceivingPlant;
    public String IssuingOrReceivingStorageLoc;
    public String SalesOrder;
    public String SalesOrderItem;
    public String SalesOrderScheduleLine;
    public String ReservationDate;
    public boolean IsCheckedAgainstFactoryCal;
    public String WBSElement;
    public String ControllingArea;
    public String OrderID;
    public String UserID;
    public String CreationDateTime;
    public String LastChangedByUser;
    public String LastChangeDateTime;
    public String ResvnVerificationCompanyCode;
    public String YY1_PRDUCTIONORDER_RDH;
    public String YY1_Remarks_RDH;
    public String YY1_ProductionOrder_RDH;

    // Optional: Add getters/setters if needed
}

