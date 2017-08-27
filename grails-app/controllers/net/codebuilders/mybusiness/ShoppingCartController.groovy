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

import org.grails.plugin.paypal.*

/**
 * Controller class for ShoppingCart
 *
 * Derived from Grails 2 ShoppingCart Plugin by Björn Wilmsmann, MetaSieve
 */

class ShoppingCartController {

    def shoppingCartService // inject service

    def index() {
        // redirect(action: show, params: params)
    }

    // used for public cart
    def show() {

    }

    def list() {}


    def add() {
        def product

        product = Product.get(params.id)

        if (params.version) {
            def version = params.version.toLong()
            if (product.version > version) {
                product.errors.rejectValue("version", "product.optimistic.locking.failure", message(code: "Product.already.updated"))
            } else {
                // product.addToShoppingCart()
                shoppingCartService.addToShoppingCart(product)
            }
        } else {
            // product.addToShoppingCart()
            shoppingCartService.addToShoppingCart(product)
        }
        // render(template:'shoppingCartContent', plugin:'shoppingCart')
        // render(template:'shoppingCartContent')
        redirect(action: show, params: params)
    }

    // add to cart for js effect to render portion (last line of method)
    def add2() {
        def product

        product = Product.get(params.id)

        if (params.version) {
            def version = params.version.toLong()
            if (product.version > version) {
                product.errors.rejectValue("version", "product.optimistic.locking.failure", message(code: "Product.already.updated"))
            } else {
                // product.addToShoppingCart()
                shoppingCartService.addToShoppingCart(product)
            }
        } else {
            // product.addToShoppingCart()
            shoppingCartService.addToShoppingCart(product)
        }
        // render(template:'shoppingCartContent', plugin:'shoppingCart')
        render(template: 'shoppingCartContent')

    }

    // add to cart for js effect to render added to cart message
    def add3() {
        println params.toString()
        def product

        product = Product.get(params.id)

        if (params.version) {
            def version = params.version.toLong()
            if (product.version > version) {
                product.errors.rejectValue("version", "product.optimistic.locking.failure", message(code: "Product.already.updated"))
            } else {
                // product.addToShoppingCart()
                // TODO: remove println
                println("in s.c. controller add3")
                println("calling addToCart")
                println("service = ${shoppingCartService}")
                println("product = ${product}")
                println("LOG TEST")
                log.info("LOG TEST")
                shoppingCartService.addToShoppingCart(product)
            }
        } else {
            // product.addToShoppingCart()
            // TODO: remove println
            println("in s.c. controller add3")
            println("calling addToCart")
            println("service = ${shoppingCartService}")
            println("product = ${product}")
            println("LOG TEST else")
            log.info("LOG TEST else")
            shoppingCartService.addToShoppingCart(product)
        }

        render(product.number + ' added to cart')
    }


    def remove() {
        def product

        product = Product.get(params.id)


        if (params.version) {
            def version = params.version.toLong()
            if (product.version > version) {
                product.errors.rejectValue("version", "product.optimistic.locking.failure", message(code: "Product.already.updated"))
            } else {
                // product.removeFromShoppingCart()
                shoppingCartService.removeFromShoppingCart(product)
            }
        } else {
            // product.removeFromShoppingCart()
            shoppingCartService.removeFromShoppingCart(product)
        }

        // render(template:'shoppingCartContent', plugin:'shoppingCart')
        render(template: 'shoppingCartContent')
        // redirect(action:show, params:params)
    }

    def removeAll() {
        def product

        product = Product.get(params.id)


        if (params.version) {
            def version = params.version.toLong()
            if (product.version > version) {
                product.errors.rejectValue("version", "product.optimistic.locking.failure", message(code: "Product.already.updated"))
            } else {
                // product.removeQuantityFromShoppingCart(shoppingCartService.getQuantity(product))
                shoppingCartService.removeFromShoppingCart(product, shoppingCartService.getQuantity(product))
            }
        } else {
            // product.removeQuantityFromShoppingCart(shoppingCartService.getQuantity(product))
            shoppingCartService.removeFromShoppingCart(product, shoppingCartService.getQuantity(product))
        }

        // render(template:'shoppingCartContent', plugin:'shoppingCart')
        render(template: 'shoppingCartContent')
        // redirect(action:show, params:params)
    }


    def checkOut() {
        log.debug "entered checkout"

        def payment = new Payment()
        payment.buyerId = 10


        assert payment.buyerId == 10



        shoppingCartService.getItems().each { item ->

            log.debug ""
            log.debug "item ="
            item.properties.each { log.debug "$it.key -> $it.value" }
            // get the Product from the cart item
            // def product = Product.findByShoppingItem(item) // FIX ME !!
            def product = Product.find(item)
            assert product != null
            log.debug ""
            log.debug "product = ${product}"
            PaymentItem paymentItem = new PaymentItem()
            assert paymentItem != null

            paymentItem.itemName = product.name
            log.debug "paymentItem.itemName = ${paymentItem.itemName}"

            paymentItem.itemNumber = (product.number ?: "No Number")
            log.debug "paymentItem.itemNumber = ${paymentItem.itemNumber}"

            paymentItem.amount = product.listPrice
            log.debug "paymentItem.amount = ${paymentItem.amount}"

            paymentItem.quantity = shoppingCartService.getQuantity(item)
            log.debug "paymentItem.quantity = ${paymentItem.quantity}"

            // only works with our modified paypal paymentItem
            paymentItem.weight = product.shipWeight
            log.debug "paymentItem.weight = ${paymentItem.weight}"

            payment.addToPaymentItems(paymentItem)
            log.debug ""
            log.debug "paymentItem ="
            paymentItem.properties.each { log.debug "$it.key -> $it.value" }
        }

        log.debug "payment before save = "
        payment.properties.each { log.debug "$it.key -> $it.value" }

        payment.save(failOnError: true, flush: true)

        // DEBUG
        log.debug ""
        log.debug "payment after save = "
        payment.properties.each { log.debug "$it.key -> $it.value" }

        // def transactionId = payment.transactionId

        // map for params. all we need is transactionId
        // everything else is saved in payment
        def m = [transactionId: payment.transactionId]

        log.debug ""
        log.debug "m = "
        m.properties.each { log.debug "$it.key -> $it.value" }

        // empty the cart
        shoppingCartService.emptyShoppingCart()


        log.debug "calling redirect..."
        redirect(controller: 'paypal', action: 'uploadCart', params: m)
    }

    // test to check properties instead of redirect to paypal
    def test() {
        println ""
        println "entering test action..."
        // params.properties.each { println "$it.key -> $it.value" }
        println "transactionId = ${params.transactionId}"
        def payment = Payment.findByTransactionId(params.transactionId)
        println payment
    }


}