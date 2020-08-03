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
//import javafx.scene.input.ScrollEvent
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

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
  //			CATEGORIES VIEW
  @FXML lateinit var categoryView: TableView<SomeProperty>
  @FXML lateinit var category: TableColumn<SomeProperty,String>
  //keyword table
	//for the last KEYWORD VIEW 
   @FXML lateinit var historicalView: TableView<FamousPerson>
   @FXML lateinit var keyword_1: TableColumn<FamousPerson, String>
   @FXML lateinit var frequency_1: TableColumn<FamousPerson,String>
   @FXML lateinit var keyword_2: TableColumn<FamousPerson, String>
   @FXML lateinit var frequency_2: TableColumn<FamousPerson, String>
   
   @FXML lateinit var keyword_3: TableColumn<FamousPerson, String>
   @FXML lateinit var frequency_3: TableColumn<FamousPerson, String>
   @FXML lateinit var keyword_4: TableColumn<FamousPerson, String>
   @FXML lateinit var frequency_4: TableColumn<FamousPerson, String>

   val famousPersonModel = FXCollections.observableArrayList<FamousPerson>()
   val listModel = FXCollections.observableArrayList<String>()
   val tableModel = FXCollections.observableArrayList<SomeProperty>()
   val categoryListModel = FXCollections.observableArrayList<SomeProperty>()

object Fruits : Table("KeywordExpanded") {
    val id = integer("idKeywordExpanded").primaryKey()
    val m_Keyword_1 = varchar("keyword_1", length =56)
    val m_Frequency_1 = varchar("frequency_1", length =55)
    val m_Keyword_2 = varchar("keyword_2", length =56)
    val m_Frequency_2 = varchar("frequency_2", length =55)
    val m_Keyword_3 = varchar("keyword_3", length =56)
    val m_Frequency_3 = varchar("frequency_3", length =55)
    val m_Keyword_4 = varchar("keyword_4", length =56)
    val m_Frequency_4 = varchar("frequency_4", length =55) 
  }


//data class Statistica(val id: Int)
data class Fruit(val id: Int, val m_Keyword_1:String, val m_Frequency_1: String,
      val m_Keyword_2: String, val m_Frequency_2: String, val m_Keyword_3: String,
      val m_Frequency_3: String, val m_Keyword_4: String, val m_Frequency_4: String)
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
      listModel.add("Giraffe")
      // items are sorted in view
      listModel.add("Bear")
      listModel.add("Giraffe")
      // items are sorted in view
      portField.text = "3306"
  }
  // private fun initializeListTab() {
   //   // Preload some data into listModel, which is then loaded into list via observable property magic
  //    listModel.add("Lion")
   //   listModel.add("Bear")
    //  listModel.add("Giraffe")
      // items are sorted in view
      // items are sorted in view
  //    itemListView.items = listModel.sorted()
  // }
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
      //!-- to change propertyColumn.cellValueFactory = PropertyValueFactory<SomeProperty, String>("name")
 //to change to normal values   
   //  hashtag.cellValueFactory = PropertyValueFactory<SomeProperty, String>("hashtag")
   //  category.cellValueFactory = PropertyValueFactory<SomeProperty, String>("category")
   //  keyword.cellValueFactory = PropertyValueFactory<SomeProperty, String>("keyword")
   //  hex.cellValueFactory = PropertyValueFactory<SomeProperty, String>("hex")
   //  textHex.cellValueFactory = PropertyValueFactory<SomeProperty, String>("textHex")
    // matchType.cellValueFactory = PropertyValueFactory<SomeProperty, String>("matchType")

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
      keyword_1.cellValueFactory = PropertyValueFactory<FamousPerson, String>("keyword_1")
      keyword_2.cellValueFactory = PropertyValueFactory<FamousPerson, String>("keyword_2")
      frequency_1.cellValueFactory = PropertyValueFactory<FamousPerson, String>("frequency_1")
      frequency_2.cellValueFactory = PropertyValueFactory<FamousPerson, String>("frequency_2")   

      keyword_3.cellValueFactory = PropertyValueFactory<FamousPerson, String>("keyword_3")
      keyword_4.cellValueFactory = PropertyValueFactory<FamousPerson, String>("keyword_4")
      frequency_3.cellValueFactory = PropertyValueFactory<FamousPerson, String>("frequency_3")
      frequency_4.cellValueFactory = PropertyValueFactory<FamousPerson, String>("frequency_4")
   val sortedList = famousPersonModel.sorted()
    sortedList.comparatorProperty().bind(historicalView.comparatorProperty())
    historicalView.items = sortedList
    var sortedCategoriesList = categoryListModel.sorted()
    sortedCategoriesList.comparatorProperty().bind(categoryView.comparatorProperty())
    categoryView.items = sortedCategoriesList
       initDB()
    transaction {
    exec("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'Statistica'") { 
        rs -> while(rs.next()) {
         categoryListModel.add(SomeProperty(rs.getString(4),rs.getString(4)))
     category.cellValueFactory = PropertyValueFactory<SomeProperty, String>("value")
        }
      }
    }
      famousPersonModel.add(FamousPerson(f[Fruits.m_Keyword_1], f[Fruits.m_Frequency_1], f[Fruits.m_Keyword_2], f[Fruits.m_Frequency_2], f[Fruits.m_Keyword_3], f[Fruits.m_Frequency_3], f[Fruits.m_Keyword_4], f[Fruits.m_Frequency_4]))         
     }
   }  
  }



/**
    * Just add a person at random to demonstrate sorting by birth date
    */
   @FXML
   fun addPerson() {
      famousPersonModel.add(FamousPerson("key1","f1","key2","f2","key3","f3","key4","f4"))
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
