<?xml version="1.0" encoding="UTF-8"?>
<de.fzi.power:PowerModelRepository xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:de.fzi.power="http://www.fzi.de/Power/Specification/1.0" id="_bOxcMH1gEeWUt_x9QCHofA">
  <powerModelSpecifications xsi:type="de.fzi.power:DeclarativePowerModelSpecification" id="_culCEn1gEeWUt_x9QCHofA" name="Declarative Linear Power Model (Test)" functionalExpression="IdleConsumption + Utilization * (MaxConsumption - IdleConsumption)">
    <consumptionFactors xsi:type="de.fzi.power:FixedFactor" id="_gcoBIH1gEeWUt_x9QCHofA" name="IdleConsumption"/>
    <consumptionFactors xsi:type="de.fzi.power:FixedFactor" id="_hkCVIH1gEeWUt_x9QCHofA" name="MaxConsumption"/>
    <consumptionFactors xsi:type="de.fzi.power:MeasuredFactor" id="_ksukYX1gEeWUt_x9QCHofA" name="Utilization">
      <metricType href="pathmap://METRIC_SPEC_MODELS/models/commonMetrics.metricSpec#_QIb6cikUEeSuf8LV7cHLgA"/>
    </consumptionFactors>
  </powerModelSpecifications>
  <powerModelSpecifications xsi:type="de.fzi.power:DeclarativePowerModelSpecification" id="_on3u0n1hEeWs5NTiSBh3Qw" name="Declarative Linear Passthrough Model (Test)" functionalExpression="SUM(Consumers)+ConstantLoss">
    <consumptionFactors xsi:type="de.fzi.power:FixedFactor" id="_tt_7JX1hEeWs5NTiSBh3Qw" name="ConstantLoss"/>
    <consumptionFactors xsi:type="de.fzi.power:MeasuredFactor" id="_u7zyEX1hEeWs5NTiSBh3Qw" name="Consumers">
      <metricType href="pathmap://METRIC_SPEC_MODELS/models/commonMetrics.metricSpec#_NbIowlt_EeS0LdH-diVVEQ"/>
    </consumptionFactors>
  </powerModelSpecifications>
  <powerModelSpecifications xsi:type="de.fzi.power:DeclarativePowerModelSpecification" id="_p0__YoI6EeW9D58bfYJKTQ" name="Declarative StandBy Power Model (Test)" functionalExpression="Consumption">
    <consumptionFactors xsi:type="de.fzi.power:FixedFactor" id="_19KEAII6EeW9D58bfYJKTQ" name="Standby Consumption"/>
  </powerModelSpecifications>
</de.fzi.power:PowerModelRepository>
