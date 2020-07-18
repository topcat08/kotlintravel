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

package org.epistatic.app2.model


/**
 * Basic model
 */
class Person(val firstName: String, val lastName: String) {

   val id = hashCode()

   override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (javaClass != other?.javaClass) return false
      other as Person
      if (id != other.id) return false
      return true
   }

   override fun hashCode(): Int {
      var result = firstName.hashCode()
      result = 31 * result + lastName.hashCode()
      return result
   }

   override fun toString(): String {
      return "$firstName $lastName"
   }


}