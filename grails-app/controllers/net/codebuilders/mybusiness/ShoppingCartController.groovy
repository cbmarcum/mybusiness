/*
 * Copyright 2016-2020 Code Builders, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
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

    def index() {
        if (!session.cart) {
            session.cart = []
            session.cartCounter=[:]
        }
        // redirect(action: show, params: params)
    }

    // used for public cart
    def show() {
    }

    def list() {}

    def add(Long id) {
        if (!session.cart) {
            session.cart = []
            session.cartCounter=[:]
        }
        List allOfThisItem = session?.cart?.findAll{it.id == id}
        Product p = Product.get(id)
        if ( p.inventoryCount - (allOfThisItem?.size()?:0)>=1 ) {
            session.cart << [id: id, name: p.name, listPrice: p.listPrice,
                             age: p.age, gender: p.gender, requestGender:p.requestGender,
                             inventoryCount: p.inventoryCount, shortDescription:p.shortDescription, photo:p?.mainPhoto ]
            session.cartCounter[id]=session?.cart?.findAll{it.id == id}?.size()
        }
        render status: 200, text: ''
    }

    def updateCart(Long id, int qty) {
        List allOfThisItem = session?.cart?.findAll{it.id == id}
        Map firstItem = [:]
        if (!allOfThisItem) {
            Product p = Product.get(id)
            allOfThisItem = [id: id, name: p.name, listPrice: p.listPrice,
                             age: p.age, gender: p.gender, requestGender:p.requestGender,
                             inventoryCount: p.inventoryCount, shortDescription:p.shortDescription, photo:p.mainPhoto ]
            firstItem = allOfThisItem[0]
        } else {
            firstItem = allOfThisItem[0]

            if (qty>firstItem.inventoryCount) {
                qty = firstItem.inventoryCount
            }

            if (qty > allOfThisItem.size() &&(firstItem.inventoryCount - (allOfThisItem?.size()?:0)>=1)) {
                int a = allOfThisItem.size()
                while (a <  qty) {
                    session.cart << firstItem
                    a++
                }
            } else if (qty < allOfThisItem.size()) {
                int a = allOfThisItem.size()
                while (qty < a) {
                    session?.cart?.remove(session.cart.find{it.id == id})
                    a--
                }
            }
        }
        session.cartCounter[id]=session?.cart?.findAll{it.id == id}?.size()
        render status: 200, text: "success"
    }



    @Deprecated
    def remove(Long id) {
        session.cart = session?.cart?.remove(session.cart.find{it.id == id})
        render status: 200, text: 'good'
    }

    @Deprecated
    def removeAll(Long id) {
        session?.cart?.removeAll {it.id == id}
        render status: 200, text: 'good'
    }

    @Transactional
    def checkOut() {
        log.info "entered checkout"

        def payment = new Payment()
        payment.buyerId = 10


        assert payment.buyerId == 10



        session?.cart?.each { item ->

            log.info ""
            log.info "item ="
            item.properties.each { log.info "$it.key -> $it.value" }
            // get the Product from the cart item
            // def product = Product.findByShoppingItem(item) // FIX ME !!
            def product = Product.get(item.id)
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
        session.cart = []
        session.cartCounter=[:]

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
        def cartQty = session?.cart?.size()
        log.info "qty=${cartQty}"
        render "${cartQty}"
    }

    def ajaxUpdateQuickCartContent() {

        log.info "entered ajaxUpdateQuickCartContent"
        render(template: '/shoppingCart/quickCartContent')
    }


}