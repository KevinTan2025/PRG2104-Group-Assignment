package com.sunway.welovesunway

object TestApp extends App {
  println("Testing data loading and analyzer...")
  
  try {
    val data = DataLoader.loadData("src/main/resources/Global_Development_Indicators_2000_2020.csv")
    println(s"Data loaded successfully: ${data.size} records")
    
    val analyzer = new Agent(data)
    
    // Test Q1 - Life Expectancy
    val result1 = analyzer.lifeExpectancyQuery(highest = true)
    result1 match {
      case Some((country, year, value)) =>
        println(s"Highest life expectancy: $country in $year with $value%")
      case None =>
        println("No data found for life expectancy")
    }
    
    // Test Q2 - Health & Education
    val result2 = analyzer.bestHealthEducationQuery(topCountries = 1)
    if (result2.nonEmpty) {
      val (country, score) = result2.head
      println(s"Best health & education: $country with score $score")
    } else {
      println("No data for health & education ranking")
    }
    
    // Test Q3 - Forest Loss
    val result3 = analyzer.forestLossQuery(fromYear = 2000, toYear = 2020, highest = true)
    result3 match {
      case Some((country, loss)) =>
        println(s"Highest forest loss: $country with $loss% loss")
      case None =>
        println("No forest loss data found")
    }
    
    println("All tests completed successfully!")
    
  } catch {
    case e: Exception =>
      println(s"Error: ${e.getMessage}")
      e.printStackTrace()
  }
}
