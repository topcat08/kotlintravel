/*
 * Copyright 2019 Serge Merzliakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.epistatic.app4.controller

import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import org.epistatic.app4.model.DateItem
import java.time.OffsetDateTime
import java.time.ZoneOffset


/**
 * Controller for app4/app4.fxml
 */
class ApplicationController {
   @FXML lateinit var exitButton: Button
   @FXML lateinit var dateView: TableView<DateItem>
   @FXML lateinit var keyword_1: TableColumn<DateItem, String>
   @FXML lateinit var frequency_1: TableColumn<DateItem, String>
   @FXML lateinit var keyword_2: TableColumn<DateItem, String>
   @FXML lateinit var frequency_2: TableColumn<DateItem, String>
   @FXML lateinit var keyword_3: TableColumn<DateItem, String>
   @FXML lateinit var frequency_3: TableColumn<DateItem, String>
   @FXML lateinit var keyword_4: TableColumn<DateItem, String>
   @FXML lateinit var frequency_4: TableColumn<DateItem, String>
 
   val model_keyword_1: ObservableList<DateItem> = FXCollections.observableArrayList<DateItem>()
   val model_frequency_1: ObservableList<DateItem> = FXCollections.observableArrayList<DateItem>()
   val model_keyword_2: ObservableList<DateItem> = FXCollections.observableArrayList<DateItem>()
   val model_frequency_2: ObservableList<DateItem> = FXCollections.observableArrayList<DateItem>()
   val model_keyword_3: ObservableList<DateItem> = FXCollections.observableArrayList<DateItem>()
   val model_frequency_3: ObservableList<DateItem> = FXCollections.observableArrayList<DateItem>()
   val model_keyword_4: ObservableList<DateItem> = FXCollections.observableArrayList<DateItem>()
   val model_frequency_4: ObservableList<DateItem> = FXCollections.observableArrayList<DateItem>()

   @FXML
   fun initialize() {
      dateView.items = model_keyword_1
      
      keyword_1.cellValueFactory = PropertyValueFactory<DateItem, String>("label")
      frequency_1.cellValueFactory = PropertyValueFactory<DateItem, String>("label")
      keyword_2.cellValueFactory = PropertyValueFactory<DateItem, String>("keyword_2")
      frequency_2.cellValueFactory = PropertyValueFactory<DateItem, String>("label")
      keyword_3.cellValueFactory = PropertyValueFactory<DateItem, String>("label")
      frequency_3.cellValueFactory = PropertyValueFactory<DateItem, String>("label")
      keyword_4.cellValueFactory = PropertyValueFactory<DateItem, String>("label")
      frequency_4.cellValueFactory = PropertyValueFactory<DateItem, String>("label")




      // cannot do much with this - string limits choices
//      dateStringColumn.cellValueFactory = PropertyValueFactory<DateItem, String>("date")
    //  dateObjectColumn.cellValueFactory = PropertyValueFactory<DateItem, OffsetDateTime>("date")
  //    dateObjectColumn.cellFactory = ModifiedISODateCellFactory()
 //     dateCustomColumn.cellValueFactory = DateCellValueFactory()
  //    dateCustomColumn.cellFactory = ModifiedISODateCellFactory()

      // Offset date by 10 seconds
    //  dateCustomColumn2.cellValueFactory = DateOffsetCellValueFactory(40)
     // dateCustomColumn2.cellFactory = CustomDateCellFactory()

      // Use Lambda to create factories
//      dateLambdaColumn.setCellValueFactory { cell: TableColumn.CellDataFeatures<DateItem, OffsetDateTime> -> ReadOnlyObjectWrapper(cell.value.date) }
  //    dateLambdaColumn.setCellFactory {
   //      object : TableCell<DateItem, OffsetDateTime>() {
    //        public override fun updateItem(dt: OffsetDateTime?, empty: Boolean) {
     //          super.updateItem(dt, empty)
      //         if (!empty)
       //           this.text = "Lambda - $dt"
        //    }
         //}
      //}

      add_keyword_1()
//      add_keyword_2()
  //    add_keyword_3()
   //   add_keyword_4()
    //  add_frequency_1()
   //   add_frequency_2()
    //  add_frequency_3()
     // add_frequency_4()
    }


