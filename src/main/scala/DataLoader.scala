import com.github.tototoshi.csv._
import java.io.File

object DataLoader {
  def loadData(filePath: String): Seq[GdpRecord] = {
    val reader = CSVReader.open(new File(filePath))
    val data = reader.allWithHeaders()
    reader.close()

    data.map(row =>
      GdpRecord(
        row("year"),
        row("country_code"),
        row("country_name"),
        row("region"),
        row("income_group"),
        row("currency_unit"),
        row("gdp_usd"),
        row("population"),
        row("gdp_per_capita"),
        row("inflation_rate"),
        row("unemployment_rate"),
        row("fdi_pct_gdp"),
        row("co2_emissions_kt"),
        row("energy_use_per_capita"),
        row("renewable_energy_pct"),
        row("forest_area_pct"),
        row("electricity_access_pct"),
        row("life_expectancy"),
        row("child_mortality"),
        row("school_enrollment_secondary"),
        row("health_expenditure_pct_gdp"),
        row("hospital_beds_per_1000"),
        row("physicians_per_1000"),
        row("internet_usage_pct"),
        row("mobile_subscriptions_per_100"),
        row("calculated_gdp_per_capita"),
        row("real_economic_growth_indicator"),
        row("econ_opportunity_index"),
        row("co2_emissions_per_capita_tons"),
        row("co2_intensity_per_million_gdp"),
        row("green_transition_score"),
        row("ecological_preservation_index"),
        row("renewable_energy_efficiency"),
        row("human_development_composite"),
        row("healthcare_capacity_index"),
        row("digital_connectivity_index"),
        row("health_development_ratio"),
        row("education_health_ratio"),
        row("years_since_2000"),
        row("years_since_century"),
        row("is_pandemic_period"),
        row("human_development_index"),
        row("climate_vulnerability_index"),
        row("digital_readiness_score"),
        row("governance_quality_index"),
        row("global_resilience_score"),
        row("global_development_resilience_index")
      )
    )
  }
}
