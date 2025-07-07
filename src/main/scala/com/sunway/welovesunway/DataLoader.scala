/**
 * WeLoveSunway API Data Loader
 *
 *
 * Used https://github.com/tototoshi/scala-csv as reference
 * DataLoader.scala
 * This file contains the data loading functionality for the WeLoveSunway application.
 * It handles loading data from CSV files and converting it into a usable format.
 * @author Tan Kok Feng
 * @version 1.0
 */

package com.sunway.welovesunway

import com.github.tototoshi.csv._
import java.io.File

object DataLoader {
  private def safeToDouble(s: String): Double =
    if (s == null || s.trim.isEmpty) 0.0 else s.toDouble
  private def safeToInt(s: String): Int =
    if (s == null || s.trim.isEmpty) 0 else s.toInt
  //use safeToOptionDouble for relevant fields in loadData  
  private def safeToOptionDouble(s: String): Option[Double] =
    if (s == null || s.trim.isEmpty) None else Some(s.toDouble)  

  def loadData(filePath: String): Seq[GdpRecord] = {
    val reader = CSVReader.open(new File(filePath))
    val data = reader.allWithHeaders()
    reader.close()

    data.map(row =>
      GdpRecord(
        safeToInt(row("year")),
        row("country_code"),
        row("country_name"),
        row("region"),
        row("income_group"),
        row("currency_unit"),
        safeToDouble(row("gdp_usd")),
        safeToDouble(row("population")),
        safeToDouble(row("gdp_per_capita")),
        safeToDouble(row("inflation_rate")),
        safeToDouble(row("unemployment_rate")),
        safeToDouble(row("fdi_pct_gdp")),
        safeToDouble(row("co2_emissions_kt")),
        safeToDouble(row("energy_use_per_capita")),
        safeToDouble(row("renewable_energy_pct")),
        safeToOptionDouble(row("forest_area_pct")),
        safeToDouble(row("electricity_access_pct")),
        safeToOptionDouble(row("life_expectancy")),
        safeToOptionDouble(row("child_mortality")),
        safeToOptionDouble(row("school_enrollment_secondary")),
        safeToDouble(row("health_expenditure_pct_gdp")),
        safeToDouble(row("hospital_beds_per_1000")),
        safeToDouble(row("physicians_per_1000")),
        safeToDouble(row("internet_usage_pct")),
        safeToDouble(row("mobile_subscriptions_per_100")),
        safeToDouble(row("calculated_gdp_per_capita")),
        safeToDouble(row("real_economic_growth_indicator")),
        safeToDouble(row("econ_opportunity_index")),
        safeToDouble(row("co2_emissions_per_capita_tons")),
        safeToDouble(row("co2_intensity_per_million_gdp")),
        safeToDouble(row("green_transition_score")),
        safeToDouble(row("ecological_preservation_index")),
        safeToDouble(row("renewable_energy_efficiency")),
        safeToDouble(row("human_development_composite")),
        safeToOptionDouble(row("healthcare_capacity_index")),
        safeToDouble(row("digital_connectivity_index")),
        safeToOptionDouble(row("health_development_ratio")),
        safeToDouble(row("education_health_ratio")),
        safeToInt(row("years_since_2000")),
        safeToInt(row("years_since_century")),
        safeToInt(row("is_pandemic_period")),
        safeToDouble(row("human_development_index")),
        safeToDouble(row("climate_vulnerability_index")),
        safeToDouble(row("digital_readiness_score")),
        safeToDouble(row("governance_quality_index")),
        safeToDouble(row("global_resilience_score")),
        safeToDouble(row("global_development_resilience_index"))
      )
    )
  }
}
