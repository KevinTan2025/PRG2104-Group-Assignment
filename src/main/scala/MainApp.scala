object MainApp extends App {
  val data = DataLoader.loadData("src/main/resources/Global_Development_Indicators_2000_2020.csv")
  // Print the loaded data
  //data.foreach(println)

  // Step 2: Create the Analysis object
  val analysis = new Analysis(data)

  // Step 3: Question 1 - Highest Life Expectancy
  analysis.highestLifeExpectancy match {
    case Some(country_name, year) =>
      println(s"1. Highest Life Expectancy: $country_name ($year)")
    case None =>
      println("1. No data found for life expectancy.")
  }

  // Step 4: Question 2 - Best Health & Education Performer
  analysis.bestHealthEducationCountry match {
    case Some(country) =>
      println(s"2. Best Health & Education Performer: $country")
    case None =>
      println("2. No data found for health and education indicators.")
  }
}
