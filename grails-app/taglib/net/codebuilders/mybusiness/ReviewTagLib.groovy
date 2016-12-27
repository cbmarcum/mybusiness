package net.codebuilders.mybusiness

/**
 * Created by carl on 12/17/16.
 */
class ReviewTagLib {
    static namespace = "cb" // <cb:bsPaginate>

    /**
     * Renders number of stars based on the body value.
     *
     * Uses font-awesome stars, shows 5 total
     *
     * @attr totalStars total stars to show filled, half, or empty
     */
    def faReviewStars = { attr, body ->

        Double rating = Double.parseDouble(body().toString())
        def hasDecimal = true
        def emptyStars = attr.totalStars.toInteger()

        // use modulo check for decimal
        if ((rating % 1) == 0) {
            hasDecimal = false
        }

        for (int i = 0; i < rating.intValue(); i++) {
            out << '<i class="fa fa-star"></i>'
            emptyStars--
        }
        if (hasDecimal) {
            out << '<i class="fa fa-star-half-o"></i>'
            emptyStars--
        }
        for (int i = 0; i < emptyStars; i++) {
            out << '<i class="fa fa-star-o"></i>'
        }

    } // method

} // class
