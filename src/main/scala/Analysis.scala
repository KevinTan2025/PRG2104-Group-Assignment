class Analysis(val records: Seq[GdpData]) {

  //Utility function to calculate the average from a sequence of Option[Double]
  private def averageOfDefined(seq: Seq[Option[Double]]): Option[Double] = {
    val definedValues = seq.flatten //remove None values
    if (definedValues.isEmpty) None
    else Some(definedValues.sum / definedValues.size)
  }

  //Question 1
  //Find the country and year with the highest life expectancy.
  def highestLifeExpectancy: Option[(String, Int)] = {
    records
      .filter(_.life_expectancy.isDefined) //keep only records where lifeExpectancy has a value (not None)
      .maxByOption(_.life_expectancy.get) //from those, find the record with the highest life expectancy
      .map(r => (r.country_name, r.year)) //return the country and year of that record
  }

  //Question 2
  //Find the country that performed best in health and education
  //Use life expectancy, child mortality, school enrollment, healthcare capacity and health development ratio to judge
  def bestHealthEducationCountry: Option[String] = {
    //Group records by country name and calculate an average of each relevant metric
    val group: Map[String, Double] = records.groupBy(_.country_name).map{case (country, recs) =>
      val avgLifeExpectancy = averageOfDefined(recs.map(_.life_expectancy))
      val avgChildMortality = averageOfDefined(recs.map(_.child_mortality)).map(100 - _) //turning a lower is better value into a higher is better score
      val avgSchoolEnrollment = averageOfDefined(recs.map(_.school_enrollment_secondary))
      val avgHealthcareCapacity = averageOfDefined(recs.map(_.healthcare_capacity_index))
      val avgHealthDevelopmentRatio = averageOfDefined(recs.map(_.health_development_ratio))

      //sum all the averages for a total score
      val totalScore = List(avgLifeExpectancy, avgChildMortality, avgSchoolEnrollment, avgHealthcareCapacity, avgHealthDevelopmentRatio)
        .flatten
        .sum
      (country, totalScore)
    }

    //return the country with the highest score
    group.maxByOption(_._2).map(_._1)
  }

  //Question 3
  //Find the country that lost the most forest area from 2000 to 2020
  def highestForestLossCountry: Option[String] = {
    //Group records by country
    val group: Map[String, Double] = records.groupBy(_.country_name).flatMap{case (country, recs) =>
      //Create a map of year
      val forestByYear: Map[Int, Double] = recs
        .filter(_.forest_area_pct.isDefined)
        .map(r => r.year -> r.forest_area_pct.get) //keep only records with defined forest area percentage
        .toMap
      
      //Get forest area for 2000 and 2020, calculate the difference(loss)
      for {
        start <- forestByYear.get(2000)
        end <- forestByYear.get(2020)
      } yield country -> (start - end)
    }
    
    //Find the country with the highest loss
    if (group.isEmpty) None else Some(group.maxBy(_._2)._1)
  }
}