   @FXML
   fun closeApplication() {
      val stage = exitButton.scene.window as Stage
      stage.close()
   }

   /**
    * Add items to table
    */
   private fun add_keyword_1() {
      var item = DateItem("Green Tree", createDate(2018, 2, 3))
      model_keyword_1.add(item)
      item = DateItem("Red Car", createDate(2017, 5, 19))
      model_keyword_1.add(item)
      item = DateItem("Blue Ship", createDate(2016, 12, 19))
      model_keyword_1.add(item)
      item = DateItem("Yellow Moon", createDate(1990, 4, 4))
      model_keyword_1.add(item)
   }
   private fun add_frequency_1() {
      var item = DateItem("13", createDate(2018, 2, 3))
      model_frequency_1.add(item)
      item = DateItem("3", createDate(2017, 5, 19))
      model_keyword_2.add(item)
      item = DateItem("3", createDate(2016, 12, 19))
      model_frequency_2.add(item)
      item = DateItem("4", createDate(1990, 4, 4))
      model_keyword_3.add(item)
   }

   private fun add_keyword_2() {
      var item = DateItem("Green Tree", createDate(2018, 2, 3))
      model_keyword_2.add(item)
      item = DateItem("Red Car", createDate(2017, 5, 19))
      model_keyword_2.add(item)
      item = DateItem("Blue Ship", createDate(2016, 12, 19))
      model_keyword_2.add(item)
      item = DateItem("Yellow Moon", createDate(1990, 4, 4))
      model_keyword_2.add(item)
   }


      private fun add_frequency_2() {
      var item = DateItem("4", createDate(2018, 2, 3))
      model_frequency_2.add(item)
      item = DateItem("12", createDate(2017, 5, 19))
      model_frequency_2.add(item)
      item = DateItem("4", createDate(2016, 12, 19))
      model_frequency_2.add(item)
      item = DateItem("5", createDate(1990, 4, 4))
      model_frequency_2.add(item)
   }

 private fun add_keyword_3() {
      var item = DateItem("Car", createDate(2018, 2, 3))
      model_keyword_3.add(item)
      item = DateItem ("Red Car", createDate(2017, 5, 19))
      model_keyword_3.add(item)
      item = DateItem ("Blue Ship", createDate(2016, 12, 19))
      model_keyword_3.add(item)
      item = DateItem("Yellow Moon", createDate(1990, 4, 4))
      model_keyword_3.add(item)
   }

     private fun add_frequency_3() {
      var item = DateItem("13", createDate(2018, 2, 3))
      model_frequency_3.add(item)
      item = DateItem("3", createDate(2017, 5, 19))
      model_frequency_3.add(item)
      item = DateItem("2", createDate(2016, 12, 19))
      model_frequency_3.add(item)
      item = DateItem("5", createDate(1990, 4, 4))
      model_frequency_3.add(item)
   }


 private fun add_keyword_4() {
      var item = DateItem("Green Tree", createDate(2018, 2, 3))
      model_keyword_4.add(item)
      item = DateItem ("Red Car", createDate(2017, 5, 19))
      model_keyword_4.add(item)
      item = DateItem ("Blue Ship", createDate(2016, 12, 19))
      model_keyword_4.add(item)
      item = DateItem("Yellow Moon", createDate(1990, 4, 4))
      model_keyword_4.add(item)
   }

     private fun add_frequency_4() {
      var item = DateItem("21", createDate(2018, 2, 3))
      model_frequency_4.add(item)
      item = DateItem("2", createDate(2017, 5, 19))
      model_frequency_4.add(item)
      item = DateItem("4", createDate(2016, 12, 19))
      model_frequency_4.add(item)
      item = DateItem("5", createDate(1990, 4, 4))
      model_frequency_4.add(item)
   }


   private fun createDate(year: Int, month: Int, day: Int): OffsetDateTime {
      return OffsetDateTime.of(year, month, day, 14, 30, 0, 0, ZoneOffset.UTC)
   }
}
