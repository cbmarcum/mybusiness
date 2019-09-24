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
import grails.gorm.transactions.Transactional

/**
 * Controller class for ShoppingCart
 *
 * Derived from Grails 2 ShoppingCart Plugin by Björn Wilmsmann, MetaSieve
 */
@Transactional(readOnly = true)
class ShoppingCartController {

    def shoppingCartService // inject service

    def index() {
        // redirect(action: show, params: params)
    }

    // used for public cart
    def show() {

    }

    def list() {}

    @Transactional
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
    @Transactional
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
    @Transactional
    def add3() {
        log.info params.toString()
        def product

        product = Product.get(params.id)

        if (params.version) {
            def version = params.version.toLong()
            if (product.version > version) {
                product.errors.rejectValue("version", "product.optimistic.locking.failure", message(code: "Product.already.updated"))
            } else {
                // product.addToShoppingCart()
                // TODO: remove println
                log.info("in s.c. controller add3")
                log.info("calling addToCart")
                log.info("service = ${shoppingCartService}")
                log.info("product = ${product}")
                log.info("LOG TEST")
                log.info("LOG TEST")
                shoppingCartService.addToShoppingCart(product)
            }
        } else {
            // product.addToShoppingCart()
            // TODO: remove println
            log.info("in s.c. controller add3")
            log.info("calling addToCart")
            log.info("service = ${shoppingCartService}")
            log.info("product = ${product}")
            log.info("LOG TEST else")
            log.info("LOG TEST else")
            shoppingCartService.addToShoppingCart(product)
        }

        render(product.number + ' added to cart')
    }
    
    // add to cart to render quickCartContents
    @Transactional
    def add4() {
        log.info params.toString()
        def product

        product = Product.get(params.id)

        if (params.version) {
            def version = params.version.toLong()
            if (product.version > version) {
                product.errors.rejectValue("version", "product.optimistic.locking.failure", message(code: "Product.already.updated"))
            } else {
                // product.addToShoppingCart()
                // TODO: remove println
                log.info("in s.c. controller add4")
                log.info("params.version = true")
                log.info("calling addToCart")
                log.info("service = ${shoppingCartService}")
                log.info("product = ${product}")
                log.info("LOG TEST 4")
                shoppingCartService.addToShoppingCart(product)
            }
        } else {
            // product.addToShoppingCart()
            // TODO: remove println
            log.info("in s.c. controller add4")
            log.info("params.version = true")
            log.info("calling addToCart")
            log.info("service = ${shoppingCartService}")
            log.info("product = ${product}")
            log.info("LOG TEST 4 else")
            shoppingCartService.addToShoppingCart(product)
        }

        render(template: 'quickCartContent')
    }

    @Transactional
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

    @Transactional
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

    @Transactional
    def checkOut() {
        log.info "entered checkout"

        def payment = new Payment()
        payment.buyerId = 10


        assert payment.buyerId == 10



        shoppingCartService.getItems().each { item ->

            log.info ""
            log.info "item ="
            item.properties.each { log.info "$it.key -> $it.value" }
            // get the Product from the cart item
            // def product = Product.findByShoppingItem(item) // FIX ME !!
            def product = Product.find(item)
            assert product != null
            log.info ""
            log.info "product = ${product}"
            PaymentItem paymentItem = new PaymentItem()
            assert paymentItem != null

            paymentItem.itemName = product.name
            log.info "paymentItem.itemName = ${paymentItem.itemName}"

            paymentItem.itemNumber = (product.number ?: "No Number")
            log.info "paymentItem.itemNumber = ${paymentItem.itemNumber}"

            paymentItem.amount = product.listPrice
            log.info "paymentItem.amount = ${paymentItem.amount}"

            paymentItem.quantity = shoppingCartService.getQuantity(item)
            log.info "paymentItem.quantity = ${paymentItem.quantity}"

            // only works with our modified paypal paymentItem
            paymentItem.weight = product.shipWeight
            log.info "paymentItem.weight = ${paymentItem.weight}"

            payment.addToPaymentItems(paymentItem)
            log.info ""
            log.info "paymentItem ="
            paymentItem.properties.each { log.info "$it.key -> $it.value" }
        }

        log.info "payment before save = "
        payment.properties.each { log.info "$it.key -> $it.value" }

        payment.save(failOnError: true, flush: true)

        // DEBUG
        log.info ""
        log.info "payment after save = "
        payment.properties.each { log.info "$it.key -> $it.value" }

        // def transactionId = payment.transactionId

        // map for params. all we need is transactionId
        // everything else is saved in payment
        def m = [transactionId: payment.transactionId]

        log.info ""
        log.info "m = "
        m.properties.each { log.info "$it.key -> $it.value" }

        // empty the cart
        shoppingCartService.emptyShoppingCart()


        log.info "calling redirect..."
        redirect(controller: 'paypal', action: 'uploadCart', params: m)
    }

    // test to check properties instead of redirect to paypal
    def test() {
        log.info ""
        log.info "entering test action..."
        // params.properties.each { println "$it.key -> $it.value" }
        log.info "transactionId = ${params.transactionId}"
        def payment = Payment.findByTransactionId(params.transactionId)
        log.info payment
    }
    
    
    // COPIED these 2 actions FROM ProductController
    // TODO: test using these from everywhere and removing the 2 in product controller.
    def ajaxUpdateCartQty() {

        log.info "entered ajaxUpdateCartQty"
        def cartQty = shoppingCartService.getItems().size()
        log.info "qty=${cartQty}"
        render "${cartQty}"
    }
    
    def ajaxUpdateQuickCartContent() {

        log.info "entered ajaxUpdateQuickCartContent"
        render(template: '/shoppingCart/quickCartContent')
    }


}