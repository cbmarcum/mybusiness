/*
 * *************************************************************
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * *************************************************************
 */

package net.codebuilders.mybusiness

import grails.gorm.transactions.Transactional

/**
 * Service class for ShoppingCart
 *
 * Derived from Grails 2 ShoppingCart Plugin by Björn Wilmsmann, MetaSieve
 */

@Transactional
class ShoppingCartService {

    // boolean transactional = true // TODO: is this transactional redundant ??

    def createShoppingCart() {
        def sessionID = SessionUtils.getSession().id

        def shoppingCart = new ShoppingCart(sessionID:sessionID)
        shoppingCart.save()

        return shoppingCart
    }

    def addToShoppingCart(Product product, Integer qty = 1, ShoppingCart previousShoppingCart = null) {

        log.info("entered add to cart")

        def shoppingCart = getShoppingCart()

        log.info("cart = ${shoppingCart}")

        def quantity = CartQuantity.findByShoppingCartAndProduct(shoppingCart, product)
        if (quantity) {
            quantity.value += qty
        } else {
            shoppingCart.addToItems(product)
            quantity = new CartQuantity(shoppingCart:shoppingCart, product:product, value:qty)
        }
        quantity.save()

        shoppingCart.save()
    }

    def removeFromShoppingCart(Product product, Integer qty = 1, ShoppingCart previousShoppingCart = null) {
        def shoppingCart = getShoppingCart()

        if (!shoppingCart) {
            return
        }

        def quantity = CartQuantity.findByShoppingCartAndProduct(shoppingCart, product)
        if (quantity) {
            if (quantity.value - qty >= 0) {
                quantity.value -= qty
            }
            quantity.save()
        }

        if (quantity.value == 0) {
            // work-around for $$_javassist types in list
            def itemToRemove = shoppingCart.items.find { item ->
                if (item.id == product.id) {
                    return true
                }
                return false
            }
            shoppingCart.removeFromItems(itemToRemove)
            quantity.delete()
        }

        shoppingCart.save()
    }

    def getQuantity(Product product, ShoppingCart previousShoppingCart = null) {
        def shoppingCart = getShoppingCart()
        def quantity = CartQuantity.findByShoppingCartAndProduct(shoppingCart, product)

        return quantity?.value
    }

    def setLastURL(def url, ShoppingCart previousShoppingCart = null) {
        def shoppingCart = getShoppingCart()
        shoppingCart.lastURL = url
        shoppingCart.save()
    }

    def emptyShoppingCart(ShoppingCart previousShoppingCart = null) {
        def shoppingCart = getShoppingCart()
        shoppingCart.items = []

        def quantities = CartQuantity.findAllByShoppingCart(shoppingCart)
        quantities.each { quantity -> quantity.delete() }

        shoppingCart.save()
    }

    Set getItems(ShoppingCart previousShoppingCart = null) {
        def shoppingCart = getShoppingCart()
        log.info("items = ${shoppingCart.items}")
        return shoppingCart.items
    }

    Set checkOut(ShoppingCart previousShoppingCart = null) {
        def shoppingCart = getShoppingCart()

        def checkedOutItems = []
        shoppingCart.items.each { item ->
            def checkedOutItem = [:]
            checkedOutItem['item'] = item
            checkedOutItem['qty'] = getQuantity(item)
            checkedOutItems.add(checkedOutItem)
        }

        shoppingCart.checkedOut = true
        shoppingCart.save()

        return checkedOutItems
    }

    def getShoppingCart(def previousSessionID = null) {
        def sessionID = previousSessionID
        if (!sessionID) {
            sessionID = SessionUtils.getSession().id
        }

        def shoppingCart = ShoppingCart.findBySessionIDAndCheckedOut(sessionID, false)

        if (!shoppingCart) {
            shoppingCart = createShoppingCart()
        }

        // DEBUG
        log.info("shopping cart = ${shoppingCart}")

        return shoppingCart
    }


}
