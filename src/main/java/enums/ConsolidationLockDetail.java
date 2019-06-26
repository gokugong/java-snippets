package enums;

public interface ConsolidationLockDetail
{
    enum AttributeEntity
    {
        ORDER("ORDER"),
        LPN("LPN");

        private final String code;

        private AttributeEntity(String code)
        {
            this.code = code;
        }

        public String getCode()
        {
            return this.code;
        }

        @Override
        public String toString() { return getCode(); }
    }

    enum OrderLockBy
    {
        ORDER_ID("orderId"),
        PRIORITY("priority"),
        FULFILLMENT_STATUS("fulfillmentStatus"),
        PICKUP_START_DATE_TIME("pickupStartDateTime"),
        PICKUP_END_DATE_TIME("pickupEndDateTime"),
        ORDER_TYPE("orderType"),
        DESIGNATED_SHIP_VIA("designatedShipVia"),
        DELIVERY_START_DATE_TIME("deliveryStartDateTime"),
        DELIVERY_END_DATE_TIME("deliveryEndDateTime"),
        ORIGIN_FACILITY_ID("originFacilityId"),
        DESTINATION_FACILITY_ID("destinationFacilityId"),
        HOT_ORDER("hotOrder"),
        LAST_PRIORITIZED_DATE_TIME("lastPrioritizedDateTime"),
        SINGLE_UNIT_ORDER("singleUnitOrder"),
        LAST_SEQUENCE_ID("lastSequenceId"),
        PIPELINE_ID("pipelineId"),
        LINE_HAUL_SHIP_VIA("lineHaulShipVia"),
        SHIPMENT_ID("shipmentId"),
        ORDERED_DATE_TIME("orderedDateTime"),
        IS_CANCELLED("isCancelled"),
        MERCHANDIZING_DEPT("merchandizingDept"),
        MONETARY_VALUE("monetaryValue"),
        CURRENCY_CODE("currencyCode"),
        BILLING_METHOD_ID("billingMethodId"),
        BUSINESS_PARTNER_ID("businessPartnerId"),
        MUST_RELEASE_BY_DATE_TIME("mustReleaseByDateTime"),
        IS_ORIGINAL_ORDER("isOriginalOrder"),
        PRODUCT_CLASS_ID("productClassId"),
        IS_PERISHABLE("isPerishable"),
        PROTECTION_LEVEL_ID("protectionLevelId"),
        IS_HAZMAT("isHazmat"),
        DANGEROUS_GOODS_ID("dangerousGoodsId"),
        PRODUCTION_SCHEDULE_REF_NBR("productionScheduleRefNbr"),
        REFERENCE_FIELD_1("referenceField1"),
        REFERENCE_FIELD_2("referenceField2"),
        REFERENCE_FIELD_3("referenceField3"),
        REFERENCE_FIELD_4("referenceField4"),
        REFERENCE_FIELD_5("referenceField5"),
        REFERENCE_FIELD_6("referenceField6"),
        REFERENCE_FIELD_7("referenceField7"),
        REFERENCE_FIELD_8("referenceField8"),
        REFERENCE_FIELD_9("referenceField9"),
        REFERENCE_FIELD_10("referenceField10"),
        REFERENCE_NUMBER_FIELD_1("referenceNumberField1"),
        REFERENCE_NUMBER_FIELD_2("referenceNumberField2"),
        REFERENCE_NUMBER_FIELD_3("referenceNumberField3"),
        REFERENCE_NUMBER_FIELD_4("referenceNumberField4"),
        REFERENCE_NUMBER_FIELD_5("referenceNumberField5"),
        NMFC_FRIGHT_CLASS("nmfcFreightClass"),
        RESIDENTIAL_DELIVERY_REQUIRED("residentialDeliveryRequired"),
        INCOTERM_NAME("incotermName"),
        DESIGNATED_MODE_ID("designatedModeId"),
        DESIGNATED_CARRIER_ID("designatedCarrierId"),
        DESIGNATED_SERVICE_LEVEL_ID("designatedServiceLevelId"),
        DESIGNATED_EQUIPMENT_ID("designatedEquipmentId"),
        DESIGNATED_TRACTOR_ID("designatedTractorId"),
        DESIGNATED_DRIVER_TYPE("designatedDriverType"),
        IS_BACK_ORDERED("isBackOrdered"),
        CUSTOMER_ID("customerId"),
        CUSTOMER_NAME("customerName"),
        FEDERATED_STORE_NBR("federatedStoreNbr"),
        DESTINATION_FACILITY_NAME("destinationFacilityName"),
        DESTINATION_ADDRESS_LINE_1("destinationAddressLine1"),
        DESTINATION_ADDRESS_LINE_2("destinationAddressLine2"),
        DESTINATION_ADDRESS_LINE_3("destinationAddressLine3"),
        DESTINATION_CITY("destinationCity"),
        DESTINATION_COUNTY("destinationCounty"),
        DESTINATION_COUNTRY("destinationCountry"),
        DESTINATION_STATE_OR_PROVINCE("destinationStateOrProvince"),
        DESTINATION_POSTAL_CODE("destinationPostalCode"),
        SHIP_THRU_FACILITY_ALIAS_ID("shipThruFacilityAliasId"),
        DESTINATION_GLOBAL_LOCATION_NBR("destinationGlobalLocationNbr"),
        PACK_AND_HOLD_FLAG("packAndHoldFlag"),
        PRE_STICKER_ID("preStickerId"),
        MAJOR_ORDER_GROUP_ATTRIBUTE("majorOrderGroupAttribute"),
        SHIP_GROUP_ID("shipGroupId"),
        REF_SHIPMENT_ID("refShipmentId"),
        REF_SHIPMENT_STOP_SEQ_NUMBER("refShipmentStopSeqNumber"),
        BILL_TO_NAME("billToName"),
        BILL_TO_TITLE("billToTitle"),
        BIIL_TO_FACILITY_NAME("billToFacilityName"),
        BILL_TO_ADDRESS_LINE_1("billToAddressLine1"),
        BILL_TO_ADDRESS_LINE_2("billToAddressLine2"),
        BILL_TO_ADDRESS_LINE_3("billToAddressLine3"),
        BILL_TO_CITY("billToCity"),
        BILL_TO_COUNTY("billToCounty"),
        BILL_TO_COUNTRY_CODE("billToCountryCode"),
        BILL_TO_STATE_OR_PROV("billToStateOrProv"),
        BILL_TO_POSTAL_CODE("billToPostalCode"),
        ROUTE_TO("routeTo"),
        ROUTE_TYPE_1("routeType1"),
        ROUTE_TYPE_2("routeType2"),
        ROUTING_SHIP_GROUP_NUMBER("routingShipGroupNumber"),
        ACT_RECEIVABLE_ACCT_NBR("acctReceivableAcctNbr"),
        SCHEDULED_DELIVERY_END_DATE("scheduledDeliveryEndDate"),
        ROUTING_ATTRIBUTE("routingAttribute"),
        SCHEDULED_PICKUP_DATE("scheduledPickupDate"),
        DESIGNATED_STATIC_ROUTE_ID("designatedStaticRouteId"),
        SPECIAL_INSTRUCTION_CODE_1("specialInstructionCode1"),
        SPECIAL_INSTRUCTION_CODE_2("specialInstructionCode2"),
        SPECIAL_INSTRUCTION_CODE_3("specialInstructionCode3"),
        SPECIAL_INSTRUCTION_CODE_4("specialInstructionCode4"),
        SPECIAL_INSTRUCTION_CODE_5("specialInstructionCode5"),
        SPECIAL_INSTRUCTION_CODE_6("specialInstructionCode6"),
        SPECIAL_INSTRUCTION_CODE_7("specialInstructionCode7"),
        SPECIAL_INSTRUCTION_CODE_8("specialInstructionCode8"),
        SPECIAL_INSTRUCTION_CODE_9("specialInstructionCode9"),
        SPECIAL_INSTRUCTION_CODE_10("specialInstructionCode10");

