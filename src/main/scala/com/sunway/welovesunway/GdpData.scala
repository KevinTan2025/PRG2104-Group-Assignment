package com.sunway.welovesunway

trait GdpData {
  def year: Int
  def country_code: String
  def country_name: String
  def region: String
  def income_group: String
  def currency_unit: String
  def gdp_usd: Double
  def population: Double
  def gdp_per_capita: Double
  def inflation_rate: Double
  def unemployment_rate: Double
  def fdi_pct_gdp: Double
  def co2_emissions_kt: Double
  def energy_use_per_capita: Double
  def renewable_energy_pct: Double
  def forest_area_pct: Option[Double]
  def electricity_access_pct: Double
  def life_expectancy: Option[Double]
  def child_mortality: Option[Double]
  def school_enrollment_secondary: Option[Double]
  def health_expenditure_pct_gdp: Double
  def hospital_beds_per_1000: Double
  def physicians_per_1000: Double
  def internet_usage_pct: Double
  def mobile_subscriptions_per_100: Double
  def calculated_gdp_per_capita: Double
  def real_economic_growth_indicator: Double
  def econ_opportunity_index: Double
  def co2_emissions_per_capita_tons: Double
  def co2_intensity_per_million_gdp: Double
  def green_transition_score: Double
  def ecological_preservation_index: Double
  def renewable_energy_efficiency: Double
  def human_development_composite: Double
  def healthcare_capacity_index: Option[Double]
  def digital_connectivity_index: Double
  def health_development_ratio: Option[Double]
  def education_health_ratio: Double
  def years_since_2000: Int
  def years_since_century: Int
  def is_pandemic_period: Int
  def human_development_index: Double
  def climate_vulnerability_index: Double
  def digital_readiness_score: Double
  def governance_quality_index: Double
  def global_resilience_score: Double
  def global_development_resilience_index: Double
}

case class GdpRecord(
  year: Int,
  country_code: String,
  country_name: String,
  region: String,
  income_group: String,
  currency_unit: String,
  gdp_usd: Double,
  population: Double,
  gdp_per_capita: Double,
  inflation_rate: Double,
  unemployment_rate: Double,
  fdi_pct_gdp: Double,
  co2_emissions_kt: Double,
  energy_use_per_capita: Double,
  renewable_energy_pct: Double,
  forest_area_pct: Option[Double],
  electricity_access_pct: Double,
  life_expectancy: Option[Double],
  child_mortality: Option[Double],
  school_enrollment_secondary: Option[Double],
  health_expenditure_pct_gdp: Double,
  hospital_beds_per_1000: Double,
  physicians_per_1000: Double,
  internet_usage_pct: Double,
  mobile_subscriptions_per_100: Double,
  calculated_gdp_per_capita: Double,
  real_economic_growth_indicator: Double,
  econ_opportunity_index: Double,
  co2_emissions_per_capita_tons: Double,
  co2_intensity_per_million_gdp: Double,
  green_transition_score: Double,
  ecological_preservation_index: Double,
  renewable_energy_efficiency: Double,
  human_development_composite: Double,
  healthcare_capacity_index: Option[Double],
  digital_connectivity_index: Double,
  health_development_ratio: Option[Double],
  education_health_ratio: Double,
  years_since_2000: Int,
  years_since_century: Int,
  is_pandemic_period: Int,
  human_development_index: Double,
  climate_vulnerability_index: Double,
  digital_readiness_score: Double,
  governance_quality_index: Double,
  global_resilience_score: Double,
  global_development_resilience_index: Double
) extends GdpData
