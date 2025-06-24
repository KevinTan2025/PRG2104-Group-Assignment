trait HospData {
  def year: String
  def country_code: String
  def country_name: String
  def region: String
  def income_group: String
  def currency_unit: String
  def gdp_usd: String
  def population: String
  def gdp_per_capita: String
  def inflation_rate: String
  def unemployment_rate: String
  def fdi_pct_gdp: String
  def co2_emissions_kt: String
  def energy_use_per_capita: String
  def renewable_energy_pct: String
  def forest_area_pct: String
  def electricity_access_pct: String
  def life_expectancy: String
  def child_mortality: String
  def school_enrollment_secondary: String
  def health_expenditure_pct_gdp: String
  def hospital_beds_per_1000: String
  def physicians_per_1000: String
  def internet_usage_pct: String
  def mobile_subscriptions_per_100: String
  def calculated_gdp_per_capita: String
  def real_economic_growth_indicator: String
  def econ_opportunity_index: String
  def co2_emissions_per_capita_tons: String
  def co2_intensity_per_million_gdp: String
  def green_transition_score: String
  def ecological_preservation_index: String
  def renewable_energy_efficiency: String
  def human_development_composite: String
  def healthcare_capacity_index: String
  def digital_connectivity_index: String
  def health_development_ratio: String
  def education_health_ratio: String
  def years_since_2000: String
  def years_since_century: String
  def is_pandemic_period: String
  def human_development_index: String
  def climate_vulnerability_index: String
  def digital_readiness_score: String
  def governance_quality_index: String
  def global_resilience_score: String
  def global_development_resilience_index: String
}

case class HospRecord(
  year: String,
  country_code: String,
  country_name: String,
  region: String,
  income_group: String,
  currency_unit: String,
  gdp_usd: String,
  population: String,
  gdp_per_capita: String,
  inflation_rate: String,
  unemployment_rate: String,
  fdi_pct_gdp: String,
  co2_emissions_kt: String,
  energy_use_per_capita: String,
  renewable_energy_pct: String,
  forest_area_pct: String,
  electricity_access_pct: String,
  life_expectancy: String,
  child_mortality: String,
  school_enrollment_secondary: String,
  health_expenditure_pct_gdp: String,
  hospital_beds_per_1000: String,
  physicians_per_1000: String,
  internet_usage_pct: String,
  mobile_subscriptions_per_100: String,
  calculated_gdp_per_capita: String,
  real_economic_growth_indicator: String,
  econ_opportunity_index: String,
  co2_emissions_per_capita_tons: String,
  co2_intensity_per_million_gdp: String,
  green_transition_score: String,
  ecological_preservation_index: String,
  renewable_energy_efficiency: String,
  human_development_composite: String,
  healthcare_capacity_index: String,
  digital_connectivity_index: String,
  health_development_ratio: String,
  education_health_ratio: String,
  years_since_2000: String,
  years_since_century: String,
  is_pandemic_period: String,
  human_development_index: String,
  climate_vulnerability_index: String,
  digital_readiness_score: String,
  governance_quality_index: String,
  global_resilience_score: String,
  global_development_resilience_index: String
) extends HospData