        private final String code;

        private OrderLockBy(String code)
        {
            this.code = code;
        }

        public String getCode()
        {
            return this.code;
        }

        @Override
        public String toString() { return getCode(); }

    }

    enum LpnLockBy
    {

        LPN_ID("lpnId"),
        CURRENT_FACILITY_ID("currentFacilityId"),
        LPN_TYPE("lpnType"),
        LPN_SIZE_TYPE_ID("lpnSizeTypeId"),
        DESTINATION_FACILITY_ID("destinationFacilityId"),
        LPN_FACILITY_STATUS("lpnFacilityStatus"),
        ORDER_ID("orderId"),
        SHIPMENT_ID("shipmentId"),
        SHIP_VIA_ID("shipViaId"),
        IS_CONVEYABLE("isConveyable"),
        FIRST_ZONE_ID("firstZoneId"),
        LAST_ZONE_ID("lastZoneId"),
        CONTAINER_TYPE_ID("containerTypeId"),
        CONTAINER_SIZE_ID("containerSizeId"),
        STATIC_ROUTE_ID("staticRouteId"),
        REFERENCE_SHIPMENT_ID("referenceShipmentId"),
        IS_LPN_LEVEL_ROUTING("isLpnLevelRouting"),
        CALCULATED_CUTOFF_TIMESTAMP("calculatedCutoffTimestamp"),
        BILLING_METHOD("billingMethod"),
        CN22_NUMBER("cn22Number"),
        IS_DRY_ICE("isDryIce"),
        DESTINATION_SUB_LOCAITON_ID("destinationSubLocationId"),
        EPI_PACKAGE_STATUS("epiPackageStatus"),
        LPN_LABEL_TYPE_ID("lpnLabelTypeId"),
        MANIFEST_ID("manifestId"),
        CURRENT_LOCATION("currentLocation"),
        ESTIMATED_WEIGHT("estimatedWeight"),
        ESTIMATED_VOLUME("estimatedVolume"),
        INITIAL_SHIP_VIA_ID("initialShipViaId"),
        IS_CHASE("isChase"),
        IS_MACHINABLE("isMachinable"),
        ORIGIN_FACILITY_ID("originFacilityId"),
        PICK_SUB_LOCATION_ID("pickSubLocationId"),
        REFERENCE_FIELD_1("referenceField1"),
        REFERENCE_FIELD_2("referenceField2"),
        REFERENCE_FIELD_3("referenceField3"),
        REFERENCE_FIELD_4("referenceField4"),
        REFERENCE_FIELD_5("referenceField5"),
        REFERENCE_FIELD_6("referenceField6"),
        REFERENCE_FIELD_7("referenceField7"),
        REFERENCE_FIELD_8("referenceField8"),
        REFERENCE_FIELD_9("referenceField9"),
        REFERENCE_FIELD_10("referenceField10"),
        REFERENCE_FIELD_11("referenceField11"),
        REFERENCE_FIELD_12("referenceField12"),
        REFERENCE_FIELD_13("referenceField13"),
        REFERENCE_FIELD_14("referenceField14"),
        REFERENCE_FIELD_15("referenceField15"),
        TOTAL_LPN_QTY("totalLpnQty"),
        REFERENCE_NUMBER_1("referenceNumber1"),
        REFERENCE_NUMBER_2("referenceNumber2"),
        REFERENCE_NUMBER_3("referenceNumber3"),
        REFERENCE_NUMBER_4("referenceNumber4"),
        REFERENCE_NUMBER_5("referenceNumber5"),
        PARENT_LPN_ID("parentLpnId"),
        CROSS_REFERENCE_LPN_ID("crossReferenceLpnId"),
        LENGTH("length"),
        WIDTH("width"),
        HEIGH("height"),
        IS_SINGLE_LINE("isSingleLine"),
        ORDER_PLANNING_RUN_ID("orderPlanningRunId"),
        WEIGHT("weight"),
        VOLUME("volume"),
        LPN_CREATION_CODE("lpnCreationCode"),
        REFERENCE_LPN_ID("referenceLpnId");

        private final String code;

        private LpnLockBy(String code)
        {
            this.code = code;
        }

        public String getCode()
        {
            return this.code;
        }

        @Override
        public String toString() { return getCode(); }
    }
}
