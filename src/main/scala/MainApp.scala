object MainApp extends App {
  val data = DataLoader.loadData("src/main/resources/Global_Development_Indicators_2000_2020.csv")
  // Print the loaded data
  data.foreach(println)
}
