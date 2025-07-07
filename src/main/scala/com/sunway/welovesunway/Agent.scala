package com.sunway.welovesunway

//agent file to handle agent-related operations

class Agent(val records: Seq[GdpData]) {

  //Utility function to calculate the average from a sequence of Option[Double]
  private def averageOfDefined(seq: Seq[Option[Double]]): Option[Double] = {
    val definedValues = seq.flatten //remove None values
    if (definedValues.isEmpty) None
    else Some(definedValues.sum / definedValues.size)
  }

  //Question 1
  //Get the country/year with the highest or lowest life expectancy.
  def lifeExpectancyQuery(highest: Boolean = true, 
                          filterCountry: Option[String] = None, 
                          filterYear: Option[Int] = None
                         ): Option[(String, Int, Double)] = {
    //Filter records with lifeExpectancy and any user-selected filters
    val filtered = records
      .filter(_.life_expectancy.isDefined)
      .filter(r => filterCountry.forall(_ == r.country_name))
      .filter(r => filterYear.forall(_ == r.year))
    
    //find the highest or lowest value
    val target = if (highest)
      filtered.maxByOption(_.life_expectancy.get)
    else
      filtered.minByOption(_.life_expectancy.get)
      
    //return the country, year and value of that record
    target.map(r => (r.country_name, r.year, r.life_expectancy.get))
  }

  //Question 2
  //Rank countries based on selected health and education indicators.
  def bestHealthEducationQuery(indicators: Seq[String] = Seq("life_expectancy", "child_mortality", "school_enrollment_secondary", "healthcare_capacity_index", "health_development_ratio"),
                               topCountries: Int = 1
                              ): Seq[(String, Double)] = {
    val group = records.groupBy(_.country_name).map{case (country, recs) =>
      //Get the average of each indicator
      val scores = indicators.map{
        case "life_expectancy" => averageOfDefined(recs.map(_.life_expectancy))
        case "child_mortality" => averageOfDefined(recs.map(_.child_mortality)).map(100 - _)
        case "school_enrollment_secondary" => averageOfDefined(recs.map(_.school_enrollment_secondary))
        case "healthcare_capacity_index" => averageOfDefined(recs.map(_.healthcare_capacity_index))
        case "health_development_ratio" => averageOfDefined(recs.map(_.health_development_ratio))
        case _ => None //ignore unknown indicators
      }

      //Sum all indicator scores
      country -> scores.flatten.sum
    }

    //Sort by score and take the top N countries
    group.toSeq.sortBy(-_._2).take(topCountries)
  }

  //Question 3
  //Get the country that lost the highest or lowest forest area between selected years.
  def forestLossQuery(fromYear: Int = 2000,
                      toYear: Int = 2020,
                      highest: Boolean = true,
                      ): Option[(String, Double)] = {

    //Group records by country
    val group = records.groupBy(_.country_name).flatMap{case (country, recs) =>
      val byYear = recs.filter(_.forest_area_pct.isDefined).map(r => r.year -> r.forest_area_pct.get).toMap

      for {
        start <- byYear.get(fromYear)
        end <- byYear.get(toYear)
      } yield {
        val loss = start - end
        country -> loss
      }
    }

    //Return country with the highest or lowest loss
    if (group.isEmpty) None
    else if (highest) Some(group.maxBy(_._2))
    else Some(group.minBy(_._2))
  }
}