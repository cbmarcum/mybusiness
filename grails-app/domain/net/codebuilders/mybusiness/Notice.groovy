package net.codebuilders.mybusiness

/* Class to display a notice on the home page for vacation holiday schedule, etc.
*
*/

class Notice {

    static constraints = {
        name(maxSize: 50)
        longDescription(maxSize: 2000)
    }

    String name = ""  // notice name
    String longDescription = "" // field for html page
    Date fromDate = new Date()
    Date thruDate = new Date()

    boolean display = true // display on page
}