object MainApp extends App {
  val data = DataLoader.loadData("src/main/resources/Global_Development_Indicators_2000_2020.csv")
  // Print the loaded data
  //data.foreach(println)

  //code for test running the analysis, should be modified later

  //Create the Analysis object
  val analysis = new Analysis(data)

  //Question 1 - Highest Life Expectancy
  analysis.highestLifeExpectancy match {
    case Some(country_name, year) =>
      println(f"1. Highest Life Expectancy: $country_name ($year)")
    case None =>
      println("1. No data found for life expectancy.")
  }

  //Question 2 - Best Health & Education Performer
  analysis.bestHealthEducationCountry match {
    case Some(country) =>
      println(f"2. Best Health & Education Performer: $country")
    case None =>
      println("2. No data found for health and education indicators.")
  }

  // Question 3: Highest forest area loss
  analysis.highestForestLossCountry match {
    case Some(country, loss) =>
      println(f"3️. Highest Forest Area Loss: $country, with $loss%.2f%% lost")
    case None =>
      println("3. Not enough forest area data available.")
  }
}
