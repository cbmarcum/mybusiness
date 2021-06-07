package net.codebuilders.mybusiness

import grails.gorm.transactions.Transactional
import java.util.Date
import java.sql.Timestamp

@Transactional
class ShopService {


    private String addClause(String where, String clause) {
        return (where ? where + ' and ' : 'where ') + clause
    }

    def getWindowList(Map searchParams) {
        Map recordCriteria = [readOnly:true,timeout:15,max:0,offset:0]

        String windowWhere = ''
        String itemQuery ="""
            from Product p 
        """

        windowWhere = addClause(windowWhere, 'p.inventoryCount >= :inStock')
        searchParams.inStock = 1

        windowWhere = addClause(windowWhere, 'p.dateCreated >= :fromDate')
        windowWhere = addClause(windowWhere, 'p.dateCreated <= :toDate')

        String windowItemQuery = """ from Product p  """



        windowItemQuery = windowItemQuery+windowWhere
        return  Product.executeQuery(windowItemQuery, searchParams, recordCriteria)
    }

    /*
    def getCategories(Date fromDate = null, Date toDate = null) {

        def returnMap = [
                activeProductCategories   : [],
                allActiveProductCategories: [],
                groupedCategories         : [],
                searchParams              : [:]
        ]

        Date date =new Date()
        returnMap.searchParams.toDate = toDate ? toDate : date
        returnMap.searchParams.fromDate = fromDate ? fromDate :date-365

        //**
        // * pick out all active productCategory with products listed / updated in last year
        // *
        // * searchPath is beginning of categoryPath assume we have 11/34/2/44/ it will keep first bit:
        // * 11/
        // * and strip rest off rest
       //  *
        // *

        ///String productQuery = """ select distinct p.productCategories from Product p where"""

        //Do a quick check do we even have any items in shop ?
        def query = "SELECT p.dateCreated from Product p order by p.lastUpdated desc"
        Timestamp stamp = Product.executeQuery(query, [max: 1, offset: 0])[0]

        if (!stamp) {
            //crash out don't go any further than this
            return returnMap
        }
        Date dateCreated = new Date(stamp.time)
        // Lets ensure we find records from when the last ever record was added to system
        // we don't want time scale to be reason nothing is listed at all
        if (dateCreated < returnMap.searchParams.fromDate) {
            returnMap.searchParams.fromDate = dateCreated
        }

        String productQuery = """ 
            select new map
                (
                    pc as productCategory
                )
                
                from Product p join p.productCategory pc 
                where  
                    ((p.dateCreated >= :fromDate or p.lastUpdated >= :fromDate) and
                    (p.dateCreated <= :toDate or p.lastUpdated <= :toDate)) 
                group by p.productCategory   
            """

        Map recordCriteria = [readOnly: true, timeout: 15, max: 0, offset: 0]
        def listing = Product.executeQuery(productQuery, returnMap.searchParams, recordCriteria)
        List finalList = []
        listing?.each { k ->
            finalList << k.productCategory.flatWalkThroughParentsById
        }
        finalList.sort().unique()
        List outputList = []
        finalList.eachWithIndex { String fl, counter ->
            List internalList = []
            if (fl.contains('/')) {
                List ids = fl.split('/')
                internalList += ids?.collect { i -> ProductCategory.get(Long.valueOf(i)) }
            } else {
                internalList += [ProductCategory.get(Long.valueOf(fl))]
            }
            outputList << internalList
        }

        return [categories: outputList, searchParams: returnMap.searchParams]
    }
    */

}