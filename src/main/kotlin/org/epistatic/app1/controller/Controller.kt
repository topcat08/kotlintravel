/*
 * Copyright 2020 Spineci Cosmin-Mugurel
 */
package org.epistatic.app1.controller

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import org.epistatic.app1.model.FamousPerson
import org.epistatic.app1.model.SomeProperty
import java.time.LocalDateTime
import kotlin.random.Random

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Initialized By JavaFX Loader when loading the FXML document.
 * fx:controller attribute in the FXML document maps a "controller" class with an FXML document.
 * A controller is a compiled class that implements the "code behind" the object hierarchy defined by the document.
 * Note: This controller currently manages three Tab Panes, and ideally this should probably be split up
 * into a controller for each logical section of the application.
 */
class Controller {

   // List Tab Controls
   @FXML lateinit var itemListView: ListView<String>
   @FXML lateinit var newItemField: TextField

   // Table Tab Controls
   @FXML lateinit var tableView: TableView<SomeProperty>
   @FXML lateinit var propertyColumn: TableColumn<SomeProperty, String>
   @FXML lateinit var valueColumn: TableColumn<SomeProperty, String>

   // Field Tab Controls
   @FXML lateinit var schemeCombo: ComboBox<String>
   @FXML lateinit var hostField: TextField
   @FXML lateinit var portField: TextField
   @FXML lateinit var generateUrlButton: Button
   @FXML lateinit var urlLabel: Label

   @FXML lateinit var historicalView: TableView<FamousPerson>
   @FXML lateinit var nameColumn: TableColumn<FamousPerson, String>
   @FXML lateinit var yearsColumn: TableColumn<FamousPerson, Int>
   @FXML lateinit var occupationColumn: TableColumn<FamousPerson, String>
   @FXML lateinit var birthColumn: TableColumn<FamousPerson, LocalDateTime>

   val famousPersonModel = FXCollections.observableArrayList<FamousPerson>()
   val listModel = FXCollections.observableArrayList<String>()
   val tableModel = FXCollections.observableArrayList<SomeProperty>()

object Fruits : Table("KeywordExpanded") {
    val id = integer("idKeywordExpanded").primaryKey()
    val name = varchar("keyword_1", length =56)
    val value = varchar("frequency_1", length =55)
}

data class Fruit(val id: Int, val name: String, val value: String)


   /**
    * Called after JavaFX initialized and document loaded
    */
   @FXML
   fun initialize() {
      initializeListTab()
      initializeTableTab()
      initializeFieldTab()
      initializeSortedTableTab()
      hostField.text = "45.56.72.19"
    //  var password = "TravelSourceLLC"
      portField.text = "3306"
  }

   private fun initializeListTab() {
      // Preload some data into listModel, which is then loaded into list via observable property magic
      listModel.add("Lion")
      listModel.add("Bear")
      listModel.add("Giraffe")

      // items are sorted in view
      itemListView.items = listModel.sorted()
   }

   /**
    * Setup model for tableview
    */
   private fun initializeTableTab() {
      // Preload some data
      tableModel.add(SomeProperty("color", "blue"))
      tableModel.add(SomeProperty("gender", "female"))
      tableModel.add(SomeProperty("age", "40"))
      tableModel.add(SomeProperty("city", "Sydney"))

      // items are sorted on property name in view
      tableView.items = tableModel.sorted()

      // map column cell value by simple property lookup by name on SomeProperty type
      propertyColumn.cellValueFactory = PropertyValueFactory<SomeProperty, String>("name")
      valueColumn.cellValueFactory = PropertyValueFactory<SomeProperty, String>("value")
   }


   private fun initializeFieldTab() {
      // fixed list so just use Strings here
      schemeCombo.items.add("http")
      schemeCombo.items.add("https")
      schemeCombo.selectionModel.select(0)
   }

   fun initDB() { 
   
   val url = "jdbc:mysql://root:new-password@localhost:3306/TravelSource?useUnicode=true&serverTimezone=UTC"
   val driver = "com.mysql.cj.jdbc.Driver"
     Database.connect(url,driver)
    }
   
   
   private fun initializeSortedTableTab() {
      nameColumn.cellValueFactory = PropertyValueFactory<FamousPerson, String>("name")
      occupationColumn.cellValueFactory = PropertyValueFactory<FamousPerson, String>("occupation")
      yearsColumn.cellValueFactory = PropertyValueFactory<FamousPerson, Int>("yearsLived")
      birthColumn.cellValueFactory = PropertyValueFactory<FamousPerson, LocalDateTime>("birthDate")

      // setup date of birth column as the single sort column for the table and sort in descending date order
      birthColumn.comparator = Comparator.reverseOrder()
      historicalView.sortOrder.addAll(birthColumn)
    val sortedList = famousPersonModel.sorted()
      sortedList.comparatorProperty().bind(historicalView.comparatorProperty())
      historicalView.items = sortedList


      initDB()
    transaction {
      val res = Fruits.selectAll().orderBy(Fruits.id, false).limit(5)
      val c = ArrayList<Fruit>()
      for (f in res) {
      c.add(Fruit(id = f[Fruits.id], name = f[Fruits.name], value = f[Fruits.value]))
 
famousPersonModel.add(FamousPerson( f[Fruits.name], 91, "Artist", LocalDateTime.of(1881, 10, 25, 0, 0, 0)))
 
        print(c)          
   }
    }
     
  //  initDB()   
    
    }



/**
    * Just add a person at random to demonstrate sorting by birth date
    */
   @FXML
   fun addPerson() {
      val occupations = arrayListOf("Artist", "Businessman", "Composer", "Scientist", "Soldier", "Statesman")
      val names = arrayListOf("Andrew", "Amy", "John", "Mary", "Michael", "Nina", "Patrick", "Stephen", "Zane")
      val surnames = arrayListOf("Bohr", "Chadwick", "Dirac", "Erdos", "Einstein", "Mann", "Starr", "Sommerfeld", "Wolfram")
      val age = Random.nextInt(122,100)
      val year = Random.nextInt(1200, 1900)
      val month = Random.nextInt(1, 12)
      val day = Random.nextInt(1, 28)
      val name = names[Random.nextInt(names.size - 1)]
      val lastName =""// surnames[Random.nextInt(surnames.size - 1)]
      val occupation = ""//occupations[Random.nextInt(occupations.size - 1)]

      famousPersonModel.add(FamousPerson(lastName, age, occupation, LocalDateTime.of(year, month, day, 0, 0, 0)))
   }

   @FXML
   fun clearPeople() {
      famousPersonModel.clear()
   }

   @FXML
   fun closeApplication() {
      val stage = itemListView.scene.window as Stage
      stage.close()
   }

   /*
    * Add item to listView
    */
   @FXML
   fun addItem() {
      val newItem = newItemField.text.trim()

      if (newItemField.text.trim().isEmpty())
         return

      listModel.add(newItem)
   }

   /**
    * Put the URL together in the urlLabel. No input validation
    * here for the sake of simplicity
    */
   @FXML
   fun generateUrlClicked() {
      val scheme = schemeCombo.selectionModel.selectedItem
      val host = hostField.text.trim()
      // Cheating here - no real integer validation -
      // just default to 80 if integer conversion fails
      val port = portField.text.trim().toIntOrNull() ?: 80

      urlLabel.text = "$scheme://$host:$port"
   }
}
