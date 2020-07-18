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

package org.epistatic.app5.controller

import javafx.stage.Modality
import javafx.stage.Stage

/**
 * Superclass of all non-trivial custom dialogs which are essentially modal windows
 * Not for standard message or warning dialogs - use JavaFX dialogs for those
 */
abstract class CustomDialog<T>(ownerStage: Stage, title: String) {

   var stage: Stage = Stage()

   init {
      stage.title = title
      stage.initOwner(ownerStage)
   }

   /**
    * override this to return result from dialog's controller
    */
   abstract fun result(): DialogResult<T>

   /**
    * Show dialog modally and then return the result
    */
   fun showAndWait(): DialogResult<T> {
      stage.initModality(Modality.APPLICATION_MODAL)
      stage.showAndWait()
      return result()
   }
}