//agent file to handle agent-related operations

class Agent(val records: Seq[GdpData]) {
  
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
  
}